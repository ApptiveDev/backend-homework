package apptive.basic.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import org.hibernate.validator.constraints.Range;

@Entity
@Getter
public class Item {

    @Id
    @GeneratedValue
    private Long id;

    private int price;

    protected Item() {}

    private int quantity;

    public Item(int price, int quantity) {
        this.price = price;
        this.quantity = quantity;
    }

    public void changePrice(int price) {
        this.price = price;
    }
    public void changeQuantity(int quantity) {
        this.quantity = quantity;
    }
}
