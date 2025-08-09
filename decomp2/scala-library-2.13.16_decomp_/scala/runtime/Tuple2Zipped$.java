package scala.runtime;

import scala.Function2;
import scala.Tuple2;
import scala.collection.BuildFrom;
import scala.collection.Iterable;
import scala.collection.IterableOnce;
import scala.collection.Iterator;
import scala.collection.mutable.Builder;

/** @deprecated */
public final class Tuple2Zipped$ {
   public static final Tuple2Zipped$ MODULE$ = new Tuple2Zipped$();

   public final Iterable coll1$extension(final Tuple2 $this) {
      return (Iterable)$this._1();
   }

   public final Iterable coll2$extension(final Tuple2 $this) {
      return (Iterable)$this._2();
   }

   public final Object map$extension(final Tuple2 $this, final Function2 f, final BuildFrom bf) {
      Builder b = bf.newBuilder((Iterable)$this._1());
      b.sizeHint((Iterable)$this._1(), 0);
      Iterator elems1 = ((Iterable)$this._1()).iterator();

      Object $plus$eq_elem;
      for(Iterator elems2 = ((Iterable)$this._2()).iterator(); elems1.hasNext() && elems2.hasNext(); $plus$eq_elem = null) {
         $plus$eq_elem = f.apply(elems1.next(), elems2.next());
         b.addOne($plus$eq_elem);
      }

      return b.result();
   }

   public final Object flatMap$extension(final Tuple2 $this, final Function2 f, final BuildFrom bf) {
      Builder b = bf.newBuilder((Iterable)$this._1());
      Iterator elems1 = ((Iterable)$this._1()).iterator();

      Object var8;
      for(Iterator elems2 = ((Iterable)$this._2()).iterator(); elems1.hasNext() && elems2.hasNext(); var8 = null) {
         IterableOnce $plus$plus$eq_elems = (IterableOnce)f.apply(elems1.next(), elems2.next());
         if (b == null) {
            throw null;
         }

         b.addAll($plus$plus$eq_elems);
      }

      return b.result();
   }

   public final Tuple2 filter$extension(final Tuple2 $this, final Function2 f, final BuildFrom bf1, final BuildFrom bf2) {
      Builder b1 = bf1.newBuilder((Iterable)$this._1());
      Builder b2 = bf2.newBuilder((Iterable)$this._2());
      Iterator elems1 = ((Iterable)$this._1()).iterator();
      Iterator elems2 = ((Iterable)$this._2()).iterator();

      while(elems1.hasNext() && elems2.hasNext()) {
         Object el1 = elems1.next();
         Object el2 = elems2.next();
         if (BoxesRunTime.unboxToBoolean(f.apply(el1, el2))) {
            if (b1 == null) {
               throw null;
            }

            b1.addOne(el1);
            if (b2 == null) {
               throw null;
            }

            b2.addOne(el2);
         }
      }

      return new Tuple2(b1.result(), b2.result());
   }

   public final boolean exists$extension(final Tuple2 $this, final Function2 p) {
      Iterator elems1 = ((Iterable)$this._1()).iterator();
      Iterator elems2 = ((Iterable)$this._2()).iterator();

      while(elems1.hasNext() && elems2.hasNext()) {
         if (BoxesRunTime.unboxToBoolean(p.apply(elems1.next(), elems2.next()))) {
            return true;
         }
      }

      return false;
   }

   public final boolean forall$extension(final Tuple2 $this, final Function2 p) {
      Iterator exists$extension_elems1 = ((Iterable)$this._1()).iterator();
      Iterator exists$extension_elems2 = ((Iterable)$this._2()).iterator();

      boolean var10000;
      while(true) {
         if (exists$extension_elems1.hasNext() && exists$extension_elems2.hasNext()) {
            Object var9 = exists$extension_elems1.next();
            Object var6 = exists$extension_elems2.next();
            Object var5 = var9;
            if (BoxesRunTime.unboxToBoolean(p.apply(var5, var6))) {
               continue;
            }

            var10000 = true;
            break;
         }

         var10000 = false;
         break;
      }

      Object var7 = null;
      Object var8 = null;
      return !var10000;
   }

   public final Iterator iterator$extension(final Tuple2 $this) {
      return ((Iterable)$this._1()).iterator().zip(((Iterable)$this._2()).iterator());
   }

   public final boolean isEmpty$extension(final Tuple2 $this) {
      return ((Iterable)$this._1()).isEmpty() || ((Iterable)$this._2()).isEmpty();
   }

   public final void foreach$extension(final Tuple2 $this, final Function2 f) {
      Iterator elems1 = ((Iterable)$this._1()).iterator();
      Iterator elems2 = ((Iterable)$this._2()).iterator();

      while(elems1.hasNext() && elems2.hasNext()) {
         f.apply(elems1.next(), elems2.next());
      }

   }

   public final String toString$extension(final Tuple2 $this) {
      return (new StringBuilder(11)).append("(").append((Iterable)$this._1()).append(", ").append((Iterable)$this._2()).append(").zipped").toString();
   }

   public final int hashCode$extension(final Tuple2 $this) {
      return $this.hashCode();
   }

   public final boolean equals$extension(final Tuple2 $this, final Object x$1) {
      if (x$1 instanceof Tuple2Zipped) {
         Tuple2 var3 = x$1 == null ? null : ((Tuple2Zipped)x$1).scala$runtime$Tuple2Zipped$$colls();
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
   public static final boolean $anonfun$forall$1(final Function2 p$1, final Object x, final Object y) {
      return !BoxesRunTime.unboxToBoolean(p$1.apply(x, y));
   }

   private Tuple2Zipped$() {
   }

   // $FF: synthetic method
   public static final Object $anonfun$forall$1$adapted(final Function2 p$1, final Object x, final Object y) {
      return BoxesRunTime.boxToBoolean($anonfun$forall$1(p$1, x, y));
   }
}
