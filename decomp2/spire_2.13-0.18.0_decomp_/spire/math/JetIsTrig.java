package spire.math;

import algebra.ring.Field;
import algebra.ring.Signed;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import spire.algebra.NRoot;
import spire.algebra.Trig;
import spire.algebra.VectorSpace;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ed\u0001C\u000e\u001d!\u0003\r\t\u0001\b\u0011\t\u000bE\u0003A\u0011\u0001*\t\u000bY\u0003a1A,\t\u000by\u0003a1A0\t\u000b\r\u0004a1\u00013\t\u000bA\u0004a1A9\t\u000bU\u0004a1\u0001<\t\u000bi\u0004a1A>\t\u000bu\u0004a1\u0001@\t\u000f\u0005-\u0001\u0001\"\u0001\u0002\u000e!9\u0011q\u0002\u0001\u0005\u0002\u00055\u0001bBA\t\u0001\u0011\u0005\u00111\u0003\u0005\b\u00033\u0001A\u0011AA\u000e\u0011\u001d\ty\u0002\u0001C\u0001\u0003CAq!!\n\u0001\t\u0003\t9\u0003C\u0004\u0002,\u0001!\t!!\f\t\u000f\u0005E\u0002\u0001\"\u0001\u00024!9\u0011q\u0007\u0001\u0005\u0002\u0005e\u0002bBA\u001f\u0001\u0011\u0005\u0011q\b\u0005\b\u0003\u0007\u0002A\u0011AA#\u0011\u001d\tI\u0005\u0001C\u0001\u0003\u0017Bq!a\u0014\u0001\t\u0003\t\t\u0006C\u0004\u0002\\\u0001!\t!!\u0018\t\u000f\u0005\u0005\u0004\u0001\"\u0001\u0002d!9\u0011q\r\u0001\u0005\u0002\u0005%\u0004bBA7\u0001\u0011\u0005\u0011q\u000e\u0005\b\u0003g\u0002A\u0011AA;\u0005%QU\r^%t)JLwM\u0003\u0002\u001e=\u0005!Q.\u0019;i\u0015\u0005y\u0012!B:qSJ,WCA\u00115'\r\u0001!\u0005\u000b\t\u0003G\u0019j\u0011\u0001\n\u0006\u0002K\u0005)1oY1mC&\u0011q\u0005\n\u0002\u0007\u0003:L(+\u001a4\u0011\u0007%bc&D\u0001+\u0015\tYc$A\u0004bY\u001e,'M]1\n\u00055R#\u0001\u0002+sS\u001e\u00042a\f\u00193\u001b\u0005a\u0012BA\u0019\u001d\u0005\rQU\r\u001e\t\u0003gQb\u0001\u0001B\u00056\u0001\u0001\u0006\t\u0011!b\u0001o\t\tAk\u0001\u0001\u0012\u0005aZ\u0004CA\u0012:\u0013\tQDEA\u0004O_RD\u0017N\\4\u0011\u0005\rb\u0014BA\u001f%\u0005\r\te.\u001f\u0015\u0005i}\u0012E\n\u0005\u0002$\u0001&\u0011\u0011\t\n\u0002\fgB,7-[1mSj,G-M\u0003$\u0007\u00123UI\u0004\u0002$\t&\u0011Q\tJ\u0001\u0006\r2|\u0017\r^\u0019\u0005I\u001d[UE\u0004\u0002I\u00176\t\u0011J\u0003\u0002Km\u00051AH]8pizJ\u0011!J\u0019\u0006G5s\u0005k\u0014\b\u0003G9K!a\u0014\u0013\u0002\r\u0011{WO\u00197fc\u0011!siS\u0013\u0002\r\u0011Jg.\u001b;%)\u0005\u0019\u0006CA\u0012U\u0013\t)FE\u0001\u0003V]&$\u0018!A2\u0016\u0003a\u00032!\u0017/3\u001b\u0005Q&BA.%\u0003\u001d\u0011XM\u001a7fGRL!!\u0018.\u0003\u0011\rc\u0017m]:UC\u001e\f\u0011\u0001Z\u000b\u0002AB\u0011q&Y\u0005\u0003Er\u0011aAS3u\t&l\u0017!\u00014\u0016\u0003\u0015\u00042AZ73\u001d\t97N\u0004\u0002iU:\u0011\u0001*[\u0005\u0002?%\u00111FH\u0005\u0003Y*\nq\u0001]1dW\u0006<W-\u0003\u0002o_\n)a)[3mI*\u0011ANK\u0001\u0002]V\t!\u000fE\u0002*gJJ!\u0001\u001e\u0016\u0003\u000b9\u0013vn\u001c;\u0002\u0003M,\u0012a\u001e\t\u0004Mb\u0014\u0014BA=p\u0005\u0019\u0019\u0016n\u001a8fI\u0006\tA/F\u0001}!\rICFM\u0001\u0002mV\tq\u0010\u0005\u0004*\u0003\u0003\t)AM\u0005\u0004\u0003\u0007Q#a\u0003,fGR|'o\u00159bG\u0016\u0004BaIA\u0004e%\u0019\u0011\u0011\u0002\u0013\u0003\u000b\u0005\u0013(/Y=\u0002\u0003\u0015,\u0012AL\u0001\u0003a&\f1!\u001a=q)\rq\u0013Q\u0003\u0005\u0007\u0003/Y\u0001\u0019\u0001\u0018\u0002\u0003\u0005\fQ!\u001a=q[F\"2ALA\u000f\u0011\u0019\t9\u0002\u0004a\u0001]\u0005\u0019An\\4\u0015\u00079\n\u0019\u0003\u0003\u0004\u0002\u00185\u0001\rAL\u0001\u0006Y><\u0017\u0007\u001d\u000b\u0004]\u0005%\u0002BBA\f\u001d\u0001\u0007a&A\u0002tS:$2ALA\u0018\u0011\u0019\t9b\u0004a\u0001]\u0005\u00191m\\:\u0015\u00079\n)\u0004\u0003\u0004\u0002\u0018A\u0001\rAL\u0001\u0004i\u0006tGc\u0001\u0018\u0002<!1\u0011qC\tA\u00029\nA!Y:j]R\u0019a&!\u0011\t\r\u0005]!\u00031\u0001/\u0003\u0011\t7m\\:\u0015\u00079\n9\u0005\u0003\u0004\u0002\u0018M\u0001\rAL\u0001\u0005CR\fg\u000eF\u0002/\u0003\u001bBa!a\u0006\u0015\u0001\u0004q\u0013!B1uC:\u0014D#\u0002\u0018\u0002T\u0005]\u0003BBA++\u0001\u0007a&A\u0001z\u0011\u0019\tI&\u0006a\u0001]\u0005\t\u00010\u0001\u0003tS:DGc\u0001\u0018\u0002`!1\u0011\u0011\f\fA\u00029\nAaY8tQR\u0019a&!\u001a\t\r\u0005es\u00031\u0001/\u0003\u0011!\u0018M\u001c5\u0015\u00079\nY\u0007\u0003\u0004\u0002Za\u0001\rAL\u0001\ni>\u0014\u0016\rZ5b]N$2ALA9\u0011\u0019\t9\"\u0007a\u0001]\u0005IAo\u001c#fOJ,Wm\u001d\u000b\u0004]\u0005]\u0004BBA\f5\u0001\u0007a\u0006"
)
public interface JetIsTrig extends Trig {
   ClassTag c();

