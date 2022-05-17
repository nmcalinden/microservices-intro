package com.micro.customer.service;

import com.micro.clients.fraud.FraudCheckResponse;
import com.micro.clients.fraud.FraudClient;
import com.micro.clients.notifications.NotificationClient;
import com.micro.clients.notifications.NotificationRequest;
import com.micro.customer.Customer;
import com.micro.customer.CustomerRepository;
import com.micro.customer.CustomerRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final FraudClient fraudClient;
    private final NotificationClient notificationClient;

    public void registerCustomer(CustomerRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .build();

        customerRepository.saveAndFlush(customer);
        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());

        if(fraudCheckResponse == null || fraudCheckResponse.getIsFraud()) {
            throw new IllegalStateException("Fraudster");
        }

        notificationClient.sendNotification(
                NotificationRequest.builder()
                        .toCustomerId(customer.getId())
                        .toCustomerName(customer.getEmail())
                        .message("Hey - Notification sent!")
                        .build()
        );
    }
}