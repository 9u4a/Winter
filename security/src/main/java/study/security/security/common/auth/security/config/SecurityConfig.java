package study.security.security.common.auth.security.config;


import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.security.ConditionalOnDefaultWebSecurity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import study.security.security.common.auth.repository.UserRepository;
import study.security.security.common.auth.security.jwt.filter.JwtFilter;
import study.security.security.common.auth.security.jwt.service.JwtService;
import study.security.security.common.auth.security.service.CustomOAuth2UserService;


@Configuration
@EnableWebSecurity
@ConditionalOnDefaultWebSecurity
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .httpBasic().disable()
                .formLogin().disable()
                .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/user/**").authenticated()
                .anyRequest().permitAll()

                .and()
                .oauth2Login()
//                .successHandler(CustomOAuth2SuccessHandler)
//                .failureHandler(CustomOAuth2FailureHandler)
                .userInfoEndpoint()
                .userService(customOAuth2UserService);
        return http.build();
    }

    private JwtFilter jwtFilter() {
        return new JwtFilter(jwtService, userRepository);
    }


    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers();
    }

}
