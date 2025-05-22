package apptive.basic.controller;

import apptive.basic.todo.dto.ToDoCreateDto;
import apptive.basic.todo.dto.ToDoResponseDto;
import apptive.basic.todo.dto.ToDoUpdateDto;
import apptive.basic.todo.service.ToDoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;



@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class ToDoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ToDoService toDoService;

    @Test
    void failSave() throws Exception {
        ToDoCreateDto toDoCreateDto = new ToDoCreateDto("", "");

        String request = objectMapper.writeValueAsString(toDoCreateDto);
        mockMvc.perform(MockMvcRequestBuilders.post("/todo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void failUpdate() throws Exception {
        ToDoCreateDto toDoCreateDto = new ToDoCreateDto("제목1", "내용1");

        ToDoResponseDto saved = toDoService.save(toDoCreateDto);

        ToDoUpdateDto toDoUpdateDto = new ToDoUpdateDto("제", "내");

        String request = objectMapper.writeValueAsString(toDoUpdateDto);


        mockMvc.perform(MockMvcRequestBuilders.patch("/todo/{id}", saved.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void notFound() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/todo/{id}", 1L))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

    }


}