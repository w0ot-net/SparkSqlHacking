package spire.std;

import cats.kernel.Monoid;
import scala.collection.Factory;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000593qa\u0001\u0003\u0011\u0002\u0007\u0005\u0011\u0002C\u0003\u0011\u0001\u0011\u0005\u0011\u0003C\u0003\u0016\u0001\u0011\raCA\tJi\u0016\u0014\u0018M\u00197f\u0013:\u001cH/\u00198dKNT!!\u0002\u0004\u0002\u0007M$HMC\u0001\b\u0003\u0015\u0019\b/\u001b:f\u0007\u0001\u0019\"\u0001\u0001\u0006\u0011\u0005-qQ\"\u0001\u0007\u000b\u00035\tQa]2bY\u0006L!a\u0004\u0007\u0003\r\u0005s\u0017PU3g\u0003\u0019!\u0013N\\5uIQ\t!\u0003\u0005\u0002\f'%\u0011A\u0003\u0004\u0002\u0005+:LG/\u0001\bJi\u0016\u0014\u0018M\u00197f\u001b>tw.\u001b3\u0016\u0007]A%\u0006\u0006\u0002\u0019\u0013B\u0019\u0011$\n\u0015\u000f\u0005i\u0011cBA\u000e!\u001d\tar$D\u0001\u001e\u0015\tq\u0002\"\u0001\u0004=e>|GOP\u0005\u0002\u000f%\u0011\u0011EB\u0001\bC2<WM\u0019:b\u0013\t\u0019C%A\u0004qC\u000e\\\u0017mZ3\u000b\u0005\u00052\u0011B\u0001\u0014(\u0005\u0019iuN\\8jI*\u00111\u0005\n\t\u0004S):E\u0002\u0001\u0003\u0006W\t\u0011\r\u0001\f\u0002\u0003\u0007\u000e+\"!\f\u001d\u0012\u00059\n\u0004CA\u00060\u0013\t\u0001DBA\u0004O_RD\u0017N\\4\u0011\u000bI*tG\u0010$\u000e\u0003MR!\u0001\u000e\u0007\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u00027g\tY\u0011\n^3sC\ndWm\u00149t!\tI\u0003\bB\u0003:U\t\u0007!HA\u0001B#\tq3\b\u0005\u0002\fy%\u0011Q\b\u0004\u0002\u0004\u0003:L\bCA D\u001d\t\u0001%I\u0004\u0002\u001d\u0003&\tQ\"\u0003\u0002$\u0019%\u0011A)\u0012\u0002\t\u0013R,'/\u00192mK*\u00111\u0005\u0004\t\u0004S):\u0004CA\u0015I\t\u0015I$A1\u0001;\u0011\u0015Q%\u0001q\u0001L\u0003\r\u0019'M\u001a\t\u0005e1;\u0005&\u0003\u0002Ng\t9a)Y2u_JL\b"
)
public interface IterableInstances {
   // $FF: synthetic method
   static Monoid IterableMonoid$(final IterableInstances $this, final Factory cbf) {
      return $this.IterableMonoid(cbf);
   }

   default Monoid IterableMonoid(final Factory cbf) {
      return new IterableMonoid(cbf);
   }

   static void $init$(final IterableInstances $this) {
   }
}
