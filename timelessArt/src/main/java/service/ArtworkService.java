package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repo.ArtworkRepo;

@Service
public class ArtworkService {

    private final ArtworkRepo artworkRepo;

    public ArtworkService(ArtworkRepo artworkRepo) {
        this.artworkRepo = artworkRepo;
    }



}
