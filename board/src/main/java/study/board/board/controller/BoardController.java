package study.board.board.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import study.board.board.domain.Board;
import study.board.board.dto.BoardDTO;
import study.board.board.dto.ResBoardDTO;
import study.board.board.service.BoardService;


import java.util.List;

@RequestMapping("/board")
@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardservice;

    @PostMapping()
    public ResponseEntity<BoardDTO> createBoard(@RequestBody BoardDTO boardDTO) {
        BoardDTO createBoardDTO = boardservice.createBoard(boardDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createBoardDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResBoardDTO> getBoardById(@PathVariable Long id) {
        ResBoardDTO resBoardDTO = boardservice.getBoardById(id);
        return ResponseEntity.status(HttpStatus.OK).body(resBoardDTO);
    }

    @GetMapping("/myboards/{id}")
    public ResponseEntity<List<Board>> getBoardByUserId(@PathVariable Long id) {
        List<Board> board = boardservice.getBoardByUserId(id);
        return ResponseEntity.status(HttpStatus.OK).body(board);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BoardDTO> updateBoard(@PathVariable Long id, @RequestBody BoardDTO updateBoardDTO) {
        BoardDTO boardDTO = boardservice.updateBoard(id, updateBoardDTO);
        return ResponseEntity.status(HttpStatus.OK).body(boardDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBoard(@PathVariable Long id) {
        boardservice.deleteBoard(id);
        return ResponseEntity.noContent().build();

    }

}
