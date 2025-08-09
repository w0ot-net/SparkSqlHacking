package org.apache.logging.log4j.layout.template.json.resolver;

import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.time.Instant;
import org.apache.logging.log4j.core.time.MutableInstant;
import org.apache.logging.log4j.layout.template.json.JsonTemplateLayoutDefaults;
import org.apache.logging.log4j.layout.template.json.util.InstantFormatter;
import org.apache.logging.log4j.layout.template.json.util.JsonWriter;

public final class TimestampResolver implements EventResolver {
   private final EventResolver internalResolver;
   private static final EventResolver EPOCH_NANOS_RESOLVER = new EpochResolver() {
      void resolve(final Instant logEventInstant, final JsonWriter jsonWriter) {
         long nanos = TimestampResolver.epochNanos(logEventInstant);
         jsonWriter.writeNumber(nanos);
      }
   };
   private static final EventResolver EPOCH_MILLIS_RESOLVER = new EpochResolver() {
      void resolve(final Instant logEventInstant, final JsonWriter jsonWriter) {
         StringBuilder jsonWriterStringBuilder = jsonWriter.getStringBuilder();
         long nanos = TimestampResolver.epochNanos(logEventInstant);
         jsonWriterStringBuilder.append(nanos);
         jsonWriterStringBuilder.insert(jsonWriterStringBuilder.length() - 6, '.');
      }
   };
   private static final EventResolver EPOCH_MILLIS_ROUNDED_RESOLVER = new EpochResolver() {
      void resolve(final Instant logEventInstant, final JsonWriter jsonWriter) {
         jsonWriter.writeNumber(logEventInstant.getEpochMillisecond());
      }
   };
   private static final EventResolver EPOCH_MILLIS_NANOS_RESOLVER = new EpochResolver() {
      void resolve(final Instant logEventInstant, final JsonWriter jsonWriter) {
         long nanos = TimestampResolver.epochNanos(logEventInstant);
         long fraction = nanos % 1000000L;
         jsonWriter.writeNumber(fraction);
      }
   };
   private static final EventResolver EPOCH_SECS_RESOLVER = new EpochResolver() {
      void resolve(final Instant logEventInstant, final JsonWriter jsonWriter) {
         StringBuilder jsonWriterStringBuilder = jsonWriter.getStringBuilder();
         long nanos = TimestampResolver.epochNanos(logEventInstant);
         jsonWriterStringBuilder.append(nanos);
         jsonWriterStringBuilder.insert(jsonWriterStringBuilder.length() - 9, '.');
      }
   };
   private static final EventResolver EPOCH_SECS_ROUNDED_RESOLVER = new EpochResolver() {
      void resolve(final Instant logEventInstant, final JsonWriter jsonWriter) {
         jsonWriter.writeNumber(logEventInstant.getEpochSecond());
      }
   };
   private static final EventResolver EPOCH_SECS_NANOS_RESOLVER = new EpochResolver() {
      void resolve(final Instant logEventInstant, final JsonWriter jsonWriter) {
         jsonWriter.writeNumber(logEventInstant.getNanoOfSecond());
      }
   };

   TimestampResolver(final TemplateResolverConfig config) {
      this.internalResolver = createResolver(config);
   }

   private static EventResolver createResolver(final TemplateResolverConfig config) {
      boolean patternProvided = config.exists("pattern");
      boolean epochProvided = config.exists("epoch");
      if (patternProvided && epochProvided) {
         throw new IllegalArgumentException("conflicting configuration options are provided: " + config);
      } else {
         return epochProvided ? createEpochResolver(config) : createPatternResolver(config);
      }
   }

   private static EventResolver createPatternResolver(final TemplateResolverConfig config) {
      PatternResolverContext patternResolverContext = TimestampResolver.PatternResolverContext.fromConfig(config);
      return new PatternResolver(patternResolverContext);
   }

   private static EventResolver createEpochResolver(final TemplateResolverConfig config) {
      String unit = config.getString(new String[]{"epoch", "unit"});
      Boolean rounded = config.getBoolean(new String[]{"epoch", "rounded"});
      if ("nanos".equals(unit) && !Boolean.FALSE.equals(rounded)) {
         return EPOCH_NANOS_RESOLVER;
      } else if ("millis".equals(unit)) {
         return !Boolean.TRUE.equals(rounded) ? EPOCH_MILLIS_RESOLVER : EPOCH_MILLIS_ROUNDED_RESOLVER;
      } else if ("millis.nanos".equals(unit) && rounded == null) {
         return EPOCH_MILLIS_NANOS_RESOLVER;
      } else if ("secs".equals(unit)) {
         return !Boolean.TRUE.equals(rounded) ? EPOCH_SECS_RESOLVER : EPOCH_SECS_ROUNDED_RESOLVER;
      } else if ("secs.nanos".equals(unit) && rounded == null) {
         return EPOCH_SECS_NANOS_RESOLVER;
      } else {
         throw new IllegalArgumentException("invalid epoch configuration: " + config);
      }
   }

   private static long epochNanos(final Instant instant) {
      long nanos = Math.multiplyExact(1000000000L, instant.getEpochSecond());
      return Math.addExact(nanos, (long)instant.getNanoOfSecond());
   }

   static String getName() {
      return "timestamp";
   }

   public void resolve(final LogEvent logEvent, final JsonWriter jsonWriter) {
      this.internalResolver.resolve(logEvent, jsonWriter);
   }

