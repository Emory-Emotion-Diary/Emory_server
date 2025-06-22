package com.example.emory_server.infrastructure.feign;

import com.example.emory_server.infrastructure.feign.exception.FeignBadRequestException;
import com.example.emory_server.infrastructure.feign.exception.FeignForbiddenException;
import com.example.emory_server.infrastructure.feign.exception.FeignUnauthorizedException;
import feign.Response;
import feign.RetryableException;
import feign.codec.ErrorDecoder;


public class CustomErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder errorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response){
        var exception = errorDecoder.decode(methodKey, response);

        if(response != null && response.status() >= 400) {
            switch (response.status()) {
                case 400:
                    return FeignBadRequestException.EXCEPTION;
                case 401:
                    return FeignUnauthorizedException.EXCEPTION;
                case 403:
                    return FeignForbiddenException.EXCEPTION;
                default:
                    if (response.status() >= 500) {
                        return new RetryableException(
                                response.status(),
                                exception.getMessage(),
                                response.request().httpMethod(),
                                exception,
                                (Long) null,
                                response.request()
                        );
                    }
                    break;
            }

        }
        return exception;
    }
}
