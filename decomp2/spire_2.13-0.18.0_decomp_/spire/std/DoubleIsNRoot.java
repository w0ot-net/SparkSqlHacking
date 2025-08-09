package spire.std;

import scala.reflect.ScalaSignature;
import spire.algebra.NRoot$mcD$sp;

@ScalaSignature(
   bytes = "\u0006\u0005E2q!\u0002\u0004\u0011\u0002\u0007\u00051\u0002C\u0003\u001c\u0001\u0011\u0005A\u0004C\u0003!\u0001\u0011\u0005\u0011\u0005C\u0003*\u0001\u0011\u0005#\u0006C\u0003-\u0001\u0011\u0005QFA\u0007E_V\u0014G.Z%t\u001dJ{w\u000e\u001e\u0006\u0003\u000f!\t1a\u001d;e\u0015\u0005I\u0011!B:qSJ,7\u0001A\n\u0004\u00011\u0011\u0002CA\u0007\u0011\u001b\u0005q!\"A\b\u0002\u000bM\u001c\u0017\r\\1\n\u0005Eq!AB!osJ+g\rE\u0002\u0014-ai\u0011\u0001\u0006\u0006\u0003+!\tq!\u00197hK\n\u0014\u0018-\u0003\u0002\u0018)\t)aJU8piB\u0011Q\"G\u0005\u000359\u0011a\u0001R8vE2,\u0017A\u0002\u0013j]&$H\u0005F\u0001\u001e!\tia$\u0003\u0002 \u001d\t!QK\\5u\u0003\u0015q'o\\8u)\rA\"\u0005\n\u0005\u0006G\t\u0001\r\u0001G\u0001\u0002C\")QE\u0001a\u0001M\u0005\t1\u000e\u0005\u0002\u000eO%\u0011\u0001F\u0004\u0002\u0004\u0013:$\u0018\u0001B:reR$\"\u0001G\u0016\t\u000b\r\u001a\u0001\u0019\u0001\r\u0002\t\u0019\u0004xn\u001e\u000b\u000419z\u0003\"B\u0012\u0005\u0001\u0004A\u0002\"\u0002\u0019\u0005\u0001\u0004A\u0012!\u00012"
)
public interface DoubleIsNRoot extends NRoot$mcD$sp {
   // $FF: synthetic method
   static double nroot$(final DoubleIsNRoot $this, final double a, final int k) {
      return $this.nroot(a, k);
   }

   default double nroot(final double a, final int k) {
      return this.nroot$mcD$sp(a, k);
   }

   // $FF: synthetic method
   static double sqrt$(final DoubleIsNRoot $this, final double a) {
      return $this.sqrt(a);
   }

   default double sqrt(final double a) {
      return this.sqrt$mcD$sp(a);
   }

   // $FF: synthetic method
   static double fpow$(final DoubleIsNRoot $this, final double a, final double b) {
      return $this.fpow(a, b);
   }

   default double fpow(final double a, final double b) {
      return this.fpow$mcD$sp(a, b);
   }

   // $FF: synthetic method
   static double nroot$mcD$sp$(final DoubleIsNRoot $this, final double a, final int k) {
      return $this.nroot$mcD$sp(a, k);
   }

   default double nroot$mcD$sp(final double a, final int k) {
      return Math.pow(a, (double)1 / (double)k);
   }

   // $FF: synthetic method
   static double sqrt$mcD$sp$(final DoubleIsNRoot $this, final double a) {
      return $this.sqrt$mcD$sp(a);
   }

   default double sqrt$mcD$sp(final double a) {
      return Math.sqrt(a);
   }

   // $FF: synthetic method
   static double fpow$mcD$sp$(final DoubleIsNRoot $this, final double a, final double b) {
      return $this.fpow$mcD$sp(a, b);
   }

   default double fpow$mcD$sp(final double a, final double b) {
      return Math.pow(a, b);
   }

   static void $init$(final DoubleIsNRoot $this) {
   }
}
