package id.co.homecredit.cofinservicepermatapayment.modules.paymentregrev.services;

import co.id.homecredit.cofinservicecommon.dto.permata.PaymentDto;

import java.util.List;

/**
 * @author fujiatma.napitupulu
 */
public interface PaymentService {

    //get data from Queue
    void doPayment(String requestId, PaymentDto paymentDto);

}
