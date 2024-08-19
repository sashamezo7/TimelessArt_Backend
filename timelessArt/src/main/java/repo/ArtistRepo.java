package repo;

import entity.ArtistEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ArtistRepo implements PanacheRepository<ArtistEntity> {

}
