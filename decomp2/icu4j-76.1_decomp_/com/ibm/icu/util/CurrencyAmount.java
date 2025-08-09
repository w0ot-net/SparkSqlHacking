package com.ibm.icu.util;

public class CurrencyAmount extends Measure {
   public CurrencyAmount(Number number, Currency currency) {
      super(number, currency);
   }

   public CurrencyAmount(double number, Currency currency) {
      super(number, currency);
   }

   public CurrencyAmount(Number number, java.util.Currency currency) {
      this(number, Currency.fromJavaCurrency(currency));
   }

   public CurrencyAmount(double number, java.util.Currency currency) {
      this(number, Currency.fromJavaCurrency(currency));
   }

   public Currency getCurrency() {
      return (Currency)this.getUnit();
   }
}
