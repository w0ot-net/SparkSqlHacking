package breeze.util;

import breeze.linalg.$times$;
import breeze.linalg.BroadcastedRows;
import breeze.linalg.BroadcastedRows$;
import breeze.linalg.Broadcaster$;
import breeze.linalg.DenseMatrix;
import breeze.linalg.DenseMatrix$mcD$sp;
import breeze.linalg.DenseMatrix$mcF$sp;
import breeze.linalg.DenseMatrix$mcI$sp;
import breeze.linalg.DenseMatrix$mcJ$sp;
import breeze.linalg.DenseVector;
import breeze.linalg.DenseVector$mcD$sp;
import breeze.linalg.DenseVector$mcF$sp;
import breeze.linalg.DenseVector$mcI$sp;
import breeze.linalg.DenseVector$mcJ$sp;
import breeze.linalg.operators.HasOps$;
import breeze.math.Complex;
import java.lang.invoke.SerializedLambda;
import scala.collection.IterableOnceOps;
import scala.reflect.ClassTag;
import scala.reflect.ClassTag.;

public final class JavaArrayOps$ {
   public static final JavaArrayOps$ MODULE$ = new JavaArrayOps$();

   public Complex[] dvCToArray(final DenseVector data) {
      return (Complex[])data.toArray(.MODULE$.apply(Complex.class));
   }

   public double[] dvDToArray(final DenseVector data) {
      return data.toArray$mcD$sp(.MODULE$.Double());
   }

   public float[] dvFToArray(final DenseVector data) {
      return data.toArray$mcF$sp(.MODULE$.Float());
   }

   public int[] dvIToArray(final DenseVector data) {
      return data.toArray$mcI$sp(.MODULE$.Int());
   }

   public long[] dvLToArray(final DenseVector data) {
      return data.toArray$mcJ$sp(.MODULE$.Long());
   }

   public Complex[][] dmCToArray2(final DenseMatrix data) {
      return (Complex[][])this.dmToArray2(data);
   }

   public double[][] dmDToArray2(final DenseMatrix data) {
      return this.dmToArray2$mDc$sp(data);
   }

   public float[][] dmFToArray2(final DenseMatrix data) {
      return this.dmToArray2$mFc$sp(data);
   }

   public int[][] dmIToArray2(final DenseMatrix data) {
      return this.dmToArray2$mIc$sp(data);
   }

   public long[][] dmLToArray2(final DenseMatrix data) {
      return this.dmToArray2$mJc$sp(data);
   }

   public DenseVector arrayCToDv(final Complex[] array) {
      return this.arrayToDv(array, .MODULE$.apply(Complex.class));
   }

   public DenseVector arrayDToDv(final double[] array) {
      return this.arrayToDv$mDc$sp(array, .MODULE$.Double());
   }

   public DenseVector arrayFToDv(final float[] array) {
      return this.arrayToDv$mFc$sp(array, .MODULE$.Float());
   }

   public DenseVector arrayIToDv(final int[] array) {
      return this.arrayToDv$mIc$sp(array, .MODULE$.Int());
   }

   public DenseVector arrayLToDv(final long[] array) {
      return this.arrayToDv$mJc$sp(array, .MODULE$.Long());
   }

   public DenseMatrix array2CToDm(final Complex[][] array) {
      return this.array2ToDm(array, .MODULE$.apply(Complex.class));
   }

   public DenseMatrix array2DToDm(final double[][] array) {
      return this.array2ToDm$mDc$sp(array, .MODULE$.Double());
   }

   public DenseMatrix array2FToDm(final float[][] array) {
      return this.array2ToDm$mFc$sp(array, .MODULE$.Float());
   }

   public DenseMatrix array2IToDm(final int[][] array) {
      return this.array2ToDm$mIc$sp(array, .MODULE$.Int());
   }

   public DenseMatrix array2LToDm(final long[][] array) {
      return this.array2ToDm$mJc$sp(array, .MODULE$.Long());
   }

   public Object dvToArray(final DenseVector dv) {
      return dv.copy().data();
   }

