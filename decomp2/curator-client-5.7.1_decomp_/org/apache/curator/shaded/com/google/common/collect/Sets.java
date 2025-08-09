package org.apache.curator.shaded.com.google.common.collect;

import java.io.Serializable;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.function.Consumer;
import java.util.stream.Collector;
import java.util.stream.Stream;
import javax.annotation.CheckForNull;
import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;
import org.apache.curator.shaded.com.google.common.annotations.GwtIncompatible;
import org.apache.curator.shaded.com.google.common.annotations.J2ktIncompatible;
import org.apache.curator.shaded.com.google.common.base.Preconditions;
import org.apache.curator.shaded.com.google.common.base.Predicate;
import org.apache.curator.shaded.com.google.common.base.Predicates;
import org.apache.curator.shaded.com.google.common.math.IntMath;
import org.apache.curator.shaded.com.google.errorprone.annotations.CanIgnoreReturnValue;
import org.apache.curator.shaded.com.google.errorprone.annotations.DoNotCall;
import org.checkerframework.checker.nullness.qual.Nullable;

@ElementTypesAreNonnullByDefault
@GwtCompatible(
   emulated = true
)
public final class Sets {
   private Sets() {
   }

   @GwtCompatible(
      serializable = true
   )
   public static ImmutableSet immutableEnumSet(Enum anElement, Enum... otherElements) {
      return ImmutableEnumSet.asImmutable(EnumSet.of(anElement, otherElements));
   }

   @GwtCompatible(
      serializable = true
   )
   public static ImmutableSet immutableEnumSet(Iterable elements) {
      if (elements instanceof ImmutableEnumSet) {
         return (ImmutableEnumSet)elements;
      } else if (elements instanceof Collection) {
         Collection<E> collection = (Collection)elements;
         return collection.isEmpty() ? ImmutableSet.of() : ImmutableEnumSet.asImmutable(EnumSet.copyOf(collection));
      } else {
         Iterator<E> itr = elements.iterator();
         if (itr.hasNext()) {
            EnumSet<E> enumSet = EnumSet.of((Enum)itr.next());
            Iterators.addAll(enumSet, itr);
            return ImmutableEnumSet.asImmutable(enumSet);
         } else {
            return ImmutableSet.of();
         }
      }
   }

   public static Collector toImmutableEnumSet() {
      return CollectCollectors.toImmutableEnumSet();
   }

   public static EnumSet newEnumSet(Iterable iterable, Class elementType) {
      EnumSet<E> set = EnumSet.noneOf(elementType);
      Iterables.addAll(set, iterable);
      return set;
   }

   public static HashSet newHashSet() {
      return new HashSet();
   }

   public static HashSet newHashSet(Object... elements) {
      HashSet<E> set = newHashSetWithExpectedSize(elements.length);
      Collections.addAll(set, elements);
      return set;
   }

   public static HashSet newHashSet(Iterable elements) {
      return elements instanceof Collection ? new HashSet((Collection)elements) : newHashSet(elements.iterator());
   }

   public static HashSet newHashSet(Iterator elements) {
      HashSet<E> set = newHashSet();
      Iterators.addAll(set, elements);
      return set;
   }

   public static HashSet newHashSetWithExpectedSize(int expectedSize) {
      return new HashSet(Maps.capacity(expectedSize));
   }

   public static Set newConcurrentHashSet() {
      return Platform.newConcurrentHashSet();
   }

   public static Set newConcurrentHashSet(Iterable elements) {
      Set<E> set = newConcurrentHashSet();
      Iterables.addAll(set, elements);
      return set;
   }

   public static LinkedHashSet newLinkedHashSet() {
      return new LinkedHashSet();
   }

   public static LinkedHashSet newLinkedHashSet(Iterable elements) {
      if (elements instanceof Collection) {
         return new LinkedHashSet((Collection)elements);
      } else {
         LinkedHashSet<E> set = newLinkedHashSet();
         Iterables.addAll(set, elements);
         return set;
      }
   }

   public static LinkedHashSet newLinkedHashSetWithExpectedSize(int expectedSize) {
      return new LinkedHashSet(Maps.capacity(expectedSize));
   }

   public static TreeSet newTreeSet() {
      return new TreeSet();
   }

   public static TreeSet newTreeSet(Iterable elements) {
      TreeSet<E> set = newTreeSet();
      Iterables.addAll(set, elements);
      return set;
   }

