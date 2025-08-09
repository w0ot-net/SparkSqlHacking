package com.ibm.icu.impl.number.parse;

import com.ibm.icu.impl.StringSegment;
import com.ibm.icu.impl.number.DecimalQuantity_DualStorageBCD;
import java.util.Comparator;

public class ParsedNumber {
   public DecimalQuantity_DualStorageBCD quantity;
   public int charEnd;
   public int flags;
   public String prefix;
   public String suffix;
   public String currencyCode;
   public static final int FLAG_NEGATIVE = 1;
   public static final int FLAG_PERCENT = 2;
   public static final int FLAG_PERMILLE = 4;
   public static final int FLAG_HAS_EXPONENT = 8;
   public static final int FLAG_HAS_DECIMAL_SEPARATOR = 32;
   public static final int FLAG_NAN = 64;
   public static final int FLAG_INFINITY = 128;
   public static final int FLAG_FAIL = 256;
   public static final Comparator COMPARATOR = new Comparator() {
      public int compare(ParsedNumber o1, ParsedNumber o2) {
         return o1.charEnd - o2.charEnd;
      }
   };

   public ParsedNumber() {
      this.clear();
   }

   public void clear() {
      this.quantity = null;
      this.charEnd = 0;
      this.flags = 0;
      this.prefix = null;
      this.suffix = null;
      this.currencyCode = null;
   }

   public void copyFrom(ParsedNumber other) {
      this.quantity = other.quantity == null ? null : (DecimalQuantity_DualStorageBCD)other.quantity.createCopy();
      this.charEnd = other.charEnd;
      this.flags = other.flags;
      this.prefix = other.prefix;
      this.suffix = other.suffix;
      this.currencyCode = other.currencyCode;
   }

   public void setCharsConsumed(StringSegment segment) {
      this.charEnd = segment.getOffset();
   }

   public void postProcess() {
      if (this.quantity != null && 0 != (this.flags & 1)) {
         this.quantity.negate();
      }

   }

   public boolean success() {
      return this.charEnd > 0 && 0 == (this.flags & 256);
   }

   public boolean seenNumber() {
      return this.quantity != null || 0 != (this.flags & 64) || 0 != (this.flags & 128);
   }

   public Number getNumber() {
      return this.getNumber(0);
   }

   public Number getNumber(int parseFlags) {
      boolean sawNaN = 0 != (this.flags & 64);
      boolean sawInfinity = 0 != (this.flags & 128);
      boolean forceBigDecimal = 0 != (parseFlags & 4096);
      boolean integerOnly = 0 != (parseFlags & 16);
      if (sawNaN) {
         return Double.NaN;
      } else if (sawInfinity) {
         return 0 != (this.flags & 1) ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY;
      } else {
         assert this.quantity != null;

         if (this.quantity.isZeroish() && this.quantity.isNegative() && !integerOnly) {
            return (double)-0.0F;
         } else {
            return (Number)(this.quantity.fitsInLong() && !forceBigDecimal ? this.quantity.toLong(false) : this.quantity.toBigDecimal());
         }
      }
   }

   boolean isBetterThan(ParsedNumber other) {
      return COMPARATOR.compare(this, other) > 0;
   }
}
