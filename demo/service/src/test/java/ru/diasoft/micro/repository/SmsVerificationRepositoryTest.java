package ru.diasoft.micro.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.diasoft.micro.domain.SmsVerification;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SmsVerificationRepositoryTest {

    @Autowired
    private SmsVerificationRepository repository;

    private final String PHONE_NUMBER = "+7999999999";
    private final String SECRET_CODE = "1234";
    private final String STATUS_OK = "OK";
    private final String STATUS_WAITING = "WAITING";
    private final String STATUS_CREATED = "CREATED";

    @Test
    public void smsVerificationCreateTest() {
        SmsVerification smsVerification =
                SmsVerification.builder()
                        .processGuid(UUID.randomUUID().toString())
                        .phoneNumber(PHONE_NUMBER)
                        .secretCode(SECRET_CODE)
                        .status(STATUS_WAITING)
                        .build();

        SmsVerification smsVerificationCreated = repository.save(smsVerification);

        assertThat(smsVerification).isEqualToComparingOnlyGivenFields(smsVerificationCreated, "verificationId");
        assertThat(smsVerificationCreated.getVerificationId()).isNotNull();
    }

    @Test
    public void findBySecretCodeAndProcessGuidAndStatus() {

        SmsVerification smsVerification =
                SmsVerification.builder()
                        .processGuid(UUID.randomUUID().toString())
                        .phoneNumber(PHONE_NUMBER)
                        .secretCode(SECRET_CODE)
                        .status(STATUS_CREATED)
                        .build();

        SmsVerification smsVerificationCreated = repository.save(smsVerification);

        Assert.assertEquals(
                repository.findBySecretCodeAndProcessGuidAndStatus(SECRET_CODE, smsVerificationCreated.getProcessGuid(), STATUS_CREATED).get(),
                smsVerificationCreated
        );
        Assert.assertNull(repository.findBySecretCodeAndProcessGuidAndStatus(SECRET_CODE, smsVerificationCreated.getProcessGuid(), STATUS_CREATED)
                .orElse(null));
    }

    @Test
    public void updateStatusByProcessGuid(){

        SmsVerification smsVerification =
                SmsVerification.builder()
                        .processGuid(UUID.randomUUID().toString())
                        .phoneNumber(PHONE_NUMBER)
                        .secretCode(SECRET_CODE)
                        .status(STATUS_WAITING)
                        .build();

        SmsVerification smsVerificationCreated = repository.save(smsVerification);

        repository.updateStatusByProcessGuid(STATUS_OK, smsVerificationCreated.getProcessGuid());

        Assert.assertEquals(
                repository.findById(smsVerificationCreated.getVerificationId()).get().getStatus(),
                STATUS_OK
        );
    }
}