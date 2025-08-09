package algebra.ring;

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

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015aaB\u0005\u000b!\u0003\r\ta\u0004\u0005\u0006\u0007\u0002!\t\u0001\u0012\u0005\u0006\u0011\u0002!\t%S\u0004\u0006%*A\ta\u0015\u0004\u0006\u0013)A\t\u0001\u0016\u0005\u0006I\u0012!\t!\u001a\u0005\u0006M\u0012!)a\u001a\u0005\u0006\u0011\u0012!)A\u001d\u0005\bu\u0012\t\t\u0011\"\u0003|\u0005yiU\u000f\u001c;ja2L7-\u0019;jm\u0016\u001cu.\\7vi\u0006$\u0018N^3He>,\bO\u0003\u0002\f\u0019\u0005!!/\u001b8h\u0015\u0005i\u0011aB1mO\u0016\u0014'/Y\u0002\u0001+\t\u0001Rd\u0005\u0003\u0001#]\u0001\u0005C\u0001\n\u0016\u001b\u0005\u0019\"\"\u0001\u000b\u0002\u000bM\u001c\u0017\r\\1\n\u0005Y\u0019\"aA!osB\u0019\u0001$G\u000e\u000e\u0003)I!A\u0007\u0006\u0003'5+H\u000e^5qY&\u001c\u0017\r^5wK\u001e\u0013x.\u001e9\u0011\u0005qiB\u0002\u0001\u0003\n=\u0001\u0001\u000b\u0011!AC\u0002}\u0011\u0011!Q\t\u0003AE\u0001\"AE\u0011\n\u0005\t\u001a\"a\u0002(pi\"Lgn\u001a\u0015\u0007;\u0011:\u0013GN\u001e\u0011\u0005I)\u0013B\u0001\u0014\u0014\u0005-\u0019\b/Z2jC2L'0\u001a32\u000b\rB\u0013f\u000b\u0016\u000f\u0005II\u0013B\u0001\u0016\u0014\u0003\rIe\u000e^\u0019\u0005I1\u0002DC\u0004\u0002.a5\taF\u0003\u00020\u001d\u00051AH]8pizJ\u0011\u0001F\u0019\u0006GI\u001aT\u0007\u000e\b\u0003%MJ!\u0001N\n\u0002\t1{gnZ\u0019\u0005I1\u0002D#M\u0003$oaR\u0014H\u0004\u0002\u0013q%\u0011\u0011hE\u0001\u0006\r2|\u0017\r^\u0019\u0005I1\u0002D#M\u0003$yuzdH\u0004\u0002\u0013{%\u0011ahE\u0001\u0007\t>,(\r\\32\t\u0011b\u0003\u0007\u0006\t\u00041\u0005[\u0012B\u0001\"\u000b\u0005}iU\u000f\u001c;ja2L7-\u0019;jm\u0016\u001cu.\\7vi\u0006$\u0018N^3N_:|\u0017\u000eZ\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003\u0015\u0003\"A\u0005$\n\u0005\u001d\u001b\"\u0001B+oSR\fa\"\\;mi&\u0004H.[2bi&4X-F\u0001K!\rYuj\u0007\b\u0003\u00196k\u0011\u0001D\u0005\u0003\u001d2\tq\u0001]1dW\u0006<W-\u0003\u0002Q#\n\u00012i\\7nkR\fG/\u001b<f\u000fJ|W\u000f\u001d\u0006\u0003\u001d2\ta$T;mi&\u0004H.[2bi&4XmQ8n[V$\u0018\r^5wK\u001e\u0013x.\u001e9\u0011\u0005a!1\u0003\u0002\u0003V1r\u0003\"A\u0005,\n\u0005]\u001b\"AB!osJ+g\rE\u0002\u00193nK!A\u0017\u0006\u000395+H\u000e^5qY&\u001c\u0017\r^5wK\u001e\u0013x.\u001e9Gk:\u001cG/[8ogB\u0011\u0001\u0004\u0001\t\u0003;\nl\u0011A\u0018\u0006\u0003?\u0002\f!![8\u000b\u0003\u0005\fAA[1wC&\u00111M\u0018\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.Z\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003M\u000bQ!\u00199qYf,\"\u0001[6\u0015\u0005%d\u0007c\u0001\r\u0001UB\u0011Ad\u001b\u0003\u0006=\u0019\u0011\ra\b\u0005\u0006[\u001a\u0001\u001d![\u0001\u0003KZD#AB8\u0011\u0005I\u0001\u0018BA9\u0014\u0005\u0019Ig\u000e\\5oKV\u00111O\u001e\u000b\u0003i^\u00042aS(v!\tab\u000fB\u0003\u001f\u000f\t\u0007q\u0004C\u0003n\u000f\u0001\u000f\u0001\u0010E\u0002\u0019\u0001UD#aB8\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0003q\u00042!`A\u0001\u001b\u0005q(BA@a\u0003\u0011a\u0017M\\4\n\u0007\u0005\raP\u0001\u0004PE*,7\r\u001e"
)
public interface MultiplicativeCommutativeGroup extends MultiplicativeGroup, MultiplicativeCommutativeMonoid {
   static MultiplicativeCommutativeGroup apply(final MultiplicativeCommutativeGroup ev) {
      return MultiplicativeCommutativeGroup$.MODULE$.apply(ev);
   }

   static boolean isMultiplicativeCommutative(final MultiplicativeSemigroup ev) {
      return MultiplicativeCommutativeGroup$.MODULE$.isMultiplicativeCommutative(ev);
   }

   // $FF: synthetic method
   static CommutativeGroup multiplicative$(final MultiplicativeCommutativeGroup $this) {
      return $this.multiplicative();
   }

   default CommutativeGroup multiplicative() {
      return new CommutativeGroup() {
         // $FF: synthetic field
         private final MultiplicativeCommutativeGroup $outer;

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
            return this.$outer.one();
         }

         public Object combine(final Object x, final Object y) {
            return this.$outer.times(x, y);
         }

         public Object remove(final Object x, final Object y) {
            return this.$outer.div(x, y);
         }

         public Object inverse(final Object x) {
            return this.$outer.reciprocal(x);
         }

         public {
            if (MultiplicativeCommutativeGroup.this == null) {
               throw null;
            } else {
               this.$outer = MultiplicativeCommutativeGroup.this;
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
   static CommutativeGroup multiplicative$mcD$sp$(final MultiplicativeCommutativeGroup $this) {
      return $this.multiplicative$mcD$sp();
   }

   default CommutativeGroup multiplicative$mcD$sp() {
      return this.multiplicative();
   }

   // $FF: synthetic method
   static CommutativeGroup multiplicative$mcF$sp$(final MultiplicativeCommutativeGroup $this) {
      return $this.multiplicative$mcF$sp();
   }

   default CommutativeGroup multiplicative$mcF$sp() {
      return this.multiplicative();
   }

   // $FF: synthetic method
   static CommutativeGroup multiplicative$mcI$sp$(final MultiplicativeCommutativeGroup $this) {
      return $this.multiplicative$mcI$sp();
   }

   default CommutativeGroup multiplicative$mcI$sp() {
      return this.multiplicative();
   }

   // $FF: synthetic method
   static CommutativeGroup multiplicative$mcJ$sp$(final MultiplicativeCommutativeGroup $this) {
      return $this.multiplicative$mcJ$sp();
   }

   default CommutativeGroup multiplicative$mcJ$sp() {
      return this.multiplicative();
   }

   static void $init$(final MultiplicativeCommutativeGroup $this) {
   }
}
