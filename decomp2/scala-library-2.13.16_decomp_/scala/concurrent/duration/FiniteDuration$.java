package scala.concurrent.duration;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import scala.runtime.ModuleSerializationProxy;

public final class FiniteDuration$ implements Serializable {
   public static final FiniteDuration$ MODULE$ = new FiniteDuration$();

   public FiniteDuration apply(final long length, final TimeUnit unit) {
      return new FiniteDuration(length, unit);
   }

   public FiniteDuration apply(final long length, final String unit) {
      return new FiniteDuration(length, (TimeUnit)Duration$.MODULE$.timeUnit().apply(unit));
   }

   private final long max_ns() {
      return Long.MAX_VALUE;
   }

   private final long max_µs() {
      return 9223372036854775L;
   }

   private final long max_ms() {
      return 9223372036854L;
   }

   private final long max_s() {
      return 9223372036L;
   }

   private final long max_min() {
      return 153722867L;
   }

   private final long max_h() {
      return 2562047L;
   }

   private final long max_d() {
      return 106751L;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(FiniteDuration$.class);
   }

   private FiniteDuration$() {
   }
}
