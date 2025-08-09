package spire.syntax;

import scala.reflect.ScalaSignature;
import spire.algebra.partial.Semigroupoid;

@ScalaSignature(
   bytes = "\u0006\u0005Q2qa\u0001\u0003\u0011\u0002\u0007\u0005\u0011\u0002C\u0003\u0011\u0001\u0011\u0005\u0011\u0003C\u0003\u0016\u0001\u0011\raC\u0001\nTK6LwM]8va>LGmU=oi\u0006D(BA\u0003\u0007\u0003\u0019\u0019\u0018P\u001c;bq*\tq!A\u0003ta&\u0014Xm\u0001\u0001\u0014\u0005\u0001Q\u0001CA\u0006\u000f\u001b\u0005a!\"A\u0007\u0002\u000bM\u001c\u0017\r\\1\n\u0005=a!AB!osJ+g-\u0001\u0004%S:LG\u000f\n\u000b\u0002%A\u00111bE\u0005\u0003)1\u0011A!\u00168ji\u0006y1/Z7jOJ|W\u000f]8jI>\u00038/\u0006\u0002\u0018?Q\u0011\u0001D\r\u000b\u00033!\u00022AG\u000e\u001e\u001b\u0005!\u0011B\u0001\u000f\u0005\u0005=\u0019V-\\5he>,\bo\\5e\u001fB\u001c\bC\u0001\u0010 \u0019\u0001!Q\u0001\t\u0002C\u0002\u0005\u0012\u0011!Q\t\u0003E\u0015\u0002\"aC\u0012\n\u0005\u0011b!a\u0002(pi\"Lgn\u001a\t\u0003\u0017\u0019J!a\n\u0007\u0003\u0007\u0005s\u0017\u0010C\u0004*\u0005\u0005\u0005\t9\u0001\u0016\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$\u0003\bE\u0002,aui\u0011\u0001\f\u0006\u0003[9\nq\u0001]1si&\fGN\u0003\u00020\r\u00059\u0011\r\\4fEJ\f\u0017BA\u0019-\u00051\u0019V-\\5he>,\bo\\5e\u0011\u0015\u0019$\u00011\u0001\u001e\u0003\u0005\t\u0007"
)
public interface SemigroupoidSyntax {
   // $FF: synthetic method
   static SemigroupoidOps semigroupoidOps$(final SemigroupoidSyntax $this, final Object a, final Semigroupoid evidence$8) {
      return $this.semigroupoidOps(a, evidence$8);
   }

   default SemigroupoidOps semigroupoidOps(final Object a, final Semigroupoid evidence$8) {
      return new SemigroupoidOps(a, evidence$8);
   }

   static void $init$(final SemigroupoidSyntax $this) {
   }
}
