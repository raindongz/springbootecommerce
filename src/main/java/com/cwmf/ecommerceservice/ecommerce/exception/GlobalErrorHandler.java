package com.cwmf.ecommerceservice.ecommerce.exception;

import com.cwmf.ecommerceservice.ecommerce.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestControllerAdvice
public class GlobalErrorHandler {
    @ExceptionHandler(ProductException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponse handleProductException(ProductException ex){
        ErrorResponse errorResponse=new ErrorResponse();
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setTimestamp(new Date());
        return errorResponse;
    }

    @ExceptionHandler(OrderException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponse handleOrderException(OrderException ex){
        ErrorResponse errorResponse=new ErrorResponse();
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setTimestamp(new Date());
        return errorResponse;
    }
    @ExceptionHandler(UserEntityException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponse handleUserEntityException(UserEntityException ex){
        ErrorResponse errorResponse=new ErrorResponse();
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setTimestamp(new Date());
        return errorResponse;
    }
/*
    @ExceptionHandler(CustomerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponse handleCustomerException(CustomerException ex){
        ErrorResponse errorResponse=new ErrorResponse();
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setTimestamp(new Date());
        return errorResponse;
    }

    @ExceptionHandler(EmployeeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponse handleEmployeeException(EmployeeException ex){
        ErrorResponse errorResponse=new ErrorResponse();
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setTimestamp(new Date());
        return errorResponse;
    }

 */
}
