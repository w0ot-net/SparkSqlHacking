package org.apache.spark.mllib.regression;

import java.io.Serializable;
import org.apache.spark.SparkContext;
import org.apache.spark.mllib.regression.impl.GLMRegressionModel;
import org.apache.spark.mllib.regression.impl.GLMRegressionModel$SaveLoadV1_0$Data;
import org.apache.spark.mllib.util.Loader;
import org.apache.spark.mllib.util.Loader$;
import org.json4s.JValue;
import scala.MatchError;
import scala.Tuple2;
import scala.Tuple3;
import scala.runtime.ModuleSerializationProxy;

public final class RidgeRegressionModel$ implements Loader, Serializable {
   public static final RidgeRegressionModel$ MODULE$ = new RidgeRegressionModel$();

   public RidgeRegressionModel load(final SparkContext sc, final String path) {
      Tuple3 var6 = Loader$.MODULE$.loadMetadata(sc, path);
      if (var6 == null) {
         throw new MatchError(var6);
      } else {
         JValue metadata;
         String classNameV1_0;
         label26: {
            String loadedClassName = (String)var6._1();
            String version = (String)var6._2();
            JValue metadata = (JValue)var6._3();
            Tuple3 var5 = new Tuple3(loadedClassName, version, metadata);
            String loadedClassName = (String)var5._1();
            String version = (String)var5._2();
            metadata = (JValue)var5._3();
            classNameV1_0 = "org.apache.spark.mllib.regression.RidgeRegressionModel";
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

            throw new Exception("RidgeRegressionModel.load did not recognize model with (className, format version):(" + loadedClassName + ", " + version + ").  Supported:\n  (" + classNameV1_0 + ", 1.0)");
         }

         int numFeatures = RegressionModel$.MODULE$.getNumFeatures(metadata);
         GLMRegressionModel$SaveLoadV1_0$Data data = GLMRegressionModel.SaveLoadV1_0$.MODULE$.loadData(sc, path, classNameV1_0, numFeatures);
         return new RidgeRegressionModel(data.weights(), data.intercept());
      }
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(RidgeRegressionModel$.class);
   }

   private RidgeRegressionModel$() {
   }
}
