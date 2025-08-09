package spire.math;

import algebra.ring.Field;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005e2\u0001\u0002B\u0003\u0011\u0002\u0007\u0005Q!\u0003\u0005\u0006Q\u0001!\t!\u000b\u0005\u0006[\u0001!\tA\f\u0005\u0006g\u0001!\t\u0005\u000e\u0002\u000e\u001dVl'-\u001a:Jg\u001aKW\r\u001c3\u000b\u0005\u00199\u0011\u0001B7bi\"T\u0011\u0001C\u0001\u0006gBL'/Z\n\u0005\u0001)\u0001R\u0005\u0005\u0002\f\u001d5\tABC\u0001\u000e\u0003\u0015\u00198-\u00197b\u0013\tyAB\u0001\u0004B]f\u0014VM\u001a\t\u0004#y\tcB\u0001\n\u001c\u001d\t\u0019\u0012D\u0004\u0002\u001515\tQC\u0003\u0002\u0017/\u00051AH]8piz\u001a\u0001!C\u0001\t\u0013\tQr!A\u0004bY\u001e,'M]1\n\u0005qi\u0012a\u00029bG.\fw-\u001a\u0006\u00035\u001dI!a\b\u0011\u0003\u000b\u0019KW\r\u001c3\u000b\u0005qi\u0002C\u0001\u0012$\u001b\u0005)\u0011B\u0001\u0013\u0006\u0005\u0019qU/\u001c2feB\u0011!EJ\u0005\u0003O\u0015\u0011QBT;nE\u0016\u0014\u0018j]\"SS:<\u0017A\u0002\u0013j]&$H\u0005F\u0001+!\tY1&\u0003\u0002-\u0019\t!QK\\5u\u0003\r!\u0017N\u001e\u000b\u0004C=\n\u0004\"\u0002\u0019\u0003\u0001\u0004\t\u0013!A1\t\u000bI\u0012\u0001\u0019A\u0011\u0002\u0003\t\f!B\u001a:p[\u0012{WO\u00197f)\t\tS\u0007C\u00031\u0007\u0001\u0007a\u0007\u0005\u0002\fo%\u0011\u0001\b\u0004\u0002\u0007\t>,(\r\\3"
)
public interface NumberIsField extends Field, NumberIsCRing {
   // $FF: synthetic method
   static Number div$(final NumberIsField $this, final Number a, final Number b) {
      return $this.div(a, b);
   }

   default Number div(final Number a, final Number b) {
      return a.$div(b);
   }

   // $FF: synthetic method
   static Number fromDouble$(final NumberIsField $this, final double a) {
      return $this.fromDouble(a);
   }

   default Number fromDouble(final double a) {
      return Number$.MODULE$.apply(a);
   }

   static void $init$(final NumberIsField $this) {
   }
}