   JetDim d();

   Field f();

   NRoot n();

   Signed s();

   Trig t();

   VectorSpace v();

   // $FF: synthetic method
   static Jet e$(final JetIsTrig $this) {
      return $this.e();
   }

   default Jet e() {
      return Jet$.MODULE$.apply(this.t().e(), this.c(), this.d(), this.f());
   }

   // $FF: synthetic method
   static Jet pi$(final JetIsTrig $this) {
      return $this.pi();
   }

   default Jet pi() {
      return Jet$.MODULE$.apply(this.t().pi(), this.c(), this.d(), this.f());
   }

   // $FF: synthetic method
   static Jet exp$(final JetIsTrig $this, final Jet a) {
      return $this.exp(a);
   }

   default Jet exp(final Jet a) {
      return a.exp(this.t(), this.v());
   }

   // $FF: synthetic method
   static Jet expm1$(final JetIsTrig $this, final Jet a) {
      return $this.expm1(a);
   }

   default Jet expm1(final Jet a) {
      return a.exp(this.t(), this.v()).$minus(this.f().one(), this.f());
   }

   // $FF: synthetic method
   static Jet log$(final JetIsTrig $this, final Jet a) {
      return $this.log(a);
   }

   default Jet log(final Jet a) {
      return a.log(this.f(), this.t(), this.v());
   }

