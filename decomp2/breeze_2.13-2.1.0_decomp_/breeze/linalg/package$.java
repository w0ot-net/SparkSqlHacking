package breeze.linalg;

import breeze.generic.UFunc;
import breeze.io.CSVReader$;
import breeze.io.CSVWriter$;
import breeze.io.FileStreams$;
import breeze.linalg.operators.HasOps$;
import breeze.linalg.support.CanCopy;
import breeze.math.Ring;
import breeze.math.Semiring;
import breeze.stats.mean$;
import breeze.stats.meanAndVariance$;
import breeze.stats.stddev$;
import breeze.stats.variance$;
import breeze.storage.Zero;
import breeze.storage.Zero$;
import dev.ludovic.netlib.blas.BLAS;
import dev.ludovic.netlib.blas.NativeBLAS;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.lang.invoke.SerializedLambda;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.collection.SeqOps;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.Range;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ClassTag.;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ObjectRef;
import scala.runtime.java8.JFunction1;
import scala.runtime.java8.JFunction2;

public final class package$ {
   public static final package$ MODULE$ = new package$();
   private static boolean usingNatives;
   private static final randomDouble$ rand;
   private static volatile boolean bitmap$0;

   static {
      rand = randomDouble$.MODULE$;
   }

   public void axpy(final Object a, final Object x, final Object y, final UFunc.InPlaceImpl3 axpy) {
      axpy.apply(y, a, x);
   }

   public DenseVector linspace(final double a, final double b, final int length) {
      double increment = (b - a) / (double)(length - 1);
      return DenseVector$.MODULE$.tabulate$mDc$sp(length, (JFunction1.mcDI.sp)(i) -> a + increment * (double)i, .MODULE$.Double());
   }

   public int linspace$default$3() {
      return 100;
   }

   public Object copy(final Object t, final CanCopy canCopy) {
      return canCopy.apply(t);
   }

   public package.String2File String2File(final String s) {
      return new package.String2File(s);
   }

   public DenseMatrix csvread(final File file, final char separator, final char quote, final char escape, final int skipLines) {
      FileReader input = new FileReader(file);
      ObjectRef mat = ObjectRef.create(CSVReader$.MODULE$.read(input, separator, quote, escape, skipLines));
      mat.elem = (IndexedSeq)((IndexedSeq)mat.elem).takeWhile((line) -> BoxesRunTime.boxToBoolean($anonfun$csvread$1(line)));
      input.close();
      return ((IndexedSeq)mat.elem).length() == 0 ? DenseMatrix$.MODULE$.zeros$mDc$sp(0, 0, .MODULE$.Double(), Zero$.MODULE$.DoubleZero()) : (DenseMatrix)DenseMatrix$.MODULE$.tabulate$mDc$sp(((IndexedSeq)mat.elem).length(), ((SeqOps)((IndexedSeq)mat.elem).head()).length(), (JFunction2.mcDII.sp)(i, j) -> scala.collection.StringOps..MODULE$.toDouble$extension(scala.Predef..MODULE$.augmentString((String)((SeqOps)((IndexedSeq)mat.elem).apply(i)).apply(j))), .MODULE$.Double(), Zero$.MODULE$.DoubleZero());
   }

   public char csvread$default$2() {
      return ',';
   }

   public char csvread$default$3() {
      return '"';
   }

   public char csvread$default$4() {
      return '\\';
   }

   public int csvread$default$5() {
      return 0;
   }

   public void csvwrite(final File file, final Matrix mat, final char separator, final char quote, final char escape, final int skipLines) {
      CSVWriter$.MODULE$.writeFile(file, (IndexedSeq)scala.package..MODULE$.IndexedSeq().tabulate(mat.rows(), mat.cols(), (x$1, x$2) -> $anonfun$csvwrite$1(mat, BoxesRunTime.unboxToInt(x$1), BoxesRunTime.unboxToInt(x$2))), separator, quote, escape);
   }

   public char csvwrite$default$3() {
      return ',';
   }

   public char csvwrite$default$4() {
      return '\u0000';
   }

   public char csvwrite$default$5() {
      return '\\';
   }

   public int csvwrite$default$6() {
      return 0;
   }

