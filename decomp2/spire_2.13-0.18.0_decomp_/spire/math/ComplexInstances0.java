package spire.math;

import algebra.ring.CommutativeRing;
import algebra.ring.Signed;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005y2qa\u0001\u0003\u0011\u0002\u0007\u0005\u0011\u0002C\u0003\u0011\u0001\u0011\u0005\u0011\u0003C\u0003\u0016\u0001\u0011\raCA\tD_6\u0004H.\u001a=J]N$\u0018M\\2fgBR!!\u0002\u0004\u0002\t5\fG\u000f\u001b\u0006\u0002\u000f\u0005)1\u000f]5sK\u000e\u00011C\u0001\u0001\u000b!\tYa\"D\u0001\r\u0015\u0005i\u0011!B:dC2\f\u0017BA\b\r\u0005\u0019\te.\u001f*fM\u00061A%\u001b8ji\u0012\"\u0012A\u0005\t\u0003\u0017MI!\u0001\u0006\u0007\u0003\tUs\u0017\u000e^\u0001\u000f\u0007>l\u0007\u000f\\3y\u001f:\u001c%+\u001b8h+\t9b\u0004F\u0002\u0019Oe\u00022!\u0007\u000e\u001d\u001b\u0005!\u0011BA\u000e\u0005\u00059\u0019u.\u001c9mKb|en\u0011*j]\u001e\u0004\"!\b\u0010\r\u0001\u0011)qD\u0001b\u0001A\t\t\u0011)\u0005\u0002\"IA\u00111BI\u0005\u0003G1\u0011qAT8uQ&tw\r\u0005\u0002\fK%\u0011a\u0005\u0004\u0002\u0004\u0003:L\bb\u0002\u0015\u0003\u0003\u0003\u0005\u001d!K\u0001\u000bKZLG-\u001a8dK\u0012\"\u0004c\u0001\u0016799\u00111f\r\b\u0003YEr!!\f\u0019\u000e\u00039R!a\f\u0005\u0002\rq\u0012xn\u001c;?\u0013\u00059\u0011B\u0001\u001a\u0007\u0003\u001d\tGnZ3ce\u0006L!\u0001N\u001b\u0002\u000fA\f7m[1hK*\u0011!GB\u0005\u0003oa\u0012Qa\u0011*j]\u001eT!\u0001N\u001b\t\u000fi\u0012\u0011\u0011!a\u0002w\u0005QQM^5eK:\u001cW\rJ\u001b\u0011\u0007)bD$\u0003\u0002>q\t11+[4oK\u0012\u0004"
)
public interface ComplexInstances0 {
   // $FF: synthetic method
   static ComplexOnCRing ComplexOnCRing$(final ComplexInstances0 $this, final CommutativeRing evidence$4, final Signed evidence$5) {
      return $this.ComplexOnCRing(evidence$4, evidence$5);
   }

   default ComplexOnCRing ComplexOnCRing(final CommutativeRing evidence$4, final Signed evidence$5) {
      return new ComplexOnCRingImpl(evidence$4);
   }

   static void $init$(final ComplexInstances0 $this) {
   }
}