   // $FF: synthetic method
   static Jet log1p$(final JetIsTrig $this, final Jet a) {
      return $this.log1p(a);
   }

   default Jet log1p(final Jet a) {
      return a.$plus(this.f().one(), this.f()).log(this.f(), this.t(), this.v());
   }

   // $FF: synthetic method
   static Jet sin$(final JetIsTrig $this, final Jet a) {
      return $this.sin(a);
   }

   default Jet sin(final Jet a) {
      return a.sin(this.t(), this.v());
   }

   // $FF: synthetic method
   static Jet cos$(final JetIsTrig $this, final Jet a) {
      return $this.cos(a);
   }

   default Jet cos(final Jet a) {
      return a.cos(this.f(), this.t(), this.v());
   }

   // $FF: synthetic method
   static Jet tan$(final JetIsTrig $this, final Jet a) {
      return $this.tan(a);
   }

   default Jet tan(final Jet a) {
      return a.tan(this.f(), this.t(), this.v());
   }

   // $FF: synthetic method
   static Jet asin$(final JetIsTrig $this, final Jet a) {
      return $this.asin(a);
   }

   default Jet asin(final Jet a) {
      return a.asin(this.f(), this.n(), this.t(), this.v());
   }

   // $FF: synthetic method
   static Jet acos$(final JetIsTrig $this, final Jet a) {
      return $this.acos(a);
   }

   default Jet acos(final Jet a) {
      return a.acos(this.f(), this.n(), this.t(), this.v());
   }

   // $FF: synthetic method
   static Jet atan$(final JetIsTrig $this, final Jet a) {
      return $this.atan(a);
   }

   default Jet atan(final Jet a) {
      return a.atan(this.f(), this.t(), this.v());
   }

   // $FF: synthetic method
   static Jet atan2$(final JetIsTrig $this, final Jet y, final Jet x) {
      return $this.atan2(y, x);
   }

   default Jet atan2(final Jet y, final Jet x) {
      return y.atan2(x, this.f(), this.t(), this.v());
   }

   // $FF: synthetic method
   static Jet sinh$(final JetIsTrig $this, final Jet x) {
      return $this.sinh(x);
   }

   default Jet sinh(final Jet x) {
      return x.sinh(this.t(), this.v());
   }

   // $FF: synthetic method
   static Jet cosh$(final JetIsTrig $this, final Jet x) {
      return $this.cosh(x);
   }

   default Jet cosh(final Jet x) {
      return x.cosh(this.t(), this.v());
   }

   // $FF: synthetic method
   static Jet tanh$(final JetIsTrig $this, final Jet x) {
      return $this.tanh(x);
   }

   default Jet tanh(final Jet x) {
      return x.tanh(this.f(), this.t(), this.v());
   }

   // $FF: synthetic method
   static Jet toRadians$(final JetIsTrig $this, final Jet a) {
      return $this.toRadians(a);
   }

   default Jet toRadians(final Jet a) {
      return a;
   }

   // $FF: synthetic method
   static Jet toDegrees$(final JetIsTrig $this, final Jet a) {
      return $this.toDegrees(a);
   }

