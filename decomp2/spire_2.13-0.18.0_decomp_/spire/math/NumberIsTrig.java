package spire.math;

import scala.reflect.ScalaSignature;
import spire.algebra.Trig;

@ScalaSignature(
   bytes = "\u0006\u0005\u001d4\u0001\u0002F\u000b\u0011\u0002\u0007\u0005Q#\u0007\u0005\u0006U\u0001!\t\u0001\f\u0005\u0006a\u0001!\t!\r\u0005\u0006e\u0001!\t!\r\u0005\u0006g\u0001!\t\u0001\u000e\u0005\u0006o\u0001!\t\u0001\u000f\u0005\u0006u\u0001!\ta\u000f\u0005\u0006{\u0001!\tA\u0010\u0005\u0006\u0001\u0002!\t!\u0011\u0005\u0006\u0007\u0002!\t\u0001\u0012\u0005\u0006\r\u0002!\ta\u0012\u0005\u0006\u0013\u0002!\tA\u0013\u0005\u0006\u0019\u0002!\t!\u0014\u0005\u0006\u001f\u0002!\t\u0001\u0015\u0005\u0006%\u0002!\ta\u0015\u0005\u00061\u0002!\t!\u0017\u0005\u00067\u0002!\t\u0001\u0018\u0005\u0006=\u0002!\ta\u0018\u0005\u0006C\u0002!\tA\u0019\u0005\u0006I\u0002!\t!\u001a\u0002\r\u001dVl'-\u001a:JgR\u0013\u0018n\u001a\u0006\u0003-]\tA!\\1uQ*\t\u0001$A\u0003ta&\u0014XmE\u0002\u00015\u0001\u0002\"a\u0007\u0010\u000e\u0003qQ\u0011!H\u0001\u0006g\u000e\fG.Y\u0005\u0003?q\u0011a!\u00118z%\u00164\u0007cA\u0011%M5\t!E\u0003\u0002$/\u00059\u0011\r\\4fEJ\f\u0017BA\u0013#\u0005\u0011!&/[4\u0011\u0005\u001dBS\"A\u000b\n\u0005%*\"A\u0002(v[\n,'/\u0001\u0004%S:LG\u000fJ\u0002\u0001)\u0005i\u0003CA\u000e/\u0013\tyCD\u0001\u0003V]&$\u0018!A3\u0016\u0003\u0019\n!\u0001]5\u0002\u0007\u0015D\b\u000f\u0006\u0002'k!)a\u0007\u0002a\u0001M\u0005\t\u0011-A\u0003fqBl\u0017\u0007\u0006\u0002's!)a'\u0002a\u0001M\u0005\u0019An\\4\u0015\u0005\u0019b\u0004\"\u0002\u001c\u0007\u0001\u00041\u0013!\u00027pOF\u0002HC\u0001\u0014@\u0011\u00151t\u00011\u0001'\u0003\r\u0019\u0018N\u001c\u000b\u0003M\tCQA\u000e\u0005A\u0002\u0019\n1aY8t)\t1S\tC\u00037\u0013\u0001\u0007a%A\u0002uC:$\"A\n%\t\u000bYR\u0001\u0019\u0001\u0014\u0002\t\u0005\u001c\u0018N\u001c\u000b\u0003M-CQAN\u0006A\u0002\u0019\nA!Y2pgR\u0011aE\u0014\u0005\u0006m1\u0001\rAJ\u0001\u0005CR\fg\u000e\u0006\u0002'#\")a'\u0004a\u0001M\u0005)\u0011\r^1oeQ\u0019a\u0005\u0016,\t\u000bUs\u0001\u0019\u0001\u0014\u0002\u0003eDQa\u0016\bA\u0002\u0019\n\u0011\u0001_\u0001\u0005g&t\u0007\u000e\u0006\u0002'5\")qk\u0004a\u0001M\u0005!1m\\:i)\t1S\fC\u0003X!\u0001\u0007a%\u0001\u0003uC:DGC\u0001\u0014a\u0011\u00159\u0016\u00031\u0001'\u0003%!xNU1eS\u0006t7\u000f\u0006\u0002'G\")aG\u0005a\u0001M\u0005IAo\u001c#fOJ,Wm\u001d\u000b\u0003M\u0019DQAN\nA\u0002\u0019\u0002"
)
public interface NumberIsTrig extends Trig {
   // $FF: synthetic method
   static Number e$(final NumberIsTrig $this) {
      return $this.e();
   }

   default Number e() {
      return Number$.MODULE$.apply(Math.E);
   }

