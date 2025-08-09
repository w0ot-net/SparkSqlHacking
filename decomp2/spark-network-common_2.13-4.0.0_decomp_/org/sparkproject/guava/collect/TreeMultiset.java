package org.sparkproject.guava.collect;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.ObjIntConsumer;
import javax.annotation.CheckForNull;
import org.sparkproject.guava.annotations.GwtCompatible;
import org.sparkproject.guava.annotations.GwtIncompatible;
import org.sparkproject.guava.annotations.J2ktIncompatible;
import org.sparkproject.guava.base.MoreObjects;
import org.sparkproject.guava.base.Preconditions;
import org.sparkproject.guava.primitives.Ints;

@ElementTypesAreNonnullByDefault
@GwtCompatible(
   emulated = true
)
public final class TreeMultiset extends AbstractSortedMultiset implements Serializable {
   private final transient Reference rootReference;
   private final transient GeneralRange range;
   private final transient AvlNode header;
   @GwtIncompatible
   @J2ktIncompatible
   private static final long serialVersionUID = 1L;

   public static TreeMultiset create() {
      return new TreeMultiset(Ordering.natural());
   }

   public static TreeMultiset create(@CheckForNull Comparator comparator) {
      return comparator == null ? new TreeMultiset(Ordering.natural()) : new TreeMultiset(comparator);
   }

   public static TreeMultiset create(Iterable elements) {
      TreeMultiset<E> multiset = create();
      Iterables.addAll(multiset, elements);
      return multiset;
   }

   TreeMultiset(Reference rootReference, GeneralRange range, AvlNode endLink) {
      super(range.comparator());
      this.rootReference = rootReference;
      this.range = range;
      this.header = endLink;
   }

   TreeMultiset(Comparator comparator) {
      super(comparator);
      this.range = GeneralRange.all(comparator);
      this.header = new AvlNode();
      successor(this.header, this.header);
      this.rootReference = new Reference();
   }

   private long aggregateForEntries(Aggregate aggr) {
      AvlNode<E> root = (AvlNode)this.rootReference.get();
      long total = aggr.treeAggregate(root);
      if (this.range.hasLowerBound()) {
         total -= this.aggregateBelowRange(aggr, root);
      }

      if (this.range.hasUpperBound()) {
         total -= this.aggregateAboveRange(aggr, root);
      }

      return total;
   }

   private long aggregateBelowRange(Aggregate aggr, @CheckForNull AvlNode node) {
      if (node == null) {
         return 0L;
      } else {
         int cmp = this.comparator().compare(NullnessCasts.uncheckedCastNullableTToT(this.range.getLowerEndpoint()), node.getElement());
         if (cmp < 0) {
            return this.aggregateBelowRange(aggr, node.left);
         } else if (cmp == 0) {
            switch (this.range.getLowerBoundType()) {
               case OPEN:
                  return (long)aggr.nodeAggregate(node) + aggr.treeAggregate(node.left);
               case CLOSED:
                  return aggr.treeAggregate(node.left);
               default:
                  throw new AssertionError();
            }
         } else {
            return aggr.treeAggregate(node.left) + (long)aggr.nodeAggregate(node) + this.aggregateBelowRange(aggr, node.right);
         }
      }
   }

   private long aggregateAboveRange(Aggregate aggr, @CheckForNull AvlNode node) {
      if (node == null) {
         return 0L;
      } else {
         int cmp = this.comparator().compare(NullnessCasts.uncheckedCastNullableTToT(this.range.getUpperEndpoint()), node.getElement());
         if (cmp > 0) {
            return this.aggregateAboveRange(aggr, node.right);
         } else if (cmp == 0) {
            switch (this.range.getUpperBoundType()) {
               case OPEN:
                  return (long)aggr.nodeAggregate(node) + aggr.treeAggregate(node.right);
               case CLOSED:
                  return aggr.treeAggregate(node.right);
               default:
                  throw new AssertionError();
            }
         } else {
            return aggr.treeAggregate(node.right) + (long)aggr.nodeAggregate(node) + this.aggregateAboveRange(aggr, node.left);
         }
      }
   }

