package org.apache.spark.mllib.stat.test;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.AbstractFunction2;
import scala.runtime.ModuleSerializationProxy;

public final class BinarySample$ extends AbstractFunction2 implements Serializable {
   public static final BinarySample$ MODULE$ = new BinarySample$();

   public final String toString() {
      return "BinarySample";
   }

   public BinarySample apply(final boolean isExperiment, final double value) {
      return new BinarySample(isExperiment, value);
   }

   public Option unapply(final BinarySample x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2.mcZD.sp(x$0.isExperiment(), x$0.value())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(BinarySample$.class);
   }

   private BinarySample$() {
   }
}
