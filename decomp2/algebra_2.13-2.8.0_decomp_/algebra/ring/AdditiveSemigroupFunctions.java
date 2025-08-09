package algebra.ring;

import scala.Option;
import scala.collection.IterableOnce;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005maa\u0002\u0004\b!\u0003\r\t\u0001\u0004\u0005\u0006)\u0001!\t!\u0006\u0005\u00063\u0001!\tA\u0007\u0005\u0006q\u0001!\t!\u000f\u0005\u0006C\u0002!\tA\u0019\u0005\u0006s\u0002!\tA\u001f\u0002\u001b\u0003\u0012$\u0017\u000e^5wKN+W.[4s_V\u0004h)\u001e8di&|gn\u001d\u0006\u0003\u0011%\tAA]5oO*\t!\"A\u0004bY\u001e,'M]1\u0004\u0001U\u0011QbI\n\u0003\u00019\u0001\"a\u0004\n\u000e\u0003AQ\u0011!E\u0001\u0006g\u000e\fG.Y\u0005\u0003'A\u0011a!\u00118z%\u00164\u0017A\u0002\u0013j]&$H\u0005F\u0001\u0017!\tyq#\u0003\u0002\u0019!\t!QK\\5u\u0003UI7/\u00113eSRLg/Z\"p[6,H/\u0019;jm\u0016,\"a\u0007\u001c\u0015\u0005qy\u0002CA\b\u001e\u0013\tq\u0002CA\u0004C_>dW-\u00198\t\u000b\u0001\u0012\u00019A\u0011\u0002\u0005\u00154\bc\u0001\u0012$k1\u0001A!\u0002\u0013\u0001\u0005\u0004)#!A*\u0016\u0005\u0019z\u0013CA\u0014+!\ty\u0001&\u0003\u0002*!\t9aj\u001c;iS:<\u0007cA\u0016-]5\tq!\u0003\u0002.\u000f\t\t\u0012\t\u001a3ji&4XmU3nS\u001e\u0014x.\u001e9\u0011\u0005\tzC!\u0002\u0019$\u0005\u0004\t$!\u0001+\u0012\u0005\u001d\u0012\u0004CA\b4\u0013\t!\u0004CA\u0002B]f\u0004\"A\t\u001c\u0005\u000b]\u0012!\u0019A\u0019\u0003\u0003\u0005\u000bA\u0001\u001d7vgV\u0011!(\u0010\u000b\u0004wu{FC\u0001\u001f\\!\t\u0011S\bB\u00058\u0007\u0001\u0006\t\u0011!b\u0001c!2Qh\u0010\"M#Z\u0003\"a\u0004!\n\u0005\u0005\u0003\"aC:qK\u000eL\u0017\r\\5{K\u0012\fTaI\"E\r\u0016s!a\u0004#\n\u0005\u0015\u0003\u0012aA%oiF\"AeR&\u0012\u001d\tA5*D\u0001J\u0015\tQ5\"\u0001\u0004=e>|GOP\u0005\u0002#E*1%\u0014(Q\u001f:\u0011qBT\u0005\u0003\u001fB\tA\u0001T8oOF\"AeR&\u0012c\u0015\u0019#kU+U\u001d\ty1+\u0003\u0002U!\u0005)a\t\\8biF\"AeR&\u0012c\u0015\u0019s\u000b\u0017.Z\u001d\ty\u0001,\u0003\u0002Z!\u00051Ai\\;cY\u0016\fD\u0001J$L#!)\u0001e\u0001a\u00029B\u0019!e\t\u001f\t\u000by\u001b\u0001\u0019\u0001\u001f\u0002\u0003aDQ\u0001Y\u0002A\u0002q\n\u0011!_\u0001\u0005gVlg*\u0006\u0002dMR\u0019AM\u001d;\u0015\u0005\u0015\u0004\bC\u0001\u0012g\t%9D\u0001)A\u0001\u0002\u000b\u0007\u0011\u0007\u000b\u0004g\u007f!TGN\\\u0019\u0006G\r#\u0015.R\u0019\u0005I\u001d[\u0015#M\u0003$\u001b:[w*\r\u0003%\u000f.\u000b\u0012'B\u0012S'6$\u0016\u0007\u0002\u0013H\u0017F\tTaI,Y_f\u000bD\u0001J$L#!)\u0001\u0005\u0002a\u0002cB\u0019!eI3\t\u000bM$\u0001\u0019A3\u0002\u0003\u0005DQ!\u001e\u0003A\u0002Y\f\u0011A\u001c\t\u0003\u001f]L!\u0001\u001f\t\u0003\u0007%sG/\u0001\u0004uef\u001cV/\\\u000b\u0004w\u0006\rAc\u0001?\u0002\nQ\u0019Q0!\u0002\u0011\t=q\u0018\u0011A\u0005\u0003\u007fB\u0011aa\u00149uS>t\u0007c\u0001\u0012\u0002\u0004\u0011)q'\u0002b\u0001c!1\u0001%\u0002a\u0002\u0003\u000f\u0001BAI\u0012\u0002\u0002!9\u00111B\u0003A\u0002\u00055\u0011AA1t!\u0019\ty!!\u0006\u0002\u00029\u0019q)!\u0005\n\u0007\u0005M\u0001#A\u0004qC\u000e\\\u0017mZ3\n\t\u0005]\u0011\u0011\u0004\u0002\u0010)J\fg/\u001a:tC\ndWm\u00148dK*\u0019\u00111\u0003\t"
)
public interface AdditiveSemigroupFunctions {
   // $FF: synthetic method
   static boolean isAdditiveCommutative$(final AdditiveSemigroupFunctions $this, final AdditiveSemigroup ev) {
      return $this.isAdditiveCommutative(ev);
   }

