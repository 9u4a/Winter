package study.board.board.service;

import study.board.board.domain.Board;
import study.board.board.dto.BoardDTO;
import study.board.board.dto.ResBoardDTO;

import java.util.List;

public interface BoardService {

    BoardDTO createBoard(BoardDTO boardDTO);
    List<Board> getBoardAll();
    ResBoardDTO getBoardById(Long id);
    List<Board> getBoardByUserId(Long id);
    BoardDTO updateBoard(Long id, BoardDTO boardDTO);
    void deleteBoard(Long id);
}
