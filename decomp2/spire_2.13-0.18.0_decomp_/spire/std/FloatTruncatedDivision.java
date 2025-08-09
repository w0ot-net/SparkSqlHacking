package spire.std;

import algebra.ring.TruncatedDivision;
import scala.math.BigInt;
import scala.reflect.ScalaSignature;
import scala.runtime.RichFloat.;

@ScalaSignature(
   bytes = "\u0006\u000553qAB\u0004\u0011\u0002\u0007\u0005A\u0002C\u0003+\u0001\u0011\u00051\u0006C\u00030\u0001\u0011\u0005\u0001\u0007C\u00033\u0001\u0011\u00051\u0007C\u0003E\u0001\u0011\u0005Q\tC\u0003J\u0001\u0011\u0005!J\u0001\fGY>\fG\u000f\u0016:v]\u000e\fG/\u001a3ESZL7/[8o\u0015\tA\u0011\"A\u0002ti\u0012T\u0011AC\u0001\u0006gBL'/Z\u0002\u0001'\u0011\u0001Qb\u0005\u0014\u0011\u00059\tR\"A\b\u000b\u0003A\tQa]2bY\u0006L!AE\b\u0003\r\u0005s\u0017PU3g!\r!\u0002e\t\b\u0003+uq!AF\u000e\u000f\u0005]QR\"\u0001\r\u000b\u0005eY\u0011A\u0002\u001fs_>$h(C\u0001\u000b\u0013\ta\u0012\"A\u0004bY\u001e,'M]1\n\u0005yy\u0012a\u00029bG.\fw-\u001a\u0006\u00039%I!!\t\u0012\u0003-Q\u0013XO\\2bi\u0016$G)\u001b<jg&|gn\u0011*j]\u001eT!AH\u0010\u0011\u00059!\u0013BA\u0013\u0010\u0005\u00151En\\1u!\t9\u0003&D\u0001\b\u0013\tIsAA\u0006GY>\fGoU5h]\u0016$\u0017A\u0002\u0013j]&$H\u0005F\u0001-!\tqQ&\u0003\u0002/\u001f\t!QK\\5u\u0003\u0015y'\u000fZ3s+\u0005\t\u0004CA\u0014\u0001\u0003-!xNQ5h\u0013:$x\n\u001d;\u0015\u0005Q\u0012\u0005cA\u001b9u5\taG\u0003\u00028\u0013\u0005!Q\u000f^5m\u0013\tIdGA\u0002PaR\u0004\"aO \u000f\u0005qrdBA\f>\u0013\u0005\u0001\u0012B\u0001\u0010\u0010\u0013\t\u0001\u0015I\u0001\u0004CS\u001eLe\u000e\u001e\u0006\u0003==AQaQ\u0002A\u0002\r\n\u0011!Y\u0001\u0006iF,x\u000e\u001e\u000b\u0004G\u0019;\u0005\"B\"\u0005\u0001\u0004\u0019\u0003\"\u0002%\u0005\u0001\u0004\u0019\u0013!\u00012\u0002\tQlw\u000e\u001a\u000b\u0004G-c\u0005\"B\"\u0006\u0001\u0004\u0019\u0003\"\u0002%\u0006\u0001\u0004\u0019\u0003"
)
public interface FloatTruncatedDivision extends TruncatedDivision.forCommutativeRing.mcF.sp, FloatSigned {
   // $FF: synthetic method
   static FloatTruncatedDivision order$(final FloatTruncatedDivision $this) {
      return $this.order();
   }

   default FloatTruncatedDivision order() {
      return this;
   }

   // $FF: synthetic method
   static BigInt toBigIntOpt$(final FloatTruncatedDivision $this, final float a) {
      return $this.toBigIntOpt(a);
   }

   default BigInt toBigIntOpt(final float a) {
      return .MODULE$.isWhole$extension(scala.Predef..MODULE$.floatWrapper(a)) ? (BigInt)spire.util.Opt..MODULE$.apply(scala.package..MODULE$.BigDecimal().apply((double)a).toBigInt()) : (BigInt)spire.util.Opt..MODULE$.empty();
   }

   // $FF: synthetic method
   static float tquot$(final FloatTruncatedDivision $this, final float a, final float b) {
      return $this.tquot(a, b);
   }

   default float tquot(final float a, final float b) {
      return this.tquot$mcF$sp(a, b);
   }

   // $FF: synthetic method
   static float tmod$(final FloatTruncatedDivision $this, final float a, final float b) {
      return $this.tmod(a, b);
   }

   default float tmod(final float a, final float b) {
      return this.tmod$mcF$sp(a, b);
   }

   // $FF: synthetic method
   static float tquot$mcF$sp$(final FloatTruncatedDivision $this, final float a, final float b) {
      return $this.tquot$mcF$sp(a, b);
   }

   default float tquot$mcF$sp(final float a, final float b) {
      return (a - a % b) / b;
   }

   // $FF: synthetic method
   static float tmod$mcF$sp$(final FloatTruncatedDivision $this, final float a, final float b) {
      return $this.tmod$mcF$sp(a, b);
   }

   default float tmod$mcF$sp(final float a, final float b) {
      return a % b;
   }

   static void $init$(final FloatTruncatedDivision $this) {
   }
}
