package study.board.member.domain;

import lombok.*;
import study.board.board.domain.Board;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
//무분별한 객체 생성 방지
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 15, nullable = false , unique = true)
    private String member_id;

    @Column(length = 10, nullable = false)
    private String member_name;

    @Column(length = 30, nullable = false, unique = true)
    private String member_email;

    @OneToMany(mappedBy ="member", cascade = CascadeType.ALL)
    private List<Board> boards ;

    @Builder
    public Member(String member_id, String member_name, String member_email) {
        this.member_id = member_id;
        this.member_name = member_name;
        this.member_email = member_email;
    }

//    @Builder
//    public Member(long id, String member_id, String member_name, String member_email) {
//        this.id = id;
//        this.member_id = member_id;
//        this.member_name = member_name;
//        this.member_email = member_email;
//    }

}
