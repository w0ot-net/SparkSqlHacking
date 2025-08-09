package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.math.IntMath;
import com.google.common.primitives.Ints;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;
import java.util.Spliterator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;
import java.util.stream.Collector;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@GwtCompatible
public final class Multisets {
   private Multisets() {
   }

   public static Collector toMultiset(Function elementFunction, ToIntFunction countFunction, Supplier multisetSupplier) {
      return CollectCollectors.toMultiset(elementFunction, countFunction, multisetSupplier);
   }

   public static Multiset unmodifiableMultiset(Multiset multiset) {
      return (Multiset)(!(multiset instanceof UnmodifiableMultiset) && !(multiset instanceof ImmutableMultiset) ? new UnmodifiableMultiset((Multiset)Preconditions.checkNotNull(multiset)) : multiset);
   }

   /** @deprecated */
   @Deprecated
   public static Multiset unmodifiableMultiset(ImmutableMultiset multiset) {
      return (Multiset)Preconditions.checkNotNull(multiset);
   }

   public static SortedMultiset unmodifiableSortedMultiset(SortedMultiset sortedMultiset) {
      return new UnmodifiableSortedMultiset((SortedMultiset)Preconditions.checkNotNull(sortedMultiset));
   }

   public static Multiset.Entry immutableEntry(@ParametricNullness Object e, int n) {
      return new ImmutableEntry(e, n);
   }

   public static Multiset filter(Multiset unfiltered, Predicate predicate) {
      if (unfiltered instanceof FilteredMultiset) {
         FilteredMultiset<E> filtered = (FilteredMultiset)unfiltered;
         Predicate<E> combinedPredicate = Predicates.and(filtered.predicate, predicate);
         return new FilteredMultiset(filtered.unfiltered, combinedPredicate);
      } else {
         return new FilteredMultiset(unfiltered, predicate);
      }
   }

   static int inferDistinctElements(Iterable elements) {
      return elements instanceof Multiset ? ((Multiset)elements).elementSet().size() : 11;
   }

   public static Multiset union(final Multiset multiset1, final Multiset multiset2) {
      Preconditions.checkNotNull(multiset1);
      Preconditions.checkNotNull(multiset2);
      return new ViewMultiset() {
         public boolean contains(@CheckForNull Object element) {
            return multiset1.contains(element) || multiset2.contains(element);
         }

         public boolean isEmpty() {
            return multiset1.isEmpty() && multiset2.isEmpty();
         }

         public int count(@CheckForNull Object element) {
            return Math.max(multiset1.count(element), multiset2.count(element));
         }

         Set createElementSet() {
            return Sets.union(multiset1.elementSet(), multiset2.elementSet());
         }

         Iterator elementIterator() {
            throw new AssertionError("should never be called");
         }

         Iterator entryIterator() {
            final Iterator<? extends Multiset.Entry<? extends E>> iterator1 = multiset1.entrySet().iterator();
            final Iterator<? extends Multiset.Entry<? extends E>> iterator2 = multiset2.entrySet().iterator();
            return new AbstractIterator() {
               @CheckForNull
               protected Multiset.Entry computeNext() {
                  if (iterator1.hasNext()) {
                     Multiset.Entry<? extends E> entry1 = (Multiset.Entry)iterator1.next();
                     E element = (E)entry1.getElement();
                     int count = Math.max(entry1.getCount(), multiset2.count(element));
                     return Multisets.immutableEntry(element, count);
                  } else {
                     while(iterator2.hasNext()) {
                        Multiset.Entry<? extends E> entry2 = (Multiset.Entry)iterator2.next();
                        E element = (E)entry2.getElement();
                        if (!multiset1.contains(element)) {
                           return Multisets.immutableEntry(element, entry2.getCount());
                        }
                     }

                     return (Multiset.Entry)this.endOfData();
                  }
               }
            };
         }
      };
   }

