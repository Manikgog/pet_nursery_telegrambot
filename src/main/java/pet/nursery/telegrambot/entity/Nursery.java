package pet.nursery.telegrambot.entity;

import javax.persistence.*;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "nursery_table")
public class Nursery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String address;
    private String phoneNumber;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Nursery nursery = (Nursery) o;
        return id == nursery.id && Objects.equals(address, nursery.address) && Objects.equals(phoneNumber, nursery.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, address, phoneNumber);
    }
}
