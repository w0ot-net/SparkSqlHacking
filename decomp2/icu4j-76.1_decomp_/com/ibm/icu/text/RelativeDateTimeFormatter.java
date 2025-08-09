package com.ibm.icu.text;

import com.ibm.icu.impl.CacheBase;
import com.ibm.icu.impl.FormattedStringBuilder;
import com.ibm.icu.impl.FormattedValueStringBuilderImpl;
import com.ibm.icu.impl.ICUResourceBundle;
import com.ibm.icu.impl.SimpleFormatterImpl;
import com.ibm.icu.impl.SoftCache;
import com.ibm.icu.impl.StandardPlural;
import com.ibm.icu.impl.UResource;
import com.ibm.icu.impl.Utility;
import com.ibm.icu.impl.number.DecimalQuantity;
import com.ibm.icu.impl.number.DecimalQuantity_DualStorageBCD;
import com.ibm.icu.lang.UCharacter;
import com.ibm.icu.util.Calendar;
import com.ibm.icu.util.ICUException;
import com.ibm.icu.util.ULocale;
import com.ibm.icu.util.UResourceBundle;
import java.io.InvalidObjectException;
import java.text.AttributedCharacterIterator;
import java.text.Format;
import java.util.EnumMap;
import java.util.Locale;

public final class RelativeDateTimeFormatter {
   private int[] styleToDateFormatSymbolsWidth = new int[]{1, 3, 2};
   private final EnumMap qualitativeUnitMap;
   private final EnumMap patternMap;
   private final String combinedDateAndTime;
   private final PluralRules pluralRules;
   private final NumberFormat numberFormat;
   private final Style style;
   private final DisplayContext capitalizationContext;
   private final BreakIterator breakIterator;
   private final ULocale locale;
   private final DateFormatSymbols dateFormatSymbols;
   private static final Style[] fallbackCache = new Style[3];
   private static final Cache cache = new Cache();

   public static RelativeDateTimeFormatter getInstance() {
      return getInstance(ULocale.getDefault(), (NumberFormat)null, RelativeDateTimeFormatter.Style.LONG, DisplayContext.CAPITALIZATION_NONE);
   }

   public static RelativeDateTimeFormatter getInstance(ULocale locale) {
      return getInstance(locale, (NumberFormat)null, RelativeDateTimeFormatter.Style.LONG, DisplayContext.CAPITALIZATION_NONE);
   }

   public static RelativeDateTimeFormatter getInstance(Locale locale) {
      return getInstance(ULocale.forLocale(locale));
   }

   public static RelativeDateTimeFormatter getInstance(ULocale locale, NumberFormat nf) {
      return getInstance(locale, nf, RelativeDateTimeFormatter.Style.LONG, DisplayContext.CAPITALIZATION_NONE);
   }

   public static RelativeDateTimeFormatter getInstance(ULocale locale, NumberFormat nf, Style style, DisplayContext capitalizationContext) {
      RelativeDateTimeFormatterData data = cache.get(locale);
      if (nf == null) {
         nf = NumberFormat.getInstance(locale);
      } else {
         nf = (NumberFormat)nf.clone();
      }

      return new RelativeDateTimeFormatter(data.qualitativeUnitMap, data.relUnitPatternMap, SimpleFormatterImpl.compileToStringMinMaxArguments(data.dateTimePattern, new StringBuilder(), 2, 2), PluralRules.forLocale(locale), nf, style, capitalizationContext, capitalizationContext == DisplayContext.CAPITALIZATION_FOR_BEGINNING_OF_SENTENCE ? BreakIterator.getSentenceInstance(locale) : null, locale);
   }

   public static RelativeDateTimeFormatter getInstance(Locale locale, NumberFormat nf) {
      return getInstance(ULocale.forLocale(locale), nf);
   }

   public String format(double quantity, Direction direction, RelativeUnit unit) {
      FormattedStringBuilder output = this.formatImpl(quantity, direction, unit);
      return this.adjustForContext(output.toString());
   }

   public FormattedRelativeDateTime formatToValue(double quantity, Direction direction, RelativeUnit unit) {
      this.checkNoAdjustForContext();
      return new FormattedRelativeDateTime(this.formatImpl(quantity, direction, unit));
   }

