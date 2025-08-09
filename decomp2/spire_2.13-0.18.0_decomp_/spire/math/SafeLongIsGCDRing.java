package spire.math;

import algebra.ring.GCDRing;
import cats.kernel.Eq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005}2\u0001\u0002B\u0003\u0011\u0002\u0007\u0005Q!\u0003\u0005\u0006Q\u0001!\t!\u000b\u0005\u0006[\u0001!\tA\f\u0005\u0006s\u0001!\tA\u000f\u0002\u0012'\u00064W\rT8oO&\u001bxi\u0011#SS:<'B\u0001\u0004\b\u0003\u0011i\u0017\r\u001e5\u000b\u0003!\tQa\u001d9je\u0016\u001cB\u0001\u0001\u0006\u0011KA\u00111BD\u0007\u0002\u0019)\tQ\"A\u0003tG\u0006d\u0017-\u0003\u0002\u0010\u0019\t1\u0011I\\=SK\u001a\u00042!\u0005\u0010\"\u001d\t\u00112D\u0004\u0002\u001439\u0011A\u0003G\u0007\u0002+)\u0011acF\u0001\u0007yI|w\u000e\u001e \u0004\u0001%\t\u0001\"\u0003\u0002\u001b\u000f\u00059\u0011\r\\4fEJ\f\u0017B\u0001\u000f\u001e\u0003\u001d\u0001\u0018mY6bO\u0016T!AG\u0004\n\u0005}\u0001#aB$D\tJKgn\u001a\u0006\u00039u\u0001\"AI\u0012\u000e\u0003\u0015I!\u0001J\u0003\u0003\u0011M\u000bg-\u001a'p]\u001e\u0004\"A\t\u0014\n\u0005\u001d*!aD*bM\u0016duN\\4Jg\u000e\u0013\u0016N\\4\u0002\r\u0011Jg.\u001b;%)\u0005Q\u0003CA\u0006,\u0013\taCB\u0001\u0003V]&$\u0018a\u00017d[R\u0019q&N\u001c\u0015\u0005\u0005\u0002\u0004\"B\u0019\u0003\u0001\b\u0011\u0014AA3w!\r\t2'I\u0005\u0003i\u0001\u0012!!R9\t\u000bY\u0012\u0001\u0019A\u0011\u0002\u0003\u0005DQ\u0001\u000f\u0002A\u0002\u0005\n\u0011AY\u0001\u0004O\u000e$GcA\u001e>}Q\u0011\u0011\u0005\u0010\u0005\u0006c\r\u0001\u001dA\r\u0005\u0006m\r\u0001\r!\t\u0005\u0006q\r\u0001\r!\t"
)
public interface SafeLongIsGCDRing extends GCDRing, SafeLongIsCRing {
   // $FF: synthetic method
   static SafeLong lcm$(final SafeLongIsGCDRing $this, final SafeLong a, final SafeLong b, final Eq ev) {
      return $this.lcm(a, b, ev);
   }

   default SafeLong lcm(final SafeLong a, final SafeLong b, final Eq ev) {
      return a.lcm(b);
   }

   // $FF: synthetic method
   static SafeLong gcd$(final SafeLongIsGCDRing $this, final SafeLong a, final SafeLong b, final Eq ev) {
      return $this.gcd(a, b, ev);
   }

   default SafeLong gcd(final SafeLong a, final SafeLong b, final Eq ev) {
      return a.gcd(b);
   }

   static void $init$(final SafeLongIsGCDRing $this) {
   }
}
