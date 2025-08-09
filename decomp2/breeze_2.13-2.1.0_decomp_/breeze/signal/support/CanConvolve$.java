package breeze.signal.support;

import breeze.linalg.DenseVector;
import breeze.linalg.DenseVector$;
import breeze.linalg.convert$;
import breeze.linalg.reverse$;
import breeze.linalg.operators.HasOps$;
import breeze.linalg.support.RangeExtender$;
import breeze.math.Semiring$;
import breeze.signal.OptMethod;
import breeze.signal.OptOverhang;
import breeze.signal.OptPadding;
import breeze.signal.OptRange;
import breeze.storage.Zero$;
import breeze.util.LazyLogger;
import breeze.util.SerializableLogging;
import java.lang.invoke.SerializedLambda;
import scala.MatchError;
import scala.Predef.;
import scala.collection.immutable.Range;
import scala.collection.immutable.Vector;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.ScalaRunTime;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;

public final class CanConvolve$ implements SerializableLogging {
   public static final CanConvolve$ MODULE$ = new CanConvolve$();
   private static final CanConvolve dvT1DConvolve_Int;
   private static final CanConvolve dvT1DConvolve_Long;
   private static final CanConvolve dvT1DConvolve_Float;
   private static final CanConvolve dvT1DConvolve_Double;
   private static final CanConvolve dvTKernel1DConvolve_Int;
   private static final CanConvolve dvTKernel1DConvolve_Long;
   private static final CanConvolve dvTKernel1DConvolve_Float;
   private static final CanConvolve dvTKernel1DConvolve_Double;
   private static final CanConvolve.CanCorrelateNoOverhang correlateLoopNoOverhangRangeT_Double;
   private static final CanConvolve.CanCorrelateNoOverhang correlateLoopNoOverhangRangeT_Float;
   private static final CanConvolve.CanCorrelateNoOverhang correlateLoopNoOverhangRangeT_Long;
   private static final CanConvolve.CanCorrelateNoOverhang correlateLoopNoOverhangRangeInt;
   private static transient volatile LazyLogger breeze$util$SerializableLogging$$_the_logger;

