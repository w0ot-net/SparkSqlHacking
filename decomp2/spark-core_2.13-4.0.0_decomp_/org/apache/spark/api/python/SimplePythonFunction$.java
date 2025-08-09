package org.apache.spark.api.python;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import org.apache.spark.util.CollectionAccumulator;
import scala.Option;
import scala.Some;
import scala.Tuple7;
import scala.None.;
import scala.collection.immutable.Seq;
import scala.runtime.AbstractFunction7;
import scala.runtime.ModuleSerializationProxy;

public final class SimplePythonFunction$ extends AbstractFunction7 implements Serializable {
   public static final SimplePythonFunction$ MODULE$ = new SimplePythonFunction$();

   public final String toString() {
      return "SimplePythonFunction";
   }

   public SimplePythonFunction apply(final Seq command, final Map envVars, final List pythonIncludes, final String pythonExec, final String pythonVer, final List broadcastVars, final CollectionAccumulator accumulator) {
      return new SimplePythonFunction(command, envVars, pythonIncludes, pythonExec, pythonVer, broadcastVars, accumulator);
   }

   public Option unapply(final SimplePythonFunction x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple7(x$0.command(), x$0.envVars(), x$0.pythonIncludes(), x$0.pythonExec(), x$0.pythonVer(), x$0.broadcastVars(), x$0.accumulator())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(SimplePythonFunction$.class);
   }

   private SimplePythonFunction$() {
   }
}
