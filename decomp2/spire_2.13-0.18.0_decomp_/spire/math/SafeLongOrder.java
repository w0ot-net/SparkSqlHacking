package spire.math;

import cats.kernel.Order;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005M3\u0001\"\u0003\u0006\u0011\u0002\u0007\u0005!B\u0004\u0005\u0006U\u0001!\ta\u000b\u0005\u0006_\u0001!\t\u0005\r\u0005\u0006q\u0001!\t%\u000f\u0005\u0006y\u0001!\t%\u0010\u0005\u0006\u0001\u0002!\t%\u0011\u0005\u0006\t\u0002!\t%\u0012\u0005\u0006\u0011\u0002!\t%\u0013\u0005\u0006\u0019\u0002!\t!\u0014\u0002\u000e'\u00064W\rT8oO>\u0013H-\u001a:\u000b\u0005-a\u0011\u0001B7bi\"T\u0011!D\u0001\u0006gBL'/Z\n\u0004\u0001=)\u0002C\u0001\t\u0014\u001b\u0005\t\"\"\u0001\n\u0002\u000bM\u001c\u0017\r\\1\n\u0005Q\t\"AB!osJ+g\rE\u0002\u0017G\u0019r!a\u0006\u0011\u000f\u0005aqbBA\r\u001e\u001b\u0005Q\"BA\u000e\u001d\u0003\u0019a$o\\8u}\r\u0001\u0011\"A\u0007\n\u0005}a\u0011aB1mO\u0016\u0014'/Y\u0005\u0003C\t\nq\u0001]1dW\u0006<WM\u0003\u0002 \u0019%\u0011A%\n\u0002\u0006\u001fJ$WM\u001d\u0006\u0003C\t\u0002\"a\n\u0015\u000e\u0003)I!!\u000b\u0006\u0003\u0011M\u000bg-\u001a'p]\u001e\fa\u0001J5oSR$C#\u0001\u0017\u0011\u0005Ai\u0013B\u0001\u0018\u0012\u0005\u0011)f.\u001b;\u0002\u0007\u0015\fh\u000fF\u00022iY\u0002\"\u0001\u0005\u001a\n\u0005M\n\"a\u0002\"p_2,\u0017M\u001c\u0005\u0006k\t\u0001\rAJ\u0001\u0002q\")qG\u0001a\u0001M\u0005\t\u00110\u0001\u0003oKF4HcA\u0019;w!)Qg\u0001a\u0001M!)qg\u0001a\u0001M\u0005\u0011q\r\u001e\u000b\u0004cyz\u0004\"B\u001b\u0005\u0001\u00041\u0003\"B\u001c\u0005\u0001\u00041\u0013!B4uKF4HcA\u0019C\u0007\")Q'\u0002a\u0001M!)q'\u0002a\u0001M\u0005\u0011A\u000e\u001e\u000b\u0004c\u0019;\u0005\"B\u001b\u0007\u0001\u00041\u0003\"B\u001c\u0007\u0001\u00041\u0013!\u00027uKF4HcA\u0019K\u0017\")Qg\u0002a\u0001M!)qg\u0002a\u0001M\u000591m\\7qCJ,Gc\u0001(R%B\u0011\u0001cT\u0005\u0003!F\u00111!\u00138u\u0011\u0015)\u0004\u00021\u0001'\u0011\u00159\u0004\u00021\u0001'\u0001"
)
public interface SafeLongOrder extends Order {
   // $FF: synthetic method
   static boolean eqv$(final SafeLongOrder $this, final SafeLong x, final SafeLong y) {
      return $this.eqv(x, y);
   }

   default boolean eqv(final SafeLong x, final SafeLong y) {
      return BoxesRunTime.equalsNumNum(x, y);
   }

   // $FF: synthetic method
   static boolean neqv$(final SafeLongOrder $this, final SafeLong x, final SafeLong y) {
      return $this.neqv(x, y);
   }

   default boolean neqv(final SafeLong x, final SafeLong y) {
      return !BoxesRunTime.equalsNumNum(x, y);
   }

   // $FF: synthetic method
   static boolean gt$(final SafeLongOrder $this, final SafeLong x, final SafeLong y) {
      return $this.gt(x, y);
   }

   default boolean gt(final SafeLong x, final SafeLong y) {
      return x.$greater(y);
   }

   // $FF: synthetic method
   static boolean gteqv$(final SafeLongOrder $this, final SafeLong x, final SafeLong y) {
      return $this.gteqv(x, y);
   }

   default boolean gteqv(final SafeLong x, final SafeLong y) {
      return x.$greater$eq(y);
   }

   // $FF: synthetic method
   static boolean lt$(final SafeLongOrder $this, final SafeLong x, final SafeLong y) {
      return $this.lt(x, y);
   }

   default boolean lt(final SafeLong x, final SafeLong y) {
      return x.$less(y);
   }

   // $FF: synthetic method
   static boolean lteqv$(final SafeLongOrder $this, final SafeLong x, final SafeLong y) {
      return $this.lteqv(x, y);
   }

   default boolean lteqv(final SafeLong x, final SafeLong y) {
      return x.$less$eq(y);
   }

   // $FF: synthetic method
   static int compare$(final SafeLongOrder $this, final SafeLong x, final SafeLong y) {
      return $this.compare(x, y);
   }

   default int compare(final SafeLong x, final SafeLong y) {
      return x.compare(y);
   }

   static void $init$(final SafeLongOrder $this) {
   }
}