   public static TreeSet newTreeSet(Comparator comparator) {
      return new TreeSet((Comparator)Preconditions.checkNotNull(comparator));
   }

   public static Set newIdentityHashSet() {
      return Collections.newSetFromMap(Maps.newIdentityHashMap());
   }

   @J2ktIncompatible
   @GwtIncompatible
   public static CopyOnWriteArraySet newCopyOnWriteArraySet() {
      return new CopyOnWriteArraySet();
   }

   @J2ktIncompatible
   @GwtIncompatible
   public static CopyOnWriteArraySet newCopyOnWriteArraySet(Iterable elements) {
      Collection<? extends E> elementsCollection = (Collection<? extends E>)(elements instanceof Collection ? (Collection)elements : Lists.newArrayList(elements));
      return new CopyOnWriteArraySet(elementsCollection);
   }

   @J2ktIncompatible
   @GwtIncompatible
   public static EnumSet complementOf(Collection collection) {
      if (collection instanceof EnumSet) {
         return EnumSet.complementOf((EnumSet)collection);
      } else {
         Preconditions.checkArgument(!collection.isEmpty(), "collection is empty; use the other version of this method");
         Class<E> type = ((Enum)collection.iterator().next()).getDeclaringClass();
         return makeComplementByHand(collection, type);
      }
   }

   @GwtIncompatible
   public static EnumSet complementOf(Collection collection, Class type) {
      Preconditions.checkNotNull(collection);
      return collection instanceof EnumSet ? EnumSet.complementOf((EnumSet)collection) : makeComplementByHand(collection, type);
   }

   @GwtIncompatible
   private static EnumSet makeComplementByHand(Collection collection, Class type) {
      EnumSet<E> result = EnumSet.allOf(type);
      result.removeAll(collection);
      return result;
   }

   /** @deprecated */
   @Deprecated
   public static Set newSetFromMap(Map map) {
      return Collections.newSetFromMap(map);
   }

   public static SetView union(final Set set1, final Set set2) {
      Preconditions.checkNotNull(set1, "set1");
      Preconditions.checkNotNull(set2, "set2");
      return new SetView() {
         public int size() {
            int size = set1.size();

            for(Object e : set2) {
               if (!set1.contains(e)) {
                  ++size;
               }
            }

            return size;
         }

         public boolean isEmpty() {
            return set1.isEmpty() && set2.isEmpty();
         }

         public UnmodifiableIterator iterator() {
            return new AbstractIterator() {
               final Iterator itr1 = set1.iterator();
               final Iterator itr2 = set2.iterator();

               @CheckForNull
               protected Object computeNext() {
                  if (this.itr1.hasNext()) {
                     return this.itr1.next();
                  } else {
                     while(this.itr2.hasNext()) {
                        E e = (E)this.itr2.next();
                        if (!set1.contains(e)) {
                           return e;
                        }
                     }

                     return this.endOfData();
                  }
               }
            };
         }

         public Stream stream() {
            return Stream.concat(set1.stream(), set2.stream().filter((e) -> !set1.contains(e)));
         }

         public Stream parallelStream() {
            return (Stream)this.stream().parallel();
         }

         public boolean contains(@CheckForNull Object object) {
            return set1.contains(object) || set2.contains(object);
         }

         public Set copyInto(Set set) {
            set.addAll(set1);
            set.addAll(set2);
            return set;
         }

         public ImmutableSet immutableCopy() {
            ImmutableSet.Builder<E> builder = (new ImmutableSet.Builder()).addAll((Iterable)set1).addAll((Iterable)set2);
            return builder.build();
         }
      };
   }

   public static SetView intersection(final Set set1, final Set set2) {
      Preconditions.checkNotNull(set1, "set1");
      Preconditions.checkNotNull(set2, "set2");
      return new SetView() {
         public UnmodifiableIterator iterator() {
            return new AbstractIterator() {
               final Iterator itr = set1.iterator();

               @CheckForNull
               protected Object computeNext() {
                  while(true) {
                     if (this.itr.hasNext()) {
                        E e = (E)this.itr.next();
                        if (!set2.contains(e)) {
                           continue;
                        }

                        return e;
                     }

                     return this.endOfData();
                  }
               }
            };
         }

         public Stream stream() {
            Stream var10000 = set1.stream();
            Set var10001 = set2;
            Objects.requireNonNull(var10001);
            return var10000.filter(var10001::contains);
         }

         public Stream parallelStream() {
            Stream var10000 = set1.parallelStream();
            Set var10001 = set2;
            Objects.requireNonNull(var10001);
            return var10000.filter(var10001::contains);
         }

         public int size() {
            int size = 0;

            for(Object e : set1) {
               if (set2.contains(e)) {
                  ++size;
               }
            }

            return size;
         }

         public boolean isEmpty() {
            return Collections.disjoint(set2, set1);
         }

         public boolean contains(@CheckForNull Object object) {
            return set1.contains(object) && set2.contains(object);
         }

         public boolean containsAll(Collection collection) {
            return set1.containsAll(collection) && set2.containsAll(collection);
         }
      };
   }

