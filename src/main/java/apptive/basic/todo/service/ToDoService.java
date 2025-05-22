package apptive.basic.todo.service;

import apptive.basic.domain.ToDo;
import apptive.basic.todo.dto.ToDoCreateDto;
import apptive.basic.todo.dto.ToDoResponseDto;
import apptive.basic.todo.dto.ToDoUpdateDto;
import apptive.basic.exception.NotFoundToDoException;
import apptive.basic.todo.repository.ToDoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ToDoService {

    private final ToDoRepository toDoRepository;


    public ToDoResponseDto save(ToDoCreateDto toDoDto) {
        ToDo toDo = new ToDo(toDoDto.getTitle(), toDoDto.getContent());
        ToDo save = toDoRepository.save(toDo);
        return new ToDoResponseDto(save.getId(), save.getTitle(), save.getContent());
    }

    public ToDoResponseDto findToDo(Long id) {

        ToDo toDo = toDoRepository.findById(id).orElseThrow(() -> new NotFoundToDoException("존재하는 toDo가 없습니다."));

        return new ToDoResponseDto(toDo.getId(), toDo.getTitle(), toDo.getContent());
    }

    public void delete(Long id) {
        toDoRepository.findById(id).orElseThrow(() -> new NotFoundToDoException("삭제 실패, 존재하는 toDo가 없습니다."));

        toDoRepository.deleteById(id);
    }

    public ToDoResponseDto update(Long id, ToDoUpdateDto toDoDto) {
        ToDo toDo = toDoRepository.findById(id).orElseThrow(() -> new NotFoundToDoException("수정 실패, 존재하는 toDo가 없습니다."));

        toDo.changeTitle(toDoDto.getTitle());
        toDo.changeContent(toDoDto.getContent());

        return new ToDoResponseDto(toDo.getId(), toDo.getTitle(), toDo.getContent());
    }
}
