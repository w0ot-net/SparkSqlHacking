package org.apache.spark.internal.plugin;

import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.SparkEnv;
import org.apache.spark.api.plugin.SparkPlugin;
import org.apache.spark.internal.config.package$;
import org.apache.spark.util.Utils$;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.collection.SeqOps;
import scala.collection.immutable.Seq;
import scala.util.Either;
import scala.util.Left;
import scala.util.Right;

public final class PluginContainer$ {
   public static final PluginContainer$ MODULE$ = new PluginContainer$();
   private static final String EXTRA_CONF_PREFIX = "spark.plugins.internal.conf.";

   public String EXTRA_CONF_PREFIX() {
      return EXTRA_CONF_PREFIX;
   }

   public Option apply(final SparkContext sc, final Map resources) {
      return this.apply((Either)(new Left(sc)), resources);
   }

   public Option apply(final SparkEnv env, final Map resources) {
      return this.apply((Either)(new Right(env)), resources);
   }

   private Option apply(final Either ctx, final Map resources) {
      SparkConf conf = (SparkConf)ctx.fold((x$1) -> x$1.conf(), (x$2) -> x$2.conf());
      Seq plugins = Utils$.MODULE$.loadExtensions(SparkPlugin.class, (Seq)((SeqOps)conf.get(package$.MODULE$.PLUGINS())).distinct(), conf);
      if (plugins.nonEmpty()) {
         if (ctx instanceof Left) {
            Left var7 = (Left)ctx;
            SparkContext sc = (SparkContext)var7.value();
            return new Some(new DriverPluginContainer(sc, resources, plugins));
         } else if (ctx instanceof Right) {
            Right var9 = (Right)ctx;
            SparkEnv env = (SparkEnv)var9.value();
            return new Some(new ExecutorPluginContainer(env, resources, plugins));
         } else {
            throw new MatchError(ctx);
         }
      } else {
         return .MODULE$;
      }
   }

   private PluginContainer$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