   default Jet toDegrees(final Jet a) {
      return a;
   }

   // $FF: synthetic method
   static Field f$mcD$sp$(final JetIsTrig $this) {
      return $this.f$mcD$sp();
   }

   default Field f$mcD$sp() {
      return this.f();
   }

   // $FF: synthetic method
   static Field f$mcF$sp$(final JetIsTrig $this) {
      return $this.f$mcF$sp();
   }

   default Field f$mcF$sp() {
      return this.f();
   }

   // $FF: synthetic method
   static NRoot n$mcD$sp$(final JetIsTrig $this) {
      return $this.n$mcD$sp();
   }

   default NRoot n$mcD$sp() {
      return this.n();
   }

   // $FF: synthetic method
   static NRoot n$mcF$sp$(final JetIsTrig $this) {
      return $this.n$mcF$sp();
   }

   default NRoot n$mcF$sp() {
      return this.n();
   }

   // $FF: synthetic method
   static Signed s$mcD$sp$(final JetIsTrig $this) {
      return $this.s$mcD$sp();
   }

   default Signed s$mcD$sp() {
      return this.s();
   }

   // $FF: synthetic method
   static Signed s$mcF$sp$(final JetIsTrig $this) {
      return $this.s$mcF$sp();
   }

   default Signed s$mcF$sp() {
      return this.s();
   }

   // $FF: synthetic method
   static Trig t$mcD$sp$(final JetIsTrig $this) {
      return $this.t$mcD$sp();
   }

   default Trig t$mcD$sp() {
      return this.t();
   }

   // $FF: synthetic method
   static Trig t$mcF$sp$(final JetIsTrig $this) {
      return $this.t$mcF$sp();
   }

   default Trig t$mcF$sp() {
      return this.t();
   }

   // $FF: synthetic method
   static VectorSpace v$mcD$sp$(final JetIsTrig $this) {
      return $this.v$mcD$sp();
   }

   default VectorSpace v$mcD$sp() {
      return this.v();
   }

   // $FF: synthetic method
   static VectorSpace v$mcF$sp$(final JetIsTrig $this) {
      return $this.v$mcF$sp();
   }

   default VectorSpace v$mcF$sp() {
      return this.v();
   }

   // $FF: synthetic method
   static Jet e$mcD$sp$(final JetIsTrig $this) {
      return $this.e$mcD$sp();
   }

   default Jet e$mcD$sp() {
      return this.e();
   }

   // $FF: synthetic method
   static Jet e$mcF$sp$(final JetIsTrig $this) {
      return $this.e$mcF$sp();
   }

   default Jet e$mcF$sp() {
      return this.e();
   }

   // $FF: synthetic method
   static Jet pi$mcD$sp$(final JetIsTrig $this) {
      return $this.pi$mcD$sp();
   }

   default Jet pi$mcD$sp() {
      return this.pi();
   }

   // $FF: synthetic method
   static Jet pi$mcF$sp$(final JetIsTrig $this) {
      return $this.pi$mcF$sp();
   }

   default Jet pi$mcF$sp() {
      return this.pi();
   }

   // $FF: synthetic method
   static Jet exp$mcD$sp$(final JetIsTrig $this, final Jet a) {
      return $this.exp$mcD$sp(a);
   }

   default Jet exp$mcD$sp(final Jet a) {
      return this.exp(a);
   }

   // $FF: synthetic method
   static Jet exp$mcF$sp$(final JetIsTrig $this, final Jet a) {
      return $this.exp$mcF$sp(a);
   }

   default Jet exp$mcF$sp(final Jet a) {
      return this.exp(a);
   }

   // $FF: synthetic method
   static Jet expm1$mcD$sp$(final JetIsTrig $this, final Jet a) {
      return $this.expm1$mcD$sp(a);
   }

   default Jet expm1$mcD$sp(final Jet a) {
      return this.expm1(a);
   }

