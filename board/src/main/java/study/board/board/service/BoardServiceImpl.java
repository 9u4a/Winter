package study.board.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.board.board.domain.Board;
import study.board.board.dto.BoardDTO;
import study.board.board.repository.BoardRepository;
import study.board.member.domain.Member;
import study.board.member.repository.MemberRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    @Override
    @Transactional
    public BoardDTO createBoard(BoardDTO boardDTO) {
//        Member member = memberRepository.findById(boardDTO.getMember().getId());
//                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원."));
//        boardDTO.setMember(member);
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
    public BoardDTO getBoardById(Long id) {
        return new BoardDTO(boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글.")));
    }

    @Override
    @Transactional
    public BoardDTO getBoardByMemberId(Long id) {
        return new BoardDTO(boardRepository.findByMemberId(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글.")));
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
