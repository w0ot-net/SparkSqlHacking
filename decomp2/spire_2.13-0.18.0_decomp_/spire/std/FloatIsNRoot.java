package spire.std;

import scala.reflect.ScalaSignature;
import spire.algebra.NRoot$mcF$sp;

@ScalaSignature(
   bytes = "\u0006\u0005E2q!\u0002\u0004\u0011\u0002\u0007\u00051\u0002C\u0003\u001c\u0001\u0011\u0005A\u0004C\u0003!\u0001\u0011\u0005\u0011\u0005C\u0003*\u0001\u0011\u0005#\u0006C\u0003-\u0001\u0011\u0005QF\u0001\u0007GY>\fG/S:O%>|GO\u0003\u0002\b\u0011\u0005\u00191\u000f\u001e3\u000b\u0003%\tQa\u001d9je\u0016\u001c\u0001aE\u0002\u0001\u0019I\u0001\"!\u0004\t\u000e\u00039Q\u0011aD\u0001\u0006g\u000e\fG.Y\u0005\u0003#9\u0011a!\u00118z%\u00164\u0007cA\n\u001715\tAC\u0003\u0002\u0016\u0011\u00059\u0011\r\\4fEJ\f\u0017BA\f\u0015\u0005\u0015q%k\\8u!\ti\u0011$\u0003\u0002\u001b\u001d\t)a\t\\8bi\u00061A%\u001b8ji\u0012\"\u0012!\b\t\u0003\u001byI!a\b\b\u0003\tUs\u0017\u000e^\u0001\u0006]J|w\u000e\u001e\u000b\u00041\t\"\u0003\"B\u0012\u0003\u0001\u0004A\u0012!A1\t\u000b\u0015\u0012\u0001\u0019\u0001\u0014\u0002\u0003-\u0004\"!D\u0014\n\u0005!r!aA%oi\u0006!1/\u001d:u)\tA2\u0006C\u0003$\u0007\u0001\u0007\u0001$\u0001\u0003ga><Hc\u0001\r/_!)1\u0005\u0002a\u00011!)\u0001\u0007\u0002a\u00011\u0005\t!\r"
)
public interface FloatIsNRoot extends NRoot$mcF$sp {
   // $FF: synthetic method
   static float nroot$(final FloatIsNRoot $this, final float a, final int k) {
      return $this.nroot(a, k);
   }

   default float nroot(final float a, final int k) {
      return this.nroot$mcF$sp(a, k);
   }

   // $FF: synthetic method
   static float sqrt$(final FloatIsNRoot $this, final float a) {
      return $this.sqrt(a);
   }

   default float sqrt(final float a) {
      return this.sqrt$mcF$sp(a);
   }

   // $FF: synthetic method
   static float fpow$(final FloatIsNRoot $this, final float a, final float b) {
      return $this.fpow(a, b);
   }

   default float fpow(final float a, final float b) {
      return this.fpow$mcF$sp(a, b);
   }

   // $FF: synthetic method
   static float nroot$mcF$sp$(final FloatIsNRoot $this, final float a, final int k) {
      return $this.nroot$mcF$sp(a, k);
   }

   default float nroot$mcF$sp(final float a, final int k) {
      return (float)Math.pow((double)a, (double)1 / (double)k);
   }

   // $FF: synthetic method
   static float sqrt$mcF$sp$(final FloatIsNRoot $this, final float a) {
      return $this.sqrt$mcF$sp(a);
   }

   default float sqrt$mcF$sp(final float a) {
      return (float)Math.sqrt((double)a);
   }

   // $FF: synthetic method
   static float fpow$mcF$sp$(final FloatIsNRoot $this, final float a, final float b) {
      return $this.fpow$mcF$sp(a, b);
   }

   default float fpow$mcF$sp(final float a, final float b) {
      return (float)Math.pow((double)a, (double)b);
   }

   static void $init$(final FloatIsNRoot $this) {
   }
}
