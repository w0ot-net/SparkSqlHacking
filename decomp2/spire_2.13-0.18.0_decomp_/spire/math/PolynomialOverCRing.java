package spire.math;

import algebra.ring.CommutativeRing;
import scala.reflect.ScalaSignature;
import spire.algebra.RingAssociativeAlgebra;

@ScalaSignature(
   bytes = "\u0006\u0005\u00113qAA\u0002\u0011\u0002G\u0005\u0001\u0002C\u0004B\u0001\t\u0007i1\t\"\u0003'A{G.\u001f8p[&\fGn\u0014<fe\u000e\u0013\u0016N\\4\u000b\u0005\u0011)\u0011\u0001B7bi\"T\u0011AB\u0001\u0006gBL'/Z\u0002\u0001+\tIaeE\u0003\u0001\u0015AQT\b\u0005\u0002\f\u001d5\tABC\u0001\u000e\u0003\u0015\u00198-\u00197b\u0013\tyAB\u0001\u0004B]f\u0014VM\u001a\t\u0004#u\u0001cB\u0001\n\u001b\u001d\t\u0019\u0002D\u0004\u0002\u0015/5\tQC\u0003\u0002\u0017\u000f\u00051AH]8pizJ\u0011AB\u0005\u00033\u0015\tq!\u00197hK\n\u0014\u0018-\u0003\u0002\u001c9\u00059\u0001/Y2lC\u001e,'BA\r\u0006\u0013\tqrDA\u0003D%&twM\u0003\u0002\u001c9A\u0019\u0011E\t\u0013\u000e\u0003\rI!aI\u0002\u0003\u0015A{G.\u001f8p[&\fG\u000e\u0005\u0002&M1\u0001A!C\u0014\u0001A\u0003\u0005\tQ1\u0001)\u0005\u0005\u0019\u0015CA\u0015-!\tY!&\u0003\u0002,\u0019\t9aj\u001c;iS:<\u0007CA\u0006.\u0013\tqCBA\u0002B]fD3A\n\u00194!\tY\u0011'\u0003\u00023\u0019\tY1\u000f]3dS\u0006d\u0017N_3ec\u0015\u0019C'N\u001c7\u001d\tYQ'\u0003\u00027\u0019\u00051Ai\\;cY\u0016\fD\u0001\n\u001d:\u001b9\u0011A#O\u0005\u0002\u001bA\u0019\u0011e\u000f\u0013\n\u0005q\u001a!A\u0005)pYftw.\\5bY>3XM\u001d*j]\u001e\u0004BAP !I5\tA$\u0003\u0002A9\t1\"+\u001b8h\u0003N\u001cxnY5bi&4X-\u00117hK\n\u0014\u0018-\u0001\u0004tG\u0006d\u0017M]\u000b\u0002\u0007B\u0019\u0011#\b\u0013"
)
public interface PolynomialOverCRing extends CommutativeRing, PolynomialOverRing, RingAssociativeAlgebra {
   CommutativeRing scalar();

   // $FF: synthetic method
   static CommutativeRing scalar$mcD$sp$(final PolynomialOverCRing $this) {
      return $this.scalar$mcD$sp();
   }

   default CommutativeRing scalar$mcD$sp() {
      return this.scalar();
   }

   // $FF: synthetic method
   static boolean specInstance$$(final PolynomialOverCRing $this) {
      return $this.specInstance$();
   }

   default boolean specInstance$() {
      return false;
   }
}
