package org.apache.spark.deploy.yarn;

import java.io.Serializable;
import org.apache.hadoop.yarn.api.records.FinalApplicationStatus;
import org.apache.hadoop.yarn.api.records.YarnApplicationState;
import scala.Option;
import scala.Some;
import scala.Tuple3;
import scala.None.;
import scala.runtime.AbstractFunction3;
import scala.runtime.ModuleSerializationProxy;

public final class YarnAppReport$ extends AbstractFunction3 implements Serializable {
   public static final YarnAppReport$ MODULE$ = new YarnAppReport$();

   public final String toString() {
      return "YarnAppReport";
   }

   public YarnAppReport apply(final YarnApplicationState appState, final FinalApplicationStatus finalState, final Option diagnostics) {
      return new YarnAppReport(appState, finalState, diagnostics);
   }

   public Option unapply(final YarnAppReport x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple3(x$0.appState(), x$0.finalState(), x$0.diagnostics())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(YarnAppReport$.class);
   }

   private YarnAppReport$() {
   }
}
