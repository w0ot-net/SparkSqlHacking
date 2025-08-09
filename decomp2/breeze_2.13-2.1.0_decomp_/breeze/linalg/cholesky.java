package breeze.linalg;

import breeze.generic.UFunc;
import breeze.math.Semiring$;
import breeze.storage.Zero$;
import dev.ludovic.netlib.lapack.LAPACK;
import org.netlib.util.intW;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;
import scala.runtime.ModuleSerializationProxy;

@ScalaSignature(
   bytes = "\u0006\u0005u:Q\u0001C\u0005\t\u000291Q\u0001E\u0005\t\u0002EAQAH\u0001\u0005\u0002}9Q\u0001I\u0001\t\u0004\u00052QaI\u0001\t\u0002\u0011BQA\b\u0003\u0005\u00029BQa\f\u0003\u0005\u0002ABqa\r\u0003\u0002\u0002\u0013%A'\u0001\u0005dQ>dWm]6z\u0015\tQ1\"\u0001\u0004mS:\fGn\u001a\u0006\u0002\u0019\u00051!M]3fu\u0016\u001c\u0001\u0001\u0005\u0002\u0010\u00035\t\u0011B\u0001\u0005dQ>dWm]6z'\r\t!\u0003\u0007\t\u0003'Yi\u0011\u0001\u0006\u0006\u0002+\u0005)1oY1mC&\u0011q\u0003\u0006\u0002\u0007\u0003:L(+\u001a4\u0011\u0005eaR\"\u0001\u000e\u000b\u0005mY\u0011aB4f]\u0016\u0014\u0018nY\u0005\u0003;i\u0011Q!\u0016$v]\u000e\fa\u0001P5oSRtD#\u0001\b\u0002\u001f%k\u0007\u000f\\\"i_2,7o[=`\t6\u0003\"A\t\u0003\u000e\u0003\u0005\u0011q\"S7qY\u000eCw\u000e\\3tWf|F)T\n\u0004\tI)\u0003\u0003\u0002\u0012'Q!J!a\n\u000f\u0003\t%k\u0007\u000f\u001c\t\u0004\u001f%Z\u0013B\u0001\u0016\n\u0005-!UM\\:f\u001b\u0006$(/\u001b=\u0011\u0005Ma\u0013BA\u0017\u0015\u0005\u0019!u.\u001e2mKR\t\u0011%A\u0003baBd\u0017\u0010\u0006\u0002)c!)!G\u0002a\u0001Q\u0005\t\u0001,\u0001\u0007xe&$XMU3qY\u0006\u001cW\rF\u00016!\t14(D\u00018\u0015\tA\u0014(\u0001\u0003mC:<'\"\u0001\u001e\u0002\t)\fg/Y\u0005\u0003y]\u0012aa\u00142kK\u000e$\b"
)
public final class cholesky {
   public static Object withSink(final Object s) {
      return cholesky$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return cholesky$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return cholesky$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return cholesky$.MODULE$.inPlace(v, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return cholesky$.MODULE$.apply(v1, v2, v3, v4, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return cholesky$.MODULE$.apply(v1, v2, v3, impl);
   }

   public static Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return cholesky$.MODULE$.apply(v1, v2, impl);
   }

   public static Object apply(final Object v, final UFunc.UImpl impl) {
      return cholesky$.MODULE$.apply(v, impl);
   }

   public static class ImplCholesky_DM$ implements UFunc.UImpl {
      public static final ImplCholesky_DM$ MODULE$ = new ImplCholesky_DM$();

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

      public DenseMatrix apply(final DenseMatrix X) {
         package$.MODULE$.requireNonEmptyMatrix(X);
         package$.MODULE$.requireSymmetricMatrix(X, package$.MODULE$.requireSymmetricMatrix$default$2());
         DenseMatrix A = package$.MODULE$.lowerTriangular(X, Semiring$.MODULE$.semiringD(), .MODULE$.Double(), Zero$.MODULE$.DoubleZero());
         int N = X.rows();
         intW info = new intW(0);
         LAPACK.getInstance().dpotrf("L", N, A.data$mcD$sp(), scala.math.package..MODULE$.max(1, N), info);
         scala.Predef..MODULE$.assert(info.val >= 0);
         if (info.val > 0) {
            throw new NotConvergedException(NotConvergedException.Iterations$.MODULE$, NotConvergedException$.MODULE$.$lessinit$greater$default$2());
         } else {
            return A;
         }
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(ImplCholesky_DM$.class);
      }
   }
}
