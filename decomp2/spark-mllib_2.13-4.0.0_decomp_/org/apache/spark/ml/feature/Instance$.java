package org.apache.spark.ml.feature;

import java.io.Serializable;
import org.apache.spark.ml.linalg.Vector;
import scala.Option;
import scala.Some;
import scala.Tuple3;
import scala.None.;
import scala.runtime.AbstractFunction3;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class Instance$ extends AbstractFunction3 implements Serializable {
   public static final Instance$ MODULE$ = new Instance$();

   public final String toString() {
      return "Instance";
   }

   public Instance apply(final double label, final double weight, final Vector features) {
      return new Instance(label, weight, features);
   }

   public Option unapply(final Instance x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple3(BoxesRunTime.boxToDouble(x$0.label()), BoxesRunTime.boxToDouble(x$0.weight()), x$0.features())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Instance$.class);
   }

   private Instance$() {
   }
}
