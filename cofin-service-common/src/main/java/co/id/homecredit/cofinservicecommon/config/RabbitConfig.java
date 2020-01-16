package co.id.homecredit.cofinservicecommon.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public abstract class RabbitConfig {

    @Bean
    public MessageConverter jsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    public Queue queue(String queue, boolean durable, boolean exclusive, boolean autoDelete){
        return new Queue(queue, durable, exclusive, autoDelete);
    }

    public DirectExchange exchange(String exchange){
        return new DirectExchange(exchange);
    }

    public Binding binding(Queue queue, DirectExchange exchange, String routing){
        return BindingBuilder.bind(queue).to(exchange).with(routing);
    }

    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory){
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }

}
