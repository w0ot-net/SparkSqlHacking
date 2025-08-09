package org.joda.time.field;

import org.joda.time.DurationField;
import org.joda.time.DurationFieldType;

public class DecoratedDurationField extends BaseDurationField {
   private static final long serialVersionUID = 8019982251647420015L;
   private final DurationField iField;

   public DecoratedDurationField(DurationField var1, DurationFieldType var2) {
      super(var2);
      if (var1 == null) {
         throw new IllegalArgumentException("The field must not be null");
      } else if (!var1.isSupported()) {
         throw new IllegalArgumentException("The field must be supported");
      } else {
         this.iField = var1;
      }
   }

   public final DurationField getWrappedField() {
      return this.iField;
   }

   public boolean isPrecise() {
      return this.iField.isPrecise();
   }

   public long getValueAsLong(long var1, long var3) {
      return this.iField.getValueAsLong(var1, var3);
   }

   public long getMillis(int var1, long var2) {
      return this.iField.getMillis(var1, var2);
   }

   public long getMillis(long var1, long var3) {
      return this.iField.getMillis(var1, var3);
   }

   public long add(long var1, int var3) {
      return this.iField.add(var1, var3);
   }

   public long add(long var1, long var3) {
      return this.iField.add(var1, var3);
   }

   public long getDifferenceAsLong(long var1, long var3) {
      return this.iField.getDifferenceAsLong(var1, var3);
   }

   public long getUnitMillis() {
      return this.iField.getUnitMillis();
   }
}
