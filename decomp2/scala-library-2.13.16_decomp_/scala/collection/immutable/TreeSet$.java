package scala.collection.immutable;

import scala.Function0;
import scala.Function1;
import scala.collection.EvidenceIterableFactory;
import scala.collection.Factory;
import scala.collection.IterableOnce;
import scala.collection.Iterator;
import scala.collection.SortedIterableFactory;
import scala.collection.mutable.ReusableBuilder;
import scala.math.Ordering;
import scala.runtime.ModuleSerializationProxy;

public final class TreeSet$ implements SortedIterableFactory {
   public static final TreeSet$ MODULE$ = new TreeSet$();
   private static final long serialVersionUID = 3L;

   static {
      TreeSet$ var10000 = MODULE$;
   }

   public Object apply(final Seq xs, final Object evidence$7) {
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
               return var3;
            }
         } else if (ordering.equals(var4)) {
            return var3;
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
         if (ordering == Ordering.Int$.MODULE$ || Ordering.CachedReverse.isReverseOf$(Ordering.Int$.MODULE$, ordering)) {
            Iterator it = ordering == Ordering.Int$.MODULE$ == var7.step() > 0 ? var7.iterator() : scala.collection.IndexedSeqOps.reverseIterator$(var7);
            RedBlackTree.Tree tree = RedBlackTree$.MODULE$.fromOrderedKeys(it, var7.length());
            return new TreeSet(tree, ordering);
         }
      }

      RedBlackTree.Tree t = null;

      for(Iterator i = it.iterator(); i.hasNext(); t = RedBlackTree$.MODULE$.update(t, i.next(), (Object)null, false, ordering)) {
      }

      return new TreeSet(t, ordering);
   }

   public ReusableBuilder newBuilder(final Ordering ordering) {
      return new TreeSet.TreeSetBuilder(ordering);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(TreeSet$.class);
   }

   private TreeSet$() {
   }
}
