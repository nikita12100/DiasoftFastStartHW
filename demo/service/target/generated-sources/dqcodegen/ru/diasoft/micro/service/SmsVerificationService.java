/*
 * Created by DQCodegen
 */
package ru.diasoft.micro.service;

import ru.diasoft.micro.domain.SmsVerificationCheckRequest;
import ru.diasoft.micro.domain.SmsVerificationCheckResponse;
import ru.diasoft.micro.domain.SmsVerificationPostRequest;
import ru.diasoft.micro.domain.SmsVerificationPostResponse;

@lombok.Generated
public interface SmsVerificationService {

    SmsVerificationCheckResponse dsSmsVerificationCheck(
        SmsVerificationCheckRequest smsVerificationCheckRequest);
    
    SmsVerificationPostResponse dsSmsVerificationCreate(
        SmsVerificationPostRequest smsVerificationPostRequest);
    
}
