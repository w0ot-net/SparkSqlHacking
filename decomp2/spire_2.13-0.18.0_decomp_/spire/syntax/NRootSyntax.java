package spire.syntax;

import scala.reflect.ScalaSignature;
import spire.algebra.NRoot;

@ScalaSignature(
   bytes = "\u0006\u0005I2qa\u0001\u0003\u0011\u0002\u0007\u0005\u0011\u0002C\u0003\u0011\u0001\u0011\u0005\u0011\u0003C\u0003\u0016\u0001\u0011\raCA\u0006O%>|GoU=oi\u0006D(BA\u0003\u0007\u0003\u0019\u0019\u0018P\u001c;bq*\tq!A\u0003ta&\u0014Xm\u0001\u0001\u0014\u0005\u0001Q\u0001CA\u0006\u000f\u001b\u0005a!\"A\u0007\u0002\u000bM\u001c\u0017\r\\1\n\u0005=a!AB!osJ+g-\u0001\u0004%S:LG\u000f\n\u000b\u0002%A\u00111bE\u0005\u0003)1\u0011A!\u00168ji\u0006AaN]8pi>\u00038/\u0006\u0002\u0018?Q\u0011\u0001\u0004\r\u000b\u00033!\u00022AG\u000e\u001e\u001b\u0005!\u0011B\u0001\u000f\u0005\u0005!q%k\\8u\u001fB\u001c\bC\u0001\u0010 \u0019\u0001!Q\u0001\t\u0002C\u0002\u0005\u0012\u0011!Q\t\u0003E\u0015\u0002\"aC\u0012\n\u0005\u0011b!a\u0002(pi\"Lgn\u001a\t\u0003\u0017\u0019J!a\n\u0007\u0003\u0007\u0005s\u0017\u0010C\u0004*\u0005\u0005\u0005\t9\u0001\u0016\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$\u0013\u0007\u000f\t\u0004W9jR\"\u0001\u0017\u000b\u000552\u0011aB1mO\u0016\u0014'/Y\u0005\u0003_1\u0012QA\u0014*p_RDQ!\r\u0002A\u0002u\t\u0011!\u0019"
)
public interface NRootSyntax {
   // $FF: synthetic method
   static NRootOps nrootOps$(final NRootSyntax $this, final Object a, final NRoot evidence$18) {
      return $this.nrootOps(a, evidence$18);
   }

   default NRootOps nrootOps(final Object a, final NRoot evidence$18) {
      return new NRootOps(a, evidence$18);
   }

   static void $init$(final NRootSyntax $this) {
   }
}
