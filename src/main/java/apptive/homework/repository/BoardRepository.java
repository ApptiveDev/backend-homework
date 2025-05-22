package apptive.homework.repository;

import apptive.homework.domain.Board;
import apptive.homework.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    // 기본 CRUD 기능 (save, findById, delete 등)은 JpaRepository가 자동으로 제공함
}
