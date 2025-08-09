package org.apache.spark.mllib.clustering;

import java.io.Serializable;
import org.apache.spark.SparkContext;
import org.apache.spark.mllib.util.Loader;
import org.apache.spark.mllib.util.Loader$;
import org.json4s.JValue;
import scala.MatchError;
import scala.Tuple2;
import scala.Tuple3;
import scala.runtime.ModuleSerializationProxy;

public final class KMeansModel$ implements Loader, Serializable {
   public static final KMeansModel$ MODULE$ = new KMeansModel$();

   public KMeansModel load(final SparkContext sc, final String path) {
      Tuple3 var6 = Loader$.MODULE$.loadMetadata(sc, path);
      if (var6 == null) {
         throw new MatchError(var6);
      } else {
         String loadedClassName = (String)var6._1();
         String version = (String)var6._2();
         JValue metadata = (JValue)var6._3();
         Tuple3 var5 = new Tuple3(loadedClassName, version, metadata);
         String loadedClassName = (String)var5._1();
         String version = (String)var5._2();
         JValue var12 = (JValue)var5._3();
         String classNameV1_0 = KMeansModel.SaveLoadV1_0$.MODULE$.thisClassName();
         String classNameV2_0 = KMeansModel.SaveLoadV2_0$.MODULE$.thisClassName();
         Tuple2 var15 = new Tuple2(loadedClassName, version);
         if (var15 != null) {
            String className = (String)var15._1();
            String var17 = (String)var15._2();
            if ("1.0".equals(var17)) {
               if (className == null) {
                  if (classNameV1_0 == null) {
                     return KMeansModel.SaveLoadV1_0$.MODULE$.load(sc, path);
                  }
               } else if (className.equals(classNameV1_0)) {
                  return KMeansModel.SaveLoadV1_0$.MODULE$.load(sc, path);
               }
            }
         }

         if (var15 != null) {
            String className = (String)var15._1();
            String var20 = (String)var15._2();
            if ("2.0".equals(var20)) {
               if (className == null) {
                  if (classNameV2_0 == null) {
                     return KMeansModel.SaveLoadV2_0$.MODULE$.load(sc, path);
                  }
               } else if (className.equals(classNameV2_0)) {
                  return KMeansModel.SaveLoadV2_0$.MODULE$.load(sc, path);
               }
            }
         }

         throw new Exception("KMeansModel.load did not recognize model with (className, format version):(" + loadedClassName + ", " + version + ").  Supported:\n  (" + classNameV1_0 + ", 1.0)\n  (" + classNameV2_0 + ", 2.0)");
      }
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(KMeansModel$.class);
   }

   private KMeansModel$() {
   }
}
