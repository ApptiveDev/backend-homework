package apptive.basic.item.dto;

import lombok.Getter;

@Getter
public class ItemResponseDto {

    private Long id;
    private int price;
    private int quantity;

    public ItemResponseDto(Long id, int price, int quantity) {
        this.id = id;
        this.price = price;
        this.quantity = quantity;
    }
}
