package spire.optional;

import cats.kernel.Semigroup;
import scala.collection.Factory;
import scala.reflect.ScalaSignature;
import spire.algebra.partial.Semigroupoid;

@ScalaSignature(
   bytes = "\u0006\u0005E3qa\u0001\u0003\u0011\u0002\u0007\u0005\u0011\u0002C\u0003\u0011\u0001\u0011\u0005\u0011\u0003C\u0003\u0016\u0001\u0011\raC\u0001\tQCJ$\u0018.\u00197Ji\u0016\u0014\u0018M\u00197fa)\u0011QAB\u0001\t_B$\u0018n\u001c8bY*\tq!A\u0003ta&\u0014Xm\u0001\u0001\u0014\u0005\u0001Q\u0001CA\u0006\u000f\u001b\u0005a!\"A\u0007\u0002\u000bM\u001c\u0017\r\\1\n\u0005=a!AB!osJ+g-\u0001\u0004%S:LG\u000f\n\u000b\u0002%A\u00111bE\u0005\u0003)1\u0011A!\u00168ji\u0006!\u0012\n^3sC\ndWmU3nS\u001e\u0014x.\u001e9pS\u0012,2aF\u001e#)\rAB\b\u0014\t\u00043y\u0001S\"\u0001\u000e\u000b\u0005ma\u0012a\u00029beRL\u0017\r\u001c\u0006\u0003;\u0019\tq!\u00197hK\n\u0014\u0018-\u0003\u0002 5\ta1+Z7jOJ|W\u000f]8jIB\u0019\u0011E\t\u001e\r\u0001\u0011)1E\u0001b\u0001I\t\u00111iQ\u000b\u0003KA\n\"AJ\u0015\u0011\u0005-9\u0013B\u0001\u0015\r\u0005\u001dqu\u000e\u001e5j]\u001e\u0004RAK\u00170mej\u0011a\u000b\u0006\u0003Y1\t!bY8mY\u0016\u001cG/[8o\u0013\tq3FA\u0006Ji\u0016\u0014\u0018M\u00197f\u001fB\u001c\bCA\u00111\t\u0015\t$E1\u00013\u0005\u0005\t\u0015C\u0001\u00144!\tYA'\u0003\u00026\u0019\t\u0019\u0011I\\=\u0011\u0005):\u0014B\u0001\u001d,\u0005!IE/\u001a:bE2,\u0007cA\u0011#_A\u0011\u0011e\u000f\u0003\u0006c\t\u0011\rA\r\u0005\b{\t\t\t\u0011q\u0001?\u0003))g/\u001b3f]\u000e,G%\r\t\u0004\u007f%SdB\u0001!H\u001d\t\teI\u0004\u0002C\u000b6\t1I\u0003\u0002E\u0011\u00051AH]8pizJ\u0011aB\u0005\u0003;\u0019I!\u0001\u0013\u000f\u0002\u000fA\f7m[1hK&\u0011!j\u0013\u0002\n'\u0016l\u0017n\u001a:pkBT!\u0001\u0013\u000f\t\u000b5\u0013\u00019\u0001(\u0002\u0007\r\u0014g\r\u0005\u0003+\u001fj\u0002\u0013B\u0001),\u0005\u001d1\u0015m\u0019;pef\u0004"
)
public interface PartialIterable0 {
   // $FF: synthetic method
   static Semigroupoid IterableSemigroupoid$(final PartialIterable0 $this, final Semigroup evidence$1, final Factory cbf) {
      return $this.IterableSemigroupoid(evidence$1, cbf);
   }

   default Semigroupoid IterableSemigroupoid(final Semigroup evidence$1, final Factory cbf) {
      return new IterableSemigroupoid(cbf, evidence$1);
   }

   static void $init$(final PartialIterable0 $this) {
   }
}
