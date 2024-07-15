package cz.itnetwork.entity.repository;

import cz.itnetwork.entity.InvoiceEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;



public interface InvoiceRepository extends JpaRepository<InvoiceEntity, Long>, JpaSpecificationExecutor<InvoiceEntity> {

    /**
     * počet faktur
     * @return
     */
    @Query("SELECT COUNT(*) FROM invoice")
    long countInvoices();

    /**
     * celková suma faktur
     * @return
     */
    @Query("SELECT SUM(`price`) FROM invoice")
    double getAllTimeSum();

    /**
     * celková suma faktur v současném roce
     * @return
     */
    @Query("SELECT SUM(`price`) FROM invoice WHERE YEAR(`dueDate`) = YEAR(CURRENT_DATE)")
    double getCurrentYearSum();
}
