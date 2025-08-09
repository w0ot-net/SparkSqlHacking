package spire.math;

import algebra.ring.CommutativeRig;
import scala.collection.StringOps.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u00053\u0001b\u0002\u0005\u0011\u0002\u0007\u0005\u0001\u0002\u0004\u0005\u0006Q\u0001!\t!\u000b\u0005\u0006[\u0001!\tA\f\u0005\u0006_\u0001!\t\u0001\r\u0005\u0006k\u0001!\tE\u000e\u0005\u0006y\u0001!\t%\u0010\u0005\u0006\u0001\u0002!\tA\f\u0002\r+NCwN\u001d;Jg\u000e\u0013\u0016n\u001a\u0006\u0003\u0013)\tA!\\1uQ*\t1\"A\u0003ta&\u0014XmE\u0002\u0001\u001bM\u0001\"AD\t\u000e\u0003=Q\u0011\u0001E\u0001\u0006g\u000e\fG.Y\u0005\u0003%=\u0011a!\u00118z%\u00164\u0007c\u0001\u000b\"I9\u0011QC\b\b\u0003-qq!aF\u000e\u000e\u0003aQ!!\u0007\u000e\u0002\rq\u0012xn\u001c;?\u0007\u0001I\u0011aC\u0005\u0003;)\tq!\u00197hK\n\u0014\u0018-\u0003\u0002 A\u00059\u0001/Y2lC\u001e,'BA\u000f\u000b\u0013\t\u00113E\u0001\u0003D%&<'BA\u0010!!\t)c%D\u0001\t\u0013\t9\u0003B\u0001\u0004V'\"|'\u000f^\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003)\u0002\"AD\u0016\n\u00051z!\u0001B+oSR\f1a\u001c8f+\u0005!\u0013\u0001\u00029mkN$2\u0001J\u00194\u0011\u0015\u00114\u00011\u0001%\u0003\u0005\t\u0007\"\u0002\u001b\u0004\u0001\u0004!\u0013!\u00012\u0002\u0007A|w\u000fF\u0002%oaBQA\r\u0003A\u0002\u0011BQ\u0001\u000e\u0003A\u0002e\u0002\"A\u0004\u001e\n\u0005mz!aA%oi\u0006)A/[7fgR\u0019AEP \t\u000bI*\u0001\u0019\u0001\u0013\t\u000bQ*\u0001\u0019\u0001\u0013\u0002\ti,'o\u001c"
)
public interface UShortIsCRig extends CommutativeRig {
   // $FF: synthetic method
   static char one$(final UShortIsCRig $this) {
      return $this.one();
   }

   default char one() {
      return UShort$.MODULE$.apply((int)1);
   }

   // $FF: synthetic method
   static char plus$(final UShortIsCRig $this, final char a, final char b) {
      return $this.plus(a, b);
   }

   default char plus(final char a, final char b) {
      return UShort$.MODULE$.$plus$extension(a, b);
   }

   // $FF: synthetic method
   static char pow$(final UShortIsCRig $this, final char a, final int b) {
      return $this.pow(a, b);
   }

   default char pow(final char a, final int b) {
      if (b < 0) {
         throw new IllegalArgumentException(.MODULE$.format$extension(scala.Predef..MODULE$.augmentString("negative exponent: %s"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(b)})));
      } else {
         return UShort$.MODULE$.$times$times$extension(a, UShort$.MODULE$.apply(b));
      }
   }

   // $FF: synthetic method
   static char times$(final UShortIsCRig $this, final char a, final char b) {
      return $this.times(a, b);
   }

   default char times(final char a, final char b) {
      return UShort$.MODULE$.$times$extension(a, b);
   }

   // $FF: synthetic method
   static char zero$(final UShortIsCRig $this) {
      return $this.zero();
   }

   default char zero() {
      return UShort$.MODULE$.apply((int)0);
   }

   static void $init$(final UShortIsCRig $this) {
   }
}
