package org.apache.spark.mllib.regression;

import java.io.Serializable;
import org.json4s.Formats;
import org.json4s.JValue;
import org.json4s.DefaultFormats.;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class RegressionModel$ implements Serializable {
   public static final RegressionModel$ MODULE$ = new RegressionModel$();

   public int getNumFeatures(final JValue metadata) {
      Formats formats = .MODULE$;
      return BoxesRunTime.unboxToInt(org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(metadata), "numFeatures")), formats, scala.reflect.ManifestFactory..MODULE$.Int()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(RegressionModel$.class);
   }

   private RegressionModel$() {
   }
}