   static {
      SerializableLogging.$init$(MODULE$);
      dvT1DConvolve_Int = new CanConvolve() {
         public DenseVector apply(final DenseVector data, final DenseVector kernel, final OptRange range, final boolean correlate, final OptOverhang overhang, final OptPadding padding, final OptMethod method) {
            if (OptMethod.Automatic$.MODULE$.equals(method)) {
               .MODULE$.require(true);
               BoxedUnit var14 = BoxedUnit.UNIT;
            } else {
               .MODULE$.require(false, () -> "currently, only loop convolutions are supported.");
               BoxedUnit var43 = BoxedUnit.UNIT;
            }

            int kl = kernel.length();
            int dl = data.length();
            DenseVector paddedData;
            if (OptOverhang.None$.MODULE$.equals(overhang)) {
               paddedData = data;
            } else if (OptOverhang.Full$.MODULE$.equals(overhang)) {
               DenseVector$ var10000 = DenseVector$.MODULE$;
               ScalaRunTime var10001 = scala.runtime.ScalaRunTime..MODULE$;
               DenseVector[] var10002 = new DenseVector[3];
               DenseVector var13;
               if (OptPadding.Cyclical$.MODULE$.equals(padding)) {
                  var13 = (DenseVector)data.apply(scala.runtime.RichInt..MODULE$.to$extension(.MODULE$.intWrapper(dl - (kl - 1)), dl - 1), HasOps$.MODULE$.canSlice_DV_Range_eq_DV());
               } else if (OptPadding.Boundary$.MODULE$.equals(padding)) {
                  var13 = DenseVector$.MODULE$.fill$mIc$sp(kernel.length() - 1, data.valueAt$mcI$sp(0), scala.reflect.ClassTag..MODULE$.Int(), Semiring$.MODULE$.semiringInt());
               } else if (OptPadding.Zero$.MODULE$.equals(padding)) {
                  var13 = DenseVector$.MODULE$.zeros$mIc$sp(kernel.length() - 1, scala.reflect.ClassTag..MODULE$.Int(), Zero$.MODULE$.IntZero());
               } else {
                  label159: {
                     if (padding instanceof OptPadding.ValueOpt) {
                        OptPadding.ValueOpt var21 = (OptPadding.ValueOpt)padding;
                        Object v = var21.value();
                        if (v instanceof Integer) {
                           int var23 = BoxesRunTime.unboxToInt(v);
                           var13 = DenseVector$.MODULE$.fill$mIc$sp(kernel.length() - 1, var23, scala.reflect.ClassTag..MODULE$.Int(), Semiring$.MODULE$.semiringInt());
                           break label159;
                        }
                     }

                     .MODULE$.require(false, () -> (new StringBuilder(31)).append("cannot handle OptPadding value ").append(padding).toString());
                     var13 = (DenseVector)DenseVector$.MODULE$.apply(scala.collection.immutable.Nil..MODULE$, scala.reflect.ClassTag..MODULE$.Int());
                  }
               }

               var10002[0] = var13;
               var10002[1] = data;
               DenseVector var12;
               if (OptPadding.Cyclical$.MODULE$.equals(padding)) {
                  var12 = (DenseVector)data.apply(scala.runtime.RichInt..MODULE$.to$extension(.MODULE$.intWrapper(0), kl - 1), HasOps$.MODULE$.canSlice_DV_Range_eq_DV());
               } else if (OptPadding.Boundary$.MODULE$.equals(padding)) {
                  var12 = DenseVector$.MODULE$.fill$mIc$sp(kernel.length() - 1, data.valueAt$mcI$sp(dl - 1), scala.reflect.ClassTag..MODULE$.Int(), Semiring$.MODULE$.semiringInt());
               } else if (OptPadding.Zero$.MODULE$.equals(padding)) {
                  var12 = DenseVector$.MODULE$.zeros$mIc$sp(kernel.length() - 1, scala.reflect.ClassTag..MODULE$.Int(), Zero$.MODULE$.IntZero());
               } else {
                  label162: {
                     if (padding instanceof OptPadding.ValueOpt) {
                        OptPadding.ValueOpt var25 = (OptPadding.ValueOpt)padding;
                        Object v = var25.value();
                        if (v instanceof Integer) {
                           int var27 = BoxesRunTime.unboxToInt(v);
                           var12 = DenseVector$.MODULE$.fill$mIc$sp(kernel.length() - 1, var27, scala.reflect.ClassTag..MODULE$.Int(), Semiring$.MODULE$.semiringInt());
                           break label162;
                        }
                     }

                     .MODULE$.require(false, () -> (new StringBuilder(31)).append("cannot handle OptPadding value ").append(padding).toString());
                     var12 = (DenseVector)DenseVector$.MODULE$.apply(scala.collection.immutable.Nil..MODULE$, scala.reflect.ClassTag..MODULE$.Int());
                  }
               }

               var10002[2] = var12;
               paddedData = var10000.vertcat(var10001.wrapRefArray(var10002), HasOps$.MODULE$.impl_Op_InPlace_DV_DV_Int_OpSet(), scala.reflect.ClassTag..MODULE$.Int(), Zero$.MODULE$.IntZero());
            } else if (OptOverhang.PreserveLength$.MODULE$.equals(overhang)) {
               int leftPadding = BoxesRunTime.unboxToBoolean(breeze.numerics.package.isOdd$.MODULE$.apply(BoxesRunTime.boxToInteger(kernel.length()), breeze.numerics.package.isOdd$.MODULE$.isOddImpl_Int())) ? (kernel.length() - 1) / 2 : kernel.length() / 2 - 1;
               int rightPadding = kernel.length() - leftPadding - 1;
               DenseVector$ var44 = DenseVector$.MODULE$;
               ScalaRunTime var45 = scala.runtime.ScalaRunTime..MODULE$;
               DenseVector[] var46 = new DenseVector[3];
               DenseVector var11;
               if (OptPadding.Cyclical$.MODULE$.equals(padding)) {
                  var11 = (DenseVector)data.apply(scala.runtime.RichInt..MODULE$.to$extension(.MODULE$.intWrapper(dl - leftPadding), dl - 1), HasOps$.MODULE$.canSlice_DV_Range_eq_DV());
               } else if (OptPadding.Boundary$.MODULE$.equals(padding)) {
                  var11 = (DenseVector)DenseVector$.MODULE$.ones$mIc$sp(leftPadding, scala.reflect.ClassTag..MODULE$.Int(), Semiring$.MODULE$.semiringInt()).$times(BoxesRunTime.boxToInteger(data.apply$mcI$sp(0)), HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Int_OpMulMatrix());
               } else if (OptPadding.Zero$.MODULE$.equals(padding)) {
                  var11 = DenseVector$.MODULE$.zeros$mIc$sp(leftPadding, scala.reflect.ClassTag..MODULE$.Int(), Zero$.MODULE$.IntZero());
               } else {
                  label165: {
                     if (padding instanceof OptPadding.ValueOpt) {
                        OptPadding.ValueOpt var31 = (OptPadding.ValueOpt)padding;
                        Object v = var31.value();
                        if (v instanceof Integer) {
                           int var33 = BoxesRunTime.unboxToInt(v);
                           var11 = (DenseVector)DenseVector$.MODULE$.ones$mIc$sp(leftPadding, scala.reflect.ClassTag..MODULE$.Int(), Semiring$.MODULE$.semiringInt()).$times(BoxesRunTime.boxToInteger(var33), HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Int_OpMulMatrix());
                           break label165;
                        }
                     }

                     .MODULE$.require(false, () -> (new StringBuilder(31)).append("cannot handle OptPadding value ").append(padding).toString());
                     var11 = (DenseVector)DenseVector$.MODULE$.apply(scala.collection.immutable.Nil..MODULE$, scala.reflect.ClassTag..MODULE$.Int());
                  }
               }

               var46[0] = var11;
               var46[1] = data;
               DenseVector var10;
               if (OptPadding.Cyclical$.MODULE$.equals(padding)) {
                  var10 = (DenseVector)data.apply(scala.runtime.RichInt..MODULE$.to$extension(.MODULE$.intWrapper(0), rightPadding - 1), HasOps$.MODULE$.canSlice_DV_Range_eq_DV());
               } else if (OptPadding.Boundary$.MODULE$.equals(padding)) {
                  var10 = (DenseVector)DenseVector$.MODULE$.ones$mIc$sp(rightPadding, scala.reflect.ClassTag..MODULE$.Int(), Semiring$.MODULE$.semiringInt()).$times(BoxesRunTime.boxToInteger(data.apply$mcI$sp(dl - 1)), HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Int_OpMulMatrix());
               } else if (OptPadding.Zero$.MODULE$.equals(padding)) {
                  var10 = DenseVector$.MODULE$.zeros$mIc$sp(rightPadding, scala.reflect.ClassTag..MODULE$.Int(), Zero$.MODULE$.IntZero());
               } else {
                  label168: {
                     if (padding instanceof OptPadding.ValueOpt) {
                        OptPadding.ValueOpt var35 = (OptPadding.ValueOpt)padding;
                        Object v = var35.value();
                        if (v instanceof Integer) {
                           int var37 = BoxesRunTime.unboxToInt(v);
                           var10 = (DenseVector)DenseVector$.MODULE$.ones$mIc$sp(rightPadding, scala.reflect.ClassTag..MODULE$.Int(), Semiring$.MODULE$.semiringInt()).$times(BoxesRunTime.boxToInteger(var37), HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Int_OpMulMatrix());
                           break label168;
                        }
                     }

                     .MODULE$.require(false, () -> (new StringBuilder(31)).append("cannot handle OptPadding value ").append(padding).toString());
                     var10 = (DenseVector)DenseVector$.MODULE$.apply(scala.collection.immutable.Nil..MODULE$, scala.reflect.ClassTag..MODULE$.Int());
                  }
               }

               var46[2] = var10;
               paddedData = var44.vertcat(var45.wrapRefArray(var46), HasOps$.MODULE$.impl_Op_InPlace_DV_DV_Int_OpSet(), scala.reflect.ClassTag..MODULE$.Int(), Zero$.MODULE$.IntZero());
            } else {
               .MODULE$.require(false, () -> (new StringBuilder(32)).append("cannot handle OptOverhang value ").append(overhang).toString());
               paddedData = data;
            }

            int fullOptRangeLength = paddedData.length() - kernel.length() + 1;
            Range parsedOptRange;
            if (OptRange.All$.MODULE$.equals(range)) {
               parsedOptRange = scala.runtime.RichInt..MODULE$.until$extension(.MODULE$.intWrapper(0), fullOptRangeLength);
            } else {
               if (!(range instanceof OptRange.RangeOpt)) {
                  throw new MatchError(range);
               }

               OptRange.RangeOpt var41 = (OptRange.RangeOpt)range;
               Range negativeR = var41.r();
               parsedOptRange = RangeExtender$.MODULE$.getRangeWithoutNegativeIndexes$extension(breeze.linalg.package$.MODULE$.RangeToRangeExtender(negativeR), fullOptRangeLength);
            }

            return correlate ? (DenseVector)CanConvolve$.MODULE$.correlateLoopNoOverhang(paddedData, kernel, parsedOptRange, CanConvolve$.MODULE$.correlateLoopNoOverhangRangeInt()) : (DenseVector)CanConvolve$.MODULE$.correlateLoopNoOverhang(paddedData, reverse$.MODULE$.apply(kernel, reverse$.MODULE$.dvReverse(scala.reflect.ClassTag..MODULE$.Int())), parsedOptRange, CanConvolve$.MODULE$.correlateLoopNoOverhangRangeInt());
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
      dvT1DConvolve_Long = new CanConvolve() {
         public DenseVector apply(final DenseVector data, final DenseVector kernel, final OptRange range, final boolean correlate, final OptOverhang overhang, final OptPadding padding, final OptMethod method) {
            if (OptMethod.Automatic$.MODULE$.equals(method)) {
               .MODULE$.require(true);
               BoxedUnit var14 = BoxedUnit.UNIT;
            } else {
               .MODULE$.require(false, () -> "currently, only loop convolutions are supported.");
               BoxedUnit var47 = BoxedUnit.UNIT;
            }

            int kl = kernel.length();
            int dl = data.length();
            DenseVector paddedData;
            if (OptOverhang.None$.MODULE$.equals(overhang)) {
               paddedData = data;
            } else if (OptOverhang.Full$.MODULE$.equals(overhang)) {
               DenseVector$ var10000 = DenseVector$.MODULE$;
               ScalaRunTime var10001 = scala.runtime.ScalaRunTime..MODULE$;
               DenseVector[] var10002 = new DenseVector[3];
               DenseVector var13;
               if (OptPadding.Cyclical$.MODULE$.equals(padding)) {
                  var13 = (DenseVector)data.apply(scala.runtime.RichInt..MODULE$.to$extension(.MODULE$.intWrapper(dl - (kl - 1)), dl - 1), HasOps$.MODULE$.canSlice_DV_Range_eq_DV());
               } else if (OptPadding.Boundary$.MODULE$.equals(padding)) {
                  var13 = DenseVector$.MODULE$.fill$mJc$sp(kernel.length() - 1, data.valueAt$mcJ$sp(0), scala.reflect.ClassTag..MODULE$.Long(), Semiring$.MODULE$.semiringLong());
               } else if (OptPadding.Zero$.MODULE$.equals(padding)) {
                  var13 = DenseVector$.MODULE$.zeros$mJc$sp(kernel.length() - 1, scala.reflect.ClassTag..MODULE$.Long(), Zero$.MODULE$.LongZero());
               } else {
                  label159: {
                     if (padding instanceof OptPadding.ValueOpt) {
                        OptPadding.ValueOpt var21 = (OptPadding.ValueOpt)padding;
                        Object v = var21.value();
                        if (v instanceof Long) {
                           long var23 = BoxesRunTime.unboxToLong(v);
                           var13 = DenseVector$.MODULE$.fill$mJc$sp(kernel.length() - 1, var23, scala.reflect.ClassTag..MODULE$.Long(), Semiring$.MODULE$.semiringLong());
                           break label159;
                        }
                     }

                     .MODULE$.require(false, () -> (new StringBuilder(31)).append("cannot handle OptPadding value ").append(padding).toString());
                     var13 = (DenseVector)DenseVector$.MODULE$.apply(scala.collection.immutable.Nil..MODULE$, scala.reflect.ClassTag..MODULE$.Long());
                  }
               }

               var10002[0] = var13;
               var10002[1] = data;
               DenseVector var12;
               if (OptPadding.Cyclical$.MODULE$.equals(padding)) {
                  var12 = (DenseVector)data.apply(scala.runtime.RichInt..MODULE$.to$extension(.MODULE$.intWrapper(0), kl - 1), HasOps$.MODULE$.canSlice_DV_Range_eq_DV());
               } else if (OptPadding.Boundary$.MODULE$.equals(padding)) {
                  var12 = DenseVector$.MODULE$.fill$mJc$sp(kernel.length() - 1, data.valueAt$mcJ$sp(dl - 1), scala.reflect.ClassTag..MODULE$.Long(), Semiring$.MODULE$.semiringLong());
               } else if (OptPadding.Zero$.MODULE$.equals(padding)) {
                  var12 = DenseVector$.MODULE$.zeros$mJc$sp(kernel.length() - 1, scala.reflect.ClassTag..MODULE$.Long(), Zero$.MODULE$.LongZero());
               } else {
                  label162: {
                     if (padding instanceof OptPadding.ValueOpt) {
                        OptPadding.ValueOpt var26 = (OptPadding.ValueOpt)padding;
                        Object v = var26.value();
                        if (v instanceof Long) {
                           long var28 = BoxesRunTime.unboxToLong(v);
                           var12 = DenseVector$.MODULE$.fill$mJc$sp(kernel.length() - 1, var28, scala.reflect.ClassTag..MODULE$.Long(), Semiring$.MODULE$.semiringLong());
                           break label162;
                        }
                     }

                     .MODULE$.require(false, () -> (new StringBuilder(31)).append("cannot handle OptPadding value ").append(padding).toString());
                     var12 = (DenseVector)DenseVector$.MODULE$.apply(scala.collection.immutable.Nil..MODULE$, scala.reflect.ClassTag..MODULE$.Long());
                  }
               }

               var10002[2] = var12;
               paddedData = var10000.vertcat(var10001.wrapRefArray(var10002), HasOps$.MODULE$.impl_Op_InPlace_DV_DV_Long_OpSet(), scala.reflect.ClassTag..MODULE$.Long(), Zero$.MODULE$.LongZero());
            } else if (OptOverhang.PreserveLength$.MODULE$.equals(overhang)) {
               int leftPadding = BoxesRunTime.unboxToBoolean(breeze.numerics.package.isOdd$.MODULE$.apply(BoxesRunTime.boxToInteger(kernel.length()), breeze.numerics.package.isOdd$.MODULE$.isOddImpl_Int())) ? (kernel.length() - 1) / 2 : kernel.length() / 2 - 1;
               int rightPadding = kernel.length() - leftPadding - 1;
               DenseVector$ var48 = DenseVector$.MODULE$;
               ScalaRunTime var49 = scala.runtime.ScalaRunTime..MODULE$;
               DenseVector[] var50 = new DenseVector[3];
               DenseVector var11;
               if (OptPadding.Cyclical$.MODULE$.equals(padding)) {
                  var11 = (DenseVector)data.apply(scala.runtime.RichInt..MODULE$.to$extension(.MODULE$.intWrapper(dl - leftPadding), dl - 1), HasOps$.MODULE$.canSlice_DV_Range_eq_DV());
               } else if (OptPadding.Boundary$.MODULE$.equals(padding)) {
                  var11 = (DenseVector)DenseVector$.MODULE$.ones$mJc$sp(leftPadding, scala.reflect.ClassTag..MODULE$.Long(), Semiring$.MODULE$.semiringLong()).$times(BoxesRunTime.boxToLong(data.apply$mcJ$sp(0)), HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Long_OpMulMatrix());
               } else if (OptPadding.Zero$.MODULE$.equals(padding)) {
                  var11 = DenseVector$.MODULE$.zeros$mJc$sp(leftPadding, scala.reflect.ClassTag..MODULE$.Long(), Zero$.MODULE$.LongZero());
               } else {
                  label165: {
                     if (padding instanceof OptPadding.ValueOpt) {
                        OptPadding.ValueOpt var33 = (OptPadding.ValueOpt)padding;
                        Object v = var33.value();
                        if (v instanceof Long) {
                           long var35 = BoxesRunTime.unboxToLong(v);
                           var11 = (DenseVector)DenseVector$.MODULE$.ones$mJc$sp(leftPadding, scala.reflect.ClassTag..MODULE$.Long(), Semiring$.MODULE$.semiringLong()).$times(BoxesRunTime.boxToLong(var35), HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Long_OpMulMatrix());
                           break label165;
                        }
                     }

                     .MODULE$.require(false, () -> (new StringBuilder(31)).append("cannot handle OptPadding value ").append(padding).toString());
                     var11 = (DenseVector)DenseVector$.MODULE$.apply(scala.collection.immutable.Nil..MODULE$, scala.reflect.ClassTag..MODULE$.Long());
                  }
               }

               var50[0] = var11;
               var50[1] = data;
               DenseVector var10;
               if (OptPadding.Cyclical$.MODULE$.equals(padding)) {
                  var10 = (DenseVector)data.apply(scala.runtime.RichInt..MODULE$.to$extension(.MODULE$.intWrapper(0), rightPadding - 1), HasOps$.MODULE$.canSlice_DV_Range_eq_DV());
               } else if (OptPadding.Boundary$.MODULE$.equals(padding)) {
                  var10 = (DenseVector)DenseVector$.MODULE$.ones$mJc$sp(rightPadding, scala.reflect.ClassTag..MODULE$.Long(), Semiring$.MODULE$.semiringLong()).$times(BoxesRunTime.boxToLong(data.apply$mcJ$sp(dl - 1)), HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Long_OpMulMatrix());
               } else if (OptPadding.Zero$.MODULE$.equals(padding)) {
                  var10 = DenseVector$.MODULE$.zeros$mJc$sp(rightPadding, scala.reflect.ClassTag..MODULE$.Long(), Zero$.MODULE$.LongZero());
               } else {
                  label168: {
                     if (padding instanceof OptPadding.ValueOpt) {
                        OptPadding.ValueOpt var38 = (OptPadding.ValueOpt)padding;
                        Object v = var38.value();
                        if (v instanceof Long) {
                           long var40 = BoxesRunTime.unboxToLong(v);
                           var10 = (DenseVector)DenseVector$.MODULE$.ones$mJc$sp(rightPadding, scala.reflect.ClassTag..MODULE$.Long(), Semiring$.MODULE$.semiringLong()).$times(BoxesRunTime.boxToLong(var40), HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Long_OpMulMatrix());
                           break label168;
                        }
                     }

                     .MODULE$.require(false, () -> (new StringBuilder(31)).append("cannot handle OptPadding value ").append(padding).toString());
                     var10 = (DenseVector)DenseVector$.MODULE$.apply(scala.collection.immutable.Nil..MODULE$, scala.reflect.ClassTag..MODULE$.Long());
                  }
               }

               var50[2] = var10;
               paddedData = var48.vertcat(var49.wrapRefArray(var50), HasOps$.MODULE$.impl_Op_InPlace_DV_DV_Long_OpSet(), scala.reflect.ClassTag..MODULE$.Long(), Zero$.MODULE$.LongZero());
            } else {
               .MODULE$.require(false, () -> (new StringBuilder(32)).append("cannot handle OptOverhang value ").append(overhang).toString());
               paddedData = data;
            }

            int fullOptRangeLength = paddedData.length() - kernel.length() + 1;
            Range parsedOptRange;
            if (OptRange.All$.MODULE$.equals(range)) {
               parsedOptRange = scala.runtime.RichInt..MODULE$.until$extension(.MODULE$.intWrapper(0), fullOptRangeLength);
            } else {
               if (!(range instanceof OptRange.RangeOpt)) {
                  throw new MatchError(range);
               }

               OptRange.RangeOpt var45 = (OptRange.RangeOpt)range;
               Range negativeR = var45.r();
               parsedOptRange = RangeExtender$.MODULE$.getRangeWithoutNegativeIndexes$extension(breeze.linalg.package$.MODULE$.RangeToRangeExtender(negativeR), fullOptRangeLength);
            }

            return correlate ? (DenseVector)CanConvolve$.MODULE$.correlateLoopNoOverhang(paddedData, kernel, parsedOptRange, CanConvolve$.MODULE$.correlateLoopNoOverhangRangeT_Long()) : (DenseVector)CanConvolve$.MODULE$.correlateLoopNoOverhang(paddedData, reverse$.MODULE$.apply(kernel, reverse$.MODULE$.dvReverse(scala.reflect.ClassTag..MODULE$.Long())), parsedOptRange, CanConvolve$.MODULE$.correlateLoopNoOverhangRangeT_Long());
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
      dvT1DConvolve_Float = new CanConvolve() {
         public DenseVector apply(final DenseVector data, final DenseVector kernel, final OptRange range, final boolean correlate, final OptOverhang overhang, final OptPadding padding, final OptMethod method) {
            if (OptMethod.Automatic$.MODULE$.equals(method)) {
               .MODULE$.require(true);
               BoxedUnit var14 = BoxedUnit.UNIT;
            } else {
               .MODULE$.require(false, () -> "currently, only loop convolutions are supported.");
               BoxedUnit var43 = BoxedUnit.UNIT;
            }

            int kl = kernel.length();
            int dl = data.length();
            DenseVector paddedData;
            if (OptOverhang.None$.MODULE$.equals(overhang)) {
               paddedData = data;
            } else if (OptOverhang.Full$.MODULE$.equals(overhang)) {
               DenseVector$ var10000 = DenseVector$.MODULE$;
               ScalaRunTime var10001 = scala.runtime.ScalaRunTime..MODULE$;
               DenseVector[] var10002 = new DenseVector[3];
               DenseVector var13;
               if (OptPadding.Cyclical$.MODULE$.equals(padding)) {
                  var13 = (DenseVector)data.apply(scala.runtime.RichInt..MODULE$.to$extension(.MODULE$.intWrapper(dl - (kl - 1)), dl - 1), HasOps$.MODULE$.canSlice_DV_Range_eq_DV());
               } else if (OptPadding.Boundary$.MODULE$.equals(padding)) {
                  var13 = DenseVector$.MODULE$.fill$mFc$sp(kernel.length() - 1, data.valueAt$mcF$sp(0), scala.reflect.ClassTag..MODULE$.Float(), Semiring$.MODULE$.semiringFloat());
               } else if (OptPadding.Zero$.MODULE$.equals(padding)) {
                  var13 = DenseVector$.MODULE$.zeros$mFc$sp(kernel.length() - 1, scala.reflect.ClassTag..MODULE$.Float(), Zero$.MODULE$.FloatZero());
               } else {
                  label159: {
                     if (padding instanceof OptPadding.ValueOpt) {
                        OptPadding.ValueOpt var21 = (OptPadding.ValueOpt)padding;
                        Object v = var21.value();
                        if (v instanceof Float) {
                           float var23 = BoxesRunTime.unboxToFloat(v);
                           var13 = DenseVector$.MODULE$.fill$mFc$sp(kernel.length() - 1, var23, scala.reflect.ClassTag..MODULE$.Float(), Semiring$.MODULE$.semiringFloat());
                           break label159;
                        }
                     }

                     .MODULE$.require(false, () -> (new StringBuilder(31)).append("cannot handle OptPadding value ").append(padding).toString());
                     var13 = (DenseVector)DenseVector$.MODULE$.apply(scala.collection.immutable.Nil..MODULE$, scala.reflect.ClassTag..MODULE$.Float());
                  }
               }

               var10002[0] = var13;
               var10002[1] = data;
               DenseVector var12;
               if (OptPadding.Cyclical$.MODULE$.equals(padding)) {
                  var12 = (DenseVector)data.apply(scala.runtime.RichInt..MODULE$.to$extension(.MODULE$.intWrapper(0), kl - 1), HasOps$.MODULE$.canSlice_DV_Range_eq_DV());
               } else if (OptPadding.Boundary$.MODULE$.equals(padding)) {
                  var12 = DenseVector$.MODULE$.fill$mFc$sp(kernel.length() - 1, data.valueAt$mcF$sp(dl - 1), scala.reflect.ClassTag..MODULE$.Float(), Semiring$.MODULE$.semiringFloat());
               } else if (OptPadding.Zero$.MODULE$.equals(padding)) {
                  var12 = DenseVector$.MODULE$.zeros$mFc$sp(kernel.length() - 1, scala.reflect.ClassTag..MODULE$.Float(), Zero$.MODULE$.FloatZero());
               } else {
                  label162: {
                     if (padding instanceof OptPadding.ValueOpt) {
                        OptPadding.ValueOpt var25 = (OptPadding.ValueOpt)padding;
                        Object v = var25.value();
                        if (v instanceof Float) {
                           float var27 = BoxesRunTime.unboxToFloat(v);
                           var12 = DenseVector$.MODULE$.fill$mFc$sp(kernel.length() - 1, var27, scala.reflect.ClassTag..MODULE$.Float(), Semiring$.MODULE$.semiringFloat());
                           break label162;
                        }
                     }

                     .MODULE$.require(false, () -> (new StringBuilder(31)).append("cannot handle OptPadding value ").append(padding).toString());
                     var12 = (DenseVector)DenseVector$.MODULE$.apply(scala.collection.immutable.Nil..MODULE$, scala.reflect.ClassTag..MODULE$.Float());
                  }
               }

               var10002[2] = var12;
               paddedData = var10000.vertcat(var10001.wrapRefArray(var10002), HasOps$.MODULE$.impl_Op_InPlace_DV_DV_Float_OpSet(), scala.reflect.ClassTag..MODULE$.Float(), Zero$.MODULE$.FloatZero());
            } else if (OptOverhang.PreserveLength$.MODULE$.equals(overhang)) {
               int leftPadding = BoxesRunTime.unboxToBoolean(breeze.numerics.package.isOdd$.MODULE$.apply(BoxesRunTime.boxToInteger(kernel.length()), breeze.numerics.package.isOdd$.MODULE$.isOddImpl_Int())) ? (kernel.length() - 1) / 2 : kernel.length() / 2 - 1;
               int rightPadding = kernel.length() - leftPadding - 1;
               DenseVector$ var44 = DenseVector$.MODULE$;
               ScalaRunTime var45 = scala.runtime.ScalaRunTime..MODULE$;
               DenseVector[] var46 = new DenseVector[3];
               DenseVector var11;
               if (OptPadding.Cyclical$.MODULE$.equals(padding)) {
                  var11 = (DenseVector)data.apply(scala.runtime.RichInt..MODULE$.to$extension(.MODULE$.intWrapper(dl - leftPadding), dl - 1), HasOps$.MODULE$.canSlice_DV_Range_eq_DV());
               } else if (OptPadding.Boundary$.MODULE$.equals(padding)) {
                  var11 = (DenseVector)DenseVector$.MODULE$.ones$mFc$sp(leftPadding, scala.reflect.ClassTag..MODULE$.Float(), Semiring$.MODULE$.semiringFloat()).$times(BoxesRunTime.boxToFloat(data.apply$mcF$sp(0)), HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Float_OpMulMatrix());
               } else if (OptPadding.Zero$.MODULE$.equals(padding)) {
                  var11 = DenseVector$.MODULE$.zeros$mFc$sp(leftPadding, scala.reflect.ClassTag..MODULE$.Float(), Zero$.MODULE$.FloatZero());
               } else {
                  label165: {
                     if (padding instanceof OptPadding.ValueOpt) {
                        OptPadding.ValueOpt var31 = (OptPadding.ValueOpt)padding;
                        Object v = var31.value();
                        if (v instanceof Float) {
                           float var33 = BoxesRunTime.unboxToFloat(v);
                           var11 = (DenseVector)DenseVector$.MODULE$.ones$mFc$sp(leftPadding, scala.reflect.ClassTag..MODULE$.Float(), Semiring$.MODULE$.semiringFloat()).$times(BoxesRunTime.boxToFloat(var33), HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Float_OpMulMatrix());
                           break label165;
                        }
                     }

                     .MODULE$.require(false, () -> (new StringBuilder(31)).append("cannot handle OptPadding value ").append(padding).toString());
                     var11 = (DenseVector)DenseVector$.MODULE$.apply(scala.collection.immutable.Nil..MODULE$, scala.reflect.ClassTag..MODULE$.Float());
                  }
               }

               var46[0] = var11;
               var46[1] = data;
               DenseVector var10;
               if (OptPadding.Cyclical$.MODULE$.equals(padding)) {
                  var10 = (DenseVector)data.apply(scala.runtime.RichInt..MODULE$.to$extension(.MODULE$.intWrapper(0), rightPadding - 1), HasOps$.MODULE$.canSlice_DV_Range_eq_DV());
               } else if (OptPadding.Boundary$.MODULE$.equals(padding)) {
                  var10 = (DenseVector)DenseVector$.MODULE$.ones$mFc$sp(rightPadding, scala.reflect.ClassTag..MODULE$.Float(), Semiring$.MODULE$.semiringFloat()).$times(BoxesRunTime.boxToFloat(data.apply$mcF$sp(dl - 1)), HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Float_OpMulMatrix());
               } else if (OptPadding.Zero$.MODULE$.equals(padding)) {
                  var10 = DenseVector$.MODULE$.zeros$mFc$sp(rightPadding, scala.reflect.ClassTag..MODULE$.Float(), Zero$.MODULE$.FloatZero());
               } else {
                  label168: {
                     if (padding instanceof OptPadding.ValueOpt) {
                        OptPadding.ValueOpt var35 = (OptPadding.ValueOpt)padding;
                        Object v = var35.value();
                        if (v instanceof Float) {
                           float var37 = BoxesRunTime.unboxToFloat(v);
                           var10 = (DenseVector)DenseVector$.MODULE$.ones$mFc$sp(rightPadding, scala.reflect.ClassTag..MODULE$.Float(), Semiring$.MODULE$.semiringFloat()).$times(BoxesRunTime.boxToFloat(var37), HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Float_OpMulMatrix());
                           break label168;
                        }
                     }

                     .MODULE$.require(false, () -> (new StringBuilder(31)).append("cannot handle OptPadding value ").append(padding).toString());
                     var10 = (DenseVector)DenseVector$.MODULE$.apply(scala.collection.immutable.Nil..MODULE$, scala.reflect.ClassTag..MODULE$.Float());
                  }
               }

               var46[2] = var10;
               paddedData = var44.vertcat(var45.wrapRefArray(var46), HasOps$.MODULE$.impl_Op_InPlace_DV_DV_Float_OpSet(), scala.reflect.ClassTag..MODULE$.Float(), Zero$.MODULE$.FloatZero());
            } else {
               .MODULE$.require(false, () -> (new StringBuilder(32)).append("cannot handle OptOverhang value ").append(overhang).toString());
               paddedData = data;
            }

            int fullOptRangeLength = paddedData.length() - kernel.length() + 1;
            Range parsedOptRange;
            if (OptRange.All$.MODULE$.equals(range)) {
               parsedOptRange = scala.runtime.RichInt..MODULE$.until$extension(.MODULE$.intWrapper(0), fullOptRangeLength);
            } else {
               if (!(range instanceof OptRange.RangeOpt)) {
                  throw new MatchError(range);
               }

               OptRange.RangeOpt var41 = (OptRange.RangeOpt)range;
               Range negativeR = var41.r();
               parsedOptRange = RangeExtender$.MODULE$.getRangeWithoutNegativeIndexes$extension(breeze.linalg.package$.MODULE$.RangeToRangeExtender(negativeR), fullOptRangeLength);
            }

            return correlate ? (DenseVector)CanConvolve$.MODULE$.correlateLoopNoOverhang(paddedData, kernel, parsedOptRange, CanConvolve$.MODULE$.correlateLoopNoOverhangRangeT_Float()) : (DenseVector)CanConvolve$.MODULE$.correlateLoopNoOverhang(paddedData, reverse$.MODULE$.apply(kernel, reverse$.MODULE$.dvReverse(scala.reflect.ClassTag..MODULE$.Float())), parsedOptRange, CanConvolve$.MODULE$.correlateLoopNoOverhangRangeT_Float());
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
      dvT1DConvolve_Double = new CanConvolve() {
         public DenseVector apply(final DenseVector data, final DenseVector kernel, final OptRange range, final boolean correlate, final OptOverhang overhang, final OptPadding padding, final OptMethod method) {
            if (OptMethod.Automatic$.MODULE$.equals(method)) {
               .MODULE$.require(true);
               BoxedUnit var14 = BoxedUnit.UNIT;
            } else {
               .MODULE$.require(false, () -> "currently, only loop convolutions are supported.");
               BoxedUnit var47 = BoxedUnit.UNIT;
            }

            int kl = kernel.length();
            int dl = data.length();
            DenseVector paddedData;
            if (OptOverhang.None$.MODULE$.equals(overhang)) {
               paddedData = data;
            } else if (OptOverhang.Full$.MODULE$.equals(overhang)) {
               DenseVector$ var10000 = DenseVector$.MODULE$;
               ScalaRunTime var10001 = scala.runtime.ScalaRunTime..MODULE$;
               DenseVector[] var10002 = new DenseVector[3];
               DenseVector var13;
               if (OptPadding.Cyclical$.MODULE$.equals(padding)) {
                  var13 = (DenseVector)data.apply(scala.runtime.RichInt..MODULE$.to$extension(.MODULE$.intWrapper(dl - (kl - 1)), dl - 1), HasOps$.MODULE$.canSlice_DV_Range_eq_DV());
               } else if (OptPadding.Boundary$.MODULE$.equals(padding)) {
                  var13 = DenseVector$.MODULE$.fill$mDc$sp(kernel.length() - 1, data.valueAt$mcD$sp(0), scala.reflect.ClassTag..MODULE$.Double(), Semiring$.MODULE$.semiringD());
               } else if (OptPadding.Zero$.MODULE$.equals(padding)) {
                  var13 = DenseVector$.MODULE$.zeros$mDc$sp(kernel.length() - 1, scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());
               } else {
                  label159: {
                     if (padding instanceof OptPadding.ValueOpt) {
                        OptPadding.ValueOpt var21 = (OptPadding.ValueOpt)padding;
                        Object v = var21.value();
                        if (v instanceof Double) {
                           double var23 = BoxesRunTime.unboxToDouble(v);
                           var13 = DenseVector$.MODULE$.fill$mDc$sp(kernel.length() - 1, var23, scala.reflect.ClassTag..MODULE$.Double(), Semiring$.MODULE$.semiringD());
                           break label159;
                        }
                     }

                     .MODULE$.require(false, () -> (new StringBuilder(31)).append("cannot handle OptPadding value ").append(padding).toString());
                     var13 = (DenseVector)DenseVector$.MODULE$.apply(scala.collection.immutable.Nil..MODULE$, scala.reflect.ClassTag..MODULE$.Double());
                  }
               }

               var10002[0] = var13;
               var10002[1] = data;
               DenseVector var12;
               if (OptPadding.Cyclical$.MODULE$.equals(padding)) {
                  var12 = (DenseVector)data.apply(scala.runtime.RichInt..MODULE$.to$extension(.MODULE$.intWrapper(0), kl - 1), HasOps$.MODULE$.canSlice_DV_Range_eq_DV());
               } else if (OptPadding.Boundary$.MODULE$.equals(padding)) {
                  var12 = DenseVector$.MODULE$.fill$mDc$sp(kernel.length() - 1, data.valueAt$mcD$sp(dl - 1), scala.reflect.ClassTag..MODULE$.Double(), Semiring$.MODULE$.semiringD());
               } else if (OptPadding.Zero$.MODULE$.equals(padding)) {
                  var12 = DenseVector$.MODULE$.zeros$mDc$sp(kernel.length() - 1, scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());
               } else {
                  label162: {
                     if (padding instanceof OptPadding.ValueOpt) {
                        OptPadding.ValueOpt var26 = (OptPadding.ValueOpt)padding;
                        Object v = var26.value();
                        if (v instanceof Double) {
                           double var28 = BoxesRunTime.unboxToDouble(v);
                           var12 = DenseVector$.MODULE$.fill$mDc$sp(kernel.length() - 1, var28, scala.reflect.ClassTag..MODULE$.Double(), Semiring$.MODULE$.semiringD());
                           break label162;
                        }
                     }

                     .MODULE$.require(false, () -> (new StringBuilder(31)).append("cannot handle OptPadding value ").append(padding).toString());
                     var12 = (DenseVector)DenseVector$.MODULE$.apply(scala.collection.immutable.Nil..MODULE$, scala.reflect.ClassTag..MODULE$.Double());
                  }
               }

               var10002[2] = var12;
               paddedData = var10000.vertcat(var10001.wrapRefArray(var10002), HasOps$.MODULE$.impl_Op_InPlace_DV_DV_Double_OpSet(), scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());
            } else if (OptOverhang.PreserveLength$.MODULE$.equals(overhang)) {
               int leftPadding = BoxesRunTime.unboxToBoolean(breeze.numerics.package.isOdd$.MODULE$.apply(BoxesRunTime.boxToInteger(kernel.length()), breeze.numerics.package.isOdd$.MODULE$.isOddImpl_Int())) ? (kernel.length() - 1) / 2 : kernel.length() / 2 - 1;
               int rightPadding = kernel.length() - leftPadding - 1;
               DenseVector$ var48 = DenseVector$.MODULE$;
               ScalaRunTime var49 = scala.runtime.ScalaRunTime..MODULE$;
               DenseVector[] var50 = new DenseVector[3];
               DenseVector var11;
               if (OptPadding.Cyclical$.MODULE$.equals(padding)) {
                  var11 = (DenseVector)data.apply(scala.runtime.RichInt..MODULE$.to$extension(.MODULE$.intWrapper(dl - leftPadding), dl - 1), HasOps$.MODULE$.canSlice_DV_Range_eq_DV());
               } else if (OptPadding.Boundary$.MODULE$.equals(padding)) {
                  var11 = (DenseVector)DenseVector$.MODULE$.ones$mDc$sp(leftPadding, scala.reflect.ClassTag..MODULE$.Double(), Semiring$.MODULE$.semiringD()).$times(BoxesRunTime.boxToDouble(data.apply$mcD$sp(0)), HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Double_OpMulMatrix());
               } else if (OptPadding.Zero$.MODULE$.equals(padding)) {
                  var11 = DenseVector$.MODULE$.zeros$mDc$sp(leftPadding, scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());
               } else {
                  label165: {
                     if (padding instanceof OptPadding.ValueOpt) {
                        OptPadding.ValueOpt var33 = (OptPadding.ValueOpt)padding;
                        Object v = var33.value();
                        if (v instanceof Double) {
                           double var35 = BoxesRunTime.unboxToDouble(v);
                           var11 = (DenseVector)DenseVector$.MODULE$.ones$mDc$sp(leftPadding, scala.reflect.ClassTag..MODULE$.Double(), Semiring$.MODULE$.semiringD()).$times(BoxesRunTime.boxToDouble(var35), HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Double_OpMulMatrix());
                           break label165;
                        }
                     }

                     .MODULE$.require(false, () -> (new StringBuilder(31)).append("cannot handle OptPadding value ").append(padding).toString());
                     var11 = (DenseVector)DenseVector$.MODULE$.apply(scala.collection.immutable.Nil..MODULE$, scala.reflect.ClassTag..MODULE$.Double());
                  }
               }

               var50[0] = var11;
               var50[1] = data;
               DenseVector var10;
               if (OptPadding.Cyclical$.MODULE$.equals(padding)) {
                  var10 = (DenseVector)data.apply(scala.runtime.RichInt..MODULE$.to$extension(.MODULE$.intWrapper(0), rightPadding - 1), HasOps$.MODULE$.canSlice_DV_Range_eq_DV());
               } else if (OptPadding.Boundary$.MODULE$.equals(padding)) {
                  var10 = (DenseVector)DenseVector$.MODULE$.ones$mDc$sp(rightPadding, scala.reflect.ClassTag..MODULE$.Double(), Semiring$.MODULE$.semiringD()).$times(BoxesRunTime.boxToDouble(data.apply$mcD$sp(dl - 1)), HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Double_OpMulMatrix());
               } else if (OptPadding.Zero$.MODULE$.equals(padding)) {
                  var10 = DenseVector$.MODULE$.zeros$mDc$sp(rightPadding, scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());
               } else {
                  label168: {
                     if (padding instanceof OptPadding.ValueOpt) {
                        OptPadding.ValueOpt var38 = (OptPadding.ValueOpt)padding;
                        Object v = var38.value();
                        if (v instanceof Double) {
                           double var40 = BoxesRunTime.unboxToDouble(v);
                           var10 = (DenseVector)DenseVector$.MODULE$.ones$mDc$sp(rightPadding, scala.reflect.ClassTag..MODULE$.Double(), Semiring$.MODULE$.semiringD()).$times(BoxesRunTime.boxToDouble(var40), HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Double_OpMulMatrix());
                           break label168;
                        }
                     }

                     .MODULE$.require(false, () -> (new StringBuilder(31)).append("cannot handle OptPadding value ").append(padding).toString());
                     var10 = (DenseVector)DenseVector$.MODULE$.apply(scala.collection.immutable.Nil..MODULE$, scala.reflect.ClassTag..MODULE$.Double());
                  }
               }

               var50[2] = var10;
               paddedData = var48.vertcat(var49.wrapRefArray(var50), HasOps$.MODULE$.impl_Op_InPlace_DV_DV_Double_OpSet(), scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());
            } else {
               .MODULE$.require(false, () -> (new StringBuilder(32)).append("cannot handle OptOverhang value ").append(overhang).toString());
               paddedData = data;
            }

            int fullOptRangeLength = paddedData.length() - kernel.length() + 1;
            Range parsedOptRange;
            if (OptRange.All$.MODULE$.equals(range)) {
               parsedOptRange = scala.runtime.RichInt..MODULE$.until$extension(.MODULE$.intWrapper(0), fullOptRangeLength);
            } else {
               if (!(range instanceof OptRange.RangeOpt)) {
                  throw new MatchError(range);
               }

               OptRange.RangeOpt var45 = (OptRange.RangeOpt)range;
               Range negativeR = var45.r();
               parsedOptRange = RangeExtender$.MODULE$.getRangeWithoutNegativeIndexes$extension(breeze.linalg.package$.MODULE$.RangeToRangeExtender(negativeR), fullOptRangeLength);
            }

            return correlate ? (DenseVector)CanConvolve$.MODULE$.correlateLoopNoOverhang(paddedData, kernel, parsedOptRange, CanConvolve$.MODULE$.correlateLoopNoOverhangRangeT_Double()) : (DenseVector)CanConvolve$.MODULE$.correlateLoopNoOverhang(paddedData, reverse$.MODULE$.apply(kernel, reverse$.MODULE$.dvReverse(scala.reflect.ClassTag..MODULE$.Double())), parsedOptRange, CanConvolve$.MODULE$.correlateLoopNoOverhangRangeT_Double());
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
      dvTKernel1DConvolve_Int = new CanConvolve() {
         public DenseVector apply(final DenseVector data, final FIRKernel1D kernel, final OptRange range, final boolean correlateVal, final OptOverhang overhang, final OptPadding padding, final OptMethod method) {
            return correlateVal ? (DenseVector)breeze.signal.package$.MODULE$.correlate(data, kernel.kernel(), range, overhang, padding, method, CanConvolve$.MODULE$.dvT1DConvolve_Int()) : (DenseVector)breeze.signal.package$.MODULE$.convolve(data, kernel.kernel(), range, overhang, padding, method, CanConvolve$.MODULE$.dvT1DConvolve_Int());
         }
      };
      dvTKernel1DConvolve_Long = new CanConvolve() {
         public DenseVector apply(final DenseVector data, final FIRKernel1D kernel, final OptRange range, final boolean correlateVal, final OptOverhang overhang, final OptPadding padding, final OptMethod method) {
            return correlateVal ? (DenseVector)breeze.signal.package$.MODULE$.correlate(data, kernel.kernel(), range, overhang, padding, method, CanConvolve$.MODULE$.dvT1DConvolve_Long()) : (DenseVector)breeze.signal.package$.MODULE$.convolve(data, kernel.kernel(), range, overhang, padding, method, CanConvolve$.MODULE$.dvT1DConvolve_Long());
         }
      };
      dvTKernel1DConvolve_Float = new CanConvolve() {
         public DenseVector apply(final DenseVector data, final FIRKernel1D kernel, final OptRange range, final boolean correlateVal, final OptOverhang overhang, final OptPadding padding, final OptMethod method) {
            return correlateVal ? (DenseVector)breeze.signal.package$.MODULE$.correlate(data, kernel.kernel(), range, overhang, padding, method, CanConvolve$.MODULE$.dvT1DConvolve_Float()) : (DenseVector)breeze.signal.package$.MODULE$.convolve(data, kernel.kernel(), range, overhang, padding, method, CanConvolve$.MODULE$.dvT1DConvolve_Float());
         }
      };
      dvTKernel1DConvolve_Double = new CanConvolve() {
         public DenseVector apply(final DenseVector data, final FIRKernel1D kernel, final OptRange range, final boolean correlateVal, final OptOverhang overhang, final OptPadding padding, final OptMethod method) {
            return correlateVal ? (DenseVector)breeze.signal.package$.MODULE$.correlate(data, kernel.kernel(), range, overhang, padding, method, CanConvolve$.MODULE$.dvT1DConvolve_Double()) : (DenseVector)breeze.signal.package$.MODULE$.convolve(data, kernel.kernel(), range, overhang, padding, method, CanConvolve$.MODULE$.dvT1DConvolve_Double());
         }
      };
      correlateLoopNoOverhangRangeT_Double = new CanConvolve.CanCorrelateNoOverhang() {
         public DenseVector apply(final DenseVector data, final DenseVector kernel, final Range range) {
            .MODULE$.require(data.length() * kernel.length() != 0, () -> "data and kernel must be non-empty DenseVectors");
            .MODULE$.require(data.length() >= kernel.length(), () -> (new StringBuilder(66)).append("kernel (").append(kernel.length()).append(") cannot be longer than data(").append(data.length()).append(") to be convolved/correlated!").toString());
            .MODULE$.require(range.start() >= 0 && range.last() <= data.length() - kernel.length() + 1, (JFunction0.mcV.sp)() -> CanConvolve$.MODULE$.logger().error(() -> (new StringBuilder(87)).append("range (start ").append(range.start()).append(", end ").append(range.end()).append(", step ").append(range.step()).append(", inclusive ").append(range.isInclusive()).append(") is OOB for data (length ").append(data.length()).append(") and kernel (length ").append(kernel.length()).append(")!").toString()));
            Vector dataVect = data.toScalaVector();
            Vector kernelVect = kernel.toScalaVector();
            double zero = (double)0;
            double[] tempArr = (double[])range.map((JFunction1.mcDI.sp)(count) -> {
               int ki = 0;

               double sum;
               for(sum = zero; ki < kernel.length(); ++ki) {
                  sum += BoxesRunTime.unboxToDouble(dataVect.apply(count + ki)) * BoxesRunTime.unboxToDouble(kernelVect.apply(ki));
               }

               return sum;
            }).toArray(scala.reflect.ClassTag..MODULE$.Double());
            return DenseVector$.MODULE$.apply$mDc$sp(tempArr);
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
      correlateLoopNoOverhangRangeT_Float = new CanConvolve.CanCorrelateNoOverhang() {
         public DenseVector apply(final DenseVector data, final DenseVector kernel, final Range range) {
            .MODULE$.require(data.length() * kernel.length() != 0, () -> "data and kernel must be non-empty DenseVectors");
            .MODULE$.require(data.length() >= kernel.length(), () -> (new StringBuilder(66)).append("kernel (").append(kernel.length()).append(") cannot be longer than data(").append(data.length()).append(") to be convolved/correlated!").toString());
            .MODULE$.require(range.start() >= 0 && range.last() <= data.length() - kernel.length() + 1, (JFunction0.mcV.sp)() -> CanConvolve$.MODULE$.logger().error(() -> (new StringBuilder(87)).append("range (start ").append(range.start()).append(", end ").append(range.end()).append(", step ").append(range.step()).append(", inclusive ").append(range.isInclusive()).append(") is OOB for data (length ").append(data.length()).append(") and kernel (length ").append(kernel.length()).append(")!").toString()));
            Vector dataVect = data.toScalaVector();
            Vector kernelVect = kernel.toScalaVector();
            float zero = (float)0;
            float[] tempArr = (float[])range.map((JFunction1.mcFI.sp)(count) -> {
               int ki = 0;

               float sum;
               for(sum = zero; ki < kernel.length(); ++ki) {
                  sum += BoxesRunTime.unboxToFloat(dataVect.apply(count + ki)) * BoxesRunTime.unboxToFloat(kernelVect.apply(ki));
               }

               return sum;
            }).toArray(scala.reflect.ClassTag..MODULE$.Float());
            return DenseVector$.MODULE$.apply$mFc$sp(tempArr);
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
      correlateLoopNoOverhangRangeT_Long = new CanConvolve.CanCorrelateNoOverhang() {
         public DenseVector apply(final DenseVector data, final DenseVector kernel, final Range range) {
            .MODULE$.require(data.length() * kernel.length() != 0, () -> "data and kernel must be non-empty DenseVectors");
            .MODULE$.require(data.length() >= kernel.length(), () -> (new StringBuilder(66)).append("kernel (").append(kernel.length()).append(") cannot be longer than data(").append(data.length()).append(") to be convolved/correlated!").toString());
            .MODULE$.require(range.start() >= 0 && range.last() <= data.length() - kernel.length() + 1, (JFunction0.mcV.sp)() -> CanConvolve$.MODULE$.logger().error(() -> (new StringBuilder(87)).append("range (start ").append(range.start()).append(", end ").append(range.end()).append(", step ").append(range.step()).append(", inclusive ").append(range.isInclusive()).append(") is OOB for data (length ").append(data.length()).append(") and kernel (length ").append(kernel.length()).append(")!").toString()));
            Vector dataVect = data.toScalaVector();
            Vector kernelVect = kernel.toScalaVector();
            long zero = (long)0;
            long[] tempArr = (long[])range.map((JFunction1.mcJI.sp)(count) -> {
               int ki = 0;

               long sum;
               for(sum = zero; ki < kernel.length(); ++ki) {
                  sum += BoxesRunTime.unboxToLong(dataVect.apply(count + ki)) * BoxesRunTime.unboxToLong(kernelVect.apply(ki));
               }

               return sum;
            }).toArray(scala.reflect.ClassTag..MODULE$.Long());
            return DenseVector$.MODULE$.apply$mJc$sp(tempArr);
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
      correlateLoopNoOverhangRangeInt = new CanConvolve.CanCorrelateNoOverhang() {
         public DenseVector apply(final DenseVector data, final DenseVector kernel, final Range range) {
            .MODULE$.require(data.length() * kernel.length() != 0, () -> "data and kernel must be non-empty DenseVectors");
            .MODULE$.require(data.length() >= kernel.length(), () -> "kernel cannot be longer than data to be convolved/corelated!");
            .MODULE$.require(range.start() >= 0 && range.last() <= data.length() - kernel.length() + 1, (JFunction0.mcV.sp)() -> CanConvolve$.MODULE$.logger().error(() -> (new StringBuilder(87)).append("range (start ").append(range.start()).append(", end ").append(range.end()).append(", step ").append(range.step()).append(", inclusive ").append(range.isInclusive()).append(") is OOB for data (length ").append(data.length()).append(") and kernel (length ").append(kernel.length()).append(")!").toString()));
            Vector dataL = ((DenseVector)convert$.MODULE$.apply(data, scala.Long..MODULE$, HasOps$.MODULE$.canMapV1DV(DenseVector$.MODULE$.DV_scalarOf(), convert$.MODULE$.impl2_Int_Long(), DenseVector$.MODULE$.DV_canMapValues(scala.reflect.ClassTag..MODULE$.Long())))).toScalaVector();
            Vector kernelL = ((DenseVector)convert$.MODULE$.apply(kernel, scala.Long..MODULE$, HasOps$.MODULE$.canMapV1DV(DenseVector$.MODULE$.DV_scalarOf(), convert$.MODULE$.impl2_Int_Long(), DenseVector$.MODULE$.DV_canMapValues(scala.reflect.ClassTag..MODULE$.Long())))).toScalaVector();
            return DenseVector$.MODULE$.tabulate$mIc$sp(range, (JFunction1.mcII.sp)(count) -> {
               int ki = 0;

               long sum;
               for(sum = 0L; ki < kernel.length(); ++ki) {
                  sum += BoxesRunTime.unboxToLong(dataL.apply(count + ki)) * BoxesRunTime.unboxToLong(kernelL.apply(ki));
               }

               return (int)sum;
            }, scala.reflect.ClassTag..MODULE$.Int());
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   public LazyLogger logger() {
      return SerializableLogging.logger$(this);
   }

   public LazyLogger breeze$util$SerializableLogging$$_the_logger() {
      return breeze$util$SerializableLogging$$_the_logger;
   }

   public void breeze$util$SerializableLogging$$_the_logger_$eq(final LazyLogger x$1) {
      breeze$util$SerializableLogging$$_the_logger = x$1;
   }

   public CanConvolve dvT1DConvolve_Int() {
      return dvT1DConvolve_Int;
   }

   public CanConvolve dvT1DConvolve_Long() {
      return dvT1DConvolve_Long;
   }

   public CanConvolve dvT1DConvolve_Float() {
      return dvT1DConvolve_Float;
   }

   public CanConvolve dvT1DConvolve_Double() {
      return dvT1DConvolve_Double;
   }

   public CanConvolve dvTKernel1DConvolve_Int() {
      return dvTKernel1DConvolve_Int;
   }

   public CanConvolve dvTKernel1DConvolve_Long() {
      return dvTKernel1DConvolve_Long;
   }

   public CanConvolve dvTKernel1DConvolve_Float() {
      return dvTKernel1DConvolve_Float;
   }

   public CanConvolve dvTKernel1DConvolve_Double() {
      return dvTKernel1DConvolve_Double;
   }

   public Object correlateLoopNoOverhang(final Object data, final Object kernel, final Range range, final CanConvolve.CanCorrelateNoOverhang canCorrelateNoOverhang) {
      return canCorrelateNoOverhang.apply(data, kernel, range);
   }

   public CanConvolve.CanCorrelateNoOverhang correlateLoopNoOverhangRangeT_Double() {
      return correlateLoopNoOverhangRangeT_Double;
   }

   public CanConvolve.CanCorrelateNoOverhang correlateLoopNoOverhangRangeT_Float() {
      return correlateLoopNoOverhangRangeT_Float;
   }

   public CanConvolve.CanCorrelateNoOverhang correlateLoopNoOverhangRangeT_Long() {
      return correlateLoopNoOverhangRangeT_Long;
   }

   public CanConvolve.CanCorrelateNoOverhang correlateLoopNoOverhangRangeInt() {
      return correlateLoopNoOverhangRangeInt;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(CanConvolve$.class);
   }

   private CanConvolve$() {
   }
}
