package breeze.linalg;

import breeze.linalg.operators.HasOps$;
import breeze.math.Semiring;
import breeze.math.Semiring$;
import breeze.stats.mean$;
import breeze.stats.median$;
import breeze.storage.Zero$;
import java.lang.invoke.SerializedLambda;
import java.util.Arrays;
import scala.MatchError;
import scala.Double.;
import scala.runtime.BoxesRunTime;

public final class CanPadLeft$ {
   public static final CanPadLeft$ MODULE$ = new CanPadLeft$();

   public CanPadLeft implDV_OptPadDim_Int() {
      return new CanPadLeft() {
         public DenseVector apply(final DenseVector v, final Options.Dimensions1 optDim, final Options.OptPadMode optMode) {
            DenseVector var4;
            if (Options.Zero$.MODULE$.equals(optMode)) {
               var4 = this.padLeft1ImplZero(v, optDim);
            } else if (Options.Max$.MODULE$.equals(optMode)) {
               var4 = this.padLeft1ImplFixed(v, optDim, BoxesRunTime.unboxToInt(max$.MODULE$.apply(v, max$.MODULE$.reduce_Int(HasOps$.MODULE$.DV_canIterateValues()))));
            } else if (Options.Min$.MODULE$.equals(optMode)) {
               var4 = this.padLeft1ImplFixed(v, optDim, BoxesRunTime.unboxToInt(min$.MODULE$.apply(v, min$.MODULE$.reduce_Int(HasOps$.MODULE$.DV_canIterateValues()))));
            } else if (Options.Mean$.MODULE$.equals(optMode)) {
               var4 = this.padLeft1ImplFixed(v, optDim, BoxesRunTime.unboxToInt(convert$.MODULE$.apply(mean$.MODULE$.apply(convert$.MODULE$.apply(v, .MODULE$, HasOps$.MODULE$.canMapV1DV(DenseVector$.MODULE$.DV_scalarOf(), convert$.MODULE$.impl2_Int_Double(), DenseVector$.MODULE$.DV_canMapValues$mIDc$sp(scala.reflect.ClassTag..MODULE$.Double()))), mean$.MODULE$.reduce_Double(HasOps$.MODULE$.DV_canIterateValues())), scala.Int..MODULE$, convert$.MODULE$.impl2_Double_Int())));
            } else if (Options.Median$.MODULE$.equals(optMode)) {
               var4 = this.padLeft1ImplFixed(v, optDim, BoxesRunTime.unboxToInt(convert$.MODULE$.apply(median$.MODULE$.apply(v, median$.MODULE$.reduce(scala.reflect.ClassTag..MODULE$.Int(), median$.MODULE$.reduceArray_Int())), scala.Int..MODULE$, convert$.MODULE$.impl2_Int_Int())));
            } else {
               if (optMode instanceof Options.Value) {
                  Options.Value var6 = (Options.Value)optMode;
                  Object n = var6.n();
                  if (n instanceof Integer) {
                     int var8 = BoxesRunTime.unboxToInt(n);
                     var4 = this.padLeft1ImplFixed(v, optDim, var8);
                     return var4;
                  }
               }

               if (Options.Wrap$.MODULE$.equals(optMode)) {
                  var4 = this.padLeft1ImplDV(v, optDim, v);
               } else {
                  if (!Options.Reflect$.MODULE$.equals(optMode)) {
                     throw new MatchError(optMode);
                  }

                  var4 = this.padLeft1ImplDV(v, optDim, (DenseVector)reverse$.MODULE$.apply(v, reverse$.MODULE$.dvReverse(scala.reflect.ClassTag..MODULE$.Int())));
               }
            }

            return var4;
         }

         private DenseVector padLeft1ImplZero(final DenseVector v, final Options.Dimensions1 optDim) {
            return this.padLeft1ImplFixed(v, optDim, ((Semiring)scala.Predef..MODULE$.implicitly(Semiring$.MODULE$.semiringInt())).zero$mcI$sp());
         }

         private DenseVector padLeft1ImplFixed(final DenseVector v, final Options.Dimensions1 optDim, final int padValue) {
            boolean cond$macro$1 = optDim.n1() > 0;
            if (!cond$macro$1) {
               throw new IllegalArgumentException("requirement failed: Cannot pad to zero or negative length!: optDim.n1.>(0)");
            } else {
               int var6 = v.length();
               DenseVector var4;
               if (optDim.n1() == var6) {
                  var4 = v.copy$mcI$sp();
               } else if (true && var6 < optDim.n1()) {
                  int[] res = new int[optDim.n1()];
                  Arrays.fill(res, padValue);
                  DenseVector r = DenseVector$.MODULE$.apply$mIc$sp(res);
                  ((NumericOps)r.apply(scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(optDim.n1() - var6), optDim.n1()), HasOps$.MODULE$.canSlice_DV_Range_eq_DV())).$colon$eq(v, HasOps$.MODULE$.impl_Op_InPlace_DV_DV_Int_OpSet());
                  var4 = r;
               } else {
                  if (false || optDim.n1() >= var6) {
                     throw new IllegalArgumentException((new StringBuilder(31)).append("(n) specification incorrect: ").append(optDim.toString()).append(" !").toString());
                  }

                  var4 = ((DenseVector)v.apply(scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(v.length() - optDim.n1()), v.length()), HasOps$.MODULE$.canSlice_DV_Range_eq_DV())).copy$mcI$sp();
               }

               return var4;
            }
         }

