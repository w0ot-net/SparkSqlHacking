package spire.math;

import algebra.ring.CommutativeRig;
import scala.collection.StringOps.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u00053\u0001b\u0002\u0005\u0011\u0002\u0007\u0005\u0001\u0002\u0004\u0005\u0006Q\u0001!\t!\u000b\u0005\u0006[\u0001!\tA\f\u0005\u0006_\u0001!\t\u0001\r\u0005\u0006k\u0001!\tE\u000e\u0005\u0006y\u0001!\t%\u0010\u0005\u0006\u0001\u0002!\tA\f\u0002\u000e\u001d\u0006$XO]1m\u0013N\u001c%+[4\u000b\u0005%Q\u0011\u0001B7bi\"T\u0011aC\u0001\u0006gBL'/Z\n\u0004\u00015\u0019\u0002C\u0001\b\u0012\u001b\u0005y!\"\u0001\t\u0002\u000bM\u001c\u0017\r\\1\n\u0005Iy!AB!osJ+g\rE\u0002\u0015C\u0011r!!\u0006\u0010\u000f\u0005YabBA\f\u001c\u001b\u0005A\"BA\r\u001b\u0003\u0019a$o\\8u}\r\u0001\u0011\"A\u0006\n\u0005uQ\u0011aB1mO\u0016\u0014'/Y\u0005\u0003?\u0001\nq\u0001]1dW\u0006<WM\u0003\u0002\u001e\u0015%\u0011!e\t\u0002\u0005\u0007JKwM\u0003\u0002 AA\u0011QEJ\u0007\u0002\u0011%\u0011q\u0005\u0003\u0002\b\u001d\u0006$XO]1m\u0003\u0019!\u0013N\\5uIQ\t!\u0006\u0005\u0002\u000fW%\u0011Af\u0004\u0002\u0005+:LG/A\u0002p]\u0016,\u0012\u0001J\u0001\u0005a2,8\u000fF\u0002%cMBQAM\u0002A\u0002\u0011\n\u0011!\u0019\u0005\u0006i\r\u0001\r\u0001J\u0001\u0002E\u0006\u0019\u0001o\\<\u0015\u0007\u0011:\u0004\bC\u00033\t\u0001\u0007A\u0005C\u00035\t\u0001\u0007\u0011\b\u0005\u0002\u000fu%\u00111h\u0004\u0002\u0004\u0013:$\u0018!\u0002;j[\u0016\u001cHc\u0001\u0013?\u007f!)!'\u0002a\u0001I!)A'\u0002a\u0001I\u0005!!0\u001a:p\u0001"
)
public interface NaturalIsCRig extends CommutativeRig {
   // $FF: synthetic method
   static Natural one$(final NaturalIsCRig $this) {
      return $this.one();
   }

   default Natural one() {
      return Natural$.MODULE$.apply(1L);
   }

   // $FF: synthetic method
   static Natural plus$(final NaturalIsCRig $this, final Natural a, final Natural b) {
      return $this.plus(a, b);
   }

   default Natural plus(final Natural a, final Natural b) {
      return a.$plus(b);
   }

   // $FF: synthetic method
   static Natural pow$(final NaturalIsCRig $this, final Natural a, final int b) {
      return $this.pow(a, b);
   }

   default Natural pow(final Natural a, final int b) {
      if (b < 0) {
         throw new IllegalArgumentException(.MODULE$.format$extension(scala.Predef..MODULE$.augmentString("negative exponent: %s"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(b)})));
      } else {
         return a.pow(UInt$.MODULE$.apply(b));
      }
   }

   // $FF: synthetic method
   static Natural times$(final NaturalIsCRig $this, final Natural a, final Natural b) {
      return $this.times(a, b);
   }

   default Natural times(final Natural a, final Natural b) {
      return a.$times(b);
   }

   // $FF: synthetic method
   static Natural zero$(final NaturalIsCRig $this) {
      return $this.zero();
   }

   default Natural zero() {
      return Natural$.MODULE$.apply(0L);
   }

   static void $init$(final NaturalIsCRig $this) {
   }
}
