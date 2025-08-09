package algebra.ring;

import cats.kernel.Eq;
import cats.kernel.Group;
import cats.kernel.Monoid;
import cats.kernel.Semigroup;
import scala.Option;
import scala.collection.IterableOnce;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005%ba\u0002\u0007\u000e!\u0003\r\tA\u0005\u0005\u0006\u0007\u0002!\t\u0001\u0012\u0005\u0006\u0011\u0002!\t%\u0013\u0005\u0006%\u0002!\ta\u0015\u0005\u0006-\u00021\ta\u0016\u0005\u00067\u0002!\t\u0005X\u0004\u0006I6A\t!\u001a\u0004\u0006\u00195A\tA\u001a\u0005\u0006m\u001e!\ta\u001e\u0005\u0006q\u001e!)!\u001f\u0005\u0007\u0011\u001e!)!!\u0003\t\u0013\u0005eq!!A\u0005\n\u0005m!aE'vYRL\u0007\u000f\\5dCRLg/Z$s_V\u0004(B\u0001\b\u0010\u0003\u0011\u0011\u0018N\\4\u000b\u0003A\tq!\u00197hK\n\u0014\u0018m\u0001\u0001\u0016\u0005M\u00013c\u0001\u0001\u00155A\u0011Q\u0003G\u0007\u0002-)\tq#A\u0003tG\u0006d\u0017-\u0003\u0002\u001a-\t\u0019\u0011I\\=\u0011\u0007mab$D\u0001\u000e\u0013\tiRB\u0001\u000bNk2$\u0018\u000e\u001d7jG\u0006$\u0018N^3N_:|\u0017\u000e\u001a\t\u0003?\u0001b\u0001\u0001B\u0005\"\u0001\u0001\u0006\t\u0011!b\u0001E\t\t\u0011)\u0005\u0002$)A\u0011Q\u0003J\u0005\u0003KY\u0011qAT8uQ&tw\r\u000b\u0004!O)\"\u0014H\u0010\t\u0003+!J!!\u000b\f\u0003\u0017M\u0004XmY5bY&TX\rZ\u0019\u0006G-bc&\f\b\u0003+1J!!\f\f\u0002\u0007%sG/\r\u0003%_M:bB\u0001\u00194\u001b\u0005\t$B\u0001\u001a\u0012\u0003\u0019a$o\\8u}%\tq#M\u0003$kYBtG\u0004\u0002\u0016m%\u0011qGF\u0001\u0005\u0019>tw-\r\u0003%_M:\u0012'B\u0012;wubdBA\u000b<\u0013\tad#A\u0003GY>\fG/\r\u0003%_M:\u0012'B\u0012@\u0001\n\u000beBA\u000bA\u0013\t\te#\u0001\u0004E_V\u0014G.Z\u0019\u0005I=\u001at#\u0001\u0004%S:LG\u000f\n\u000b\u0002\u000bB\u0011QCR\u0005\u0003\u000fZ\u0011A!\u00168ji\u0006qQ.\u001e7uSBd\u0017nY1uSZ,W#\u0001&\u0011\u0007-{eD\u0004\u0002M\u001b6\tq\"\u0003\u0002O\u001f\u00059\u0001/Y2lC\u001e,\u0017B\u0001)R\u0005\u00159%o\\;q\u0015\tqu\"\u0001\u0006sK\u000eL\u0007O]8dC2$\"A\b+\t\u000bU\u001b\u0001\u0019\u0001\u0010\u0002\u0003a\f1\u0001Z5w)\rq\u0002,\u0017\u0005\u0006+\u0012\u0001\rA\b\u0005\u00065\u0012\u0001\rAH\u0001\u0002s\u0006\u0019\u0001o\\<\u0015\u0007yiv\fC\u0003_\u000b\u0001\u0007a$A\u0001b\u0011\u0015\u0001W\u00011\u0001b\u0003\u0005q\u0007CA\u000bc\u0013\t\u0019gCA\u0002J]R\f1#T;mi&\u0004H.[2bi&4Xm\u0012:pkB\u0004\"aG\u0004\u0014\t\u001d9'N\u001c\t\u0003+!L!!\u001b\f\u0003\r\u0005s\u0017PU3g!\rY2.\\\u0005\u0003Y6\u0011A$T;mi&\u0004H.[2bi&4Xm\u0012:pkB4UO\\2uS>t7\u000f\u0005\u0002\u001c\u0001A\u0011q\u000e^\u0007\u0002a*\u0011\u0011O]\u0001\u0003S>T\u0011a]\u0001\u0005U\u00064\u0018-\u0003\u0002va\na1+\u001a:jC2L'0\u00192mK\u00061A(\u001b8jiz\"\u0012!Z\u0001\u0006CB\u0004H._\u000b\u0003uv$\"a\u001f@\u0011\u0007m\u0001A\u0010\u0005\u0002 {\u0012)\u0011%\u0003b\u0001E!)q0\u0003a\u0002w\u0006\u0011QM\u001e\u0015\u0004\u0013\u0005\r\u0001cA\u000b\u0002\u0006%\u0019\u0011q\u0001\f\u0003\r%tG.\u001b8f+\u0011\tY!!\u0005\u0015\t\u00055\u00111\u0003\t\u0005\u0017>\u000by\u0001E\u0002 \u0003#!Q!\t\u0006C\u0002\tBaa \u0006A\u0004\u0005U\u0001\u0003B\u000e\u0001\u0003\u001fA3ACA\u0002\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\ti\u0002\u0005\u0003\u0002 \u0005\u0015RBAA\u0011\u0015\r\t\u0019C]\u0001\u0005Y\u0006tw-\u0003\u0003\u0002(\u0005\u0005\"AB(cU\u0016\u001cG\u000f"
)
public interface MultiplicativeGroup extends MultiplicativeMonoid {
   static MultiplicativeGroup apply(final MultiplicativeGroup ev) {
      return MultiplicativeGroup$.MODULE$.apply(ev);
   }