   private FormattedStringBuilder formatImpl(double quantity, Direction direction, RelativeUnit unit) {
      if (direction != RelativeDateTimeFormatter.Direction.LAST && direction != RelativeDateTimeFormatter.Direction.NEXT) {
         throw new IllegalArgumentException("direction must be NEXT or LAST");
      } else {
         int pastFutureIndex = direction == RelativeDateTimeFormatter.Direction.NEXT ? 1 : 0;
         FormattedStringBuilder output = new FormattedStringBuilder();
         String pluralKeyword;
         if (this.numberFormat instanceof DecimalFormat) {
            DecimalQuantity dq = new DecimalQuantity_DualStorageBCD(quantity);
            ((DecimalFormat)this.numberFormat).toNumberFormatter().formatImpl(dq, output);
            pluralKeyword = this.pluralRules.select((PluralRules.IFixedDecimal)dq);
         } else {
            String result = this.numberFormat.format(quantity);
            output.append((CharSequence)result, (Object)null);
            pluralKeyword = this.pluralRules.select(quantity);
         }

         StandardPlural pluralForm = StandardPlural.orOtherFromString(pluralKeyword);
         String compiledPattern = this.getRelativeUnitPluralPattern(this.style, unit, pastFutureIndex, pluralForm);
         SimpleFormatterImpl.formatPrefixSuffix(compiledPattern, RelativeDateTimeFormatter.Field.LITERAL, 0, output.length(), output);
         return output;
      }
   }

   public String formatNumeric(double offset, RelativeDateTimeUnit unit) {
      FormattedStringBuilder output = this.formatNumericImpl(offset, unit);
      return this.adjustForContext(output.toString());
   }

   public FormattedRelativeDateTime formatNumericToValue(double offset, RelativeDateTimeUnit unit) {
      this.checkNoAdjustForContext();
      return new FormattedRelativeDateTime(this.formatNumericImpl(offset, unit));
   }

   private FormattedStringBuilder formatNumericImpl(double offset, RelativeDateTimeUnit unit) {
      RelativeUnit relunit = RelativeDateTimeFormatter.RelativeUnit.SECONDS;
      switch (unit) {
         case YEAR:
            relunit = RelativeDateTimeFormatter.RelativeUnit.YEARS;
            break;
         case QUARTER:
            relunit = RelativeDateTimeFormatter.RelativeUnit.QUARTERS;
            break;
         case MONTH:
            relunit = RelativeDateTimeFormatter.RelativeUnit.MONTHS;
            break;
         case WEEK:
            relunit = RelativeDateTimeFormatter.RelativeUnit.WEEKS;
            break;
         case DAY:
            relunit = RelativeDateTimeFormatter.RelativeUnit.DAYS;
            break;
         case HOUR:
            relunit = RelativeDateTimeFormatter.RelativeUnit.HOURS;
            break;
         case MINUTE:
            relunit = RelativeDateTimeFormatter.RelativeUnit.MINUTES;
         case SECOND:
         default:
            break;
         case SUNDAY:
            relunit = RelativeDateTimeFormatter.RelativeUnit.SUNDAYS;
            break;
         case MONDAY:
            relunit = RelativeDateTimeFormatter.RelativeUnit.MONDAYS;
            break;
         case TUESDAY:
            relunit = RelativeDateTimeFormatter.RelativeUnit.TUESDAYS;
            break;
         case WEDNESDAY:
            relunit = RelativeDateTimeFormatter.RelativeUnit.WEDNESDAYS;
            break;
         case THURSDAY:
            relunit = RelativeDateTimeFormatter.RelativeUnit.THURSDAYS;
            break;
         case FRIDAY:
            relunit = RelativeDateTimeFormatter.RelativeUnit.FRIDAYS;
            break;
         case SATURDAY:
            relunit = RelativeDateTimeFormatter.RelativeUnit.SATURDAYS;
      }

      Direction direction = RelativeDateTimeFormatter.Direction.NEXT;
      if (Double.compare(offset, (double)0.0F) < 0) {
         direction = RelativeDateTimeFormatter.Direction.LAST;
         offset = -offset;
      }

      return this.formatImpl(offset, direction, relunit);
   }

   public String format(Direction direction, AbsoluteUnit unit) {
      String result = this.formatAbsoluteImpl(direction, unit);
      return result != null ? this.adjustForContext(result) : null;
   }

   public FormattedRelativeDateTime formatToValue(Direction direction, AbsoluteUnit unit) {
      this.checkNoAdjustForContext();
      String string = this.formatAbsoluteImpl(direction, unit);
      if (string == null) {
         return null;
      } else {
         FormattedStringBuilder nsb = new FormattedStringBuilder();
         nsb.append((CharSequence)string, (Object)RelativeDateTimeFormatter.Field.LITERAL);
         return new FormattedRelativeDateTime(nsb);
      }
   }

