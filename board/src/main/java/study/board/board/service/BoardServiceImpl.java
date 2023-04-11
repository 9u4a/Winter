package study.board.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.board.board.domain.Board;
import study.board.board.dto.BoardDTO;
import study.board.board.dto.ResBoardDTO;
import study.board.board.repository.BoardRepository;

import java.util.List;

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
    @Transactional
    public List<Board> getBoardAll() {
        return boardRepository.findAll();
    }

    @Override
    @Transactional
    public ResBoardDTO getBoardById(Long id) {
        return new ResBoardDTO(boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글.")));
    }

    @Override
    @Transactional
    public List<Board> getBoardByMemberId(Long member_id) {
        return boardRepository.findByMember_id(member_id);
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
}
