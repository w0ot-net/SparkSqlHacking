package org.apache.spark.sql.expressions;

import java.io.Serializable;
import org.apache.spark.sql.Encoder;
import scala.Function2;
import scala.runtime.ModuleSerializationProxy;

public final class ReduceAggregator$ implements Serializable {
   public static final ReduceAggregator$ MODULE$ = new ReduceAggregator$();

   public ReduceAggregator apply(final Object f, final Encoder evidence$2) {
      return new ReduceAggregator((Function2)f, evidence$2);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ReduceAggregator$.class);
   }

   private ReduceAggregator$() {
   }
}
