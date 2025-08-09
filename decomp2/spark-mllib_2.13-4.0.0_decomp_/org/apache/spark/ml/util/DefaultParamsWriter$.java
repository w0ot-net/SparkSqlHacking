package org.apache.spark.ml.util;

import java.lang.invoke.SerializedLambda;
import org.apache.hadoop.fs.Path;
import org.apache.spark.SparkContext;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.Params;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.SparkSession.;
import org.json4s.JObject;
import org.json4s.JValue;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple1;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.Seq;
import scala.reflect.api.JavaUniverse;
import scala.reflect.api.Mirror;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;
import scala.runtime.BoxesRunTime;

public final class DefaultParamsWriter$ {
   public static final DefaultParamsWriter$ MODULE$ = new DefaultParamsWriter$();

   /** @deprecated */
   public void saveMetadata(final Params instance, final String path, final SparkContext sc, final Option extraMetadata, final Option paramMap) {
      this.saveMetadata(instance, path, .MODULE$.builder().sparkContext(sc).getOrCreate(), extraMetadata, paramMap);
   }

   public void saveMetadata(final Params instance, final String path, final SparkSession spark, final Option extraMetadata, final Option paramMap) {
      String metadataPath = (new Path(path, "metadata")).toString();
      String metadataJson = this.getMetadataToSave(instance, spark, extraMetadata, paramMap);
      scala.collection.immutable..colon.colon var10001 = new scala.collection.immutable..colon.colon(new Tuple1(metadataJson), scala.collection.immutable.Nil..MODULE$);
      JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
      JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(this.getClass().getClassLoader());

      final class $typecreator1$1 extends TypeCreator {
         public Types.TypeApi apply(final Mirror $m$untyped) {
            Universe $u = $m$untyped.universe();
            return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala").asModule().moduleClass()), $m$untyped.staticClass("scala.Tuple1"), new scala.collection.immutable..colon.colon($u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().SingleType($m$untyped.staticPackage("scala").asModule().moduleClass().asType().toTypeConstructor(), $m$untyped.staticModule("scala.Predef")), $u.internal().reificationSupport().selectType($m$untyped.staticModule("scala.Predef").asModule().moduleClass(), "String"), scala.collection.immutable.Nil..MODULE$), scala.collection.immutable.Nil..MODULE$));
         }

         public $typecreator1$1() {
         }
      }

      spark.createDataFrame(var10001, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$1())).write().text(metadataPath);
   }

   public void saveMetadata(final Params instance, final String path, final SparkSession spark, final Option extraMetadata) {
      this.saveMetadata(instance, path, (SparkSession)spark, extraMetadata, scala.None..MODULE$);
   }

   public void saveMetadata(final Params instance, final String path, final SparkSession spark) {
      this.saveMetadata(instance, path, (SparkSession)spark, scala.None..MODULE$, scala.None..MODULE$);
   }

   public Option saveMetadata$default$4() {
      return scala.None..MODULE$;
   }

   public Option saveMetadata$default$5() {
      return scala.None..MODULE$;
   }

   /** @deprecated */
   public String getMetadataToSave(final Params instance, final SparkContext sc, final Option extraMetadata, final Option paramMap) {
      return this.getMetadataToSave(instance, .MODULE$.builder().sparkContext(sc).getOrCreate(), extraMetadata, paramMap);
   }

   public String getMetadataToSave(final Params instance, final SparkSession spark, final Option extraMetadata, final Option paramMap) {
      String uid = instance.uid();
      String cls = instance.getClass().getName();
      Seq params = instance.paramMap().toSeq();
      Seq defaultParams = instance.defaultParamMap().toSeq();
      JValue jsonParams = (JValue)paramMap.getOrElse(() -> org.json4s.jackson.JsonMethods..MODULE$.render(org.json4s.JsonDSL..MODULE$.list2jvalue(((IterableOnceOps)params.map((x0$1) -> {
            if (x0$1 != null) {
               Param p = x0$1.param();
               Object v = x0$1.value();
               return scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(p.name()), org.json4s.jackson.JsonMethods..MODULE$.parse(p.jsonEncode(v), org.json4s.jackson.JsonMethods..MODULE$.parse$default$2(), org.json4s.jackson.JsonMethods..MODULE$.parse$default$3(), org.json4s.AsJsonInput..MODULE$.stringAsJsonInput()));
            } else {
               throw new MatchError(x0$1);
            }
         })).toList()), org.json4s.jackson.JsonMethods..MODULE$.render$default$2(), org.json4s.jackson.JsonMethods..MODULE$.render$default$3()));
      JValue jsonDefaultParams = org.json4s.jackson.JsonMethods..MODULE$.render(org.json4s.JsonDSL..MODULE$.list2jvalue(((IterableOnceOps)defaultParams.map((x0$2) -> {
         if (x0$2 != null) {
            Param p = x0$2.param();
            Object v = x0$2.value();
            return scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(p.name()), org.json4s.jackson.JsonMethods..MODULE$.parse(p.jsonEncode(v), org.json4s.jackson.JsonMethods..MODULE$.parse$default$2(), org.json4s.jackson.JsonMethods..MODULE$.parse$default$3(), org.json4s.AsJsonInput..MODULE$.stringAsJsonInput()));
         } else {
            throw new MatchError(x0$2);
         }
      })).toList()), org.json4s.jackson.JsonMethods..MODULE$.render$default$2(), org.json4s.jackson.JsonMethods..MODULE$.render$default$3());
      JObject basicMetadata = org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.pair2Assoc(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("class"), cls), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("timestamp"), BoxesRunTime.boxToLong(System.currentTimeMillis())), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x), (x) -> $anonfun$getMetadataToSave$6(BoxesRunTime.unboxToLong(x)))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("sparkVersion"), spark.version()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("uid"), uid), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)))), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("paramMap"), jsonParams))), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("defaultParamMap"), jsonDefaultParams));
      JObject var10000;
      if (extraMetadata instanceof Some var15) {
         JObject jObject = (JObject)var15.value();
         var10000 = org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(basicMetadata), jObject);
      } else {
         if (!scala.None..MODULE$.equals(extraMetadata)) {
            throw new MatchError(extraMetadata);
         }

         var10000 = basicMetadata;
      }

      JObject metadata = var10000;
      String metadataJson = org.json4s.jackson.JsonMethods..MODULE$.compact(org.json4s.jackson.JsonMethods..MODULE$.render(metadata, org.json4s.jackson.JsonMethods..MODULE$.render$default$2(), org.json4s.jackson.JsonMethods..MODULE$.render$default$3()));
      return metadataJson;
   }

   public String getMetadataToSave(final Params instance, final SparkSession spark, final Option extraMetadata) {
      return this.getMetadataToSave(instance, (SparkSession)spark, extraMetadata, scala.None..MODULE$);
   }

   public String getMetadataToSave(final Params instance, final SparkSession spark) {
      return this.getMetadataToSave(instance, (SparkSession)spark, scala.None..MODULE$, scala.None..MODULE$);
   }

   public Option getMetadataToSave$default$3() {
      return scala.None..MODULE$;
   }

   public Option getMetadataToSave$default$4() {
      return scala.None..MODULE$;
   }

   // $FF: synthetic method
   public static final JValue $anonfun$getMetadataToSave$6(final long x) {
      return org.json4s.JsonDSL..MODULE$.long2jvalue(x);
   }

   private DefaultParamsWriter$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
