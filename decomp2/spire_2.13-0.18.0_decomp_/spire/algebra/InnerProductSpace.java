package spire.algebra;

import algebra.ring.AdditiveCommutativeGroup;
import algebra.ring.AdditiveCommutativeMonoid;
import algebra.ring.AdditiveCommutativeSemigroup;
import algebra.ring.AdditiveGroup;
import algebra.ring.AdditiveMonoid;
import algebra.ring.AdditiveSemigroup;
import algebra.ring.Field;
import cats.kernel.CommutativeGroup;
import cats.kernel.Eq;
import scala.Option;
import scala.collection.IterableOnce;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005EaaB\u0005\u000b!\u0003\r\ta\u0004\u0005\u0006\u0007\u0002!\t\u0001\u0012\u0005\u0006\u0011\u00021\t!\u0013\u0005\u0006\u001d\u0002!\taT\u0004\u00061*A\t!\u0017\u0004\u0006\u0013)A\tA\u0017\u0005\u0006M\u0016!\ta\u001a\u0005\u0006Q\u0016!)!\u001b\u0005\n\u0003\u0003)\u0011\u0011!C\u0005\u0003\u0007\u0011\u0011#\u00138oKJ\u0004&o\u001c3vGR\u001c\u0006/Y2f\u0015\tYA\"A\u0004bY\u001e,'M]1\u000b\u00035\tQa\u001d9je\u0016\u001c\u0001!F\u0002\u0011;\u0011\u001a2\u0001A\t\u0018!\t\u0011R#D\u0001\u0014\u0015\u0005!\u0012!B:dC2\f\u0017B\u0001\f\u0014\u0005\r\te.\u001f\t\u00051eY2%D\u0001\u000b\u0013\tQ\"BA\u0006WK\u000e$xN]*qC\u000e,\u0007C\u0001\u000f\u001e\u0019\u0001!QA\b\u0001C\u0002}\u0011\u0011AV\t\u0003AE\u0001\"AE\u0011\n\u0005\t\u001a\"a\u0002(pi\"Lgn\u001a\t\u00039\u0011\"\u0011\"\n\u0001!\u0002\u0003\u0005)\u0019A\u0010\u0003\u0003\u0019Cc\u0001J\u0014+ier\u0004C\u0001\n)\u0013\tI3CA\u0006ta\u0016\u001c\u0017.\u00197ju\u0016$\u0017'B\u0012,Y9jcB\u0001\n-\u0013\ti3#A\u0002J]R\fD\u0001J\u00184)9\u0011\u0001gM\u0007\u0002c)\u0011!GD\u0001\u0007yI|w\u000e\u001e \n\u0003Q\tTaI\u001b7q]r!A\u0005\u001c\n\u0005]\u001a\u0012\u0001\u0002'p]\u001e\fD\u0001J\u00184)E*1EO\u001e>y9\u0011!cO\u0005\u0003yM\tQA\u00127pCR\fD\u0001J\u00184)E*1e\u0010!C\u0003:\u0011!\u0003Q\u0005\u0003\u0003N\ta\u0001R8vE2,\u0017\u0007\u0002\u00130gQ\ta\u0001J5oSR$C#A#\u0011\u0005I1\u0015BA$\u0014\u0005\u0011)f.\u001b;\u0002\u0007\u0011|G\u000fF\u0002$\u00152CQa\u0013\u0002A\u0002m\t\u0011A\u001e\u0005\u0006\u001b\n\u0001\raG\u0001\u0002o\u00061an\u001c:nK\u0012$\"\u0001U*\u0011\ta\t6dI\u0005\u0003%*\u0011\u0011CT8s[\u0016$g+Z2u_J\u001c\u0006/Y2f\u0011\u0015!6\u0001q\u0001V\u0003\t)g\u000fE\u0002\u0019-\u000eJ!a\u0016\u0006\u0003\u000b9\u0013vn\u001c;\u0002#%sg.\u001a:Qe>$Wo\u0019;Ta\u0006\u001cW\r\u0005\u0002\u0019\u000bM\u0019Qa\u00170\u0011\u0005Ia\u0016BA/\u0014\u0005\u0019\te.\u001f*fMB\u0011q\fZ\u0007\u0002A*\u0011\u0011MY\u0001\u0003S>T\u0011aY\u0001\u0005U\u00064\u0018-\u0003\u0002fA\na1+\u001a:jC2L'0\u00192mK\u00061A(\u001b8jiz\"\u0012!W\u0001\u0006CB\u0004H._\u000b\u0004U6|GCA6{!\u0011A\u0002\u0001\u001c8\u0011\u0005qiG!\u0002\u0010\b\u0005\u0004y\u0002C\u0001\u000fp\t%\u0001x\u0001)A\u0001\u0002\u000b\u0007qDA\u0001SQ\u0019ywE\u001d;wqF*1e\u000b\u0017t[E\"AeL\u001a\u0015c\u0015\u0019SGN;8c\u0011!sf\r\u000b2\u000b\rR4h\u001e\u001f2\t\u0011z3\u0007F\u0019\u0006G}\u0002\u00150Q\u0019\u0005I=\u001aD\u0003C\u0003|\u000f\u0001\u000f1.A\u0001WQ\t9Q\u0010\u0005\u0002\u0013}&\u0011qp\u0005\u0002\u0007S:d\u0017N\\3\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0005\u0015\u0001\u0003BA\u0004\u0003\u001bi!!!\u0003\u000b\u0007\u0005-!-\u0001\u0003mC:<\u0017\u0002BA\b\u0003\u0013\u0011aa\u00142kK\u000e$\b"
)
public interface InnerProductSpace extends VectorSpace {
   static InnerProductSpace apply(final InnerProductSpace V) {
      return InnerProductSpace$.MODULE$.apply(V);
   }