   public static SetView difference(final Set set1, final Set set2) {
      Preconditions.checkNotNull(set1, "set1");
      Preconditions.checkNotNull(set2, "set2");
      return new SetView() {
         public UnmodifiableIterator iterator() {
            return new AbstractIterator() {
               final Iterator itr = set1.iterator();

               @CheckForNull
               protected Object computeNext() {
                  while(true) {
                     if (this.itr.hasNext()) {
                        E e = (E)this.itr.next();
                        if (set2.contains(e)) {
                           continue;
                        }

                        return e;
                     }

                     return this.endOfData();
                  }
               }
            };
         }

         public Stream stream() {
            return set1.stream().filter((e) -> !set2.contains(e));
         }

         public Stream parallelStream() {
            return set1.parallelStream().filter((e) -> !set2.contains(e));
         }

         public int size() {
            int size = 0;

            for(Object e : set1) {
               if (!set2.contains(e)) {
                  ++size;
               }
            }

            return size;
         }

         public boolean isEmpty() {
            return set2.containsAll(set1);
         }

         public boolean contains(@CheckForNull Object element) {
            return set1.contains(element) && !set2.contains(element);
         }
      };
   }

   public static SetView symmetricDifference(final Set set1, final Set set2) {
      Preconditions.checkNotNull(set1, "set1");
      Preconditions.checkNotNull(set2, "set2");
      return new SetView() {
         public UnmodifiableIterator iterator() {
            final Iterator<? extends E> itr1 = set1.iterator();
            final Iterator<? extends E> itr2 = set2.iterator();
            return new AbstractIterator() {
               @CheckForNull
               public Object computeNext() {
                  while(true) {
                     if (itr1.hasNext()) {
                        E elem1 = (E)itr1.next();
                        if (set2.contains(elem1)) {
                           continue;
                        }

                        return elem1;
                     }

                     while(itr2.hasNext()) {
                        E elem2 = (E)itr2.next();
                        if (!set1.contains(elem2)) {
                           return elem2;
                        }
                     }

                     return this.endOfData();
                  }
               }
            };
         }

         public int size() {
            int size = 0;

            for(Object e : set1) {
               if (!set2.contains(e)) {
                  ++size;
               }
            }

            for(Object e : set2) {
               if (!set1.contains(e)) {
                  ++size;
               }
            }

            return size;
         }

         public boolean isEmpty() {
            return set1.equals(set2);
         }

         public boolean contains(@CheckForNull Object element) {
            return set1.contains(element) ^ set2.contains(element);
         }
      };
   }

   public static Set filter(Set unfiltered, Predicate predicate) {
      if (unfiltered instanceof SortedSet) {
         return filter((SortedSet)unfiltered, predicate);
      } else if (unfiltered instanceof FilteredSet) {
         FilteredSet<E> filtered = (FilteredSet)unfiltered;
         Predicate<E> combinedPredicate = Predicates.and(filtered.predicate, predicate);
         return new FilteredSet((Set)filtered.unfiltered, combinedPredicate);
      } else {
         return new FilteredSet((Set)Preconditions.checkNotNull(unfiltered), (Predicate)Preconditions.checkNotNull(predicate));
      }
   }

   public static SortedSet filter(SortedSet unfiltered, Predicate predicate) {
      if (unfiltered instanceof FilteredSet) {
         FilteredSet<E> filtered = (FilteredSet)unfiltered;
         Predicate<E> combinedPredicate = Predicates.and(filtered.predicate, predicate);
         return new FilteredSortedSet((SortedSet)filtered.unfiltered, combinedPredicate);
      } else {
         return new FilteredSortedSet((SortedSet)Preconditions.checkNotNull(unfiltered), (Predicate)Preconditions.checkNotNull(predicate));
      }
   }

