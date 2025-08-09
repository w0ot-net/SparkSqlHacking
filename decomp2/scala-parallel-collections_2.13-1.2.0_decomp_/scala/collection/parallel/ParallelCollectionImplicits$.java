package scala.collection.parallel;

import scala.Function0;
import scala.Function1;
import scala.collection.IterableOnce;
import scala.collection.Parallel;

public final class ParallelCollectionImplicits$ {
   public static final ParallelCollectionImplicits$ MODULE$ = new ParallelCollectionImplicits$();

   public TraversableOps traversable2ops(final IterableOnce t) {
      return new TraversableOps(t) {
         private final IterableOnce t$1;

         public boolean isParallel() {
            return this.t$1 instanceof Parallel;
         }

         public boolean isParIterable() {
            return this.t$1 instanceof ParIterable;
         }

         public ParIterable asParIterable() {
            return (ParIterable)this.t$1;
         }

         public boolean isParSeq() {
            return this.t$1 instanceof ParSeq;
         }

         public ParSeq asParSeq() {
            return (ParSeq)this.t$1;
         }

         public TraversableOps.Otherwise ifParSeq(final Function1 isbody) {
            return new TraversableOps.Otherwise(isbody) {
               // $FF: synthetic field
               private final <undefinedtype> $outer;
               private final Function1 isbody$1;

               public Object otherwise(final Function0 notbody) {
                  return this.$outer.isParallel() ? this.isbody$1.apply(this.$outer.asParSeq()) : notbody.apply();
               }

               public {
                  if (<VAR_NAMELESS_ENCLOSURE> == null) {
                     throw null;
                  } else {
                     this.$outer = <VAR_NAMELESS_ENCLOSURE>;
                     this.isbody$1 = isbody$1;
                  }
               }
            };
         }

         public {
            this.t$1 = t$1;
            TraversableOps.$init$(this);
         }
      };
   }

   private ParallelCollectionImplicits$() {
   }
}
