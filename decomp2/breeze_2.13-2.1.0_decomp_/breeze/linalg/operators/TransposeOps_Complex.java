package breeze.linalg.operators;

import breeze.linalg.CSCMatrix;
import breeze.linalg.CSCMatrix$;
import breeze.linalg.DenseMatrix;
import breeze.linalg.DenseMatrix$;
import breeze.linalg.DenseVector;
import breeze.linalg.SparseVector;
import breeze.linalg.support.CanTranspose;
import breeze.math.Complex;
import breeze.math.Complex$;
import java.lang.invoke.SerializedLambda;
import scala.collection.ArrayOps.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005u2q\u0001B\u0003\u0011\u0002\u0007\u0005A\u0002C\u0003\u001b\u0001\u0011\u00051\u0004C\u0003 \u0001\u0011\r\u0001\u0005C\u00035\u0001\u0011\rQG\u0001\u000bUe\u0006t7\u000f]8tK>\u00038oX\"p[BdW\r\u001f\u0006\u0003\r\u001d\t\u0011b\u001c9fe\u0006$xN]:\u000b\u0005!I\u0011A\u00027j]\u0006dwMC\u0001\u000b\u0003\u0019\u0011'/Z3{K\u000e\u00011\u0003\u0002\u0001\u000e']\u0001\"AD\t\u000e\u0003=Q\u0011\u0001E\u0001\u0006g\u000e\fG.Y\u0005\u0003%=\u0011a!\u00118z%\u00164\u0007C\u0001\u000b\u0016\u001b\u0005)\u0011B\u0001\f\u0006\u0005Q!&/\u00198ta>\u001cXm\u00149t?\u001e+g.\u001a:jGB\u0011A\u0003G\u0005\u00033\u0015\u0011\u0001\u0004R3og\u0016l\u0015\r\u001e:jq~#&/\u00198ta>\u001cXm\u00149t\u0003\u0019!\u0013N\\5uIQ\tA\u0004\u0005\u0002\u000f;%\u0011ad\u0004\u0002\u0005+:LG/A\fdC:$&/\u00198ta>\u001cXm\u0018#W?\u000e{W\u000e\u001d7fqV\t\u0011\u0005\u0005\u0003#K\u001d\nT\"A\u0012\u000b\u0005\u0011:\u0011aB:vaB|'\u000f^\u0005\u0003M\r\u0012AbQ1o)J\fgn\u001d9pg\u0016\u00042\u0001K\u0015,\u001b\u00059\u0011B\u0001\u0016\b\u0005-!UM\\:f-\u0016\u001cGo\u001c:\u0011\u00051zS\"A\u0017\u000b\u00059J\u0011\u0001B7bi\"L!\u0001M\u0017\u0003\u000f\r{W\u000e\u001d7fqB\u0019\u0001FM\u0016\n\u0005M:!a\u0003#f]N,W*\u0019;sSb\fqcY1o)J\fgn\u001d9pg\u0016|6KV0D_6\u0004H.\u001a=\u0016\u0003Y\u0002BAI\u00138uA\u0019\u0001\u0006O\u0016\n\u0005e:!\u0001D*qCJ\u001cXMV3di>\u0014\bc\u0001\u0015<W%\u0011Ah\u0002\u0002\n\u0007N\u001bU*\u0019;sSb\u0004"
)
public interface TransposeOps_Complex extends DenseMatrix_TransposeOps {
   // $FF: synthetic method
   static CanTranspose canTranspose_DV_Complex$(final TransposeOps_Complex $this) {
      return $this.canTranspose_DV_Complex();
   }

   default CanTranspose canTranspose_DV_Complex() {
      return new CanTranspose() {
         public DenseMatrix apply(final DenseVector from) {
            Complex[] x$1 = (Complex[]).MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(from.data()), (x$1x) -> x$1x.conjugate(), scala.reflect.ClassTag..MODULE$.apply(Complex.class));
            int x$2 = from.offset();
            int x$3 = from.length();
            int x$4 = 1;
            int x$5 = from.stride();
            boolean x$6 = DenseMatrix$.MODULE$.$lessinit$greater$default$6();
            return new DenseMatrix(1, x$3, x$1, x$2, x$5, x$6);
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static CanTranspose canTranspose_SV_Complex$(final TransposeOps_Complex $this) {
      return $this.canTranspose_SV_Complex();
   }

   default CanTranspose canTranspose_SV_Complex() {
      return new CanTranspose() {
         public CSCMatrix apply(final SparseVector from) {
            CSCMatrix transposedMtx = CSCMatrix$.MODULE$.zeros(1, from.length(), scala.reflect.ClassTag..MODULE$.apply(Complex.class), Complex$.MODULE$.ComplexZero());

            for(int i = 0; i < from.activeSize(); ++i) {
               int c = from.index()[i];
               transposedMtx.update(0, c, ((Complex[])from.data())[i].conjugate());
            }

            return transposedMtx;
         }
      };
   }

   static void $init$(final TransposeOps_Complex $this) {
   }
}
