package spire.math;

import algebra.ring.Signed;
import cats.kernel.Order;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005y3\u0001b\u0003\u0007\u0011\u0002\u0007\u0005A\u0002\u0005\u0005\u0006_\u0001!\t\u0001\r\u0005\u0006i\u0001!\t!\u000e\u0005\u0006o\u0001!\t\u0005\u000f\u0005\u0006\u0001\u0002!\t%\u0011\u0005\u0006\t\u0002!\t%\u0012\u0005\u0006\u0011\u0002!\t%\u0013\u0005\u0006\u0019\u0002!\t%\u0014\u0005\u0006!\u0002!\t%\u0015\u0005\u0006)\u0002!\t!\u0016\u0005\u00067\u0002!\t\u0001\u0018\u0002\u000b+&sGoU5h]\u0016$'BA\u0007\u000f\u0003\u0011i\u0017\r\u001e5\u000b\u0003=\tQa\u001d9je\u0016\u001cB\u0001A\t\u0018YA\u0011!#F\u0007\u0002')\tA#A\u0003tG\u0006d\u0017-\u0003\u0002\u0017'\t1\u0011I\\=SK\u001a\u00042\u0001G\u0013)\u001d\tI\"E\u0004\u0002\u001bA9\u00111dH\u0007\u00029)\u0011QDH\u0001\u0007yI|w\u000e\u001e \u0004\u0001%\tq\"\u0003\u0002\"\u001d\u00059\u0011\r\\4fEJ\f\u0017BA\u0012%\u0003\u001d\u0001\u0018mY6bO\u0016T!!\t\b\n\u0005\u0019:#!B(sI\u0016\u0014(BA\u0012%!\tI#&D\u0001\r\u0013\tYCB\u0001\u0003V\u0013:$\bc\u0001\r.Q%\u0011af\n\u0002\u0016'&<g.\u001a3BI\u0012LG/\u001b<f\u00076{gn\\5e\u0003\u0019!\u0013N\\5uIQ\t\u0011\u0007\u0005\u0002\u0013e%\u00111g\u0005\u0002\u0005+:LG/A\u0003pe\u0012,'/F\u00017!\tI\u0003!A\u0002fcZ$2!\u000f\u001f?!\t\u0011\"(\u0003\u0002<'\t9!i\\8mK\u0006t\u0007\"B\u001f\u0004\u0001\u0004A\u0013!\u0001=\t\u000b}\u001a\u0001\u0019\u0001\u0015\u0002\u0003e\fAA\\3rmR\u0019\u0011HQ\"\t\u000bu\"\u0001\u0019\u0001\u0015\t\u000b}\"\u0001\u0019\u0001\u0015\u0002\u0005\u001d$HcA\u001dG\u000f\")Q(\u0002a\u0001Q!)q(\u0002a\u0001Q\u0005)q\r^3rmR\u0019\u0011HS&\t\u000bu2\u0001\u0019\u0001\u0015\t\u000b}2\u0001\u0019\u0001\u0015\u0002\u00051$HcA\u001dO\u001f\")Qh\u0002a\u0001Q!)qh\u0002a\u0001Q\u0005)A\u000e^3rmR\u0019\u0011HU*\t\u000buB\u0001\u0019\u0001\u0015\t\u000b}B\u0001\u0019\u0001\u0015\u0002\u000f\r|W\u000e]1sKR\u0019a+\u0017.\u0011\u0005I9\u0016B\u0001-\u0014\u0005\rIe\u000e\u001e\u0005\u0006{%\u0001\r\u0001\u000b\u0005\u0006\u007f%\u0001\r\u0001K\u0001\u0004C\n\u001cHC\u0001\u0015^\u0011\u0015i$\u00021\u0001)\u0001"
)
public interface UIntSigned extends Order, Signed.forAdditiveCommutativeMonoid {
   // $FF: synthetic method
   static UIntSigned order$(final UIntSigned $this) {
      return $this.order();
   }

   default UIntSigned order() {
      return this;
   }

   // $FF: synthetic method
   static boolean eqv$(final UIntSigned $this, final int x, final int y) {
      return $this.eqv(x, y);
   }

   default boolean eqv(final int x, final int y) {
      return UInt$.MODULE$.$eq$eq$extension(x, y);
   }

   // $FF: synthetic method
   static boolean neqv$(final UIntSigned $this, final int x, final int y) {
      return $this.neqv(x, y);
   }

   default boolean neqv(final int x, final int y) {
      return UInt$.MODULE$.$bang$eq$extension(x, y);
   }

   // $FF: synthetic method
   static boolean gt$(final UIntSigned $this, final int x, final int y) {
      return $this.gt(x, y);
   }

   default boolean gt(final int x, final int y) {
      return UInt$.MODULE$.$greater$extension(x, y);
   }

   // $FF: synthetic method
   static boolean gteqv$(final UIntSigned $this, final int x, final int y) {
      return $this.gteqv(x, y);
   }

   default boolean gteqv(final int x, final int y) {
      return UInt$.MODULE$.$greater$eq$extension(x, y);
   }

   // $FF: synthetic method
   static boolean lt$(final UIntSigned $this, final int x, final int y) {
      return $this.lt(x, y);
   }

   default boolean lt(final int x, final int y) {
      return UInt$.MODULE$.$less$extension(x, y);
   }

   // $FF: synthetic method
   static boolean lteqv$(final UIntSigned $this, final int x, final int y) {
      return $this.lteqv(x, y);
   }

   default boolean lteqv(final int x, final int y) {
      return UInt$.MODULE$.$less$eq$extension(x, y);
   }

   // $FF: synthetic method
   static int compare$(final UIntSigned $this, final int x, final int y) {
      return $this.compare(x, y);
   }

   default int compare(final int x, final int y) {
      return UInt$.MODULE$.$less$extension(x, y) ? -1 : (UInt$.MODULE$.$greater$extension(x, y) ? 1 : 0);
   }

   // $FF: synthetic method
   static int abs$(final UIntSigned $this, final int x) {
      return $this.abs(x);
   }

   default int abs(final int x) {
      return x;
   }

   static void $init$(final UIntSigned $this) {
   }
}
