package study.security.security.common.auth.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import study.security.security.common.auth.domain.User;

import java.util.*;

@Getter
public class CustomUserDetails implements UserDetails, OAuth2User {

    private final User user;
    private List<GrantedAuthority> authorities;
    private Map<String, Object> oauthUserAttributes;

    private CustomUserDetails(User user, List<GrantedAuthority> authorities,
                          Map<String, Object> oauthUserAttributes) {
        this.user = user;
        this.authorities = authorities;
        this.oauthUserAttributes = oauthUserAttributes;
    }

    public static CustomUserDetails create(User user, Map<String, Object> oauthUserAttributes) {
        return new CustomUserDetails(user, List.of(() -> "ROLE_USER"), oauthUserAttributes);
    }

    public static CustomUserDetails create(User user) {
        return new CustomUserDetails(user, List.of(() -> "ROLE_USER"), new HashMap<>());
    }

    public CustomUserDetails(User user) {
        this.user = user;
    }

    @Override
    public String getName() {
        return user.getEmail();
    }


    @Override
    public Map<String, Object> getAttributes() {
        return Collections.unmodifiableMap(oauthUserAttributes);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.unmodifiableList(authorities);
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
