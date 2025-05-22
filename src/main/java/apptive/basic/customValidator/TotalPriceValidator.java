package apptive.basic.customValidator;

import apptive.basic.customAnno.TotalPrice;
import apptive.basic.item.dto.ItemCreateDto;
import apptive.basic.item.dto.ItemDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TotalPriceValidator implements ConstraintValidator<TotalPrice, ItemDto> {
    @Override
    public boolean isValid(ItemDto itemDto, ConstraintValidatorContext constraintValidatorContext) {

        if (itemDto.getQuantity() * itemDto.getPrice() <= 100) return false;
        return true;
    }
}
