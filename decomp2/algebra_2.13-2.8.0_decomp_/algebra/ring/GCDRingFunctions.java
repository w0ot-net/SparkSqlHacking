package algebra.ring;

import cats.kernel.Eq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005a4q\u0001B\u0003\u0011\u0002\u0007\u0005!\u0002C\u0003*\u0001\u0011\u0005!\u0006C\u0003/\u0001\u0011\u0005q\u0006C\u0003d\u0001\u0011\u0005AM\u0001\tH\u0007\u0012\u0013\u0016N\\4Gk:\u001cG/[8og*\u0011aaB\u0001\u0005e&twMC\u0001\t\u0003\u001d\tGnZ3ce\u0006\u001c\u0001!\u0006\u0002\f1M\u0019\u0001\u0001\u0004\n\u0011\u00055\u0001R\"\u0001\b\u000b\u0003=\tQa]2bY\u0006L!!\u0005\b\u0003\r\u0005s\u0017PU3g!\r\u0019BCF\u0007\u0002\u000b%\u0011Q#\u0002\u0002\u000e%&twMR;oGRLwN\\:\u0011\u0005]AB\u0002\u0001\u0003\u00063\u0001\u0011\rA\u0007\u0002\u0002%V\u00111dI\t\u00039}\u0001\"!D\u000f\n\u0005yq!a\u0002(pi\"Lgn\u001a\t\u0004'\u0001\u0012\u0013BA\u0011\u0006\u0005\u001d95\t\u0012*j]\u001e\u0004\"aF\u0012\u0005\u000b\u0011B\"\u0019A\u0013\u0003\u0003Q\u000b\"\u0001\b\u0014\u0011\u000559\u0013B\u0001\u0015\u000f\u0005\r\te._\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003-\u0002\"!\u0004\u0017\n\u00055r!\u0001B+oSR\f1aZ2e+\t\u00014\u0007F\u00022?\u0006$2A\r*V!\t92\u0007B\u00055\u0005\u0001\u0006\t\u0011!b\u0001K\t\t\u0011\t\u000b\u00044me\u001a\u0005*\u0014\t\u0003\u001b]J!\u0001\u000f\b\u0003\u0017M\u0004XmY5bY&TX\rZ\u0019\u0006GiZT\b\u0010\b\u0003\u001bmJ!\u0001\u0010\b\u0002\u0007%sG/\r\u0003%}\t{aBA C\u001b\u0005\u0001%BA!\n\u0003\u0019a$o\\8u}%\tq\"M\u0003$\t\u0016;eI\u0004\u0002\u000e\u000b&\u0011aID\u0001\u0005\u0019>tw-\r\u0003%}\t{\u0011'B\u0012J\u00152[eBA\u0007K\u0013\tYe\"A\u0003GY>\fG/\r\u0003%}\t{\u0011'B\u0012O\u001fF\u0003fBA\u0007P\u0013\t\u0001f\"\u0001\u0004E_V\u0014G.Z\u0019\u0005Iy\u0012u\u0002C\u0003T\u0005\u0001\u000fA+\u0001\u0002fmB\u0019q\u0003\u0007\u001a\t\u000bY\u0013\u00019A,\u0002\u0007\u0015\f\u0018\tE\u0002Y9Jr!!\u0017.\u000e\u0003\u001dI!aW\u0004\u0002\u000fA\f7m[1hK&\u0011QL\u0018\u0002\u0003\u000bFT!aW\u0004\t\u000b\u0001\u0014\u0001\u0019\u0001\u001a\u0002\u0003\u0005DQA\u0019\u0002A\u0002I\n\u0011AY\u0001\u0004Y\u000elWCA3i)\r1go\u001e\u000b\u0004OJ$\bCA\fi\t%!4\u0001)A\u0001\u0002\u000b\u0007Q\u0005\u000b\u0004im)dg\u000e]\u0019\u0006GiZ4\u000eP\u0019\u0005Iy\u0012u\"M\u0003$\t\u0016kg)\r\u0003%}\t{\u0011'B\u0012J\u0015>\\\u0015\u0007\u0002\u0013?\u0005>\tTa\t(PcB\u000bD\u0001\n C\u001f!)1k\u0001a\u0002gB\u0019q\u0003G4\t\u000bY\u001b\u00019A;\u0011\u0007acv\rC\u0003a\u0007\u0001\u0007q\rC\u0003c\u0007\u0001\u0007q\r"
)
public interface GCDRingFunctions extends RingFunctions {
   // $FF: synthetic method
   static Object gcd$(final GCDRingFunctions $this, final Object a, final Object b, final GCDRing ev, final Eq eqA) {
      return $this.gcd(a, b, ev, eqA);
   }

