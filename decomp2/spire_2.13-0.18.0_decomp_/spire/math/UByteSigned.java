package spire.math;

import algebra.ring.Signed;
import cats.kernel.Order;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005y3\u0001b\u0003\u0007\u0011\u0002\u0007\u0005A\u0002\u0005\u0005\u0006_\u0001!\t\u0001\r\u0005\u0006i\u0001!\t!\u000e\u0005\u0006o\u0001!\t\u0005\u000f\u0005\u0006\u0001\u0002!\t%\u0011\u0005\u0006\t\u0002!\t%\u0012\u0005\u0006\u0011\u0002!\t%\u0013\u0005\u0006\u0019\u0002!\t%\u0014\u0005\u0006!\u0002!\t%\u0015\u0005\u0006)\u0002!\t!\u0016\u0005\u00067\u0002!\t\u0001\u0018\u0002\f+\nKH/Z*jO:,GM\u0003\u0002\u000e\u001d\u0005!Q.\u0019;i\u0015\u0005y\u0011!B:qSJ,7\u0003\u0002\u0001\u0012/1\u0002\"AE\u000b\u000e\u0003MQ\u0011\u0001F\u0001\u0006g\u000e\fG.Y\u0005\u0003-M\u0011a!\u00118z%\u00164\u0007c\u0001\r&Q9\u0011\u0011D\t\b\u00035\u0001r!aG\u0010\u000e\u0003qQ!!\b\u0010\u0002\rq\u0012xn\u001c;?\u0007\u0001I\u0011aD\u0005\u0003C9\tq!\u00197hK\n\u0014\u0018-\u0003\u0002$I\u00059\u0001/Y2lC\u001e,'BA\u0011\u000f\u0013\t1sEA\u0003Pe\u0012,'O\u0003\u0002$IA\u0011\u0011FK\u0007\u0002\u0019%\u00111\u0006\u0004\u0002\u0006+\nKH/\u001a\t\u000415B\u0013B\u0001\u0018(\u0005U\u0019\u0016n\u001a8fI\u0006#G-\u001b;jm\u0016\u001cUj\u001c8pS\u0012\fa\u0001J5oSR$C#A\u0019\u0011\u0005I\u0011\u0014BA\u001a\u0014\u0005\u0011)f.\u001b;\u0002\u000b=\u0014H-\u001a:\u0016\u0003Y\u0002\"!\u000b\u0001\u0002\u0007\u0015\fh\u000fF\u0002:yy\u0002\"A\u0005\u001e\n\u0005m\u001a\"a\u0002\"p_2,\u0017M\u001c\u0005\u0006{\r\u0001\r\u0001K\u0001\u0002q\")qh\u0001a\u0001Q\u0005\t\u00110\u0001\u0003oKF4HcA\u001dC\u0007\")Q\b\u0002a\u0001Q!)q\b\u0002a\u0001Q\u0005\u0011q\r\u001e\u000b\u0004s\u0019;\u0005\"B\u001f\u0006\u0001\u0004A\u0003\"B \u0006\u0001\u0004A\u0013!B4uKF4HcA\u001dK\u0017\")QH\u0002a\u0001Q!)qH\u0002a\u0001Q\u0005\u0011A\u000e\u001e\u000b\u0004s9{\u0005\"B\u001f\b\u0001\u0004A\u0003\"B \b\u0001\u0004A\u0013!\u00027uKF4HcA\u001dS'\")Q\b\u0003a\u0001Q!)q\b\u0003a\u0001Q\u000591m\\7qCJ,Gc\u0001,Z5B\u0011!cV\u0005\u00031N\u00111!\u00138u\u0011\u0015i\u0014\u00021\u0001)\u0011\u0015y\u0014\u00021\u0001)\u0003\r\t'm\u001d\u000b\u0003QuCQ!\u0010\u0006A\u0002!\u0002"
)
public interface UByteSigned extends Order, Signed.forAdditiveCommutativeMonoid {
   // $FF: synthetic method
   static UByteSigned order$(final UByteSigned $this) {
      return $this.order();
   }

   default UByteSigned order() {
      return this;
   }

   // $FF: synthetic method
   static boolean eqv$(final UByteSigned $this, final byte x, final byte y) {
      return $this.eqv(x, y);
   }

   default boolean eqv(final byte x, final byte y) {
      return UByte$.MODULE$.$eq$eq$extension(x, y);
   }

   // $FF: synthetic method
   static boolean neqv$(final UByteSigned $this, final byte x, final byte y) {
      return $this.neqv(x, y);
   }

   default boolean neqv(final byte x, final byte y) {
      return UByte$.MODULE$.$bang$eq$extension(x, y);
   }

   // $FF: synthetic method
   static boolean gt$(final UByteSigned $this, final byte x, final byte y) {
      return $this.gt(x, y);
   }

   default boolean gt(final byte x, final byte y) {
      return UByte$.MODULE$.$greater$extension(x, y);
   }

   // $FF: synthetic method
   static boolean gteqv$(final UByteSigned $this, final byte x, final byte y) {
      return $this.gteqv(x, y);
   }

   default boolean gteqv(final byte x, final byte y) {
      return UByte$.MODULE$.$greater$eq$extension(x, y);
   }

   // $FF: synthetic method
   static boolean lt$(final UByteSigned $this, final byte x, final byte y) {
      return $this.lt(x, y);
   }

   default boolean lt(final byte x, final byte y) {
      return UByte$.MODULE$.$less$extension(x, y);
   }

   // $FF: synthetic method
   static boolean lteqv$(final UByteSigned $this, final byte x, final byte y) {
      return $this.lteqv(x, y);
   }

   default boolean lteqv(final byte x, final byte y) {
      return UByte$.MODULE$.$less$eq$extension(x, y);
   }

   // $FF: synthetic method
   static int compare$(final UByteSigned $this, final byte x, final byte y) {
      return $this.compare(x, y);
   }

   default int compare(final byte x, final byte y) {
      return UByte$.MODULE$.$less$extension(x, y) ? -1 : (UByte$.MODULE$.$greater$extension(x, y) ? 1 : 0);
   }

   // $FF: synthetic method
   static byte abs$(final UByteSigned $this, final byte x) {
      return $this.abs(x);
   }

   default byte abs(final byte x) {
      return x;
   }

   static void $init$(final UByteSigned $this) {
   }
}
