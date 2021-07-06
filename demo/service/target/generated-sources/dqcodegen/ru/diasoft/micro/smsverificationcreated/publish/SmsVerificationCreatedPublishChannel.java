/*
 * Created by DQCodegen
 */
package ru.diasoft.micro.smsverificationcreated.publish;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/*
spring:
  cloud:
    stream:
      bindings:
        smsVerificationCreatedPublish:
          destination: sms-verification-created
*/
@lombok.Generated
public interface SmsVerificationCreatedPublishChannel {

    @Output(SmsVerificationCreatedPublishChannelConstants.SMS_VERIFICATION_CREATED)
    MessageChannel smsVerificationCreated();
    
}