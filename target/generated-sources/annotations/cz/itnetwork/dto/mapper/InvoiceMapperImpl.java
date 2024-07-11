package cz.itnetwork.dto.mapper;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.PersonDTO;
import cz.itnetwork.entity.InvoiceEntity;
import cz.itnetwork.entity.PersonEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.3.Final, compiler: Eclipse JDT (IDE) 3.39.0.v20240620-1855, environment: Java 17.0.11 (Eclipse Adoptium)"
)
@Component
public class InvoiceMapperImpl implements InvoiceMapper {

    @Override
    public InvoiceEntity toEntity(InvoiceDTO source) {
        if ( source == null ) {
            return null;
        }

        InvoiceEntity invoiceEntity = new InvoiceEntity();

        invoiceEntity.setBuyer( personDTOToPersonEntity( source.getBuyer() ) );
        invoiceEntity.setDueDate( source.getDueDate() );
        if ( source.getId() != null ) {
            invoiceEntity.setId( source.getId() );
        }
        invoiceEntity.setInvoiceNumber( source.getInvoiceNumber() );
        invoiceEntity.setIssued( source.getIssued() );
        invoiceEntity.setNote( source.getNote() );
        invoiceEntity.setPrice( source.getPrice() );
        invoiceEntity.setProduct( source.getProduct() );
        invoiceEntity.setSeller( personDTOToPersonEntity( source.getSeller() ) );
        invoiceEntity.setVat( source.getVat() );

        return invoiceEntity;
    }

    @Override
    public InvoiceDTO toDTO(InvoiceEntity source) {
        if ( source == null ) {
            return null;
        }

        InvoiceDTO invoiceDTO = new InvoiceDTO();

        invoiceDTO.setBuyer( personEntityToPersonDTO( source.getBuyer() ) );
        invoiceDTO.setDueDate( source.getDueDate() );
        invoiceDTO.setId( source.getId() );
        invoiceDTO.setInvoiceNumber( source.getInvoiceNumber() );
        invoiceDTO.setIssued( source.getIssued() );
        invoiceDTO.setNote( source.getNote() );
        invoiceDTO.setPrice( source.getPrice() );
        invoiceDTO.setProduct( source.getProduct() );
        invoiceDTO.setSeller( personEntityToPersonDTO( source.getSeller() ) );
        invoiceDTO.setVat( source.getVat() );

        return invoiceDTO;
    }

    @Override
    public InvoiceEntity editEntity(InvoiceDTO source, InvoiceEntity target) {
        if ( source == null ) {
            return target;
        }

        target.setDueDate( source.getDueDate() );
        target.setInvoiceNumber( source.getInvoiceNumber() );
        target.setIssued( source.getIssued() );
        target.setNote( source.getNote() );
        target.setPrice( source.getPrice() );
        target.setProduct( source.getProduct() );
        target.setVat( source.getVat() );

        return target;
    }

    protected PersonEntity personDTOToPersonEntity(PersonDTO personDTO) {
        if ( personDTO == null ) {
            return null;
        }

        PersonEntity personEntity = new PersonEntity();

        personEntity.setAccountNumber( personDTO.getAccountNumber() );
        personEntity.setBankCode( personDTO.getBankCode() );
        personEntity.setCity( personDTO.getCity() );
        personEntity.setCountry( personDTO.getCountry() );
        personEntity.setIban( personDTO.getIban() );
        if ( personDTO.getId() != null ) {
            personEntity.setId( personDTO.getId() );
        }
        personEntity.setIdentificationNumber( personDTO.getIdentificationNumber() );
        personEntity.setMail( personDTO.getMail() );
        personEntity.setName( personDTO.getName() );
        personEntity.setNote( personDTO.getNote() );
        personEntity.setStreet( personDTO.getStreet() );
        personEntity.setTaxNumber( personDTO.getTaxNumber() );
        personEntity.setTelephone( personDTO.getTelephone() );
        personEntity.setZip( personDTO.getZip() );

        return personEntity;
    }

    protected PersonDTO personEntityToPersonDTO(PersonEntity personEntity) {
        if ( personEntity == null ) {
            return null;
        }

        PersonDTO personDTO = new PersonDTO();

        personDTO.setAccountNumber( personEntity.getAccountNumber() );
        personDTO.setBankCode( personEntity.getBankCode() );
        personDTO.setCity( personEntity.getCity() );
        personDTO.setCountry( personEntity.getCountry() );
        personDTO.setIban( personEntity.getIban() );
        personDTO.setId( personEntity.getId() );
        personDTO.setIdentificationNumber( personEntity.getIdentificationNumber() );
        personDTO.setMail( personEntity.getMail() );
        personDTO.setName( personEntity.getName() );
        personDTO.setNote( personEntity.getNote() );
        personDTO.setStreet( personEntity.getStreet() );
        personDTO.setTaxNumber( personEntity.getTaxNumber() );
        personDTO.setTelephone( personEntity.getTelephone() );
        personDTO.setZip( personEntity.getZip() );

        return personDTO;
    }
}
