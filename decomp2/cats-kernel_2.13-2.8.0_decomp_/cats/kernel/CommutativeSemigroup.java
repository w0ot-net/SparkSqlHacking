package cats.kernel;

import scala.Function2;
import scala.Option;
import scala.collection.IterableOnce;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005y4qAC\u0006\u0011\u0002\u0007\u0005\u0001\u0003C\u0003B\u0001\u0011\u0005!\tC\u0003G\u0001\u0011\u0005s\tC\u0003J\u0001\u0011\u0005#jB\u0003N\u0017!\u0005aJB\u0003\u000b\u0017!\u0005q\nC\u0003]\u000b\u0011\u0005Q\fC\u0003_\u000b\u0011\u0015q\fC\u0003k\u000b\u0011\u00051\u000eC\u0004w\u000b\u0005\u0005I\u0011B<\u0003)\r{W.\\;uCRLg/Z*f[&<'o\\;q\u0015\taQ\"\u0001\u0004lKJtW\r\u001c\u0006\u0002\u001d\u0005!1-\u0019;t\u0007\u0001)\"!\u0005\u0010\u0014\u0007\u0001\u0011\u0002\u0004\u0005\u0002\u0014-5\tACC\u0001\u0016\u0003\u0015\u00198-\u00197b\u0013\t9BCA\u0002B]f\u00042!\u0007\u000e\u001d\u001b\u0005Y\u0011BA\u000e\f\u0005%\u0019V-\\5he>,\b\u000f\u0005\u0002\u001e=1\u0001A!C\u0010\u0001A\u0003\u0005\tQ1\u0001!\u0005\u0005\t\u0015CA\u0011\u0013!\t\u0019\"%\u0003\u0002$)\t9aj\u001c;iS:<\u0007F\u0002\u0010&QI:D\b\u0005\u0002\u0014M%\u0011q\u0005\u0006\u0002\fgB,7-[1mSj,G-M\u0003$S)b3F\u0004\u0002\u0014U%\u00111\u0006F\u0001\u0004\u0013:$\u0018\u0007\u0002\u0013.cUq!AL\u0019\u000e\u0003=R!\u0001M\b\u0002\rq\u0012xn\u001c;?\u0013\u0005)\u0012'B\u00124iY*dBA\n5\u0013\t)D#\u0001\u0003M_:<\u0017\u0007\u0002\u0013.cU\tTa\t\u001d:wir!aE\u001d\n\u0005i\"\u0012!\u0002$m_\u0006$\u0018\u0007\u0002\u0013.cU\tTaI\u001f?\u0001~r!a\u0005 \n\u0005}\"\u0012A\u0002#pk\ndW-\r\u0003%[E*\u0012A\u0002\u0013j]&$H\u0005F\u0001D!\t\u0019B)\u0003\u0002F)\t!QK\\5u\u0003\u001d\u0011XM^3sg\u0016,\u0012\u0001\u0013\t\u00043\u0001a\u0012aC5oi\u0016\u00148-\u00197bi\u0016$\"\u0001S&\t\u000b1\u001b\u0001\u0019\u0001\u000f\u0002\r5LG\r\u001a7f\u0003Q\u0019u.\\7vi\u0006$\u0018N^3TK6LwM]8vaB\u0011\u0011$B\n\u0004\u000bA#\u0006cA\rR'&\u0011!k\u0003\u0002\u0013'\u0016l\u0017n\u001a:pkB4UO\\2uS>t7\u000f\u0005\u0002\u001a\u0001A\u0011QKW\u0007\u0002-*\u0011q\u000bW\u0001\u0003S>T\u0011!W\u0001\u0005U\u00064\u0018-\u0003\u0002\\-\na1+\u001a:jC2L'0\u00192mK\u00061A(\u001b8jiz\"\u0012AT\u0001\u0006CB\u0004H._\u000b\u0003A\u000e$\"!\u00193\u0011\u0007e\u0001!\r\u0005\u0002\u001eG\u0012)qd\u0002b\u0001A!)Qm\u0002a\u0002C\u0006\u0011QM\u001e\u0015\u0003\u000f\u001d\u0004\"a\u00055\n\u0005%$\"AB5oY&tW-\u0001\u0005j]N$\u0018M\\2f+\taw\u000e\u0006\u0002naB\u0019\u0011\u0004\u00018\u0011\u0005uyG!B\u0010\t\u0005\u0004\u0001\u0003\"B9\t\u0001\u0004\u0011\u0018aA2nEB)1c\u001d8o]&\u0011A\u000f\u0006\u0002\n\rVt7\r^5p]JB#\u0001C4\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0003a\u0004\"!\u001f?\u000e\u0003iT!a\u001f-\u0002\t1\fgnZ\u0005\u0003{j\u0014aa\u00142kK\u000e$\b"
)
public interface CommutativeSemigroup extends Semigroup {
   static CommutativeSemigroup instance(final Function2 cmb) {
      return CommutativeSemigroup$.MODULE$.instance(cmb);
   }

   static CommutativeSemigroup apply(final CommutativeSemigroup ev) {
      return CommutativeSemigroup$.MODULE$.apply(ev);
   }

   static boolean isIdempotent(final Semigroup ev) {
      return CommutativeSemigroup$.MODULE$.isIdempotent(ev);
   }

   static boolean isCommutative(final Semigroup ev) {
      return CommutativeSemigroup$.MODULE$.isCommutative(ev);
   }

   static Object maybeCombine(final Object x, final Option oy, final Semigroup ev) {
      return CommutativeSemigroup$.MODULE$.maybeCombine(x, oy, ev);
   }

