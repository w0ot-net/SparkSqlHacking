package org.sparkproject.guava.collect;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Set;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Stream;
import javax.annotation.CheckForNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.sparkproject.guava.annotations.GwtCompatible;
import org.sparkproject.guava.annotations.GwtIncompatible;
import org.sparkproject.guava.base.Function;
import org.sparkproject.guava.base.Optional;
import org.sparkproject.guava.base.Preconditions;
import org.sparkproject.guava.base.Predicate;
import org.sparkproject.guava.base.Predicates;

@ElementTypesAreNonnullByDefault
@GwtCompatible(
   emulated = true
)
public final class Iterables {
   private Iterables() {
   }

   public static Iterable unmodifiableIterable(final Iterable iterable) {
      Preconditions.checkNotNull(iterable);
      return (Iterable)(!(iterable instanceof UnmodifiableIterable) && !(iterable instanceof ImmutableCollection) ? new UnmodifiableIterable(iterable) : iterable);
   }

   /** @deprecated */
   @Deprecated
   public static Iterable unmodifiableIterable(ImmutableCollection iterable) {
      return (Iterable)Preconditions.checkNotNull(iterable);
   }

   public static int size(Iterable iterable) {
      return iterable instanceof Collection ? ((Collection)iterable).size() : Iterators.size(iterable.iterator());
   }

   public static boolean contains(Iterable iterable, @CheckForNull Object element) {
      if (iterable instanceof Collection) {
         Collection<?> collection = (Collection)iterable;
         return Collections2.safeContains(collection, element);
      } else {
         return Iterators.contains(iterable.iterator(), element);
      }
   }

   @CanIgnoreReturnValue
   public static boolean removeAll(Iterable removeFrom, Collection elementsToRemove) {
      return removeFrom instanceof Collection ? ((Collection)removeFrom).removeAll((Collection)Preconditions.checkNotNull(elementsToRemove)) : Iterators.removeAll(removeFrom.iterator(), elementsToRemove);
   }

   @CanIgnoreReturnValue
   public static boolean retainAll(Iterable removeFrom, Collection elementsToRetain) {
      return removeFrom instanceof Collection ? ((Collection)removeFrom).retainAll((Collection)Preconditions.checkNotNull(elementsToRetain)) : Iterators.retainAll(removeFrom.iterator(), elementsToRetain);
   }

   @CanIgnoreReturnValue
   public static boolean removeIf(Iterable removeFrom, Predicate predicate) {
      return removeFrom instanceof Collection ? ((Collection)removeFrom).removeIf(predicate) : Iterators.removeIf(removeFrom.iterator(), predicate);
   }

   @CheckForNull
   static Object removeFirstMatching(Iterable removeFrom, Predicate predicate) {
      Preconditions.checkNotNull(predicate);
      Iterator<T> iterator = removeFrom.iterator();

      while(iterator.hasNext()) {
         T next = (T)iterator.next();
         if (predicate.apply(next)) {
            iterator.remove();
            return next;
         }
      }

      return null;
   }

   public static boolean elementsEqual(Iterable iterable1, Iterable iterable2) {
      if (iterable1 instanceof Collection && iterable2 instanceof Collection) {
         Collection<?> collection1 = (Collection)iterable1;
         Collection<?> collection2 = (Collection)iterable2;
         if (collection1.size() != collection2.size()) {
            return false;
         }
      }

      return Iterators.elementsEqual(iterable1.iterator(), iterable2.iterator());
   }

   public static String toString(Iterable iterable) {
      return Iterators.toString(iterable.iterator());
   }

   @ParametricNullness
   public static Object getOnlyElement(Iterable iterable) {
      return Iterators.getOnlyElement(iterable.iterator());
   }

   @ParametricNullness
   public static Object getOnlyElement(Iterable iterable, @ParametricNullness Object defaultValue) {
      return Iterators.getOnlyElement(iterable.iterator(), defaultValue);
   }

   @GwtIncompatible
   public static Object[] toArray(Iterable iterable, Class type) {
      return toArray(iterable, ObjectArrays.newArray((Class)type, 0));
   }

   static Object[] toArray(Iterable iterable, Object[] array) {
      Collection<? extends T> collection = castOrCopyToCollection(iterable);
      return collection.toArray(array);
   }

   static @Nullable Object[] toArray(Iterable iterable) {
      return castOrCopyToCollection(iterable).toArray();
   }

   private static Collection castOrCopyToCollection(Iterable iterable) {
      return (Collection)(iterable instanceof Collection ? (Collection)iterable : Lists.newArrayList(iterable.iterator()));
   }

   @CanIgnoreReturnValue
   public static boolean addAll(Collection addTo, Iterable elementsToAdd) {
      if (elementsToAdd instanceof Collection) {
         Collection<? extends T> c = (Collection)elementsToAdd;
         return addTo.addAll(c);
      } else {
         return Iterators.addAll(addTo, ((Iterable)Preconditions.checkNotNull(elementsToAdd)).iterator());
      }
   }

