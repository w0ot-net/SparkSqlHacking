package org.joda.time;

import [Lorg.joda.time.DateTimeFieldType;;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import org.joda.time.base.AbstractPartial;
import org.joda.time.field.AbstractPartialFieldProperty;
import org.joda.time.field.FieldUtils;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

public final class Partial extends AbstractPartial implements ReadablePartial, Serializable {
   private static final long serialVersionUID = 12324121189002L;
   private final Chronology iChronology;
   private final DateTimeFieldType[] iTypes;
   private final int[] iValues;
   private transient DateTimeFormatter[] iFormatter;

   public Partial() {
      this((Chronology)null);
   }

   public Partial(Chronology var1) {
      this.iChronology = DateTimeUtils.getChronology(var1).withUTC();
      this.iTypes = new DateTimeFieldType[0];
      this.iValues = new int[0];
   }

   public Partial(DateTimeFieldType var1, int var2) {
      this(var1, var2, (Chronology)null);
   }

   public Partial(DateTimeFieldType var1, int var2, Chronology var3) {
      var3 = DateTimeUtils.getChronology(var3).withUTC();
      this.iChronology = var3;
      if (var1 == null) {
         throw new IllegalArgumentException("The field type must not be null");
      } else {
         this.iTypes = new DateTimeFieldType[]{var1};
         this.iValues = new int[]{var2};
         var3.validate(this, this.iValues);
      }
   }

   public Partial(DateTimeFieldType[] var1, int[] var2) {
      this((DateTimeFieldType[])var1, (int[])var2, (Chronology)null);
   }

   public Partial(DateTimeFieldType[] var1, int[] var2, Chronology var3) {
      var3 = DateTimeUtils.getChronology(var3).withUTC();
      this.iChronology = var3;
      if (var1 == null) {
         throw new IllegalArgumentException("Types array must not be null");
      } else if (var2 == null) {
         throw new IllegalArgumentException("Values array must not be null");
      } else if (var2.length != var1.length) {
         throw new IllegalArgumentException("Values array must be the same length as the types array");
      } else if (var1.length == 0) {
         this.iTypes = var1;
         this.iValues = var2;
      } else {
         for(int var4 = 0; var4 < var1.length; ++var4) {
            if (var1[var4] == null) {
               throw new IllegalArgumentException("Types array must not contain null: index " + var4);
            }
         }

         DurationField var14 = null;

         for(int var5 = 0; var5 < var1.length; ++var5) {
            DateTimeFieldType var6 = var1[var5];
            DurationField var7 = var6.getDurationType().getField(this.iChronology);
            if (var5 > 0) {
               if (!var7.isSupported()) {
                  if (var14.isSupported()) {
                     throw new IllegalArgumentException("Types array must be in order largest-smallest: " + var1[var5 - 1].getName() + " < " + var6.getName());
                  }

                  throw new IllegalArgumentException("Types array must not contain duplicate unsupported: " + var1[var5 - 1].getName() + " and " + var6.getName());
               }

               int var8 = var14.compareTo(var7);
               if (var8 < 0) {
                  throw new IllegalArgumentException("Types array must be in order largest-smallest: " + var1[var5 - 1].getName() + " < " + var6.getName());
               }

               if (var8 == 0) {
                  if (var14.equals(var7)) {
                     DurationFieldType var9 = var1[var5 - 1].getRangeDurationType();
                     DurationFieldType var10 = var6.getRangeDurationType();
                     if (var9 == null) {
                        if (var10 == null) {
                           throw new IllegalArgumentException("Types array must not contain duplicate: " + var1[var5 - 1].getName() + " and " + var6.getName());
                        }
                     } else {
                        if (var10 == null) {
                           throw new IllegalArgumentException("Types array must be in order largest-smallest: " + var1[var5 - 1].getName() + " < " + var6.getName());
                        }

                        DurationField var11 = var9.getField(this.iChronology);
                        DurationField var12 = var10.getField(this.iChronology);
                        if (var11.compareTo(var12) < 0) {
                           throw new IllegalArgumentException("Types array must be in order largest-smallest: " + var1[var5 - 1].getName() + " < " + var6.getName());
                        }

                        if (var11.compareTo(var12) == 0) {
                           throw new IllegalArgumentException("Types array must not contain duplicate: " + var1[var5 - 1].getName() + " and " + var6.getName());
                        }
                     }
                  } else if (var14.isSupported() && var14.getType() != DurationFieldType.YEARS_TYPE) {
                     throw new IllegalArgumentException("Types array must be in order largest-smallest, for year-based fields, years is defined as being largest: " + var1[var5 - 1].getName() + " < " + var6.getName());
                  }
               }
            }

            var14 = var7;
         }

         this.iTypes = (DateTimeFieldType[])((DateTimeFieldType;)var1).clone();
         var3.validate(this, var2);
         this.iValues = (int[])(([I)var2).clone();
      }
   }

   public Partial(ReadablePartial var1) {
      if (var1 == null) {
         throw new IllegalArgumentException("The partial must not be null");
      } else {
         this.iChronology = DateTimeUtils.getChronology(var1.getChronology()).withUTC();
         this.iTypes = new DateTimeFieldType[var1.size()];
         this.iValues = new int[var1.size()];

         for(int var2 = 0; var2 < var1.size(); ++var2) {
            this.iTypes[var2] = var1.getFieldType(var2);
            this.iValues[var2] = var1.getValue(var2);
         }

      }
   }

   Partial(Partial var1, int[] var2) {
      this.iChronology = var1.iChronology;
      this.iTypes = var1.iTypes;
      this.iValues = var2;
   }

   Partial(Chronology var1, DateTimeFieldType[] var2, int[] var3) {
      this.iChronology = var1;
      this.iTypes = var2;
      this.iValues = var3;
   }

   public int size() {
      return this.iTypes.length;
   }

   public Chronology getChronology() {
      return this.iChronology;
   }

   protected DateTimeField getField(int var1, Chronology var2) {
      return this.iTypes[var1].getField(var2);
   }

   public DateTimeFieldType getFieldType(int var1) {
      return this.iTypes[var1];
   }

   public DateTimeFieldType[] getFieldTypes() {
      return (DateTimeFieldType[])this.iTypes.clone();
   }

   public int getValue(int var1) {
      return this.iValues[var1];
   }

   public int[] getValues() {
      return (int[])this.iValues.clone();
   }

   public Partial withChronologyRetainFields(Chronology var1) {
      var1 = DateTimeUtils.getChronology(var1);
      var1 = var1.withUTC();
      if (var1 == this.getChronology()) {
         return this;
      } else {
         Partial var2 = new Partial(var1, this.iTypes, this.iValues);
         var1.validate(var2, this.iValues);
         return var2;
      }
   }

   public Partial with(DateTimeFieldType var1, int var2) {
      if (var1 == null) {
         throw new IllegalArgumentException("The field type must not be null");
      } else {
         int var3 = this.indexOf(var1);
         if (var3 != -1) {
            if (var2 == this.getValue(var3)) {
               return this;
            } else {
               int[] var13 = this.getValues();
               var13 = this.getField(var3).set(this, var3, var13, var2);
               return new Partial(this, var13);
            }
         } else {
            DateTimeFieldType[] var4 = new DateTimeFieldType[this.iTypes.length + 1];
            int[] var5 = new int[var4.length];
            int var6 = 0;
            DurationField var7 = var1.getDurationType().getField(this.iChronology);
            if (var7.isSupported()) {
               for(; var6 < this.iTypes.length; ++var6) {
                  DateTimeFieldType var8 = this.iTypes[var6];
                  DurationField var9 = var8.getDurationType().getField(this.iChronology);
                  if (var9.isSupported()) {
                     int var10 = var7.compareTo(var9);
                     if (var10 > 0) {
                        break;
                     }

                     if (var10 == 0) {
                        if (var1.getRangeDurationType() == null) {
                           break;
                        }

                        if (var8.getRangeDurationType() != null) {
                           DurationField var11 = var1.getRangeDurationType().getField(this.iChronology);
                           DurationField var12 = var8.getRangeDurationType().getField(this.iChronology);
                           if (var11.compareTo(var12) > 0) {
                              break;
                           }
                        }
                     }
                  }
               }
            }

            System.arraycopy(this.iTypes, 0, var4, 0, var6);
            System.arraycopy(this.iValues, 0, var5, 0, var6);
            var4[var6] = var1;
            var5[var6] = var2;
            System.arraycopy(this.iTypes, var6, var4, var6 + 1, var4.length - var6 - 1);
            System.arraycopy(this.iValues, var6, var5, var6 + 1, var5.length - var6 - 1);
            Partial var15 = new Partial(var4, var5, this.iChronology);
            this.iChronology.validate(var15, var5);
            return var15;
         }
      }
   }

   public Partial without(DateTimeFieldType var1) {
      int var2 = this.indexOf(var1);
      if (var2 != -1) {
         DateTimeFieldType[] var3 = new DateTimeFieldType[this.size() - 1];
         int[] var4 = new int[this.size() - 1];
         System.arraycopy(this.iTypes, 0, var3, 0, var2);
         System.arraycopy(this.iTypes, var2 + 1, var3, var2, var3.length - var2);
         System.arraycopy(this.iValues, 0, var4, 0, var2);
         System.arraycopy(this.iValues, var2 + 1, var4, var2, var4.length - var2);
         Partial var5 = new Partial(this.iChronology, var3, var4);
         this.iChronology.validate(var5, var4);
         return var5;
      } else {
         return this;
      }
   }

   public Partial withField(DateTimeFieldType var1, int var2) {
      int var3 = this.indexOfSupported(var1);
      if (var2 == this.getValue(var3)) {
         return this;
      } else {
         int[] var4 = this.getValues();
         var4 = this.getField(var3).set(this, var3, var4, var2);
         return new Partial(this, var4);
      }
   }

   public Partial withFieldAdded(DurationFieldType var1, int var2) {
      int var3 = this.indexOfSupported(var1);
      if (var2 == 0) {
         return this;
      } else {
         int[] var4 = this.getValues();
         var4 = this.getField(var3).add(this, var3, var4, var2);
         return new Partial(this, var4);
      }
   }

   public Partial withFieldAddWrapped(DurationFieldType var1, int var2) {
      int var3 = this.indexOfSupported(var1);
      if (var2 == 0) {
         return this;
      } else {
         int[] var4 = this.getValues();
         var4 = this.getField(var3).addWrapPartial(this, var3, var4, var2);
         return new Partial(this, var4);
      }
   }

   public Partial withPeriodAdded(ReadablePeriod var1, int var2) {
      if (var1 != null && var2 != 0) {
         int[] var3 = this.getValues();

         for(int var4 = 0; var4 < var1.size(); ++var4) {
            DurationFieldType var5 = var1.getFieldType(var4);
            int var6 = this.indexOf(var5);
            if (var6 >= 0) {
               var3 = this.getField(var6).add(this, var6, var3, FieldUtils.safeMultiply(var1.getValue(var4), var2));
            }
         }

         return new Partial(this, var3);
      } else {
         return this;
      }
   }

   public Partial plus(ReadablePeriod var1) {
      return this.withPeriodAdded(var1, 1);
   }

   public Partial minus(ReadablePeriod var1) {
      return this.withPeriodAdded(var1, -1);
   }

   public Property property(DateTimeFieldType var1) {
      return new Property(this, this.indexOfSupported(var1));
   }

   public boolean isMatch(ReadableInstant var1) {
      long var2 = DateTimeUtils.getInstantMillis(var1);
      Chronology var4 = DateTimeUtils.getInstantChronology(var1);

      for(int var5 = 0; var5 < this.iTypes.length; ++var5) {
         int var6 = this.iTypes[var5].getField(var4).get(var2);
         if (var6 != this.iValues[var5]) {
            return false;
         }
      }

      return true;
   }

   public boolean isMatch(ReadablePartial var1) {
      if (var1 == null) {
         throw new IllegalArgumentException("The partial must not be null");
      } else {
         for(int var2 = 0; var2 < this.iTypes.length; ++var2) {
            int var3 = var1.get(this.iTypes[var2]);
            if (var3 != this.iValues[var2]) {
               return false;
            }
         }

         return true;
      }
   }

   public DateTimeFormatter getFormatter() {
      DateTimeFormatter[] var1 = this.iFormatter;
      if (var1 == null) {
         if (this.size() == 0) {
            return null;
         }

         var1 = new DateTimeFormatter[2];

         try {
            ArrayList var2 = new ArrayList(Arrays.asList(this.iTypes));
            var1[0] = ISODateTimeFormat.forFields(var2, true, false);
            if (var2.size() == 0) {
               var1[1] = var1[0];
            }
         } catch (IllegalArgumentException var3) {
         }

         this.iFormatter = var1;
      }

      return var1[0];
   }

   public String toString() {
      DateTimeFormatter[] var1 = this.iFormatter;
      if (var1 == null) {
         this.getFormatter();
         var1 = this.iFormatter;
         if (var1 == null) {
            return this.toStringList();
         }
      }

      DateTimeFormatter var2 = var1[1];
      return var2 == null ? this.toStringList() : var2.print((ReadablePartial)this);
   }

   public String toStringList() {
      int var1 = this.size();
      StringBuilder var2 = new StringBuilder(20 * var1);
      var2.append('[');

      for(int var3 = 0; var3 < var1; ++var3) {
         if (var3 > 0) {
            var2.append(',').append(' ');
         }

         var2.append(this.iTypes[var3].getName());
         var2.append('=');
         var2.append(this.iValues[var3]);
      }

      var2.append(']');
      return var2.toString();
   }

   public String toString(String var1) {
      return var1 == null ? this.toString() : DateTimeFormat.forPattern(var1).print((ReadablePartial)this);
   }

   public String toString(String var1, Locale var2) {
      return var1 == null ? this.toString() : DateTimeFormat.forPattern(var1).withLocale(var2).print((ReadablePartial)this);
   }

   public static class Property extends AbstractPartialFieldProperty implements Serializable {
      private static final long serialVersionUID = 53278362873888L;
      private final Partial iPartial;
      private final int iFieldIndex;

      Property(Partial var1, int var2) {
         this.iPartial = var1;
         this.iFieldIndex = var2;
      }

      public DateTimeField getField() {
         return this.iPartial.getField(this.iFieldIndex);
      }

      protected ReadablePartial getReadablePartial() {
         return this.iPartial;
      }

      public Partial getPartial() {
         return this.iPartial;
      }

      public int get() {
         return this.iPartial.getValue(this.iFieldIndex);
      }

      public Partial addToCopy(int var1) {
         int[] var2 = this.iPartial.getValues();
         var2 = this.getField().add(this.iPartial, this.iFieldIndex, var2, var1);
         return new Partial(this.iPartial, var2);
      }

      public Partial addWrapFieldToCopy(int var1) {
         int[] var2 = this.iPartial.getValues();
         var2 = this.getField().addWrapField(this.iPartial, this.iFieldIndex, var2, var1);
         return new Partial(this.iPartial, var2);
      }

      public Partial setCopy(int var1) {
         int[] var2 = this.iPartial.getValues();
         var2 = this.getField().set(this.iPartial, this.iFieldIndex, var2, var1);
         return new Partial(this.iPartial, var2);
      }

      public Partial setCopy(String var1, Locale var2) {
         int[] var3 = this.iPartial.getValues();
         var3 = this.getField().set(this.iPartial, this.iFieldIndex, var3, var1, var2);
         return new Partial(this.iPartial, var3);
      }

      public Partial setCopy(String var1) {
         return this.setCopy(var1, (Locale)null);
      }

      public Partial withMaximumValue() {
         return this.setCopy(this.getMaximumValue());
      }

      public Partial withMinimumValue() {
         return this.setCopy(this.getMinimumValue());
      }
   }
}
