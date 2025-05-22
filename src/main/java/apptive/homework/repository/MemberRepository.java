package apptive.homework.repository;

import apptive.homework.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    // 기본 CRUD 기능 (save, findById, delete 등)은 JpaRepository가 자동으로 제공함

    // 메서드 이름 규칙을 따르면 JPA가 자동으로 쿼리를 생성해 구현해 줌
    // 예: findByEmail → SELECT * FROM member WHERE email = ?

    // 나중에 이베일 중복 확인 및 이메일로 게시글을 확인하기 위해 필요한 메서드
    Optional<Member> findByEmail(String email);
}
