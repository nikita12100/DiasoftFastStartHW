/*
 * Created by DQCodegen
 */
package ru.diasoft.micro.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@lombok.Generated
public class SmsVerificationMessage {

    @JsonProperty("guid")
    protected String guid;

    @JsonProperty("phoneNumber")
    protected String phoneNumber;

    @JsonProperty("code")
    protected String code;

}
