package algebra.ring;

import cats.kernel.CommutativeMonoid;
import cats.kernel.CommutativeSemigroup;
import cats.kernel.Eq;
import cats.kernel.Monoid;
import cats.kernel.Semigroup;
import scala.Option;
import scala.collection.IterableOnce;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015aaB\u0005\u000b!\u0003\r\ta\u0004\u0005\u0006\u0007\u0002!\t\u0001\u0012\u0005\u0006\u0011\u0002!\t%S\u0004\u0006%*A\ta\u0015\u0004\u0006\u0013)A\t\u0001\u0016\u0005\u0006I\u0012!\t!\u001a\u0005\u0006M\u0012!)a\u001a\u0005\u0006\u0011\u0012!)A\u001d\u0005\bu\u0012\t\t\u0011\"\u0003|\u0005e\tE\rZ5uSZ,7i\\7nkR\fG/\u001b<f\u001b>tw.\u001b3\u000b\u0005-a\u0011\u0001\u0002:j]\u001eT\u0011!D\u0001\bC2<WM\u0019:b\u0007\u0001)\"\u0001E\u000f\u0014\t\u0001\tr\u0003\u0011\t\u0003%Ui\u0011a\u0005\u0006\u0002)\u0005)1oY1mC&\u0011ac\u0005\u0002\u0004\u0003:L\bc\u0001\r\u001a75\t!\"\u0003\u0002\u001b\u0015\tq\u0011\t\u001a3ji&4X-T8o_&$\u0007C\u0001\u000f\u001e\u0019\u0001!\u0011B\b\u0001!\u0002\u0003\u0005)\u0019A\u0010\u0003\u0003\u0005\u000b\"\u0001I\t\u0011\u0005I\t\u0013B\u0001\u0012\u0014\u0005\u001dqu\u000e\u001e5j]\u001eDc!\b\u0013(cYZ\u0004C\u0001\n&\u0013\t13CA\u0006ta\u0016\u001c\u0017.\u00197ju\u0016$\u0017'B\u0012)S-RcB\u0001\n*\u0013\tQ3#A\u0002J]R\fD\u0001\n\u00171)9\u0011Q\u0006M\u0007\u0002])\u0011qFD\u0001\u0007yI|w\u000e\u001e \n\u0003Q\tTa\t\u001a4kQr!AE\u001a\n\u0005Q\u001a\u0012\u0001\u0002'p]\u001e\fD\u0001\n\u00171)E*1e\u000e\u001d;s9\u0011!\u0003O\u0005\u0003sM\tQA\u00127pCR\fD\u0001\n\u00171)E*1\u0005P\u001f@}9\u0011!#P\u0005\u0003}M\ta\u0001R8vE2,\u0017\u0007\u0002\u0013-aQ\u00012\u0001G!\u001c\u0013\t\u0011%B\u0001\u000fBI\u0012LG/\u001b<f\u0007>lW.\u001e;bi&4XmU3nS\u001e\u0014x.\u001e9\u0002\r\u0011Jg.\u001b;%)\u0005)\u0005C\u0001\nG\u0013\t95C\u0001\u0003V]&$\u0018\u0001C1eI&$\u0018N^3\u0016\u0003)\u00032aS(\u001c\u001d\taU*D\u0001\r\u0013\tqE\"A\u0004qC\u000e\\\u0017mZ3\n\u0005A\u000b&!E\"p[6,H/\u0019;jm\u0016luN\\8jI*\u0011a\nD\u0001\u001a\u0003\u0012$\u0017\u000e^5wK\u000e{W.\\;uCRLg/Z'p]>LG\r\u0005\u0002\u0019\tM!A!\u0016-]!\t\u0011b+\u0003\u0002X'\t1\u0011I\\=SK\u001a\u00042\u0001G-\\\u0013\tQ&BA\fBI\u0012LG/\u001b<f\u001b>tw.\u001b3Gk:\u001cG/[8ogB\u0011\u0001\u0004\u0001\t\u0003;\nl\u0011A\u0018\u0006\u0003?\u0002\f!![8\u000b\u0003\u0005\fAA[1wC&\u00111M\u0018\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.Z\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003M\u000bQ!\u00199qYf,\"\u0001[6\u0015\u0005%d\u0007c\u0001\r\u0001UB\u0011Ad\u001b\u0003\u0006=\u0019\u0011\ra\b\u0005\u0006[\u001a\u0001\u001d![\u0001\u0003KZD#AB8\u0011\u0005I\u0001\u0018BA9\u0014\u0005\u0019Ig\u000e\\5oKV\u00111O\u001e\u000b\u0003i^\u00042aS(v!\tab\u000fB\u0003\u001f\u000f\t\u0007q\u0004C\u0003n\u000f\u0001\u000f\u0001\u0010E\u0002\u0019\u0001UD#aB8\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0003q\u00042!`A\u0001\u001b\u0005q(BA@a\u0003\u0011a\u0017M\\4\n\u0007\u0005\raP\u0001\u0004PE*,7\r\u001e"
)
public interface AdditiveCommutativeMonoid extends AdditiveMonoid, AdditiveCommutativeSemigroup {
   static AdditiveCommutativeMonoid apply(final AdditiveCommutativeMonoid ev) {
      return AdditiveCommutativeMonoid$.MODULE$.apply(ev);
   }

