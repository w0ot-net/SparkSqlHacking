package org.apache.spark.mllib.tree.model;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkContext;
import org.apache.spark.mllib.tree.configuration.Algo$;
import org.apache.spark.mllib.util.Loader;
import org.apache.spark.mllib.util.Loader$;
import org.json4s.JValue;
import scala.MatchError;
import scala.Tuple2;
import scala.Tuple3;
import scala.Predef.;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.java8.JFunction1;

public final class RandomForestModel$ implements Loader, Serializable {
   public static final RandomForestModel$ MODULE$ = new RandomForestModel$();

   public RandomForestModel load(final SparkContext sc, final String path) {
      Tuple3 var6 = Loader$.MODULE$.loadMetadata(sc, path);
      if (var6 == null) {
         throw new MatchError(var6);
      } else {
         JValue jsonMetadata;
         label26: {
            String loadedClassName = (String)var6._1();
            String version = (String)var6._2();
            JValue jsonMetadata = (JValue)var6._3();
            Tuple3 var5 = new Tuple3(loadedClassName, version, jsonMetadata);
            String loadedClassName = (String)var5._1();
            String version = (String)var5._2();
            jsonMetadata = (JValue)var5._3();
            String classNameV1_0 = RandomForestModel.SaveLoadV1_0$.MODULE$.thisClassName();
            Tuple2 var14 = new Tuple2(loadedClassName, version);
            if (var14 != null) {
               String className = (String)var14._1();
               String var16 = (String)var14._2();
               if ("1.0".equals(var16)) {
                  if (className == null) {
                     if (classNameV1_0 == null) {
                        break label26;
                     }
                  } else if (className.equals(classNameV1_0)) {
                     break label26;
                  }
               }
            }

            throw new Exception("RandomForestModel.load did not recognize model with (className, format version): (" + loadedClassName + ", " + version + ").  Supported:\n  (" + classNameV1_0 + ", 1.0)");
         }

         TreeEnsembleModel$SaveLoadV1_0$Metadata metadata = TreeEnsembleModel.SaveLoadV1_0$.MODULE$.readMetadata(jsonMetadata);
         .MODULE$.assert(scala.collection.ArrayOps..MODULE$.forall$extension(.MODULE$.doubleArrayOps(metadata.treeWeights()), (JFunction1.mcZD.sp)(x$3) -> x$3 == (double)1.0F));
         DecisionTreeModel[] trees = TreeEnsembleModel.SaveLoadV1_0$.MODULE$.loadTrees(sc, path, metadata.treeAlgo());
         return new RandomForestModel(Algo$.MODULE$.fromString(metadata.algo()), trees);
      }
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(RandomForestModel$.class);
   }

   private RandomForestModel$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
