package breeze.linalg;

import breeze.generic.UFunc;
import breeze.linalg.operators.HasOps$;
import breeze.linalg.support.CanTranspose;
import breeze.linalg.support.LiteralRow$;
import breeze.storage.Zero$;
import dev.ludovic.netlib.arpack.ARPACK;
import dev.ludovic.netlib.lapack.LAPACK;
import java.lang.invoke.SerializedLambda;
import java.util.Arrays;
import org.netlib.util.doubleW;
import org.netlib.util.intW;
import scala.MatchError;
import scala.Tuple2;
import scala.runtime.BoxesRunTime;
import scala.runtime.RichInt.;
import scala.runtime.java8.JFunction1;

public final class svd$ implements UFunc {
   public static final svd$ MODULE$ = new svd$();

   static {
      UFunc.$init$(MODULE$);
   }

   public final Object apply(final Object v, final UFunc.UImpl impl) {
      return UFunc.apply$(this, v, impl);
   }

   public final double apply$mDDc$sp(final double v, final UFunc.UImpl impl) {
      return UFunc.apply$mDDc$sp$(this, v, impl);
   }

   public final float apply$mDFc$sp(final double v, final UFunc.UImpl impl) {
      return UFunc.apply$mDFc$sp$(this, v, impl);
   }

   public final int apply$mDIc$sp(final double v, final UFunc.UImpl impl) {
      return UFunc.apply$mDIc$sp$(this, v, impl);
   }

   public final double apply$mFDc$sp(final float v, final UFunc.UImpl impl) {
      return UFunc.apply$mFDc$sp$(this, v, impl);
   }

   public final float apply$mFFc$sp(final float v, final UFunc.UImpl impl) {
      return UFunc.apply$mFFc$sp$(this, v, impl);
   }

   public final int apply$mFIc$sp(final float v, final UFunc.UImpl impl) {
      return UFunc.apply$mFIc$sp$(this, v, impl);
   }

   public final double apply$mIDc$sp(final int v, final UFunc.UImpl impl) {
      return UFunc.apply$mIDc$sp$(this, v, impl);
   }

   public final float apply$mIFc$sp(final int v, final UFunc.UImpl impl) {
      return UFunc.apply$mIFc$sp$(this, v, impl);
   }

   public final int apply$mIIc$sp(final int v, final UFunc.UImpl impl) {
      return UFunc.apply$mIIc$sp$(this, v, impl);
   }

