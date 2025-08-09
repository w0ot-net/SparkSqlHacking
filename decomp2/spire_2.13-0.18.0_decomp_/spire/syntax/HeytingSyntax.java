package spire.syntax;

import algebra.lattice.Heyting;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00013qa\u0001\u0003\u0011\u0002\u0007\u0005\u0011\u0002C\u0003\u0011\u0001\u0011\u0005\u0011\u0003C\u0003\u0016\u0001\u0011\raCA\u0007IKf$\u0018N\\4Ts:$\u0018\r\u001f\u0006\u0003\u000b\u0019\taa]=oi\u0006D(\"A\u0004\u0002\u000bM\u0004\u0018N]3\u0004\u0001M\u0011\u0001A\u0003\t\u0003\u00179i\u0011\u0001\u0004\u0006\u0002\u001b\u0005)1oY1mC&\u0011q\u0002\u0004\u0002\u0007\u0003:L(+\u001a4\u0002\r\u0011Jg.\u001b;%)\u0005\u0011\u0002CA\u0006\u0014\u0013\t!BB\u0001\u0003V]&$\u0018A\u00035fsRLgnZ(qgV\u0011qc\b\u000b\u00031y\"\"!\u0007\u0015\u0011\u0007iYR$D\u0001\u0005\u0013\taBA\u0001\u0006IKf$\u0018N\\4PaN\u0004\"AH\u0010\r\u0001\u0011)\u0001E\u0001b\u0001C\t\t\u0011)\u0005\u0002#KA\u00111bI\u0005\u0003I1\u0011qAT8uQ&tw\r\u0005\u0002\fM%\u0011q\u0005\u0004\u0002\u0004\u0003:L\bbB\u0015\u0003\u0003\u0003\u0005\u001dAK\u0001\fKZLG-\u001a8dK\u0012\u0012$\u0007E\u0002,wuq!\u0001\f\u001d\u000f\u00055*dB\u0001\u00184\u001d\ty#'D\u00011\u0015\t\t\u0004\"\u0001\u0004=e>|GOP\u0005\u0002\u000f%\u0011AGB\u0001\bC2<WM\u0019:b\u0013\t1t'A\u0004mCR$\u0018nY3\u000b\u0005Q2\u0011BA\u001d;\u0003\u001d\u0001\u0018mY6bO\u0016T!AN\u001c\n\u0005qj$a\u0002%fsRLgn\u001a\u0006\u0003siBQa\u0010\u0002A\u0002u\t\u0011!\u0019"
)
public interface HeytingSyntax {
   // $FF: synthetic method
   static HeytingOps heytingOps$(final HeytingSyntax $this, final Object a, final Heyting evidence$22) {
      return $this.heytingOps(a, evidence$22);
   }

   default HeytingOps heytingOps(final Object a, final Heyting evidence$22) {
      return new HeytingOps(a, evidence$22);
   }

   static void $init$(final HeytingSyntax $this) {
   }
}
