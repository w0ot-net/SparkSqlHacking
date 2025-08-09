package spire.algebra;

import algebra.ring.MultiplicativeCommutativeGroup;
import algebra.ring.MultiplicativeCommutativeMonoid;
import algebra.ring.MultiplicativeCommutativeSemigroup;
import algebra.ring.MultiplicativeGroup;
import algebra.ring.MultiplicativeMonoid;
import algebra.ring.MultiplicativeSemigroup;
import cats.kernel.CommutativeGroup;
import cats.kernel.Eq;
import scala.Option;
import scala.collection.IterableOnce;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005m4q!\u0003\u0006\u0011\u0002\u0007\u0005q\u0002C\u0003D\u0001\u0011\u0005A\tC\u0003I\u0001\u0019\r\u0011\nC\u0003R\u0001\u0019\u0005!\u000bC\u0003X\u0001\u0011\u0005\u0001lB\u0003]\u0015!\u0005QLB\u0003\n\u0015!\u0005a\fC\u0003c\r\u0011\u00051\rC\u0003e\r\u0011\u0015QM\u0001\u000bNk2$\u0018\u000e\u001d7jG\u0006$\u0018N^3U_J\u001cxN\u001d\u0006\u0003\u00171\tq!\u00197hK\n\u0014\u0018MC\u0001\u000e\u0003\u0015\u0019\b/\u001b:f\u0007\u0001)2\u0001E\u000f%'\r\u0001\u0011c\u0006\t\u0003%Ui\u0011a\u0005\u0006\u0002)\u0005)1oY1mC&\u0011ac\u0005\u0002\u0004\u0003:L\b\u0003\u0002\r\u001a7\rj\u0011AC\u0005\u00035)\u0011A#T;mi&\u0004H.[2bi&4X-Q2uS>t\u0007C\u0001\u000f\u001e\u0019\u0001!QA\b\u0001C\u0002}\u0011\u0011AV\t\u0003AE\u0001\"AE\u0011\n\u0005\t\u001a\"a\u0002(pi\"Lgn\u001a\t\u00039\u0011\"\u0011\"\n\u0001!\u0002\u0003\u0005)\u0019A\u0010\u0003\u0003ICc\u0001J\u0014+ier\u0004C\u0001\n)\u0013\tI3CA\u0006ta\u0016\u001c\u0017.\u00197ju\u0016$\u0017'B\u0012,Y9jcB\u0001\n-\u0013\ti3#A\u0002J]R\fD\u0001J\u00184)9\u0011\u0001gM\u0007\u0002c)\u0011!GD\u0001\u0007yI|w\u000e\u001e \n\u0003Q\tTaI\u001b7q]r!A\u0005\u001c\n\u0005]\u001a\u0012\u0001\u0002'p]\u001e\fD\u0001J\u00184)E*1EO\u001e>y9\u0011!cO\u0005\u0003yM\tQA\u00127pCR\fD\u0001J\u00184)E*1e\u0010!C\u0003:\u0011!\u0003Q\u0005\u0003\u0003N\ta\u0001R8vE2,\u0017\u0007\u0002\u00130gQ\ta\u0001J5oSR$C#A#\u0011\u0005I1\u0015BA$\u0014\u0005\u0011)f.\u001b;\u0002\rM\u001c\u0017\r\\1s+\u0005Q\u0005cA&OG9\u0011\u0001\u0004T\u0005\u0003\u001b*\tq\u0001]1dW\u0006<W-\u0003\u0002P!\n)R*\u001e7uSBd\u0017nY1uSZ,\u0017IY$s_V\u0004(BA'\u000b\u0003\u0011\u0001H-\u001b<\u0015\u0007\r\u001aV\u000bC\u0003U\u0007\u0001\u00071$A\u0001w\u0011\u001516\u00011\u0001\u001c\u0003\u00059\u0018!\u00034jq>\u0013\u0018nZ5o)\tI&\fE\u0002L\u001dnAQa\u0017\u0003A\u0002m\t!!\u001b3\u0002)5+H\u000e^5qY&\u001c\u0017\r^5wKR{'o]8s!\tAba\u0005\u0002\u0007?B\u0011!\u0003Y\u0005\u0003CN\u0011a!\u00118z%\u00164\u0017A\u0002\u001fj]&$h\bF\u0001^\u0003\u0015\t\u0007\u000f\u001d7z+\r1\u0017n\u001b\u000b\u0003OV\u0004B\u0001\u0007\u0001iUB\u0011A$\u001b\u0003\u0006=!\u0011\ra\b\t\u00039-$\u0011\"\n\u0005!\u0002\u0003\u0005)\u0019A\u0010)\r-<Sn\\9tc\u0015\u00193\u0006\f8.c\u0011!sf\r\u000b2\u000b\r*d\u0007]\u001c2\t\u0011z3\u0007F\u0019\u0006GiZ$\u000fP\u0019\u0005I=\u001aD#M\u0003$\u007f\u0001#\u0018)\r\u0003%_M\"\u0002\"\u0002<\t\u0001\b9\u0017!\u0001,)\u0005!A\bC\u0001\nz\u0013\tQ8C\u0001\u0004j]2Lg.\u001a"
)
public interface MultiplicativeTorsor extends MultiplicativeAction {
   static MultiplicativeTorsor apply(final MultiplicativeTorsor V) {
      return MultiplicativeTorsor$.MODULE$.apply(V);
   }

