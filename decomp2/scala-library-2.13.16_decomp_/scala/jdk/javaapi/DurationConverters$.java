package scala.jdk.javaapi;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;
import scala.Int$;
import scala.MatchError;
import scala.concurrent.duration.Duration$;
import scala.concurrent.duration.FiniteDuration;
import scala.concurrent.duration.FiniteDuration$;

public final class DurationConverters$ {
   public static final DurationConverters$ MODULE$ = new DurationConverters$();

   public FiniteDuration toScala(final Duration duration) {
      long originalSeconds = duration.getSeconds();
      int originalNanos = duration.getNano();
      if (originalNanos == 0) {
         if (originalSeconds == 0L) {
            return Duration$.MODULE$.Zero();
         } else {
            FiniteDuration$ var20 = FiniteDuration$.MODULE$;
            TimeUnit apply_unit = TimeUnit.SECONDS;
            return new FiniteDuration(originalSeconds, apply_unit);
         }
      } else if (originalSeconds == 0L) {
         FiniteDuration$ var17 = FiniteDuration$.MODULE$;
         Int$ var18 = Int$.MODULE$;
         long var19 = (long)originalNanos;
         TimeUnit apply_unit = TimeUnit.NANOSECONDS;
         long apply_length = var19;
         return new FiniteDuration(apply_length, apply_unit);
      } else {
         try {
            long secondsAsNanos = Math.multiplyExact(originalSeconds, 1000000000L);
            long totalNanos = secondsAsNanos + (long)originalNanos;
            if ((totalNanos >= 0L || secondsAsNanos >= 0L) && (totalNanos <= 0L || secondsAsNanos <= 0L)) {
               throw new ArithmeticException();
            } else {
               FiniteDuration$ var10000 = FiniteDuration$.MODULE$;
               TimeUnit apply_unit = TimeUnit.NANOSECONDS;
               FiniteDuration var16 = new FiniteDuration(totalNanos, apply_unit);
               Object var15 = null;
               return var16;
            }
         } catch (ArithmeticException var14) {
            throw new IllegalArgumentException((new StringBuilder(54)).append("Java duration ").append(duration).append(" cannot be expressed as a Scala duration").toString());
         }
      }
   }

   public Duration toJava(final FiniteDuration duration) {
      if (duration.length() == 0L) {
         return Duration.ZERO;
      } else {
         TimeUnit var2 = duration.unit();
         if (TimeUnit.NANOSECONDS.equals(var2)) {
            return Duration.ofNanos(duration.length());
         } else if (TimeUnit.MICROSECONDS.equals(var2)) {
            return Duration.of(duration.length(), ChronoUnit.MICROS);
         } else if (TimeUnit.MILLISECONDS.equals(var2)) {
            return Duration.ofMillis(duration.length());
         } else if (TimeUnit.SECONDS.equals(var2)) {
            return Duration.ofSeconds(duration.length());
         } else if (TimeUnit.MINUTES.equals(var2)) {
            return Duration.ofMinutes(duration.length());
         } else if (TimeUnit.HOURS.equals(var2)) {
            return Duration.ofHours(duration.length());
         } else if (TimeUnit.DAYS.equals(var2)) {
            return Duration.ofDays(duration.length());
         } else {
            throw new MatchError(var2);
         }
      }
   }

   private DurationConverters$() {
   }
}
