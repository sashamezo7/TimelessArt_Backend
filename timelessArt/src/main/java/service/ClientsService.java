package service;

import DTO.ClientDTO;
import Mapper.ClientMapper;
import entity.AccountEntity;
import entity.ClientsEntity;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.AllArgsConstructor;
import repo.AccountRepo;
import repo.ClientsRepo;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
@AllArgsConstructor
public class ClientsService {
    private final ClientsRepo clientsRepo;
    private final ClientMapper clientMapper;

    private final AccountRepo accountRepository;

    public ClientDTO getInfoAboutMe(String email) {
        AccountEntity accountEntity = accountRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Client not found"));
        ClientsEntity clientEntity = clientsRepo.findByAccount(accountEntity)
                .orElseThrow(() -> new RuntimeException("Client not found"));
        if (clientEntity == null) {
            throw new RuntimeException("Client profile not found for the given account");
        }

        return clientMapper.mapToDto(clientEntity);
    }

    public List<ClientDTO> getAllClients() {
        return clientsRepo.findAll()
                .stream()
                .map(clientMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public void createNewClient(ClientDTO clientDTO,String email) {
        AccountEntity accountEntity = accountRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        ClientsEntity clientEntity = clientMapper.mapToEntity(clientDTO, accountEntity);

        clientsRepo.save(clientEntity);
    }

}


