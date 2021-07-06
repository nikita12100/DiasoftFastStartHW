package ru.diasoft.micro.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.diasoft.micro.domain.*;
import ru.diasoft.micro.model.SmsVerificationMessage;
import ru.diasoft.micro.repository.SmsVerificationRepository;
import ru.diasoft.micro.smsverificationcreated.publish.SmsVerificationCreatedPublishGateway;

import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Primary
public class SmsVerificationServiceImpl implements SmsVerificationService {

    private final SmsVerificationRepository smsVerificationRepository;
    private final SmsVerificationCreatedPublishGateway gateway;

    private static final String STATUS_OK = "OK";
    private static final String STATUS_WAITING = "WAITING";

    @Override
    public SmsVerificationCheckResponse dsSmsVerificationCheck(SmsVerificationCheckRequest smsVerificationCheckRequest) {
        Optional<SmsVerification> result = smsVerificationRepository.findBySecretCodeAndProcessGuidAndStatus(smsVerificationCheckRequest.getCode(), smsVerificationCheckRequest.getProcessGUID(), STATUS_OK);

        SmsVerificationCheckResponse response = new SmsVerificationCheckResponse();
        response.setCheckResult(result.isPresent());

        return response;
    }

    @Override
    public SmsVerificationPostResponse dsSmsVerificationCreate(SmsVerificationPostRequest smsVerificationPostRequest) {

        SmsVerification smsVerification =
                SmsVerification.builder()
                        .phoneNumber(smsVerificationPostRequest.getPhoneNumber())
                        .processGuid(UUID.randomUUID().toString())
                        .secretCode(String.format("%04d", new Random().nextInt(10000)))
                        .status(STATUS_WAITING)
                        .build();

        SmsVerification createdSmsVerification = smsVerificationRepository.save(smsVerification);

        gateway.smsVerificationCreated(
                SmsVerificationMessage.builder()
                        .guid(createdSmsVerification.getProcessGuid())
                        .code(createdSmsVerification.getSecretCode())
                        .phoneNumber(smsVerificationPostRequest.getPhoneNumber())
                        .build());

        return new SmsVerificationPostResponse(createdSmsVerification.getProcessGuid());
    }
}

