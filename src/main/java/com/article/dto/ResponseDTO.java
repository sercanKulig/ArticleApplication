package com.article.dto;

import com.article.enumerations.ResponseMessageStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.stereotype.Component;

@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class ResponseDTO {
    private Boolean status;
    private String message;
    private ResponseMessageStatus responseMessageStatus;
    private String relation;

    public ResponseDTO(Boolean status, String message, ResponseMessageStatus responseMessageStatus) {
        this.status = status;
        this.message = message;
        this.responseMessageStatus = responseMessageStatus;
    }

    public ResponseDTO(Boolean status, String message, ResponseMessageStatus responseMessageStatus, String relation) {
        this.status = status;
        this.message = message;
        this.responseMessageStatus = responseMessageStatus;
        this.relation = relation;
    }

    public ResponseDTO() {
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResponseMessageStatus getResponseMessageStatus() {
        return responseMessageStatus;
    }

    public void setResponseMessageStatus(ResponseMessageStatus responseMessageStatus) {
        this.responseMessageStatus = responseMessageStatus;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }
}
