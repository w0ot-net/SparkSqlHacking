package org.joda.time.format;

import java.util.Arrays;
import java.util.Locale;
import org.joda.time.Chronology;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DateTimeUtils;
import org.joda.time.DateTimeZone;
import org.joda.time.DurationField;
import org.joda.time.DurationFieldType;
import org.joda.time.IllegalFieldValueException;
import org.joda.time.IllegalInstantException;

public class DateTimeParserBucket {
   private final Chronology iChrono;
   private final long iMillis;
   private final Locale iLocale;
   private final int iDefaultYear;
   private final DateTimeZone iDefaultZone;
   private final Integer iDefaultPivotYear;
   private DateTimeZone iZone;
   private Integer iOffset;
   private Integer iPivotYear;
   private SavedField[] iSavedFields;
   private int iSavedFieldsCount;
   private boolean iSavedFieldsShared;
   private Object iSavedState;

   /** @deprecated */
   @Deprecated
   public DateTimeParserBucket(long var1, Chronology var3, Locale var4) {
      this(var1, var3, var4, (Integer)null, 2000);
   }

   /** @deprecated */
   @Deprecated
   public DateTimeParserBucket(long var1, Chronology var3, Locale var4, Integer var5) {
      this(var1, var3, var4, var5, 2000);
   }

   public DateTimeParserBucket(long var1, Chronology var3, Locale var4, Integer var5, int var6) {
      var3 = DateTimeUtils.getChronology(var3);
      this.iMillis = var1;
      this.iDefaultZone = var3.getZone();
      this.iChrono = var3.withUTC();
      this.iLocale = var4 == null ? Locale.getDefault() : var4;
      this.iDefaultYear = var6;
      this.iDefaultPivotYear = var5;
      this.iZone = this.iDefaultZone;
      this.iPivotYear = this.iDefaultPivotYear;
      this.iSavedFields = new SavedField[8];
   }

   public void reset() {
      this.iZone = this.iDefaultZone;
      this.iOffset = null;
      this.iPivotYear = this.iDefaultPivotYear;
      this.iSavedFieldsCount = 0;
      this.iSavedFieldsShared = false;
      this.iSavedState = null;
   }

   public long parseMillis(DateTimeParser var1, CharSequence var2) {
      this.reset();
      return this.doParseMillis(DateTimeParserInternalParser.of(var1), var2);
   }

   long doParseMillis(InternalParser var1, CharSequence var2) {
      int var3 = var1.parseInto(this, var2, 0);
      if (var3 >= 0) {
         if (var3 >= var2.length()) {
            return this.computeMillis(true, var2);
         }
      } else {
         var3 = ~var3;
      }

      throw new IllegalArgumentException(FormatUtils.createErrorMessage(var2.toString(), var3));
   }

   public Chronology getChronology() {
      return this.iChrono;
   }

   public Locale getLocale() {
      return this.iLocale;
   }

   public DateTimeZone getZone() {
      return this.iZone;
   }

   public void setZone(DateTimeZone var1) {
      this.iSavedState = null;
      this.iZone = var1;
   }

   /** @deprecated */
   @Deprecated
   public int getOffset() {
      return this.iOffset != null ? this.iOffset : 0;
   }

   public Integer getOffsetInteger() {
      return this.iOffset;
   }

   /** @deprecated */
   @Deprecated
   public void setOffset(int var1) {
      this.iSavedState = null;
      this.iOffset = var1;
   }

   public void setOffset(Integer var1) {
      this.iSavedState = null;
      this.iOffset = var1;
   }

   public Integer getPivotYear() {
      return this.iPivotYear;
   }

   /** @deprecated */
   @Deprecated
   public void setPivotYear(Integer var1) {
      this.iPivotYear = var1;
   }

   public void saveField(DateTimeField var1, int var2) {
      this.obtainSaveField().init(var1, var2);
   }

   public void saveField(DateTimeFieldType var1, int var2) {
      this.obtainSaveField().init(var1.getField(this.iChrono), var2);
   }

   public void saveField(DateTimeFieldType var1, String var2, Locale var3) {
      this.obtainSaveField().init(var1.getField(this.iChrono), var2, var3);
   }

   private SavedField obtainSaveField() {
      SavedField[] var1 = this.iSavedFields;
      int var2 = this.iSavedFieldsCount;
      if (var2 == var1.length || this.iSavedFieldsShared) {
         SavedField[] var3 = new SavedField[var2 == var1.length ? var2 * 2 : var1.length];
         System.arraycopy(var1, 0, var3, 0, var2);
         var1 = var3;
         this.iSavedFields = var3;
         this.iSavedFieldsShared = false;
      }

      this.iSavedState = null;
      SavedField var4 = var1[var2];
      if (var4 == null) {
         var4 = var1[var2] = new SavedField();
      }

      this.iSavedFieldsCount = var2 + 1;
      return var4;
   }

   public Object saveState() {
      if (this.iSavedState == null) {
         this.iSavedState = new SavedState();
      }

      return this.iSavedState;
   }

   public boolean restoreState(Object var1) {
      if (var1 instanceof SavedState && ((SavedState)var1).restoreState(this)) {
         this.iSavedState = var1;
         return true;
      } else {
         return false;
      }
   }

