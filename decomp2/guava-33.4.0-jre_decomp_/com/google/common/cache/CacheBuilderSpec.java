package com.google.common.cache;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import javax.annotation.CheckForNull;
import org.checkerframework.checker.nullness.qual.Nullable;

@ElementTypesAreNonnullByDefault
@GwtIncompatible
public final class CacheBuilderSpec {
   private static final Splitter KEYS_SPLITTER = Splitter.on(',').trimResults();
   private static final Splitter KEY_VALUE_SPLITTER = Splitter.on('=').trimResults();
   private static final ImmutableMap VALUE_PARSERS;
   @CheckForNull
   @VisibleForTesting
   Integer initialCapacity;
   @CheckForNull
   @VisibleForTesting
   Long maximumSize;
   @CheckForNull
   @VisibleForTesting
   Long maximumWeight;
   @CheckForNull
   @VisibleForTesting
   Integer concurrencyLevel;
   @CheckForNull
   @VisibleForTesting
   LocalCache.Strength keyStrength;
   @CheckForNull
   @VisibleForTesting
   LocalCache.Strength valueStrength;
   @CheckForNull
   @VisibleForTesting
   Boolean recordStats;
   @VisibleForTesting
   long writeExpirationDuration;
   @CheckForNull
   @VisibleForTesting
   TimeUnit writeExpirationTimeUnit;
   @VisibleForTesting
   long accessExpirationDuration;
   @CheckForNull
   @VisibleForTesting
   TimeUnit accessExpirationTimeUnit;
   @VisibleForTesting
   long refreshDuration;
   @CheckForNull
   @VisibleForTesting
   TimeUnit refreshTimeUnit;
   private final String specification;

   private CacheBuilderSpec(String specification) {
      this.specification = specification;
   }

   public static CacheBuilderSpec parse(String cacheBuilderSpecification) {
      CacheBuilderSpec spec = new CacheBuilderSpec(cacheBuilderSpecification);
      if (!cacheBuilderSpecification.isEmpty()) {
         for(String keyValuePair : KEYS_SPLITTER.split(cacheBuilderSpecification)) {
            List<String> keyAndValue = ImmutableList.copyOf(KEY_VALUE_SPLITTER.split(keyValuePair));
            Preconditions.checkArgument(!keyAndValue.isEmpty(), "blank key-value pair");
            Preconditions.checkArgument(keyAndValue.size() <= 2, "key-value pair %s with more than one equals sign", (Object)keyValuePair);
            String key = (String)keyAndValue.get(0);
            ValueParser valueParser = (ValueParser)VALUE_PARSERS.get(key);
            Preconditions.checkArgument(valueParser != null, "unknown key %s", (Object)key);
            String value = keyAndValue.size() == 1 ? null : (String)keyAndValue.get(1);
            valueParser.parse(spec, key, value);
         }
      }

      return spec;
   }

   public static CacheBuilderSpec disableCaching() {
      return parse("maximumSize=0");
   }

   CacheBuilder toCacheBuilder() {
      CacheBuilder<Object, Object> builder = CacheBuilder.newBuilder();
      if (this.initialCapacity != null) {
         builder.initialCapacity(this.initialCapacity);
      }

      if (this.maximumSize != null) {
         builder.maximumSize(this.maximumSize);
      }

      if (this.maximumWeight != null) {
         builder.maximumWeight(this.maximumWeight);
      }

      if (this.concurrencyLevel != null) {
         builder.concurrencyLevel(this.concurrencyLevel);
      }

      if (this.keyStrength != null) {
         switch (this.keyStrength) {
            case WEAK:
               builder.weakKeys();
               break;
            default:
               throw new AssertionError();
         }
      }

      if (this.valueStrength != null) {
         switch (this.valueStrength) {
            case WEAK:
               builder.weakValues();
               break;
            case SOFT:
               builder.softValues();
               break;
            default:
               throw new AssertionError();
         }
      }

      if (this.recordStats != null && this.recordStats) {
         builder.recordStats();
      }

      if (this.writeExpirationTimeUnit != null) {
         builder.expireAfterWrite(this.writeExpirationDuration, this.writeExpirationTimeUnit);
      }

      if (this.accessExpirationTimeUnit != null) {
         builder.expireAfterAccess(this.accessExpirationDuration, this.accessExpirationTimeUnit);
      }

      if (this.refreshTimeUnit != null) {
         builder.refreshAfterWrite(this.refreshDuration, this.refreshTimeUnit);
      }

      return builder;
   }

   public String toParsableString() {
      return this.specification;
   }

   public String toString() {
      return MoreObjects.toStringHelper((Object)this).addValue(this.toParsableString()).toString();
   }

