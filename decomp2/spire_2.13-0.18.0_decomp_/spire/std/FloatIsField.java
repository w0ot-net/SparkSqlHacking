package spire.std;

import algebra.ring.Field;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005i3q\u0001D\u0007\u0011\u0002\u0007\u0005!\u0003C\u0003-\u0001\u0011\u0005Q\u0006C\u00032\u0001\u0011\u0005#\u0007C\u00038\u0001\u0011\u0005\u0001\bC\u0003;\u0001\u0011\u00051\bC\u0003=\u0001\u0011\u0005Q\bC\u0003A\u0001\u0011\u0005\u0013\tC\u0003H\u0001\u0011\u0005\u0003\nC\u0003L\u0001\u0011\u00051\bC\u0003M\u0001\u0011\u0005S\nC\u0003Q\u0001\u0011\u0005\u0011\u000bC\u0003U\u0001\u0011\u0005SK\u0001\u0007GY>\fG/S:GS\u0016dGM\u0003\u0002\u000f\u001f\u0005\u00191\u000f\u001e3\u000b\u0003A\tQa\u001d9je\u0016\u001c\u0001aE\u0002\u0001'e\u0001\"\u0001F\f\u000e\u0003UQ\u0011AF\u0001\u0006g\u000e\fG.Y\u0005\u00031U\u0011a!\u00118z%\u00164\u0007c\u0001\u000e'S9\u00111d\t\b\u00039\u0005r!!\b\u0011\u000e\u0003yQ!aH\t\u0002\rq\u0012xn\u001c;?\u0013\u0005\u0001\u0012B\u0001\u0012\u0010\u0003\u001d\tGnZ3ce\u0006L!\u0001J\u0013\u0002\u000fA\f7m[1hK*\u0011!eD\u0005\u0003O!\u0012QAR5fY\u0012T!\u0001J\u0013\u0011\u0005QQ\u0013BA\u0016\u0016\u0005\u00151En\\1u\u0003\u0019!\u0013N\\5uIQ\ta\u0006\u0005\u0002\u0015_%\u0011\u0001'\u0006\u0002\u0005+:LG/A\u0003nS:,8\u000fF\u0002*gUBQ\u0001\u000e\u0002A\u0002%\n\u0011!\u0019\u0005\u0006m\t\u0001\r!K\u0001\u0002E\u00061a.Z4bi\u0016$\"!K\u001d\t\u000bQ\u001a\u0001\u0019A\u0015\u0002\u0007=tW-F\u0001*\u0003\u0011\u0001H.^:\u0015\u0007%rt\bC\u00035\u000b\u0001\u0007\u0011\u0006C\u00037\u000b\u0001\u0007\u0011&A\u0002q_^$2!\u000b\"D\u0011\u0015!d\u00011\u0001*\u0011\u00151d\u00011\u0001E!\t!R)\u0003\u0002G+\t\u0019\u0011J\u001c;\u0002\u000bQLW.Z:\u0015\u0007%J%\nC\u00035\u000f\u0001\u0007\u0011\u0006C\u00037\u000f\u0001\u0007\u0011&\u0001\u0003{KJ|\u0017a\u00024s_6Le\u000e\u001e\u000b\u0003S9CQaT\u0005A\u0002\u0011\u000b\u0011A\\\u0001\u0004I&4HcA\u0015S'\")AG\u0003a\u0001S!)aG\u0003a\u0001S\u0005QaM]8n\t>,(\r\\3\u0015\u0005%2\u0006\"B(\f\u0001\u00049\u0006C\u0001\u000bY\u0013\tIVC\u0001\u0004E_V\u0014G.\u001a"
)
public interface FloatIsField extends Field.mcF.sp {
   // $FF: synthetic method
   static float minus$(final FloatIsField $this, final float a, final float b) {
      return $this.minus(a, b);
   }

   default float minus(final float a, final float b) {
      return this.minus$mcF$sp(a, b);
   }

   // $FF: synthetic method
   static float negate$(final FloatIsField $this, final float a) {
      return $this.negate(a);
   }

   default float negate(final float a) {
      return this.negate$mcF$sp(a);
   }

