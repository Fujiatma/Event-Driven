package id.co.homecredit.cofinservicepermatapayment.modules.paymentregrev.facades.impl;

import co.id.homecredit.cofinservicecommon.entities.StPayment;
import co.id.homecredit.cofinservicecommon.entities.TrPaymentJfs;
import co.id.homecredit.cofinservicecommon.entities.TrPaymentJfs2St;
import co.id.homecredit.cofinservicecommon.repositories.StPaymentRepository;
import co.id.homecredit.cofinservicecommon.repositories.TrPaymentJfs2StRepository;
import co.id.homecredit.cofinservicecommon.repositories.TrPaymentJfsRepository;
import id.co.homecredit.cofinservicepermatapayment.modules.paymentregrev.facades.PaymentFacades;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author fujiatma.napitupulu
 */
@Service
public class PaymentFacadesImpl implements PaymentFacades {

    @Autowired
    StPaymentRepository stPaymentRepository;

    @Autowired
    TrPaymentJfsRepository trPaymentJfsRepository;

    @Autowired
    TrPaymentJfs2StRepository trPaymentJfs2StRepository;


    @Override
    public List<StPayment> findAllStPayment() {
        return stPaymentRepository.findAll();
    }

    @Override
    public List<TrPaymentJfs> findAllTrPaymentJfs(){
        return trPaymentJfsRepository.findAll();
    }

    @Override
    //get st_payment by contract_Number and Export Date = NULL
    public StPayment findStPayment(String contractNumber) {
        return stPaymentRepository.findByContractNumberAndExportDateIsNull(contractNumber);
    }

    @Override
    public List<TrPaymentJfs2St> findByStPaymentId(Long stPayment) {
        return trPaymentJfs2StRepository.findByStPaymentId(stPayment);
    }

    @Override
    public List<TrPaymentJfs2St> findByTrPaymentJfsId(Long trPaymentJfs) {
        return trPaymentJfs2StRepository.findByTrPaymentJfsId(trPaymentJfs);
    }


    @Override
    public TrPaymentJfs findTrPaymentJfs(Long id) {
        Optional<TrPaymentJfs> paymentJfs = trPaymentJfsRepository.findById(id);
        return paymentJfs.isPresent() ? paymentJfs.get() : null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(StPayment payment, TrPaymentJfs paymentJfs, TrPaymentJfs2St jfs2St) {
        //save st payment
        payment = stPaymentRepository.save(payment);

        //save tr_payment_jfs
        paymentJfs = trPaymentJfsRepository.save(paymentJfs);

        //save tr_payment_jfs_2_st
        jfs2St.setStPayment(payment);
        jfs2St.setTrPaymentJfs(paymentJfs);
        trPaymentJfs2StRepository.save(jfs2St);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteTrPaymentJfs2St(List<TrPaymentJfs2St> trPaymentJfs2StList){
        //delete TrPaymentJfs2St
        trPaymentJfs2StRepository.deleteAll(trPaymentJfs2StList);
    }

    @Override
    public void deleteByStPaymentId(Long stPaymentId) {
        stPaymentRepository.deleteById(stPaymentId);
    }


}
