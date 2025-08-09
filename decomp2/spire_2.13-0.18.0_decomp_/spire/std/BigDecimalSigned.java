package spire.std;

import algebra.ring.Signed;
import scala.math.BigDecimal;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00013q!\u0002\u0004\u0011\u0002\u0007\u00051\u0002C\u0003/\u0001\u0011\u0005q\u0006C\u00034\u0001\u0011\u0005A\u0007C\u00037\u0001\u0011\u0005s\u0007C\u0003>\u0001\u0011\u0005cH\u0001\tCS\u001e$UmY5nC2\u001c\u0016n\u001a8fI*\u0011q\u0001C\u0001\u0004gR$'\"A\u0005\u0002\u000bM\u0004\u0018N]3\u0004\u0001M!\u0001\u0001\u0004\n+!\ti\u0001#D\u0001\u000f\u0015\u0005y\u0011!B:dC2\f\u0017BA\t\u000f\u0005\u0019\te.\u001f*fMB\u00191c\b\u0012\u000f\u0005QabBA\u000b\u001b\u001d\t1\u0012$D\u0001\u0018\u0015\tA\"\"\u0001\u0004=e>|GOP\u0005\u0002\u0013%\u00111\u0004C\u0001\bC2<WM\u0019:b\u0013\tib$A\u0004qC\u000e\\\u0017mZ3\u000b\u0005mA\u0011B\u0001\u0011\"\u0005\u0019\u0019\u0016n\u001a8fI*\u0011QD\b\t\u0003G\u001dr!\u0001\n\u0014\u000f\u0005Y)\u0013\"A\b\n\u0005uq\u0011B\u0001\u0015*\u0005)\u0011\u0015n\u001a#fG&l\u0017\r\u001c\u0006\u0003;9\u0001\"a\u000b\u0017\u000e\u0003\u0019I!!\f\u0004\u0003\u001f\tKw\rR3dS6\fGn\u0014:eKJ\fa\u0001J5oSR$C#\u0001\u0019\u0011\u00055\t\u0014B\u0001\u001a\u000f\u0005\u0011)f.\u001b;\u0002\u000b=\u0014H-\u001a:\u0016\u0003U\u0002\"a\u000b\u0001\u0002\rMLwM\\;n)\tA4\b\u0005\u0002\u000es%\u0011!H\u0004\u0002\u0004\u0013:$\b\"\u0002\u001f\u0004\u0001\u0004\u0011\u0013!A1\u0002\u0007\u0005\u00147\u000f\u0006\u0002#\u007f!)A\b\u0002a\u0001E\u0001"
)
public interface BigDecimalSigned extends Signed, BigDecimalOrder {
   // $FF: synthetic method
   static BigDecimalSigned order$(final BigDecimalSigned $this) {
      return $this.order();
   }

   default BigDecimalSigned order() {
      return this;
   }

   // $FF: synthetic method
   static int signum$(final BigDecimalSigned $this, final BigDecimal a) {
      return $this.signum(a);
   }

   default int signum(final BigDecimal a) {
      return a.signum();
   }

   // $FF: synthetic method
   static BigDecimal abs$(final BigDecimalSigned $this, final BigDecimal a) {
      return $this.abs(a);
   }

   default BigDecimal abs(final BigDecimal a) {
      return a.abs();
   }

   static void $init$(final BigDecimalSigned $this) {
   }
}
