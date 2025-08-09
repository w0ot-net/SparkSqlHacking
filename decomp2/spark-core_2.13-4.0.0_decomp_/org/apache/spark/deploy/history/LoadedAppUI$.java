package org.apache.spark.deploy.history;

import java.io.Serializable;
import org.apache.spark.ui.SparkUI;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class LoadedAppUI$ extends AbstractFunction1 implements Serializable {
   public static final LoadedAppUI$ MODULE$ = new LoadedAppUI$();

   public final String toString() {
      return "LoadedAppUI";
   }

   public LoadedAppUI apply(final SparkUI ui) {
      return new LoadedAppUI(ui);
   }

   public Option unapply(final LoadedAppUI x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.ui()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(LoadedAppUI$.class);
   }

   private LoadedAppUI$() {
   }
}
