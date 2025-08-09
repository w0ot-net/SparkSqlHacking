package org.apache.spark.streaming;

import java.io.Serializable;
import scala.Function4;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.ModuleSerializationProxy;

public final class StateSpecImpl$ implements Serializable {
   public static final StateSpecImpl$ MODULE$ = new StateSpecImpl$();

   public final String toString() {
      return "StateSpecImpl";
   }

   public StateSpecImpl apply(final Function4 function) {
      return new StateSpecImpl(function);
   }

   public Option unapply(final StateSpecImpl x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.function()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(StateSpecImpl$.class);
   }

   private StateSpecImpl$() {
   }
}