   public void mmwrite(final File file, final Matrix mat, final Numeric evidence$1) {
      if (mat.activeSize() == mat.size()) {
         PrintWriter out = new PrintWriter(FileStreams$.MODULE$.output(file));
         out.println("%%MatrixMarket matrix array real general");
         out.println((new StringBuilder(14)).append("% produced by ").append(this.getClass()).toString());
         out.println((new StringBuilder(1)).append(mat.rows()).append(" ").append(mat.cols()).toString());
         int index$macro$7 = 0;

         for(int limit$macro$9 = mat.cols(); index$macro$7 < limit$macro$9; ++index$macro$7) {
            int index$macro$2 = 0;

            for(int limit$macro$4 = mat.rows(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               ((j, i) -> out.println(mat.apply(i, j))).apply$mcVII$sp(index$macro$7, index$macro$2);
            }
         }

         out.close();
      } else {
         PrintWriter out = new PrintWriter(FileStreams$.MODULE$.output(file));
         out.println("%%MatrixMarket matrix coordinate real general");
         out.println((new StringBuilder(14)).append("% produced by ").append(this.getClass()).toString());
         out.println((new StringBuilder(2)).append(mat.rows()).append(" ").append(mat.cols()).append(" ").append(mat.activeSize()).toString());
         mat.activeIterator().foreach((x0$1) -> {
            $anonfun$mmwrite$2(out, x0$1);
            return BoxedUnit.UNIT;
         });
         out.close();
      }

   }

   public Range RangeToRangeExtender(final Range re) {
      return re;
   }

   public Object InjectNumericOps(final Object repr) {
      return repr;
   }

   public void requireNonEmptyMatrix(final Matrix mat) {
      if (mat.cols() == 0 || mat.rows() == 0) {
         throw new MatrixEmptyException();
      }
   }

   public void requireSquareMatrix(final Matrix mat) {
      if (mat.rows() != mat.cols()) {
         throw new MatrixNotSquareException();
      }
   }

   public void requireSymmetricMatrix(final Matrix mat, final double tol) {
      this.requireSquareMatrix(mat);
      scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), mat.rows()).foreach$mVc$sp((JFunction1.mcVI.sp)(i) -> scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), i).foreach$mVc$sp((JFunction1.mcVI.sp)(j) -> {
            if (!breeze.numerics.package$.MODULE$.closeTo(mat.apply$mcD$sp(i, j), mat.apply$mcD$sp(j, i), tol)) {
               throw new MatrixNotSymmetricException();
            }
         }));
   }

   public double requireSymmetricMatrix$default$2() {
      return 1.0E-7;
   }

   public DenseVector cross(final DenseVector a, final DenseVector b, final Ring ring, final ClassTag man) {
      int left$macro$1 = a.length();
      int right$macro$2 = 3;
      if (left$macro$1 != 3) {
         throw new IllegalArgumentException((new StringBuilder(51)).append("requirement failed: ").append("a.length == 3 (").append(left$macro$1).append(" ").append("!=").append(" ").append(3).append(")").toString());
      } else {
         int left$macro$3 = b.length();
         int right$macro$4 = 3;
         if (left$macro$3 != 3) {
            throw new IllegalArgumentException((new StringBuilder(51)).append("requirement failed: ").append("b.length == 3 (").append(left$macro$3).append(" ").append("!=").append(" ").append(3).append(")").toString());
         } else {
            return (DenseVector)DenseVector$.MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{ring.$minus(ring.$times(a.apply(1), b.apply(2)), ring.$times(a.apply(2), b.apply(1))), ring.$minus(ring.$times(a.apply(2), b.apply(0)), ring.$times(a.apply(0), b.apply(2))), ring.$minus(ring.$times(a.apply(0), b.apply(1)), ring.$times(a.apply(1), b.apply(0)))}), man);
         }
      }
   }

   public double[] ranks(final Vector x, final Ordering evidence$2) {
      Vector a = x;
      IndexedSeq as = (IndexedSeq)argsort$.MODULE$.apply(x, argsort$.MODULE$.argsortQuasiTensorWithOrdering(scala..less.colon.less..MODULE$.refl(), evidence$2));
      double[] rv = new double[as.length()];

      int numTiedValuesAtI;
      for(int i = 0; i < as.length(); i += numTiedValuesAtI) {
         for(numTiedValuesAtI = 1; i + numTiedValuesAtI < as.length() && BoxesRunTime.equals(a.apply(as.apply(i + numTiedValuesAtI)), a.apply(as.apply(i))); ++numTiedValuesAtI) {
         }

         double rank = (double)(1 + i) + (double)(numTiedValuesAtI - 1) / (double)2.0F;

         for(int j = 0; j < numTiedValuesAtI; ++j) {
            rv[BoxesRunTime.unboxToInt(as.apply(i + j))] = rank;
         }
      }

      return rv;
   }

   public DenseMatrix lowerTriangular(final Matrix X, final Semiring evidence$3, final ClassTag evidence$4, final Zero evidence$5) {
      int N = X.rows();
      return (DenseMatrix)DenseMatrix$.MODULE$.tabulate(N, N, (i, j) -> $anonfun$lowerTriangular$1(X, evidence$3, BoxesRunTime.unboxToInt(i), BoxesRunTime.unboxToInt(j)), evidence$4, evidence$5);
   }

   public DenseMatrix strictlyLowerTriangular(final Matrix X, final Semiring evidence$6, final ClassTag evidence$7, final Zero evidence$8) {
      int N = X.rows();
      return (DenseMatrix)DenseMatrix$.MODULE$.tabulate(N, N, (i, j) -> $anonfun$strictlyLowerTriangular$1(X, evidence$6, BoxesRunTime.unboxToInt(i), BoxesRunTime.unboxToInt(j)), evidence$7, evidence$8);
   }

   public DenseMatrix upperTriangular(final Matrix X, final Semiring evidence$9, final ClassTag evidence$10, final Zero evidence$11) {
      int N = X.rows();
      return (DenseMatrix)DenseMatrix$.MODULE$.tabulate(N, N, (i, j) -> $anonfun$upperTriangular$1(X, evidence$9, BoxesRunTime.unboxToInt(i), BoxesRunTime.unboxToInt(j)), evidence$10, evidence$11);
   }

   public DenseMatrix strictlyUpperTriangular(final Matrix X, final Semiring evidence$12, final ClassTag evidence$13, final Zero evidence$14) {
      int N = X.rows();
      return (DenseMatrix)DenseMatrix$.MODULE$.tabulate(N, N, (i, j) -> $anonfun$strictlyUpperTriangular$1(X, evidence$12, BoxesRunTime.unboxToInt(i), BoxesRunTime.unboxToInt(j)), evidence$13, evidence$14);
   }

   public PCA princomp(final DenseMatrix x, final Option covmatOpt) {
      PCA var3;
      if (covmatOpt instanceof Some) {
         Some var5 = (Some)covmatOpt;
         DenseMatrix covmat = (DenseMatrix)var5.value();
         var3 = new PCA(x, covmat);
      } else {
         if (!scala.None..MODULE$.equals(covmatOpt)) {
            throw new MatchError(covmatOpt);
         }

         var3 = new PCA(x, this.cov(x, this.cov$default$2()));
      }

      return var3;
   }

   public Option princomp$default$2() {
      return scala.None..MODULE$;
   }

   public DenseMatrix scale(final DenseMatrix x, final boolean center, final boolean scale) {
      DenseMatrix var10000;
      if (center) {
         DenseMatrix xc = (DenseMatrix)((ImmutableNumericOps)x.apply($times$.MODULE$, scala.package..MODULE$.$colon$colon(), Broadcaster$.MODULE$.canBroadcastRows(HasOps$.MODULE$.handholdCanMapCols_DM()))).$minus(((ImmutableNumericOps)mean$.MODULE$.apply(x, Axis._0$.MODULE$, Axis$.MODULE$.collapseUred(HasOps$.MODULE$.handholdCanMapRows_DM(), mean$.MODULE$.reduce_Double(HasOps$.MODULE$.DV_canIterateValues()), HasOps$.MODULE$.canCollapseRows_DM(.MODULE$.Double())))).t(HasOps$.MODULE$.canUntranspose()), HasOps$.MODULE$.broadcastOp2_BRows(HasOps$.MODULE$.handholdCanMapCols_DM(), HasOps$.MODULE$.impl_OpSub_DV_DV_eq_DV_Double(), HasOps$.MODULE$.canMapCols_DM(.MODULE$.Double(), Zero$.MODULE$.DoubleZero(), HasOps$.MODULE$.impl_Op_InPlace_DV_DV_Double_OpSet())));
         var10000 = scale ? (DenseMatrix)((ImmutableNumericOps)xc.apply($times$.MODULE$, scala.package..MODULE$.$colon$colon(), Broadcaster$.MODULE$.canBroadcastRows(HasOps$.MODULE$.handholdCanMapCols_DM()))).$div$colon$div(((ImmutableNumericOps)stddev$.MODULE$.apply(x.apply(scala.package..MODULE$.$colon$colon(), $times$.MODULE$, Broadcaster$.MODULE$.canBroadcastColumns(HasOps$.MODULE$.handholdCanMapRows_DM())), HasOps$.MODULE$.broadcastOp_BCols(HasOps$.MODULE$.handholdCanMapRows_DM(), stddev$.MODULE$.reduceDouble(variance$.MODULE$.reduceDouble(meanAndVariance$.MODULE$.reduce_Double(HasOps$.MODULE$.DV_canIterateValues()))), HasOps$.MODULE$.canCollapseRows_DM(.MODULE$.Double())))).t(HasOps$.MODULE$.canUntranspose()), HasOps$.MODULE$.broadcastOp2_BRows(HasOps$.MODULE$.handholdCanMapCols_DM(), HasOps$.MODULE$.impl_Op_DV_DV_eq_DV_Double_OpDiv(), HasOps$.MODULE$.canMapCols_DM(.MODULE$.Double(), Zero$.MODULE$.DoubleZero(), HasOps$.MODULE$.impl_Op_InPlace_DV_DV_Double_OpSet()))) : xc;
      } else {
         var10000 = scale ? (DenseMatrix)((ImmutableNumericOps)x.apply($times$.MODULE$, scala.package..MODULE$.$colon$colon(), Broadcaster$.MODULE$.canBroadcastRows(HasOps$.MODULE$.handholdCanMapCols_DM()))).$div$colon$div(this.columnRMS(x), HasOps$.MODULE$.broadcastOp2_BRows(HasOps$.MODULE$.handholdCanMapCols_DM(), HasOps$.MODULE$.impl_Op_DV_DV_eq_DV_Double_OpDiv(), HasOps$.MODULE$.canMapCols_DM(.MODULE$.Double(), Zero$.MODULE$.DoubleZero(), HasOps$.MODULE$.impl_Op_InPlace_DV_DV_Double_OpSet()))) : x;
      }

      return var10000;
   }

   public boolean scale$default$2() {
      return true;
   }

   public boolean scale$default$3() {
      return false;
   }

   public DenseMatrix cov(final DenseMatrix x, final boolean center) {
      DenseMatrix xc = this.scale(x, center, false);
      return (DenseMatrix)((NumericOps)((ImmutableNumericOps)xc.t(HasOps$.MODULE$.canTranspose_DM())).$times(xc, HasOps$.MODULE$.impl_OpMulMatrix_DMD_DMD_eq_DMD())).$div$eq(BoxesRunTime.boxToDouble((double)xc.rows() - (double)1.0F), HasOps$.MODULE$.dm_s_UpdateOp_Double_OpDiv());
   }

   public boolean cov$default$2() {
      return true;
   }

   public DenseVector padRight(final DenseVector v, final Options.Dimensions1 dimensions, final CanPadRight canPad) {
      return (DenseVector)canPad.apply(v, dimensions, Options.Zero$.MODULE$);
   }

   public DenseVector padRight(final DenseVector v, final Options.Dimensions1 dimensions, final Options.OptPadMode mode, final CanPadRight canPad) {
      return (DenseVector)canPad.apply(v, dimensions, mode);
   }

   public DenseMatrix padRight(final DenseMatrix v, final Options.Dimensions1 dimensions, final CanPadRight canPad) {
      return (DenseMatrix)canPad.apply(v, dimensions, Options.Zero$.MODULE$);
   }

   public DenseMatrix padRight(final DenseMatrix v, final Options.Dimensions2 dimensions, final Options.OptPadMode mode, final CanPadRight canPad) {
      return (DenseMatrix)canPad.apply(v, dimensions, mode);
   }

   public DenseVector padLeft(final DenseVector v, final Options.Dimensions1 dimensions, final CanPadLeft canPad) {
      return (DenseVector)canPad.apply(v, dimensions, Options.Zero$.MODULE$);
   }

   public DenseVector padLeft(final DenseVector v, final Options.Dimensions1 dimensions, final Options.OptPadMode mode, final CanPadLeft canPad) {
      return (DenseVector)canPad.apply(v, dimensions, mode);
   }

   public DenseMatrix padLeft(final DenseMatrix v, final Options.Dimensions1 dimensions, final CanPadLeft canPad) {
      return (DenseMatrix)canPad.apply(v, dimensions, Options.Zero$.MODULE$);
   }

   public DenseMatrix padLeft(final DenseMatrix v, final Options.Dimensions2 dimensions, final Options.OptPadMode mode, final CanPadLeft canPad) {
      return (DenseMatrix)canPad.apply(v, dimensions, mode);
   }

   private DenseVector columnRMS(final DenseMatrix x) {
      return (DenseVector)((VectorLike)((ImmutableNumericOps)((ImmutableNumericOps)sum$.MODULE$.apply(x.$times$colon$times(x, HasOps$.MODULE$.op_DM_DM_Double_OpMulScalar()), Axis._0$.MODULE$, Axis$.MODULE$.collapseUred(HasOps$.MODULE$.handholdCanMapRows_DM(), sum$.MODULE$.reduce_Double(HasOps$.MODULE$.DV_canIterateValues()), HasOps$.MODULE$.canCollapseRows_DM(.MODULE$.Double())))).$div(BoxesRunTime.boxToDouble((double)x.rows() - (double)1.0F), HasOps$.MODULE$.impl_Op_Tt_S_eq_RT_from_T_S(DenseVector$.MODULE$.DV_scalarOf(), HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Double_OpDiv(), HasOps$.MODULE$.transposeTensor(scala..less.colon.less..MODULE$.refl())))).t(HasOps$.MODULE$.canUntranspose())).map$mcD$sp((JFunction1.mcDD.sp)(xx) -> scala.math.package..MODULE$.sqrt(xx), DenseVector$.MODULE$.DV_canMapValues$mDDc$sp(.MODULE$.Double()));
   }

   public randomDouble$ rand() {
      return rand;
   }

   private boolean usingNatives$lzycompute() {
      synchronized(this){}

      try {
         if (!bitmap$0) {
            usingNatives = BLAS.getInstance() instanceof NativeBLAS;
            bitmap$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return usingNatives;
   }

   public boolean usingNatives() {
      return !bitmap$0 ? this.usingNatives$lzycompute() : usingNatives;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$csvread$1(final IndexedSeq line) {
      return line.length() != 0 && scala.collection.StringOps..MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString((String)line.head()));
   }

   // $FF: synthetic method
   public static final String $anonfun$csvwrite$1(final Matrix mat$2, final int x$1, final int x$2) {
      return Double.toString(mat$2.apply$mcD$sp(x$1, x$2));
   }

   // $FF: synthetic method
   public static final void $anonfun$mmwrite$2(final PrintWriter out$2, final Tuple2 x0$1) {
      if (x0$1 != null) {
         Tuple2 var4 = (Tuple2)x0$1._1();
         Object v = x0$1._2();
         if (var4 != null) {
            int i = var4._1$mcI$sp();
            int j = var4._2$mcI$sp();
            out$2.println((new StringBuilder(2)).append(i + 1).append(" ").append(j + 1).append(" ").append(v).toString());
            BoxedUnit var2 = BoxedUnit.UNIT;
            return;
         }
      }

      throw new MatchError(x0$1);
   }

   // $FF: synthetic method
   public static final Object $anonfun$lowerTriangular$1(final Matrix X$1, final Semiring evidence$3$1, final int i, final int j) {
      return j <= i ? X$1.apply(i, j) : ((Semiring)scala.Predef..MODULE$.implicitly(evidence$3$1)).zero();
   }

   // $FF: synthetic method
   public static final Object $anonfun$strictlyLowerTriangular$1(final Matrix X$2, final Semiring evidence$6$1, final int i, final int j) {
      return j < i ? X$2.apply(i, j) : ((Semiring)scala.Predef..MODULE$.implicitly(evidence$6$1)).zero();
   }

   // $FF: synthetic method
   public static final Object $anonfun$upperTriangular$1(final Matrix X$3, final Semiring evidence$9$1, final int i, final int j) {
      return j >= i ? X$3.apply(i, j) : ((Semiring)scala.Predef..MODULE$.implicitly(evidence$9$1)).zero();
   }

   // $FF: synthetic method
   public static final Object $anonfun$strictlyUpperTriangular$1(final Matrix X$4, final Semiring evidence$12$1, final int i, final int j) {
      return j > i ? X$4.apply(i, j) : ((Semiring)scala.Predef..MODULE$.implicitly(evidence$12$1)).zero();
   }

   private package$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
