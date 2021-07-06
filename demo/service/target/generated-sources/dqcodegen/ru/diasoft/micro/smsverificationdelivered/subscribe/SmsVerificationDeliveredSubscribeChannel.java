/*
 * Created by DQCodegen
 */
package ru.diasoft.micro.smsverificationdelivered.subscribe;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;
/*
spring:
  cloud:
    stream:
      bindings:
        smsVerificationDeliveredSubscribe:
          destination: sms-verification-delivered
*/
@lombok.Generated
public interface SmsVerificationDeliveredSubscribeChannel {

    @Input(SmsVerificationDeliveredSubscribeChannelConstants.SMS_VERIFICATION_DELIVERED)
    SubscribableChannel smsVerificationDelivered();
    
}