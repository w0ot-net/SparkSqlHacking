package org.apache.spark.internal.config;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class ConfigBuilder$ extends AbstractFunction1 implements Serializable {
   public static final ConfigBuilder$ MODULE$ = new ConfigBuilder$();

   public final String toString() {
      return "ConfigBuilder";
   }

   public ConfigBuilder apply(final String key) {
      return new ConfigBuilder(key);
   }

   public Option unapply(final ConfigBuilder x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.key()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ConfigBuilder$.class);
   }

   private ConfigBuilder$() {
   }
}