   public int hashCode() {
      return Objects.hashCode(this.initialCapacity, this.maximumSize, this.maximumWeight, this.concurrencyLevel, this.keyStrength, this.valueStrength, this.recordStats, durationInNanos(this.writeExpirationDuration, this.writeExpirationTimeUnit), durationInNanos(this.accessExpirationDuration, this.accessExpirationTimeUnit), durationInNanos(this.refreshDuration, this.refreshTimeUnit));
   }

   public boolean equals(@CheckForNull Object obj) {
      if (this == obj) {
         return true;
      } else if (!(obj instanceof CacheBuilderSpec)) {
         return false;
      } else {
         CacheBuilderSpec that = (CacheBuilderSpec)obj;
         return Objects.equal(this.initialCapacity, that.initialCapacity) && Objects.equal(this.maximumSize, that.maximumSize) && Objects.equal(this.maximumWeight, that.maximumWeight) && Objects.equal(this.concurrencyLevel, that.concurrencyLevel) && Objects.equal(this.keyStrength, that.keyStrength) && Objects.equal(this.valueStrength, that.valueStrength) && Objects.equal(this.recordStats, that.recordStats) && Objects.equal(durationInNanos(this.writeExpirationDuration, this.writeExpirationTimeUnit), durationInNanos(that.writeExpirationDuration, that.writeExpirationTimeUnit)) && Objects.equal(durationInNanos(this.accessExpirationDuration, this.accessExpirationTimeUnit), durationInNanos(that.accessExpirationDuration, that.accessExpirationTimeUnit)) && Objects.equal(durationInNanos(this.refreshDuration, this.refreshTimeUnit), durationInNanos(that.refreshDuration, that.refreshTimeUnit));
      }
   }

   @CheckForNull
   private static Long durationInNanos(long duration, @CheckForNull TimeUnit unit) {
      return unit == null ? null : unit.toNanos(duration);
   }

   private static String format(String format, Object... args) {
      return String.format(Locale.ROOT, format, args);
   }

   static {
      VALUE_PARSERS = ImmutableMap.builder().put("initialCapacity", new InitialCapacityParser()).put("maximumSize", new MaximumSizeParser()).put("maximumWeight", new MaximumWeightParser()).put("concurrencyLevel", new ConcurrencyLevelParser()).put("weakKeys", new KeyStrengthParser(LocalCache.Strength.WEAK)).put("softValues", new ValueStrengthParser(LocalCache.Strength.SOFT)).put("weakValues", new ValueStrengthParser(LocalCache.Strength.WEAK)).put("recordStats", new RecordStatsParser()).put("expireAfterAccess", new AccessDurationParser()).put("expireAfterWrite", new WriteDurationParser()).put("refreshAfterWrite", new RefreshDurationParser()).put("refreshInterval", new RefreshDurationParser()).buildOrThrow();
   }

   abstract static class IntegerParser implements ValueParser {
      protected abstract void parseInteger(CacheBuilderSpec spec, int value);

      public void parse(CacheBuilderSpec spec, String key, @Nullable String value) {
         if (Strings.isNullOrEmpty(value)) {
            throw new IllegalArgumentException("value of key " + key + " omitted");
         } else {
            try {
               this.parseInteger(spec, Integer.parseInt(value));
            } catch (NumberFormatException e) {
               throw new IllegalArgumentException(CacheBuilderSpec.format("key %s value set to %s, must be integer", key, value), e);
            }
         }
      }
   }

   abstract static class LongParser implements ValueParser {
      protected abstract void parseLong(CacheBuilderSpec spec, long value);

      public void parse(CacheBuilderSpec spec, String key, @Nullable String value) {
         if (Strings.isNullOrEmpty(value)) {
            throw new IllegalArgumentException("value of key " + key + " omitted");
         } else {
            try {
               this.parseLong(spec, Long.parseLong(value));
            } catch (NumberFormatException e) {
               throw new IllegalArgumentException(CacheBuilderSpec.format("key %s value set to %s, must be integer", key, value), e);
            }
         }
      }
   }

   static class InitialCapacityParser extends IntegerParser {
      protected void parseInteger(CacheBuilderSpec spec, int value) {
         Preconditions.checkArgument(spec.initialCapacity == null, "initial capacity was already set to %s", (Object)spec.initialCapacity);
         spec.initialCapacity = value;
      }
   }

   static class MaximumSizeParser extends LongParser {
      protected void parseLong(CacheBuilderSpec spec, long value) {
         Preconditions.checkArgument(spec.maximumSize == null, "maximum size was already set to %s", (Object)spec.maximumSize);
         Preconditions.checkArgument(spec.maximumWeight == null, "maximum weight was already set to %s", (Object)spec.maximumWeight);
         spec.maximumSize = value;
      }
   }

