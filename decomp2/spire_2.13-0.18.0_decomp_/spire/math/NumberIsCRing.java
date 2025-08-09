package spire.math;

import algebra.ring.CommutativeRing;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005=3\u0001BC\u0006\u0011\u0002\u0007\u00051b\u0004\u0005\u0006W\u0001!\t\u0001\f\u0005\u0006a\u0001!\t%\r\u0005\u0006m\u0001!\ta\u000e\u0005\u0006s\u0001!\tA\u000f\u0005\u0006w\u0001!\t\u0001\u0010\u0005\u0006\u007f\u0001!\t\u0005\u0011\u0005\u0006\r\u0002!\te\u0012\u0005\u0006\u0015\u0002!\tA\u000f\u0005\u0006\u0017\u0002!\t\u0005\u0014\u0002\u000e\u001dVl'-\u001a:Jg\u000e\u0013\u0016N\\4\u000b\u00051i\u0011\u0001B7bi\"T\u0011AD\u0001\u0006gBL'/Z\n\u0004\u0001A1\u0002CA\t\u0015\u001b\u0005\u0011\"\"A\n\u0002\u000bM\u001c\u0017\r\\1\n\u0005U\u0011\"AB!osJ+g\rE\u0002\u0018I\u001dr!\u0001G\u0011\u000f\u0005eybB\u0001\u000e\u001f\u001b\u0005Y\"B\u0001\u000f\u001e\u0003\u0019a$o\\8u}\r\u0001\u0011\"\u0001\b\n\u0005\u0001j\u0011aB1mO\u0016\u0014'/Y\u0005\u0003E\r\nq\u0001]1dW\u0006<WM\u0003\u0002!\u001b%\u0011QE\n\u0002\u0006\u0007JKgn\u001a\u0006\u0003E\r\u0002\"\u0001K\u0015\u000e\u0003-I!AK\u0006\u0003\r9+XNY3s\u0003\u0019!\u0013N\\5uIQ\tQ\u0006\u0005\u0002\u0012]%\u0011qF\u0005\u0002\u0005+:LG/A\u0003nS:,8\u000fF\u0002(eQBQa\r\u0002A\u0002\u001d\n\u0011!\u0019\u0005\u0006k\t\u0001\raJ\u0001\u0002E\u00061a.Z4bi\u0016$\"a\n\u001d\t\u000bM\u001a\u0001\u0019A\u0014\u0002\u0007=tW-F\u0001(\u0003\u0011\u0001H.^:\u0015\u0007\u001djd\bC\u00034\u000b\u0001\u0007q\u0005C\u00036\u000b\u0001\u0007q%A\u0002q_^$2aJ!C\u0011\u0015\u0019d\u00011\u0001(\u0011\u0015)d\u00011\u0001D!\t\tB)\u0003\u0002F%\t\u0019\u0011J\u001c;\u0002\u000bQLW.Z:\u0015\u0007\u001dB\u0015\nC\u00034\u000f\u0001\u0007q\u0005C\u00036\u000f\u0001\u0007q%\u0001\u0003{KJ|\u0017a\u00024s_6Le\u000e\u001e\u000b\u0003O5CQAT\u0005A\u0002\r\u000b\u0011A\u001c"
)
public interface NumberIsCRing extends CommutativeRing {
   // $FF: synthetic method
   static Number minus$(final NumberIsCRing $this, final Number a, final Number b) {
      return $this.minus(a, b);
   }

   default Number minus(final Number a, final Number b) {
      return a.$minus(b);
   }

   // $FF: synthetic method
   static Number negate$(final NumberIsCRing $this, final Number a) {
      return $this.negate(a);
   }

   default Number negate(final Number a) {
      return a.unary_$minus();
   }

   // $FF: synthetic method
   static Number one$(final NumberIsCRing $this) {
      return $this.one();
   }

   default Number one() {
      return Number$.MODULE$.one();
   }

   // $FF: synthetic method
   static Number plus$(final NumberIsCRing $this, final Number a, final Number b) {
      return $this.plus(a, b);
   }

   default Number plus(final Number a, final Number b) {
      return a.$plus(b);
   }

   // $FF: synthetic method
   static Number pow$(final NumberIsCRing $this, final Number a, final int b) {
      return $this.pow(a, b);
   }

   default Number pow(final Number a, final int b) {
      return a.pow(Number$.MODULE$.apply(b));
   }

   // $FF: synthetic method
   static Number times$(final NumberIsCRing $this, final Number a, final Number b) {
      return $this.times(a, b);
   }

   default Number times(final Number a, final Number b) {
      return a.$times(b);
   }

   // $FF: synthetic method
   static Number zero$(final NumberIsCRing $this) {
      return $this.zero();
   }

   default Number zero() {
      return Number$.MODULE$.zero();
   }

   // $FF: synthetic method
   static Number fromInt$(final NumberIsCRing $this, final int n) {
      return $this.fromInt(n);
   }

   default Number fromInt(final int n) {
      return Number$.MODULE$.apply(n);
   }

   static void $init$(final NumberIsCRing $this) {
   }
}
