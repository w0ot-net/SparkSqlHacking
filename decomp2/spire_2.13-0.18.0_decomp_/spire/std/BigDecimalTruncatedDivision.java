package spire.std;

import algebra.ring.TruncatedDivision;
import scala.Tuple2;
import scala.math.BigDecimal;
import scala.math.BigInt;
import scala.reflect.ScalaSignature;
import spire.util.Opt.;

@ScalaSignature(
   bytes = "\u0006\u0005E3qAB\u0004\u0011\u0002\u0007\u0005A\u0002C\u00030\u0001\u0011\u0005\u0001\u0007C\u00035\u0001\u0011\u0005Q\u0007C\u0003B\u0001\u0011\u0005!\tC\u0003G\u0001\u0011\u0005q\tC\u0003K\u0001\u0011\u00053JA\u000eCS\u001e$UmY5nC2$&/\u001e8dCR,G\rR5wSNLwN\u001c\u0006\u0003\u0011%\t1a\u001d;e\u0015\u0005Q\u0011!B:qSJ,7\u0001A\n\u0005\u00015\u00192\u0006\u0005\u0002\u000f#5\tqBC\u0001\u0011\u0003\u0015\u00198-\u00197b\u0013\t\u0011rB\u0001\u0004B]f\u0014VM\u001a\t\u0004)\u0001\u001acBA\u000b\u001e\u001d\t12D\u0004\u0002\u001855\t\u0001D\u0003\u0002\u001a\u0017\u00051AH]8pizJ\u0011AC\u0005\u00039%\tq!\u00197hK\n\u0014\u0018-\u0003\u0002\u001f?\u00059\u0001/Y2lC\u001e,'B\u0001\u000f\n\u0013\t\t#E\u0001\fUeVt7-\u0019;fI\u0012Kg/[:j_:\u001c%+\u001b8h\u0015\tqr\u0004\u0005\u0002%Q9\u0011Qe\n\b\u0003/\u0019J\u0011\u0001E\u0005\u0003==I!!\u000b\u0016\u0003\u0015\tKw\rR3dS6\fGN\u0003\u0002\u001f\u001fA\u0011A&L\u0007\u0002\u000f%\u0011af\u0002\u0002\u0011\u0005&<G)Z2j[\u0006d7+[4oK\u0012\fa\u0001J5oSR$C#A\u0019\u0011\u00059\u0011\u0014BA\u001a\u0010\u0005\u0011)f.\u001b;\u0002\u0017Q|')[4J]R|\u0005\u000f\u001e\u000b\u0003m}\u00022a\u000e\u001e=\u001b\u0005A$BA\u001d\n\u0003\u0011)H/\u001b7\n\u0005mB$aA(qiB\u0011A%P\u0005\u0003})\u0012aAQ5h\u0013:$\b\"\u0002!\u0003\u0001\u0004\u0019\u0013!A1\u0002\u000bQ\fXo\u001c;\u0015\u0007\r\u001aE\tC\u0003A\u0007\u0001\u00071\u0005C\u0003F\u0007\u0001\u00071%A\u0001c\u0003\u0011!Xn\u001c3\u0015\u0007\rB\u0015\nC\u0003A\t\u0001\u00071\u0005C\u0003F\t\u0001\u00071%\u0001\u0005ucV|G/\\8e)\rau\n\u0015\t\u0005\u001d5\u001b3%\u0003\u0002O\u001f\t1A+\u001e9mKJBQ\u0001Q\u0003A\u0002\rBQ!R\u0003A\u0002\r\u0002"
)
public interface BigDecimalTruncatedDivision extends TruncatedDivision.forCommutativeRing, BigDecimalSigned {
   // $FF: synthetic method
   static BigInt toBigIntOpt$(final BigDecimalTruncatedDivision $this, final BigDecimal a) {
      return $this.toBigIntOpt(a);
   }

   default BigInt toBigIntOpt(final BigDecimal a) {
      return a.isWhole() ? (BigInt).MODULE$.apply(a.toBigInt()) : (BigInt).MODULE$.empty();
   }

   // $FF: synthetic method
   static BigDecimal tquot$(final BigDecimalTruncatedDivision $this, final BigDecimal a, final BigDecimal b) {
      return $this.tquot(a, b);
   }

   default BigDecimal tquot(final BigDecimal a, final BigDecimal b) {
      return a.quot(b);
   }

   // $FF: synthetic method
   static BigDecimal tmod$(final BigDecimalTruncatedDivision $this, final BigDecimal a, final BigDecimal b) {
      return $this.tmod(a, b);
   }

   default BigDecimal tmod(final BigDecimal a, final BigDecimal b) {
      return a.$percent(b);
   }

   // $FF: synthetic method
   static Tuple2 tquotmod$(final BigDecimalTruncatedDivision $this, final BigDecimal a, final BigDecimal b) {
      return $this.tquotmod(a, b);
   }

   default Tuple2 tquotmod(final BigDecimal a, final BigDecimal b) {
      return a.$div$percent(b);
   }

   static void $init$(final BigDecimalTruncatedDivision $this) {
   }
}
