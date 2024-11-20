package service;

import DTO.ClientDTO;
import Mapper.ClientMapper;
import entity.AccountEntity;
import entity.ClientsEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import repo.AccountRepo;
import repo.ClientsRepo;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
@AllArgsConstructor
public class ClientsService implements service.Repo.ClientsServiceRepo {

    private final ClientsRepo clientsRepo;
    private final ClientMapper clientMapper;

    private final AccountRepo accountRepository;
    @Override
    @Transactional
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
    @Override
    @Transactional
    public List<ClientDTO> getAllClients() {
        return clientsRepo.findAll()
                .stream()
                .map(clientMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void createNewClient(ClientDTO clientDTO, String email) {
        AccountEntity accountEntity = accountRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        ClientsEntity clientEntity = clientMapper.mapToEntity(clientDTO, accountEntity);

        clientsRepo.save(clientEntity);
    }
    @Override
    @Transactional
    public void updateClientField(String email, String fieldName, String newValue) {
        AccountEntity accountEntity = accountRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        ClientsEntity clientEntity = clientsRepo.findByAccount(accountEntity)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        switch (fieldName) {
            case "name":
                clientEntity.setName(newValue);
                break;
            case "firstName":
                clientEntity.setFirstName(newValue);
                break;
            case "phone":
                clientEntity.setPhone(newValue);
                break;
            case "address":
                clientEntity.setAddress(newValue);
                break;
            case "city":
                clientEntity.setCity(newValue);
                break;
            case "postalCode":
                clientEntity.setPostalCode(newValue);
                break;
            case "country":
                clientEntity.setCountry(newValue);
                break;
            // Adaugă alte câmpuri după cum este necesar
            default:
                throw new IllegalArgumentException("Invalid field name: " + fieldName);
        }

        clientsRepo.save(clientEntity);
    }

}


