package spire.algebra;

import algebra.ring.AdditiveCommutativeGroup;
import algebra.ring.AdditiveCommutativeMonoid;
import algebra.ring.AdditiveCommutativeSemigroup;
import algebra.ring.AdditiveGroup;
import algebra.ring.AdditiveMonoid;
import algebra.ring.AdditiveSemigroup;
import cats.kernel.CommutativeGroup;
import cats.kernel.Eq;
import scala.Option;
import scala.collection.IterableOnce;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005m4q!\u0003\u0006\u0011\u0002\u0007\u0005q\u0002C\u0003D\u0001\u0011\u0005A\tC\u0003I\u0001\u0019\r\u0011\nC\u0003R\u0001\u0019\u0005!\u000bC\u0003X\u0001\u0011\u0005\u0001lB\u0003]\u0015!\u0005QLB\u0003\n\u0015!\u0005a\fC\u0003c\r\u0011\u00051\rC\u0003e\r\u0011\u0015QM\u0001\bBI\u0012LG/\u001b<f)>\u00148o\u001c:\u000b\u0005-a\u0011aB1mO\u0016\u0014'/\u0019\u0006\u0002\u001b\u0005)1\u000f]5sK\u000e\u0001Qc\u0001\t\u001eIM\u0019\u0001!E\f\u0011\u0005I)R\"A\n\u000b\u0003Q\tQa]2bY\u0006L!AF\n\u0003\u0007\u0005s\u0017\u0010\u0005\u0003\u00193m\u0019S\"\u0001\u0006\n\u0005iQ!AD!eI&$\u0018N^3BGRLwN\u001c\t\u00039ua\u0001\u0001B\u0003\u001f\u0001\t\u0007qDA\u0001W#\t\u0001\u0013\u0003\u0005\u0002\u0013C%\u0011!e\u0005\u0002\b\u001d>$\b.\u001b8h!\taB\u0005B\u0005&\u0001\u0001\u0006\t\u0011!b\u0001?\t\t!\u000b\u000b\u0004%O)\"\u0014H\u0010\t\u0003%!J!!K\n\u0003\u0017M\u0004XmY5bY&TX\rZ\u0019\u0006G-bc&\f\b\u0003%1J!!L\n\u0002\u0007%sG/\r\u0003%_M\"bB\u0001\u00194\u001b\u0005\t$B\u0001\u001a\u000f\u0003\u0019a$o\\8u}%\tA#M\u0003$kYBtG\u0004\u0002\u0013m%\u0011qgE\u0001\u0005\u0019>tw-\r\u0003%_M\"\u0012'B\u0012;wubdB\u0001\n<\u0013\ta4#A\u0003GY>\fG/\r\u0003%_M\"\u0012'B\u0012@\u0001\n\u000beB\u0001\nA\u0013\t\t5#\u0001\u0004E_V\u0014G.Z\u0019\u0005I=\u001aD#\u0001\u0004%S:LG\u000f\n\u000b\u0002\u000bB\u0011!CR\u0005\u0003\u000fN\u0011A!\u00168ji\u000611oY1mCJ,\u0012A\u0013\t\u0004\u0017:\u001bcB\u0001\rM\u0013\ti%\"A\u0004qC\u000e\\\u0017mZ3\n\u0005=\u0003&aD!eI&$\u0018N^3BE\u001e\u0013x.\u001e9\u000b\u00055S\u0011A\u00029nS:,8\u000fF\u0002$'VCQ\u0001V\u0002A\u0002m\t\u0011A\u001e\u0005\u0006-\u000e\u0001\raG\u0001\u0002o\u0006Ia-\u001b=Pe&<\u0017N\u001c\u000b\u00033j\u00032a\u0013(\u001c\u0011\u0015YF\u00011\u0001\u001c\u0003\tIG-\u0001\bBI\u0012LG/\u001b<f)>\u00148o\u001c:\u0011\u0005a11C\u0001\u0004`!\t\u0011\u0002-\u0003\u0002b'\t1\u0011I\\=SK\u001a\fa\u0001P5oSRtD#A/\u0002\u000b\u0005\u0004\b\u000f\\=\u0016\u0007\u0019L7\u000e\u0006\u0002hkB!\u0001\u0004\u00015k!\ta\u0012\u000eB\u0003\u001f\u0011\t\u0007q\u0004\u0005\u0002\u001dW\u0012IQ\u0005\u0003Q\u0001\u0002\u0003\u0015\ra\b\u0015\u0007W\u001ejw.]:2\u000b\rZCF\\\u00172\t\u0011z3\u0007F\u0019\u0006GU2\u0004oN\u0019\u0005I=\u001aD#M\u0003$um\u0012H(\r\u0003%_M\"\u0012'B\u0012@\u0001R\f\u0015\u0007\u0002\u00130gQAQA\u001e\u0005A\u0004\u001d\f\u0011A\u0016\u0015\u0003\u0011a\u0004\"AE=\n\u0005i\u001c\"AB5oY&tW\r"
)
public interface AdditiveTorsor extends AdditiveAction {
   static AdditiveTorsor apply(final AdditiveTorsor V) {
      return AdditiveTorsor$.MODULE$.apply(V);
   }

