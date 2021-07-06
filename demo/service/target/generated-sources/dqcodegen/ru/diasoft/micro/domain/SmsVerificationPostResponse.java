/*
 * Created by DQCodegen
 */
package ru.diasoft.micro.domain;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "SmsVerificationPostResponse", description = "")
@JsonFilter("DynamicExclude")
@lombok.Generated
public class SmsVerificationPostResponse implements Serializable {

    @ApiModelProperty(name = "ProcessGUID", dataType = "java.lang.String", value = "GUID процесса", required = false, position = 1)
    @JsonProperty("ProcessGUID")
    private java.lang.String processGUID;

}
