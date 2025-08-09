package spire.macros;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005U1AAA\u0002\u0001\u0011!)\u0011\u0003\u0001C\u0001%\tY\u0012I]5uQ6,G/[2Pm\u0016\u0014h\r\\8x\u000bb\u001cW\r\u001d;j_:T!\u0001B\u0003\u0002\r5\f7M]8t\u0015\u00051\u0011!B:qSJ,7\u0001A\n\u0003\u0001%\u0001\"AC\b\u000e\u0003-Q!\u0001D\u0007\u0002\t1\fgn\u001a\u0006\u0002\u001d\u0005!!.\u0019<b\u0013\t\u00012BA\nBe&$\b.\\3uS\u000e,\u0005pY3qi&|g.\u0001\u0004=S:LGO\u0010\u000b\u0002'A\u0011A\u0003A\u0007\u0002\u0007\u0001"
)
public class ArithmeticOverflowException extends ArithmeticException {
   public ArithmeticOverflowException() {
      super("arithmetic overflow detected");
   }
}
