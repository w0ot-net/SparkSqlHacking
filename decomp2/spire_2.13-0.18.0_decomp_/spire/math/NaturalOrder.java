package spire.math;

import cats.kernel.Order;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005M3\u0001\"\u0003\u0006\u0011\u0002\u0007\u0005!B\u0004\u0005\u0006U\u0001!\ta\u000b\u0005\u0006_\u0001!\t\u0005\r\u0005\u0006q\u0001!\t%\u000f\u0005\u0006y\u0001!\t%\u0010\u0005\u0006\u0001\u0002!\t%\u0011\u0005\u0006\t\u0002!\t%\u0012\u0005\u0006\u0011\u0002!\t%\u0013\u0005\u0006\u0019\u0002!\t!\u0014\u0002\r\u001d\u0006$XO]1m\u001fJ$WM\u001d\u0006\u0003\u00171\tA!\\1uQ*\tQ\"A\u0003ta&\u0014XmE\u0002\u0001\u001fU\u0001\"\u0001E\n\u000e\u0003EQ\u0011AE\u0001\u0006g\u000e\fG.Y\u0005\u0003)E\u0011a!\u00118z%\u00164\u0007c\u0001\f$M9\u0011q\u0003\t\b\u00031yq!!G\u000f\u000e\u0003iQ!a\u0007\u000f\u0002\rq\u0012xn\u001c;?\u0007\u0001I\u0011!D\u0005\u0003?1\tq!\u00197hK\n\u0014\u0018-\u0003\u0002\"E\u00059\u0001/Y2lC\u001e,'BA\u0010\r\u0013\t!SEA\u0003Pe\u0012,'O\u0003\u0002\"EA\u0011q\u0005K\u0007\u0002\u0015%\u0011\u0011F\u0003\u0002\b\u001d\u0006$XO]1m\u0003\u0019!\u0013N\\5uIQ\tA\u0006\u0005\u0002\u0011[%\u0011a&\u0005\u0002\u0005+:LG/A\u0002fcZ$2!\r\u001b7!\t\u0001\"'\u0003\u00024#\t9!i\\8mK\u0006t\u0007\"B\u001b\u0003\u0001\u00041\u0013!\u0001=\t\u000b]\u0012\u0001\u0019\u0001\u0014\u0002\u0003e\fAA\\3rmR\u0019\u0011GO\u001e\t\u000bU\u001a\u0001\u0019\u0001\u0014\t\u000b]\u001a\u0001\u0019\u0001\u0014\u0002\u0005\u001d$HcA\u0019?\u007f!)Q\u0007\u0002a\u0001M!)q\u0007\u0002a\u0001M\u0005)q\r^3rmR\u0019\u0011GQ\"\t\u000bU*\u0001\u0019\u0001\u0014\t\u000b]*\u0001\u0019\u0001\u0014\u0002\u00051$HcA\u0019G\u000f\")QG\u0002a\u0001M!)qG\u0002a\u0001M\u0005)A\u000e^3rmR\u0019\u0011GS&\t\u000bU:\u0001\u0019\u0001\u0014\t\u000b]:\u0001\u0019\u0001\u0014\u0002\u000f\r|W\u000e]1sKR\u0019a*\u0015*\u0011\u0005Ay\u0015B\u0001)\u0012\u0005\rIe\u000e\u001e\u0005\u0006k!\u0001\rA\n\u0005\u0006o!\u0001\rA\n"
)
public interface NaturalOrder extends Order {
   // $FF: synthetic method
   static boolean eqv$(final NaturalOrder $this, final Natural x, final Natural y) {
      return $this.eqv(x, y);
   }

   default boolean eqv(final Natural x, final Natural y) {
      return BoxesRunTime.equalsNumNum(x, y);
   }

   // $FF: synthetic method
   static boolean neqv$(final NaturalOrder $this, final Natural x, final Natural y) {
      return $this.neqv(x, y);
   }

   default boolean neqv(final Natural x, final Natural y) {
      return !BoxesRunTime.equalsNumNum(x, y);
   }

   // $FF: synthetic method
   static boolean gt$(final NaturalOrder $this, final Natural x, final Natural y) {
      return $this.gt(x, y);
   }

   default boolean gt(final Natural x, final Natural y) {
      return x.$greater(y);
   }

   // $FF: synthetic method
   static boolean gteqv$(final NaturalOrder $this, final Natural x, final Natural y) {
      return $this.gteqv(x, y);
   }

   default boolean gteqv(final Natural x, final Natural y) {
      return x.$greater$eq(y);
   }

   // $FF: synthetic method
   static boolean lt$(final NaturalOrder $this, final Natural x, final Natural y) {
      return $this.lt(x, y);
   }

   default boolean lt(final Natural x, final Natural y) {
      return x.$less(y);
   }

   // $FF: synthetic method
   static boolean lteqv$(final NaturalOrder $this, final Natural x, final Natural y) {
      return $this.lteqv(x, y);
   }

   default boolean lteqv(final Natural x, final Natural y) {
      return x.$less$eq(y);
   }

   // $FF: synthetic method
   static int compare$(final NaturalOrder $this, final Natural x, final Natural y) {
      return $this.compare(x, y);
   }

   default int compare(final Natural x, final Natural y) {
      return x.compare(y);
   }

   static void $init$(final NaturalOrder $this) {
   }
}
