package org.apache.spark.mllib.util;

import java.lang.invoke.SerializedLambda;
import org.apache.hadoop.fs.Path;
import org.apache.spark.SparkContext;
import org.apache.spark.sql.catalyst.ScalaReflection.;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import org.json4s.Formats;
import org.json4s.JValue;
import scala.Tuple3;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.Map;
import scala.reflect.api.TypeTags;
import scala.runtime.BoxedUnit;

public final class Loader$ {
   public static final Loader$ MODULE$ = new Loader$();

   public String dataPath(final String path) {
      return (new Path(path, "data")).toUri().toString();
   }

   public String metadataPath(final String path) {
      return (new Path(path, "metadata")).toUri().toString();
   }

   public void checkSchema(final StructType loadedSchema, final TypeTags.TypeTag evidence$1) {
      StructField[] expectedFields = ((StructType).MODULE$.schemaFor(evidence$1).dataType()).fields();
      Map loadedFields = ((IterableOnceOps)loadedSchema.map((field) -> scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(field.name()), field.dataType()))).toMap(scala..less.colon.less..MODULE$.refl());
      scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps((Object[])expectedFields), (field) -> {
         $anonfun$checkSchema$2(loadedFields, field);
         return BoxedUnit.UNIT;
      });
   }

   public Tuple3 loadMetadata(final SparkContext sc, final String path) {
      Formats formats = org.json4s.DefaultFormats..MODULE$;
      JValue metadata = org.json4s.jackson.JsonMethods..MODULE$.parse(sc.textFile(this.metadataPath(path), sc.textFile$default$2()).first(), org.json4s.jackson.JsonMethods..MODULE$.parse$default$2(), org.json4s.jackson.JsonMethods..MODULE$.parse$default$3(), org.json4s.AsJsonInput..MODULE$.stringAsJsonInput());
      String clazz = (String)org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(metadata), "class")), formats, scala.reflect.ManifestFactory..MODULE$.classType(String.class));
      String version = (String)org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(metadata), "version")), formats, scala.reflect.ManifestFactory..MODULE$.classType(String.class));
      return new Tuple3(clazz, version, metadata);
   }

   // $FF: synthetic method
   public static final void $anonfun$checkSchema$2(final Map loadedFields$1, final StructField field) {
      scala.Predef..MODULE$.assert(loadedFields$1.contains(field.name()), () -> {
         String var10000 = field.name();
         return "Unable to parse model data.  Expected field with name " + var10000 + " was missing in loaded schema: " + loadedFields$1.mkString(", ");
      });
      scala.Predef..MODULE$.assert(org.apache.spark.sql.catalyst.types.DataTypeUtils..MODULE$.sameType((DataType)loadedFields$1.apply(field.name()), field.dataType()), () -> "Unable to parse model data.  Expected field " + field + " but found field with different type: " + loadedFields$1.apply(field.name()));
   }

   private Loader$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
