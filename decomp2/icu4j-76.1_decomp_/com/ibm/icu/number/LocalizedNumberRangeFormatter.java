package com.ibm.icu.number;

import com.ibm.icu.impl.number.DecimalQuantity;
import com.ibm.icu.impl.number.DecimalQuantity_DualStorageBCD;

public class LocalizedNumberRangeFormatter extends NumberRangeFormatterSettings {
   private volatile NumberRangeFormatterImpl fImpl;

   LocalizedNumberRangeFormatter(NumberRangeFormatterSettings parent, int key, Object value) {
      super(parent, key, value);
   }

   public FormattedNumberRange formatRange(int first, int second) {
      DecimalQuantity dq1 = new DecimalQuantity_DualStorageBCD(first);
      DecimalQuantity dq2 = new DecimalQuantity_DualStorageBCD(second);
      return this.formatImpl(dq1, dq2, first == second);
   }

   public FormattedNumberRange formatRange(double first, double second) {
      DecimalQuantity dq1 = new DecimalQuantity_DualStorageBCD(first);
      DecimalQuantity dq2 = new DecimalQuantity_DualStorageBCD(second);
      return this.formatImpl(dq1, dq2, first == second);
   }

   public FormattedNumberRange formatRange(Number first, Number second) {
      if (first != null && second != null) {
         DecimalQuantity dq1 = new DecimalQuantity_DualStorageBCD(first);
         DecimalQuantity dq2 = new DecimalQuantity_DualStorageBCD(second);
         return this.formatImpl(dq1, dq2, first.equals(second));
      } else {
         throw new IllegalArgumentException("Cannot format null values in range");
      }
   }

   public UnlocalizedNumberRangeFormatter withoutLocale() {
      return new UnlocalizedNumberRangeFormatter(this, 1, (Object)null);
   }

   FormattedNumberRange formatImpl(DecimalQuantity first, DecimalQuantity second, boolean equalBeforeRounding) {
      if (this.fImpl == null) {
         this.fImpl = new NumberRangeFormatterImpl(this.resolve());
      }

      return this.fImpl.format(first, second, equalBeforeRounding);
   }

   LocalizedNumberRangeFormatter create(int key, Object value) {
      return new LocalizedNumberRangeFormatter(this, key, value);
   }
}
