package org.apache.spark.ml.fpm;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import scala.collection.Map;
import scala.reflect.ClassTag;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime.;

public final class AssociationRules$ {
   public static final AssociationRules$ MODULE$ = new AssociationRules$();

   public Dataset getAssociationRulesFromFP(final Dataset dataset, final String itemsCol, final String freqCol, final double minConfidence, final Map itemSupport, final long numTrainingRecords, final ClassTag evidence$2) {
      RDD freqItemSetRdd = dataset.select(itemsCol, .MODULE$.wrapRefArray((Object[])(new String[]{freqCol}))).rdd().map((row) -> new org.apache.spark.mllib.fpm.FPGrowth.FreqItemset(row.getSeq(0).toArray(evidence$2), row.getLong(1)), scala.reflect.ClassTag..MODULE$.apply(org.apache.spark.mllib.fpm.FPGrowth.FreqItemset.class));
      RDD rows = (new org.apache.spark.mllib.fpm.AssociationRules()).setMinConfidence(minConfidence).run(freqItemSetRdd, itemSupport, evidence$2).map((r) -> org.apache.spark.sql.Row..MODULE$.apply(.MODULE$.genericWrapArray(new Object[]{r.antecedent(), r.consequent(), BoxesRunTime.boxToDouble(r.confidence()), r.lift().orNull(scala..less.colon.less..MODULE$.refl()), BoxesRunTime.boxToDouble(r.freqUnion() / (double)numTrainingRecords)})), scala.reflect.ClassTag..MODULE$.apply(Row.class));
      DataType dt = dataset.schema().apply(itemsCol).dataType();
      StructType schema = new StructType((StructField[])((Object[])(new StructField[]{new StructField("antecedent", dt, false, org.apache.spark.sql.types.StructField..MODULE$.apply$default$4()), new StructField("consequent", dt, false, org.apache.spark.sql.types.StructField..MODULE$.apply$default$4()), new StructField("confidence", org.apache.spark.sql.types.DoubleType..MODULE$, false, org.apache.spark.sql.types.StructField..MODULE$.apply$default$4()), new StructField("lift", org.apache.spark.sql.types.DoubleType..MODULE$, org.apache.spark.sql.types.StructField..MODULE$.apply$default$3(), org.apache.spark.sql.types.StructField..MODULE$.apply$default$4()), new StructField("support", org.apache.spark.sql.types.DoubleType..MODULE$, false, org.apache.spark.sql.types.StructField..MODULE$.apply$default$4())})));
      Dataset rules = dataset.sparkSession().createDataFrame(rows, schema);
      return rules;
   }

   private AssociationRules$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
