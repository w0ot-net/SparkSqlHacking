package breeze.optimize;

import breeze.generic.UFunc;
import breeze.linalg.DenseMatrix;
import breeze.linalg.DenseMatrix$;
import breeze.linalg.DenseVector;
import breeze.linalg.ImmutableNumericOps;
import breeze.linalg.NumericOps;
import breeze.linalg.operators.HasOps$;
import breeze.linalg.support.CanCopy;
import breeze.math.VectorSpace;
import breeze.storage.Zero$;
import java.lang.invoke.SerializedLambda;
import scala.reflect.ClassTag.;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction1;

public final class EmpiricalHessian$ {
   public static final EmpiricalHessian$ MODULE$ = new EmpiricalHessian$();

   public double $lessinit$greater$default$4() {
      return 1.0E-5;
   }

   public UFunc.UImpl2 product() {
      return new UFunc.UImpl2() {
         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public Object apply(final EmpiricalHessian a, final Object b) {
            return a.$times(b);
         }
      };
   }

   public DenseMatrix hessian(final DiffFunction df, final DenseVector x, final double eps, final VectorSpace vs, final CanCopy copy) {
      int n = x.length();
      DenseMatrix H = DenseMatrix$.MODULE$.zeros$mDc$sp(n, n, .MODULE$.Double(), Zero$.MODULE$.DoubleZero());
      DenseVector xx = (DenseVector)copy.apply(x);
      scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), n).foreach$mVc$sp((JFunction1.mcVI.sp)(i) -> {
         xx.update$mcD$sp(i, x.apply$mcD$sp(i) + eps);
         DenseVector df1 = (DenseVector)df.gradientAt(xx);
         xx.update$mcD$sp(i, x.apply$mcD$sp(i) - eps);
         DenseVector df2 = (DenseVector)df.gradientAt(xx);
         DenseVector gradient = (DenseVector)((ImmutableNumericOps)df1.$minus(df2, vs.subVV())).$div(BoxesRunTime.boxToDouble((double)2 * eps), vs.divVS());
         ((NumericOps)H.apply(BoxesRunTime.boxToInteger(i), scala.package..MODULE$.$colon$colon(), HasOps$.MODULE$.canSliceRow())).$colon$eq(gradient.t(HasOps$.MODULE$.transposeTensor(scala..less.colon.less..MODULE$.refl())), HasOps$.MODULE$.liftInPlaceOps(breeze.gymnastics.NotGiven..MODULE$.neq(), HasOps$.MODULE$.canUntranspose(), HasOps$.MODULE$.impl_Op_InPlace_DV_DV_Double_OpSet()));
         xx.update$mcD$sp(i, x.apply$mcD$sp(i));
      });
      scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), n).foreach$mVc$sp((JFunction1.mcVI.sp)(i) -> scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), i).foreach$mVc$sp((JFunction1.mcVI.sp)(j) -> {
            double tmp = (H.apply$mcD$sp(i, j) + H.apply$mcD$sp(j, i)) * (double)0.5F;
            H.update$mcD$sp(i, j, tmp);
            H.update$mcD$sp(j, i, tmp);
         }));
      return H;
   }

   public double hessian$default$3() {
      return 1.0E-5;
   }

   private EmpiricalHessian$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
