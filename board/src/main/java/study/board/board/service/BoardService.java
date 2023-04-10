package study.board.board.service;

import study.board.board.domain.Board;
import study.board.board.dto.BoardDTO;

import java.util.List;

public interface BoardService {

    BoardDTO createBoard(BoardDTO boardDTO);
    List<Board> getBoardAll();
    BoardDTO getBoardById(Long id);
    BoardDTO getBoardByMemberId(Long id);
    BoardDTO updateBoard(Long id, BoardDTO boardDTO);
    void deleteBoard(Long id);
}
