package study.security.security.common.auth.security.jwt.filter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.NullAuthoritiesMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;
import study.security.security.common.auth.domain.User;
import study.security.security.common.auth.repository.UserRepository;
import study.security.security.common.auth.security.jwt.service.JwtService;
import study.security.security.common.auth.security.util.PasswordUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Slf4j
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final GrantedAuthoritiesMapper authoritiesMapper = new NullAuthoritiesMapper();



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if(request.getRequestURI().equals("/login")){
            filterChain.doFilter(request, response);
        }else{
            String refreshToken = jwtService.getRefreshToken(request)
                    .filter(jwtService::validRefreshToken)
                    .orElse(null);

            if(refreshToken != null){
                reissueToken(response, refreshToken);
            }if(refreshToken == null){
                AuthenticateUser(request,response,filterChain);
            }
        }
    }

    public void reissueToken(HttpServletResponse response, String refreshToken) {
        userRepository.findByRefreshToken(refreshToken)
                .ifPresent(user-> {
                    jwtService.setAccessToken(response, jwtService.createAccessToken(user.getEmail()));
                    jwtService.setRefreshToken(response, reissueRefreshToken(user));
                });
    }

    public String reissueRefreshToken(User user){
        String refreshToken = jwtService.createRefreshToken();
        user.updateRefreshToken(refreshToken);
        userRepository.saveAndFlush(user);
        return refreshToken;
    }

    public void saveAuthenticationUser(User user){
        String password = user.getPassword();
        if(password == null){
            password = PasswordUtil.randomPassword();
        }
        UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(password)
                .roles(user.getRole().name())
                .build();

        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null,
                authoritiesMapper.mapAuthorities(userDetails.getAuthorities()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    public void AuthenticateUser(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException{
        jwtService.getAccessToken(request)
                .filter(jwtService::validAccessToken)
                .flatMap(jwtService::getEmailByToken)
                .flatMap(userRepository::findByEmail)
                .ifPresent(this::saveAuthenticationUser);

        filterChain.doFilter(request, response);
    }
}