   static class MaximumWeightParser extends LongParser {
      protected void parseLong(CacheBuilderSpec spec, long value) {
         Preconditions.checkArgument(spec.maximumWeight == null, "maximum weight was already set to %s", (Object)spec.maximumWeight);
         Preconditions.checkArgument(spec.maximumSize == null, "maximum size was already set to %s", (Object)spec.maximumSize);
         spec.maximumWeight = value;
      }
   }

   static class ConcurrencyLevelParser extends IntegerParser {
      protected void parseInteger(CacheBuilderSpec spec, int value) {
         Preconditions.checkArgument(spec.concurrencyLevel == null, "concurrency level was already set to %s", (Object)spec.concurrencyLevel);
         spec.concurrencyLevel = value;
      }
   }

   static class KeyStrengthParser implements ValueParser {
      private final LocalCache.Strength strength;

      public KeyStrengthParser(LocalCache.Strength strength) {
         this.strength = strength;
      }

      public void parse(CacheBuilderSpec spec, String key, @CheckForNull String value) {
         Preconditions.checkArgument(value == null, "key %s does not take values", (Object)key);
         Preconditions.checkArgument(spec.keyStrength == null, "%s was already set to %s", key, spec.keyStrength);
         spec.keyStrength = this.strength;
      }
   }

   static class ValueStrengthParser implements ValueParser {
      private final LocalCache.Strength strength;

      public ValueStrengthParser(LocalCache.Strength strength) {
         this.strength = strength;
      }

      public void parse(CacheBuilderSpec spec, String key, @CheckForNull String value) {
         Preconditions.checkArgument(value == null, "key %s does not take values", (Object)key);
         Preconditions.checkArgument(spec.valueStrength == null, "%s was already set to %s", key, spec.valueStrength);
         spec.valueStrength = this.strength;
      }
   }

   static class RecordStatsParser implements ValueParser {
      public void parse(CacheBuilderSpec spec, String key, @CheckForNull String value) {
         Preconditions.checkArgument(value == null, "recordStats does not take values");
         Preconditions.checkArgument(spec.recordStats == null, "recordStats already set");
         spec.recordStats = true;
      }
   }

   abstract static class DurationParser implements ValueParser {
      protected abstract void parseDuration(CacheBuilderSpec spec, long duration, TimeUnit unit);

      public void parse(CacheBuilderSpec spec, String key, @CheckForNull String value) {
         if (Strings.isNullOrEmpty(value)) {
            throw new IllegalArgumentException("value of key " + key + " omitted");
         } else {
            try {
               char lastChar = value.charAt(value.length() - 1);
               TimeUnit timeUnit;
               switch (lastChar) {
                  case 'd':
                     timeUnit = TimeUnit.DAYS;
                     break;
                  case 'h':
                     timeUnit = TimeUnit.HOURS;
                     break;
                  case 'm':
                     timeUnit = TimeUnit.MINUTES;
                     break;
                  case 's':
                     timeUnit = TimeUnit.SECONDS;
                     break;
                  default:
                     throw new IllegalArgumentException(CacheBuilderSpec.format("key %s invalid unit: was %s, must end with one of [dhms]", key, value));
               }

               long duration = Long.parseLong(value.substring(0, value.length() - 1));
               this.parseDuration(spec, duration, timeUnit);
            } catch (NumberFormatException var8) {
               throw new IllegalArgumentException(CacheBuilderSpec.format("key %s value set to %s, must be integer", key, value));
            }
         }
      }
   }

   static class AccessDurationParser extends DurationParser {
      protected void parseDuration(CacheBuilderSpec spec, long duration, TimeUnit unit) {
         Preconditions.checkArgument(spec.accessExpirationTimeUnit == null, "expireAfterAccess already set");
         spec.accessExpirationDuration = duration;
         spec.accessExpirationTimeUnit = unit;
      }
   }

   static class WriteDurationParser extends DurationParser {
      protected void parseDuration(CacheBuilderSpec spec, long duration, TimeUnit unit) {
         Preconditions.checkArgument(spec.writeExpirationTimeUnit == null, "expireAfterWrite already set");
         spec.writeExpirationDuration = duration;
         spec.writeExpirationTimeUnit = unit;
      }
   }

   static class RefreshDurationParser extends DurationParser {
      protected void parseDuration(CacheBuilderSpec spec, long duration, TimeUnit unit) {
         Preconditions.checkArgument(spec.refreshTimeUnit == null, "refreshAfterWrite already set");
         spec.refreshDuration = duration;
         spec.refreshTimeUnit = unit;
      }
   }

   private interface ValueParser {
      void parse(CacheBuilderSpec spec, String key, @CheckForNull String value);
   }
}
