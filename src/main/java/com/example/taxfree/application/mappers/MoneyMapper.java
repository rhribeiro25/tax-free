package com.example.taxfree.application.mappers;

import com.example.taxfree.application.dtos.request.MoneyRequest;
import com.example.taxfree.domain.model.Money;

public class MoneyMapper {


    public static Money toDomain(MoneyRequest dto) {
        if (dto == null) return null;
        return Money.builder()
                .value(dto.value())
                .currency(dto.currency())
                .build();
    }

    public static MoneyRequest toDto(Money money) {
        if (money == null) return null;
        return new MoneyRequest(money.getValue(), money.getCurrency());
    }
}
