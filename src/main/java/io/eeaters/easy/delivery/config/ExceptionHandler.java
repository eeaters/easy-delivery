package io.eeaters.easy.delivery.config;

import io.eeaters.easy.delivery.entity.view.BaseResponse;
import io.eeaters.easy.delivery.exception.ResultCodeEnum;
import io.netty.util.internal.ThrowableUtil;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ExceptionHandler implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception exception) {
        System.out.println("exception = " + ThrowableUtil.stackTraceToString(exception));

        return Response.ok()
                .entity(BaseResponse.failure(ResultCodeEnum.SYSTEM_ERROR.getCode(), ResultCodeEnum.SYSTEM_ERROR.getMessage()))
                .build();
    }

}
