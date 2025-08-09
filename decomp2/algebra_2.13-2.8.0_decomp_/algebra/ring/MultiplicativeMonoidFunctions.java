package algebra.ring;

import cats.kernel.Eq;
import scala.collection.IterableOnce;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005}aaB\u0003\u0007!\u0003\r\ta\u0003\u0005\u0006U\u0001!\ta\u000b\u0005\u0006_\u0001!\t\u0001\r\u0005\u0006+\u0002!\tA\u0016\u0005\u0006m\u0002!\ta\u001e\u0002\u001e\u001bVdG/\u001b9mS\u000e\fG/\u001b<f\u001b>tw.\u001b3Gk:\u001cG/[8og*\u0011q\u0001C\u0001\u0005e&twMC\u0001\n\u0003\u001d\tGnZ3ce\u0006\u001c\u0001!\u0006\u0002\r3M\u0019\u0001!D\n\u0011\u00059\tR\"A\b\u000b\u0003A\tQa]2bY\u0006L!AE\b\u0003\r\u0005s\u0017PU3g!\r!RcF\u0007\u0002\r%\u0011aC\u0002\u0002!\u001bVdG/\u001b9mS\u000e\fG/\u001b<f'\u0016l\u0017n\u001a:pkB4UO\\2uS>t7\u000f\u0005\u0002\u001931\u0001A!\u0002\u000e\u0001\u0005\u0004Y\"!A'\u0016\u0005q!\u0013CA\u000f!!\tqa$\u0003\u0002 \u001f\t9aj\u001c;iS:<\u0007c\u0001\u000b\"G%\u0011!E\u0002\u0002\u0015\u001bVdG/\u001b9mS\u000e\fG/\u001b<f\u001b>tw.\u001b3\u0011\u0005a!C!B\u0013\u001a\u0005\u00041#!\u0001+\u0012\u0005u9\u0003C\u0001\b)\u0013\tIsBA\u0002B]f\fa\u0001J5oSR$C#\u0001\u0017\u0011\u00059i\u0013B\u0001\u0018\u0010\u0005\u0011)f.\u001b;\u0002\u0007=tW-\u0006\u00022gQ\u0011!G\u0015\t\u00031M\"\u0011\u0002\u000e\u0002!\u0002\u0003\u0005)\u0019\u0001\u0014\u0003\u0003\u0005Cca\r\u001c:\u0007\"k\u0005C\u0001\b8\u0013\tAtBA\u0006ta\u0016\u001c\u0017.\u00197ju\u0016$\u0017'B\u0012;wubdB\u0001\b<\u0013\tat\"A\u0002J]R\fD\u0001\n C!9\u0011qHQ\u0007\u0002\u0001*\u0011\u0011IC\u0001\u0007yI|w\u000e\u001e \n\u0003A\tTa\t#F\u000f\u001as!AD#\n\u0005\u0019{\u0011\u0001\u0002'p]\u001e\fD\u0001\n C!E*1%\u0013&M\u0017:\u0011aBS\u0005\u0003\u0017>\tQA\u00127pCR\fD\u0001\n C!E*1ET(R!:\u0011abT\u0005\u0003!>\ta\u0001R8vE2,\u0017\u0007\u0002\u0013?\u0005BAQa\u0015\u0002A\u0004Q\u000b!!\u001a<\u0011\u0007aI\"'A\u0003jg>sW-\u0006\u0002XAR\u0011\u0001\f\u001e\u000b\u00043rS\u0007C\u0001\b[\u0013\tYvBA\u0004C_>dW-\u00198\t\u000bu\u001b\u00019\u00010\u0002\u0007\u00154\b\u0007E\u0002\u00193}\u0003\"\u0001\u00071\u0005\u0013Q\u001a\u0001\u0015!A\u0001\u0006\u00041\u0003F\u000217E\u00124\u0007.M\u0003$um\u001aG(\r\u0003%}\t\u0003\u0012'B\u0012E\u000b\u00164\u0015\u0007\u0002\u0013?\u0005B\tTaI%KO.\u000bD\u0001\n C!E*1ET(j!F\"AE\u0010\"\u0011\u0011\u0015Y7\u0001q\u0001m\u0003\r)g/\r\t\u0004[F|fB\u00018p\u001b\u0005A\u0011B\u00019\t\u0003\u001d\u0001\u0018mY6bO\u0016L!A]:\u0003\u0005\u0015\u000b(B\u00019\t\u0011\u0015)8\u00011\u0001`\u0003\u0005\t\u0017a\u00029s_\u0012,8\r^\u000b\u0003qn$2!_A\b)\rQ\u00181\u0002\t\u00031m$\u0011\u0002\u000e\u0003!\u0002\u0003\u0005)\u0019\u0001\u0014)\u0011m4Tp`A\u0002\u0003\u000f\tTa\t\u001e<}r\nD\u0001\n C!E21\u0005R#\u0002\u0002\u0019\u000bD\u0001\n C!E21%\u0013&\u0002\u0006-\u000bD\u0001\n C!E21ET(\u0002\nA\u000bD\u0001\n C!!11\u000b\u0002a\u0002\u0003\u001b\u00012\u0001G\r{\u0011\u001d\t\t\u0002\u0002a\u0001\u0003'\t!!Y:\u0011\u000b\u0005U\u0011\u0011\u0004>\u000f\u0007y\n9\"\u0003\u0002q\u001f%!\u00111DA\u000f\u0005=!&/\u0019<feN\f'\r\\3P]\u000e,'B\u00019\u0010\u0001"
)
public interface MultiplicativeMonoidFunctions extends MultiplicativeSemigroupFunctions {
   // $FF: synthetic method
   static Object one$(final MultiplicativeMonoidFunctions $this, final MultiplicativeMonoid ev) {
      return $this.one(ev);
   }

