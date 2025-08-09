package spire.math;

import algebra.ring.Field;
import algebra.ring.Signed;
import cats.kernel.Order;
import scala.reflect.ScalaSignature;
import spire.algebra.NRoot;
import spire.algebra.Trig;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Uc\u0001C\r\u001b!\u0003\r\tA\u0007\u0010\t\u000b=\u0003A\u0011\u0001)\t\u000bQ\u0003a1A+\t\u000b\u0005\u0004a1\u00012\t\u000b\u0019\u0004a1A4\t\u000b-\u0004a1\u00017\t\u000b9\u0004a1A8\t\u000bM\u0004A\u0011\u0001;\t\u000bU\u0004A\u0011\u0001;\t\u000bY\u0004A\u0011A<\t\u000bi\u0004A\u0011A>\t\u000bu\u0004A\u0011\u0001@\t\u000f\u0005\u0005\u0001\u0001\"\u0001\u0002\u0004!9\u0011q\u0001\u0001\u0005\u0002\u0005%\u0001bBA\u0007\u0001\u0011\u0005\u0011q\u0002\u0005\b\u0003'\u0001A\u0011AA\u000b\u0011\u001d\tI\u0002\u0001C\u0001\u00037Aq!a\b\u0001\t\u0003\t\t\u0003C\u0004\u0002&\u0001!\t!a\n\t\u000f\u0005-\u0002\u0001\"\u0001\u0002.!9\u0011q\u0007\u0001\u0005\u0002\u0005e\u0002bBA\u001f\u0001\u0011\u0005\u0011q\b\u0005\b\u0003\u0007\u0002A\u0011AA#\u0011\u001d\tI\u0005\u0001C\u0001\u0003\u0017Bq!a\u0014\u0001\t\u0003\t\tFA\u0007D_6\u0004H.\u001a=P]R\u0013\u0018n\u001a\u0006\u00037q\tA!\\1uQ*\tQ$A\u0003ta&\u0014X-\u0006\u0002 eM\u0019\u0001\u0001\t\u0014\u0011\u0005\u0005\"S\"\u0001\u0012\u000b\u0003\r\nQa]2bY\u0006L!!\n\u0012\u0003\r\u0005s\u0017PU3g!\r9#\u0006L\u0007\u0002Q)\u0011\u0011\u0006H\u0001\bC2<WM\u0019:b\u0013\tY\u0003F\u0001\u0003Ue&<\u0007cA\u0017/a5\t!$\u0003\u000205\t91i\\7qY\u0016D\bCA\u00193\u0019\u0001!\u0011b\r\u0001!\u0002\u0003\u0005)\u0019A\u001b\u0003\u0003\u0005\u001b\u0001!\u0005\u00027sA\u0011\u0011eN\u0005\u0003q\t\u0012qAT8uQ&tw\r\u0005\u0002\"u%\u00111H\t\u0002\u0004\u0003:L\b\u0006\u0002\u001a>\u0001*\u0003\"!\t \n\u0005}\u0012#aC:qK\u000eL\u0017\r\\5{K\u0012\fTaI!C\t\u000es!!\t\"\n\u0005\r\u0013\u0013!\u0002$m_\u0006$\u0018\u0007\u0002\u0013F\u0013\u000er!AR%\u000e\u0003\u001dS!\u0001\u0013\u001b\u0002\rq\u0012xn\u001c;?\u0013\u0005\u0019\u0013'B\u0012L\u0019:keBA\u0011M\u0013\ti%%\u0001\u0004E_V\u0014G.Z\u0019\u0005I\u0015K5%\u0001\u0004%S:LG\u000f\n\u000b\u0002#B\u0011\u0011EU\u0005\u0003'\n\u0012A!\u00168ji\u000611oY1mCJ,\u0012A\u0016\t\u0004/z\u0003dB\u0001-]\u001d\tI6L\u0004\u0002G5&\tQ$\u0003\u0002*9%\u0011Q\fK\u0001\ba\u0006\u001c7.Y4f\u0013\ty\u0006MA\u0003GS\u0016dGM\u0003\u0002^Q\u0005)aN]8piV\t1\rE\u0002(IBJ!!\u001a\u0015\u0003\u000b9\u0013vn\u001c;\u0002\u000b=\u0014H-\u001a:\u0016\u0003!\u00042aV51\u0013\tQ\u0007MA\u0003Pe\u0012,'/\u0001\u0003ue&<W#A7\u0011\u0007\u001dR\u0003'\u0001\u0004tS\u001etW\rZ\u000b\u0002aB\u0019q+\u001d\u0019\n\u0005I\u0004'AB*jO:,G-A\u0001f+\u0005a\u0013A\u00019j\u0003\r)\u0007\u0010\u001d\u000b\u0003YaDQ!_\u0005A\u00021\n\u0011!Y\u0001\u0006Kb\u0004X.\r\u000b\u0003YqDQ!\u001f\u0006A\u00021\n1\u0001\\8h)\tas\u0010C\u0003z\u0017\u0001\u0007A&A\u0003m_\u001e\f\u0004\u000fF\u0002-\u0003\u000bAQ!\u001f\u0007A\u00021\n1a]5o)\ra\u00131\u0002\u0005\u0006s6\u0001\r\u0001L\u0001\u0004G>\u001cHc\u0001\u0017\u0002\u0012!)\u0011P\u0004a\u0001Y\u0005\u0019A/\u00198\u0015\u00071\n9\u0002C\u0003z\u001f\u0001\u0007A&\u0001\u0003bg&tGc\u0001\u0017\u0002\u001e!)\u0011\u0010\u0005a\u0001Y\u0005!\u0011mY8t)\ra\u00131\u0005\u0005\u0006sF\u0001\r\u0001L\u0001\u0005CR\fg\u000eF\u0002-\u0003SAQ!\u001f\nA\u00021\nQ!\u0019;b]J\"R\u0001LA\u0018\u0003gAa!!\r\u0014\u0001\u0004a\u0013!A=\t\r\u0005U2\u00031\u0001-\u0003\u0005A\u0018\u0001B:j]\"$2\u0001LA\u001e\u0011\u0019\t)\u0004\u0006a\u0001Y\u0005!1m\\:i)\ra\u0013\u0011\t\u0005\u0007\u0003k)\u0002\u0019\u0001\u0017\u0002\tQ\fg\u000e\u001b\u000b\u0004Y\u0005\u001d\u0003BBA\u001b-\u0001\u0007A&A\u0005u_J\u000bG-[1ogR\u0019A&!\u0014\t\u000be<\u0002\u0019\u0001\u0017\u0002\u0013Q|G)Z4sK\u0016\u001cHc\u0001\u0017\u0002T!)\u0011\u0010\u0007a\u0001Y\u0001"
)
public interface ComplexOnTrig extends Trig {
   Field scalar();

