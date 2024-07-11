package cz.itnetwork.entity.repository;

import cz.itnetwork.entity.InvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface InvoiceRepository extends JpaRepository<InvoiceEntity, Long> {


    @Query("SELECT COUNT(*) FROM invoice")
    long countInvoices();

    @Query("SELECT SUM(`price`) FROM invoice")
    double getAllTimeSum();

    @Query("SELECT SUM(`price`) FROM invoice WHERE YEAR(`dueDate`) = YEAR(CURRENT_DATE)")
    double getCurrentYearSum();





}
