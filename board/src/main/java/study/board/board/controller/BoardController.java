package study.board.board.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import study.board.board.dto.BoardDTO;
import study.board.board.service.BoardService;

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
    public ResponseEntity<BoardDTO> getBoardById(@PathVariable Long id) {
        BoardDTO boardDTO = boardservice.getBoardById(id);
        return ResponseEntity.status(HttpStatus.OK).body(boardDTO);
    }

    @GetMapping("/myboards/{id}")
    public ResponseEntity<BoardDTO> getBoardByMemberId(@PathVariable Long id) {
        BoardDTO boardDTO = boardservice.getBoardByMemberId(id);
        return ResponseEntity.status(HttpStatus.OK).body(boardDTO);
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
