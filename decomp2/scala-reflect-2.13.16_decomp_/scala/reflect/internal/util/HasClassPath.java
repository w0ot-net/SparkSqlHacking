package scala.reflect.internal.util;

import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\t2qAA\u0002\u0011\u0002G\u0005A\u0002C\u0003\u0012\u0001\u0019\u0005!C\u0001\u0007ICN\u001cE.Y:t!\u0006$\bN\u0003\u0002\u0005\u000b\u0005!Q\u000f^5m\u0015\t1q!\u0001\u0005j]R,'O\\1m\u0015\tA\u0011\"A\u0004sK\u001adWm\u0019;\u000b\u0003)\tQa]2bY\u0006\u001c\u0001a\u0005\u0002\u0001\u001bA\u0011abD\u0007\u0002\u0013%\u0011\u0001#\u0003\u0002\u0007\u0003:L(+\u001a4\u0002\u001b\rd\u0017m]:QCRDWK\u0015't+\u0005\u0019\u0002c\u0001\u000b\u001859\u0011a\"F\u0005\u0003-%\tq\u0001]1dW\u0006<W-\u0003\u0002\u00193\t\u00191+Z9\u000b\u0005YI\u0001CA\u000e!\u001b\u0005a\"BA\u000f\u001f\u0003\rqW\r\u001e\u0006\u0002?\u0005!!.\u0019<b\u0013\t\tCDA\u0002V%2\u0003"
)
public interface HasClassPath {
   Seq classPathURLs();
}
