package cz.itnetwork.dto.mapper;

import cz.itnetwork.dto.PersonDTO;
import cz.itnetwork.entity.PersonEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.3.Final, compiler: Eclipse JDT (IDE) 3.39.0.v20240620-1855, environment: Java 17.0.11 (Eclipse Adoptium)"
)
@Component
public class PersonMapperImpl implements PersonMapper {

    @Override
    public PersonEntity toEntity(PersonDTO source) {
        if ( source == null ) {
            return null;
        }

        PersonEntity personEntity = new PersonEntity();

        personEntity.setAccountNumber( source.getAccountNumber() );
        personEntity.setBankCode( source.getBankCode() );
        personEntity.setCity( source.getCity() );
        personEntity.setCountry( source.getCountry() );
        personEntity.setIban( source.getIban() );
        if ( source.getId() != null ) {
            personEntity.setId( source.getId() );
        }
        personEntity.setIdentificationNumber( source.getIdentificationNumber() );
        personEntity.setMail( source.getMail() );
        personEntity.setName( source.getName() );
        personEntity.setNote( source.getNote() );
        personEntity.setStreet( source.getStreet() );
        personEntity.setTaxNumber( source.getTaxNumber() );
        personEntity.setTelephone( source.getTelephone() );
        personEntity.setZip( source.getZip() );

        return personEntity;
    }

    @Override
    public PersonDTO toDTO(PersonEntity source) {
        if ( source == null ) {
            return null;
        }

        PersonDTO personDTO = new PersonDTO();

        personDTO.setAccountNumber( source.getAccountNumber() );
        personDTO.setBankCode( source.getBankCode() );
        personDTO.setCity( source.getCity() );
        personDTO.setCountry( source.getCountry() );
        personDTO.setIban( source.getIban() );
        personDTO.setId( source.getId() );
        personDTO.setIdentificationNumber( source.getIdentificationNumber() );
        personDTO.setMail( source.getMail() );
        personDTO.setName( source.getName() );
        personDTO.setNote( source.getNote() );
        personDTO.setStreet( source.getStreet() );
        personDTO.setTaxNumber( source.getTaxNumber() );
        personDTO.setTelephone( source.getTelephone() );
        personDTO.setZip( source.getZip() );

        return personDTO;
    }
}
