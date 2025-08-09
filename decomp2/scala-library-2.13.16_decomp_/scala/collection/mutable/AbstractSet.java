package scala.collection.mutable;

import scala.Function1;
import scala.collection.IterableFactory;
import scala.collection.IterableOnce;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\t2QAA\u0002\u0002\u0002)AQa\b\u0001\u0005\u0002\u0001\u00121\"\u00112tiJ\f7\r^*fi*\u0011A!B\u0001\b[V$\u0018M\u00197f\u0015\t1q!\u0001\u0006d_2dWm\u0019;j_:T\u0011\u0001C\u0001\u0006g\u000e\fG.Y\u0002\u0001+\tY\u0011cE\u0002\u0001\u0019m\u00012!\u0004\b\u0010\u001b\u0005)\u0011B\u0001\u0002\u0006!\t\u0001\u0012\u0003\u0004\u0001\u0005\u000bI\u0001!\u0019A\n\u0003\u0003\u0005\u000b\"\u0001\u0006\r\u0011\u0005U1R\"A\u0004\n\u0005]9!a\u0002(pi\"Lgn\u001a\t\u0003+eI!AG\u0004\u0003\u0007\u0005s\u0017\u0010E\u0002\u001d;=i\u0011aA\u0005\u0003=\r\u00111aU3u\u0003\u0019a\u0014N\\5u}Q\t\u0011\u0005E\u0002\u001d\u0001=\u0001"
)
public abstract class AbstractSet extends scala.collection.AbstractSet implements Set {
   public IterableFactory iterableFactory() {
      return Set.iterableFactory$(this);
   }

   public SetOps result() {
      return SetOps.result$(this);
   }

   public boolean add(final Object elem) {
      return SetOps.add$(this, elem);
   }

   public void update(final Object elem, final boolean included) {
      SetOps.update$(this, elem, included);
   }

   public boolean remove(final Object elem) {
      return SetOps.remove$(this, elem);
   }

   public SetOps diff(final scala.collection.Set that) {
      return SetOps.diff$(this, that);
   }

   /** @deprecated */
   public final void retain(final Function1 p) {
      SetOps.retain$(this, p);
   }

   public SetOps filterInPlace(final Function1 p) {
      return SetOps.filterInPlace$(this, p);
   }

   public SetOps clone() {
      return SetOps.clone$(this);
   }

   public int knownSize() {
      return SetOps.knownSize$(this);
   }

   public final Shrinkable $minus$eq(final Object elem) {
      return Shrinkable.$minus$eq$(this, elem);
   }

   /** @deprecated */
   public Shrinkable $minus$eq(final Object elem1, final Object elem2, final scala.collection.immutable.Seq elems) {
      return Shrinkable.$minus$eq$(this, elem1, elem2, elems);
   }

   public Shrinkable subtractAll(final IterableOnce xs) {
      return Shrinkable.subtractAll$(this, xs);
   }

   public final Shrinkable $minus$minus$eq(final IterableOnce xs) {
      return Shrinkable.$minus$minus$eq$(this, xs);
   }

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

   // $FF: synthetic method
   public Object scala$collection$mutable$Cloneable$$super$clone() {
      return super.clone();
   }
}
