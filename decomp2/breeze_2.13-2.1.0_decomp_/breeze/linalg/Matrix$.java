package breeze.linalg;

import breeze.generic.UFunc;
import breeze.linalg.support.CanCreateZeros;
import breeze.linalg.support.CanMapKeyValuePairs;
import breeze.linalg.support.CanMapValues;
import breeze.linalg.support.CanSlice;
import breeze.linalg.support.CanSlice2;
import breeze.linalg.support.CanTranspose;
import breeze.linalg.support.CanTraverseKeyValuePairs;
import breeze.linalg.support.CanTraverseValues;
import breeze.linalg.support.LiteralRow;
import breeze.linalg.support.TensorActive;
import breeze.linalg.support.TensorKeys;
import breeze.linalg.support.TensorPairs;
import breeze.linalg.support.TensorValues;
import breeze.math.Semiring;
import breeze.stats.distributions.Rand;
import breeze.storage.Zero;
import java.lang.invoke.SerializedLambda;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.package.;
import scala.reflect.ClassTag;
import scala.runtime.BoxedUnit;

public final class Matrix$ implements MatrixConstructors, LowPriorityMatrix {
   public static final Matrix$ MODULE$ = new Matrix$();

   static {
      MatrixConstructors.$init$(MODULE$);
      LowPriorityMatrix.$init$(MODULE$);
   }

   public CanSlice2 canSliceTensorBooleanRows(final Semiring evidence$21, final ClassTag evidence$22) {
      return LowPriorityMatrix.canSliceTensorBooleanRows$(this, evidence$21, evidence$22);
   }

   public CanSlice2 canSliceTensorBooleanCols(final Semiring evidence$23, final ClassTag evidence$24) {
      return LowPriorityMatrix.canSliceTensorBooleanCols$(this, evidence$23, evidence$24);
   }

   public CanSlice2 canSliceTensorBooleanRowsAndCol(final Semiring evidence$25, final ClassTag evidence$26) {
      return LowPriorityMatrix.canSliceTensorBooleanRowsAndCol$(this, evidence$25, evidence$26);
   }

   public CanSlice2 canSliceRowAndTensorBooleanCols(final Semiring evidence$27, final ClassTag evidence$28) {
      return LowPriorityMatrix.canSliceRowAndTensorBooleanCols$(this, evidence$27, evidence$28);
   }

   public CanSlice2 canSliceTensorBooleanRowsAndCols(final Semiring evidence$29, final ClassTag evidence$30) {
      return LowPriorityMatrix.canSliceTensorBooleanRowsAndCols$(this, evidence$29, evidence$30);
   }

   public CanSlice2 canSliceTensorBooleanRowsAndWeirdCols(final Semiring evidence$31, final ClassTag evidence$32) {
      return LowPriorityMatrix.canSliceTensorBooleanRowsAndWeirdCols$(this, evidence$31, evidence$32);
   }

   public CanSlice2 canSliceWeirdRowsAndTensorBooleanCols(final Semiring evidence$33, final ClassTag evidence$34) {
      return LowPriorityMatrix.canSliceWeirdRowsAndTensorBooleanCols$(this, evidence$33, evidence$34);
   }

   public Matrix ones(final int rows, final int cols, final ClassTag evidence$10, final Zero evidence$11, final Semiring evidence$12) {
      return MatrixConstructors.ones$(this, rows, cols, evidence$10, evidence$11, evidence$12);
   }

   public Matrix ones$mDc$sp(final int rows, final int cols, final ClassTag evidence$10, final Zero evidence$11, final Semiring evidence$12) {
      return MatrixConstructors.ones$mDc$sp$(this, rows, cols, evidence$10, evidence$11, evidence$12);
   }

   public Matrix ones$mFc$sp(final int rows, final int cols, final ClassTag evidence$10, final Zero evidence$11, final Semiring evidence$12) {
      return MatrixConstructors.ones$mFc$sp$(this, rows, cols, evidence$10, evidence$11, evidence$12);
   }

   public Matrix ones$mIc$sp(final int rows, final int cols, final ClassTag evidence$10, final Zero evidence$11, final Semiring evidence$12) {
      return MatrixConstructors.ones$mIc$sp$(this, rows, cols, evidence$10, evidence$11, evidence$12);
   }

   public Matrix ones$mJc$sp(final int rows, final int cols, final ClassTag evidence$10, final Zero evidence$11, final Semiring evidence$12) {
      return MatrixConstructors.ones$mJc$sp$(this, rows, cols, evidence$10, evidence$11, evidence$12);
   }

