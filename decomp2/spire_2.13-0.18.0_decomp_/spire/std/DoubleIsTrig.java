package spire.std;

import scala.reflect.ScalaSignature;
import spire.algebra.Trig$mcD$sp;

@ScalaSignature(
   bytes = "\u0006\u0005\u00194q\u0001F\u000b\u0011\u0002\u0007\u0005!\u0004C\u0003+\u0001\u0011\u00051\u0006C\u00030\u0001\u0011\u0005\u0001\u0007C\u00032\u0001\u0011\u0005\u0001\u0007C\u00033\u0001\u0011\u00051\u0007C\u00037\u0001\u0011\u0005q\u0007C\u0003:\u0001\u0011\u0005!\bC\u0003=\u0001\u0011\u0005Q\bC\u0003@\u0001\u0011\u0005\u0001\tC\u0003C\u0001\u0011\u00051\tC\u0003F\u0001\u0011\u0005a\tC\u0003I\u0001\u0011\u0005\u0011\nC\u0003L\u0001\u0011\u0005A\nC\u0003O\u0001\u0011\u0005q\nC\u0003R\u0001\u0011\u0005!\u000bC\u0003X\u0001\u0011\u0005\u0001\fC\u0003[\u0001\u0011\u00051\fC\u0003^\u0001\u0011\u0005a\fC\u0003a\u0001\u0011\u0005\u0011\rC\u0003d\u0001\u0011\u0005AM\u0001\u0007E_V\u0014G.Z%t)JLwM\u0003\u0002\u0017/\u0005\u00191\u000f\u001e3\u000b\u0003a\tQa\u001d9je\u0016\u001c\u0001aE\u0002\u00017\u0005\u0002\"\u0001H\u0010\u000e\u0003uQ\u0011AH\u0001\u0006g\u000e\fG.Y\u0005\u0003Au\u0011a!\u00118z%\u00164\u0007c\u0001\u0012&O5\t1E\u0003\u0002%/\u00059\u0011\r\\4fEJ\f\u0017B\u0001\u0014$\u0005\u0011!&/[4\u0011\u0005qA\u0013BA\u0015\u001e\u0005\u0019!u.\u001e2mK\u00061A%\u001b8ji\u0012\"\u0012\u0001\f\t\u000395J!AL\u000f\u0003\tUs\u0017\u000e^\u0001\u0002KV\tq%\u0001\u0002qS\u0006\u0019Q\r\u001f9\u0015\u0005\u001d\"\u0004\"B\u001b\u0005\u0001\u00049\u0013!A1\u0002\u000b\u0015D\b/\\\u0019\u0015\u0005\u001dB\u0004\"B\u001b\u0006\u0001\u00049\u0013a\u00017pOR\u0011qe\u000f\u0005\u0006k\u0019\u0001\raJ\u0001\u0006Y><\u0017\u0007\u001d\u000b\u0003OyBQ!N\u0004A\u0002\u001d\n1a]5o)\t9\u0013\tC\u00036\u0011\u0001\u0007q%A\u0002d_N$\"a\n#\t\u000bUJ\u0001\u0019A\u0014\u0002\u0007Q\fg\u000e\u0006\u0002(\u000f\")QG\u0003a\u0001O\u0005!\u0011m]5o)\t9#\nC\u00036\u0017\u0001\u0007q%\u0001\u0003bG>\u001cHCA\u0014N\u0011\u0015)D\u00021\u0001(\u0003\u0011\tG/\u00198\u0015\u0005\u001d\u0002\u0006\"B\u001b\u000e\u0001\u00049\u0013!B1uC:\u0014DcA\u0014T+\")AK\u0004a\u0001O\u0005\t\u0011\u0010C\u0003W\u001d\u0001\u0007q%A\u0001y\u0003\u0011\u0019\u0018N\u001c5\u0015\u0005\u001dJ\u0006\"\u0002,\u0010\u0001\u00049\u0013\u0001B2pg\"$\"a\n/\t\u000bY\u0003\u0002\u0019A\u0014\u0002\tQ\fg\u000e\u001b\u000b\u0003O}CQAV\tA\u0002\u001d\n\u0011\u0002^8SC\u0012L\u0017M\\:\u0015\u0005\u001d\u0012\u0007\"B\u001b\u0013\u0001\u00049\u0013!\u0003;p\t\u0016<'/Z3t)\t9S\rC\u00036'\u0001\u0007q\u0005"
)
public interface DoubleIsTrig extends Trig$mcD$sp {
   // $FF: synthetic method
   static double e$(final DoubleIsTrig $this) {
      return $this.e();
   }

