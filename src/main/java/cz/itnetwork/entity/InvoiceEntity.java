package cz.itnetwork.entity;

import cz.itnetwork.dto.PersonDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.text.DateFormat;
import java.time.LocalDate;

@Entity(name = "invoice")
@Getter
@Setter
public class InvoiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private int invoiceNumber;

    @ManyToOne
    private PersonEntity seller;

    @ManyToOne
    private PersonEntity buyer;

    @Column(nullable = false)
    private LocalDate issued;

    @Column(nullable = false)
    private LocalDate dueDate;

    @Column(nullable = false)
    private String product;

    @Column(nullable = false)
    private long price;

    @Column(nullable = false)
    private int vat;

    private String note;







}