   // $FF: synthetic method
   static Jet expm1$mcF$sp$(final JetIsTrig $this, final Jet a) {
      return $this.expm1$mcF$sp(a);
   }

   default Jet expm1$mcF$sp(final Jet a) {
      return this.expm1(a);
   }

   // $FF: synthetic method
   static Jet log$mcD$sp$(final JetIsTrig $this, final Jet a) {
      return $this.log$mcD$sp(a);
   }

   default Jet log$mcD$sp(final Jet a) {
      return this.log(a);
   }

   // $FF: synthetic method
   static Jet log$mcF$sp$(final JetIsTrig $this, final Jet a) {
      return $this.log$mcF$sp(a);
   }

   default Jet log$mcF$sp(final Jet a) {
      return this.log(a);
   }

   // $FF: synthetic method
   static Jet log1p$mcD$sp$(final JetIsTrig $this, final Jet a) {
      return $this.log1p$mcD$sp(a);
   }

   default Jet log1p$mcD$sp(final Jet a) {
      return this.log1p(a);
   }

   // $FF: synthetic method
   static Jet log1p$mcF$sp$(final JetIsTrig $this, final Jet a) {
      return $this.log1p$mcF$sp(a);
   }

   default Jet log1p$mcF$sp(final Jet a) {
      return this.log1p(a);
   }

   // $FF: synthetic method
   static Jet sin$mcD$sp$(final JetIsTrig $this, final Jet a) {
      return $this.sin$mcD$sp(a);
   }

   default Jet sin$mcD$sp(final Jet a) {
      return this.sin(a);
   }

   // $FF: synthetic method
   static Jet sin$mcF$sp$(final JetIsTrig $this, final Jet a) {
      return $this.sin$mcF$sp(a);
   }

   default Jet sin$mcF$sp(final Jet a) {
      return this.sin(a);
   }

   // $FF: synthetic method
   static Jet cos$mcD$sp$(final JetIsTrig $this, final Jet a) {
      return $this.cos$mcD$sp(a);
   }

   default Jet cos$mcD$sp(final Jet a) {
      return this.cos(a);
   }

   // $FF: synthetic method
   static Jet cos$mcF$sp$(final JetIsTrig $this, final Jet a) {
      return $this.cos$mcF$sp(a);
   }

   default Jet cos$mcF$sp(final Jet a) {
      return this.cos(a);
   }

   // $FF: synthetic method
   static Jet tan$mcD$sp$(final JetIsTrig $this, final Jet a) {
      return $this.tan$mcD$sp(a);
   }

   default Jet tan$mcD$sp(final Jet a) {
      return this.tan(a);
   }

   // $FF: synthetic method
   static Jet tan$mcF$sp$(final JetIsTrig $this, final Jet a) {
      return $this.tan$mcF$sp(a);
   }

   default Jet tan$mcF$sp(final Jet a) {
      return this.tan(a);
   }

   // $FF: synthetic method
   static Jet asin$mcD$sp$(final JetIsTrig $this, final Jet a) {
      return $this.asin$mcD$sp(a);
   }

   default Jet asin$mcD$sp(final Jet a) {
      return this.asin(a);
   }

   // $FF: synthetic method
   static Jet asin$mcF$sp$(final JetIsTrig $this, final Jet a) {
      return $this.asin$mcF$sp(a);
   }

   default Jet asin$mcF$sp(final Jet a) {
      return this.asin(a);
   }

   // $FF: synthetic method
   static Jet acos$mcD$sp$(final JetIsTrig $this, final Jet a) {
      return $this.acos$mcD$sp(a);
   }

   default Jet acos$mcD$sp(final Jet a) {
      return this.acos(a);
   }

   // $FF: synthetic method
   static Jet acos$mcF$sp$(final JetIsTrig $this, final Jet a) {
      return $this.acos$mcF$sp(a);
   }

   default Jet acos$mcF$sp(final Jet a) {
      return this.acos(a);
   }

