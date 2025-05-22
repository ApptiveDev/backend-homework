package apptive.homework.service;


import apptive.homework.domain.Board;
import apptive.homework.domain.Member;
import apptive.homework.dto.BoardDto;
import apptive.homework.repository.BoardRepository;
import apptive.homework.repository.MemberRepository;
import apptive.homework.validator.BoardValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BoardService {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final BoardValidator boardValidator;

    @Autowired
    public BoardService(BoardRepository boardRepository, MemberRepository memberRepository, BoardValidator boardValidator) {
        this.boardRepository = boardRepository;
        this.memberRepository = memberRepository;
        this.boardValidator = boardValidator;  //얘도 @Component로 빈에 주입했으므로 여기 정의해야 함.
    }

    public void write(BoardDto dto){
        boardValidator.validateContent(dto);
        Member member = memberRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 이메일입니다."));

        if (!member.getPassword().equals(dto.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        Board board = new Board();
        board.setTitle(dto.getTitle());
        board.setContent(dto.getContent());
        board.setName(dto.getName());
        board.setMember_id(member.getMember_id()); // 게시글 작성자 식별용

        boardRepository.save(board);
    }

    public void update(Long id, BoardDto dto){
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
        boardValidator.validateContent(dto);
        boardValidator.IsValidAccess(dto);


        board.setTitle(dto.getTitle());
        board.setContent(dto.getContent());
        board.setName(dto.getName());

        boardRepository.save(board); // 수정된 게시글 저장

    }

    public Board getBoard(Long id){
        return boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
    }

    public void delete(Long id, BoardDto dto){
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
        boardValidator.IsValidAccess(dto);

        boardRepository.delete(board);
    }
}