   default double e() {
      return this.e$mcD$sp();
   }

   // $FF: synthetic method
   static double pi$(final DoubleIsTrig $this) {
      return $this.pi();
   }

   default double pi() {
      return this.pi$mcD$sp();
   }

   // $FF: synthetic method
   static double exp$(final DoubleIsTrig $this, final double a) {
      return $this.exp(a);
   }

   default double exp(final double a) {
      return this.exp$mcD$sp(a);
   }

   // $FF: synthetic method
   static double expm1$(final DoubleIsTrig $this, final double a) {
      return $this.expm1(a);
   }

   default double expm1(final double a) {
      return this.expm1$mcD$sp(a);
   }

   // $FF: synthetic method
   static double log$(final DoubleIsTrig $this, final double a) {
      return $this.log(a);
   }

   default double log(final double a) {
      return this.log$mcD$sp(a);
   }

   // $FF: synthetic method
   static double log1p$(final DoubleIsTrig $this, final double a) {
      return $this.log1p(a);
   }

   default double log1p(final double a) {
      return this.log1p$mcD$sp(a);
   }

   // $FF: synthetic method
   static double sin$(final DoubleIsTrig $this, final double a) {
      return $this.sin(a);
   }

   default double sin(final double a) {
      return this.sin$mcD$sp(a);
   }

   // $FF: synthetic method
   static double cos$(final DoubleIsTrig $this, final double a) {
      return $this.cos(a);
   }

   default double cos(final double a) {
      return this.cos$mcD$sp(a);
   }

   // $FF: synthetic method
   static double tan$(final DoubleIsTrig $this, final double a) {
      return $this.tan(a);
   }

   default double tan(final double a) {
      return this.tan$mcD$sp(a);
   }

   // $FF: synthetic method
   static double asin$(final DoubleIsTrig $this, final double a) {
      return $this.asin(a);
   }

   default double asin(final double a) {
      return this.asin$mcD$sp(a);
   }

   // $FF: synthetic method
   static double acos$(final DoubleIsTrig $this, final double a) {
      return $this.acos(a);
   }

   default double acos(final double a) {
      return this.acos$mcD$sp(a);
   }

   // $FF: synthetic method
   static double atan$(final DoubleIsTrig $this, final double a) {
      return $this.atan(a);
   }

   default double atan(final double a) {
      return this.atan$mcD$sp(a);
   }

   // $FF: synthetic method
   static double atan2$(final DoubleIsTrig $this, final double y, final double x) {
      return $this.atan2(y, x);
   }

   default double atan2(final double y, final double x) {
      return this.atan2$mcD$sp(y, x);
   }

   // $FF: synthetic method
   static double sinh$(final DoubleIsTrig $this, final double x) {
      return $this.sinh(x);
   }

   default double sinh(final double x) {
      return this.sinh$mcD$sp(x);
   }

   // $FF: synthetic method
   static double cosh$(final DoubleIsTrig $this, final double x) {
      return $this.cosh(x);
   }

   default double cosh(final double x) {
      return this.cosh$mcD$sp(x);
   }

   // $FF: synthetic method
   static double tanh$(final DoubleIsTrig $this, final double x) {
      return $this.tanh(x);
   }

   default double tanh(final double x) {
      return this.tanh$mcD$sp(x);
   }

   // $FF: synthetic method
   static double toRadians$(final DoubleIsTrig $this, final double a) {
      return $this.toRadians(a);
   }

   default double toRadians(final double a) {
      return this.toRadians$mcD$sp(a);
   }

   // $FF: synthetic method
   static double toDegrees$(final DoubleIsTrig $this, final double a) {
      return $this.toDegrees(a);
   }

   default double toDegrees(final double a) {
      return this.toDegrees$mcD$sp(a);
   }

   // $FF: synthetic method
   static double e$mcD$sp$(final DoubleIsTrig $this) {
      return $this.e$mcD$sp();
   }

   default double e$mcD$sp() {
      return Math.E;
   }

   // $FF: synthetic method
   static double pi$mcD$sp$(final DoubleIsTrig $this) {
      return $this.pi$mcD$sp();
   }

