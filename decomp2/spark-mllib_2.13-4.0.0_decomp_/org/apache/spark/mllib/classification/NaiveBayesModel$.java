package org.apache.spark.mllib.classification;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkContext;
import org.apache.spark.mllib.util.Loader;
import org.apache.spark.mllib.util.Loader$;
import org.json4s.JValue;
import scala.MatchError;
import scala.Tuple2;
import scala.Tuple3;
import scala.Predef.;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class NaiveBayesModel$ implements Loader, Serializable {
   public static final NaiveBayesModel$ MODULE$ = new NaiveBayesModel$();

   public NaiveBayesModel load(final SparkContext sc, final String path) {
      Tuple3 var9 = Loader$.MODULE$.loadMetadata(sc, path);
      if (var9 == null) {
         throw new MatchError(var9);
      } else {
         Tuple3 var10000;
         label81: {
            JValue metadata;
            label82: {
               String loadedClassName = (String)var9._1();
               String version = (String)var9._2();
               JValue metadata = (JValue)var9._3();
               Tuple3 var8 = new Tuple3(loadedClassName, version, metadata);
               String loadedClassName = (String)var8._1();
               String version = (String)var8._2();
               metadata = (JValue)var8._3();
               String classNameV1_0 = NaiveBayesModel.SaveLoadV1_0$.MODULE$.thisClassName();
               String classNameV2_0 = NaiveBayesModel.SaveLoadV2_0$.MODULE$.thisClassName();
               Tuple2 var20 = new Tuple2(loadedClassName, version);
               if (var20 != null) {
                  String className = (String)var20._1();
                  String var22 = (String)var20._2();
                  if ("1.0".equals(var22)) {
                     if (className == null) {
                        if (classNameV1_0 == null) {
                           break label82;
                        }
                     } else if (className.equals(classNameV1_0)) {
                        break label82;
                     }
                  }
               }

               if (var20 == null) {
                  throw new Exception("NaiveBayesModel.load did not recognize model with (className, format version):(" + loadedClassName + ", " + version + ").  Supported:\n  (" + classNameV1_0 + ", 1.0)");
               }

               String className = (String)var20._1();
               String var32 = (String)var20._2();
               if (!"2.0".equals(var32)) {
                  throw new Exception("NaiveBayesModel.load did not recognize model with (className, format version):(" + loadedClassName + ", " + version + ").  Supported:\n  (" + classNameV1_0 + ", 1.0)");
               }

               if (className == null) {
                  if (classNameV2_0 != null) {
                     throw new Exception("NaiveBayesModel.load did not recognize model with (className, format version):(" + loadedClassName + ", " + version + ").  Supported:\n  (" + classNameV1_0 + ", 1.0)");
                  }
               } else if (!className.equals(classNameV2_0)) {
                  throw new Exception("NaiveBayesModel.load did not recognize model with (className, format version):(" + loadedClassName + ", " + version + ").  Supported:\n  (" + classNameV1_0 + ", 1.0)");
               }

               Tuple2 var35 = ClassificationModel$.MODULE$.getNumFeaturesClasses(metadata);
               if (var35 == null) {
                  throw new MatchError(var35);
               }

               int numFeatures = var35._1$mcI$sp();
               int numClasses = var35._2$mcI$sp();
               Tuple2.mcII.sp var34 = new Tuple2.mcII.sp(numFeatures, numClasses);
               int numFeatures = ((Tuple2)var34)._1$mcI$sp();
               int numClasses = ((Tuple2)var34)._2$mcI$sp();
               NaiveBayesModel model = NaiveBayesModel.SaveLoadV2_0$.MODULE$.load(sc, path);
               var10000 = new Tuple3(model, BoxesRunTime.boxToInteger(numFeatures), BoxesRunTime.boxToInteger(numClasses));
               break label81;
            }

            Tuple2 var25 = ClassificationModel$.MODULE$.getNumFeaturesClasses(metadata);
            if (var25 == null) {
               throw new MatchError(var25);
            }

            int numFeatures = var25._1$mcI$sp();
            int numClasses = var25._2$mcI$sp();
            Tuple2.mcII.sp var24 = new Tuple2.mcII.sp(numFeatures, numClasses);
            int numFeatures = ((Tuple2)var24)._1$mcI$sp();
            int numClasses = ((Tuple2)var24)._2$mcI$sp();
            NaiveBayesModel model = NaiveBayesModel.SaveLoadV1_0$.MODULE$.load(sc, path);
            var10000 = new Tuple3(model, BoxesRunTime.boxToInteger(numFeatures), BoxesRunTime.boxToInteger(numClasses));
         }

         Tuple3 var19 = var10000;
         if (var19 != null) {
            NaiveBayesModel model = (NaiveBayesModel)var19._1();
            int numFeatures = BoxesRunTime.unboxToInt(var19._2());
            int numClasses = BoxesRunTime.unboxToInt(var19._3());
            Tuple3 var18 = new Tuple3(model, BoxesRunTime.boxToInteger(numFeatures), BoxesRunTime.boxToInteger(numClasses));
            NaiveBayesModel model = (NaiveBayesModel)var18._1();
            int numFeatures = BoxesRunTime.unboxToInt(var18._2());
            int numClasses = BoxesRunTime.unboxToInt(var18._3());
            .MODULE$.assert(model.pi().length == numClasses, () -> "NaiveBayesModel.load expected " + numClasses + " classes, but class priors vector pi had " + model.pi().length + " elements");
            .MODULE$.assert(model.theta().length == numClasses, () -> "NaiveBayesModel.load expected " + numClasses + " classes, but class conditionals array theta had " + model.theta().length + " elements");
            .MODULE$.assert(scala.collection.ArrayOps..MODULE$.forall$extension(.MODULE$.refArrayOps((Object[])model.theta()), (x$11) -> BoxesRunTime.boxToBoolean($anonfun$load$7(numFeatures, x$11))), () -> "NaiveBayesModel.load expected " + numFeatures + " features, but class conditionals array theta had elements of size: " + .MODULE$.wrapIntArray((int[])scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.refArrayOps((Object[])model.theta()), (x$12) -> BoxesRunTime.boxToInteger($anonfun$load$9(x$12)), scala.reflect.ClassTag..MODULE$.Int())).mkString(","));
            return model;
         } else {
            throw new MatchError(var19);
         }
      }
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(NaiveBayesModel$.class);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$load$7(final int numFeatures$1, final double[] x$11) {
      return x$11.length == numFeatures$1;
   }

   // $FF: synthetic method
   public static final int $anonfun$load$9(final double[] x$12) {
      return x$12.length;
   }

   private NaiveBayesModel$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
