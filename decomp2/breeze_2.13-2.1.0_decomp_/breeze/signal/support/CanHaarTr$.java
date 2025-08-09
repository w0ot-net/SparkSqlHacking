package breeze.signal.support;

import breeze.linalg.DenseMatrix;
import breeze.linalg.DenseMatrix$;
import breeze.linalg.DenseVector;
import breeze.linalg.DenseVector$mcD$sp;
import breeze.linalg.operators.HasOps$;
import breeze.storage.Zero$;
import java.lang.invoke.SerializedLambda;
import scala.Predef;
import scala.collection.ArrayOps;
import scala.collection.ArrayOps.;
import scala.collection.immutable.List;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction1;

public final class CanHaarTr$ {
   public static final CanHaarTr$ MODULE$ = new CanHaarTr$();
   private static final double breeze$signal$support$CanHaarTr$$nFactor = (double)1.0F / Math.sqrt((double)2.0F);
   private static final CanHaarTr dvDouble1FHT = new CanHaarTr() {
      public DenseVector apply(final DenseVector v) {
         return _fht$1(CanHaarTr$.MODULE$.breeze$signal$support$CanHaarTr$$padOrCopy(v));
      }

      // $FF: synthetic method
      public static final double $anonfun$apply$1(final double[] e) {
         return (e[0] + e[1]) * CanHaarTr$.MODULE$.breeze$signal$support$CanHaarTr$$nFactor();
      }

      // $FF: synthetic method
      public static final double $anonfun$apply$2(final double[] e) {
         return (e[0] - e[1]) * CanHaarTr$.MODULE$.breeze$signal$support$CanHaarTr$$nFactor();
      }

      private static final DenseVector _fht$1(final DenseVector v) {
         if (v.length() > 1) {
            List p = .MODULE$.grouped$extension(scala.Predef..MODULE$.doubleArrayOps(v.toArray$mcD$sp(scala.reflect.ClassTag..MODULE$.Double())), 2).toList();
            v.slice$mcD$sp(0, v.length() / 2, v.slice$default$3()).$colon$eq(_fht$1(new DenseVector$mcD$sp((double[])p.map((e) -> BoxesRunTime.boxToDouble($anonfun$apply$1(e))).toArray(scala.reflect.ClassTag..MODULE$.Double()))), HasOps$.MODULE$.impl_Op_InPlace_DV_DV_Double_OpSet());
            v.slice$mcD$sp(v.length() / 2, v.length(), v.slice$default$3()).$colon$eq(new DenseVector$mcD$sp((double[])p.map((e) -> BoxesRunTime.boxToDouble($anonfun$apply$2(e))).toArray(scala.reflect.ClassTag..MODULE$.Double())), HasOps$.MODULE$.impl_Op_InPlace_DV_DV_Double_OpSet());
         } else {
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }

         return v;
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   };
   private static final CanHaarTr dmDouble1FHT = new CanHaarTr() {
      public DenseMatrix apply(final DenseMatrix m) {
         DenseMatrix v = CanHaarTr$.MODULE$.breeze$signal$support$CanHaarTr$$squareMatrix(m);
         this._fht$2(v, v.rows());
         return v;
      }

      // $FF: synthetic method
      public static final double $anonfun$apply$4(final double[] e) {
         return (e[0] + e[1]) * CanHaarTr$.MODULE$.breeze$signal$support$CanHaarTr$$nFactor();
      }

      // $FF: synthetic method
      public static final double $anonfun$apply$5(final double[] e) {
         return (e[0] - e[1]) * CanHaarTr$.MODULE$.breeze$signal$support$CanHaarTr$$nFactor();
      }

      // $FF: synthetic method
      public static final double $anonfun$apply$8(final double[] e) {
         return (e[0] + e[1]) * CanHaarTr$.MODULE$.breeze$signal$support$CanHaarTr$$nFactor();
      }

      // $FF: synthetic method
      public static final double $anonfun$apply$9(final double[] e) {
         return (e[0] - e[1]) * CanHaarTr$.MODULE$.breeze$signal$support$CanHaarTr$$nFactor();
      }

      private final void _fht$2(final DenseMatrix m, final int limit) {
         while(limit > 1) {
            scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), limit).foreach$mVc$sp((JFunction1.mcVI.sp)(c) -> {
               ArrayOps var10000 = .MODULE$;
               Predef var10001 = scala.Predef..MODULE$;
               DenseVector qual$1 = (DenseVector)m.apply(scala.package..MODULE$.$colon$colon(), BoxesRunTime.boxToInteger(c), HasOps$.MODULE$.canSliceCol());
               int x$1 = 0;
               int x$3 = qual$1.slice$default$3();
               double[][] p = (double[][])var10000.grouped$extension(var10001.doubleArrayOps(qual$1.slice$mcD$sp(0, limit, x$3).toArray$mcD$sp(scala.reflect.ClassTag..MODULE$.Double())), 2).toArray(scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Double.TYPE)));
               double[] v = (double[]).MODULE$.$plus$plus$extension(scala.Predef..MODULE$.doubleArrayOps((double[]).MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])p), (e) -> BoxesRunTime.boxToDouble($anonfun$apply$4(e)), scala.reflect.ClassTag..MODULE$.Double())), .MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])p), (e) -> BoxesRunTime.boxToDouble($anonfun$apply$5(e)), scala.reflect.ClassTag..MODULE$.Double()), scala.reflect.ClassTag..MODULE$.Double());
               scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), limit).foreach$mVc$sp((JFunction1.mcVI.sp)(r) -> m.update$mcD$sp(r, c, v[r]));
            });
            scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), limit).foreach$mVc$sp((JFunction1.mcVI.sp)(r) -> {
               double[][] p = (double[][]).MODULE$.grouped$extension(scala.Predef..MODULE$.doubleArrayOps(((DenseVector)m.t(scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), limit), BoxesRunTime.boxToInteger(r), HasOps$.MODULE$.canTranspose_DM(), HasOps$.MODULE$.canSlicePartOfCol())).toArray$mcD$sp(scala.reflect.ClassTag..MODULE$.Double())), 2).toArray(scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Double.TYPE)));
               double[] v = (double[]).MODULE$.$plus$plus$extension(scala.Predef..MODULE$.doubleArrayOps((double[]).MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])p), (e) -> BoxesRunTime.boxToDouble($anonfun$apply$8(e)), scala.reflect.ClassTag..MODULE$.Double())), .MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])p), (e) -> BoxesRunTime.boxToDouble($anonfun$apply$9(e)), scala.reflect.ClassTag..MODULE$.Double()), scala.reflect.ClassTag..MODULE$.Double());
               scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), limit).foreach$mVc$sp((JFunction1.mcVI.sp)(c) -> m.update$mcD$sp(r, c, v[c]));
            });
            limit /= 2;
            m = m;
         }

         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   };

   public double breeze$signal$support$CanHaarTr$$nFactor() {
      return breeze$signal$support$CanHaarTr$$nFactor;
   }

   public DenseVector breeze$signal$support$CanHaarTr$$padOrCopy(final DenseVector v) {
      Object var10000;
      if ((v.length() & -v.length()) == v.length()) {
         var10000 = v.copy$mcD$sp();
      } else {
         int length = 1 << 32 - Integer.numberOfLeadingZeros(v.length());
         DenseVector r = new DenseVector$mcD$sp(new double[length]);
         r.slice$mcD$sp(0, v.length(), r.slice$default$3()).$colon$eq(v, HasOps$.MODULE$.impl_Op_InPlace_DV_DV_Double_OpSet());
         var10000 = r;
      }

      return (DenseVector)var10000;
   }

   public DenseMatrix breeze$signal$support$CanHaarTr$$squareMatrix(final DenseMatrix m) {
      int maxd = Math.max(m.rows(), m.cols());
      int rows = (maxd & -maxd) == maxd ? maxd : 1 << 32 - Integer.numberOfLeadingZeros(Math.max(m.rows(), m.cols()));
      DenseMatrix o = DenseMatrix$.MODULE$.zeros$mDc$sp(rows, rows, scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());
      scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), m.rows()).foreach$mVc$sp((JFunction1.mcVI.sp)(r) -> scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), m.cols()).foreach$mVc$sp((JFunction1.mcVI.sp)(c) -> o.update$mcD$sp(r, c, m.apply$mcD$sp(r, c))));
      return o;
   }

   private DenseVector denseMatrixDToVector(final DenseMatrix m) {
      double[] v = new double[m.size()];
      scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), m.rows()).foreach$mVc$sp((JFunction1.mcVI.sp)(r) -> scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), m.cols()).foreach$mVc$sp((JFunction1.mcVI.sp)(c) -> {
            int i = r * m.cols() + c;
            if (i < v.length) {
               v[i] = m.apply$mcD$sp(r, c);
            }

         }));
      return new DenseVector$mcD$sp(v);
   }

   private DenseMatrix denseVectorDToMatrix(final DenseVector v, final int rows, final int cols) {
      DenseMatrix m = DenseMatrix$.MODULE$.zeros$mDc$sp(rows, cols, scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());
      scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), m.rows()).foreach$mVc$sp((JFunction1.mcVI.sp)(r) -> scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), m.cols()).foreach$mVc$sp((JFunction1.mcVI.sp)(c) -> m.update$mcD$sp(r, c, v.apply$mcD$sp(r * cols + c))));
      return m;
   }

   public CanHaarTr dvDouble1FHT() {
      return dvDouble1FHT;
   }

   public CanHaarTr dmDouble1FHT() {
      return dmDouble1FHT;
   }

   private CanHaarTr$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
