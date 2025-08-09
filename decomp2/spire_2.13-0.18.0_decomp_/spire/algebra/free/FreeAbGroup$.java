package spire.algebra.free;

import cats.kernel.CommutativeGroup;
import cats.kernel.CommutativeMonoid;
import cats.kernel.CommutativeSemigroup;
import cats.kernel.Eq;
import cats.kernel.Group;
import cats.kernel.Monoid;
import cats.kernel.Semigroup;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.Iterator;
import scala.collection.immutable.Map;
import scala.runtime.BoxesRunTime;
import spire.std.package;

public final class FreeAbGroup$ {
   public static final FreeAbGroup$ MODULE$ = new FreeAbGroup$();

   public final Map id() {
      return .MODULE$.Map().empty();
   }

   public final Map apply(final Object a) {
      return this.lift(a);
   }

   public final Map lift(final Object a) {
      return (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{new Tuple2(a, BoxesRunTime.boxToInteger(1))})));
   }

   public CommutativeGroup FreeAbGroupGroup() {
      return new CommutativeGroup() {
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

         public Map empty() {
            return FreeAbGroup$.MODULE$.id();
         }

         public Map combine(final Map a, final Map b) {
            return FreeAbGroup$.MODULE$.$bar$plus$bar$extension(a, b);
         }

         public Map inverse(final Map a) {
            return FreeAbGroup$.MODULE$.inverse$extension(a);
         }

         public Map remove(final Map a, final Map b) {
            return FreeAbGroup$.MODULE$.$bar$minus$bar$extension(a, b);
         }

         public {
            Semigroup.$init$(this);
            Monoid.$init$(this);
            Group.$init$(this);
            CommutativeSemigroup.$init$(this);
            CommutativeMonoid.$init$(this);
         }
      };
   }

   public final Object run$extension(final Map $this, final Function1 f, final CommutativeGroup B) {
      return $this.foldLeft(B.empty(), (x0$1, x1$1) -> {
         Tuple2 var5 = new Tuple2(x0$1, x1$1);
         if (var5 != null) {
            Object total = var5._1();
            Tuple2 var7 = (Tuple2)var5._2();
            if (var7 != null) {
               Object a = var7._1();
               int n = var7._2$mcI$sp();
               Object var4 = B.combine(total, B.combineN(f.apply(a), n));
               return var4;
            }
         }

         throw new MatchError(var5);
      });
   }

   public final Option runMonoid$extension(final Map $this, final Function1 f, final CommutativeMonoid B) {
      Iterator it = $this.iterator();
      return this.loop$1(B.empty(), it, B, f);
   }

   public final Option runSemigroup$extension(final Map $this, final Function1 f, final CommutativeSemigroup B) {
      Iterator it = $this.iterator();
      return this.loop0$1(it, B, f);
   }

   public final Tuple2 split$extension(final Map $this, final Function1 f, final CommutativeMonoid B) {
      return (Tuple2)$this.foldLeft(new Tuple2(B.empty(), B.empty()), (x0$1, x1$1) -> {
         Tuple2 var5 = new Tuple2(x0$1, x1$1);
         if (var5 != null) {
            Tuple2 var6 = (Tuple2)var5._1();
            Tuple2 var7 = (Tuple2)var5._2();
            if (var6 != null) {
               Object ltotal = var6._1();
               Object rtotal = var6._2();
               if (var7 != null) {
                  Object a = var7._1();
                  int n = var7._2$mcI$sp();
                  Tuple2 var4 = n < 0 ? new Tuple2(B.combine(ltotal, B.combineN(f.apply(a), -n)), rtotal) : (n > 0 ? new Tuple2(ltotal, B.combine(rtotal, B.combineN(f.apply(a), n))) : new Tuple2(ltotal, rtotal));
                  return var4;
               }
            }
         }

         throw new MatchError(var5);
      });
   }

   public final Tuple2 splitSemigroup$extension(final Map $this, final Function1 f, final CommutativeSemigroup B) {
      return this.split$extension($this, (a) -> new Some(f.apply(a)), package.option$.MODULE$.OptionCMonoid(B));
   }

   public final Map $bar$plus$bar$extension(final Map $this, final Map rhs) {
      return package.map$.MODULE$.MapCRng(package.int$.MODULE$.IntAlgebra()).plus($this, rhs);
   }

   public final Map $bar$minus$bar$extension(final Map $this, final Map rhs) {
      return (Map)package.map$.MODULE$.MapCRng(package.int$.MODULE$.IntAlgebra()).minus($this, rhs);
   }

   public final Map inverse$extension(final Map $this) {
      return package.map$.MODULE$.MapCRng(package.int$.MODULE$.IntAlgebra()).negate($this);
   }

   public final String toString$extension(final Map $this) {
      return $this.isEmpty() ? "e" : ((IterableOnceOps)((IterableOps)$this.filter((x$4) -> BoxesRunTime.boxToBoolean($anonfun$toString$1(x$4)))).collect(new Serializable() {
         private static final long serialVersionUID = 0L;

         public final Object applyOrElse(final Tuple2 x1, final Function1 default) {
            Object var3;
            if (x1 != null) {
               Object a = x1._1();
               int n = x1._2$mcI$sp();
               if (n == 1) {
                  var3 = a.toString();
                  return var3;
               }
            }

            if (x1 != null) {
               Object a = x1._1();
               int n = x1._2$mcI$sp();
               if (n != 0) {
                  var3 = (new StringBuilder(3)).append("(").append(a).append(")^").append(n).toString();
                  return var3;
               }
            }

            var3 = default.apply(x1);
            return var3;
         }

         public final boolean isDefinedAt(final Tuple2 x1) {
            boolean var2;
            if (x1 != null) {
               int n = x1._2$mcI$sp();
               if (n == 1) {
                  var2 = true;
                  return var2;
               }
            }

            if (x1 != null) {
               int n = x1._2$mcI$sp();
               if (n != 0) {
                  var2 = true;
                  return var2;
               }
            }

            var2 = false;
            return var2;
         }
      })).mkString(" |+| ");
   }

   public final int hashCode$extension(final Map $this) {
      return $this.hashCode();
   }

   public final boolean equals$extension(final Map $this, final Object x$1) {
      boolean var3;
      if (x$1 instanceof FreeAbGroup) {
         var3 = true;
      } else {
         var3 = false;
      }

      boolean var7;
      if (var3) {
         label32: {
            label31: {
               Map var5 = x$1 == null ? null : ((FreeAbGroup)x$1).terms();
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

   private final Option loop$1(final Object total, final Iterator it$1, final CommutativeMonoid B$2, final Function1 f$2) {
      while(true) {
         Object var10000;
         if (it$1.hasNext()) {
            Tuple2 var8 = (Tuple2)it$1.next();
            if (var8 == null) {
               throw new MatchError(var8);
            }

            Object a = var8._1();
            int n = var8._2$mcI$sp();
            Tuple2 var6 = new Tuple2(a, BoxesRunTime.boxToInteger(n));
            Object a = var6._1();
            int n = var6._2$mcI$sp();
            if (n >= 0) {
               total = B$2.combine(total, B$2.combineN(f$2.apply(a), n));
               continue;
            }

            var10000 = scala.None..MODULE$;
         } else {
            var10000 = new Some(total);
         }

         return (Option)var10000;
      }
   }

   private final Option loop1$1(final Object total, final Iterator it$2, final CommutativeSemigroup B$3, final Function1 f$3) {
      while(true) {
         Object var10000;
         if (it$2.hasNext()) {
            Tuple2 var8 = (Tuple2)it$2.next();
            if (var8 == null) {
               throw new MatchError(var8);
            }

            Object a = var8._1();
            int n = var8._2$mcI$sp();
            Tuple2 var6 = new Tuple2(a, BoxesRunTime.boxToInteger(n));
            Object a = var6._1();
            int n = var6._2$mcI$sp();
            if (n == 0) {
               total = total;
               continue;
            }

            if (n >= 0) {
               total = B$3.combine(total, B$3.combineN(f$3.apply(a), n));
               continue;
            }

            var10000 = scala.None..MODULE$;
         } else {
            var10000 = new Some(total);
         }

         return (Option)var10000;
      }
   }

   private final Option loop0$1(final Iterator it$2, final CommutativeSemigroup B$3, final Function1 f$3) {
      while(true) {
         Object var10000;
         if (it$2.hasNext()) {
            Tuple2 var7 = (Tuple2)it$2.next();
            if (var7 == null) {
               throw new MatchError(var7);
            }

            Object a = var7._1();
            int n = var7._2$mcI$sp();
            Tuple2 var5 = new Tuple2(a, BoxesRunTime.boxToInteger(n));
            Object a = var5._1();
            int n = var5._2$mcI$sp();
            if (n == 0) {
               continue;
            }

            var10000 = n < 0 ? scala.None..MODULE$ : this.loop1$1(B$3.combineN(f$3.apply(a), n), it$2, B$3, f$3);
         } else {
            var10000 = scala.None..MODULE$;
         }

         return (Option)var10000;
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$toString$1(final Tuple2 x$4) {
      return x$4._2$mcI$sp() != 0;
   }

   private FreeAbGroup$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