   static Object maybeCombine(final Option ox, final Object y, final Semigroup ev) {
      return CommutativeSemigroup$.MODULE$.maybeCombine(ox, y, ev);
   }

   default CommutativeSemigroup reverse() {
      return this;
   }

   default CommutativeSemigroup intercalate(final Object middle) {
      return new CommutativeSemigroup(middle) {
         // $FF: synthetic field
         private final CommutativeSemigroup $outer;
         private final Object middle$1;

         public CommutativeSemigroup reverse() {
            return CommutativeSemigroup.super.reverse();
         }

         public CommutativeSemigroup reverse$mcD$sp() {
            return CommutativeSemigroup.super.reverse$mcD$sp();
         }

         public CommutativeSemigroup reverse$mcF$sp() {
            return CommutativeSemigroup.super.reverse$mcF$sp();
         }

         public CommutativeSemigroup reverse$mcI$sp() {
            return CommutativeSemigroup.super.reverse$mcI$sp();
         }

         public CommutativeSemigroup reverse$mcJ$sp() {
            return CommutativeSemigroup.super.reverse$mcJ$sp();
         }

         public CommutativeSemigroup intercalate(final Object middle) {
            return CommutativeSemigroup.super.intercalate(middle);
         }

         public CommutativeSemigroup intercalate$mcD$sp(final double middle) {
            return CommutativeSemigroup.super.intercalate$mcD$sp(middle);
         }

         public CommutativeSemigroup intercalate$mcF$sp(final float middle) {
            return CommutativeSemigroup.super.intercalate$mcF$sp(middle);
         }

         public CommutativeSemigroup intercalate$mcI$sp(final int middle) {
            return CommutativeSemigroup.super.intercalate$mcI$sp(middle);
         }

         public CommutativeSemigroup intercalate$mcJ$sp(final long middle) {
            return CommutativeSemigroup.super.intercalate$mcJ$sp(middle);
         }

         public double combine$mcD$sp(final double x, final double y) {
            return Semigroup.combine$mcD$sp$(this, x, y);
         }

         public float combine$mcF$sp(final float x, final float y) {
            return Semigroup.combine$mcF$sp$(this, x, y);
         }

         public int combine$mcI$sp(final int x, final int y) {
            return Semigroup.combine$mcI$sp$(this, x, y);
         }

         public long combine$mcJ$sp(final long x, final long y) {
            return Semigroup.combine$mcJ$sp$(this, x, y);
         }

         public double combineN$mcD$sp(final double a, final int n) {
            return Semigroup.combineN$mcD$sp$(this, a, n);
         }

         public float combineN$mcF$sp(final float a, final int n) {
            return Semigroup.combineN$mcF$sp$(this, a, n);
         }

         public int combineN$mcI$sp(final int a, final int n) {
            return Semigroup.combineN$mcI$sp$(this, a, n);
         }

         public long combineN$mcJ$sp(final long a, final int n) {
            return Semigroup.combineN$mcJ$sp$(this, a, n);
         }

         public Object repeatedCombineN(final Object a, final int n) {
            return Semigroup.repeatedCombineN$(this, a, n);
         }

         public double repeatedCombineN$mcD$sp(final double a, final int n) {
            return Semigroup.repeatedCombineN$mcD$sp$(this, a, n);
         }

         public float repeatedCombineN$mcF$sp(final float a, final int n) {
            return Semigroup.repeatedCombineN$mcF$sp$(this, a, n);
         }

         public int repeatedCombineN$mcI$sp(final int a, final int n) {
            return Semigroup.repeatedCombineN$mcI$sp$(this, a, n);
         }

         public long repeatedCombineN$mcJ$sp(final long a, final int n) {
            return Semigroup.repeatedCombineN$mcJ$sp$(this, a, n);
         }

         public Option combineAllOption(final IterableOnce as) {
            return Semigroup.combineAllOption$(this, as);
         }

         public Object combine(final Object a, final Object b) {
            return this.$outer.combine(a, this.$outer.combine(this.middle$1, b));
         }

         public Object combineN(final Object a, final int n) {
            return n <= 1 ? this.$outer.combineN(a, n) : this.$outer.combine(this.$outer.combineN(a, n), this.$outer.combineN(this.middle$1, n - 1));
         }

         public {
            if (CommutativeSemigroup.this == null) {
               throw null;
            } else {
               this.$outer = CommutativeSemigroup.this;
               this.middle$1 = middle$1;
               Semigroup.$init$(this);
               CommutativeSemigroup.$init$(this);
            }
         }
      };
   }

   default CommutativeSemigroup reverse$mcD$sp() {
      return this.reverse();
   }

   default CommutativeSemigroup reverse$mcF$sp() {
      return this.reverse();
   }

   default CommutativeSemigroup reverse$mcI$sp() {
      return this.reverse();
   }

   default CommutativeSemigroup reverse$mcJ$sp() {
      return this.reverse();
   }

   default CommutativeSemigroup intercalate$mcD$sp(final double middle) {
      return this.intercalate(BoxesRunTime.boxToDouble(middle));
   }

   default CommutativeSemigroup intercalate$mcF$sp(final float middle) {
      return this.intercalate(BoxesRunTime.boxToFloat(middle));
   }

   default CommutativeSemigroup intercalate$mcI$sp(final int middle) {
      return this.intercalate(BoxesRunTime.boxToInteger(middle));
   }

   default CommutativeSemigroup intercalate$mcJ$sp(final long middle) {
      return this.intercalate(BoxesRunTime.boxToLong(middle));
   }

   static void $init$(final CommutativeSemigroup $this) {
   }
}
