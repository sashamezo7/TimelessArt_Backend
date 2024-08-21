package DTO;
import java.sql.Date;
import java.sql.Timestamp;
import lombok.Data;

@Data
public class ClientDTO {

    private String name;

    private String firstName;

    private String phone;

    private String address;

    private String city;

    private String postalCode;

    private String country;

    private Date birthDate;

}