   // $FF: synthetic method
   static Number pi$(final NumberIsTrig $this) {
      return $this.pi();
   }

   default Number pi() {
      return Number$.MODULE$.apply(Math.PI);
   }

   // $FF: synthetic method
   static Number exp$(final NumberIsTrig $this, final Number a) {
      return $this.exp(a);
   }

   default Number exp(final Number a) {
      return Number$.MODULE$.apply(Math.exp(a.toDouble()));
   }

   // $FF: synthetic method
   static Number expm1$(final NumberIsTrig $this, final Number a) {
      return $this.expm1(a);
   }

   default Number expm1(final Number a) {
      return Number$.MODULE$.apply(Math.expm1(a.toDouble()));
   }

   // $FF: synthetic method
   static Number log$(final NumberIsTrig $this, final Number a) {
      return $this.log(a);
   }

   default Number log(final Number a) {
      return Number$.MODULE$.apply(Math.log(a.toDouble()));
   }

   // $FF: synthetic method
   static Number log1p$(final NumberIsTrig $this, final Number a) {
      return $this.log1p(a);
   }

   default Number log1p(final Number a) {
      return Number$.MODULE$.apply(Math.log1p(a.toDouble()));
   }

   // $FF: synthetic method
   static Number sin$(final NumberIsTrig $this, final Number a) {
      return $this.sin(a);
   }

   default Number sin(final Number a) {
      return Number$.MODULE$.apply(Math.sin(a.toDouble()));
   }

   // $FF: synthetic method
   static Number cos$(final NumberIsTrig $this, final Number a) {
      return $this.cos(a);
   }

   default Number cos(final Number a) {
      return Number$.MODULE$.apply(Math.cos(a.toDouble()));
   }

   // $FF: synthetic method
   static Number tan$(final NumberIsTrig $this, final Number a) {
      return $this.tan(a);
   }

   default Number tan(final Number a) {
      return Number$.MODULE$.apply(Math.tan(a.toDouble()));
   }

   // $FF: synthetic method
   static Number asin$(final NumberIsTrig $this, final Number a) {
      return $this.asin(a);
   }

   default Number asin(final Number a) {
      return Number$.MODULE$.apply(Math.asin(a.toDouble()));
   }

   // $FF: synthetic method
   static Number acos$(final NumberIsTrig $this, final Number a) {
      return $this.acos(a);
   }

   default Number acos(final Number a) {
      return Number$.MODULE$.apply(Math.acos(a.toDouble()));
   }

   // $FF: synthetic method
   static Number atan$(final NumberIsTrig $this, final Number a) {
      return $this.atan(a);
   }

   default Number atan(final Number a) {
      return Number$.MODULE$.apply(Math.atan(a.toDouble()));
   }

   // $FF: synthetic method
   static Number atan2$(final NumberIsTrig $this, final Number y, final Number x) {
      return $this.atan2(y, x);
   }

   default Number atan2(final Number y, final Number x) {
      return Number$.MODULE$.apply(Math.atan2(y.toDouble(), x.toDouble()));
   }

   // $FF: synthetic method
   static Number sinh$(final NumberIsTrig $this, final Number x) {
      return $this.sinh(x);
   }

   default Number sinh(final Number x) {
      return Number$.MODULE$.apply(Math.sinh(x.toDouble()));
   }

   // $FF: synthetic method
   static Number cosh$(final NumberIsTrig $this, final Number x) {
      return $this.cosh(x);
   }

   default Number cosh(final Number x) {
      return Number$.MODULE$.apply(Math.cosh(x.toDouble()));
   }

   // $FF: synthetic method
   static Number tanh$(final NumberIsTrig $this, final Number x) {
      return $this.tanh(x);
   }

   default Number tanh(final Number x) {
      return Number$.MODULE$.apply(Math.tanh(x.toDouble()));
   }

   // $FF: synthetic method
   static Number toRadians$(final NumberIsTrig $this, final Number a) {
      return $this.toRadians(a);
   }

   default Number toRadians(final Number a) {
      return a.$times(Number$.MODULE$.apply(2)).$times(this.pi()).$div(Number$.MODULE$.apply(360));
   }

   // $FF: synthetic method
   static Number toDegrees$(final NumberIsTrig $this, final Number a) {
      return $this.toDegrees(a);
   }

   default Number toDegrees(final Number a) {
      return a.$times(Number$.MODULE$.apply(360)).$div(Number$.MODULE$.apply(2).$times(this.pi()));
   }

   static void $init$(final NumberIsTrig $this) {
   }
}
