package study.board.member.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
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

}
