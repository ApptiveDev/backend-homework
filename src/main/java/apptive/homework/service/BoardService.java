package apptive.homework.service;

import apptive.homework.domain.Board;
import apptive.homework.dto.BoardRequest;
import apptive.homework.domain.Member;
import apptive.homework.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepo;
    private final MemberService memberService;

    public Board write(BoardRequest req) {
        Member m = memberService.authenticate(req.getEmail(), req.getPassword());
        Board b = Board.builder()
                .title(req.getTitle())
                .content(req.getContent())
                .name(req.getName())
                .memberId(m.getMemberId())
                .build();
        return boardRepo.save(b);
    }

    public Board get(Long id) {
        return boardRepo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("게시글을 찾을 수 없습니다."));
    }

    public Board update(Long id, BoardRequest req) {
        Board b = get(id);
        Member m = memberService.authenticate(req.getEmail(), req.getPassword());
        if (!b.getMemberId().equals(m.getMemberId())) {
            throw new IllegalArgumentException("권한이 없습니다.");
        }
        b = Board.builder()
                .boardId(b.getBoardId())
                .title(req.getTitle())
                .content(req.getContent())
                .name(req.getName())
                .memberId(b.getMemberId())
                .build();
        return boardRepo.save(b);
    }

    public void delete(Long id, String email, String password) {
        Board b = get(id);
        Member m = memberService.authenticate(email, password);
        if (!b.getMemberId().equals(m.getMemberId())) {
            throw new IllegalArgumentException("권한이 없습니다.");
        }
        boardRepo.delete(b);
    }
}
