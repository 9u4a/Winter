package study.board.user.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;


@Entity
@Table(name="users")
@Getter
@Setter
//무분별한 객체 생성 방지
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 15, nullable = false , unique = true)
    private String uid;

    @Column(length = 10, nullable = false)
    private String name;

    @Column(length = 30, nullable = false, unique = true)
    private String email;

    @JsonIgnore
    private String password;

//    @OneToMany(mappedBy ="user", cascade = CascadeType.ALL)
//    private List<Board> boards ;

    @Builder
    public User(String uid, String name, String email, String password) {
        this.uid = uid;
        this.name = name;
        this.email = email;
        this.password = password;
    }


}
