package org.apache.spark.network.netty;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkConf;
import org.apache.spark.network.util.ConfigProvider;
import org.apache.spark.network.util.NettyUtils;
import org.apache.spark.network.util.TransportConf;
import scala.Option;
import scala.collection.immutable.;

public final class SparkTransportConf$ {
   public static final SparkTransportConf$ MODULE$ = new SparkTransportConf$();

   public TransportConf fromSparkConf(final SparkConf _conf, final String module, final int numUsableCores, final Option role, final Option sslOptions) {
      SparkConf conf = _conf.clone();
      int numThreads = NettyUtils.defaultNumThreads(numUsableCores);
      (new .colon.colon("serverThreads", new .colon.colon("clientThreads", scala.collection.immutable.Nil..MODULE$))).foreach((suffix) -> {
         String value = (String)role.flatMap((r) -> conf.getOption("spark." + r + "." + module + ".io." + suffix)).getOrElse(() -> conf.get("spark." + module + ".io." + suffix, Integer.toString(numThreads)));
         return conf.set("spark." + module + ".io." + suffix, value);
      });
      ConfigProvider configProvider = (ConfigProvider)sslOptions.map((x$1) -> x$1.createConfigProvider(conf)).getOrElse(() -> new ConfigProvider(conf) {
            private final SparkConf conf$1;

            public String get(final String name) {
               return this.conf$1.get(name);
            }

            public String get(final String name, final String defaultValue) {
               return this.conf$1.get(name, defaultValue);
            }

            public Iterable getAll() {
               return scala.jdk.CollectionConverters..MODULE$.MapHasAsJava(scala.Predef..MODULE$.wrapRefArray((Object[])this.conf$1.getAll()).toMap(scala..less.colon.less..MODULE$.refl())).asJava().entrySet();
            }

            public {
               this.conf$1 = conf$1;
            }
         });
      return new TransportConf(module, configProvider);
   }

   public int fromSparkConf$default$3() {
      return 0;
   }

   public Option fromSparkConf$default$4() {
      return scala.None..MODULE$;
   }

   public Option fromSparkConf$default$5() {
      return scala.None..MODULE$;
   }

   private SparkTransportConf$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
