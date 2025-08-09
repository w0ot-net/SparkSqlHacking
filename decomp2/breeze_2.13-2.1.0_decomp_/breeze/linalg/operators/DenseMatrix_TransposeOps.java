package breeze.linalg.operators;

import breeze.linalg.DenseMatrix;
import breeze.linalg.DenseMatrix$;
import breeze.linalg.support.CanTranspose;
import breeze.math.Complex;
import java.lang.invoke.SerializedLambda;
import scala.collection.ArrayOps.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005y2q\u0001B\u0003\u0011\u0002\u0007\u0005A\u0002C\u0003\u0018\u0001\u0011\u0005\u0001\u0004C\u0003\u001d\u0001\u0011\rQ\u0004C\u00035\u0001\u0011\rQG\u0001\rEK:\u001cX-T1ue&Dx\f\u0016:b]N\u0004xn]3PaNT!AB\u0004\u0002\u0013=\u0004XM]1u_J\u001c(B\u0001\u0005\n\u0003\u0019a\u0017N\\1mO*\t!\"\u0001\u0004ce\u0016,'0Z\u0002\u0001'\r\u0001Qb\u0005\t\u0003\u001dEi\u0011a\u0004\u0006\u0002!\u0005)1oY1mC&\u0011!c\u0004\u0002\u0007\u0003:L(+\u001a4\u0011\u0005Q)R\"A\u0003\n\u0005Y)!\u0001\u0006+sC:\u001c\bo\\:f\u001fB\u001cxlR3oKJL7-\u0001\u0004%S:LG\u000f\n\u000b\u00023A\u0011aBG\u0005\u00037=\u0011A!\u00168ji\u0006y1-\u00198Ue\u0006t7\u000f]8tK~#U*\u0006\u0002\u001fWU\tq\u0004\u0005\u0003!G\u0015*S\"A\u0011\u000b\u0005\t:\u0011aB:vaB|'\u000f^\u0005\u0003I\u0005\u0012AbQ1o)J\fgn\u001d9pg\u0016\u00042AJ\u0014*\u001b\u00059\u0011B\u0001\u0015\b\u0005-!UM\\:f\u001b\u0006$(/\u001b=\u0011\u0005)ZC\u0002\u0001\u0003\u0006Y\t\u0011\r!\f\u0002\u0002-F\u0011a&\r\t\u0003\u001d=J!\u0001M\b\u0003\u000f9{G\u000f[5oOB\u0011aBM\u0005\u0003g=\u00111!\u00118z\u0003]\u0019\u0017M\u001c+sC:\u001c\bo\\:f?\u0012kulQ8na2,\u00070F\u00017!\u0011\u00013eN\u001c\u0011\u0007\u0019:\u0003\b\u0005\u0002:y5\t!H\u0003\u0002<\u0013\u0005!Q.\u0019;i\u0013\ti$HA\u0004D_6\u0004H.\u001a="
)
public interface DenseMatrix_TransposeOps extends TransposeOps_Generic {
   // $FF: synthetic method
   static CanTranspose canTranspose_DM$(final DenseMatrix_TransposeOps $this) {
      return $this.canTranspose_DM();
   }

   default CanTranspose canTranspose_DM() {
      return new CanTranspose() {
         public DenseMatrix apply(final DenseMatrix from) {
            Object x$1 = from.data();
            int x$2 = from.offset();
            int x$3 = from.rows();
            int x$4 = from.cols();
            int x$5 = from.majorStride();
            boolean x$6 = !from.isTranspose();
            return DenseMatrix$.MODULE$.create(x$4, x$3, x$1, x$2, x$5, x$6);
         }
      };
   }

   // $FF: synthetic method
   static CanTranspose canTranspose_DM_Complex$(final DenseMatrix_TransposeOps $this) {
      return $this.canTranspose_DM_Complex();
   }

   default CanTranspose canTranspose_DM_Complex() {
      return new CanTranspose() {
         public DenseMatrix apply(final DenseMatrix from) {
            Complex[] x$1 = (Complex[]).MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(from.data()), (x$2x) -> x$2x.conjugate(), scala.reflect.ClassTag..MODULE$.apply(Complex.class));
            int x$2 = from.offset();
            int x$3 = from.rows();
            int x$4 = from.cols();
            int x$5 = from.majorStride();
            boolean x$6 = !from.isTranspose();
            return new DenseMatrix(x$4, x$3, x$1, x$2, x$5, x$6);
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   static void $init$(final DenseMatrix_TransposeOps $this) {
   }
}
