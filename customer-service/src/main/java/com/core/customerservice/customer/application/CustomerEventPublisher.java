package com.core.customerservice.customer.application;

import com.core.customerservice.account.domain.AccountPostDTO;
import com.core.customerservice.customer.domain.events.CustomerCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerEventPublisher {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerEventPublisher.class);
    private final RabbitTemplate rabbitTemplate;

    @Value("${spring.rabbitmq.template.exchange}")
    private String exchange;

    @Value("${spring.rabbitmq.template.routing-key.customer-created}")
    private String routingKey;

    public CustomerEventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishCustomerCreated(Long customerId, List<AccountPostDTO> dtos) {
        dtos.forEach(dto -> {
            dto.setCustomerId(customerId);
            CustomerCreatedEvent event = new CustomerCreatedEvent(dto);
            try {
                rabbitTemplate.convertAndSend(exchange, routingKey, event);
                LOGGER.info("Event sent to RabbitMQ: {}", event);
            } catch (Exception e) {
                LOGGER.error("Error sending event to RabbitMQ", e);
            }
        });
    }
}