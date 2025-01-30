package com.learning.courses.model.enums;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Degree {

  A(BigDecimal.valueOf(5.0)),
  B(BigDecimal.valueOf(4.5)),
  C(BigDecimal.valueOf(4.0)),
  D(BigDecimal.valueOf(3.5)),
  E(BigDecimal.valueOf(3.0)),
  F(BigDecimal.valueOf(2.0)),
  ;

  private final BigDecimal majority;
}
