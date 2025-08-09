package spire.std;

import algebra.ring.Signed;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005m2q!\u0002\u0004\u0011\u0002\u0007\u00051\u0002C\u0003*\u0001\u0011\u0005!\u0006C\u0003/\u0001\u0011\u0005q\u0006C\u00032\u0001\u0011\u0005#\u0007C\u00039\u0001\u0011\u0005\u0013H\u0001\u0007E_V\u0014G.Z*jO:,GM\u0003\u0002\b\u0011\u0005\u00191\u000f\u001e3\u000b\u0003%\tQa\u001d9je\u0016\u001c\u0001a\u0005\u0003\u0001\u0019I)\u0003CA\u0007\u0011\u001b\u0005q!\"A\b\u0002\u000bM\u001c\u0017\r\\1\n\u0005Eq!AB!osJ+g\rE\u0002\u0014?\tr!\u0001\u0006\u000f\u000f\u0005UQbB\u0001\f\u001a\u001b\u00059\"B\u0001\r\u000b\u0003\u0019a$o\\8u}%\t\u0011\"\u0003\u0002\u001c\u0011\u00059\u0011\r\\4fEJ\f\u0017BA\u000f\u001f\u0003\u001d\u0001\u0018mY6bO\u0016T!a\u0007\u0005\n\u0005\u0001\n#AB*jO:,GM\u0003\u0002\u001e=A\u0011QbI\u0005\u0003I9\u0011a\u0001R8vE2,\u0007C\u0001\u0014(\u001b\u00051\u0011B\u0001\u0015\u0007\u0005-!u.\u001e2mK>\u0013H-\u001a:\u0002\r\u0011Jg.\u001b;%)\u0005Y\u0003CA\u0007-\u0013\ticB\u0001\u0003V]&$\u0018!B8sI\u0016\u0014X#\u0001\u0019\u0011\u0005\u0019\u0002\u0011AB:jO:,X\u000e\u0006\u00024mA\u0011Q\u0002N\u0005\u0003k9\u00111!\u00138u\u0011\u001594\u00011\u0001#\u0003\u0005\t\u0017aA1cgR\u0011!E\u000f\u0005\u0006o\u0011\u0001\rA\t"
)
public interface DoubleSigned extends Signed.mcD.sp, DoubleOrder {
   // $FF: synthetic method
   static DoubleSigned order$(final DoubleSigned $this) {
      return $this.order();
   }

   default DoubleSigned order() {
      return this;
   }

   // $FF: synthetic method
   static int signum$(final DoubleSigned $this, final double a) {
      return $this.signum(a);
   }

   default int signum(final double a) {
      return this.signum$mcD$sp(a);
   }

   // $FF: synthetic method
   static double abs$(final DoubleSigned $this, final double a) {
      return $this.abs(a);
   }

   default double abs(final double a) {
      return this.abs$mcD$sp(a);
   }

   // $FF: synthetic method
   static int signum$mcD$sp$(final DoubleSigned $this, final double a) {
      return $this.signum$mcD$sp(a);
   }

   default int signum$mcD$sp(final double a) {
      return (int)Math.signum(a);
   }

   // $FF: synthetic method
   static double abs$mcD$sp$(final DoubleSigned $this, final double a) {
      return $this.abs$mcD$sp(a);
   }

   default double abs$mcD$sp(final double a) {
      return a < (double)0.0F ? -a : a;
   }

   static void $init$(final DoubleSigned $this) {
   }
}
