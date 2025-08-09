package scala.runtime;

import java.lang.invoke.SerializedLambda;
import scala.Function3;
import scala.MatchError;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.BuildFrom;
import scala.collection.Iterable;
import scala.collection.IterableOnce;
import scala.collection.Iterator;
import scala.collection.mutable.Builder;
import scala.util.hashing.MurmurHash3$;

/** @deprecated */
public final class Tuple3Zipped$ {
   public static final Tuple3Zipped$ MODULE$ = new Tuple3Zipped$();

   public final Iterable coll1$extension(final Tuple3 $this) {
      return (Iterable)$this._1();
   }

   public final Iterable coll2$extension(final Tuple3 $this) {
      return (Iterable)$this._2();
   }

   public final Iterable coll3$extension(final Tuple3 $this) {
      return (Iterable)$this._3();
   }

   public final Object map$extension(final Tuple3 $this, final Function3 f, final BuildFrom bf) {
      Builder b = bf.newBuilder((Iterable)$this._1());
      Iterator elems1 = ((Iterable)$this._1()).iterator();
      Iterator elems2 = ((Iterable)$this._2()).iterator();

      Object var9;
      for(Iterator elems3 = ((Iterable)$this._3()).iterator(); elems1.hasNext() && elems2.hasNext() && elems3.hasNext(); var9 = null) {
         var9 = f.apply(elems1.next(), elems2.next(), elems3.next());
         if (b == null) {
            throw null;
         }

         b.addOne(var9);
      }

      return b.result();
   }

   public final Object flatMap$extension(final Tuple3 $this, final Function3 f, final BuildFrom bf) {
      Builder b = bf.newBuilder((Iterable)$this._1());
      Iterator elems1 = ((Iterable)$this._1()).iterator();
      Iterator elems2 = ((Iterable)$this._2()).iterator();

      Object var9;
      for(Iterator elems3 = ((Iterable)$this._3()).iterator(); elems1.hasNext() && elems2.hasNext() && elems3.hasNext(); var9 = null) {
         IterableOnce $plus$plus$eq_elems = (IterableOnce)f.apply(elems1.next(), elems2.next(), elems3.next());
         if (b == null) {
            throw null;
         }

         b.addAll($plus$plus$eq_elems);
      }

      return b.result();
   }

   public final Tuple3 filter$extension(final Tuple3 $this, final Function3 f, final BuildFrom bf1, final BuildFrom bf2, final BuildFrom bf3) {
      Builder b1 = bf1.newBuilder((Iterable)$this._1());
      Builder b2 = bf2.newBuilder((Iterable)$this._2());
      Builder b3 = bf3.newBuilder((Iterable)$this._3());
      Iterator elems1 = ((Iterable)$this._1()).iterator();
      Iterator elems2 = ((Iterable)$this._2()).iterator();
      Iterator elems3 = ((Iterable)$this._3()).iterator();

      while(elems1.hasNext() && elems2.hasNext() && elems3.hasNext()) {
         Object el1 = elems1.next();
         Object el2 = elems2.next();
         Object el3 = elems3.next();
         if (BoxesRunTime.unboxToBoolean(f.apply(el1, el2, el3))) {
            if (b1 == null) {
               throw null;
            }

            b1.addOne(el1);
            if (b2 == null) {
               throw null;
            }

            b2.addOne(el2);
            if (b3 == null) {
               throw null;
            }

            b3.addOne(el3);
         }
      }

      return new Tuple3(b1.result(), b2.result(), b3.result());
   }

   public final boolean exists$extension(final Tuple3 $this, final Function3 p) {
      Iterator elems1 = ((Iterable)$this._1()).iterator();
      Iterator elems2 = ((Iterable)$this._2()).iterator();
      Iterator elems3 = ((Iterable)$this._3()).iterator();

      while(elems1.hasNext() && elems2.hasNext() && elems3.hasNext()) {
         if (BoxesRunTime.unboxToBoolean(p.apply(elems1.next(), elems2.next(), elems3.next()))) {
            return true;
         }
      }

      return false;
   }

   public final boolean forall$extension(final Tuple3 $this, final Function3 p) {
      Iterator exists$extension_elems1 = ((Iterable)$this._1()).iterator();
      Iterator exists$extension_elems2 = ((Iterable)$this._2()).iterator();
      Iterator exists$extension_elems3 = ((Iterable)$this._3()).iterator();

      boolean var10000;
      while(true) {
         if (exists$extension_elems1.hasNext() && exists$extension_elems2.hasNext() && exists$extension_elems3.hasNext()) {
            Object var12 = exists$extension_elems1.next();
            Object var10001 = exists$extension_elems2.next();
            Object var8 = exists$extension_elems3.next();
            Object var7 = var10001;
            Object var6 = var12;
            if (BoxesRunTime.unboxToBoolean(p.apply(var6, var7, var8))) {
               continue;
            }

            var10000 = true;
            break;
         }

         var10000 = false;
         break;
      }

      Object var9 = null;
      Object var10 = null;
      Object var11 = null;
      return !var10000;
   }

   public final Iterator iterator$extension(final Tuple3 $this) {
      return ((Iterable)$this._1()).iterator().zip(((Iterable)$this._2()).iterator()).zip(((Iterable)$this._3()).iterator()).map((x0$1) -> {
         if (x0$1 != null) {
            Tuple2 var1 = (Tuple2)x0$1._1();
            Object c = x0$1._2();
            if (var1 != null) {
               Object a = var1._1();
               Object b = var1._2();
               return new Tuple3(a, b, c);
            }
         }

         throw new MatchError(x0$1);
      });
   }

   public final boolean isEmpty$extension(final Tuple3 $this) {
      return ((Iterable)$this._1()).isEmpty() || ((Iterable)$this._2()).isEmpty() || ((Iterable)$this._3()).isEmpty();
   }

   public final void foreach$extension(final Tuple3 $this, final Function3 f) {
      Iterator elems1 = ((Iterable)$this._1()).iterator();
      Iterator elems2 = ((Iterable)$this._2()).iterator();
      Iterator elems3 = ((Iterable)$this._3()).iterator();

      while(elems1.hasNext() && elems2.hasNext() && elems3.hasNext()) {
         f.apply(elems1.next(), elems2.next(), elems3.next());
      }

   }

   public final String toString$extension(final Tuple3 $this) {
      return (new StringBuilder(13)).append("(").append((Iterable)$this._1()).append(", ").append((Iterable)$this._2()).append(", ").append((Iterable)$this._3()).append(").zipped").toString();
   }

   public final int hashCode$extension(final Tuple3 $this) {
      if ($this == null) {
         throw null;
      } else {
         return MurmurHash3$.MODULE$.productHash($this);
      }
   }

   public final boolean equals$extension(final Tuple3 $this, final Object x$1) {
      if (x$1 instanceof Tuple3Zipped) {
         Tuple3 var3 = x$1 == null ? null : ((Tuple3Zipped)x$1).scala$runtime$Tuple3Zipped$$colls();
         if ($this == null) {
            if (var3 == null) {
               return true;
            }
         } else if ($this.equals(var3)) {
            return true;
         }
      }

      return false;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$forall$1(final Function3 p$1, final Object x, final Object y, final Object z) {
      return !BoxesRunTime.unboxToBoolean(p$1.apply(x, y, z));
   }

   private Tuple3Zipped$() {
   }

   // $FF: synthetic method
   public static final Object $anonfun$forall$1$adapted(final Function3 p$1, final Object x, final Object y, final Object z) {
      return BoxesRunTime.boxToBoolean($anonfun$forall$1(p$1, x, y, z));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