   NRoot nroot();

   Order order();

   Trig trig();

   Signed signed();

   // $FF: synthetic method
   static Complex e$(final ComplexOnTrig $this) {
      return $this.e();
   }

   default Complex e() {
      return new Complex(this.trig().e(), this.scalar().zero());
   }

   // $FF: synthetic method
   static Complex pi$(final ComplexOnTrig $this) {
      return $this.pi();
   }

   default Complex pi() {
      return new Complex(this.trig().pi(), this.scalar().zero());
   }

   // $FF: synthetic method
   static Complex exp$(final ComplexOnTrig $this, final Complex a) {
      return $this.exp(a);
   }

   default Complex exp(final Complex a) {
      return a.exp(this.scalar(), this.trig());
   }

   // $FF: synthetic method
   static Complex expm1$(final ComplexOnTrig $this, final Complex a) {
      return $this.expm1(a);
   }

   default Complex expm1(final Complex a) {
      return a.exp(this.scalar(), this.trig()).$minus((Object)this.scalar().one(), this.scalar());
   }

   // $FF: synthetic method
   static Complex log$(final ComplexOnTrig $this, final Complex a) {
      return $this.log(a);
   }

   default Complex log(final Complex a) {
      return a.log(this.scalar(), this.nroot(), this.order(), this.trig(), this.signed());
   }

