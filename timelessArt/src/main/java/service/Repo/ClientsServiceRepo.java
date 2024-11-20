package service.Repo;

import DTO.ClientDTO;
import jakarta.transaction.Transactional;

import java.util.List;

public interface ClientsServiceRepo {
    @Transactional
    ClientDTO getInfoAboutMe(String email);

    @Transactional
    List<ClientDTO> getAllClients();

    @Transactional
    void createNewClient(ClientDTO clientDTO, String email);

    @Transactional
    void updateClientField(String email, String fieldName, String newValue);
}
