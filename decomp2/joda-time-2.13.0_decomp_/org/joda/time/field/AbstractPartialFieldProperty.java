package org.joda.time.field;

import java.util.Locale;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DurationField;
import org.joda.time.ReadableInstant;
import org.joda.time.ReadablePartial;

public abstract class AbstractPartialFieldProperty {
   protected AbstractPartialFieldProperty() {
   }

   public abstract DateTimeField getField();

   public DateTimeFieldType getFieldType() {
      return this.getField().getType();
   }

   public String getName() {
      return this.getField().getName();
   }

   protected abstract ReadablePartial getReadablePartial();

   public abstract int get();

   public String getAsString() {
      return Integer.toString(this.get());
   }

   public String getAsText() {
      return this.getAsText((Locale)null);
   }

   public String getAsText(Locale var1) {
      return this.getField().getAsText(this.getReadablePartial(), this.get(), var1);
   }

   public String getAsShortText() {
      return this.getAsShortText((Locale)null);
   }

   public String getAsShortText(Locale var1) {
      return this.getField().getAsShortText(this.getReadablePartial(), this.get(), var1);
   }

   public DurationField getDurationField() {
      return this.getField().getDurationField();
   }

   public DurationField getRangeDurationField() {
      return this.getField().getRangeDurationField();
   }

   public int getMinimumValueOverall() {
      return this.getField().getMinimumValue();
   }

   public int getMinimumValue() {
      return this.getField().getMinimumValue(this.getReadablePartial());
   }

   public int getMaximumValueOverall() {
      return this.getField().getMaximumValue();
   }

   public int getMaximumValue() {
      return this.getField().getMaximumValue(this.getReadablePartial());
   }

   public int getMaximumTextLength(Locale var1) {
      return this.getField().getMaximumTextLength(var1);
   }

   public int getMaximumShortTextLength(Locale var1) {
      return this.getField().getMaximumShortTextLength(var1);
   }

   public int compareTo(ReadableInstant var1) {
      if (var1 == null) {
         throw new IllegalArgumentException("The instant must not be null");
      } else {
         int var2 = this.get();
         int var3 = var1.get(this.getFieldType());
         if (var2 < var3) {
            return -1;
         } else {
            return var2 > var3 ? 1 : 0;
         }
      }
   }

   public int compareTo(ReadablePartial var1) {
      if (var1 == null) {
         throw new IllegalArgumentException("The instant must not be null");
      } else {
         int var2 = this.get();
         int var3 = var1.get(this.getFieldType());
         if (var2 < var3) {
            return -1;
         } else {
            return var2 > var3 ? 1 : 0;
         }
      }
   }

   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof AbstractPartialFieldProperty)) {
         return false;
      } else {
         AbstractPartialFieldProperty var2 = (AbstractPartialFieldProperty)var1;
         return this.get() == var2.get() && this.getFieldType() == var2.getFieldType() && FieldUtils.equals(this.getReadablePartial().getChronology(), var2.getReadablePartial().getChronology());
      }
   }

   public int hashCode() {
      int var1 = 19;
      var1 = 13 * var1 + this.get();
      var1 = 13 * var1 + this.getFieldType().hashCode();
      var1 = 13 * var1 + this.getReadablePartial().getChronology().hashCode();
      return var1;
   }

   public String toString() {
      return "Property[" + this.getName() + "]";
   }
}
