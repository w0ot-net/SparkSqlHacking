package breeze.linalg;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005y1AAA\u0002\u0001\u0011!)1\u0004\u0001C\u00019\t!R*\u0019;sSb,U\u000e\u001d;z\u000bb\u001cW\r\u001d;j_:T!\u0001B\u0003\u0002\r1Lg.\u00197h\u0015\u00051\u0011A\u00022sK\u0016TXm\u0001\u0001\u0014\u0007\u0001Iq\u0003\u0005\u0002\u000b)9\u00111\"\u0005\b\u0003\u0019=i\u0011!\u0004\u0006\u0003\u001d\u001d\ta\u0001\u0010:p_Rt\u0014\"\u0001\t\u0002\u000bM\u001c\u0017\r\\1\n\u0005I\u0019\u0012a\u00029bG.\fw-\u001a\u0006\u0002!%\u0011QC\u0006\u0002\u0019\u00132dWmZ1m\u0003J<W/\\3oi\u0016C8-\u001a9uS>t'B\u0001\n\u0014!\tA\u0012$D\u0001\u0004\u0013\tQ2A\u0001\fMS:,\u0017M]!mO\u0016\u0014'/Y#yG\u0016\u0004H/[8o\u0003\u0019a\u0014N\\5u}Q\tQ\u0004\u0005\u0002\u0019\u0001\u0001"
)
public class MatrixEmptyException extends IllegalArgumentException implements LinearAlgebraException {
   public MatrixEmptyException() {
      super("Matrix is empty");
   }
}
