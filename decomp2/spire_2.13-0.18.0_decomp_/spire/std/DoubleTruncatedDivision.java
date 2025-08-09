package spire.std;

import algebra.ring.TruncatedDivision;
import scala.math.BigInt;
import scala.reflect.ScalaSignature;
import scala.runtime.RichDouble.;

@ScalaSignature(
   bytes = "\u0006\u0005%3q!\u0002\u0004\u0011\u0002\u0007\u00051\u0002C\u0003*\u0001\u0011\u0005!\u0006C\u0003/\u0001\u0011\u0005q\u0006C\u0003A\u0001\u0011\u0005\u0011\tC\u0003F\u0001\u0011\u0005aIA\fE_V\u0014G.\u001a+sk:\u001c\u0017\r^3e\t&4\u0018n]5p]*\u0011q\u0001C\u0001\u0004gR$'\"A\u0005\u0002\u000bM\u0004\u0018N]3\u0004\u0001M!\u0001\u0001\u0004\n&!\ti\u0001#D\u0001\u000f\u0015\u0005y\u0011!B:dC2\f\u0017BA\t\u000f\u0005\u0019\te.\u001f*fMB\u00191c\b\u0012\u000f\u0005QabBA\u000b\u001b\u001d\t1\u0012$D\u0001\u0018\u0015\tA\"\"\u0001\u0004=e>|GOP\u0005\u0002\u0013%\u00111\u0004C\u0001\bC2<WM\u0019:b\u0013\tib$A\u0004qC\u000e\\\u0017mZ3\u000b\u0005mA\u0011B\u0001\u0011\"\u0005Y!&/\u001e8dCR,G\rR5wSNLwN\\\"SS:<'BA\u000f\u001f!\ti1%\u0003\u0002%\u001d\t1Ai\\;cY\u0016\u0004\"AJ\u0014\u000e\u0003\u0019I!\u0001\u000b\u0004\u0003\u0019\u0011{WO\u00197f'&<g.\u001a3\u0002\r\u0011Jg.\u001b;%)\u0005Y\u0003CA\u0007-\u0013\ticB\u0001\u0003V]&$\u0018a\u0003;p\u0005&<\u0017J\u001c;PaR$\"\u0001\r \u0011\u0007E\"d'D\u00013\u0015\t\u0019\u0004\"\u0001\u0003vi&d\u0017BA\u001b3\u0005\ry\u0005\u000f\u001e\t\u0003omr!\u0001\u000f\u001e\u000f\u0005YI\u0014\"A\b\n\u0005uq\u0011B\u0001\u001f>\u0005\u0019\u0011\u0015nZ%oi*\u0011QD\u0004\u0005\u0006\u007f\t\u0001\rAI\u0001\u0002C\u0006)A/];piR\u0019!EQ\"\t\u000b}\u001a\u0001\u0019\u0001\u0012\t\u000b\u0011\u001b\u0001\u0019\u0001\u0012\u0002\u0003\t\fA\u0001^7pIR\u0019!e\u0012%\t\u000b}\"\u0001\u0019\u0001\u0012\t\u000b\u0011#\u0001\u0019\u0001\u0012"
)
public interface DoubleTruncatedDivision extends TruncatedDivision.forCommutativeRing.mcD.sp, DoubleSigned {
   // $FF: synthetic method
   static BigInt toBigIntOpt$(final DoubleTruncatedDivision $this, final double a) {
      return $this.toBigIntOpt(a);
   }

   default BigInt toBigIntOpt(final double a) {
      return .MODULE$.isWhole$extension(scala.Predef..MODULE$.doubleWrapper(a)) ? (BigInt)spire.util.Opt..MODULE$.apply(scala.package..MODULE$.BigDecimal().apply(a).toBigInt()) : (BigInt)spire.util.Opt..MODULE$.empty();
   }

   // $FF: synthetic method
   static double tquot$(final DoubleTruncatedDivision $this, final double a, final double b) {
      return $this.tquot(a, b);
   }

   default double tquot(final double a, final double b) {
      return this.tquot$mcD$sp(a, b);
   }

   // $FF: synthetic method
   static double tmod$(final DoubleTruncatedDivision $this, final double a, final double b) {
      return $this.tmod(a, b);
   }

   default double tmod(final double a, final double b) {
      return this.tmod$mcD$sp(a, b);
   }

   // $FF: synthetic method
   static double tquot$mcD$sp$(final DoubleTruncatedDivision $this, final double a, final double b) {
      return $this.tquot$mcD$sp(a, b);
   }

   default double tquot$mcD$sp(final double a, final double b) {
      return (a - a % b) / b;
   }

   // $FF: synthetic method
   static double tmod$mcD$sp$(final DoubleTruncatedDivision $this, final double a, final double b) {
      return $this.tmod$mcD$sp(a, b);
   }

   default double tmod$mcD$sp(final double a, final double b) {
      return a % b;
   }

   static void $init$(final DoubleTruncatedDivision $this) {
   }
}
