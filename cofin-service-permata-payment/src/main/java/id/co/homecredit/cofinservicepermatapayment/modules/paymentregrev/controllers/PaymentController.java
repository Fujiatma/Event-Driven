package id.co.homecredit.cofinservicepermatapayment.modules.paymentregrev.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;

/**
 * @author fujiatma.napitupulu
 */
@Controller
public class PaymentController {

    @RequestMapping(value = "/permata/payment/regrev", method = {RequestMethod.GET,RequestMethod.POST})
    public void populatePayment() throws IOException,Exception{

    }
}
