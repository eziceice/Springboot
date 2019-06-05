package com.example.springboot.core.config;
import com.example.springboot.core.other.message.service.RabbitMessageReceiver;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;

@Configuration
public class AmqpConfig {

    @Value("${rabbitmq.queue.user}")
    private String userQueueName;

    @Value("${rabbitmq.exchange}")
    private String exchangeName;

    @Bean
    Queue queue() {
        return new Queue(userQueueName, true);
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange(exchangeName);
    }

    @Bean
    Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(userQueueName);
    }

    @Bean
    SimpleMessageListenerContainer springAmqpContainer(ConnectionFactory connectionFactory,
                                                       MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(userQueueName);
        container.setMessageListener(listenerAdapter);
        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(RabbitMessageReceiver messageReceiver) {
        return new MessageListenerAdapter(messageReceiver, "receiveUser");
    }

}
