package spire.std;

import algebra.ring.Field;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005]3q\u0001D\u0007\u0011\u0002\u0007\u0005!\u0003C\u0003-\u0001\u0011\u0005Q\u0006C\u00032\u0001\u0011\u0005#\u0007C\u00038\u0001\u0011\u0005\u0001\bC\u0003;\u0001\u0011\u00051\bC\u0003=\u0001\u0011\u0005Q\bC\u0003A\u0001\u0011\u0005\u0013\tC\u0003H\u0001\u0011\u0005\u0003\nC\u0003L\u0001\u0011\u00051\bC\u0003M\u0001\u0011\u0005S\nC\u0003Q\u0001\u0011\u0005\u0013\u000bC\u0003T\u0001\u0011\u0005AKA\u0007E_V\u0014G.Z%t\r&,G\u000e\u001a\u0006\u0003\u001d=\t1a\u001d;e\u0015\u0005\u0001\u0012!B:qSJ,7\u0001A\n\u0004\u0001MI\u0002C\u0001\u000b\u0018\u001b\u0005)\"\"\u0001\f\u0002\u000bM\u001c\u0017\r\\1\n\u0005a)\"AB!osJ+g\rE\u0002\u001bM%r!aG\u0012\u000f\u0005q\tcBA\u000f!\u001b\u0005q\"BA\u0010\u0012\u0003\u0019a$o\\8u}%\t\u0001#\u0003\u0002#\u001f\u00059\u0011\r\\4fEJ\f\u0017B\u0001\u0013&\u0003\u001d\u0001\u0018mY6bO\u0016T!AI\b\n\u0005\u001dB#!\u0002$jK2$'B\u0001\u0013&!\t!\"&\u0003\u0002,+\t1Ai\\;cY\u0016\fa\u0001J5oSR$C#\u0001\u0018\u0011\u0005Qy\u0013B\u0001\u0019\u0016\u0005\u0011)f.\u001b;\u0002\u000b5Lg.^:\u0015\u0007%\u001aT\u0007C\u00035\u0005\u0001\u0007\u0011&A\u0001b\u0011\u00151$\u00011\u0001*\u0003\u0005\u0011\u0017A\u00028fO\u0006$X\r\u0006\u0002*s!)Ag\u0001a\u0001S\u0005\u0019qN\\3\u0016\u0003%\nA\u0001\u001d7vgR\u0019\u0011FP \t\u000bQ*\u0001\u0019A\u0015\t\u000bY*\u0001\u0019A\u0015\u0002\u0007A|w\u000fF\u0002*\u0005\u000eCQ\u0001\u000e\u0004A\u0002%BQA\u000e\u0004A\u0002\u0011\u0003\"\u0001F#\n\u0005\u0019+\"aA%oi\u0006)A/[7fgR\u0019\u0011&\u0013&\t\u000bQ:\u0001\u0019A\u0015\t\u000bY:\u0001\u0019A\u0015\u0002\ti,'o\\\u0001\bMJ|W.\u00138u)\tIc\nC\u0003P\u0013\u0001\u0007A)A\u0001o\u0003)1'o\\7E_V\u0014G.\u001a\u000b\u0003SICQa\u0014\u0006A\u0002%\n1\u0001Z5w)\rISK\u0016\u0005\u0006i-\u0001\r!\u000b\u0005\u0006m-\u0001\r!\u000b"
)
public interface DoubleIsField extends Field.mcD.sp {
   // $FF: synthetic method
   static double minus$(final DoubleIsField $this, final double a, final double b) {
      return $this.minus(a, b);
   }

   default double minus(final double a, final double b) {
      return this.minus$mcD$sp(a, b);
   }

   // $FF: synthetic method
   static double negate$(final DoubleIsField $this, final double a) {
      return $this.negate(a);
   }

   default double negate(final double a) {
      return this.negate$mcD$sp(a);
   }

   // $FF: synthetic method
   static double one$(final DoubleIsField $this) {
      return $this.one();
   }

   default double one() {
      return this.one$mcD$sp();
   }

