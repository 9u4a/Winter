package study.board.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import study.board.member.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