   // $FF: synthetic method
   static Jet atan$mcD$sp$(final JetIsTrig $this, final Jet a) {
      return $this.atan$mcD$sp(a);
   }

   default Jet atan$mcD$sp(final Jet a) {
      return this.atan(a);
   }

   // $FF: synthetic method
   static Jet atan$mcF$sp$(final JetIsTrig $this, final Jet a) {
      return $this.atan$mcF$sp(a);
   }

   default Jet atan$mcF$sp(final Jet a) {
      return this.atan(a);
   }

   // $FF: synthetic method
   static Jet atan2$mcD$sp$(final JetIsTrig $this, final Jet y, final Jet x) {
      return $this.atan2$mcD$sp(y, x);
   }

   default Jet atan2$mcD$sp(final Jet y, final Jet x) {
      return this.atan2(y, x);
   }

   // $FF: synthetic method
   static Jet atan2$mcF$sp$(final JetIsTrig $this, final Jet y, final Jet x) {
      return $this.atan2$mcF$sp(y, x);
   }

   default Jet atan2$mcF$sp(final Jet y, final Jet x) {
      return this.atan2(y, x);
   }

   // $FF: synthetic method
   static Jet sinh$mcD$sp$(final JetIsTrig $this, final Jet x) {
      return $this.sinh$mcD$sp(x);
   }

   default Jet sinh$mcD$sp(final Jet x) {
      return this.sinh(x);
   }

   // $FF: synthetic method
   static Jet sinh$mcF$sp$(final JetIsTrig $this, final Jet x) {
      return $this.sinh$mcF$sp(x);
   }

   default Jet sinh$mcF$sp(final Jet x) {
      return this.sinh(x);
   }

   // $FF: synthetic method
   static Jet cosh$mcD$sp$(final JetIsTrig $this, final Jet x) {
      return $this.cosh$mcD$sp(x);
   }

   default Jet cosh$mcD$sp(final Jet x) {
      return this.cosh(x);
   }

   // $FF: synthetic method
   static Jet cosh$mcF$sp$(final JetIsTrig $this, final Jet x) {
      return $this.cosh$mcF$sp(x);
   }

   default Jet cosh$mcF$sp(final Jet x) {
      return this.cosh(x);
   }

   // $FF: synthetic method
   static Jet tanh$mcD$sp$(final JetIsTrig $this, final Jet x) {
      return $this.tanh$mcD$sp(x);
   }

   default Jet tanh$mcD$sp(final Jet x) {
      return this.tanh(x);
   }

   // $FF: synthetic method
   static Jet tanh$mcF$sp$(final JetIsTrig $this, final Jet x) {
      return $this.tanh$mcF$sp(x);
   }

   default Jet tanh$mcF$sp(final Jet x) {
      return this.tanh(x);
   }

   // $FF: synthetic method
   static Jet toRadians$mcD$sp$(final JetIsTrig $this, final Jet a) {
      return $this.toRadians$mcD$sp(a);
   }

   default Jet toRadians$mcD$sp(final Jet a) {
      return this.toRadians(a);
   }

   // $FF: synthetic method
   static Jet toRadians$mcF$sp$(final JetIsTrig $this, final Jet a) {
      return $this.toRadians$mcF$sp(a);
   }

   default Jet toRadians$mcF$sp(final Jet a) {
      return this.toRadians(a);
   }

   // $FF: synthetic method
   static Jet toDegrees$mcD$sp$(final JetIsTrig $this, final Jet a) {
      return $this.toDegrees$mcD$sp(a);
   }

   default Jet toDegrees$mcD$sp(final Jet a) {
      return this.toDegrees(a);
   }

   // $FF: synthetic method
   static Jet toDegrees$mcF$sp$(final JetIsTrig $this, final Jet a) {
      return $this.toDegrees$mcF$sp(a);
   }

   default Jet toDegrees$mcF$sp(final Jet a) {
      return this.toDegrees(a);
   }

   static void $init$(final JetIsTrig $this) {
   }
}
