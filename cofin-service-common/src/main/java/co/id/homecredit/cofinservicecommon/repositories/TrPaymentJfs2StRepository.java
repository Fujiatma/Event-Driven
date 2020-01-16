package co.id.homecredit.cofinservicecommon.repositories;

import co.id.homecredit.cofinservicecommon.entities.TrPaymentJfs2St;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author fujiatma.napitupulu
 */
public interface TrPaymentJfs2StRepository extends JpaRepository<TrPaymentJfs2St, Long> {
    List<TrPaymentJfs2St> findByStPaymentId(Long stPaymentId);
    List<TrPaymentJfs2St> findByTrPaymentJfsId(Long trPaymentJfsId);
}
