package pl.konczak.rental.web.rest.item;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

public class ItemNew {

    @NotNull
    @Size(min = 2,
          max = 255)
    @Getter
    @Setter
    private String name;

    @Size(max = 65000)
    @Getter
    @Setter
    private String description;

}
