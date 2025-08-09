package org.threeten.extra;

import java.time.Duration;
import java.time.Period;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public final class AmountFormats {
   private static final int DAYS_PER_WEEK = 7;
   private static final int HOURS_PER_DAY = 24;
   private static final int MINUTES_PER_HOUR = 60;
   private static final int SECONDS_PER_MINUTE = 60;
   private static final int NANOS_PER_MILLIS = 1000000;
   private static final String BUNDLE_NAME = "org.threeten.extra.wordbased";
   private static final Pattern SPLITTER = Pattern.compile("[|][|][|]");
   private static final String WORDBASED_COMMASPACE = "WordBased.commaspace";
   private static final String WORDBASED_SPACEANDSPACE = "WordBased.spaceandspace";
   private static final String WORDBASED_YEAR = "WordBased.year";
   private static final String WORDBASED_MONTH = "WordBased.month";
   private static final String WORDBASED_WEEK = "WordBased.week";
   private static final String WORDBASED_DAY = "WordBased.day";
   private static final String WORDBASED_HOUR = "WordBased.hour";
   private static final String WORDBASED_MINUTE = "WordBased.minute";
   private static final String WORDBASED_SECOND = "WordBased.second";
   private static final String WORDBASED_MILLISECOND = "WordBased.millisecond";
   private static final IntPredicate PREDICATE_1 = (value) -> value == 1 || value == -1;
   private static final IntPredicate PREDICATE_END1_NOT11 = (value) -> {
      int abs = Math.abs(value);
      int last = abs % 10;
      int secondLast = abs % 100 / 10;
      return last == 1 && secondLast != 1;
   };
   private static final IntPredicate PREDICATE_END234_NOTTEENS = (value) -> {
      int abs = Math.abs(value);
      int last = abs % 10;
      int secondLast = abs % 100 / 10;
      return last >= 2 && last <= 4 && secondLast != 1;
   };
   private static final List DURATION_UNITS = Arrays.asList(new DurationUnit("ns", Duration.ofNanos(1L)), new DurationUnit("µs", Duration.ofNanos(1000L)), new DurationUnit("μs", Duration.ofNanos(1000L)), new DurationUnit("us", Duration.ofNanos(1000L)), new DurationUnit("ms", Duration.ofMillis(1L)), new DurationUnit("s", Duration.ofSeconds(1L)), new DurationUnit("m", Duration.ofMinutes(1L)), new DurationUnit("h", Duration.ofHours(1L)));
   private static final FractionScalarPart EMPTY_FRACTION = new FractionScalarPart(0L, 0L);

   public static String iso8601(Period period, Duration duration) {
      Objects.requireNonNull(period, "period must not be null");
      Objects.requireNonNull(duration, "duration must not be null");
      if (period.isZero()) {
         return duration.toString();
      } else {
         return duration.isZero() ? period.toString() : period.toString() + duration.toString().substring(1);
      }
   }

   public static String wordBased(Period period, Locale locale) {
      Objects.requireNonNull(period, "period must not be null");
      Objects.requireNonNull(locale, "locale must not be null");
      ResourceBundle bundle = ResourceBundle.getBundle("org.threeten.extra.wordbased", locale);
      UnitFormat[] formats = new UnitFormat[]{AmountFormats.UnitFormat.of(bundle, "WordBased.year"), AmountFormats.UnitFormat.of(bundle, "WordBased.month"), AmountFormats.UnitFormat.of(bundle, "WordBased.week"), AmountFormats.UnitFormat.of(bundle, "WordBased.day")};
      WordBased wb = new WordBased(formats, bundle.getString("WordBased.commaspace"), bundle.getString("WordBased.spaceandspace"));
      Period normPeriod = oppositeSigns(period.getMonths(), period.getYears()) ? period.normalized() : period;
      int weeks = 0;
      int days = 0;
      if (normPeriod.getDays() % 7 == 0) {
         weeks = normPeriod.getDays() / 7;
      } else {
         days = normPeriod.getDays();
      }

      int[] values = new int[]{normPeriod.getYears(), normPeriod.getMonths(), weeks, days};
      return wb.format(values);
   }

   public static String wordBased(Duration duration, Locale locale) {
      Objects.requireNonNull(duration, "duration must not be null");
      Objects.requireNonNull(locale, "locale must not be null");
      ResourceBundle bundle = ResourceBundle.getBundle("org.threeten.extra.wordbased", locale);
      UnitFormat[] formats = new UnitFormat[]{AmountFormats.UnitFormat.of(bundle, "WordBased.hour"), AmountFormats.UnitFormat.of(bundle, "WordBased.minute"), AmountFormats.UnitFormat.of(bundle, "WordBased.second"), AmountFormats.UnitFormat.of(bundle, "WordBased.millisecond")};
      WordBased wb = new WordBased(formats, bundle.getString("WordBased.commaspace"), bundle.getString("WordBased.spaceandspace"));
      long hours = duration.toHours();
      long mins = duration.toMinutes() % 60L;
      long secs = duration.getSeconds() % 60L;
      int millis = duration.getNano() / 1000000;
      int[] values = new int[]{(int)hours, (int)mins, (int)secs, millis};
      return wb.format(values);
   }

   public static String wordBased(Period period, Duration duration, Locale locale) {
      Objects.requireNonNull(period, "period must not be null");
      Objects.requireNonNull(duration, "duration must not be null");
      Objects.requireNonNull(locale, "locale must not be null");
      ResourceBundle bundle = ResourceBundle.getBundle("org.threeten.extra.wordbased", locale);
      UnitFormat[] formats = new UnitFormat[]{AmountFormats.UnitFormat.of(bundle, "WordBased.year"), AmountFormats.UnitFormat.of(bundle, "WordBased.month"), AmountFormats.UnitFormat.of(bundle, "WordBased.week"), AmountFormats.UnitFormat.of(bundle, "WordBased.day"), AmountFormats.UnitFormat.of(bundle, "WordBased.hour"), AmountFormats.UnitFormat.of(bundle, "WordBased.minute"), AmountFormats.UnitFormat.of(bundle, "WordBased.second"), AmountFormats.UnitFormat.of(bundle, "WordBased.millisecond")};
      WordBased wb = new WordBased(formats, bundle.getString("WordBased.commaspace"), bundle.getString("WordBased.spaceandspace"));
      Period normPeriod = oppositeSigns(period.getMonths(), period.getYears()) ? period.normalized() : period;
      int weeks = 0;
      int days = 0;
      if (normPeriod.getDays() % 7 == 0) {
         weeks = normPeriod.getDays() / 7;
      } else {
         days = normPeriod.getDays();
      }

      long totalHours = duration.toHours();
      days += (int)(totalHours / 24L);
      int hours = (int)(totalHours % 24L);
      int mins = (int)(duration.toMinutes() % 60L);
      int secs = (int)(duration.getSeconds() % 60L);
      int millis = duration.getNano() / 1000000;
      int[] values = new int[]{normPeriod.getYears(), normPeriod.getMonths(), weeks, days, hours, mins, secs, millis};
      return wb.format(values);
   }

   private static boolean oppositeSigns(int a, int b) {
      return a < 0 ? b >= 0 : b < 0;
   }

   public static Duration parseUnitBasedDuration(CharSequence durationText) {
      Objects.requireNonNull(durationText, "durationText must not be null");
      int offset = 0;
      CharSequence original = durationText;
      int sign = 1;
      Optional<CharSequence> updatedText = consumePrefix(durationText, '-');
      if (updatedText.isPresent()) {
         sign = -1;
         ++offset;
         durationText = (CharSequence)updatedText.get();
      } else {
         updatedText = consumePrefix(durationText, '+');
         if (updatedText.isPresent()) {
            ++offset;
         }

         durationText = (CharSequence)updatedText.orElse(durationText);
      }

      if (durationText.equals("0")) {
         return Duration.ZERO;
      } else if (durationText.length() == 0) {
         throw new DateTimeParseException("Not a numeric value", durationText, 0);
      } else {
         Duration value = Duration.ZERO;

         CharSequence remainingText;
         for(int durationTextLength = durationText.length(); durationTextLength > 0; durationTextLength = remainingText.length()) {
            ParsedUnitPart integerPart = consumeDurationLeadingInt(durationText, original, offset);
            offset += durationText.length() - integerPart.remainingText().length();
            durationText = integerPart.remainingText();
            DurationScalar leadingInt = integerPart;
            DurationScalar fraction = EMPTY_FRACTION;
            Optional<CharSequence> dot = consumePrefix(durationText, '.');
            if (dot.isPresent()) {
               ++offset;
               durationText = (CharSequence)dot.get();
               ParsedUnitPart fractionPart = consumeDurationFraction(durationText, original, offset);
               offset += durationText.length() - fractionPart.remainingText().length();
               durationText = fractionPart.remainingText();
               fraction = fractionPart;
            }

            Optional<DurationUnit> optUnit = findUnit(durationText);
            if (!optUnit.isPresent()) {
               throw new DateTimeParseException("Invalid duration unit", original, offset);
            }

            DurationUnit unit = (DurationUnit)optUnit.get();

            try {
               Duration unitValue = leadingInt.applyTo(unit);
               Duration fractionValue = fraction.applyTo(unit);
               unitValue = unitValue.plus(fractionValue);
               value = value.plus(unitValue);
            } catch (ArithmeticException e) {
               throw new DateTimeParseException("Duration string exceeds valid numeric range", original, offset, e);
            }

            remainingText = unit.consumeDurationUnit(durationText);
            offset += durationText.length() - remainingText.length();
            durationText = remainingText;
         }

         return sign < 0 ? value.negated() : value;
      }
   }

   private static ParsedUnitPart consumeDurationLeadingInt(CharSequence text, CharSequence original, int offset) {
      long integerPart = 0L;
      int i = 0;

      for(int valueLength = text.length(); i < valueLength; ++i) {
         char c = text.charAt(i);
         if (c < '0' || c > '9') {
            break;
         }

         if (integerPart > 922337203685477580L) {
            throw new DateTimeParseException("Duration string exceeds valid numeric range", original, i + offset);
         }

         integerPart *= 10L;
         integerPart += (long)(c - 48);
         if (integerPart < 0L) {
            throw new DateTimeParseException("Duration string exceeds valid numeric range", original, i + offset);
         }
      }

      if (i == 0) {
         throw new DateTimeParseException("Missing leading integer", original, offset);
      } else {
         return new ParsedUnitPart(text.subSequence(i, text.length()), new IntegerScalarPart(integerPart));
      }
   }

   private static ParsedUnitPart consumeDurationFraction(CharSequence text, CharSequence original, int offset) {
      int i = 0;
      long fraction = 0L;
      long scale = 1L;

      for(boolean overflow = false; i < text.length(); ++i) {
         char c = text.charAt(i);
         if (c < '0' || c > '9') {
            break;
         }

         if (!overflow && fraction <= 922337203685477580L) {
            long tmp = fraction * 10L + (long)(c - 48);
            if (tmp < 0L) {
               overflow = true;
            } else {
               fraction = tmp;
               scale *= 10L;
            }
         }
      }

      if (i == 0) {
         throw new DateTimeParseException("Missing numeric fraction after '.'", original, offset);
      } else {
         return new ParsedUnitPart(text.subSequence(i, text.length()), new FractionScalarPart(fraction, scale));
      }
   }

   private static Optional findUnit(CharSequence text) {
      return ((Stream)DURATION_UNITS.stream().sequential()).filter((du) -> du.prefixMatchesUnit(text)).findFirst();
   }

   private static Optional consumePrefix(CharSequence text, char prefix) {
      return text.length() > 0 && text.charAt(0) == prefix ? Optional.of(text.subSequence(1, text.length())) : Optional.empty();
   }

   private AmountFormats() {
   }

   static final class WordBased {
      private final UnitFormat[] units;
      private final String separator;
      private final String lastSeparator;

      public WordBased(UnitFormat[] units, String separator, String lastSeparator) {
         this.units = units;
         this.separator = separator;
         this.lastSeparator = lastSeparator;
      }

      String format(int[] values) {
         StringBuilder buf = new StringBuilder(32);
         int nonZeroCount = 0;

         for(int i = 0; i < values.length; ++i) {
            if (values[i] != 0) {
               ++nonZeroCount;
            }
         }

         int count = 0;

         for(int i = 0; i < values.length; ++i) {
            if (values[i] != 0 || count == 0 && i == values.length - 1) {
               this.units[i].formatTo(values[i], buf);
               if (count < nonZeroCount - 2) {
                  buf.append(this.separator);
               } else if (count == nonZeroCount - 2) {
                  buf.append(this.lastSeparator);
               }

               ++count;
            }
         }

         return buf.toString();
      }
   }

   interface UnitFormat {
      static UnitFormat of(ResourceBundle bundle, String keyStem) {
         if (bundle.containsKey(keyStem + "s.predicates")) {
            String predicateList = bundle.getString(keyStem + "s.predicates");
            String textList = bundle.getString(keyStem + "s.list");
            String[] regexes = AmountFormats.SPLITTER.split(predicateList);
            String[] text = AmountFormats.SPLITTER.split(textList);
            return new PredicateFormat(regexes, text);
         } else {
            String single = bundle.getString(keyStem);
            String plural = bundle.getString(keyStem + "s");
            return new SinglePluralFormat(single, plural);
         }
      }

      void formatTo(int var1, StringBuilder var2);
   }

   static final class SinglePluralFormat implements UnitFormat {
      private final String single;
      private final String plural;

      SinglePluralFormat(String single, String plural) {
         this.single = single;
         this.plural = plural;
      }

      public void formatTo(int value, StringBuilder buf) {
         buf.append(value).append(value != 1 && value != -1 ? this.plural : this.single);
      }
   }

   static final class PredicateFormat implements UnitFormat {
      private final IntPredicate[] predicates;
      private final String[] text;

      PredicateFormat(String[] predicateStrs, String[] text) {
         if (predicateStrs.length + 1 != text.length) {
            throw new IllegalStateException("Invalid word-based resource");
         } else {
            this.predicates = (IntPredicate[])Stream.of(predicateStrs).map((predicateStr) -> this.findPredicate(predicateStr)).toArray((x$0) -> new IntPredicate[x$0]);
            this.text = text;
         }
      }

      private IntPredicate findPredicate(String predicateStr) {
         switch (predicateStr) {
            case "One":
               return AmountFormats.PREDICATE_1;
            case "End234NotTeens":
               return AmountFormats.PREDICATE_END234_NOTTEENS;
            case "End1Not11":
               return AmountFormats.PREDICATE_END1_NOT11;
            default:
               throw new IllegalStateException("Invalid word-based resource");
         }
      }

      public void formatTo(int value, StringBuilder buf) {
         for(int i = 0; i < this.predicates.length; ++i) {
            if (this.predicates[i].test(value)) {
               buf.append(value).append(this.text[i]);
               return;
            }
         }

         buf.append(value).append(this.text[this.predicates.length]);
      }
   }

   static final class DurationUnit {
      private final String abbrev;
      private final Duration value;

      private DurationUnit(String abbrev, Duration value) {
         this.abbrev = abbrev;
         this.value = value;
      }

      boolean prefixMatchesUnit(CharSequence text) {
         return text.length() >= this.abbrev.length() && this.abbrev.equals(text.subSequence(0, this.abbrev.length()));
      }

      CharSequence consumeDurationUnit(CharSequence text) {
         return text.subSequence(this.abbrev.length(), text.length());
      }

      Duration scaleBy(Function scaleFunc) {
         return (Duration)scaleFunc.apply(this.value);
      }
   }

   static final class ParsedUnitPart implements DurationScalar {
      private final CharSequence remainingText;
      private final DurationScalar scalar;

      private ParsedUnitPart(CharSequence remainingText, DurationScalar scalar) {
         this.remainingText = remainingText;
         this.scalar = scalar;
      }

      public Duration applyTo(DurationUnit unit) {
         return this.scalar.applyTo(unit);
      }

      CharSequence remainingText() {
         return this.remainingText;
      }
   }

   static final class IntegerScalarPart implements DurationScalar {
      private final long value;

      private IntegerScalarPart(long value) {
         this.value = value;
      }

      public Duration applyTo(DurationUnit unit) {
         return unit.scaleBy((d) -> d.multipliedBy(this.value));
      }
   }

   static final class FractionScalarPart implements DurationScalar {
      private final long value;
      private final long scale;

      private FractionScalarPart(long value, long scale) {
         this.value = value;
         this.scale = scale;
      }

      public Duration applyTo(DurationUnit unit) {
         return this.value == 0L ? Duration.ZERO : unit.scaleBy((d) -> d.multipliedBy(this.value).dividedBy(this.scale));
      }
   }

   interface DurationScalar {
      Duration applyTo(DurationUnit var1);
   }
}
