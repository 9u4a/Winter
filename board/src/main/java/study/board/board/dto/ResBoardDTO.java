package study.board.board.dto;

import lombok.*;
import study.board.board.domain.Board;
import study.board.member.domain.Member;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResBoardDTO {

    private String title;
    private String content;

    public ResBoardDTO(Board board){
        this.title = board.getTitle();
        this.content = board.getContent();
    }
    public Board toEntity() {
        return Board.builder()
                .title(title)
                .content(content)
                .build();

    }
}
