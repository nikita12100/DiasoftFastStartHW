package ru.diasoft.micro.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.diasoft.micro.domain.*;
import ru.diasoft.micro.repository.SmsVerificationRepository;
import ru.diasoft.micro.smsverificationcreated.publish.SmsVerificationCreatedPublishGateway;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SmsVerificationServiceImplTest {

    @Mock
    private SmsVerificationRepository repository;

    @Mock
    private SmsVerificationCreatedPublishGateway gateway;

    private SmsVerificationServiceImpl service;

    private final String PHONE_NUMBER = "+79765431234";
    private final String VALID_SECRET_CODE = "0001";
    private final String INVALID_SECRET_CODE = "0002";
    private final String GUID = UUID.randomUUID().toString();
    private final String STATUS_OK = "OK";

    @Before
    public void init() {
        service = new SmsVerificationServiceImpl(repository, gateway);
        SmsVerification smsVerification =
                SmsVerification.builder()
                        .phoneNumber(PHONE_NUMBER)
                        .processGuid(GUID)
                        .secretCode(VALID_SECRET_CODE)
                        .status(STATUS_OK)
                        .build();

        Mockito.when(repository.findBySecretCodeAndProcessGuidAndStatus(VALID_SECRET_CODE, GUID, STATUS_OK))
                .thenReturn(Optional.of(smsVerification));
        Mockito.when(repository.findBySecretCodeAndProcessGuidAndStatus(INVALID_SECRET_CODE, GUID, STATUS_OK))
                .thenReturn(Optional.empty());
        Mockito.when(repository.save(any(SmsVerification.class))).thenReturn(smsVerification);
    }

    @Test
    public void dsSmsVerificationCheck() {
        SmsVerificationCheckRequest smsVerificationCheckRequest = new SmsVerificationCheckRequest();
        smsVerificationCheckRequest.setProcessGUID(GUID);
        smsVerificationCheckRequest.setCode(VALID_SECRET_CODE);

        SmsVerificationCheckResponse response = service.dsSmsVerificationCheck(smsVerificationCheckRequest);

        Assert.assertEquals(response.getCheckResult(), true);
    }

    @Test
    public void dsSmsVerificationCheckInvalidCode() {
        SmsVerificationCheckRequest smsVerificationCheckRequest = new SmsVerificationCheckRequest();
        smsVerificationCheckRequest.setProcessGUID(GUID);
        smsVerificationCheckRequest.setCode(INVALID_SECRET_CODE);

        SmsVerificationCheckResponse response = service.dsSmsVerificationCheck(smsVerificationCheckRequest);

        Assert.assertEquals(response.getCheckResult(), false);
    }

    @Test
    public void dsSmsVerificationCreate() {
        SmsVerificationPostRequest smsVerificationPostRequest = new SmsVerificationPostRequest();
        smsVerificationPostRequest.setPhoneNumber(PHONE_NUMBER);

        SmsVerificationPostResponse response = service.dsSmsVerificationCreate(smsVerificationPostRequest);

        Assert.assertNotNull(response.getProcessGUID());
    }
}