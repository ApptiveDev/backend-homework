package apptive.basic.item.dto;

import apptive.basic.customAnno.TotalPrice;
import lombok.Getter;
import org.hibernate.validator.constraints.Range;

@Getter
@TotalPrice
public class ItemUpdateDto implements ItemDto{

    @Range(min = 100, max = 10000)
    private int price;

    @Range(min = 1, max = 100)
    private int quantity;

    public ItemUpdateDto(int price, int quantity) {
        this.price = price;
        this.quantity = quantity;
    }

    protected ItemUpdateDto() {}
}
