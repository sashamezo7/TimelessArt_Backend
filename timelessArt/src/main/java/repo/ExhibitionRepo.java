package repo;

import entity.ExhibitionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExhibitionRepo extends JpaRepository<ExhibitionEntity, Integer> {

}