   public static int frequency(Iterable iterable, @CheckForNull Object element) {
      if (iterable instanceof Multiset) {
         return ((Multiset)iterable).count(element);
      } else if (iterable instanceof Set) {
         return ((Set)iterable).contains(element) ? 1 : 0;
      } else {
         return Iterators.frequency(iterable.iterator(), element);
      }
   }

   public static Iterable cycle(final Iterable iterable) {
      Preconditions.checkNotNull(iterable);
      return new FluentIterable() {
         public Iterator iterator() {
            return Iterators.cycle(iterable);
         }

         public Spliterator spliterator() {
            return Stream.generate(() -> iterable).flatMap(Streams::stream).spliterator();
         }

         public String toString() {
            return iterable.toString() + " (cycled)";
         }
      };
   }

   @SafeVarargs
   public static Iterable cycle(Object... elements) {
      return cycle((Iterable)Lists.newArrayList(elements));
   }

   public static Iterable concat(Iterable a, Iterable b) {
      return FluentIterable.concat(a, b);
   }

   public static Iterable concat(Iterable a, Iterable b, Iterable c) {
      return FluentIterable.concat(a, b, c);
   }

   public static Iterable concat(Iterable a, Iterable b, Iterable c, Iterable d) {
      return FluentIterable.concat(a, b, c, d);
   }

   @SafeVarargs
   public static Iterable concat(Iterable... inputs) {
      return FluentIterable.concat(inputs);
   }

   public static Iterable concat(Iterable inputs) {
      return FluentIterable.concat(inputs);
   }

   public static Iterable partition(final Iterable iterable, final int size) {
      Preconditions.checkNotNull(iterable);
      Preconditions.checkArgument(size > 0);
      return new FluentIterable() {
         public Iterator iterator() {
            return Iterators.partition(iterable.iterator(), size);
         }
      };
   }

   public static Iterable paddedPartition(final Iterable iterable, final int size) {
      Preconditions.checkNotNull(iterable);
      Preconditions.checkArgument(size > 0);
      return new FluentIterable() {
         public Iterator iterator() {
            return Iterators.paddedPartition(iterable.iterator(), size);
         }
      };
   }

   public static Iterable filter(final Iterable unfiltered, final Predicate retainIfTrue) {
      Preconditions.checkNotNull(unfiltered);
      Preconditions.checkNotNull(retainIfTrue);
      return new FluentIterable() {
         public Iterator iterator() {
            return Iterators.filter(unfiltered.iterator(), retainIfTrue);
         }

         public void forEach(Consumer action) {
            Preconditions.checkNotNull(action);
            unfiltered.forEach((a) -> {
               if (retainIfTrue.test(a)) {
                  action.accept(a);
               }

            });
         }

         public Spliterator spliterator() {
            return CollectSpliterators.filter(unfiltered.spliterator(), retainIfTrue);
         }
      };
   }

   @GwtIncompatible
   public static Iterable filter(final Iterable unfiltered, final Class desiredType) {
      Preconditions.checkNotNull(unfiltered);
      Preconditions.checkNotNull(desiredType);
      return filter(unfiltered, Predicates.instanceOf(desiredType));
   }

   public static boolean any(Iterable iterable, Predicate predicate) {
      return Iterators.any(iterable.iterator(), predicate);
   }

   public static boolean all(Iterable iterable, Predicate predicate) {
      return Iterators.all(iterable.iterator(), predicate);
   }

   @ParametricNullness
   public static Object find(Iterable iterable, Predicate predicate) {
      return Iterators.find(iterable.iterator(), predicate);
   }

   @CheckForNull
   public static Object find(Iterable iterable, Predicate predicate, @CheckForNull Object defaultValue) {
      return Iterators.find(iterable.iterator(), predicate, defaultValue);
   }

   public static Optional tryFind(Iterable iterable, Predicate predicate) {
      return Iterators.tryFind(iterable.iterator(), predicate);
   }

   public static int indexOf(Iterable iterable, Predicate predicate) {
      return Iterators.indexOf(iterable.iterator(), predicate);
   }

   public static Iterable transform(final Iterable fromIterable, final Function function) {
      Preconditions.checkNotNull(fromIterable);
      Preconditions.checkNotNull(function);
      return new FluentIterable() {
         public Iterator iterator() {
            return Iterators.transform(fromIterable.iterator(), function);
         }

         public void forEach(Consumer action) {
            Preconditions.checkNotNull(action);
            fromIterable.forEach((f) -> action.accept(function.apply(f)));
         }

         public Spliterator spliterator() {
            return CollectSpliterators.map(fromIterable.spliterator(), function);
         }
      };
   }

   @ParametricNullness
   public static Object get(Iterable iterable, int position) {
      Preconditions.checkNotNull(iterable);
      return iterable instanceof List ? ((List)iterable).get(position) : Iterators.get(iterable.iterator(), position);
   }

