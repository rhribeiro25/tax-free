package com.example.taxfree.application.usecases;

public interface UseCaseTemplate<TRequest, TResponse> {

    abstract void validate(TRequest request);

    abstract TResponse execute(TRequest request);

}