   private String formatAbsoluteImpl(Direction direction, AbsoluteUnit unit) {
      if (unit == RelativeDateTimeFormatter.AbsoluteUnit.NOW && direction != RelativeDateTimeFormatter.Direction.PLAIN) {
         throw new IllegalArgumentException("NOW can only accept direction PLAIN.");
      } else {
         String result;
         if (direction == RelativeDateTimeFormatter.Direction.PLAIN && RelativeDateTimeFormatter.AbsoluteUnit.SUNDAY.ordinal() <= unit.ordinal() && unit.ordinal() <= RelativeDateTimeFormatter.AbsoluteUnit.SATURDAY.ordinal()) {
            int dateSymbolsDayOrdinal = unit.ordinal() - RelativeDateTimeFormatter.AbsoluteUnit.SUNDAY.ordinal() + 1;
            String[] dayNames = this.dateFormatSymbols.getWeekdays(1, this.styleToDateFormatSymbolsWidth[this.style.ordinal()]);
            result = dayNames[dateSymbolsDayOrdinal];
         } else {
            result = this.getAbsoluteUnitString(this.style, unit, direction);
         }

         return result;
      }
   }

   public String format(double offset, RelativeDateTimeUnit unit) {
      return this.adjustForContext(this.formatRelativeImpl(offset, unit).toString());
   }

   public FormattedRelativeDateTime formatToValue(double offset, RelativeDateTimeUnit unit) {
      this.checkNoAdjustForContext();
      CharSequence cs = this.formatRelativeImpl(offset, unit);
      FormattedStringBuilder nsb;
      if (cs instanceof FormattedStringBuilder) {
         nsb = (FormattedStringBuilder)cs;
      } else {
         nsb = new FormattedStringBuilder();
         nsb.append((CharSequence)cs, (Object)RelativeDateTimeFormatter.Field.LITERAL);
      }

      return new FormattedRelativeDateTime(nsb);
   }

   private CharSequence formatRelativeImpl(double offset, RelativeDateTimeUnit unit) {
      boolean useNumeric = true;
      Direction direction = RelativeDateTimeFormatter.Direction.THIS;
      if (offset > -2.1 && offset < 2.1) {
         double offsetx100 = offset * (double)100.0F;
         int intoffsetx100 = offsetx100 < (double)0.0F ? (int)(offsetx100 - (double)0.5F) : (int)(offsetx100 + (double)0.5F);
         switch (intoffsetx100) {
            case -200:
               direction = RelativeDateTimeFormatter.Direction.LAST_2;
               useNumeric = false;
               break;
            case -100:
               direction = RelativeDateTimeFormatter.Direction.LAST;
               useNumeric = false;
               break;
            case 0:
               useNumeric = false;
               break;
            case 100:
               direction = RelativeDateTimeFormatter.Direction.NEXT;
               useNumeric = false;
               break;
            case 200:
               direction = RelativeDateTimeFormatter.Direction.NEXT_2;
               useNumeric = false;
         }
      }

      AbsoluteUnit absunit = RelativeDateTimeFormatter.AbsoluteUnit.NOW;
      switch (unit) {
         case YEAR:
            absunit = RelativeDateTimeFormatter.AbsoluteUnit.YEAR;
            break;
         case QUARTER:
            absunit = RelativeDateTimeFormatter.AbsoluteUnit.QUARTER;
            break;
         case MONTH:
            absunit = RelativeDateTimeFormatter.AbsoluteUnit.MONTH;
            break;
         case WEEK:
            absunit = RelativeDateTimeFormatter.AbsoluteUnit.WEEK;
            break;
         case DAY:
            absunit = RelativeDateTimeFormatter.AbsoluteUnit.DAY;
            break;
         case HOUR:
            absunit = RelativeDateTimeFormatter.AbsoluteUnit.HOUR;
            break;
         case MINUTE:
            absunit = RelativeDateTimeFormatter.AbsoluteUnit.MINUTE;
            break;
         case SECOND:
            if (direction == RelativeDateTimeFormatter.Direction.THIS) {
               direction = RelativeDateTimeFormatter.Direction.PLAIN;
            } else {
               useNumeric = true;
            }
            break;
         case SUNDAY:
            absunit = RelativeDateTimeFormatter.AbsoluteUnit.SUNDAY;
            break;
         case MONDAY:
            absunit = RelativeDateTimeFormatter.AbsoluteUnit.MONDAY;
            break;
         case TUESDAY:
            absunit = RelativeDateTimeFormatter.AbsoluteUnit.TUESDAY;
            break;
         case WEDNESDAY:
            absunit = RelativeDateTimeFormatter.AbsoluteUnit.WEDNESDAY;
            break;
         case THURSDAY:
            absunit = RelativeDateTimeFormatter.AbsoluteUnit.THURSDAY;
            break;
         case FRIDAY:
            absunit = RelativeDateTimeFormatter.AbsoluteUnit.FRIDAY;
            break;
         case SATURDAY:
            absunit = RelativeDateTimeFormatter.AbsoluteUnit.SATURDAY;
            break;
         default:
            useNumeric = true;
      }

      if (!useNumeric) {
         String result = this.formatAbsoluteImpl(direction, absunit);
         if (result != null && result.length() > 0) {
            return result;
         }
      }

      return this.formatNumericImpl(offset, unit);
   }

