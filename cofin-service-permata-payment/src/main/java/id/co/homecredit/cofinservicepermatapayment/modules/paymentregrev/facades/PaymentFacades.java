package id.co.homecredit.cofinservicepermatapayment.modules.paymentregrev.facades;

import co.id.homecredit.cofinservicecommon.entities.StPayment;
import co.id.homecredit.cofinservicecommon.entities.TrPaymentJfs;
import co.id.homecredit.cofinservicecommon.entities.TrPaymentJfs2St;

import java.util.List;

/**
 * @author fujiatma.napitupulu
 */
public interface PaymentFacades {

    List<StPayment> findAllStPayment();

    List<TrPaymentJfs> findAllTrPaymentJfs();

    StPayment findStPayment(String contractNumber);

    TrPaymentJfs findTrPaymentJfs(Long id);

    void save(StPayment payment, TrPaymentJfs paymentJfs, TrPaymentJfs2St jfs2St);

    void deleteTrPaymentJfs2St(List<TrPaymentJfs2St> trPaymentJfs2StList);

    void deleteByStPaymentId(Long stPaymentId);

    List<TrPaymentJfs2St> findByStPaymentId(Long stPayment);

    List<TrPaymentJfs2St> findByTrPaymentJfsId(Long trPaymentJfs);




}
