package cz.itnetwork.entity.repository;

import cz.itnetwork.entity.InvoiceEntity;
import cz.itnetwork.entity.filter.InvoiceFilter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;


public interface InvoiceRepository extends JpaRepository<InvoiceEntity, Long>, JpaSpecificationExecutor<InvoiceEntity> {


    @Query("SELECT COUNT(*) FROM invoice")
    long countInvoices();

    @Query("SELECT SUM(`price`) FROM invoice")
    double getAllTimeSum();

    @Query("SELECT SUM(`price`) FROM invoice WHERE YEAR(`dueDate`) = YEAR(CURRENT_DATE)")
    double getCurrentYearSum();







}