   // $FF: synthetic method
   static double plus$(final DoubleIsField $this, final double a, final double b) {
      return $this.plus(a, b);
   }

   default double plus(final double a, final double b) {
      return this.plus$mcD$sp(a, b);
   }

   // $FF: synthetic method
   static double pow$(final DoubleIsField $this, final double a, final int b) {
      return $this.pow(a, b);
   }

   default double pow(final double a, final int b) {
      return this.pow$mcD$sp(a, b);
   }

   // $FF: synthetic method
   static double times$(final DoubleIsField $this, final double a, final double b) {
      return $this.times(a, b);
   }

   default double times(final double a, final double b) {
      return this.times$mcD$sp(a, b);
   }

   // $FF: synthetic method
   static double zero$(final DoubleIsField $this) {
      return $this.zero();
   }

   default double zero() {
      return this.zero$mcD$sp();
   }

   // $FF: synthetic method
   static double fromInt$(final DoubleIsField $this, final int n) {
      return $this.fromInt(n);
   }

   default double fromInt(final int n) {
      return this.fromInt$mcD$sp(n);
   }

   // $FF: synthetic method
   static double fromDouble$(final DoubleIsField $this, final double n) {
      return $this.fromDouble(n);
   }

   default double fromDouble(final double n) {
      return this.fromDouble$mcD$sp(n);
   }

   // $FF: synthetic method
   static double div$(final DoubleIsField $this, final double a, final double b) {
      return $this.div(a, b);
   }

   default double div(final double a, final double b) {
      return this.div$mcD$sp(a, b);
   }

   // $FF: synthetic method
   static double minus$mcD$sp$(final DoubleIsField $this, final double a, final double b) {
      return $this.minus$mcD$sp(a, b);
   }

   default double minus$mcD$sp(final double a, final double b) {
      return a - b;
   }

   // $FF: synthetic method
   static double negate$mcD$sp$(final DoubleIsField $this, final double a) {
      return $this.negate$mcD$sp(a);
   }

   default double negate$mcD$sp(final double a) {
      return -a;
   }

   // $FF: synthetic method
   static double one$mcD$sp$(final DoubleIsField $this) {
      return $this.one$mcD$sp();
   }

   default double one$mcD$sp() {
      return (double)1.0F;
   }

   // $FF: synthetic method
   static double plus$mcD$sp$(final DoubleIsField $this, final double a, final double b) {
      return $this.plus$mcD$sp(a, b);
   }

   default double plus$mcD$sp(final double a, final double b) {
      return a + b;
   }

   // $FF: synthetic method
   static double pow$mcD$sp$(final DoubleIsField $this, final double a, final int b) {
      return $this.pow$mcD$sp(a, b);
   }

   default double pow$mcD$sp(final double a, final int b) {
      return Math.pow(a, (double)b);
   }

   // $FF: synthetic method
   static double times$mcD$sp$(final DoubleIsField $this, final double a, final double b) {
      return $this.times$mcD$sp(a, b);
   }

   default double times$mcD$sp(final double a, final double b) {
      return a * b;
   }

   // $FF: synthetic method
   static double zero$mcD$sp$(final DoubleIsField $this) {
      return $this.zero$mcD$sp();
   }

   default double zero$mcD$sp() {
      return (double)0.0F;
   }

   // $FF: synthetic method
   static double fromInt$mcD$sp$(final DoubleIsField $this, final int n) {
      return $this.fromInt$mcD$sp(n);
   }

   default double fromInt$mcD$sp(final int n) {
      return (double)n;
   }

   // $FF: synthetic method
   static double fromDouble$mcD$sp$(final DoubleIsField $this, final double n) {
      return $this.fromDouble$mcD$sp(n);
   }

   default double fromDouble$mcD$sp(final double n) {
      return n;
   }

   // $FF: synthetic method
   static double div$mcD$sp$(final DoubleIsField $this, final double a, final double b) {
      return $this.div$mcD$sp(a, b);
   }

   default double div$mcD$sp(final double a, final double b) {
      return a / b;
   }

   static void $init$(final DoubleIsField $this) {
   }
}
