package cats.kernel;

import scala.math.Equiv;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005=2qa\u0001\u0003\u0011\u0002\u0007\u0005\u0011\u0002C\u0003\u0011\u0001\u0011\u0005\u0011\u0003C\u0003\u0016\u0001\u0011\raCA\nFcR{W)];jm\u000e{gN^3sg&|gN\u0003\u0002\u0006\r\u000511.\u001a:oK2T\u0011aB\u0001\u0005G\u0006$8o\u0001\u0001\u0014\u0005\u0001Q\u0001CA\u0006\u000f\u001b\u0005a!\"A\u0007\u0002\u000bM\u001c\u0017\r\\1\n\u0005=a!AB!osJ+g-\u0001\u0004%S:LG\u000f\n\u000b\u0002%A\u00111bE\u0005\u0003)1\u0011A!\u00168ji\u0006!2-\u0019;t\u0017\u0016\u0014h.\u001a7FcVLgOR8s\u000bF,\"a\u0006\u0011\u0015\u0005aI\u0003cA\r\u001d=5\t!D\u0003\u0002\u001c\u0019\u0005!Q.\u0019;i\u0013\ti\"DA\u0003FcVLg\u000f\u0005\u0002 A1\u0001A!B\u0011\u0003\u0005\u0004\u0011#!A!\u0012\u0005\r2\u0003CA\u0006%\u0013\t)CBA\u0004O_RD\u0017N\\4\u0011\u0005-9\u0013B\u0001\u0015\r\u0005\r\te.\u001f\u0005\u0006U\t\u0001\u001daK\u0001\u0003KZ\u00042\u0001L\u0017\u001f\u001b\u0005!\u0011B\u0001\u0018\u0005\u0005\t)\u0015\u000f"
)
public interface EqToEquivConversion {
   // $FF: synthetic method
   static Equiv catsKernelEquivForEq$(final EqToEquivConversion $this, final Eq ev) {
      return $this.catsKernelEquivForEq(ev);
   }

   default Equiv catsKernelEquivForEq(final Eq ev) {
      return new Equiv(ev) {
         private final Eq ev$1;

         public boolean equiv(final Object a, final Object b) {
            return this.ev$1.eqv(a, b);
         }

         public {
            this.ev$1 = ev$1;
         }
      };
   }

   static void $init$(final EqToEquivConversion $this) {
   }
}