   Object dot(final Object v, final Object w);

   // $FF: synthetic method
   static NormedVectorSpace normed$(final InnerProductSpace $this, final NRoot ev) {
      return $this.normed(ev);
   }

   default NormedVectorSpace normed(final NRoot ev) {
      return new NormedInnerProductSpace(ev) {
         // $FF: synthetic field
         private final InnerProductSpace $outer;
         private final NRoot ev$1;

         public InnerProductSpace space$mcD$sp() {
            return NormedInnerProductSpace.space$mcD$sp$(this);
         }

         public InnerProductSpace space$mcF$sp() {
            return NormedInnerProductSpace.space$mcF$sp$(this);
         }

         public Field scalar() {
            return NormedInnerProductSpace.scalar$(this);
         }

         public Field scalar$mcD$sp() {
            return NormedInnerProductSpace.scalar$mcD$sp$(this);
         }

         public Field scalar$mcF$sp() {
            return NormedInnerProductSpace.scalar$mcF$sp$(this);
         }

         public NRoot nroot$mcD$sp() {
            return NormedInnerProductSpace.nroot$mcD$sp$(this);
         }

         public NRoot nroot$mcF$sp() {
            return NormedInnerProductSpace.nroot$mcF$sp$(this);
         }

         public Object zero() {
            return NormedInnerProductSpace.zero$(this);
         }

         public Object plus(final Object v, final Object w) {
            return NormedInnerProductSpace.plus$(this, v, w);
         }

         public Object negate(final Object v) {
            return NormedInnerProductSpace.negate$(this, v);
         }

         public Object minus(final Object v, final Object w) {
            return NormedInnerProductSpace.minus$(this, v, w);
         }

         public Object timesl(final Object f, final Object v) {
            return NormedInnerProductSpace.timesl$(this, f, v);
         }

         public Object timesl$mcD$sp(final double f, final Object v) {
            return NormedInnerProductSpace.timesl$mcD$sp$(this, f, v);
         }

         public Object timesl$mcF$sp(final float f, final Object v) {
            return NormedInnerProductSpace.timesl$mcF$sp$(this, f, v);
         }

         public Object divr(final Object v, final Object f) {
            return NormedInnerProductSpace.divr$(this, v, f);
         }

         public Object divr$mcD$sp(final Object v, final double f) {
            return NormedInnerProductSpace.divr$mcD$sp$(this, v, f);
         }

         public Object divr$mcF$sp(final Object v, final float f) {
            return NormedInnerProductSpace.divr$mcF$sp$(this, v, f);
         }

         public Object norm(final Object v) {
            return NormedInnerProductSpace.norm$(this, v);
         }

         public double norm$mcD$sp(final Object v) {
            return NormedInnerProductSpace.norm$mcD$sp$(this, v);
         }

         public float norm$mcF$sp(final Object v) {
            return NormedInnerProductSpace.norm$mcF$sp$(this, v);
         }

         public int norm$mcI$sp(final Object v) {
            return NormedVectorSpace.norm$mcI$sp$(this, v);
         }

         public long norm$mcJ$sp(final Object v) {
            return NormedVectorSpace.norm$mcJ$sp$(this, v);
         }

         public Object normalize(final Object v) {
            return NormedVectorSpace.normalize$(this, v);
         }

         public Object distance(final Object v, final Object w) {
            return NormedVectorSpace.distance$(this, v, w);
         }

         public double distance$mcD$sp(final Object v, final Object w) {
            return NormedVectorSpace.distance$mcD$sp$(this, v, w);
         }

         public float distance$mcF$sp(final Object v, final Object w) {
            return NormedVectorSpace.distance$mcF$sp$(this, v, w);
         }

         public int distance$mcI$sp(final Object v, final Object w) {
            return NormedVectorSpace.distance$mcI$sp$(this, v, w);
         }

         public long distance$mcJ$sp(final Object v, final Object w) {
            return NormedVectorSpace.distance$mcJ$sp$(this, v, w);
         }

         public Field scalar$mcI$sp() {
            return VectorSpace.scalar$mcI$sp$(this);
         }

         public Field scalar$mcJ$sp() {
            return VectorSpace.scalar$mcJ$sp$(this);
         }

         public Object divr$mcI$sp(final Object v, final int f) {
            return VectorSpace.divr$mcI$sp$(this, v, f);
         }

         public Object divr$mcJ$sp(final Object v, final long f) {
            return VectorSpace.divr$mcJ$sp$(this, v, f);
         }

         public Object timesr(final Object v, final Object r) {
            return CModule.timesr$(this, v, r);
         }

         public Object timesr$mcD$sp(final Object v, final double r) {
            return CModule.timesr$mcD$sp$(this, v, r);
         }

         public Object timesr$mcF$sp(final Object v, final float r) {
            return CModule.timesr$mcF$sp$(this, v, r);
         }

         public Object timesr$mcI$sp(final Object v, final int r) {
            return CModule.timesr$mcI$sp$(this, v, r);
         }

         public Object timesr$mcJ$sp(final Object v, final long r) {
            return CModule.timesr$mcJ$sp$(this, v, r);
         }

         public Object timesl$mcI$sp(final int r, final Object v) {
            return LeftModule.timesl$mcI$sp$(this, r, v);
         }

         public Object timesl$mcJ$sp(final long r, final Object v) {
            return LeftModule.timesl$mcJ$sp$(this, r, v);
         }

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

         public InnerProductSpace space() {
            return this.$outer;
         }

         public NRoot nroot() {
            return this.ev$1;
         }

         public {
            if (InnerProductSpace.this == null) {
               throw null;
            } else {
               this.$outer = InnerProductSpace.this;
               this.ev$1 = ev$1;
               AdditiveSemigroup.$init$(this);
               AdditiveMonoid.$init$(this);
               AdditiveGroup.$init$(this);
               AdditiveCommutativeSemigroup.$init$(this);
               AdditiveCommutativeMonoid.$init$(this);
               AdditiveCommutativeGroup.$init$(this);
               CModule.$init$(this);
               VectorSpace.$init$(this);
               NormedVectorSpace.$init$(this);
               NormedInnerProductSpace.$init$(this);
            }
         }
      };
   }

