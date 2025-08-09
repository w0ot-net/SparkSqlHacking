package spire.math;

import algebra.ring.CommutativeRing;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005=3\u0001BC\u0006\u0011\u0002\u0007\u00051b\u0004\u0005\u0006W\u0001!\t\u0001\f\u0005\u0006a\u0001!\t%\r\u0005\u0006m\u0001!\ta\u000e\u0005\bs\u0001\u0011\r\u0011\"\u0001;\u0011\u0015Y\u0004\u0001\"\u0001=\u0011\u0015y\u0004\u0001\"\u0011A\u0011\u00151\u0005\u0001\"\u0011H\u0011\u001dQ\u0005A1A\u0005\u0002iBQa\u0013\u0001\u0005B1\u0013qbU1gK2{gnZ%t\u0007JKgn\u001a\u0006\u0003\u00195\tA!\\1uQ*\ta\"A\u0003ta&\u0014XmE\u0002\u0001!Y\u0001\"!\u0005\u000b\u000e\u0003IQ\u0011aE\u0001\u0006g\u000e\fG.Y\u0005\u0003+I\u0011a!\u00118z%\u00164\u0007cA\f%O9\u0011\u0001$\t\b\u00033}q!A\u0007\u0010\u000e\u0003mQ!\u0001H\u000f\u0002\rq\u0012xn\u001c;?\u0007\u0001I\u0011AD\u0005\u0003A5\tq!\u00197hK\n\u0014\u0018-\u0003\u0002#G\u00059\u0001/Y2lC\u001e,'B\u0001\u0011\u000e\u0013\t)cEA\u0003D%&twM\u0003\u0002#GA\u0011\u0001&K\u0007\u0002\u0017%\u0011!f\u0003\u0002\t'\u00064W\rT8oO\u00061A%\u001b8ji\u0012\"\u0012!\f\t\u0003#9J!a\f\n\u0003\tUs\u0017\u000e^\u0001\u0006[&tWo\u001d\u000b\u0004OI\"\u0004\"B\u001a\u0003\u0001\u00049\u0013!A1\t\u000bU\u0012\u0001\u0019A\u0014\u0002\u0003\t\faA\\3hCR,GCA\u00149\u0011\u0015\u00194\u00011\u0001(\u0003\ryg.Z\u000b\u0002O\u0005!\u0001\u000f\\;t)\r9SH\u0010\u0005\u0006g\u0015\u0001\ra\n\u0005\u0006k\u0015\u0001\raJ\u0001\u0004a><HcA\u0014B\u0005\")1G\u0002a\u0001O!)QG\u0002a\u0001\u0007B\u0011\u0011\u0003R\u0005\u0003\u000bJ\u00111!\u00138u\u0003\u0015!\u0018.\\3t)\r9\u0003*\u0013\u0005\u0006g\u001d\u0001\ra\n\u0005\u0006k\u001d\u0001\raJ\u0001\u0005u\u0016\u0014x.A\u0004ge>l\u0017J\u001c;\u0015\u0005\u001dj\u0005\"\u0002(\n\u0001\u0004\u0019\u0015!\u00018"
)
public interface SafeLongIsCRing extends CommutativeRing {
   void spire$math$SafeLongIsCRing$_setter_$one_$eq(final SafeLong x$1);

   void spire$math$SafeLongIsCRing$_setter_$zero_$eq(final SafeLong x$1);

   // $FF: synthetic method
   static SafeLong minus$(final SafeLongIsCRing $this, final SafeLong a, final SafeLong b) {
      return $this.minus(a, b);
   }

   default SafeLong minus(final SafeLong a, final SafeLong b) {
      return a.$minus(b);
   }

   // $FF: synthetic method
   static SafeLong negate$(final SafeLongIsCRing $this, final SafeLong a) {
      return $this.negate(a);
   }

   default SafeLong negate(final SafeLong a) {
      return a.unary_$minus();
   }

   SafeLong one();

   // $FF: synthetic method
   static SafeLong plus$(final SafeLongIsCRing $this, final SafeLong a, final SafeLong b) {
      return $this.plus(a, b);
   }

   default SafeLong plus(final SafeLong a, final SafeLong b) {
      return a.$plus(b);
   }

   // $FF: synthetic method
   static SafeLong pow$(final SafeLongIsCRing $this, final SafeLong a, final int b) {
      return $this.pow(a, b);
   }

   default SafeLong pow(final SafeLong a, final int b) {
      return a.pow(b);
   }

   // $FF: synthetic method
   static SafeLong times$(final SafeLongIsCRing $this, final SafeLong a, final SafeLong b) {
      return $this.times(a, b);
   }

   default SafeLong times(final SafeLong a, final SafeLong b) {
      return a.$times(b);
   }

   SafeLong zero();

   // $FF: synthetic method
   static SafeLong fromInt$(final SafeLongIsCRing $this, final int n) {
      return $this.fromInt(n);
   }

   default SafeLong fromInt(final int n) {
      return SafeLong$.MODULE$.apply(n);
   }

   static void $init$(final SafeLongIsCRing $this) {
      $this.spire$math$SafeLongIsCRing$_setter_$one_$eq(SafeLong$.MODULE$.one());
      $this.spire$math$SafeLongIsCRing$_setter_$zero_$eq(SafeLong$.MODULE$.zero());
   }
}
