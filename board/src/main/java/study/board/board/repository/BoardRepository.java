package study.board.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import study.board.board.domain.Board;

import java.util.List;


@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findByMember_id(Long member_id);
}