   MultiplicativeCommutativeGroup scalar();

   Object pdiv(final Object v, final Object w);

   // $FF: synthetic method
   static MultiplicativeCommutativeGroup fixOrigin$(final MultiplicativeTorsor $this, final Object id) {
      return $this.fixOrigin(id);
   }

   default MultiplicativeCommutativeGroup fixOrigin(final Object id) {
      return new MultiplicativeCommutativeGroup(id) {
         // $FF: synthetic field
         private final MultiplicativeTorsor $outer;
         private final Object id$2;

         public CommutativeGroup multiplicative() {
            return MultiplicativeCommutativeGroup.multiplicative$(this);
         }

         public CommutativeGroup multiplicative$mcD$sp() {
            return MultiplicativeCommutativeGroup.multiplicative$mcD$sp$(this);
         }

         public CommutativeGroup multiplicative$mcF$sp() {
            return MultiplicativeCommutativeGroup.multiplicative$mcF$sp$(this);
         }

         public CommutativeGroup multiplicative$mcI$sp() {
            return MultiplicativeCommutativeGroup.multiplicative$mcI$sp$(this);
         }

         public CommutativeGroup multiplicative$mcJ$sp() {
            return MultiplicativeCommutativeGroup.multiplicative$mcJ$sp$(this);
         }

         public double reciprocal$mcD$sp(final double x) {
            return MultiplicativeGroup.reciprocal$mcD$sp$(this, x);
         }

         public float reciprocal$mcF$sp(final float x) {
            return MultiplicativeGroup.reciprocal$mcF$sp$(this, x);
         }

         public int reciprocal$mcI$sp(final int x) {
            return MultiplicativeGroup.reciprocal$mcI$sp$(this, x);
         }

         public long reciprocal$mcJ$sp(final long x) {
            return MultiplicativeGroup.reciprocal$mcJ$sp$(this, x);
         }

         public double div$mcD$sp(final double x, final double y) {
            return MultiplicativeGroup.div$mcD$sp$(this, x, y);
         }

         public float div$mcF$sp(final float x, final float y) {
            return MultiplicativeGroup.div$mcF$sp$(this, x, y);
         }

         public int div$mcI$sp(final int x, final int y) {
            return MultiplicativeGroup.div$mcI$sp$(this, x, y);
         }

         public long div$mcJ$sp(final long x, final long y) {
            return MultiplicativeGroup.div$mcJ$sp$(this, x, y);
         }

         public Object pow(final Object a, final int n) {
            return MultiplicativeGroup.pow$(this, a, n);
         }

         public double pow$mcD$sp(final double a, final int n) {
            return MultiplicativeGroup.pow$mcD$sp$(this, a, n);
         }

         public float pow$mcF$sp(final float a, final int n) {
            return MultiplicativeGroup.pow$mcF$sp$(this, a, n);
         }

         public int pow$mcI$sp(final int a, final int n) {
            return MultiplicativeGroup.pow$mcI$sp$(this, a, n);
         }

         public long pow$mcJ$sp(final long a, final int n) {
            return MultiplicativeGroup.pow$mcJ$sp$(this, a, n);
         }

         public double one$mcD$sp() {
            return MultiplicativeMonoid.one$mcD$sp$(this);
         }

         public float one$mcF$sp() {
            return MultiplicativeMonoid.one$mcF$sp$(this);
         }

         public int one$mcI$sp() {
            return MultiplicativeMonoid.one$mcI$sp$(this);
         }

         public long one$mcJ$sp() {
            return MultiplicativeMonoid.one$mcJ$sp$(this);
         }

         public boolean isOne(final Object a, final Eq ev) {
            return MultiplicativeMonoid.isOne$(this, a, ev);
         }

         public boolean isOne$mcD$sp(final double a, final Eq ev) {
            return MultiplicativeMonoid.isOne$mcD$sp$(this, a, ev);
         }

         public boolean isOne$mcF$sp(final float a, final Eq ev) {
            return MultiplicativeMonoid.isOne$mcF$sp$(this, a, ev);
         }

         public boolean isOne$mcI$sp(final int a, final Eq ev) {
            return MultiplicativeMonoid.isOne$mcI$sp$(this, a, ev);
         }

         public boolean isOne$mcJ$sp(final long a, final Eq ev) {
            return MultiplicativeMonoid.isOne$mcJ$sp$(this, a, ev);
         }

         public Object product(final IterableOnce as) {
            return MultiplicativeMonoid.product$(this, as);
         }

         public double product$mcD$sp(final IterableOnce as) {
            return MultiplicativeMonoid.product$mcD$sp$(this, as);
         }

         public float product$mcF$sp(final IterableOnce as) {
            return MultiplicativeMonoid.product$mcF$sp$(this, as);
         }

         public int product$mcI$sp(final IterableOnce as) {
            return MultiplicativeMonoid.product$mcI$sp$(this, as);
         }

         public long product$mcJ$sp(final IterableOnce as) {
            return MultiplicativeMonoid.product$mcJ$sp$(this, as);
         }

         public Option tryProduct(final IterableOnce as) {
            return MultiplicativeMonoid.tryProduct$(this, as);
         }

         public double times$mcD$sp(final double x, final double y) {
            return MultiplicativeSemigroup.times$mcD$sp$(this, x, y);
         }

         public float times$mcF$sp(final float x, final float y) {
            return MultiplicativeSemigroup.times$mcF$sp$(this, x, y);
         }

         public int times$mcI$sp(final int x, final int y) {
            return MultiplicativeSemigroup.times$mcI$sp$(this, x, y);
         }

         public long times$mcJ$sp(final long x, final long y) {
            return MultiplicativeSemigroup.times$mcJ$sp$(this, x, y);
         }

         public Object positivePow(final Object a, final int n) {
            return MultiplicativeSemigroup.positivePow$(this, a, n);
         }

         public double positivePow$mcD$sp(final double a, final int n) {
            return MultiplicativeSemigroup.positivePow$mcD$sp$(this, a, n);
         }

         public float positivePow$mcF$sp(final float a, final int n) {
            return MultiplicativeSemigroup.positivePow$mcF$sp$(this, a, n);
         }

         public int positivePow$mcI$sp(final int a, final int n) {
            return MultiplicativeSemigroup.positivePow$mcI$sp$(this, a, n);
         }

         public long positivePow$mcJ$sp(final long a, final int n) {
            return MultiplicativeSemigroup.positivePow$mcJ$sp$(this, a, n);
         }

         public Object one() {
            return this.id$2;
         }

         public Object times(final Object v, final Object w) {
            return this.$outer.gtimesl(this.$outer.pdiv(v, this.id$2), w);
         }

         public Object reciprocal(final Object v) {
            return this.$outer.gtimesl(this.$outer.pdiv(this.id$2, v), this.id$2);
         }

         public Object div(final Object v, final Object w) {
            return this.$outer.gtimesl(this.$outer.pdiv(v, w), this.id$2);
         }

         public {
            if (MultiplicativeTorsor.this == null) {
               throw null;
            } else {
               this.$outer = MultiplicativeTorsor.this;
               this.id$2 = id$2;
               MultiplicativeSemigroup.$init$(this);
               MultiplicativeMonoid.$init$(this);
               MultiplicativeGroup.$init$(this);
               MultiplicativeCommutativeSemigroup.$init$(this);
               MultiplicativeCommutativeMonoid.$init$(this);
               MultiplicativeCommutativeGroup.$init$(this);
            }
         }
      };
   }