   public int size() {
      return Ints.saturatedCast(this.aggregateForEntries(TreeMultiset.Aggregate.SIZE));
   }

   int distinctElements() {
      return Ints.saturatedCast(this.aggregateForEntries(TreeMultiset.Aggregate.DISTINCT));
   }

   static int distinctElements(@CheckForNull AvlNode node) {
      return node == null ? 0 : node.distinctElements;
   }

   public int count(@CheckForNull Object element) {
      try {
         AvlNode<E> root = (AvlNode)this.rootReference.get();
         return this.range.contains(element) && root != null ? root.count(this.comparator(), element) : 0;
      } catch (NullPointerException | ClassCastException var4) {
         return 0;
      }
   }

   @CanIgnoreReturnValue
   public int add(@ParametricNullness Object element, int occurrences) {
      CollectPreconditions.checkNonnegative(occurrences, "occurrences");
      if (occurrences == 0) {
         return this.count(element);
      } else {
         Preconditions.checkArgument(this.range.contains(element));
         AvlNode<E> root = (AvlNode)this.rootReference.get();
         if (root == null) {
            int unused = this.comparator().compare(element, element);
            AvlNode<E> newRoot = new AvlNode(element, occurrences);
            successor(this.header, newRoot, this.header);
            this.rootReference.checkAndSet(root, newRoot);
            return 0;
         } else {
            int[] result = new int[1];
            AvlNode<E> newRoot = root.add(this.comparator(), element, occurrences, result);
            this.rootReference.checkAndSet(root, newRoot);
            return result[0];
         }
      }
   }

   @CanIgnoreReturnValue
   public int remove(@CheckForNull Object element, int occurrences) {
      CollectPreconditions.checkNonnegative(occurrences, "occurrences");
      if (occurrences == 0) {
         return this.count(element);
      } else {
         AvlNode<E> root = (AvlNode)this.rootReference.get();
         int[] result = new int[1];

         AvlNode<E> newRoot;
         try {
            if (!this.range.contains(element) || root == null) {
               return 0;
            }

            newRoot = root.remove(this.comparator(), element, occurrences, result);
         } catch (NullPointerException | ClassCastException var7) {
            return 0;
         }

         this.rootReference.checkAndSet(root, newRoot);
         return result[0];
      }
   }

   @CanIgnoreReturnValue
   public int setCount(@ParametricNullness Object element, int count) {
      CollectPreconditions.checkNonnegative(count, "count");
      if (!this.range.contains(element)) {
         Preconditions.checkArgument(count == 0);
         return 0;
      } else {
         AvlNode<E> root = (AvlNode)this.rootReference.get();
         if (root == null) {
            if (count > 0) {
               this.add(element, count);
            }

            return 0;
         } else {
            int[] result = new int[1];
            AvlNode<E> newRoot = root.setCount(this.comparator(), element, count, result);
            this.rootReference.checkAndSet(root, newRoot);
            return result[0];
         }
      }
   }

   @CanIgnoreReturnValue
   public boolean setCount(@ParametricNullness Object element, int oldCount, int newCount) {
      CollectPreconditions.checkNonnegative(newCount, "newCount");
      CollectPreconditions.checkNonnegative(oldCount, "oldCount");
      Preconditions.checkArgument(this.range.contains(element));
      AvlNode<E> root = (AvlNode)this.rootReference.get();
      if (root == null) {
         if (oldCount == 0) {
            if (newCount > 0) {
               this.add(element, newCount);
            }

            return true;
         } else {
            return false;
         }
      } else {
         int[] result = new int[1];
         AvlNode<E> newRoot = root.setCount(this.comparator(), element, oldCount, newCount, result);
         this.rootReference.checkAndSet(root, newRoot);
         return result[0] == oldCount;
      }
   }

   public void clear() {
      if (!this.range.hasLowerBound() && !this.range.hasUpperBound()) {
         AvlNode<E> next;
         for(AvlNode<E> current = this.header.succ(); current != this.header; current = next) {
            next = current.succ();
            current.elemCount = 0;
            current.left = null;
            current.right = null;
            current.pred = null;
            current.succ = null;
         }

         successor(this.header, this.header);
         this.rootReference.clear();
      } else {
         Iterators.clear(this.entryIterator());
      }

   }

