package com.ibm.icu.impl.number;

import com.ibm.icu.number.IntegerWidth;
import com.ibm.icu.number.NumberFormatter;
import com.ibm.icu.number.Precision;
import com.ibm.icu.text.DecimalFormatSymbols;
import com.ibm.icu.util.MeasureUnit;
import java.util.List;

public class MicroProps implements Cloneable, MicroPropsGenerator {
   public NumberFormatter.SignDisplay sign;
   public DecimalFormatSymbols symbols;
   public String nsName;
   public Padder padding;
   public NumberFormatter.DecimalSeparatorDisplay decimal;
   public IntegerWidth integerWidth;
   public Modifier modOuter;
   public Modifier modMiddle;
   public Modifier modInner;
   public Precision rounder;
   public Grouper grouping;
   public boolean useCurrency;
   public String gender;
   public String currencyAsDecimal;
   private final boolean immutable;
   public MeasureUnit outputUnit;
   public List mixedMeasures;
   public int indexOfQuantity = -1;
   private volatile boolean exhausted;

   public MicroProps(boolean immutable) {
      this.immutable = immutable;
   }

   public MicroProps processQuantity(DecimalQuantity quantity) {
      if (this.immutable) {
         return (MicroProps)this.clone();
      } else if (this.exhausted) {
         throw new AssertionError("Cannot re-use a mutable MicroProps in the quantity chain");
      } else {
         this.exhausted = true;
         return this;
      }
   }

   public Object clone() {
      try {
         return super.clone();
      } catch (CloneNotSupportedException e) {
         throw new AssertionError(e);
      }
   }
}
