package com.quane.irish_railroad_network_api.exception;

public class RailroadNetworkException extends RuntimeException {

    public RailroadNetworkException(String exMessage, Exception exception) {
        super(exMessage, exception);
    }

    public RailroadNetworkException(String exMessage) {
        super(exMessage);
    }

}