   private String getAbsoluteUnitString(Style style, AbsoluteUnit unit, Direction direction) {
      do {
         EnumMap<AbsoluteUnit, EnumMap<Direction, String>> unitMap = (EnumMap)this.qualitativeUnitMap.get(style);
         if (unitMap != null) {
            EnumMap<Direction, String> dirMap = (EnumMap)unitMap.get(unit);
            if (dirMap != null) {
               String result = (String)dirMap.get(direction);
               if (result != null) {
                  return result;
               }
            }
         }
      } while((style = fallbackCache[style.ordinal()]) != null);

      return null;
   }

   public String combineDateAndTime(String relativeDateString, String timeString) {
      return SimpleFormatterImpl.formatCompiledPattern(this.combinedDateAndTime, timeString, relativeDateString);
   }

   public NumberFormat getNumberFormat() {
      synchronized(this.numberFormat) {
         return (NumberFormat)this.numberFormat.clone();
      }
   }

   public DisplayContext getCapitalizationContext() {
      return this.capitalizationContext;
   }

   public Style getFormatStyle() {
      return this.style;
   }

   private String adjustForContext(String originalFormattedString) {
      if (this.breakIterator != null && originalFormattedString.length() != 0 && UCharacter.isLowerCase(UCharacter.codePointAt((CharSequence)originalFormattedString, 0))) {
         synchronized(this.breakIterator) {
            return UCharacter.toTitleCase((ULocale)this.locale, originalFormattedString, this.breakIterator, 768);
         }
      } else {
         return originalFormattedString;
      }
   }

   private void checkNoAdjustForContext() {
      if (this.breakIterator != null) {
         throw new UnsupportedOperationException("Capitalization context is not supported in formatV");
      }
   }

   private RelativeDateTimeFormatter(EnumMap qualitativeUnitMap, EnumMap patternMap, String combinedDateAndTime, PluralRules pluralRules, NumberFormat numberFormat, Style style, DisplayContext capitalizationContext, BreakIterator breakIterator, ULocale locale) {
      this.qualitativeUnitMap = qualitativeUnitMap;
      this.patternMap = patternMap;
      this.combinedDateAndTime = combinedDateAndTime;
      this.pluralRules = pluralRules;
      this.numberFormat = numberFormat;
      this.style = style;
      if (capitalizationContext.type() != DisplayContext.Type.CAPITALIZATION) {
         throw new IllegalArgumentException(capitalizationContext.toString());
      } else {
         this.capitalizationContext = capitalizationContext;
         this.breakIterator = breakIterator;
         this.locale = locale;
         this.dateFormatSymbols = new DateFormatSymbols(locale);
      }
   }

   private String getRelativeUnitPluralPattern(Style style, RelativeUnit unit, int pastFutureIndex, StandardPlural pluralForm) {
      if (pluralForm != StandardPlural.OTHER) {
         String formatter = this.getRelativeUnitPattern(style, unit, pastFutureIndex, pluralForm);
         if (formatter != null) {
            return formatter;
         }
      }

      return this.getRelativeUnitPattern(style, unit, pastFutureIndex, StandardPlural.OTHER);
   }

   private String getRelativeUnitPattern(Style style, RelativeUnit unit, int pastFutureIndex, StandardPlural pluralForm) {
      int pluralIndex = pluralForm.ordinal();

      do {
         EnumMap<RelativeUnit, String[][]> unitMap = (EnumMap)this.patternMap.get(style);
         if (unitMap != null) {
            String[][] spfCompiledPatterns = (String[][])unitMap.get(unit);
            if (spfCompiledPatterns != null && spfCompiledPatterns[pastFutureIndex][pluralIndex] != null) {
               return spfCompiledPatterns[pastFutureIndex][pluralIndex];
            }
         }
      } while((style = fallbackCache[style.ordinal()]) != null);

      return null;
   }

