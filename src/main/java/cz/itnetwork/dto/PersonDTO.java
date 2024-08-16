
package cz.itnetwork.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import cz.itnetwork.constant.Countries;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {

    @JsonProperty("_id")
    private Long id;

    private String name;

    private String identificationNumber;

    private String taxNumber;

    private String accountNumber;

    private String bankCode;

    private String iban;

    private String telephone;

    private String mail;

    private String street;

    private String zip;

    private String city;

    private Countries country;

    private String note;
}
