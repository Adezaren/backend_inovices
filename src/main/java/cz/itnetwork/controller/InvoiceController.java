package cz.itnetwork.controller;

import cz.itnetwork.dto.InvoiceDTO;

import cz.itnetwork.dto.InvoiceStatisticsDTO;
import cz.itnetwork.entity.filter.InvoiceFilter;
import cz.itnetwork.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    /**
     * Vložení faktury
     * @param invoiceDTO
     * @return
     */
    @PostMapping("/invoices")
    public InvoiceDTO addInvoice(@RequestBody InvoiceDTO invoiceDTO) {
        return invoiceService.addInvoice(invoiceDTO);
    }

    /**
     * Vypsání detailu faktury
     * @param invoiceId
     * @return
     */
    @GetMapping("/invoices/{invoiceId}")
    public InvoiceDTO getInvoice(@PathVariable long invoiceId) {
        return invoiceService.getInvoiceById(invoiceId);
    }


    /**
     * Smazání faktury
     * @param invoiceId
     */
    @DeleteMapping("/invoices/{invoiceId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteInvoice(@PathVariable long invoiceId) {
        invoiceService.deleteInvoice(invoiceId);
    }

    /**
     * Úprava faktury
     * @param invoiceDTO
     * @param invoiceId
     * @return
     */
    @PutMapping("/invoices/{invoiceId}")
    public InvoiceDTO editInvoice(@RequestBody InvoiceDTO invoiceDTO, @PathVariable long invoiceId) {
        return invoiceService.editInvoice(invoiceDTO, invoiceId);
    }

    /**
     * Vypsání všech faktur
     * @param invoiceFilter
     * @return List
     */
    @GetMapping("/invoices")
    public List<InvoiceDTO> getInvoices(InvoiceFilter invoiceFilter) {
        return invoiceService.getAll(invoiceFilter);
    }


    /**
     * Vypsání statistiky faktur
     * @return
     */
    @GetMapping("/invoices/statistics")
    public InvoiceStatisticsDTO getInvoiceStatistics(){

        return invoiceService.getInvoiceStatistics();
    }


}
