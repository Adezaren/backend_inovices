package cz.itnetwork.service;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.InvoiceStatisticsDTO;
import cz.itnetwork.dto.mapper.InvoiceMapper;
import cz.itnetwork.entity.InvoiceEntity;
import cz.itnetwork.entity.repository.InvoiceRepository;
import cz.itnetwork.entity.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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


    public InvoiceDTO addInvoice(InvoiceDTO invoiceDTO) {
        InvoiceEntity entity = invoiceMapper.toEntity(invoiceDTO);
        entity.setBuyer(personRepository.getReferenceById(invoiceDTO.getBuyer().getId()));
        entity.setSeller(personRepository.getReferenceById(invoiceDTO.getSeller().getId()));
        entity = invoiceRepository.saveAndFlush(entity);

        return invoiceMapper.toDTO(entity);
    }

    @Override
    public List<InvoiceDTO> getAll() {
        return invoiceRepository.findAll()
                .stream()
                .map(i -> invoiceMapper.toDTO(i))
                .collect(Collectors.toList());
    }

    @Override
    public InvoiceDTO getInvoiceById(long id) {
        InvoiceEntity invoiceEntity = fetchInvoiceById(id);
        return invoiceMapper.toDTO(invoiceEntity);
    }

    @Override
    public void deleteInvoice(long invoiceId){
        try {
            InvoiceEntity invoice = fetchInvoiceById(invoiceId);

            invoiceRepository.delete(invoice);
        } catch (NotFoundException ignored) {
            // The contract in the interface states, that no exception is thrown, if the entity is not found.

        }
    }

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

    @Override
    public InvoiceStatisticsDTO getInvoiceStatistics(){

        InvoiceStatisticsDTO statisticsDTO = new InvoiceStatisticsDTO();
            statisticsDTO.setInvoicesCount(invoiceRepository.countInvoices());
            statisticsDTO.setCurrentYearSum(invoiceRepository.getCurrentYearSum());
            statisticsDTO.setAllTimeSum(invoiceRepository.getAllTimeSum());

        return statisticsDTO;
    }



    private InvoiceEntity fetchInvoiceById(long id) {
        return invoiceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Invoice with id" + id + "was't found"));
    }





}
