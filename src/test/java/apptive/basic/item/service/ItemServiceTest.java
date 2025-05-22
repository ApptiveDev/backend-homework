package apptive.basic.item.service;

import apptive.basic.item.dto.ItemCreateDto;
import apptive.basic.item.dto.ItemResponseDto;
import apptive.basic.item.dto.ItemUpdateDto;
import apptive.basic.todo.dto.ToDoCreateDto;
import apptive.basic.todo.dto.ToDoResponseDto;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class ItemServiceTest
{
    @Autowired
    ItemService itemService;
    @Autowired
    EntityManager em;

    @Test
    void save()
    {
        ItemCreateDto itemCreateDto = new ItemCreateDto(1,1);

        ItemResponseDto saved = itemService.save(itemCreateDto);

        ItemResponseDto findItem = itemService.findItem(saved.getId());

        Assertions.assertThat(findItem.getId()).isEqualTo(saved.getId());
        Assertions.assertThat(findItem.getPrice()).isEqualTo(saved.getPrice());
        Assertions.assertThat(findItem.getQuantity()).isEqualTo(saved.getQuantity());
    }

    @Test
    void update()
    {
        ItemCreateDto itemCreateDto = new ItemCreateDto(1,1);

        ItemResponseDto saved = itemService.save(itemCreateDto);

        ItemUpdateDto itemUpdateDto = new ItemUpdateDto(2,2);

        itemService.updateItem(itemUpdateDto, saved.getId());

        ItemResponseDto updatedItem = itemService.findItem(saved.getId());

        Assertions.assertThat(updatedItem.getPrice()).isEqualTo(itemUpdateDto.getPrice());
        Assertions.assertThat(updatedItem.getQuantity()).isEqualTo(itemUpdateDto.getQuantity());
    }



}