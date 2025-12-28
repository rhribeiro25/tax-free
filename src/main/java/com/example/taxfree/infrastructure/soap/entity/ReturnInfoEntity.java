package com.example.taxfree.infrastructure.soap.entity;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReturnInfoEntity {

    private String code;
    private String message;
}
