package spire.std;

import scala.reflect.ScalaSignature;
import spire.algebra.Trig$mcF$sp;

@ScalaSignature(
   bytes = "\u0006\u0005\u00194q\u0001F\u000b\u0011\u0002\u0007\u0005!\u0004C\u0003+\u0001\u0011\u00051\u0006C\u00030\u0001\u0011\u0005\u0001\u0007C\u00032\u0001\u0011\u0005\u0001\u0007C\u00033\u0001\u0011\u00051\u0007C\u00037\u0001\u0011\u0005q\u0007C\u0003:\u0001\u0011\u0005!\bC\u0003=\u0001\u0011\u0005Q\bC\u0003@\u0001\u0011\u0005\u0001\tC\u0003C\u0001\u0011\u00051\tC\u0003F\u0001\u0011\u0005a\tC\u0003I\u0001\u0011\u0005\u0011\nC\u0003L\u0001\u0011\u0005A\nC\u0003O\u0001\u0011\u0005q\nC\u0003R\u0001\u0011\u0005!\u000bC\u0003X\u0001\u0011\u0005\u0001\fC\u0003[\u0001\u0011\u00051\fC\u0003^\u0001\u0011\u0005a\fC\u0003a\u0001\u0011\u0005\u0011\rC\u0003d\u0001\u0011\u0005AMA\u0006GY>\fG/S:Ue&<'B\u0001\f\u0018\u0003\r\u0019H\u000f\u001a\u0006\u00021\u0005)1\u000f]5sK\u000e\u00011c\u0001\u0001\u001cCA\u0011AdH\u0007\u0002;)\ta$A\u0003tG\u0006d\u0017-\u0003\u0002!;\t1\u0011I\\=SK\u001a\u00042AI\u0013(\u001b\u0005\u0019#B\u0001\u0013\u0018\u0003\u001d\tGnZ3ce\u0006L!AJ\u0012\u0003\tQ\u0013\u0018n\u001a\t\u00039!J!!K\u000f\u0003\u000b\u0019cw.\u0019;\u0002\r\u0011Jg.\u001b;%)\u0005a\u0003C\u0001\u000f.\u0013\tqSD\u0001\u0003V]&$\u0018!A3\u0016\u0003\u001d\n!\u0001]5\u0002\u0007\u0015D\b\u000f\u0006\u0002(i!)Q\u0007\u0002a\u0001O\u0005\t\u0011-A\u0003fqBl\u0017\u0007\u0006\u0002(q!)Q'\u0002a\u0001O\u0005\u0019An\\4\u0015\u0005\u001dZ\u0004\"B\u001b\u0007\u0001\u00049\u0013!\u00027pOF\u0002HCA\u0014?\u0011\u0015)t\u00011\u0001(\u0003\r\u0019\u0018N\u001c\u000b\u0003O\u0005CQ!\u000e\u0005A\u0002\u001d\n1aY8t)\t9C\tC\u00036\u0013\u0001\u0007q%A\u0002uC:$\"aJ$\t\u000bUR\u0001\u0019A\u0014\u0002\t\u0005\u001c\u0018N\u001c\u000b\u0003O)CQ!N\u0006A\u0002\u001d\nA!Y2pgR\u0011q%\u0014\u0005\u0006k1\u0001\raJ\u0001\u0005CR\fg\u000e\u0006\u0002(!\")Q'\u0004a\u0001O\u0005)\u0011\r^1oeQ\u0019qeU+\t\u000bQs\u0001\u0019A\u0014\u0002\u0003eDQA\u0016\bA\u0002\u001d\n\u0011\u0001_\u0001\u0005g&t\u0007\u000e\u0006\u0002(3\")ak\u0004a\u0001O\u0005!1m\\:i)\t9C\fC\u0003W!\u0001\u0007q%\u0001\u0003uC:DGCA\u0014`\u0011\u00151\u0016\u00031\u0001(\u0003%!xNU1eS\u0006t7\u000f\u0006\u0002(E\")QG\u0005a\u0001O\u0005IAo\u001c#fOJ,Wm\u001d\u000b\u0003O\u0015DQ!N\nA\u0002\u001d\u0002"
)
public interface FloatIsTrig extends Trig$mcF$sp {
   // $FF: synthetic method
   static float e$(final FloatIsTrig $this) {
      return $this.e();
   }