   @GwtIncompatible
   public static NavigableSet filter(NavigableSet unfiltered, Predicate predicate) {
      if (unfiltered instanceof FilteredSet) {
         FilteredSet<E> filtered = (FilteredSet)unfiltered;
         Predicate<E> combinedPredicate = Predicates.and(filtered.predicate, predicate);
         return new FilteredNavigableSet((NavigableSet)filtered.unfiltered, combinedPredicate);
      } else {
         return new FilteredNavigableSet((NavigableSet)Preconditions.checkNotNull(unfiltered), (Predicate)Preconditions.checkNotNull(predicate));
      }
   }

   public static Set cartesianProduct(List sets) {
      return Sets.CartesianSet.create(sets);
   }

   @SafeVarargs
   public static Set cartesianProduct(Set... sets) {
      return cartesianProduct(Arrays.asList(sets));
   }

   @GwtCompatible(
      serializable = false
   )
   public static Set powerSet(Set set) {
      return new PowerSet(set);
   }

   public static Set combinations(Set set, final int size) {
      final ImmutableMap<E, Integer> index = Maps.indexMap(set);
      CollectPreconditions.checkNonnegative(size, "size");
      Preconditions.checkArgument(size <= index.size(), "size (%s) must be <= set.size() (%s)", size, index.size());
      if (size == 0) {
         return ImmutableSet.of(ImmutableSet.of());
      } else {
         return (Set)(size == index.size() ? ImmutableSet.of(index.keySet()) : new AbstractSet() {
            public boolean contains(@CheckForNull Object o) {
               if (!(o instanceof Set)) {
                  return false;
               } else {
                  Set<?> s = (Set)o;
                  return s.size() == size && index.keySet().containsAll(s);
               }
            }

            public Iterator iterator() {
               return new AbstractIterator() {
                  final BitSet bits = new BitSet(index.size());

                  @CheckForNull
                  protected Set computeNext() {
                     if (this.bits.isEmpty()) {
                        this.bits.set(0, size);
                     } else {
                        int firstSetBit = this.bits.nextSetBit(0);
                        int bitToFlip = this.bits.nextClearBit(firstSetBit);
                        if (bitToFlip == index.size()) {
                           return (Set)this.endOfData();
                        }

                        this.bits.set(0, bitToFlip - firstSetBit - 1);
                        this.bits.clear(bitToFlip - firstSetBit - 1, bitToFlip);
                        this.bits.set(bitToFlip);
                     }

                     final BitSet copy = (BitSet)this.bits.clone();
                     return new AbstractSet() {
                        public boolean contains(@CheckForNull Object o) {
                           Integer i = (Integer)index.get(o);
                           return i != null && copy.get(i);
                        }

                        public Iterator iterator() {
                           return new AbstractIterator() {
                              int i = -1;

                              @CheckForNull
                              protected Object computeNext() {
                                 this.i = copy.nextSetBit(this.i + 1);
                                 return this.i == -1 ? this.endOfData() : index.keySet().asList().get(this.i);
                              }
                           };
                        }

                        public int size() {
                           return size;
                        }
                     };
                  }
               };
            }

            public int size() {
               return IntMath.binomial(index.size(), size);
            }

            public String toString() {
               return "Sets.combinations(" + index.keySet() + ", " + size + ")";
            }
         });
      }
   }

   static int hashCodeImpl(Set s) {
      int hashCode = 0;

      for(Object o : s) {
         hashCode += o != null ? o.hashCode() : 0;
         hashCode = ~(~hashCode);
      }

      return hashCode;
   }

   static boolean equalsImpl(Set s, @CheckForNull Object object) {
      if (s == object) {
         return true;
      } else if (object instanceof Set) {
         Set<?> o = (Set)object;

         try {
            return s.size() == o.size() && s.containsAll(o);
         } catch (ClassCastException | NullPointerException var4) {
            return false;
         }
      } else {
         return false;
      }
   }

   public static NavigableSet unmodifiableNavigableSet(NavigableSet set) {
      return (NavigableSet)(!(set instanceof ImmutableCollection) && !(set instanceof UnmodifiableNavigableSet) ? new UnmodifiableNavigableSet(set) : set);
   }

   @GwtIncompatible
   public static NavigableSet synchronizedNavigableSet(NavigableSet navigableSet) {
      return Synchronized.navigableSet(navigableSet);
   }

   static boolean removeAllImpl(Set set, Iterator iterator) {
      boolean changed;
      for(changed = false; iterator.hasNext(); changed |= set.remove(iterator.next())) {
      }

      return changed;
   }

