package org.apache.logging.log4j.core.pattern;

import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.time.Instant;
import org.apache.logging.log4j.core.time.MutableInstant;
import org.apache.logging.log4j.core.util.Constants;
import org.apache.logging.log4j.core.util.datetime.FastDateFormat;
import org.apache.logging.log4j.core.util.datetime.FixedDateFormat;
import org.apache.logging.log4j.util.PerformanceSensitive;

@Plugin(
   name = "DatePatternConverter",
   category = "Converter"
)
@ConverterKeys({"d", "date"})
@PerformanceSensitive({"allocation"})
public final class DatePatternConverter extends LogEventPatternConverter implements ArrayPatternConverter {
   private static final String UNIX_FORMAT = "UNIX";
   private static final String UNIX_MILLIS_FORMAT = "UNIX_MILLIS";
   private final String[] options;
   private final ThreadLocal threadLocalMutableInstant = new ThreadLocal();
   private final ThreadLocal threadLocalFormatter = new ThreadLocal();
   private final AtomicReference cachedTime;
   private final Formatter formatter;

   private DatePatternConverter(final String[] options) {
      super("Date", "date");
      this.options = options == null ? null : (String[])Arrays.copyOf(options, options.length);
      this.formatter = this.createFormatter(options);
      this.cachedTime = new AtomicReference(this.fromEpochMillis(System.currentTimeMillis()));
   }

   private CachedTime fromEpochMillis(final long epochMillis) {
      MutableInstant temp = new MutableInstant();
      temp.initFromEpochMilli(epochMillis, 0);
      return new CachedTime(temp);
   }

   private Formatter createFormatter(final String[] options) {
      FixedDateFormat fixedDateFormat = FixedDateFormat.createIfSupported(options);
      return fixedDateFormat != null ? createFixedFormatter(fixedDateFormat) : createNonFixedFormatter(options);
   }

   public static DatePatternConverter newInstance(final String[] options) {
      return new DatePatternConverter(options);
   }

   private static Formatter createFixedFormatter(final FixedDateFormat fixedDateFormat) {
      return new FixedFormatter(fixedDateFormat);
   }

   private static Formatter createNonFixedFormatter(final String[] options) {
      Objects.requireNonNull(options);
      if (options.length == 0) {
         throw new IllegalArgumentException("Options array must have at least one element");
      } else {
         Objects.requireNonNull(options[0]);
         String patternOption = options[0];
         if ("UNIX".equals(patternOption)) {
            return new UnixFormatter();
         } else if ("UNIX_MILLIS".equals(patternOption)) {
            return new UnixMillisFormatter();
         } else {
            FixedDateFormat.FixedFormat fixedFormat = FixedDateFormat.FixedFormat.lookup(patternOption);
            String pattern = fixedFormat == null ? patternOption : fixedFormat.getPattern();
            TimeZone tz = null;
            if (options.length > 1 && options[1] != null) {
               tz = TimeZone.getTimeZone(options[1]);
            }

            Locale locale = null;
            if (options.length > 2 && options[2] != null) {
               locale = Locale.forLanguageTag(options[2]);
            }

            try {
               FastDateFormat tempFormat = FastDateFormat.getInstance(pattern, tz, locale);
               return new PatternFormatter(tempFormat);
            } catch (IllegalArgumentException e) {
               LOGGER.warn("Could not instantiate FastDateFormat with pattern " + pattern, e);
               return createFixedFormatter(FixedDateFormat.create(FixedDateFormat.FixedFormat.DEFAULT, tz));
            }
         }
      }
   }

   public void format(final Date date, final StringBuilder toAppendTo) {
      this.format(date.getTime(), toAppendTo);
   }

   public void format(final LogEvent event, final StringBuilder output) {
      this.format(event.getInstant(), output);
   }

   public void format(final long epochMilli, final StringBuilder output) {
      MutableInstant instant = this.getMutableInstant();
      instant.initFromEpochMilli(epochMilli, 0);
      this.format((Instant)instant, (StringBuilder)output);
   }

   private MutableInstant getMutableInstant() {
      if (Constants.ENABLE_THREADLOCALS) {
         MutableInstant result = (MutableInstant)this.threadLocalMutableInstant.get();
         if (result == null) {
            result = new MutableInstant();
            this.threadLocalMutableInstant.set(result);
         }

         return result;
      } else {
         return new MutableInstant();
      }
   }

   public void format(final Instant instant, final StringBuilder output) {
      if (Constants.ENABLE_THREADLOCALS) {
         this.formatWithoutAllocation(instant, output);
      } else {
         this.formatWithoutThreadLocals(instant, output);
      }

   }

   private void formatWithoutAllocation(final Instant instant, final StringBuilder output) {
      this.getThreadLocalFormatter().formatToBuffer(instant, output);
   }