   // $FF: synthetic method
   static MultiplicativeCommutativeGroup scalar$mcD$sp$(final MultiplicativeTorsor $this) {
      return $this.scalar$mcD$sp();
   }

   default MultiplicativeCommutativeGroup scalar$mcD$sp() {
      return this.scalar();
   }

   // $FF: synthetic method
   static MultiplicativeCommutativeGroup scalar$mcF$sp$(final MultiplicativeTorsor $this) {
      return $this.scalar$mcF$sp();
   }

   default MultiplicativeCommutativeGroup scalar$mcF$sp() {
      return this.scalar();
   }

   // $FF: synthetic method
   static MultiplicativeCommutativeGroup scalar$mcI$sp$(final MultiplicativeTorsor $this) {
      return $this.scalar$mcI$sp();
   }

   default MultiplicativeCommutativeGroup scalar$mcI$sp() {
      return this.scalar();
   }

   // $FF: synthetic method
   static MultiplicativeCommutativeGroup scalar$mcJ$sp$(final MultiplicativeTorsor $this) {
      return $this.scalar$mcJ$sp();
   }

   default MultiplicativeCommutativeGroup scalar$mcJ$sp() {
      return this.scalar();
   }

   // $FF: synthetic method
   static double pdiv$mcD$sp$(final MultiplicativeTorsor $this, final Object v, final Object w) {
      return $this.pdiv$mcD$sp(v, w);
   }

