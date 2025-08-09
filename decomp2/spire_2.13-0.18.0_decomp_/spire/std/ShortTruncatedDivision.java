package spire.std;

import algebra.ring.TruncatedDivision;
import scala.math.BigInt;
import scala.reflect.ScalaSignature;
import spire.util.Opt.;

@ScalaSignature(
   bytes = "\u0006\u0005%3q!\u0002\u0004\u0011\u0002\u0007\u00051\u0002C\u0003*\u0001\u0011\u0005!\u0006C\u0003/\u0001\u0011\u0005q\u0006C\u0003A\u0001\u0011\u0005\u0011\tC\u0003F\u0001\u0011\u0005aI\u0001\fTQ>\u0014H\u000f\u0016:v]\u000e\fG/\u001a3ESZL7/[8o\u0015\t9\u0001\"A\u0002ti\u0012T\u0011!C\u0001\u0006gBL'/Z\u0002\u0001'\u0011\u0001ABE\u0013\u0011\u00055\u0001R\"\u0001\b\u000b\u0003=\tQa]2bY\u0006L!!\u0005\b\u0003\r\u0005s\u0017PU3g!\r\u0019rD\t\b\u0003)qq!!\u0006\u000e\u000f\u0005YIR\"A\f\u000b\u0005aQ\u0011A\u0002\u001fs_>$h(C\u0001\n\u0013\tY\u0002\"A\u0004bY\u001e,'M]1\n\u0005uq\u0012a\u00029bG.\fw-\u001a\u0006\u00037!I!\u0001I\u0011\u0003-Q\u0013XO\\2bi\u0016$G)\u001b<jg&|gn\u0011*j]\u001eT!!\b\u0010\u0011\u00055\u0019\u0013B\u0001\u0013\u000f\u0005\u0015\u0019\u0006n\u001c:u!\t1s%D\u0001\u0007\u0013\tAcAA\u0006TQ>\u0014HoU5h]\u0016$\u0017A\u0002\u0013j]&$H\u0005F\u0001,!\tiA&\u0003\u0002.\u001d\t!QK\\5u\u0003-!xNQ5h\u0013:$x\n\u001d;\u0015\u0005Ar\u0004cA\u00195m5\t!G\u0003\u00024\u0011\u0005!Q\u000f^5m\u0013\t)$GA\u0002PaR\u0004\"aN\u001e\u000f\u0005aRdB\u0001\f:\u0013\u0005y\u0011BA\u000f\u000f\u0013\taTH\u0001\u0004CS\u001eLe\u000e\u001e\u0006\u0003;9AQa\u0010\u0002A\u0002\t\n\u0011\u0001_\u0001\u0006iF,x\u000e\u001e\u000b\u0004E\t\u001b\u0005\"B \u0004\u0001\u0004\u0011\u0003\"\u0002#\u0004\u0001\u0004\u0011\u0013!A=\u0002\tQlw\u000e\u001a\u000b\u0004E\u001dC\u0005\"B \u0005\u0001\u0004\u0011\u0003\"\u0002#\u0005\u0001\u0004\u0011\u0003"
)
public interface ShortTruncatedDivision extends TruncatedDivision.forCommutativeRing.mcS.sp, ShortSigned {
   // $FF: synthetic method
   static BigInt toBigIntOpt$(final ShortTruncatedDivision $this, final short x) {
      return $this.toBigIntOpt(x);
   }

   default BigInt toBigIntOpt(final short x) {
      return (BigInt).MODULE$.apply(scala.package..MODULE$.BigInt().apply(x));
   }

   // $FF: synthetic method
   static short tquot$(final ShortTruncatedDivision $this, final short x, final short y) {
      return $this.tquot(x, y);
   }

   default short tquot(final short x, final short y) {
      return this.tquot$mcS$sp(x, y);
   }

   // $FF: synthetic method
   static short tmod$(final ShortTruncatedDivision $this, final short x, final short y) {
      return $this.tmod(x, y);
   }

   default short tmod(final short x, final short y) {
      return this.tmod$mcS$sp(x, y);
   }

   // $FF: synthetic method
   static short tquot$mcS$sp$(final ShortTruncatedDivision $this, final short x, final short y) {
      return $this.tquot$mcS$sp(x, y);
   }

   default short tquot$mcS$sp(final short x, final short y) {
      return (short)(x / y);
   }

   // $FF: synthetic method
   static short tmod$mcS$sp$(final ShortTruncatedDivision $this, final short x, final short y) {
      return $this.tmod$mcS$sp(x, y);
   }

   default short tmod$mcS$sp(final short x, final short y) {
      return (short)(x % y);
   }

   static void $init$(final ShortTruncatedDivision $this) {
   }
}
