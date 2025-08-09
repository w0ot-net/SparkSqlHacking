package spire.math;

import algebra.ring.Rig;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005%3q\u0001B\u0003\u0011\u0002\u0007\u0005!\u0002C\u0003@\u0001\u0011\u0005\u0001\tC\u0004E\u0001\t\u0007i1I#\t\u000b\u001d\u0003A\u0011\u0001%\u0003#A{G.\u001f8p[&\fGn\u0014<feJKwM\u0003\u0002\u0007\u000f\u0005!Q.\u0019;i\u0015\u0005A\u0011!B:qSJ,7\u0001A\u000b\u0003\u0017a\u0019B\u0001\u0001\u0007\u0013_A\u0011Q\u0002E\u0007\u0002\u001d)\tq\"A\u0003tG\u0006d\u0017-\u0003\u0002\u0012\u001d\t1\u0011I\\=SK\u001a\u00042a\u0005\u000b\u0017\u001b\u0005)\u0011BA\u000b\u0006\u0005Y\u0001v\u000e\\=o_6L\u0017\r\\(wKJ\u001cV-\\5sS:<\u0007CA\f\u0019\u0019\u0001!\u0011\"\u0007\u0001!\u0002\u0003\u0005)\u0019\u0001\u000e\u0003\u0003\r\u000b\"a\u0007\u0010\u0011\u00055a\u0012BA\u000f\u000f\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"!D\u0010\n\u0005\u0001r!aA!os\"\u001a\u0001DI\u0013\u0011\u00055\u0019\u0013B\u0001\u0013\u000f\u0005-\u0019\b/Z2jC2L'0\u001a32\u000b\r2s%\u000b\u0015\u000f\u000559\u0013B\u0001\u0015\u000f\u0003\u0019!u.\u001e2mKF\"AE\u000b\u0018\u0010\u001d\tYc&D\u0001-\u0015\ti\u0013\"\u0001\u0004=e>|GOP\u0005\u0002\u001fA\u0019\u0001'\u000f\u001f\u000f\u0005E2dB\u0001\u001a5\u001d\tY3'C\u0001\t\u0013\t)t!A\u0004bY\u001e,'M]1\n\u0005]B\u0014a\u00029bG.\fw-\u001a\u0006\u0003k\u001dI!AO\u001e\u0003\u0007IKwM\u0003\u00028qA\u00191#\u0010\f\n\u0005y*!A\u0003)pYftw.\\5bY\u00061A%\u001b8ji\u0012\"\u0012!\u0011\t\u0003\u001b\tK!a\u0011\b\u0003\tUs\u0017\u000e^\u0001\u0007g\u000e\fG.\u0019:\u0016\u0003\u0019\u00032\u0001M\u001d\u0017\u0003\ryg.Z\u000b\u0002y\u0001"
)
public interface PolynomialOverRig extends PolynomialOverSemiring, Rig {
   Rig scalar();

   // $FF: synthetic method
   static Polynomial one$(final PolynomialOverRig $this) {
      return $this.one();
   }

   default Polynomial one() {
      return Polynomial$.MODULE$.one(this.eq(), this.scalar(), this.ct());
   }

   // $FF: synthetic method
   static Rig scalar$mcD$sp$(final PolynomialOverRig $this) {
      return $this.scalar$mcD$sp();
   }

   default Rig scalar$mcD$sp() {
      return this.scalar();
   }

   // $FF: synthetic method
   static Polynomial one$mcD$sp$(final PolynomialOverRig $this) {
      return $this.one$mcD$sp();
   }

   default Polynomial one$mcD$sp() {
      return this.one();
   }

   // $FF: synthetic method
   static boolean specInstance$$(final PolynomialOverRig $this) {
      return $this.specInstance$();
   }

   default boolean specInstance$() {
      return false;
   }

   static void $init$(final PolynomialOverRig $this) {
   }
}
