package org.apache.spark.sql.streaming;

import java.io.Serializable;
import java.time.Duration;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.ModuleSerializationProxy;

public final class TTLConfig$ implements Serializable {
   public static final TTLConfig$ MODULE$ = new TTLConfig$();

   public TTLConfig NONE() {
      return new TTLConfig(Duration.ZERO);
   }

   public TTLConfig apply(final Duration ttlDuration) {
      return new TTLConfig(ttlDuration);
   }

   public Option unapply(final TTLConfig x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.ttlDuration()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(TTLConfig$.class);
   }

   private TTLConfig$() {
   }
}
