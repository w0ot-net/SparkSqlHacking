package spire.std;

import cats.kernel.Order;
import java.math.BigInteger;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00054qa\u0003\u0007\u0011\u0002\u0007\u0005\u0011\u0003C\u00031\u0001\u0011\u0005\u0011\u0007C\u00036\u0001\u0011\u0005c\u0007C\u0003?\u0001\u0011\u0005s\bC\u0003C\u0001\u0011\u00053\tC\u0003G\u0001\u0011\u0005s\tC\u0003K\u0001\u0011\u00053\nC\u0003O\u0001\u0011\u0005s\nC\u0003S\u0001\u0011\u00053\u000bC\u0003W\u0001\u0011\u0005s\u000bC\u0003[\u0001\u0011\u00051LA\bCS\u001eLe\u000e^3hKJ|%\u000fZ3s\u0015\tia\"A\u0002ti\u0012T\u0011aD\u0001\u0006gBL'/Z\u0002\u0001'\r\u0001!\u0003\u0007\t\u0003'Yi\u0011\u0001\u0006\u0006\u0002+\u0005)1oY1mC&\u0011q\u0003\u0006\u0002\u0007\u0003:L(+\u001a4\u0011\u0007e)\u0003F\u0004\u0002\u001bE9\u00111\u0004\t\b\u00039}i\u0011!\b\u0006\u0003=A\ta\u0001\u0010:p_Rt\u0014\"A\b\n\u0005\u0005r\u0011aB1mO\u0016\u0014'/Y\u0005\u0003G\u0011\nq\u0001]1dW\u0006<WM\u0003\u0002\"\u001d%\u0011ae\n\u0002\u0006\u001fJ$WM\u001d\u0006\u0003G\u0011\u0002\"!\u000b\u0018\u000e\u0003)R!a\u000b\u0017\u0002\t5\fG\u000f\u001b\u0006\u0002[\u0005!!.\u0019<b\u0013\ty#F\u0001\u0006CS\u001eLe\u000e^3hKJ\fa\u0001J5oSR$C#\u0001\u001a\u0011\u0005M\u0019\u0014B\u0001\u001b\u0015\u0005\u0011)f.\u001b;\u0002\u0007\u0015\fh\u000fF\u00028uq\u0002\"a\u0005\u001d\n\u0005e\"\"a\u0002\"p_2,\u0017M\u001c\u0005\u0006w\t\u0001\r\u0001K\u0001\u0002q\")QH\u0001a\u0001Q\u0005\t\u00110\u0001\u0003oKF4HcA\u001cA\u0003\")1h\u0001a\u0001Q!)Qh\u0001a\u0001Q\u0005\u0011q\r\u001e\u000b\u0004o\u0011+\u0005\"B\u001e\u0005\u0001\u0004A\u0003\"B\u001f\u0005\u0001\u0004A\u0013!B4uKF4HcA\u001cI\u0013\")1(\u0002a\u0001Q!)Q(\u0002a\u0001Q\u0005\u0011A\u000e\u001e\u000b\u0004o1k\u0005\"B\u001e\u0007\u0001\u0004A\u0003\"B\u001f\u0007\u0001\u0004A\u0013!\u00027uKF4HcA\u001cQ#\")1h\u0002a\u0001Q!)Qh\u0002a\u0001Q\u0005\u0019Q.\u001b8\u0015\u0007!\"V\u000bC\u0003<\u0011\u0001\u0007\u0001\u0006C\u0003>\u0011\u0001\u0007\u0001&A\u0002nCb$2\u0001\u000b-Z\u0011\u0015Y\u0014\u00021\u0001)\u0011\u0015i\u0014\u00021\u0001)\u0003\u001d\u0019w.\u001c9be\u0016$2\u0001X0a!\t\u0019R,\u0003\u0002_)\t\u0019\u0011J\u001c;\t\u000bmR\u0001\u0019\u0001\u0015\t\u000buR\u0001\u0019\u0001\u0015"
)
public interface BigIntegerOrder extends Order {
   // $FF: synthetic method
   static boolean eqv$(final BigIntegerOrder $this, final BigInteger x, final BigInteger y) {
      return $this.eqv(x, y);
   }

   default boolean eqv(final BigInteger x, final BigInteger y) {
      return x.equals(y);
   }

   // $FF: synthetic method
   static boolean neqv$(final BigIntegerOrder $this, final BigInteger x, final BigInteger y) {
      return $this.neqv(x, y);
   }

   default boolean neqv(final BigInteger x, final BigInteger y) {
      return !x.equals(y);
   }

   // $FF: synthetic method
   static boolean gt$(final BigIntegerOrder $this, final BigInteger x, final BigInteger y) {
      return $this.gt(x, y);
   }

   default boolean gt(final BigInteger x, final BigInteger y) {
      return x.compareTo(y) > 0;
   }

   // $FF: synthetic method
   static boolean gteqv$(final BigIntegerOrder $this, final BigInteger x, final BigInteger y) {
      return $this.gteqv(x, y);
   }

   default boolean gteqv(final BigInteger x, final BigInteger y) {
      return x.compareTo(y) >= 0;
   }

   // $FF: synthetic method
   static boolean lt$(final BigIntegerOrder $this, final BigInteger x, final BigInteger y) {
      return $this.lt(x, y);
   }

   default boolean lt(final BigInteger x, final BigInteger y) {
      return x.compareTo(y) < 0;
   }

   // $FF: synthetic method
   static boolean lteqv$(final BigIntegerOrder $this, final BigInteger x, final BigInteger y) {
      return $this.lteqv(x, y);
   }

   default boolean lteqv(final BigInteger x, final BigInteger y) {
      return x.compareTo(y) <= 0;
   }

   // $FF: synthetic method
   static BigInteger min$(final BigIntegerOrder $this, final BigInteger x, final BigInteger y) {
      return $this.min(x, y);
   }

   default BigInteger min(final BigInteger x, final BigInteger y) {
      return x.min(y);
   }

   // $FF: synthetic method
   static BigInteger max$(final BigIntegerOrder $this, final BigInteger x, final BigInteger y) {
      return $this.max(x, y);
   }

   default BigInteger max(final BigInteger x, final BigInteger y) {
      return x.max(y);
   }

   // $FF: synthetic method
   static int compare$(final BigIntegerOrder $this, final BigInteger x, final BigInteger y) {
      return $this.compare(x, y);
   }

   default int compare(final BigInteger x, final BigInteger y) {
      return x.compareTo(y);
   }

   static void $init$(final BigIntegerOrder $this) {
   }
}
