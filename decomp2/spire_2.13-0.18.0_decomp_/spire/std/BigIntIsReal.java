package spire.std;

import scala.math.BigInt;
import scala.reflect.ScalaSignature;
import spire.algebra.IsIntegral;

@ScalaSignature(
   bytes = "\u0006\u0005e2q\u0001B\u0003\u0011\u0002\u0007\u0005!\u0002C\u0003+\u0001\u0011\u00051\u0006C\u00030\u0001\u0011\u0005\u0001\u0007C\u00037\u0001\u0011\u0005qG\u0001\u0007CS\u001eLe\u000e^%t%\u0016\fGN\u0003\u0002\u0007\u000f\u0005\u00191\u000f\u001e3\u000b\u0003!\tQa\u001d9je\u0016\u001c\u0001aE\u0003\u0001\u0017E\u0019s\u0005\u0005\u0002\r\u001f5\tQBC\u0001\u000f\u0003\u0015\u00198-\u00197b\u0013\t\u0001RB\u0001\u0004B]f\u0014VM\u001a\t\u0004%U9R\"A\n\u000b\u0005Q9\u0011aB1mO\u0016\u0014'/Y\u0005\u0003-M\u0011!\"S:J]R,wM]1m!\tA\u0002E\u0004\u0002\u001a=9\u0011!$H\u0007\u00027)\u0011A$C\u0001\u0007yI|w\u000e\u001e \n\u00039I!aH\u0007\u0002\u000fA\f7m[1hK&\u0011\u0011E\t\u0002\u0007\u0005&<\u0017J\u001c;\u000b\u0005}i\u0001C\u0001\u0013&\u001b\u0005)\u0011B\u0001\u0014\u0006\u0005]\u0011\u0015nZ%oiR\u0013XO\\2bi\u0016$G)\u001b<jg&|g\u000e\u0005\u0002\u0019Q%\u0011\u0011F\t\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.Z\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u00031\u0002\"\u0001D\u0017\n\u00059j!\u0001B+oSR\f\u0001\u0002^8E_V\u0014G.\u001a\u000b\u0003cQ\u0002\"\u0001\u0004\u001a\n\u0005Mj!A\u0002#pk\ndW\rC\u00036\u0005\u0001\u0007q#A\u0001o\u0003!!xNQ5h\u0013:$HCA\f9\u0011\u0015)4\u00011\u0001\u0018\u0001"
)
public interface BigIntIsReal extends IsIntegral, BigIntTruncatedDivision {
   // $FF: synthetic method
   static double toDouble$(final BigIntIsReal $this, final BigInt n) {
      return $this.toDouble(n);
   }

   default double toDouble(final BigInt n) {
      return n.toDouble();
   }

   // $FF: synthetic method
   static BigInt toBigInt$(final BigIntIsReal $this, final BigInt n) {
      return $this.toBigInt(n);
   }

   default BigInt toBigInt(final BigInt n) {
      return n;
   }

   static void $init$(final BigIntIsReal $this) {
   }
}
