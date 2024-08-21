package repo;

import entity.ArtworkEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;


@ApplicationScoped
public class ArtworkRepo implements PanacheRepository<ArtworkEntity> {
    public long deleteArtworkIfOwner(Long artworkId, String email) {
        return delete("id = ?1 AND artist.account.email = ?2", artworkId, email);
    }
    public List<ArtworkEntity> findByArtistId(Long artistId){
        return list("artist.id", artistId);
    }
}