   @ParametricNullness
   public static Object get(Iterable iterable, int position, @ParametricNullness Object defaultValue) {
      Preconditions.checkNotNull(iterable);
      Iterators.checkNonnegative(position);
      if (iterable instanceof List) {
         List<? extends T> list = Lists.cast(iterable);
         return position < list.size() ? list.get(position) : defaultValue;
      } else {
         Iterator<? extends T> iterator = iterable.iterator();
         Iterators.advance(iterator, position);
         return Iterators.getNext(iterator, defaultValue);
      }
   }

   @ParametricNullness
   public static Object getFirst(Iterable iterable, @ParametricNullness Object defaultValue) {
      return Iterators.getNext(iterable.iterator(), defaultValue);
   }

   @ParametricNullness
   public static Object getLast(Iterable iterable) {
      if (iterable instanceof List) {
         List<T> list = (List)iterable;
         if (list.isEmpty()) {
            throw new NoSuchElementException();
         } else {
            return getLastInNonemptyList(list);
         }
      } else {
         return Iterators.getLast(iterable.iterator());
      }
   }

   @ParametricNullness
   public static Object getLast(Iterable iterable, @ParametricNullness Object defaultValue) {
      if (iterable instanceof Collection) {
         Collection<? extends T> c = (Collection)iterable;
         if (c.isEmpty()) {
            return defaultValue;
         }

         if (iterable instanceof List) {
            return getLastInNonemptyList(Lists.cast(iterable));
         }
      }

      return Iterators.getLast(iterable.iterator(), defaultValue);
   }

   @ParametricNullness
   private static Object getLastInNonemptyList(List list) {
      return list.get(list.size() - 1);
   }

   public static Iterable skip(final Iterable iterable, final int numberToSkip) {
      Preconditions.checkNotNull(iterable);
      Preconditions.checkArgument(numberToSkip >= 0, "number to skip cannot be negative");
      return new FluentIterable() {
         public Iterator iterator() {
            if (iterable instanceof List) {
               List<T> list = (List)iterable;
               int toSkip = Math.min(list.size(), numberToSkip);
               return list.subList(toSkip, list.size()).iterator();
            } else {
               final Iterator<T> iterator = iterable.iterator();
               Iterators.advance(iterator, numberToSkip);
               return new Iterator() {
                  boolean atStart = true;

                  public boolean hasNext() {
                     return iterator.hasNext();
                  }

                  @ParametricNullness
                  public Object next() {
                     T result = (T)iterator.next();
                     this.atStart = false;
                     return result;
                  }

                  public void remove() {
                     CollectPreconditions.checkRemove(!this.atStart);
                     iterator.remove();
                  }
               };
            }
         }

         public Spliterator spliterator() {
            if (iterable instanceof List) {
               List<T> list = (List)iterable;
               int toSkip = Math.min(list.size(), numberToSkip);
               return list.subList(toSkip, list.size()).spliterator();
            } else {
               return Streams.stream(iterable).skip((long)numberToSkip).spliterator();
            }
         }
      };
   }

   public static Iterable limit(final Iterable iterable, final int limitSize) {
      Preconditions.checkNotNull(iterable);
      Preconditions.checkArgument(limitSize >= 0, "limit is negative");
      return new FluentIterable() {
         public Iterator iterator() {
            return Iterators.limit(iterable.iterator(), limitSize);
         }

         public Spliterator spliterator() {
            return Streams.stream(iterable).limit((long)limitSize).spliterator();
         }
      };
   }

   public static Iterable consumingIterable(final Iterable iterable) {
      Preconditions.checkNotNull(iterable);
      return new FluentIterable() {
         public Iterator iterator() {
            return (Iterator)(iterable instanceof Queue ? new ConsumingQueueIterator((Queue)iterable) : Iterators.consumingIterator(iterable.iterator()));
         }

         public String toString() {
            return "Iterables.consumingIterable(...)";
         }
      };
   }

   public static boolean isEmpty(Iterable iterable) {
      if (iterable instanceof Collection) {
         return ((Collection)iterable).isEmpty();
      } else {
         return !iterable.iterator().hasNext();
      }
   }

   public static Iterable mergeSorted(final Iterable iterables, final Comparator comparator) {
      Preconditions.checkNotNull(iterables, "iterables");
      Preconditions.checkNotNull(comparator, "comparator");
      Iterable<T> iterable = new FluentIterable() {
         public Iterator iterator() {
            return Iterators.mergeSorted(Iterables.transform(iterables, Iterable::iterator), comparator);
         }
      };
      return new UnmodifiableIterable(iterable);
   }

   private static final class UnmodifiableIterable extends FluentIterable {
      private final Iterable iterable;

      private UnmodifiableIterable(Iterable iterable) {
         this.iterable = iterable;
      }

      public Iterator iterator() {
         return Iterators.unmodifiableIterator(this.iterable.iterator());
      }

      public void forEach(Consumer action) {
         this.iterable.forEach(action);
      }

      public Spliterator spliterator() {
         return this.iterable.spliterator();
      }

      public String toString() {
         return this.iterable.toString();
      }
   }
}