   static boolean isMultiplicativeCommutative(final MultiplicativeSemigroup ev) {
      return MultiplicativeGroup$.MODULE$.isMultiplicativeCommutative(ev);
   }

   // $FF: synthetic method
   static Group multiplicative$(final MultiplicativeGroup $this) {
      return $this.multiplicative();
   }

   default Group multiplicative() {
      return new Group() {
         // $FF: synthetic field
         private final MultiplicativeGroup $outer;

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

         public Monoid reverse() {
            return Monoid.reverse$(this);
         }

         public Monoid reverse$mcD$sp() {
            return Monoid.reverse$mcD$sp$(this);
         }

         public Monoid reverse$mcF$sp() {
            return Monoid.reverse$mcF$sp$(this);
         }

         public Monoid reverse$mcI$sp() {
            return Monoid.reverse$mcI$sp$(this);
         }

         public Monoid reverse$mcJ$sp() {
            return Monoid.reverse$mcJ$sp$(this);
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

         public Semigroup intercalate(final Object middle) {
            return Semigroup.intercalate$(this, middle);
         }

         public Semigroup intercalate$mcD$sp(final double middle) {
            return Semigroup.intercalate$mcD$sp$(this, middle);
         }

         public Semigroup intercalate$mcF$sp(final float middle) {
            return Semigroup.intercalate$mcF$sp$(this, middle);
         }

         public Semigroup intercalate$mcI$sp(final int middle) {
            return Semigroup.intercalate$mcI$sp$(this, middle);
         }

         public Semigroup intercalate$mcJ$sp(final long middle) {
            return Semigroup.intercalate$mcJ$sp$(this, middle);
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
            if (MultiplicativeGroup.this == null) {
               throw null;
            } else {
               this.$outer = MultiplicativeGroup.this;
               Semigroup.$init$(this);
               Monoid.$init$(this);
               Group.$init$(this);
            }
         }
      };
   }

   // $FF: synthetic method
   static Object reciprocal$(final MultiplicativeGroup $this, final Object x) {
      return $this.reciprocal(x);
   }

   default Object reciprocal(final Object x) {
      return this.div(this.one(), x);
   }

   Object div(final Object x, final Object y);

   // $FF: synthetic method
   static Object pow$(final MultiplicativeGroup $this, final Object a, final int n) {
      return $this.pow(a, n);
   }

   default Object pow(final Object a, final int n) {
      return n > 0 ? this.positivePow(a, n) : (n == 0 ? this.one() : (n == Integer.MIN_VALUE ? this.positivePow(this.reciprocal(this.times(a, a)), 1073741824) : this.positivePow(this.reciprocal(a), -n)));
   }

   // $FF: synthetic method
   static Group multiplicative$mcD$sp$(final MultiplicativeGroup $this) {
      return $this.multiplicative$mcD$sp();
   }

   default Group multiplicative$mcD$sp() {
      return this.multiplicative();
   }

   // $FF: synthetic method
   static Group multiplicative$mcF$sp$(final MultiplicativeGroup $this) {
      return $this.multiplicative$mcF$sp();
   }

   default Group multiplicative$mcF$sp() {
      return this.multiplicative();
   }

   // $FF: synthetic method
   static Group multiplicative$mcI$sp$(final MultiplicativeGroup $this) {
      return $this.multiplicative$mcI$sp();
   }

   default Group multiplicative$mcI$sp() {
      return this.multiplicative();
   }

   // $FF: synthetic method
   static Group multiplicative$mcJ$sp$(final MultiplicativeGroup $this) {
      return $this.multiplicative$mcJ$sp();
   }

   default Group multiplicative$mcJ$sp() {
      return this.multiplicative();
   }

   // $FF: synthetic method
   static double reciprocal$mcD$sp$(final MultiplicativeGroup $this, final double x) {
      return $this.reciprocal$mcD$sp(x);
   }

   default double reciprocal$mcD$sp(final double x) {
      return BoxesRunTime.unboxToDouble(this.reciprocal(BoxesRunTime.boxToDouble(x)));
   }

   // $FF: synthetic method
   static float reciprocal$mcF$sp$(final MultiplicativeGroup $this, final float x) {
      return $this.reciprocal$mcF$sp(x);
   }

   default float reciprocal$mcF$sp(final float x) {
      return BoxesRunTime.unboxToFloat(this.reciprocal(BoxesRunTime.boxToFloat(x)));
   }

   // $FF: synthetic method
   static int reciprocal$mcI$sp$(final MultiplicativeGroup $this, final int x) {
      return $this.reciprocal$mcI$sp(x);
   }

   default int reciprocal$mcI$sp(final int x) {
      return BoxesRunTime.unboxToInt(this.reciprocal(BoxesRunTime.boxToInteger(x)));
   }

   // $FF: synthetic method
   static long reciprocal$mcJ$sp$(final MultiplicativeGroup $this, final long x) {
      return $this.reciprocal$mcJ$sp(x);
   }

   default long reciprocal$mcJ$sp(final long x) {
      return BoxesRunTime.unboxToLong(this.reciprocal(BoxesRunTime.boxToLong(x)));
   }

   // $FF: synthetic method
   static double div$mcD$sp$(final MultiplicativeGroup $this, final double x, final double y) {
      return $this.div$mcD$sp(x, y);
   }

   default double div$mcD$sp(final double x, final double y) {
      return BoxesRunTime.unboxToDouble(this.div(BoxesRunTime.boxToDouble(x), BoxesRunTime.boxToDouble(y)));
   }

   // $FF: synthetic method
   static float div$mcF$sp$(final MultiplicativeGroup $this, final float x, final float y) {
      return $this.div$mcF$sp(x, y);
   }

   default float div$mcF$sp(final float x, final float y) {
      return BoxesRunTime.unboxToFloat(this.div(BoxesRunTime.boxToFloat(x), BoxesRunTime.boxToFloat(y)));
   }

   // $FF: synthetic method
   static int div$mcI$sp$(final MultiplicativeGroup $this, final int x, final int y) {
      return $this.div$mcI$sp(x, y);
   }

   default int div$mcI$sp(final int x, final int y) {
      return BoxesRunTime.unboxToInt(this.div(BoxesRunTime.boxToInteger(x), BoxesRunTime.boxToInteger(y)));
   }

   // $FF: synthetic method
   static long div$mcJ$sp$(final MultiplicativeGroup $this, final long x, final long y) {
      return $this.div$mcJ$sp(x, y);
   }

   default long div$mcJ$sp(final long x, final long y) {
      return BoxesRunTime.unboxToLong(this.div(BoxesRunTime.boxToLong(x), BoxesRunTime.boxToLong(y)));
   }

   // $FF: synthetic method
   static double pow$mcD$sp$(final MultiplicativeGroup $this, final double a, final int n) {
      return $this.pow$mcD$sp(a, n);
   }

   default double pow$mcD$sp(final double a, final int n) {
      return BoxesRunTime.unboxToDouble(this.pow(BoxesRunTime.boxToDouble(a), n));
   }

   // $FF: synthetic method
   static float pow$mcF$sp$(final MultiplicativeGroup $this, final float a, final int n) {
      return $this.pow$mcF$sp(a, n);
   }

   default float pow$mcF$sp(final float a, final int n) {
      return BoxesRunTime.unboxToFloat(this.pow(BoxesRunTime.boxToFloat(a), n));
   }

   // $FF: synthetic method
   static int pow$mcI$sp$(final MultiplicativeGroup $this, final int a, final int n) {
      return $this.pow$mcI$sp(a, n);
   }

   default int pow$mcI$sp(final int a, final int n) {
      return BoxesRunTime.unboxToInt(this.pow(BoxesRunTime.boxToInteger(a), n));
   }

   // $FF: synthetic method
   static long pow$mcJ$sp$(final MultiplicativeGroup $this, final long a, final int n) {
      return $this.pow$mcJ$sp(a, n);
   }

   default long pow$mcJ$sp(final long a, final int n) {
      return BoxesRunTime.unboxToLong(this.pow(BoxesRunTime.boxToLong(a), n));
   }

   static void $init$(final MultiplicativeGroup $this) {
   }
}
