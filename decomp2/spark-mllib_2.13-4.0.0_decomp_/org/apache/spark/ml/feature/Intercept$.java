package org.apache.spark.ml.feature;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class Intercept$ extends AbstractFunction1 implements Serializable {
   public static final Intercept$ MODULE$ = new Intercept$();

   public final String toString() {
      return "Intercept";
   }

   public Intercept apply(final boolean enabled) {
      return new Intercept(enabled);
   }

   public Option unapply(final Intercept x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(BoxesRunTime.boxToBoolean(x$0.enabled())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Intercept$.class);
   }

   private Intercept$() {
   }
}
