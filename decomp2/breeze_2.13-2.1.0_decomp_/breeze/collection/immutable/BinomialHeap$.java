package breeze.collection.immutable;

import java.io.Serializable;
import scala.Function1;
import scala.collection.BuildFrom;
import scala.collection.Factory;
import scala.collection.Iterable;
import scala.collection.IterableOnce;
import scala.collection.immutable.List;
import scala.collection.immutable.Nil;
import scala.collection.immutable.Seq;
import scala.collection.mutable.Builder;
import scala.collection.mutable.Growable;
import scala.math.Ordering;
import scala.package.;
import scala.runtime.ModuleSerializationProxy;

public final class BinomialHeap$ implements Serializable {
   public static final BinomialHeap$ MODULE$ = new BinomialHeap$();

   public BinomialHeap empty(final Ordering evidence$1) {
      return new BinomialHeap(evidence$1) {
         private final Nil trees;

         public Nil trees() {
            return this.trees;
         }

         public {
            this.trees = .MODULE$.Nil();
         }
      };
   }

   public BinomialHeap breeze$collection$immutable$BinomialHeap$$mkHeap(final List ns, final int sz, final Ordering evidence$2) {
      return new BinomialHeap(evidence$2, ns, sz) {
         private final List trees;
         private final int size;

         public List trees() {
            return this.trees;
         }

         public int size() {
            return this.size;
         }

         public {
            this.trees = ns$1;
            this.size = sz$1;
         }
      };
   }

   public BinomialHeap apply(final Seq t, final Ordering evidence$3) {
      return this.empty(evidence$3).$plus$plus((IterableOnce)t);
   }

   public BuildFrom canBuildFrom(final Ordering evidence$4) {
      return new BuildFrom(evidence$4) {
         public final Ordering evidence$4$1;

         /** @deprecated */
         public Builder apply(final Object from) {
            return BuildFrom.apply$(this, from);
         }

         public Factory toFactory(final Object from) {
            return BuildFrom.toFactory$(this, from);
         }

         public Builder newBuilder(final BinomialHeap heap) {
            return new Builder() {
               private BinomialHeap heap;
               // $FF: synthetic field
               private final <undefinedtype> $outer;

               public void sizeHint(final int size) {
                  Builder.sizeHint$(this, size);
               }

               public final void sizeHint(final IterableOnce coll, final int delta) {
                  Builder.sizeHint$(this, coll, delta);
               }

               public final int sizeHint$default$2() {
                  return Builder.sizeHint$default$2$(this);
               }

               public final void sizeHintBounded(final int size, final Iterable boundingColl) {
                  Builder.sizeHintBounded$(this, size, boundingColl);
               }

               public Builder mapResult(final Function1 f) {
                  return Builder.mapResult$(this, f);
               }

               public final Growable $plus$eq(final Object elem) {
                  return Growable.$plus$eq$(this, elem);
               }

               /** @deprecated */
               public final Growable $plus$eq(final Object elem1, final Object elem2, final Seq elems) {
                  return Growable.$plus$eq$(this, elem1, elem2, elems);
               }

               public Growable addAll(final IterableOnce xs) {
                  return Growable.addAll$(this, xs);
               }

               public final Growable $plus$plus$eq(final IterableOnce xs) {
                  return Growable.$plus$plus$eq$(this, xs);
               }

               public int knownSize() {
                  return Growable.knownSize$(this);
               }

               private BinomialHeap heap() {
                  return this.heap;
               }

               private void heap_$eq(final BinomialHeap x$1) {
                  this.heap = x$1;
               }

               public BinomialHeap result() {
                  return this.heap();
               }

               public void clear() {
                  this.heap_$eq(BinomialHeap$.MODULE$.empty(this.$outer.evidence$4$1));
               }

               public <undefinedtype> addOne(final Object elem) {
                  this.heap_$eq(this.heap().$plus(elem));
                  return this;
               }

               public {
                  if (<VAR_NAMELESS_ENCLOSURE> == null) {
                     throw null;
                  } else {
                     this.$outer = <VAR_NAMELESS_ENCLOSURE>;
                     Growable.$init$(this);
                     Builder.$init$(this);
                     this.heap = BinomialHeap$.MODULE$.empty(evidence$4$1);
                  }
               }
            };
         }

         public BinomialHeap fromSpecific(final BinomialHeap heap, final IterableOnce it) {
            return BinomialHeap$.MODULE$.empty(this.evidence$4$1).$plus$plus(it);
         }

         public {
            this.evidence$4$1 = evidence$4$1;
            BuildFrom.$init$(this);
         }
      };
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(BinomialHeap$.class);
   }

   private BinomialHeap$() {
   }
}