   private static Direction keyToDirection(UResource.Key key) {
      if (key.contentEquals("-2")) {
         return RelativeDateTimeFormatter.Direction.LAST_2;
      } else if (key.contentEquals("-1")) {
         return RelativeDateTimeFormatter.Direction.LAST;
      } else if (key.contentEquals("0")) {
         return RelativeDateTimeFormatter.Direction.THIS;
      } else if (key.contentEquals("1")) {
         return RelativeDateTimeFormatter.Direction.NEXT;
      } else {
         return key.contentEquals("2") ? RelativeDateTimeFormatter.Direction.NEXT_2 : null;
      }
   }

   public static enum Style {
      LONG,
      SHORT,
      NARROW;

      private static final int INDEX_COUNT = 3;
   }

   public static enum RelativeUnit {
      SECONDS,
      MINUTES,
      HOURS,
      DAYS,
      WEEKS,
      MONTHS,
      YEARS,
      QUARTERS,
      SUNDAYS,
      MONDAYS,
      TUESDAYS,
      WEDNESDAYS,
      THURSDAYS,
      FRIDAYS,
      SATURDAYS;
   }

   public static enum AbsoluteUnit {
      SUNDAY,
      MONDAY,
      TUESDAY,
      WEDNESDAY,
      THURSDAY,
      FRIDAY,
      SATURDAY,
      DAY,
      WEEK,
      MONTH,
      YEAR,
      NOW,
      QUARTER,
      HOUR,
      MINUTE;
   }

   public static enum Direction {
      LAST_2,
      LAST,
      THIS,
      NEXT,
      NEXT_2,
      PLAIN;
   }

   public static enum RelativeDateTimeUnit {
      YEAR,
      QUARTER,
      MONTH,
      WEEK,
      DAY,
      HOUR,
      MINUTE,
      SECOND,
      SUNDAY,
      MONDAY,
      TUESDAY,
      WEDNESDAY,
      THURSDAY,
      FRIDAY,
      SATURDAY;
   }

   public static class Field extends Format.Field {
      private static final long serialVersionUID = -5327685528663492325L;
      public static final Field LITERAL = new Field("literal");
      public static final Field NUMERIC = new Field("numeric");

      private Field(String fieldName) {
         super(fieldName);
      }

      /** @deprecated */
      @Deprecated
      protected Object readResolve() throws InvalidObjectException {
         if (this.getName().equals(LITERAL.getName())) {
            return LITERAL;
         } else if (this.getName().equals(NUMERIC.getName())) {
            return NUMERIC;
         } else {
            throw new InvalidObjectException("An invalid object.");
         }
      }
   }

   public static class FormattedRelativeDateTime implements FormattedValue {
      private final FormattedStringBuilder string;

      private FormattedRelativeDateTime(FormattedStringBuilder string) {
         this.string = string;
      }

      public String toString() {
         return this.string.toString();
      }

      public int length() {
         return this.string.length();
      }

      public char charAt(int index) {
         return this.string.charAt(index);
      }

      public CharSequence subSequence(int start, int end) {
         return this.string.subString(start, end);
      }

      public Appendable appendTo(Appendable appendable) {
         return Utility.appendTo(this.string, appendable);
      }

      public boolean nextPosition(ConstrainedFieldPosition cfpos) {
         return FormattedValueStringBuilderImpl.nextPosition(this.string, cfpos, RelativeDateTimeFormatter.Field.NUMERIC);
      }

      public AttributedCharacterIterator toCharacterIterator() {
         return FormattedValueStringBuilderImpl.toCharacterIterator(this.string, RelativeDateTimeFormatter.Field.NUMERIC);
      }
   }

   private static class RelativeDateTimeFormatterData {
      public final EnumMap qualitativeUnitMap;
      EnumMap relUnitPatternMap;
      public final String dateTimePattern;

      public RelativeDateTimeFormatterData(EnumMap qualitativeUnitMap, EnumMap relUnitPatternMap, String dateTimePattern) {
         this.qualitativeUnitMap = qualitativeUnitMap;
         this.relUnitPatternMap = relUnitPatternMap;
         this.dateTimePattern = dateTimePattern;
      }
   }

   private static class Cache {
      private final CacheBase cache;

      private Cache() {
         this.cache = new SoftCache() {
            protected RelativeDateTimeFormatterData createInstance(String key, ULocale locale) {
               return (new Loader(locale)).load();
            }
         };
      }

      public RelativeDateTimeFormatterData get(ULocale locale) {
         String key = locale.toString();
         return (RelativeDateTimeFormatterData)this.cache.getInstance(key, locale);
      }
   }

   private static final class RelDateTimeDataSink extends UResource.Sink {
      EnumMap qualitativeUnitMap = new EnumMap(Style.class);
      EnumMap styleRelUnitPatterns = new EnumMap(Style.class);
      StringBuilder sb = new StringBuilder();
      int pastFutureIndex;
      Style style;
      DateTimeUnit unit;

