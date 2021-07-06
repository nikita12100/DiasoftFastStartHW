/*
 * Created by DQCodegen
 */
package ru.diasoft.micro.smsverificationcreated.publish;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Configuration;

/*
spring:
  cloud:
    stream:
      bindings:
        smsVerificationCreatedPublish:
          destination: sms-verification-created
*/
@Configuration
@EnableBinding(SmsVerificationCreatedPublishChannel.class)
@lombok.Generated
public class SmsVerificationCreatedPublishChannelBinding {
}