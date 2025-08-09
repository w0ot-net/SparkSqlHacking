package org.apache.spark.mllib.classification;

import java.io.Serializable;
import org.json4s.Formats;
import org.json4s.JValue;
import org.json4s.DefaultFormats.;
import scala.Tuple2;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class ClassificationModel$ implements Serializable {
   public static final ClassificationModel$ MODULE$ = new ClassificationModel$();

   public Tuple2 getNumFeaturesClasses(final JValue metadata) {
      Formats formats = .MODULE$;
      return new Tuple2.mcII.sp(BoxesRunTime.unboxToInt(org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(metadata), "numFeatures")), formats, scala.reflect.ManifestFactory..MODULE$.Int())), BoxesRunTime.unboxToInt(org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(metadata), "numClasses")), formats, scala.reflect.ManifestFactory..MODULE$.Int())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ClassificationModel$.class);
   }

   private ClassificationModel$() {
   }
}
