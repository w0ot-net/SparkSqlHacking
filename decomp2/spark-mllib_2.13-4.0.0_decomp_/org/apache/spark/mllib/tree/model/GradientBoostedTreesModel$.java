package org.apache.spark.mllib.tree.model;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkContext;
import org.apache.spark.mllib.regression.LabeledPoint;
import org.apache.spark.mllib.tree.configuration.Algo$;
import org.apache.spark.mllib.tree.configuration.EnsembleCombiningStrategy$;
import org.apache.spark.mllib.tree.loss.Loss;
import org.apache.spark.mllib.util.Loader;
import org.apache.spark.mllib.util.Loader$;
import org.apache.spark.rdd.RDD;
import org.json4s.JValue;
import scala.Function1;
import scala.MatchError;
import scala.Predef;
import scala.Tuple2;
import scala.Tuple3;
import scala.reflect.ClassTag.;
import scala.runtime.ModuleSerializationProxy;

public final class GradientBoostedTreesModel$ implements Loader, Serializable {
   public static final GradientBoostedTreesModel$ MODULE$ = new GradientBoostedTreesModel$();

   public RDD computeInitialPredictionAndError(final RDD data, final double initTreeWeight, final DecisionTreeModel initTree, final Loss loss) {
      return data.map((lp) -> {
         double pred = initTreeWeight * initTree.predict(lp.features());
         double error = loss.computeError(pred, lp.label());
         return new Tuple2.mcDD.sp(pred, error);
      }, .MODULE$.apply(Tuple2.class));
   }

   public RDD updatePredictionError(final RDD data, final RDD predictionAndError, final double treeWeight, final DecisionTreeModel tree, final Loss loss) {
      RDD qual$1 = data.zip(predictionAndError, .MODULE$.apply(Tuple2.class));
      Function1 x$1 = (iter) -> iter.map((x0$1) -> {
            if (x0$1 != null) {
               LabeledPoint lp = (LabeledPoint)x0$1._1();
               Tuple2 var8 = (Tuple2)x0$1._2();
               if (var8 != null) {
                  double pred = var8._1$mcD$sp();
                  double newPred = pred + tree.predict(lp.features()) * treeWeight;
                  double newError = loss.computeError(newPred, lp.label());
                  return new Tuple2.mcDD.sp(newPred, newError);
               }
            }

            throw new MatchError(x0$1);
         });
      boolean x$2 = qual$1.mapPartitions$default$2();
      RDD newPredError = qual$1.mapPartitions(x$1, x$2, .MODULE$.apply(Tuple2.class));
      return newPredError;
   }

   public GradientBoostedTreesModel load(final SparkContext sc, final String path) {
      Tuple3 var6 = Loader$.MODULE$.loadMetadata(sc, path);
      if (var6 == null) {
         throw new MatchError(var6);
      } else {
         String loadedClassName = (String)var6._1();
         String version = (String)var6._2();
         JValue jsonMetadata = (JValue)var6._3();
         Tuple3 var5 = new Tuple3(loadedClassName, version, jsonMetadata);
         String loadedClassName = (String)var5._1();
         String version = (String)var5._2();
         JValue jsonMetadata = (JValue)var5._3();
         String classNameV1_0 = GradientBoostedTreesModel.SaveLoadV1_0$.MODULE$.thisClassName();
         Tuple2 var14 = new Tuple2(loadedClassName, version);
         if (var14 != null) {
            String className = (String)var14._1();
            String var16 = (String)var14._2();
            if ("1.0".equals(var16)) {
               if (className == null) {
                  if (classNameV1_0 != null) {
                     throw new Exception("GradientBoostedTreesModel.load did not recognize model with (className, format version): (" + loadedClassName + ", " + version + ").  Supported:\n  (" + classNameV1_0 + ", 1.0)");
                  }
               } else if (!className.equals(classNameV1_0)) {
                  throw new Exception("GradientBoostedTreesModel.load did not recognize model with (className, format version): (" + loadedClassName + ", " + version + ").  Supported:\n  (" + classNameV1_0 + ", 1.0)");
               }

               TreeEnsembleModel$SaveLoadV1_0$Metadata metadata;
               boolean var21;
               Predef var10000;
               label31: {
                  label30: {
                     metadata = TreeEnsembleModel.SaveLoadV1_0$.MODULE$.readMetadata(jsonMetadata);
                     var10000 = scala.Predef..MODULE$;
                     String var10001 = metadata.combiningStrategy();
                     String var19 = EnsembleCombiningStrategy$.MODULE$.Sum().toString();
                     if (var10001 == null) {
                        if (var19 == null) {
                           break label30;
                        }
                     } else if (var10001.equals(var19)) {
                        break label30;
                     }

                     var21 = false;
                     break label31;
                  }

                  var21 = true;
               }

               var10000.assert(var21);
               DecisionTreeModel[] trees = TreeEnsembleModel.SaveLoadV1_0$.MODULE$.loadTrees(sc, path, metadata.treeAlgo());
               return new GradientBoostedTreesModel(Algo$.MODULE$.fromString(metadata.algo()), trees, metadata.treeWeights());
            }
         }

         throw new Exception("GradientBoostedTreesModel.load did not recognize model with (className, format version): (" + loadedClassName + ", " + version + ").  Supported:\n  (" + classNameV1_0 + ", 1.0)");
      }
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(GradientBoostedTreesModel$.class);
   }

   private GradientBoostedTreesModel$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
