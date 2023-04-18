package study.board.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@AllArgsConstructor
public class LoginUserDTO {

    @NotBlank(message = "아이디 필요")
    @Pattern(regexp = "^[a-zA-Z0-9]{2,15}")
    private String uid;

    @NotBlank(message = "비밀번호 필요")
    private String password;
}
