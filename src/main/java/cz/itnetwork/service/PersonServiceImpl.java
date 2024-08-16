
package cz.itnetwork.service;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.PersonDTO;
import cz.itnetwork.dto.PersonStatisticsDTO;
import cz.itnetwork.dto.mapper.InvoiceMapper;
import cz.itnetwork.dto.mapper.PersonMapper;
import cz.itnetwork.entity.PersonEntity;
import cz.itnetwork.entity.repository.InvoiceRepository;
import cz.itnetwork.entity.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;


import java.util.List;

import java.util.stream.Collectors;




@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonMapper personMapper;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private InvoiceMapper invoiceMapper;

    @Autowired
    private InvoiceRepository invoiceRepository;


    /**
     * přidat osobu
     * @param personDTO Person to create
     * @return
     */
    public PersonDTO addPerson(PersonDTO personDTO) {
        PersonEntity entity = personMapper.toEntity(personDTO);
        entity = personRepository.save(entity);

        return personMapper.toDTO(entity);
    }

    /**
     * smazat osobu: skryje osobu
     * @param personId Person to delete
     */
    @Override
    public void removePerson(long personId) {
        try {
            PersonEntity person = fetchPersonById(personId);
            person.setHidden(true);

            personRepository.save(person);
        } catch (NotFoundException ignored) {
            // The contract in the interface states, that no exception is thrown, if the entity is not found.
        }
    }

    /**
     * vypsat všechny osoby
     * @return
     */
    @Override
    public List<PersonDTO> getAll() {
        return personRepository.findByHidden(false)
                .stream()
                .map(i -> personMapper.toDTO(i))
                .collect(Collectors.toList());
    }

    /**
     * vyspat detail osoby dle ID
     * @param id
     * @return
     */
    @Override
    public PersonDTO getPersonById(long id) {
        PersonEntity personEntity = fetchPersonById(id);

        return personMapper.toDTO(personEntity);
    }

    /**
     * úprava osoby: smaže existující a založí osobu novou
     * @param personDTO
     * @param personId
     * @return
     */
    @Override
    public PersonDTO editPerson(PersonDTO personDTO, long personId) {
        PersonEntity entity = fetchPersonById(personId);
        entity.setHidden(true);
        personRepository.save(entity);

        personDTO.setId(null);
        PersonDTO newEntity = addPerson(personDTO);

        return newEntity;
    }

    /**
     * vypíše vystavené faktury danou osobou
     * @param identificationNumber
     * @return
     */
    @Override
    public List<InvoiceDTO> getInvoicesBySeller(String identificationNumber) {

        return personRepository.findByIdentificationNumber(identificationNumber)
                .stream()
                .flatMap(i -> i.getSales()
                        .stream())
                .map(i -> invoiceMapper.toDTO(i))
                .collect(Collectors.toList());
    }

    /**
     * vypíše přijaté faktury danou osobou
     * @param identificationNumber
     * @return
     */
    @Override
    public List<InvoiceDTO> getInvoicesByBuyer(String identificationNumber) {
        return personRepository.findByIdentificationNumber(identificationNumber)
                .stream()
                .flatMap(i -> i.getPurchase()
                        .stream())
                .map(i -> invoiceMapper.toDTO(i))
                .collect(Collectors.toList());
    }

    /**
     * vypíše statistiku osob
     * @return
     */
    @Override
    public List<PersonStatisticsDTO> getPersonStatistics() {
        return personRepository.getPersonStatistics();
    }


    /**
     * <p>Attempts to fetch a person.</p>
     * <p>In case a person with the passed [id] doesn't exist a [{@link org.webjars.NotFoundException}] is thrown.</p>
     *
     * @param id Person to fetch
     * @return Fetched entity
     * @throws org.webjars.NotFoundException In case a person with the passed [id] isn't found
     */
    private PersonEntity fetchPersonById(long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Person with id " + id + " wasn't found in the database."));
    }

}