   static boolean removeAllImpl(Set set, Collection collection) {
      Preconditions.checkNotNull(collection);
      if (collection instanceof Multiset) {
         collection = ((Multiset)collection).elementSet();
      }

      return collection instanceof Set && collection.size() > set.size() ? Iterators.removeAll(set.iterator(), collection) : removeAllImpl(set, collection.iterator());
   }

   @GwtIncompatible
   public static NavigableSet subSet(NavigableSet set, Range range) {
      if (set.comparator() != null && set.comparator() != Ordering.natural() && range.hasLowerBound() && range.hasUpperBound()) {
         Preconditions.checkArgument(set.comparator().compare(range.lowerEndpoint(), range.upperEndpoint()) <= 0, "set is using a custom comparator which is inconsistent with the natural ordering.");
      }

      if (range.hasLowerBound() && range.hasUpperBound()) {
         return set.subSet(range.lowerEndpoint(), range.lowerBoundType() == BoundType.CLOSED, range.upperEndpoint(), range.upperBoundType() == BoundType.CLOSED);
      } else if (range.hasLowerBound()) {
         return set.tailSet(range.lowerEndpoint(), range.lowerBoundType() == BoundType.CLOSED);
      } else {
         return range.hasUpperBound() ? set.headSet(range.upperEndpoint(), range.upperBoundType() == BoundType.CLOSED) : (NavigableSet)Preconditions.checkNotNull(set);
      }
   }

   abstract static class ImprovedAbstractSet extends AbstractSet {
      public boolean removeAll(Collection c) {
         return Sets.removeAllImpl(this, (Collection)c);
      }

      public boolean retainAll(Collection c) {
         return super.retainAll((Collection)Preconditions.checkNotNull(c));
      }
   }

   public abstract static class SetView extends AbstractSet {
      private SetView() {
      }

      public ImmutableSet immutableCopy() {
         return ImmutableSet.copyOf((Collection)this);
      }

      @CanIgnoreReturnValue
      public Set copyInto(Set set) {
         set.addAll(this);
         return set;
      }

      /** @deprecated */
      @Deprecated
      @CanIgnoreReturnValue
      @DoNotCall("Always throws UnsupportedOperationException")
      public final boolean add(@ParametricNullness Object e) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      @CanIgnoreReturnValue
      @DoNotCall("Always throws UnsupportedOperationException")
      public final boolean remove(@CheckForNull Object object) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      @CanIgnoreReturnValue
      @DoNotCall("Always throws UnsupportedOperationException")
      public final boolean addAll(Collection newElements) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      @CanIgnoreReturnValue
      @DoNotCall("Always throws UnsupportedOperationException")
      public final boolean removeAll(Collection oldElements) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      @CanIgnoreReturnValue
      @DoNotCall("Always throws UnsupportedOperationException")
      public final boolean removeIf(java.util.function.Predicate filter) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      @CanIgnoreReturnValue
      @DoNotCall("Always throws UnsupportedOperationException")
      public final boolean retainAll(Collection elementsToKeep) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      @DoNotCall("Always throws UnsupportedOperationException")
      public final void clear() {
         throw new UnsupportedOperationException();
      }

      public abstract UnmodifiableIterator iterator();
   }

   private static class FilteredSet extends Collections2.FilteredCollection implements Set {
      FilteredSet(Set unfiltered, Predicate predicate) {
         super(unfiltered, predicate);
      }

      public boolean equals(@CheckForNull Object object) {
         return Sets.equalsImpl(this, object);
      }

      public int hashCode() {
         return Sets.hashCodeImpl(this);
      }
   }

   private static class FilteredSortedSet extends FilteredSet implements SortedSet {
      FilteredSortedSet(SortedSet unfiltered, Predicate predicate) {
         super(unfiltered, predicate);
      }

      @CheckForNull
      public Comparator comparator() {
         return ((SortedSet)this.unfiltered).comparator();
      }

      public SortedSet subSet(@ParametricNullness Object fromElement, @ParametricNullness Object toElement) {
         return new FilteredSortedSet(((SortedSet)this.unfiltered).subSet(fromElement, toElement), this.predicate);
      }

      public SortedSet headSet(@ParametricNullness Object toElement) {
         return new FilteredSortedSet(((SortedSet)this.unfiltered).headSet(toElement), this.predicate);
      }

      public SortedSet tailSet(@ParametricNullness Object fromElement) {
         return new FilteredSortedSet(((SortedSet)this.unfiltered).tailSet(fromElement), this.predicate);
      }