   static boolean isAdditiveCommutative(final AdditiveSemigroup ev) {
      return AdditiveCommutativeMonoid$.MODULE$.isAdditiveCommutative(ev);
   }

   // $FF: synthetic method
   static CommutativeMonoid additive$(final AdditiveCommutativeMonoid $this) {
      return $this.additive();
   }

   default CommutativeMonoid additive() {
      return new CommutativeMonoid() {
         // $FF: synthetic field
         private final AdditiveCommutativeMonoid $outer;

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

         public Object combineN(final Object a, final int n) {
            return Monoid.combineN$(this, a, n);
         }

         public double combineN$mcD$sp(final double a, final int n) {
            return Monoid.combineN$mcD$sp$(this, a, n);
         }

         public float combineN$mcF$sp(final float a, final int n) {
            return Monoid.combineN$mcF$sp$(this, a, n);
         }

         public int combineN$mcI$sp(final int a, final int n) {
            return Monoid.combineN$mcI$sp$(this, a, n);
         }

         public long combineN$mcJ$sp(final long a, final int n) {
            return Monoid.combineN$mcJ$sp$(this, a, n);
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
            return this.$outer.zero();
         }

         public Object combine(final Object x, final Object y) {
            return this.$outer.plus(x, y);
         }

         public Option combineAllOption(final IterableOnce as) {
            return this.$outer.trySum(as);
         }

         public Object combineAll(final IterableOnce as) {
            return this.$outer.sum(as);
         }

         public {
            if (AdditiveCommutativeMonoid.this == null) {
               throw null;
            } else {
               this.$outer = AdditiveCommutativeMonoid.this;
               Semigroup.$init$(this);
               Monoid.$init$(this);
               CommutativeSemigroup.$init$(this);
               CommutativeMonoid.$init$(this);
            }
         }
      };
   }

   // $FF: synthetic method
   static CommutativeMonoid additive$mcD$sp$(final AdditiveCommutativeMonoid $this) {
      return $this.additive$mcD$sp();
   }

   default CommutativeMonoid additive$mcD$sp() {
      return this.additive();
   }

   // $FF: synthetic method
   static CommutativeMonoid additive$mcF$sp$(final AdditiveCommutativeMonoid $this) {
      return $this.additive$mcF$sp();
   }

   default CommutativeMonoid additive$mcF$sp() {
      return this.additive();
   }

   // $FF: synthetic method
   static CommutativeMonoid additive$mcI$sp$(final AdditiveCommutativeMonoid $this) {
      return $this.additive$mcI$sp();
   }

   default CommutativeMonoid additive$mcI$sp() {
      return this.additive();
   }

   // $FF: synthetic method
   static CommutativeMonoid additive$mcJ$sp$(final AdditiveCommutativeMonoid $this) {
      return $this.additive$mcJ$sp();
   }

   default CommutativeMonoid additive$mcJ$sp() {
      return this.additive();
   }

   static void $init$(final AdditiveCommutativeMonoid $this) {
   }
}
