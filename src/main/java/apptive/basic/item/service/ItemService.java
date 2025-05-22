package apptive.basic.item.service;

import apptive.basic.domain.Item;
import apptive.basic.exception.NotFoundItemException;
import apptive.basic.item.dto.ItemCreateDto;
import apptive.basic.item.dto.ItemResponseDto;
import apptive.basic.item.dto.ItemUpdateDto;
import apptive.basic.item.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemResponseDto save(ItemCreateDto itemCreateDto) {
        Item item = new Item(itemCreateDto.getPrice(), itemCreateDto.getQuantity());

        Item save = itemRepository.save(item);

        return new ItemResponseDto(save.getId(), save.getPrice(), save.getQuantity());
    }

    public ItemResponseDto updateItem(ItemUpdateDto itemUpdateDto, Long id) {
        Item findItem = itemRepository.findById(id).orElseThrow(() -> new NotFoundItemException("존재하지 않는 물품입니다."));

        findItem.changePrice(itemUpdateDto.getPrice());
        findItem.changeQuantity(itemUpdateDto.getQuantity());

        return new ItemResponseDto(findItem.getId(), findItem.getPrice(), findItem.getQuantity());
    }

    public ItemResponseDto findItem(Long id)
    {
        Item item = itemRepository.findById(id).orElseThrow(()-> new NotFoundItemException("존재하는 item이 없습니다."));

        return new ItemResponseDto(item.getId(), item.getPrice(),item.getQuantity());
    }
}