      @ParametricNullness
      public Object first() {
         return Iterators.find(this.unfiltered.iterator(), this.predicate);
      }

      @ParametricNullness
      public Object last() {
         SortedSet<E> sortedUnfiltered = (SortedSet)this.unfiltered;

         while(true) {
            E element = (E)sortedUnfiltered.last();
            if (this.predicate.apply(element)) {
               return element;
            }

            sortedUnfiltered = sortedUnfiltered.headSet(element);
         }
      }
   }

   @GwtIncompatible
   private static class FilteredNavigableSet extends FilteredSortedSet implements NavigableSet {
      FilteredNavigableSet(NavigableSet unfiltered, Predicate predicate) {
         super(unfiltered, predicate);
      }

      NavigableSet unfiltered() {
         return (NavigableSet)this.unfiltered;
      }

      @CheckForNull
      public Object lower(@ParametricNullness Object e) {
         return Iterators.find(this.unfiltered().headSet(e, false).descendingIterator(), this.predicate, (Object)null);
      }

      @CheckForNull
      public Object floor(@ParametricNullness Object e) {
         return Iterators.find(this.unfiltered().headSet(e, true).descendingIterator(), this.predicate, (Object)null);
      }

      @CheckForNull
      public Object ceiling(@ParametricNullness Object e) {
         return Iterables.find(this.unfiltered().tailSet(e, true), this.predicate, (Object)null);
      }

      @CheckForNull
      public Object higher(@ParametricNullness Object e) {
         return Iterables.find(this.unfiltered().tailSet(e, false), this.predicate, (Object)null);
      }

      @CheckForNull
      public Object pollFirst() {
         return Iterables.removeFirstMatching(this.unfiltered(), this.predicate);
      }

      @CheckForNull
      public Object pollLast() {
         return Iterables.removeFirstMatching(this.unfiltered().descendingSet(), this.predicate);
      }

      public NavigableSet descendingSet() {
         return Sets.filter(this.unfiltered().descendingSet(), this.predicate);
      }

      public Iterator descendingIterator() {
         return Iterators.filter(this.unfiltered().descendingIterator(), this.predicate);
      }

      @ParametricNullness
      public Object last() {
         return Iterators.find(this.unfiltered().descendingIterator(), this.predicate);
      }

      public NavigableSet subSet(@ParametricNullness Object fromElement, boolean fromInclusive, @ParametricNullness Object toElement, boolean toInclusive) {
         return Sets.filter(this.unfiltered().subSet(fromElement, fromInclusive, toElement, toInclusive), this.predicate);
      }

      public NavigableSet headSet(@ParametricNullness Object toElement, boolean inclusive) {
         return Sets.filter(this.unfiltered().headSet(toElement, inclusive), this.predicate);
      }

      public NavigableSet tailSet(@ParametricNullness Object fromElement, boolean inclusive) {
         return Sets.filter(this.unfiltered().tailSet(fromElement, inclusive), this.predicate);
      }
   }

   private static final class CartesianSet extends ForwardingCollection implements Set {
      private final transient ImmutableList axes;
      private final transient CartesianList delegate;

      static Set create(List sets) {
         ImmutableList.Builder<ImmutableSet<E>> axesBuilder = new ImmutableList.Builder(sets.size());

         for(Set set : sets) {
            ImmutableSet<E> copy = ImmutableSet.copyOf((Collection)set);
            if (copy.isEmpty()) {
               return ImmutableSet.of();
            }

            axesBuilder.add((Object)copy);
         }

         final ImmutableList<ImmutableSet<E>> axes = axesBuilder.build();
         ImmutableList<List<E>> listAxes = new ImmutableList() {
            public int size() {
               return axes.size();
            }

            public List get(int index) {
               return ((ImmutableSet)axes.get(index)).asList();
            }

            boolean isPartialView() {
               return true;
            }
         };
         return new CartesianSet(axes, new CartesianList(listAxes));
      }

      private CartesianSet(ImmutableList axes, CartesianList delegate) {
         this.axes = axes;
         this.delegate = delegate;
      }

      protected Collection delegate() {
         return this.delegate;
      }

      public boolean contains(@CheckForNull Object object) {
         if (!(object instanceof List)) {
            return false;
         } else {
            List<?> list = (List)object;
            if (list.size() != this.axes.size()) {
               return false;
            } else {
               int i = 0;

               for(Object o : list) {
                  if (!((ImmutableSet)this.axes.get(i)).contains(o)) {
                     return false;
                  }

                  ++i;
               }

               return true;
            }
         }
      }

