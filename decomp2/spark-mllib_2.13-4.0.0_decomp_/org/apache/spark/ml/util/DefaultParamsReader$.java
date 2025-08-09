package org.apache.spark.ml.util;

import java.lang.invoke.SerializedLambda;
import org.apache.hadoop.fs.Path;
import org.apache.spark.SparkContext;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.SparkSession.;
import org.json4s.DefaultFormats;
import org.json4s.JValue;
import scala.Predef;
import scala.runtime.BoxesRunTime;

public final class DefaultParamsReader$ {
   public static final DefaultParamsReader$ MODULE$ = new DefaultParamsReader$();

   /** @deprecated */
   public DefaultParamsReader.Metadata loadMetadata(final String path, final SparkContext sc, final String expectedClassName) {
      return this.loadMetadata(path, .MODULE$.builder().sparkContext(sc).getOrCreate(), expectedClassName);
   }

   public DefaultParamsReader.Metadata loadMetadata(final String path, final SparkSession spark, final String expectedClassName) {
      String metadataPath = (new Path(path, "metadata")).toString();
      String metadataStr = ((Row)spark.read().text(metadataPath).first()).getString(0);
      return this.parseMetadata(metadataStr, expectedClassName);
   }

   public DefaultParamsReader.Metadata loadMetadata(final String path, final SparkSession spark) {
      return this.loadMetadata(path, spark, "");
   }

   public String loadMetadata$default$3() {
      return "";
   }

   public DefaultParamsReader.Metadata parseMetadata(final String metadataStr, final String expectedClassName) {
      JValue metadata = org.json4s.jackson.JsonMethods..MODULE$.parse(metadataStr, org.json4s.jackson.JsonMethods..MODULE$.parse$default$2(), org.json4s.jackson.JsonMethods..MODULE$.parse$default$3(), org.json4s.AsJsonInput..MODULE$.stringAsJsonInput());
      DefaultFormats format = org.json4s.DefaultFormats..MODULE$;
      String className = (String)org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(metadata), "class")), format, scala.reflect.ManifestFactory..MODULE$.classType(String.class));
      String uid = (String)org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(metadata), "uid")), format, scala.reflect.ManifestFactory..MODULE$.classType(String.class));
      long timestamp = BoxesRunTime.unboxToLong(org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(metadata), "timestamp")), format, scala.reflect.ManifestFactory..MODULE$.Long()));
      String sparkVersion = (String)org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(metadata), "sparkVersion")), format, scala.reflect.ManifestFactory..MODULE$.classType(String.class));
      JValue defaultParams = org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(metadata), "defaultParamMap");
      JValue params = org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(metadata), "paramMap");
      if (scala.collection.StringOps..MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString(expectedClassName))) {
         Predef var10000;
         boolean var10001;
         label19: {
            label18: {
               var10000 = scala.Predef..MODULE$;
               if (className == null) {
                  if (expectedClassName == null) {
                     break label18;
                  }
               } else if (className.equals(expectedClassName)) {
                  break label18;
               }

               var10001 = false;
               break label19;
            }

            var10001 = true;
         }

         var10000.require(var10001, () -> "Error loading metadata: Expected class name " + expectedClassName + " but found class name " + className);
      }

      return new DefaultParamsReader.Metadata(className, uid, timestamp, sparkVersion, params, defaultParams, metadata, metadataStr);
   }

   public String parseMetadata$default$2() {
      return "";
   }

   /** @deprecated */
   public Object loadParamsInstance(final String path, final SparkContext sc) {
      return this.loadParamsInstance(path, .MODULE$.builder().sparkContext(sc).getOrCreate());
   }

   public Object loadParamsInstance(final String path, final SparkSession spark) {
      return this.loadParamsInstanceReader(path, spark).load(path);
   }

   /** @deprecated */
   public MLReader loadParamsInstanceReader(final String path, final SparkContext sc) {
      return this.loadParamsInstanceReader(path, .MODULE$.builder().sparkContext(sc).getOrCreate());
   }

   public MLReader loadParamsInstanceReader(final String path, final SparkSession spark) {
      DefaultParamsReader.Metadata metadata = this.loadMetadata(path, spark);
      Class cls = org.apache.spark.util.Utils..MODULE$.classForName(metadata.className(), org.apache.spark.util.Utils..MODULE$.classForName$default$2(), org.apache.spark.util.Utils..MODULE$.classForName$default$3());
      return (MLReader)cls.getMethod("read").invoke((Object)null);
   }

   private DefaultParamsReader$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
