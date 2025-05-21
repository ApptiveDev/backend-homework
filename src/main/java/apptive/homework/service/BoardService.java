package apptive.homework.service;


import apptive.homework.domain.Board;
import apptive.homework.dto.BoardDto;
import apptive.homework.dto.BoardInfoDto;
import apptive.homework.exception.InvalidInputException;
import apptive.homework.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardService {
    private final BoardRepository boardRepository;
    public void save(BoardDto boardDto, Long user_id) {
        if(boardDto.getTitle().length() > 20 || boardDto.getTitle().length() < 10) {
            throw new InvalidInputException("제목은 10글자 이상 20글자 이하여야 합니다.");
        }
        if(boardDto.getContent().length() < 3){
            throw new InvalidInputException("본문 내용은 3글자 이상이어야 합니다");
        }
        if(boardDto.getName().length() < 3){
            throw new InvalidInputException("이름은 3글자 이상이어야 합니다.");
        }
        Board board = new Board(boardDto.getTitle(),boardDto.getContent(),boardDto.getName(),user_id);
        boardRepository.save(board);
    }

    public BoardInfoDto findBoard(Long id) {
       Board board = boardRepository.findById(id).orElseThrow();
       return new BoardInfoDto(board.getTitle(), board.getContent(), board.getName());
    }

    public boolean delete(Long id) {
        Optional<Board> board = boardRepository.findById(id);

        if (board.isPresent()) {
            boardRepository.deleteById(id);
            return true;
        }
        else {
            return false;
        }
    }

    public boolean update(Long boardId, BoardDto boardDto) {
        Optional<Board> boardOpt = boardRepository.findById(boardId);
        if (boardOpt.isPresent()) {
            Board board = boardOpt.get();
            if (board.getBoard_id().equals(boardId)) {
                board.ChangeTitle(boardDto.getTitle());
                board.ChangeContent(boardDto.getContent());
                boardRepository.save(board);
                return true;
            }
        }
        return false;
    }



}