   // $FF: synthetic method
   static Complex log1p$(final ComplexOnTrig $this, final Complex a) {
      return $this.log1p(a);
   }

   default Complex log1p(final Complex a) {
      return a.$plus((Object)this.scalar().one(), this.scalar()).log(this.scalar(), this.nroot(), this.order(), this.trig(), this.signed());
   }

   // $FF: synthetic method
   static Complex sin$(final ComplexOnTrig $this, final Complex a) {
      return $this.sin(a);
   }

   default Complex sin(final Complex a) {
      return a.sin(this.scalar(), this.trig());
   }

   // $FF: synthetic method
   static Complex cos$(final ComplexOnTrig $this, final Complex a) {
      return $this.cos(a);
   }

   default Complex cos(final Complex a) {
      return a.cos(this.scalar(), this.trig());
   }

   // $FF: synthetic method
   static Complex tan$(final ComplexOnTrig $this, final Complex a) {
      return $this.tan(a);
   }

   default Complex tan(final Complex a) {
      return a.tan(this.scalar(), this.trig());
   }

   // $FF: synthetic method
   static Complex asin$(final ComplexOnTrig $this, final Complex a) {
      return $this.asin(a);
   }

   default Complex asin(final Complex a) {
      return a.asin(this.scalar(), this.nroot(), this.order(), this.trig(), this.signed());
   }

   // $FF: synthetic method
   static Complex acos$(final ComplexOnTrig $this, final Complex a) {
      return $this.acos(a);
   }

   default Complex acos(final Complex a) {
      return a.acos(this.scalar(), this.nroot(), this.order(), this.trig(), this.signed());
   }

   // $FF: synthetic method
   static Complex atan$(final ComplexOnTrig $this, final Complex a) {
      return $this.atan(a);
   }

   default Complex atan(final Complex a) {
      return a.atan(this.scalar(), this.order(), this.nroot(), this.signed(), this.trig());
   }

   // $FF: synthetic method
   static Complex atan2$(final ComplexOnTrig $this, final Complex y, final Complex x) {
      return $this.atan2(y, x);
   }

   default Complex atan2(final Complex y, final Complex x) {
      return (new Complex(x.real(), y.imag())).atan(this.scalar(), this.order(), this.nroot(), this.signed(), this.trig());
   }

   // $FF: synthetic method
   static Complex sinh$(final ComplexOnTrig $this, final Complex x) {
      return $this.sinh(x);
   }

   default Complex sinh(final Complex x) {
      return x.sinh(this.scalar(), this.trig());
   }

   // $FF: synthetic method
   static Complex cosh$(final ComplexOnTrig $this, final Complex x) {
      return $this.cosh(x);
   }

   default Complex cosh(final Complex x) {
      return x.cosh(this.scalar(), this.trig());
   }

   // $FF: synthetic method
   static Complex tanh$(final ComplexOnTrig $this, final Complex x) {
      return $this.tanh(x);
   }

   default Complex tanh(final Complex x) {
      return x.tanh(this.scalar(), this.trig());
   }

   // $FF: synthetic method
   static Complex toRadians$(final ComplexOnTrig $this, final Complex a) {
      return $this.toRadians(a);
   }

   default Complex toRadians(final Complex a) {
      return a;
   }

   // $FF: synthetic method
   static Complex toDegrees$(final ComplexOnTrig $this, final Complex a) {
      return $this.toDegrees(a);
   }

   default Complex toDegrees(final Complex a) {
      return a;
   }

   // $FF: synthetic method
   static Field scalar$mcD$sp$(final ComplexOnTrig $this) {
      return $this.scalar$mcD$sp();
   }

