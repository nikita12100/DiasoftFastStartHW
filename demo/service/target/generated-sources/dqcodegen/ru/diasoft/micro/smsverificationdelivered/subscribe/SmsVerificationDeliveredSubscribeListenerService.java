/*
 * Created by DQCodegen
 */
package ru.diasoft.micro.smsverificationdelivered.subscribe;

import ru.diasoft.micro.model.SmsDeliveredMessage;

@lombok.Generated
public interface SmsVerificationDeliveredSubscribeListenerService {

    void smsVerificationDelivered(SmsDeliveredMessage msg);
    
}