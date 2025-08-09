package scala.collection.mutable;

import scala.Function0;
import scala.Function1;
import scala.collection.EvidenceIterableFactory;
import scala.collection.Factory;
import scala.collection.IterableOnce;
import scala.collection.SortedIterableFactory;
import scala.math.Ordering;
import scala.runtime.ModuleSerializationProxy;

public final class PriorityQueue$ implements SortedIterableFactory {
   public static final PriorityQueue$ MODULE$ = new PriorityQueue$();
   private static final long serialVersionUID = 3L;

   static {
      PriorityQueue$ var10000 = MODULE$;
   }

   public Object apply(final scala.collection.immutable.Seq xs, final Object evidence$7) {
      return EvidenceIterableFactory.apply$(this, xs, evidence$7);
   }

   public Object fill(final int n, final Function0 elem, final Object evidence$8) {
      return EvidenceIterableFactory.fill$(this, n, elem, evidence$8);
   }

   public Object tabulate(final int n, final Function1 f, final Object evidence$9) {
      return EvidenceIterableFactory.tabulate$(this, n, f, evidence$9);
   }

   public Object iterate(final Object start, final int len, final Function1 f, final Object evidence$10) {
      return EvidenceIterableFactory.iterate$(this, start, len, f, evidence$10);
   }

   public Object unfold(final Object init, final Function1 f, final Object evidence$11) {
      return EvidenceIterableFactory.unfold$(this, init, f, evidence$11);
   }

   public Factory evidenceIterableFactory(final Object evidence$13) {
      return EvidenceIterableFactory.evidenceIterableFactory$(this, evidence$13);
   }

   public Builder newBuilder(final Ordering evidence$1) {
      return new Builder(evidence$1) {
         private final PriorityQueue pq;

         public void sizeHint(final int size) {
            Builder.sizeHint$(this, size);
         }

         public final void sizeHint(final IterableOnce coll, final int delta) {
            Builder.sizeHint$(this, coll, delta);
         }

         public final int sizeHint$default$2() {
            return Builder.sizeHint$default$2$(this);
         }

         public final void sizeHintBounded(final int size, final scala.collection.Iterable boundingColl) {
            Builder.sizeHintBounded$(this, size, boundingColl);
         }

         public Builder mapResult(final Function1 f) {
            return Builder.mapResult$(this, f);
         }

         public final Growable $plus$eq(final Object elem) {
            return Growable.$plus$eq$(this, elem);
         }

         /** @deprecated */
         public final Growable $plus$eq(final Object elem1, final Object elem2, final scala.collection.immutable.Seq elems) {
            return Growable.$plus$eq$(this, elem1, elem2, elems);
         }

         public Growable addAll(final IterableOnce elems) {
            return Growable.addAll$(this, elems);
         }

         public final Growable $plus$plus$eq(final IterableOnce elems) {
            return Growable.$plus$plus$eq$(this, elems);
         }

         public int knownSize() {
            return Growable.knownSize$(this);
         }

         private PriorityQueue pq() {
            return this.pq;
         }

         public <undefinedtype> addOne(final Object elem) {
            this.pq().scala$collection$mutable$PriorityQueue$$unsafeAdd(elem);
            return this;
         }

         public PriorityQueue result() {
            this.pq().scala$collection$mutable$PriorityQueue$$heapify(1);
            return this.pq();
         }

         public void clear() {
            this.pq().clear();
         }

         public {
            this.pq = new PriorityQueue(evidence$1$1);
         }
      };
   }

   public PriorityQueue empty(final Ordering evidence$2) {
      return new PriorityQueue(evidence$2);
   }

   public PriorityQueue from(final IterableOnce it, final Ordering evidence$3) {
      Builder b = new Builder(evidence$3) {
         private final PriorityQueue pq;

         public void sizeHint(final int size) {
            Builder.sizeHint$(this, size);
         }

         public final void sizeHint(final IterableOnce coll, final int delta) {
            Builder.sizeHint$(this, coll, delta);
         }

         public final int sizeHint$default$2() {
            return Builder.sizeHint$default$2$(this);
         }

         public final void sizeHintBounded(final int size, final scala.collection.Iterable boundingColl) {
            Builder.sizeHintBounded$(this, size, boundingColl);
         }

         public Builder mapResult(final Function1 f) {
            return Builder.mapResult$(this, f);
         }

         public final Growable $plus$eq(final Object elem) {
            return Growable.$plus$eq$(this, elem);
         }

         /** @deprecated */
         public final Growable $plus$eq(final Object elem1, final Object elem2, final scala.collection.immutable.Seq elems) {
            return Growable.$plus$eq$(this, elem1, elem2, elems);
         }

         public Growable addAll(final IterableOnce elems) {
            return Growable.addAll$(this, elems);
         }

         public final Growable $plus$plus$eq(final IterableOnce elems) {
            return Growable.$plus$plus$eq$(this, elems);
         }

         public int knownSize() {
            return Growable.knownSize$(this);
         }

         private PriorityQueue pq() {
            return this.pq;
         }

         public <undefinedtype> addOne(final Object elem) {
            this.pq().scala$collection$mutable$PriorityQueue$$unsafeAdd(elem);
            return this;
         }

         public PriorityQueue result() {
            this.pq().scala$collection$mutable$PriorityQueue$$heapify(1);
            return this.pq();
         }

         public void clear() {
            this.pq().clear();
         }

         public {
            this.pq = new PriorityQueue(evidence$1$1);
         }
      };
      Growable.addAll$(b, it);
      return ((<undefinedtype>)b).result();
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(PriorityQueue$.class);
   }

   private PriorityQueue$() {
   }
}