   public final Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$(this, v1, v2, impl);
   }

   public final double apply$mDDDc$sp(final double v1, final double v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mDDDc$sp$(this, v1, v2, impl);
   }

   public final float apply$mDDFc$sp(final double v1, final double v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mDDFc$sp$(this, v1, v2, impl);
   }

   public final int apply$mDDIc$sp(final double v1, final double v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mDDIc$sp$(this, v1, v2, impl);
   }

   public final double apply$mDFDc$sp(final double v1, final float v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mDFDc$sp$(this, v1, v2, impl);
   }

   public final float apply$mDFFc$sp(final double v1, final float v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mDFFc$sp$(this, v1, v2, impl);
   }

   public final int apply$mDFIc$sp(final double v1, final float v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mDFIc$sp$(this, v1, v2, impl);
   }

   public final double apply$mDIDc$sp(final double v1, final int v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mDIDc$sp$(this, v1, v2, impl);
   }

   public final float apply$mDIFc$sp(final double v1, final int v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mDIFc$sp$(this, v1, v2, impl);
   }

   public final int apply$mDIIc$sp(final double v1, final int v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mDIIc$sp$(this, v1, v2, impl);
   }

   public final double apply$mFDDc$sp(final float v1, final double v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mFDDc$sp$(this, v1, v2, impl);
   }

   public final float apply$mFDFc$sp(final float v1, final double v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mFDFc$sp$(this, v1, v2, impl);
   }

   public final int apply$mFDIc$sp(final float v1, final double v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mFDIc$sp$(this, v1, v2, impl);
   }

   public final double apply$mFFDc$sp(final float v1, final float v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mFFDc$sp$(this, v1, v2, impl);
   }

   public final float apply$mFFFc$sp(final float v1, final float v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mFFFc$sp$(this, v1, v2, impl);
   }

   public final int apply$mFFIc$sp(final float v1, final float v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mFFIc$sp$(this, v1, v2, impl);
   }

   public final double apply$mFIDc$sp(final float v1, final int v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mFIDc$sp$(this, v1, v2, impl);
   }

   public final float apply$mFIFc$sp(final float v1, final int v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mFIFc$sp$(this, v1, v2, impl);
   }

   public final int apply$mFIIc$sp(final float v1, final int v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mFIIc$sp$(this, v1, v2, impl);
   }

   public final double apply$mIDDc$sp(final int v1, final double v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mIDDc$sp$(this, v1, v2, impl);
   }

   public final float apply$mIDFc$sp(final int v1, final double v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mIDFc$sp$(this, v1, v2, impl);
   }

   public final int apply$mIDIc$sp(final int v1, final double v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mIDIc$sp$(this, v1, v2, impl);
   }

   public final double apply$mIFDc$sp(final int v1, final float v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mIFDc$sp$(this, v1, v2, impl);
   }

   public final float apply$mIFFc$sp(final int v1, final float v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mIFFc$sp$(this, v1, v2, impl);
   }

   public final int apply$mIFIc$sp(final int v1, final float v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mIFIc$sp$(this, v1, v2, impl);
   }

   public final double apply$mIIDc$sp(final int v1, final int v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mIIDc$sp$(this, v1, v2, impl);
   }

   public final float apply$mIIFc$sp(final int v1, final int v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mIIFc$sp$(this, v1, v2, impl);
   }

   public final int apply$mIIIc$sp(final int v1, final int v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mIIIc$sp$(this, v1, v2, impl);
   }

   public final Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$(this, v1, v2, v3, impl);
   }

   public final double apply$mDDDc$sp(final Object v1, final double v2, final double v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mDDDc$sp$(this, v1, v2, v3, impl);
   }

   public final float apply$mDDFc$sp(final Object v1, final double v2, final double v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mDDFc$sp$(this, v1, v2, v3, impl);
   }

   public final int apply$mDDIc$sp(final Object v1, final double v2, final double v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mDDIc$sp$(this, v1, v2, v3, impl);
   }

   public final double apply$mDFDc$sp(final Object v1, final double v2, final float v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mDFDc$sp$(this, v1, v2, v3, impl);
   }

   public final float apply$mDFFc$sp(final Object v1, final double v2, final float v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mDFFc$sp$(this, v1, v2, v3, impl);
   }

   public final int apply$mDFIc$sp(final Object v1, final double v2, final float v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mDFIc$sp$(this, v1, v2, v3, impl);
   }

   public final double apply$mDIDc$sp(final Object v1, final double v2, final int v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mDIDc$sp$(this, v1, v2, v3, impl);
   }

   public final float apply$mDIFc$sp(final Object v1, final double v2, final int v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mDIFc$sp$(this, v1, v2, v3, impl);
   }

   public final int apply$mDIIc$sp(final Object v1, final double v2, final int v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mDIIc$sp$(this, v1, v2, v3, impl);
   }

   public final double apply$mFDDc$sp(final Object v1, final float v2, final double v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mFDDc$sp$(this, v1, v2, v3, impl);
   }

   public final float apply$mFDFc$sp(final Object v1, final float v2, final double v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mFDFc$sp$(this, v1, v2, v3, impl);
   }

   public final int apply$mFDIc$sp(final Object v1, final float v2, final double v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mFDIc$sp$(this, v1, v2, v3, impl);
   }

   public final double apply$mFFDc$sp(final Object v1, final float v2, final float v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mFFDc$sp$(this, v1, v2, v3, impl);
   }

   public final float apply$mFFFc$sp(final Object v1, final float v2, final float v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mFFFc$sp$(this, v1, v2, v3, impl);
   }

   public final int apply$mFFIc$sp(final Object v1, final float v2, final float v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mFFIc$sp$(this, v1, v2, v3, impl);
   }

   public final double apply$mFIDc$sp(final Object v1, final float v2, final int v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mFIDc$sp$(this, v1, v2, v3, impl);
   }

   public final float apply$mFIFc$sp(final Object v1, final float v2, final int v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mFIFc$sp$(this, v1, v2, v3, impl);
   }

   public final int apply$mFIIc$sp(final Object v1, final float v2, final int v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mFIIc$sp$(this, v1, v2, v3, impl);
   }

   public final double apply$mIDDc$sp(final Object v1, final int v2, final double v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mIDDc$sp$(this, v1, v2, v3, impl);
   }

   public final float apply$mIDFc$sp(final Object v1, final int v2, final double v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mIDFc$sp$(this, v1, v2, v3, impl);
   }

   public final int apply$mIDIc$sp(final Object v1, final int v2, final double v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mIDIc$sp$(this, v1, v2, v3, impl);
   }

   public final double apply$mIFDc$sp(final Object v1, final int v2, final float v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mIFDc$sp$(this, v1, v2, v3, impl);
   }

   public final float apply$mIFFc$sp(final Object v1, final int v2, final float v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mIFFc$sp$(this, v1, v2, v3, impl);
   }

   public final int apply$mIFIc$sp(final Object v1, final int v2, final float v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mIFIc$sp$(this, v1, v2, v3, impl);
   }

   public final double apply$mIIDc$sp(final Object v1, final int v2, final int v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mIIDc$sp$(this, v1, v2, v3, impl);
   }

   public final float apply$mIIFc$sp(final Object v1, final int v2, final int v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mIIFc$sp$(this, v1, v2, v3, impl);
   }

   public final int apply$mIIIc$sp(final Object v1, final int v2, final int v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mIIIc$sp$(this, v1, v2, v3, impl);
   }

   public final Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return UFunc.apply$(this, v1, v2, v3, v4, impl);
   }

   public final Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return UFunc.inPlace$(this, v, impl);
   }

   public final Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return UFunc.inPlace$(this, v, v2, impl);
   }

   public final Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return UFunc.inPlace$(this, v, v2, v3, impl);
   }

   public final Object withSink(final Object s) {
      return UFunc.withSink$(this, s);
   }

   public svd.SVD breeze$linalg$svd$$doSVD_Double(final DenseMatrix mat, final SVDMode mode) {
      package$.MODULE$.requireNonEmptyMatrix(mat);
      int m = mat.rows();
      int n = mat.cols();
      DenseVector S = DenseVector$.MODULE$.zeros$mDc$sp(.MODULE$.min$extension(scala.Predef..MODULE$.intWrapper(m), n), scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());
      DenseMatrix U;
      if (CompleteSVD$.MODULE$.equals(mode)) {
         U = DenseMatrix$.MODULE$.zeros$mDc$sp(m, m, scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());
      } else {
         if (!ReducedSVD$.MODULE$.equals(mode)) {
            throw new MatchError(mode);
         }

         U = DenseMatrix$.MODULE$.zeros$mDc$sp(m, .MODULE$.min$extension(scala.Predef..MODULE$.intWrapper(m), n), scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());
      }

      DenseMatrix Vt;
      if (CompleteSVD$.MODULE$.equals(mode)) {
         Vt = DenseMatrix$.MODULE$.zeros$mDc$sp(n, n, scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());
      } else {
         if (!ReducedSVD$.MODULE$.equals(mode)) {
            throw new MatchError(mode);
         }

         Vt = DenseMatrix$.MODULE$.zeros$mDc$sp(.MODULE$.min$extension(scala.Predef..MODULE$.intWrapper(m), n), n, scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());
      }

      int[] iwork = new int[8 * .MODULE$.min$extension(scala.Predef..MODULE$.intWrapper(m), n)];
      long workSize = 3L * (long)scala.math.package..MODULE$.min(m, n) * (long)scala.math.package..MODULE$.min(m, n) + scala.math.package..MODULE$.max((long)scala.math.package..MODULE$.max(m, n), 4L * (long)scala.math.package..MODULE$.min(m, n) * (long)scala.math.package..MODULE$.min(m, n) + 4L * (long)scala.math.package..MODULE$.min(m, n));
      if (workSize >= 2147483647L) {
         throw new RuntimeException("The param k and numFeatures is too large for SVD computation. Try reducing the parameter k for PCA, or reduce the input feature vector dimension to make this tractable.");
      } else {
         double[] work = new double[(int)workSize];
         intW info = new intW(0);
         DenseMatrix cm = (DenseMatrix)package$.MODULE$.copy(mat, HasOps$.MODULE$.canCopy_DM(scala.reflect.ClassTag..MODULE$.Double()));
         int LDVT;
         if (CompleteSVD$.MODULE$.equals(mode)) {
            LDVT = scala.math.package..MODULE$.max(1, n);
         } else {
            if (!ReducedSVD$.MODULE$.equals(mode)) {
               throw new MatchError(mode);
            }

            LDVT = .MODULE$.min$extension(scala.Predef..MODULE$.intWrapper(m), n);
         }

         LAPACK.getInstance().dgesdd(mode.JOBZ(), m, n, cm.data$mcD$sp(), scala.math.package..MODULE$.max(1, m), S.data$mcD$sp(), U.data$mcD$sp(), scala.math.package..MODULE$.max(1, m), Vt.data$mcD$sp(), LDVT, work, work.length, iwork, info);
         if (info.val > 0) {
            throw new NotConvergedException(NotConvergedException.Iterations$.MODULE$, NotConvergedException$.MODULE$.$lessinit$greater$default$2());
         } else if (info.val < 0) {
            throw new IllegalArgumentException();
         } else {
            return new svd.SVD(U, S, Vt);
         }
      }
   }

   public svd.SVD breeze$linalg$svd$$doSVD_Float(final DenseMatrix mat, final SVDMode mode) {
      package$.MODULE$.requireNonEmptyMatrix(mat);
      int m = mat.rows();
      int n = mat.cols();
      DenseVector S = DenseVector$.MODULE$.zeros$mFc$sp(.MODULE$.min$extension(scala.Predef..MODULE$.intWrapper(m), n), scala.reflect.ClassTag..MODULE$.Float(), Zero$.MODULE$.FloatZero());
      String var10 = mode.JOBZ();
      DenseMatrix U;
      switch (var10 == null ? 0 : var10.hashCode()) {
         case 65:
            if (!"A".equals(var10)) {
               throw new MatchError(var10);
            }

            U = DenseMatrix$.MODULE$.zeros$mFc$sp(m, m, scala.reflect.ClassTag..MODULE$.Float(), Zero$.MODULE$.FloatZero());
            break;
         case 83:
            if ("S".equals(var10)) {
               U = DenseMatrix$.MODULE$.zeros$mFc$sp(m, .MODULE$.min$extension(scala.Predef..MODULE$.intWrapper(m), n), scala.reflect.ClassTag..MODULE$.Float(), Zero$.MODULE$.FloatZero());
               break;
            }

            throw new MatchError(var10);
         default:
            throw new MatchError(var10);
      }

      String var12 = mode.JOBZ();
      DenseMatrix Vt;
      switch (var12 == null ? 0 : var12.hashCode()) {
         case 65:
            if (!"A".equals(var12)) {
               throw new MatchError(var12);
            }

            Vt = DenseMatrix$.MODULE$.zeros$mFc$sp(n, n, scala.reflect.ClassTag..MODULE$.Float(), Zero$.MODULE$.FloatZero());
            break;
         case 83:
            if ("S".equals(var12)) {
               Vt = DenseMatrix$.MODULE$.zeros$mFc$sp(.MODULE$.min$extension(scala.Predef..MODULE$.intWrapper(m), n), n, scala.reflect.ClassTag..MODULE$.Float(), Zero$.MODULE$.FloatZero());
               break;
            }

            throw new MatchError(var12);
         default:
            throw new MatchError(var12);
      }

      int[] iwork = new int[8 * .MODULE$.min$extension(scala.Predef..MODULE$.intWrapper(m), n)];
      int workSize = 3 * scala.math.package..MODULE$.min(m, n) * scala.math.package..MODULE$.min(m, n) + scala.math.package..MODULE$.max(scala.math.package..MODULE$.max(m, n), 4 * scala.math.package..MODULE$.min(m, n) * scala.math.package..MODULE$.min(m, n) + 4 * scala.math.package..MODULE$.min(m, n));
      float[] work = new float[workSize];
      intW info = new intW(0);
      DenseMatrix cm = (DenseMatrix)package$.MODULE$.copy(mat, HasOps$.MODULE$.canCopy_DM(scala.reflect.ClassTag..MODULE$.Float()));
      String var19 = mode.JOBZ();
      int LDVT;
      switch (var19 == null ? 0 : var19.hashCode()) {
         case 65:
            if (!"A".equals(var19)) {
               throw new MatchError(var19);
            }

            LDVT = scala.math.package..MODULE$.max(1, n);
            break;
         case 83:
            if ("S".equals(var19)) {
               LDVT = .MODULE$.min$extension(scala.Predef..MODULE$.intWrapper(m), n);
               break;
            }

            throw new MatchError(var19);
         default:
            throw new MatchError(var19);
      }

      LAPACK.getInstance().sgesdd(mode.JOBZ(), m, n, cm.data$mcF$sp(), scala.math.package..MODULE$.max(1, m), S.data$mcF$sp(), U.data$mcF$sp(), scala.math.package..MODULE$.max(1, m), Vt.data$mcF$sp(), LDVT, work, work.length, iwork, info);
      if (info.val > 0) {
         throw new NotConvergedException(NotConvergedException.Iterations$.MODULE$, NotConvergedException$.MODULE$.$lessinit$greater$default$2());
      } else if (info.val < 0) {
         throw new IllegalArgumentException();
      } else {
         return new svd.SVD(U, S, Vt);
      }
   }

   public UFunc.UImpl3 Svd_Sparse_Impl(final UFunc.UImpl2 mul, final CanTranspose trans, final UFunc.UImpl2 mulTrans, final UFunc.UImpl dimImpl) {
      class Svd_Sparse_Impl_Instance$1 implements UFunc.UImpl3 {
         private final ARPACK arpack;
         private final UFunc.UImpl2 mulTrans$1;
         private final UFunc.UImpl2 mul$1;
         private final UFunc.UImpl dimImpl$1;
         private final CanTranspose trans$1;

         public double apply$mcDDDD$sp(final double v, final double v2, final double v3) {
            return UFunc.UImpl3.apply$mcDDDD$sp$(this, v, v2, v3);
         }

         public float apply$mcDDDF$sp(final double v, final double v2, final double v3) {
            return UFunc.UImpl3.apply$mcDDDF$sp$(this, v, v2, v3);
         }

         public int apply$mcDDDI$sp(final double v, final double v2, final double v3) {
            return UFunc.UImpl3.apply$mcDDDI$sp$(this, v, v2, v3);
         }

         public double apply$mcDDFD$sp(final double v, final double v2, final float v3) {
            return UFunc.UImpl3.apply$mcDDFD$sp$(this, v, v2, v3);
         }

         public float apply$mcDDFF$sp(final double v, final double v2, final float v3) {
            return UFunc.UImpl3.apply$mcDDFF$sp$(this, v, v2, v3);
         }

         public int apply$mcDDFI$sp(final double v, final double v2, final float v3) {
            return UFunc.UImpl3.apply$mcDDFI$sp$(this, v, v2, v3);
         }

         public double apply$mcDDID$sp(final double v, final double v2, final int v3) {
            return UFunc.UImpl3.apply$mcDDID$sp$(this, v, v2, v3);
         }

         public float apply$mcDDIF$sp(final double v, final double v2, final int v3) {
            return UFunc.UImpl3.apply$mcDDIF$sp$(this, v, v2, v3);
         }

         public int apply$mcDDII$sp(final double v, final double v2, final int v3) {
            return UFunc.UImpl3.apply$mcDDII$sp$(this, v, v2, v3);
         }

         public double apply$mcDFDD$sp(final double v, final float v2, final double v3) {
            return UFunc.UImpl3.apply$mcDFDD$sp$(this, v, v2, v3);
         }

         public float apply$mcDFDF$sp(final double v, final float v2, final double v3) {
            return UFunc.UImpl3.apply$mcDFDF$sp$(this, v, v2, v3);
         }

         public int apply$mcDFDI$sp(final double v, final float v2, final double v3) {
            return UFunc.UImpl3.apply$mcDFDI$sp$(this, v, v2, v3);
         }

         public double apply$mcDFFD$sp(final double v, final float v2, final float v3) {
            return UFunc.UImpl3.apply$mcDFFD$sp$(this, v, v2, v3);
         }

         public float apply$mcDFFF$sp(final double v, final float v2, final float v3) {
            return UFunc.UImpl3.apply$mcDFFF$sp$(this, v, v2, v3);
         }

         public int apply$mcDFFI$sp(final double v, final float v2, final float v3) {
            return UFunc.UImpl3.apply$mcDFFI$sp$(this, v, v2, v3);
         }

         public double apply$mcDFID$sp(final double v, final float v2, final int v3) {
            return UFunc.UImpl3.apply$mcDFID$sp$(this, v, v2, v3);
         }

         public float apply$mcDFIF$sp(final double v, final float v2, final int v3) {
            return UFunc.UImpl3.apply$mcDFIF$sp$(this, v, v2, v3);
         }

         public int apply$mcDFII$sp(final double v, final float v2, final int v3) {
            return UFunc.UImpl3.apply$mcDFII$sp$(this, v, v2, v3);
         }

         public double apply$mcDIDD$sp(final double v, final int v2, final double v3) {
            return UFunc.UImpl3.apply$mcDIDD$sp$(this, v, v2, v3);
         }

         public float apply$mcDIDF$sp(final double v, final int v2, final double v3) {
            return UFunc.UImpl3.apply$mcDIDF$sp$(this, v, v2, v3);
         }

         public int apply$mcDIDI$sp(final double v, final int v2, final double v3) {
            return UFunc.UImpl3.apply$mcDIDI$sp$(this, v, v2, v3);
         }

         public double apply$mcDIFD$sp(final double v, final int v2, final float v3) {
            return UFunc.UImpl3.apply$mcDIFD$sp$(this, v, v2, v3);
         }

         public float apply$mcDIFF$sp(final double v, final int v2, final float v3) {
            return UFunc.UImpl3.apply$mcDIFF$sp$(this, v, v2, v3);
         }

         public int apply$mcDIFI$sp(final double v, final int v2, final float v3) {
            return UFunc.UImpl3.apply$mcDIFI$sp$(this, v, v2, v3);
         }

         public double apply$mcDIID$sp(final double v, final int v2, final int v3) {
            return UFunc.UImpl3.apply$mcDIID$sp$(this, v, v2, v3);
         }

         public float apply$mcDIIF$sp(final double v, final int v2, final int v3) {
            return UFunc.UImpl3.apply$mcDIIF$sp$(this, v, v2, v3);
         }

         public int apply$mcDIII$sp(final double v, final int v2, final int v3) {
            return UFunc.UImpl3.apply$mcDIII$sp$(this, v, v2, v3);
         }

         public double apply$mcFDDD$sp(final float v, final double v2, final double v3) {
            return UFunc.UImpl3.apply$mcFDDD$sp$(this, v, v2, v3);
         }

         public float apply$mcFDDF$sp(final float v, final double v2, final double v3) {
            return UFunc.UImpl3.apply$mcFDDF$sp$(this, v, v2, v3);
         }

         public int apply$mcFDDI$sp(final float v, final double v2, final double v3) {
            return UFunc.UImpl3.apply$mcFDDI$sp$(this, v, v2, v3);
         }

         public double apply$mcFDFD$sp(final float v, final double v2, final float v3) {
            return UFunc.UImpl3.apply$mcFDFD$sp$(this, v, v2, v3);
         }

         public float apply$mcFDFF$sp(final float v, final double v2, final float v3) {
            return UFunc.UImpl3.apply$mcFDFF$sp$(this, v, v2, v3);
         }

         public int apply$mcFDFI$sp(final float v, final double v2, final float v3) {
            return UFunc.UImpl3.apply$mcFDFI$sp$(this, v, v2, v3);
         }

         public double apply$mcFDID$sp(final float v, final double v2, final int v3) {
            return UFunc.UImpl3.apply$mcFDID$sp$(this, v, v2, v3);
         }

         public float apply$mcFDIF$sp(final float v, final double v2, final int v3) {
            return UFunc.UImpl3.apply$mcFDIF$sp$(this, v, v2, v3);
         }

         public int apply$mcFDII$sp(final float v, final double v2, final int v3) {
            return UFunc.UImpl3.apply$mcFDII$sp$(this, v, v2, v3);
         }

         public double apply$mcFFDD$sp(final float v, final float v2, final double v3) {
            return UFunc.UImpl3.apply$mcFFDD$sp$(this, v, v2, v3);
         }

         public float apply$mcFFDF$sp(final float v, final float v2, final double v3) {
            return UFunc.UImpl3.apply$mcFFDF$sp$(this, v, v2, v3);
         }

         public int apply$mcFFDI$sp(final float v, final float v2, final double v3) {
            return UFunc.UImpl3.apply$mcFFDI$sp$(this, v, v2, v3);
         }

         public double apply$mcFFFD$sp(final float v, final float v2, final float v3) {
            return UFunc.UImpl3.apply$mcFFFD$sp$(this, v, v2, v3);
         }

         public float apply$mcFFFF$sp(final float v, final float v2, final float v3) {
            return UFunc.UImpl3.apply$mcFFFF$sp$(this, v, v2, v3);
         }

         public int apply$mcFFFI$sp(final float v, final float v2, final float v3) {
            return UFunc.UImpl3.apply$mcFFFI$sp$(this, v, v2, v3);
         }

         public double apply$mcFFID$sp(final float v, final float v2, final int v3) {
            return UFunc.UImpl3.apply$mcFFID$sp$(this, v, v2, v3);
         }

         public float apply$mcFFIF$sp(final float v, final float v2, final int v3) {
            return UFunc.UImpl3.apply$mcFFIF$sp$(this, v, v2, v3);
         }

         public int apply$mcFFII$sp(final float v, final float v2, final int v3) {
            return UFunc.UImpl3.apply$mcFFII$sp$(this, v, v2, v3);
         }

         public double apply$mcFIDD$sp(final float v, final int v2, final double v3) {
            return UFunc.UImpl3.apply$mcFIDD$sp$(this, v, v2, v3);
         }

         public float apply$mcFIDF$sp(final float v, final int v2, final double v3) {
            return UFunc.UImpl3.apply$mcFIDF$sp$(this, v, v2, v3);
         }

         public int apply$mcFIDI$sp(final float v, final int v2, final double v3) {
            return UFunc.UImpl3.apply$mcFIDI$sp$(this, v, v2, v3);
         }

         public double apply$mcFIFD$sp(final float v, final int v2, final float v3) {
            return UFunc.UImpl3.apply$mcFIFD$sp$(this, v, v2, v3);
         }

         public float apply$mcFIFF$sp(final float v, final int v2, final float v3) {
            return UFunc.UImpl3.apply$mcFIFF$sp$(this, v, v2, v3);
         }

         public int apply$mcFIFI$sp(final float v, final int v2, final float v3) {
            return UFunc.UImpl3.apply$mcFIFI$sp$(this, v, v2, v3);
         }

         public double apply$mcFIID$sp(final float v, final int v2, final int v3) {
            return UFunc.UImpl3.apply$mcFIID$sp$(this, v, v2, v3);
         }

         public float apply$mcFIIF$sp(final float v, final int v2, final int v3) {
            return UFunc.UImpl3.apply$mcFIIF$sp$(this, v, v2, v3);
         }

         public int apply$mcFIII$sp(final float v, final int v2, final int v3) {
            return UFunc.UImpl3.apply$mcFIII$sp$(this, v, v2, v3);
         }

         public double apply$mcIDDD$sp(final int v, final double v2, final double v3) {
            return UFunc.UImpl3.apply$mcIDDD$sp$(this, v, v2, v3);
         }

         public float apply$mcIDDF$sp(final int v, final double v2, final double v3) {
            return UFunc.UImpl3.apply$mcIDDF$sp$(this, v, v2, v3);
         }

         public int apply$mcIDDI$sp(final int v, final double v2, final double v3) {
            return UFunc.UImpl3.apply$mcIDDI$sp$(this, v, v2, v3);
         }

         public double apply$mcIDFD$sp(final int v, final double v2, final float v3) {
            return UFunc.UImpl3.apply$mcIDFD$sp$(this, v, v2, v3);
         }

         public float apply$mcIDFF$sp(final int v, final double v2, final float v3) {
            return UFunc.UImpl3.apply$mcIDFF$sp$(this, v, v2, v3);
         }

         public int apply$mcIDFI$sp(final int v, final double v2, final float v3) {
            return UFunc.UImpl3.apply$mcIDFI$sp$(this, v, v2, v3);
         }

         public double apply$mcIDID$sp(final int v, final double v2, final int v3) {
            return UFunc.UImpl3.apply$mcIDID$sp$(this, v, v2, v3);
         }

         public float apply$mcIDIF$sp(final int v, final double v2, final int v3) {
            return UFunc.UImpl3.apply$mcIDIF$sp$(this, v, v2, v3);
         }

         public int apply$mcIDII$sp(final int v, final double v2, final int v3) {
            return UFunc.UImpl3.apply$mcIDII$sp$(this, v, v2, v3);
         }

         public double apply$mcIFDD$sp(final int v, final float v2, final double v3) {
            return UFunc.UImpl3.apply$mcIFDD$sp$(this, v, v2, v3);
         }

         public float apply$mcIFDF$sp(final int v, final float v2, final double v3) {
            return UFunc.UImpl3.apply$mcIFDF$sp$(this, v, v2, v3);
         }

         public int apply$mcIFDI$sp(final int v, final float v2, final double v3) {
            return UFunc.UImpl3.apply$mcIFDI$sp$(this, v, v2, v3);
         }

         public double apply$mcIFFD$sp(final int v, final float v2, final float v3) {
            return UFunc.UImpl3.apply$mcIFFD$sp$(this, v, v2, v3);
         }

         public float apply$mcIFFF$sp(final int v, final float v2, final float v3) {
            return UFunc.UImpl3.apply$mcIFFF$sp$(this, v, v2, v3);
         }

         public int apply$mcIFFI$sp(final int v, final float v2, final float v3) {
            return UFunc.UImpl3.apply$mcIFFI$sp$(this, v, v2, v3);
         }

         public double apply$mcIFID$sp(final int v, final float v2, final int v3) {
            return UFunc.UImpl3.apply$mcIFID$sp$(this, v, v2, v3);
         }

         public float apply$mcIFIF$sp(final int v, final float v2, final int v3) {
            return UFunc.UImpl3.apply$mcIFIF$sp$(this, v, v2, v3);
         }

         public int apply$mcIFII$sp(final int v, final float v2, final int v3) {
            return UFunc.UImpl3.apply$mcIFII$sp$(this, v, v2, v3);
         }

         public double apply$mcIIDD$sp(final int v, final int v2, final double v3) {
            return UFunc.UImpl3.apply$mcIIDD$sp$(this, v, v2, v3);
         }

         public float apply$mcIIDF$sp(final int v, final int v2, final double v3) {
            return UFunc.UImpl3.apply$mcIIDF$sp$(this, v, v2, v3);
         }

         public int apply$mcIIDI$sp(final int v, final int v2, final double v3) {
            return UFunc.UImpl3.apply$mcIIDI$sp$(this, v, v2, v3);
         }

         public double apply$mcIIFD$sp(final int v, final int v2, final float v3) {
            return UFunc.UImpl3.apply$mcIIFD$sp$(this, v, v2, v3);
         }

         public float apply$mcIIFF$sp(final int v, final int v2, final float v3) {
            return UFunc.UImpl3.apply$mcIIFF$sp$(this, v, v2, v3);
         }

         public int apply$mcIIFI$sp(final int v, final int v2, final float v3) {
            return UFunc.UImpl3.apply$mcIIFI$sp$(this, v, v2, v3);
         }

         public double apply$mcIIID$sp(final int v, final int v2, final int v3) {
            return UFunc.UImpl3.apply$mcIIID$sp$(this, v, v2, v3);
         }

         public float apply$mcIIIF$sp(final int v, final int v2, final int v3) {
            return UFunc.UImpl3.apply$mcIIIF$sp$(this, v, v2, v3);
         }

         public int apply$mcIIII$sp(final int v, final int v2, final int v3) {
            return UFunc.UImpl3.apply$mcIIII$sp$(this, v, v2, v3);
         }

         private ARPACK arpack() {
            return this.arpack;
         }

         private void av(final Object mat, final Object matTrans, final int n, final int k, final double[] work, final int input_offset, final int output_offset) {
            DenseVector w = DenseVector$.MODULE$.apply$mDc$sp(work);
            DenseVector x = (DenseVector)w.apply(.MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(input_offset), input_offset + n), HasOps$.MODULE$.canSlice_DV_Range_eq_DV());
            DenseVector y = (DenseVector)w.apply(.MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(output_offset), output_offset + n), HasOps$.MODULE$.canSlice_DV_Range_eq_DV());
            DenseVector z = (DenseVector)this.mulTrans$1.apply(matTrans, x);
            if (z.length() <= k) {
               throw new IllegalArgumentException("The number of rows or columns should be bigger than k.");
            } else {
               y.$colon$eq(this.mul$1.apply(mat, z), HasOps$.MODULE$.impl_Op_InPlace_DV_DV_Double_OpSet());
            }
         }

         public svd.SVD apply(final Object mt, final int k, final double tol) {
            int n = ((Tuple2)dim$.MODULE$.apply(mt, this.dimImpl$1))._1$mcI$sp();
            if (n <= k) {
               throw new IllegalArgumentException("The number of rows or columns should be bigger than k.");
            } else {
               Object mtTrans = this.trans$1.apply(mt);
               doubleW tolW = new doubleW(tol);
               intW nev = new intW(k);
               int ncv = scala.math.package..MODULE$.min(2 * k, n);
               String bmat = "I";
               String which = "LM";
               int[] iparam = new int[11];
               iparam[0] = 1;
               iparam[2] = 300;
               iparam[6] = 1;
               intW ido = new intW(0);
               intW info = new intW(0);
               double[] resid = new double[n];
               double[] v = new double[n * ncv];
               double[] workd = new double[3 * n];
               double[] workl = new double[ncv * (ncv + 8)];
               int[] ipntr = new int[11];
               this.arpack().dsaupd(ido, bmat, n, which, nev.val, tolW, resid, ncv, v, n, iparam, ipntr, workd, workl, workl.length, info);

               while(ido.val != 99) {
                  if (ido.val != -1 && ido.val != 1) {
                     throw new IllegalStateException((new StringBuilder(6)).append("ido = ").append(ido.val).toString());
                  }

                  this.av(mt, mtTrans, n, k, workd, ipntr[0] - 1, ipntr[1] - 1);
                  this.arpack().dsaupd(ido, bmat, n, which, nev.val, tolW, resid, ncv, v, n, iparam, ipntr, workd, workl, workl.length, info);
               }

               if (info.val != 0) {
                  throw new IllegalStateException((new StringBuilder(7)).append("info = ").append(info.val).toString());
               } else {
                  double[] d = new double[nev.val];
                  boolean[] select = new boolean[ncv];
                  double[] z = Arrays.copyOfRange(v, 0, nev.val * n);
                  this.arpack().dseupd(true, "A", select, d, z, n, (double)0.0F, bmat, n, which, nev, tol, resid, ncv, v, n, iparam, ipntr, workd, workl, workl.length, info);
                  int computed = iparam[4];
                  DenseVector eigenVectors = new DenseVector$mcD$sp(z);
                  Tuple2[] mp = new Tuple2[computed];
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = computed; index$macro$2 < limit$macro$4; ++index$macro$2) {
                     double eigenVal = d[index$macro$2];
                     if (eigenVal < (double)0.0F) {
                        throw new IllegalStateException("encountered negative eigenvalue, please make sure your multiplication operators are applied to the same matrix.");
                     }

                     DenseVector eigenVec = (DenseVector)eigenVectors.apply(.MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(index$macro$2 * n), index$macro$2 * n + n), HasOps$.MODULE$.canSlice_DV_Range_eq_DV());
                     mp[index$macro$2] = new Tuple2(BoxesRunTime.boxToDouble(scala.math.package..MODULE$.sqrt(eigenVal)), eigenVec);
                  }

                  mp = (Tuple2[])scala.collection.ArrayOps..MODULE$.sortBy$extension(scala.Predef..MODULE$.refArrayOps((Object[])mp), (x$1) -> BoxesRunTime.boxToDouble($anonfun$apply$1(x$1)), scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$);
                  double[] sp = (double[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])mp), (x$2) -> BoxesRunTime.boxToDouble($anonfun$apply$2(x$2)), scala.reflect.ClassTag..MODULE$.Double());
                  DenseVector s = DenseVector$.MODULE$.apply$mDc$sp((double[])scala.collection.ArrayOps..MODULE$.toArray$extension(scala.Predef..MODULE$.doubleArrayOps(sp), scala.reflect.ClassTag..MODULE$.Double()));
                  DenseMatrix siMatrix = (DenseMatrix)diag$.MODULE$.apply(DenseVector$.MODULE$.apply$mDc$sp((double[])scala.collection.ArrayOps..MODULE$.toArray$extension(scala.Predef..MODULE$.doubleArrayOps((double[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.doubleArrayOps(sp), (JFunction1.mcDD.sp)(u) -> (double)1 / u, scala.reflect.ClassTag..MODULE$.Double())), scala.reflect.ClassTag..MODULE$.Double())), diag$.MODULE$.diagDVDMImpl(scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero()));
                  DenseVector[] va = (DenseVector[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])mp), (x0$1) -> {
                     if (x0$1 != null) {
                        DenseVector ev = (DenseVector)x0$1._2();
                        return ev;
                     } else {
                        throw new MatchError(x0$1);
                     }
                  }, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class));
                  DenseMatrix uOutput = (DenseMatrix)DenseMatrix$.MODULE$.apply$mDc$sp(scala.collection.ArrayOps..MODULE$.toSeq$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(va), (r) -> r.toArray$mcD$sp(scala.reflect.ClassTag..MODULE$.Double()), scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Double.TYPE))))), LiteralRow$.MODULE$.array(), scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero()).t(HasOps$.MODULE$.canTranspose_DM());
                  DenseMatrix vtOutput = (DenseMatrix)siMatrix.$times(DenseMatrix$.MODULE$.apply$mDc$sp(scala.collection.ArrayOps..MODULE$.toSeq$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(va), (r) -> ((DenseVector)this.mulTrans$1.apply(mtTrans, r)).toArray$mcD$sp(scala.reflect.ClassTag..MODULE$.Double()), scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Double.TYPE))))), LiteralRow$.MODULE$.array(), scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero()), HasOps$.MODULE$.impl_OpMulMatrix_DMD_DMD_eq_DMD());
                  return new svd.SVD(uOutput, s, vtOutput);
               }
            }
         }

         // $FF: synthetic method
         public static final double $anonfun$apply$1(final Tuple2 x$1) {
            return (double)-1 * x$1._1$mcD$sp();
         }

         // $FF: synthetic method
         public static final double $anonfun$apply$2(final Tuple2 x$2) {
            return x$2._1$mcD$sp();
         }

         public Svd_Sparse_Impl_Instance$1(final UFunc.UImpl2 mulTrans$1, final UFunc.UImpl2 mul$1, final UFunc.UImpl dimImpl$1, final CanTranspose trans$1) {
            this.mulTrans$1 = mulTrans$1;
            this.mul$1 = mul$1;
            this.dimImpl$1 = dimImpl$1;
            this.trans$1 = trans$1;
            this.arpack = ARPACK.getInstance();
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      }

      return new Svd_Sparse_Impl_Instance$1(mulTrans, mul, dimImpl, trans);
   }

   private svd$() {
   }
}
