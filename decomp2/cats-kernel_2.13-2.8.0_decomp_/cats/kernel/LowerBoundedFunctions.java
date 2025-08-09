package cats.kernel;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005Y2qa\u0001\u0003\u0011\u0002\u0007\u0005\u0011\u0002C\u0003\u0012\u0001\u0011\u0005!\u0003C\u0003\u0017\u0001\u0011\u0005qCA\u000bM_^,'OQ8v]\u0012,GMR;oGRLwN\\:\u000b\u0005\u00151\u0011AB6fe:,GNC\u0001\b\u0003\u0011\u0019\u0017\r^:\u0004\u0001U\u0011!bK\n\u0003\u0001-\u0001\"\u0001D\b\u000e\u00035Q\u0011AD\u0001\u0006g\u000e\fG.Y\u0005\u0003!5\u0011a!\u00118z%\u00164\u0017A\u0002\u0013j]&$H\u0005F\u0001\u0014!\taA#\u0003\u0002\u0016\u001b\t!QK\\5u\u0003!i\u0017N\u001c\"pk:$WC\u0001\r\u001c)\tI\u0002\u0006\u0005\u0002\u001b71\u0001A!\u0003\u000f\u0003A\u0003\u0005\tQ1\u0001\u001e\u0005\u0005\t\u0015C\u0001\u0010\"!\taq$\u0003\u0002!\u001b\t9aj\u001c;iS:<\u0007C\u0001\u0007#\u0013\t\u0019SBA\u0002B]fD#aG\u0013\u0011\u000511\u0013BA\u0014\u000e\u0005-\u0019\b/Z2jC2L'0\u001a3\t\u000b%\u0012\u00019\u0001\u0016\u0002\u0005\u00154\bc\u0001\u000e,3\u0011)A\u0006\u0001b\u0001[\t\tA*\u0006\u0002/iE\u0011ad\f\t\u0004aE\u001aT\"\u0001\u0003\n\u0005I\"!\u0001\u0004'po\u0016\u0014(i\\;oI\u0016$\u0007C\u0001\u000e5\t\u0015)4F1\u0001\u001e\u0005\u0005!\u0006"
)
public interface LowerBoundedFunctions {
   // $FF: synthetic method
   static Object minBound$(final LowerBoundedFunctions $this, final LowerBounded ev) {
      return $this.minBound(ev);
   }

   default Object minBound(final LowerBounded ev) {
      return ev.minBound();
   }

   // $FF: synthetic method
   static boolean minBound$mZc$sp$(final LowerBoundedFunctions $this, final LowerBounded ev) {
      return $this.minBound$mZc$sp(ev);
   }

   default boolean minBound$mZc$sp(final LowerBounded ev) {
      return ev.minBound$mcZ$sp();
   }

   // $FF: synthetic method
   static byte minBound$mBc$sp$(final LowerBoundedFunctions $this, final LowerBounded ev) {
      return $this.minBound$mBc$sp(ev);
   }

   default byte minBound$mBc$sp(final LowerBounded ev) {
      return ev.minBound$mcB$sp();
   }

   // $FF: synthetic method
   static char minBound$mCc$sp$(final LowerBoundedFunctions $this, final LowerBounded ev) {
      return $this.minBound$mCc$sp(ev);
   }

   default char minBound$mCc$sp(final LowerBounded ev) {
      return ev.minBound$mcC$sp();
   }

   // $FF: synthetic method
   static double minBound$mDc$sp$(final LowerBoundedFunctions $this, final LowerBounded ev) {
      return $this.minBound$mDc$sp(ev);
   }

   default double minBound$mDc$sp(final LowerBounded ev) {
      return ev.minBound$mcD$sp();
   }

   // $FF: synthetic method
   static float minBound$mFc$sp$(final LowerBoundedFunctions $this, final LowerBounded ev) {
      return $this.minBound$mFc$sp(ev);
   }

   default float minBound$mFc$sp(final LowerBounded ev) {
      return ev.minBound$mcF$sp();
   }

   // $FF: synthetic method
   static int minBound$mIc$sp$(final LowerBoundedFunctions $this, final LowerBounded ev) {
      return $this.minBound$mIc$sp(ev);
   }

   default int minBound$mIc$sp(final LowerBounded ev) {
      return ev.minBound$mcI$sp();
   }

   // $FF: synthetic method
   static long minBound$mJc$sp$(final LowerBoundedFunctions $this, final LowerBounded ev) {
      return $this.minBound$mJc$sp(ev);
   }

   default long minBound$mJc$sp(final LowerBounded ev) {
      return ev.minBound$mcJ$sp();
   }

   // $FF: synthetic method
   static short minBound$mSc$sp$(final LowerBoundedFunctions $this, final LowerBounded ev) {
      return $this.minBound$mSc$sp(ev);
   }

   default short minBound$mSc$sp(final LowerBounded ev) {
      return ev.minBound$mcS$sp();
   }

   // $FF: synthetic method
   static void minBound$mVc$sp$(final LowerBoundedFunctions $this, final LowerBounded ev) {
      $this.minBound$mVc$sp(ev);
   }

   default void minBound$mVc$sp(final LowerBounded ev) {
      ev.minBound$mcV$sp();
   }

   static void $init$(final LowerBoundedFunctions $this) {
   }
}
