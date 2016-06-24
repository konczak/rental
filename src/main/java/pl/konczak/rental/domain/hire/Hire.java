package pl.konczak.rental.domain.hire;

import java.time.LocalDate;
import java.time.LocalDateTime;

import pl.konczak.rental.domain.item.Item;
import pl.konczak.rental.domain.person.Person;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import lombok.Getter;

@Entity
public class Hire {

    @Id
    @GeneratedValue
    @Getter
    private long id;

    @NotNull
    @Getter
    private LocalDateTime hireAt;

    @Getter
    private LocalDate deadlineAt;

    @NotNull
    @ManyToOne
    private Person person;

    @NotNull
    @OneToOne
    @Getter
    private Item item;

    protected Hire() {
    }

    public Hire(Person person, Item item) {
        this.person = person;
        this.item = item;
        this.hireAt = LocalDateTime.now();
    }

    public void addDeadline(LocalDate deadlineAt) {
        if (deadlineAt == null
                || deadlineAt.isBefore(hireAt.toLocalDate())) {
            throw new RuntimeException("Specified deadline " + deadlineAt + " is empty or beofre hireAt");
        }
        this.deadlineAt = deadlineAt;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Hire other = (Hire) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}