   private Multiset.Entry wrapEntry(final AvlNode baseEntry) {
      return new Multisets.AbstractEntry() {
         @ParametricNullness
         public Object getElement() {
            return baseEntry.getElement();
         }

         public int getCount() {
            int result = baseEntry.getCount();
            return result == 0 ? TreeMultiset.this.count(this.getElement()) : result;
         }
      };
   }

   @CheckForNull
   private AvlNode firstNode() {
      AvlNode<E> root = (AvlNode)this.rootReference.get();
      if (root == null) {
         return null;
      } else {
         AvlNode<E> node;
         if (this.range.hasLowerBound()) {
            E endpoint = (E)NullnessCasts.uncheckedCastNullableTToT(this.range.getLowerEndpoint());
            node = root.ceiling(this.comparator(), endpoint);
            if (node == null) {
               return null;
            }

            if (this.range.getLowerBoundType() == BoundType.OPEN && this.comparator().compare(endpoint, node.getElement()) == 0) {
               node = node.succ();
            }
         } else {
            node = this.header.succ();
         }

         return node != this.header && this.range.contains(node.getElement()) ? node : null;
      }
   }

   @CheckForNull
   private AvlNode lastNode() {
      AvlNode<E> root = (AvlNode)this.rootReference.get();
      if (root == null) {
         return null;
      } else {
         AvlNode<E> node;
         if (this.range.hasUpperBound()) {
            E endpoint = (E)NullnessCasts.uncheckedCastNullableTToT(this.range.getUpperEndpoint());
            node = root.floor(this.comparator(), endpoint);
            if (node == null) {
               return null;
            }

            if (this.range.getUpperBoundType() == BoundType.OPEN && this.comparator().compare(endpoint, node.getElement()) == 0) {
               node = node.pred();
            }
         } else {
            node = this.header.pred();
         }

         return node != this.header && this.range.contains(node.getElement()) ? node : null;
      }
   }

   Iterator elementIterator() {
      return Multisets.elementIterator(this.entryIterator());
   }

   Iterator entryIterator() {
      return new Iterator() {
         @CheckForNull
         AvlNode current = TreeMultiset.this.firstNode();
         @CheckForNull
         Multiset.Entry prevEntry;

         public boolean hasNext() {
            if (this.current == null) {
               return false;
            } else if (TreeMultiset.this.range.tooHigh(this.current.getElement())) {
               this.current = null;
               return false;
            } else {
               return true;
            }
         }

         public Multiset.Entry next() {
            if (!this.hasNext()) {
               throw new NoSuchElementException();
            } else {
               Multiset.Entry<E> result = TreeMultiset.this.wrapEntry((AvlNode)Objects.requireNonNull(this.current));
               this.prevEntry = result;
               if (this.current.succ() == TreeMultiset.this.header) {
                  this.current = null;
               } else {
                  this.current = this.current.succ();
               }

               return result;
            }
         }

         public void remove() {
            Preconditions.checkState(this.prevEntry != null, "no calls to next() since the last call to remove()");
            TreeMultiset.this.setCount(this.prevEntry.getElement(), 0);
            this.prevEntry = null;
         }
      };
   }

   Iterator descendingEntryIterator() {
      return new Iterator() {
         @CheckForNull
         AvlNode current = TreeMultiset.this.lastNode();
         @CheckForNull
         Multiset.Entry prevEntry = null;

         public boolean hasNext() {
            if (this.current == null) {
               return false;
            } else if (TreeMultiset.this.range.tooLow(this.current.getElement())) {
               this.current = null;
               return false;
            } else {
               return true;
            }
         }

         public Multiset.Entry next() {
            if (!this.hasNext()) {
               throw new NoSuchElementException();
            } else {
               Objects.requireNonNull(this.current);
               Multiset.Entry<E> result = TreeMultiset.this.wrapEntry(this.current);
               this.prevEntry = result;
               if (this.current.pred() == TreeMultiset.this.header) {
                  this.current = null;
               } else {
                  this.current = this.current.pred();
               }

               return result;
            }
         }

         public void remove() {
            Preconditions.checkState(this.prevEntry != null, "no calls to next() since the last call to remove()");
            TreeMultiset.this.setCount(this.prevEntry.getElement(), 0);
            this.prevEntry = null;
         }
      };
   }