   // $FF: synthetic method
   static float one$(final FloatIsField $this) {
      return $this.one();
   }

   default float one() {
      return this.one$mcF$sp();
   }

   // $FF: synthetic method
   static float plus$(final FloatIsField $this, final float a, final float b) {
      return $this.plus(a, b);
   }

   default float plus(final float a, final float b) {
      return this.plus$mcF$sp(a, b);
   }

   // $FF: synthetic method
   static float pow$(final FloatIsField $this, final float a, final int b) {
      return $this.pow(a, b);
   }

   default float pow(final float a, final int b) {
      return this.pow$mcF$sp(a, b);
   }

   // $FF: synthetic method
   static float times$(final FloatIsField $this, final float a, final float b) {
      return $this.times(a, b);
   }

   default float times(final float a, final float b) {
      return this.times$mcF$sp(a, b);
   }

   // $FF: synthetic method
   static float zero$(final FloatIsField $this) {
      return $this.zero();
   }

   default float zero() {
      return this.zero$mcF$sp();
   }

   // $FF: synthetic method
   static float fromInt$(final FloatIsField $this, final int n) {
      return $this.fromInt(n);
   }

   default float fromInt(final int n) {
      return this.fromInt$mcF$sp(n);
   }

   // $FF: synthetic method
   static float div$(final FloatIsField $this, final float a, final float b) {
      return $this.div(a, b);
   }

   default float div(final float a, final float b) {
      return this.div$mcF$sp(a, b);
   }

   // $FF: synthetic method
   static float fromDouble$(final FloatIsField $this, final double n) {
      return $this.fromDouble(n);
   }

   default float fromDouble(final double n) {
      return this.fromDouble$mcF$sp(n);
   }

   // $FF: synthetic method
   static float minus$mcF$sp$(final FloatIsField $this, final float a, final float b) {
      return $this.minus$mcF$sp(a, b);
   }

   default float minus$mcF$sp(final float a, final float b) {
      return a - b;
   }

   // $FF: synthetic method
   static float negate$mcF$sp$(final FloatIsField $this, final float a) {
      return $this.negate$mcF$sp(a);
   }

   default float negate$mcF$sp(final float a) {
      return -a;
   }

   // $FF: synthetic method
   static float one$mcF$sp$(final FloatIsField $this) {
      return $this.one$mcF$sp();
   }

   default float one$mcF$sp() {
      return 1.0F;
   }

   // $FF: synthetic method
   static float plus$mcF$sp$(final FloatIsField $this, final float a, final float b) {
      return $this.plus$mcF$sp(a, b);
   }

   default float plus$mcF$sp(final float a, final float b) {
      return a + b;
   }

   // $FF: synthetic method
   static float pow$mcF$sp$(final FloatIsField $this, final float a, final int b) {
      return $this.pow$mcF$sp(a, b);
   }

   default float pow$mcF$sp(final float a, final int b) {
      return (float)Math.pow((double)a, (double)b);
   }

   // $FF: synthetic method
   static float times$mcF$sp$(final FloatIsField $this, final float a, final float b) {
      return $this.times$mcF$sp(a, b);
   }

   default float times$mcF$sp(final float a, final float b) {
      return a * b;
   }

   // $FF: synthetic method
   static float zero$mcF$sp$(final FloatIsField $this) {
      return $this.zero$mcF$sp();
   }

   default float zero$mcF$sp() {
      return 0.0F;
   }

   // $FF: synthetic method
   static float fromInt$mcF$sp$(final FloatIsField $this, final int n) {
      return $this.fromInt$mcF$sp(n);
   }

   default float fromInt$mcF$sp(final int n) {
      return (float)n;
   }

   // $FF: synthetic method
   static float div$mcF$sp$(final FloatIsField $this, final float a, final float b) {
      return $this.div$mcF$sp(a, b);
   }

   default float div$mcF$sp(final float a, final float b) {
      return a / b;
   }

   // $FF: synthetic method
   static float fromDouble$mcF$sp$(final FloatIsField $this, final double n) {
      return $this.fromDouble$mcF$sp(n);
   }

   default float fromDouble$mcF$sp(final double n) {
      return (float)n;
   }

   static void $init$(final FloatIsField $this) {
   }
}
