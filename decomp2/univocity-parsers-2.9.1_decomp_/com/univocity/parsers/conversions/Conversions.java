package com.univocity.parsers.conversions;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Conversions {
   private static final UpperCaseConversion upperCase = new UpperCaseConversion();
   private static final LowerCaseConversion lowerCase = new LowerCaseConversion();
   private static final TrimConversion trim = new TrimConversion();
   private static final ToStringConversion toString = new ToStringConversion();

   private Conversions() {
   }

   public static ToStringConversion string() {
      return toString;
   }

   public static UpperCaseConversion toUpperCase() {
      return upperCase;
   }

   public static LowerCaseConversion toLowerCase() {
      return lowerCase;
   }

   public static TrimConversion trim() {
      return trim;
   }

   public static TrimConversion trim(int length) {
      return new TrimConversion(length);
   }

   public static RegexConversion replace(String replaceRegex, String replacement) {
      return new RegexConversion(replaceRegex, replacement);
   }

   public static NullStringConversion toNull(String... nullRepresentations) {
      return new NullStringConversion(nullRepresentations);
   }

   public static DateConversion toDate(Locale locale, String... dateFormats) {
      return new DateConversion(locale, dateFormats);
   }

   public static DateConversion toDate(String... dateFormats) {
      return new DateConversion(Locale.getDefault(), dateFormats);
   }

   public static DateConversion toDate(Locale locale, Date dateIfNull, String... dateFormats) {
      return new DateConversion(locale, dateIfNull, (String)null, dateFormats);
   }

   public static DateConversion toDate(Date dateIfNull, String... dateFormats) {
      return new DateConversion(Locale.getDefault(), dateIfNull, (String)null, dateFormats);
   }

   public static DateConversion toDate(TimeZone timeZone, Locale locale, Date dateIfNull, String stringIfNull, String... dateFormats) {
      return new DateConversion(timeZone, locale, dateIfNull, stringIfNull, dateFormats);
   }

   public static DateConversion toDate(Locale locale, Date dateIfNull, String stringIfNull, String... dateFormats) {
      return new DateConversion(locale, dateIfNull, stringIfNull, dateFormats);
   }

   public static DateConversion toDate(Date dateIfNull, String stringIfNull, String... dateFormats) {
      return new DateConversion(Locale.getDefault(), dateIfNull, stringIfNull, dateFormats);
   }

   public static CalendarConversion toCalendar(Locale locale, String... dateFormats) {
      return new CalendarConversion(locale, dateFormats);
   }

   public static CalendarConversion toCalendar(String... dateFormats) {
      return new CalendarConversion(Locale.getDefault(), dateFormats);
   }

   public static CalendarConversion toCalendar(Locale locale, Calendar dateIfNull, String... dateFormats) {
      return new CalendarConversion(locale, dateIfNull, (String)null, dateFormats);
   }

   public static CalendarConversion toCalendar(Calendar dateIfNull, String... dateFormats) {
      return new CalendarConversion(Locale.getDefault(), dateIfNull, (String)null, dateFormats);
   }

   public static CalendarConversion toCalendar(Locale locale, Calendar dateIfNull, String stringIfNull, String... dateFormats) {
      return new CalendarConversion(TimeZone.getDefault(), locale, dateIfNull, stringIfNull, dateFormats);
   }

   public static CalendarConversion toCalendar(TimeZone timeZone, Locale locale, Calendar dateIfNull, String stringIfNull, String... dateFormats) {
      return new CalendarConversion(timeZone, locale, dateIfNull, stringIfNull, dateFormats);
   }

   public static CalendarConversion toCalendar(Calendar dateIfNull, String stringIfNull, String... dateFormats) {
      return new CalendarConversion(Locale.getDefault(), dateIfNull, stringIfNull, dateFormats);
   }

   public static ByteConversion toByte() {
      return new ByteConversion();
   }

   public static ShortConversion toShort() {
      return new ShortConversion();
   }

   public static IntegerConversion toInteger() {
      return new IntegerConversion();
   }

   public static LongConversion toLong() {
      return new LongConversion();
   }

   public static BigIntegerConversion toBigInteger() {
      return new BigIntegerConversion();
   }

   public static FloatConversion toFloat() {
      return new FloatConversion();
   }

   public static DoubleConversion toDouble() {
      return new DoubleConversion();
   }

   public static BigDecimalConversion toBigDecimal() {
      return new BigDecimalConversion();
   }

   public static NumericConversion formatToNumber(String... numberFormats) {
      return new NumericConversion(numberFormats) {
         protected void configureFormatter(DecimalFormat formatter) {
         }
      };
   }

   public static NumericConversion formatToNumber(Class numberType, String... numberFormats) {
      return new NumericConversion(numberFormats) {
         protected void configureFormatter(DecimalFormat formatter) {
         }
      };
   }

   public static FormattedBigDecimalConversion formatToBigDecimal(String... numberFormats) {
      return new FormattedBigDecimalConversion(numberFormats);
   }

   public static FormattedBigDecimalConversion formatToBigDecimal(BigDecimal defaultValueForNullString, String... numberFormats) {
      return new FormattedBigDecimalConversion(defaultValueForNullString, (String)null, numberFormats);
   }

   public static FormattedBigDecimalConversion formatToBigDecimal(BigDecimal defaultValueForNullString, String stringIfNull, String... numberFormats) {
      return new FormattedBigDecimalConversion(defaultValueForNullString, stringIfNull, numberFormats);
   }

   public static BooleanConversion toBoolean(Boolean defaultValueForNullString, String defaultValueForNullBoolean, String[] valuesForTrue, String[] valuesForFalse) {
      return new BooleanConversion(defaultValueForNullString, defaultValueForNullBoolean, valuesForTrue, valuesForFalse);
   }

   public static BooleanConversion toBoolean(Boolean defaultValueForNullString, String defaultValueForNullBoolean, String valueForTrue, String valueForFalse) {
      return new BooleanConversion(defaultValueForNullString, defaultValueForNullBoolean, new String[]{valueForTrue}, new String[]{valueForFalse});
   }

   public static BooleanConversion toBoolean(String[] valuesForTrue, String[] valuesForFalse) {
      return new BooleanConversion(valuesForTrue, valuesForFalse);
   }

   public static BooleanConversion toBoolean() {
      return toBoolean("true", "false");
   }

   public static BooleanConversion toBoolean(String valueForTrue, String valueForFalse) {
      return new BooleanConversion(new String[]{valueForTrue}, new String[]{valueForFalse});
   }

   public static CharacterConversion toChar() {
      return new CharacterConversion();
   }

   public static CharacterConversion toChar(Character defaultValueForNullString, String defaultValueForNullChar) {
      return new CharacterConversion(defaultValueForNullString, defaultValueForNullChar);
   }

   public static CharacterConversion toChar(Character defaultValueForNullString) {
      return new CharacterConversion(defaultValueForNullString, (String)null);
   }

   public static EnumConversion toEnum(Class enumType) {
      return new EnumConversion(enumType);
   }

   public static EnumConversion toEnum(Class enumType, EnumSelector... selectors) {
      return toEnum(enumType, (Enum)null, (String)null, (String)null, selectors);
   }

   public static EnumConversion toEnum(Class enumType, String customEnumElement, EnumSelector... selectors) {
      return toEnum(enumType, (Enum)null, (String)null, customEnumElement);
   }

   public static EnumConversion toEnum(Class enumType, Enum valueIfStringIsNull, String valueIfEnumIsNull, String customEnumElement, EnumSelector... selectors) {
      return new EnumConversion(enumType, valueIfStringIsNull, valueIfEnumIsNull, customEnumElement, selectors);
   }

   public static FormattedDateConversion toFormattedDate(String pattern) {
      return toFormattedDate(pattern, (Locale)null, (String)null);
   }

   public static FormattedDateConversion toFormattedDate(String pattern, String valueIfObjectIsNull) {
      return toFormattedDate(pattern, (Locale)null, valueIfObjectIsNull);
   }

   public static FormattedDateConversion toFormattedDate(String pattern, Locale locale) {
      return toFormattedDate(pattern, locale, (String)null);
   }

   public static FormattedDateConversion toFormattedDate(String pattern, Locale locale, String valueIfObjectIsNull) {
      return new FormattedDateConversion(pattern, locale, valueIfObjectIsNull);
   }

   public static ValidatedConversion notNull() {
      return validate(false, true, (String[])null, (String[])null);
   }

   public static ValidatedConversion notBlank() {
      return validate(false, false, (String[])null, (String[])null);
   }

   public static ValidatedConversion notBlank(String regexToMatch) {
      return validate(false, false, (String[])null, (String[])null, regexToMatch);
   }

   public static ValidatedConversion validate(boolean nullable, boolean allowBlanks) {
      return new ValidatedConversion(nullable, allowBlanks, (String[])null, (String[])null, (String)null);
   }

   public static ValidatedConversion validate(boolean nullable, boolean allowBlanks, String[] oneOf, String[] noneOf) {
      return new ValidatedConversion(nullable, allowBlanks, oneOf, noneOf, (String)null);
   }

   public static ValidatedConversion validate(boolean nullable, boolean allowBlanks, String regexToMatch) {
      return new ValidatedConversion(nullable, allowBlanks, (String[])null, (String[])null, regexToMatch);
   }

   public static ValidatedConversion validate(boolean nullable, boolean allowBlanks, String[] oneOf, String[] noneOf, String regexToMatch) {
      return new ValidatedConversion(nullable, allowBlanks, oneOf, noneOf, regexToMatch);
   }

   public static ValidatedConversion oneOf(String... oneOf) {
      return new ValidatedConversion(false, false, oneOf, (String[])null, (String)null);
   }

   public static ValidatedConversion noneOf(String... noneOf) {
      return new ValidatedConversion(false, false, (String[])null, noneOf, (String)null);
   }
}
