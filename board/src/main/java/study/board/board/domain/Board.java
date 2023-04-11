package study.board.board.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import study.board.member.domain.Member;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(length = 2000, nullable = false)
    private String content;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Board(String title, String content, Member member) {
        this.title = title;
        this.content = content;
        this.member = member;
    }

}
