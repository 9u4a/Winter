package study.security.security.common.auth.dto;

import lombok.*;
import study.security.security.common.auth.domain.Role;
import study.security.security.common.auth.domain.User;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

    private String name;
    private String email;
    private String password;
    private String image;

    public UserDTO(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.image = user.getImage();
    }
    public User toEntity() {
        return User.builder()
                .name(name)
                .email(email)
                .password(password)
                .image(image)
                .role(Role.USER)
                .build();
    }

}
