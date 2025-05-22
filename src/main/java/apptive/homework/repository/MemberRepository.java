package apptive.homework.repository;

import apptive.homework.domain.Member;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserRepository {

    private static Map<Long, Member> DB = new HashMap<>();
    private static long sequence = 0L;


    public Member save(Member member){
        member.setMember_id(++sequence);  // 멤버 ID 할당
        DB.put(member.getMember_id(), member);
        return member;
    }

    // 나중에 이메일 중복 검사를 위해 필요한 메서드
    public Optional<Member> findByEmail(String email) {
        return DB.values().stream()
                .filter(member -> member.getEmail().equals(email))
                .findFirst();
    }

}