   default float e() {
      return this.e$mcF$sp();
   }

   // $FF: synthetic method
   static float pi$(final FloatIsTrig $this) {
      return $this.pi();
   }

   default float pi() {
      return this.pi$mcF$sp();
   }

   // $FF: synthetic method
   static float exp$(final FloatIsTrig $this, final float a) {
      return $this.exp(a);
   }

   default float exp(final float a) {
      return this.exp$mcF$sp(a);
   }

   // $FF: synthetic method
   static float expm1$(final FloatIsTrig $this, final float a) {
      return $this.expm1(a);
   }

   default float expm1(final float a) {
      return this.expm1$mcF$sp(a);
   }

   // $FF: synthetic method
   static float log$(final FloatIsTrig $this, final float a) {
      return $this.log(a);
   }

   default float log(final float a) {
      return this.log$mcF$sp(a);
   }

   // $FF: synthetic method
   static float log1p$(final FloatIsTrig $this, final float a) {
      return $this.log1p(a);
   }

   default float log1p(final float a) {
      return this.log1p$mcF$sp(a);
   }

   // $FF: synthetic method
   static float sin$(final FloatIsTrig $this, final float a) {
      return $this.sin(a);
   }

   default float sin(final float a) {
      return this.sin$mcF$sp(a);
   }

   // $FF: synthetic method
   static float cos$(final FloatIsTrig $this, final float a) {
      return $this.cos(a);
   }

   default float cos(final float a) {
      return this.cos$mcF$sp(a);
   }

   // $FF: synthetic method
   static float tan$(final FloatIsTrig $this, final float a) {
      return $this.tan(a);
   }

   default float tan(final float a) {
      return this.tan$mcF$sp(a);
   }

   // $FF: synthetic method
   static float asin$(final FloatIsTrig $this, final float a) {
      return $this.asin(a);
   }

   default float asin(final float a) {
      return this.asin$mcF$sp(a);
   }

   // $FF: synthetic method
   static float acos$(final FloatIsTrig $this, final float a) {
      return $this.acos(a);
   }

   default float acos(final float a) {
      return this.acos$mcF$sp(a);
   }

   // $FF: synthetic method
   static float atan$(final FloatIsTrig $this, final float a) {
      return $this.atan(a);
   }

   default float atan(final float a) {
      return this.atan$mcF$sp(a);
   }

   // $FF: synthetic method
   static float atan2$(final FloatIsTrig $this, final float y, final float x) {
      return $this.atan2(y, x);
   }

   default float atan2(final float y, final float x) {
      return this.atan2$mcF$sp(y, x);
   }

   // $FF: synthetic method
   static float sinh$(final FloatIsTrig $this, final float x) {
      return $this.sinh(x);
   }

   default float sinh(final float x) {
      return this.sinh$mcF$sp(x);
   }

   // $FF: synthetic method
   static float cosh$(final FloatIsTrig $this, final float x) {
      return $this.cosh(x);
   }

   default float cosh(final float x) {
      return this.cosh$mcF$sp(x);
   }

   // $FF: synthetic method
   static float tanh$(final FloatIsTrig $this, final float x) {
      return $this.tanh(x);
   }

   default float tanh(final float x) {
      return this.tanh$mcF$sp(x);
   }

   // $FF: synthetic method
   static float toRadians$(final FloatIsTrig $this, final float a) {
      return $this.toRadians(a);
   }

   default float toRadians(final float a) {
      return this.toRadians$mcF$sp(a);
   }

   // $FF: synthetic method
   static float toDegrees$(final FloatIsTrig $this, final float a) {
      return $this.toDegrees(a);
   }

   default float toDegrees(final float a) {
      return this.toDegrees$mcF$sp(a);
   }

   // $FF: synthetic method
   static float e$mcF$sp$(final FloatIsTrig $this) {
      return $this.e$mcF$sp();
   }

   default float e$mcF$sp() {
      return (float)Math.E;
   }

   // $FF: synthetic method
   static float pi$mcF$sp$(final FloatIsTrig $this) {
      return $this.pi$mcF$sp();
   }

   default float pi$mcF$sp() {
      return (float)Math.PI;
   }

   // $FF: synthetic method
   static float exp$mcF$sp$(final FloatIsTrig $this, final float a) {
      return $this.exp$mcF$sp(a);
   }

