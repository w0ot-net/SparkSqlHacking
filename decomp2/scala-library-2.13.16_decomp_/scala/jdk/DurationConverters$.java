package scala.jdk;

import java.time.Duration;
import scala.concurrent.duration.FiniteDuration;

public final class DurationConverters$ {
   public static final DurationConverters$ MODULE$ = new DurationConverters$();

   public Duration JavaDurationOps(final Duration duration) {
      return duration;
   }

   public final FiniteDuration ScalaDurationOps(final FiniteDuration duration) {
      return duration;
   }

   private DurationConverters$() {
   }
}
