package cz.itnetwork.service;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.InvoiceStatisticsDTO;
import cz.itnetwork.dto.mapper.InvoiceMapper;
import cz.itnetwork.entity.InvoiceEntity;
import cz.itnetwork.entity.filter.InvoiceFilter;
import cz.itnetwork.entity.repository.InvoiceRepository;
import cz.itnetwork.entity.repository.PersonRepository;
import cz.itnetwork.entity.repository.specification.InvoiceSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceMapper invoiceMapper;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private PersonRepository personRepository;

    /**
     * přidání faktury
     * @param invoiceDTO
     * @return
     */
    public InvoiceDTO addInvoice(InvoiceDTO invoiceDTO) {
        InvoiceEntity entity = invoiceMapper.toEntity(invoiceDTO);
        entity.setBuyer(personRepository.getReferenceById(invoiceDTO.getBuyer().getId()));
        entity.setSeller(personRepository.getReferenceById(invoiceDTO.getSeller().getId()));
        entity = invoiceRepository.saveAndFlush(entity);

        return invoiceMapper.toDTO(entity);
    }

    /**
     * výpis všech faktur
     * @param invoiceFilter
     * @return
     */
    @Override
    public List<InvoiceDTO> getAll(InvoiceFilter invoiceFilter) {
        InvoiceSpecification invoiceSpecification = new InvoiceSpecification(invoiceFilter);

        return invoiceRepository.findAll(invoiceSpecification, PageRequest.of(0,invoiceFilter.getLimit()))
                .stream()
                .map(invoiceMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * vypsání detalu faktury
     * @param id
     * @return
     */
    @Override
    public InvoiceDTO getInvoiceById(long id) {
        InvoiceEntity invoiceEntity = fetchInvoiceById(id);
        return invoiceMapper.toDTO(invoiceEntity);
    }

    /**
     * smazání faktury
     * @param invoiceId
     */
    @Override
    public void deleteInvoice(long invoiceId){
        try {
            InvoiceEntity invoice = fetchInvoiceById(invoiceId);

            invoiceRepository.delete(invoice);
        } catch (NotFoundException ignored) {
            // The contract in the interface states, that no exception is thrown, if the entity is not found.

        }
    }

    /**
     * úprava faktury: přepsání existující faktury
     * @param invoiceDTO
     * @param invoiceId
     * @return
     */
    @Override
    public InvoiceDTO editInvoice(InvoiceDTO invoiceDTO, long invoiceId){

        InvoiceEntity entity = fetchInvoiceById(invoiceId);
        invoiceDTO.setId(invoiceId);
        invoiceMapper.editEntity(invoiceDTO, entity);

        entity.setBuyer(personRepository.getReferenceById(invoiceDTO.getBuyer().getId()));
        entity.setSeller(personRepository.getReferenceById(invoiceDTO.getSeller().getId()));

       InvoiceEntity saved = invoiceRepository.save(entity);

        return invoiceMapper.toDTO(saved);
    }

    /**
     * vypsání statistik faktur
     * @return
     */
    @Override
    public InvoiceStatisticsDTO getInvoiceStatistics(){

        InvoiceStatisticsDTO statisticsDTO = new InvoiceStatisticsDTO();
            statisticsDTO.setInvoicesCount(invoiceRepository.countInvoices());
            statisticsDTO.setCurrentYearSum(invoiceRepository.getCurrentYearSum());
            statisticsDTO.setAllTimeSum(invoiceRepository.getAllTimeSum());

            return statisticsDTO;
    }


    // region PRIVATE

    /**
     * pomocná funkce k vyheldání faktury dle ID
     * @param id
     * @return
     */
    private InvoiceEntity fetchInvoiceById(long id) {
        return invoiceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Invoice with id" + id + "was't found"));
    }
    // endregion
}