   public long computeMillis() {
      return this.computeMillis(false, (CharSequence)null);
   }

   public long computeMillis(boolean var1) {
      return this.computeMillis(var1, (CharSequence)null);
   }

   public long computeMillis(boolean var1, String var2) {
      return this.computeMillis(var1, (CharSequence)var2);
   }

   public long computeMillis(boolean var1, CharSequence var2) {
      SavedField[] var3 = this.iSavedFields;
      int var4 = this.iSavedFieldsCount;
      if (this.iSavedFieldsShared) {
         this.iSavedFields = var3 = (SavedField[])this.iSavedFields.clone();
         this.iSavedFieldsShared = false;
      }

      sort(var3, var4);
      if (var4 > 0) {
         DurationField var5 = DurationFieldType.months().getField(this.iChrono);
         DurationField var6 = DurationFieldType.days().getField(this.iChrono);
         DurationField var7 = var3[0].iField.getDurationField();
         if (compareReverse(var7, var5) >= 0 && compareReverse(var7, var6) <= 0) {
            this.saveField(DateTimeFieldType.year(), this.iDefaultYear);
            return this.computeMillis(var1, var2);
         }
      }

      long var10 = this.iMillis;

      try {
         for(int var11 = 0; var11 < var4; ++var11) {
            var10 = var3[var11].set(var10, var1);
         }

         if (var1) {
            for(int var12 = 0; var12 < var4; ++var12) {
               if (!var3[var12].iField.isLenient()) {
                  var10 = var3[var12].set(var10, var12 == var4 - 1);
               }
            }
         }
      } catch (IllegalFieldValueException var9) {
         if (var2 != null) {
            var9.prependMessage("Cannot parse \"" + var2 + '"');
         }

         throw var9;
      }

      if (this.iOffset != null) {
         var10 -= (long)this.iOffset;
      } else if (this.iZone != null) {
         int var13 = this.iZone.getOffsetFromLocal(var10);
         var10 -= (long)var13;
         if (var13 != this.iZone.getOffset(var10)) {
            String var8 = "Illegal instant due to time zone offset transition (" + this.iZone + ')';
            if (var2 != null) {
               var8 = "Cannot parse \"" + var2 + "\": " + var8;
            }

            throw new IllegalInstantException(var8);
         }
      }

      return var10;
   }

   private static void sort(SavedField[] var0, int var1) {
      if (var1 > 10) {
         Arrays.sort(var0, 0, var1);
      } else {
         for(int var2 = 0; var2 < var1; ++var2) {
            for(int var3 = var2; var3 > 0 && var0[var3 - 1].compareTo(var0[var3]) > 0; --var3) {
               SavedField var4 = var0[var3];
               var0[var3] = var0[var3 - 1];
               var0[var3 - 1] = var4;
            }
         }
      }

   }

   static int compareReverse(DurationField var0, DurationField var1) {
      if (var0 != null && var0.isSupported()) {
         return var1 != null && var1.isSupported() ? -var0.compareTo(var1) : 1;
      } else {
         return var1 != null && var1.isSupported() ? -1 : 0;
      }
   }

   class SavedState {
      final DateTimeZone iZone;
      final Integer iOffset;
      final SavedField[] iSavedFields;
      final int iSavedFieldsCount;

      SavedState() {
         this.iZone = DateTimeParserBucket.this.iZone;
         this.iOffset = DateTimeParserBucket.this.iOffset;
         this.iSavedFields = DateTimeParserBucket.this.iSavedFields;
         this.iSavedFieldsCount = DateTimeParserBucket.this.iSavedFieldsCount;
      }

      boolean restoreState(DateTimeParserBucket var1) {
         if (var1 != DateTimeParserBucket.this) {
            return false;
         } else {
            var1.iZone = this.iZone;
            var1.iOffset = this.iOffset;
            var1.iSavedFields = this.iSavedFields;
            if (this.iSavedFieldsCount < var1.iSavedFieldsCount) {
               var1.iSavedFieldsShared = true;
            }

            var1.iSavedFieldsCount = this.iSavedFieldsCount;
            return true;
         }
      }
   }

   static class SavedField implements Comparable {
      DateTimeField iField;
      int iValue;
      String iText;
      Locale iLocale;

      void init(DateTimeField var1, int var2) {
         this.iField = var1;
         this.iValue = var2;
         this.iText = null;
         this.iLocale = null;
      }

      void init(DateTimeField var1, String var2, Locale var3) {
         this.iField = var1;
         this.iValue = 0;
         this.iText = var2;
         this.iLocale = var3;
      }

      long set(long var1, boolean var3) {
         if (this.iText == null) {
            var1 = this.iField.setExtended(var1, this.iValue);
         } else {
            var1 = this.iField.set(var1, this.iText, this.iLocale);
         }

         if (var3) {
            var1 = this.iField.roundFloor(var1);
         }

         return var1;
      }

      public int compareTo(SavedField var1) {
         DateTimeField var2 = var1.iField;
         int var3 = DateTimeParserBucket.compareReverse(this.iField.getRangeDurationField(), var2.getRangeDurationField());
         return var3 != 0 ? var3 : DateTimeParserBucket.compareReverse(this.iField.getDurationField(), var2.getDurationField());
      }
   }
}
