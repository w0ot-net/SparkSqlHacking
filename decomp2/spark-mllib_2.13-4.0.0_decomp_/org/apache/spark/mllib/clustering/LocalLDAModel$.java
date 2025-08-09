package org.apache.spark.mllib.clustering;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkContext;
import org.apache.spark.mllib.linalg.Matrix;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors$;
import org.apache.spark.mllib.util.Loader;
import org.apache.spark.mllib.util.Loader$;
import org.json4s.DefaultFormats;
import org.json4s.JValue;
import org.json4s.DefaultFormats.;
import scala.MatchError;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.Seq;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class LocalLDAModel$ implements Loader, Serializable {
   public static final LocalLDAModel$ MODULE$ = new LocalLDAModel$();

   public double $lessinit$greater$default$4() {
      return (double)100.0F;
   }

   public LocalLDAModel load(final SparkContext sc, final String path) {
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
         DefaultFormats formats = .MODULE$;
         int expectedK = BoxesRunTime.unboxToInt(org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(metadata), "k")), formats, scala.reflect.ManifestFactory..MODULE$.Int()));
         int expectedVocabSize = BoxesRunTime.unboxToInt(org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(metadata), "vocabSize")), formats, scala.reflect.ManifestFactory..MODULE$.Int()));
         Vector docConcentration = Vectors$.MODULE$.dense((double[])((IterableOnceOps)org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(metadata), "docConcentration")), formats, scala.reflect.ManifestFactory..MODULE$.classType(Seq.class, scala.reflect.ManifestFactory..MODULE$.Double(), scala.collection.immutable.Nil..MODULE$))).toArray(scala.reflect.ClassTag..MODULE$.Double()));
         double topicConcentration = BoxesRunTime.unboxToDouble(org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(metadata), "topicConcentration")), formats, scala.reflect.ManifestFactory..MODULE$.Double()));
         double gammaShape = BoxesRunTime.unboxToDouble(org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(metadata), "gammaShape")), formats, scala.reflect.ManifestFactory..MODULE$.Double()));
         String classNameV1_0 = LocalLDAModel.SaveLoadV1_0$.MODULE$.thisClassName();
         Tuple2 var23 = new Tuple2(loadedClassName, loadedVersion);
         if (var23 != null) {
            String className = (String)var23._1();
            String var25 = (String)var23._2();
            if ("1.0".equals(var25)) {
               if (className == null) {
                  if (classNameV1_0 != null) {
                     throw new Exception("LocalLDAModel.load did not recognize model with (className, format version):(" + loadedClassName + ", " + loadedVersion + ").  Supported:\n  (" + classNameV1_0 + ", 1.0)");
                  }
               } else if (!className.equals(classNameV1_0)) {
                  throw new Exception("LocalLDAModel.load did not recognize model with (className, format version):(" + loadedClassName + ", " + loadedVersion + ").  Supported:\n  (" + classNameV1_0 + ", 1.0)");
               }

               LocalLDAModel model = LocalLDAModel.SaveLoadV1_0$.MODULE$.load(sc, path, docConcentration, topicConcentration, gammaShape);
               Matrix topicsMatrix = model.topicsMatrix();
               scala.Predef..MODULE$.require(expectedK == topicsMatrix.numCols(), () -> "LocalLDAModel requires " + expectedK + " topics, got " + topicsMatrix.numCols() + " topics");
               scala.Predef..MODULE$.require(expectedVocabSize == topicsMatrix.numRows(), () -> "LocalLDAModel requires " + expectedVocabSize + " terms for each topic, but got " + topicsMatrix.numRows());
               return model;
            }
         }

         throw new Exception("LocalLDAModel.load did not recognize model with (className, format version):(" + loadedClassName + ", " + loadedVersion + ").  Supported:\n  (" + classNameV1_0 + ", 1.0)");
      }
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(LocalLDAModel$.class);
   }

   private LocalLDAModel$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
