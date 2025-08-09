package cats.kernel;

import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005%3Q\u0001B\u0003\u0002\u0002)AQA\u0005\u0001\u0005\u0002MAQ!\u000b\u0001\u0005\u0002)BQA\u0010\u0001\u0005\u0002}\u00121\"R9Gk:\u001cG/[8og*\u0011aaB\u0001\u0007W\u0016\u0014h.\u001a7\u000b\u0003!\tAaY1ug\u000e\u0001QCA\u0006\u0019'\t\u0001A\u0002\u0005\u0002\u000e!5\taBC\u0001\u0010\u0003\u0015\u00198-\u00197b\u0013\t\tbB\u0001\u0004B]f\u0014VMZ\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003Q\u00012!\u0006\u0001\u0017\u001b\u0005)\u0001CA\f\u0019\u0019\u0001!Q!\u0007\u0001C\u0002i\u0011\u0011!R\u000b\u00037\r\n\"\u0001H\u0010\u0011\u00055i\u0012B\u0001\u0010\u000f\u0005\u001dqu\u000e\u001e5j]\u001e\u00042!\u0006\u0011#\u0013\t\tSA\u0001\u0002FcB\u0011qc\t\u0003\u0006Ia\u0011\r!\n\u0002\u0002)F\u0011AD\n\t\u0003\u001b\u001dJ!\u0001\u000b\b\u0003\u0007\u0005s\u00170A\u0002fcZ,\"a\u000b\u001b\u0015\u00071RD\b\u0006\u0002.aA\u0011QBL\u0005\u0003_9\u0011qAQ8pY\u0016\fg\u000eC\u00032\u0005\u0001\u000f!'\u0001\u0002fmB\u0019q\u0003G\u001a\u0011\u0005]!D!C\u001b\u0003A\u0003\u0005\tQ1\u0001&\u0005\u0005\t\u0005F\u0001\u001b8!\ti\u0001(\u0003\u0002:\u001d\tY1\u000f]3dS\u0006d\u0017N_3e\u0011\u0015Y$\u00011\u00014\u0003\u0005A\b\"B\u001f\u0003\u0001\u0004\u0019\u0014!A=\u0002\t9,\u0017O^\u000b\u0003\u0001\u0016#2!Q$I)\ti#\tC\u00032\u0007\u0001\u000f1\tE\u0002\u00181\u0011\u0003\"aF#\u0005\u0013U\u001a\u0001\u0015!A\u0001\u0006\u0004)\u0003FA#8\u0011\u0015Y4\u00011\u0001E\u0011\u0015i4\u00011\u0001E\u0001"
)
public abstract class EqFunctions {
   public boolean eqv(final Object x, final Object y, final Eq ev) {
      return ev.eqv(x, y);
   }

   public boolean neqv(final Object x, final Object y, final Eq ev) {
      return ev.neqv(x, y);
   }

   public boolean eqv$mZc$sp(final boolean x, final boolean y, final Eq ev) {
      return ev.eqv$mcZ$sp(x, y);
   }

   public boolean eqv$mBc$sp(final byte x, final byte y, final Eq ev) {
      return ev.eqv$mcB$sp(x, y);
   }

   public boolean eqv$mCc$sp(final char x, final char y, final Eq ev) {
      return ev.eqv$mcC$sp(x, y);
   }

   public boolean eqv$mDc$sp(final double x, final double y, final Eq ev) {
      return ev.eqv$mcD$sp(x, y);
   }

   public boolean eqv$mFc$sp(final float x, final float y, final Eq ev) {
      return ev.eqv$mcF$sp(x, y);
   }

   public boolean eqv$mIc$sp(final int x, final int y, final Eq ev) {
      return ev.eqv$mcI$sp(x, y);
   }

   public boolean eqv$mJc$sp(final long x, final long y, final Eq ev) {
      return ev.eqv$mcJ$sp(x, y);
   }

   public boolean eqv$mSc$sp(final short x, final short y, final Eq ev) {
      return ev.eqv$mcS$sp(x, y);
   }

   public boolean eqv$mVc$sp(final BoxedUnit x, final BoxedUnit y, final Eq ev) {
      return ev.eqv$mcV$sp(x, y);
   }

   public boolean neqv$mZc$sp(final boolean x, final boolean y, final Eq ev) {
      return ev.neqv$mcZ$sp(x, y);
   }

   public boolean neqv$mBc$sp(final byte x, final byte y, final Eq ev) {
      return ev.neqv$mcB$sp(x, y);
   }

   public boolean neqv$mCc$sp(final char x, final char y, final Eq ev) {
      return ev.neqv$mcC$sp(x, y);
   }

   public boolean neqv$mDc$sp(final double x, final double y, final Eq ev) {
      return ev.neqv$mcD$sp(x, y);
   }

   public boolean neqv$mFc$sp(final float x, final float y, final Eq ev) {
      return ev.neqv$mcF$sp(x, y);
   }

   public boolean neqv$mIc$sp(final int x, final int y, final Eq ev) {
      return ev.neqv$mcI$sp(x, y);
   }

   public boolean neqv$mJc$sp(final long x, final long y, final Eq ev) {
      return ev.neqv$mcJ$sp(x, y);
   }

   public boolean neqv$mSc$sp(final short x, final short y, final Eq ev) {
      return ev.neqv$mcS$sp(x, y);
   }

   public boolean neqv$mVc$sp(final BoxedUnit x, final BoxedUnit y, final Eq ev) {
      return ev.neqv$mcV$sp(x, y);
   }
}