      private Style styleFromKey(UResource.Key key) {
         if (key.endsWith("-short")) {
            return RelativeDateTimeFormatter.Style.SHORT;
         } else {
            return key.endsWith("-narrow") ? RelativeDateTimeFormatter.Style.NARROW : RelativeDateTimeFormatter.Style.LONG;
         }
      }

      private Style styleFromAlias(UResource.Value value) {
         String s = value.getAliasString();
         if (s.endsWith("-short")) {
            return RelativeDateTimeFormatter.Style.SHORT;
         } else {
            return s.endsWith("-narrow") ? RelativeDateTimeFormatter.Style.NARROW : RelativeDateTimeFormatter.Style.LONG;
         }
      }

      private static int styleSuffixLength(Style style) {
         switch (style) {
            case SHORT:
               return 6;
            case NARROW:
               return 7;
            default:
               return 0;
         }
      }

      public void consumeTableRelative(UResource.Key key, UResource.Value value) {
         UResource.Table unitTypesTable = value.getTable();

         for(int i = 0; unitTypesTable.getKeyAndValue(i, key, value); ++i) {
            if (value.getType() == 0) {
               String valueString = value.getString();
               EnumMap<AbsoluteUnit, EnumMap<Direction, String>> absMap = (EnumMap)this.qualitativeUnitMap.get(this.style);
               if (this.unit.relUnit == RelativeDateTimeFormatter.RelativeUnit.SECONDS && key.contentEquals("0")) {
                  EnumMap<Direction, String> unitStrings = (EnumMap)absMap.get(RelativeDateTimeFormatter.AbsoluteUnit.NOW);
                  if (unitStrings == null) {
                     unitStrings = new EnumMap(Direction.class);
                     absMap.put(RelativeDateTimeFormatter.AbsoluteUnit.NOW, unitStrings);
                  }

                  if (unitStrings.get(RelativeDateTimeFormatter.Direction.PLAIN) == null) {
                     unitStrings.put(RelativeDateTimeFormatter.Direction.PLAIN, valueString);
                  }
               } else {
                  Direction keyDirection = RelativeDateTimeFormatter.keyToDirection(key);
                  if (keyDirection != null) {
                     AbsoluteUnit absUnit = this.unit.absUnit;
                     if (absUnit != null) {
                        if (absMap == null) {
                           absMap = new EnumMap(AbsoluteUnit.class);
                           this.qualitativeUnitMap.put(this.style, absMap);
                        }

                        EnumMap<Direction, String> dirMap = (EnumMap)absMap.get(absUnit);
                        if (dirMap == null) {
                           dirMap = new EnumMap(Direction.class);
                           absMap.put(absUnit, dirMap);
                        }

                        if (dirMap.get(keyDirection) == null) {
                           dirMap.put(keyDirection, value.getString());
                        }
                     }
                  }
               }
            }
         }

      }

      public void consumeTableRelativeTime(UResource.Key key, UResource.Value value) {
         if (this.unit.relUnit != null) {
            UResource.Table unitTypesTable = value.getTable();

            for(int i = 0; unitTypesTable.getKeyAndValue(i, key, value); ++i) {
               if (key.contentEquals("past")) {
                  this.pastFutureIndex = 0;
               } else {
                  if (!key.contentEquals("future")) {
                     continue;
                  }

                  this.pastFutureIndex = 1;
               }

               this.consumeTimeDetail(key, value);
            }

         }
      }

      public void consumeTimeDetail(UResource.Key key, UResource.Value value) {
         UResource.Table unitTypesTable = value.getTable();
         EnumMap<RelativeUnit, String[][]> unitPatterns = (EnumMap)this.styleRelUnitPatterns.get(this.style);
         if (unitPatterns == null) {
            unitPatterns = new EnumMap(RelativeUnit.class);
            this.styleRelUnitPatterns.put(this.style, unitPatterns);
         }

         String[][] patterns = (String[][])unitPatterns.get(this.unit.relUnit);
         if (patterns == null) {
            patterns = new String[2][StandardPlural.COUNT];
            unitPatterns.put(this.unit.relUnit, patterns);
         }

         for(int i = 0; unitTypesTable.getKeyAndValue(i, key, value); ++i) {
            if (value.getType() == 0) {
               int pluralIndex = StandardPlural.indexFromString(key.toString());
               if (patterns[this.pastFutureIndex][pluralIndex] == null) {
                  patterns[this.pastFutureIndex][pluralIndex] = SimpleFormatterImpl.compileToStringMinMaxArguments(value.getString(), this.sb, 0, 1);
               }
            }
         }

      }

