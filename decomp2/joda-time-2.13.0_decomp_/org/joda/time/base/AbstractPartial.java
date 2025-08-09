package org.joda.time.base;

import org.joda.time.Chronology;
import org.joda.time.DateTime;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DateTimeUtils;
import org.joda.time.DurationFieldType;
import org.joda.time.ReadableInstant;
import org.joda.time.ReadablePartial;
import org.joda.time.field.FieldUtils;
import org.joda.time.format.DateTimeFormatter;

public abstract class AbstractPartial implements ReadablePartial, Comparable {
   protected AbstractPartial() {
   }

   protected abstract DateTimeField getField(int var1, Chronology var2);

   public DateTimeFieldType getFieldType(int var1) {
      return this.getField(var1, this.getChronology()).getType();
   }

   public DateTimeFieldType[] getFieldTypes() {
      DateTimeFieldType[] var1 = new DateTimeFieldType[this.size()];

      for(int var2 = 0; var2 < var1.length; ++var2) {
         var1[var2] = this.getFieldType(var2);
      }

      return var1;
   }

   public DateTimeField getField(int var1) {
      return this.getField(var1, this.getChronology());
   }

   public DateTimeField[] getFields() {
      DateTimeField[] var1 = new DateTimeField[this.size()];

      for(int var2 = 0; var2 < var1.length; ++var2) {
         var1[var2] = this.getField(var2);
      }

      return var1;
   }

   public int[] getValues() {
      int[] var1 = new int[this.size()];

      for(int var2 = 0; var2 < var1.length; ++var2) {
         var1[var2] = this.getValue(var2);
      }

      return var1;
   }

   public int get(DateTimeFieldType var1) {
      return this.getValue(this.indexOfSupported(var1));
   }

   public boolean isSupported(DateTimeFieldType var1) {
      return this.indexOf(var1) != -1;
   }

   public int indexOf(DateTimeFieldType var1) {
      int var2 = 0;

      for(int var3 = this.size(); var2 < var3; ++var2) {
         if (this.getFieldType(var2) == var1) {
            return var2;
         }
      }

      return -1;
   }

   protected int indexOfSupported(DateTimeFieldType var1) {
      int var2 = this.indexOf(var1);
      if (var2 == -1) {
         throw new IllegalArgumentException("Field '" + var1 + "' is not supported");
      } else {
         return var2;
      }
   }

   protected int indexOf(DurationFieldType var1) {
      int var2 = 0;

      for(int var3 = this.size(); var2 < var3; ++var2) {
         if (this.getFieldType(var2).getDurationType() == var1) {
            return var2;
         }
      }

      return -1;
   }

   protected int indexOfSupported(DurationFieldType var1) {
      int var2 = this.indexOf(var1);
      if (var2 == -1) {
         throw new IllegalArgumentException("Field '" + var1 + "' is not supported");
      } else {
         return var2;
      }
   }

   public DateTime toDateTime(ReadableInstant var1) {
      Chronology var2 = DateTimeUtils.getInstantChronology(var1);
      long var3 = DateTimeUtils.getInstantMillis(var1);
      long var5 = var2.set(this, var3);
      return new DateTime(var5, var2);
   }

   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof ReadablePartial)) {
         return false;
      } else {
         ReadablePartial var2 = (ReadablePartial)var1;
         if (this.size() != var2.size()) {
            return false;
         } else {
            int var3 = 0;

            for(int var4 = this.size(); var3 < var4; ++var3) {
               if (this.getValue(var3) != var2.getValue(var3) || this.getFieldType(var3) != var2.getFieldType(var3)) {
                  return false;
               }
            }

            return FieldUtils.equals(this.getChronology(), var2.getChronology());
         }
      }
   }

   public int hashCode() {
      int var1 = 157;
      int var2 = 0;

      for(int var3 = this.size(); var2 < var3; ++var2) {
         var1 = 23 * var1 + this.getValue(var2);
         var1 = 23 * var1 + this.getFieldType(var2).hashCode();
      }

      var1 += this.getChronology().hashCode();
      return var1;
   }

   public int compareTo(ReadablePartial var1) {
      if (this == var1) {
         return 0;
      } else if (this.size() != var1.size()) {
         throw new ClassCastException("ReadablePartial objects must have matching field types");
      } else {
         int var2 = 0;

         for(int var3 = this.size(); var2 < var3; ++var2) {
            if (this.getFieldType(var2) != var1.getFieldType(var2)) {
               throw new ClassCastException("ReadablePartial objects must have matching field types");
            }
         }

         var2 = 0;

         for(int var5 = this.size(); var2 < var5; ++var2) {
            if (this.getValue(var2) > var1.getValue(var2)) {
               return 1;
            }

            if (this.getValue(var2) < var1.getValue(var2)) {
               return -1;
            }
         }

         return 0;
      }
   }

   public boolean isAfter(ReadablePartial var1) {
      if (var1 == null) {
         throw new IllegalArgumentException("Partial cannot be null");
      } else {
         return this.compareTo(var1) > 0;
      }
   }

   public boolean isBefore(ReadablePartial var1) {
      if (var1 == null) {
         throw new IllegalArgumentException("Partial cannot be null");
      } else {
         return this.compareTo(var1) < 0;
      }
   }

   public boolean isEqual(ReadablePartial var1) {
      if (var1 == null) {
         throw new IllegalArgumentException("Partial cannot be null");
      } else {
         return this.compareTo(var1) == 0;
      }
   }

   public String toString(DateTimeFormatter var1) {
      return var1 == null ? this.toString() : var1.print((ReadablePartial)this);
   }
}
