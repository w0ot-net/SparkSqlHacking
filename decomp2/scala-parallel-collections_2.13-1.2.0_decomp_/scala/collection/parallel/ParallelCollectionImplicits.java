package scala.collection.parallel;

import scala.collection.IterableOnce;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u00055:a\u0001B\u0003\t\u0002\u001dYaAB\u0007\u0006\u0011\u00039a\u0002C\u0003\u0014\u0003\u0011\u0005Q\u0003C\u0003\u0017\u0003\u0011\rq#A\u000eQCJ\fG\u000e\\3m\u0007>dG.Z2uS>t\u0017*\u001c9mS\u000eLGo\u001d\u0006\u0003\r\u001d\t\u0001\u0002]1sC2dW\r\u001c\u0006\u0003\u0011%\t!bY8mY\u0016\u001cG/[8o\u0015\u0005Q\u0011!B:dC2\f\u0007C\u0001\u0007\u0002\u001b\u0005)!a\u0007)be\u0006dG.\u001a7D_2dWm\u0019;j_:LU\u000e\u001d7jG&$8o\u0005\u0002\u0002\u001fA\u0011\u0001#E\u0007\u0002\u0013%\u0011!#\u0003\u0002\u0007\u0003:L(+\u001a4\u0002\rqJg.\u001b;?\u0007\u0001!\u0012aC\u0001\u0010iJ\fg/\u001a:tC\ndWMM8qgV\u0011\u0001D\b\u000b\u00033\u001d\u00022\u0001\u0004\u000e\u001d\u0013\tYRA\u0001\bUe\u00064XM]:bE2,w\n]:\u0011\u0005uqB\u0002\u0001\u0003\u0006?\r\u0011\r\u0001\t\u0002\u0002)F\u0011\u0011\u0005\n\t\u0003!\tJ!aI\u0005\u0003\u000f9{G\u000f[5oOB\u0011\u0001#J\u0005\u0003M%\u00111!\u00118z\u0011\u0015A3\u00011\u0001*\u0003\u0005!\bc\u0001\u0016,95\tq!\u0003\u0002-\u000f\ta\u0011\n^3sC\ndWm\u00148dK\u0002"
)
public final class ParallelCollectionImplicits {
   public static TraversableOps traversable2ops(final IterableOnce t) {
      return ParallelCollectionImplicits$.MODULE$.traversable2ops(t);
   }
}
