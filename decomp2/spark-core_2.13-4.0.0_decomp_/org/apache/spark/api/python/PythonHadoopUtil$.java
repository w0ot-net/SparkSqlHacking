package org.apache.spark.api.python;

import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.hadoop.conf.Configuration;
import org.apache.spark.rdd.RDD;
import scala.MatchError;
import scala.Tuple2;
import scala.jdk.CollectionConverters.;
import scala.runtime.BoxedUnit;

public final class PythonHadoopUtil$ {
   public static final PythonHadoopUtil$ MODULE$ = new PythonHadoopUtil$();

   public Configuration mapToConf(final Map map) {
      Configuration conf = new Configuration(false);
      .MODULE$.MapHasAsScala(map).asScala().foreach((x0$1) -> {
         $anonfun$mapToConf$1(conf, x0$1);
         return BoxedUnit.UNIT;
      });
      return conf;
   }

   public Configuration mergeConfs(final Configuration left, final Configuration right) {
      Configuration copy = new Configuration(left);
      .MODULE$.IterableHasAsScala(right).asScala().foreach((entry) -> {
         $anonfun$mergeConfs$1(copy, entry);
         return BoxedUnit.UNIT;
      });
      return copy;
   }

   public RDD convertRDD(final RDD rdd, final Converter keyConverter, final Converter valueConverter) {
      return rdd.map((x0$1) -> {
         if (x0$1 != null) {
            Object k = x0$1._1();
            Object v = x0$1._2();
            return new Tuple2(keyConverter.convert(k), valueConverter.convert(v));
         } else {
            throw new MatchError(x0$1);
         }
      }, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
   }

   // $FF: synthetic method
   public static final void $anonfun$mapToConf$1(final Configuration conf$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         String k = (String)x0$1._1();
         String v = (String)x0$1._2();
         conf$1.set(k, v);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$mergeConfs$1(final Configuration copy$1, final Map.Entry entry) {
      copy$1.set((String)entry.getKey(), (String)entry.getValue());
   }

   private PythonHadoopUtil$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