      private void handlePlainDirection(UResource.Key key, UResource.Value value) {
         AbsoluteUnit absUnit = this.unit.absUnit;
         if (absUnit != null) {
            EnumMap<AbsoluteUnit, EnumMap<Direction, String>> unitMap = (EnumMap)this.qualitativeUnitMap.get(this.style);
            if (unitMap == null) {
               unitMap = new EnumMap(AbsoluteUnit.class);
               this.qualitativeUnitMap.put(this.style, unitMap);
            }

            EnumMap<Direction, String> dirMap = (EnumMap)unitMap.get(absUnit);
            if (dirMap == null) {
               dirMap = new EnumMap(Direction.class);
               unitMap.put(absUnit, dirMap);
            }

            if (dirMap.get(RelativeDateTimeFormatter.Direction.PLAIN) == null) {
               dirMap.put(RelativeDateTimeFormatter.Direction.PLAIN, value.toString());
            }

         }
      }

      public void consumeTimeUnit(UResource.Key key, UResource.Value value) {
         UResource.Table unitTypesTable = value.getTable();

         for(int i = 0; unitTypesTable.getKeyAndValue(i, key, value); ++i) {
            if (key.contentEquals("dn") && value.getType() == 0) {
               this.handlePlainDirection(key, value);
            }

            if (value.getType() == 2) {
               if (key.contentEquals("relative")) {
                  this.consumeTableRelative(key, value);
               } else if (key.contentEquals("relativeTime")) {
                  this.consumeTableRelativeTime(key, value);
               }
            }
         }

      }

      private void handleAlias(UResource.Key key, UResource.Value value, boolean noFallback) {
         Style sourceStyle = this.styleFromKey(key);
         int limit = key.length() - styleSuffixLength(sourceStyle);
         DateTimeUnit unit = RelativeDateTimeFormatter.RelDateTimeDataSink.DateTimeUnit.orNullFromString(key.substring(0, limit));
         if (unit != null) {
            Style targetStyle = this.styleFromAlias(value);
            if (sourceStyle == targetStyle) {
               throw new ICUException("Invalid style fallback from " + sourceStyle + " to itself");
            } else {
               if (RelativeDateTimeFormatter.fallbackCache[sourceStyle.ordinal()] == null) {
                  RelativeDateTimeFormatter.fallbackCache[sourceStyle.ordinal()] = targetStyle;
               } else if (RelativeDateTimeFormatter.fallbackCache[sourceStyle.ordinal()] != targetStyle) {
                  throw new ICUException("Inconsistent style fallback for style " + sourceStyle + " to " + targetStyle);
               }

            }
         }
      }

      public void put(UResource.Key key, UResource.Value value, boolean noFallback) {
         if (value.getType() != 3) {
            UResource.Table table = value.getTable();

            for(int i = 0; table.getKeyAndValue(i, key, value); ++i) {
               if (value.getType() == 3) {
                  this.handleAlias(key, value, noFallback);
               } else {
                  this.style = this.styleFromKey(key);
                  int limit = key.length() - styleSuffixLength(this.style);
                  this.unit = RelativeDateTimeFormatter.RelDateTimeDataSink.DateTimeUnit.orNullFromString(key.substring(0, limit));
                  if (this.unit != null) {
                     this.consumeTimeUnit(key, value);
                  }
               }
            }

         }
      }

      RelDateTimeDataSink() {
      }

