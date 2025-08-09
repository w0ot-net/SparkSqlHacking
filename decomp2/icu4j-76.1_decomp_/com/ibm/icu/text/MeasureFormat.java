package com.ibm.icu.text;

import com.ibm.icu.impl.DontCareFieldPosition;
import com.ibm.icu.impl.FormattedStringBuilder;
import com.ibm.icu.impl.FormattedValueStringBuilderImpl;
import com.ibm.icu.impl.ICUResourceBundle;
import com.ibm.icu.impl.SimpleCache;
import com.ibm.icu.impl.SimpleFormatterImpl;
import com.ibm.icu.impl.Utility;
import com.ibm.icu.impl.number.DecimalQuantity;
import com.ibm.icu.impl.number.DecimalQuantity_DualStorageBCD;
import com.ibm.icu.impl.number.LongNameHandler;
import com.ibm.icu.impl.number.RoundingUtils;
import com.ibm.icu.number.IntegerWidth;
import com.ibm.icu.number.LocalizedNumberFormatter;
import com.ibm.icu.number.NumberFormatter;
import com.ibm.icu.number.Precision;
import com.ibm.icu.util.Currency;
import com.ibm.icu.util.ICUUncheckedIOException;
import com.ibm.icu.util.Measure;
import com.ibm.icu.util.MeasureUnit;
import com.ibm.icu.util.ULocale;
import com.ibm.icu.util.UResourceBundle;
import java.io.Externalizable;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;
import java.math.RoundingMode;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.concurrent.ConcurrentHashMap;

public class MeasureFormat extends UFormat {
   static final long serialVersionUID = -7182021401701778240L;
   private final transient FormatWidth formatWidth;
   private final transient PluralRules rules;
   private final transient NumericFormatters numericFormatters;
   private final transient NumberFormat numberFormat;
   private final transient LocalizedNumberFormatter numberFormatter;
   private static final SimpleCache localeToNumericDurationFormatters = new SimpleCache();
   private static final Map hmsTo012 = new HashMap();
   private static final int MEASURE_FORMAT = 0;
   private static final int TIME_UNIT_FORMAT = 1;
   private static final int CURRENCY_FORMAT = 2;
   static final int NUMBER_FORMATTER_STANDARD = 1;
   static final int NUMBER_FORMATTER_CURRENCY = 2;
   static final int NUMBER_FORMATTER_INTEGER = 3;
   private transient NumberFormatterCacheEntry formatter1;
   private transient NumberFormatterCacheEntry formatter2;
   private transient NumberFormatterCacheEntry formatter3;
   private static final Map localeIdToRangeFormat;

   public static MeasureFormat getInstance(ULocale locale, FormatWidth formatWidth) {
      return getInstance(locale, formatWidth, NumberFormat.getInstance(locale));
   }

   public static MeasureFormat getInstance(Locale locale, FormatWidth formatWidth) {
      return getInstance(ULocale.forLocale(locale), formatWidth);
   }

   public static MeasureFormat getInstance(ULocale locale, FormatWidth formatWidth, NumberFormat format) {
      return new MeasureFormat(locale, formatWidth, format, (PluralRules)null, (NumericFormatters)null);
   }

   public static MeasureFormat getInstance(Locale locale, FormatWidth formatWidth, NumberFormat format) {
      return getInstance(ULocale.forLocale(locale), formatWidth, format);
   }

