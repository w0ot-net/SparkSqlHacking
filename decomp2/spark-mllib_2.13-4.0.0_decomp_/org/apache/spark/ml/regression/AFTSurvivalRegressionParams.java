package org.apache.spark.ml.regression;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.internal.Logging;
import org.apache.spark.ml.PredictorParams;
import org.apache.spark.ml.linalg.VectorUDT;
import org.apache.spark.ml.param.DoubleArrayParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamPair;
import org.apache.spark.ml.param.ParamValidators$;
import org.apache.spark.ml.param.shared.HasAggregationDepth;
import org.apache.spark.ml.param.shared.HasFitIntercept;
import org.apache.spark.ml.param.shared.HasMaxBlockSizeInMB;
import org.apache.spark.ml.param.shared.HasMaxIter;
import org.apache.spark.ml.param.shared.HasTol;
import org.apache.spark.ml.util.SchemaUtils$;
import org.apache.spark.sql.types.StructType;
import scala.collection.StringOps.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u001da\u0001\u0003\u0006\f!\u0003\r\taC\u000b\t\u000bi\u0002A\u0011\u0001\u001f\t\u000f\u0001\u0003!\u0019!C\u0003\u0003\")!\f\u0001C\u00017\"9Q\f\u0001b\u0001\n\u000bq\u0006\"B2\u0001\t\u0003!\u0007b\u00027\u0001\u0005\u0004%)!\u0011\u0005\u0006]\u0002!\ta\u0017\u0005\u0007a\u0002!\taC9\t\u000bU\u0004A\u0011\u0003<\u00037\u00053EkU;sm&4\u0018\r\u001c*fOJ,7o]5p]B\u000b'/Y7t\u0015\taQ\"\u0001\u0006sK\u001e\u0014Xm]:j_:T!AD\b\u0002\u00055d'B\u0001\t\u0012\u0003\u0015\u0019\b/\u0019:l\u0015\t\u00112#\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002)\u0005\u0019qN]4\u0014\u0013\u00011B\u0004\t\u0015,]E\"\u0004CA\f\u001b\u001b\u0005A\"\"A\r\u0002\u000bM\u001c\u0017\r\\1\n\u0005mA\"AB!osJ+g\r\u0005\u0002\u001e=5\tQ\"\u0003\u0002 \u001b\ty\u0001K]3eS\u000e$xN\u001d)be\u0006l7\u000f\u0005\u0002\"M5\t!E\u0003\u0002$I\u000511\u000f[1sK\u0012T!!J\u0007\u0002\u000bA\f'/Y7\n\u0005\u001d\u0012#A\u0003%bg6\u000b\u00070\u0013;feB\u0011\u0011%K\u0005\u0003U\t\u0012a\u0001S1t)>d\u0007CA\u0011-\u0013\ti#EA\bICN4\u0015\u000e^%oi\u0016\u00148-\u001a9u!\t\ts&\u0003\u00021E\t\u0019\u0002*Y:BO\u001e\u0014XmZ1uS>tG)\u001a9uQB\u0011\u0011EM\u0005\u0003g\t\u00121\u0003S1t\u001b\u0006D(\t\\8dWNK'0Z%o\u001b\n\u0003\"!\u000e\u001d\u000e\u0003YR!aN\b\u0002\u0011%tG/\u001a:oC2L!!\u000f\u001c\u0003\u000f1{wmZ5oO\u00061A%\u001b8ji\u0012\u001a\u0001\u0001F\u0001>!\t9b(\u0003\u0002@1\t!QK\\5u\u0003%\u0019WM\\:pe\u000e{G.F\u0001C!\r\u0019EIR\u0007\u0002I%\u0011Q\t\n\u0002\u0006!\u0006\u0014\u0018-\u001c\t\u0003\u000f:s!\u0001\u0013'\u0011\u0005%CR\"\u0001&\u000b\u0005-[\u0014A\u0002\u001fs_>$h(\u0003\u0002N1\u00051\u0001K]3eK\u001aL!a\u0014)\u0003\rM#(/\u001b8h\u0015\ti\u0005\u0004K\u0002\u0003%b\u0003\"a\u0015,\u000e\u0003QS!!V\b\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002X)\n)1+\u001b8dK\u0006\n\u0011,A\u00032]Yr\u0003'\u0001\u0007hKR\u001cUM\\:pe\u000e{G.F\u0001GQ\r\u0019!\u000bW\u0001\u0016cV\fg\u000e^5mKB\u0013xNY1cS2LG/[3t+\u0005y\u0006CA\"a\u0013\t\tGE\u0001\tE_V\u0014G.Z!se\u0006L\b+\u0019:b[\"\u001aAA\u0015-\u00021\u001d,G/U;b]RLG.\u001a)s_\n\f'-\u001b7ji&,7/F\u0001f!\r9b\r[\u0005\u0003Ob\u0011Q!\u0011:sCf\u0004\"aF5\n\u0005)D\"A\u0002#pk\ndW\rK\u0002\u0006%b\u000bA\"];b]RLG.Z:D_2D3A\u0002*Y\u0003=9W\r^)vC:$\u0018\u000e\\3t\u0007>d\u0007fA\u0004S1\u0006y\u0001.Y:Rk\u0006tG/\u001b7fg\u000e{G.F\u0001s!\t92/\u0003\u0002u1\t9!i\\8mK\u0006t\u0017A\u0007<bY&$\u0017\r^3B]\u0012$&/\u00198tM>\u0014XnU2iK6\fG\u0003B<\u0000\u0003\u0007\u0001\"\u0001_?\u000e\u0003eT!A_>\u0002\u000bQL\b/Z:\u000b\u0005q|\u0011aA:rY&\u0011a0\u001f\u0002\u000b'R\u0014Xo\u0019;UsB,\u0007BBA\u0001\u0013\u0001\u0007q/\u0001\u0004tG\",W.\u0019\u0005\u0007\u0003\u000bI\u0001\u0019\u0001:\u0002\u000f\u0019LG\u000f^5oO\u0002"
)
public interface AFTSurvivalRegressionParams extends PredictorParams, HasMaxIter, HasTol, HasFitIntercept, HasAggregationDepth, HasMaxBlockSizeInMB, Logging {
   void org$apache$spark$ml$regression$AFTSurvivalRegressionParams$_setter_$censorCol_$eq(final Param x$1);

