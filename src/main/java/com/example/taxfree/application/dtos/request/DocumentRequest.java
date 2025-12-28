package com.example.taxfree.application.dtos.request;

public record DocumentRequest (
    String type,
    String number,
    String country
){}