package org.apache.spark.mllib.evaluation.binary;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.AbstractFunction2;
import scala.runtime.ModuleSerializationProxy;

public final class BinaryConfusionMatrixImpl$ extends AbstractFunction2 implements Serializable {
   public static final BinaryConfusionMatrixImpl$ MODULE$ = new BinaryConfusionMatrixImpl$();

   public final String toString() {
      return "BinaryConfusionMatrixImpl";
   }

   public BinaryConfusionMatrixImpl apply(final BinaryLabelCounter count, final BinaryLabelCounter totalCount) {
      return new BinaryConfusionMatrixImpl(count, totalCount);
   }

   public Option unapply(final BinaryConfusionMatrixImpl x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.count(), x$0.totalCount())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(BinaryConfusionMatrixImpl$.class);
   }

   private BinaryConfusionMatrixImpl$() {
   }
}