   public void forEachEntry(ObjIntConsumer action) {
      Preconditions.checkNotNull(action);

      for(AvlNode<E> node = this.firstNode(); node != this.header && node != null && !this.range.tooHigh(node.getElement()); node = node.succ()) {
         action.accept(node.getElement(), node.getCount());
      }

   }

   public Iterator iterator() {
      return Multisets.iteratorImpl(this);
   }

   public SortedMultiset headMultiset(@ParametricNullness Object upperBound, BoundType boundType) {
      return new TreeMultiset(this.rootReference, this.range.intersect(GeneralRange.upTo(this.comparator(), upperBound, boundType)), this.header);
   }

   public SortedMultiset tailMultiset(@ParametricNullness Object lowerBound, BoundType boundType) {
      return new TreeMultiset(this.rootReference, this.range.intersect(GeneralRange.downTo(this.comparator(), lowerBound, boundType)), this.header);
   }

   private static void successor(AvlNode a, AvlNode b) {
      a.succ = b;
      b.pred = a;
   }

   private static void successor(AvlNode a, AvlNode b, AvlNode c) {
      successor(a, b);
      successor(b, c);
   }

   @J2ktIncompatible
   @GwtIncompatible
   private void writeObject(ObjectOutputStream stream) throws IOException {
      stream.defaultWriteObject();
      stream.writeObject(this.elementSet().comparator());
      Serialization.writeMultiset(this, stream);
   }

