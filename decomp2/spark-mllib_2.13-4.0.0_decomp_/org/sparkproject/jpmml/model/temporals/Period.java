package org.sparkproject.jpmml.model.temporals;

import org.sparkproject.dmg.pmml.ComplexValue;
import org.sparkproject.dmg.pmml.DataType;

public abstract class Period extends Number implements ComplexValue, Comparable {
   Period() {
   }

   public abstract DataType getDataType();

   public Long toSimpleValue() {
      return this.longValue();
   }

   public int intValue() {
      long longValue = this.longValue();
      int intValue = (int)longValue;
      if ((long)intValue != longValue) {
         throw new ArithmeticException("integer overflow");
      } else {
         return intValue;
      }
   }

   public float floatValue() {
      return (float)this.longValue();
   }

   public double doubleValue() {
      return (double)this.longValue();
   }
}
