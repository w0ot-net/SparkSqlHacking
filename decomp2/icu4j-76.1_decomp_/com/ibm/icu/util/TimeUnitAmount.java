package com.ibm.icu.util;

public class TimeUnitAmount extends Measure {
   public TimeUnitAmount(Number number, TimeUnit unit) {
      super(number, unit);
   }

   public TimeUnitAmount(double number, TimeUnit unit) {
      super(number, unit);
   }

   public TimeUnit getTimeUnit() {
      return (TimeUnit)this.getUnit();
   }
}
