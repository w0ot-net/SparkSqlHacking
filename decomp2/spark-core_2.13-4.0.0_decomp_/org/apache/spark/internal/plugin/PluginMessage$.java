package org.apache.spark.internal.plugin;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.AbstractFunction2;
import scala.runtime.ModuleSerializationProxy;

public final class PluginMessage$ extends AbstractFunction2 implements Serializable {
   public static final PluginMessage$ MODULE$ = new PluginMessage$();

   public final String toString() {
      return "PluginMessage";
   }

   public PluginMessage apply(final String pluginName, final Object message) {
      return new PluginMessage(pluginName, message);
   }

   public Option unapply(final PluginMessage x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.pluginName(), x$0.message())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(PluginMessage$.class);
   }

   private PluginMessage$() {
   }
}
