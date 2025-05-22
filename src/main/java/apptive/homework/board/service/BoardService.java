package apptive.homework.board.service;

import apptive.homework.board.dto.*;
import apptive.homework.board.repository.BoardRepository;
import apptive.homework.domain.Board;
import apptive.homework.exception.AuthenticationFailedException;
import apptive.homework.exception.BoardNotFoundException;
import apptive.homework.member.service.MemberService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberService memberService;

    public BoardResponseDto save(BoardCreateDto boardDto) {
        Long memberId = memberService.validateMember(boardDto.getEmail(), boardDto.getPassword());
        Board board = new Board(boardDto.getTitle(), boardDto.getContent(), boardDto.getName(), memberId);
        Board savedBoard = boardRepository.save(board);
        return new BoardResponseDto(savedBoard.getId(), savedBoard.getTitle(), savedBoard.getContent(), savedBoard.getName());
    }

    public BoardResponseDto update(Long id, BoardUpdateDto boardDto) {
        Long memberId = memberService.validateMember(boardDto.getEmail(), boardDto.getPassword());
        Board findBoard = boardRepository.findById(id)
                .orElseThrow(() -> new BoardNotFoundException());
        if (!findBoard.getMemberId().equals(memberId)) {
            throw new AuthenticationFailedException();
        }
        findBoard.changeContent(boardDto.getContent());
        findBoard.changeTitle(boardDto.getTitle());
        findBoard.changeName(boardDto.getName());
        return new BoardResponseDto(findBoard.getId(), findBoard.getTitle(), findBoard.getContent(), findBoard.getName());
    }

    public BoardResponseDto getBoard(Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new BoardNotFoundException());
        return new BoardResponseDto(board.getId(), board.getTitle(), board.getContent(), board.getName());
    }

    public void delete(Long boardId, BoardDeleteDto boardDto) {
        Long memberId = memberService.validateMember(boardDto.getEmail(), boardDto.getPassword());
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new BoardNotFoundException());
        if (!board.getMemberId().equals(memberId)) {
            throw new AuthenticationFailedException();
        }
        boardRepository.deleteById(boardId);
    }
}
