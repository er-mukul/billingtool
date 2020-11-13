package com.mukul.billing_tool.http.handlers;

import com.mukul.billing_tool.dom.common.BeanValidation;
import com.mukul.billing_tool.dto.BillDetail;
import com.mukul.billing_tool.entity.Customer;
import com.mukul.billing_tool.entity.ItemDetail;
import com.mukul.billing_tool.exception.ClientExceptionMessaging;
import com.mukul.billing_tool.jpa.CustomerRepository;
import com.mukul.billing_tool.jpa.ItemRepository;
import com.mukul.billing_tool.services.BillingServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;
@Slf4j
@Component
@RequiredArgsConstructor
public class Version1Handler {
   private final CustomerRepository customerRepository;
   private final ItemRepository itemRepository;
   private final BeanValidation beanValidation;
   private final BillingServiceImpl billingService;

    public Mono<ServerResponse> saveCustomer(final ServerRequest serverRequest){
        log.debug("In Save customer method.");
       return serverRequest.bodyToMono(Customer.class)
               .map(beanValidation::dtoValidation)
               .map(customer -> customerRepository.save(customer))
               .flatMap(customer -> {
                   log.debug("Customer saved successfully");
                   return ok().contentType(MediaType.APPLICATION_STREAM_JSON).body(Mono.just(customer), Customer.class);
               })
               .onErrorResume(throwable -> Mono.error(ClientExceptionMessaging.builder.get().withStatus(HttpStatus.BAD_REQUEST).withMessage("Customer info not correct").build()));
    }

    public Mono<ServerResponse> generateBill(final ServerRequest serverRequest){
        log.debug("In generateBill method.");
        return serverRequest.bodyToMono(BillDetail.class)
                .map(beanValidation::dtoValidation)
                .flatMap(billDetail -> {
                    Customer customer = customerRepository.findCustomerByCustomerName(billDetail.getCustomerName());
                    List<ItemDetail> itemDetailList = billDetail.getItems().stream().map(itemDetail -> {
                        ItemDetail itemDetailFromDb = itemRepository.findById(itemDetail.getId()).get();
                        itemDetailFromDb.setQuantity(itemDetail.getQuantity());
                        return itemDetailFromDb;
                    }).collect(Collectors.toList());
                    log.debug("Bill generated successfully");
                    return ok().contentType(MediaType.APPLICATION_STREAM_JSON).body(Mono.just(billingService.calculateBillAmount(customer,itemDetailList)), Customer.class);
                })
                .onErrorResume(throwable -> Mono.error(ClientExceptionMessaging.builder.get().withStatus(HttpStatus.BAD_REQUEST).withMessage("Billing info not correct").build()));
    }

}
