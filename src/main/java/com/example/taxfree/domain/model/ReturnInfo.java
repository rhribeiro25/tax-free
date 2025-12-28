package com.example.taxfree.domain.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReturnInfo {
    private final String code;
    private final String message;
}
