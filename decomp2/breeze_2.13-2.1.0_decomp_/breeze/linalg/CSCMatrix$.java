package breeze.linalg;

import breeze.generic.UFunc;
import breeze.linalg.support.CanCreateZeros;
import breeze.linalg.support.LiteralRow;
import breeze.math.Semiring;
import breeze.stats.distributions.Rand;
import breeze.storage.Zero;
import breeze.util.ReflectionUtil$;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Function0;
import scala.Function2;
import scala.Tuple2;
import scala.Array.;
import scala.collection.immutable.Range;
import scala.collection.immutable.Seq;
import scala.reflect.ClassTag;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.java8.JFunction1;

public final class CSCMatrix$ implements MatrixConstructors, Serializable {
   public static final CSCMatrix$ MODULE$ = new CSCMatrix$();

   static {
      MatrixConstructors.$init$(MODULE$);
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

   public CSCMatrix zeros(final int rows, final int cols, final int initialNonzero, final ClassTag evidence$3, final Zero evidence$4) {
      return new CSCMatrix(evidence$3.newArray(initialNonzero), rows, cols, new int[cols + 1], 0, new int[initialNonzero], evidence$4);
   }

   public CSCMatrix zeros(final int rows, final int cols, final ClassTag evidence$5, final Zero evidence$6) {
      return this.zeros(rows, cols, 0, evidence$5, evidence$6);
   }

   public CSCMatrix create(final int rows, final int cols, final Object data, final Zero evidence$7) {
      ClassTag man = ReflectionUtil$.MODULE$.elemClassTagFromArray(data);
      return new CSCMatrix(data, rows, cols, (int[]).MODULE$.tabulate(cols + 1, (JFunction1.mcII.sp)(x$1) -> x$1 * rows, scala.reflect.ClassTag..MODULE$.Int()), (int[])scala.collection.ArrayOps..MODULE$.flatten$extension(scala.Predef..MODULE$.refArrayOps(.MODULE$.fill(cols, () -> scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), rows), scala.reflect.ClassTag..MODULE$.apply(Range.class))), scala.Predef..MODULE$.$conforms(), scala.reflect.ClassTag..MODULE$.Int()), evidence$7);
   }

   public UFunc.UImpl canDim() {
      return new UFunc.UImpl() {
         public double apply$mcDD$sp(final double v) {
            return UFunc.UImpl.apply$mcDD$sp$(this, v);
         }

         public float apply$mcDF$sp(final double v) {
            return UFunc.UImpl.apply$mcDF$sp$(this, v);
         }

         public int apply$mcDI$sp(final double v) {
            return UFunc.UImpl.apply$mcDI$sp$(this, v);
         }

         public double apply$mcFD$sp(final float v) {
            return UFunc.UImpl.apply$mcFD$sp$(this, v);
         }

         public float apply$mcFF$sp(final float v) {
            return UFunc.UImpl.apply$mcFF$sp$(this, v);
         }

         public int apply$mcFI$sp(final float v) {
            return UFunc.UImpl.apply$mcFI$sp$(this, v);
         }

         public double apply$mcID$sp(final int v) {
            return UFunc.UImpl.apply$mcID$sp$(this, v);
         }

         public float apply$mcIF$sp(final int v) {
            return UFunc.UImpl.apply$mcIF$sp$(this, v);
         }

         public int apply$mcII$sp(final int v) {
            return UFunc.UImpl.apply$mcII$sp$(this, v);
         }

         public Tuple2 apply(final CSCMatrix v) {
            return new Tuple2.mcII.sp(v.rows(), v.cols());
         }
      };
   }

   public void breeze$linalg$CSCMatrix$$init() {
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(CSCMatrix$.class);
   }

   public CSCMatrix zeros$mDc$sp(final int rows, final int cols, final int initialNonzero, final ClassTag evidence$3, final Zero evidence$4) {
      return new CSCMatrix$mcD$sp((double[])evidence$3.newArray(initialNonzero), rows, cols, new int[cols + 1], 0, new int[initialNonzero], evidence$4);
   }

   public CSCMatrix zeros$mFc$sp(final int rows, final int cols, final int initialNonzero, final ClassTag evidence$3, final Zero evidence$4) {
      return new CSCMatrix$mcF$sp((float[])evidence$3.newArray(initialNonzero), rows, cols, new int[cols + 1], 0, new int[initialNonzero], evidence$4);
   }

   public CSCMatrix zeros$mIc$sp(final int rows, final int cols, final int initialNonzero, final ClassTag evidence$3, final Zero evidence$4) {
      return new CSCMatrix$mcI$sp((int[])evidence$3.newArray(initialNonzero), rows, cols, new int[cols + 1], 0, new int[initialNonzero], evidence$4);
   }

   public CSCMatrix zeros$mDc$sp(final int rows, final int cols, final ClassTag evidence$5, final Zero evidence$6) {
      return this.zeros$mDc$sp(rows, cols, 0, evidence$5, evidence$6);
   }

   public CSCMatrix zeros$mFc$sp(final int rows, final int cols, final ClassTag evidence$5, final Zero evidence$6) {
      return this.zeros$mFc$sp(rows, cols, 0, evidence$5, evidence$6);
   }

   public CSCMatrix zeros$mIc$sp(final int rows, final int cols, final ClassTag evidence$5, final Zero evidence$6) {
      return this.zeros$mIc$sp(rows, cols, 0, evidence$5, evidence$6);
   }

   public CSCMatrix zeros$mJc$sp(final int rows, final int cols, final ClassTag evidence$5, final Zero evidence$6) {
      return this.zeros(rows, cols, 0, evidence$5, evidence$6);
   }

   public CSCMatrix create$mDc$sp(final int rows, final int cols, final double[] data, final Zero evidence$7) {
      ClassTag man = ReflectionUtil$.MODULE$.elemClassTagFromArray(data);
      return new CSCMatrix$mcD$sp(data, rows, cols, (int[]).MODULE$.tabulate(cols + 1, (JFunction1.mcII.sp)(x$1) -> x$1 * rows, scala.reflect.ClassTag..MODULE$.Int()), (int[])scala.collection.ArrayOps..MODULE$.flatten$extension(scala.Predef..MODULE$.refArrayOps(.MODULE$.fill(cols, () -> scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), rows), scala.reflect.ClassTag..MODULE$.apply(Range.class))), scala.Predef..MODULE$.$conforms(), scala.reflect.ClassTag..MODULE$.Int()), evidence$7);
   }

   public CSCMatrix create$mFc$sp(final int rows, final int cols, final float[] data, final Zero evidence$7) {
      ClassTag man = ReflectionUtil$.MODULE$.elemClassTagFromArray(data);
      return new CSCMatrix$mcF$sp(data, rows, cols, (int[]).MODULE$.tabulate(cols + 1, (JFunction1.mcII.sp)(x$1) -> x$1 * rows, scala.reflect.ClassTag..MODULE$.Int()), (int[])scala.collection.ArrayOps..MODULE$.flatten$extension(scala.Predef..MODULE$.refArrayOps(.MODULE$.fill(cols, () -> scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), rows), scala.reflect.ClassTag..MODULE$.apply(Range.class))), scala.Predef..MODULE$.$conforms(), scala.reflect.ClassTag..MODULE$.Int()), evidence$7);
   }

   public CSCMatrix create$mIc$sp(final int rows, final int cols, final int[] data, final Zero evidence$7) {
      ClassTag man = ReflectionUtil$.MODULE$.elemClassTagFromArray(data);
      return new CSCMatrix$mcI$sp(data, rows, cols, (int[]).MODULE$.tabulate(cols + 1, (JFunction1.mcII.sp)(x$1) -> x$1 * rows, scala.reflect.ClassTag..MODULE$.Int()), (int[])scala.collection.ArrayOps..MODULE$.flatten$extension(scala.Predef..MODULE$.refArrayOps(.MODULE$.fill(cols, () -> scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), rows), scala.reflect.ClassTag..MODULE$.apply(Range.class))), scala.Predef..MODULE$.$conforms(), scala.reflect.ClassTag..MODULE$.Int()), evidence$7);
   }

   public CSCMatrix create$mJc$sp(final int rows, final int cols, final long[] data, final Zero evidence$7) {
      ClassTag man = ReflectionUtil$.MODULE$.elemClassTagFromArray(data);
      return new CSCMatrix$mcJ$sp(data, rows, cols, (int[]).MODULE$.tabulate(cols + 1, (JFunction1.mcII.sp)(x$1) -> x$1 * rows, scala.reflect.ClassTag..MODULE$.Int()), (int[])scala.collection.ArrayOps..MODULE$.flatten$extension(scala.Predef..MODULE$.refArrayOps(.MODULE$.fill(cols, () -> scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), rows), scala.reflect.ClassTag..MODULE$.apply(Range.class))), scala.Predef..MODULE$.$conforms(), scala.reflect.ClassTag..MODULE$.Int()), evidence$7);
   }

   private CSCMatrix$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
