package org.apache.spark.ml.linalg;

import java.lang.invoke.SerializedLambda;
import org.json4s.Formats;
import org.json4s.JObject;
import org.json4s.JValue;
import org.json4s.DefaultFormats.;
import scala.Option;
import scala.Tuple3;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.Seq;
import scala.runtime.BoxesRunTime;

public final class JsonVectorConverter$ {
   public static final JsonVectorConverter$ MODULE$ = new JsonVectorConverter$();

   public Vector fromJson(final String json) {
      Formats formats = .MODULE$;
      JValue jValue = org.json4s.jackson.JsonMethods..MODULE$.parse(json, org.json4s.jackson.JsonMethods..MODULE$.parse$default$2(), org.json4s.jackson.JsonMethods..MODULE$.parse$default$3(), org.json4s.AsJsonInput..MODULE$.stringAsJsonInput());
      int var4 = BoxesRunTime.unboxToInt(org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(jValue), "type")), formats, scala.reflect.ManifestFactory..MODULE$.Int()));
      switch (var4) {
         case 0:
            int size = BoxesRunTime.unboxToInt(org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(jValue), "size")), formats, scala.reflect.ManifestFactory..MODULE$.Int()));
            int[] indices = (int[])((IterableOnceOps)org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(jValue), "indices")), formats, scala.reflect.ManifestFactory..MODULE$.classType(Seq.class, scala.reflect.ManifestFactory..MODULE$.Int(), scala.collection.immutable.Nil..MODULE$))).toArray(scala.reflect.ClassTag..MODULE$.Int());
            double[] values = (double[])((IterableOnceOps)org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(jValue), "values")), formats, scala.reflect.ManifestFactory..MODULE$.classType(Seq.class, scala.reflect.ManifestFactory..MODULE$.Double(), scala.collection.immutable.Nil..MODULE$))).toArray(scala.reflect.ClassTag..MODULE$.Double());
            return org.apache.spark.ml.linalg.Vectors..MODULE$.sparse(size, indices, values);
         case 1:
            double[] values = (double[])((IterableOnceOps)org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(jValue), "values")), formats, scala.reflect.ManifestFactory..MODULE$.classType(Seq.class, scala.reflect.ManifestFactory..MODULE$.Double(), scala.collection.immutable.Nil..MODULE$))).toArray(scala.reflect.ClassTag..MODULE$.Double());
            return org.apache.spark.ml.linalg.Vectors..MODULE$.dense(values);
         default:
            throw new IllegalArgumentException("Cannot parse " + json + " into a vector.");
      }
   }

   public String toJson(final Vector v) {
      if (v instanceof SparseVector var4) {
         Option var5 = org.apache.spark.ml.linalg.SparseVector..MODULE$.unapply(var4);
         if (!var5.isEmpty()) {
            int size = BoxesRunTime.unboxToInt(((Tuple3)var5.get())._1());
            int[] indices = (int[])((Tuple3)var5.get())._2();
            double[] values = (double[])((Tuple3)var5.get())._3();
            JObject jValue = org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.pair2Assoc(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("type"), BoxesRunTime.boxToInteger(0)), (x) -> $anonfun$toJson$1(BoxesRunTime.unboxToInt(x))), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("size"), BoxesRunTime.boxToInteger(size)), (x) -> $anonfun$toJson$2(BoxesRunTime.unboxToInt(x)), (x) -> $anonfun$toJson$3(BoxesRunTime.unboxToInt(x)))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("indices"), org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(indices).toImmutableArraySeq()), (s) -> org.json4s.JsonDSL..MODULE$.seq2jvalue(s, (x) -> $anonfun$toJson$5(BoxesRunTime.unboxToInt(x)))))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("values"), org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(values).toImmutableArraySeq()), (s) -> org.json4s.JsonDSL..MODULE$.seq2jvalue(s, (x) -> $anonfun$toJson$7(BoxesRunTime.unboxToDouble(x)))));
            return org.json4s.jackson.JsonMethods..MODULE$.compact(org.json4s.jackson.JsonMethods..MODULE$.render(jValue, org.json4s.jackson.JsonMethods..MODULE$.render$default$2(), org.json4s.jackson.JsonMethods..MODULE$.render$default$3()));
         }
      }

      if (v instanceof DenseVector var10) {
         Option var11 = org.apache.spark.ml.linalg.DenseVector..MODULE$.unapply(var10);
         if (!var11.isEmpty()) {
            double[] values = (double[])var11.get();
            JObject jValue = org.json4s.JsonAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.pair2Assoc(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("type"), BoxesRunTime.boxToInteger(1)), (x) -> $anonfun$toJson$8(BoxesRunTime.unboxToInt(x))), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("values"), org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(values).toImmutableArraySeq()), (x) -> $anonfun$toJson$9(BoxesRunTime.unboxToInt(x)), (s) -> org.json4s.JsonDSL..MODULE$.seq2jvalue(s, (x) -> $anonfun$toJson$11(BoxesRunTime.unboxToDouble(x))));
            return org.json4s.jackson.JsonMethods..MODULE$.compact(org.json4s.jackson.JsonMethods..MODULE$.render(jValue, org.json4s.jackson.JsonMethods..MODULE$.render$default$2(), org.json4s.jackson.JsonMethods..MODULE$.render$default$3()));
         }
      }

      throw new IllegalArgumentException("Unknown vector type " + v.getClass() + ".");
   }

   // $FF: synthetic method
   public static final JValue $anonfun$toJson$1(final int x) {
      return org.json4s.JsonDSL..MODULE$.int2jvalue(x);
   }

   // $FF: synthetic method
   public static final JValue $anonfun$toJson$2(final int x) {
      return org.json4s.JsonDSL..MODULE$.int2jvalue(x);
   }

   // $FF: synthetic method
   public static final JValue $anonfun$toJson$3(final int x) {
      return org.json4s.JsonDSL..MODULE$.int2jvalue(x);
   }

   // $FF: synthetic method
   public static final JValue $anonfun$toJson$5(final int x) {
      return org.json4s.JsonDSL..MODULE$.int2jvalue(x);
   }

   // $FF: synthetic method
   public static final JValue $anonfun$toJson$7(final double x) {
      return org.json4s.JsonDSL..MODULE$.double2jvalue(x);
   }

   // $FF: synthetic method
   public static final JValue $anonfun$toJson$8(final int x) {
      return org.json4s.JsonDSL..MODULE$.int2jvalue(x);
   }

   // $FF: synthetic method
   public static final JValue $anonfun$toJson$9(final int x) {
      return org.json4s.JsonDSL..MODULE$.int2jvalue(x);
   }

   // $FF: synthetic method
   public static final JValue $anonfun$toJson$11(final double x) {
      return org.json4s.JsonDSL..MODULE$.double2jvalue(x);
   }

   private JsonVectorConverter$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
