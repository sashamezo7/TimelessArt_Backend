package Mapper;

import DTO.ClientDTO;
import entity.AccountEntity;
import entity.ClientsEntity;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

    public ClientsEntity mapToEntity(ClientDTO clientDTO, AccountEntity accountEntity) {
        ClientsEntity client = new ClientsEntity();
        //client.setId(clientDTO.getId());
        client.setName(clientDTO.getName());
        client.setFirstName(clientDTO.getFirstName());
        client.setPhone(clientDTO.getPhone());
        client.setAddress(clientDTO.getAddress());
        client.setCity(clientDTO.getCity());
        client.setPostalCode(clientDTO.getPostalCode());
        client.setCountry(clientDTO.getCountry());
        client.setBirthDate(clientDTO.getBirthDate());
        //client.setRegistrationDate(clientDTO.getRegistrationDate());
        client.setAccount(accountEntity);
        return client;
    }

    public ClientDTO mapToDto(ClientsEntity client) {
        ClientDTO clientDTO = new ClientDTO();
        //clientDTO.setId(client.getId());
        clientDTO.setName(client.getName());
        clientDTO.setFirstName(client.getFirstName());
        clientDTO.setPhone(client.getPhone());
        clientDTO.setAddress(client.getAddress());
        clientDTO.setCity(client.getCity());
        clientDTO.setPostalCode(client.getPostalCode());
        clientDTO.setCountry(client.getCountry());
        clientDTO.setBirthDate(client.getBirthDate());
        //clientDTO.setRegistrationDate(client.getRegistrationDate());
        return clientDTO;
    }
}
