package scala.collection.mutable;

import scala.Function0;
import scala.Function1;
import scala.collection.EvidenceIterableFactory;
import scala.collection.Factory;
import scala.collection.IterableOnce;
import scala.collection.Iterator;
import scala.collection.SortedIterableFactory;
import scala.collection.immutable.Range;
import scala.math.Ordering;
import scala.runtime.ModuleSerializationProxy;

public final class TreeSet$ implements SortedIterableFactory {
   public static final TreeSet$ MODULE$ = new TreeSet$();
   private static final long serialVersionUID = 3L;

   static {
      TreeSet$ var10000 = MODULE$;
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

   public TreeSet empty(final Ordering evidence$1) {
      return new TreeSet(evidence$1);
   }

   public TreeSet from(final IterableOnce it, final Ordering ordering) {
      if (it instanceof TreeSet) {
         TreeSet var3 = (TreeSet)it;
         Ordering var4 = var3.ordering();
         if (ordering == null) {
            if (var4 == null) {
               return new TreeSet(var3.scala$collection$mutable$TreeSet$$tree().treeCopy(), ordering);
            }
         } else if (ordering.equals(var4)) {
            return new TreeSet(var3.scala$collection$mutable$TreeSet$$tree().treeCopy(), ordering);
         }
      }

      if (it instanceof scala.collection.SortedSet) {
         scala.collection.SortedSet var5 = (scala.collection.SortedSet)it;
         Ordering var6 = var5.ordering();
         if (ordering == null) {
            if (var6 == null) {
               return new TreeSet(RedBlackTree$.MODULE$.fromOrderedKeys(var5.iterator(), var5.size()), ordering);
            }
         } else if (ordering.equals(var6)) {
            return new TreeSet(RedBlackTree$.MODULE$.fromOrderedKeys(var5.iterator(), var5.size()), ordering);
         }
      }

      if (it instanceof Range) {
         Range var7 = (Range)it;
         if (ordering == Ordering.Int$.MODULE$ || ordering == Ordering.Int$.MODULE$.scala$math$Ordering$CachedReverse$$_reverse()) {
            Iterator it = ordering == Ordering.Int$.MODULE$ == var7.step() > 0 ? var7.iterator() : scala.collection.IndexedSeqOps.reverseIterator$(var7);
            return new TreeSet(RedBlackTree$.MODULE$.fromOrderedKeys(it, var7.length()), ordering);
         }
      }

      RedBlackTree.Tree t = RedBlackTree.Tree$.MODULE$.empty();
      Iterator i = it.iterator();

      while(i.hasNext()) {
         RedBlackTree$.MODULE$.insert(t, i.next(), (Object)null, ordering);
      }

      return new TreeSet(t, ordering);
   }

   public Builder newBuilder(final Ordering ordering) {
      return new ReusableBuilder(ordering) {
         private RedBlackTree.Tree tree;
         private final Ordering ordering$1;

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

         public <undefinedtype> addOne(final Object elem) {
            RedBlackTree$.MODULE$.insert(this.tree, elem, (Object)null, this.ordering$1);
            return this;
         }

         public TreeSet result() {
            return new TreeSet(this.tree, this.ordering$1);
         }

         public void clear() {
            this.tree = RedBlackTree.Tree$.MODULE$.empty();
         }

         public {
            this.ordering$1 = ordering$1;
            this.tree = RedBlackTree.Tree$.MODULE$.empty();
         }
      };
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(TreeSet$.class);
   }

   private TreeSet$() {
   }
}
