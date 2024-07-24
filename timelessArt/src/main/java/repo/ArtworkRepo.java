package repo;

import entity.ArtworkEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtworkRepo extends JpaRepository<ArtworkEntity,Integer> {
}
