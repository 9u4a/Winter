package study.board.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import study.board.user.domain.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserDTO {

    @NotBlank(message = "아이디 필요")
    @Pattern(regexp = "^[a-zA-Z0-9]{2,15}$")
    private String uid;
    @NotBlank(message = "이름 필요")
    @Pattern(regexp = "^[가-힣]{2,10}$")
    private String name;
    @Email
    private String email;
    @NotBlank(message = "비밀번호 필요")
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
