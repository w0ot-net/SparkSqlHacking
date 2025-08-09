package algebra.ring;

import cats.kernel.Eq;
import scala.collection.IterableOnce;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005}aaB\u0003\u0007!\u0003\r\ta\u0003\u0005\u0006U\u0001!\ta\u000b\u0005\u0006_\u0001!\t\u0001\r\u0005\u0006+\u0002!\tA\u0016\u0005\u0006m\u0002!\ta\u001e\u0002\u0018\u0003\u0012$\u0017\u000e^5wK6{gn\\5e\rVt7\r^5p]NT!a\u0002\u0005\u0002\tILgn\u001a\u0006\u0002\u0013\u00059\u0011\r\\4fEJ\f7\u0001A\u000b\u0003\u0019e\u00192\u0001A\u0007\u0014!\tq\u0011#D\u0001\u0010\u0015\u0005\u0001\u0012!B:dC2\f\u0017B\u0001\n\u0010\u0005\u0019\te.\u001f*fMB\u0019A#F\f\u000e\u0003\u0019I!A\u0006\u0004\u00035\u0005#G-\u001b;jm\u0016\u001cV-\\5he>,\bOR;oGRLwN\\:\u0011\u0005aIB\u0002\u0001\u0003\u00065\u0001\u0011\ra\u0007\u0002\u0002\u001bV\u0011A\u0004J\t\u0003;\u0001\u0002\"A\u0004\u0010\n\u0005}y!a\u0002(pi\"Lgn\u001a\t\u0004)\u0005\u001a\u0013B\u0001\u0012\u0007\u00059\tE\rZ5uSZ,Wj\u001c8pS\u0012\u0004\"\u0001\u0007\u0013\u0005\u000b\u0015J\"\u0019\u0001\u0014\u0003\u0003Q\u000b\"!H\u0014\u0011\u00059A\u0013BA\u0015\u0010\u0005\r\te._\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u00031\u0002\"AD\u0017\n\u00059z!\u0001B+oSR\fAA_3s_V\u0011\u0011g\r\u000b\u0003eI\u0003\"\u0001G\u001a\u0005\u0013Q\u0012\u0001\u0015!A\u0001\u0006\u00041#!A!)\rM2\u0014h\u0011%N!\tqq'\u0003\u00029\u001f\tY1\u000f]3dS\u0006d\u0017N_3ec\u0015\u0019#hO\u001f=\u001d\tq1(\u0003\u0002=\u001f\u0005\u0019\u0011J\u001c;2\t\u0011r$\t\u0005\b\u0003\u007f\tk\u0011\u0001\u0011\u0006\u0003\u0003*\ta\u0001\u0010:p_Rt\u0014\"\u0001\t2\u000b\r\"Ui\u0012$\u000f\u00059)\u0015B\u0001$\u0010\u0003\u0011auN\\42\t\u0011r$\tE\u0019\u0006G%SEj\u0013\b\u0003\u001d)K!aS\b\u0002\u000b\u0019cw.\u0019;2\t\u0011r$\tE\u0019\u0006G9{\u0015\u000b\u0015\b\u0003\u001d=K!\u0001U\b\u0002\r\u0011{WO\u00197fc\u0011!cH\u0011\t\t\u000bM\u0013\u00019\u0001+\u0002\u0005\u00154\bc\u0001\r\u001ae\u00051\u0011n\u001d.fe>,\"a\u00161\u0015\u0005a#HcA-]UB\u0011aBW\u0005\u00037>\u0011qAQ8pY\u0016\fg\u000eC\u0003^\u0007\u0001\u000fa,A\u0002fmB\u00022\u0001G\r`!\tA\u0002\rB\u00055\u0007\u0001\u0006\t\u0011!b\u0001M!2\u0001M\u000e2eM\"\fTa\t\u001e<Gr\nD\u0001\n C!E*1\u0005R#f\rF\"AE\u0010\"\u0011c\u0015\u0019\u0013JS4Lc\u0011!cH\u0011\t2\u000b\rru*\u001b)2\t\u0011r$\t\u0005\u0005\u0006W\u000e\u0001\u001d\u0001\\\u0001\u0004KZ\f\u0004cA7r?:\u0011an\\\u0007\u0002\u0011%\u0011\u0001\u000fC\u0001\ba\u0006\u001c7.Y4f\u0013\t\u00118O\u0001\u0002Fc*\u0011\u0001\u000f\u0003\u0005\u0006k\u000e\u0001\raX\u0001\u0002C\u0006\u00191/^7\u0016\u0005a\\HcA=\u0002\u0010Q\u0019!0a\u0003\u0011\u0005aYH!\u0003\u001b\u0005A\u0003\u0005\tQ1\u0001'Q!Yh'`@\u0002\u0004\u0005\u001d\u0011'B\u0012;wyd\u0014\u0007\u0002\u0013?\u0005B\tda\t#F\u0003\u00031\u0015\u0007\u0002\u0013?\u0005B\tdaI%K\u0003\u000bY\u0015\u0007\u0002\u0013?\u0005B\tda\t(P\u0003\u0013\u0001\u0016\u0007\u0002\u0013?\u0005BAaa\u0015\u0003A\u0004\u00055\u0001c\u0001\r\u001au\"9\u0011\u0011\u0003\u0003A\u0002\u0005M\u0011AA1t!\u0015\t)\"!\u0007{\u001d\rq\u0014qC\u0005\u0003a>IA!a\u0007\u0002\u001e\tyAK]1wKJ\u001c\u0018M\u00197f\u001f:\u001cWM\u0003\u0002q\u001f\u0001"
)
public interface AdditiveMonoidFunctions extends AdditiveSemigroupFunctions {
   // $FF: synthetic method
   static Object zero$(final AdditiveMonoidFunctions $this, final AdditiveMonoid ev) {
      return $this.zero(ev);
   }

