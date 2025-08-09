package breeze.linalg;

import breeze.generic.UFunc;
import breeze.linalg.operators.HasOps$;
import breeze.storage.Zero$;
import java.lang.invoke.SerializedLambda;
import scala.MatchError;
import scala.Tuple3;
import scala.collection.ArrayOps.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005U;QAD\b\t\u0002Q1QAF\b\t\u0002]AQ\u0001J\u0001\u0005\u0002\u0015BQAJ\u0001\u0005\n\u001d:Q!N\u0001\t\u0004Y2Q\u0001O\u0001\t\u0002eBQ\u0001J\u0003\u0005\u0002uBQAP\u0003\u0005\u0002}BqAQ\u0003\u0002\u0002\u0013%1iB\u0003M\u0003!\rQJB\u0003O\u0003!\u0005q\nC\u0003%\u0015\u0011\u0005\u0011\u000bC\u0003?\u0015\u0011\u0005!\u000bC\u0004C\u0015\u0005\u0005I\u0011B\"\u0002\t5\u0004xn\u001e\u0006\u0003!E\ta\u0001\\5oC2<'\"\u0001\n\u0002\r\t\u0014X-\u001a>f\u0007\u0001\u0001\"!F\u0001\u000e\u0003=\u0011A!\u001c9poN\u0019\u0011\u0001\u0007\u0010\u0011\u0005eaR\"\u0001\u000e\u000b\u0003m\tQa]2bY\u0006L!!\b\u000e\u0003\r\u0005s\u0017PU3g!\ty\"%D\u0001!\u0015\t\t\u0013#A\u0004hK:,'/[2\n\u0005\r\u0002#!B+Gk:\u001c\u0017A\u0002\u001fj]&$h\bF\u0001\u0015\u00035\u0001xn\u001e\"z'F,\u0018M]5oOR\u0019\u0001F\f\u0019\u0011\u0007UI3&\u0003\u0002+\u001f\tYA)\u001a8tK6\u000bGO]5y!\tIB&\u0003\u0002.5\t1Ai\\;cY\u0016DQaL\u0002A\u0002!\n\u0011!\u001c\u0005\u0006c\r\u0001\rAM\u0001\u0004Kb\u0004\bCA\r4\u0013\t!$DA\u0002J]R\fA#[7qY\u0012ku\fR8vE2,w\fR8vE2,\u0007CA\u001c\u0006\u001b\u0005\t!\u0001F5na2$Uj\u0018#pk\ndWm\u0018#pk\ndWmE\u0002\u00061i\u0002RaN\u001e)W!J!\u0001\u0010\u0012\u0003\u000b%k\u0007\u000f\u001c\u001a\u0015\u0003Y\nQ!\u00199qYf$2\u0001\u000b!B\u0011\u0015ys\u00011\u0001)\u0011\u0015\tt\u00011\u0001,\u000319(/\u001b;f%\u0016\u0004H.Y2f)\u0005!\u0005CA#K\u001b\u00051%BA$I\u0003\u0011a\u0017M\\4\u000b\u0003%\u000bAA[1wC&\u00111J\u0012\u0002\u0007\u001f\nTWm\u0019;\u0002#%l\u0007\u000f\u001c#N?\u0012{WO\u00197f?&sG\u000f\u0005\u00028\u0015\t\t\u0012.\u001c9m\t6{Fi\\;cY\u0016|\u0016J\u001c;\u0014\u0007)A\u0002\u000bE\u00038w!\u0012\u0004\u0006F\u0001N)\rA3\u000b\u0016\u0005\u0006_1\u0001\r\u0001\u000b\u0005\u0006c1\u0001\rA\r"
)
public final class mpow {
   public static Object withSink(final Object s) {
      return mpow$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return mpow$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return mpow$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return mpow$.MODULE$.inPlace(v, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return mpow$.MODULE$.apply(v1, v2, v3, v4, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return mpow$.MODULE$.apply(v1, v2, v3, impl);
   }

   public static Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return mpow$.MODULE$.apply(v1, v2, impl);
   }

   public static Object apply(final Object v, final UFunc.UImpl impl) {
      return mpow$.MODULE$.apply(v, impl);
   }

   public static class implDM_Double_Double$ implements UFunc.UImpl2 {
      public static final implDM_Double_Double$ MODULE$ = new implDM_Double_Double$();

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

      public DenseMatrix apply(final DenseMatrix m, final double exp) {
         package$.MODULE$.requireSquareMatrix(m);
         eig.Eig var6 = (eig.Eig)eig$.MODULE$.apply(m, eig.Eig_DM_Impl$.MODULE$);
         if (var6 != null) {
            DenseVector real = (DenseVector)var6.eigenvalues();
            DenseVector imag = (DenseVector)var6.eigenvaluesComplex();
            DenseMatrix evectors = (DenseMatrix)var6.eigenvectors();
            Tuple3 var4 = new Tuple3(real, imag, evectors);
            DenseVector real = (DenseVector)var4._1();
            DenseVector imag = (DenseVector)var4._2();
            DenseMatrix evectors = (DenseMatrix)var4._3();
            DenseMatrix var10000;
            if (BoxesRunTime.unboxToDouble(norm$.MODULE$.apply(imag, BoxesRunTime.boxToDouble((double)1.0F), norm$.MODULE$.canNorm(HasOps$.MODULE$.DV_canIterateValues(), norm$.MODULE$.scalarNorm_Double()))) == (double)0.0F) {
               DenseVector exped = new DenseVector$mcD$sp((double[]).MODULE$.map$extension(scala.Predef..MODULE$.doubleArrayOps(real.data$mcD$sp()), (JFunction1.mcDD.sp)(x$2) -> scala.math.package..MODULE$.pow(x$2, exp), scala.reflect.ClassTag..MODULE$.Double()));
               var10000 = (DenseMatrix)((ImmutableNumericOps)((ImmutableNumericOps)evectors.t(HasOps$.MODULE$.canTranspose_DM())).$bslash(((ImmutableNumericOps)evectors.$times(diag$.MODULE$.apply(exped, diag$.MODULE$.diagDVDMImpl(scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero())), HasOps$.MODULE$.impl_OpMulMatrix_DMD_DMD_eq_DMD())).t(HasOps$.MODULE$.canTranspose_DM()), HasOps$.MODULE$.impl_OpSolveMatrixBy_DMD_DMD_eq_DMD())).t(HasOps$.MODULE$.canTranspose_DM());
            } else {
               scala.Predef..MODULE$.require(exp % (double)1 == (double)0, () -> "If m has complex eigenvalues exp need to be integer");
               var10000 = mpow$.MODULE$.breeze$linalg$mpow$$powBySquaring(m, (int)exp);
            }

            return var10000;
         } else {
            throw new MatchError(var6);
         }
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(implDM_Double_Double$.class);
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public static class implDM_Double_Int$ implements UFunc.UImpl2 {
      public static final implDM_Double_Int$ MODULE$ = new implDM_Double_Int$();

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

      public DenseMatrix apply(final DenseMatrix m, final int exp) {
         return mpow.implDM_Double_Double$.MODULE$.apply(m, (double)exp);
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(implDM_Double_Int$.class);
      }
   }
}
