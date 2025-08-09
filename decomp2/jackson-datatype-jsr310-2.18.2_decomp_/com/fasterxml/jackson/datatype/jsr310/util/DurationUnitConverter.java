package com.fasterxml.jackson.datatype.jsr310.util;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DurationUnitConverter {
   private static final Map UNITS;
   final DurationSerialization serialization;

   DurationUnitConverter(DurationSerialization serialization) {
      this.serialization = serialization;
   }

   public Duration convert(long value) {
      return (Duration)this.serialization.deserializer.apply(value);
   }

   public long convert(Duration duration) {
      return (Long)this.serialization.serializer.apply(duration);
   }

   public static String descForAllowed() {
      return "\"" + (String)UNITS.keySet().stream().collect(Collectors.joining("\", \"")) + "\"";
   }

   public static DurationUnitConverter from(String unit) {
      DurationSerialization def = (DurationSerialization)UNITS.get(unit);
      return def == null ? null : new DurationUnitConverter(def);
   }

   static {
      Map<String, DurationSerialization> units = new LinkedHashMap();
      units.put(ChronoUnit.NANOS.name(), new DurationSerialization(Duration::toNanos, DurationUnitConverter.DurationSerialization.deserializer(ChronoUnit.NANOS)));
      units.put(ChronoUnit.MICROS.name(), new DurationSerialization((d) -> d.toNanos() / 1000L, DurationUnitConverter.DurationSerialization.deserializer(ChronoUnit.MICROS)));
      units.put(ChronoUnit.MILLIS.name(), new DurationSerialization(Duration::toMillis, DurationUnitConverter.DurationSerialization.deserializer(ChronoUnit.MILLIS)));
      units.put(ChronoUnit.SECONDS.name(), new DurationSerialization(Duration::getSeconds, DurationUnitConverter.DurationSerialization.deserializer(ChronoUnit.SECONDS)));
      units.put(ChronoUnit.MINUTES.name(), new DurationSerialization(Duration::toMinutes, DurationUnitConverter.DurationSerialization.deserializer(ChronoUnit.MINUTES)));
      units.put(ChronoUnit.HOURS.name(), new DurationSerialization(Duration::toHours, DurationUnitConverter.DurationSerialization.deserializer(ChronoUnit.HOURS)));
      units.put(ChronoUnit.HALF_DAYS.name(), new DurationSerialization((d) -> d.toHours() / 12L, DurationUnitConverter.DurationSerialization.deserializer(ChronoUnit.HALF_DAYS)));
      units.put(ChronoUnit.DAYS.name(), new DurationSerialization(Duration::toDays, DurationUnitConverter.DurationSerialization.deserializer(ChronoUnit.DAYS)));
      UNITS = units;
   }

   protected static class DurationSerialization {
      final Function serializer;
      final Function deserializer;

      DurationSerialization(Function serializer, Function deserializer) {
         this.serializer = serializer;
         this.deserializer = deserializer;
      }

      static Function deserializer(TemporalUnit unit) {
         return (v) -> Duration.of(v, unit);
      }
   }
}
