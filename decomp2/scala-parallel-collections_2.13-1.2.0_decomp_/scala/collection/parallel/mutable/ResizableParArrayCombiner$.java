package scala.collection.parallel.mutable;

import scala.Function1;
import scala.collection.Iterable;
import scala.collection.IterableOnce;
import scala.collection.immutable.Seq;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.Builder;
import scala.collection.mutable.Growable;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.TaskSupport;
import scala.runtime.Statics;

public final class ResizableParArrayCombiner$ {
   public static final ResizableParArrayCombiner$ MODULE$ = new ResizableParArrayCombiner$();

   public ResizableParArrayCombiner apply(final ArrayBuffer c) {
      class ResizableParArrayCombinerC$1 implements ResizableParArrayCombiner {
         private final ArrayBuffer chain;
         private ExposedArrayBuffer lastbuff;
         private transient volatile TaskSupport _combinerTaskSupport;

         public void sizeHint(final int sz) {
            ResizableParArrayCombiner.sizeHint$(this, sz);
         }

         public final ResizableParArrayCombiner newLazyCombiner(final ArrayBuffer c) {
            return ResizableParArrayCombiner.newLazyCombiner$(this, c);
         }

         public ParArray allocateAndCopy() {
            return ResizableParArrayCombiner.allocateAndCopy$(this);
         }

         public String toString() {
            return ResizableParArrayCombiner.toString$(this);
         }

         public LazyCombiner addOne(final Object elem) {
            return LazyCombiner.addOne$(this, elem);
         }

         public Object result() {
            return LazyCombiner.result$(this);
         }

         public void clear() {
            LazyCombiner.clear$(this);
         }

         public Combiner combine(final Combiner other) {
            return LazyCombiner.combine$(this, other);
         }

         public int size() {
            return LazyCombiner.size$(this);
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

         public ExposedArrayBuffer lastbuff() {
            return this.lastbuff;
         }

         public void scala$collection$parallel$mutable$LazyCombiner$_setter_$lastbuff_$eq(final ExposedArrayBuffer x$1) {
            this.lastbuff = x$1;
         }

         public TaskSupport _combinerTaskSupport() {
            return this._combinerTaskSupport;
         }

         public void _combinerTaskSupport_$eq(final TaskSupport x$1) {
            this._combinerTaskSupport = x$1;
         }

         public ArrayBuffer chain() {
            return this.chain;
         }

         public ResizableParArrayCombinerC$1(final ArrayBuffer chain) {
            this.chain = chain;
            Growable.$init$(this);
            Builder.$init$(this);
            Combiner.$init$(this);
            LazyCombiner.$init$(this);
            ResizableParArrayCombiner.$init$(this);
            Statics.releaseFence();
         }
      }

      return new ResizableParArrayCombinerC$1(c);
   }

   public ResizableParArrayCombiner apply() {
      return this.apply((ArrayBuffer)(new ArrayBuffer()).$plus$eq(new ExposedArrayBuffer()));
   }

   private ResizableParArrayCombiner$() {
   }
}