         private DenseVector padLeft1ImplDV(final DenseVector v, final Options.Dimensions1 optDim, final DenseVector padDV) {
            boolean cond$macro$1 = optDim.n1() > 0;
            if (!cond$macro$1) {
               throw new IllegalArgumentException("requirement failed: Cannot pad to zero or negative length!: optDim.n1.>(0)");
            } else {
               boolean cond$macro$2 = optDim.n1() - v.length() <= padDV.length();
               if (!cond$macro$2) {
                  throw new IllegalArgumentException("requirement failed: Cannot pad beyond specified padding DenseVector!: optDim.n1.-(v.length).<=(padDV.length)");
               } else {
                  int var7 = v.length();
                  DenseVector var4;
                  if (optDim.n1() == var7) {
                     var4 = v.copy$mcI$sp();
                  } else if (true && var7 < optDim.n1()) {
                     var4 = DenseVector$.MODULE$.apply$mIc$sp((int[])scala.collection.ArrayOps..MODULE$.$plus$plus$extension(scala.Predef..MODULE$.intArrayOps(((DenseVector)reverse$.MODULE$.apply(((TensorLike)reverse$.MODULE$.apply(padDV, reverse$.MODULE$.dvReverse(scala.reflect.ClassTag..MODULE$.Int()))).apply(scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), optDim.n1() - var7), HasOps$.MODULE$.canSlice_DV_Range_eq_DV()), reverse$.MODULE$.dvReverse(scala.reflect.ClassTag..MODULE$.Int()))).toArray$mcI$sp(scala.reflect.ClassTag..MODULE$.Int())), v.toArray$mcI$sp(scala.reflect.ClassTag..MODULE$.Int()), scala.reflect.ClassTag..MODULE$.Int()));
                  } else {
                     if (false || optDim.n1() >= var7) {
                        throw new IllegalArgumentException((new StringBuilder(31)).append("(n) specification incorrect: ").append(optDim.toString()).append(" !").toString());
                     }

                     var4 = ((DenseVector)v.apply(scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), optDim.n1()), HasOps$.MODULE$.canSlice_DV_Range_eq_DV())).copy$mcI$sp();
                  }

                  return var4;
               }
            }
         }
      };
   }

   public CanPadLeft implDV_OptPadDim_Long() {
      return new CanPadLeft() {
         public DenseVector apply(final DenseVector v, final Options.Dimensions1 optDim, final Options.OptPadMode optMode) {
            DenseVector var4;
            if (Options.Zero$.MODULE$.equals(optMode)) {
               var4 = this.padLeft1ImplZero(v, optDim);
            } else if (Options.Max$.MODULE$.equals(optMode)) {
               var4 = this.padLeft1ImplFixed(v, optDim, BoxesRunTime.unboxToLong(max$.MODULE$.apply(v, max$.MODULE$.reduce_Long(HasOps$.MODULE$.DV_canIterateValues()))));
            } else if (Options.Min$.MODULE$.equals(optMode)) {
               var4 = this.padLeft1ImplFixed(v, optDim, BoxesRunTime.unboxToLong(min$.MODULE$.apply(v, min$.MODULE$.reduce_Long(HasOps$.MODULE$.DV_canIterateValues()))));
            } else if (Options.Mean$.MODULE$.equals(optMode)) {
               var4 = this.padLeft1ImplFixed(v, optDim, BoxesRunTime.unboxToLong(convert$.MODULE$.apply(mean$.MODULE$.apply(convert$.MODULE$.apply(v, .MODULE$, HasOps$.MODULE$.canMapV1DV(DenseVector$.MODULE$.DV_scalarOf(), convert$.MODULE$.impl2_Long_Double(), DenseVector$.MODULE$.DV_canMapValues(scala.reflect.ClassTag..MODULE$.Double()))), mean$.MODULE$.reduce_Double(HasOps$.MODULE$.DV_canIterateValues())), scala.Long..MODULE$, convert$.MODULE$.impl2_Double_Long())));
            } else if (Options.Median$.MODULE$.equals(optMode)) {
               var4 = this.padLeft1ImplFixed(v, optDim, BoxesRunTime.unboxToLong(convert$.MODULE$.apply(median$.MODULE$.apply(v, median$.MODULE$.reduce(scala.reflect.ClassTag..MODULE$.Long(), median$.MODULE$.reduceArray_Long())), scala.Long..MODULE$, convert$.MODULE$.impl2_Long_Long())));
            } else {
               if (optMode instanceof Options.Value) {
                  Options.Value var6 = (Options.Value)optMode;
                  Object n = var6.n();
                  if (n instanceof Long) {
                     long var8 = BoxesRunTime.unboxToLong(n);
                     var4 = this.padLeft1ImplFixed(v, optDim, var8);
                     return var4;
                  }
               }

               if (Options.Wrap$.MODULE$.equals(optMode)) {
                  var4 = this.padLeft1ImplDV(v, optDim, v);
               } else {
                  if (!Options.Reflect$.MODULE$.equals(optMode)) {
                     throw new MatchError(optMode);
                  }

                  var4 = this.padLeft1ImplDV(v, optDim, (DenseVector)reverse$.MODULE$.apply(v, reverse$.MODULE$.dvReverse(scala.reflect.ClassTag..MODULE$.Long())));
               }
            }

            return var4;
         }

         private DenseVector padLeft1ImplZero(final DenseVector v, final Options.Dimensions1 optDim) {
            return this.padLeft1ImplFixed(v, optDim, ((Semiring)scala.Predef..MODULE$.implicitly(Semiring$.MODULE$.semiringLong())).zero$mcJ$sp());
         }

         private DenseVector padLeft1ImplFixed(final DenseVector v, final Options.Dimensions1 optDim, final long padValue) {
            boolean cond$macro$1 = optDim.n1() > 0;
            if (!cond$macro$1) {
               throw new IllegalArgumentException("requirement failed: Cannot pad to zero or negative length!: optDim.n1.>(0)");
            } else {
               int var7 = v.length();
               DenseVector var5;
               if (optDim.n1() == var7) {
                  var5 = v.copy$mcJ$sp();
               } else if (true && var7 < optDim.n1()) {
                  long[] res = new long[optDim.n1()];
                  Arrays.fill(res, padValue);
                  DenseVector r = DenseVector$.MODULE$.apply$mJc$sp(res);
                  ((NumericOps)r.apply(scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(optDim.n1() - var7), optDim.n1()), HasOps$.MODULE$.canSlice_DV_Range_eq_DV())).$colon$eq(v, HasOps$.MODULE$.impl_Op_InPlace_DV_DV_Long_OpSet());
                  var5 = r;
               } else {
                  if (false || optDim.n1() >= var7) {
                     throw new IllegalArgumentException((new StringBuilder(31)).append("(n) specification incorrect: ").append(optDim.toString()).append(" !").toString());
                  }

                  var5 = ((DenseVector)v.apply(scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(v.length() - optDim.n1()), v.length()), HasOps$.MODULE$.canSlice_DV_Range_eq_DV())).copy$mcJ$sp();
               }

               return var5;
            }
         }

         private DenseVector padLeft1ImplDV(final DenseVector v, final Options.Dimensions1 optDim, final DenseVector padDV) {
            boolean cond$macro$1 = optDim.n1() > 0;
            if (!cond$macro$1) {
               throw new IllegalArgumentException("requirement failed: Cannot pad to zero or negative length!: optDim.n1.>(0)");
            } else {
               boolean cond$macro$2 = optDim.n1() - v.length() <= padDV.length();
               if (!cond$macro$2) {
                  throw new IllegalArgumentException("requirement failed: Cannot pad beyond specified padding DenseVector!: optDim.n1.-(v.length).<=(padDV.length)");
               } else {
                  int var7 = v.length();
                  DenseVector var4;
                  if (optDim.n1() == var7) {
                     var4 = v.copy$mcJ$sp();
                  } else if (true && var7 < optDim.n1()) {
                     var4 = DenseVector$.MODULE$.apply$mJc$sp((long[])scala.collection.ArrayOps..MODULE$.$plus$plus$extension(scala.Predef..MODULE$.longArrayOps(((DenseVector)reverse$.MODULE$.apply(((TensorLike)reverse$.MODULE$.apply(padDV, reverse$.MODULE$.dvReverse(scala.reflect.ClassTag..MODULE$.Long()))).apply(scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), optDim.n1() - var7), HasOps$.MODULE$.canSlice_DV_Range_eq_DV()), reverse$.MODULE$.dvReverse(scala.reflect.ClassTag..MODULE$.Long()))).toArray$mcJ$sp(scala.reflect.ClassTag..MODULE$.Long())), v.toArray$mcJ$sp(scala.reflect.ClassTag..MODULE$.Long()), scala.reflect.ClassTag..MODULE$.Long()));
                  } else {
                     if (false || optDim.n1() >= var7) {
                        throw new IllegalArgumentException((new StringBuilder(31)).append("(n) specification incorrect: ").append(optDim.toString()).append(" !").toString());
                     }

                     var4 = ((DenseVector)v.apply(scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), optDim.n1()), HasOps$.MODULE$.canSlice_DV_Range_eq_DV())).copy$mcJ$sp();
                  }

                  return var4;
               }
            }
         }
      };
   }

   public CanPadLeft implDV_OptPadDim_Float() {
      return new CanPadLeft() {
         public DenseVector apply(final DenseVector v, final Options.Dimensions1 optDim, final Options.OptPadMode optMode) {
            DenseVector var4;
            if (Options.Zero$.MODULE$.equals(optMode)) {
               var4 = this.padLeft1ImplZero(v, optDim);
            } else if (Options.Max$.MODULE$.equals(optMode)) {
               var4 = this.padLeft1ImplFixed(v, optDim, BoxesRunTime.unboxToFloat(max$.MODULE$.apply(v, max$.MODULE$.reduce_Float(HasOps$.MODULE$.DV_canIterateValues()))));
            } else if (Options.Min$.MODULE$.equals(optMode)) {
               var4 = this.padLeft1ImplFixed(v, optDim, BoxesRunTime.unboxToFloat(min$.MODULE$.apply(v, min$.MODULE$.reduce_Float(HasOps$.MODULE$.DV_canIterateValues()))));
            } else if (Options.Mean$.MODULE$.equals(optMode)) {
               var4 = this.padLeft1ImplFixed(v, optDim, BoxesRunTime.unboxToFloat(convert$.MODULE$.apply(mean$.MODULE$.apply(convert$.MODULE$.apply(v, .MODULE$, HasOps$.MODULE$.canMapV1DV(DenseVector$.MODULE$.DV_scalarOf(), convert$.MODULE$.impl2_Float_Double(), DenseVector$.MODULE$.DV_canMapValues$mFDc$sp(scala.reflect.ClassTag..MODULE$.Double()))), mean$.MODULE$.reduce_Double(HasOps$.MODULE$.DV_canIterateValues())), scala.Float..MODULE$, convert$.MODULE$.impl2_Double_Float())));
            } else if (Options.Median$.MODULE$.equals(optMode)) {
               var4 = this.padLeft1ImplFixed(v, optDim, BoxesRunTime.unboxToFloat(convert$.MODULE$.apply(median$.MODULE$.apply(v, median$.MODULE$.reduce(scala.reflect.ClassTag..MODULE$.Float(), median$.MODULE$.reduceArray_Float())), scala.Float..MODULE$, convert$.MODULE$.impl2_Float_Float())));
            } else {
               if (optMode instanceof Options.Value) {
                  Options.Value var6 = (Options.Value)optMode;
                  Object n = var6.n();
                  if (n instanceof Float) {
                     float var8 = BoxesRunTime.unboxToFloat(n);
                     var4 = this.padLeft1ImplFixed(v, optDim, var8);
                     return var4;
                  }
               }

               if (Options.Wrap$.MODULE$.equals(optMode)) {
                  var4 = this.padLeft1ImplDV(v, optDim, v);
               } else {
                  if (!Options.Reflect$.MODULE$.equals(optMode)) {
                     throw new MatchError(optMode);
                  }

                  var4 = this.padLeft1ImplDV(v, optDim, (DenseVector)reverse$.MODULE$.apply(v, reverse$.MODULE$.dvReverse(scala.reflect.ClassTag..MODULE$.Float())));
               }
            }

            return var4;
         }

         private DenseVector padLeft1ImplZero(final DenseVector v, final Options.Dimensions1 optDim) {
            return this.padLeft1ImplFixed(v, optDim, ((Semiring)scala.Predef..MODULE$.implicitly(Semiring$.MODULE$.semiringFloat())).zero$mcF$sp());
         }

         private DenseVector padLeft1ImplFixed(final DenseVector v, final Options.Dimensions1 optDim, final float padValue) {
            boolean cond$macro$1 = optDim.n1() > 0;
            if (!cond$macro$1) {
               throw new IllegalArgumentException("requirement failed: Cannot pad to zero or negative length!: optDim.n1.>(0)");
            } else {
               int var6 = v.length();
               DenseVector var4;
               if (optDim.n1() == var6) {
                  var4 = v.copy$mcF$sp();
               } else if (true && var6 < optDim.n1()) {
                  float[] res = new float[optDim.n1()];
                  Arrays.fill(res, padValue);
                  DenseVector r = DenseVector$.MODULE$.apply$mFc$sp(res);
                  ((NumericOps)r.apply(scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(optDim.n1() - var6), optDim.n1()), HasOps$.MODULE$.canSlice_DV_Range_eq_DV())).$colon$eq(v, HasOps$.MODULE$.impl_Op_InPlace_DV_DV_Float_OpSet());
                  var4 = r;
               } else {
                  if (false || optDim.n1() >= var6) {
                     throw new IllegalArgumentException((new StringBuilder(31)).append("(n) specification incorrect: ").append(optDim.toString()).append(" !").toString());
                  }

                  var4 = ((DenseVector)v.apply(scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(v.length() - optDim.n1()), v.length()), HasOps$.MODULE$.canSlice_DV_Range_eq_DV())).copy$mcF$sp();
               }

               return var4;
            }
         }

         private DenseVector padLeft1ImplDV(final DenseVector v, final Options.Dimensions1 optDim, final DenseVector padDV) {
            boolean cond$macro$1 = optDim.n1() > 0;
            if (!cond$macro$1) {
               throw new IllegalArgumentException("requirement failed: Cannot pad to zero or negative length!: optDim.n1.>(0)");
            } else {
               boolean cond$macro$2 = optDim.n1() - v.length() <= padDV.length();
               if (!cond$macro$2) {
                  throw new IllegalArgumentException("requirement failed: Cannot pad beyond specified padding DenseVector!: optDim.n1.-(v.length).<=(padDV.length)");
               } else {
                  int var7 = v.length();
                  DenseVector var4;
                  if (optDim.n1() == var7) {
                     var4 = v.copy$mcF$sp();
                  } else if (true && var7 < optDim.n1()) {
                     var4 = DenseVector$.MODULE$.apply$mFc$sp((float[])scala.collection.ArrayOps..MODULE$.$plus$plus$extension(scala.Predef..MODULE$.floatArrayOps(((DenseVector)reverse$.MODULE$.apply(((TensorLike)reverse$.MODULE$.apply(padDV, reverse$.MODULE$.dvReverse(scala.reflect.ClassTag..MODULE$.Float()))).apply(scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), optDim.n1() - var7), HasOps$.MODULE$.canSlice_DV_Range_eq_DV()), reverse$.MODULE$.dvReverse(scala.reflect.ClassTag..MODULE$.Float()))).toArray$mcF$sp(scala.reflect.ClassTag..MODULE$.Float())), v.toArray$mcF$sp(scala.reflect.ClassTag..MODULE$.Float()), scala.reflect.ClassTag..MODULE$.Float()));
                  } else {
                     if (false || optDim.n1() >= var7) {
                        throw new IllegalArgumentException((new StringBuilder(31)).append("(n) specification incorrect: ").append(optDim.toString()).append(" !").toString());
                     }

                     var4 = ((DenseVector)v.apply(scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), optDim.n1()), HasOps$.MODULE$.canSlice_DV_Range_eq_DV())).copy$mcF$sp();
                  }

                  return var4;
               }
            }
         }
      };
   }

   public CanPadLeft implDV_OptPadDim_Double() {
      return new CanPadLeft() {
         public DenseVector apply(final DenseVector v, final Options.Dimensions1 optDim, final Options.OptPadMode optMode) {
            DenseVector var4;
            if (Options.Zero$.MODULE$.equals(optMode)) {
               var4 = this.padLeft1ImplZero(v, optDim);
            } else if (Options.Max$.MODULE$.equals(optMode)) {
               var4 = this.padLeft1ImplFixed(v, optDim, BoxesRunTime.unboxToDouble(max$.MODULE$.apply(v, max$.MODULE$.reduce_Double(HasOps$.MODULE$.DV_canIterateValues()))));
            } else if (Options.Min$.MODULE$.equals(optMode)) {
               var4 = this.padLeft1ImplFixed(v, optDim, BoxesRunTime.unboxToDouble(min$.MODULE$.apply(v, min$.MODULE$.reduce_Double(HasOps$.MODULE$.DV_canIterateValues()))));
            } else if (Options.Mean$.MODULE$.equals(optMode)) {
               var4 = this.padLeft1ImplFixed(v, optDim, BoxesRunTime.unboxToDouble(convert$.MODULE$.apply(mean$.MODULE$.apply(convert$.MODULE$.apply(v, .MODULE$, HasOps$.MODULE$.canMapV1DV(DenseVector$.MODULE$.DV_scalarOf(), convert$.MODULE$.impl2_Double_Double(), DenseVector$.MODULE$.DV_canMapValues$mDDc$sp(scala.reflect.ClassTag..MODULE$.Double()))), mean$.MODULE$.reduce_Double(HasOps$.MODULE$.DV_canIterateValues())), .MODULE$, convert$.MODULE$.impl2_Double_Double())));
            } else if (Options.Median$.MODULE$.equals(optMode)) {
               var4 = this.padLeft1ImplFixed(v, optDim, BoxesRunTime.unboxToDouble(convert$.MODULE$.apply(median$.MODULE$.apply(v, median$.MODULE$.reduce(scala.reflect.ClassTag..MODULE$.Double(), median$.MODULE$.reduceArray_Double())), .MODULE$, convert$.MODULE$.impl2_Double_Double())));
            } else {
               if (optMode instanceof Options.Value) {
                  Options.Value var6 = (Options.Value)optMode;
                  Object n = var6.n();
                  if (n instanceof Double) {
                     double var8 = BoxesRunTime.unboxToDouble(n);
                     var4 = this.padLeft1ImplFixed(v, optDim, var8);
                     return var4;
                  }
               }

               if (Options.Wrap$.MODULE$.equals(optMode)) {
                  var4 = this.padLeft1ImplDV(v, optDim, v);
               } else {
                  if (!Options.Reflect$.MODULE$.equals(optMode)) {
                     throw new MatchError(optMode);
                  }

                  var4 = this.padLeft1ImplDV(v, optDim, (DenseVector)reverse$.MODULE$.apply(v, reverse$.MODULE$.dvReverse(scala.reflect.ClassTag..MODULE$.Double())));
               }
            }

            return var4;
         }

         private DenseVector padLeft1ImplZero(final DenseVector v, final Options.Dimensions1 optDim) {
            return this.padLeft1ImplFixed(v, optDim, ((Semiring)scala.Predef..MODULE$.implicitly(Semiring$.MODULE$.semiringD())).zero$mcD$sp());
         }

         private DenseVector padLeft1ImplFixed(final DenseVector v, final Options.Dimensions1 optDim, final double padValue) {
            boolean cond$macro$1 = optDim.n1() > 0;
            if (!cond$macro$1) {
               throw new IllegalArgumentException("requirement failed: Cannot pad to zero or negative length!: optDim.n1.>(0)");
            } else {
               int var7 = v.length();
               DenseVector var5;
               if (optDim.n1() == var7) {
                  var5 = v.copy$mcD$sp();
               } else if (true && var7 < optDim.n1()) {
                  double[] res = new double[optDim.n1()];
                  Arrays.fill(res, padValue);
                  DenseVector r = DenseVector$.MODULE$.apply$mDc$sp(res);
                  ((NumericOps)r.apply(scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(optDim.n1() - var7), optDim.n1()), HasOps$.MODULE$.canSlice_DV_Range_eq_DV())).$colon$eq(v, HasOps$.MODULE$.impl_Op_InPlace_DV_DV_Double_OpSet());
                  var5 = r;
               } else {
                  if (false || optDim.n1() >= var7) {
                     throw new IllegalArgumentException((new StringBuilder(31)).append("(n) specification incorrect: ").append(optDim.toString()).append(" !").toString());
                  }

                  var5 = ((DenseVector)v.apply(scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(v.length() - optDim.n1()), v.length()), HasOps$.MODULE$.canSlice_DV_Range_eq_DV())).copy$mcD$sp();
               }

               return var5;
            }
         }

         private DenseVector padLeft1ImplDV(final DenseVector v, final Options.Dimensions1 optDim, final DenseVector padDV) {
            boolean cond$macro$1 = optDim.n1() > 0;
            if (!cond$macro$1) {
               throw new IllegalArgumentException("requirement failed: Cannot pad to zero or negative length!: optDim.n1.>(0)");
            } else {
               boolean cond$macro$2 = optDim.n1() - v.length() <= padDV.length();
               if (!cond$macro$2) {
                  throw new IllegalArgumentException("requirement failed: Cannot pad beyond specified padding DenseVector!: optDim.n1.-(v.length).<=(padDV.length)");
               } else {
                  int var7 = v.length();
                  DenseVector var4;
                  if (optDim.n1() == var7) {
                     var4 = v.copy$mcD$sp();
                  } else if (true && var7 < optDim.n1()) {
                     var4 = DenseVector$.MODULE$.apply$mDc$sp((double[])scala.collection.ArrayOps..MODULE$.$plus$plus$extension(scala.Predef..MODULE$.doubleArrayOps(((DenseVector)reverse$.MODULE$.apply(((TensorLike)reverse$.MODULE$.apply(padDV, reverse$.MODULE$.dvReverse(scala.reflect.ClassTag..MODULE$.Double()))).apply(scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), optDim.n1() - var7), HasOps$.MODULE$.canSlice_DV_Range_eq_DV()), reverse$.MODULE$.dvReverse(scala.reflect.ClassTag..MODULE$.Double()))).toArray$mcD$sp(scala.reflect.ClassTag..MODULE$.Double())), v.toArray$mcD$sp(scala.reflect.ClassTag..MODULE$.Double()), scala.reflect.ClassTag..MODULE$.Double()));
                  } else {
                     if (false || optDim.n1() >= var7) {
                        throw new IllegalArgumentException((new StringBuilder(31)).append("(n) specification incorrect: ").append(optDim.toString()).append(" !").toString());
                     }

                     var4 = ((DenseVector)v.apply(scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), optDim.n1()), HasOps$.MODULE$.canSlice_DV_Range_eq_DV())).copy$mcD$sp();
                  }

                  return var4;
               }
            }
         }
      };
   }

   public CanPadLeft implDM_OptPadDim_OptPadMode_Int() {
      return new CanPadLeft() {
         public DenseMatrix apply(final DenseMatrix m, final Options.Dimensions2 optDim, final Options.OptPadMode optMode) {
            DenseMatrix var4;
            if (Options.Zero$.MODULE$.equals(optMode)) {
               var4 = this.padLeft2ImplZero(m, optDim);
            } else if (Options.Max$.MODULE$.equals(optMode)) {
               var4 = this.padLeft2ImplFixed(m, optDim, BoxesRunTime.unboxToInt(max$.MODULE$.apply(m, max$.MODULE$.reduce_Int(HasOps$.MODULE$.canTraverseValues()))));
            } else if (Options.Min$.MODULE$.equals(optMode)) {
               var4 = this.padLeft2ImplFixed(m, optDim, BoxesRunTime.unboxToInt(min$.MODULE$.apply(m, min$.MODULE$.reduce_Int(HasOps$.MODULE$.canTraverseValues()))));
            } else if (Options.Mean$.MODULE$.equals(optMode)) {
               var4 = this.padLeft2ImplFixed(m, optDim, BoxesRunTime.unboxToInt(convert$.MODULE$.apply(mean$.MODULE$.apply(convert$.MODULE$.apply(m.toDenseVector$mcI$sp(), .MODULE$, HasOps$.MODULE$.canMapV1DV(DenseVector$.MODULE$.DV_scalarOf(), convert$.MODULE$.impl2_Int_Double(), DenseVector$.MODULE$.DV_canMapValues$mIDc$sp(scala.reflect.ClassTag..MODULE$.Double()))), mean$.MODULE$.reduce_Double(HasOps$.MODULE$.DV_canIterateValues())), scala.Int..MODULE$, convert$.MODULE$.impl2_Double_Int())));
            } else if (Options.Median$.MODULE$.equals(optMode)) {
               var4 = this.padLeft2ImplFixed(m, optDim, BoxesRunTime.unboxToInt(convert$.MODULE$.apply(median$.MODULE$.apply(convert$.MODULE$.apply(m.toDenseVector$mcI$sp(), .MODULE$, HasOps$.MODULE$.canMapV1DV(DenseVector$.MODULE$.DV_scalarOf(), convert$.MODULE$.impl2_Int_Double(), DenseVector$.MODULE$.DV_canMapValues$mIDc$sp(scala.reflect.ClassTag..MODULE$.Double()))), median$.MODULE$.reduce(scala.reflect.ClassTag..MODULE$.Double(), median$.MODULE$.reduceArray_Double())), scala.Int..MODULE$, convert$.MODULE$.impl2_Double_Int())));
            } else {
               if (optMode instanceof Options.Value) {
                  Options.Value var6 = (Options.Value)optMode;
                  Object n = var6.n();
                  if (n instanceof Integer) {
                     int var8 = BoxesRunTime.unboxToInt(n);
                     var4 = this.padLeft2ImplFixed(m, optDim, var8);
                     return var4;
                  }
               }

               if (Options.Wrap$.MODULE$.equals(optMode)) {
                  throw new IllegalArgumentException("Option <Wrap> is not supported for 2D padding.");
               }

               if (Options.Reflect$.MODULE$.equals(optMode)) {
                  throw new IllegalArgumentException("Option <Reflect> is not supported for 2D padding.");
               }

               throw new MatchError(optMode);
            }

            return var4;
         }

         private DenseMatrix padLeft2ImplZero(final DenseMatrix v, final Options.Dimensions2 optDim) {
            return this.padLeft2ImplFixed(v, optDim, ((Semiring)scala.Predef..MODULE$.implicitly(Semiring$.MODULE$.semiringInt())).zero$mcI$sp());
         }

         private DenseMatrix padLeft2ImplFixed(final DenseMatrix m, final Options.Dimensions2 optDim, final int padValue) {
            boolean cond$macro$1 = optDim.n1() > 0 && optDim.n2() > 0;
            if (!cond$macro$1) {
               throw new IllegalArgumentException("requirement failed: Cannot pad to zero or negative length!: optDim.n1.>(0).&&(optDim.n2.>(0))");
            } else {
               DenseMatrix tempret = DenseMatrix$.MODULE$.zeros$mIc$sp(optDim.n1(), optDim.n2(), scala.reflect.ClassTag..MODULE$.Int(), Zero$.MODULE$.IntZero());
               int index$macro$8 = 1;

               for(int end$macro$9 = min$.MODULE$.apply$mIIIc$sp(optDim.n2(), m.cols(), min$.MODULE$.minImpl2_Int()); index$macro$8 <= end$macro$9; ++index$macro$8) {
                  int index$macro$3 = 1;

                  for(int end$macro$4 = min$.MODULE$.apply$mIIIc$sp(optDim.n1(), m.rows(), min$.MODULE$.minImpl2_Int()); index$macro$3 <= end$macro$4; ++index$macro$3) {
                     ((c, r) -> tempret.update$mcI$sp(optDim.n1() - r, optDim.n2() - c, m.apply$mcI$sp(m.rows() - r, m.cols() - c))).apply$mcVII$sp(index$macro$8, index$macro$3);
                  }
               }

               return tempret;
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   public CanPadLeft implDM_OptPadDim_OptPadMode_Long() {
      return new CanPadLeft() {
         public DenseMatrix apply(final DenseMatrix m, final Options.Dimensions2 optDim, final Options.OptPadMode optMode) {
            DenseMatrix var4;
            if (Options.Zero$.MODULE$.equals(optMode)) {
               var4 = this.padLeft2ImplZero(m, optDim);
            } else if (Options.Max$.MODULE$.equals(optMode)) {
               var4 = this.padLeft2ImplFixed(m, optDim, BoxesRunTime.unboxToLong(max$.MODULE$.apply(m, max$.MODULE$.reduce_Long(HasOps$.MODULE$.canTraverseValues()))));
            } else if (Options.Min$.MODULE$.equals(optMode)) {
               var4 = this.padLeft2ImplFixed(m, optDim, BoxesRunTime.unboxToLong(min$.MODULE$.apply(m, min$.MODULE$.reduce_Long(HasOps$.MODULE$.canTraverseValues()))));
            } else if (Options.Mean$.MODULE$.equals(optMode)) {
               var4 = this.padLeft2ImplFixed(m, optDim, BoxesRunTime.unboxToLong(convert$.MODULE$.apply(mean$.MODULE$.apply(convert$.MODULE$.apply(m.toDenseVector$mcJ$sp(), .MODULE$, HasOps$.MODULE$.canMapV1DV(DenseVector$.MODULE$.DV_scalarOf(), convert$.MODULE$.impl2_Long_Double(), DenseVector$.MODULE$.DV_canMapValues(scala.reflect.ClassTag..MODULE$.Double()))), mean$.MODULE$.reduce_Double(HasOps$.MODULE$.DV_canIterateValues())), scala.Long..MODULE$, convert$.MODULE$.impl2_Double_Long())));
            } else if (Options.Median$.MODULE$.equals(optMode)) {
               var4 = this.padLeft2ImplFixed(m, optDim, BoxesRunTime.unboxToLong(convert$.MODULE$.apply(median$.MODULE$.apply(convert$.MODULE$.apply(m.toDenseVector$mcJ$sp(), .MODULE$, HasOps$.MODULE$.canMapV1DV(DenseVector$.MODULE$.DV_scalarOf(), convert$.MODULE$.impl2_Long_Double(), DenseVector$.MODULE$.DV_canMapValues(scala.reflect.ClassTag..MODULE$.Double()))), median$.MODULE$.reduce(scala.reflect.ClassTag..MODULE$.Double(), median$.MODULE$.reduceArray_Double())), scala.Long..MODULE$, convert$.MODULE$.impl2_Double_Long())));
            } else {
               if (optMode instanceof Options.Value) {
                  Options.Value var6 = (Options.Value)optMode;
                  Object n = var6.n();
                  if (n instanceof Long) {
                     long var8 = BoxesRunTime.unboxToLong(n);
                     var4 = this.padLeft2ImplFixed(m, optDim, var8);
                     return var4;
                  }
               }

               if (Options.Wrap$.MODULE$.equals(optMode)) {
                  throw new IllegalArgumentException("Option <Wrap> is not supported for 2D padding.");
               }

               if (Options.Reflect$.MODULE$.equals(optMode)) {
                  throw new IllegalArgumentException("Option <Reflect> is not supported for 2D padding.");
               }

               throw new MatchError(optMode);
            }

            return var4;
         }

         private DenseMatrix padLeft2ImplZero(final DenseMatrix v, final Options.Dimensions2 optDim) {
            return this.padLeft2ImplFixed(v, optDim, ((Semiring)scala.Predef..MODULE$.implicitly(Semiring$.MODULE$.semiringLong())).zero$mcJ$sp());
         }

         private DenseMatrix padLeft2ImplFixed(final DenseMatrix m, final Options.Dimensions2 optDim, final long padValue) {
            boolean cond$macro$1 = optDim.n1() > 0 && optDim.n2() > 0;
            if (!cond$macro$1) {
               throw new IllegalArgumentException("requirement failed: Cannot pad to zero or negative length!: optDim.n1.>(0).&&(optDim.n2.>(0))");
            } else {
               DenseMatrix tempret = DenseMatrix$.MODULE$.zeros$mJc$sp(optDim.n1(), optDim.n2(), scala.reflect.ClassTag..MODULE$.Long(), Zero$.MODULE$.LongZero());
               int index$macro$8 = 1;

               for(int end$macro$9 = min$.MODULE$.apply$mIIIc$sp(optDim.n2(), m.cols(), min$.MODULE$.minImpl2_Int()); index$macro$8 <= end$macro$9; ++index$macro$8) {
                  int index$macro$3 = 1;

                  for(int end$macro$4 = min$.MODULE$.apply$mIIIc$sp(optDim.n1(), m.rows(), min$.MODULE$.minImpl2_Int()); index$macro$3 <= end$macro$4; ++index$macro$3) {
                     ((c, r) -> tempret.update$mcJ$sp(optDim.n1() - r, optDim.n2() - c, m.apply$mcJ$sp(m.rows() - r, m.cols() - c))).apply$mcVII$sp(index$macro$8, index$macro$3);
                  }
               }

               return tempret;
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   public CanPadLeft implDM_OptPadDim_OptPadMode_Float() {
      return new CanPadLeft() {
         public DenseMatrix apply(final DenseMatrix m, final Options.Dimensions2 optDim, final Options.OptPadMode optMode) {
            DenseMatrix var4;
            if (Options.Zero$.MODULE$.equals(optMode)) {
               var4 = this.padLeft2ImplZero(m, optDim);
            } else if (Options.Max$.MODULE$.equals(optMode)) {
               var4 = this.padLeft2ImplFixed(m, optDim, BoxesRunTime.unboxToFloat(max$.MODULE$.apply(m, max$.MODULE$.reduce_Float(HasOps$.MODULE$.canTraverseValues()))));
            } else if (Options.Min$.MODULE$.equals(optMode)) {
               var4 = this.padLeft2ImplFixed(m, optDim, BoxesRunTime.unboxToFloat(min$.MODULE$.apply(m, min$.MODULE$.reduce_Float(HasOps$.MODULE$.canTraverseValues()))));
            } else if (Options.Mean$.MODULE$.equals(optMode)) {
               var4 = this.padLeft2ImplFixed(m, optDim, BoxesRunTime.unboxToFloat(convert$.MODULE$.apply(mean$.MODULE$.apply(convert$.MODULE$.apply(m.toDenseVector$mcF$sp(), .MODULE$, HasOps$.MODULE$.canMapV1DV(DenseVector$.MODULE$.DV_scalarOf(), convert$.MODULE$.impl2_Float_Double(), DenseVector$.MODULE$.DV_canMapValues$mFDc$sp(scala.reflect.ClassTag..MODULE$.Double()))), mean$.MODULE$.reduce_Double(HasOps$.MODULE$.DV_canIterateValues())), scala.Float..MODULE$, convert$.MODULE$.impl2_Double_Float())));
            } else if (Options.Median$.MODULE$.equals(optMode)) {
               var4 = this.padLeft2ImplFixed(m, optDim, BoxesRunTime.unboxToFloat(convert$.MODULE$.apply(median$.MODULE$.apply(convert$.MODULE$.apply(m.toDenseVector$mcF$sp(), .MODULE$, HasOps$.MODULE$.canMapV1DV(DenseVector$.MODULE$.DV_scalarOf(), convert$.MODULE$.impl2_Float_Double(), DenseVector$.MODULE$.DV_canMapValues$mFDc$sp(scala.reflect.ClassTag..MODULE$.Double()))), median$.MODULE$.reduce(scala.reflect.ClassTag..MODULE$.Double(), median$.MODULE$.reduceArray_Double())), scala.Float..MODULE$, convert$.MODULE$.impl2_Double_Float())));
            } else {
               if (optMode instanceof Options.Value) {
                  Options.Value var6 = (Options.Value)optMode;
                  Object n = var6.n();
                  if (n instanceof Float) {
                     float var8 = BoxesRunTime.unboxToFloat(n);
                     var4 = this.padLeft2ImplFixed(m, optDim, var8);
                     return var4;
                  }
               }

               if (Options.Wrap$.MODULE$.equals(optMode)) {
                  throw new IllegalArgumentException("Option <Wrap> is not supported for 2D padding.");
               }

               if (Options.Reflect$.MODULE$.equals(optMode)) {
                  throw new IllegalArgumentException("Option <Reflect> is not supported for 2D padding.");
               }

               throw new MatchError(optMode);
            }

            return var4;
         }

         private DenseMatrix padLeft2ImplZero(final DenseMatrix v, final Options.Dimensions2 optDim) {
            return this.padLeft2ImplFixed(v, optDim, ((Semiring)scala.Predef..MODULE$.implicitly(Semiring$.MODULE$.semiringFloat())).zero$mcF$sp());
         }

         private DenseMatrix padLeft2ImplFixed(final DenseMatrix m, final Options.Dimensions2 optDim, final float padValue) {
            boolean cond$macro$1 = optDim.n1() > 0 && optDim.n2() > 0;
            if (!cond$macro$1) {
               throw new IllegalArgumentException("requirement failed: Cannot pad to zero or negative length!: optDim.n1.>(0).&&(optDim.n2.>(0))");
            } else {
               DenseMatrix tempret = DenseMatrix$.MODULE$.zeros$mFc$sp(optDim.n1(), optDim.n2(), scala.reflect.ClassTag..MODULE$.Float(), Zero$.MODULE$.FloatZero());
               int index$macro$8 = 1;

               for(int end$macro$9 = min$.MODULE$.apply$mIIIc$sp(optDim.n2(), m.cols(), min$.MODULE$.minImpl2_Int()); index$macro$8 <= end$macro$9; ++index$macro$8) {
                  int index$macro$3 = 1;

                  for(int end$macro$4 = min$.MODULE$.apply$mIIIc$sp(optDim.n1(), m.rows(), min$.MODULE$.minImpl2_Int()); index$macro$3 <= end$macro$4; ++index$macro$3) {
                     ((c, r) -> tempret.update$mcF$sp(optDim.n1() - r, optDim.n2() - c, m.apply$mcF$sp(m.rows() - r, m.cols() - c))).apply$mcVII$sp(index$macro$8, index$macro$3);
                  }
               }

               return tempret;
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   public CanPadLeft implDM_OptPadDim_OptPadMode_Double() {
      return new CanPadLeft() {
         public DenseMatrix apply(final DenseMatrix m, final Options.Dimensions2 optDim, final Options.OptPadMode optMode) {
            DenseMatrix var4;
            if (Options.Zero$.MODULE$.equals(optMode)) {
               var4 = this.padLeft2ImplZero(m, optDim);
            } else if (Options.Max$.MODULE$.equals(optMode)) {
               var4 = this.padLeft2ImplFixed(m, optDim, BoxesRunTime.unboxToDouble(max$.MODULE$.apply(m, max$.MODULE$.reduce_Double(HasOps$.MODULE$.canTraverseValues()))));
            } else if (Options.Min$.MODULE$.equals(optMode)) {
               var4 = this.padLeft2ImplFixed(m, optDim, BoxesRunTime.unboxToDouble(min$.MODULE$.apply(m, min$.MODULE$.reduce_Double(HasOps$.MODULE$.canTraverseValues()))));
            } else if (Options.Mean$.MODULE$.equals(optMode)) {
               var4 = this.padLeft2ImplFixed(m, optDim, BoxesRunTime.unboxToDouble(convert$.MODULE$.apply(mean$.MODULE$.apply(convert$.MODULE$.apply(m.toDenseVector$mcD$sp(), .MODULE$, HasOps$.MODULE$.canMapV1DV(DenseVector$.MODULE$.DV_scalarOf(), convert$.MODULE$.impl2_Double_Double(), DenseVector$.MODULE$.DV_canMapValues$mDDc$sp(scala.reflect.ClassTag..MODULE$.Double()))), mean$.MODULE$.reduce_Double(HasOps$.MODULE$.DV_canIterateValues())), .MODULE$, convert$.MODULE$.impl2_Double_Double())));
            } else if (Options.Median$.MODULE$.equals(optMode)) {
               var4 = this.padLeft2ImplFixed(m, optDim, BoxesRunTime.unboxToDouble(convert$.MODULE$.apply(median$.MODULE$.apply(convert$.MODULE$.apply(m.toDenseVector$mcD$sp(), .MODULE$, HasOps$.MODULE$.canMapV1DV(DenseVector$.MODULE$.DV_scalarOf(), convert$.MODULE$.impl2_Double_Double(), DenseVector$.MODULE$.DV_canMapValues$mDDc$sp(scala.reflect.ClassTag..MODULE$.Double()))), median$.MODULE$.reduce(scala.reflect.ClassTag..MODULE$.Double(), median$.MODULE$.reduceArray_Double())), .MODULE$, convert$.MODULE$.impl2_Double_Double())));
            } else {
               if (optMode instanceof Options.Value) {
                  Options.Value var6 = (Options.Value)optMode;
                  Object n = var6.n();
                  if (n instanceof Double) {
                     double var8 = BoxesRunTime.unboxToDouble(n);
                     var4 = this.padLeft2ImplFixed(m, optDim, var8);
                     return var4;
                  }
               }

               if (Options.Wrap$.MODULE$.equals(optMode)) {
                  throw new IllegalArgumentException("Option <Wrap> is not supported for 2D padding.");
               }

               if (Options.Reflect$.MODULE$.equals(optMode)) {
                  throw new IllegalArgumentException("Option <Reflect> is not supported for 2D padding.");
               }

               throw new MatchError(optMode);
            }

            return var4;
         }

         private DenseMatrix padLeft2ImplZero(final DenseMatrix v, final Options.Dimensions2 optDim) {
            return this.padLeft2ImplFixed(v, optDim, ((Semiring)scala.Predef..MODULE$.implicitly(Semiring$.MODULE$.semiringD())).zero$mcD$sp());
         }

         private DenseMatrix padLeft2ImplFixed(final DenseMatrix m, final Options.Dimensions2 optDim, final double padValue) {
            boolean cond$macro$1 = optDim.n1() > 0 && optDim.n2() > 0;
            if (!cond$macro$1) {
               throw new IllegalArgumentException("requirement failed: Cannot pad to zero or negative length!: optDim.n1.>(0).&&(optDim.n2.>(0))");
            } else {
               DenseMatrix tempret = DenseMatrix$.MODULE$.zeros$mDc$sp(optDim.n1(), optDim.n2(), scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());
               int index$macro$8 = 1;

               for(int end$macro$9 = min$.MODULE$.apply$mIIIc$sp(optDim.n2(), m.cols(), min$.MODULE$.minImpl2_Int()); index$macro$8 <= end$macro$9; ++index$macro$8) {
                  int index$macro$3 = 1;

                  for(int end$macro$4 = min$.MODULE$.apply$mIIIc$sp(optDim.n1(), m.rows(), min$.MODULE$.minImpl2_Int()); index$macro$3 <= end$macro$4; ++index$macro$3) {
                     ((c, r) -> tempret.update$mcD$sp(optDim.n1() - r, optDim.n2() - c, m.apply$mcD$sp(m.rows() - r, m.cols() - c))).apply$mcVII$sp(index$macro$8, index$macro$3);
                  }
               }

               return tempret;
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   private CanPadLeft$() {
   }
}