   // $FF: synthetic method
   static double dot$mcD$sp$(final InnerProductSpace $this, final Object v, final Object w) {
      return $this.dot$mcD$sp(v, w);
   }

   default double dot$mcD$sp(final Object v, final Object w) {
      return BoxesRunTime.unboxToDouble(this.dot(v, w));
   }

   // $FF: synthetic method
   static float dot$mcF$sp$(final InnerProductSpace $this, final Object v, final Object w) {
      return $this.dot$mcF$sp(v, w);
   }

   default float dot$mcF$sp(final Object v, final Object w) {
      return BoxesRunTime.unboxToFloat(this.dot(v, w));
   }

   // $FF: synthetic method
   static int dot$mcI$sp$(final InnerProductSpace $this, final Object v, final Object w) {
      return $this.dot$mcI$sp(v, w);
   }

   default int dot$mcI$sp(final Object v, final Object w) {
      return BoxesRunTime.unboxToInt(this.dot(v, w));
   }

   // $FF: synthetic method
   static long dot$mcJ$sp$(final InnerProductSpace $this, final Object v, final Object w) {
      return $this.dot$mcJ$sp(v, w);
   }

   default long dot$mcJ$sp(final Object v, final Object w) {
      return BoxesRunTime.unboxToLong(this.dot(v, w));
   }

   // $FF: synthetic method
   static NormedVectorSpace normed$mcD$sp$(final InnerProductSpace $this, final NRoot ev) {
      return $this.normed$mcD$sp(ev);
   }

   default NormedVectorSpace normed$mcD$sp(final NRoot ev) {
      return this.normed(ev);
   }

   // $FF: synthetic method
   static NormedVectorSpace normed$mcF$sp$(final InnerProductSpace $this, final NRoot ev) {
      return $this.normed$mcF$sp(ev);
   }

   default NormedVectorSpace normed$mcF$sp(final NRoot ev) {
      return this.normed(ev);
   }

   // $FF: synthetic method
   static NormedVectorSpace normed$mcI$sp$(final InnerProductSpace $this, final NRoot ev) {
      return $this.normed$mcI$sp(ev);
   }

   default NormedVectorSpace normed$mcI$sp(final NRoot ev) {
      return this.normed(ev);
   }

   // $FF: synthetic method
   static NormedVectorSpace normed$mcJ$sp$(final InnerProductSpace $this, final NRoot ev) {
      return $this.normed$mcJ$sp(ev);
   }

   default NormedVectorSpace normed$mcJ$sp(final NRoot ev) {
      return this.normed(ev);
   }

   static void $init$(final InnerProductSpace $this) {
   }
}
