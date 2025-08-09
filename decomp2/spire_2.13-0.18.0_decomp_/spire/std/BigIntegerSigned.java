package spire.std;

import algebra.ring.Signed;
import java.math.BigInteger;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00013q!\u0002\u0004\u0011\u0002\u0007\u00051\u0002C\u0003/\u0001\u0011\u0005q\u0006C\u00034\u0001\u0011\u0005A\u0007C\u00037\u0001\u0011\u0005s\u0007C\u0003>\u0001\u0011\u0005cH\u0001\tCS\u001eLe\u000e^3hKJ\u001c\u0016n\u001a8fI*\u0011q\u0001C\u0001\u0004gR$'\"A\u0005\u0002\u000bM\u0004\u0018N]3\u0004\u0001M!\u0001\u0001\u0004\n+!\ti\u0001#D\u0001\u000f\u0015\u0005y\u0011!B:dC2\f\u0017BA\t\u000f\u0005\u0019\te.\u001f*fMB\u00191c\b\u0012\u000f\u0005QabBA\u000b\u001b\u001d\t1\u0012$D\u0001\u0018\u0015\tA\"\"\u0001\u0004=e>|GOP\u0005\u0002\u0013%\u00111\u0004C\u0001\bC2<WM\u0019:b\u0013\tib$A\u0004qC\u000e\\\u0017mZ3\u000b\u0005mA\u0011B\u0001\u0011\"\u0005\u0019\u0019\u0016n\u001a8fI*\u0011QD\b\t\u0003G!j\u0011\u0001\n\u0006\u0003K\u0019\nA!\\1uQ*\tq%\u0001\u0003kCZ\f\u0017BA\u0015%\u0005)\u0011\u0015nZ%oi\u0016<WM\u001d\t\u0003W1j\u0011AB\u0005\u0003[\u0019\u0011qBQ5h\u0013:$XmZ3s\u001fJ$WM]\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003A\u0002\"!D\u0019\n\u0005Ir!\u0001B+oSR\fQa\u001c:eKJ,\u0012!\u000e\t\u0003W\u0001\taa]5h]VlGC\u0001\u001d<!\ti\u0011(\u0003\u0002;\u001d\t\u0019\u0011J\u001c;\t\u000bq\u001a\u0001\u0019\u0001\u0012\u0002\u0003\u0005\f1!\u00192t)\t\u0011s\bC\u0003=\t\u0001\u0007!\u0005"
)
public interface BigIntegerSigned extends Signed, BigIntegerOrder {
   // $FF: synthetic method
   static BigIntegerSigned order$(final BigIntegerSigned $this) {
      return $this.order();
   }

   default BigIntegerSigned order() {
      return this;
   }

   // $FF: synthetic method
   static int signum$(final BigIntegerSigned $this, final BigInteger a) {
      return $this.signum(a);
   }

   default int signum(final BigInteger a) {
      return a.signum();
   }

   // $FF: synthetic method
   static BigInteger abs$(final BigIntegerSigned $this, final BigInteger a) {
      return $this.abs(a);
   }

   default BigInteger abs(final BigInteger a) {
      return a.abs();
   }

   static void $init$(final BigIntegerSigned $this) {
   }
}
