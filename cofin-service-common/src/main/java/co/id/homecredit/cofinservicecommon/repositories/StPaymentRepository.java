
package co.id.homecredit.cofinservicecommon.repositories;

import co.id.homecredit.cofinservicecommon.entities.StPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



/**
 * @author fujiatma.napitupulu
 */

@Repository
public interface StPaymentRepository extends JpaRepository<StPayment, Long> {
//    @Query("SELECT st FROM ST_PAYMENT st WHERE st.contractNumber = :contractNumber, st.exportDate = :exportDate")
//    List<StPayment> findByContNumExpDate(@Param("contractNumber") String contractNumber, @Param("exportDate") Date exportDate);
    StPayment findByContractNumberAndExportDateIsNull(String contractNumber);
}

