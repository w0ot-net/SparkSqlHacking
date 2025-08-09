package org.apache.spark.mllib.feature;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkContext;
import org.apache.spark.mllib.util.Loader;
import org.apache.spark.mllib.util.Loader$;
import org.json4s.DefaultFormats;
import org.json4s.JValue;
import scala.MatchError;
import scala.Tuple2;
import scala.Tuple3;
import scala.Predef.;
import scala.collection.immutable.Map;
import scala.collection.mutable.Builder;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class Word2VecModel$ implements Loader, Serializable {
   public static final Word2VecModel$ MODULE$ = new Word2VecModel$();

   public Tuple2 org$apache$spark$mllib$feature$Word2VecModel$$buildFromVecMap(final Map model) {
      .MODULE$.require(model.nonEmpty(), () -> "Word2VecMap should be non-empty");
      Tuple2.mcII.sp var4 = new Tuple2.mcII.sp(((float[])((Tuple2)model.head())._2()).length, model.size());
      if (var4 != null) {
         int vectorSize = ((Tuple2)var4)._1$mcI$sp();
         int numWords = ((Tuple2)var4)._2$mcI$sp();
         Tuple2.mcII.sp var3 = new Tuple2.mcII.sp(vectorSize, numWords);
         int vectorSize = ((Tuple2)var3)._1$mcI$sp();
         int numWords = ((Tuple2)var3)._2$mcI$sp();
         float[] wordVectors = new float[vectorSize * numWords];
         Builder wordIndex = scala.collection.immutable.Map..MODULE$.newBuilder();
         wordIndex.sizeHint(numWords);
         model.iterator().zipWithIndex().foreach((x0$1) -> {
            $anonfun$buildFromVecMap$2(wordIndex, wordVectors, vectorSize, x0$1);
            return BoxedUnit.UNIT;
         });
         return new Tuple2(wordIndex.result(), wordVectors);
      } else {
         throw new MatchError(var4);
      }
   }

   public Word2VecModel load(final SparkContext sc, final String path) {
      Tuple3 var6 = Loader$.MODULE$.loadMetadata(sc, path);
      if (var6 != null) {
         String loadedClassName = (String)var6._1();
         String loadedVersion = (String)var6._2();
         JValue metadata = (JValue)var6._3();
         Tuple3 var5 = new Tuple3(loadedClassName, loadedVersion, metadata);
         String loadedClassName = (String)var5._1();
         String loadedVersion = (String)var5._2();
         JValue metadata = (JValue)var5._3();
         DefaultFormats formats = org.json4s.DefaultFormats..MODULE$;
         int expectedVectorSize = BoxesRunTime.unboxToInt(org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(metadata), "vectorSize")), formats, scala.reflect.ManifestFactory..MODULE$.Int()));
         int expectedNumWords = BoxesRunTime.unboxToInt(org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(metadata), "numWords")), formats, scala.reflect.ManifestFactory..MODULE$.Int()));
         String classNameV1_0 = Word2VecModel.SaveLoadV1_0$.MODULE$.classNameV1_0();
         Tuple2 var17 = new Tuple2(loadedClassName, loadedVersion);
         if (var17 != null) {
            String var18 = (String)var17._2();
            if ("1.0".equals(var18)) {
               Word2VecModel model = Word2VecModel.SaveLoadV1_0$.MODULE$.load(sc, path);
               int vectorSize = ((float[])model.getVectors().values().head()).length;
               int numWords = model.getVectors().size();
               .MODULE$.require(expectedVectorSize == vectorSize, () -> "Word2VecModel requires each word to be mapped to a vector of size " + expectedVectorSize + ", got vector of size " + vectorSize);
               .MODULE$.require(expectedNumWords == numWords, () -> "Word2VecModel requires " + expectedNumWords + " words, but got " + numWords);
               return model;
            }
         }

         throw new Exception("Word2VecModel.load did not recognize model with (className, format version):(" + loadedClassName + ", " + loadedVersion + ").  Supported:\n  (" + classNameV1_0 + ", 1.0)");
      } else {
         throw new MatchError(var6);
      }
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Word2VecModel$.class);
   }

   // $FF: synthetic method
   public static final void $anonfun$buildFromVecMap$2(final Builder wordIndex$1, final float[] wordVectors$1, final int vectorSize$2, final Tuple2 x0$1) {
      if (x0$1 != null) {
         Tuple2 var6 = (Tuple2)x0$1._1();
         int i = x0$1._2$mcI$sp();
         if (var6 != null) {
            String word = (String)var6._1();
            float[] vector = (float[])var6._2();
            wordIndex$1.$plus$eq(new Tuple2(word, BoxesRunTime.boxToInteger(i)));
            scala.Array..MODULE$.copy(vector, 0, wordVectors$1, i * vectorSize$2, vectorSize$2);
            BoxedUnit var10000 = BoxedUnit.UNIT;
            return;
         }
      }

      throw new MatchError(x0$1);
   }

   private Word2VecModel$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
