package study.board.board.dto;

import lombok.*;
import study.board.board.domain.Board;
import study.board.member.domain.Member;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {

    private String title;
    private String content;
    private Member member;

    public BoardDTO(Board board){
        this.title = board.getTitle();
        this.content = board.getContent();
        this.member = board.getMember();
    }
    public Board toEntity() {
        return Board.builder()
                .title(title)
                .content(content)
                .member(member)
                .build();

    }
}
