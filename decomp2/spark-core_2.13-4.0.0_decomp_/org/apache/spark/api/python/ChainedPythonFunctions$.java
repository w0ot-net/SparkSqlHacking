package org.apache.spark.api.python;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.collection.immutable.Seq;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class ChainedPythonFunctions$ extends AbstractFunction1 implements Serializable {
   public static final ChainedPythonFunctions$ MODULE$ = new ChainedPythonFunctions$();

   public final String toString() {
      return "ChainedPythonFunctions";
   }

   public ChainedPythonFunctions apply(final Seq funcs) {
      return new ChainedPythonFunctions(funcs);
   }

   public Option unapply(final ChainedPythonFunctions x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.funcs()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ChainedPythonFunctions$.class);
   }

   private ChainedPythonFunctions$() {
   }
}