   default Field scalar$mcD$sp() {
      return this.scalar();
   }

   // $FF: synthetic method
   static Field scalar$mcF$sp$(final ComplexOnTrig $this) {
      return $this.scalar$mcF$sp();
   }

   default Field scalar$mcF$sp() {
      return this.scalar();
   }

   // $FF: synthetic method
   static NRoot nroot$mcD$sp$(final ComplexOnTrig $this) {
      return $this.nroot$mcD$sp();
   }

   default NRoot nroot$mcD$sp() {
      return this.nroot();
   }

   // $FF: synthetic method
   static NRoot nroot$mcF$sp$(final ComplexOnTrig $this) {
      return $this.nroot$mcF$sp();
   }

   default NRoot nroot$mcF$sp() {
      return this.nroot();
   }

   // $FF: synthetic method
   static Order order$mcD$sp$(final ComplexOnTrig $this) {
      return $this.order$mcD$sp();
   }

   default Order order$mcD$sp() {
      return this.order();
   }

   // $FF: synthetic method
   static Order order$mcF$sp$(final ComplexOnTrig $this) {
      return $this.order$mcF$sp();
   }

   default Order order$mcF$sp() {
      return this.order();
   }

   // $FF: synthetic method
   static Trig trig$mcD$sp$(final ComplexOnTrig $this) {
      return $this.trig$mcD$sp();
   }

   default Trig trig$mcD$sp() {
      return this.trig();
   }

   // $FF: synthetic method
   static Trig trig$mcF$sp$(final ComplexOnTrig $this) {
      return $this.trig$mcF$sp();
   }

   default Trig trig$mcF$sp() {
      return this.trig();
   }

   // $FF: synthetic method
   static Signed signed$mcD$sp$(final ComplexOnTrig $this) {
      return $this.signed$mcD$sp();
   }

   default Signed signed$mcD$sp() {
      return this.signed();
   }

   // $FF: synthetic method
   static Signed signed$mcF$sp$(final ComplexOnTrig $this) {
      return $this.signed$mcF$sp();
   }

   default Signed signed$mcF$sp() {
      return this.signed();
   }

   // $FF: synthetic method
   static Complex e$mcD$sp$(final ComplexOnTrig $this) {
      return $this.e$mcD$sp();
   }

   default Complex e$mcD$sp() {
      return this.e();
   }

   // $FF: synthetic method
   static Complex e$mcF$sp$(final ComplexOnTrig $this) {
      return $this.e$mcF$sp();
   }

   default Complex e$mcF$sp() {
      return this.e();
   }

   // $FF: synthetic method
   static Complex pi$mcD$sp$(final ComplexOnTrig $this) {
      return $this.pi$mcD$sp();
   }

   default Complex pi$mcD$sp() {
      return this.pi();
   }

   // $FF: synthetic method
   static Complex pi$mcF$sp$(final ComplexOnTrig $this) {
      return $this.pi$mcF$sp();
   }

   default Complex pi$mcF$sp() {
      return this.pi();
   }

   // $FF: synthetic method
   static Complex exp$mcD$sp$(final ComplexOnTrig $this, final Complex a) {
      return $this.exp$mcD$sp(a);
   }

   default Complex exp$mcD$sp(final Complex a) {
      return this.exp(a);
   }

   // $FF: synthetic method
   static Complex exp$mcF$sp$(final ComplexOnTrig $this, final Complex a) {
      return $this.exp$mcF$sp(a);
   }

   default Complex exp$mcF$sp(final Complex a) {
      return this.exp(a);
   }

   // $FF: synthetic method
   static Complex expm1$mcD$sp$(final ComplexOnTrig $this, final Complex a) {
      return $this.expm1$mcD$sp(a);
   }

   default Complex expm1$mcD$sp(final Complex a) {
      return this.expm1(a);
   }

   // $FF: synthetic method
   static Complex expm1$mcF$sp$(final ComplexOnTrig $this, final Complex a) {
      return $this.expm1$mcF$sp(a);
   }

