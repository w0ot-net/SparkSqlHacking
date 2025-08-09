package io.fabric8.kubernetes.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
import java.text.ParseException;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalUnit;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import lombok.Generated;

@JsonInclude(Include.NON_NULL)
@JsonDeserialize
@JsonSerialize(
   using = Serializer.class
)
public class Duration implements KubernetesResource {
   private static final long serialVersionUID = -2326157920610452294L;
   private static final String DURATION_REGEX = "(\\d+)\\s*([A-Za-zµ]+)";
   private static final Pattern DURATION_PATTERN = Pattern.compile("(\\d+)\\s*([A-Za-zµ]+)");
   private java.time.Duration javaDuration;

   public Duration() {
   }

   public Duration(java.time.Duration javaDuration) {
      this.javaDuration = javaDuration;
   }

   public java.time.Duration getDuration() {
      return this.javaDuration;
   }

   public void setDuration(java.time.Duration javaDuration) {
      this.javaDuration = javaDuration;
   }

   public Long getValue() {
      return (Long)Optional.ofNullable(this.javaDuration).map(java.time.Duration::toNanos).orElse(0L);
   }

   public static boolean isDuration(String durationToTest) {
      try {
         parse(durationToTest);
         return true;
      } catch (ParseException var2) {
         return false;
      }
   }

   @JsonCreator(
      mode = Mode.DELEGATING
   )
   public static Duration parse(String duration) throws ParseException {
      java.time.Duration accumulator = java.time.Duration.ZERO;
      boolean found = false;
      Optional var10000 = Optional.ofNullable(duration).map(String::trim);
      Pattern var10001 = DURATION_PATTERN;
      Objects.requireNonNull(var10001);

      java.time.Duration durationToken;
      for(Matcher matcher = (Matcher)var10000.map(var10001::matcher).orElse((Object)null); matcher != null && matcher.find(); accumulator = accumulator.plus(durationToken)) {
         found = true;
         durationToken = (java.time.Duration)Optional.ofNullable(Duration.TimeUnits.from(matcher.group(2))).map((tu) -> java.time.Duration.of(Long.parseLong(matcher.group(1)), tu.timeUnit)).orElseThrow(() -> new ParseException(String.format("Invalid duration token (%s)", matcher.group()), 0));
      }

      if (!found) {
         throw new ParseException(String.format("Provided duration string (%s) is invalid", duration), 0);
      } else {
         return new Duration(accumulator);
      }
   }

   @Generated
   public String toString() {
      return "Duration(javaDuration=" + this.javaDuration + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof Duration)) {
         return false;
      } else {
         Duration other = (Duration)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$javaDuration = this.javaDuration;
            Object other$javaDuration = other.javaDuration;
            if (this$javaDuration == null) {
               if (other$javaDuration != null) {
                  return false;
               }
            } else if (!this$javaDuration.equals(other$javaDuration)) {
               return false;
            }

            return true;
         }
      }
   }

   @Generated
   protected boolean canEqual(Object other) {
      return other instanceof Duration;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $javaDuration = this.javaDuration;
      result = result * 59 + ($javaDuration == null ? 43 : $javaDuration.hashCode());
      return result;
   }

   public static class Serializer extends StdSerializer {
      public Serializer() {
         super(Duration.class);
      }

      public void serialize(Duration duration, JsonGenerator jgen, SerializerProvider provider) throws IOException {
         jgen.writeString(String.format("%sns", duration.getValue()));
      }

      public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper visitor, JavaType typeHint) throws JsonMappingException {
         visitor.expectStringFormat(typeHint);
      }
   }

   private static enum TimeUnits {
      NANOSECOND(ChronoUnit.NANOS, new String[]{"ns", "nano", "nanos"}),
      MICROSECOND(ChronoUnit.MICROS, new String[]{"us", "µs", "micro", "micros"}),
      MILLISECOND(ChronoUnit.MILLIS, new String[]{"ms", "milli", "millis"}),
      SECOND(ChronoUnit.SECONDS, new String[]{"s", "sec", "secs"}),
      MINUTE(ChronoUnit.MINUTES, new String[]{"m", "min", "mins"}),
      HOUR(ChronoUnit.HOURS, new String[]{"h", "hr", "hour", "hours"}),
      DAY(ChronoUnit.DAYS, new String[]{"d", "day", "days"}),
      WEEK(Duration.SevenDayWeek.INSTANCE, new String[]{"w", "wk", "week", "weeks"});

      private final Set abbreviations;
      private final TemporalUnit timeUnit;

      private TimeUnits(TemporalUnit timeUnit, String... abbreviations) {
         this.timeUnit = timeUnit;
         this.abbreviations = new HashSet(Arrays.asList(abbreviations));
      }

      static TimeUnits from(String abbreviation) {
         return (TimeUnits)Stream.of(values()).filter((tu) -> tu.abbreviations.contains(abbreviation.toLowerCase())).findAny().orElse((Object)null);
      }
   }

   private static class SevenDayWeek implements TemporalUnit {
      private static final SevenDayWeek INSTANCE = new SevenDayWeek();
      private static final java.time.Duration SEVEN_DAYS = java.time.Duration.ofDays(7L);

      public java.time.Duration getDuration() {
         return SEVEN_DAYS;
      }

      public boolean isDurationEstimated() {
         return false;
      }

      public boolean isDateBased() {
         return true;
      }

      public boolean isTimeBased() {
         return false;
      }

      public Temporal addTo(Temporal temporal, long amount) {
         return temporal.plus(amount, this);
      }

      public long between(Temporal temporal1Inclusive, Temporal temporal2Exclusive) {
         return temporal1Inclusive.until(temporal2Exclusive, this);
      }
   }
}
