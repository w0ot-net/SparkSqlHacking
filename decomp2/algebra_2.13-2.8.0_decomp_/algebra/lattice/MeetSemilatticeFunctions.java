package algebra.lattice;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005M3qa\u0001\u0003\u0011\u0002\u0007\u0005\u0011\u0002C\u0003\u0012\u0001\u0011\u0005!\u0003C\u0003\u0017\u0001\u0011\u0005qC\u0001\rNK\u0016$8+Z7jY\u0006$H/[2f\rVt7\r^5p]NT!!\u0002\u0004\u0002\u000f1\fG\u000f^5dK*\tq!A\u0004bY\u001e,'M]1\u0004\u0001U\u0011!\"R\n\u0003\u0001-\u0001\"\u0001D\b\u000e\u00035Q\u0011AD\u0001\u0006g\u000e\fG.Y\u0005\u0003!5\u0011a!\u00118z%\u00164\u0017A\u0002\u0013j]&$H\u0005F\u0001\u0014!\taA#\u0003\u0002\u0016\u001b\t!QK\\5u\u0003\u0011iW-\u001a;\u0016\u0005aaBcA\rP#R\u0011!D\u0011\t\u00037qa\u0001\u0001B\u0005\u001e\u0005\u0001\u0006\t\u0011!b\u0001=\t\t\u0011)\u0005\u0002 EA\u0011A\u0002I\u0005\u0003C5\u0011qAT8uQ&tw\r\u0005\u0002\rG%\u0011A%\u0004\u0002\u0004\u0003:L\bF\u0002\u000f'SMBT\b\u0005\u0002\rO%\u0011\u0001&\u0004\u0002\fgB,7-[1mSj,G-M\u0003$U-jCF\u0004\u0002\rW%\u0011A&D\u0001\u0004\u0013:$\u0018\u0007\u0002\u0013/e9q!a\f\u001a\u000e\u0003AR!!\r\u0005\u0002\rq\u0012xn\u001c;?\u0013\u0005q\u0011'B\u00125k]2dB\u0001\u00076\u0013\t1T\"\u0001\u0003M_:<\u0017\u0007\u0002\u0013/e9\tTaI\u001d;ymr!\u0001\u0004\u001e\n\u0005mj\u0011!\u0002$m_\u0006$\u0018\u0007\u0002\u0013/e9\tTa\t @\u0003\u0002s!\u0001D \n\u0005\u0001k\u0011A\u0002#pk\ndW-\r\u0003%]Ir\u0001\"B\"\u0003\u0001\b!\u0015AA3w!\rYRI\u0007\u0003\u0006\r\u0002\u0011\ra\u0012\u0002\u0002\u001bV\u0011\u0001JT\t\u0003?%\u00032AS&N\u001b\u0005!\u0011B\u0001'\u0005\u0005=iU-\u001a;TK6LG.\u0019;uS\u000e,\u0007CA\u000eO\t\u0015iRI1\u0001\u001f\u0011\u0015\u0001&\u00011\u0001\u001b\u0003\u0005A\b\"\u0002*\u0003\u0001\u0004Q\u0012!A="
)
public interface MeetSemilatticeFunctions {
   // $FF: synthetic method
   static Object meet$(final MeetSemilatticeFunctions $this, final Object x, final Object y, final MeetSemilattice ev) {
      return $this.meet(x, y, ev);
   }

   default Object meet(final Object x, final Object y, final MeetSemilattice ev) {
      return ev.meet(x, y);
   }

   // $FF: synthetic method
   static double meet$mDc$sp$(final MeetSemilatticeFunctions $this, final double x, final double y, final MeetSemilattice ev) {
      return $this.meet$mDc$sp(x, y, ev);
   }

   default double meet$mDc$sp(final double x, final double y, final MeetSemilattice ev) {
      return ev.meet$mcD$sp(x, y);
   }

   // $FF: synthetic method
   static float meet$mFc$sp$(final MeetSemilatticeFunctions $this, final float x, final float y, final MeetSemilattice ev) {
      return $this.meet$mFc$sp(x, y, ev);
   }

   default float meet$mFc$sp(final float x, final float y, final MeetSemilattice ev) {
      return ev.meet$mcF$sp(x, y);
   }

   // $FF: synthetic method
   static int meet$mIc$sp$(final MeetSemilatticeFunctions $this, final int x, final int y, final MeetSemilattice ev) {
      return $this.meet$mIc$sp(x, y, ev);
   }

   default int meet$mIc$sp(final int x, final int y, final MeetSemilattice ev) {
      return ev.meet$mcI$sp(x, y);
   }

   // $FF: synthetic method
   static long meet$mJc$sp$(final MeetSemilatticeFunctions $this, final long x, final long y, final MeetSemilattice ev) {
      return $this.meet$mJc$sp(x, y, ev);
   }

   default long meet$mJc$sp(final long x, final long y, final MeetSemilattice ev) {
      return ev.meet$mcJ$sp(x, y);
   }

   static void $init$(final MeetSemilatticeFunctions $this) {
   }
}