   default Object one(final MultiplicativeMonoid ev) {
      return ev.one();
   }

   // $FF: synthetic method
   static boolean isOne$(final MultiplicativeMonoidFunctions $this, final Object a, final MultiplicativeMonoid ev0, final Eq ev1) {
      return $this.isOne(a, ev0, ev1);
   }

   default boolean isOne(final Object a, final MultiplicativeMonoid ev0, final Eq ev1) {
      return ev0.isOne(a, ev1);
   }

   // $FF: synthetic method
   static Object product$(final MultiplicativeMonoidFunctions $this, final IterableOnce as, final MultiplicativeMonoid ev) {
      return $this.product(as, ev);
   }

   default Object product(final IterableOnce as, final MultiplicativeMonoid ev) {
      return ev.product(as);
   }

   // $FF: synthetic method
   static double one$mDc$sp$(final MultiplicativeMonoidFunctions $this, final MultiplicativeMonoid ev) {
      return $this.one$mDc$sp(ev);
   }

   default double one$mDc$sp(final MultiplicativeMonoid ev) {
      return ev.one$mcD$sp();
   }

   // $FF: synthetic method
   static float one$mFc$sp$(final MultiplicativeMonoidFunctions $this, final MultiplicativeMonoid ev) {
      return $this.one$mFc$sp(ev);
   }

   default float one$mFc$sp(final MultiplicativeMonoid ev) {
      return ev.one$mcF$sp();
   }

   // $FF: synthetic method
   static int one$mIc$sp$(final MultiplicativeMonoidFunctions $this, final MultiplicativeMonoid ev) {
      return $this.one$mIc$sp(ev);
   }

   default int one$mIc$sp(final MultiplicativeMonoid ev) {
      return ev.one$mcI$sp();
   }

   // $FF: synthetic method
   static long one$mJc$sp$(final MultiplicativeMonoidFunctions $this, final MultiplicativeMonoid ev) {
      return $this.one$mJc$sp(ev);
   }

   default long one$mJc$sp(final MultiplicativeMonoid ev) {
      return ev.one$mcJ$sp();
   }

   // $FF: synthetic method
   static boolean isOne$mDc$sp$(final MultiplicativeMonoidFunctions $this, final double a, final MultiplicativeMonoid ev0, final Eq ev1) {
      return $this.isOne$mDc$sp(a, ev0, ev1);
   }

   default boolean isOne$mDc$sp(final double a, final MultiplicativeMonoid ev0, final Eq ev1) {
      return ev0.isOne$mcD$sp(a, ev1);
   }

   // $FF: synthetic method
   static boolean isOne$mFc$sp$(final MultiplicativeMonoidFunctions $this, final float a, final MultiplicativeMonoid ev0, final Eq ev1) {
      return $this.isOne$mFc$sp(a, ev0, ev1);
   }

   default boolean isOne$mFc$sp(final float a, final MultiplicativeMonoid ev0, final Eq ev1) {
      return ev0.isOne$mcF$sp(a, ev1);
   }

   // $FF: synthetic method
   static boolean isOne$mIc$sp$(final MultiplicativeMonoidFunctions $this, final int a, final MultiplicativeMonoid ev0, final Eq ev1) {
      return $this.isOne$mIc$sp(a, ev0, ev1);
   }

   default boolean isOne$mIc$sp(final int a, final MultiplicativeMonoid ev0, final Eq ev1) {
      return ev0.isOne$mcI$sp(a, ev1);
   }

   // $FF: synthetic method
   static boolean isOne$mJc$sp$(final MultiplicativeMonoidFunctions $this, final long a, final MultiplicativeMonoid ev0, final Eq ev1) {
      return $this.isOne$mJc$sp(a, ev0, ev1);
   }

   default boolean isOne$mJc$sp(final long a, final MultiplicativeMonoid ev0, final Eq ev1) {
      return ev0.isOne$mcJ$sp(a, ev1);
   }

   // $FF: synthetic method
   static double product$mDc$sp$(final MultiplicativeMonoidFunctions $this, final IterableOnce as, final MultiplicativeMonoid ev) {
      return $this.product$mDc$sp(as, ev);
   }

   default double product$mDc$sp(final IterableOnce as, final MultiplicativeMonoid ev) {
      return ev.product$mcD$sp(as);
   }

   // $FF: synthetic method
   static float product$mFc$sp$(final MultiplicativeMonoidFunctions $this, final IterableOnce as, final MultiplicativeMonoid ev) {
      return $this.product$mFc$sp(as, ev);
   }

   default float product$mFc$sp(final IterableOnce as, final MultiplicativeMonoid ev) {
      return ev.product$mcF$sp(as);
   }

   // $FF: synthetic method
   static int product$mIc$sp$(final MultiplicativeMonoidFunctions $this, final IterableOnce as, final MultiplicativeMonoid ev) {
      return $this.product$mIc$sp(as, ev);
   }

   default int product$mIc$sp(final IterableOnce as, final MultiplicativeMonoid ev) {
      return ev.product$mcI$sp(as);
   }

   // $FF: synthetic method
   static long product$mJc$sp$(final MultiplicativeMonoidFunctions $this, final IterableOnce as, final MultiplicativeMonoid ev) {
      return $this.product$mJc$sp(as, ev);
   }

   default long product$mJc$sp(final IterableOnce as, final MultiplicativeMonoid ev) {
      return ev.product$mcJ$sp(as);
   }

   static void $init$(final MultiplicativeMonoidFunctions $this) {
   }
}