   default Object zero(final AdditiveMonoid ev) {
      return ev.zero();
   }

   // $FF: synthetic method
   static boolean isZero$(final AdditiveMonoidFunctions $this, final Object a, final AdditiveMonoid ev0, final Eq ev1) {
      return $this.isZero(a, ev0, ev1);
   }

   default boolean isZero(final Object a, final AdditiveMonoid ev0, final Eq ev1) {
      return ev0.isZero(a, ev1);
   }

   // $FF: synthetic method
   static Object sum$(final AdditiveMonoidFunctions $this, final IterableOnce as, final AdditiveMonoid ev) {
      return $this.sum(as, ev);
   }

   default Object sum(final IterableOnce as, final AdditiveMonoid ev) {
      return ev.sum(as);
   }

   // $FF: synthetic method
   static double zero$mDc$sp$(final AdditiveMonoidFunctions $this, final AdditiveMonoid ev) {
      return $this.zero$mDc$sp(ev);
   }

   default double zero$mDc$sp(final AdditiveMonoid ev) {
      return ev.zero$mcD$sp();
   }

   // $FF: synthetic method
   static float zero$mFc$sp$(final AdditiveMonoidFunctions $this, final AdditiveMonoid ev) {
      return $this.zero$mFc$sp(ev);
   }

   default float zero$mFc$sp(final AdditiveMonoid ev) {
      return ev.zero$mcF$sp();
   }

   // $FF: synthetic method
   static int zero$mIc$sp$(final AdditiveMonoidFunctions $this, final AdditiveMonoid ev) {
      return $this.zero$mIc$sp(ev);
   }

   default int zero$mIc$sp(final AdditiveMonoid ev) {
      return ev.zero$mcI$sp();
   }

   // $FF: synthetic method
   static long zero$mJc$sp$(final AdditiveMonoidFunctions $this, final AdditiveMonoid ev) {
      return $this.zero$mJc$sp(ev);
   }

   default long zero$mJc$sp(final AdditiveMonoid ev) {
      return ev.zero$mcJ$sp();
   }

   // $FF: synthetic method
   static boolean isZero$mDc$sp$(final AdditiveMonoidFunctions $this, final double a, final AdditiveMonoid ev0, final Eq ev1) {
      return $this.isZero$mDc$sp(a, ev0, ev1);
   }

   default boolean isZero$mDc$sp(final double a, final AdditiveMonoid ev0, final Eq ev1) {
      return ev0.isZero$mcD$sp(a, ev1);
   }

   // $FF: synthetic method
   static boolean isZero$mFc$sp$(final AdditiveMonoidFunctions $this, final float a, final AdditiveMonoid ev0, final Eq ev1) {
      return $this.isZero$mFc$sp(a, ev0, ev1);
   }

   default boolean isZero$mFc$sp(final float a, final AdditiveMonoid ev0, final Eq ev1) {
      return ev0.isZero$mcF$sp(a, ev1);
   }

   // $FF: synthetic method
   static boolean isZero$mIc$sp$(final AdditiveMonoidFunctions $this, final int a, final AdditiveMonoid ev0, final Eq ev1) {
      return $this.isZero$mIc$sp(a, ev0, ev1);
   }

   default boolean isZero$mIc$sp(final int a, final AdditiveMonoid ev0, final Eq ev1) {
      return ev0.isZero$mcI$sp(a, ev1);
   }

   // $FF: synthetic method
   static boolean isZero$mJc$sp$(final AdditiveMonoidFunctions $this, final long a, final AdditiveMonoid ev0, final Eq ev1) {
      return $this.isZero$mJc$sp(a, ev0, ev1);
   }

   default boolean isZero$mJc$sp(final long a, final AdditiveMonoid ev0, final Eq ev1) {
      return ev0.isZero$mcJ$sp(a, ev1);
   }

   // $FF: synthetic method
   static double sum$mDc$sp$(final AdditiveMonoidFunctions $this, final IterableOnce as, final AdditiveMonoid ev) {
      return $this.sum$mDc$sp(as, ev);
   }

   default double sum$mDc$sp(final IterableOnce as, final AdditiveMonoid ev) {
      return ev.sum$mcD$sp(as);
   }

   // $FF: synthetic method
   static float sum$mFc$sp$(final AdditiveMonoidFunctions $this, final IterableOnce as, final AdditiveMonoid ev) {
      return $this.sum$mFc$sp(as, ev);
   }

   default float sum$mFc$sp(final IterableOnce as, final AdditiveMonoid ev) {
      return ev.sum$mcF$sp(as);
   }

   // $FF: synthetic method
   static int sum$mIc$sp$(final AdditiveMonoidFunctions $this, final IterableOnce as, final AdditiveMonoid ev) {
      return $this.sum$mIc$sp(as, ev);
   }

   default int sum$mIc$sp(final IterableOnce as, final AdditiveMonoid ev) {
      return ev.sum$mcI$sp(as);
   }

   // $FF: synthetic method
   static long sum$mJc$sp$(final AdditiveMonoidFunctions $this, final IterableOnce as, final AdditiveMonoid ev) {
      return $this.sum$mJc$sp(as, ev);
   }

   default long sum$mJc$sp(final IterableOnce as, final AdditiveMonoid ev) {
      return ev.sum$mcJ$sp(as);
   }

   static void $init$(final AdditiveMonoidFunctions $this) {
   }
}
