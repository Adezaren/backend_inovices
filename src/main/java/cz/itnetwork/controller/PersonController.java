/*  _____ _______         _                      _
 * |_   _|__   __|       | |                    | |
 *   | |    | |_ __   ___| |___      _____  _ __| | __  ___ ____
 *   | |    | | '_ \ / _ \ __\ \ /\ / / _ \| '__| |/ / / __|_  /
 *  _| |_   | | | | |  __/ |_ \ V  V / (_) | |  |   < | (__ / /
 * |_____|  |_|_| |_|\___|\__| \_/\_/ \___/|_|  |_|\_(_)___/___|
 *                                _
 *              ___ ___ ___ _____|_|_ _ _____
 *             | . |  _| -_|     | | | |     |  LICENCE
 *             |  _|_| |___|_|_|_|_|___|_|_|_|
 *             |_|
 *
 *   PROGRAMOVÁNÍ  <>  DESIGN  <>  PRÁCE/PODNIKÁNÍ  <>  HW A SW
 *
 * Tento zdrojový kód je součástí výukových seriálů na
 * IT sociální síti WWW.ITNETWORK.CZ
 *
 * Kód spadá pod licenci prémiového obsahu a vznikl díky podpoře
 * našich členů. Je určen pouze pro osobní užití a nesmí být šířen.
 * Více informací na http://www.itnetwork.cz/licence
 */
package cz.itnetwork.controller;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.PersonDTO;
import cz.itnetwork.dto.PersonStatisticsDTO;
import cz.itnetwork.dto.mapper.PersonMapper;
import cz.itnetwork.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PersonController {

    @Autowired
    private PersonService personService;

    @Autowired
    private PersonMapper personMapper;


    /**
     * Přidání osoby
     * @param personDTO
     * @return
     */
    @PostMapping("/persons")
    public PersonDTO addPerson(@RequestBody PersonDTO personDTO) {

        return personService.addPerson(personDTO);
    }

    /**
     * Vypsání detailu všech osoby
     * @return
     */
    @GetMapping("/persons")
    public List<PersonDTO> getPersons() {
        return personService.getAll();
    }

    /**
     * smazání osoby
     * @param personId
     */
    @DeleteMapping("/persons/{personId}")
    public void deletePerson(@PathVariable Long personId) {
        personService.removePerson(personId);
    }

    /**
     * Vypsání detailu osoby
     * @param personId
     * @return
     */
    @GetMapping("/persons/{personId}")
    public PersonDTO getPerson(@PathVariable Long personId) {
        return personService.getPersonById(personId);
    }

    /**
     * vypsání vystavených faktur dle identifikačního čísla
     * @param identificationNumber
     * @return
     */
    @GetMapping("/identification/{identificationNumber}/sales")
    public List<InvoiceDTO> getInvoicesBySeller(@PathVariable String identificationNumber) {
        return personService.getInvoicesBySeller(identificationNumber);
    }

    /**
     * vypsání obdžených faktur dle identifikačního čísla
     * @param identificationNumber
     * @return
     */
    @GetMapping("/identification/{identificationNumber}/purchases")
    public List<InvoiceDTO> getInvoicesByBuyer(@PathVariable String identificationNumber) {
        return personService.getInvoicesByBuyer(identificationNumber);
    }

    /**
     * @GetMapping("/persons/edit/{personId}") public PersonDTO renderPersonForm(@PathVariable long personId, PersonDTO personDTO) {
     * PersonDTO entity = personService.getPersonById(personDTO.getId());
     * personMapper.updatePersonDTO(entity, personDTO);
     * <p>
     * return personService.getPersonById(personId);
     * }
     */
    @PutMapping("/persons/{personId}")
    public PersonDTO editPersonById(@RequestBody PersonDTO personDTO, @PathVariable long personId) {
        return personService.editPerson(personDTO, personId);
    }

    /**
     * vypsání statistik osob: jméno osoby + celková suma vystavených faktur
     * @return
     */
    @GetMapping("/persons/statistics")
    public List<PersonStatisticsDTO> getPersonStatistics(){
        return personService.getPersonStatistics();
    }





}











