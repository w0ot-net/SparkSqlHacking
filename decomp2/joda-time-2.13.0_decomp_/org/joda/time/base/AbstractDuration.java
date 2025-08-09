package org.joda.time.base;

import org.joda.convert.ToString;
import org.joda.time.Duration;
import org.joda.time.Period;
import org.joda.time.ReadableDuration;
import org.joda.time.format.FormatUtils;

public abstract class AbstractDuration implements ReadableDuration {
   protected AbstractDuration() {
   }

   public Duration toDuration() {
      return new Duration(this.getMillis());
   }

   public Period toPeriod() {
      return new Period(this.getMillis());
   }

   public int compareTo(ReadableDuration var1) {
      long var2 = this.getMillis();
      long var4 = var1.getMillis();
      if (var2 < var4) {
         return -1;
      } else {
         return var2 > var4 ? 1 : 0;
      }
   }

   public boolean isEqual(ReadableDuration var1) {
      if (var1 == null) {
         var1 = Duration.ZERO;
      }

      return this.compareTo((ReadableDuration)var1) == 0;
   }

   public boolean isLongerThan(ReadableDuration var1) {
      if (var1 == null) {
         var1 = Duration.ZERO;
      }

      return this.compareTo((ReadableDuration)var1) > 0;
   }

   public boolean isShorterThan(ReadableDuration var1) {
      if (var1 == null) {
         var1 = Duration.ZERO;
      }

      return this.compareTo((ReadableDuration)var1) < 0;
   }

   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof ReadableDuration)) {
         return false;
      } else {
         ReadableDuration var2 = (ReadableDuration)var1;
         return this.getMillis() == var2.getMillis();
      }
   }

   public int hashCode() {
      long var1 = this.getMillis();
      return (int)(var1 ^ var1 >>> 32);
   }

   @ToString
   public String toString() {
      long var1 = this.getMillis();
      StringBuffer var3 = new StringBuffer();
      var3.append("PT");
      boolean var4 = var1 < 0L;
      FormatUtils.appendUnpaddedInteger(var3, var1);

      while(var3.length() < (var4 ? 7 : 6)) {
         var3.insert(var4 ? 3 : 2, "0");
      }

      if (var1 / 1000L * 1000L == var1) {
         var3.setLength(var3.length() - 3);
      } else {
         var3.insert(var3.length() - 3, ".");
      }

      var3.append('S');
      return var3.toString();
   }
}
