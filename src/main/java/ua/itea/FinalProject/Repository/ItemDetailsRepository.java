package ua.itea.FinalProject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.itea.FinalProject.Entity.ItemDetails;

@Repository
public interface ItemDetailsRepository extends JpaRepository<ItemDetails, Integer> {
}
