package org.apache.spark.ml.r;

import java.lang.invoke.SerializedLambda;
import java.util.Locale;
import org.apache.spark.ml.Pipeline;
import org.apache.spark.ml.PipelineModel;
import org.apache.spark.ml.PipelineStage;
import org.apache.spark.ml.feature.RFormula;
import org.apache.spark.ml.feature.RFormulaModel;
import org.apache.spark.ml.regression.GeneralizedLinearRegression;
import org.apache.spark.ml.regression.GeneralizedLinearRegressionModel;
import org.apache.spark.ml.regression.GeneralizedLinearRegressionTrainingSummary;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.sql.Dataset;
import scala.Tuple5;
import scala.collection.ArrayOps.;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction1;

public final class GeneralizedLinearRegressionWrapper$ implements MLReadable {
   public static final GeneralizedLinearRegressionWrapper$ MODULE$ = new GeneralizedLinearRegressionWrapper$();

   static {
      MLReadable.$init$(MODULE$);
   }

   private boolean $lessinit$greater$default$11() {
      return false;
   }

   public GeneralizedLinearRegressionWrapper fit(final String formula, final Dataset data, final String family, final String link, final double tol, final int maxIter, final String weightCol, final double regParam, final double variancePower, final double linkPower, final String stringIndexerOrderType, final String offsetCol) {
      RFormulaModel rFormulaModel;
      GeneralizedLinearRegression glr;
      label56: {
         label55: {
            RFormula rFormula = (new RFormula()).setFormula(formula).setStringIndexerOrderType(stringIndexerOrderType);
            RWrapperUtils$.MODULE$.checkDataColumns(rFormula, data);
            rFormulaModel = rFormula.fit(data);
            glr = (GeneralizedLinearRegression)(new GeneralizedLinearRegression()).setFamily(family).setFitIntercept(rFormula.hasIntercept()).setTol(tol).setMaxIter(maxIter).setRegParam(regParam).setFeaturesCol(rFormula.getFeaturesCol());
            String var10000 = family.toLowerCase(Locale.ROOT);
            String var20 = "tweedie";
            if (var10000 == null) {
               if (var20 == null) {
                  break label55;
               }
            } else if (var10000.equals(var20)) {
               break label55;
            }

            glr.setLink(link);
            break label56;
         }

         glr.setVariancePower(variancePower).setLinkPower(linkPower);
      }

      if (weightCol != null) {
         glr.setWeightCol(weightCol);
      } else {
         BoxedUnit var40 = BoxedUnit.UNIT;
      }

      if (offsetCol != null) {
         glr.setOffsetCol(offsetCol);
      } else {
         BoxedUnit var41 = BoxedUnit.UNIT;
      }

      PipelineModel pipeline;
      GeneralizedLinearRegressionTrainingSummary summary;
      String[] rFeatures;
      double[] rCoefficients;
      double rDispersion;
      double rNullDeviance;
      double rDeviance;
      long rResidualDegreeOfFreedomNull;
      long rResidualDegreeOfFreedom;
      double var43;
      label47: {
         label46: {
            pipeline = (new Pipeline()).setStages((PipelineStage[])(new PipelineStage[]{rFormulaModel, glr})).fit(data);
            GeneralizedLinearRegressionModel glm = (GeneralizedLinearRegressionModel)pipeline.stages()[1];
            summary = glm.summary();
            rFeatures = glm.getFitIntercept() ? (String[]).MODULE$.$plus$plus$extension(scala.Predef..MODULE$.refArrayOps((Object[])(new String[]{"(Intercept)"})), summary.featureNames(), scala.reflect.ClassTag..MODULE$.apply(String.class)) : summary.featureNames();
            rCoefficients = summary.isNormalSolver() ? (double[]).MODULE$.$plus$plus$extension(scala.Predef..MODULE$.doubleArrayOps((double[]).MODULE$.$plus$plus$extension(scala.Predef..MODULE$.doubleArrayOps((double[]).MODULE$.$plus$plus$extension(scala.Predef..MODULE$.doubleArrayOps((double[]).MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])summary.coefficientsWithStatistics()), (x$1) -> BoxesRunTime.boxToDouble($anonfun$fit$1(x$1)), scala.reflect.ClassTag..MODULE$.Double())), .MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])summary.coefficientsWithStatistics()), (x$2) -> BoxesRunTime.boxToDouble($anonfun$fit$2(x$2)), scala.reflect.ClassTag..MODULE$.Double()), scala.reflect.ClassTag..MODULE$.Double())), .MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])summary.coefficientsWithStatistics()), (x$3) -> BoxesRunTime.boxToDouble($anonfun$fit$3(x$3)), scala.reflect.ClassTag..MODULE$.Double()), scala.reflect.ClassTag..MODULE$.Double())), .MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])summary.coefficientsWithStatistics()), (x$4) -> BoxesRunTime.boxToDouble($anonfun$fit$4(x$4)), scala.reflect.ClassTag..MODULE$.Double()), scala.reflect.ClassTag..MODULE$.Double()) : (glm.getFitIntercept() ? (double[]).MODULE$.$plus$plus$extension(scala.Predef..MODULE$.doubleArrayOps(new double[]{glm.intercept()}), glm.coefficients().toArray(), scala.reflect.ClassTag..MODULE$.Double()) : glm.coefficients().toArray());
            rDispersion = summary.dispersion();
            rNullDeviance = summary.nullDeviance();
            rDeviance = summary.deviance();
            rResidualDegreeOfFreedomNull = summary.residualDegreeOfFreedomNull();
            rResidualDegreeOfFreedom = summary.residualDegreeOfFreedom();
            String var42 = family.toLowerCase(Locale.ROOT);
            String var38 = "tweedie";
            if (var42 == null) {
               if (var38 != null) {
                  break label46;
               }
            } else if (!var42.equals(var38)) {
               break label46;
            }

            if (!.MODULE$.exists$extension(scala.Predef..MODULE$.doubleArrayOps(new double[]{(double)0.0F, (double)1.0F, (double)2.0F}), (JFunction1.mcZD.sp)(x) -> scala.math.package..MODULE$.abs(x - variancePower) < 1.0E-8)) {
               var43 = (double)0.0F;
               break label47;
            }
         }

         var43 = summary.aic();
      }

      double rAic = var43;
      int rNumIterations = summary.numIterations();
      return new GeneralizedLinearRegressionWrapper(pipeline, rFeatures, rCoefficients, rDispersion, rNullDeviance, rDeviance, rResidualDegreeOfFreedomNull, rResidualDegreeOfFreedom, rAic, rNumIterations, this.$lessinit$greater$default$11());
   }

   public MLReader read() {
      return new GeneralizedLinearRegressionWrapper.GeneralizedLinearRegressionWrapperReader();
   }

   public GeneralizedLinearRegressionWrapper load(final String path) {
      return (GeneralizedLinearRegressionWrapper)MLReadable.load$(this, path);
   }

   // $FF: synthetic method
   public static final double $anonfun$fit$1(final Tuple5 x$1) {
      return BoxesRunTime.unboxToDouble(x$1._2());
   }

   // $FF: synthetic method
   public static final double $anonfun$fit$2(final Tuple5 x$2) {
      return BoxesRunTime.unboxToDouble(x$2._3());
   }

   // $FF: synthetic method
   public static final double $anonfun$fit$3(final Tuple5 x$3) {
      return BoxesRunTime.unboxToDouble(x$3._4());
   }

   // $FF: synthetic method
   public static final double $anonfun$fit$4(final Tuple5 x$4) {
      return BoxesRunTime.unboxToDouble(x$4._5());
   }

   private GeneralizedLinearRegressionWrapper$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