   @J2ktIncompatible
   @GwtIncompatible
   private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
      stream.defaultReadObject();
      Comparator<? super E> comparator = (Comparator)Objects.requireNonNull(stream.readObject());
      Serialization.getFieldSetter(AbstractSortedMultiset.class, "comparator").set(this, comparator);
      Serialization.getFieldSetter(TreeMultiset.class, "range").set(this, GeneralRange.all(comparator));
      Serialization.getFieldSetter(TreeMultiset.class, "rootReference").set(this, new Reference());
      AvlNode<E> header = new AvlNode();
      Serialization.getFieldSetter(TreeMultiset.class, "header").set(this, header);
      successor(header, header);
      Serialization.populateMultiset(this, stream);
   }

   private static enum Aggregate {
      SIZE {
         int nodeAggregate(AvlNode node) {
            return node.elemCount;
         }

         long treeAggregate(@CheckForNull AvlNode root) {
            return root == null ? 0L : root.totalCount;
         }
      },
      DISTINCT {
         int nodeAggregate(AvlNode node) {
            return 1;
         }

         long treeAggregate(@CheckForNull AvlNode root) {
            return root == null ? 0L : (long)root.distinctElements;
         }
      };

      private Aggregate() {
      }

      abstract int nodeAggregate(AvlNode node);

      abstract long treeAggregate(@CheckForNull AvlNode root);

      // $FF: synthetic method
      private static Aggregate[] $values() {
         return new Aggregate[]{SIZE, DISTINCT};
      }
   }

   private static final class Reference {
      @CheckForNull
      private Object value;

      private Reference() {
      }

      @CheckForNull
      public Object get() {
         return this.value;
      }

      public void checkAndSet(@CheckForNull Object expected, @CheckForNull Object newValue) {
         if (this.value != expected) {
            throw new ConcurrentModificationException();
         } else {
            this.value = newValue;
         }
      }

      void clear() {
         this.value = null;
      }
   }

   private static final class AvlNode {
      @CheckForNull
      private final Object elem;
      private int elemCount;
      private int distinctElements;
      private long totalCount;
      private int height;
      @CheckForNull
      private AvlNode left;
      @CheckForNull
      private AvlNode right;
      @CheckForNull
      private AvlNode pred;
      @CheckForNull
      private AvlNode succ;

      AvlNode(@ParametricNullness Object elem, int elemCount) {
         Preconditions.checkArgument(elemCount > 0);
         this.elem = elem;
         this.elemCount = elemCount;
         this.totalCount = (long)elemCount;
         this.distinctElements = 1;
         this.height = 1;
         this.left = null;
         this.right = null;
      }

      AvlNode() {
         this.elem = null;
         this.elemCount = 1;
      }

      private AvlNode pred() {
         return (AvlNode)Objects.requireNonNull(this.pred);
      }

      private AvlNode succ() {
         return (AvlNode)Objects.requireNonNull(this.succ);
      }

      int count(Comparator comparator, @ParametricNullness Object e) {
         int cmp = comparator.compare(e, this.getElement());
         if (cmp < 0) {
            return this.left == null ? 0 : this.left.count(comparator, e);
         } else if (cmp > 0) {
            return this.right == null ? 0 : this.right.count(comparator, e);
         } else {
            return this.elemCount;
         }
      }

      private AvlNode addRightChild(@ParametricNullness Object e, int count) {
         this.right = new AvlNode(e, count);
         TreeMultiset.successor(this, this.right, this.succ());
         this.height = Math.max(2, this.height);
         ++this.distinctElements;
         this.totalCount += (long)count;
         return this;
      }

      private AvlNode addLeftChild(@ParametricNullness Object e, int count) {
         this.left = new AvlNode(e, count);
         TreeMultiset.successor(this.pred(), this.left, this);
         this.height = Math.max(2, this.height);
         ++this.distinctElements;
         this.totalCount += (long)count;
         return this;
      }

      AvlNode add(Comparator comparator, @ParametricNullness Object e, int count, int[] result) {
         int cmp = comparator.compare(e, this.getElement());
         if (cmp < 0) {
            AvlNode<E> initLeft = this.left;
            if (initLeft == null) {
               result[0] = 0;
               return this.addLeftChild(e, count);
            } else {
               int initHeight = initLeft.height;
               this.left = initLeft.add(comparator, e, count, result);
               if (result[0] == 0) {
                  ++this.distinctElements;
               }

               this.totalCount += (long)count;
               return this.left.height == initHeight ? this : this.rebalance();
            }
         } else if (cmp > 0) {
            AvlNode<E> initRight = this.right;
            if (initRight == null) {
               result[0] = 0;
               return this.addRightChild(e, count);
            } else {
               int initHeight = initRight.height;
               this.right = initRight.add(comparator, e, count, result);
               if (result[0] == 0) {
                  ++this.distinctElements;
               }

               this.totalCount += (long)count;
               return this.right.height == initHeight ? this : this.rebalance();
            }
         } else {
            result[0] = this.elemCount;
            long resultCount = (long)this.elemCount + (long)count;
            Preconditions.checkArgument(resultCount <= 2147483647L);
            this.elemCount += count;
            this.totalCount += (long)count;
            return this;
         }
      }

      @CheckForNull
      AvlNode remove(Comparator comparator, @ParametricNullness Object e, int count, int[] result) {
         int cmp = comparator.compare(e, this.getElement());
         if (cmp < 0) {
            AvlNode<E> initLeft = this.left;
            if (initLeft == null) {
               result[0] = 0;
               return this;
            } else {
               this.left = initLeft.remove(comparator, e, count, result);
               if (result[0] > 0) {
                  if (count >= result[0]) {
                     --this.distinctElements;
                     this.totalCount -= (long)result[0];
                  } else {
                     this.totalCount -= (long)count;
                  }
               }

               return result[0] == 0 ? this : this.rebalance();
            }
         } else if (cmp > 0) {
            AvlNode<E> initRight = this.right;
            if (initRight == null) {
               result[0] = 0;
               return this;
            } else {
               this.right = initRight.remove(comparator, e, count, result);
               if (result[0] > 0) {
                  if (count >= result[0]) {
                     --this.distinctElements;
                     this.totalCount -= (long)result[0];
                  } else {
                     this.totalCount -= (long)count;
                  }
               }

               return this.rebalance();
            }
         } else {
            result[0] = this.elemCount;
            if (count >= this.elemCount) {
               return this.deleteMe();
            } else {
               this.elemCount -= count;
               this.totalCount -= (long)count;
               return this;
            }
         }
      }

      @CheckForNull
      AvlNode setCount(Comparator comparator, @ParametricNullness Object e, int count, int[] result) {
         int cmp = comparator.compare(e, this.getElement());
         if (cmp < 0) {
            AvlNode<E> initLeft = this.left;
            if (initLeft == null) {
               result[0] = 0;
               return count > 0 ? this.addLeftChild(e, count) : this;
            } else {
               this.left = initLeft.setCount(comparator, e, count, result);
               if (count == 0 && result[0] != 0) {
                  --this.distinctElements;
               } else if (count > 0 && result[0] == 0) {
                  ++this.distinctElements;
               }

               this.totalCount += (long)(count - result[0]);
               return this.rebalance();
            }
         } else if (cmp > 0) {
            AvlNode<E> initRight = this.right;
            if (initRight == null) {
               result[0] = 0;
               return count > 0 ? this.addRightChild(e, count) : this;
            } else {
               this.right = initRight.setCount(comparator, e, count, result);
               if (count == 0 && result[0] != 0) {
                  --this.distinctElements;
               } else if (count > 0 && result[0] == 0) {
                  ++this.distinctElements;
               }

               this.totalCount += (long)(count - result[0]);
               return this.rebalance();
            }
         } else {
            result[0] = this.elemCount;
            if (count == 0) {
               return this.deleteMe();
            } else {
               this.totalCount += (long)(count - this.elemCount);
               this.elemCount = count;
               return this;
            }
         }
      }

      @CheckForNull
      AvlNode setCount(Comparator comparator, @ParametricNullness Object e, int expectedCount, int newCount, int[] result) {
         int cmp = comparator.compare(e, this.getElement());
         if (cmp < 0) {
            AvlNode<E> initLeft = this.left;
            if (initLeft == null) {
               result[0] = 0;
               return expectedCount == 0 && newCount > 0 ? this.addLeftChild(e, newCount) : this;
            } else {
               this.left = initLeft.setCount(comparator, e, expectedCount, newCount, result);
               if (result[0] == expectedCount) {
                  if (newCount == 0 && result[0] != 0) {
                     --this.distinctElements;
                  } else if (newCount > 0 && result[0] == 0) {
                     ++this.distinctElements;
                  }

                  this.totalCount += (long)(newCount - result[0]);
               }

               return this.rebalance();
            }
         } else if (cmp > 0) {
            AvlNode<E> initRight = this.right;
            if (initRight == null) {
               result[0] = 0;
               return expectedCount == 0 && newCount > 0 ? this.addRightChild(e, newCount) : this;
            } else {
               this.right = initRight.setCount(comparator, e, expectedCount, newCount, result);
               if (result[0] == expectedCount) {
                  if (newCount == 0 && result[0] != 0) {
                     --this.distinctElements;
                  } else if (newCount > 0 && result[0] == 0) {
                     ++this.distinctElements;
                  }

                  this.totalCount += (long)(newCount - result[0]);
               }

               return this.rebalance();
            }
         } else {
            result[0] = this.elemCount;
            if (expectedCount == this.elemCount) {
               if (newCount == 0) {
                  return this.deleteMe();
               }

               this.totalCount += (long)(newCount - this.elemCount);
               this.elemCount = newCount;
            }

            return this;
         }
      }

      @CheckForNull
      private AvlNode deleteMe() {
         int oldElemCount = this.elemCount;
         this.elemCount = 0;
         TreeMultiset.successor(this.pred(), this.succ());
         if (this.left == null) {
            return this.right;
         } else if (this.right == null) {
            return this.left;
         } else if (this.left.height >= this.right.height) {
            AvlNode<E> newTop = this.pred();
            newTop.left = this.left.removeMax(newTop);
            newTop.right = this.right;
            newTop.distinctElements = this.distinctElements - 1;
            newTop.totalCount = this.totalCount - (long)oldElemCount;
            return newTop.rebalance();
         } else {
            AvlNode<E> newTop = this.succ();
            newTop.right = this.right.removeMin(newTop);
            newTop.left = this.left;
            newTop.distinctElements = this.distinctElements - 1;
            newTop.totalCount = this.totalCount - (long)oldElemCount;
            return newTop.rebalance();
         }
      }

      @CheckForNull
      private AvlNode removeMin(AvlNode node) {
         if (this.left == null) {
            return this.right;
         } else {
            this.left = this.left.removeMin(node);
            --this.distinctElements;
            this.totalCount -= (long)node.elemCount;
            return this.rebalance();
         }
      }

      @CheckForNull
      private AvlNode removeMax(AvlNode node) {
         if (this.right == null) {
            return this.left;
         } else {
            this.right = this.right.removeMax(node);
            --this.distinctElements;
            this.totalCount -= (long)node.elemCount;
            return this.rebalance();
         }
      }

      private void recomputeMultiset() {
         this.distinctElements = 1 + TreeMultiset.distinctElements(this.left) + TreeMultiset.distinctElements(this.right);
         this.totalCount = (long)this.elemCount + totalCount(this.left) + totalCount(this.right);
      }

      private void recomputeHeight() {
         this.height = 1 + Math.max(height(this.left), height(this.right));
      }

      private void recompute() {
         this.recomputeMultiset();
         this.recomputeHeight();
      }

      private AvlNode rebalance() {
         switch (this.balanceFactor()) {
            case -2:
               Objects.requireNonNull(this.right);
               if (this.right.balanceFactor() > 0) {
                  this.right = this.right.rotateRight();
               }

               return this.rotateLeft();
            case 2:
               Objects.requireNonNull(this.left);
               if (this.left.balanceFactor() < 0) {
                  this.left = this.left.rotateLeft();
               }

               return this.rotateRight();
            default:
               this.recomputeHeight();
               return this;
         }
      }

      private int balanceFactor() {
         return height(this.left) - height(this.right);
      }

      private AvlNode rotateLeft() {
         Preconditions.checkState(this.right != null);
         AvlNode<E> newTop = this.right;
         this.right = newTop.left;
         newTop.left = this;
         newTop.totalCount = this.totalCount;
         newTop.distinctElements = this.distinctElements;
         this.recompute();
         newTop.recomputeHeight();
         return newTop;
      }

      private AvlNode rotateRight() {
         Preconditions.checkState(this.left != null);
         AvlNode<E> newTop = this.left;
         this.left = newTop.right;
         newTop.right = this;
         newTop.totalCount = this.totalCount;
         newTop.distinctElements = this.distinctElements;
         this.recompute();
         newTop.recomputeHeight();
         return newTop;
      }

      private static long totalCount(@CheckForNull AvlNode node) {
         return node == null ? 0L : node.totalCount;
      }

      private static int height(@CheckForNull AvlNode node) {
         return node == null ? 0 : node.height;
      }

      @CheckForNull
      private AvlNode ceiling(Comparator comparator, @ParametricNullness Object e) {
         int cmp = comparator.compare(e, this.getElement());
         if (cmp < 0) {
            return this.left == null ? this : (AvlNode)MoreObjects.firstNonNull(this.left.ceiling(comparator, e), this);
         } else if (cmp == 0) {
            return this;
         } else {
            return this.right == null ? null : this.right.ceiling(comparator, e);
         }
      }

      @CheckForNull
      private AvlNode floor(Comparator comparator, @ParametricNullness Object e) {
         int cmp = comparator.compare(e, this.getElement());
         if (cmp > 0) {
            return this.right == null ? this : (AvlNode)MoreObjects.firstNonNull(this.right.floor(comparator, e), this);
         } else if (cmp == 0) {
            return this;
         } else {
            return this.left == null ? null : this.left.floor(comparator, e);
         }
      }

      @ParametricNullness
      Object getElement() {
         return NullnessCasts.uncheckedCastNullableTToT(this.elem);
      }

      int getCount() {
         return this.elemCount;
      }

      public String toString() {
         return Multisets.immutableEntry(this.getElement(), this.getCount()).toString();
      }
   }
}
