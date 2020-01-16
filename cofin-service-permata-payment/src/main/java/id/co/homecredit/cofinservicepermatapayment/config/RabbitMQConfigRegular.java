package id.co.homecredit.cofinservicepermatapayment.config;

import co.id.homecredit.cofinservicecommon.config.RabbitConfig;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfigRegular extends RabbitConfig {

    public final static String QUEUE_REG = "jfs.permata-payment-reg-staging.queue";

    @Bean
    Queue queuePayment(){
        return super.queue("", true, false, false);
    }

    @Bean
    DirectExchange exchangePayment(){
        return super.exchange("");
    }

    @Bean
    Binding bindingPayment(){
        return super.binding(this.queuePayment(), this.exchangePayment(), "");
    }

    @Bean
    AmqpTemplate amqpTemplatePayment(ConnectionFactory connectionFactory){
        return super.amqpTemplate(connectionFactory);
    }

}
