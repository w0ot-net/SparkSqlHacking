package org.apache.spark.mllib.classification;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkContext;
import org.apache.spark.mllib.classification.impl.GLMClassificationModel;
import org.apache.spark.mllib.classification.impl.GLMClassificationModel$SaveLoadV1_0$Data;
import org.apache.spark.mllib.util.Loader;
import org.apache.spark.mllib.util.Loader$;
import org.json4s.JValue;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.Predef.;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class SVMModel$ implements Loader, Serializable {
   public static final SVMModel$ MODULE$ = new SVMModel$();

   public SVMModel load(final SparkContext sc, final String path) {
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
         String classNameV1_0 = "org.apache.spark.mllib.classification.SVMModel";
         Tuple2 var16 = new Tuple2(loadedClassName, version);
         if (var16 != null) {
            String className = (String)var16._1();
            String var18 = (String)var16._2();
            if ("1.0".equals(var18)) {
               if (className == null) {
                  if (classNameV1_0 != null) {
                     throw new Exception("SVMModel.load did not recognize model with (className, format version):(" + loadedClassName + ", " + version + ").  Supported:\n  (" + classNameV1_0 + ", 1.0)");
                  }
               } else if (!className.equals(classNameV1_0)) {
                  throw new Exception("SVMModel.load did not recognize model with (className, format version):(" + loadedClassName + ", " + version + ").  Supported:\n  (" + classNameV1_0 + ", 1.0)");
               }

               Tuple2 var21 = ClassificationModel$.MODULE$.getNumFeaturesClasses(metadata);
               if (var21 != null) {
                  int numFeatures = var21._1$mcI$sp();
                  int numClasses = var21._2$mcI$sp();
                  Tuple2.mcII.sp var20 = new Tuple2.mcII.sp(numFeatures, numClasses);
                  int numFeatures = ((Tuple2)var20)._1$mcI$sp();
                  int numClasses = ((Tuple2)var20)._2$mcI$sp();
                  GLMClassificationModel$SaveLoadV1_0$Data data = GLMClassificationModel.SaveLoadV1_0$.MODULE$.loadData(sc, path, classNameV1_0);
                  SVMModel model = new SVMModel(data.weights(), data.intercept());
                  .MODULE$.assert(model.weights().size() == numFeatures, () -> "SVMModel.load with numFeatures=" + numFeatures + " was given non-matching weights vector of size " + model.weights().size());
                  .MODULE$.assert(numClasses == 2, () -> "SVMModel.load was given numClasses=" + numClasses + " but only supports 2 classes");
                  Option var28 = data.threshold();
                  if (var28 instanceof Some) {
                     Some var29 = (Some)var28;
                     double t = BoxesRunTime.unboxToDouble(var29.value());
                     model.setThreshold(t);
                  } else {
                     if (!scala.None..MODULE$.equals(var28)) {
                        throw new MatchError(var28);
                     }

                     model.clearThreshold();
                  }

                  return model;
               }

               throw new MatchError(var21);
            }
         }

         throw new Exception("SVMModel.load did not recognize model with (className, format version):(" + loadedClassName + ", " + version + ").  Supported:\n  (" + classNameV1_0 + ", 1.0)");
      }
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(SVMModel$.class);
   }

   private SVMModel$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
