package study.security.security.common.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import study.security.security.common.auth.domain.User;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String email;
    private String password;
    private String image;

    public UserDTO(User user) {
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.image = user.getImage();
    }
    public User toEntity() {
        return User.builder()
                .email(email)
                .password(password)
                .image(image)
                .build();
    }

}