      public boolean equals(@CheckForNull Object object) {
         if (object instanceof CartesianSet) {
            CartesianSet<?> that = (CartesianSet)object;
            return this.axes.equals(that.axes);
         } else {
            return super.equals(object);
         }
      }

      public int hashCode() {
         int adjust = this.size() - 1;

         for(int i = 0; i < this.axes.size(); ++i) {
            adjust *= 31;
            adjust = ~(~adjust);
         }

         int hash = 1;

         for(Set axis : this.axes) {
            hash = 31 * hash + this.size() / axis.size() * axis.hashCode();
            hash = ~(~hash);
         }

         hash += adjust;
         return ~(~hash);
      }
   }

   private static final class SubSet extends AbstractSet {
      private final ImmutableMap inputSet;
      private final int mask;

      SubSet(ImmutableMap inputSet, int mask) {
         this.inputSet = inputSet;
         this.mask = mask;
      }

      public Iterator iterator() {
         return new UnmodifiableIterator() {
            final ImmutableList elements;
            int remainingSetBits;

            {
               this.elements = SubSet.this.inputSet.keySet().asList();
               this.remainingSetBits = SubSet.this.mask;
            }

            public boolean hasNext() {
               return this.remainingSetBits != 0;
            }

            public Object next() {
               int index = Integer.numberOfTrailingZeros(this.remainingSetBits);
               if (index == 32) {
                  throw new NoSuchElementException();
               } else {
                  this.remainingSetBits &= ~(1 << index);
                  return this.elements.get(index);
               }
            }
         };
      }

      public int size() {
         return Integer.bitCount(this.mask);
      }

      public boolean contains(@CheckForNull Object o) {
         Integer index = (Integer)this.inputSet.get(o);
         return index != null && (this.mask & 1 << index) != 0;
      }
   }

   private static final class PowerSet extends AbstractSet {
      final ImmutableMap inputSet;

      PowerSet(Set input) {
         Preconditions.checkArgument(input.size() <= 30, "Too many elements to create power set: %s > 30", input.size());
         this.inputSet = Maps.indexMap(input);
      }

      public int size() {
         return 1 << this.inputSet.size();
      }

      public boolean isEmpty() {
         return false;
      }

      public Iterator iterator() {
         return new AbstractIndexedListIterator(this.size()) {
            protected Set get(final int setBits) {
               return new SubSet(PowerSet.this.inputSet, setBits);
            }
         };
      }

      public boolean contains(@CheckForNull Object obj) {
         if (obj instanceof Set) {
            Set<?> set = (Set)obj;
            return this.inputSet.keySet().containsAll(set);
         } else {
            return false;
         }
      }

      public boolean equals(@CheckForNull Object obj) {
         if (obj instanceof PowerSet) {
            PowerSet<?> that = (PowerSet)obj;
            return this.inputSet.keySet().equals(that.inputSet.keySet());
         } else {
            return super.equals(obj);
         }
      }

      public int hashCode() {
         return this.inputSet.keySet().hashCode() << this.inputSet.size() - 1;
      }

      public String toString() {
         return "powerSet(" + this.inputSet + ")";
      }
   }

   static final class UnmodifiableNavigableSet extends ForwardingSortedSet implements NavigableSet, Serializable {
      private final NavigableSet delegate;
      private final SortedSet unmodifiableDelegate;
      @CheckForNull
      private transient UnmodifiableNavigableSet descendingSet;
      private static final long serialVersionUID = 0L;

      UnmodifiableNavigableSet(NavigableSet delegate) {
         this.delegate = (NavigableSet)Preconditions.checkNotNull(delegate);
         this.unmodifiableDelegate = Collections.unmodifiableSortedSet(delegate);
      }

      protected SortedSet delegate() {
         return this.unmodifiableDelegate;
      }

      public boolean removeIf(java.util.function.Predicate filter) {
         throw new UnsupportedOperationException();
      }

      public Stream stream() {
         return this.delegate.stream();
      }

      public Stream parallelStream() {
         return this.delegate.parallelStream();
      }

      public void forEach(Consumer action) {
         this.delegate.forEach(action);
      }

      @CheckForNull
      public Object lower(@ParametricNullness Object e) {
         return this.delegate.lower(e);
      }

      @CheckForNull
      public Object floor(@ParametricNullness Object e) {
         return this.delegate.floor(e);
      }

