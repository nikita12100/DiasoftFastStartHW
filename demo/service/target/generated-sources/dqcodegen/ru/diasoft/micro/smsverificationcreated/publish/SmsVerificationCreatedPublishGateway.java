/*
 * Created by DQCodegen
 */
package ru.diasoft.micro.smsverificationcreated.publish;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ru.diasoft.micro.model.SmsVerificationMessage;

@MessagingGateway
@lombok.Generated
public interface SmsVerificationCreatedPublishGateway {

    @Gateway(requestChannel = SmsVerificationCreatedPublishChannelConstants.SMS_VERIFICATION_CREATED)
    void smsVerificationCreated(SmsVerificationMessage msg);

}

