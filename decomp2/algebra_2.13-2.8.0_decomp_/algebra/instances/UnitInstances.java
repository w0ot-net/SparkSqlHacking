package algebra.instances;

import algebra.ring.CommutativeRing;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00152qa\u0001\u0003\u0011\u0002\u0007\u0005\u0011\u0002C\u0003\u0019\u0001\u0011\u0005\u0011\u0004C\u0004\u001e\u0001\t\u0007I1\u0001\u0010\u0003\u001bUs\u0017\u000e^%ogR\fgnY3t\u0015\t)a!A\u0005j]N$\u0018M\\2fg*\tq!A\u0004bY\u001e,'M]1\u0004\u0001M\u0019\u0001A\u0003\t\u0011\u0005-qQ\"\u0001\u0007\u000b\u00035\tQa]2bY\u0006L!a\u0004\u0007\u0003\r\u0005s\u0017PU3g!\t\tr#D\u0001\u0013\u0015\t)1C\u0003\u0002\u0015+\u000511.\u001a:oK2T\u0011AF\u0001\u0005G\u0006$8/\u0003\u0002\u0004%\u00051A%\u001b8ji\u0012\"\u0012A\u0007\t\u0003\u0017mI!\u0001\b\u0007\u0003\tUs\u0017\u000e^\u0001\tk:LGOU5oOV\tq\u0004E\u0002!Gii\u0011!\t\u0006\u0003E\u0019\tAA]5oO&\u0011A%\t\u0002\u0010\u0007>lW.\u001e;bi&4XMU5oO\u0002"
)
public interface UnitInstances extends cats.kernel.instances.UnitInstances {
   void algebra$instances$UnitInstances$_setter_$unitRing_$eq(final CommutativeRing x$1);

   CommutativeRing unitRing();

   static void $init$(final UnitInstances $this) {
      $this.algebra$instances$UnitInstances$_setter_$unitRing_$eq(new UnitAlgebra());
   }
}
