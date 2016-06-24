package pl.konczak.rental.domain.item;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IItemRepository
        extends JpaRepository<Item, Long> {

    Item findByName(String name);
}