   default float exp$mcF$sp(final float a) {
      return (float)Math.exp((double)a);
   }

   // $FF: synthetic method
   static float expm1$mcF$sp$(final FloatIsTrig $this, final float a) {
      return $this.expm1$mcF$sp(a);
   }

   default float expm1$mcF$sp(final float a) {
      return (float)Math.expm1((double)a);
   }

   // $FF: synthetic method
   static float log$mcF$sp$(final FloatIsTrig $this, final float a) {
      return $this.log$mcF$sp(a);
   }

   default float log$mcF$sp(final float a) {
      return (float)Math.log((double)a);
   }

   // $FF: synthetic method
   static float log1p$mcF$sp$(final FloatIsTrig $this, final float a) {
      return $this.log1p$mcF$sp(a);
   }

   default float log1p$mcF$sp(final float a) {
      return (float)Math.log1p((double)a);
   }

   // $FF: synthetic method
   static float sin$mcF$sp$(final FloatIsTrig $this, final float a) {
      return $this.sin$mcF$sp(a);
   }

   default float sin$mcF$sp(final float a) {
      return (float)Math.sin((double)a);
   }

   // $FF: synthetic method
   static float cos$mcF$sp$(final FloatIsTrig $this, final float a) {
      return $this.cos$mcF$sp(a);
   }

   default float cos$mcF$sp(final float a) {
      return (float)Math.cos((double)a);
   }

   // $FF: synthetic method
   static float tan$mcF$sp$(final FloatIsTrig $this, final float a) {
      return $this.tan$mcF$sp(a);
   }

   default float tan$mcF$sp(final float a) {
      return (float)Math.tan((double)a);
   }

   // $FF: synthetic method
   static float asin$mcF$sp$(final FloatIsTrig $this, final float a) {
      return $this.asin$mcF$sp(a);
   }

   default float asin$mcF$sp(final float a) {
      return (float)Math.asin((double)a);
   }

   // $FF: synthetic method
   static float acos$mcF$sp$(final FloatIsTrig $this, final float a) {
      return $this.acos$mcF$sp(a);
   }

   default float acos$mcF$sp(final float a) {
      return (float)Math.acos((double)a);
   }

   // $FF: synthetic method
   static float atan$mcF$sp$(final FloatIsTrig $this, final float a) {
      return $this.atan$mcF$sp(a);
   }

   default float atan$mcF$sp(final float a) {
      return (float)Math.atan((double)a);
   }

   // $FF: synthetic method
   static float atan2$mcF$sp$(final FloatIsTrig $this, final float y, final float x) {
      return $this.atan2$mcF$sp(y, x);
   }

   default float atan2$mcF$sp(final float y, final float x) {
      return (float)Math.atan2((double)y, (double)x);
   }

   // $FF: synthetic method
   static float sinh$mcF$sp$(final FloatIsTrig $this, final float x) {
      return $this.sinh$mcF$sp(x);
   }

   default float sinh$mcF$sp(final float x) {
      return (float)Math.sinh((double)x);
   }

   // $FF: synthetic method
   static float cosh$mcF$sp$(final FloatIsTrig $this, final float x) {
      return $this.cosh$mcF$sp(x);
   }

   default float cosh$mcF$sp(final float x) {
      return (float)Math.cosh((double)x);
   }

   // $FF: synthetic method
   static float tanh$mcF$sp$(final FloatIsTrig $this, final float x) {
      return $this.tanh$mcF$sp(x);
   }

   default float tanh$mcF$sp(final float x) {
      return (float)Math.tanh((double)x);
   }

   // $FF: synthetic method
   static float toRadians$mcF$sp$(final FloatIsTrig $this, final float a) {
      return $this.toRadians$mcF$sp(a);
   }

   default float toRadians$mcF$sp(final float a) {
      return a * (float)2 * this.pi$mcF$sp() / (float)360;
   }

   // $FF: synthetic method
   static float toDegrees$mcF$sp$(final FloatIsTrig $this, final float a) {
      return $this.toDegrees$mcF$sp(a);
   }

   default float toDegrees$mcF$sp(final float a) {
      return a * (float)360 / ((float)2 * this.pi$mcF$sp());
   }

   static void $init$(final FloatIsTrig $this) {
   }
}
