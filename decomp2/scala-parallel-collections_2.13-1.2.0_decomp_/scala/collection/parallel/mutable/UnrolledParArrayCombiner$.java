package scala.collection.parallel.mutable;

import scala.Function1;
import scala.collection.Iterable;
import scala.collection.IterableOnce;
import scala.collection.immutable.Seq;
import scala.collection.mutable.Builder;
import scala.collection.mutable.DoublingUnrolledBuffer;
import scala.collection.mutable.Growable;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.TaskSupport;
import scala.runtime.Statics;

public final class UnrolledParArrayCombiner$ {
   public static final UnrolledParArrayCombiner$ MODULE$ = new UnrolledParArrayCombiner$();

   public UnrolledParArrayCombiner apply() {
      return new UnrolledParArrayCombiner() {
         private DoublingUnrolledBuffer buff;
         private transient volatile TaskSupport _combinerTaskSupport;

         public UnrolledParArrayCombiner addOne(final Object elem) {
            return UnrolledParArrayCombiner.addOne$(this, elem);
         }

         public ParArray result() {
            return UnrolledParArrayCombiner.result$(this);
         }

         public void clear() {
            UnrolledParArrayCombiner.clear$(this);
         }

         public void sizeHint(final int sz) {
            UnrolledParArrayCombiner.sizeHint$(this, sz);
         }

         public Combiner combine(final Combiner other) {
            return UnrolledParArrayCombiner.combine$(this, other);
         }

         public int size() {
            return UnrolledParArrayCombiner.size$(this);
         }

         public TaskSupport combinerTaskSupport() {
            return Combiner.combinerTaskSupport$(this);
         }

         public void combinerTaskSupport_$eq(final TaskSupport cts) {
            Combiner.combinerTaskSupport_$eq$(this, cts);
         }

         public boolean canBeShared() {
            return Combiner.canBeShared$(this);
         }

         public Object resultWithTaskSupport() {
            return Combiner.resultWithTaskSupport$(this);
         }

         public Object fromSequential(final IterableOnce seq) {
            return Combiner.fromSequential$(this, seq);
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

         public Growable addAll(final IterableOnce elems) {
            return Growable.addAll$(this, elems);
         }

         public final Growable $plus$plus$eq(final IterableOnce elems) {
            return Growable.$plus$plus$eq$(this, elems);
         }

         public int knownSize() {
            return Growable.knownSize$(this);
         }

         public DoublingUnrolledBuffer buff() {
            return this.buff;
         }

         public void scala$collection$parallel$mutable$UnrolledParArrayCombiner$_setter_$buff_$eq(final DoublingUnrolledBuffer x$1) {
            this.buff = x$1;
         }

         public TaskSupport _combinerTaskSupport() {
            return this._combinerTaskSupport;
         }

         public void _combinerTaskSupport_$eq(final TaskSupport x$1) {
            this._combinerTaskSupport = x$1;
         }

         public {
            Growable.$init$(this);
            Builder.$init$(this);
            Combiner.$init$(this);
            UnrolledParArrayCombiner.$init$(this);
            Statics.releaseFence();
         }
      };
   }

   private UnrolledParArrayCombiner$() {
   }
}
