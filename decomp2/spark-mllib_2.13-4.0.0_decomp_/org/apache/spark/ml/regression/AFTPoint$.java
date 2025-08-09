package org.apache.spark.ml.regression;

import java.io.Serializable;
import org.apache.spark.ml.linalg.Vector;
import scala.Option;
import scala.Some;
import scala.Tuple3;
import scala.None.;
import scala.runtime.AbstractFunction3;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class AFTPoint$ extends AbstractFunction3 implements Serializable {
   public static final AFTPoint$ MODULE$ = new AFTPoint$();

   public final String toString() {
      return "AFTPoint";
   }

   public AFTPoint apply(final Vector features, final double label, final double censor) {
      return new AFTPoint(features, label, censor);
   }

   public Option unapply(final AFTPoint x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple3(x$0.features(), BoxesRunTime.boxToDouble(x$0.label()), BoxesRunTime.boxToDouble(x$0.censor()))));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(AFTPoint$.class);
   }

   private AFTPoint$() {
   }
}
