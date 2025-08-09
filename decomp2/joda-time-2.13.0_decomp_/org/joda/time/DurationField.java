package org.joda.time;

public abstract class DurationField implements Comparable {
   public abstract DurationFieldType getType();

   public abstract String getName();

   public abstract boolean isSupported();

   public abstract boolean isPrecise();

   public abstract long getUnitMillis();

   public abstract int getValue(long var1);

   public abstract long getValueAsLong(long var1);

   public abstract int getValue(long var1, long var3);

   public abstract long getValueAsLong(long var1, long var3);

   public abstract long getMillis(int var1);

   public abstract long getMillis(long var1);

   public abstract long getMillis(int var1, long var2);

   public abstract long getMillis(long var1, long var3);

   public abstract long add(long var1, int var3);

   public abstract long add(long var1, long var3);

   public long subtract(long var1, int var3) {
      return var3 == Integer.MIN_VALUE ? this.subtract(var1, (long)var3) : this.add(var1, -var3);
   }

   public long subtract(long var1, long var3) {
      if (var3 == Long.MIN_VALUE) {
         throw new ArithmeticException("Long.MIN_VALUE cannot be negated");
      } else {
         return this.add(var1, -var3);
      }
   }

   public abstract int getDifference(long var1, long var3);

   public abstract long getDifferenceAsLong(long var1, long var3);

   public abstract String toString();
}
