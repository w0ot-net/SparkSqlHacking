package cats.kernel;

import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005m2Qa\u0001\u0003\u0002\u0002%AQ!\n\u0001\u0005\u0002\u0019BQ\u0001\u000b\u0001\u0005\u0002%\u0012Q\u0002S1tQ\u001a+hn\u0019;j_:\u001c(BA\u0003\u0007\u0003\u0019YWM\u001d8fY*\tq!\u0001\u0003dCR\u001c8\u0001A\u000b\u0003\u0015E\u0019\"\u0001A\u0006\u0011\u00071iq\"D\u0001\u0005\u0013\tqAAA\u0006Fc\u001a+hn\u0019;j_:\u001c\bC\u0001\t\u0012\u0019\u0001!QA\u0005\u0001C\u0002M\u0011\u0011\u0001S\u000b\u0003)}\t\"!F\u000e\u0011\u0005YIR\"A\f\u000b\u0003a\tQa]2bY\u0006L!AG\f\u0003\u000f9{G\u000f[5oOB\u0019A\u0002\b\u0010\n\u0005u!!\u0001\u0002%bg\"\u0004\"\u0001E\u0010\u0005\u000b\u0001\n\"\u0019A\u0011\u0003\u0003Q\u000b\"!\u0006\u0012\u0011\u0005Y\u0019\u0013B\u0001\u0013\u0018\u0005\r\te._\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003\u001d\u00022\u0001\u0004\u0001\u0010\u0003\u0011A\u0017m\u001d5\u0016\u0005)\u001aDCA\u0016:)\tas\u0006\u0005\u0002\u0017[%\u0011af\u0006\u0002\u0004\u0013:$\b\"\u0002\u0019\u0003\u0001\b\t\u0014AA3w!\r\u0001\u0012C\r\t\u0003!M\"\u0011\u0002\u000e\u0002!\u0002\u0003\u0005)\u0019A\u0011\u0003\u0003\u0005C#a\r\u001c\u0011\u0005Y9\u0014B\u0001\u001d\u0018\u0005-\u0019\b/Z2jC2L'0\u001a3\t\u000bi\u0012\u0001\u0019\u0001\u001a\u0002\u0003a\u0004"
)
public abstract class HashFunctions extends EqFunctions {
   public int hash(final Object x, final Hash ev) {
      return ev.hash(x);
   }

   public int hash$mZc$sp(final boolean x, final Hash ev) {
      return ev.hash$mcZ$sp(x);
   }

   public int hash$mBc$sp(final byte x, final Hash ev) {
      return ev.hash$mcB$sp(x);
   }

   public int hash$mCc$sp(final char x, final Hash ev) {
      return ev.hash$mcC$sp(x);
   }

   public int hash$mDc$sp(final double x, final Hash ev) {
      return ev.hash$mcD$sp(x);
   }

   public int hash$mFc$sp(final float x, final Hash ev) {
      return ev.hash$mcF$sp(x);
   }

   public int hash$mIc$sp(final int x, final Hash ev) {
      return ev.hash$mcI$sp(x);
   }

   public int hash$mJc$sp(final long x, final Hash ev) {
      return ev.hash$mcJ$sp(x);
   }

   public int hash$mSc$sp(final short x, final Hash ev) {
      return ev.hash$mcS$sp(x);
   }

   public int hash$mVc$sp(final BoxedUnit x, final Hash ev) {
      return ev.hash$mcV$sp(x);
   }
}
