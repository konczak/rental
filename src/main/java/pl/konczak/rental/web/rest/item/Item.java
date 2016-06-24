package pl.konczak.rental.web.rest.item;

import lombok.Getter;

public class Item {

    @Getter
    private final long itemId;

    @Getter
    private final String name;

    @Getter
    private final String description;

    Item(pl.konczak.rental.domain.item.Item item) {
        this.itemId = item.getId();
        this.name = item.getName();
        this.description = item.getDescription();
    }

}