   default boolean isAdditiveCommutative(final AdditiveSemigroup ev) {
      return ev instanceof AdditiveCommutativeSemigroup;
   }

   // $FF: synthetic method
   static Object plus$(final AdditiveSemigroupFunctions $this, final Object x, final Object y, final AdditiveSemigroup ev) {
      return $this.plus(x, y, ev);
   }

   default Object plus(final Object x, final Object y, final AdditiveSemigroup ev) {
      return ev.plus(x, y);
   }

   // $FF: synthetic method
   static Object sumN$(final AdditiveSemigroupFunctions $this, final Object a, final int n, final AdditiveSemigroup ev) {
      return $this.sumN(a, n, ev);
   }

   default Object sumN(final Object a, final int n, final AdditiveSemigroup ev) {
      return ev.sumN(a, n);
   }

   // $FF: synthetic method
   static Option trySum$(final AdditiveSemigroupFunctions $this, final IterableOnce as, final AdditiveSemigroup ev) {
      return $this.trySum(as, ev);
   }

   default Option trySum(final IterableOnce as, final AdditiveSemigroup ev) {
      return ev.trySum(as);
   }

   // $FF: synthetic method
   static double plus$mDc$sp$(final AdditiveSemigroupFunctions $this, final double x, final double y, final AdditiveSemigroup ev) {
      return $this.plus$mDc$sp(x, y, ev);
   }

   default double plus$mDc$sp(final double x, final double y, final AdditiveSemigroup ev) {
      return ev.plus$mcD$sp(x, y);
   }

   // $FF: synthetic method
   static float plus$mFc$sp$(final AdditiveSemigroupFunctions $this, final float x, final float y, final AdditiveSemigroup ev) {
      return $this.plus$mFc$sp(x, y, ev);
   }

   default float plus$mFc$sp(final float x, final float y, final AdditiveSemigroup ev) {
      return ev.plus$mcF$sp(x, y);
   }

   // $FF: synthetic method
   static int plus$mIc$sp$(final AdditiveSemigroupFunctions $this, final int x, final int y, final AdditiveSemigroup ev) {
      return $this.plus$mIc$sp(x, y, ev);
   }

   default int plus$mIc$sp(final int x, final int y, final AdditiveSemigroup ev) {
      return ev.plus$mcI$sp(x, y);
   }

   // $FF: synthetic method
   static long plus$mJc$sp$(final AdditiveSemigroupFunctions $this, final long x, final long y, final AdditiveSemigroup ev) {
      return $this.plus$mJc$sp(x, y, ev);
   }

   default long plus$mJc$sp(final long x, final long y, final AdditiveSemigroup ev) {
      return ev.plus$mcJ$sp(x, y);
   }

   // $FF: synthetic method
   static double sumN$mDc$sp$(final AdditiveSemigroupFunctions $this, final double a, final int n, final AdditiveSemigroup ev) {
      return $this.sumN$mDc$sp(a, n, ev);
   }

   default double sumN$mDc$sp(final double a, final int n, final AdditiveSemigroup ev) {
      return ev.sumN$mcD$sp(a, n);
   }

   // $FF: synthetic method
   static float sumN$mFc$sp$(final AdditiveSemigroupFunctions $this, final float a, final int n, final AdditiveSemigroup ev) {
      return $this.sumN$mFc$sp(a, n, ev);
   }

   default float sumN$mFc$sp(final float a, final int n, final AdditiveSemigroup ev) {
      return ev.sumN$mcF$sp(a, n);
   }

   // $FF: synthetic method
   static int sumN$mIc$sp$(final AdditiveSemigroupFunctions $this, final int a, final int n, final AdditiveSemigroup ev) {
      return $this.sumN$mIc$sp(a, n, ev);
   }

   default int sumN$mIc$sp(final int a, final int n, final AdditiveSemigroup ev) {
      return ev.sumN$mcI$sp(a, n);
   }

   // $FF: synthetic method
   static long sumN$mJc$sp$(final AdditiveSemigroupFunctions $this, final long a, final int n, final AdditiveSemigroup ev) {
      return $this.sumN$mJc$sp(a, n, ev);
   }

   default long sumN$mJc$sp(final long a, final int n, final AdditiveSemigroup ev) {
      return ev.sumN$mcJ$sp(a, n);
   }

   static void $init$(final AdditiveSemigroupFunctions $this) {
   }
}
