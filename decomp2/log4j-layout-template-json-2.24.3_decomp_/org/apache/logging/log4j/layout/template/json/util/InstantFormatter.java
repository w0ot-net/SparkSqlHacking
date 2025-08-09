package org.apache.logging.log4j.layout.template.json.util;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;
import java.util.TimeZone;
import org.apache.logging.log4j.core.time.Instant;
import org.apache.logging.log4j.core.time.MutableInstant;
import org.apache.logging.log4j.core.util.datetime.FastDateFormat;
import org.apache.logging.log4j.core.util.datetime.FixedDateFormat;
import org.apache.logging.log4j.status.StatusLogger;
import org.apache.logging.log4j.util.Strings;

public final class InstantFormatter {
   private static final StatusLogger LOGGER = StatusLogger.getLogger();
   private static final FormatterFactory[] FORMATTER_FACTORIES = new FormatterFactory[]{new Log4jFixedFormatterFactory(), new Log4jFastFormatterFactory(), new JavaDateTimeFormatterFactory()};
   private final Formatter formatter;

   private InstantFormatter(final Builder builder) {
      this.formatter = (Formatter)Arrays.stream(FORMATTER_FACTORIES).map((formatterFactory) -> {
         try {
            return formatterFactory.createIfSupported(builder.getPattern(), builder.getLocale(), builder.getTimeZone());
         } catch (Exception error) {
            LOGGER.warn("skipping the failed formatter factory \"{}\"", formatterFactory, error);
            return null;
         }
      }).filter(Objects::nonNull).findFirst().orElseThrow(() -> new AssertionError("could not find a matching formatter"));
   }

   public String format(final Instant instant) {
      Objects.requireNonNull(instant, "instant");
      StringBuilder stringBuilder = new StringBuilder();
      this.formatter.format(instant, stringBuilder);
      return stringBuilder.toString();
   }

   public void format(final Instant instant, final StringBuilder stringBuilder) {
      Objects.requireNonNull(instant, "instant");
      Objects.requireNonNull(stringBuilder, "stringBuilder");
      this.formatter.format(instant, stringBuilder);
   }

   public boolean isInstantMatching(final Instant instant1, final Instant instant2) {
      return this.formatter.isInstantMatching(instant1, instant2);
   }

   public Class getInternalImplementationClass() {
      return this.formatter.getInternalImplementationClass();
   }

   public static Builder newBuilder() {
      return new Builder();
   }

   private static boolean patternSupported(final String pattern, final Locale locale, final TimeZone timeZone, final Formatter formatter) {
      DateTimeFormatter javaFormatter = DateTimeFormatter.ofPattern(pattern).withLocale(locale).withZone(timeZone.toZoneId());
      MutableInstant instant = new MutableInstant();
      instant.initFromEpochSecond(1621280470L, 123456789);
      String expectedFormat = javaFormatter.format(instant);
      StringBuilder stringBuilder = new StringBuilder();
      formatter.format(instant, stringBuilder);
      String actualFormat = stringBuilder.toString();
      return expectedFormat.equals(actualFormat);
   }

   public static final class Builder {
      private String pattern;
      private Locale locale;
      private TimeZone timeZone;

      private Builder() {
         this.locale = Locale.getDefault();
         this.timeZone = TimeZone.getDefault();
      }

      public String getPattern() {
         return this.pattern;
      }

      public Builder setPattern(final String pattern) {
         this.pattern = pattern;
         return this;
      }

      public Locale getLocale() {
         return this.locale;
      }

      public Builder setLocale(final Locale locale) {
         this.locale = locale;
         return this;
      }

      public TimeZone getTimeZone() {
         return this.timeZone;
      }

      public Builder setTimeZone(final TimeZone timeZone) {
         this.timeZone = timeZone;
         return this;
      }

      public InstantFormatter build() {
         this.validate();
         return new InstantFormatter(this);
      }

      private void validate() {
         if (Strings.isBlank(this.pattern)) {
            throw new IllegalArgumentException("blank pattern");
         } else {
            Objects.requireNonNull(this.locale, "locale");
            Objects.requireNonNull(this.timeZone, "timeZone");
         }
      }
   }

   private static final class JavaDateTimeFormatterFactory implements FormatterFactory {
      private JavaDateTimeFormatterFactory() {
      }

      public Formatter createIfSupported(final String pattern, final Locale locale, final TimeZone timeZone) {
         return new JavaDateTimeFormatter(pattern, locale, timeZone);
      }
   }

