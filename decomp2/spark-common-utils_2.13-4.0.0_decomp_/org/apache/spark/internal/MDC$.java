package org.apache.spark.internal;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.ModuleSerializationProxy;

public final class MDC$ implements Serializable {
   public static final MDC$ MODULE$ = new MDC$();

   public MDC of(final LogKey key, final Object value) {
      return new MDC(key, value);
   }

   public MDC apply(final LogKey key, final Object value) {
      return new MDC(key, value);
   }

   public Option unapply(final MDC x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.key(), x$0.value())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(MDC$.class);
   }

   private MDC$() {
   }
}
