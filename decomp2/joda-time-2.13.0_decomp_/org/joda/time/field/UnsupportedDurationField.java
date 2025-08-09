package org.joda.time.field;

import java.io.Serializable;
import java.util.HashMap;
import org.joda.time.DurationField;
import org.joda.time.DurationFieldType;

public final class UnsupportedDurationField extends DurationField implements Serializable {
   private static final long serialVersionUID = -6390301302770925357L;
   private static HashMap cCache;
   private final DurationFieldType iType;

   public static synchronized UnsupportedDurationField getInstance(DurationFieldType var0) {
      UnsupportedDurationField var1;
      if (cCache == null) {
         cCache = new HashMap(7);
         var1 = null;
      } else {
         var1 = (UnsupportedDurationField)cCache.get(var0);
      }

      if (var1 == null) {
         var1 = new UnsupportedDurationField(var0);
         cCache.put(var0, var1);
      }

      return var1;
   }

   private UnsupportedDurationField(DurationFieldType var1) {
      this.iType = var1;
   }

   public final DurationFieldType getType() {
      return this.iType;
   }

   public String getName() {
      return this.iType.getName();
   }

   public boolean isSupported() {
      return false;
   }

   public boolean isPrecise() {
      return true;
   }

   public int getValue(long var1) {
      throw this.unsupported();
   }

   public long getValueAsLong(long var1) {
      throw this.unsupported();
   }

   public int getValue(long var1, long var3) {
      throw this.unsupported();
   }

   public long getValueAsLong(long var1, long var3) {
      throw this.unsupported();
   }

   public long getMillis(int var1) {
      throw this.unsupported();
   }

   public long getMillis(long var1) {
      throw this.unsupported();
   }

   public long getMillis(int var1, long var2) {
      throw this.unsupported();
   }

   public long getMillis(long var1, long var3) {
      throw this.unsupported();
   }

   public long add(long var1, int var3) {
      throw this.unsupported();
   }

   public long add(long var1, long var3) {
      throw this.unsupported();
   }

   public int getDifference(long var1, long var3) {
      throw this.unsupported();
   }

   public long getDifferenceAsLong(long var1, long var3) {
      throw this.unsupported();
   }

   public long getUnitMillis() {
      return 0L;
   }

   public int compareTo(DurationField var1) {
      return 0;
   }

   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 instanceof UnsupportedDurationField) {
         UnsupportedDurationField var2 = (UnsupportedDurationField)var1;
         if (var2.getName() == null) {
            return this.getName() == null;
         } else {
            return var2.getName().equals(this.getName());
         }
      } else {
         return false;
      }
   }

   public int hashCode() {
      return this.getName().hashCode();
   }

   public String toString() {
      return "UnsupportedDurationField[" + this.getName() + ']';
   }

   private Object readResolve() {
      return getInstance(this.iType);
   }

   private UnsupportedOperationException unsupported() {
      return new UnsupportedOperationException(this.iType + " field is unsupported");
   }
}
