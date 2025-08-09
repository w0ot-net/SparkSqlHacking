package scala.collection.parallel;

import java.util.concurrent.ForkJoinPool;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005m1qAA\u0002\u0011\u0002G\u0005!\u0002C\u0003\u0010\u0001\u0019\u0005\u0001C\u0001\nICZLgn\u001a$pe.Tu.\u001b8Q_>d'B\u0001\u0003\u0006\u0003!\u0001\u0018M]1mY\u0016d'B\u0001\u0004\b\u0003)\u0019w\u000e\u001c7fGRLwN\u001c\u0006\u0002\u0011\u0005)1oY1mC\u000e\u00011C\u0001\u0001\f!\taQ\"D\u0001\b\u0013\tqqA\u0001\u0004B]f\u0014VMZ\u0001\rM>\u00148NS8j]B{w\u000e\\\u000b\u0002#A\u0011!#G\u0007\u0002')\u0011A#F\u0001\u000bG>t7-\u001e:sK:$(B\u0001\f\u0018\u0003\u0011)H/\u001b7\u000b\u0003a\tAA[1wC&\u0011!d\u0005\u0002\r\r>\u00148NS8j]B{w\u000e\u001c"
)
public interface HavingForkJoinPool {
   ForkJoinPool forkJoinPool();
}
