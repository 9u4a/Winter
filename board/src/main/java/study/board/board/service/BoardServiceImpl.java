package study.board.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.board.board.domain.Board;
import study.board.board.dto.BoardDTO;
import study.board.board.dto.ResBoardDTO;
import study.board.board.repository.BoardRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    @Override
    @Transactional
    public BoardDTO createBoard(BoardDTO boardDTO) {
        Board board = boardDTO.toEntity();
        Board savedBoard = boardRepository.save(board);
        return new BoardDTO(savedBoard);
    }

    @Override
    public List<Board> getBoardAll() {
        return null;
    }
    // 구현 필요
//    @Override
//    @Transactional
//    public List<ResBoardDTO> getBoardAll() {
//        return boardRepository.findAll()
//                .stream()
//                .map(this::)
//                .collect(Collectors.toList());
//    }


    @Override
    @Transactional
    public ResBoardDTO getBoardById(Long id) {
        return new ResBoardDTO(boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글.")));
    }

    @Override
    @Transactional
    public List<Board> getBoardByUserId(Long uid) {
        return boardRepository.findByUser_id(uid);
    }

    @Override
    @Transactional
    public BoardDTO updateBoard(Long id, BoardDTO boardDTO) {
        Board board = boardDTO.toEntity();
        board.setId(id);
        Board savedBoard = boardRepository.save(board);
        return new BoardDTO(savedBoard);
    }

    @Override
    @Transactional
    public void deleteBoard(Long id) {
        boardRepository.deleteById(id);
    }


    private BoardDTO buildBoardDTO(Board board) {
        return new BoardDTO(board);
    }
}
