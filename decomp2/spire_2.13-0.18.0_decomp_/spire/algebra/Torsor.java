package spire.algebra;

import cats.kernel.CommutativeGroup;
import cats.kernel.CommutativeMonoid;
import cats.kernel.CommutativeSemigroup;
import cats.kernel.Eq;
import cats.kernel.Group;
import cats.kernel.Monoid;
import cats.kernel.Semigroup;
import scala.Option;
import scala.collection.IterableOnce;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005]4q\u0001C\u0005\u0011\u0002\u0007\u0005a\u0002C\u0003C\u0001\u0011\u00051\tC\u0003H\u0001\u0019\u0005\u0001\nC\u0003N\u0001\u0011\u0005ajB\u0003Y\u0013!\u0005\u0011LB\u0003\t\u0013!\u0005!\fC\u0003_\u000b\u0011\u0005q\fC\u0003a\u000b\u0011\u0015\u0011M\u0001\u0004U_J\u001cxN\u001d\u0006\u0003\u0015-\tq!\u00197hK\n\u0014\u0018MC\u0001\r\u0003\u0015\u0019\b/\u001b:f\u0007\u0001)2a\u0004\u000f$'\r\u0001\u0001C\u0006\t\u0003#Qi\u0011A\u0005\u0006\u0002'\u0005)1oY1mC&\u0011QC\u0005\u0002\u0004\u0003:L\b\u0003B\f\u00195\tj\u0011!C\u0005\u00033%\u0011a!Q2uS>t\u0007CA\u000e\u001d\u0019\u0001!Q!\b\u0001C\u0002y\u0011\u0011AV\t\u0003?A\u0001\"!\u0005\u0011\n\u0005\u0005\u0012\"a\u0002(pi\"Lgn\u001a\t\u00037\r\"\u0011\u0002\n\u0001!\u0002\u0003\u0005)\u0019\u0001\u0010\u0003\u0003ICca\t\u0014*gaj\u0004CA\t(\u0013\tA#CA\u0006ta\u0016\u001c\u0017.\u00197ju\u0016$\u0017'B\u0012+W5bcBA\t,\u0013\ta##A\u0002J]R\fD\u0001\n\u00183'9\u0011qFM\u0007\u0002a)\u0011\u0011'D\u0001\u0007yI|w\u000e\u001e \n\u0003M\tTa\t\u001b6oYr!!E\u001b\n\u0005Y\u0012\u0012\u0001\u0002'p]\u001e\fD\u0001\n\u00183'E*1%\u000f\u001e=w9\u0011\u0011CO\u0005\u0003wI\tQA\u00127pCR\fD\u0001\n\u00183'E*1EP B\u0001:\u0011\u0011cP\u0005\u0003\u0001J\ta\u0001R8vE2,\u0017\u0007\u0002\u0013/eM\ta\u0001J5oSR$C#\u0001#\u0011\u0005E)\u0015B\u0001$\u0013\u0005\u0011)f.\u001b;\u0002\t\u0011LgM\u001a\u000b\u0004E%[\u0005\"\u0002&\u0003\u0001\u0004Q\u0012!\u0001<\t\u000b1\u0013\u0001\u0019\u0001\u000e\u0002\u0003]\f\u0011BZ5y\u001fJLw-\u001b8\u0015\u0005=3\u0006c\u0001)T59\u0011q#U\u0005\u0003%&\tq\u0001]1dW\u0006<W-\u0003\u0002U+\n9\u0011IY$s_V\u0004(B\u0001*\n\u0011\u001596\u00011\u0001\u001b\u0003\rIG\rM\u0001\u0007)>\u00148o\u001c:\u0011\u0005])1CA\u0003\\!\t\tB,\u0003\u0002^%\t1\u0011I\\=SK\u001a\fa\u0001P5oSRtD#A-\u0002\u000b\u0005\u0004\b\u000f\\=\u0016\u0007\t,w\r\u0006\u0002dcB!q\u0003\u00013g!\tYR\rB\u0003\u001e\u000f\t\u0007a\u0004\u0005\u0002\u001cO\u0012IAe\u0002Q\u0001\u0002\u0003\u0015\rA\b\u0015\u0007O\u001aJ7.\\82\u000b\rR3F\u001b\u00172\t\u0011r#gE\u0019\u0006GQ*DNN\u0019\u0005I9\u00124#M\u0003$sir7(\r\u0003%]I\u001a\u0012'B\u0012?\u007fA\u0004\u0015\u0007\u0002\u0013/eMAQA]\u0004A\u0004\r\f\u0011A\u0016\u0015\u0003\u000fQ\u0004\"!E;\n\u0005Y\u0014\"AB5oY&tW\r"
)
public interface Torsor extends Action {
   static Torsor apply(final Torsor V) {
      return Torsor$.MODULE$.apply(V);
   }

