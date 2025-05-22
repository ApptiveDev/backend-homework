package apptive.basic.todo.controller;


import apptive.basic.todo.dto.ToDoCreateDto;
import apptive.basic.todo.dto.ToDoResponseDto;
import apptive.basic.todo.dto.ToDoUpdateDto;
import apptive.basic.todo.service.ToDoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ToDoController {

    private final ToDoService toDoService;

    @PostMapping("/todo")
    public ResponseEntity<ToDoResponseDto> saveToDo(@Valid @RequestBody ToDoCreateDto toDoDto) {

        ToDoResponseDto save = toDoService.save(toDoDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(save);
    }

    @GetMapping("/todo/{id}")
    public ResponseEntity<ToDoResponseDto> getToDo(@PathVariable Long id) {
        ToDoResponseDto toDoDto = toDoService.findToDo(id);
        return ResponseEntity.status(HttpStatus.OK).body(toDoDto);
    }

    @DeleteMapping("/todo/{id}")
    public ResponseEntity<Map<String,String>> deleteToDo(@PathVariable Long id) {
        toDoService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(Map.of("message","삭제 성공"));
    }

    @PatchMapping("/todo/{id}")
    public ResponseEntity<ToDoResponseDto> updateToDo(@PathVariable Long id, @Valid @RequestBody ToDoUpdateDto toDoDto) {
        ToDoResponseDto updateToDo = toDoService.update(id, toDoDto);
        return ResponseEntity.status(HttpStatus.OK).body(updateToDo);
    }

}
