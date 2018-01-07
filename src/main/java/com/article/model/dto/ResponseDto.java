package com.article.model.dto;

import com.article.enumerations.ResponseMessageStatus;

public class ResponseDto {
    private Boolean status;
    private String message;
    private ResponseMessageStatus responseMessageStatus;
    private String relation;

    public ResponseDto(Boolean status, String message, ResponseMessageStatus responseMessageStatus) {
        this.status = status;
        this.message = message;
        this.responseMessageStatus = responseMessageStatus;
    }

    public ResponseDto(Boolean status, String message, ResponseMessageStatus responseMessageStatus, String relation) {
        this.status = status;
        this.message = message;
        this.responseMessageStatus = responseMessageStatus;
        this.relation = relation;
    }

    public ResponseDto() {
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
