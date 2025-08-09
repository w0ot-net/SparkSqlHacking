package org.apache.spark.ml.stat;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.linalg.SQLDataTypes$;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.mllib.linalg.Matrix;
import org.apache.spark.mllib.linalg.Vectors$;
import org.apache.spark.mllib.stat.Statistics$;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import scala.MatchError;
import scala.Some;
import scala.collection.SeqOps;
import scala.collection.immutable.Nil.;

public final class Correlation$ {
   public static final Correlation$ MODULE$ = new Correlation$();

   public Dataset corr(final Dataset dataset, final String column, final String method) {
      RDD rdd = dataset.select(column, .MODULE$).rdd().map((x0$1) -> {
         if (x0$1 != null) {
            Some var3 = org.apache.spark.sql.Row..MODULE$.unapplySeq(x0$1);
            if (!var3.isEmpty() && var3.get() != null && ((SeqOps)var3.get()).lengthCompare(1) == 0) {
               Object v = ((SeqOps)var3.get()).apply(0);
               if (v instanceof Vector) {
                  Vector var5 = (Vector)v;
                  return Vectors$.MODULE$.fromML(var5);
               }
            }
         }

         throw new MatchError(x0$1);
      }, scala.reflect.ClassTag..MODULE$.apply(org.apache.spark.mllib.linalg.Vector.class));
      Matrix oldM = Statistics$.MODULE$.corr(rdd, method);
      String name = method + "(" + column + ")";
      StructType schema = new StructType((StructField[])((Object[])(new StructField[]{new StructField(name, SQLDataTypes$.MODULE$.MatrixType(), false, org.apache.spark.sql.types.StructField..MODULE$.apply$default$4())})));
      return dataset.sparkSession().createDataFrame(scala.jdk.CollectionConverters..MODULE$.SeqHasAsJava(new scala.collection.immutable..colon.colon(org.apache.spark.sql.Row..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{oldM.asML()})), .MODULE$)).asJava(), schema);
   }

   public Dataset corr(final Dataset dataset, final String column) {
      return this.corr(dataset, column, "pearson");
   }

   private Correlation$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
