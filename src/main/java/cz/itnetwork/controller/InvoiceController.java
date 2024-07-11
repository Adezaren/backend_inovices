package cz.itnetwork.controller;

import cz.itnetwork.dto.InvoiceDTO;

import cz.itnetwork.dto.InvoiceStatisticsDTO;
import cz.itnetwork.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;


    @PostMapping("/invoices")
    public InvoiceDTO addInvoice(@RequestBody InvoiceDTO invoiceDTO) {
        return invoiceService.addInvoice(invoiceDTO);
    }

    @GetMapping("/invoices/{invoiceId}")
    public InvoiceDTO getInvoice(@PathVariable long invoiceId) {
        return invoiceService.getInvoiceById(invoiceId);
    }

    @DeleteMapping("/invoices/{invoiceId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteInvoice(@PathVariable long invoiceId) {
        invoiceService.deleteInvoice(invoiceId);
    }

    @PutMapping("/invoices/{invoiceId}")
    public InvoiceDTO editInvoice(@RequestBody InvoiceDTO invoiceDTO, @PathVariable long invoiceId) {
        return invoiceService.editInvoice(invoiceDTO, invoiceId);
    }

    @GetMapping("/invoices")
    public List<InvoiceDTO> getInvoices() {
        return invoiceService.getAll();
    }


    @GetMapping("/invoices/statistics")
    public InvoiceStatisticsDTO getInvoiceStatistics(){

        return invoiceService.getInvoiceStatistics();
    }


}
