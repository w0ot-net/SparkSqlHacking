package org.apache.curator.shaded.com.google.common.collect;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import java.util.stream.Stream;
import javax.annotation.CheckForNull;
import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;
import org.apache.curator.shaded.com.google.common.annotations.GwtIncompatible;
import org.apache.curator.shaded.com.google.common.base.Function;
import org.apache.curator.shaded.com.google.common.base.Joiner;
import org.apache.curator.shaded.com.google.common.base.Optional;
import org.apache.curator.shaded.com.google.common.base.Preconditions;
import org.apache.curator.shaded.com.google.common.base.Predicate;
import org.apache.curator.shaded.com.google.errorprone.annotations.CanIgnoreReturnValue;
import org.apache.curator.shaded.com.google.errorprone.annotations.InlineMe;
import org.checkerframework.checker.nullness.qual.Nullable;

@ElementTypesAreNonnullByDefault
@GwtCompatible(
   emulated = true
)
public abstract class FluentIterable implements Iterable {
   private final Optional iterableDelegate;

   protected FluentIterable() {
      this.iterableDelegate = Optional.absent();
   }

   FluentIterable(Iterable iterable) {
      this.iterableDelegate = Optional.of(iterable);
   }

   private Iterable getDelegate() {
      return (Iterable)this.iterableDelegate.or((Object)this);
   }

   public static FluentIterable from(final Iterable iterable) {
      return iterable instanceof FluentIterable ? (FluentIterable)iterable : new FluentIterable(iterable) {
         public Iterator iterator() {
            return iterable.iterator();
         }
      };
   }

   public static FluentIterable from(Object[] elements) {
      return from((Iterable)Arrays.asList(elements));
   }

   /** @deprecated */
   @Deprecated
   @InlineMe(
      replacement = "checkNotNull(iterable)",
      staticImports = {"org.apache.curator.shaded.com.google.common.base.Preconditions.checkNotNull"}
   )
   public static FluentIterable from(FluentIterable iterable) {
      return (FluentIterable)Preconditions.checkNotNull(iterable);
   }

   public static FluentIterable concat(Iterable a, Iterable b) {
      return concatNoDefensiveCopy(a, b);
   }

   public static FluentIterable concat(Iterable a, Iterable b, Iterable c) {
      return concatNoDefensiveCopy(a, b, c);
   }

   public static FluentIterable concat(Iterable a, Iterable b, Iterable c, Iterable d) {
      return concatNoDefensiveCopy(a, b, c, d);
   }

   public static FluentIterable concat(Iterable... inputs) {
      return concatNoDefensiveCopy((Iterable[])Arrays.copyOf(inputs, inputs.length));
   }

   public static FluentIterable concat(final Iterable inputs) {
      Preconditions.checkNotNull(inputs);
      return new FluentIterable() {
         public Iterator iterator() {
            return Iterators.concat(Iterators.transform(inputs.iterator(), Iterable::iterator));
         }
      };
   }

   private static FluentIterable concatNoDefensiveCopy(final Iterable... inputs) {
      for(Iterable input : inputs) {
         Preconditions.checkNotNull(input);
      }

      return new FluentIterable() {
         public Iterator iterator() {
            return Iterators.concat((Iterator)(new AbstractIndexedListIterator(inputs.length) {
               public Iterator get(int i) {
                  return inputs[i].iterator();
               }
            }));
         }
      };
   }

   public static FluentIterable of() {
      return from((Iterable)Collections.emptyList());
   }

   public static FluentIterable of(@ParametricNullness Object element, Object... elements) {
      return from((Iterable)Lists.asList(element, elements));
   }

   public String toString() {
      return Iterables.toString(this.getDelegate());
   }

   public final int size() {
      return Iterables.size(this.getDelegate());
   }

   public final boolean contains(@CheckForNull Object target) {
      return Iterables.contains(this.getDelegate(), target);
   }

   public final FluentIterable cycle() {
      return from(Iterables.cycle(this.getDelegate()));
   }

   public final FluentIterable append(Iterable other) {
      return concat(this.getDelegate(), other);
   }

   public final FluentIterable append(Object... elements) {
      return concat(this.getDelegate(), Arrays.asList(elements));
   }

