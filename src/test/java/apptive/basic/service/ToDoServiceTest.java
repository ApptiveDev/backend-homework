package apptive.basic.service;

import apptive.basic.todo.dto.ToDoCreateDto;
import apptive.basic.todo.dto.ToDoResponseDto;
import apptive.basic.todo.dto.ToDoUpdateDto;
import apptive.basic.exception.NotFoundToDoException;
import apptive.basic.todo.service.ToDoService;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
@Transactional
class ToDoServiceTest {

    @Autowired
    ToDoService toDoService;
    @Autowired
    EntityManager em;

    @Test
    void save() {
        ToDoCreateDto toDoCreateDto = new ToDoCreateDto("제목1", "내용1");

        ToDoResponseDto saved = toDoService.save(toDoCreateDto);

        ToDoResponseDto findToDo = toDoService.findToDo(saved.getId());

        Assertions.assertThat(findToDo.getId()).isEqualTo(saved.getId());
        Assertions.assertThat(findToDo.getTitle()).isEqualTo(saved.getTitle());
        Assertions.assertThat(findToDo.getContent()).isEqualTo(saved.getContent());
    }

    @Test
    void update() {

        ToDoCreateDto toDoCreateDto = new ToDoCreateDto("제목1", "내용1");

        ToDoResponseDto saved = toDoService.save(toDoCreateDto);

        ToDoUpdateDto toDoUpdateDto = new ToDoUpdateDto("수정된 제목", "수정된 내용");

        toDoService.update(saved.getId(), toDoUpdateDto);

//        em.flush(); // 데이터 베이스와 영속성 컨텍스트 일치화
//        em.clear(); // 영속성 컨텍스트 비우기

        ToDoResponseDto updatedToDo = toDoService.findToDo(saved.getId());

        Assertions.assertThat(updatedToDo.getTitle()).isEqualTo(toDoUpdateDto.getTitle());
        Assertions.assertThat(updatedToDo.getContent()).isEqualTo(toDoUpdateDto.getContent());
    }

    @Test
    void delete() {
        ToDoCreateDto toDoCreateDto = new ToDoCreateDto("제목1", "내용1");

        ToDoResponseDto saved = toDoService.save(toDoCreateDto);

        toDoService.delete(saved.getId());

        Assertions.assertThatThrownBy(()->toDoService.findToDo(saved.getId()))
                .isInstanceOf(NotFoundToDoException.class);
    }

}