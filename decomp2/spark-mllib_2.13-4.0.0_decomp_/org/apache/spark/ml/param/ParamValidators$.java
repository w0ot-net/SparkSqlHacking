package org.apache.spark.ml.param;

import java.lang.invoke.SerializedLambda;
import java.util.List;
import scala.Function1;
import scala.Predef.;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.immutable.Seq;
import scala.collection.mutable.StringBuilder;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

public final class ParamValidators$ {
   public static final ParamValidators$ MODULE$ = new ParamValidators$();

   public Function1 alwaysTrue() {
      return (x$2) -> BoxesRunTime.boxToBoolean($anonfun$alwaysTrue$1(x$2));
   }

   private double getDouble(final Object value) {
      if (value instanceof Integer) {
         int var5 = BoxesRunTime.unboxToInt(value);
         return (double)var5;
      } else if (value instanceof Long) {
         long var6 = BoxesRunTime.unboxToLong(value);
         return (double)var6;
      } else if (value instanceof Float) {
         float var8 = BoxesRunTime.unboxToFloat(value);
         return (double)var8;
      } else if (value instanceof Double) {
         double var9 = BoxesRunTime.unboxToDouble(value);
         return var9;
      } else {
         throw new IllegalArgumentException("Numerical Param validation failed because of unexpected input type: " + value.getClass());
      }
   }

   public Function1 gt(final double lowerBound) {
      return (value) -> BoxesRunTime.boxToBoolean($anonfun$gt$1(lowerBound, value));
   }

   public Function1 gtEq(final double lowerBound) {
      return (value) -> BoxesRunTime.boxToBoolean($anonfun$gtEq$1(lowerBound, value));
   }

   public Function1 lt(final double upperBound) {
      return (value) -> BoxesRunTime.boxToBoolean($anonfun$lt$1(upperBound, value));
   }

   public Function1 ltEq(final double upperBound) {
      return (value) -> BoxesRunTime.boxToBoolean($anonfun$ltEq$1(upperBound, value));
   }

   public Function1 inRange(final double lowerBound, final double upperBound, final boolean lowerInclusive, final boolean upperInclusive) {
      return (value) -> BoxesRunTime.boxToBoolean($anonfun$inRange$1(lowerInclusive, lowerBound, upperInclusive, upperBound, value));
   }

   public Function1 inRange(final double lowerBound, final double upperBound) {
      return this.inRange(lowerBound, upperBound, true, true);
   }

   public Function1 inArray(final Object allowed) {
      return (value) -> BoxesRunTime.boxToBoolean($anonfun$inArray$1(allowed, value));
   }

   public Function1 inArray(final List allowed) {
      return (value) -> BoxesRunTime.boxToBoolean($anonfun$inArray$2(allowed, value));
   }

   public Function1 arrayLengthGt(final double lowerBound) {
      return (value) -> BoxesRunTime.boxToBoolean($anonfun$arrayLengthGt$1(lowerBound, value));
   }

   public void checkSingleVsMultiColumnParams(final Params model, final Seq singleColumnParams, final Seq multiColumnParams) {
      String var10000 = model.getClass().getSimpleName();
      String name = var10000 + " " + model;
      Param inputCol = model.getParam("inputCol");
      Param inputCols = model.getParam("inputCols");
      if (model.isSet(inputCol)) {
         .MODULE$.require(!model.isSet(inputCols), () -> name + " requires exactly one of inputCol, inputCols Params to be set, but both are set.");
         checkExclusiveParams$1(true, singleColumnParams, multiColumnParams, model, name);
      } else if (model.isSet(inputCols)) {
         checkExclusiveParams$1(false, multiColumnParams, singleColumnParams, model, name);
      } else {
         throw new IllegalArgumentException(name + " requires exactly one of inputCol, inputCols Params to be set, but neither is set.");
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$alwaysTrue$1(final Object x$2) {
      return true;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$gt$1(final double lowerBound$1, final Object value) {
      return MODULE$.getDouble(value) > lowerBound$1;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$gtEq$1(final double lowerBound$2, final Object value) {
      return MODULE$.getDouble(value) >= lowerBound$2;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$lt$1(final double upperBound$1, final Object value) {
      return MODULE$.getDouble(value) < upperBound$1;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$ltEq$1(final double upperBound$2, final Object value) {
      return MODULE$.getDouble(value) <= upperBound$2;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$inRange$1(final boolean lowerInclusive$1, final double lowerBound$3, final boolean upperInclusive$1, final double upperBound$3, final Object value) {
      double x = MODULE$.getDouble(value);
      boolean lowerValid = lowerInclusive$1 ? x >= lowerBound$3 : x > lowerBound$3;
      boolean upperValid = upperInclusive$1 ? x <= upperBound$3 : x < upperBound$3;
      return lowerValid && upperValid;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$inArray$1(final Object allowed$1, final Object value) {
      return scala.collection.ArrayOps..MODULE$.contains$extension(.MODULE$.genericArrayOps(allowed$1), value);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$inArray$2(final List allowed$2, final Object value) {
      return allowed$2.contains(value);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$arrayLengthGt$1(final double lowerBound$4, final Object value) {
      return (double)scala.runtime.ScalaRunTime..MODULE$.array_length(value) > lowerBound$4;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$checkSingleVsMultiColumnParams$1(final Params model$1, final Param p) {
      return model$1.isSet(p);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$checkSingleVsMultiColumnParams$3(final Params model$1, final Param p) {
      return !model$1.isDefined(p);
   }

   private static final void checkExclusiveParams$1(final boolean isSingleCol, final Seq requiredParams, final Seq excludedParams, final Params model$1, final String name$1) {
      StringBuilder badParamsMsgBuilder = new StringBuilder();
      String mustUnsetParams = ((IterableOnceOps)((IterableOps)excludedParams.filter((p) -> BoxesRunTime.boxToBoolean($anonfun$checkSingleVsMultiColumnParams$1(model$1, p)))).map((x$3) -> x$3.name())).mkString(", ");
      if (scala.collection.StringOps..MODULE$.nonEmpty$extension(.MODULE$.augmentString(mustUnsetParams))) {
         badParamsMsgBuilder.$plus$plus$eq("The following Params are not applicable and should not be set: " + mustUnsetParams + ".");
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      String mustSetParams = ((IterableOnceOps)((IterableOps)requiredParams.filter((p) -> BoxesRunTime.boxToBoolean($anonfun$checkSingleVsMultiColumnParams$3(model$1, p)))).map((x$4) -> x$4.name())).mkString(", ");
      if (scala.collection.StringOps..MODULE$.nonEmpty$extension(.MODULE$.augmentString(mustSetParams))) {
         badParamsMsgBuilder.$plus$plus$eq("The following Params must be defined but are not set: " + mustSetParams + ".");
      } else {
         BoxedUnit var10 = BoxedUnit.UNIT;
      }

      String badParamsMsg = badParamsMsgBuilder.toString();
      if (scala.collection.StringOps..MODULE$.nonEmpty$extension(.MODULE$.augmentString(badParamsMsg))) {
         String errPrefix = isSingleCol ? name$1 + " has the inputCol Param set for single-column transform." : name$1 + " has the inputCols Param set for multi-column transform.";
         throw new IllegalArgumentException(errPrefix + " " + badParamsMsg);
      }
   }

   private ParamValidators$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
