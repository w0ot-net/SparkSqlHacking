package org.apache.spark.ml.feature;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.attribute.Attribute;
import org.apache.spark.ml.attribute.Attribute$;
import org.apache.spark.ml.attribute.AttributeGroup;
import org.apache.spark.ml.attribute.BinaryAttribute;
import org.apache.spark.ml.attribute.BinaryAttribute$;
import org.apache.spark.ml.attribute.NominalAttribute;
import org.apache.spark.ml.attribute.NumericAttribute;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.types.StructField;
import scala.Function2;
import scala.MatchError;
import scala.Option;
import scala.Predef;
import scala.Some;
import scala.None.;
import scala.collection.ArrayOps;
import scala.collection.IterableOps;
import scala.collection.immutable.Seq;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction1;

public final class OneHotEncoderCommon$ {
   public static final OneHotEncoderCommon$ MODULE$ = new OneHotEncoderCommon$();

   private Option genOutputAttrNames(final StructField inputCol) {
      Attribute inputAttr = Attribute$.MODULE$.fromStructField(inputCol);
      if (inputAttr instanceof NominalAttribute var5) {
         if (var5.values().isDefined()) {
            return var5.values();
         } else {
            return (Option)(var5.numValues().isDefined() ? var5.numValues().map((n) -> $anonfun$genOutputAttrNames$1(BoxesRunTime.unboxToInt(n))) : .MODULE$);
         }
      } else if (inputAttr instanceof BinaryAttribute var6) {
         return (Option)(var6.values().isDefined() ? var6.values() : new Some(scala.Array..MODULE$.tabulate(2, (x$11) -> $anonfun$genOutputAttrNames$3(BoxesRunTime.unboxToInt(x$11)), scala.reflect.ClassTag..MODULE$.apply(String.class))));
      } else if (inputAttr instanceof NumericAttribute) {
         throw new RuntimeException("The input column " + inputCol.name() + " cannot be continuous-value.");
      } else {
         return .MODULE$;
      }
   }

