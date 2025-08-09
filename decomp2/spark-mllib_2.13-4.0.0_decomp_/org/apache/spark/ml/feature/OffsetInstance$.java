package org.apache.spark.ml.feature;

import java.io.Serializable;
import org.apache.spark.ml.linalg.Vector;
import scala.Option;
import scala.Some;
import scala.Tuple4;
import scala.None.;
import scala.runtime.AbstractFunction4;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class OffsetInstance$ extends AbstractFunction4 implements Serializable {
   public static final OffsetInstance$ MODULE$ = new OffsetInstance$();

   public final String toString() {
      return "OffsetInstance";
   }

   public OffsetInstance apply(final double label, final double weight, final double offset, final Vector features) {
      return new OffsetInstance(label, weight, offset, features);
   }

   public Option unapply(final OffsetInstance x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple4(BoxesRunTime.boxToDouble(x$0.label()), BoxesRunTime.boxToDouble(x$0.weight()), BoxesRunTime.boxToDouble(x$0.offset()), x$0.features())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(OffsetInstance$.class);
   }

   private OffsetInstance$() {
   }
}
