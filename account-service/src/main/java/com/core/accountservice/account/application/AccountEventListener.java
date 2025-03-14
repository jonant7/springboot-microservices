package com.core.accountservice.account.application;

import com.core.accountservice.account.domain.dtos.AccountPostDTO;
import com.core.accountservice.customer.event.CustomerCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class AccountEventListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountEventListener.class);
    private final AccountService accountService;

    public AccountEventListener(AccountService accountService) {
        this.accountService = accountService;
    }

    @RabbitListener(queues = "${spring.rabbitmq.template.default-receive-queue}")
    public void handleCustomerCreatedEvent(CustomerCreatedEvent event) {
        try {
            LOGGER.info("Event received: {}", event);
            accountService.create(new AccountPostDTO(event));
            LOGGER.info("Account successfully created for customer: {}", event.getCustomerId());
        } catch (Exception e) {
            LOGGER.error("Error processing CustomerCreatedEvent", e);
        }
    }
}