package org.apache.spark.ml.linalg;

import java.lang.invoke.SerializedLambda;
import org.json4s.Formats;
import org.json4s.JObject;
import org.json4s.JValue;
import org.json4s.DefaultFormats.;
import scala.Option;
import scala.Tuple4;
import scala.Tuple6;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.Seq;
import scala.runtime.BoxesRunTime;

public final class JsonMatrixConverter$ {
   public static final JsonMatrixConverter$ MODULE$ = new JsonMatrixConverter$();
   private static final String className = "matrix";

   public String className() {
      return className;
   }

   public Matrix fromJson(final String json) {
      Formats formats = .MODULE$;
      JValue jValue = org.json4s.jackson.JsonMethods..MODULE$.parse(json, org.json4s.jackson.JsonMethods..MODULE$.parse$default$2(), org.json4s.jackson.JsonMethods..MODULE$.parse$default$3(), org.json4s.AsJsonInput..MODULE$.stringAsJsonInput());
      int var4 = BoxesRunTime.unboxToInt(org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(jValue), "type")), formats, scala.reflect.ManifestFactory..MODULE$.Int()));
      switch (var4) {
         case 0:
            int numRows = BoxesRunTime.unboxToInt(org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(jValue), "numRows")), formats, scala.reflect.ManifestFactory..MODULE$.Int()));
            int numCols = BoxesRunTime.unboxToInt(org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(jValue), "numCols")), formats, scala.reflect.ManifestFactory..MODULE$.Int()));
            int[] colPtrs = (int[])((IterableOnceOps)org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(jValue), "colPtrs")), formats, scala.reflect.ManifestFactory..MODULE$.classType(Seq.class, scala.reflect.ManifestFactory..MODULE$.Int(), scala.collection.immutable.Nil..MODULE$))).toArray(scala.reflect.ClassTag..MODULE$.Int());
            int[] rowIndices = (int[])((IterableOnceOps)org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(jValue), "rowIndices")), formats, scala.reflect.ManifestFactory..MODULE$.classType(Seq.class, scala.reflect.ManifestFactory..MODULE$.Int(), scala.collection.immutable.Nil..MODULE$))).toArray(scala.reflect.ClassTag..MODULE$.Int());
            double[] values = (double[])((IterableOnceOps)org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(jValue), "values")), formats, scala.reflect.ManifestFactory..MODULE$.classType(Seq.class, scala.reflect.ManifestFactory..MODULE$.Double(), scala.collection.immutable.Nil..MODULE$))).toArray(scala.reflect.ClassTag..MODULE$.Double());
            boolean isTransposed = BoxesRunTime.unboxToBoolean(org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(jValue), "isTransposed")), formats, scala.reflect.ManifestFactory..MODULE$.Boolean()));
            return new SparseMatrix(numRows, numCols, colPtrs, rowIndices, values, isTransposed);
         case 1:
            int numRows = BoxesRunTime.unboxToInt(org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(jValue), "numRows")), formats, scala.reflect.ManifestFactory..MODULE$.Int()));
            int numCols = BoxesRunTime.unboxToInt(org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(jValue), "numCols")), formats, scala.reflect.ManifestFactory..MODULE$.Int()));
            double[] values = (double[])((IterableOnceOps)org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(jValue), "values")), formats, scala.reflect.ManifestFactory..MODULE$.classType(Seq.class, scala.reflect.ManifestFactory..MODULE$.Double(), scala.collection.immutable.Nil..MODULE$))).toArray(scala.reflect.ClassTag..MODULE$.Double());
            boolean isTransposed = BoxesRunTime.unboxToBoolean(org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(jValue), "isTransposed")), formats, scala.reflect.ManifestFactory..MODULE$.Boolean()));
            return new DenseMatrix(numRows, numCols, values, isTransposed);
         default:
            throw new IllegalArgumentException("Cannot parse " + json + " into a Matrix.");
      }
   }

   public String toJson(final Matrix m) {
      if (m instanceof SparseMatrix var4) {
         Option var5 = org.apache.spark.ml.linalg.SparseMatrix..MODULE$.unapply(var4);
         if (!var5.isEmpty()) {
            int numRows = BoxesRunTime.unboxToInt(((Tuple6)var5.get())._1());
            int numCols = BoxesRunTime.unboxToInt(((Tuple6)var5.get())._2());
            int[] colPtrs = (int[])((Tuple6)var5.get())._3();
            int[] rowIndices = (int[])((Tuple6)var5.get())._4();
            double[] values = (double[])((Tuple6)var5.get())._5();
            boolean isTransposed = BoxesRunTime.unboxToBoolean(((Tuple6)var5.get())._6());
            JObject jValue = org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.pair2Assoc(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("class"), this.className()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("type"), BoxesRunTime.boxToInteger(0)), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x), (x) -> $anonfun$toJson$3(BoxesRunTime.unboxToInt(x)))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("numRows"), BoxesRunTime.boxToInteger(numRows)), (x) -> $anonfun$toJson$4(BoxesRunTime.unboxToInt(x))))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("numCols"), BoxesRunTime.boxToInteger(numCols)), (x) -> $anonfun$toJson$5(BoxesRunTime.unboxToInt(x))))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("colPtrs"), org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(colPtrs).toImmutableArraySeq()), (s) -> org.json4s.JsonDSL..MODULE$.seq2jvalue(s, (x) -> $anonfun$toJson$7(BoxesRunTime.unboxToInt(x)))))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("rowIndices"), org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(rowIndices).toImmutableArraySeq()), (s) -> org.json4s.JsonDSL..MODULE$.seq2jvalue(s, (x) -> $anonfun$toJson$9(BoxesRunTime.unboxToInt(x)))))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("values"), org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(values).toImmutableArraySeq()), (s) -> org.json4s.JsonDSL..MODULE$.seq2jvalue(s, (x) -> $anonfun$toJson$11(BoxesRunTime.unboxToDouble(x)))))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("isTransposed"), BoxesRunTime.boxToBoolean(isTransposed)), (x) -> $anonfun$toJson$12(BoxesRunTime.unboxToBoolean(x))));
            return org.json4s.jackson.JsonMethods..MODULE$.compact(org.json4s.jackson.JsonMethods..MODULE$.render(jValue, org.json4s.jackson.JsonMethods..MODULE$.render$default$2(), org.json4s.jackson.JsonMethods..MODULE$.render$default$3()));
         }
      }

      if (m instanceof DenseMatrix var13) {
         Option var14 = org.apache.spark.ml.linalg.DenseMatrix..MODULE$.unapply(var13);
         if (!var14.isEmpty()) {
            int numRows = BoxesRunTime.unboxToInt(((Tuple4)var14.get())._1());
            int numCols = BoxesRunTime.unboxToInt(((Tuple4)var14.get())._2());
            double[] values = (double[])((Tuple4)var14.get())._3();
            boolean isTransposed = BoxesRunTime.unboxToBoolean(((Tuple4)var14.get())._4());
            JObject jValue = org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.pair2Assoc(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("class"), this.className()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("type"), BoxesRunTime.boxToInteger(1)), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x), (x) -> $anonfun$toJson$15(BoxesRunTime.unboxToInt(x)))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("numRows"), BoxesRunTime.boxToInteger(numRows)), (x) -> $anonfun$toJson$16(BoxesRunTime.unboxToInt(x))))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("numCols"), BoxesRunTime.boxToInteger(numCols)), (x) -> $anonfun$toJson$17(BoxesRunTime.unboxToInt(x))))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("values"), org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(values).toImmutableArraySeq()), (s) -> org.json4s.JsonDSL..MODULE$.seq2jvalue(s, (x) -> $anonfun$toJson$19(BoxesRunTime.unboxToDouble(x)))))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("isTransposed"), BoxesRunTime.boxToBoolean(isTransposed)), (x) -> $anonfun$toJson$20(BoxesRunTime.unboxToBoolean(x))));
            return org.json4s.jackson.JsonMethods..MODULE$.compact(org.json4s.jackson.JsonMethods..MODULE$.render(jValue, org.json4s.jackson.JsonMethods..MODULE$.render$default$2(), org.json4s.jackson.JsonMethods..MODULE$.render$default$3()));
         }
      }

      throw new IllegalArgumentException("Unknown matrix type " + m.getClass() + ".");
   }

   // $FF: synthetic method
   public static final JValue $anonfun$toJson$3(final int x) {
      return org.json4s.JsonDSL..MODULE$.int2jvalue(x);
   }

   // $FF: synthetic method
   public static final JValue $anonfun$toJson$4(final int x) {
      return org.json4s.JsonDSL..MODULE$.int2jvalue(x);
   }

   // $FF: synthetic method
   public static final JValue $anonfun$toJson$5(final int x) {
      return org.json4s.JsonDSL..MODULE$.int2jvalue(x);
   }

   // $FF: synthetic method
   public static final JValue $anonfun$toJson$7(final int x) {
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

   // $FF: synthetic method
   public static final JValue $anonfun$toJson$12(final boolean x) {
      return org.json4s.JsonDSL..MODULE$.boolean2jvalue(x);
   }

   // $FF: synthetic method
   public static final JValue $anonfun$toJson$15(final int x) {
      return org.json4s.JsonDSL..MODULE$.int2jvalue(x);
   }

   // $FF: synthetic method
   public static final JValue $anonfun$toJson$16(final int x) {
      return org.json4s.JsonDSL..MODULE$.int2jvalue(x);
   }

   // $FF: synthetic method
   public static final JValue $anonfun$toJson$17(final int x) {
      return org.json4s.JsonDSL..MODULE$.int2jvalue(x);
   }

   // $FF: synthetic method
   public static final JValue $anonfun$toJson$19(final double x) {
      return org.json4s.JsonDSL..MODULE$.double2jvalue(x);
   }

   // $FF: synthetic method
   public static final JValue $anonfun$toJson$20(final boolean x) {
      return org.json4s.JsonDSL..MODULE$.boolean2jvalue(x);
   }

   private JsonMatrixConverter$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