   public static Multiset intersection(final Multiset multiset1, final Multiset multiset2) {
      Preconditions.checkNotNull(multiset1);
      Preconditions.checkNotNull(multiset2);
      return new ViewMultiset() {
         public int count(@CheckForNull Object element) {
            int count1 = multiset1.count(element);
            return count1 == 0 ? 0 : Math.min(count1, multiset2.count(element));
         }

         Set createElementSet() {
            return Sets.intersection(multiset1.elementSet(), multiset2.elementSet());
         }

         Iterator elementIterator() {
            throw new AssertionError("should never be called");
         }

         Iterator entryIterator() {
            final Iterator<Multiset.Entry<E>> iterator1 = multiset1.entrySet().iterator();
            return new AbstractIterator() {
               @CheckForNull
               protected Multiset.Entry computeNext() {
                  while(true) {
                     if (iterator1.hasNext()) {
                        Multiset.Entry<E> entry1 = (Multiset.Entry)iterator1.next();
                        E element = (E)entry1.getElement();
                        int count = Math.min(entry1.getCount(), multiset2.count(element));
                        if (count <= 0) {
                           continue;
                        }

                        return Multisets.immutableEntry(element, count);
                     }

                     return (Multiset.Entry)this.endOfData();
                  }
               }
            };
         }
      };
   }

   public static Multiset sum(final Multiset multiset1, final Multiset multiset2) {
      Preconditions.checkNotNull(multiset1);
      Preconditions.checkNotNull(multiset2);
      return new ViewMultiset() {
         public boolean contains(@CheckForNull Object element) {
            return multiset1.contains(element) || multiset2.contains(element);
         }

         public boolean isEmpty() {
            return multiset1.isEmpty() && multiset2.isEmpty();
         }

         public int size() {
            return IntMath.saturatedAdd(multiset1.size(), multiset2.size());
         }

         public int count(@CheckForNull Object element) {
            return multiset1.count(element) + multiset2.count(element);
         }

         Set createElementSet() {
            return Sets.union(multiset1.elementSet(), multiset2.elementSet());
         }

         Iterator elementIterator() {
            throw new AssertionError("should never be called");
         }

         Iterator entryIterator() {
            final Iterator<? extends Multiset.Entry<? extends E>> iterator1 = multiset1.entrySet().iterator();
            final Iterator<? extends Multiset.Entry<? extends E>> iterator2 = multiset2.entrySet().iterator();
            return new AbstractIterator() {
               @CheckForNull
               protected Multiset.Entry computeNext() {
                  if (iterator1.hasNext()) {
                     Multiset.Entry<? extends E> entry1 = (Multiset.Entry)iterator1.next();
                     E element = (E)entry1.getElement();
                     int count = entry1.getCount() + multiset2.count(element);
                     return Multisets.immutableEntry(element, count);
                  } else {
                     while(iterator2.hasNext()) {
                        Multiset.Entry<? extends E> entry2 = (Multiset.Entry)iterator2.next();
                        E element = (E)entry2.getElement();
                        if (!multiset1.contains(element)) {
                           return Multisets.immutableEntry(element, entry2.getCount());
                        }
                     }

                     return (Multiset.Entry)this.endOfData();
                  }
               }
            };
         }
      };
   }

   public static Multiset difference(final Multiset multiset1, final Multiset multiset2) {
      Preconditions.checkNotNull(multiset1);
      Preconditions.checkNotNull(multiset2);
      return new ViewMultiset() {
         public int count(@CheckForNull Object element) {
            int count1 = multiset1.count(element);
            return count1 == 0 ? 0 : Math.max(0, count1 - multiset2.count(element));
         }

         public void clear() {
            throw new UnsupportedOperationException();
         }

         Iterator elementIterator() {
            final Iterator<Multiset.Entry<E>> iterator1 = multiset1.entrySet().iterator();
            return new AbstractIterator() {
               @CheckForNull
               protected Object computeNext() {
                  while(true) {
                     if (iterator1.hasNext()) {
                        Multiset.Entry<E> entry1 = (Multiset.Entry)iterator1.next();
                        E element = (E)entry1.getElement();
                        if (entry1.getCount() <= multiset2.count(element)) {
                           continue;
                        }

                        return element;
                     }

                     return this.endOfData();
                  }
               }
            };
         }

         Iterator entryIterator() {
            final Iterator<Multiset.Entry<E>> iterator1 = multiset1.entrySet().iterator();
            return new AbstractIterator() {
               @CheckForNull
               protected Multiset.Entry computeNext() {
                  while(true) {
                     if (iterator1.hasNext()) {
                        Multiset.Entry<E> entry1 = (Multiset.Entry)iterator1.next();
                        E element = (E)entry1.getElement();
                        int count = entry1.getCount() - multiset2.count(element);
                        if (count <= 0) {
                           continue;
                        }

                        return Multisets.immutableEntry(element, count);
                     }

                     return (Multiset.Entry)this.endOfData();
                  }
               }
            };
         }

         int distinctElements() {
            return Iterators.size(this.entryIterator());
         }
      };
   }

