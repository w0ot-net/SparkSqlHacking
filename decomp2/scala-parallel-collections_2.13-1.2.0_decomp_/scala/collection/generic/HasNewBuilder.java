package scala.collection.generic;

import scala.collection.mutable.Builder;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000552qAA\u0002\u0011\u0002G\u0005!\u0002\u0003\u0004\u0011\u0001\u00016\t\"\u0005\u0002\u000e\u0011\u0006\u001ch*Z<Ck&dG-\u001a:\u000b\u0005\u0011)\u0011aB4f]\u0016\u0014\u0018n\u0019\u0006\u0003\r\u001d\t!bY8mY\u0016\u001cG/[8o\u0015\u0005A\u0011!B:dC2\f7\u0001A\u000b\u0004\u0017mY3C\u0001\u0001\r!\tia\"D\u0001\b\u0013\tyqAA\u0002B]f\f!B\\3x\u0005VLG\u000eZ3s+\u0005\u0011\u0002\u0003B\n\u00171)j\u0011\u0001\u0006\u0006\u0003+\u0015\tq!\\;uC\ndW-\u0003\u0002\u0018)\t9!)^5mI\u0016\u0014(FA\r\"!\tQ2\u0004\u0004\u0001\u0005\rq\u0001AQ1\u0001\u001e\u0005\u0005\t\u0015C\u0001\u0010\r!\tiq$\u0003\u0002!\u000f\t9aj\u001c;iS:<7&\u0001\u0012\u0011\u0005\rBS\"\u0001\u0013\u000b\u0005\u00152\u0013!C;oG\",7m[3e\u0015\t9s!\u0001\u0006b]:|G/\u0019;j_:L!!\u000b\u0013\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW\r\u0005\u0002\u001bW\u00111A\u0006\u0001CC\u0002u\u0011AAU3qe\u0002"
)
public interface HasNewBuilder {
   Builder newBuilder();
}
