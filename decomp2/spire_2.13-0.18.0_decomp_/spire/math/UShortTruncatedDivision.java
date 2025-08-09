package spire.math;

import algebra.ring.TruncatedDivision;
import scala.math.BigInt;
import scala.reflect.ScalaSignature;
import spire.util.Opt.;

@ScalaSignature(
   bytes = "\u0006\u0005M3\u0001b\u0002\u0005\u0011\u0002\u0007\u0005\u0001\u0002\u0004\u0005\u0006W\u0001!\t\u0001\f\u0005\u0006a\u0001!\t!\r\u0005\u0006\u0005\u0002!\ta\u0011\u0005\u0006\u000f\u0002!\t\u0001\u0013\u0005\u0006\u0017\u0002!\t\u0001\u0014\u0005\u0006\u001f\u0002!\t\u0001\u0015\u0002\u0018+NCwN\u001d;UeVt7-\u0019;fI\u0012Kg/[:j_:T!!\u0003\u0006\u0002\t5\fG\u000f\u001b\u0006\u0002\u0017\u0005)1\u000f]5sKN!\u0001!D\n)!\tq\u0011#D\u0001\u0010\u0015\u0005\u0001\u0012!B:dC2\f\u0017B\u0001\n\u0010\u0005\u0019\te.\u001f*fMB\u0019A#\t\u0013\u000f\u0005UqbB\u0001\f\u001d\u001d\t92$D\u0001\u0019\u0015\tI\"$\u0001\u0004=e>|GOP\u0002\u0001\u0013\u0005Y\u0011BA\u000f\u000b\u0003\u001d\tGnZ3ce\u0006L!a\b\u0011\u0002\u000fA\f7m[1hK*\u0011QDC\u0005\u0003E\r\u0012\u0011\u0003\u0016:v]\u000e\fG/\u001a3ESZL7/[8o\u0015\ty\u0002\u0005\u0005\u0002&M5\t\u0001\"\u0003\u0002(\u0011\t1Qk\u00155peR\u0004\"!J\u0015\n\u0005)B!\u0001D+TQ>\u0014HoU5h]\u0016$\u0017A\u0002\u0013j]&$H\u0005F\u0001.!\tqa&\u0003\u00020\u001f\t!QK\\5u\u0003-!xNQ5h\u0013:$x\n\u001d;\u0015\u0005I\u0002\u0005cA\u001a7q5\tAG\u0003\u00026\u0015\u0005!Q\u000f^5m\u0013\t9DGA\u0002PaR\u0004\"!O\u001f\u000f\u0005ibdBA\f<\u0013\u0005\u0001\u0012BA\u0010\u0010\u0013\tqtH\u0001\u0004CS\u001eLe\u000e\u001e\u0006\u0003?=AQ!\u0011\u0002A\u0002\u0011\n\u0011\u0001_\u0001\u0006iF,x\u000e\u001e\u000b\u0004I\u0011+\u0005\"B!\u0004\u0001\u0004!\u0003\"\u0002$\u0004\u0001\u0004!\u0013!A=\u0002\tQlw\u000e\u001a\u000b\u0004I%S\u0005\"B!\u0005\u0001\u0004!\u0003\"\u0002$\u0005\u0001\u0004!\u0013!\u00024rk>$Hc\u0001\u0013N\u001d\")\u0011)\u0002a\u0001I!)a)\u0002a\u0001I\u0005!a-\\8e)\r!\u0013K\u0015\u0005\u0006\u0003\u001a\u0001\r\u0001\n\u0005\u0006\r\u001a\u0001\r\u0001\n"
)
public interface UShortTruncatedDivision extends TruncatedDivision, UShortSigned {
   // $FF: synthetic method
   static BigInt toBigIntOpt$(final UShortTruncatedDivision $this, final char x) {
      return $this.toBigIntOpt(x);
   }

   default BigInt toBigIntOpt(final char x) {
      return (BigInt).MODULE$.apply(UShort$.MODULE$.toBigInt$extension(x));
   }

   // $FF: synthetic method
   static char tquot$(final UShortTruncatedDivision $this, final char x, final char y) {
      return $this.tquot(x, y);
   }

   default char tquot(final char x, final char y) {
      return UShort$.MODULE$.$div$extension(x, y);
   }

   // $FF: synthetic method
   static char tmod$(final UShortTruncatedDivision $this, final char x, final char y) {
      return $this.tmod(x, y);
   }

   default char tmod(final char x, final char y) {
      return UShort$.MODULE$.$percent$extension(x, y);
   }

   // $FF: synthetic method
   static char fquot$(final UShortTruncatedDivision $this, final char x, final char y) {
      return $this.fquot(x, y);
   }

   default char fquot(final char x, final char y) {
      return UShort$.MODULE$.$div$extension(x, y);
   }

   // $FF: synthetic method
   static char fmod$(final UShortTruncatedDivision $this, final char x, final char y) {
      return $this.fmod(x, y);
   }

   default char fmod(final char x, final char y) {
      return UShort$.MODULE$.$percent$extension(x, y);
   }

   static void $init$(final UShortTruncatedDivision $this) {
   }
}