   @CanIgnoreReturnValue
   public static boolean containsOccurrences(Multiset superMultiset, Multiset subMultiset) {
      Preconditions.checkNotNull(superMultiset);
      Preconditions.checkNotNull(subMultiset);

      for(Multiset.Entry entry : subMultiset.entrySet()) {
         int superCount = superMultiset.count(entry.getElement());
         if (superCount < entry.getCount()) {
            return false;
         }
      }

      return true;
   }

   @CanIgnoreReturnValue
   public static boolean retainOccurrences(Multiset multisetToModify, Multiset multisetToRetain) {
      return retainOccurrencesImpl(multisetToModify, multisetToRetain);
   }

   private static boolean retainOccurrencesImpl(Multiset multisetToModify, Multiset occurrencesToRetain) {
      Preconditions.checkNotNull(multisetToModify);
      Preconditions.checkNotNull(occurrencesToRetain);
      Iterator<Multiset.Entry<E>> entryIterator = multisetToModify.entrySet().iterator();
      boolean changed = false;

      while(entryIterator.hasNext()) {
         Multiset.Entry<E> entry = (Multiset.Entry)entryIterator.next();
         int retainCount = occurrencesToRetain.count(entry.getElement());
         if (retainCount == 0) {
            entryIterator.remove();
            changed = true;
         } else if (retainCount < entry.getCount()) {
            multisetToModify.setCount(entry.getElement(), retainCount);
            changed = true;
         }
      }

      return changed;
   }

   @CanIgnoreReturnValue
   public static boolean removeOccurrences(Multiset multisetToModify, Iterable occurrencesToRemove) {
      if (occurrencesToRemove instanceof Multiset) {
         return removeOccurrences(multisetToModify, (Multiset)occurrencesToRemove);
      } else {
         Preconditions.checkNotNull(multisetToModify);
         Preconditions.checkNotNull(occurrencesToRemove);
         boolean changed = false;

         for(Object o : occurrencesToRemove) {
            changed |= multisetToModify.remove(o);
         }

         return changed;
      }
   }

   @CanIgnoreReturnValue
   public static boolean removeOccurrences(Multiset multisetToModify, Multiset occurrencesToRemove) {
      Preconditions.checkNotNull(multisetToModify);
      Preconditions.checkNotNull(occurrencesToRemove);
      boolean changed = false;
      Iterator<? extends Multiset.Entry<?>> entryIterator = multisetToModify.entrySet().iterator();

      while(entryIterator.hasNext()) {
         Multiset.Entry<?> entry = (Multiset.Entry)entryIterator.next();
         int removeCount = occurrencesToRemove.count(entry.getElement());
         if (removeCount >= entry.getCount()) {
            entryIterator.remove();
            changed = true;
         } else if (removeCount > 0) {
            multisetToModify.remove(entry.getElement(), removeCount);
            changed = true;
         }
      }

      return changed;
   }