   default Object gcd(final Object a, final Object b, final GCDRing ev, final Eq eqA) {
      return ev.gcd(a, b, eqA);
   }

   // $FF: synthetic method
   static Object lcm$(final GCDRingFunctions $this, final Object a, final Object b, final GCDRing ev, final Eq eqA) {
      return $this.lcm(a, b, ev, eqA);
   }

   default Object lcm(final Object a, final Object b, final GCDRing ev, final Eq eqA) {
      return ev.lcm(a, b, eqA);
   }

   // $FF: synthetic method
   static double gcd$mDc$sp$(final GCDRingFunctions $this, final double a, final double b, final GCDRing ev, final Eq eqA) {
      return $this.gcd$mDc$sp(a, b, ev, eqA);
   }

   default double gcd$mDc$sp(final double a, final double b, final GCDRing ev, final Eq eqA) {
      return ev.gcd$mcD$sp(a, b, eqA);
   }

   // $FF: synthetic method
   static float gcd$mFc$sp$(final GCDRingFunctions $this, final float a, final float b, final GCDRing ev, final Eq eqA) {
      return $this.gcd$mFc$sp(a, b, ev, eqA);
   }

   default float gcd$mFc$sp(final float a, final float b, final GCDRing ev, final Eq eqA) {
      return ev.gcd$mcF$sp(a, b, eqA);
   }

   // $FF: synthetic method
   static int gcd$mIc$sp$(final GCDRingFunctions $this, final int a, final int b, final GCDRing ev, final Eq eqA) {
      return $this.gcd$mIc$sp(a, b, ev, eqA);
   }

   default int gcd$mIc$sp(final int a, final int b, final GCDRing ev, final Eq eqA) {
      return ev.gcd$mcI$sp(a, b, eqA);
   }

   // $FF: synthetic method
   static long gcd$mJc$sp$(final GCDRingFunctions $this, final long a, final long b, final GCDRing ev, final Eq eqA) {
      return $this.gcd$mJc$sp(a, b, ev, eqA);
   }

   default long gcd$mJc$sp(final long a, final long b, final GCDRing ev, final Eq eqA) {
      return ev.gcd$mcJ$sp(a, b, eqA);
   }

   // $FF: synthetic method
   static double lcm$mDc$sp$(final GCDRingFunctions $this, final double a, final double b, final GCDRing ev, final Eq eqA) {
      return $this.lcm$mDc$sp(a, b, ev, eqA);
   }

   default double lcm$mDc$sp(final double a, final double b, final GCDRing ev, final Eq eqA) {
      return ev.lcm$mcD$sp(a, b, eqA);
   }

   // $FF: synthetic method
   static float lcm$mFc$sp$(final GCDRingFunctions $this, final float a, final float b, final GCDRing ev, final Eq eqA) {
      return $this.lcm$mFc$sp(a, b, ev, eqA);
   }

   default float lcm$mFc$sp(final float a, final float b, final GCDRing ev, final Eq eqA) {
      return ev.lcm$mcF$sp(a, b, eqA);
   }

   // $FF: synthetic method
   static int lcm$mIc$sp$(final GCDRingFunctions $this, final int a, final int b, final GCDRing ev, final Eq eqA) {
      return $this.lcm$mIc$sp(a, b, ev, eqA);
   }

   default int lcm$mIc$sp(final int a, final int b, final GCDRing ev, final Eq eqA) {
      return ev.lcm$mcI$sp(a, b, eqA);
   }

   // $FF: synthetic method
   static long lcm$mJc$sp$(final GCDRingFunctions $this, final long a, final long b, final GCDRing ev, final Eq eqA) {
      return $this.lcm$mJc$sp(a, b, ev, eqA);
   }

   default long lcm$mJc$sp(final long a, final long b, final GCDRing ev, final Eq eqA) {
      return ev.lcm$mcJ$sp(a, b, eqA);
   }

   static void $init$(final GCDRingFunctions $this) {
   }
}
