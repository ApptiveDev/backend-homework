package apptive.basic.customAnno;

import apptive.basic.customValidator.TotalPriceValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = TotalPriceValidator.class)
public @interface TotalPrice {

    String message() default "총 가격은 100이상이어야합니다."; //선언 필수, 사용중
    Class<?>[] groups() default {}; //선언 필수, 사용하지 않음
    Class<? extends Payload>[] payload() default {}; //선언 필수, 사용하지 않음
}
