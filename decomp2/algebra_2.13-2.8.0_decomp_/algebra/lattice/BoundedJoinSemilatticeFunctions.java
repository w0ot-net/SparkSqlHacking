package algebra.lattice;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005I3qa\u0001\u0003\u0011\u0002\u0007\u0005\u0011\u0002C\u0003)\u0001\u0011\u0005\u0011\u0006C\u0003.\u0001\u0011\u0005aFA\u0010C_VtG-\u001a3K_&t7+Z7jY\u0006$H/[2f\rVt7\r^5p]NT!!\u0002\u0004\u0002\u000f1\fG\u000f^5dK*\tq!A\u0004bY\u001e,'M]1\u0004\u0001U\u0011!bF\n\u0004\u0001-\t\u0002C\u0001\u0007\u0010\u001b\u0005i!\"\u0001\b\u0002\u000bM\u001c\u0017\r\\1\n\u0005Ai!AB!osJ+g\rE\u0002\u0013'Ui\u0011\u0001B\u0005\u0003)\u0011\u0011\u0001DS8j]N+W.\u001b7biRL7-\u001a$v]\u000e$\u0018n\u001c8t!\t1r\u0003\u0004\u0001\u0005\u000ba\u0001!\u0019A\r\u0003\u0003\t+\"A\u0007\u0012\u0012\u0005mq\u0002C\u0001\u0007\u001d\u0013\tiRBA\u0004O_RD\u0017N\\4\u0011\u0007Iy\u0012%\u0003\u0002!\t\t1\"i\\;oI\u0016$'j\\5o'\u0016l\u0017\u000e\\1ui&\u001cW\r\u0005\u0002\u0017E\u0011)1e\u0006b\u0001I\t\t\u0011)\u0005\u0002\u001cKA\u0011ABJ\u0005\u0003O5\u00111!\u00118z\u0003\u0019!\u0013N\\5uIQ\t!\u0006\u0005\u0002\rW%\u0011A&\u0004\u0002\u0005+:LG/\u0001\u0003{KJ|WCA\u00182)\t\u0001t\n\u0005\u0002\u0017c\u0011I1E\u0001Q\u0001\u0002\u0003\u0015\r\u0001\n\u0015\u0007cM2\u0004)\u0012&\u0011\u00051!\u0014BA\u001b\u000e\u0005-\u0019\b/Z2jC2L'0\u001a32\u000b\r:\u0004HO\u001d\u000f\u00051A\u0014BA\u001d\u000e\u0003\rIe\u000e^\u0019\u0005ImzdB\u0004\u0002=\u007f5\tQH\u0003\u0002?\u0011\u00051AH]8pizJ\u0011AD\u0019\u0006G\u0005\u0013Ei\u0011\b\u0003\u0019\tK!aQ\u0007\u0002\t1{gnZ\u0019\u0005Imzd\"M\u0003$\r\u001eK\u0005J\u0004\u0002\r\u000f&\u0011\u0001*D\u0001\u0006\r2|\u0017\r^\u0019\u0005Imzd\"M\u0003$\u00172sUJ\u0004\u0002\r\u0019&\u0011Q*D\u0001\u0007\t>,(\r\\32\t\u0011ZtH\u0004\u0005\u0006!\n\u0001\u001d!U\u0001\u0003KZ\u00042AF\f1\u0001"
)
public interface BoundedJoinSemilatticeFunctions extends JoinSemilatticeFunctions {
   // $FF: synthetic method
   static Object zero$(final BoundedJoinSemilatticeFunctions $this, final BoundedJoinSemilattice ev) {
      return $this.zero(ev);
   }

   default Object zero(final BoundedJoinSemilattice ev) {
      return ev.zero();
   }

   // $FF: synthetic method
   static double zero$mDc$sp$(final BoundedJoinSemilatticeFunctions $this, final BoundedJoinSemilattice ev) {
      return $this.zero$mDc$sp(ev);
   }

   default double zero$mDc$sp(final BoundedJoinSemilattice ev) {
      return ev.zero$mcD$sp();
   }

   // $FF: synthetic method
   static float zero$mFc$sp$(final BoundedJoinSemilatticeFunctions $this, final BoundedJoinSemilattice ev) {
      return $this.zero$mFc$sp(ev);
   }

   default float zero$mFc$sp(final BoundedJoinSemilattice ev) {
      return ev.zero$mcF$sp();
   }

   // $FF: synthetic method
   static int zero$mIc$sp$(final BoundedJoinSemilatticeFunctions $this, final BoundedJoinSemilattice ev) {
      return $this.zero$mIc$sp(ev);
   }

   default int zero$mIc$sp(final BoundedJoinSemilattice ev) {
      return ev.zero$mcI$sp();
   }

   // $FF: synthetic method
   static long zero$mJc$sp$(final BoundedJoinSemilatticeFunctions $this, final BoundedJoinSemilattice ev) {
      return $this.zero$mJc$sp(ev);
   }

   default long zero$mJc$sp(final BoundedJoinSemilattice ev) {
      return ev.zero$mcJ$sp();
   }

   static void $init$(final BoundedJoinSemilatticeFunctions $this) {
   }
}
