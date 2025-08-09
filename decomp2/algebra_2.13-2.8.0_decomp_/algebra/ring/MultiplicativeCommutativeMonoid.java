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
   bytes = "\u0006\u0005\u0005\u0015aaB\u0005\u000b!\u0003\r\ta\u0004\u0005\u0006\u0007\u0002!\t\u0001\u0012\u0005\u0006\u0011\u0002!\t%S\u0004\u0006%*A\ta\u0015\u0004\u0006\u0013)A\t\u0001\u0016\u0005\u0006I\u0012!\t!\u001a\u0005\u0006M\u0012!)a\u001a\u0005\u0006\u0011\u0012!)A\u001d\u0005\bu\u0012\t\t\u0011\"\u0003|\u0005}iU\u000f\u001c;ja2L7-\u0019;jm\u0016\u001cu.\\7vi\u0006$\u0018N^3N_:|\u0017\u000e\u001a\u0006\u0003\u00171\tAA]5oO*\tQ\"A\u0004bY\u001e,'M]1\u0004\u0001U\u0011\u0001#H\n\u0005\u0001E9\u0002\t\u0005\u0002\u0013+5\t1CC\u0001\u0015\u0003\u0015\u00198-\u00197b\u0013\t12CA\u0002B]f\u00042\u0001G\r\u001c\u001b\u0005Q\u0011B\u0001\u000e\u000b\u0005QiU\u000f\u001c;ja2L7-\u0019;jm\u0016luN\\8jIB\u0011A$\b\u0007\u0001\t%q\u0002\u0001)A\u0001\u0002\u000b\u0007qDA\u0001B#\t\u0001\u0013\u0003\u0005\u0002\u0013C%\u0011!e\u0005\u0002\b\u001d>$\b.\u001b8hQ\u0019iBeJ\u00197wA\u0011!#J\u0005\u0003MM\u00111b\u001d9fG&\fG.\u001b>fIF*1\u0005K\u0015,U9\u0011!#K\u0005\u0003UM\t1!\u00138uc\u0011!C\u0006\r\u000b\u000f\u00055\u0002T\"\u0001\u0018\u000b\u0005=r\u0011A\u0002\u001fs_>$h(C\u0001\u0015c\u0015\u0019#gM\u001b5\u001d\t\u00112'\u0003\u00025'\u0005!Aj\u001c8hc\u0011!C\u0006\r\u000b2\u000b\r:\u0004HO\u001d\u000f\u0005IA\u0014BA\u001d\u0014\u0003\u00151En\\1uc\u0011!C\u0006\r\u000b2\u000b\rbTh\u0010 \u000f\u0005Ii\u0014B\u0001 \u0014\u0003\u0019!u.\u001e2mKF\"A\u0005\f\u0019\u0015!\rA\u0012iG\u0005\u0003\u0005*\u0011!%T;mi&\u0004H.[2bi&4XmQ8n[V$\u0018\r^5wKN+W.[4s_V\u0004\u0018A\u0002\u0013j]&$H\u0005F\u0001F!\t\u0011b)\u0003\u0002H'\t!QK\\5u\u00039iW\u000f\u001c;ja2L7-\u0019;jm\u0016,\u0012A\u0013\t\u0004\u0017>[bB\u0001'N\u001b\u0005a\u0011B\u0001(\r\u0003\u001d\u0001\u0018mY6bO\u0016L!\u0001U)\u0003#\r{W.\\;uCRLg/Z'p]>LGM\u0003\u0002O\u0019\u0005yR*\u001e7uSBd\u0017nY1uSZ,7i\\7nkR\fG/\u001b<f\u001b>tw.\u001b3\u0011\u0005a!1\u0003\u0002\u0003V1r\u0003\"A\u0005,\n\u0005]\u001b\"AB!osJ+g\rE\u0002\u00193nK!A\u0017\u0006\u0003;5+H\u000e^5qY&\u001c\u0017\r^5wK6{gn\\5e\rVt7\r^5p]N\u0004\"\u0001\u0007\u0001\u0011\u0005u\u0013W\"\u00010\u000b\u0005}\u0003\u0017AA5p\u0015\u0005\t\u0017\u0001\u00026bm\u0006L!a\u00190\u0003\u0019M+'/[1mSj\f'\r\\3\u0002\rqJg.\u001b;?)\u0005\u0019\u0016!B1qa2LXC\u00015l)\tIG\u000eE\u0002\u0019\u0001)\u0004\"\u0001H6\u0005\u000by1!\u0019A\u0010\t\u000b54\u00019A5\u0002\u0005\u00154\bF\u0001\u0004p!\t\u0011\u0002/\u0003\u0002r'\t1\u0011N\u001c7j]\u0016,\"a\u001d<\u0015\u0005Q<\bcA&PkB\u0011AD\u001e\u0003\u0006=\u001d\u0011\ra\b\u0005\u0006[\u001e\u0001\u001d\u0001\u001f\t\u00041\u0001)\bFA\u0004p\u000319(/\u001b;f%\u0016\u0004H.Y2f)\u0005a\bcA?\u0002\u00025\taP\u0003\u0002\u0000A\u0006!A.\u00198h\u0013\r\t\u0019A \u0002\u0007\u001f\nTWm\u0019;"
)
public interface MultiplicativeCommutativeMonoid extends MultiplicativeMonoid, MultiplicativeCommutativeSemigroup {
   static MultiplicativeCommutativeMonoid apply(final MultiplicativeCommutativeMonoid ev) {
      return MultiplicativeCommutativeMonoid$.MODULE$.apply(ev);
   }

