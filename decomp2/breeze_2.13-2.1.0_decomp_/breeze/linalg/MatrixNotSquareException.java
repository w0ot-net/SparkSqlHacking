package breeze.linalg;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005y1AAA\u0002\u0001\u0011!)1\u0004\u0001C\u00019\tAR*\u0019;sSbtu\u000e^*rk\u0006\u0014X-\u0012=dKB$\u0018n\u001c8\u000b\u0005\u0011)\u0011A\u00027j]\u0006dwMC\u0001\u0007\u0003\u0019\u0011'/Z3{K\u000e\u00011c\u0001\u0001\n/A\u0011!\u0002\u0006\b\u0003\u0017Eq!\u0001D\b\u000e\u00035Q!AD\u0004\u0002\rq\u0012xn\u001c;?\u0013\u0005\u0001\u0012!B:dC2\f\u0017B\u0001\n\u0014\u0003\u001d\u0001\u0018mY6bO\u0016T\u0011\u0001E\u0005\u0003+Y\u0011\u0001$\u00137mK\u001e\fG.\u0011:hk6,g\u000e^#yG\u0016\u0004H/[8o\u0015\t\u00112\u0003\u0005\u0002\u001935\t1!\u0003\u0002\u001b\u0007\t1B*\u001b8fCJ\fEnZ3ce\u0006,\u0005pY3qi&|g.\u0001\u0004=S:LGO\u0010\u000b\u0002;A\u0011\u0001\u0004\u0001"
)
public class MatrixNotSquareException extends IllegalArgumentException implements LinearAlgebraException {
   public MatrixNotSquareException() {
      super("Matrix is not square");
   }
}