   Object diff(final Object v, final Object w);

   // $FF: synthetic method
   static CommutativeGroup fixOrigin$(final Torsor $this, final Object id0) {
      return $this.fixOrigin(id0);
   }

   default CommutativeGroup fixOrigin(final Object id0) {
      return new CommutativeGroup(id0) {
         // $FF: synthetic field
         private final Torsor $outer;
         private final Object id0$1;

         public CommutativeMonoid reverse() {
            return CommutativeMonoid.reverse$(this);
         }

         public CommutativeMonoid reverse$mcD$sp() {
            return CommutativeMonoid.reverse$mcD$sp$(this);
         }

         public CommutativeMonoid reverse$mcF$sp() {
            return CommutativeMonoid.reverse$mcF$sp$(this);
         }

         public CommutativeMonoid reverse$mcI$sp() {
            return CommutativeMonoid.reverse$mcI$sp$(this);
         }

         public CommutativeMonoid reverse$mcJ$sp() {
            return CommutativeMonoid.reverse$mcJ$sp$(this);
         }

         public CommutativeSemigroup intercalate(final Object middle) {
            return CommutativeSemigroup.intercalate$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcD$sp(final double middle) {
            return CommutativeSemigroup.intercalate$mcD$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcF$sp(final float middle) {
            return CommutativeSemigroup.intercalate$mcF$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcI$sp(final int middle) {
            return CommutativeSemigroup.intercalate$mcI$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcJ$sp(final long middle) {
            return CommutativeSemigroup.intercalate$mcJ$sp$(this, middle);
         }

         public double inverse$mcD$sp(final double a) {
            return Group.inverse$mcD$sp$(this, a);
         }

         public float inverse$mcF$sp(final float a) {
            return Group.inverse$mcF$sp$(this, a);
         }

         public int inverse$mcI$sp(final int a) {
            return Group.inverse$mcI$sp$(this, a);
         }

         public long inverse$mcJ$sp(final long a) {
            return Group.inverse$mcJ$sp$(this, a);
         }

         public double remove$mcD$sp(final double a, final double b) {
            return Group.remove$mcD$sp$(this, a, b);
         }

         public float remove$mcF$sp(final float a, final float b) {
            return Group.remove$mcF$sp$(this, a, b);
         }

         public int remove$mcI$sp(final int a, final int b) {
            return Group.remove$mcI$sp$(this, a, b);
         }

         public long remove$mcJ$sp(final long a, final long b) {
            return Group.remove$mcJ$sp$(this, a, b);
         }

         public Object combineN(final Object a, final int n) {
            return Group.combineN$(this, a, n);
         }

         public double combineN$mcD$sp(final double a, final int n) {
            return Group.combineN$mcD$sp$(this, a, n);
         }

         public float combineN$mcF$sp(final float a, final int n) {
            return Group.combineN$mcF$sp$(this, a, n);
         }

         public int combineN$mcI$sp(final int a, final int n) {
            return Group.combineN$mcI$sp$(this, a, n);
         }

         public long combineN$mcJ$sp(final long a, final int n) {
            return Group.combineN$mcJ$sp$(this, a, n);
         }

         public double empty$mcD$sp() {
            return Monoid.empty$mcD$sp$(this);
         }

         public float empty$mcF$sp() {
            return Monoid.empty$mcF$sp$(this);
         }

         public int empty$mcI$sp() {
            return Monoid.empty$mcI$sp$(this);
         }

         public long empty$mcJ$sp() {
            return Monoid.empty$mcJ$sp$(this);
         }

         public boolean isEmpty(final Object a, final Eq ev) {
            return Monoid.isEmpty$(this, a, ev);
         }

         public boolean isEmpty$mcD$sp(final double a, final Eq ev) {
            return Monoid.isEmpty$mcD$sp$(this, a, ev);
         }

         public boolean isEmpty$mcF$sp(final float a, final Eq ev) {
            return Monoid.isEmpty$mcF$sp$(this, a, ev);
         }

         public boolean isEmpty$mcI$sp(final int a, final Eq ev) {
            return Monoid.isEmpty$mcI$sp$(this, a, ev);
         }

         public boolean isEmpty$mcJ$sp(final long a, final Eq ev) {
            return Monoid.isEmpty$mcJ$sp$(this, a, ev);
         }

         public Object combineAll(final IterableOnce as) {
            return Monoid.combineAll$(this, as);
         }

         public double combineAll$mcD$sp(final IterableOnce as) {
            return Monoid.combineAll$mcD$sp$(this, as);
         }

         public float combineAll$mcF$sp(final IterableOnce as) {
            return Monoid.combineAll$mcF$sp$(this, as);
         }

         public int combineAll$mcI$sp(final IterableOnce as) {
            return Monoid.combineAll$mcI$sp$(this, as);
         }

         public long combineAll$mcJ$sp(final IterableOnce as) {
            return Monoid.combineAll$mcJ$sp$(this, as);
         }

         public Option combineAllOption(final IterableOnce as) {
            return Monoid.combineAllOption$(this, as);
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

         public Object empty() {
            return this.id0$1;
         }

         public Object combine(final Object v, final Object w) {
            return this.$outer.actl(this.$outer.diff(v, this.id0$1), w);
         }

         public Object inverse(final Object v) {
            return this.$outer.actl(this.$outer.diff(this.id0$1, v), this.id0$1);
         }

         public Object remove(final Object v, final Object w) {
            return this.$outer.actl(this.$outer.diff(v, w), this.id0$1);
         }

         public {
            if (Torsor.this == null) {
               throw null;
            } else {
               this.$outer = Torsor.this;
               this.id0$1 = id0$1;
               Semigroup.$init$(this);
               Monoid.$init$(this);
               Group.$init$(this);
               CommutativeSemigroup.$init$(this);
               CommutativeMonoid.$init$(this);
            }
         }
      };
   }

   // $FF: synthetic method
   static double diff$mcD$sp$(final Torsor $this, final Object v, final Object w) {
      return $this.diff$mcD$sp(v, w);
   }

   default double diff$mcD$sp(final Object v, final Object w) {
      return BoxesRunTime.unboxToDouble(this.diff(v, w));
   }

   // $FF: synthetic method
   static float diff$mcF$sp$(final Torsor $this, final Object v, final Object w) {
      return $this.diff$mcF$sp(v, w);
   }

   default float diff$mcF$sp(final Object v, final Object w) {
      return BoxesRunTime.unboxToFloat(this.diff(v, w));
   }

   // $FF: synthetic method
   static int diff$mcI$sp$(final Torsor $this, final Object v, final Object w) {
      return $this.diff$mcI$sp(v, w);
   }

   default int diff$mcI$sp(final Object v, final Object w) {
      return BoxesRunTime.unboxToInt(this.diff(v, w));
   }

   // $FF: synthetic method
   static long diff$mcJ$sp$(final Torsor $this, final Object v, final Object w) {
      return $this.diff$mcJ$sp(v, w);
   }

   default long diff$mcJ$sp(final Object v, final Object w) {
      return BoxesRunTime.unboxToLong(this.diff(v, w));
   }

   static void $init$(final Torsor $this) {
   }
}