   void org$apache$spark$ml$regression$AFTSurvivalRegressionParams$_setter_$quantileProbabilities_$eq(final DoubleArrayParam x$1);

   void org$apache$spark$ml$regression$AFTSurvivalRegressionParams$_setter_$quantilesCol_$eq(final Param x$1);

   Param censorCol();

   // $FF: synthetic method
   static String getCensorCol$(final AFTSurvivalRegressionParams $this) {
      return $this.getCensorCol();
   }

   default String getCensorCol() {
      return (String)this.$(this.censorCol());
   }

   DoubleArrayParam quantileProbabilities();

   // $FF: synthetic method
   static double[] getQuantileProbabilities$(final AFTSurvivalRegressionParams $this) {
      return $this.getQuantileProbabilities();
   }

   default double[] getQuantileProbabilities() {
      return (double[])this.$(this.quantileProbabilities());
   }

   Param quantilesCol();

   // $FF: synthetic method
   static String getQuantilesCol$(final AFTSurvivalRegressionParams $this) {
      return $this.getQuantilesCol();
   }

   default String getQuantilesCol() {
      return (String)this.$(this.quantilesCol());
   }

   // $FF: synthetic method
   static boolean hasQuantilesCol$(final AFTSurvivalRegressionParams $this) {
      return $this.hasQuantilesCol();
   }

   default boolean hasQuantilesCol() {
      return this.isDefined(this.quantilesCol()) && .MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString((String)this.$(this.quantilesCol())));
   }

   // $FF: synthetic method
   static StructType validateAndTransformSchema$(final AFTSurvivalRegressionParams $this, final StructType schema, final boolean fitting) {
      return $this.validateAndTransformSchema(schema, fitting);
   }

   default StructType validateAndTransformSchema(final StructType schema, final boolean fitting) {
      SchemaUtils$.MODULE$.checkColumnType(schema, (String)this.$(this.featuresCol()), new VectorUDT(), SchemaUtils$.MODULE$.checkColumnType$default$4());
      if (fitting) {
         SchemaUtils$.MODULE$.checkNumericType(schema, (String)this.$(this.censorCol()), SchemaUtils$.MODULE$.checkNumericType$default$3());
         SchemaUtils$.MODULE$.checkNumericType(schema, (String)this.$(this.labelCol()), SchemaUtils$.MODULE$.checkNumericType$default$3());
      }

      StructType schemaWithQuantilesCol = this.hasQuantilesCol() ? SchemaUtils$.MODULE$.appendColumn(schema, (String)this.$(this.quantilesCol()), new VectorUDT(), SchemaUtils$.MODULE$.appendColumn$default$4()) : schema;
      return SchemaUtils$.MODULE$.appendColumn(schemaWithQuantilesCol, (String)this.$(this.predictionCol()), org.apache.spark.sql.types.DoubleType..MODULE$, SchemaUtils$.MODULE$.appendColumn$default$4());
   }

   // $FF: synthetic method
   static boolean $anonfun$quantileProbabilities$1(final double[] t) {
      return scala.collection.ArrayOps..MODULE$.forall$extension(scala.Predef..MODULE$.doubleArrayOps(t), ParamValidators$.MODULE$.inRange((double)0.0F, (double)1.0F, false, false)) && t.length > 0;
   }

   static void $init$(final AFTSurvivalRegressionParams $this) {
      $this.org$apache$spark$ml$regression$AFTSurvivalRegressionParams$_setter_$censorCol_$eq(new Param($this, "censorCol", "censor column name", scala.reflect.ClassTag..MODULE$.apply(String.class)));
      $this.org$apache$spark$ml$regression$AFTSurvivalRegressionParams$_setter_$quantileProbabilities_$eq(new DoubleArrayParam($this, "quantileProbabilities", "quantile probabilities array", (t) -> BoxesRunTime.boxToBoolean($anonfun$quantileProbabilities$1(t))));
      $this.org$apache$spark$ml$regression$AFTSurvivalRegressionParams$_setter_$quantilesCol_$eq(new Param($this, "quantilesCol", "quantiles column name", scala.reflect.ClassTag..MODULE$.apply(String.class)));
      $this.setDefault(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new ParamPair[]{$this.censorCol().$minus$greater("censor"), $this.quantileProbabilities().$minus$greater(new double[]{0.01, 0.05, 0.1, (double)0.25F, (double)0.5F, (double)0.75F, 0.9, 0.95, 0.99}), $this.fitIntercept().$minus$greater(BoxesRunTime.boxToBoolean(true)), $this.maxIter().$minus$greater(BoxesRunTime.boxToInteger(100)), $this.tol().$minus$greater(BoxesRunTime.boxToDouble(1.0E-6)), $this.aggregationDepth().$minus$greater(BoxesRunTime.boxToInteger(2)), $this.maxBlockSizeInMB().$minus$greater(BoxesRunTime.boxToDouble((double)0.0F))}));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
