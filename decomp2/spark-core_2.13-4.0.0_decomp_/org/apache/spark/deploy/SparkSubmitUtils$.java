package org.apache.spark.deploy;

import java.lang.invoke.SerializedLambda;
import java.util.ServiceLoader;
import org.apache.spark.SparkException;
import scala.Tuple2;
import scala.collection.Iterable;
import scala.collection.SeqFactory;
import scala.collection.SeqOps;
import scala.collection.immutable.ArraySeq;
import scala.jdk.CollectionConverters.;
import scala.runtime.BoxesRunTime;

public final class SparkSubmitUtils$ {
   public static final SparkSubmitUtils$ MODULE$ = new SparkSubmitUtils$();

   public SparkSubmitOperation getSubmitOperations(final String master) {
      ClassLoader loader = org.apache.spark.util.Utils$.MODULE$.getContextOrSparkClassLoader();
      Iterable serviceLoaders = (Iterable).MODULE$.IterableHasAsScala(ServiceLoader.load(SparkSubmitOperation.class, loader)).asScala().filter((x$28) -> BoxesRunTime.boxToBoolean($anonfun$getSubmitOperations$1(master, x$28)));
      int var5 = serviceLoaders.size();
      if (var5 > 1) {
         throw new SparkException("Multiple(" + var5 + ") external SparkSubmitOperations clients registered for master url " + master + ".");
      } else if (1 == var5) {
         return (SparkSubmitOperation)serviceLoaders.headOption().get();
      } else {
         throw new IllegalArgumentException("No external SparkSubmitOperations clients found for master url: '" + master + "'");
      }
   }

   public Tuple2 parseSparkConfProperty(final String pair) {
      ArraySeq var3 = org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(pair.split("=", 2)).toImmutableArraySeq();
      if (var3 != null) {
         SeqOps var4 = scala.package..MODULE$.Seq().unapplySeq(var3);
         if (!scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.isEmpty$extension(var4) && new SeqFactory.UnapplySeqWrapper(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var4)) != null && scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.lengthCompare$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var4), 2) == 0) {
            String k = (String)scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.apply$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var4), 0);
            String v = (String)scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.apply$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var4), 1);
            return new Tuple2(k, v);
         }
      }

      throw new SparkException("Spark config without '=': " + pair);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getSubmitOperations$1(final String master$1, final SparkSubmitOperation x$28) {
      return x$28.supports(master$1);
   }

   private SparkSubmitUtils$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
