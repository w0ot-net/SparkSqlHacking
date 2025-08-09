package breeze.linalg;

import breeze.generic.UFunc;
import dev.ludovic.netlib.lapack.LAPACK;
import org.netlib.util.intW;
import scala.Tuple2;
import scala.Array.;
import scala.runtime.ModuleSerializationProxy;

public class LU$primitive$LU_DM_Impl_Float$ implements UFunc.UImpl {
   public static final LU$primitive$LU_DM_Impl_Float$ MODULE$ = new LU$primitive$LU_DM_Impl_Float$();

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

   public Tuple2 apply(final DenseMatrix X) {
      int M = X.rows();
      int N = X.cols();
      DenseMatrix Y = X.copy$mcF$sp();
      int[] ipiv = (int[]).MODULE$.ofDim(scala.math.package..MODULE$.min(M, N), scala.reflect.ClassTag..MODULE$.Int());
      intW info = new intW(0);
      LAPACK.getInstance().sgetrf(M, N, Y.data$mcF$sp(), scala.math.package..MODULE$.max(1, M), ipiv, info);
      scala.Predef..MODULE$.assert(info.val >= 0);
      return new Tuple2(Y, ipiv);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(LU$primitive$LU_DM_Impl_Float$.class);
   }
}
