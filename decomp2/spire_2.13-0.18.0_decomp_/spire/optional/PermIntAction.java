package spire.optional;

import scala.reflect.ScalaSignature;
import spire.algebra.Action$mcI$sp;

@ScalaSignature(
   bytes = "\u0006\u0005-2A\u0001B\u0003\u0003\u0015!)a\u0004\u0001C\u0001?!)\u0011\u0005\u0001C\u0001E!)q\u0005\u0001C\u0001Q\ti\u0001+\u001a:n\u0013:$\u0018i\u0019;j_:T!AB\u0004\u0002\u0011=\u0004H/[8oC2T\u0011\u0001C\u0001\u0006gBL'/Z\u0002\u0001'\r\u00011\"\u0005\t\u0003\u0019=i\u0011!\u0004\u0006\u0002\u001d\u0005)1oY1mC&\u0011\u0001#\u0004\u0002\u0007\u0003:L(+\u001a4\u0011\tI)rCG\u0007\u0002')\u0011AcB\u0001\bC2<WM\u0019:b\u0013\t12C\u0001\u0004BGRLwN\u001c\t\u0003\u0019aI!!G\u0007\u0003\u0007%sG\u000f\u0005\u0002\u001c95\tQ!\u0003\u0002\u001e\u000b\t!\u0001+\u001a:n\u0003\u0019a\u0014N\\5u}Q\t\u0001\u0005\u0005\u0002\u001c\u0001\u0005!\u0011m\u0019;m)\r92%\n\u0005\u0006I\t\u0001\rAG\u0001\u0005a\u0016\u0014X\u000eC\u0003'\u0005\u0001\u0007q#A\u0001l\u0003\u0011\t7\r\u001e:\u0015\u0007]I#\u0006C\u0003'\u0007\u0001\u0007q\u0003C\u0003%\u0007\u0001\u0007!\u0004"
)
public final class PermIntAction implements Action$mcI$sp {
   public int actl(final Perm perm, final int k) {
      return this.actl$mcI$sp(perm, k);
   }

   public int actr(final int k, final Perm perm) {
      return this.actr$mcI$sp(k, perm);
   }

   public int actl$mcI$sp(final Perm perm, final int k) {
      return perm.apply$mcII$sp(k);
   }

   public int actr$mcI$sp(final int k, final Perm perm) {
      return perm.invert(k);
   }
}
