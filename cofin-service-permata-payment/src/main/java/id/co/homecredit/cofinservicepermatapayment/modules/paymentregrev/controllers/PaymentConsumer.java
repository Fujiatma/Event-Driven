package id.co.homecredit.cofinservicepermatapayment.modules.paymentregrev.controllers;

import co.id.homecredit.cofinservicecommon.dto.permata.PaymentDto;
import id.co.homecredit.cofinservicepermatapayment.config.RabbitMQConfigRegular;
import id.co.homecredit.cofinservicepermatapayment.config.RabbitMQConfigReversal;
import id.co.homecredit.cofinservicepermatapayment.modules.paymentregrev.services.PaymentService;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentConsumer {

    private static final Logger log = LoggerFactory.getLogger(PaymentConsumer.class);

    @Autowired
    PaymentService paymentService;

    @RabbitListener(queues = RabbitMQConfigRegular.QUEUE_REG)
    public void consumeReg(PaymentDto paymentDtoReg) {
        //TODO : consume payment from queue
        String requestId = RandomStringUtils.randomAlphanumeric(10);
        log.info("PaymentConsumer.consumeReg requestId : {}, paymentDtoReg : {}", requestId, paymentDtoReg);
        paymentService.doPayment(requestId, paymentDtoReg);
    }

    @RabbitListener(queues = RabbitMQConfigReversal.QUEUE_REV)
    public void consumeRev(PaymentDto paymentDtoRev) {
        //TODO : consume payment from queue
        String requestId = RandomStringUtils.randomAlphanumeric(10);
        log.info("PaymentConsumer.consumeRev requestId : {}, paymentDtoRev : {}", requestId, paymentDtoRev);
        paymentService.doPayment(requestId, paymentDtoRev);
    }

}
