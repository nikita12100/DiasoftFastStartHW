/*
 * Created by DQCodegen
 */
package ru.diasoft.micro.smsverificationdelivered.subscribe;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Configuration;

/*
spring:
  cloud:
    stream:
      bindings:
        smsVerificationDeliveredSubscribe:
          destination: sms-verification-delivered
*/
@Configuration
@EnableBinding(SmsVerificationDeliveredSubscribeChannel.class)
@lombok.Generated
public class SmsVerificationDeliveredSubscribeChannelBinding {
}