   public Matrix fill(final int rows, final int cols, final Function0 v, final ClassTag evidence$13, final Zero evidence$14) {
      return MatrixConstructors.fill$(this, rows, cols, v, evidence$13, evidence$14);
   }

   public Matrix fill$mDc$sp(final int rows, final int cols, final Function0 v, final ClassTag evidence$13, final Zero evidence$14) {
      return MatrixConstructors.fill$mDc$sp$(this, rows, cols, v, evidence$13, evidence$14);
   }

   public Matrix fill$mFc$sp(final int rows, final int cols, final Function0 v, final ClassTag evidence$13, final Zero evidence$14) {
      return MatrixConstructors.fill$mFc$sp$(this, rows, cols, v, evidence$13, evidence$14);
   }

   public Matrix fill$mIc$sp(final int rows, final int cols, final Function0 v, final ClassTag evidence$13, final Zero evidence$14) {
      return MatrixConstructors.fill$mIc$sp$(this, rows, cols, v, evidence$13, evidence$14);
   }

   public Matrix fill$mJc$sp(final int rows, final int cols, final Function0 v, final ClassTag evidence$13, final Zero evidence$14) {
      return MatrixConstructors.fill$mJc$sp$(this, rows, cols, v, evidence$13, evidence$14);
   }

   public Matrix tabulate(final int rows, final int cols, final Function2 f, final ClassTag evidence$15, final Zero evidence$16) {
      return MatrixConstructors.tabulate$(this, rows, cols, f, evidence$15, evidence$16);
   }

   public Matrix tabulate$mDc$sp(final int rows, final int cols, final Function2 f, final ClassTag evidence$15, final Zero evidence$16) {
      return MatrixConstructors.tabulate$mDc$sp$(this, rows, cols, f, evidence$15, evidence$16);
   }

   public Matrix tabulate$mFc$sp(final int rows, final int cols, final Function2 f, final ClassTag evidence$15, final Zero evidence$16) {
      return MatrixConstructors.tabulate$mFc$sp$(this, rows, cols, f, evidence$15, evidence$16);
   }

   public Matrix tabulate$mIc$sp(final int rows, final int cols, final Function2 f, final ClassTag evidence$15, final Zero evidence$16) {
      return MatrixConstructors.tabulate$mIc$sp$(this, rows, cols, f, evidence$15, evidence$16);
   }

   public Matrix tabulate$mJc$sp(final int rows, final int cols, final Function2 f, final ClassTag evidence$15, final Zero evidence$16) {
      return MatrixConstructors.tabulate$mJc$sp$(this, rows, cols, f, evidence$15, evidence$16);
   }

   public Matrix rand(final int rows, final int cols, final Rand rand, final ClassTag evidence$17, final Zero evidence$18) {
      return MatrixConstructors.rand$(this, rows, cols, rand, evidence$17, evidence$18);
   }

   public Rand rand$default$3() {
      return MatrixConstructors.rand$default$3$(this);
   }

   public Matrix apply(final Seq rows, final LiteralRow rl, final ClassTag man, final Zero zero) {
      return MatrixConstructors.apply$(this, rows, rl, man, zero);
   }

   public Matrix apply$mDc$sp(final Seq rows, final LiteralRow rl, final ClassTag man, final Zero zero) {
      return MatrixConstructors.apply$mDc$sp$(this, rows, rl, man, zero);
   }

   public Matrix apply$mFc$sp(final Seq rows, final LiteralRow rl, final ClassTag man, final Zero zero) {
      return MatrixConstructors.apply$mFc$sp$(this, rows, rl, man, zero);
   }

   public Matrix apply$mIc$sp(final Seq rows, final LiteralRow rl, final ClassTag man, final Zero zero) {
      return MatrixConstructors.apply$mIc$sp$(this, rows, rl, man, zero);
   }

   public Matrix apply$mJc$sp(final Seq rows, final LiteralRow rl, final ClassTag man, final Zero zero) {
      return MatrixConstructors.apply$mJc$sp$(this, rows, rl, man, zero);
   }

   public CanCreateZeros canCreateZeros(final ClassTag evidence$19, final Zero evidence$20) {
      return MatrixConstructors.canCreateZeros$(this, evidence$19, evidence$20);
   }

   public Matrix zeros(final int rows, final int cols, final ClassTag evidence$1, final Zero evidence$2) {
      return DenseMatrix$.MODULE$.zeros(rows, cols, evidence$1, evidence$2);
   }

   public Matrix create(final int rows, final int cols, final Object data, final Zero evidence$3) {
      return DenseMatrix$.MODULE$.create(rows, cols, data, evidence$3);
   }

   public Matrix zeroRows(final int cols, final ClassTag evidence$4) {
      return this.emptyMatrix(0, cols, evidence$4);
   }

