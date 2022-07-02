package io.eeaters.easy.delivery.config;

import io.eeaters.easy.delivery.entity.view.BaseResponse;
import io.eeaters.easy.delivery.exception.ResultCodeEnum;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.stream.Collectors;

@Provider
public class ValidExceptionHandler implements ExceptionMapper<ConstraintViolationException> {
    @Override
    public Response toResponse(ConstraintViolationException exception) {
        String message = exception.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(","));
        return Response.ok()
                .entity(BaseResponse.failure(ResultCodeEnum.LOGIC_EXCEPTION.getCode(), message))
                .build();
    }

}