      @CheckForNull
      public Object ceiling(@ParametricNullness Object e) {
         return this.delegate.ceiling(e);
      }

      @CheckForNull
      public Object higher(@ParametricNullness Object e) {
         return this.delegate.higher(e);
      }

      @CheckForNull
      public Object pollFirst() {
         throw new UnsupportedOperationException();
      }

      @CheckForNull
      public Object pollLast() {
         throw new UnsupportedOperationException();
      }

      public NavigableSet descendingSet() {
         UnmodifiableNavigableSet<E> result = this.descendingSet;
         if (result == null) {
            result = this.descendingSet = new UnmodifiableNavigableSet(this.delegate.descendingSet());
            result.descendingSet = this;
         }

         return result;
      }

      public Iterator descendingIterator() {
         return Iterators.unmodifiableIterator(this.delegate.descendingIterator());
      }

      public NavigableSet subSet(@ParametricNullness Object fromElement, boolean fromInclusive, @ParametricNullness Object toElement, boolean toInclusive) {
         return Sets.unmodifiableNavigableSet(this.delegate.subSet(fromElement, fromInclusive, toElement, toInclusive));
      }

      public NavigableSet headSet(@ParametricNullness Object toElement, boolean inclusive) {
         return Sets.unmodifiableNavigableSet(this.delegate.headSet(toElement, inclusive));
      }

      public NavigableSet tailSet(@ParametricNullness Object fromElement, boolean inclusive) {
         return Sets.unmodifiableNavigableSet(this.delegate.tailSet(fromElement, inclusive));
      }
   }

   @GwtIncompatible
   static class DescendingSet extends ForwardingNavigableSet {
      private final NavigableSet forward;

      DescendingSet(NavigableSet forward) {
         this.forward = forward;
      }

      protected NavigableSet delegate() {
         return this.forward;
      }

      @CheckForNull
      public Object lower(@ParametricNullness Object e) {
         return this.forward.higher(e);
      }

      @CheckForNull
      public Object floor(@ParametricNullness Object e) {
         return this.forward.ceiling(e);
      }

      @CheckForNull
      public Object ceiling(@ParametricNullness Object e) {
         return this.forward.floor(e);
      }

      @CheckForNull
      public Object higher(@ParametricNullness Object e) {
         return this.forward.lower(e);
      }

      @CheckForNull
      public Object pollFirst() {
         return this.forward.pollLast();
      }

      @CheckForNull
      public Object pollLast() {
         return this.forward.pollFirst();
      }

      public NavigableSet descendingSet() {
         return this.forward;
      }

      public Iterator descendingIterator() {
         return this.forward.iterator();
      }

      public NavigableSet subSet(@ParametricNullness Object fromElement, boolean fromInclusive, @ParametricNullness Object toElement, boolean toInclusive) {
         return this.forward.subSet(toElement, toInclusive, fromElement, fromInclusive).descendingSet();
      }

      public SortedSet subSet(@ParametricNullness Object fromElement, @ParametricNullness Object toElement) {
         return this.standardSubSet(fromElement, toElement);
      }

      public NavigableSet headSet(@ParametricNullness Object toElement, boolean inclusive) {
         return this.forward.tailSet(toElement, inclusive).descendingSet();
      }

      public SortedSet headSet(@ParametricNullness Object toElement) {
         return this.standardHeadSet(toElement);
      }

      public NavigableSet tailSet(@ParametricNullness Object fromElement, boolean inclusive) {
         return this.forward.headSet(fromElement, inclusive).descendingSet();
      }

      public SortedSet tailSet(@ParametricNullness Object fromElement) {
         return this.standardTailSet(fromElement);
      }

      public Comparator comparator() {
         Comparator<? super E> forwardComparator = this.forward.comparator();
         return forwardComparator == null ? Ordering.natural().reverse() : reverse(forwardComparator);
      }

      private static Ordering reverse(Comparator forward) {
         return Ordering.from(forward).reverse();
      }

      @ParametricNullness
      public Object first() {
         return this.forward.last();
      }

      @ParametricNullness
      public Object last() {
         return this.forward.first();
      }

      public Iterator iterator() {
         return this.forward.descendingIterator();
      }

      public @Nullable Object[] toArray() {
         return this.standardToArray();
      }

      public Object[] toArray(Object[] array) {
         return this.standardToArray(array);
      }

      public String toString() {
         return this.standardToString();
      }
   }
}
