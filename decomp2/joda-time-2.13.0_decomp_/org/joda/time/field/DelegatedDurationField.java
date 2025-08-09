package org.joda.time.field;

import java.io.Serializable;
import org.joda.time.DurationField;
import org.joda.time.DurationFieldType;

public class DelegatedDurationField extends DurationField implements Serializable {
   private static final long serialVersionUID = -5576443481242007829L;
   private final DurationField iField;
   private final DurationFieldType iType;

   protected DelegatedDurationField(DurationField var1) {
      this(var1, (DurationFieldType)null);
   }

   protected DelegatedDurationField(DurationField var1, DurationFieldType var2) {
      if (var1 == null) {
         throw new IllegalArgumentException("The field must not be null");
      } else {
         this.iField = var1;
         this.iType = var2 == null ? var1.getType() : var2;
      }
   }

   public final DurationField getWrappedField() {
      return this.iField;
   }

   public DurationFieldType getType() {
      return this.iType;
   }

   public String getName() {
      return this.iType.getName();
   }

   public boolean isSupported() {
      return this.iField.isSupported();
   }

   public boolean isPrecise() {
      return this.iField.isPrecise();
   }

   public int getValue(long var1) {
      return this.iField.getValue(var1);
   }

   public long getValueAsLong(long var1) {
      return this.iField.getValueAsLong(var1);
   }

   public int getValue(long var1, long var3) {
      return this.iField.getValue(var1, var3);
   }

   public long getValueAsLong(long var1, long var3) {
      return this.iField.getValueAsLong(var1, var3);
   }

   public long getMillis(int var1) {
      return this.iField.getMillis(var1);
   }

   public long getMillis(long var1) {
      return this.iField.getMillis(var1);
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

   public int getDifference(long var1, long var3) {
      return this.iField.getDifference(var1, var3);
   }

   public long getDifferenceAsLong(long var1, long var3) {
      return this.iField.getDifferenceAsLong(var1, var3);
   }

   public long getUnitMillis() {
      return this.iField.getUnitMillis();
   }

   public int compareTo(DurationField var1) {
      return this.iField.compareTo(var1);
   }

   public boolean equals(Object var1) {
      return var1 instanceof DelegatedDurationField ? this.iField.equals(((DelegatedDurationField)var1).iField) : false;
   }

   public int hashCode() {
      return this.iField.hashCode() ^ this.iType.hashCode();
   }

   public String toString() {
      return this.iType == null ? this.iField.toString() : "DurationField[" + this.iType + ']';
   }
}
