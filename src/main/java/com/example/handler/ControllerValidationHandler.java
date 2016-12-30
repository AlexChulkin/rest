package com.example.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

/**
 * Created by alexc_000 on 2016-12-30.
 */
@ControllerAdvice
public class ControllerValidationHandler {
    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorDTO processValidationError(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> errors = result.getFieldErrors();

        return processFieldError(errors);
    }

    private ValidationErrorDTO processFieldError(List<FieldError> errors) {
        ValidationErrorDTO validationErrorDTO = new ValidationErrorDTO();

        if (Optional.ofNullable(errors).isPresent()) {
            for (FieldError error : errors) {
                Locale currentLocale = LocaleContextHolder.getLocale();
                String msg = messageSource.getMessage(error.getDefaultMessage(), null, currentLocale);
                validationErrorDTO.addErrorMessage(new MessageDTO(MessageType.ERROR, msg));
            }
        }
        return validationErrorDTO;
    }
}