   private Formatter getThreadLocalFormatter() {
      Formatter result = (Formatter)this.threadLocalFormatter.get();
      if (result == null) {
         result = this.createFormatter(this.options);
         this.threadLocalFormatter.set(result);
      }

      return result;
   }

   private void formatWithoutThreadLocals(final Instant instant, final StringBuilder output) {
      CachedTime cached = (CachedTime)this.cachedTime.get();
      CachedTime effective;
      if (instant.getEpochSecond() == cached.epochSecond && instant.getNanoOfSecond() == cached.nanoOfSecond) {
         effective = cached;
      } else {
         effective = new CachedTime(instant);
         this.cachedTime.compareAndSet(cached, effective);
      }

      output.append(effective.formatted);
   }

   public void format(final Object obj, final StringBuilder output) {
      if (obj instanceof Date) {
         this.format((Date)obj, output);
      }

      super.format(obj, output);
   }

   public void format(final StringBuilder toAppendTo, final Object... objects) {
      for(Object obj : objects) {
         if (obj instanceof Date) {
            this.format(obj, toAppendTo);
            break;
         }
      }

   }

   public String getPattern() {
      return this.formatter.toPattern();
   }

   public TimeZone getTimeZone() {
      return this.formatter.getTimeZone();
   }

   private abstract static class Formatter {
      long previousTime;
      int nanos;

      private Formatter() {
      }

      abstract String format(final Instant instant);

      abstract void formatToBuffer(final Instant instant, StringBuilder destination);

      public String toPattern() {
         return null;
      }

      public TimeZone getTimeZone() {
         return TimeZone.getDefault();
      }
   }

   private static final class PatternFormatter extends Formatter {
      private final FastDateFormat fastDateFormat;
      private final StringBuilder cachedBuffer = new StringBuilder(64);

      PatternFormatter(final FastDateFormat fastDateFormat) {
         this.fastDateFormat = fastDateFormat;
      }

      String format(final Instant instant) {
         return this.fastDateFormat.format(instant.getEpochMillisecond());
      }

      void formatToBuffer(final Instant instant, final StringBuilder destination) {
         long timeMillis = instant.getEpochMillisecond();
         if (this.previousTime != timeMillis) {
            this.cachedBuffer.setLength(0);
            this.fastDateFormat.format(timeMillis, this.cachedBuffer);
         }

         destination.append(this.cachedBuffer);
      }

      public String toPattern() {
         return this.fastDateFormat.getPattern();
      }

      public TimeZone getTimeZone() {
         return this.fastDateFormat.getTimeZone();
      }
   }

   private static final class FixedFormatter extends Formatter {
      private final FixedDateFormat fixedDateFormat;
      private final char[] cachedBuffer = new char[70];
      private int length = 0;

      FixedFormatter(final FixedDateFormat fixedDateFormat) {
         this.fixedDateFormat = fixedDateFormat;
      }

      String format(final Instant instant) {
         return this.fixedDateFormat.formatInstant(instant);
      }

      void formatToBuffer(final Instant instant, final StringBuilder destination) {
         long epochSecond = instant.getEpochSecond();
         int nanoOfSecond = instant.getNanoOfSecond();
         if (!this.fixedDateFormat.isEquivalent(this.previousTime, this.nanos, epochSecond, nanoOfSecond)) {
            this.length = this.fixedDateFormat.formatInstant(instant, this.cachedBuffer, 0);
            this.previousTime = epochSecond;
            this.nanos = nanoOfSecond;
         }

         destination.append(this.cachedBuffer, 0, this.length);
      }

      public String toPattern() {
         return this.fixedDateFormat.getFormat();
      }

      public TimeZone getTimeZone() {
         return this.fixedDateFormat.getTimeZone();
      }
   }

   private static final class UnixFormatter extends Formatter {
      private UnixFormatter() {
      }

      String format(final Instant instant) {
         return Long.toString(instant.getEpochSecond());
      }

      void formatToBuffer(final Instant instant, final StringBuilder destination) {
         destination.append(instant.getEpochSecond());
      }
   }

   private static final class UnixMillisFormatter extends Formatter {
      private UnixMillisFormatter() {
      }

      String format(final Instant instant) {
         return Long.toString(instant.getEpochMillisecond());
      }

      void formatToBuffer(final Instant instant, final StringBuilder destination) {
         destination.append(instant.getEpochMillisecond());
      }
   }

   private final class CachedTime {
      public long epochSecond;
      public int nanoOfSecond;
      public String formatted;

      public CachedTime(final Instant instant) {
         this.epochSecond = instant.getEpochSecond();
         this.nanoOfSecond = instant.getNanoOfSecond();
         this.formatted = DatePatternConverter.this.formatter.format(instant);
      }
   }
}
