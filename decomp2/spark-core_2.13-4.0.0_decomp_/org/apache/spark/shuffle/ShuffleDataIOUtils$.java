package org.apache.spark.shuffle;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkConf;
import org.apache.spark.internal.config.package$;
import org.apache.spark.shuffle.api.ShuffleDataIO;
import org.apache.spark.util.Utils$;
import scala.collection.immutable.;
import scala.collection.immutable.Seq;

public final class ShuffleDataIOUtils$ {
   public static final ShuffleDataIOUtils$ MODULE$ = new ShuffleDataIOUtils$();
   private static final String SHUFFLE_SPARK_CONF_PREFIX = "spark.shuffle.plugin.__config__.";

   public String SHUFFLE_SPARK_CONF_PREFIX() {
      return SHUFFLE_SPARK_CONF_PREFIX;
   }

   public ShuffleDataIO loadShuffleDataIO(final SparkConf conf) {
      String configuredPluginClass = (String)conf.get(package$.MODULE$.SHUFFLE_IO_PLUGIN_CLASS());
      Seq maybeIO = Utils$.MODULE$.loadExtensions(ShuffleDataIO.class, new .colon.colon(configuredPluginClass, scala.collection.immutable.Nil..MODULE$), conf);
      scala.Predef..MODULE$.require(maybeIO.nonEmpty(), () -> {
         String var10000 = package$.MODULE$.SHUFFLE_IO_PLUGIN_CLASS().key();
         return "A valid shuffle plugin must be specified by config " + var10000 + ", but " + configuredPluginClass + " resulted in zero valid plugins.";
      });
      return (ShuffleDataIO)maybeIO.head();
   }

   private ShuffleDataIOUtils$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
