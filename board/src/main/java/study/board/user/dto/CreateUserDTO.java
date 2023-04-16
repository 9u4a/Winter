package study.board.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import study.board.user.domain.User;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserDTO {

    private String uid;
    private String name;
    private String email;
    private String password;

    public CreateUserDTO(User user) {
        this.uid = user.getUid();
        this.name = user.getName();
        this.email = user.getEmail();
        this.password = user.getPassword();
    }

    public User toEntity() {
        return User.builder()
                .uid(uid)
                .name(name)
                .email(email)
                .password(password)
                .build();
    }

    public void encodePassword(BCryptPasswordEncoder encoder) {
        this.password = encoder.encode(password);
    }
}
