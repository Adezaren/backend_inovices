package cz.itnetwork.service;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.InvoiceStatisticsDTO;
import cz.itnetwork.entity.filter.InvoiceFilter;

import java.util.List;

public interface InvoiceService {

    InvoiceDTO addInvoice(InvoiceDTO invoiceDTO);

    List<InvoiceDTO> getAll(InvoiceFilter invoiceFilter);

    InvoiceDTO getInvoiceById(long id);

    void deleteInvoice(long id);

    InvoiceDTO editInvoice(InvoiceDTO invoiceDTO, long invoiceId);

    InvoiceStatisticsDTO getInvoiceStatistics();




}
