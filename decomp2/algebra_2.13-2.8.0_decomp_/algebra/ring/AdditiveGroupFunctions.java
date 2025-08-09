package algebra.ring;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005-4q\u0001B\u0003\u0011\u0002\u0007\u0005!\u0002C\u0003*\u0001\u0011\u0005!\u0006C\u0003/\u0001\u0011\u0005q\u0006C\u0003X\u0001\u0011\u0005\u0001L\u0001\fBI\u0012LG/\u001b<f\u000fJ|W\u000f\u001d$v]\u000e$\u0018n\u001c8t\u0015\t1q!\u0001\u0003sS:<'\"\u0001\u0005\u0002\u000f\u0005dw-\u001a2sC\u000e\u0001QCA\u0006\u0019'\r\u0001AB\u0005\t\u0003\u001bAi\u0011A\u0004\u0006\u0002\u001f\u0005)1oY1mC&\u0011\u0011C\u0004\u0002\u0007\u0003:L(+\u001a4\u0011\u0007M!b#D\u0001\u0006\u0013\t)RAA\fBI\u0012LG/\u001b<f\u001b>tw.\u001b3Gk:\u001cG/[8ogB\u0011q\u0003\u0007\u0007\u0001\t\u0015I\u0002A1\u0001\u001b\u0005\u00059UCA\u000e$#\tar\u0004\u0005\u0002\u000e;%\u0011aD\u0004\u0002\b\u001d>$\b.\u001b8h!\r\u0019\u0002EI\u0005\u0003C\u0015\u0011Q\"\u00113eSRLg/Z$s_V\u0004\bCA\f$\t\u0015!\u0003D1\u0001&\u0005\u0005!\u0016C\u0001\u000f'!\tiq%\u0003\u0002)\u001d\t\u0019\u0011I\\=\u0002\r\u0011Jg.\u001b;%)\u0005Y\u0003CA\u0007-\u0013\ticB\u0001\u0003V]&$\u0018A\u00028fO\u0006$X-\u0006\u00021gQ\u0011\u0011'\u0016\u000b\u0003eI\u0003\"aF\u001a\u0005\u0013Q\u0012\u0001\u0015!A\u0001\u0006\u0004)#!A!)\rM2\u0014h\u0011%N!\tiq'\u0003\u00029\u001d\tY1\u000f]3dS\u0006d\u0017N_3ec\u0015\u0019#hO\u001f=\u001d\ti1(\u0003\u0002=\u001d\u0005\u0019\u0011J\u001c;2\t\u0011r$i\u0004\b\u0003\u007f\tk\u0011\u0001\u0011\u0006\u0003\u0003&\ta\u0001\u0010:p_Rt\u0014\"A\b2\u000b\r\"Ui\u0012$\u000f\u00055)\u0015B\u0001$\u000f\u0003\u0011auN\\42\t\u0011r$iD\u0019\u0006G%SEj\u0013\b\u0003\u001b)K!a\u0013\b\u0002\u000b\u0019cw.\u0019;2\t\u0011r$iD\u0019\u0006G9{\u0015\u000b\u0015\b\u0003\u001b=K!\u0001\u0015\b\u0002\r\u0011{WO\u00197fc\u0011!cHQ\b\t\u000bM\u0013\u00019\u0001+\u0002\u0005\u00154\bcA\f\u0019e!)aK\u0001a\u0001e\u0005\t\u00010A\u0003nS:,8/\u0006\u0002Z9R\u0019!\f[5\u0015\u0005m3\u0007CA\f]\t%!4\u0001)A\u0001\u0002\u000b\u0007Q\u0005\u000b\u0004]my\u0003'\rZ\u0019\u0006GiZt\fP\u0019\u0005Iy\u0012u\"M\u0003$\t\u0016\u000bg)\r\u0003%}\t{\u0011'B\u0012J\u0015\u000e\\\u0015\u0007\u0002\u0013?\u0005>\tTa\t(PKB\u000bD\u0001\n C\u001f!)1k\u0001a\u0002OB\u0019q\u0003G.\t\u000bY\u001b\u0001\u0019A.\t\u000b)\u001c\u0001\u0019A.\u0002\u0003e\u0004"
)
public interface AdditiveGroupFunctions extends AdditiveMonoidFunctions {
   // $FF: synthetic method
   static Object negate$(final AdditiveGroupFunctions $this, final Object x, final AdditiveGroup ev) {
      return $this.negate(x, ev);
   }