   private static final class PatternResolverContext {
      private final InstantFormatter formatter;
      private final StringBuilder lastFormattedInstantBuffer = new StringBuilder();
      private final MutableInstant lastFormattedInstant = new MutableInstant();

      private PatternResolverContext(final String pattern, final TimeZone timeZone, final Locale locale) {
         this.formatter = InstantFormatter.newBuilder().setPattern(pattern).setTimeZone(timeZone).setLocale(locale).build();
         this.lastFormattedInstant.initFromEpochSecond(-1L, 0);
      }

      private static PatternResolverContext fromConfig(final TemplateResolverConfig config) {
         String pattern = readPattern(config);
         TimeZone timeZone = readTimeZone(config);
         Locale locale = config.getLocale(new String[]{"pattern", "locale"});
         return new PatternResolverContext(pattern, timeZone, locale);
      }

      private static String readPattern(final TemplateResolverConfig config) {
         String format = config.getString(new String[]{"pattern", "format"});
         return format != null ? format : JsonTemplateLayoutDefaults.getTimestampFormatPattern();
      }

      private static TimeZone readTimeZone(final TemplateResolverConfig config) {
         String timeZoneId = config.getString(new String[]{"pattern", "timeZone"});
         if (timeZoneId == null) {
            return JsonTemplateLayoutDefaults.getTimeZone();
         } else {
            boolean found = false;

            for(String availableTimeZone : TimeZone.getAvailableIDs()) {
               if (availableTimeZone.equalsIgnoreCase(timeZoneId)) {
                  found = true;
                  break;
               }
            }

            if (!found) {
               throw new IllegalArgumentException("invalid timestamp time zone: " + config);
            } else {
               return TimeZone.getTimeZone(timeZoneId);
            }
         }
      }
   }

   private static final class PatternResolver implements EventResolver {
      private final Lock lock;
      private final PatternResolverContext patternResolverContext;

      private PatternResolver(final PatternResolverContext patternResolverContext) {
         this.lock = new ReentrantLock();
         this.patternResolverContext = patternResolverContext;
      }

      public void resolve(final LogEvent logEvent, final JsonWriter jsonWriter) {
         this.lock.lock();

         try {
            this.unsynchronizedResolve(logEvent, jsonWriter);
         } finally {
            this.lock.unlock();
         }

      }

      private void unsynchronizedResolve(final LogEvent logEvent, final JsonWriter jsonWriter) {
         boolean instantMatching = this.patternResolverContext.formatter.isInstantMatching(this.patternResolverContext.lastFormattedInstant, logEvent.getInstant());
         if (!instantMatching) {
            this.patternResolverContext.lastFormattedInstantBuffer.setLength(0);
            this.patternResolverContext.lastFormattedInstant.initFrom(logEvent.getInstant());
            this.patternResolverContext.formatter.format(this.patternResolverContext.lastFormattedInstant, this.patternResolverContext.lastFormattedInstantBuffer);
            StringBuilder jsonWriterStringBuilder = jsonWriter.getStringBuilder();
            int startIndex = jsonWriterStringBuilder.length();
            jsonWriter.writeString((CharSequence)this.patternResolverContext.lastFormattedInstantBuffer);
            this.patternResolverContext.lastFormattedInstantBuffer.setLength(0);
            this.patternResolverContext.lastFormattedInstantBuffer.append(jsonWriterStringBuilder, startIndex, jsonWriterStringBuilder.length());
         } else {
            jsonWriter.writeRawString((CharSequence)this.patternResolverContext.lastFormattedInstantBuffer);
         }

      }
   }

   private static final class EpochResolutionRecord {
      private static final int MAX_LONG_LENGTH = String.valueOf(Long.MAX_VALUE).length();
      private final MutableInstant instant;
      private final char[] resolution;
      private int resolutionLength;

      private EpochResolutionRecord() {
         this.instant = new MutableInstant();
         this.resolution = new char[MAX_LONG_LENGTH + 1 + MAX_LONG_LENGTH];
         this.instant.initFromEpochSecond(-1L, 0);
      }
   }

   private abstract static class EpochResolver implements EventResolver {
      private final Lock lock;
      private final EpochResolutionRecord resolutionRecord;

      private EpochResolver() {
         this.lock = new ReentrantLock();
         this.resolutionRecord = new EpochResolutionRecord();
      }

      public void resolve(final LogEvent logEvent, final JsonWriter jsonWriter) {
         this.lock.lock();

         try {
            this.unsynchronizedResolve(logEvent, jsonWriter);
         } finally {
            this.lock.unlock();
         }

      }

      private void unsynchronizedResolve(final LogEvent logEvent, final JsonWriter jsonWriter) {
         Instant logEventInstant = logEvent.getInstant();
         if (logEventInstant.equals(this.resolutionRecord.instant)) {
            jsonWriter.writeRawString((char[])this.resolutionRecord.resolution, 0, this.resolutionRecord.resolutionLength);
         } else {
            this.resolutionRecord.instant.initFrom(logEventInstant);
            StringBuilder stringBuilder = jsonWriter.getStringBuilder();
            int startIndex = stringBuilder.length();
            this.resolve(logEventInstant, jsonWriter);
            this.resolutionRecord.resolutionLength = stringBuilder.length() - startIndex;
            stringBuilder.getChars(startIndex, stringBuilder.length(), this.resolutionRecord.resolution, 0);
         }

      }

      abstract void resolve(Instant logEventInstant, JsonWriter jsonWriter);
   }
}