   static boolean equalsImpl(Multiset multiset, @CheckForNull Object object) {
      if (object == multiset) {
         return true;
      } else if (object instanceof Multiset) {
         Multiset<?> that = (Multiset)object;
         if (multiset.size() == that.size() && multiset.entrySet().size() == that.entrySet().size()) {
            for(Multiset.Entry entry : that.entrySet()) {
               if (multiset.count(entry.getElement()) != entry.getCount()) {
                  return false;
               }
            }

            return true;
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   static boolean addAllImpl(Multiset self, Collection elements) {
      Preconditions.checkNotNull(self);
      Preconditions.checkNotNull(elements);
      if (elements instanceof Multiset) {
         return addAllImpl(self, cast(elements));
      } else {
         return elements.isEmpty() ? false : Iterators.addAll(self, elements.iterator());
      }
   }

   private static boolean addAllImpl(Multiset self, Multiset elements) {
      if (elements.isEmpty()) {
         return false;
      } else {
         Objects.requireNonNull(self);
         elements.forEachEntry(self::add);
         return true;
      }
   }

   static boolean removeAllImpl(Multiset self, Collection elementsToRemove) {
      Collection<?> collection = (Collection<?>)(elementsToRemove instanceof Multiset ? ((Multiset)elementsToRemove).elementSet() : elementsToRemove);
      return self.elementSet().removeAll(collection);
   }

   static boolean retainAllImpl(Multiset self, Collection elementsToRetain) {
      Preconditions.checkNotNull(elementsToRetain);
      Collection<?> collection = (Collection<?>)(elementsToRetain instanceof Multiset ? ((Multiset)elementsToRetain).elementSet() : elementsToRetain);
      return self.elementSet().retainAll(collection);
   }

   static int setCountImpl(Multiset self, @ParametricNullness Object element, int count) {
      CollectPreconditions.checkNonnegative(count, "count");
      int oldCount = self.count(element);
      int delta = count - oldCount;
      if (delta > 0) {
         self.add(element, delta);
      } else if (delta < 0) {
         self.remove(element, -delta);
      }

      return oldCount;
   }

   static boolean setCountImpl(Multiset self, @ParametricNullness Object element, int oldCount, int newCount) {
      CollectPreconditions.checkNonnegative(oldCount, "oldCount");
      CollectPreconditions.checkNonnegative(newCount, "newCount");
      if (self.count(element) == oldCount) {
         self.setCount(element, newCount);
         return true;
      } else {
         return false;
      }
   }

   static Iterator elementIterator(Iterator entryIterator) {
      return new TransformedIterator(entryIterator) {
         @ParametricNullness
         Object transform(Multiset.Entry entry) {
            return entry.getElement();
         }
      };
   }

   static Iterator iteratorImpl(Multiset multiset) {
      return new MultisetIteratorImpl(multiset, multiset.entrySet().iterator());
   }

   static Spliterator spliteratorImpl(Multiset multiset) {
      Spliterator<Multiset.Entry<E>> entrySpliterator = multiset.entrySet().spliterator();
      return CollectSpliterators.flatMap(entrySpliterator, (entry) -> Collections.nCopies(entry.getCount(), entry.getElement()).spliterator(), 64 | entrySpliterator.characteristics() & 1296, (long)multiset.size());
   }

   static int linearTimeSizeImpl(Multiset multiset) {
      long size = 0L;

      for(Multiset.Entry entry : multiset.entrySet()) {
         size += (long)entry.getCount();
      }

      return Ints.saturatedCast(size);
   }

   static Multiset cast(Iterable iterable) {
      return (Multiset)iterable;
   }

   public static ImmutableMultiset copyHighestCountFirst(Multiset multiset) {
      Multiset.Entry<E>[] entries = (Multiset.Entry[])multiset.entrySet().toArray(new Multiset.Entry[0]);
      Arrays.sort(entries, Multisets.DecreasingCount.INSTANCE);
      return ImmutableMultiset.copyFromEntries(Arrays.asList(entries));
   }

   static class UnmodifiableMultiset extends ForwardingMultiset implements Serializable {
      final Multiset delegate;
      @LazyInit
      @CheckForNull
      transient Set elementSet;
      @LazyInit
      @CheckForNull
      transient Set entrySet;
      private static final long serialVersionUID = 0L;

      UnmodifiableMultiset(Multiset delegate) {
         this.delegate = delegate;
      }

      protected Multiset delegate() {
         return this.delegate;
      }

      Set createElementSet() {
         return Collections.unmodifiableSet(this.delegate.elementSet());
      }

      public Set elementSet() {
         Set<E> es = this.elementSet;
         return es == null ? (this.elementSet = this.createElementSet()) : es;
      }

      public Set entrySet() {
         Set<Multiset.Entry<E>> es = this.entrySet;
         return es == null ? (this.entrySet = Collections.unmodifiableSet(this.delegate.entrySet())) : es;
      }

      public Iterator iterator() {
         return Iterators.unmodifiableIterator(this.delegate.iterator());
      }

      public boolean add(@ParametricNullness Object element) {
         throw new UnsupportedOperationException();
      }

      public int add(@ParametricNullness Object element, int occurrences) {
         throw new UnsupportedOperationException();
      }

      public boolean addAll(Collection elementsToAdd) {
         throw new UnsupportedOperationException();
      }

      public boolean remove(@CheckForNull Object element) {
         throw new UnsupportedOperationException();
      }

      public int remove(@CheckForNull Object element, int occurrences) {
         throw new UnsupportedOperationException();
      }

      public boolean removeAll(Collection elementsToRemove) {
         throw new UnsupportedOperationException();
      }

      public boolean removeIf(java.util.function.Predicate filter) {
         throw new UnsupportedOperationException();
      }

      public boolean retainAll(Collection elementsToRetain) {
         throw new UnsupportedOperationException();
      }

      public void clear() {
         throw new UnsupportedOperationException();
      }

      public int setCount(@ParametricNullness Object element, int count) {
         throw new UnsupportedOperationException();
      }

      public boolean setCount(@ParametricNullness Object element, int oldCount, int newCount) {
         throw new UnsupportedOperationException();
      }
   }

   static class ImmutableEntry extends AbstractEntry implements Serializable {
      @ParametricNullness
      private final Object element;
      private final int count;
      private static final long serialVersionUID = 0L;

      ImmutableEntry(@ParametricNullness Object element, int count) {
         this.element = element;
         this.count = count;
         CollectPreconditions.checkNonnegative(count, "count");
      }

      @ParametricNullness
      public final Object getElement() {
         return this.element;
      }

      public final int getCount() {
         return this.count;
      }

      @CheckForNull
      public ImmutableEntry nextInBucket() {
         return null;
      }
   }

   private static final class FilteredMultiset extends ViewMultiset {
      final Multiset unfiltered;
      final Predicate predicate;

      FilteredMultiset(Multiset unfiltered, Predicate predicate) {
         this.unfiltered = (Multiset)Preconditions.checkNotNull(unfiltered);
         this.predicate = (Predicate)Preconditions.checkNotNull(predicate);
      }

      public UnmodifiableIterator iterator() {
         return Iterators.filter(this.unfiltered.iterator(), this.predicate);
      }

      Set createElementSet() {
         return Sets.filter(this.unfiltered.elementSet(), this.predicate);
      }

      Iterator elementIterator() {
         throw new AssertionError("should never be called");
      }

      Set createEntrySet() {
         return Sets.filter(this.unfiltered.entrySet(), new Predicate() {
            public boolean apply(Multiset.Entry entry) {
               return FilteredMultiset.this.predicate.apply(entry.getElement());
            }
         });
      }

      Iterator entryIterator() {
         throw new AssertionError("should never be called");
      }

      public int count(@CheckForNull Object element) {
         int count = this.unfiltered.count(element);
         if (count > 0) {
            return this.predicate.apply(element) ? count : 0;
         } else {
            return 0;
         }
      }

      public int add(@ParametricNullness Object element, int occurrences) {
         Preconditions.checkArgument(this.predicate.apply(element), "Element %s does not match predicate %s", element, this.predicate);
         return this.unfiltered.add(element, occurrences);
      }

      public int remove(@CheckForNull Object element, int occurrences) {
         CollectPreconditions.checkNonnegative(occurrences, "occurrences");
         if (occurrences == 0) {
            return this.count(element);
         } else {
            return this.contains(element) ? this.unfiltered.remove(element, occurrences) : 0;
         }
      }
   }

   abstract static class AbstractEntry implements Multiset.Entry {
      public boolean equals(@CheckForNull Object object) {
         if (!(object instanceof Multiset.Entry)) {
            return false;
         } else {
            Multiset.Entry<?> that = (Multiset.Entry)object;
            return this.getCount() == that.getCount() && com.google.common.base.Objects.equal(this.getElement(), that.getElement());
         }
      }

      public int hashCode() {
         E e = (E)this.getElement();
         return (e == null ? 0 : e.hashCode()) ^ this.getCount();
      }

      public String toString() {
         String text = String.valueOf(this.getElement());
         int n = this.getCount();
         return n == 1 ? text : text + " x " + n;
      }
   }

   abstract static class ElementSet extends Sets.ImprovedAbstractSet {
      abstract Multiset multiset();

      public void clear() {
         this.multiset().clear();
      }

      public boolean contains(@CheckForNull Object o) {
         return this.multiset().contains(o);
      }

      public boolean containsAll(Collection c) {
         return this.multiset().containsAll(c);
      }

      public boolean isEmpty() {
         return this.multiset().isEmpty();
      }

      public abstract Iterator iterator();

      public boolean remove(@CheckForNull Object o) {
         return this.multiset().remove(o, Integer.MAX_VALUE) > 0;
      }

      public int size() {
         return this.multiset().entrySet().size();
      }
   }

   abstract static class EntrySet extends Sets.ImprovedAbstractSet {
      abstract Multiset multiset();

      public boolean contains(@CheckForNull Object o) {
         if (o instanceof Multiset.Entry) {
            Multiset.Entry<?> entry = (Multiset.Entry)o;
            if (entry.getCount() <= 0) {
               return false;
            } else {
               int count = this.multiset().count(entry.getElement());
               return count == entry.getCount();
            }
         } else {
            return false;
         }
      }

      public boolean remove(@CheckForNull Object object) {
         if (object instanceof Multiset.Entry) {
            Multiset.Entry<?> entry = (Multiset.Entry)object;
            Object element = entry.getElement();
            int entryCount = entry.getCount();
            if (entryCount != 0) {
               Multiset<Object> multiset = this.multiset();
               return multiset.setCount(element, entryCount, 0);
            }
         }

         return false;
      }

      public void clear() {
         this.multiset().clear();
      }
   }

   static final class MultisetIteratorImpl implements Iterator {
      private final Multiset multiset;
      private final Iterator entryIterator;
      @CheckForNull
      private Multiset.Entry currentEntry;
      private int laterCount;
      private int totalCount;
      private boolean canRemove;

      MultisetIteratorImpl(Multiset multiset, Iterator entryIterator) {
         this.multiset = multiset;
         this.entryIterator = entryIterator;
      }

      public boolean hasNext() {
         return this.laterCount > 0 || this.entryIterator.hasNext();
      }

      @ParametricNullness
      public Object next() {
         if (!this.hasNext()) {
            throw new NoSuchElementException();
         } else {
            if (this.laterCount == 0) {
               this.currentEntry = (Multiset.Entry)this.entryIterator.next();
               this.totalCount = this.laterCount = this.currentEntry.getCount();
            }

            --this.laterCount;
            this.canRemove = true;
            return ((Multiset.Entry)Objects.requireNonNull(this.currentEntry)).getElement();
         }
      }

      public void remove() {
         CollectPreconditions.checkRemove(this.canRemove);
         if (this.totalCount == 1) {
            this.entryIterator.remove();
         } else {
            this.multiset.remove(((Multiset.Entry)Objects.requireNonNull(this.currentEntry)).getElement());
         }

         --this.totalCount;
         this.canRemove = false;
      }
   }

   private static final class DecreasingCount implements Comparator {
      static final Comparator INSTANCE = new DecreasingCount();

      public int compare(Multiset.Entry entry1, Multiset.Entry entry2) {
         return entry2.getCount() - entry1.getCount();
      }
   }

   private abstract static class ViewMultiset extends AbstractMultiset {
      private ViewMultiset() {
      }

      public int size() {
         return Multisets.linearTimeSizeImpl(this);
      }

      public void clear() {
         this.elementSet().clear();
      }

      public Iterator iterator() {
         return Multisets.iteratorImpl(this);
      }

      int distinctElements() {
         return this.elementSet().size();
      }
   }
}
