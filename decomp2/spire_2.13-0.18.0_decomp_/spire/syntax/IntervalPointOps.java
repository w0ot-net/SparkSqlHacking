package spire.syntax;

import algebra.ring.AdditiveGroup;
import cats.kernel.Order;
import scala.reflect.ScalaSignature;
import spire.math.Interval;
import spire.math.Interval$;

@ScalaSignature(
   bytes = "\u0006\u0005-3Aa\u0002\u0005\u0003\u001b!AQ\u0003\u0001B\u0001B\u0003%a\u0003\u0003\u0005\"\u0001\t\u0005\t\u0015a\u0003#\u0011!\u0011\u0004A!A!\u0002\u0017\u0019\u0004\"\u0002\u001c\u0001\t\u00039\u0004\"\u0002 \u0001\t\u0003y\u0004\"\u0002%\u0001\t\u0003I%\u0001E%oi\u0016\u0014h/\u00197Q_&tGo\u00149t\u0015\tI!\"\u0001\u0004ts:$\u0018\r\u001f\u0006\u0002\u0017\u0005)1\u000f]5sK\u000e\u0001QC\u0001\b\u0019'\t\u0001q\u0002\u0005\u0002\u0011'5\t\u0011CC\u0001\u0013\u0003\u0015\u00198-\u00197b\u0013\t!\u0012C\u0001\u0004B]f\u0014VMZ\u0001\u0004Y\"\u001c\bCA\f\u0019\u0019\u0001!Q!\u0007\u0001C\u0002i\u0011\u0011!Q\t\u00037y\u0001\"\u0001\u0005\u000f\n\u0005u\t\"a\u0002(pi\"Lgn\u001a\t\u0003!}I!\u0001I\t\u0003\u0007\u0005s\u00170A\u0001p!\r\u0019sF\u0006\b\u0003I1r!!\n\u0016\u000f\u0005\u0019JS\"A\u0014\u000b\u0005!b\u0011A\u0002\u001fs_>$h(C\u0001\f\u0013\tY#\"A\u0004bY\u001e,'M]1\n\u00055r\u0013a\u00029bG.\fw-\u001a\u0006\u0003W)I!\u0001M\u0019\u0003\u000b=\u0013H-\u001a:\u000b\u00055r\u0013AA3w!\r\u0019CGF\u0005\u0003kE\u0012Q\"\u00113eSRLg/Z$s_V\u0004\u0018A\u0002\u001fj]&$h\b\u0006\u00029{Q\u0019\u0011h\u000f\u001f\u0011\u0007i\u0002a#D\u0001\t\u0011\u0015\tC\u0001q\u0001#\u0011\u0015\u0011D\u0001q\u00014\u0011\u0015)B\u00011\u0001\u0017\u0003\u0019!S\u000f\r\u0019CcQ\u0011\u0001I\u0012\t\u0004\u0003\u00123R\"\u0001\"\u000b\u0005\rS\u0011\u0001B7bi\"L!!\u0012\"\u0003\u0011%sG/\u001a:wC2DQaR\u0003A\u0002Y\t1A\u001d5t\u0003=!\u0003\u000f\\;tI\u0011Lg\u000fJ7j]V\u001cHC\u0001!K\u0011\u00159e\u00011\u0001\u0017\u0001"
)
public final class IntervalPointOps {
   private final Object lhs;
   private final Order o;
   private final AdditiveGroup ev;

   public Interval $u00B1(final Object rhs) {
      return Interval$.MODULE$.apply(this.ev.minus(this.lhs, rhs), this.ev.plus(this.lhs, rhs), this.o);
   }

   public Interval $plus$div$minus(final Object rhs) {
      return Interval$.MODULE$.apply(this.ev.minus(this.lhs, rhs), this.ev.plus(this.lhs, rhs), this.o);
   }

   public IntervalPointOps(final Object lhs, final Order o, final AdditiveGroup ev) {
      this.lhs = lhs;
      this.o = o;
      this.ev = ev;
   }
}
