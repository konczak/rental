package pl.konczak.rental.domain.person;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.konczak.rental.domain.hire.Hire;
import pl.konczak.rental.domain.item.IItemRepository;
import pl.konczak.rental.domain.item.Item;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Getter;

@Entity
public class Person {

    private static final Logger LOGGER = LoggerFactory.getLogger(Person.class);

    public static IPersonRepository personRepository;

    public static IItemRepository itemRepository;

    @Id
    @GeneratedValue
    @Getter
    private long id;

    @Getter
    private String firstname;

    @Getter
    private String lastname;

    @Getter
    private String pesel;

    @OneToMany(cascade = CascadeType.ALL,
               mappedBy = "person")
    private final Set<Hire> hires = new HashSet<>();

    protected Person() {
    }

    public Person(String firstname, String lastname, String pesel) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.pesel = pesel;
    }

    public void hire(long itemId) {
        Item item = itemRepository.findOne(itemId);

        if (item == null) {
            throw new RuntimeException("Item " + itemId + " does not exists");
        }
        if (item.isHired()) {
            throw new RuntimeException("Item " + itemId + " is already hired");
        }

        Hire hire = new Hire(this, item);

        this.hires.add(hire);

        personRepository.saveAndFlush(this);

        LOGGER.info("Person {} hired item {}", pesel, item.getName());
    }

    public void hire(long itemId, LocalDate deadlineAt) {
        Item item = itemRepository.findOne(itemId);

        if (item == null) {
            throw new RuntimeException("Item " + itemId + " does not exists");
        }
        if (item.isHired()) {
            throw new RuntimeException("Item " + itemId + " is already hired");
        }

        Hire hire = new Hire(this, item);
        hire.addDeadline(deadlineAt);

        this.hires.add(hire);

        personRepository.saveAndFlush(this);

        LOGGER.info("Person {} hired item {} up to {}", pesel, item.getName(), deadlineAt);
    }

    public void returnHire(long hireId) {
        Optional<Hire> optional = hires.stream()
                .filter(hire -> hire.getId() == hireId)
                .findAny();

        if (optional.isPresent()) {
            hires.remove(optional.get());
        }
    }

    public Set<Hire> getHires() {
        return Collections.unmodifiableSet(hires);
    }
}
