package ua.itea.FinalProject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.itea.FinalProject.Entity.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
}