   public Object[] dmToArray2(final DenseMatrix dm) {
      ClassTag ct = ReflectionUtil$.MODULE$.elemClassTagFromArray(dm.data());
      return ((IterableOnceOps)BroadcastedRows$.MODULE$.BroadcastRowsDMToIndexedSeq((BroadcastedRows)dm.apply($times$.MODULE$, scala.package..MODULE$.$colon$colon(), Broadcaster$.MODULE$.canBroadcastRows(HasOps$.MODULE$.handholdCanMapCols_DM()))).toIndexedSeq().map((x$1) -> ((DenseVector)x$1.t(HasOps$.MODULE$.canUntranspose())).toArray(ct))).toArray(.MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(ct.runtimeClass())));
   }

   public DenseVector arrayToDv(final Object array, final ClassTag evidence$1) {
      return new DenseVector(array);
   }

   public DenseMatrix array2ToDm(final Object[] values, final ClassTag evidence$2) {
      int tempRows = values.length;
      int tempCols = scala.runtime.ScalaRunTime..MODULE$.array_length(values[0]);
      Object tempret = evidence$2.newArray(tempRows * tempCols);
      int rowIndex = 0;

      int tempretIndex;
      for(tempretIndex = 0; rowIndex < tempRows; ++rowIndex) {
         scala.Predef..MODULE$.require(scala.runtime.ScalaRunTime..MODULE$.array_length(values[rowIndex]) == tempCols, () -> "Input Array[Array[V]] is ragged!");
      }

      for(int colIndex = 0; colIndex < tempCols; ++colIndex) {
         for(int var9 = 0; var9 < tempRows; ++var9) {
            scala.runtime.ScalaRunTime..MODULE$.array_update(tempret, tempretIndex, scala.runtime.ScalaRunTime..MODULE$.array_apply(values[var9], colIndex));
            ++tempretIndex;
         }
      }

      return new DenseMatrix(tempRows, tempCols, tempret);
   }

   public double[] dvToArray$mDc$sp(final DenseVector dv) {
      return dv.copy$mcD$sp().data$mcD$sp();
   }

   public float[] dvToArray$mFc$sp(final DenseVector dv) {
      return dv.copy$mcF$sp().data$mcF$sp();
   }

   public int[] dvToArray$mIc$sp(final DenseVector dv) {
      return dv.copy$mcI$sp().data$mcI$sp();
   }

   public long[] dvToArray$mJc$sp(final DenseVector dv) {
      return dv.copy$mcJ$sp().data$mcJ$sp();
   }

   public double[][] dmToArray2$mDc$sp(final DenseMatrix dm) {
      ClassTag ct = ReflectionUtil$.MODULE$.elemClassTagFromArray(dm.data$mcD$sp());
      return (double[][])((IterableOnceOps)BroadcastedRows$.MODULE$.BroadcastRowsDMToIndexedSeq((BroadcastedRows)dm.apply($times$.MODULE$, scala.package..MODULE$.$colon$colon(), Broadcaster$.MODULE$.canBroadcastRows(HasOps$.MODULE$.handholdCanMapCols_DM()))).toIndexedSeq().map((x$1) -> ((DenseVector)x$1.t(HasOps$.MODULE$.canUntranspose())).toArray$mcD$sp(ct))).toArray(.MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(ct.runtimeClass())));
   }

   public float[][] dmToArray2$mFc$sp(final DenseMatrix dm) {
      ClassTag ct = ReflectionUtil$.MODULE$.elemClassTagFromArray(dm.data$mcF$sp());
      return (float[][])((IterableOnceOps)BroadcastedRows$.MODULE$.BroadcastRowsDMToIndexedSeq((BroadcastedRows)dm.apply($times$.MODULE$, scala.package..MODULE$.$colon$colon(), Broadcaster$.MODULE$.canBroadcastRows(HasOps$.MODULE$.handholdCanMapCols_DM()))).toIndexedSeq().map((x$1) -> ((DenseVector)x$1.t(HasOps$.MODULE$.canUntranspose())).toArray$mcF$sp(ct))).toArray(.MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(ct.runtimeClass())));
   }

   public int[][] dmToArray2$mIc$sp(final DenseMatrix dm) {
      ClassTag ct = ReflectionUtil$.MODULE$.elemClassTagFromArray(dm.data$mcI$sp());
      return (int[][])((IterableOnceOps)BroadcastedRows$.MODULE$.BroadcastRowsDMToIndexedSeq((BroadcastedRows)dm.apply($times$.MODULE$, scala.package..MODULE$.$colon$colon(), Broadcaster$.MODULE$.canBroadcastRows(HasOps$.MODULE$.handholdCanMapCols_DM()))).toIndexedSeq().map((x$1) -> ((DenseVector)x$1.t(HasOps$.MODULE$.canUntranspose())).toArray$mcI$sp(ct))).toArray(.MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(ct.runtimeClass())));
   }

   public long[][] dmToArray2$mJc$sp(final DenseMatrix dm) {
      ClassTag ct = ReflectionUtil$.MODULE$.elemClassTagFromArray(dm.data$mcJ$sp());
      return (long[][])((IterableOnceOps)BroadcastedRows$.MODULE$.BroadcastRowsDMToIndexedSeq((BroadcastedRows)dm.apply($times$.MODULE$, scala.package..MODULE$.$colon$colon(), Broadcaster$.MODULE$.canBroadcastRows(HasOps$.MODULE$.handholdCanMapCols_DM()))).toIndexedSeq().map((x$1) -> ((DenseVector)x$1.t(HasOps$.MODULE$.canUntranspose())).toArray$mcJ$sp(ct))).toArray(.MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(ct.runtimeClass())));
   }

   public DenseVector arrayToDv$mDc$sp(final double[] array, final ClassTag evidence$1) {
      return new DenseVector$mcD$sp(array);
   }

   public DenseVector arrayToDv$mFc$sp(final float[] array, final ClassTag evidence$1) {
      return new DenseVector$mcF$sp(array);
   }

   public DenseVector arrayToDv$mIc$sp(final int[] array, final ClassTag evidence$1) {
      return new DenseVector$mcI$sp(array);
   }

   public DenseVector arrayToDv$mJc$sp(final long[] array, final ClassTag evidence$1) {
      return new DenseVector$mcJ$sp(array);
   }

   public DenseMatrix array2ToDm$mDc$sp(final double[][] values, final ClassTag evidence$2) {
      int tempRows = values.length;
      int tempCols = values[0].length;
      double[] tempret = (double[])evidence$2.newArray(tempRows * tempCols);
      int rowIndex = 0;

      int tempretIndex;
      for(tempretIndex = 0; rowIndex < tempRows; ++rowIndex) {
         scala.Predef..MODULE$.require(values[rowIndex].length == tempCols, () -> "Input Array[Array[V]] is ragged!");
      }

      for(int colIndex = 0; colIndex < tempCols; ++colIndex) {
         for(int var9 = 0; var9 < tempRows; ++var9) {
            tempret[tempretIndex] = values[var9][colIndex];
            ++tempretIndex;
         }
      }

      return new DenseMatrix$mcD$sp(tempRows, tempCols, tempret);
   }

   public DenseMatrix array2ToDm$mFc$sp(final float[][] values, final ClassTag evidence$2) {
      int tempRows = values.length;
      int tempCols = values[0].length;
      float[] tempret = (float[])evidence$2.newArray(tempRows * tempCols);
      int rowIndex = 0;

      int tempretIndex;
      for(tempretIndex = 0; rowIndex < tempRows; ++rowIndex) {
         scala.Predef..MODULE$.require(values[rowIndex].length == tempCols, () -> "Input Array[Array[V]] is ragged!");
      }

      for(int colIndex = 0; colIndex < tempCols; ++colIndex) {
         for(int var9 = 0; var9 < tempRows; ++var9) {
            tempret[tempretIndex] = values[var9][colIndex];
            ++tempretIndex;
         }
      }

      return new DenseMatrix$mcF$sp(tempRows, tempCols, tempret);
   }

   public DenseMatrix array2ToDm$mIc$sp(final int[][] values, final ClassTag evidence$2) {
      int tempRows = values.length;
      int tempCols = values[0].length;
      int[] tempret = (int[])evidence$2.newArray(tempRows * tempCols);
      int rowIndex = 0;

      int tempretIndex;
      for(tempretIndex = 0; rowIndex < tempRows; ++rowIndex) {
         scala.Predef..MODULE$.require(values[rowIndex].length == tempCols, () -> "Input Array[Array[V]] is ragged!");
      }

      for(int colIndex = 0; colIndex < tempCols; ++colIndex) {
         for(int var9 = 0; var9 < tempRows; ++var9) {
            tempret[tempretIndex] = values[var9][colIndex];
            ++tempretIndex;
         }
      }

      return new DenseMatrix$mcI$sp(tempRows, tempCols, tempret);
   }

   public DenseMatrix array2ToDm$mJc$sp(final long[][] values, final ClassTag evidence$2) {
      int tempRows = values.length;
      int tempCols = values[0].length;
      long[] tempret = (long[])evidence$2.newArray(tempRows * tempCols);
      int rowIndex = 0;

      int tempretIndex;
      for(tempretIndex = 0; rowIndex < tempRows; ++rowIndex) {
         scala.Predef..MODULE$.require(values[rowIndex].length == tempCols, () -> "Input Array[Array[V]] is ragged!");
      }

      for(int colIndex = 0; colIndex < tempCols; ++colIndex) {
         for(int var9 = 0; var9 < tempRows; ++var9) {
            tempret[tempretIndex] = values[var9][colIndex];
            ++tempretIndex;
         }
      }

      return new DenseMatrix$mcJ$sp(tempRows, tempCols, tempret);
   }

   private JavaArrayOps$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
