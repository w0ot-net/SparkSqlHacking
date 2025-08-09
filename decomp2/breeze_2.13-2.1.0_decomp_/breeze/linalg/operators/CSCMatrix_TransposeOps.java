package breeze.linalg.operators;

import breeze.linalg.CSCMatrix;
import breeze.linalg.CSCMatrix$;
import breeze.linalg.support.CanTranspose;
import breeze.math.Complex;
import breeze.math.Complex$;
import breeze.math.Semiring;
import breeze.storage.Zero;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005M3q\u0001B\u0003\u0011\u0002\u0007\u0005A\u0002C\u0003\u0018\u0001\u0011\u0005\u0001\u0004C\u0003\u001d\u0001\u0011\rQ\u0004C\u0003M\u0001\u0011\rQJ\u0001\fD'\u000ek\u0015\r\u001e:jq~#&/\u00198ta>\u001cXm\u00149t\u0015\t1q!A\u0005pa\u0016\u0014\u0018\r^8sg*\u0011\u0001\"C\u0001\u0007Y&t\u0017\r\\4\u000b\u0003)\taA\u0019:fKj,7\u0001A\n\u0004\u00015\u0019\u0002C\u0001\b\u0012\u001b\u0005y!\"\u0001\t\u0002\u000bM\u001c\u0017\r\\1\n\u0005Iy!AB!osJ+g\r\u0005\u0002\u0015+5\tQ!\u0003\u0002\u0017\u000b\t!BK]1ogB|7/Z(qg~;UM\\3sS\u000e\fa\u0001J5oSR$C#A\r\u0011\u00059Q\u0012BA\u000e\u0010\u0005\u0011)f.\u001b;\u0002!\r\fg\u000e\u0016:b]N\u0004xn]3`\u0007N\u001bUC\u0001\u0010,)\u0011yB\u0007\u0010#\u0011\t\u0001\u001aS%J\u0007\u0002C)\u0011!eB\u0001\bgV\u0004\bo\u001c:u\u0013\t!\u0013E\u0001\u0007DC:$&/\u00198ta>\u001cX\rE\u0002'O%j\u0011aB\u0005\u0003Q\u001d\u0011\u0011bQ*D\u001b\u0006$(/\u001b=\u0011\u0005)ZC\u0002\u0001\u0003\u0006Y\t\u0011\r!\f\u0002\u0002-F\u0011a&\r\t\u0003\u001d=J!\u0001M\b\u0003\u000f9{G\u000f[5oOB\u0011aBM\u0005\u0003g=\u00111!\u00118z\u0011\u001d)$!!AA\u0004Y\n!\"\u001a<jI\u0016t7-\u001a\u00132!\r9$(K\u0007\u0002q)\u0011\u0011hD\u0001\be\u00164G.Z2u\u0013\tY\u0004H\u0001\u0005DY\u0006\u001c8\u000fV1h\u0011\u001di$!!AA\u0004y\n!\"\u001a<jI\u0016t7-\u001a\u00133!\ry$)K\u0007\u0002\u0001*\u0011\u0011)C\u0001\bgR|'/Y4f\u0013\t\u0019\u0005I\u0001\u0003[KJ|\u0007bB#\u0003\u0003\u0003\u0005\u001dAR\u0001\u000bKZLG-\u001a8dK\u0012\u001a\u0004cA$KS5\t\u0001J\u0003\u0002J\u0013\u0005!Q.\u0019;i\u0013\tY\u0005J\u0001\u0005TK6L'/\u001b8h\u0003a\u0019\u0017M\u001c+sC:\u001c\bo\\:f?\u000e\u001b6iX\"p[BdW\r_\u000b\u0002\u001dB!\u0001eI(P!\r1s\u0005\u0015\t\u0003\u000fFK!A\u0015%\u0003\u000f\r{W\u000e\u001d7fq\u0002"
)
public interface CSCMatrix_TransposeOps extends TransposeOps_Generic {
   // $FF: synthetic method
   static CanTranspose canTranspose_CSC$(final CSCMatrix_TransposeOps $this, final ClassTag evidence$1, final Zero evidence$2, final Semiring evidence$3) {
      return $this.canTranspose_CSC(evidence$1, evidence$2, evidence$3);
   }

   default CanTranspose canTranspose_CSC(final ClassTag evidence$1, final Zero evidence$2, final Semiring evidence$3) {
      return new CanTranspose(evidence$1, evidence$3, evidence$2) {
         private final ClassTag evidence$1$1;
         private final Semiring evidence$3$1;
         private final Zero evidence$2$1;

         public CSCMatrix apply(final CSCMatrix from) {
            CSCMatrix.Builder transposedMtx = new CSCMatrix.Builder(from.cols(), from.rows(), from.activeSize(), this.evidence$1$1, this.evidence$3$1, this.evidence$2$1);

            for(int j = 0; j < from.cols(); ++j) {
               for(int ip = from.colPtrs()[j]; ip < from.colPtrs()[j + 1]; ++ip) {
                  int i = from.rowIndices()[ip];
                  transposedMtx.add(j, i, .MODULE$.array_apply(from.data(), ip));
               }
            }

            return transposedMtx.result(false, false);
         }

         public {
            this.evidence$1$1 = evidence$1$1;
            this.evidence$3$1 = evidence$3$1;
            this.evidence$2$1 = evidence$2$1;
         }
      };
   }

   // $FF: synthetic method
   static CanTranspose canTranspose_CSC_Complex$(final CSCMatrix_TransposeOps $this) {
      return $this.canTranspose_CSC_Complex();
   }

   default CanTranspose canTranspose_CSC_Complex() {
      return new CanTranspose() {
         public CSCMatrix apply(final CSCMatrix from) {
            CSCMatrix transposedMtx = CSCMatrix$.MODULE$.zeros(from.cols(), from.rows(), scala.reflect.ClassTag..MODULE$.apply(Complex.class), Complex$.MODULE$.ComplexZero());

            for(int j = 0; j < from.cols(); ++j) {
               for(int ip = from.colPtrs()[j]; ip < from.colPtrs()[j + 1]; ++ip) {
                  int i = from.rowIndices()[ip];
                  transposedMtx.update(j, i, ((Complex[])from.data())[ip].conjugate());
               }
            }

            return transposedMtx;
         }
      };
   }

   static void $init$(final CSCMatrix_TransposeOps $this) {
   }
}
