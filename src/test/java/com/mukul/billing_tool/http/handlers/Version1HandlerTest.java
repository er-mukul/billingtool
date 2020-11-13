package com.mukul.billing_tool.http.handlers;

import com.mukul.billing_tool.dom.common.BeanValidation;
import com.mukul.billing_tool.dto.BillDetail;
import com.mukul.billing_tool.entity.Customer;
import com.mukul.billing_tool.entity.ItemDetail;
import com.mukul.billing_tool.enums.CustomerType;
import com.mukul.billing_tool.exception.ClientException;
import com.mukul.billing_tool.exception.GenericException;
import com.mukul.billing_tool.helper.DTOHelper;
import com.mukul.billing_tool.jpa.CustomerRepository;
import com.mukul.billing_tool.jpa.ItemRepository;
import com.mukul.billing_tool.services.BillingServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.ServerRequest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

@DisplayName("Unit Tests- Version1 Handler Test")
class Version1HandlerTest {
    @InjectMocks
    private Version1Handler version1Handler;
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private ItemRepository itemRepository;
    @Mock
    private BeanValidation beanValidation;
    @Mock
    private BillingServiceImpl billingService;

    private ServerRequest serverRequest;

    @BeforeEach
    public void init(){
        MockitoAnnotations.initMocks(this);
        serverRequest = mock(ServerRequest.class);
    }

    @Test
    void saveCustomer() {
        Customer mockCustomer = Customer.builder().id(12l).customerName("TestName")
                .customerType(CustomerType.Employee).joiningDate(LocalDate.now()).build();
        doReturn(Mono.just(mockCustomer))
                .when(serverRequest).bodyToMono(Customer.class);
        doReturn(mockCustomer).when(beanValidation).dtoValidation(any());
        doReturn(mockCustomer).when(customerRepository).save(any());

        StepVerifier.create(version1Handler.saveCustomer(serverRequest))
                .assertNext(value -> {
                    assertThat(value,is(notNullValue()));
                    assertThat("Status should be OK",value.statusCode().equals(HttpStatus.OK));
                })
                .expectComplete()
                .verify();
    }

    @Test
    void saveCustomerErrorCase() {
        Customer mockCustomer = DTOHelper.createCustomer(CustomerType.Employee,LocalDate.now());
        doReturn(Mono.just(mockCustomer))
                .when(serverRequest).bodyToMono(Customer.class);
        doReturn(mockCustomer).when(beanValidation).dtoValidation(any());
        doReturn(Mono.error(ClientException.builder
        .get().withStatus(HttpStatus.INTERNAL_SERVER_ERROR).build())).when(customerRepository).save(any());

        StepVerifier.create(version1Handler.saveCustomer(serverRequest))
                .expectError(GenericException.class)
                .verify();
    }

    @Test
    void generateBill() {
        Customer mockCustomer = DTOHelper.createCustomer(CustomerType.Employee,LocalDate.now());
        List<ItemDetail> itemDetailList = DTOHelper.createList();
        BillDetail billDetail = DTOHelper.getBillDetail();

        doReturn(Mono.just(billDetail))
                .when(serverRequest).bodyToMono(BillDetail.class);
        doReturn(billDetail).when(beanValidation).dtoValidation(any());
        doReturn(mockCustomer).when(customerRepository).findCustomerByCustomerName(any());
        doReturn(Optional.of(itemDetailList.get(0))).when(itemRepository).findById(anyLong());

        StepVerifier.create(version1Handler.generateBill(serverRequest))
                .assertNext(value -> {
                    assertThat(value,is(notNullValue()));
                    assertThat("Status should be OK",value.statusCode().equals(HttpStatus.OK));
                })
                .expectComplete()
                .verify();


    }

    @Test
    void generateBillgivesError() {
        Customer mockCustomer = DTOHelper.createCustomer(CustomerType.Employee,LocalDate.now());
        List<ItemDetail> itemDetailList = DTOHelper.createList();
        BillDetail billDetail = DTOHelper.getBillDetail();

        doReturn(Mono.error(ClientException.builder
                .get().withStatus(HttpStatus.INTERNAL_SERVER_ERROR).build()))
                .when(serverRequest).bodyToMono(BillDetail.class);
        doReturn(billDetail).when(beanValidation).dtoValidation(any());
        doReturn(mockCustomer).when(customerRepository).findCustomerByCustomerName(any());
        doReturn(Optional.of(itemDetailList.get(0))).when(itemRepository).findById(anyLong());

        StepVerifier.create(version1Handler.generateBill(serverRequest))
                .expectError(GenericException.class)
                .verify();


    }
}