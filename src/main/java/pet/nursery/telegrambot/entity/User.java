package pet.nursery.telegrambot.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Setter
@Getter
@Table(name="users_table")
public class User {
    @Id
    private long telegramUserId;
    private String firstName;
    private String lastName;
    private String userName;
    private String phoneNumber;
    private String address;
    private boolean isVolunteer;
    private boolean isAdmin;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return telegramUserId == user.telegramUserId &&
                isVolunteer == user.isVolunteer &&
                isAdmin == user.isAdmin &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(userName, user.userName) &&
                Objects.equals(phoneNumber, user.phoneNumber) &&
                Objects.equals(address, user.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(telegramUserId, firstName, lastName, userName, phoneNumber, address, isVolunteer, isAdmin);
    }
}
