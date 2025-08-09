package algebra.lattice;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005I3qa\u0001\u0003\u0011\u0002\u0007\u0005\u0011\u0002C\u0003)\u0001\u0011\u0005\u0011\u0006C\u0003.\u0001\u0011\u0005aFA\u0010C_VtG-\u001a3NK\u0016$8+Z7jY\u0006$H/[2f\rVt7\r^5p]NT!!\u0002\u0004\u0002\u000f1\fG\u000f^5dK*\tq!A\u0004bY\u001e,'M]1\u0004\u0001U\u0011!bF\n\u0004\u0001-\t\u0002C\u0001\u0007\u0010\u001b\u0005i!\"\u0001\b\u0002\u000bM\u001c\u0017\r\\1\n\u0005Ai!AB!osJ+g\rE\u0002\u0013'Ui\u0011\u0001B\u0005\u0003)\u0011\u0011\u0001$T3fiN+W.\u001b7biRL7-\u001a$v]\u000e$\u0018n\u001c8t!\t1r\u0003\u0004\u0001\u0005\u000ba\u0001!\u0019A\r\u0003\u0003\t+\"A\u0007\u0012\u0012\u0005mq\u0002C\u0001\u0007\u001d\u0013\tiRBA\u0004O_RD\u0017N\\4\u0011\u0007Iy\u0012%\u0003\u0002!\t\t1\"i\\;oI\u0016$W*Z3u'\u0016l\u0017\u000e\\1ui&\u001cW\r\u0005\u0002\u0017E\u0011)1e\u0006b\u0001I\t\t\u0011)\u0005\u0002\u001cKA\u0011ABJ\u0005\u0003O5\u00111!\u00118z\u0003\u0019!\u0013N\\5uIQ\t!\u0006\u0005\u0002\rW%\u0011A&\u0004\u0002\u0005+:LG/A\u0002p]\u0016,\"aL\u0019\u0015\u0005Az\u0005C\u0001\f2\t%\u0019#\u0001)A\u0001\u0002\u000b\u0007A\u0005\u000b\u00042gY\u0002UI\u0013\t\u0003\u0019QJ!!N\u0007\u0003\u0017M\u0004XmY5bY&TX\rZ\u0019\u0006G]B$(\u000f\b\u0003\u0019aJ!!O\u0007\u0002\u0007%sG/\r\u0003%w}raB\u0001\u001f@\u001b\u0005i$B\u0001 \t\u0003\u0019a$o\\8u}%\ta\"M\u0003$\u0003\n#5I\u0004\u0002\r\u0005&\u00111)D\u0001\u0005\u0019>tw-\r\u0003%w}r\u0011'B\u0012G\u000f&CeB\u0001\u0007H\u0013\tAU\"A\u0003GY>\fG/\r\u0003%w}r\u0011'B\u0012L\u0019:keB\u0001\u0007M\u0013\tiU\"\u0001\u0004E_V\u0014G.Z\u0019\u0005Imzd\u0002C\u0003Q\u0005\u0001\u000f\u0011+\u0001\u0002fmB\u0019ac\u0006\u0019"
)
public interface BoundedMeetSemilatticeFunctions extends MeetSemilatticeFunctions {
   // $FF: synthetic method
   static Object one$(final BoundedMeetSemilatticeFunctions $this, final BoundedMeetSemilattice ev) {
      return $this.one(ev);
   }

   default Object one(final BoundedMeetSemilattice ev) {
      return ev.one();
   }

   // $FF: synthetic method
   static double one$mDc$sp$(final BoundedMeetSemilatticeFunctions $this, final BoundedMeetSemilattice ev) {
      return $this.one$mDc$sp(ev);
   }

   default double one$mDc$sp(final BoundedMeetSemilattice ev) {
      return ev.one$mcD$sp();
   }

   // $FF: synthetic method
   static float one$mFc$sp$(final BoundedMeetSemilatticeFunctions $this, final BoundedMeetSemilattice ev) {
      return $this.one$mFc$sp(ev);
   }

   default float one$mFc$sp(final BoundedMeetSemilattice ev) {
      return ev.one$mcF$sp();
   }

   // $FF: synthetic method
   static int one$mIc$sp$(final BoundedMeetSemilatticeFunctions $this, final BoundedMeetSemilattice ev) {
      return $this.one$mIc$sp(ev);
   }

   default int one$mIc$sp(final BoundedMeetSemilattice ev) {
      return ev.one$mcI$sp();
   }

   // $FF: synthetic method
   static long one$mJc$sp$(final BoundedMeetSemilatticeFunctions $this, final BoundedMeetSemilattice ev) {
      return $this.one$mJc$sp(ev);
   }

   default long one$mJc$sp(final BoundedMeetSemilattice ev) {
      return ev.one$mcJ$sp();
   }

   static void $init$(final BoundedMeetSemilatticeFunctions $this) {
   }
}
