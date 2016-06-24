package pl.konczak.rental.domain.item;

import pl.konczak.rental.domain.hire.Hire;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;

@Entity
public class Item {

    @Id
    @GeneratedValue
    @Getter
    private long id;

    @Column(unique = true)
    @NotNull
    @Size(min = 2,
          max = 255)
    @Getter
    private String name;

    @Size(max = 65000)
    @Getter
    private String description;

    @OneToOne
    private Hire hire;

    protected Item() {
    }

    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public boolean isHired() {
        return this.hire != null;
    }

}
