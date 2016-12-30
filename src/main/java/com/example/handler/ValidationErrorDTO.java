package com.example.handler;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by alexc_000 on 2016-12-30.
 */
class ValidationErrorDTO {

    private List<MessageDTO> fieldErrors = new LinkedList<>();

    ValidationErrorDTO() {

    }

    void addErrorMessage(MessageDTO messageDTO) {
        fieldErrors.add(messageDTO);
    }

    public List<MessageDTO> getFieldErrors() {
        return fieldErrors;
    }
}
