package com.article.dto;

import com.article.entity.session.SessionItem;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SessionDTO extends ResponseDTO {

    @ApiModelProperty(required = true, value = "")
    private SessionItem item;

}
