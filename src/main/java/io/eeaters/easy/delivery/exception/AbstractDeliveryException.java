package io.eeaters.easy.delivery.exception;

public abstract class AbstractDeliveryException extends RuntimeException {

    public abstract String getCode();

    public abstract String getMessage();

}