   default Object negate(final Object x, final AdditiveGroup ev) {
      return ev.negate(x);
   }

   // $FF: synthetic method
   static Object minus$(final AdditiveGroupFunctions $this, final Object x, final Object y, final AdditiveGroup ev) {
      return $this.minus(x, y, ev);
   }

   default Object minus(final Object x, final Object y, final AdditiveGroup ev) {
      return ev.minus(x, y);
   }

   // $FF: synthetic method
   static double negate$mDc$sp$(final AdditiveGroupFunctions $this, final double x, final AdditiveGroup ev) {
      return $this.negate$mDc$sp(x, ev);
   }

   default double negate$mDc$sp(final double x, final AdditiveGroup ev) {
      return ev.negate$mcD$sp(x);
   }

   // $FF: synthetic method
   static float negate$mFc$sp$(final AdditiveGroupFunctions $this, final float x, final AdditiveGroup ev) {
      return $this.negate$mFc$sp(x, ev);
   }

   default float negate$mFc$sp(final float x, final AdditiveGroup ev) {
      return ev.negate$mcF$sp(x);
   }

   // $FF: synthetic method
   static int negate$mIc$sp$(final AdditiveGroupFunctions $this, final int x, final AdditiveGroup ev) {
      return $this.negate$mIc$sp(x, ev);
   }

   default int negate$mIc$sp(final int x, final AdditiveGroup ev) {
      return ev.negate$mcI$sp(x);
   }

   // $FF: synthetic method
   static long negate$mJc$sp$(final AdditiveGroupFunctions $this, final long x, final AdditiveGroup ev) {
      return $this.negate$mJc$sp(x, ev);
   }

   default long negate$mJc$sp(final long x, final AdditiveGroup ev) {
      return ev.negate$mcJ$sp(x);
   }

   // $FF: synthetic method
   static double minus$mDc$sp$(final AdditiveGroupFunctions $this, final double x, final double y, final AdditiveGroup ev) {
      return $this.minus$mDc$sp(x, y, ev);
   }

   default double minus$mDc$sp(final double x, final double y, final AdditiveGroup ev) {
      return ev.minus$mcD$sp(x, y);
   }

   // $FF: synthetic method
   static float minus$mFc$sp$(final AdditiveGroupFunctions $this, final float x, final float y, final AdditiveGroup ev) {
      return $this.minus$mFc$sp(x, y, ev);
   }

   default float minus$mFc$sp(final float x, final float y, final AdditiveGroup ev) {
      return ev.minus$mcF$sp(x, y);
   }

   // $FF: synthetic method
   static int minus$mIc$sp$(final AdditiveGroupFunctions $this, final int x, final int y, final AdditiveGroup ev) {
      return $this.minus$mIc$sp(x, y, ev);
   }

   default int minus$mIc$sp(final int x, final int y, final AdditiveGroup ev) {
      return ev.minus$mcI$sp(x, y);
   }

   // $FF: synthetic method
   static long minus$mJc$sp$(final AdditiveGroupFunctions $this, final long x, final long y, final AdditiveGroup ev) {
      return $this.minus$mJc$sp(x, y, ev);
   }

   default long minus$mJc$sp(final long x, final long y, final AdditiveGroup ev) {
      return ev.minus$mcJ$sp(x, y);
   }

   static void $init$(final AdditiveGroupFunctions $this) {
   }
}
