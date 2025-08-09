package org.apache.spark.mllib.clustering;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkContext;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors$;
import org.apache.spark.mllib.util.Loader;
import org.apache.spark.mllib.util.Loader$;
import org.json4s.DefaultFormats;
import org.json4s.JValue;
import scala.MatchError;
import scala.Predef;
import scala.Tuple2;
import scala.Tuple3;
import scala.Array.;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.Seq;
import scala.runtime.BoxesRunTime;

public final class DistributedLDAModel$ implements Loader {
   public static final DistributedLDAModel$ MODULE$ = new DistributedLDAModel$();
   private static final double defaultGammaShape = (double)100.0F;

   public double $lessinit$greater$default$8() {
      return this.defaultGammaShape();
   }

   public String[] $lessinit$greater$default$9() {
      return (String[]).MODULE$.empty(scala.reflect.ClassTag..MODULE$.apply(String.class));
   }

   public double defaultGammaShape() {
      return defaultGammaShape;
   }

   public DistributedLDAModel load(final SparkContext sc, final String path) {
      Tuple3 var6 = Loader$.MODULE$.loadMetadata(sc, path);
      if (var6 == null) {
         throw new MatchError(var6);
      } else {
         String loadedClassName = (String)var6._1();
         String loadedVersion = (String)var6._2();
         JValue metadata = (JValue)var6._3();
         Tuple3 var5 = new Tuple3(loadedClassName, loadedVersion, metadata);
         String loadedClassName = (String)var5._1();
         String loadedVersion = (String)var5._2();
         JValue metadata = (JValue)var5._3();
         DefaultFormats formats = org.json4s.DefaultFormats..MODULE$;
         int expectedK = BoxesRunTime.unboxToInt(org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(metadata), "k")), formats, scala.reflect.ManifestFactory..MODULE$.Int()));
         int vocabSize = BoxesRunTime.unboxToInt(org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(metadata), "vocabSize")), formats, scala.reflect.ManifestFactory..MODULE$.Int()));
         Vector docConcentration = Vectors$.MODULE$.dense((double[])((IterableOnceOps)org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(metadata), "docConcentration")), formats, scala.reflect.ManifestFactory..MODULE$.classType(Seq.class, scala.reflect.ManifestFactory..MODULE$.Double(), scala.collection.immutable.Nil..MODULE$))).toArray(scala.reflect.ClassTag..MODULE$.Double()));
         double topicConcentration = BoxesRunTime.unboxToDouble(org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(metadata), "topicConcentration")), formats, scala.reflect.ManifestFactory..MODULE$.Double()));
         Seq iterationTimes = (Seq)org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(metadata), "iterationTimes")), formats, scala.reflect.ManifestFactory..MODULE$.classType(Seq.class, scala.reflect.ManifestFactory..MODULE$.Double(), scala.collection.immutable.Nil..MODULE$));
         double gammaShape = BoxesRunTime.unboxToDouble(org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(metadata), "gammaShape")), formats, scala.reflect.ManifestFactory..MODULE$.Double()));
         String classNameV1_0 = DistributedLDAModel.SaveLoadV1_0$.MODULE$.thisClassName();
         Tuple2 var24 = new Tuple2(loadedClassName, loadedVersion);
         if (var24 != null) {
            String className = (String)var24._1();
            String var26 = (String)var24._2();
            if ("1.0".equals(var26)) {
               if (className == null) {
                  if (classNameV1_0 != null) {
                     throw new Exception("DistributedLDAModel.load did not recognize model with (className, format version):(" + loadedClassName + ", " + loadedVersion + ").  Supported: (" + classNameV1_0 + ", 1.0)");
                  }
               } else if (!className.equals(classNameV1_0)) {
                  throw new Exception("DistributedLDAModel.load did not recognize model with (className, format version):(" + loadedClassName + ", " + loadedVersion + ").  Supported: (" + classNameV1_0 + ", 1.0)");
               }

               DistributedLDAModel model;
               boolean var29;
               Predef var10000;
               label46: {
                  label45: {
                     model = DistributedLDAModel.SaveLoadV1_0$.MODULE$.load(sc, path, vocabSize, docConcentration, topicConcentration, (double[])iterationTimes.toArray(scala.reflect.ClassTag..MODULE$.Double()), gammaShape);
                     scala.Predef..MODULE$.require(model.vocabSize() == vocabSize, () -> "DistributedLDAModel requires " + vocabSize + " vocabSize, got " + model.vocabSize() + " vocabSize");
                     var10000 = scala.Predef..MODULE$;
                     Vector var10001 = model.docConcentration();
                     if (var10001 == null) {
                        if (docConcentration == null) {
                           break label45;
                        }
                     } else if (var10001.equals(docConcentration)) {
                        break label45;
                     }

                     var29 = false;
                     break label46;
                  }

                  var29 = true;
               }

               var10000.require(var29, () -> "DistributedLDAModel requires " + docConcentration + " docConcentration, got " + model.docConcentration() + " docConcentration");
               scala.Predef..MODULE$.require(model.topicConcentration() == topicConcentration, () -> "DistributedLDAModel requires " + topicConcentration + " docConcentration, got " + model.topicConcentration() + " docConcentration");
               scala.Predef..MODULE$.require(expectedK == model.k(), () -> "DistributedLDAModel requires " + expectedK + " topics, got " + model.k() + " topics");
               return model;
            }
         }

         throw new Exception("DistributedLDAModel.load did not recognize model with (className, format version):(" + loadedClassName + ", " + loadedVersion + ").  Supported: (" + classNameV1_0 + ", 1.0)");
      }
   }

   private DistributedLDAModel$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
