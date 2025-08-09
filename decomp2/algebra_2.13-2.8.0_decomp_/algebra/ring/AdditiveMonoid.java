package algebra.ring;

import cats.kernel.Eq;
import cats.kernel.Monoid;
import cats.kernel.Semigroup;
import java.lang.invoke.SerializedLambda;
import scala.Option;
import scala.Some;
import scala.collection.IterableOnce;
import scala.collection.StringOps.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Uca\u0002\b\u0010!\u0003\r\t\u0001\u0006\u0005\u0006\u000b\u0002!\tA\u0012\u0005\u0006\u0015\u0002!\te\u0013\u0005\u0006)\u00021\t!\u0016\u0005\u0006-\u0002!\ta\u0016\u0005\u0006G\u0002!\t\u0005\u001a\u0005\u0006W\u0002!\t\u0001\u001c\u0005\u0006k\u0002!\tE^\u0004\u0006w>A\t\u0001 \u0004\u0006\u001d=A\t! \u0005\b\u00037IA\u0011AA\u000f\u0011\u001d\ty\"\u0003C\u0003\u0003CAaAS\u0005\u0005\u0006\u0005U\u0002\"CA#\u0013\u0005\u0005I\u0011BA$\u00059\tE\rZ5uSZ,Wj\u001c8pS\u0012T!\u0001E\t\u0002\tILgn\u001a\u0006\u0002%\u00059\u0011\r\\4fEJ\f7\u0001A\u000b\u0003+\t\u001a2\u0001\u0001\f\u001d!\t9\"$D\u0001\u0019\u0015\u0005I\u0012!B:dC2\f\u0017BA\u000e\u0019\u0005\r\te.\u001f\t\u0004;y\u0001S\"A\b\n\u0005}y!!E!eI&$\u0018N^3TK6LwM]8vaB\u0011\u0011E\t\u0007\u0001\t%\u0019\u0003\u0001)A\u0001\u0002\u000b\u0007AEA\u0001B#\t)c\u0003\u0005\u0002\u0018M%\u0011q\u0005\u0007\u0002\b\u001d>$\b.\u001b8hQ\u0019\u0011\u0013\u0006\f\u001c<\u0001B\u0011qCK\u0005\u0003Wa\u00111b\u001d9fG&\fG.\u001b>fIF*1%\f\u00181_9\u0011qCL\u0005\u0003_a\t1!\u00138uc\u0011!\u0013'N\r\u000f\u0005I*T\"A\u001a\u000b\u0005Q\u001a\u0012A\u0002\u001fs_>$h(C\u0001\u001ac\u0015\u0019s\u0007\u000f\u001e:\u001d\t9\u0002(\u0003\u0002:1\u0005!Aj\u001c8hc\u0011!\u0013'N\r2\u000b\rbTh\u0010 \u000f\u0005]i\u0014B\u0001 \u0019\u0003\u00151En\\1uc\u0011!\u0013'N\r2\u000b\r\n%\tR\"\u000f\u0005]\u0011\u0015BA\"\u0019\u0003\u0019!u.\u001e2mKF\"A%M\u001b\u001a\u0003\u0019!\u0013N\\5uIQ\tq\t\u0005\u0002\u0018\u0011&\u0011\u0011\n\u0007\u0002\u0005+:LG/\u0001\u0005bI\u0012LG/\u001b<f+\u0005a\u0005cA'RA9\u0011ajT\u0007\u0002#%\u0011\u0001+E\u0001\ba\u0006\u001c7.Y4f\u0013\t\u00116K\u0001\u0004N_:|\u0017\u000e\u001a\u0006\u0003!F\tAA_3s_V\t\u0001%\u0001\u0004jgj+'o\u001c\u000b\u00031\u0006$\"!\u0017/\u0011\u0005]Q\u0016BA.\u0019\u0005\u001d\u0011un\u001c7fC:DQ!\u0018\u0003A\u0004y\u000b!!\u001a<\u0011\u00075{\u0006%\u0003\u0002a'\n\u0011Q)\u001d\u0005\u0006E\u0012\u0001\r\u0001I\u0001\u0002C\u0006!1/^7O)\r\u0001SM\u001a\u0005\u0006E\u0016\u0001\r\u0001\t\u0005\u0006O\u0016\u0001\r\u0001[\u0001\u0002]B\u0011q#[\u0005\u0003Ub\u00111!\u00138u\u0003\r\u0019X/\u001c\u000b\u0003A5DQA\u001c\u0004A\u0002=\f!!Y:\u0011\u0007A\u0014\bE\u0004\u00022c&\u0011\u0001\u000bG\u0005\u0003gR\u0014q\u0002\u0016:bm\u0016\u00148/\u00192mK>s7-\u001a\u0006\u0003!b\ta\u0001\u001e:z'VlGCA<{!\r9\u0002\u0010I\u0005\u0003sb\u0011aa\u00149uS>t\u0007\"\u00028\b\u0001\u0004y\u0017AD!eI&$\u0018N^3N_:|\u0017\u000e\u001a\t\u0003;%\u0019b!\u0003@\u0002\u0004\u0005-\u0001CA\f\u0000\u0013\r\t\t\u0001\u0007\u0002\u0007\u0003:L(+\u001a4\u0011\u000bu\t)!!\u0003\n\u0007\u0005\u001dqBA\fBI\u0012LG/\u001b<f\u001b>tw.\u001b3Gk:\u001cG/[8ogB\u0011Q\u0004\u0001\t\u0005\u0003\u001b\t9\"\u0004\u0002\u0002\u0010)!\u0011\u0011CA\n\u0003\tIwN\u0003\u0002\u0002\u0016\u0005!!.\u0019<b\u0013\u0011\tI\"a\u0004\u0003\u0019M+'/[1mSj\f'\r\\3\u0002\rqJg.\u001b;?)\u0005a\u0018!B1qa2LX\u0003BA\u0012\u0003S!B!!\n\u0002,A!Q\u0004AA\u0014!\r\t\u0013\u0011\u0006\u0003\u0006G-\u0011\r\u0001\n\u0005\u0007;.\u0001\u001d!!\n)\u0007-\ty\u0003E\u0002\u0018\u0003cI1!a\r\u0019\u0005\u0019Ig\u000e\\5oKV!\u0011qGA\u001f)\u0011\tI$a\u0010\u0011\t5\u000b\u00161\b\t\u0004C\u0005uB!B\u0012\r\u0005\u0004!\u0003BB/\r\u0001\b\t\t\u0005\u0005\u0003\u001e\u0001\u0005m\u0002f\u0001\u0007\u00020\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u0011\u0011\n\t\u0005\u0003\u0017\n\t&\u0004\u0002\u0002N)!\u0011qJA\n\u0003\u0011a\u0017M\\4\n\t\u0005M\u0013Q\n\u0002\u0007\u001f\nTWm\u0019;"
)
public interface AdditiveMonoid extends AdditiveSemigroup {
   static AdditiveMonoid apply(final AdditiveMonoid ev) {
      return AdditiveMonoid$.MODULE$.apply(ev);
   }

