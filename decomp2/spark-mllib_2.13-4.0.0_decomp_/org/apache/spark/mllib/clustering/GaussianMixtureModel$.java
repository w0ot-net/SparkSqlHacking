package org.apache.spark.mllib.clustering;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkContext;
import org.apache.spark.mllib.util.Loader;
import org.apache.spark.mllib.util.Loader$;
import org.json4s.DefaultFormats;
import org.json4s.JValue;
import org.json4s.DefaultFormats.;
import scala.MatchError;
import scala.Tuple2;
import scala.Tuple3;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class GaussianMixtureModel$ implements Loader, Serializable {
   public static final GaussianMixtureModel$ MODULE$ = new GaussianMixtureModel$();

   public GaussianMixtureModel load(final SparkContext sc, final String path) {
      Tuple3 var6 = Loader$.MODULE$.loadMetadata(sc, path);
      if (var6 != null) {
         String loadedClassName = (String)var6._1();
         String version = (String)var6._2();
         JValue metadata = (JValue)var6._3();
         Tuple3 var5 = new Tuple3(loadedClassName, version, metadata);
         String loadedClassName = (String)var5._1();
         String version = (String)var5._2();
         JValue metadata = (JValue)var5._3();
         DefaultFormats formats = .MODULE$;
         int k = BoxesRunTime.unboxToInt(org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(metadata), "k")), formats, scala.reflect.ManifestFactory..MODULE$.Int()));
         String classNameV1_0 = GaussianMixtureModel.SaveLoadV1_0$.MODULE$.classNameV1_0();
         Tuple2 var16 = new Tuple2(loadedClassName, version);
         if (var16 != null) {
            String var17 = (String)var16._2();
            if ("1.0".equals(var17)) {
               GaussianMixtureModel model = GaussianMixtureModel.SaveLoadV1_0$.MODULE$.load(sc, path);
               scala.Predef..MODULE$.require(model.weights().length == k, () -> "GaussianMixtureModel requires weights of length " + k + " got weights of length " + model.weights().length);
               scala.Predef..MODULE$.require(model.gaussians().length == k, () -> "GaussianMixtureModel requires gaussians of length " + k + " got gaussians of length " + model.gaussians().length);
               return model;
            }
         }

         throw new Exception("GaussianMixtureModel.load did not recognize model with (className, format version):(" + loadedClassName + ", " + version + ").  Supported:\n  (" + classNameV1_0 + ", 1.0)");
      } else {
         throw new MatchError(var6);
      }
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(GaussianMixtureModel$.class);
   }

   private GaussianMixtureModel$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
