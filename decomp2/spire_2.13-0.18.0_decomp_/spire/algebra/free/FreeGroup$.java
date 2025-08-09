package spire.algebra.free;

import cats.kernel.Eq;
import cats.kernel.Group;
import cats.kernel.Monoid;
import cats.kernel.Semigroup;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Tuple2;
import scala.collection.IterableOnce;
import scala.collection.Iterator;
import scala.collection.immutable.Vector;
import scala.collection.mutable.ReusableBuilder;
import scala.package.;
import scala.runtime.BoxesRunTime;
import scala.util.Either;
import scala.util.Left;
import scala.util.Right;

public final class FreeGroup$ {
   public static final FreeGroup$ MODULE$ = new FreeGroup$();

   public final Vector id() {
      return .MODULE$.Vector().empty();
   }

   public final Vector apply(final Object a) {
      return this.lift(a);
   }

   public final Vector lift(final Object a) {
      return (Vector).MODULE$.Vector().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Right[]{.MODULE$.Right().apply(a)})));
   }

   public Group FreeGroupGroup() {
      return new Group() {
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

         public Vector empty() {
            return FreeGroup$.MODULE$.id();
         }

         public Vector combine(final Vector a, final Vector b) {
            return FreeGroup$.MODULE$.$bar$plus$bar$extension(a, b);
         }

         public Vector inverse(final Vector a) {
            return FreeGroup$.MODULE$.inverse$extension(a);
         }

         public Vector remove(final Vector a, final Vector b) {
            return FreeGroup$.MODULE$.$bar$minus$bar$extension(a, b);
         }

         public Vector combineAll(final IterableOnce as) {
            ReusableBuilder bldr = .MODULE$.Vector().newBuilder();
            as.iterator().foreach((x$2) -> $anonfun$combineAll$1(bldr, ((FreeGroup)x$2).terms()));
            return (Vector)bldr.result();
         }

         // $FF: synthetic method
         public static final ReusableBuilder $anonfun$combineAll$1(final ReusableBuilder bldr$1, final Vector x$2) {
            return (ReusableBuilder)bldr$1.$plus$plus$eq(x$2);
         }

         public {
            Semigroup.$init$(this);
            Monoid.$init$(this);
            Group.$init$(this);
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   public final Object run$extension(final Vector $this, final Function1 f, final Group B) {
      return $this.foldLeft(B.empty(), (x0$1, x1$1) -> {
         Tuple2 var5 = new Tuple2(x0$1, x1$1);
         Object var4;
         if (var5 != null) {
            Object sum = var5._1();
            Either var7 = (Either)var5._2();
            if (var7 instanceof Right) {
               Right var8 = (Right)var7;
               Object a = var8.value();
               var4 = B.combine(sum, f.apply(a));
               return var4;
            }
         }

         if (var5 == null) {
            throw new MatchError(var5);
         } else {
            Object sum = var5._1();
            Either var11 = (Either)var5._2();
            if (!(var11 instanceof Left)) {
               throw new MatchError(var5);
            } else {
               Left var12 = (Left)var11;
               Object a = var12.value();
               var4 = B.remove(sum, f.apply(a));
               return var4;
            }
         }
      });
   }

   public final Vector $bar$plus$bar$extension(final Vector $this, final Vector rhs) {
      return this.reduce$extension($this, $this.iterator().$plus$plus(() -> rhs.iterator()));
   }

   public final Vector $bar$minus$bar$extension(final Vector $this, final Vector rhs) {
      return this.reduce$extension($this, $this.iterator().$plus$plus(() -> rhs.reverseIterator().map((x$1) -> x$1.swap())));
   }

   public final Vector inverse$extension(final Vector $this) {
      ReusableBuilder bldr = .MODULE$.Vector().newBuilder();
      $this.reverseIterator().foreach((term) -> (ReusableBuilder)bldr.$plus$eq(term.swap()));
      return (Vector)bldr.result();
   }

   public final Vector reduce$extension(final Vector $this, final Iterator it) {
      return this.loop$1(.MODULE$.Vector().empty(), it);
   }

   public final String toString$extension(final Vector $this) {
      String var10000;
      if ($this.isEmpty()) {
         var10000 = "e";
      } else {
         Either var4 = (Either)$this.head();
         String init;
         if (var4 instanceof Left) {
            Left var5 = (Left)var4;
            Object h = var5.value();
            init = (new StringBuilder(10)).append("(").append(h).append(").inverse").toString();
         } else {
            if (!(var4 instanceof Right)) {
               throw new MatchError(var4);
            }

            Right var7 = (Right)var4;
            Object h = var7.value();
            init = h.toString();
         }

         Vector tail = (Vector)$this.tail().map((x0$1) -> {
            String var1;
            if (x0$1 instanceof Left) {
               Left var3 = (Left)x0$1;
               Object x = var3.value();
               var1 = (new StringBuilder(5)).append(" |-| ").append(x).toString();
            } else {
               if (!(x0$1 instanceof Right)) {
                  throw new MatchError(x0$1);
               }

               Right var5 = (Right)x0$1;
               Object x = var5.value();
               var1 = (new StringBuilder(5)).append(" |+| ").append(x).toString();
            }

            return var1;
         });
         var10000 = (new StringBuilder(0)).append(init).append(tail.mkString()).toString();
      }

      return var10000;
   }

   public final int hashCode$extension(final Vector $this) {
      return $this.hashCode();
   }

   public final boolean equals$extension(final Vector $this, final Object x$1) {
      boolean var3;
      if (x$1 instanceof FreeGroup) {
         var3 = true;
      } else {
         var3 = false;
      }

      boolean var7;
      if (var3) {
         label32: {
            label31: {
               Vector var5 = x$1 == null ? null : ((FreeGroup)x$1).terms();
               if ($this == null) {
                  if (var5 == null) {
                     break label31;
                  }
               } else if ($this.equals(var5)) {
                  break label31;
               }

               var7 = false;
               break label32;
            }

            var7 = true;
         }

         if (var7) {
            var7 = true;
            return var7;
         }
      }

      var7 = false;
      return var7;
   }

   private static final boolean annihilated$1(final Either x, final Either y) {
      Tuple2 var3 = new Tuple2(x, y);
      boolean var2;
      if (var3 != null) {
         Either var4 = (Either)var3._1();
         Either var5 = (Either)var3._2();
         if (var4 instanceof Left) {
            Left var6 = (Left)var4;
            Object x0 = var6.value();
            if (var5 instanceof Right) {
               Right var8 = (Right)var5;
               Object y0 = var8.value();
               var2 = BoxesRunTime.equals(x0, y0);
               return var2;
            }
         }
      }

      if (var3 != null) {
         Either var10 = (Either)var3._1();
         Either var11 = (Either)var3._2();
         if (var10 instanceof Right) {
            Right var12 = (Right)var10;
            Object x0 = var12.value();
            if (var11 instanceof Left) {
               Left var14 = (Left)var11;
               Object y0 = var14.value();
               var2 = BoxesRunTime.equals(x0, y0);
               return var2;
            }
         }
      }

      var2 = false;
      return var2;
   }

   private final Vector loop$1(final Vector acc, final Iterator it$1) {
      while(it$1.hasNext()) {
         Either cand = (Either)it$1.next();
         if (acc.nonEmpty() && annihilated$1((Either)acc.last(), cand)) {
            acc = acc.init();
         } else {
            acc = (Vector)acc.$colon$plus(cand);
         }
      }

      return acc;
   }

   private FreeGroup$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
