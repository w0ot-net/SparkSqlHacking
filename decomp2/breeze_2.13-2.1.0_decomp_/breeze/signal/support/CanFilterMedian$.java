package breeze.signal.support;

import breeze.linalg.DenseVector;
import breeze.linalg.DenseVector$;
import breeze.linalg.operators.HasOps$;
import breeze.signal.OptOverhang;
import breeze.stats.median$;
import breeze.util.quickSelectImpl$;
import java.lang.invoke.SerializedLambda;
import scala.MatchError;
import scala.Predef.;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ObjectRef;
import scala.runtime.java8.JFunction1;

public final class CanFilterMedian$ {
   public static final CanFilterMedian$ MODULE$ = new CanFilterMedian$();

   public CanFilterMedian dvFilterMedianT_Int() {
      return new CanFilterMedian() {
         public DenseVector apply(final DenseVector data, final int windowLength, final OptOverhang overhang) {
            .MODULE$.require(BoxesRunTime.unboxToBoolean(breeze.numerics.package.isOdd$.MODULE$.apply(BoxesRunTime.boxToInteger(windowLength), breeze.numerics.package.isOdd$.MODULE$.isOddImpl_Int())), () -> "median filter can only take odd windowLength values, since even values will cause a half-frame time shift");
            .MODULE$.require(data.length() >= 3, () -> "data must be longer than 3");
            .MODULE$.require(windowLength >= 1, () -> "window length must be longer than 1");
            DenseVector var10000;
            if (windowLength == 1) {
               var10000 = data.copy$mcI$sp();
            } else {
               ObjectRef tempret = ObjectRef.create(new int[data.length()]);
               int halfWindow = (windowLength - 1) / 2;
               if (OptOverhang.PreserveLength$.MODULE$.equals(overhang)) {
                  scala.runtime.RichInt..MODULE$.until$extension(.MODULE$.intWrapper(0), halfWindow).foreach$mVc$sp((JFunction1.mcVI.sp)(indexFromBeginning) -> ((int[])tempret.elem)[indexFromBeginning] = BoxesRunTime.unboxToInt(median$.MODULE$.apply(data.apply(scala.runtime.RichInt..MODULE$.to$extension(.MODULE$.intWrapper(0), indexFromBeginning * 2), HasOps$.MODULE$.canSlice_DV_Range_eq_DV()), median$.MODULE$.reduce(scala.reflect.ClassTag..MODULE$.Int(), median$.MODULE$.reduceArray_Int()))));
                  scala.runtime.RichInt..MODULE$.until$extension(.MODULE$.intWrapper(0), halfWindow).foreach$mVc$sp((JFunction1.mcVI.sp)(indexToEnd) -> ((int[])tempret.elem)[data.length() - indexToEnd - 1] = BoxesRunTime.unboxToInt(median$.MODULE$.apply(data.apply(scala.runtime.RichInt..MODULE$.until$extension(.MODULE$.intWrapper(data.length() - 2 * indexToEnd - 1), data.length()), HasOps$.MODULE$.canSlice_DV_Range_eq_DV()), median$.MODULE$.reduce(scala.reflect.ClassTag..MODULE$.Int(), median$.MODULE$.reduceArray_Int()))));
                  BoxedUnit var5 = BoxedUnit.UNIT;
               } else {
                  if (!OptOverhang.None$.MODULE$.equals(overhang)) {
                     if (overhang != null) {
                        throw new IllegalArgumentException((new StringBuilder(24)).append("Option ").append(overhang).append(" is invalid here.").toString());
                     }

                     throw new MatchError(overhang);
                  }

                  BoxedUnit var16 = BoxedUnit.UNIT;
               }

               int[] tempDataExtract = ((DenseVector)data.apply(scala.runtime.RichInt..MODULE$.to$extension(.MODULE$.intWrapper(0), halfWindow + halfWindow), HasOps$.MODULE$.canSlice_DV_Range_eq_DV())).toArray$mcI$sp(scala.reflect.ClassTag..MODULE$.Int());
               int currentMedian = BoxesRunTime.unboxToInt(quickSelectImpl$.MODULE$.apply(tempDataExtract, BoxesRunTime.boxToInteger(halfWindow), quickSelectImpl$.MODULE$.impl_Int()));
               ((int[])tempret.elem)[halfWindow] = currentMedian;

               for(int index = halfWindow + 1; index < data.length() - halfWindow; ++index) {
                  int nowObsoleteWindowValue = data.apply$mcI$sp(index - halfWindow - 1);
                  int newWindowValue = data.apply$mcI$sp(index + halfWindow);
                  if (nowObsoleteWindowValue != newWindowValue) {
                     this.findAndReplaceInstanceInPlace(tempDataExtract, nowObsoleteWindowValue, newWindowValue, halfWindow);
                     if ((nowObsoleteWindowValue >= currentMedian || newWindowValue >= currentMedian) && (nowObsoleteWindowValue <= currentMedian || newWindowValue <= currentMedian)) {
                        currentMedian = BoxesRunTime.unboxToInt(quickSelectImpl$.MODULE$.apply(tempDataExtract, BoxesRunTime.boxToInteger(halfWindow), quickSelectImpl$.MODULE$.impl_Int()));
                     }
                  }

                  ((int[])tempret.elem)[index] = currentMedian;
               }

               DenseVector var4;
               if (OptOverhang.PreserveLength$.MODULE$.equals(overhang)) {
                  var4 = DenseVector$.MODULE$.apply$mIc$sp((int[])tempret.elem);
               } else {
                  if (!OptOverhang.None$.MODULE$.equals(overhang)) {
                     throw new MatchError(overhang);
                  }

                  var4 = DenseVector$.MODULE$.apply$mIc$sp((int[])scala.collection.ArrayOps..MODULE$.slice$extension(.MODULE$.intArrayOps((int[])tempret.elem), halfWindow, data.length() - halfWindow));
               }

               var10000 = var4;
            }

            return var10000;
         }

         private void findAndReplaceInstanceInPlace(final int[] arr, final int fromValue, final int toValue, final int pivotPoint) {
            int pivotValue = arr[pivotPoint];
            boolean found = false;
            if (fromValue == pivotValue) {
               arr[pivotPoint] = toValue;
               found = true;
            } else if (fromValue < pivotValue) {
               int count = pivotPoint - 1;

               while(count >= 0) {
                  if (arr[count] == fromValue) {
                     arr[count] = toValue;
                     count = Integer.MIN_VALUE;
                     found = true;
                  } else {
                     --count;
                  }
               }
            } else {
               int count = pivotPoint + 1;

               while(count < arr.length) {
                  if (arr[count] == fromValue) {
                     arr[count] = toValue;
                     count = Integer.MAX_VALUE;
                     found = true;
                  } else {
                     ++count;
                  }
               }
            }

            .MODULE$.require(found, () -> "The fromValue was not found within the given array, something is wrong!");
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   public CanFilterMedian dvFilterMedianT_Long() {
      return new CanFilterMedian() {
         public DenseVector apply(final DenseVector data, final int windowLength, final OptOverhang overhang) {
            .MODULE$.require(BoxesRunTime.unboxToBoolean(breeze.numerics.package.isOdd$.MODULE$.apply(BoxesRunTime.boxToInteger(windowLength), breeze.numerics.package.isOdd$.MODULE$.isOddImpl_Int())), () -> "median filter can only take odd windowLength values, since even values will cause a half-frame time shift");
            .MODULE$.require(data.length() >= 3, () -> "data must be longer than 3");
            .MODULE$.require(windowLength >= 1, () -> "window length must be longer than 1");
            DenseVector var10000;
            if (windowLength == 1) {
               var10000 = data.copy$mcJ$sp();
            } else {
               ObjectRef tempret = ObjectRef.create(new long[data.length()]);
               int halfWindow = (windowLength - 1) / 2;
               if (OptOverhang.PreserveLength$.MODULE$.equals(overhang)) {
                  scala.runtime.RichInt..MODULE$.until$extension(.MODULE$.intWrapper(0), halfWindow).foreach$mVc$sp((JFunction1.mcVI.sp)(indexFromBeginning) -> ((long[])tempret.elem)[indexFromBeginning] = BoxesRunTime.unboxToLong(median$.MODULE$.apply(data.apply(scala.runtime.RichInt..MODULE$.to$extension(.MODULE$.intWrapper(0), indexFromBeginning * 2), HasOps$.MODULE$.canSlice_DV_Range_eq_DV()), median$.MODULE$.reduce(scala.reflect.ClassTag..MODULE$.Long(), median$.MODULE$.reduceArray_Long()))));
                  scala.runtime.RichInt..MODULE$.until$extension(.MODULE$.intWrapper(0), halfWindow).foreach$mVc$sp((JFunction1.mcVI.sp)(indexToEnd) -> ((long[])tempret.elem)[data.length() - indexToEnd - 1] = BoxesRunTime.unboxToLong(median$.MODULE$.apply(data.apply(scala.runtime.RichInt..MODULE$.until$extension(.MODULE$.intWrapper(data.length() - 2 * indexToEnd - 1), data.length()), HasOps$.MODULE$.canSlice_DV_Range_eq_DV()), median$.MODULE$.reduce(scala.reflect.ClassTag..MODULE$.Long(), median$.MODULE$.reduceArray_Long()))));
                  BoxedUnit var5 = BoxedUnit.UNIT;
               } else {
                  if (!OptOverhang.None$.MODULE$.equals(overhang)) {
                     if (overhang != null) {
                        throw new IllegalArgumentException((new StringBuilder(24)).append("Option ").append(overhang).append(" is invalid here.").toString());
                     }

                     throw new MatchError(overhang);
                  }

                  BoxedUnit var19 = BoxedUnit.UNIT;
               }

               long[] tempDataExtract = ((DenseVector)data.apply(scala.runtime.RichInt..MODULE$.to$extension(.MODULE$.intWrapper(0), halfWindow + halfWindow), HasOps$.MODULE$.canSlice_DV_Range_eq_DV())).toArray$mcJ$sp(scala.reflect.ClassTag..MODULE$.Long());
               long currentMedian = BoxesRunTime.unboxToLong(quickSelectImpl$.MODULE$.apply(tempDataExtract, BoxesRunTime.boxToInteger(halfWindow), quickSelectImpl$.MODULE$.impl_Long()));
               ((long[])tempret.elem)[halfWindow] = currentMedian;

               for(int index = halfWindow + 1; index < data.length() - halfWindow; ++index) {
                  long nowObsoleteWindowValue = data.apply$mcJ$sp(index - halfWindow - 1);
                  long newWindowValue = data.apply$mcJ$sp(index + halfWindow);
                  if (nowObsoleteWindowValue != newWindowValue) {
                     this.findAndReplaceInstanceInPlace(tempDataExtract, nowObsoleteWindowValue, newWindowValue, halfWindow);
                     if ((nowObsoleteWindowValue >= currentMedian || newWindowValue >= currentMedian) && (nowObsoleteWindowValue <= currentMedian || newWindowValue <= currentMedian)) {
                        currentMedian = BoxesRunTime.unboxToLong(quickSelectImpl$.MODULE$.apply(tempDataExtract, BoxesRunTime.boxToInteger(halfWindow), quickSelectImpl$.MODULE$.impl_Long()));
                     }
                  }

                  ((long[])tempret.elem)[index] = currentMedian;
               }

               DenseVector var4;
               if (OptOverhang.PreserveLength$.MODULE$.equals(overhang)) {
                  var4 = DenseVector$.MODULE$.apply$mJc$sp((long[])tempret.elem);
               } else {
                  if (!OptOverhang.None$.MODULE$.equals(overhang)) {
                     throw new MatchError(overhang);
                  }

                  var4 = DenseVector$.MODULE$.apply$mJc$sp((long[])scala.collection.ArrayOps..MODULE$.slice$extension(.MODULE$.longArrayOps((long[])tempret.elem), halfWindow, data.length() - halfWindow));
               }

               var10000 = var4;
            }

            return var10000;
         }

         private void findAndReplaceInstanceInPlace(final long[] arr, final long fromValue, final long toValue, final int pivotPoint) {
            long pivotValue = arr[pivotPoint];
            boolean found = false;
            if (fromValue == pivotValue) {
               arr[pivotPoint] = toValue;
               found = true;
            } else if (fromValue < pivotValue) {
               int count = pivotPoint - 1;

               while(count >= 0) {
                  if (arr[count] == fromValue) {
                     arr[count] = toValue;
                     count = Integer.MIN_VALUE;
                     found = true;
                  } else {
                     --count;
                  }
               }
            } else {
               int count = pivotPoint + 1;

               while(count < arr.length) {
                  if (arr[count] == fromValue) {
                     arr[count] = toValue;
                     count = Integer.MAX_VALUE;
                     found = true;
                  } else {
                     ++count;
                  }
               }
            }

            .MODULE$.require(found, () -> "The fromValue was not found within the given array, something is wrong!");
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   public CanFilterMedian dvFilterMedianT_Double() {
      return new CanFilterMedian() {
         public DenseVector apply(final DenseVector data, final int windowLength, final OptOverhang overhang) {
            .MODULE$.require(BoxesRunTime.unboxToBoolean(breeze.numerics.package.isOdd$.MODULE$.apply(BoxesRunTime.boxToInteger(windowLength), breeze.numerics.package.isOdd$.MODULE$.isOddImpl_Int())), () -> "median filter can only take odd windowLength values, since even values will cause a half-frame time shift");
            .MODULE$.require(data.length() >= 3, () -> "data must be longer than 3");
            .MODULE$.require(windowLength >= 1, () -> "window length must be longer than 1");
            DenseVector var10000;
            if (windowLength == 1) {
               var10000 = data.copy$mcD$sp();
            } else {
               ObjectRef tempret = ObjectRef.create(new double[data.length()]);
               int halfWindow = (windowLength - 1) / 2;
               if (OptOverhang.PreserveLength$.MODULE$.equals(overhang)) {
                  scala.runtime.RichInt..MODULE$.until$extension(.MODULE$.intWrapper(0), halfWindow).foreach$mVc$sp((JFunction1.mcVI.sp)(indexFromBeginning) -> ((double[])tempret.elem)[indexFromBeginning] = BoxesRunTime.unboxToDouble(median$.MODULE$.apply(data.apply(scala.runtime.RichInt..MODULE$.to$extension(.MODULE$.intWrapper(0), indexFromBeginning * 2), HasOps$.MODULE$.canSlice_DV_Range_eq_DV()), median$.MODULE$.reduce(scala.reflect.ClassTag..MODULE$.Double(), median$.MODULE$.reduceArray_Double()))));
                  scala.runtime.RichInt..MODULE$.until$extension(.MODULE$.intWrapper(0), halfWindow).foreach$mVc$sp((JFunction1.mcVI.sp)(indexToEnd) -> ((double[])tempret.elem)[data.length() - indexToEnd - 1] = BoxesRunTime.unboxToDouble(median$.MODULE$.apply(data.apply(scala.runtime.RichInt..MODULE$.until$extension(.MODULE$.intWrapper(data.length() - 2 * indexToEnd - 1), data.length()), HasOps$.MODULE$.canSlice_DV_Range_eq_DV()), median$.MODULE$.reduce(scala.reflect.ClassTag..MODULE$.Double(), median$.MODULE$.reduceArray_Double()))));
                  BoxedUnit var5 = BoxedUnit.UNIT;
               } else {
                  if (!OptOverhang.None$.MODULE$.equals(overhang)) {
                     if (overhang != null) {
                        throw new IllegalArgumentException((new StringBuilder(24)).append("Option ").append(overhang).append(" is invalid here.").toString());
                     }

                     throw new MatchError(overhang);
                  }

                  BoxedUnit var19 = BoxedUnit.UNIT;
               }

               double[] tempDataExtract = ((DenseVector)data.apply(scala.runtime.RichInt..MODULE$.to$extension(.MODULE$.intWrapper(0), halfWindow + halfWindow), HasOps$.MODULE$.canSlice_DV_Range_eq_DV())).toArray$mcD$sp(scala.reflect.ClassTag..MODULE$.Double());
               double currentMedian = BoxesRunTime.unboxToDouble(quickSelectImpl$.MODULE$.apply(tempDataExtract, BoxesRunTime.boxToInteger(halfWindow), quickSelectImpl$.MODULE$.impl_Double()));
               ((double[])tempret.elem)[halfWindow] = currentMedian;

               for(int index = halfWindow + 1; index < data.length() - halfWindow; ++index) {
                  double nowObsoleteWindowValue = data.apply$mcD$sp(index - halfWindow - 1);
                  double newWindowValue = data.apply$mcD$sp(index + halfWindow);
                  if (nowObsoleteWindowValue != newWindowValue) {
                     this.findAndReplaceInstanceInPlace(tempDataExtract, nowObsoleteWindowValue, newWindowValue, halfWindow);
                     if ((nowObsoleteWindowValue >= currentMedian || newWindowValue >= currentMedian) && (nowObsoleteWindowValue <= currentMedian || newWindowValue <= currentMedian)) {
                        currentMedian = BoxesRunTime.unboxToDouble(quickSelectImpl$.MODULE$.apply(tempDataExtract, BoxesRunTime.boxToInteger(halfWindow), quickSelectImpl$.MODULE$.impl_Double()));
                     }
                  }

                  ((double[])tempret.elem)[index] = currentMedian;
               }

               DenseVector var4;
               if (OptOverhang.PreserveLength$.MODULE$.equals(overhang)) {
                  var4 = DenseVector$.MODULE$.apply$mDc$sp((double[])tempret.elem);
               } else {
                  if (!OptOverhang.None$.MODULE$.equals(overhang)) {
                     throw new MatchError(overhang);
                  }

                  var4 = DenseVector$.MODULE$.apply$mDc$sp((double[])scala.collection.ArrayOps..MODULE$.slice$extension(.MODULE$.doubleArrayOps((double[])tempret.elem), halfWindow, data.length() - halfWindow));
               }

               var10000 = var4;
            }

            return var10000;
         }

         private void findAndReplaceInstanceInPlace(final double[] arr, final double fromValue, final double toValue, final int pivotPoint) {
            double pivotValue = arr[pivotPoint];
            boolean found = false;
            if (fromValue == pivotValue) {
               arr[pivotPoint] = toValue;
               found = true;
            } else if (fromValue < pivotValue) {
               int count = pivotPoint - 1;

               while(count >= 0) {
                  if (arr[count] == fromValue) {
                     arr[count] = toValue;
                     count = Integer.MIN_VALUE;
                     found = true;
                  } else {
                     --count;
                  }
               }
            } else {
               int count = pivotPoint + 1;

               while(count < arr.length) {
                  if (arr[count] == fromValue) {
                     arr[count] = toValue;
                     count = Integer.MAX_VALUE;
                     found = true;
                  } else {
                     ++count;
                  }
               }
            }

            .MODULE$.require(found, () -> "The fromValue was not found within the given array, something is wrong!");
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   public CanFilterMedian dvFilterMedianT_Float() {
      return new CanFilterMedian() {
         public DenseVector apply(final DenseVector data, final int windowLength, final OptOverhang overhang) {
            .MODULE$.require(BoxesRunTime.unboxToBoolean(breeze.numerics.package.isOdd$.MODULE$.apply(BoxesRunTime.boxToInteger(windowLength), breeze.numerics.package.isOdd$.MODULE$.isOddImpl_Int())), () -> "median filter can only take odd windowLength values, since even values will cause a half-frame time shift");
            .MODULE$.require(data.length() >= 3, () -> "data must be longer than 3");
            .MODULE$.require(windowLength >= 1, () -> "window length must be longer than 1");
            DenseVector var10000;
            if (windowLength == 1) {
               var10000 = data.copy$mcF$sp();
            } else {
               ObjectRef tempret = ObjectRef.create(new float[data.length()]);
               int halfWindow = (windowLength - 1) / 2;
               if (OptOverhang.PreserveLength$.MODULE$.equals(overhang)) {
                  scala.runtime.RichInt..MODULE$.until$extension(.MODULE$.intWrapper(0), halfWindow).foreach$mVc$sp((JFunction1.mcVI.sp)(indexFromBeginning) -> ((float[])tempret.elem)[indexFromBeginning] = BoxesRunTime.unboxToFloat(median$.MODULE$.apply(data.apply(scala.runtime.RichInt..MODULE$.to$extension(.MODULE$.intWrapper(0), indexFromBeginning * 2), HasOps$.MODULE$.canSlice_DV_Range_eq_DV()), median$.MODULE$.reduce(scala.reflect.ClassTag..MODULE$.Float(), median$.MODULE$.reduceArray_Float()))));
                  scala.runtime.RichInt..MODULE$.until$extension(.MODULE$.intWrapper(0), halfWindow).foreach$mVc$sp((JFunction1.mcVI.sp)(indexToEnd) -> ((float[])tempret.elem)[data.length() - indexToEnd - 1] = BoxesRunTime.unboxToFloat(median$.MODULE$.apply(data.apply(scala.runtime.RichInt..MODULE$.until$extension(.MODULE$.intWrapper(data.length() - 2 * indexToEnd - 1), data.length()), HasOps$.MODULE$.canSlice_DV_Range_eq_DV()), median$.MODULE$.reduce(scala.reflect.ClassTag..MODULE$.Float(), median$.MODULE$.reduceArray_Float()))));
                  BoxedUnit var5 = BoxedUnit.UNIT;
               } else {
                  if (!OptOverhang.None$.MODULE$.equals(overhang)) {
                     if (overhang != null) {
                        throw new IllegalArgumentException((new StringBuilder(24)).append("Option ").append(overhang).append(" is invalid here.").toString());
                     }

                     throw new MatchError(overhang);
                  }

                  BoxedUnit var16 = BoxedUnit.UNIT;
               }

               float[] tempDataExtract = ((DenseVector)data.apply(scala.runtime.RichInt..MODULE$.to$extension(.MODULE$.intWrapper(0), halfWindow + halfWindow), HasOps$.MODULE$.canSlice_DV_Range_eq_DV())).toArray$mcF$sp(scala.reflect.ClassTag..MODULE$.Float());
               float currentMedian = BoxesRunTime.unboxToFloat(quickSelectImpl$.MODULE$.apply(tempDataExtract, BoxesRunTime.boxToInteger(halfWindow), quickSelectImpl$.MODULE$.impl_Float()));
               ((float[])tempret.elem)[halfWindow] = currentMedian;

               for(int index = halfWindow + 1; index < data.length() - halfWindow; ++index) {
                  float nowObsoleteWindowValue = data.apply$mcF$sp(index - halfWindow - 1);
                  float newWindowValue = data.apply$mcF$sp(index + halfWindow);
                  if (nowObsoleteWindowValue != newWindowValue) {
                     this.findAndReplaceInstanceInPlace(tempDataExtract, nowObsoleteWindowValue, newWindowValue, halfWindow);
                     if ((nowObsoleteWindowValue >= currentMedian || newWindowValue >= currentMedian) && (nowObsoleteWindowValue <= currentMedian || newWindowValue <= currentMedian)) {
                        currentMedian = BoxesRunTime.unboxToFloat(quickSelectImpl$.MODULE$.apply(tempDataExtract, BoxesRunTime.boxToInteger(halfWindow), quickSelectImpl$.MODULE$.impl_Float()));
                     }
                  }

                  ((float[])tempret.elem)[index] = currentMedian;
               }

               DenseVector var4;
               if (OptOverhang.PreserveLength$.MODULE$.equals(overhang)) {
                  var4 = DenseVector$.MODULE$.apply$mFc$sp((float[])tempret.elem);
               } else {
                  if (!OptOverhang.None$.MODULE$.equals(overhang)) {
                     throw new MatchError(overhang);
                  }

                  var4 = DenseVector$.MODULE$.apply$mFc$sp((float[])scala.collection.ArrayOps..MODULE$.slice$extension(.MODULE$.floatArrayOps((float[])tempret.elem), halfWindow, data.length() - halfWindow));
               }

               var10000 = var4;
            }

            return var10000;
         }

         private void findAndReplaceInstanceInPlace(final float[] arr, final float fromValue, final float toValue, final int pivotPoint) {
            float pivotValue = arr[pivotPoint];
            boolean found = false;
            if (fromValue == pivotValue) {
               arr[pivotPoint] = toValue;
               found = true;
            } else if (fromValue < pivotValue) {
               int count = pivotPoint - 1;

               while(count >= 0) {
                  if (arr[count] == fromValue) {
                     arr[count] = toValue;
                     count = Integer.MIN_VALUE;
                     found = true;
                  } else {
                     --count;
                  }
               }
            } else {
               int count = pivotPoint + 1;

               while(count < arr.length) {
                  if (arr[count] == fromValue) {
                     arr[count] = toValue;
                     count = Integer.MAX_VALUE;
                     found = true;
                  } else {
                     ++count;
                  }
               }
            }

            .MODULE$.require(found, () -> "The fromValue was not found within the given array, something is wrong!");
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   private CanFilterMedian$() {
   }
}
