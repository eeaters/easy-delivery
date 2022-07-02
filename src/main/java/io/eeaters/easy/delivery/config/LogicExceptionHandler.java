package io.eeaters.easy.delivery.config;

import io.eeaters.easy.delivery.entity.view.BaseResponse;
import io.eeaters.easy.delivery.exception.LogicException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class LogicExceptionHandler implements ExceptionMapper<LogicException> {

    @Override
    public Response toResponse(LogicException exception) {
        return Response.ok()
                .entity(BaseResponse.failure(exception.getCode(), exception.getMessage()))
                .build();
    }

}
