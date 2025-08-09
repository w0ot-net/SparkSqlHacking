package spire.math;

import scala.math.BigInt;
import scala.reflect.ScalaSignature;
import spire.algebra.IsIntegral;

@ScalaSignature(
   bytes = "\u0006\u0005e2\u0001\u0002B\u0003\u0011\u0002\u0007\u0005Q!\u0003\u0005\u0006;\u0001!\ta\b\u0005\u0006G\u0001!\t\u0001\n\u0005\u0006U\u0001!\ta\u000b\u0002\u000e\u001d\u0006$XO]1m\u0013N\u0014V-\u00197\u000b\u0005\u00199\u0011\u0001B7bi\"T\u0011\u0001C\u0001\u0006gBL'/Z\n\u0005\u0001)\u0001\"\u0004\u0005\u0002\f\u001d5\tABC\u0001\u000e\u0003\u0015\u00198-\u00197b\u0013\tyAB\u0001\u0004B]f\u0014VM\u001a\t\u0004#Q1R\"\u0001\n\u000b\u0005M9\u0011aB1mO\u0016\u0014'/Y\u0005\u0003+I\u0011!\"S:J]R,wM]1m!\t9\u0002$D\u0001\u0006\u0013\tIRAA\u0004OCR,(/\u00197\u0011\u0005]Y\u0012B\u0001\u000f\u0006\u00051q\u0015\r^;sC2|%\u000fZ3s\u0003\u0019!\u0013N\\5uI\r\u0001A#\u0001\u0011\u0011\u0005-\t\u0013B\u0001\u0012\r\u0005\u0011)f.\u001b;\u0002\u0011Q|Gi\\;cY\u0016$\"!\n\u0015\u0011\u0005-1\u0013BA\u0014\r\u0005\u0019!u.\u001e2mK\")\u0011F\u0001a\u0001-\u0005\ta.\u0001\u0005u_\nKw-\u00138u)\ta\u0003\b\u0005\u0002.k9\u0011af\r\b\u0003_Ij\u0011\u0001\r\u0006\u0003cy\ta\u0001\u0010:p_Rt\u0014\"A\u0007\n\u0005Qb\u0011a\u00029bG.\fw-Z\u0005\u0003m]\u0012aAQ5h\u0013:$(B\u0001\u001b\r\u0011\u0015I3\u00011\u0001\u0017\u0001"
)
public interface NaturalIsReal extends IsIntegral, NaturalOrder {
   // $FF: synthetic method
   static double toDouble$(final NaturalIsReal $this, final Natural n) {
      return $this.toDouble(n);
   }

   default double toDouble(final Natural n) {
      return n.toDouble();
   }

   // $FF: synthetic method
   static BigInt toBigInt$(final NaturalIsReal $this, final Natural n) {
      return $this.toBigInt(n);
   }

   default BigInt toBigInt(final Natural n) {
      return n.toBigInt();
   }

   static void $init$(final NaturalIsReal $this) {
   }
}
