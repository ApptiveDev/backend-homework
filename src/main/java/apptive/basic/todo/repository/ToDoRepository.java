package apptive.basic.todo.repository;

import apptive.basic.domain.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ToDoRepository extends JpaRepository<ToDo, Long> {


    @Modifying(clearAutomatically = false)
    @Query("update ToDo t set t.title = :changeTitle where t.title = :title")
    void updateTitle(String title, String changeTitle);
}