   public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition fpos) {
      int prevLength = toAppendTo.length();
      fpos.setBeginIndex(0);
      fpos.setEndIndex(0);
      if (obj instanceof Collection) {
         Collection<?> coll = (Collection)obj;
         Measure[] measures = new Measure[coll.size()];
         int idx = 0;

         for(Object o : coll) {
            if (!(o instanceof Measure)) {
               throw new IllegalArgumentException(obj.toString());
            }

            measures[idx++] = (Measure)o;
         }

         this.formatMeasuresInternal(toAppendTo, fpos, measures);
      } else if (obj instanceof Measure[]) {
         this.formatMeasuresInternal(toAppendTo, fpos, (Measure[])obj);
      } else {
         if (!(obj instanceof Measure)) {
            throw new IllegalArgumentException(obj.toString());
         }

         FormattedStringBuilder result = this.formatMeasure((Measure)obj);
         FormattedValueStringBuilderImpl.nextFieldPosition(result, fpos);
         Utility.appendTo(result, toAppendTo);
      }

      if (prevLength > 0 && fpos.getEndIndex() != 0) {
         fpos.setBeginIndex(fpos.getBeginIndex() + prevLength);
         fpos.setEndIndex(fpos.getEndIndex() + prevLength);
      }

      return toAppendTo;
   }

   public Measure parseObject(String source, ParsePosition pos) {
      throw new UnsupportedOperationException();
   }

   public final String formatMeasures(Measure... measures) {
      return this.formatMeasures(new StringBuilder(), DontCareFieldPosition.INSTANCE, measures).toString();
   }

   public StringBuilder formatMeasurePerUnit(Measure measure, MeasureUnit perUnit, StringBuilder appendTo, FieldPosition pos) {
      DecimalQuantity dq = new DecimalQuantity_DualStorageBCD(measure.getNumber());
      FormattedStringBuilder string = new FormattedStringBuilder();
      this.getUnitFormatterFromCache(1, measure.getUnit(), perUnit).formatImpl(dq, string);
      DecimalFormat.fieldPositionHelper(dq, string, pos, appendTo.length());
      Utility.appendTo(string, appendTo);
      return appendTo;
   }

   public StringBuilder formatMeasures(StringBuilder appendTo, FieldPosition fpos, Measure... measures) {
      int prevLength = appendTo.length();
      this.formatMeasuresInternal(appendTo, fpos, measures);
      if (prevLength > 0 && fpos.getEndIndex() > 0) {
         fpos.setBeginIndex(fpos.getBeginIndex() + prevLength);
         fpos.setEndIndex(fpos.getEndIndex() + prevLength);
      }

      return appendTo;
   }

   private void formatMeasuresInternal(Appendable appendTo, FieldPosition fieldPosition, Measure... measures) {
      if (measures.length != 0) {
         if (measures.length == 1) {
            FormattedStringBuilder result = this.formatMeasure(measures[0]);
            FormattedValueStringBuilderImpl.nextFieldPosition(result, fieldPosition);
            Utility.appendTo(result, appendTo);
         } else {
            if (this.formatWidth == MeasureFormat.FormatWidth.NUMERIC) {
               Number[] hms = toHMS(measures);
               if (hms != null) {
                  this.formatNumeric(hms, appendTo);
                  return;
               }
            }

            ListFormatter listFormatter = ListFormatter.getInstance(this.getLocale(), ListFormatter.Type.UNITS, this.formatWidth.listWidth);
            if (fieldPosition != DontCareFieldPosition.INSTANCE) {
               this.formatMeasuresSlowTrack(listFormatter, appendTo, fieldPosition, measures);
            } else {
               String[] results = new String[measures.length];

               for(int i = 0; i < measures.length; ++i) {
                  if (i == measures.length - 1) {
                     results[i] = this.formatMeasure(measures[i]).toString();
                  } else {
                     results[i] = this.formatMeasureInteger(measures[i]).toString();
                  }
               }

               ListFormatter.FormattedListBuilder builder = listFormatter.formatImpl(Arrays.asList(results), false);
               builder.appendTo(appendTo);
            }
         }
      }
   }

   public String getUnitDisplayName(MeasureUnit unit) {
      return LongNameHandler.getUnitDisplayName(this.getLocale(), unit, this.formatWidth.unitWidth);
   }

   public final boolean equals(Object other) {
      if (this == other) {
         return true;
      } else if (!(other instanceof MeasureFormat)) {
         return false;
      } else {
         MeasureFormat rhs = (MeasureFormat)other;
         return this.getWidth() == rhs.getWidth() && this.getLocale().equals(rhs.getLocale()) && this.getNumberFormatInternal().equals(rhs.getNumberFormatInternal());
      }
   }

   public final int hashCode() {
      return (this.getLocale().hashCode() * 31 + this.getNumberFormatInternal().hashCode()) * 31 + this.getWidth().hashCode();
   }

   public FormatWidth getWidth() {
      return this.formatWidth == MeasureFormat.FormatWidth.DEFAULT_CURRENCY ? MeasureFormat.FormatWidth.WIDE : this.formatWidth;
   }

   public final ULocale getLocale() {
      return this.getLocale(ULocale.VALID_LOCALE);
   }

   public NumberFormat getNumberFormat() {
      return (NumberFormat)this.numberFormat.clone();
   }

   NumberFormat getNumberFormatInternal() {
      return this.numberFormat;
   }

   public static MeasureFormat getCurrencyFormat(ULocale locale) {
      return new CurrencyFormat(locale);
   }

   public static MeasureFormat getCurrencyFormat(Locale locale) {
      return getCurrencyFormat(ULocale.forLocale(locale));
   }

   public static MeasureFormat getCurrencyFormat() {
      return getCurrencyFormat(ULocale.getDefault(ULocale.Category.FORMAT));
   }

   MeasureFormat withLocale(ULocale locale) {
      return getInstance(locale, this.getWidth());
   }

   MeasureFormat withNumberFormat(NumberFormat format) {
      return new MeasureFormat(this.getLocale(), this.formatWidth, format, this.rules, this.numericFormatters);
   }

   MeasureFormat(ULocale locale, FormatWidth formatWidth) {
      this(locale, formatWidth, (NumberFormat)null, (PluralRules)null, (NumericFormatters)null);
   }

   private MeasureFormat(ULocale locale, FormatWidth formatWidth, NumberFormat numberFormat, PluralRules rules, NumericFormatters formatters) {
      this.formatter1 = null;
      this.formatter2 = null;
      this.formatter3 = null;
      this.setLocale(locale, locale);
      this.formatWidth = formatWidth;
      if (rules == null) {
         rules = PluralRules.forLocale(locale);
      }

      this.rules = rules;
      if (numberFormat == null) {
         numberFormat = NumberFormat.getInstance(locale);
      } else {
         numberFormat = (NumberFormat)numberFormat.clone();
      }

      this.numberFormat = numberFormat;
      if (formatters == null && formatWidth == MeasureFormat.FormatWidth.NUMERIC) {
         formatters = (NumericFormatters)localeToNumericDurationFormatters.get(locale);
         if (formatters == null) {
            formatters = loadNumericFormatters(locale);
            localeToNumericDurationFormatters.put(locale, formatters);
         }
      }

      this.numericFormatters = formatters;
      if (!(numberFormat instanceof DecimalFormat)) {
         throw new IllegalArgumentException();
      } else {
         this.numberFormatter = (LocalizedNumberFormatter)((DecimalFormat)numberFormat).toNumberFormatter().unitWidth(formatWidth.unitWidth);
      }
   }

   MeasureFormat(ULocale locale, FormatWidth formatWidth, NumberFormat numberFormat, PluralRules rules) {
      this(locale, formatWidth, numberFormat, rules, (NumericFormatters)null);
      if (formatWidth == MeasureFormat.FormatWidth.NUMERIC) {
         throw new IllegalArgumentException("The format width 'numeric' is not allowed by this constructor");
      }
   }

   private static NumericFormatters loadNumericFormatters(ULocale locale) {
      ICUResourceBundle r = (ICUResourceBundle)UResourceBundle.getBundleInstance("com/ibm/icu/impl/data/icudata/unit", locale);
      return new NumericFormatters(loadNumericDurationFormat(r, "hm"), loadNumericDurationFormat(r, "ms"), loadNumericDurationFormat(r, "hms"));
   }

   private synchronized LocalizedNumberFormatter getUnitFormatterFromCache(int type, MeasureUnit unit, MeasureUnit perUnit) {
      if (this.formatter1 != null) {
         if (this.formatter1.type == type && this.formatter1.unit == unit && this.formatter1.perUnit == perUnit) {
            return this.formatter1.formatter;
         }

         if (this.formatter2 != null) {
            if (this.formatter2.type == type && this.formatter2.unit == unit && this.formatter2.perUnit == perUnit) {
               return this.formatter2.formatter;
            }

            if (this.formatter3 != null && this.formatter3.type == type && this.formatter3.unit == unit && this.formatter3.perUnit == perUnit) {
               return this.formatter3.formatter;
            }
         }
      }

      LocalizedNumberFormatter formatter;
      if (type == 1) {
         formatter = (LocalizedNumberFormatter)((LocalizedNumberFormatter)((LocalizedNumberFormatter)this.getNumberFormatter().unit(unit)).perUnit(perUnit)).unitWidth(this.formatWidth.unitWidth);
      } else if (type == 2) {
         formatter = (LocalizedNumberFormatter)((LocalizedNumberFormatter)((LocalizedNumberFormatter)NumberFormatter.withLocale(this.getLocale()).unit(unit)).perUnit(perUnit)).unitWidth(this.formatWidth.currencyWidth);
      } else {
         assert type == 3;

         formatter = (LocalizedNumberFormatter)((LocalizedNumberFormatter)((LocalizedNumberFormatter)((LocalizedNumberFormatter)this.getNumberFormatter().unit(unit)).perUnit(perUnit)).unitWidth(this.formatWidth.unitWidth)).precision(Precision.integer().withMode(RoundingUtils.mathContextUnlimited(RoundingMode.DOWN)));
      }

      this.formatter3 = this.formatter2;
      this.formatter2 = this.formatter1;
      this.formatter1 = new NumberFormatterCacheEntry();
      this.formatter1.type = type;
      this.formatter1.unit = unit;
      this.formatter1.perUnit = perUnit;
      this.formatter1.formatter = formatter;
      return formatter;
   }

   synchronized void clearCache() {
      this.formatter1 = null;
      this.formatter2 = null;
      this.formatter3 = null;
   }

   LocalizedNumberFormatter getNumberFormatter() {
      return this.numberFormatter;
   }

   private FormattedStringBuilder formatMeasure(Measure measure) {
      MeasureUnit unit = measure.getUnit();
      DecimalQuantity dq = new DecimalQuantity_DualStorageBCD(measure.getNumber());
      FormattedStringBuilder string = new FormattedStringBuilder();
      if (unit instanceof Currency) {
         this.getUnitFormatterFromCache(2, unit, (MeasureUnit)null).formatImpl(dq, string);
      } else {
         this.getUnitFormatterFromCache(1, unit, (MeasureUnit)null).formatImpl(dq, string);
      }

      return string;
   }

   private FormattedStringBuilder formatMeasureInteger(Measure measure) {
      DecimalQuantity dq = new DecimalQuantity_DualStorageBCD(measure.getNumber());
      FormattedStringBuilder string = new FormattedStringBuilder();
      this.getUnitFormatterFromCache(3, measure.getUnit(), (MeasureUnit)null).formatImpl(dq, string);
      return string;
   }

   private void formatMeasuresSlowTrack(ListFormatter listFormatter, Appendable appendTo, FieldPosition fieldPosition, Measure... measures) {
      String[] results = new String[measures.length];
      FieldPosition fpos = new FieldPosition(fieldPosition.getFieldAttribute(), fieldPosition.getField());
      int fieldPositionFoundIndex = -1;

      for(int i = 0; i < measures.length; ++i) {
         FormattedStringBuilder result;
         if (i == measures.length - 1) {
            result = this.formatMeasure(measures[i]);
         } else {
            result = this.formatMeasureInteger(measures[i]);
         }

         if (fieldPositionFoundIndex == -1) {
            FormattedValueStringBuilderImpl.nextFieldPosition(result, fpos);
            if (fpos.getEndIndex() != 0) {
               fieldPositionFoundIndex = i;
            }
         }

         results[i] = result.toString();
      }

      ListFormatter.FormattedListBuilder builder = listFormatter.formatImpl(Arrays.asList(results), true);
      int offset = builder.getOffset(fieldPositionFoundIndex);
      if (offset != -1) {
         fieldPosition.setBeginIndex(fpos.getBeginIndex() + offset);
         fieldPosition.setEndIndex(fpos.getEndIndex() + offset);
      }

      builder.appendTo(appendTo);
   }

   private static String loadNumericDurationFormat(ICUResourceBundle r, String type) {
      r = r.getWithFallback(String.format("durationUnits/%s", type));
      return r.getString().replace("h", "H");
   }

   private static Number[] toHMS(Measure[] measures) {
      Number[] result = new Number[3];
      int lastIdx = -1;

      for(Measure m : measures) {
         if (m.getNumber().doubleValue() < (double)0.0F) {
            return null;
         }

         Integer idxObj = (Integer)hmsTo012.get(m.getUnit());
         if (idxObj == null) {
            return null;
         }

         int idx = idxObj;
         if (idx <= lastIdx) {
            return null;
         }

         lastIdx = idx;
         result[idx] = m.getNumber();
      }

      return result;
   }

   private void formatNumeric(Number[] hms, Appendable appendable) {
      String pattern;
      if (hms[0] != null && hms[2] != null) {
         pattern = this.numericFormatters.getHourMinuteSecond();
         if (hms[1] == null) {
            hms[1] = 0;
         }

         hms[1] = Math.floor(hms[1].doubleValue());
         hms[0] = Math.floor(hms[0].doubleValue());
      } else if (hms[0] != null && hms[1] != null) {
         pattern = this.numericFormatters.getHourMinute();
         hms[0] = Math.floor(hms[0].doubleValue());
      } else {
         if (hms[1] == null || hms[2] == null) {
            throw new IllegalStateException();
         }

         pattern = this.numericFormatters.getMinuteSecond();
         hms[1] = Math.floor(hms[1].doubleValue());
      }

      LocalizedNumberFormatter numberFormatter2 = (LocalizedNumberFormatter)this.numberFormatter.integerWidth(IntegerWidth.zeroFillTo(2));
      FormattedStringBuilder fsb = new FormattedStringBuilder();
      boolean protect = false;

      for(int i = 0; i < pattern.length(); ++i) {
         char c = pattern.charAt(i);
         Number value = 0;
         switch (c) {
            case 'H':
               value = hms[0];
               break;
            case 'm':
               value = hms[1];
               break;
            case 's':
               value = hms[2];
         }

         switch (c) {
            case '\'':
               if (i + 1 < pattern.length() && pattern.charAt(i + 1) == c) {
                  fsb.appendChar16(c, (Object)null);
                  ++i;
               } else {
                  protect = !protect;
               }
               break;
            case 'H':
            case 'm':
            case 's':
               if (protect) {
                  fsb.appendChar16(c, (Object)null);
               } else if (i + 1 < pattern.length() && pattern.charAt(i + 1) == c) {
                  fsb.append((CharSequence)numberFormatter2.format(value), (Object)null);
                  ++i;
               } else {
                  fsb.append((CharSequence)this.numberFormatter.format(value), (Object)null);
               }
               break;
            default:
               fsb.appendChar16(c, (Object)null);
         }
      }

      try {
         appendable.append(fsb);
      } catch (IOException e) {
         throw new ICUUncheckedIOException(e);
      }
   }

   Object toTimeUnitProxy() {
      return new MeasureProxy(this.getLocale(), this.formatWidth, this.getNumberFormatInternal(), 1);
   }

   Object toCurrencyProxy() {
      return new MeasureProxy(this.getLocale(), this.formatWidth, this.getNumberFormatInternal(), 2);
   }

   private Object writeReplace() throws ObjectStreamException {
      return new MeasureProxy(this.getLocale(), this.formatWidth, this.getNumberFormatInternal(), 0);
   }

   private static FormatWidth fromFormatWidthOrdinal(int ordinal) {
      FormatWidth[] values = MeasureFormat.FormatWidth.values();
      return ordinal >= 0 && ordinal < values.length ? values[ordinal] : MeasureFormat.FormatWidth.SHORT;
   }

   /** @deprecated */
   @Deprecated
   public static String getRangeFormat(ULocale forLocale, FormatWidth width) {
      if (forLocale.getLanguage().equals("fr")) {
         return getRangeFormat(ULocale.ROOT, width);
      } else {
         String result = (String)localeIdToRangeFormat.get(forLocale);
         if (result == null) {
            ICUResourceBundle rb = (ICUResourceBundle)UResourceBundle.getBundleInstance("com/ibm/icu/impl/data/icudata", forLocale);
            ULocale realLocale = rb.getULocale();
            if (!forLocale.equals(realLocale)) {
               result = (String)localeIdToRangeFormat.get(forLocale);
               if (result != null) {
                  localeIdToRangeFormat.put(forLocale, result);
                  return result;
               }
            }

            NumberingSystem ns = NumberingSystem.getInstance(forLocale);
            String resultString = null;

            try {
               resultString = rb.getStringWithFallback("NumberElements/" + ns.getName() + "/miscPatterns/range");
            } catch (MissingResourceException var8) {
               resultString = rb.getStringWithFallback("NumberElements/latn/patterns/range");
            }

            result = SimpleFormatterImpl.compileToStringMinMaxArguments(resultString, new StringBuilder(), 2, 2);
            localeIdToRangeFormat.put(forLocale, result);
            if (!forLocale.equals(realLocale)) {
               localeIdToRangeFormat.put(realLocale, result);
            }
         }

         return result;
      }
   }

   static {
      hmsTo012.put(MeasureUnit.HOUR, 0);
      hmsTo012.put(MeasureUnit.MINUTE, 1);
      hmsTo012.put(MeasureUnit.SECOND, 2);
      localeIdToRangeFormat = new ConcurrentHashMap();
   }

   public static enum FormatWidth {
      WIDE(ListFormatter.Width.WIDE, NumberFormatter.UnitWidth.FULL_NAME, NumberFormatter.UnitWidth.FULL_NAME),
      SHORT(ListFormatter.Width.SHORT, NumberFormatter.UnitWidth.SHORT, NumberFormatter.UnitWidth.ISO_CODE),
      NARROW(ListFormatter.Width.NARROW, NumberFormatter.UnitWidth.NARROW, NumberFormatter.UnitWidth.SHORT),
      NUMERIC(ListFormatter.Width.NARROW, NumberFormatter.UnitWidth.NARROW, NumberFormatter.UnitWidth.SHORT),
      /** @deprecated */
      @Deprecated
      DEFAULT_CURRENCY(ListFormatter.Width.SHORT, NumberFormatter.UnitWidth.FULL_NAME, NumberFormatter.UnitWidth.SHORT);

      final ListFormatter.Width listWidth;
      final NumberFormatter.UnitWidth unitWidth;
      final NumberFormatter.UnitWidth currencyWidth;

      private FormatWidth(ListFormatter.Width listWidth, NumberFormatter.UnitWidth unitWidth, NumberFormatter.UnitWidth currencyWidth) {
         this.listWidth = listWidth;
         this.unitWidth = unitWidth;
         this.currencyWidth = currencyWidth;
      }
   }

   static class NumericFormatters {
      private String hourMinute;
      private String minuteSecond;
      private String hourMinuteSecond;

      public NumericFormatters(String hourMinute, String minuteSecond, String hourMinuteSecond) {
         this.hourMinute = hourMinute;
         this.minuteSecond = minuteSecond;
         this.hourMinuteSecond = hourMinuteSecond;
      }

      public String getHourMinute() {
         return this.hourMinute;
      }

      public String getMinuteSecond() {
         return this.minuteSecond;
      }

      public String getHourMinuteSecond() {
         return this.hourMinuteSecond;
      }
   }

   static class NumberFormatterCacheEntry {
      int type;
      MeasureUnit unit;
      MeasureUnit perUnit;
      LocalizedNumberFormatter formatter;
   }

   static class MeasureProxy implements Externalizable {
      private static final long serialVersionUID = -6033308329886716770L;
      private ULocale locale;
      private FormatWidth formatWidth;
      private NumberFormat numberFormat;
      private int subClass;
      private HashMap keyValues;

      public MeasureProxy(ULocale locale, FormatWidth width, NumberFormat numberFormat, int subClass) {
         this.locale = locale;
         this.formatWidth = width;
         this.numberFormat = numberFormat;
         this.subClass = subClass;
         this.keyValues = new HashMap();
      }

      public MeasureProxy() {
      }

      public void writeExternal(ObjectOutput out) throws IOException {
         out.writeByte(0);
         out.writeUTF(this.locale.toLanguageTag());
         out.writeByte(this.formatWidth.ordinal());
         out.writeObject(this.numberFormat);
         out.writeByte(this.subClass);
         out.writeObject(this.keyValues);
      }

      public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
         in.readByte();
         this.locale = ULocale.forLanguageTag(in.readUTF());
         this.formatWidth = MeasureFormat.fromFormatWidthOrdinal(in.readByte() & 255);
         this.numberFormat = (NumberFormat)in.readObject();
         if (this.numberFormat == null) {
            throw new InvalidObjectException("Missing number format.");
         } else {
            this.subClass = in.readByte() & 255;
            this.keyValues = (HashMap)in.readObject();
            if (this.keyValues == null) {
               throw new InvalidObjectException("Missing optional values map.");
            }
         }
      }

      private TimeUnitFormat createTimeUnitFormat() throws InvalidObjectException {
         int style;
         if (this.formatWidth == MeasureFormat.FormatWidth.WIDE) {
            style = 0;
         } else {
            if (this.formatWidth != MeasureFormat.FormatWidth.SHORT) {
               throw new InvalidObjectException("Bad width: " + this.formatWidth);
            }

            style = 1;
         }

         TimeUnitFormat result = new TimeUnitFormat(this.locale, style);
         result.setNumberFormat(this.numberFormat);
         return result;
      }

      private Object readResolve() throws ObjectStreamException {
         switch (this.subClass) {
            case 0:
               return MeasureFormat.getInstance(this.locale, this.formatWidth, this.numberFormat);
            case 1:
               return this.createTimeUnitFormat();
            case 2:
               return MeasureFormat.getCurrencyFormat(this.locale);
            default:
               throw new InvalidObjectException("Unknown subclass: " + this.subClass);
         }
      }
   }
}