   default double pi$mcD$sp() {
      return Math.PI;
   }

   // $FF: synthetic method
   static double exp$mcD$sp$(final DoubleIsTrig $this, final double a) {
      return $this.exp$mcD$sp(a);
   }

   default double exp$mcD$sp(final double a) {
      return Math.exp(a);
   }

   // $FF: synthetic method
   static double expm1$mcD$sp$(final DoubleIsTrig $this, final double a) {
      return $this.expm1$mcD$sp(a);
   }

   default double expm1$mcD$sp(final double a) {
      return Math.expm1(a);
   }

   // $FF: synthetic method
   static double log$mcD$sp$(final DoubleIsTrig $this, final double a) {
      return $this.log$mcD$sp(a);
   }

   default double log$mcD$sp(final double a) {
      return Math.log(a);
   }

   // $FF: synthetic method
   static double log1p$mcD$sp$(final DoubleIsTrig $this, final double a) {
      return $this.log1p$mcD$sp(a);
   }

   default double log1p$mcD$sp(final double a) {
      return Math.log1p(a);
   }

   // $FF: synthetic method
   static double sin$mcD$sp$(final DoubleIsTrig $this, final double a) {
      return $this.sin$mcD$sp(a);
   }

   default double sin$mcD$sp(final double a) {
      return Math.sin(a);
   }

   // $FF: synthetic method
   static double cos$mcD$sp$(final DoubleIsTrig $this, final double a) {
      return $this.cos$mcD$sp(a);
   }

   default double cos$mcD$sp(final double a) {
      return Math.cos(a);
   }

   // $FF: synthetic method
   static double tan$mcD$sp$(final DoubleIsTrig $this, final double a) {
      return $this.tan$mcD$sp(a);
   }

   default double tan$mcD$sp(final double a) {
      return Math.tan(a);
   }

   // $FF: synthetic method
   static double asin$mcD$sp$(final DoubleIsTrig $this, final double a) {
      return $this.asin$mcD$sp(a);
   }

   default double asin$mcD$sp(final double a) {
      return Math.asin(a);
   }

   // $FF: synthetic method
   static double acos$mcD$sp$(final DoubleIsTrig $this, final double a) {
      return $this.acos$mcD$sp(a);
   }

   default double acos$mcD$sp(final double a) {
      return Math.acos(a);
   }

   // $FF: synthetic method
   static double atan$mcD$sp$(final DoubleIsTrig $this, final double a) {
      return $this.atan$mcD$sp(a);
   }

   default double atan$mcD$sp(final double a) {
      return Math.atan(a);
   }

   // $FF: synthetic method
   static double atan2$mcD$sp$(final DoubleIsTrig $this, final double y, final double x) {
      return $this.atan2$mcD$sp(y, x);
   }

   default double atan2$mcD$sp(final double y, final double x) {
      return Math.atan2(y, x);
   }

   // $FF: synthetic method
   static double sinh$mcD$sp$(final DoubleIsTrig $this, final double x) {
      return $this.sinh$mcD$sp(x);
   }

   default double sinh$mcD$sp(final double x) {
      return Math.sinh(x);
   }

   // $FF: synthetic method
   static double cosh$mcD$sp$(final DoubleIsTrig $this, final double x) {
      return $this.cosh$mcD$sp(x);
   }

   default double cosh$mcD$sp(final double x) {
      return Math.cosh(x);
   }

   // $FF: synthetic method
   static double tanh$mcD$sp$(final DoubleIsTrig $this, final double x) {
      return $this.tanh$mcD$sp(x);
   }

   default double tanh$mcD$sp(final double x) {
      return Math.tanh(x);
   }

   // $FF: synthetic method
   static double toRadians$mcD$sp$(final DoubleIsTrig $this, final double a) {
      return $this.toRadians$mcD$sp(a);
   }

   default double toRadians$mcD$sp(final double a) {
      return a * (double)2 * this.pi$mcD$sp() / (double)360;
   }

   // $FF: synthetic method
   static double toDegrees$mcD$sp$(final DoubleIsTrig $this, final double a) {
      return $this.toDegrees$mcD$sp(a);
   }

   default double toDegrees$mcD$sp(final double a) {
      return a * (double)360 / ((double)2 * this.pi$mcD$sp());
   }

   static void $init$(final DoubleIsTrig $this) {
   }
}