   AdditiveCommutativeGroup scalar();

   Object pminus(final Object v, final Object w);

   // $FF: synthetic method
   static AdditiveCommutativeGroup fixOrigin$(final AdditiveTorsor $this, final Object id) {
      return $this.fixOrigin(id);
   }

   default AdditiveCommutativeGroup fixOrigin(final Object id) {
      return new AdditiveCommutativeGroup(id) {
         // $FF: synthetic field
         private final AdditiveTorsor $outer;
         private final Object id$1;

         public CommutativeGroup additive() {
            return AdditiveCommutativeGroup.additive$(this);
         }

         public CommutativeGroup additive$mcD$sp() {
            return AdditiveCommutativeGroup.additive$mcD$sp$(this);
         }

         public CommutativeGroup additive$mcF$sp() {
            return AdditiveCommutativeGroup.additive$mcF$sp$(this);
         }

         public CommutativeGroup additive$mcI$sp() {
            return AdditiveCommutativeGroup.additive$mcI$sp$(this);
         }

         public CommutativeGroup additive$mcJ$sp() {
            return AdditiveCommutativeGroup.additive$mcJ$sp$(this);
         }

         public double negate$mcD$sp(final double x) {
            return AdditiveGroup.negate$mcD$sp$(this, x);
         }

         public float negate$mcF$sp(final float x) {
            return AdditiveGroup.negate$mcF$sp$(this, x);
         }

         public int negate$mcI$sp(final int x) {
            return AdditiveGroup.negate$mcI$sp$(this, x);
         }

         public long negate$mcJ$sp(final long x) {
            return AdditiveGroup.negate$mcJ$sp$(this, x);
         }

         public double minus$mcD$sp(final double x, final double y) {
            return AdditiveGroup.minus$mcD$sp$(this, x, y);
         }

         public float minus$mcF$sp(final float x, final float y) {
            return AdditiveGroup.minus$mcF$sp$(this, x, y);
         }

         public int minus$mcI$sp(final int x, final int y) {
            return AdditiveGroup.minus$mcI$sp$(this, x, y);
         }

         public long minus$mcJ$sp(final long x, final long y) {
            return AdditiveGroup.minus$mcJ$sp$(this, x, y);
         }

         public Object sumN(final Object a, final int n) {
            return AdditiveGroup.sumN$(this, a, n);
         }

         public double sumN$mcD$sp(final double a, final int n) {
            return AdditiveGroup.sumN$mcD$sp$(this, a, n);
         }

         public float sumN$mcF$sp(final float a, final int n) {
            return AdditiveGroup.sumN$mcF$sp$(this, a, n);
         }

         public int sumN$mcI$sp(final int a, final int n) {
            return AdditiveGroup.sumN$mcI$sp$(this, a, n);
         }

         public long sumN$mcJ$sp(final long a, final int n) {
            return AdditiveGroup.sumN$mcJ$sp$(this, a, n);
         }

         public double zero$mcD$sp() {
            return AdditiveMonoid.zero$mcD$sp$(this);
         }

         public float zero$mcF$sp() {
            return AdditiveMonoid.zero$mcF$sp$(this);
         }

         public int zero$mcI$sp() {
            return AdditiveMonoid.zero$mcI$sp$(this);
         }

         public long zero$mcJ$sp() {
            return AdditiveMonoid.zero$mcJ$sp$(this);
         }

         public boolean isZero(final Object a, final Eq ev) {
            return AdditiveMonoid.isZero$(this, a, ev);
         }

         public boolean isZero$mcD$sp(final double a, final Eq ev) {
            return AdditiveMonoid.isZero$mcD$sp$(this, a, ev);
         }

         public boolean isZero$mcF$sp(final float a, final Eq ev) {
            return AdditiveMonoid.isZero$mcF$sp$(this, a, ev);
         }

         public boolean isZero$mcI$sp(final int a, final Eq ev) {
            return AdditiveMonoid.isZero$mcI$sp$(this, a, ev);
         }

         public boolean isZero$mcJ$sp(final long a, final Eq ev) {
            return AdditiveMonoid.isZero$mcJ$sp$(this, a, ev);
         }

         public Object sum(final IterableOnce as) {
            return AdditiveMonoid.sum$(this, as);
         }

         public double sum$mcD$sp(final IterableOnce as) {
            return AdditiveMonoid.sum$mcD$sp$(this, as);
         }

         public float sum$mcF$sp(final IterableOnce as) {
            return AdditiveMonoid.sum$mcF$sp$(this, as);
         }

         public int sum$mcI$sp(final IterableOnce as) {
            return AdditiveMonoid.sum$mcI$sp$(this, as);
         }

         public long sum$mcJ$sp(final IterableOnce as) {
            return AdditiveMonoid.sum$mcJ$sp$(this, as);
         }

         public Option trySum(final IterableOnce as) {
            return AdditiveMonoid.trySum$(this, as);
         }

         public double plus$mcD$sp(final double x, final double y) {
            return AdditiveSemigroup.plus$mcD$sp$(this, x, y);
         }

         public float plus$mcF$sp(final float x, final float y) {
            return AdditiveSemigroup.plus$mcF$sp$(this, x, y);
         }

         public int plus$mcI$sp(final int x, final int y) {
            return AdditiveSemigroup.plus$mcI$sp$(this, x, y);
         }

         public long plus$mcJ$sp(final long x, final long y) {
            return AdditiveSemigroup.plus$mcJ$sp$(this, x, y);
         }

         public Object positiveSumN(final Object a, final int n) {
            return AdditiveSemigroup.positiveSumN$(this, a, n);
         }

         public double positiveSumN$mcD$sp(final double a, final int n) {
            return AdditiveSemigroup.positiveSumN$mcD$sp$(this, a, n);
         }

         public float positiveSumN$mcF$sp(final float a, final int n) {
            return AdditiveSemigroup.positiveSumN$mcF$sp$(this, a, n);
         }

         public int positiveSumN$mcI$sp(final int a, final int n) {
            return AdditiveSemigroup.positiveSumN$mcI$sp$(this, a, n);
         }

         public long positiveSumN$mcJ$sp(final long a, final int n) {
            return AdditiveSemigroup.positiveSumN$mcJ$sp$(this, a, n);
         }

         public Object zero() {
            return this.id$1;
         }

         public Object plus(final Object v, final Object w) {
            return this.$outer.gplusl(this.$outer.pminus(v, this.id$1), w);
         }

         public Object negate(final Object v) {
            return this.$outer.gplusl(this.$outer.pminus(this.id$1, v), this.id$1);
         }

         public Object minus(final Object v, final Object w) {
            return this.$outer.gplusl(this.$outer.pminus(v, w), this.id$1);
         }

         public {
            if (AdditiveTorsor.this == null) {
               throw null;
            } else {
               this.$outer = AdditiveTorsor.this;
               this.id$1 = id$1;
               AdditiveSemigroup.$init$(this);
               AdditiveMonoid.$init$(this);
               AdditiveGroup.$init$(this);
               AdditiveCommutativeSemigroup.$init$(this);
               AdditiveCommutativeMonoid.$init$(this);
               AdditiveCommutativeGroup.$init$(this);
            }
         }
      };
   }

