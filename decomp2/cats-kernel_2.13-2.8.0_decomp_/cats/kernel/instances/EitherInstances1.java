package cats.kernel.instances;

import cats.kernel.Eq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005y2\u0001b\u0001\u0003\u0011\u0002\u0007\u0005AA\u0003\u0005\u0006#\u0001!\ta\u0005\u0005\u0006/\u0001!\u0019\u0001\u0007\u0002\u0011\u000b&$\b.\u001a:J]N$\u0018M\\2fgFR!!\u0002\u0004\u0002\u0013%t7\u000f^1oG\u0016\u001c(BA\u0004\t\u0003\u0019YWM\u001d8fY*\t\u0011\"\u0001\u0003dCR\u001c8C\u0001\u0001\f!\taq\"D\u0001\u000e\u0015\u0005q\u0011!B:dC2\f\u0017B\u0001\t\u000e\u0005\u0019\te.\u001f*fM\u00061A%\u001b8ji\u0012\u001a\u0001\u0001F\u0001\u0015!\taQ#\u0003\u0002\u0017\u001b\t!QK\\5u\u0003I\u0019\u0017\r^:Ti\u0012,\u0015OR8s\u000b&$\b.\u001a:\u0016\u0007eac\u0007F\u0002\u001bqm\u00022a\u0007\u000f\u001f\u001b\u00051\u0011BA\u000f\u0007\u0005\t)\u0015\u000f\u0005\u0003 O)*dB\u0001\u0011&\u001d\t\tC%D\u0001#\u0015\t\u0019##\u0001\u0004=e>|GOP\u0005\u0002\u001d%\u0011a%D\u0001\ba\u0006\u001c7.Y4f\u0013\tA\u0013F\u0001\u0004FSRDWM\u001d\u0006\u0003M5\u0001\"a\u000b\u0017\r\u0001\u0011)QF\u0001b\u0001]\t\t\u0011)\u0005\u00020eA\u0011A\u0002M\u0005\u0003c5\u0011qAT8uQ&tw\r\u0005\u0002\rg%\u0011A'\u0004\u0002\u0004\u0003:L\bCA\u00167\t\u00159$A1\u0001/\u0005\u0005\u0011\u0005\"B\u001d\u0003\u0001\bQ\u0014!A!\u0011\u0007ma\"\u0006C\u0003=\u0005\u0001\u000fQ(A\u0001C!\rYB$\u000e"
)
public interface EitherInstances1 {
   // $FF: synthetic method
   static Eq catsStdEqForEither$(final EitherInstances1 $this, final Eq A, final Eq B) {
      return $this.catsStdEqForEither(A, B);
   }

   default Eq catsStdEqForEither(final Eq A, final Eq B) {
      return new EitherEq(A, B);
   }

   static void $init$(final EitherInstances1 $this) {
   }
}
