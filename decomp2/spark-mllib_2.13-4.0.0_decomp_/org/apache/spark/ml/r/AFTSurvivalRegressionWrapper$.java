package org.apache.spark.ml.r;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkException;
import org.apache.spark.ml.Pipeline;
import org.apache.spark.ml.PipelineModel;
import org.apache.spark.ml.PipelineStage;
import org.apache.spark.ml.attribute.Attribute;
import org.apache.spark.ml.attribute.AttributeGroup$;
import org.apache.spark.ml.feature.RFormula;
import org.apache.spark.ml.feature.RFormulaModel;
import org.apache.spark.ml.regression.AFTSurvivalRegression;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.types.StructType;
import scala.MatchError;
import scala.Option;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.LinearSeqOps;
import scala.collection.StringOps.;
import scala.collection.immutable.List;
import scala.util.matching.Regex;

public final class AFTSurvivalRegressionWrapper$ implements MLReadable {
   public static final AFTSurvivalRegressionWrapper$ MODULE$ = new AFTSurvivalRegressionWrapper$();
   private static final Regex FORMULA_REGEXP;

   static {
      MLReadable.$init$(MODULE$);
      FORMULA_REGEXP = .MODULE$.r$extension(scala.Predef..MODULE$.augmentString("Surv\\(([^,]+), ([^,]+)\\) ~ (.+)"));
   }

   private Regex FORMULA_REGEXP() {
      return FORMULA_REGEXP;
   }

   private Tuple2 formulaRewrite(final String formula) {
      String rewrittenFormula = null;
      String censorCol = null;

      try {
         if (formula != null) {
            Option var7 = this.FORMULA_REGEXP().unapplySeq(formula);
            if (!var7.isEmpty() && var7.get() != null && ((List)var7.get()).lengthCompare(3) == 0) {
               String label = (String)((LinearSeqOps)var7.get()).apply(0);
               String censor = (String)((LinearSeqOps)var7.get()).apply(1);
               String features = (String)((LinearSeqOps)var7.get()).apply(2);
               Tuple3 var5 = new Tuple3(label, censor, features);
               String label = (String)var5._1();
               String censor = (String)var5._2();
               String features = (String)var5._3();
               if (features.contains(".")) {
                  throw new UnsupportedOperationException("Terms of survreg formula can not support dot operator.");
               }

               String var10000 = label.trim();
               rewrittenFormula = var10000 + "~" + features.trim();
               censorCol = censor.trim();
               return new Tuple2(rewrittenFormula, censorCol);
            }
         }

         throw new MatchError(formula);
      } catch (MatchError var15) {
         throw new SparkException("Could not parse formula: " + formula);
      }
   }

   public AFTSurvivalRegressionWrapper fit(final String formula, final Dataset data, final int aggregationDepth, final String stringIndexerOrderType) {
      Tuple2 var7 = this.formulaRewrite(formula);
      if (var7 != null) {
         String rewrittenFormula = (String)var7._1();
         String censorCol = (String)var7._2();
         Tuple2 var6 = new Tuple2(rewrittenFormula, censorCol);
         String rewrittenFormula = (String)var6._1();
         String censorCol = (String)var6._2();
         RFormula rFormula = (new RFormula()).setFormula(rewrittenFormula).setStringIndexerOrderType(stringIndexerOrderType);
         RWrapperUtils$.MODULE$.checkDataColumns(rFormula, data);
         RFormulaModel rFormulaModel = rFormula.fit(data);
         StructType schema = rFormulaModel.transform(data).schema();
         Attribute[] featureAttrs = (Attribute[])AttributeGroup$.MODULE$.fromStructField(schema.apply(rFormula.getFeaturesCol())).attributes().get();
         String[] features = (String[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(featureAttrs), (x$3) -> (String)x$3.name().get(), scala.reflect.ClassTag..MODULE$.apply(String.class));
         AFTSurvivalRegression aft = ((AFTSurvivalRegression)(new AFTSurvivalRegression()).setCensorCol(censorCol).setFitIntercept(rFormula.hasIntercept()).setFeaturesCol(rFormula.getFeaturesCol())).setAggregationDepth(aggregationDepth);
         PipelineModel pipeline = (new Pipeline()).setStages((PipelineStage[])(new PipelineStage[]{rFormulaModel, aft})).fit(data);
         return new AFTSurvivalRegressionWrapper(pipeline, features);
      } else {
         throw new MatchError(var7);
      }
   }

   public MLReader read() {
      return new AFTSurvivalRegressionWrapper.AFTSurvivalRegressionWrapperReader();
   }

   public AFTSurvivalRegressionWrapper load(final String path) {
      return (AFTSurvivalRegressionWrapper)MLReadable.load$(this, path);
   }

   private AFTSurvivalRegressionWrapper$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
