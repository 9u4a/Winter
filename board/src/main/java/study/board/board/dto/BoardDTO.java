package study.board.board.dto;

import lombok.*;
import study.board.board.domain.Board;
import study.board.user.domain.User;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {

    private String title;
    private String content;
    private User user;

    public BoardDTO(Board board){
        this.title = board.getTitle();
        this.content = board.getContent();
        this.user = board.getUser();
    }
    public Board toEntity() {
        return Board.builder()
                .title(title)
                .content(content)
                .user(user)
                .build();

    }
}