   // $FF: synthetic method
   static AdditiveCommutativeGroup scalar$mcD$sp$(final AdditiveTorsor $this) {
      return $this.scalar$mcD$sp();
   }

   default AdditiveCommutativeGroup scalar$mcD$sp() {
      return this.scalar();
   }

   // $FF: synthetic method
   static AdditiveCommutativeGroup scalar$mcF$sp$(final AdditiveTorsor $this) {
      return $this.scalar$mcF$sp();
   }

   default AdditiveCommutativeGroup scalar$mcF$sp() {
      return this.scalar();
   }

   // $FF: synthetic method
   static AdditiveCommutativeGroup scalar$mcI$sp$(final AdditiveTorsor $this) {
      return $this.scalar$mcI$sp();
   }

   default AdditiveCommutativeGroup scalar$mcI$sp() {
      return this.scalar();
   }

   // $FF: synthetic method
   static AdditiveCommutativeGroup scalar$mcJ$sp$(final AdditiveTorsor $this) {
      return $this.scalar$mcJ$sp();
   }

   default AdditiveCommutativeGroup scalar$mcJ$sp() {
      return this.scalar();
   }

   // $FF: synthetic method
   static double pminus$mcD$sp$(final AdditiveTorsor $this, final Object v, final Object w) {
      return $this.pminus$mcD$sp(v, w);
   }

   default double pminus$mcD$sp(final Object v, final Object w) {
      return BoxesRunTime.unboxToDouble(this.pminus(v, w));
   }

   // $FF: synthetic method
   static float pminus$mcF$sp$(final AdditiveTorsor $this, final Object v, final Object w) {
      return $this.pminus$mcF$sp(v, w);
   }

   default float pminus$mcF$sp(final Object v, final Object w) {
      return BoxesRunTime.unboxToFloat(this.pminus(v, w));
   }

   // $FF: synthetic method
   static int pminus$mcI$sp$(final AdditiveTorsor $this, final Object v, final Object w) {
      return $this.pminus$mcI$sp(v, w);
   }

   default int pminus$mcI$sp(final Object v, final Object w) {
      return BoxesRunTime.unboxToInt(this.pminus(v, w));
   }

   // $FF: synthetic method
   static long pminus$mcJ$sp$(final AdditiveTorsor $this, final Object v, final Object w) {
      return $this.pminus$mcJ$sp(v, w);
   }

   default long pminus$mcJ$sp(final Object v, final Object w) {
      return BoxesRunTime.unboxToLong(this.pminus(v, w));
   }

   static void $init$(final AdditiveTorsor $this) {
   }
}
