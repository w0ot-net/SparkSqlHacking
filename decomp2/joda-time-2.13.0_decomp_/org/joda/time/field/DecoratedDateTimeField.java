package org.joda.time.field;

import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DurationField;

public abstract class DecoratedDateTimeField extends BaseDateTimeField {
   private static final long serialVersionUID = 203115783733757597L;
   private final DateTimeField iField;

   protected DecoratedDateTimeField(DateTimeField var1, DateTimeFieldType var2) {
      super(var2);
      if (var1 == null) {
         throw new IllegalArgumentException("The field must not be null");
      } else if (!var1.isSupported()) {
         throw new IllegalArgumentException("The field must be supported");
      } else {
         this.iField = var1;
      }
   }

   public final DateTimeField getWrappedField() {
      return this.iField;
   }

   public boolean isLenient() {
      return this.iField.isLenient();
   }

   public int get(long var1) {
      return this.iField.get(var1);
   }

   public long set(long var1, int var3) {
      return this.iField.set(var1, var3);
   }

   public DurationField getDurationField() {
      return this.iField.getDurationField();
   }

   public DurationField getRangeDurationField() {
      return this.iField.getRangeDurationField();
   }

   public int getMinimumValue() {
      return this.iField.getMinimumValue();
   }

   public int getMaximumValue() {
      return this.iField.getMaximumValue();
   }

   public long roundFloor(long var1) {
      return this.iField.roundFloor(var1);
   }
}
