package apptive.basic.repository;

import apptive.basic.domain.ToDo;
import apptive.basic.todo.repository.ToDoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@SpringBootTest
class ToDoRepositoryTest {

    @Autowired
    ToDoRepository toDoRepository;

    @Test
    void bulkError() {
        ToDo toDo1 = new ToDo("제목", "내용1");
        ToDo toDo2 = new ToDo("제목", "내용2");

        ToDo save = toDoRepository.save(toDo1);
        ToDo save1 = toDoRepository.save(toDo2);

        //영속성 컨텍스트 거치지 않고 바로 DB로 고고씽
        toDoRepository.updateTitle("제목", "변경된 제목");

        ToDo find1 = toDoRepository.findById(save.getId()).get(); // 영속성 컨텍스트에 있네? DB에 안가야지!!
        ToDo find2 = toDoRepository.findById(save1.getId()).get(); // // 영속성 컨텍스트에 있네? DB에 안가야지!!

        System.out.println(find1.getTitle()); // 제목 출력
        System.out.println(find2.getTitle()); // 제목 출력
    }
}