   default double pdiv$mcD$sp(final Object v, final Object w) {
      return BoxesRunTime.unboxToDouble(this.pdiv(v, w));
   }

   // $FF: synthetic method
   static float pdiv$mcF$sp$(final MultiplicativeTorsor $this, final Object v, final Object w) {
      return $this.pdiv$mcF$sp(v, w);
   }

   default float pdiv$mcF$sp(final Object v, final Object w) {
      return BoxesRunTime.unboxToFloat(this.pdiv(v, w));
   }

   // $FF: synthetic method
   static int pdiv$mcI$sp$(final MultiplicativeTorsor $this, final Object v, final Object w) {
      return $this.pdiv$mcI$sp(v, w);
   }

   default int pdiv$mcI$sp(final Object v, final Object w) {
      return BoxesRunTime.unboxToInt(this.pdiv(v, w));
   }

   // $FF: synthetic method
   static long pdiv$mcJ$sp$(final MultiplicativeTorsor $this, final Object v, final Object w) {
      return $this.pdiv$mcJ$sp(v, w);
   }

   default long pdiv$mcJ$sp(final Object v, final Object w) {
      return BoxesRunTime.unboxToLong(this.pdiv(v, w));
   }

   static void $init$(final MultiplicativeTorsor $this) {
   }
}
