package spire.math;

import algebra.ring.Signed;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00193\u0001BB\u0004\u0011\u0002\u0007\u0005qa\u0003\u0005\u0006U\u0001!\ta\u000b\u0005\u0006_\u0001!\t\u0001\r\u0005\u0006e\u0001!\ta\r\u0005\u0006y\u0001!\t%\u0010\u0005\u0006\u0007\u0002!\t\u0005\u0012\u0002\u000f'\u00064W\rT8oONKwM\\3e\u0015\tA\u0011\"\u0001\u0003nCRD'\"\u0001\u0006\u0002\u000bM\u0004\u0018N]3\u0014\t\u0001a!c\n\t\u0003\u001bAi\u0011A\u0004\u0006\u0002\u001f\u0005)1oY1mC&\u0011\u0011C\u0004\u0002\u0007\u0003:L(+\u001a4\u0011\u0007M\u00013E\u0004\u0002\u0015;9\u0011Qc\u0007\b\u0003-ii\u0011a\u0006\u0006\u00031e\ta\u0001\u0010:p_Rt4\u0001A\u0005\u0002\u0015%\u0011A$C\u0001\bC2<WM\u0019:b\u0013\tqr$A\u0004qC\u000e\\\u0017mZ3\u000b\u0005qI\u0011BA\u0011#\u0005\u0019\u0019\u0016n\u001a8fI*\u0011ad\b\t\u0003I\u0015j\u0011aB\u0005\u0003M\u001d\u0011\u0001bU1gK2{gn\u001a\t\u0003I!J!!K\u0004\u0003\u001bM\u000bg-\u001a'p]\u001e|%\u000fZ3s\u0003\u0019!\u0013N\\5uIQ\tA\u0006\u0005\u0002\u000e[%\u0011aF\u0004\u0002\u0005+:LG/A\u0003pe\u0012,'/F\u00012!\t!\u0003!A\rbI\u0012LG/\u001b<f\u0007>lW.\u001e;bi&4X-T8o_&$W#\u0001\u001b\u0011\u0005UBdB\u0001\u00137\u0013\t9t!\u0001\u0005TC\u001a,Gj\u001c8h\u0015\tI$(A\bTC\u001a,Gj\u001c8h\u00032<WM\u0019:b\u0013\tYtAA\tTC\u001a,Gj\u001c8h\u0013:\u001cH/\u00198dKN\faa]5h]VlGC\u0001 B!\tiq(\u0003\u0002A\u001d\t\u0019\u0011J\u001c;\t\u000b\t#\u0001\u0019A\u0012\u0002\u0003\u0005\f1!\u00192t)\t\u0019S\tC\u0003C\u000b\u0001\u00071\u0005"
)
public interface SafeLongSigned extends Signed, SafeLongOrder {
   // $FF: synthetic method
   static SafeLongSigned order$(final SafeLongSigned $this) {
      return $this.order();
   }

   default SafeLongSigned order() {
      return this;
   }

   // $FF: synthetic method
   static SafeLongInstances.SafeLongAlgebra$ additiveCommutativeMonoid$(final SafeLongSigned $this) {
      return $this.additiveCommutativeMonoid();
   }

   default SafeLongInstances.SafeLongAlgebra$ additiveCommutativeMonoid() {
      return SafeLong$.MODULE$.SafeLongAlgebra();
   }

   // $FF: synthetic method
   static int signum$(final SafeLongSigned $this, final SafeLong a) {
      return $this.signum(a);
   }

   default int signum(final SafeLong a) {
      return a.signum();
   }

   // $FF: synthetic method
   static SafeLong abs$(final SafeLongSigned $this, final SafeLong a) {
      return $this.abs(a);
   }

   default SafeLong abs(final SafeLong a) {
      return a.abs();
   }

   static void $init$(final SafeLongSigned $this) {
   }
}
