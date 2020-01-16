package id.co.homecredit.cofinservicepermatapayment.modules.paymentregrev.services.impl;

import co.id.homecredit.cofinservicecommon.dto.permata.PaymentDto;
import co.id.homecredit.cofinservicecommon.entities.StPayment;
import co.id.homecredit.cofinservicecommon.entities.TrPaymentJfs;
import co.id.homecredit.cofinservicecommon.entities.TrPaymentJfs2St;
import id.co.homecredit.cofinservicepermatapayment.modules.paymentregrev.facades.PaymentFacades;
import id.co.homecredit.cofinservicepermatapayment.modules.paymentregrev.services.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author fujiatma.napitupulu
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    public static final Logger log = LoggerFactory.getLogger(PaymentServiceImpl.class);

    @Autowired
    PaymentFacades paymentFacades;

    @Override
    public void doPayment(String requestId,PaymentDto paymentDto) {
        //get tr_payment_jfs
        log.info("PaymentServiceImpl.doPayment requestId : {}, paymentDto : {}", requestId, paymentDto);
        TrPaymentJfs trPaymentJfs = paymentFacades.findTrPaymentJfs(paymentDto.getId());
        if(trPaymentJfs == null){
            log.info("PaymentServiceImpl.doPayment with id {} not found", paymentDto.getId());
            return;
        }

        this.process(paymentDto, trPaymentJfs);

        log.info("PaymentServiceImpl.doPayment success requestId : {}, paymentDto : {}", requestId, paymentDto);
    }

    private void process(PaymentDto paymentDto, TrPaymentJfs trPaymentJfs){
        //get st_payment by contract_Number and Export Date = NULL
        StPayment payment = paymentFacades.findStPayment(paymentDto.getContractNumber());

        //initial date
        Date now = new Date();
        boolean isProcess = true;

        //if st_payment is null then
        if(payment == null){
            payment = this.insertToStPayment(paymentDto, trPaymentJfs);
        }else{  //if st_payment exist then
            payment.setPmtInstallment(payment.getPmtInstallment().add(trPaymentJfs.getPmtInstallment()));
            payment.setBslTotalAmtPayment(payment.getBslTotalAmtPayment().add(trPaymentJfs.getTotalAmtPayment()));
            payment.setBslAmtPayment(payment.getBslAmtPayment().add(trPaymentJfs.getTotalAmtPaymentJfs()));
            payment.setUpdatedBy("PaymentServiceImpl");
            payment.setDtimeUpdated(now);
        }

        //decide st_payment, is st_payment is regular, reversal, or not send to bank
        int result = payment.getPmtInstallment().compareTo(BigDecimal.ZERO);
        switch (result){
            case -1 :
                payment.setPaymentTypeJfs("REV");
                break;
            case 1:
                payment.setPaymentTypeJfs("REG");
                break;
            case 0:
                isProcess = false;
                break;
        }

        if(isProcess){
            //set process to 1 for tr_payment_jfs
            trPaymentJfs.setStatus("P");
            trPaymentJfs.setUpdatedBy("PaymentServiceImpl");
            trPaymentJfs.setDtimeUpdated(now);

            //build TrPaymentJfs2St
            TrPaymentJfs2St jfs2St = new TrPaymentJfs2St();

            //save all
            paymentFacades.save(payment, trPaymentJfs, jfs2St);
        }else {
            List<TrPaymentJfs2St> jfs2StListStPayment = new ArrayList<>();
            Long stPaymentId = null;
            stPaymentId = payment.getId();
            jfs2StListStPayment = paymentFacades.findByStPaymentId(stPaymentId);

            List<TrPaymentJfs2St> jfs2StListTrPaymentJfs = new ArrayList<>();
            jfs2StListTrPaymentJfs = paymentFacades.findByStPaymentId(stPaymentId);
            //check if st_payment exist
            //if exist
            if(payment != null){
                //find all TrPaymentJfs2St which have st_payment_id = st_payment.id
                jfs2StListStPayment = paymentFacades.findByStPaymentId(payment.getId());

                //find all tr_payment_jfs which have TrPaymentJfs2St.tr_payment_jfs_id
                jfs2StListTrPaymentJfs = paymentFacades.findByTrPaymentJfsId(trPaymentJfs.getId());

                //set tr_payment_jfs.status to Y
                trPaymentJfs.setStatus("Y");

                //delete all TrPaymentJfs2St, update all tr_payment_jfs, delete st_payment
                paymentFacades.deleteTrPaymentJfs2St(jfs2StListStPayment);
                this.updateTrPaymentJfs(trPaymentJfs, payment);
                paymentFacades.deleteByStPaymentId(stPaymentId);


            }else{
                //if not exists
                //update tr_payment_jfs
                this.updateTrPaymentJfs(trPaymentJfs, payment);

            }
        }

    }

    private TrPaymentJfs updateTrPaymentJfs(TrPaymentJfs trPaymentJfs, StPayment payment) {
        Date now = new Date();
        trPaymentJfs = new TrPaymentJfs();
        trPaymentJfs.setPmtInstallment(payment.getPmtInstallment().add(trPaymentJfs.getPmtInstallment()));
        trPaymentJfs.setTotalAmtPayment(payment.getBslTotalAmtPayment().add(trPaymentJfs.getTotalAmtPayment()));
        trPaymentJfs.setTotalAmtPaymentJfs(payment.getBslAmtPayment().add(trPaymentJfs.getTotalAmtPaymentJfs()));
        trPaymentJfs.setUpdatedBy("PaymentServiceImpl");
        trPaymentJfs.setStatus("S");
        trPaymentJfs.setDtimeUpdated(now);
        return trPaymentJfs;
    }

    private StPayment insertToStPayment(PaymentDto paymentDto, TrPaymentJfs trPaymentJfs) {
        //get st_payment
        StPayment payment = new StPayment();
        payment.setBslTotalAmtPayment(trPaymentJfs.getTotalAmtPayment());
        payment.setPaymentTypeJfs(trPaymentJfs.getPaymentTypeJfs());
        payment.setBslAmtPayment(trPaymentJfs.getTotalAmtPaymentJfs());
        payment.setAdjPaymentDate(trPaymentJfs.getAdjPaymentDate());
        payment.setContractNumber(trPaymentJfs.getContractNumber());
        payment.setPaymentDate(trPaymentJfs.getPaymentDate());
        payment.setPmtInstallment(trPaymentJfs.getPmtInstallment());
        payment.setCreatedBy(trPaymentJfs.getCreatedBy());
        payment.setExportDate(trPaymentJfs.getExportDate());
        payment.setBankPaymentType(trPaymentJfs.getPaymentTypeJfs());

        return payment;
    }

}
