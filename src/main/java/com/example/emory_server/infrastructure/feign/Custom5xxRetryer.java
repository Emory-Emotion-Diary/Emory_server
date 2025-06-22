package com.example.emory_server.infrastructure.feign;

import feign.RetryableException;
import feign.Retryer;

public class Custom5xxRetryer implements Retryer {

    private final int maxAttempts;
    private final long backoff;
    private int attempt;

    public Custom5xxRetryer(){
        this(2, 100L);
    }

    public Custom5xxRetryer(int maxAttempts, long backoff){
        this.maxAttempts = maxAttempts;
        this.backoff = backoff;
        this.attempt = 1;
    }

    @Override
    public void continueOrPropagate(RetryableException e){
        if(attempt >= maxAttempts){
            throw e;
        }

        try{
            Thread.sleep(backoff);
        }catch (InterruptedException interruptedException) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public Retryer clone(){
        return new Custom5xxRetryer(maxAttempts, backoff);
    }
}
