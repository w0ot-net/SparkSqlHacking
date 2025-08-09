package breeze.linalg;

import breeze.generic.UFunc;
import breeze.linalg.operators.HasOps$;
import breeze.linalg.support.CanCreateZeros;
import breeze.linalg.support.CanCreateZerosLike;
import breeze.linalg.support.LiteralRow;
import breeze.linalg.support.ScalarOf;
import breeze.linalg.support.ScalarOf$;
import breeze.math.Semiring;
import breeze.stats.distributions.Rand;
import breeze.storage.Zero;
import breeze.util.ArrayUtil$;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Function0;
import scala.Function2;
import scala.Predef.;
import scala.collection.immutable.Seq;
import scala.reflect.ClassTag;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.ModuleSerializationProxy;

public final class DenseMatrix$ implements MatrixConstructors, Serializable {
   public static final DenseMatrix$ MODULE$ = new DenseMatrix$();

   static {
      MatrixConstructors.$init$(MODULE$);
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

   public boolean $lessinit$greater$default$6() {
      return false;
   }

   public DenseMatrix zeros(final int rows, final int cols, final ClassTag evidence$1, final Zero evidence$2) {
      Object data = evidence$1.newArray(rows * cols);
      if (.MODULE$.implicitly(evidence$2) != null && rows * cols != 0 && !BoxesRunTime.equals(scala.runtime.ScalaRunTime..MODULE$.array_apply(data, 0), ((Zero).MODULE$.implicitly(evidence$2)).zero())) {
         ArrayUtil$.MODULE$.fill(data, 0, scala.runtime.ScalaRunTime..MODULE$.array_length(data), ((Zero).MODULE$.implicitly(evidence$2)).zero());
      }

      return this.create(rows, cols, data, evidence$2);
   }

   public DenseMatrix create(final int rows, final int cols, final Object data, final Zero evidence$3) {
      return this.create(rows, cols, data, 0, rows, false);
   }

   public DenseMatrix create(final int rows, final int cols, final Object data, final int offset, final int majorStride, final boolean isTranspose) {
      Object var7;
      if (data instanceof double[]) {
         double[] var9 = (double[])data;
         var7 = new DenseMatrix$mcD$sp(rows, cols, var9, offset, majorStride, isTranspose);
      } else if (data instanceof float[]) {
         float[] var10 = (float[])data;
         var7 = new DenseMatrix$mcF$sp(rows, cols, var10, offset, majorStride, isTranspose);
      } else if (data instanceof long[]) {
         long[] var11 = (long[])data;
         var7 = new DenseMatrix$mcJ$sp(rows, cols, var11, offset, majorStride, isTranspose);
      } else if (data instanceof int[]) {
         int[] var12 = (int[])data;
         var7 = new DenseMatrix$mcI$sp(rows, cols, var12, offset, majorStride, isTranspose);
      } else {
         var7 = new DenseMatrix(rows, cols, data, offset, majorStride, isTranspose);
      }

      return (DenseMatrix)var7;
   }

   public boolean create$default$6() {
      return false;
   }

   public DenseMatrix ones(final int rows, final int cols, final ClassTag evidence$4, final Zero evidence$5, final Semiring evidence$6) {
      Object data = evidence$4.newArray(rows * cols);
      if (rows * cols != 0 && !BoxesRunTime.equals(scala.runtime.ScalaRunTime..MODULE$.array_apply(data, 0), ((Semiring).MODULE$.implicitly(evidence$6)).one())) {
         ArrayUtil$.MODULE$.fill(data, 0, scala.runtime.ScalaRunTime..MODULE$.array_length(data), ((Semiring).MODULE$.implicitly(evidence$6)).one());
      }

      return this.create(rows, cols, data, evidence$5);
   }

   public DenseMatrix eye(final int dim, final ClassTag evidence$7, final Zero evidence$8, final Semiring evidence$9) {
      DenseMatrix r = this.zeros(dim, dim, evidence$7, evidence$8);
      ((NumericOps)diag$.MODULE$.diagDMDVImpl().apply(r)).$colon$eq(((Semiring).MODULE$.implicitly(evidence$9)).one(), HasOps$.MODULE$.impl_OpSet_InPlace_DV_V_Generic());
      return r;
   }

   public DenseMatrix horzcat(final Seq matrices, final scala..less.colon.less ev, final UFunc.InPlaceImpl2 opset, final ClassTag vman, final Zero zero) {
      DenseMatrix var10000;
      if (matrices.isEmpty()) {
         var10000 = this.zeros(0, 0, vman, zero);
      } else {
         boolean cond$macro$1 = matrices.forall((m) -> BoxesRunTime.boxToBoolean($anonfun$horzcat$1(ev, matrices, m)));
         if (!cond$macro$1) {
            throw new IllegalArgumentException("requirement failed: Not all matrices have the same number of rows: matrices.forall(((m: M) => ev.apply(m).rows.==(ev.apply(matrices.apply(0)).rows)))");
         }

         int numCols = BoxesRunTime.unboxToInt(matrices.foldLeft(BoxesRunTime.boxToInteger(0), (x$1, x$2) -> BoxesRunTime.boxToInteger($anonfun$horzcat$2(ev, BoxesRunTime.unboxToInt(x$1), x$2))));
         int numRows = ((Matrix)ev.apply(matrices.apply(0))).rows();
         DenseMatrix res = this.zeros(numRows, numCols, vman, zero);
         IntRef offset = IntRef.create(0);
         matrices.foreach((m) -> {
            $anonfun$horzcat$3(res, numRows, offset, ev, opset, m);
            return BoxedUnit.UNIT;
         });
         var10000 = res;
      }

      return var10000;
   }

   public DenseMatrix vertcat(final Seq matrices, final UFunc.InPlaceImpl2 opset, final ClassTag vman, final Zero zero) {
      DenseMatrix var10000;
      if (matrices.isEmpty()) {
         var10000 = this.zeros(0, 0, vman, zero);
      } else {
         boolean cond$macro$1 = matrices.forall((m) -> BoxesRunTime.boxToBoolean($anonfun$vertcat$1(matrices, m)));
         if (!cond$macro$1) {
            throw new IllegalArgumentException("requirement failed: Not all matrices have the same number of columns: matrices.forall(((m: breeze.linalg.DenseMatrix[V]) => m.cols.==(matrices.apply(0).cols)))");
         }

         int numRows = BoxesRunTime.unboxToInt(matrices.foldLeft(BoxesRunTime.boxToInteger(0), (x$3, x$4) -> BoxesRunTime.boxToInteger($anonfun$vertcat$2(BoxesRunTime.unboxToInt(x$3), x$4))));
         int numCols = ((DenseMatrix)matrices.apply(0)).cols();
         DenseMatrix res = this.zeros(numRows, numCols, vman, zero);
         IntRef offset = IntRef.create(0);
         matrices.foreach((m) -> {
            $anonfun$vertcat$3(res, offset, numCols, opset, m);
            return BoxedUnit.UNIT;
         });
         var10000 = res;
      }

      return var10000;
   }

   public ScalarOf scalarOf() {
      return ScalarOf$.MODULE$.dummy();
   }

   public CanCreateZerosLike canCreateZerosLike(final ClassTag evidence$10, final Zero evidence$11) {
      return new CanCreateZerosLike(evidence$10, evidence$11) {
         private final ClassTag evidence$10$1;
         private final Zero evidence$11$1;

         public DenseMatrix apply(final DenseMatrix v1) {
            return DenseMatrix$.MODULE$.zeros(v1.rows(), v1.cols(), this.evidence$10$1, this.evidence$11$1);
         }

         public {
            this.evidence$10$1 = evidence$10$1;
            this.evidence$11$1 = evidence$11$1;
         }
      };
   }

   public void breeze$linalg$DenseMatrix$$init() {
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(DenseMatrix$.class);
   }

   public DenseMatrix zeros$mDc$sp(final int rows, final int cols, final ClassTag evidence$1, final Zero evidence$2) {
      double[] data = (double[])evidence$1.newArray(rows * cols);
      if (.MODULE$.implicitly(evidence$2) != null && rows * cols != 0 && data[0] != ((Zero).MODULE$.implicitly(evidence$2)).zero$mcD$sp()) {
         ArrayUtil$.MODULE$.fill(data, 0, data.length, BoxesRunTime.boxToDouble(((Zero).MODULE$.implicitly(evidence$2)).zero$mcD$sp()));
      }

      return this.create$mDc$sp(rows, cols, data, evidence$2);
   }

   public DenseMatrix zeros$mFc$sp(final int rows, final int cols, final ClassTag evidence$1, final Zero evidence$2) {
      float[] data = (float[])evidence$1.newArray(rows * cols);
      if (.MODULE$.implicitly(evidence$2) != null && rows * cols != 0 && data[0] != ((Zero).MODULE$.implicitly(evidence$2)).zero$mcF$sp()) {
         ArrayUtil$.MODULE$.fill(data, 0, data.length, BoxesRunTime.boxToFloat(((Zero).MODULE$.implicitly(evidence$2)).zero$mcF$sp()));
      }

      return this.create$mFc$sp(rows, cols, data, evidence$2);
   }

   public DenseMatrix zeros$mIc$sp(final int rows, final int cols, final ClassTag evidence$1, final Zero evidence$2) {
      int[] data = (int[])evidence$1.newArray(rows * cols);
      if (.MODULE$.implicitly(evidence$2) != null && rows * cols != 0 && data[0] != ((Zero).MODULE$.implicitly(evidence$2)).zero$mcI$sp()) {
         ArrayUtil$.MODULE$.fill(data, 0, data.length, BoxesRunTime.boxToInteger(((Zero).MODULE$.implicitly(evidence$2)).zero$mcI$sp()));
      }

      return this.create$mIc$sp(rows, cols, data, evidence$2);
   }

   public DenseMatrix zeros$mJc$sp(final int rows, final int cols, final ClassTag evidence$1, final Zero evidence$2) {
      long[] data = (long[])evidence$1.newArray(rows * cols);
      if (.MODULE$.implicitly(evidence$2) != null && rows * cols != 0 && data[0] != ((Zero).MODULE$.implicitly(evidence$2)).zero$mcJ$sp()) {
         ArrayUtil$.MODULE$.fill(data, 0, data.length, BoxesRunTime.boxToLong(((Zero).MODULE$.implicitly(evidence$2)).zero$mcJ$sp()));
      }

      return this.create$mJc$sp(rows, cols, data, evidence$2);
   }

   public DenseMatrix create$mDc$sp(final int rows, final int cols, final double[] data, final Zero evidence$3) {
      return this.create$mDc$sp(rows, cols, data, 0, rows, false);
   }

   public DenseMatrix create$mFc$sp(final int rows, final int cols, final float[] data, final Zero evidence$3) {
      return this.create$mFc$sp(rows, cols, data, 0, rows, false);
   }

   public DenseMatrix create$mIc$sp(final int rows, final int cols, final int[] data, final Zero evidence$3) {
      return this.create$mIc$sp(rows, cols, data, 0, rows, false);
   }

   public DenseMatrix create$mJc$sp(final int rows, final int cols, final long[] data, final Zero evidence$3) {
      return this.create$mJc$sp(rows, cols, data, 0, rows, false);
   }

   public DenseMatrix create$mDc$sp(final int rows, final int cols, final double[] data, final int offset, final int majorStride, final boolean isTranspose) {
      Object var7;
      if (data instanceof double[]) {
         double[] var9 = data;
         var7 = new DenseMatrix$mcD$sp(rows, cols, var9, offset, majorStride, isTranspose);
      } else if (data instanceof float[]) {
         float[] var10 = (float[])data;
         var7 = new DenseMatrix$mcF$sp(rows, cols, var10, offset, majorStride, isTranspose);
      } else if (data instanceof long[]) {
         long[] var11 = (long[])data;
         var7 = new DenseMatrix$mcJ$sp(rows, cols, var11, offset, majorStride, isTranspose);
      } else if (data instanceof int[]) {
         int[] var12 = (int[])data;
         var7 = new DenseMatrix$mcI$sp(rows, cols, var12, offset, majorStride, isTranspose);
      } else {
         var7 = new DenseMatrix$mcD$sp(rows, cols, data, offset, majorStride, isTranspose);
      }

      return (DenseMatrix)var7;
   }

   public DenseMatrix create$mFc$sp(final int rows, final int cols, final float[] data, final int offset, final int majorStride, final boolean isTranspose) {
      Object var7;
      if (data instanceof double[]) {
         double[] var9 = (double[])data;
         var7 = new DenseMatrix$mcD$sp(rows, cols, var9, offset, majorStride, isTranspose);
      } else if (data instanceof float[]) {
         float[] var10 = data;
         var7 = new DenseMatrix$mcF$sp(rows, cols, var10, offset, majorStride, isTranspose);
      } else if (data instanceof long[]) {
         long[] var11 = (long[])data;
         var7 = new DenseMatrix$mcJ$sp(rows, cols, var11, offset, majorStride, isTranspose);
      } else if (data instanceof int[]) {
         int[] var12 = (int[])data;
         var7 = new DenseMatrix$mcI$sp(rows, cols, var12, offset, majorStride, isTranspose);
      } else {
         var7 = new DenseMatrix$mcF$sp(rows, cols, data, offset, majorStride, isTranspose);
      }

      return (DenseMatrix)var7;
   }

   public DenseMatrix create$mIc$sp(final int rows, final int cols, final int[] data, final int offset, final int majorStride, final boolean isTranspose) {
      Object var7;
      if (data instanceof double[]) {
         double[] var9 = (double[])data;
         var7 = new DenseMatrix$mcD$sp(rows, cols, var9, offset, majorStride, isTranspose);
      } else if (data instanceof float[]) {
         float[] var10 = (float[])data;
         var7 = new DenseMatrix$mcF$sp(rows, cols, var10, offset, majorStride, isTranspose);
      } else if (data instanceof long[]) {
         long[] var11 = (long[])data;
         var7 = new DenseMatrix$mcJ$sp(rows, cols, var11, offset, majorStride, isTranspose);
      } else if (data instanceof int[]) {
         int[] var12 = data;
         var7 = new DenseMatrix$mcI$sp(rows, cols, var12, offset, majorStride, isTranspose);
      } else {
         var7 = new DenseMatrix$mcI$sp(rows, cols, data, offset, majorStride, isTranspose);
      }

      return (DenseMatrix)var7;
   }

   public DenseMatrix create$mJc$sp(final int rows, final int cols, final long[] data, final int offset, final int majorStride, final boolean isTranspose) {
      Object var7;
      if (data instanceof double[]) {
         double[] var9 = (double[])data;
         var7 = new DenseMatrix$mcD$sp(rows, cols, var9, offset, majorStride, isTranspose);
      } else if (data instanceof float[]) {
         float[] var10 = (float[])data;
         var7 = new DenseMatrix$mcF$sp(rows, cols, var10, offset, majorStride, isTranspose);
      } else if (data instanceof long[]) {
         long[] var11 = data;
         var7 = new DenseMatrix$mcJ$sp(rows, cols, var11, offset, majorStride, isTranspose);
      } else if (data instanceof int[]) {
         int[] var12 = (int[])data;
         var7 = new DenseMatrix$mcI$sp(rows, cols, var12, offset, majorStride, isTranspose);
      } else {
         var7 = new DenseMatrix$mcJ$sp(rows, cols, data, offset, majorStride, isTranspose);
      }

      return (DenseMatrix)var7;
   }

   public DenseMatrix ones$mDc$sp(final int rows, final int cols, final ClassTag evidence$4, final Zero evidence$5, final Semiring evidence$6) {
      double[] data = (double[])evidence$4.newArray(rows * cols);
      if (rows * cols != 0 && data[0] != ((Semiring).MODULE$.implicitly(evidence$6)).one$mcD$sp()) {
         ArrayUtil$.MODULE$.fill(data, 0, data.length, BoxesRunTime.boxToDouble(((Semiring).MODULE$.implicitly(evidence$6)).one$mcD$sp()));
      }

      return this.create$mDc$sp(rows, cols, data, evidence$5);
   }

   public DenseMatrix ones$mFc$sp(final int rows, final int cols, final ClassTag evidence$4, final Zero evidence$5, final Semiring evidence$6) {
      float[] data = (float[])evidence$4.newArray(rows * cols);
      if (rows * cols != 0 && data[0] != ((Semiring).MODULE$.implicitly(evidence$6)).one$mcF$sp()) {
         ArrayUtil$.MODULE$.fill(data, 0, data.length, BoxesRunTime.boxToFloat(((Semiring).MODULE$.implicitly(evidence$6)).one$mcF$sp()));
      }

      return this.create$mFc$sp(rows, cols, data, evidence$5);
   }

   public DenseMatrix ones$mIc$sp(final int rows, final int cols, final ClassTag evidence$4, final Zero evidence$5, final Semiring evidence$6) {
      int[] data = (int[])evidence$4.newArray(rows * cols);
      if (rows * cols != 0 && data[0] != ((Semiring).MODULE$.implicitly(evidence$6)).one$mcI$sp()) {
         ArrayUtil$.MODULE$.fill(data, 0, data.length, BoxesRunTime.boxToInteger(((Semiring).MODULE$.implicitly(evidence$6)).one$mcI$sp()));
      }

      return this.create$mIc$sp(rows, cols, data, evidence$5);
   }

   public DenseMatrix ones$mJc$sp(final int rows, final int cols, final ClassTag evidence$4, final Zero evidence$5, final Semiring evidence$6) {
      long[] data = (long[])evidence$4.newArray(rows * cols);
      if (rows * cols != 0 && data[0] != ((Semiring).MODULE$.implicitly(evidence$6)).one$mcJ$sp()) {
         ArrayUtil$.MODULE$.fill(data, 0, data.length, BoxesRunTime.boxToLong(((Semiring).MODULE$.implicitly(evidence$6)).one$mcJ$sp()));
      }

      return this.create$mJc$sp(rows, cols, data, evidence$5);
   }

   public DenseMatrix eye$mDc$sp(final int dim, final ClassTag evidence$7, final Zero evidence$8, final Semiring evidence$9) {
      DenseMatrix r = this.zeros$mDc$sp(dim, dim, evidence$7, evidence$8);
      ((NumericOps)diag$.MODULE$.diagDMDVImpl().apply(r)).$colon$eq(BoxesRunTime.boxToDouble(((Semiring).MODULE$.implicitly(evidence$9)).one$mcD$sp()), HasOps$.MODULE$.impl_OpSet_InPlace_DV_V_Generic());
      return r;
   }

   public DenseMatrix eye$mFc$sp(final int dim, final ClassTag evidence$7, final Zero evidence$8, final Semiring evidence$9) {
      DenseMatrix r = this.zeros$mFc$sp(dim, dim, evidence$7, evidence$8);
      ((NumericOps)diag$.MODULE$.diagDMDVImpl().apply(r)).$colon$eq(BoxesRunTime.boxToFloat(((Semiring).MODULE$.implicitly(evidence$9)).one$mcF$sp()), HasOps$.MODULE$.impl_OpSet_InPlace_DV_V_Generic());
      return r;
   }

   public DenseMatrix eye$mIc$sp(final int dim, final ClassTag evidence$7, final Zero evidence$8, final Semiring evidence$9) {
      DenseMatrix r = this.zeros$mIc$sp(dim, dim, evidence$7, evidence$8);
      ((NumericOps)diag$.MODULE$.diagDMDVImpl().apply(r)).$colon$eq(BoxesRunTime.boxToInteger(((Semiring).MODULE$.implicitly(evidence$9)).one$mcI$sp()), HasOps$.MODULE$.impl_OpSet_InPlace_DV_V_Generic());
      return r;
   }

   public DenseMatrix eye$mJc$sp(final int dim, final ClassTag evidence$7, final Zero evidence$8, final Semiring evidence$9) {
      DenseMatrix r = this.zeros$mJc$sp(dim, dim, evidence$7, evidence$8);
      ((NumericOps)diag$.MODULE$.diagDMDVImpl().apply(r)).$colon$eq(BoxesRunTime.boxToLong(((Semiring).MODULE$.implicitly(evidence$9)).one$mcJ$sp()), HasOps$.MODULE$.impl_OpSet_InPlace_DV_V_Generic());
      return r;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$horzcat$1(final scala..less.colon.less ev$1, final Seq matrices$3, final Object m) {
      return ((Matrix)ev$1.apply(m)).rows() == ((Matrix)ev$1.apply(matrices$3.apply(0))).rows();
   }

   // $FF: synthetic method
   public static final int $anonfun$horzcat$2(final scala..less.colon.less ev$1, final int x$1, final Object x$2) {
      return x$1 + ((Matrix)ev$1.apply(x$2)).cols();
   }

   // $FF: synthetic method
   public static final void $anonfun$horzcat$3(final DenseMatrix res$1, final int numRows$1, final IntRef offset$1, final scala..less.colon.less ev$1, final UFunc.InPlaceImpl2 opset$1, final Object m) {
      ((NumericOps)res$1.apply(scala.runtime.RichInt..MODULE$.until$extension(.MODULE$.intWrapper(0), numRows$1), scala.runtime.RichInt..MODULE$.until$extension(.MODULE$.intWrapper(offset$1.elem), offset$1.elem + ((Matrix)ev$1.apply(m)).cols()), HasOps$.MODULE$.canSliceColsAndRows())).$colon$eq(m, opset$1);
      offset$1.elem += ((Matrix)ev$1.apply(m)).cols();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$vertcat$1(final Seq matrices$4, final DenseMatrix m) {
      return m.cols() == ((DenseMatrix)matrices$4.apply(0)).cols();
   }

   // $FF: synthetic method
   public static final int $anonfun$vertcat$2(final int x$3, final DenseMatrix x$4) {
      return x$3 + x$4.rows();
   }

   // $FF: synthetic method
   public static final void $anonfun$vertcat$3(final DenseMatrix res$2, final IntRef offset$2, final int numCols$1, final UFunc.InPlaceImpl2 opset$2, final DenseMatrix m) {
      ((NumericOps)res$2.apply(scala.runtime.RichInt..MODULE$.until$extension(.MODULE$.intWrapper(offset$2.elem), offset$2.elem + m.rows()), scala.runtime.RichInt..MODULE$.until$extension(.MODULE$.intWrapper(0), numCols$1), HasOps$.MODULE$.canSliceColsAndRows())).$colon$eq(m, opset$2);
      offset$2.elem += m.rows();
   }

   private DenseMatrix$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
