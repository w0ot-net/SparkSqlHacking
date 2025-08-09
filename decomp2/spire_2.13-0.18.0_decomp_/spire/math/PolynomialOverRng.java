package spire.math;

import algebra.ring.Rng;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005I3q!\u0002\u0004\u0011\u0002\u0007\u00051\u0002C\u0003A\u0001\u0011\u0005\u0011\tC\u0004F\u0001\t\u0007i1\t$\t\u000b!\u0003A\u0011A%\t\u000b9\u0003A\u0011A(\u0003#A{G.\u001f8p[&\fGn\u0014<feJswM\u0003\u0002\b\u0011\u0005!Q.\u0019;i\u0015\u0005I\u0011!B:qSJ,7\u0001A\u000b\u0003\u0019e\u0019B\u0001A\u0007\u0014aA\u0011a\"E\u0007\u0002\u001f)\t\u0001#A\u0003tG\u0006d\u0017-\u0003\u0002\u0013\u001f\t1\u0011I\\=SK\u001a\u00042\u0001F\u000b\u0018\u001b\u00051\u0011B\u0001\f\u0007\u0005Y\u0001v\u000e\\=o_6L\u0017\r\\(wKJ\u001cV-\\5sS:<\u0007C\u0001\r\u001a\u0019\u0001!\u0011B\u0007\u0001!\u0002\u0003\u0005)\u0019A\u000e\u0003\u0003\r\u000b\"\u0001H\u0010\u0011\u00059i\u0012B\u0001\u0010\u0010\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"A\u0004\u0011\n\u0005\u0005z!aA!os\"\u001a\u0011d\t\u0014\u0011\u00059!\u0013BA\u0013\u0010\u0005-\u0019\b/Z2jC2L'0\u001a32\u000b\r:\u0003FK\u0015\u000f\u00059A\u0013BA\u0015\u0010\u0003\u0019!u.\u001e2mKF\"AeK\u0018\u0011\u001d\tas&D\u0001.\u0015\tq#\"\u0001\u0004=e>|GOP\u0005\u0002!A\u0019\u0011GO\u001f\u000f\u0005I:dBA\u001a6\u001d\taC'C\u0001\n\u0013\t1\u0004\"A\u0004bY\u001e,'M]1\n\u0005aJ\u0014a\u00029bG.\fw-\u001a\u0006\u0003m!I!a\u000f\u001f\u0003\u0007IswM\u0003\u00029sA\u0019ACP\f\n\u0005}2!A\u0003)pYftw.\\5bY\u00061A%\u001b8ji\u0012\"\u0012A\u0011\t\u0003\u001d\rK!\u0001R\b\u0003\tUs\u0017\u000e^\u0001\u0007g\u000e\fG.\u0019:\u0016\u0003\u001d\u00032!\r\u001e\u0018\u0003\u0019!\u0018.\\3tYR\u0019QH\u0013'\t\u000b-\u001b\u0001\u0019A\f\u0002\u0003IDQ!T\u0002A\u0002u\n\u0011A^\u0001\u0007]\u0016<\u0017\r^3\u0015\u0005u\u0002\u0006\"B)\u0005\u0001\u0004i\u0014!\u0001="
)
public interface PolynomialOverRng extends PolynomialOverSemiring, Rng {
   Rng scalar();

   // $FF: synthetic method
   static Polynomial timesl$(final PolynomialOverRng $this, final Object r, final Polynomial v) {
      return $this.timesl(r, v);
   }

   default Polynomial timesl(final Object r, final Polynomial v) {
      return v.$times$colon(r, this.scalar(), this.eq());
   }

   // $FF: synthetic method
   static Polynomial negate$(final PolynomialOverRng $this, final Polynomial x) {
      return $this.negate(x);
   }

   default Polynomial negate(final Polynomial x) {
      return x.unary_$minus(this.scalar());
   }

   // $FF: synthetic method
   static Rng scalar$mcD$sp$(final PolynomialOverRng $this) {
      return $this.scalar$mcD$sp();
   }

   default Rng scalar$mcD$sp() {
      return this.scalar();
   }

   // $FF: synthetic method
   static Polynomial timesl$mcD$sp$(final PolynomialOverRng $this, final double r, final Polynomial v) {
      return $this.timesl$mcD$sp(r, v);
   }

   default Polynomial timesl$mcD$sp(final double r, final Polynomial v) {
      return this.timesl(BoxesRunTime.boxToDouble(r), v);
   }

   // $FF: synthetic method
   static Polynomial negate$mcD$sp$(final PolynomialOverRng $this, final Polynomial x) {
      return $this.negate$mcD$sp(x);
   }

   default Polynomial negate$mcD$sp(final Polynomial x) {
      return this.negate(x);
   }

   // $FF: synthetic method
   static boolean specInstance$$(final PolynomialOverRng $this) {
      return $this.specInstance$();
   }

   default boolean specInstance$() {
      return false;
   }

   static void $init$(final PolynomialOverRng $this) {
   }
}