   default Complex expm1$mcF$sp(final Complex a) {
      return this.expm1(a);
   }

   // $FF: synthetic method
   static Complex log$mcD$sp$(final ComplexOnTrig $this, final Complex a) {
      return $this.log$mcD$sp(a);
   }

   default Complex log$mcD$sp(final Complex a) {
      return this.log(a);
   }

   // $FF: synthetic method
   static Complex log$mcF$sp$(final ComplexOnTrig $this, final Complex a) {
      return $this.log$mcF$sp(a);
   }

   default Complex log$mcF$sp(final Complex a) {
      return this.log(a);
   }

   // $FF: synthetic method
   static Complex log1p$mcD$sp$(final ComplexOnTrig $this, final Complex a) {
      return $this.log1p$mcD$sp(a);
   }

   default Complex log1p$mcD$sp(final Complex a) {
      return this.log1p(a);
   }

   // $FF: synthetic method
   static Complex log1p$mcF$sp$(final ComplexOnTrig $this, final Complex a) {
      return $this.log1p$mcF$sp(a);
   }

   default Complex log1p$mcF$sp(final Complex a) {
      return this.log1p(a);
   }

   // $FF: synthetic method
   static Complex sin$mcD$sp$(final ComplexOnTrig $this, final Complex a) {
      return $this.sin$mcD$sp(a);
   }

   default Complex sin$mcD$sp(final Complex a) {
      return this.sin(a);
   }

   // $FF: synthetic method
   static Complex sin$mcF$sp$(final ComplexOnTrig $this, final Complex a) {
      return $this.sin$mcF$sp(a);
   }

   default Complex sin$mcF$sp(final Complex a) {
      return this.sin(a);
   }

   // $FF: synthetic method
   static Complex cos$mcD$sp$(final ComplexOnTrig $this, final Complex a) {
      return $this.cos$mcD$sp(a);
   }

   default Complex cos$mcD$sp(final Complex a) {
      return this.cos(a);
   }

   // $FF: synthetic method
   static Complex cos$mcF$sp$(final ComplexOnTrig $this, final Complex a) {
      return $this.cos$mcF$sp(a);
   }

   default Complex cos$mcF$sp(final Complex a) {
      return this.cos(a);
   }

   // $FF: synthetic method
   static Complex tan$mcD$sp$(final ComplexOnTrig $this, final Complex a) {
      return $this.tan$mcD$sp(a);
   }

   default Complex tan$mcD$sp(final Complex a) {
      return this.tan(a);
   }

   // $FF: synthetic method
   static Complex tan$mcF$sp$(final ComplexOnTrig $this, final Complex a) {
      return $this.tan$mcF$sp(a);
   }

   default Complex tan$mcF$sp(final Complex a) {
      return this.tan(a);
   }

   // $FF: synthetic method
   static Complex asin$mcD$sp$(final ComplexOnTrig $this, final Complex a) {
      return $this.asin$mcD$sp(a);
   }

   default Complex asin$mcD$sp(final Complex a) {
      return this.asin(a);
   }

   // $FF: synthetic method
   static Complex asin$mcF$sp$(final ComplexOnTrig $this, final Complex a) {
      return $this.asin$mcF$sp(a);
   }

   default Complex asin$mcF$sp(final Complex a) {
      return this.asin(a);
   }

   // $FF: synthetic method
   static Complex acos$mcD$sp$(final ComplexOnTrig $this, final Complex a) {
      return $this.acos$mcD$sp(a);
   }

   default Complex acos$mcD$sp(final Complex a) {
      return this.acos(a);
   }

   // $FF: synthetic method
   static Complex acos$mcF$sp$(final ComplexOnTrig $this, final Complex a) {
      return $this.acos$mcF$sp(a);
   }

   default Complex acos$mcF$sp(final Complex a) {
      return this.acos(a);
   }

   // $FF: synthetic method
   static Complex atan$mcD$sp$(final ComplexOnTrig $this, final Complex a) {
      return $this.atan$mcD$sp(a);
   }

