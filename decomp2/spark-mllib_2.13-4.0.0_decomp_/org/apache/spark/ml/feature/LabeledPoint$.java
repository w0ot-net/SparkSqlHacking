package org.apache.spark.ml.feature;

import java.io.Serializable;
import org.apache.spark.ml.linalg.Vector;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.AbstractFunction2;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class LabeledPoint$ extends AbstractFunction2 implements Serializable {
   public static final LabeledPoint$ MODULE$ = new LabeledPoint$();

   public final String toString() {
      return "LabeledPoint";
   }

   public LabeledPoint apply(final double label, final Vector features) {
      return new LabeledPoint(label, features);
   }

   public Option unapply(final LabeledPoint x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(BoxesRunTime.boxToDouble(x$0.label()), x$0.features())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(LabeledPoint$.class);
   }

   private LabeledPoint$() {
   }
}