      private static enum DateTimeUnit {
         SECOND(RelativeDateTimeFormatter.RelativeUnit.SECONDS, (AbsoluteUnit)null),
         MINUTE(RelativeDateTimeFormatter.RelativeUnit.MINUTES, RelativeDateTimeFormatter.AbsoluteUnit.MINUTE),
         HOUR(RelativeDateTimeFormatter.RelativeUnit.HOURS, RelativeDateTimeFormatter.AbsoluteUnit.HOUR),
         DAY(RelativeDateTimeFormatter.RelativeUnit.DAYS, RelativeDateTimeFormatter.AbsoluteUnit.DAY),
         WEEK(RelativeDateTimeFormatter.RelativeUnit.WEEKS, RelativeDateTimeFormatter.AbsoluteUnit.WEEK),
         MONTH(RelativeDateTimeFormatter.RelativeUnit.MONTHS, RelativeDateTimeFormatter.AbsoluteUnit.MONTH),
         QUARTER(RelativeDateTimeFormatter.RelativeUnit.QUARTERS, RelativeDateTimeFormatter.AbsoluteUnit.QUARTER),
         YEAR(RelativeDateTimeFormatter.RelativeUnit.YEARS, RelativeDateTimeFormatter.AbsoluteUnit.YEAR),
         SUNDAY(RelativeDateTimeFormatter.RelativeUnit.SUNDAYS, RelativeDateTimeFormatter.AbsoluteUnit.SUNDAY),
         MONDAY(RelativeDateTimeFormatter.RelativeUnit.MONDAYS, RelativeDateTimeFormatter.AbsoluteUnit.MONDAY),
         TUESDAY(RelativeDateTimeFormatter.RelativeUnit.TUESDAYS, RelativeDateTimeFormatter.AbsoluteUnit.TUESDAY),
         WEDNESDAY(RelativeDateTimeFormatter.RelativeUnit.WEDNESDAYS, RelativeDateTimeFormatter.AbsoluteUnit.WEDNESDAY),
         THURSDAY(RelativeDateTimeFormatter.RelativeUnit.THURSDAYS, RelativeDateTimeFormatter.AbsoluteUnit.THURSDAY),
         FRIDAY(RelativeDateTimeFormatter.RelativeUnit.FRIDAYS, RelativeDateTimeFormatter.AbsoluteUnit.FRIDAY),
         SATURDAY(RelativeDateTimeFormatter.RelativeUnit.SATURDAYS, RelativeDateTimeFormatter.AbsoluteUnit.SATURDAY);

         RelativeUnit relUnit;
         AbsoluteUnit absUnit;

         private DateTimeUnit(RelativeUnit relUnit, AbsoluteUnit absUnit) {
            this.relUnit = relUnit;
            this.absUnit = absUnit;
         }

         private static final DateTimeUnit orNullFromString(CharSequence keyword) {
            switch (keyword.length()) {
               case 3:
                  if ("day".contentEquals(keyword)) {
                     return DAY;
                  }

                  if ("sun".contentEquals(keyword)) {
                     return SUNDAY;
                  }

                  if ("mon".contentEquals(keyword)) {
                     return MONDAY;
                  }

                  if ("tue".contentEquals(keyword)) {
                     return TUESDAY;
                  }

                  if ("wed".contentEquals(keyword)) {
                     return WEDNESDAY;
                  }

                  if ("thu".contentEquals(keyword)) {
                     return THURSDAY;
                  }

                  if ("fri".contentEquals(keyword)) {
                     return FRIDAY;
                  }

                  if ("sat".contentEquals(keyword)) {
                     return SATURDAY;
                  }
                  break;
               case 4:
                  if ("hour".contentEquals(keyword)) {
                     return HOUR;
                  }

                  if ("week".contentEquals(keyword)) {
                     return WEEK;
                  }

                  if ("year".contentEquals(keyword)) {
                     return YEAR;
                  }
                  break;
               case 5:
                  if ("month".contentEquals(keyword)) {
                     return MONTH;
                  }
                  break;
               case 6:
                  if ("minute".contentEquals(keyword)) {
                     return MINUTE;
                  }

                  if ("second".contentEquals(keyword)) {
                     return SECOND;
                  }
                  break;
               case 7:
                  if ("quarter".contentEquals(keyword)) {
                     return QUARTER;
                  }
            }

            return null;
         }
      }
   }

   private static class Loader {
      private final ULocale ulocale;

      public Loader(ULocale ulocale) {
         this.ulocale = ulocale;
      }

      private String getDateTimePattern() {
         Calendar cal = Calendar.getInstance(this.ulocale);
         return Calendar.getDateAtTimePattern(cal, this.ulocale, 2);
      }

      public RelativeDateTimeFormatterData load() {
         RelDateTimeDataSink sink = new RelDateTimeDataSink();
         ICUResourceBundle r = (ICUResourceBundle)UResourceBundle.getBundleInstance("com/ibm/icu/impl/data/icudata", this.ulocale);
         r.getAllItemsWithFallback("fields", sink);

         for(Style testStyle : RelativeDateTimeFormatter.Style.values()) {
            Style newStyle1 = RelativeDateTimeFormatter.fallbackCache[testStyle.ordinal()];
            if (newStyle1 != null) {
               Style newStyle2 = RelativeDateTimeFormatter.fallbackCache[newStyle1.ordinal()];
               if (newStyle2 != null && RelativeDateTimeFormatter.fallbackCache[newStyle2.ordinal()] != null) {
                  throw new IllegalStateException("Style fallback too deep");
               }
            }
         }

         return new RelativeDateTimeFormatterData(sink.qualitativeUnitMap, sink.styleRelUnitPatterns, this.getDateTimePattern());
      }
   }
}
