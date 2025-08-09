package org.apache.spark.ml.clustering;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.util.DatasetUtils$;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.mllib.linalg.Vectors$;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import scala.MatchError;
import scala.Some;
import scala.Tuple2;
import scala.collection.SeqOps;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.ScalaRunTime.;

public final class LDA$ implements MLReadable, Serializable {
   public static final LDA$ MODULE$ = new LDA$();

   static {
      MLReadable.$init$(MODULE$);
   }

   public RDD getOldDataset(final Dataset dataset, final String featuresCol) {
      return dataset.select(.MODULE$.wrapRefArray((Object[])(new Column[]{org.apache.spark.sql.functions..MODULE$.monotonically_increasing_id(), DatasetUtils$.MODULE$.checkNonNanVectors(DatasetUtils$.MODULE$.columnToVector(dataset, featuresCol))}))).rdd().map((x0$1) -> {
         if (x0$1 != null) {
            Some var3 = org.apache.spark.sql.Row..MODULE$.unapplySeq(x0$1);
            if (!var3.isEmpty() && var3.get() != null && ((SeqOps)var3.get()).lengthCompare(2) == 0) {
               Object docId = ((SeqOps)var3.get()).apply(0);
               Object f = ((SeqOps)var3.get()).apply(1);
               if (docId instanceof Long) {
                  long var6 = BoxesRunTime.unboxToLong(docId);
                  if (f instanceof Vector) {
                     Vector var8 = (Vector)f;
                     return new Tuple2(BoxesRunTime.boxToLong(var6), Vectors$.MODULE$.fromML(var8));
                  }
               }
            }
         }

         throw new MatchError(x0$1);
      }, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
   }

   public MLReader read() {
      return new LDA.LDAReader();
   }

   public LDA load(final String path) {
      return (LDA)MLReadable.load$(this, path);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(LDA$.class);
   }

   private LDA$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