   private static final class JavaDateTimeFormatter implements Formatter {
      private final DateTimeFormatter formatter;
      private final MutableInstant mutableInstant;

      private JavaDateTimeFormatter(final String pattern, final Locale locale, final TimeZone timeZone) {
         this.formatter = DateTimeFormatter.ofPattern(pattern).withLocale(locale).withZone(timeZone.toZoneId());
         this.mutableInstant = new MutableInstant();
      }

      public Class getInternalImplementationClass() {
         return DateTimeFormatter.class;
      }

      public void format(final Instant instant, final StringBuilder stringBuilder) {
         if (instant instanceof MutableInstant) {
            this.formatMutableInstant((MutableInstant)instant, stringBuilder);
         } else {
            this.formatInstant(instant, stringBuilder);
         }

      }

      private void formatMutableInstant(final MutableInstant instant, final StringBuilder stringBuilder) {
         this.formatter.formatTo(instant, stringBuilder);
      }

      private void formatInstant(final Instant instant, final StringBuilder stringBuilder) {
         this.mutableInstant.initFrom(instant);
         this.formatMutableInstant(this.mutableInstant, stringBuilder);
      }

      public boolean isInstantMatching(final Instant instant1, final Instant instant2) {
         return instant1.getEpochSecond() == instant2.getEpochSecond() && instant1.getNanoOfSecond() == instant2.getNanoOfSecond();
      }
   }

   private static final class Log4jFastFormatterFactory implements FormatterFactory {
      private Log4jFastFormatterFactory() {
      }

      public Formatter createIfSupported(final String pattern, final Locale locale, final TimeZone timeZone) {
         Log4jFastFormatter formatter = new Log4jFastFormatter(pattern, locale, timeZone);
         boolean patternSupported = InstantFormatter.patternSupported(pattern, locale, timeZone, formatter);
         return patternSupported ? formatter : null;
      }
   }

   private static final class Log4jFastFormatter implements Formatter {
      private final FastDateFormat formatter;
      private final Calendar calendar;

      private Log4jFastFormatter(final String pattern, final Locale locale, final TimeZone timeZone) {
         this.formatter = FastDateFormat.getInstance(pattern, timeZone, locale);
         this.calendar = Calendar.getInstance(timeZone, locale);
      }

      public Class getInternalImplementationClass() {
         return FastDateFormat.class;
      }

      public void format(final Instant instant, final StringBuilder stringBuilder) {
         this.calendar.setTimeInMillis(instant.getEpochMillisecond());
         this.formatter.format(this.calendar, stringBuilder);
      }

      public boolean isInstantMatching(final Instant instant1, final Instant instant2) {
         return instant1.getEpochMillisecond() == instant2.getEpochMillisecond();
      }
   }

   private static final class Log4jFixedFormatterFactory implements FormatterFactory {
      private Log4jFixedFormatterFactory() {
      }

      public Formatter createIfSupported(final String pattern, final Locale locale, final TimeZone timeZone) {
         FixedDateFormat internalFormatter = FixedDateFormat.createIfSupported(new String[]{pattern, timeZone.getID()});
         if (internalFormatter == null) {
            return null;
         } else {
            Log4jFixedFormatter formatter = new Log4jFixedFormatter(internalFormatter);
            boolean patternSupported = InstantFormatter.patternSupported(pattern, locale, timeZone, formatter);
            return patternSupported ? formatter : null;
         }
      }
   }

   private static final class Log4jFixedFormatter implements Formatter {
      private final FixedDateFormat formatter;
      private final char[] buffer;

      private Log4jFixedFormatter(final FixedDateFormat formatter) {
         this.formatter = formatter;
         this.buffer = new char[formatter.getLength() << 1];
      }

      public Class getInternalImplementationClass() {
         return FixedDateFormat.class;
      }

      public void format(final Instant instant, final StringBuilder stringBuilder) {
         int length = this.formatter.formatInstant(instant, this.buffer, 0);
         stringBuilder.append(this.buffer, 0, length);
      }

      public boolean isInstantMatching(final Instant instant1, final Instant instant2) {
         return this.formatter.isEquivalent(instant1.getEpochSecond(), instant1.getNanoOfSecond(), instant2.getEpochSecond(), instant2.getNanoOfSecond());
      }
   }

   private interface Formatter {
      Class getInternalImplementationClass();

      void format(Instant instant, StringBuilder stringBuilder);

      boolean isInstantMatching(Instant instant1, Instant instant2);
   }

   private interface FormatterFactory {
      Formatter createIfSupported(String pattern, Locale locale, TimeZone timeZone);
   }
}