   public final FluentIterable filter(Predicate predicate) {
      return from(Iterables.filter(this.getDelegate(), predicate));
   }

   @GwtIncompatible
   public final FluentIterable filter(Class type) {
      return from(Iterables.filter(this.getDelegate(), type));
   }

   public final boolean anyMatch(Predicate predicate) {
      return Iterables.any(this.getDelegate(), predicate);
   }

   public final boolean allMatch(Predicate predicate) {
      return Iterables.all(this.getDelegate(), predicate);
   }

   public final Optional firstMatch(Predicate predicate) {
      return Iterables.tryFind(this.getDelegate(), predicate);
   }

   public final FluentIterable transform(Function function) {
      return from(Iterables.transform(this.getDelegate(), function));
   }

   public FluentIterable transformAndConcat(Function function) {
      return concat((Iterable)this.transform(function));
   }

   public final Optional first() {
      Iterator<E> iterator = this.getDelegate().iterator();
      return iterator.hasNext() ? Optional.of(iterator.next()) : Optional.absent();
   }

   public final Optional last() {
      Iterable<E> iterable = this.getDelegate();
      if (iterable instanceof List) {
         List<E> list = (List)iterable;
         return list.isEmpty() ? Optional.absent() : Optional.of(list.get(list.size() - 1));
      } else {
         Iterator<E> iterator = iterable.iterator();
         if (!iterator.hasNext()) {
            return Optional.absent();
         } else if (iterable instanceof SortedSet) {
            SortedSet<E> sortedSet = (SortedSet)iterable;
            return Optional.of(sortedSet.last());
         } else {
            E current;
            do {
               current = (E)iterator.next();
            } while(iterator.hasNext());

            return Optional.of(current);
         }
      }
   }

   public final FluentIterable skip(int numberToSkip) {
      return from(Iterables.skip(this.getDelegate(), numberToSkip));
   }

   public final FluentIterable limit(int maxSize) {
      return from(Iterables.limit(this.getDelegate(), maxSize));
   }

   public final boolean isEmpty() {
      return !this.getDelegate().iterator().hasNext();
   }

   public final ImmutableList toList() {
      return ImmutableList.copyOf(this.getDelegate());
   }

   public final ImmutableList toSortedList(Comparator comparator) {
      return Ordering.from(comparator).immutableSortedCopy(this.getDelegate());
   }

   public final ImmutableSet toSet() {
      return ImmutableSet.copyOf(this.getDelegate());
   }

   public final ImmutableSortedSet toSortedSet(Comparator comparator) {
      return ImmutableSortedSet.copyOf(comparator, this.getDelegate());
   }

   public final ImmutableMultiset toMultiset() {
      return ImmutableMultiset.copyOf(this.getDelegate());
   }

   public final ImmutableMap toMap(Function valueFunction) {
      return Maps.toMap(this.getDelegate(), valueFunction);
   }

   public final ImmutableListMultimap index(Function keyFunction) {
      return Multimaps.index(this.getDelegate(), keyFunction);
   }

   public final ImmutableMap uniqueIndex(Function keyFunction) {
      return Maps.uniqueIndex(this.getDelegate(), keyFunction);
   }

   @GwtIncompatible
   public final @Nullable Object[] toArray(Class type) {
      return Iterables.toArray(this.getDelegate(), type);
   }

   @CanIgnoreReturnValue
   public final Collection copyInto(Collection collection) {
      Preconditions.checkNotNull(collection);
      Iterable<E> iterable = this.getDelegate();
      if (iterable instanceof Collection) {
         collection.addAll((Collection)iterable);
      } else {
         for(Object item : iterable) {
            collection.add(item);
         }
      }

      return collection;
   }

   public final String join(Joiner joiner) {
      return joiner.join((Iterable)this);
   }

   @ParametricNullness
   public final Object get(int position) {
      return Iterables.get(this.getDelegate(), position);
   }

   public final Stream stream() {
      return Streams.stream(this.getDelegate());
   }

   private static class FromIterableFunction implements Function {
      public FluentIterable apply(Iterable fromObject) {
         return FluentIterable.from(fromObject);
      }
   }
}
