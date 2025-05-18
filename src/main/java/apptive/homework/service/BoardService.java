package apptive.homework.service;


import apptive.homework.dto.BoardDto;
import apptive.homework.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardService {
    private final BoardRepository boardRepository;
    public void save(BoardDto boardDto) {
        Board toDo = new Board(boardDto.getTitle(), boardDto.getContent());
        boardRepository.save(toDo);
    }
}
