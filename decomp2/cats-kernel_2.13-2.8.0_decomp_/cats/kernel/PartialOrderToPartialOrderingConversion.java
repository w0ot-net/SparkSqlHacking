package cats.kernel;

import scala.Option;
import scala.math.PartialOrdering;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005U2qa\u0001\u0003\u0011\u0002\u0007\u0005\u0011\u0002C\u0003\u0011\u0001\u0011\u0005\u0011\u0003C\u0003\u0016\u0001\u0011\raCA\u0014QCJ$\u0018.\u00197Pe\u0012,'\u000fV8QCJ$\u0018.\u00197Pe\u0012,'/\u001b8h\u0007>tg/\u001a:tS>t'BA\u0003\u0007\u0003\u0019YWM\u001d8fY*\tq!\u0001\u0003dCR\u001c8\u0001A\n\u0003\u0001)\u0001\"a\u0003\b\u000e\u00031Q\u0011!D\u0001\u0006g\u000e\fG.Y\u0005\u0003\u001f1\u0011a!\u00118z%\u00164\u0017A\u0002\u0013j]&$H\u0005F\u0001\u0013!\tY1#\u0003\u0002\u0015\u0019\t!QK\\5u\u0003!\u001a\u0017\r^:LKJtW\r\u001c)beRL\u0017\r\\(sI\u0016\u0014\u0018N\\4G_J\u0004\u0016M\u001d;jC2|%\u000fZ3s+\t9b\u0005\u0006\u0002\u0019_A\u0019\u0011$\t\u0013\u000f\u0005iybBA\u000e\u001f\u001b\u0005a\"BA\u000f\t\u0003\u0019a$o\\8u}%\tQ\"\u0003\u0002!\u0019\u00059\u0001/Y2lC\u001e,\u0017B\u0001\u0012$\u0005=\u0001\u0016M\u001d;jC2|%\u000fZ3sS:<'B\u0001\u0011\r!\t)c\u0005\u0004\u0001\u0005\u000b\u001d\u0012!\u0019\u0001\u0015\u0003\u0003\u0005\u000b\"!\u000b\u0017\u0011\u0005-Q\u0013BA\u0016\r\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"aC\u0017\n\u00059b!aA!os\")\u0001G\u0001a\u0002c\u0005\u0011QM\u001e\t\u0004eM\"S\"\u0001\u0003\n\u0005Q\"!\u0001\u0004)beRL\u0017\r\\(sI\u0016\u0014\b"
)
public interface PartialOrderToPartialOrderingConversion {
   // $FF: synthetic method
   static PartialOrdering catsKernelPartialOrderingForPartialOrder$(final PartialOrderToPartialOrderingConversion $this, final PartialOrder ev) {
      return $this.catsKernelPartialOrderingForPartialOrder(ev);
   }

   default PartialOrdering catsKernelPartialOrderingForPartialOrder(final PartialOrder ev) {
      return new PartialOrdering(ev) {
         private final PartialOrder ev$84;

         public boolean gteq(final Object x, final Object y) {
            return PartialOrdering.gteq$(this, x, y);
         }

         public boolean lt(final Object x, final Object y) {
            return PartialOrdering.lt$(this, x, y);
         }

         public boolean gt(final Object x, final Object y) {
            return PartialOrdering.gt$(this, x, y);
         }

         public boolean equiv(final Object x, final Object y) {
            return PartialOrdering.equiv$(this, x, y);
         }

         public PartialOrdering reverse() {
            return PartialOrdering.reverse$(this);
         }

         public Option tryCompare(final Object x, final Object y) {
            return this.ev$84.tryCompare(x, y);
         }

         public boolean lteq(final Object x, final Object y) {
            return this.ev$84.lteqv(x, y);
         }

         public {
            this.ev$84 = ev$84;
            PartialOrdering.$init$(this);
         }
      };
   }

   static void $init$(final PartialOrderToPartialOrderingConversion $this) {
   }
}
