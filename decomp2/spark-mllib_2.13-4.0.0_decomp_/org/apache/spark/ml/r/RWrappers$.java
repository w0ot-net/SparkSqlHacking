package org.apache.spark.ml.r;

import org.apache.hadoop.fs.Path;
import org.apache.spark.SparkException;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.sql.Row;
import org.json4s.DefaultFormats;
import org.json4s.JValue;
import org.json4s.DefaultFormats.;

public final class RWrappers$ extends MLReader {
   public static final RWrappers$ MODULE$ = new RWrappers$();

   public Object load(final String path) {
      DefaultFormats format = .MODULE$;
      String rMetadataPath = (new Path(path, "rMetadata")).toString();
      String rMetadataStr = ((Row)this.sparkSession().read().text(rMetadataPath).first()).getString(0);
      JValue rMetadata = org.json4s.jackson.JsonMethods..MODULE$.parse(rMetadataStr, org.json4s.jackson.JsonMethods..MODULE$.parse$default$2(), org.json4s.jackson.JsonMethods..MODULE$.parse$default$3(), org.json4s.AsJsonInput..MODULE$.stringAsJsonInput());
      String className = (String)org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(rMetadata), "class")), format, scala.reflect.ManifestFactory..MODULE$.classType(String.class));
      switch (className == null ? 0 : className.hashCode()) {
         case -1914743276:
            if ("org.apache.spark.ml.r.DecisionTreeClassifierWrapper".equals(className)) {
               return DecisionTreeClassifierWrapper$.MODULE$.load(path);
            }
            break;
         case -1634032951:
            if ("org.apache.spark.ml.r.LinearRegressionWrapper".equals(className)) {
               return LinearRegressionWrapper$.MODULE$.load(path);
            }
            break;
         case -1527620346:
            if ("org.apache.spark.ml.r.IsotonicRegressionWrapper".equals(className)) {
               return IsotonicRegressionWrapper$.MODULE$.load(path);
            }
            break;
         case -1406028836:
            if ("org.apache.spark.ml.r.BisectingKMeansWrapper".equals(className)) {
               return BisectingKMeansWrapper$.MODULE$.load(path);
            }
            break;
         case -1257725049:
            if ("org.apache.spark.ml.r.DecisionTreeRegressorWrapper".equals(className)) {
               return DecisionTreeRegressorWrapper$.MODULE$.load(path);
            }
            break;
         case -933227026:
            if ("org.apache.spark.ml.r.LogisticRegressionWrapper".equals(className)) {
               return LogisticRegressionWrapper$.MODULE$.load(path);
            }
            break;
         case -879005201:
            if ("org.apache.spark.ml.r.ALSWrapper".equals(className)) {
               return ALSWrapper$.MODULE$.load(path);
            }
            break;
         case -820072025:
            if ("org.apache.spark.ml.r.FMClassifierWrapper".equals(className)) {
               return FMClassifierWrapper$.MODULE$.load(path);
            }
            break;
         case 24512916:
            if ("org.apache.spark.ml.r.FMRegressorWrapper".equals(className)) {
               return FMRegressorWrapper$.MODULE$.load(path);
            }
            break;
         case 95421086:
            if ("org.apache.spark.ml.r.FPGrowthWrapper".equals(className)) {
               return FPGrowthWrapper$.MODULE$.load(path);
            }
            break;
         case 143771500:
            if ("org.apache.spark.ml.r.NaiveBayesWrapper".equals(className)) {
               return NaiveBayesWrapper$.MODULE$.load(path);
            }
            break;
         case 405900381:
            if ("org.apache.spark.ml.r.GBTClassifierWrapper".equals(className)) {
               return GBTClassifierWrapper$.MODULE$.load(path);
            }
            break;
         case 797717708:
            if ("org.apache.spark.ml.r.KMeansWrapper".equals(className)) {
               return KMeansWrapper$.MODULE$.load(path);
            }
            break;
         case 941933628:
            if ("org.apache.spark.ml.r.LinearSVCWrapper".equals(className)) {
               return LinearSVCWrapper$.MODULE$.load(path);
            }
            break;
         case 1167922793:
            if ("org.apache.spark.ml.r.AFTSurvivalRegressionWrapper".equals(className)) {
               return AFTSurvivalRegressionWrapper$.MODULE$.load(path);
            }
            break;
         case 1172439070:
            if ("org.apache.spark.ml.r.GBTRegressorWrapper".equals(className)) {
               return GBTRegressorWrapper$.MODULE$.load(path);
            }
            break;
         case 1184446201:
            if ("org.apache.spark.ml.r.GeneralizedLinearRegressionWrapper".equals(className)) {
               return GeneralizedLinearRegressionWrapper$.MODULE$.load(path);
            }
            break;
         case 1377754344:
            if ("org.apache.spark.ml.r.MultilayerPerceptronClassifierWrapper".equals(className)) {
               return MultilayerPerceptronClassifierWrapper$.MODULE$.load(path);
            }
            break;
         case 1523232398:
            if ("org.apache.spark.ml.r.LDAWrapper".equals(className)) {
               return LDAWrapper$.MODULE$.load(path);
            }
            break;
         case 1640275117:
            if ("org.apache.spark.ml.r.RandomForestRegressorWrapper".equals(className)) {
               return RandomForestRegressorWrapper$.MODULE$.load(path);
            }
            break;
         case 2023915950:
            if ("org.apache.spark.ml.r.RandomForestClassifierWrapper".equals(className)) {
               return RandomForestClassifierWrapper$.MODULE$.load(path);
            }
            break;
         case 2070807202:
            if ("org.apache.spark.ml.r.GaussianMixtureWrapper".equals(className)) {
               return GaussianMixtureWrapper$.MODULE$.load(path);
            }
      }

      throw new SparkException("SparkR read.ml does not support load " + className);
   }

   private RWrappers$() {
   }
}
