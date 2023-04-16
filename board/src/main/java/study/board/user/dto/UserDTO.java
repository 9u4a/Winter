package study.board.user.dto;

import lombok.*;
import study.board.user.domain.User;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {


    private String uid;
    private String name;
    private String email;



    public UserDTO(User user) {
        this.uid = user.getUid();
        this.name = user.getName();
        this.email = user.getEmail();
    }
    public User toEntity() {
        return User.builder()
                .uid(uid)
                .name(name)
                .email(email)
                .build();
    }

}
