package scala.concurrent.duration;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import scala.None$;
import scala.Option;
import scala.Some;
import scala.runtime.ModuleSerializationProxy;

public final class Deadline$ implements Serializable {
   public static final Deadline$ MODULE$ = new Deadline$();

   public Deadline now() {
      Duration$ var10000 = Duration$.MODULE$;
      long var6 = System.nanoTime();
      TimeUnit apply_unit = TimeUnit.NANOSECONDS;
      long apply_length = var6;
      FiniteDuration var7 = new FiniteDuration(apply_length, apply_unit);
      Object var5 = null;
      FiniteDuration apply_time = var7;
      return new Deadline(apply_time);
   }

   public Deadline apply(final FiniteDuration time) {
      return new Deadline(time);
   }

   public Option unapply(final Deadline x$0) {
      return (Option)(x$0 == null ? None$.MODULE$ : new Some(x$0.time()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Deadline$.class);
   }

   private Deadline$() {
   }
}
