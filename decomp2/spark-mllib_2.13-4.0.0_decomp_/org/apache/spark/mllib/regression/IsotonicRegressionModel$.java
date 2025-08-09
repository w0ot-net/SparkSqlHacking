package org.apache.spark.mllib.regression;

import java.io.Serializable;
import org.apache.spark.SparkContext;
import org.apache.spark.mllib.util.Loader;
import org.apache.spark.mllib.util.Loader$;
import org.json4s.Formats;
import org.json4s.JValue;
import org.json4s.DefaultFormats.;
import scala.MatchError;
import scala.Tuple2;
import scala.Tuple3;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class IsotonicRegressionModel$ implements Loader, Serializable {
   public static final IsotonicRegressionModel$ MODULE$ = new IsotonicRegressionModel$();

   public IsotonicRegressionModel load(final SparkContext sc, final String path) {
      Formats formats = .MODULE$;
      Tuple3 var8 = Loader$.MODULE$.loadMetadata(sc, path);
      if (var8 == null) {
         throw new MatchError(var8);
      } else {
         String loadedClassName = (String)var8._1();
         String version = (String)var8._2();
         JValue metadata = (JValue)var8._3();
         Tuple3 var7 = new Tuple3(loadedClassName, version, metadata);
         String loadedClassName = (String)var7._1();
         String version = (String)var7._2();
         JValue metadata = (JValue)var7._3();
         boolean isotonic = BoxesRunTime.unboxToBoolean(org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(metadata), "isotonic")), formats, scala.reflect.ManifestFactory..MODULE$.Boolean()));
         String classNameV1_0 = IsotonicRegressionModel.SaveLoadV1_0$.MODULE$.thisClassName();
         Tuple2 var17 = new Tuple2(loadedClassName, version);
         if (var17 != null) {
            String className = (String)var17._1();
            String var19 = (String)var17._2();
            if ("1.0".equals(var19)) {
               if (className == null) {
                  if (classNameV1_0 != null) {
                     throw new Exception("IsotonicRegressionModel.load did not recognize model with (className, format version): (" + loadedClassName + ", " + version + ").  Supported:\n  (" + classNameV1_0 + ", 1.0)");
                  }
               } else if (!className.equals(classNameV1_0)) {
                  throw new Exception("IsotonicRegressionModel.load did not recognize model with (className, format version): (" + loadedClassName + ", " + version + ").  Supported:\n  (" + classNameV1_0 + ", 1.0)");
               }

               Tuple2 var22 = IsotonicRegressionModel.SaveLoadV1_0$.MODULE$.load(sc, path);
               if (var22 != null) {
                  double[] boundaries = (double[])var22._1();
                  double[] predictions = (double[])var22._2();
                  Tuple2 var21 = new Tuple2(boundaries, predictions);
                  double[] boundaries = (double[])var21._1();
                  double[] predictions = (double[])var21._2();
                  return new IsotonicRegressionModel(boundaries, predictions, isotonic);
               }

               throw new MatchError(var22);
            }
         }

         throw new Exception("IsotonicRegressionModel.load did not recognize model with (className, format version): (" + loadedClassName + ", " + version + ").  Supported:\n  (" + classNameV1_0 + ", 1.0)");
      }
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(IsotonicRegressionModel$.class);
   }

   private IsotonicRegressionModel$() {
   }
}
