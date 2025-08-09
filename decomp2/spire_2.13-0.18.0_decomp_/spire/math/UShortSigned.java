package spire.math;

import algebra.ring.Signed;
import cats.kernel.Order;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005y3\u0001b\u0003\u0007\u0011\u0002\u0007\u0005A\u0002\u0005\u0005\u0006_\u0001!\t\u0001\r\u0005\u0006i\u0001!\t!\u000e\u0005\u0006o\u0001!\t\u0005\u000f\u0005\u0006\u0001\u0002!\t%\u0011\u0005\u0006\t\u0002!\t%\u0012\u0005\u0006\u0011\u0002!\t%\u0013\u0005\u0006\u0019\u0002!\t%\u0014\u0005\u0006!\u0002!\t%\u0015\u0005\u0006)\u0002!\t!\u0016\u0005\u00067\u0002!\t\u0001\u0018\u0002\r+NCwN\u001d;TS\u001etW\r\u001a\u0006\u0003\u001b9\tA!\\1uQ*\tq\"A\u0003ta&\u0014Xm\u0005\u0003\u0001#]a\u0003C\u0001\n\u0016\u001b\u0005\u0019\"\"\u0001\u000b\u0002\u000bM\u001c\u0017\r\\1\n\u0005Y\u0019\"AB!osJ+g\rE\u0002\u0019K!r!!\u0007\u0012\u000f\u0005i\u0001cBA\u000e \u001b\u0005a\"BA\u000f\u001f\u0003\u0019a$o\\8u}\r\u0001\u0011\"A\b\n\u0005\u0005r\u0011aB1mO\u0016\u0014'/Y\u0005\u0003G\u0011\nq\u0001]1dW\u0006<WM\u0003\u0002\"\u001d%\u0011ae\n\u0002\u0006\u001fJ$WM\u001d\u0006\u0003G\u0011\u0002\"!\u000b\u0016\u000e\u00031I!a\u000b\u0007\u0003\rU\u001b\u0006n\u001c:u!\rAR\u0006K\u0005\u0003]\u001d\u0012QcU5h]\u0016$\u0017\t\u001a3ji&4XmQ'p]>LG-\u0001\u0004%S:LG\u000f\n\u000b\u0002cA\u0011!CM\u0005\u0003gM\u0011A!\u00168ji\u0006)qN\u001d3feV\ta\u0007\u0005\u0002*\u0001\u0005\u0019Q-\u001d<\u0015\u0007ebd\b\u0005\u0002\u0013u%\u00111h\u0005\u0002\b\u0005>|G.Z1o\u0011\u0015i4\u00011\u0001)\u0003\u0005A\b\"B \u0004\u0001\u0004A\u0013!A=\u0002\t9,\u0017O\u001e\u000b\u0004s\t\u001b\u0005\"B\u001f\u0005\u0001\u0004A\u0003\"B \u0005\u0001\u0004A\u0013AA4u)\rIdi\u0012\u0005\u0006{\u0015\u0001\r\u0001\u000b\u0005\u0006\u007f\u0015\u0001\r\u0001K\u0001\u0006OR,\u0017O\u001e\u000b\u0004s)[\u0005\"B\u001f\u0007\u0001\u0004A\u0003\"B \u0007\u0001\u0004A\u0013A\u00017u)\rIdj\u0014\u0005\u0006{\u001d\u0001\r\u0001\u000b\u0005\u0006\u007f\u001d\u0001\r\u0001K\u0001\u0006YR,\u0017O\u001e\u000b\u0004sI\u001b\u0006\"B\u001f\t\u0001\u0004A\u0003\"B \t\u0001\u0004A\u0013aB2p[B\f'/\u001a\u000b\u0004-fS\u0006C\u0001\nX\u0013\tA6CA\u0002J]RDQ!P\u0005A\u0002!BQaP\u0005A\u0002!\n1!\u00192t)\tAS\fC\u0003>\u0015\u0001\u0007\u0001\u0006"
)
public interface UShortSigned extends Order, Signed.forAdditiveCommutativeMonoid {
   // $FF: synthetic method
   static UShortSigned order$(final UShortSigned $this) {
      return $this.order();
   }

   default UShortSigned order() {
      return this;
   }

   // $FF: synthetic method
   static boolean eqv$(final UShortSigned $this, final char x, final char y) {
      return $this.eqv(x, y);
   }

   default boolean eqv(final char x, final char y) {
      return UShort$.MODULE$.$eq$eq$extension(x, y);
   }

   // $FF: synthetic method
   static boolean neqv$(final UShortSigned $this, final char x, final char y) {
      return $this.neqv(x, y);
   }

   default boolean neqv(final char x, final char y) {
      return UShort$.MODULE$.$bang$eq$extension(x, y);
   }

   // $FF: synthetic method
   static boolean gt$(final UShortSigned $this, final char x, final char y) {
      return $this.gt(x, y);
   }

   default boolean gt(final char x, final char y) {
      return UShort$.MODULE$.$greater$extension(x, y);
   }

   // $FF: synthetic method
   static boolean gteqv$(final UShortSigned $this, final char x, final char y) {
      return $this.gteqv(x, y);
   }

   default boolean gteqv(final char x, final char y) {
      return UShort$.MODULE$.$greater$eq$extension(x, y);
   }

   // $FF: synthetic method
   static boolean lt$(final UShortSigned $this, final char x, final char y) {
      return $this.lt(x, y);
   }

   default boolean lt(final char x, final char y) {
      return UShort$.MODULE$.$less$extension(x, y);
   }

   // $FF: synthetic method
   static boolean lteqv$(final UShortSigned $this, final char x, final char y) {
      return $this.lteqv(x, y);
   }

   default boolean lteqv(final char x, final char y) {
      return UShort$.MODULE$.$less$eq$extension(x, y);
   }

   // $FF: synthetic method
   static int compare$(final UShortSigned $this, final char x, final char y) {
      return $this.compare(x, y);
   }

   default int compare(final char x, final char y) {
      return UShort$.MODULE$.$less$extension(x, y) ? -1 : (UShort$.MODULE$.$greater$extension(x, y) ? 1 : 0);
   }

   // $FF: synthetic method
   static char abs$(final UShortSigned $this, final char x) {
      return $this.abs(x);
   }

   default char abs(final char x) {
      return x;
   }

   static void $init$(final UShortSigned $this) {
   }
}