   static boolean isAdditiveCommutative(final AdditiveSemigroup ev) {
      return AdditiveMonoid$.MODULE$.isAdditiveCommutative(ev);
   }

   // $FF: synthetic method
   static Monoid additive$(final AdditiveMonoid $this) {
      return $this.additive();
   }

   default Monoid additive() {
      return new Monoid() {
         // $FF: synthetic field
         private final AdditiveMonoid $outer;

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
            if (AdditiveMonoid.this == null) {
               throw null;
            } else {
               this.$outer = AdditiveMonoid.this;
               Semigroup.$init$(this);
               Monoid.$init$(this);
            }
         }
      };
   }

   Object zero();

   // $FF: synthetic method
   static boolean isZero$(final AdditiveMonoid $this, final Object a, final Eq ev) {
      return $this.isZero(a, ev);
   }

   default boolean isZero(final Object a, final Eq ev) {
      return ev.eqv(a, this.zero());
   }

   // $FF: synthetic method
   static Object sumN$(final AdditiveMonoid $this, final Object a, final int n) {
      return $this.sumN(a, n);
   }

   default Object sumN(final Object a, final int n) {
      Object var10000;
      if (n > 0) {
         var10000 = this.positiveSumN(a, n);
      } else {
         if (n != 0) {
            throw new IllegalArgumentException(.MODULE$.format$extension(scala.Predef..MODULE$.augmentString("Illegal negative exponent to sumN: %s"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(n)})));
         }

         var10000 = this.zero();
      }

      return var10000;
   }

   // $FF: synthetic method
   static Object sum$(final AdditiveMonoid $this, final IterableOnce as) {
      return $this.sum(as);
   }

   default Object sum(final IterableOnce as) {
      return scala.collection.IterableOnceExtensionMethods..MODULE$.foldLeft$extension(scala.collection.IterableOnce..MODULE$.iterableOnceExtensionMethods(as), this.zero(), (x, y) -> this.plus(x, y));
   }

   // $FF: synthetic method
   static Option trySum$(final AdditiveMonoid $this, final IterableOnce as) {
      return $this.trySum(as);
   }

   default Option trySum(final IterableOnce as) {
      return (Option)(scala.collection.IterableOnceExtensionMethods..MODULE$.isEmpty$extension(scala.collection.IterableOnce..MODULE$.iterableOnceExtensionMethods(as)) ? scala.None..MODULE$ : new Some(this.sum(as)));
   }

   // $FF: synthetic method
   static Monoid additive$mcD$sp$(final AdditiveMonoid $this) {
      return $this.additive$mcD$sp();
   }

   default Monoid additive$mcD$sp() {
      return this.additive();
   }

   // $FF: synthetic method
   static Monoid additive$mcF$sp$(final AdditiveMonoid $this) {
      return $this.additive$mcF$sp();
   }

   default Monoid additive$mcF$sp() {
      return this.additive();
   }

   // $FF: synthetic method
   static Monoid additive$mcI$sp$(final AdditiveMonoid $this) {
      return $this.additive$mcI$sp();
   }

   default Monoid additive$mcI$sp() {
      return this.additive();
   }

   // $FF: synthetic method
   static Monoid additive$mcJ$sp$(final AdditiveMonoid $this) {
      return $this.additive$mcJ$sp();
   }

   default Monoid additive$mcJ$sp() {
      return this.additive();
   }

   // $FF: synthetic method
   static double zero$mcD$sp$(final AdditiveMonoid $this) {
      return $this.zero$mcD$sp();
   }

   default double zero$mcD$sp() {
      return BoxesRunTime.unboxToDouble(this.zero());
   }

   // $FF: synthetic method
   static float zero$mcF$sp$(final AdditiveMonoid $this) {
      return $this.zero$mcF$sp();
   }

   default float zero$mcF$sp() {
      return BoxesRunTime.unboxToFloat(this.zero());
   }

   // $FF: synthetic method
   static int zero$mcI$sp$(final AdditiveMonoid $this) {
      return $this.zero$mcI$sp();
   }

   default int zero$mcI$sp() {
      return BoxesRunTime.unboxToInt(this.zero());
   }

   // $FF: synthetic method
   static long zero$mcJ$sp$(final AdditiveMonoid $this) {
      return $this.zero$mcJ$sp();
   }

   default long zero$mcJ$sp() {
      return BoxesRunTime.unboxToLong(this.zero());
   }

   // $FF: synthetic method
   static boolean isZero$mcD$sp$(final AdditiveMonoid $this, final double a, final Eq ev) {
      return $this.isZero$mcD$sp(a, ev);
   }

   default boolean isZero$mcD$sp(final double a, final Eq ev) {
      return this.isZero(BoxesRunTime.boxToDouble(a), ev);
   }

   // $FF: synthetic method
   static boolean isZero$mcF$sp$(final AdditiveMonoid $this, final float a, final Eq ev) {
      return $this.isZero$mcF$sp(a, ev);
   }

   default boolean isZero$mcF$sp(final float a, final Eq ev) {
      return this.isZero(BoxesRunTime.boxToFloat(a), ev);
   }

   // $FF: synthetic method
   static boolean isZero$mcI$sp$(final AdditiveMonoid $this, final int a, final Eq ev) {
      return $this.isZero$mcI$sp(a, ev);
   }

   default boolean isZero$mcI$sp(final int a, final Eq ev) {
      return this.isZero(BoxesRunTime.boxToInteger(a), ev);
   }

   // $FF: synthetic method
   static boolean isZero$mcJ$sp$(final AdditiveMonoid $this, final long a, final Eq ev) {
      return $this.isZero$mcJ$sp(a, ev);
   }

   default boolean isZero$mcJ$sp(final long a, final Eq ev) {
      return this.isZero(BoxesRunTime.boxToLong(a), ev);
   }

   // $FF: synthetic method
   static double sumN$mcD$sp$(final AdditiveMonoid $this, final double a, final int n) {
      return $this.sumN$mcD$sp(a, n);
   }

   default double sumN$mcD$sp(final double a, final int n) {
      return BoxesRunTime.unboxToDouble(this.sumN(BoxesRunTime.boxToDouble(a), n));
   }

   // $FF: synthetic method
   static float sumN$mcF$sp$(final AdditiveMonoid $this, final float a, final int n) {
      return $this.sumN$mcF$sp(a, n);
   }

   default float sumN$mcF$sp(final float a, final int n) {
      return BoxesRunTime.unboxToFloat(this.sumN(BoxesRunTime.boxToFloat(a), n));
   }

   // $FF: synthetic method
   static int sumN$mcI$sp$(final AdditiveMonoid $this, final int a, final int n) {
      return $this.sumN$mcI$sp(a, n);
   }

   default int sumN$mcI$sp(final int a, final int n) {
      return BoxesRunTime.unboxToInt(this.sumN(BoxesRunTime.boxToInteger(a), n));
   }

   // $FF: synthetic method
   static long sumN$mcJ$sp$(final AdditiveMonoid $this, final long a, final int n) {
      return $this.sumN$mcJ$sp(a, n);
   }

   default long sumN$mcJ$sp(final long a, final int n) {
      return BoxesRunTime.unboxToLong(this.sumN(BoxesRunTime.boxToLong(a), n));
   }

   // $FF: synthetic method
   static double sum$mcD$sp$(final AdditiveMonoid $this, final IterableOnce as) {
      return $this.sum$mcD$sp(as);
   }

   default double sum$mcD$sp(final IterableOnce as) {
      return BoxesRunTime.unboxToDouble(this.sum(as));
   }

   // $FF: synthetic method
   static float sum$mcF$sp$(final AdditiveMonoid $this, final IterableOnce as) {
      return $this.sum$mcF$sp(as);
   }

   default float sum$mcF$sp(final IterableOnce as) {
      return BoxesRunTime.unboxToFloat(this.sum(as));
   }

   // $FF: synthetic method
   static int sum$mcI$sp$(final AdditiveMonoid $this, final IterableOnce as) {
      return $this.sum$mcI$sp(as);
   }

   default int sum$mcI$sp(final IterableOnce as) {
      return BoxesRunTime.unboxToInt(this.sum(as));
   }

   // $FF: synthetic method
   static long sum$mcJ$sp$(final AdditiveMonoid $this, final IterableOnce as) {
      return $this.sum$mcJ$sp(as);
   }

   default long sum$mcJ$sp(final IterableOnce as) {
      return BoxesRunTime.unboxToLong(this.sum(as));
   }

   static void $init$(final AdditiveMonoid $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