   public Matrix zeroCols(final int rows, final ClassTag evidence$5) {
      return this.emptyMatrix(rows, 0, evidence$5);
   }

   public Matrix emptyMatrix(final int _rows, final int _cols, final ClassTag evidence$6) {
      return new Matrix(_rows, _cols, evidence$6) {
         private final int _rows$1;
         private final int _cols$1;
         private final ClassTag evidence$6$1;

         public Object apply(final Tuple2 i) {
            return Matrix.apply$(this, i);
         }

         public double apply$mcD$sp(final Tuple2 i) {
            return Matrix.apply$mcD$sp$(this, i);
         }

         public float apply$mcF$sp(final Tuple2 i) {
            return Matrix.apply$mcF$sp$(this, i);
         }

         public int apply$mcI$sp(final Tuple2 i) {
            return Matrix.apply$mcI$sp$(this, i);
         }

         public long apply$mcJ$sp(final Tuple2 i) {
            return Matrix.apply$mcJ$sp$(this, i);
         }

         public void update(final Tuple2 i, final Object e) {
            Matrix.update$(this, i, e);
         }

         public void update$mcD$sp(final Tuple2 i, final double e) {
            Matrix.update$mcD$sp$(this, i, e);
         }

         public void update$mcF$sp(final Tuple2 i, final float e) {
            Matrix.update$mcF$sp$(this, i, e);
         }

         public void update$mcI$sp(final Tuple2 i, final int e) {
            Matrix.update$mcI$sp$(this, i, e);
         }

         public void update$mcJ$sp(final Tuple2 i, final long e) {
            Matrix.update$mcJ$sp$(this, i, e);
         }

         public double apply$mcD$sp(final int i, final int j) {
            return Matrix.apply$mcD$sp$(this, i, j);
         }

         public float apply$mcF$sp(final int i, final int j) {
            return Matrix.apply$mcF$sp$(this, i, j);
         }

         public int apply$mcI$sp(final int i, final int j) {
            return Matrix.apply$mcI$sp$(this, i, j);
         }

         public long apply$mcJ$sp(final int i, final int j) {
            return Matrix.apply$mcJ$sp$(this, i, j);
         }

         public void update$mcD$sp(final int i, final int j, final double e) {
            Matrix.update$mcD$sp$(this, i, j, e);
         }

         public void update$mcF$sp(final int i, final int j, final float e) {
            Matrix.update$mcF$sp$(this, i, j, e);
         }

         public void update$mcI$sp(final int i, final int j, final int e) {
            Matrix.update$mcI$sp$(this, i, j, e);
         }

         public void update$mcJ$sp(final int i, final int j, final long e) {
            Matrix.update$mcJ$sp$(this, i, j, e);
         }

         public int size() {
            return Matrix.size$(this);
         }

         public Set keySet() {
            return Matrix.keySet$(this);
         }

         public Iterator iterator() {
            return Matrix.iterator$(this);
         }

         public Iterator valuesIterator() {
            return Matrix.valuesIterator$(this);
         }

         public Iterator keysIterator() {
            return Matrix.keysIterator$(this);
         }

         public String toString(final int maxLines, final int maxWidth) {
            return Matrix.toString$(this, maxLines, maxWidth);
         }

         public int toString$default$1() {
            return Matrix.toString$default$1$(this);
         }

         public int toString$default$2() {
            return Matrix.toString$default$2$(this);
         }

         public String toString() {
            return Matrix.toString$(this);
         }

         public DenseMatrix toDenseMatrix(final ClassTag cm, final Zero zero) {
            return Matrix.toDenseMatrix$(this, cm, zero);
         }

         public DenseMatrix toDenseMatrix$mcD$sp(final ClassTag cm, final Zero zero) {
            return Matrix.toDenseMatrix$mcD$sp$(this, cm, zero);
         }

         public DenseMatrix toDenseMatrix$mcF$sp(final ClassTag cm, final Zero zero) {
            return Matrix.toDenseMatrix$mcF$sp$(this, cm, zero);
         }

         public DenseMatrix toDenseMatrix$mcI$sp(final ClassTag cm, final Zero zero) {
            return Matrix.toDenseMatrix$mcI$sp$(this, cm, zero);
         }

         public DenseMatrix toDenseMatrix$mcJ$sp(final ClassTag cm, final Zero zero) {
            return Matrix.toDenseMatrix$mcJ$sp$(this, cm, zero);
         }

         public Matrix copy$mcD$sp() {
            return Matrix.copy$mcD$sp$(this);
         }

         public Matrix copy$mcF$sp() {
            return Matrix.copy$mcF$sp$(this);
         }

         public Matrix copy$mcI$sp() {
            return Matrix.copy$mcI$sp$(this);
         }

         public Matrix copy$mcJ$sp() {
            return Matrix.copy$mcJ$sp$(this);
         }

         public Vector flatten$mcD$sp(final View view) {
            return Matrix.flatten$mcD$sp$(this, view);
         }

         public Vector flatten$mcF$sp(final View view) {
            return Matrix.flatten$mcF$sp$(this, view);
         }

         public Vector flatten$mcI$sp(final View view) {
            return Matrix.flatten$mcI$sp$(this, view);
         }

         public Vector flatten$mcJ$sp(final View view) {
            return Matrix.flatten$mcJ$sp$(this, view);
         }

         public View flatten$default$1() {
            return Matrix.flatten$default$1$(this);
         }

         public boolean equals(final Object p1) {
            return Matrix.equals$(this, p1);
         }

         public Object map(final Function1 fn, final CanMapValues canMapValues) {
            return MatrixLike.map$(this, fn, canMapValues);
         }

         public Object map$mcD$sp(final Function1 fn, final CanMapValues canMapValues) {
            return MatrixLike.map$mcD$sp$(this, fn, canMapValues);
         }

         public Object map$mcF$sp(final Function1 fn, final CanMapValues canMapValues) {
            return MatrixLike.map$mcF$sp$(this, fn, canMapValues);
         }

         public Object map$mcI$sp(final Function1 fn, final CanMapValues canMapValues) {
            return MatrixLike.map$mcI$sp$(this, fn, canMapValues);
         }

         public Object map$mcJ$sp(final Function1 fn, final CanMapValues canMapValues) {
            return MatrixLike.map$mcJ$sp$(this, fn, canMapValues);
         }

         public double apply$mcID$sp(final int i) {
            return TensorLike.apply$mcID$sp$(this, i);
         }

         public float apply$mcIF$sp(final int i) {
            return TensorLike.apply$mcIF$sp$(this, i);
         }

         public int apply$mcII$sp(final int i) {
            return TensorLike.apply$mcII$sp$(this, i);
         }

         public long apply$mcIJ$sp(final int i) {
            return TensorLike.apply$mcIJ$sp$(this, i);
         }

         public void update$mcID$sp(final int i, final double v) {
            TensorLike.update$mcID$sp$(this, i, v);
         }

         public void update$mcIF$sp(final int i, final float v) {
            TensorLike.update$mcIF$sp$(this, i, v);
         }

         public void update$mcII$sp(final int i, final int v) {
            TensorLike.update$mcII$sp$(this, i, v);
         }

         public void update$mcIJ$sp(final int i, final long v) {
            TensorLike.update$mcIJ$sp$(this, i, v);
         }

         public TensorKeys keys() {
            return TensorLike.keys$(this);
         }

         public TensorValues values() {
            return TensorLike.values$(this);
         }

         public TensorPairs pairs() {
            return TensorLike.pairs$(this);
         }

         public TensorActive active() {
            return TensorLike.active$(this);
         }

         public Object apply(final Object slice, final CanSlice canSlice) {
            return TensorLike.apply$(this, slice, canSlice);
         }

         public Object apply(final Object a, final Object b, final Object c, final Seq slice, final CanSlice canSlice) {
            return TensorLike.apply$(this, a, b, c, slice, canSlice);
         }

         public Object apply$mcI$sp(final int a, final int b, final int c, final Seq slice, final CanSlice canSlice) {
            return TensorLike.apply$mcI$sp$(this, a, b, c, slice, canSlice);
         }

         public Object apply(final Object slice1, final Object slice2, final CanSlice2 canSlice) {
            return TensorLike.apply$(this, slice1, slice2, canSlice);
         }

         public Object mapPairs(final Function2 f, final CanMapKeyValuePairs bf) {
            return TensorLike.mapPairs$(this, f, bf);
         }

         public Object mapPairs$mcID$sp(final Function2 f, final CanMapKeyValuePairs bf) {
            return TensorLike.mapPairs$mcID$sp$(this, f, bf);
         }

         public Object mapPairs$mcIF$sp(final Function2 f, final CanMapKeyValuePairs bf) {
            return TensorLike.mapPairs$mcIF$sp$(this, f, bf);
         }

         public Object mapPairs$mcII$sp(final Function2 f, final CanMapKeyValuePairs bf) {
            return TensorLike.mapPairs$mcII$sp$(this, f, bf);
         }

         public Object mapPairs$mcIJ$sp(final Function2 f, final CanMapKeyValuePairs bf) {
            return TensorLike.mapPairs$mcIJ$sp$(this, f, bf);
         }

         public Object mapActivePairs(final Function2 f, final CanMapKeyValuePairs bf) {
            return TensorLike.mapActivePairs$(this, f, bf);
         }

         public Object mapActivePairs$mcID$sp(final Function2 f, final CanMapKeyValuePairs bf) {
            return TensorLike.mapActivePairs$mcID$sp$(this, f, bf);
         }

         public Object mapActivePairs$mcIF$sp(final Function2 f, final CanMapKeyValuePairs bf) {
            return TensorLike.mapActivePairs$mcIF$sp$(this, f, bf);
         }

         public Object mapActivePairs$mcII$sp(final Function2 f, final CanMapKeyValuePairs bf) {
            return TensorLike.mapActivePairs$mcII$sp$(this, f, bf);
         }

         public Object mapActivePairs$mcIJ$sp(final Function2 f, final CanMapKeyValuePairs bf) {
            return TensorLike.mapActivePairs$mcIJ$sp$(this, f, bf);
         }

         public Object mapValues(final Function1 f, final CanMapValues bf) {
            return TensorLike.mapValues$(this, f, bf);
         }

         public Object mapValues$mcD$sp(final Function1 f, final CanMapValues bf) {
            return TensorLike.mapValues$mcD$sp$(this, f, bf);
         }

         public Object mapValues$mcF$sp(final Function1 f, final CanMapValues bf) {
            return TensorLike.mapValues$mcF$sp$(this, f, bf);
         }

         public Object mapValues$mcI$sp(final Function1 f, final CanMapValues bf) {
            return TensorLike.mapValues$mcI$sp$(this, f, bf);
         }

         public Object mapValues$mcJ$sp(final Function1 f, final CanMapValues bf) {
            return TensorLike.mapValues$mcJ$sp$(this, f, bf);
         }

         public Object mapActiveValues(final Function1 f, final CanMapValues bf) {
            return TensorLike.mapActiveValues$(this, f, bf);
         }

         public Object mapActiveValues$mcD$sp(final Function1 f, final CanMapValues bf) {
            return TensorLike.mapActiveValues$mcD$sp$(this, f, bf);
         }

         public Object mapActiveValues$mcF$sp(final Function1 f, final CanMapValues bf) {
            return TensorLike.mapActiveValues$mcF$sp$(this, f, bf);
         }

         public Object mapActiveValues$mcI$sp(final Function1 f, final CanMapValues bf) {
            return TensorLike.mapActiveValues$mcI$sp$(this, f, bf);
         }

         public Object mapActiveValues$mcJ$sp(final Function1 f, final CanMapValues bf) {
            return TensorLike.mapActiveValues$mcJ$sp$(this, f, bf);
         }

         public void foreachKey(final Function1 fn) {
            TensorLike.foreachKey$(this, fn);
         }

         public void foreachKey$mcI$sp(final Function1 fn) {
            TensorLike.foreachKey$mcI$sp$(this, fn);
         }

         public void foreachPair(final Function2 fn) {
            TensorLike.foreachPair$(this, fn);
         }

         public void foreachPair$mcID$sp(final Function2 fn) {
            TensorLike.foreachPair$mcID$sp$(this, fn);
         }

         public void foreachPair$mcIF$sp(final Function2 fn) {
            TensorLike.foreachPair$mcIF$sp$(this, fn);
         }

         public void foreachPair$mcII$sp(final Function2 fn) {
            TensorLike.foreachPair$mcII$sp$(this, fn);
         }

         public void foreachPair$mcIJ$sp(final Function2 fn) {
            TensorLike.foreachPair$mcIJ$sp$(this, fn);
         }

         public void foreachValue(final Function1 fn) {
            TensorLike.foreachValue$(this, fn);
         }

         public void foreachValue$mcD$sp(final Function1 fn) {
            TensorLike.foreachValue$mcD$sp$(this, fn);
         }

         public void foreachValue$mcF$sp(final Function1 fn) {
            TensorLike.foreachValue$mcF$sp$(this, fn);
         }

         public void foreachValue$mcI$sp(final Function1 fn) {
            TensorLike.foreachValue$mcI$sp$(this, fn);
         }

         public void foreachValue$mcJ$sp(final Function1 fn) {
            TensorLike.foreachValue$mcJ$sp$(this, fn);
         }

         public boolean forall(final Function2 fn) {
            return TensorLike.forall$(this, (Function2)fn);
         }

         public boolean forall$mcID$sp(final Function2 fn) {
            return TensorLike.forall$mcID$sp$(this, fn);
         }

         public boolean forall$mcIF$sp(final Function2 fn) {
            return TensorLike.forall$mcIF$sp$(this, fn);
         }

         public boolean forall$mcII$sp(final Function2 fn) {
            return TensorLike.forall$mcII$sp$(this, fn);
         }

         public boolean forall$mcIJ$sp(final Function2 fn) {
            return TensorLike.forall$mcIJ$sp$(this, fn);
         }

         public boolean forall(final Function1 fn) {
            return TensorLike.forall$(this, (Function1)fn);
         }

         public boolean forall$mcD$sp(final Function1 fn) {
            return TensorLike.forall$mcD$sp$(this, fn);
         }

         public boolean forall$mcF$sp(final Function1 fn) {
            return TensorLike.forall$mcF$sp$(this, fn);
         }

         public boolean forall$mcI$sp(final Function1 fn) {
            return TensorLike.forall$mcI$sp$(this, fn);
         }

         public boolean forall$mcJ$sp(final Function1 fn) {
            return TensorLike.forall$mcJ$sp$(this, fn);
         }

         public final Object $plus(final Object b, final UFunc.UImpl2 op) {
            return NumericOps.$plus$(this, b, op);
         }

         public final Object $colon$eq(final Object b, final UFunc.InPlaceImpl2 op) {
            return NumericOps.$colon$eq$(this, b, op);
         }

         public final Object $colon$plus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
            return NumericOps.$colon$plus$eq$(this, b, op);
         }

         public final Object $colon$times$eq(final Object b, final UFunc.InPlaceImpl2 op) {
            return NumericOps.$colon$times$eq$(this, b, op);
         }

         public final Object $plus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
            return NumericOps.$plus$eq$(this, b, op);
         }

