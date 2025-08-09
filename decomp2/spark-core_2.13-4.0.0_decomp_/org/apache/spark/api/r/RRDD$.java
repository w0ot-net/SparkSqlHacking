package org.apache.spark.api.r;

import java.io.File;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext$;
import org.apache.spark.SparkEnv$;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaRDD$;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.security.SocketAuthServer$;
import scala.Function1;
import scala.MatchError;
import scala.Tuple2;
import scala.jdk.CollectionConverters.;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class RRDD$ implements Serializable {
   public static final RRDD$ MODULE$ = new RRDD$();

   public JavaSparkContext createSparkContext(final String master, final String appName, final String sparkHome, final String[] jars, final Map sparkEnvirMap, final Map sparkExecutorEnvMap) {
      SparkConf sparkConf;
      label26: {
         label25: {
            sparkConf = (new SparkConf()).setAppName(appName).setSparkHome(sparkHome);
            String var8 = "";
            if (master == null) {
               if (var8 != null) {
                  break label25;
               }
            } else if (!master.equals(var8)) {
               break label25;
            }

            sparkConf.setIfMissing("spark.master", "local");
            break label26;
         }

         sparkConf.setMaster(master);
      }

      .MODULE$.MapHasAsScala(sparkEnvirMap).asScala().withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$createSparkContext$1(check$ifrefutable$1))).foreach((x$1) -> {
         if (x$1 != null) {
            Object name = x$1._1();
            Object value = x$1._2();
            return sparkConf.set(name.toString(), value.toString());
         } else {
            throw new MatchError(x$1);
         }
      });
      .MODULE$.MapHasAsScala(sparkExecutorEnvMap).asScala().withFilter((check$ifrefutable$2) -> BoxesRunTime.boxToBoolean($anonfun$createSparkContext$3(check$ifrefutable$2))).foreach((x$2) -> {
         if (x$2 != null) {
            Object name = x$2._1();
            Object value = x$2._2();
            return sparkConf.setExecutorEnv(name.toString(), value.toString());
         } else {
            throw new MatchError(x$2);
         }
      });
      if (sparkEnvirMap.containsKey("spark.r.sql.derby.temp.dir") && System.getProperty("derby.stream.error.file") == null) {
         System.setProperty("derby.stream.error.file", (new scala.collection.immutable..colon.colon(sparkEnvirMap.get("spark.r.sql.derby.temp.dir").toString(), new scala.collection.immutable..colon.colon("derby.log", scala.collection.immutable.Nil..MODULE$))).mkString(File.separator));
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      JavaSparkContext jsc = new JavaSparkContext(SparkContext$.MODULE$.getOrCreate(sparkConf));
      scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps((Object[])jars), (jar) -> {
         $anonfun$createSparkContext$5(jsc, jar);
         return BoxedUnit.UNIT;
      });
      return jsc;
   }

   public JavaRDD createRDDFromArray(final JavaSparkContext jsc, final byte[][] arr) {
      return JavaRDD$.MODULE$.fromRDD(jsc.sc().parallelize(org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(arr).toImmutableArraySeq(), arr.length, scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Byte.TYPE))), scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Byte.TYPE)));
   }

   public JavaRDD createRDDFromFile(final JavaSparkContext jsc, final String fileName, final int parallelism) {
      return JavaRDD$.MODULE$.readRDDFromFile(jsc, fileName, parallelism);
   }

   public Object[] serveToStream(final String threadName, final Function1 writeFunc) {
      return SocketAuthServer$.MODULE$.serveToStream(threadName, new RAuthHelper(SparkEnv$.MODULE$.get().conf()), writeFunc);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(RRDD$.class);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$createSparkContext$1(final Tuple2 check$ifrefutable$1) {
      return check$ifrefutable$1 != null;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$createSparkContext$3(final Tuple2 check$ifrefutable$2) {
      return check$ifrefutable$2 != null;
   }

   // $FF: synthetic method
   public static final void $anonfun$createSparkContext$5(final JavaSparkContext jsc$1, final String jar) {
      jsc$1.addJar(jar);
   }

   private RRDD$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
