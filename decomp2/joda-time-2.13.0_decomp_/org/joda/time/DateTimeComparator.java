package org.joda.time;

import java.io.Serializable;
import java.util.Comparator;
import org.joda.time.convert.ConverterManager;
import org.joda.time.convert.InstantConverter;

public class DateTimeComparator implements Comparator, Serializable {
   private static final long serialVersionUID = -6097339773320178364L;
   private static final DateTimeComparator ALL_INSTANCE = new DateTimeComparator((DateTimeFieldType)null, (DateTimeFieldType)null);
   private static final DateTimeComparator DATE_INSTANCE = new DateTimeComparator(DateTimeFieldType.dayOfYear(), (DateTimeFieldType)null);
   private static final DateTimeComparator TIME_INSTANCE = new DateTimeComparator((DateTimeFieldType)null, DateTimeFieldType.dayOfYear());
   private final DateTimeFieldType iLowerLimit;
   private final DateTimeFieldType iUpperLimit;

   public static DateTimeComparator getInstance() {
      return ALL_INSTANCE;
   }

   public static DateTimeComparator getInstance(DateTimeFieldType var0) {
      return getInstance(var0, (DateTimeFieldType)null);
   }

   public static DateTimeComparator getInstance(DateTimeFieldType var0, DateTimeFieldType var1) {
      if (var0 == null && var1 == null) {
         return ALL_INSTANCE;
      } else if (var0 == DateTimeFieldType.dayOfYear() && var1 == null) {
         return DATE_INSTANCE;
      } else {
         return var0 == null && var1 == DateTimeFieldType.dayOfYear() ? TIME_INSTANCE : new DateTimeComparator(var0, var1);
      }
   }

   public static DateTimeComparator getDateOnlyInstance() {
      return DATE_INSTANCE;
   }

   public static DateTimeComparator getTimeOnlyInstance() {
      return TIME_INSTANCE;
   }

   protected DateTimeComparator(DateTimeFieldType var1, DateTimeFieldType var2) {
      this.iLowerLimit = var1;
      this.iUpperLimit = var2;
   }

   public DateTimeFieldType getLowerLimit() {
      return this.iLowerLimit;
   }

   public DateTimeFieldType getUpperLimit() {
      return this.iUpperLimit;
   }

   public int compare(Object var1, Object var2) {
      InstantConverter var3 = ConverterManager.getInstance().getInstantConverter(var1);
      Chronology var4 = var3.getChronology(var1, (Chronology)null);
      long var5 = var3.getInstantMillis(var1, var4);
      if (var1 == var2) {
         return 0;
      } else {
         var3 = ConverterManager.getInstance().getInstantConverter(var2);
         Chronology var7 = var3.getChronology(var2, (Chronology)null);
         long var8 = var3.getInstantMillis(var2, var7);
         if (this.iLowerLimit != null) {
            var5 = this.iLowerLimit.getField(var4).roundFloor(var5);
            var8 = this.iLowerLimit.getField(var7).roundFloor(var8);
         }

         if (this.iUpperLimit != null) {
            var5 = this.iUpperLimit.getField(var4).remainder(var5);
            var8 = this.iUpperLimit.getField(var7).remainder(var8);
         }

         if (var5 < var8) {
            return -1;
         } else {
            return var5 > var8 ? 1 : 0;
         }
      }
   }

   private Object readResolve() {
      return getInstance(this.iLowerLimit, this.iUpperLimit);
   }

   public boolean equals(Object var1) {
      if (!(var1 instanceof DateTimeComparator)) {
         return false;
      } else {
         DateTimeComparator var2 = (DateTimeComparator)var1;
         return (this.iLowerLimit == var2.getLowerLimit() || this.iLowerLimit != null && this.iLowerLimit.equals(var2.getLowerLimit())) && (this.iUpperLimit == var2.getUpperLimit() || this.iUpperLimit != null && this.iUpperLimit.equals(var2.getUpperLimit()));
      }
   }

   public int hashCode() {
      return (this.iLowerLimit == null ? 0 : this.iLowerLimit.hashCode()) + 123 * (this.iUpperLimit == null ? 0 : this.iUpperLimit.hashCode());
   }

   public String toString() {
      return this.iLowerLimit == this.iUpperLimit ? "DateTimeComparator[" + (this.iLowerLimit == null ? "" : this.iLowerLimit.getName()) + "]" : "DateTimeComparator[" + (this.iLowerLimit == null ? "" : this.iLowerLimit.getName()) + "-" + (this.iUpperLimit == null ? "" : this.iUpperLimit.getName()) + "]";
   }
}
