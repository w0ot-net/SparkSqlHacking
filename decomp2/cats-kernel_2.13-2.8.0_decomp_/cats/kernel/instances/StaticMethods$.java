package cats.kernel.instances;

import cats.kernel.Eq;
import cats.kernel.Order;
import cats.kernel.PartialOrder;
import cats.kernel.compat.HashCompat;
import java.lang.invoke.SerializedLambda;
import scala.collection.Iterable;
import scala.collection.IterableOnce;
import scala.collection.Iterator;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.Map;
import scala.collection.mutable.Builder;
import scala.util.hashing.MurmurHash3.;

public final class StaticMethods$ extends HashCompat {
   public static final StaticMethods$ MODULE$ = new StaticMethods$();

   public Map wrapMutableMap(final scala.collection.mutable.Map m) {
      return new StaticMethods.WrappedMutableMap(m);
   }

   public IndexedSeq wrapMutableIndexedSeq(final scala.collection.mutable.IndexedSeq m) {
      return new StaticMethods.WrappedIndexedSeq(m);
   }

   public int iteratorCompare(final Iterator xs, final Iterator ys, final Order ev) {
      while(true) {
         if (xs.hasNext()) {
            if (ys.hasNext()) {
               Object x = xs.next();
               Object y = ys.next();
               int cmp = ev.compare(x, y);
               if (cmp == 0) {
                  continue;
               }

               return cmp;
            }

            return 1;
         }

         return ys.hasNext() ? -1 : 0;
      }
   }

   public double iteratorPartialCompare(final Iterator xs, final Iterator ys, final PartialOrder ev) {
      while(true) {
         if (xs.hasNext()) {
            if (ys.hasNext()) {
               Object x = xs.next();
               Object y = ys.next();
               double cmp = ev.partialCompare(x, y);
               if (cmp == (double)0.0F) {
                  continue;
               }

               return cmp;
            }

            return (double)1.0F;
         }

         return ys.hasNext() ? (double)-1.0F : (double)0.0F;
      }
   }

   public boolean iteratorEq(final Iterator xs, final Iterator ys, final Eq ev) {
      while(true) {
         if (xs.hasNext()) {
            if (ys.hasNext()) {
               if (!ev.neqv(xs.next(), ys.next())) {
                  continue;
               }

               return false;
            }

            return false;
         }

         return !ys.hasNext();
      }
   }

   public Object combineNIterable(final Builder b, final Iterable x, final int n) {
      for(int i = n; i > 0; --i) {
         b.$plus$plus$eq(x);
      }

      return b.result();
   }

   public Object combineAllIterable(final Builder b, final IterableOnce xs) {
      xs.iterator().foreach((x$1) -> (Builder)b.$plus$plus$eq(x$1));
      return b.result();
   }

   public int product1Hash(final int _1Hash) {
      int h = -889275714;
      h = .MODULE$.mix(h, _1Hash);
      return .MODULE$.finalizeHash(h, 1);
   }

   public int product2Hash(final int _1Hash, final int _2Hash) {
      int h = -889275714;
      h = .MODULE$.mix(h, _1Hash);
      h = .MODULE$.mix(h, _2Hash);
      return .MODULE$.finalizeHash(h, 2);
   }

   private StaticMethods$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