   static boolean isMultiplicativeCommutative(final MultiplicativeSemigroup ev) {
      return MultiplicativeCommutativeMonoid$.MODULE$.isMultiplicativeCommutative(ev);
   }

   // $FF: synthetic method
   static CommutativeMonoid multiplicative$(final MultiplicativeCommutativeMonoid $this) {
      return $this.multiplicative();
   }

   default CommutativeMonoid multiplicative() {
      return new CommutativeMonoid() {
         // $FF: synthetic field
         private final MultiplicativeCommutativeMonoid $outer;

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

         public {
            if (MultiplicativeCommutativeMonoid.this == null) {
               throw null;
            } else {
               this.$outer = MultiplicativeCommutativeMonoid.this;
               Semigroup.$init$(this);
               Monoid.$init$(this);
               CommutativeSemigroup.$init$(this);
               CommutativeMonoid.$init$(this);
            }
         }
      };
   }

   // $FF: synthetic method
   static CommutativeMonoid multiplicative$mcD$sp$(final MultiplicativeCommutativeMonoid $this) {
      return $this.multiplicative$mcD$sp();
   }

   default CommutativeMonoid multiplicative$mcD$sp() {
      return this.multiplicative();
   }

   // $FF: synthetic method
   static CommutativeMonoid multiplicative$mcF$sp$(final MultiplicativeCommutativeMonoid $this) {
      return $this.multiplicative$mcF$sp();
   }

   default CommutativeMonoid multiplicative$mcF$sp() {
      return this.multiplicative();
   }

   // $FF: synthetic method
   static CommutativeMonoid multiplicative$mcI$sp$(final MultiplicativeCommutativeMonoid $this) {
      return $this.multiplicative$mcI$sp();
   }

   default CommutativeMonoid multiplicative$mcI$sp() {
      return this.multiplicative();
   }

   // $FF: synthetic method
   static CommutativeMonoid multiplicative$mcJ$sp$(final MultiplicativeCommutativeMonoid $this) {
      return $this.multiplicative$mcJ$sp();
   }

   default CommutativeMonoid multiplicative$mcJ$sp() {
      return this.multiplicative();
   }

   static void $init$(final MultiplicativeCommutativeMonoid $this) {
   }
}