   private AttributeGroup genOutputAttrGroup(final Option outputAttrNames, final String outputColName) {
      return (AttributeGroup)outputAttrNames.map((attrNames) -> {
         Attribute[] attrs = (Attribute[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])attrNames), (name) -> BinaryAttribute$.MODULE$.defaultAttr().withName(name), scala.reflect.ClassTag..MODULE$.apply(Attribute.class));
         return new AttributeGroup(outputColName, attrs);
      }).getOrElse(() -> new AttributeGroup(outputColName));
   }

   public StructField transformOutputColumnSchema(final StructField inputCol, final String outputColName, final boolean dropLast, final boolean keepInvalid) {
      Option outputAttrNames = this.genOutputAttrNames(inputCol);
      Option filteredOutputAttrNames = outputAttrNames.map((names) -> {
         if (dropLast && !keepInvalid) {
            scala.Predef..MODULE$.require(names.length > 1, () -> "The input column " + inputCol.name() + " should have at least two distinct values.");
            return (String[])scala.collection.ArrayOps..MODULE$.dropRight$extension(scala.Predef..MODULE$.refArrayOps((Object[])names), 1);
         } else {
            return !dropLast && keepInvalid ? (String[])scala.collection.ArrayOps..MODULE$.$plus$plus$extension(scala.Predef..MODULE$.refArrayOps((Object[])names), new scala.collection.immutable..colon.colon("invalidValues", scala.collection.immutable.Nil..MODULE$), scala.reflect.ClassTag..MODULE$.apply(String.class)) : names;
         }
      });
      return this.genOutputAttrGroup(filteredOutputAttrNames, outputColName).toStructField();
   }

   public boolean transformOutputColumnSchema$default$4() {
      return false;
   }

   public Seq getOutputAttrGroupFromData(final Dataset dataset, final Seq inputColNames, final Seq outputColNames, final boolean dropLast) {
      Seq columns = (Seq)inputColNames.map((inputColName) -> org.apache.spark.sql.functions..MODULE$.col(inputColName).cast(org.apache.spark.sql.types.DoubleType..MODULE$));
      int numOfColumns = columns.length();
      ArrayOps var10000 = scala.collection.ArrayOps..MODULE$;
      Predef var10001 = scala.Predef..MODULE$;
      RDD qual$1 = dataset.select(columns).rdd().map((row) -> (double[])scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), numOfColumns).map((JFunction1.mcDI.sp)(idx) -> row.getDouble(idx)).toArray(scala.reflect.ClassTag..MODULE$.Double()), scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Double.TYPE)));
      double[] x$1 = new double[numOfColumns];
      Function2 x$2 = (maxValues, curValues) -> {
         scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), numOfColumns).foreach$mVc$sp((JFunction1.mcVI.sp)(idx) -> {
            double x = curValues[idx];
            scala.Predef..MODULE$.assert(x <= (double)Integer.MAX_VALUE, () -> "OneHotEncoder only supports up to " + Integer.MAX_VALUE + " indices, but got " + x + ".");
            scala.Predef..MODULE$.assert(x >= (double)0.0F && x == (double)((int)x), () -> {
               Object var10000 = inputColNames.apply(idx);
               return "Values from column " + var10000 + " must be indices, but got " + x + ".";
            });
            maxValues[idx] = scala.math.package..MODULE$.max(maxValues[idx], x);
         });
         return maxValues;
      };
      Function2 x$3 = (m0, m1) -> {
         scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), numOfColumns).foreach$mVc$sp((JFunction1.mcVI.sp)(idx) -> m0[idx] = scala.math.package..MODULE$.max(m0[idx], m1[idx]));
         return m0;
      };
      int x$4 = qual$1.treeAggregate$default$4(x$1);
      int[] numAttrsArray = (int[])var10000.map$extension(var10001.doubleArrayOps((double[])qual$1.treeAggregate(x$1, x$2, x$3, x$4, scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Double.TYPE)))), (JFunction1.mcID.sp)(x$12) -> (int)x$12 + 1, scala.reflect.ClassTag..MODULE$.Int());
      return (Seq)((IterableOps)outputColNames.zip(scala.Predef..MODULE$.wrapIntArray(numAttrsArray))).map((x0$1) -> {
         if (x0$1 != null) {
            String outputColName = (String)x0$1._1();
            int numAttrs = x0$1._2$mcI$sp();
            return MODULE$.createAttrGroupForAttrNames(outputColName, numAttrs, dropLast, false);
         } else {
            throw new MatchError(x0$1);
         }
      });
   }

   public AttributeGroup createAttrGroupForAttrNames(final String outputColName, final int numAttrs, final boolean dropLast, final boolean keepInvalid) {
      String[] outputAttrNames = (String[])scala.Array..MODULE$.tabulate(numAttrs, (x$13) -> $anonfun$createAttrGroupForAttrNames$1(BoxesRunTime.unboxToInt(x$13)), scala.reflect.ClassTag..MODULE$.apply(String.class));
      String[] filtered = dropLast && !keepInvalid ? (String[])scala.collection.ArrayOps..MODULE$.dropRight$extension(scala.Predef..MODULE$.refArrayOps((Object[])outputAttrNames), 1) : (!dropLast && keepInvalid ? (String[])scala.collection.ArrayOps..MODULE$.$plus$plus$extension(scala.Predef..MODULE$.refArrayOps((Object[])outputAttrNames), new scala.collection.immutable..colon.colon("invalidValues", scala.collection.immutable.Nil..MODULE$), scala.reflect.ClassTag..MODULE$.apply(String.class)) : outputAttrNames);
      return this.genOutputAttrGroup(new Some(filtered), outputColName);
   }

   // $FF: synthetic method
   public static final String $anonfun$genOutputAttrNames$2(final int x$10) {
      return Integer.toString(x$10);
   }

   // $FF: synthetic method
   public static final String[] $anonfun$genOutputAttrNames$1(final int n) {
      return (String[])scala.Array..MODULE$.tabulate(n, (x$10) -> $anonfun$genOutputAttrNames$2(BoxesRunTime.unboxToInt(x$10)), scala.reflect.ClassTag..MODULE$.apply(String.class));
   }

   // $FF: synthetic method
   public static final String $anonfun$genOutputAttrNames$3(final int x$11) {
      return Integer.toString(x$11);
   }

   // $FF: synthetic method
   public static final String $anonfun$createAttrGroupForAttrNames$1(final int x$13) {
      return Integer.toString(x$13);
   }

   private OneHotEncoderCommon$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