   default Complex atan$mcD$sp(final Complex a) {
      return this.atan(a);
   }

   // $FF: synthetic method
   static Complex atan$mcF$sp$(final ComplexOnTrig $this, final Complex a) {
      return $this.atan$mcF$sp(a);
   }

   default Complex atan$mcF$sp(final Complex a) {
      return this.atan(a);
   }

   // $FF: synthetic method
   static Complex atan2$mcD$sp$(final ComplexOnTrig $this, final Complex y, final Complex x) {
      return $this.atan2$mcD$sp(y, x);
   }

   default Complex atan2$mcD$sp(final Complex y, final Complex x) {
      return this.atan2(y, x);
   }

   // $FF: synthetic method
   static Complex atan2$mcF$sp$(final ComplexOnTrig $this, final Complex y, final Complex x) {
      return $this.atan2$mcF$sp(y, x);
   }

   default Complex atan2$mcF$sp(final Complex y, final Complex x) {
      return this.atan2(y, x);
   }

   // $FF: synthetic method
   static Complex sinh$mcD$sp$(final ComplexOnTrig $this, final Complex x) {
      return $this.sinh$mcD$sp(x);
   }

   default Complex sinh$mcD$sp(final Complex x) {
      return this.sinh(x);
   }

   // $FF: synthetic method
   static Complex sinh$mcF$sp$(final ComplexOnTrig $this, final Complex x) {
      return $this.sinh$mcF$sp(x);
   }

   default Complex sinh$mcF$sp(final Complex x) {
      return this.sinh(x);
   }

   // $FF: synthetic method
   static Complex cosh$mcD$sp$(final ComplexOnTrig $this, final Complex x) {
      return $this.cosh$mcD$sp(x);
   }

   default Complex cosh$mcD$sp(final Complex x) {
      return this.cosh(x);
   }

   // $FF: synthetic method
   static Complex cosh$mcF$sp$(final ComplexOnTrig $this, final Complex x) {
      return $this.cosh$mcF$sp(x);
   }

   default Complex cosh$mcF$sp(final Complex x) {
      return this.cosh(x);
   }

   // $FF: synthetic method
   static Complex tanh$mcD$sp$(final ComplexOnTrig $this, final Complex x) {
      return $this.tanh$mcD$sp(x);
   }

   default Complex tanh$mcD$sp(final Complex x) {
      return this.tanh(x);
   }

   // $FF: synthetic method
   static Complex tanh$mcF$sp$(final ComplexOnTrig $this, final Complex x) {
      return $this.tanh$mcF$sp(x);
   }

   default Complex tanh$mcF$sp(final Complex x) {
      return this.tanh(x);
   }

   // $FF: synthetic method
   static Complex toRadians$mcD$sp$(final ComplexOnTrig $this, final Complex a) {
      return $this.toRadians$mcD$sp(a);
   }

   default Complex toRadians$mcD$sp(final Complex a) {
      return this.toRadians(a);
   }

   // $FF: synthetic method
   static Complex toRadians$mcF$sp$(final ComplexOnTrig $this, final Complex a) {
      return $this.toRadians$mcF$sp(a);
   }

   default Complex toRadians$mcF$sp(final Complex a) {
      return this.toRadians(a);
   }

   // $FF: synthetic method
   static Complex toDegrees$mcD$sp$(final ComplexOnTrig $this, final Complex a) {
      return $this.toDegrees$mcD$sp(a);
   }

   default Complex toDegrees$mcD$sp(final Complex a) {
      return this.toDegrees(a);
   }

   // $FF: synthetic method
   static Complex toDegrees$mcF$sp$(final ComplexOnTrig $this, final Complex a) {
      return $this.toDegrees$mcF$sp(a);
   }

   default Complex toDegrees$mcF$sp(final Complex a) {
      return this.toDegrees(a);
   }

   static void $init$(final ComplexOnTrig $this) {
   }
}