         public final Object $times$eq(final Object b, final UFunc.InPlaceImpl2 op) {
            return NumericOps.$times$eq$(this, b, op);
         }

         public final Object $colon$minus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
            return NumericOps.$colon$minus$eq$(this, b, op);
         }

         public final Object $colon$percent$eq(final Object b, final UFunc.InPlaceImpl2 op) {
            return NumericOps.$colon$percent$eq$(this, b, op);
         }

         public final Object $percent$eq(final Object b, final UFunc.InPlaceImpl2 op) {
            return NumericOps.$percent$eq$(this, b, op);
         }

         public final Object $minus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
            return NumericOps.$minus$eq$(this, b, op);
         }

         public final Object $colon$div$eq(final Object b, final UFunc.InPlaceImpl2 op) {
            return NumericOps.$colon$div$eq$(this, b, op);
         }

         public final Object $colon$up$eq(final Object b, final UFunc.InPlaceImpl2 op) {
            return NumericOps.$colon$up$eq$(this, b, op);
         }

         public final Object $div$eq(final Object b, final UFunc.InPlaceImpl2 op) {
            return NumericOps.$div$eq$(this, b, op);
         }

         public final Object $less$colon$less(final Object b, final UFunc.UImpl2 op) {
            return NumericOps.$less$colon$less$(this, b, op);
         }

         public final Object $less$colon$eq(final Object b, final UFunc.UImpl2 op) {
            return NumericOps.$less$colon$eq$(this, b, op);
         }

         public final Object $greater$colon$greater(final Object b, final UFunc.UImpl2 op) {
            return NumericOps.$greater$colon$greater$(this, b, op);
         }

         public final Object $greater$colon$eq(final Object b, final UFunc.UImpl2 op) {
            return NumericOps.$greater$colon$eq$(this, b, op);
         }

         public final Object $colon$amp$eq(final Object b, final UFunc.InPlaceImpl2 op) {
            return NumericOps.$colon$amp$eq$(this, b, op);
         }

         public final Object $colon$bar$eq(final Object b, final UFunc.InPlaceImpl2 op) {
            return NumericOps.$colon$bar$eq$(this, b, op);
         }

         public final Object $colon$up$up$eq(final Object b, final UFunc.InPlaceImpl2 op) {
            return NumericOps.$colon$up$up$eq$(this, b, op);
         }

         public final Object $amp$eq(final Object b, final UFunc.InPlaceImpl2 op) {
            return NumericOps.$amp$eq$(this, b, op);
         }

         public final Object $bar$eq(final Object b, final UFunc.InPlaceImpl2 op) {
            return NumericOps.$bar$eq$(this, b, op);
         }

         public final Object $up$up$eq(final Object b, final UFunc.InPlaceImpl2 op) {
            return NumericOps.$up$up$eq$(this, b, op);
         }

         public final Object $plus$colon$plus(final Object b, final UFunc.UImpl2 op) {
            return ImmutableNumericOps.$plus$colon$plus$(this, b, op);
         }

         public final Object $times$colon$times(final Object b, final UFunc.UImpl2 op) {
            return ImmutableNumericOps.$times$colon$times$(this, b, op);
         }

         public final Object $colon$eq$eq(final Object b, final UFunc.UImpl2 op) {
            return ImmutableNumericOps.$colon$eq$eq$(this, b, op);
         }

         public final Object $colon$bang$eq(final Object b, final UFunc.UImpl2 op) {
            return ImmutableNumericOps.$colon$bang$eq$(this, b, op);
         }

         public final Object unary_$minus(final UFunc.UImpl op) {
            return ImmutableNumericOps.unary_$minus$(this, op);
         }

         public final Object $minus$colon$minus(final Object b, final UFunc.UImpl2 op) {
            return ImmutableNumericOps.$minus$colon$minus$(this, b, op);
         }

         public final Object $minus(final Object b, final UFunc.UImpl2 op) {
            return ImmutableNumericOps.$minus$(this, b, op);
         }

         public final Object $percent$colon$percent(final Object b, final UFunc.UImpl2 op) {
            return ImmutableNumericOps.$percent$colon$percent$(this, b, op);
         }

         public final Object $percent(final Object b, final UFunc.UImpl2 op) {
            return ImmutableNumericOps.$percent$(this, b, op);
         }

         public final Object $div$colon$div(final Object b, final UFunc.UImpl2 op) {
            return ImmutableNumericOps.$div$colon$div$(this, b, op);
         }

         public final Object $div(final Object b, final UFunc.UImpl2 op) {
            return ImmutableNumericOps.$div$(this, b, op);
         }

         public final Object $up$colon$up(final Object b, final UFunc.UImpl2 op) {
            return ImmutableNumericOps.$up$colon$up$(this, b, op);
         }

         public final Object dot(final Object b, final UFunc.UImpl2 op) {
            return ImmutableNumericOps.dot$(this, b, op);
         }

         public final Object unary_$bang(final UFunc.UImpl op) {
            return ImmutableNumericOps.unary_$bang$(this, op);
         }

         public final Object $amp$colon$amp(final Object b, final UFunc.UImpl2 op) {
            return ImmutableNumericOps.$amp$colon$amp$(this, b, op);
         }

         public final Object $bar$colon$bar(final Object b, final UFunc.UImpl2 op) {
            return ImmutableNumericOps.$bar$colon$bar$(this, b, op);
         }

         public final Object $up$up$colon$up$up(final Object b, final UFunc.UImpl2 op) {
            return ImmutableNumericOps.$up$up$colon$up$up$(this, b, op);
         }

         public final Object $amp(final Object b, final UFunc.UImpl2 op) {
            return ImmutableNumericOps.$amp$(this, b, op);
         }

         public final Object $bar(final Object b, final UFunc.UImpl2 op) {
            return ImmutableNumericOps.$bar$(this, b, op);
         }

         public final Object $up$up(final Object b, final UFunc.UImpl2 op) {
            return ImmutableNumericOps.$up$up$(this, b, op);
         }

         public final Object $times(final Object b, final UFunc.UImpl2 op) {
            return ImmutableNumericOps.$times$(this, b, op);
         }

         public final Object t(final CanTranspose op) {
            return ImmutableNumericOps.t$(this, op);
         }

         public Object $bslash(final Object b, final UFunc.UImpl2 op) {
            return ImmutableNumericOps.$bslash$(this, b, op);
         }

         public final Object t(final Object a, final Object b, final CanTranspose op, final CanSlice2 canSlice) {
            return ImmutableNumericOps.t$(this, a, b, op, canSlice);
         }

         public final Object t(final Object a, final CanTranspose op, final CanSlice canSlice) {
            return ImmutableNumericOps.t$(this, a, op, canSlice);
         }

         public IndexedSeq findAll(final Function1 f) {
            return QuasiTensor.findAll$(this, f);
         }

         public IndexedSeq findAll$mcD$sp(final Function1 f) {
            return QuasiTensor.findAll$mcD$sp$(this, f);
         }

         public IndexedSeq findAll$mcF$sp(final Function1 f) {
            return QuasiTensor.findAll$mcF$sp$(this, f);
         }

         public IndexedSeq findAll$mcI$sp(final Function1 f) {
            return QuasiTensor.findAll$mcI$sp$(this, f);
         }

         public IndexedSeq findAll$mcJ$sp(final Function1 f) {
            return QuasiTensor.findAll$mcJ$sp$(this, f);
         }

         public int hashCode() {
            return QuasiTensor.hashCode$(this);
         }

         public Iterator activeIterator() {
            return .MODULE$.Iterator().empty();
         }

         public Iterator activeValuesIterator() {
            return .MODULE$.Iterator().empty();
         }

         public Iterator activeKeysIterator() {
            return .MODULE$.Iterator().empty();
         }

         public Object apply(final int i, final int j) {
            throw new IndexOutOfBoundsException("Empty matrix!");
         }

         public void update(final int i, final int j, final Object e) {
            throw new IndexOutOfBoundsException("Empty matrix!");
         }

         public int rows() {
            return this._rows$1;
         }

         public int cols() {
            return this._cols$1;
         }

         public Matrix copy() {
            return this;
         }

         public int activeSize() {
            return 0;
         }

         public Matrix repr() {
            return this;
         }

         public Vector flatten(final View view) {
            return Vector$.MODULE$.apply(scala.collection.immutable.Nil..MODULE$, this.evidence$6$1);
         }

         public {
            this._rows$1 = _rows$1;
            this._cols$1 = _cols$1;
            this.evidence$6$1 = evidence$6$1;
            QuasiTensor.$init$(this);
            ImmutableNumericOps.$init$(this);
            NumericOps.$init$(this);
            TensorLike.$init$(this);
            MatrixLike.$init$(this);
            Matrix.$init$(this);
         }
      };
   }

   public CanTraverseKeyValuePairs canTraverseKeyValuePairs() {
      return new CanTraverseKeyValuePairs() {
         public boolean isTraversableAgain(final Matrix from) {
            return true;
         }

         public void traverse(final Matrix from, final CanTraverseKeyValuePairs.KeyValuePairsVisitor fn) {
            from.iterator().foreach(((k, a) -> {
               $anonfun$traverse$1(fn, k, a);
               return BoxedUnit.UNIT;
            }).tupled());
         }

         // $FF: synthetic method
         public static final void $anonfun$traverse$1(final CanTraverseKeyValuePairs.KeyValuePairsVisitor fn$1, final Tuple2 k, final Object a) {
            fn$1.visit(k, a);
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   public CanTraverseValues canTraverseValues() {
      return new CanTraverseValues() {
         public Object foldLeft(final Object from, final Object b, final Function2 fn) {
            return CanTraverseValues.foldLeft$(this, from, b, fn);
         }

         public boolean isTraversableAgain(final Matrix from) {
            return true;
         }

         public CanTraverseValues.ValuesVisitor traverse(final Matrix from, final CanTraverseValues.ValuesVisitor fn) {
            from.valuesIterator().foreach((a) -> {
               $anonfun$traverse$2(fn, a);
               return BoxedUnit.UNIT;
            });
            return fn;
         }

         // $FF: synthetic method
         public static final void $anonfun$traverse$2(final CanTraverseValues.ValuesVisitor fn$2, final Object a) {
            fn$2.visit(a);
         }

         public {
            CanTraverseValues.$init$(this);
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   public Matrix zeros$mDc$sp(final int rows, final int cols, final ClassTag evidence$1, final Zero evidence$2) {
      return DenseMatrix$.MODULE$.zeros$mDc$sp(rows, cols, evidence$1, evidence$2);
   }

   public Matrix zeros$mFc$sp(final int rows, final int cols, final ClassTag evidence$1, final Zero evidence$2) {
      return DenseMatrix$.MODULE$.zeros$mFc$sp(rows, cols, evidence$1, evidence$2);
   }

   public Matrix zeros$mIc$sp(final int rows, final int cols, final ClassTag evidence$1, final Zero evidence$2) {
      return DenseMatrix$.MODULE$.zeros$mIc$sp(rows, cols, evidence$1, evidence$2);
   }

   public Matrix zeros$mJc$sp(final int rows, final int cols, final ClassTag evidence$1, final Zero evidence$2) {
      return DenseMatrix$.MODULE$.zeros$mJc$sp(rows, cols, evidence$1, evidence$2);
   }

   public Matrix create$mDc$sp(final int rows, final int cols, final double[] data, final Zero evidence$3) {
      return DenseMatrix$.MODULE$.create$mDc$sp(rows, cols, data, evidence$3);
   }

   public Matrix create$mFc$sp(final int rows, final int cols, final float[] data, final Zero evidence$3) {
      return DenseMatrix$.MODULE$.create$mFc$sp(rows, cols, data, evidence$3);
   }

   public Matrix create$mIc$sp(final int rows, final int cols, final int[] data, final Zero evidence$3) {
      return DenseMatrix$.MODULE$.create$mIc$sp(rows, cols, data, evidence$3);
   }

   public Matrix create$mJc$sp(final int rows, final int cols, final long[] data, final Zero evidence$3) {
      return DenseMatrix$.MODULE$.create$mJc$sp(rows, cols, data, evidence$3);
   }

   private Matrix$() {
   }
}
