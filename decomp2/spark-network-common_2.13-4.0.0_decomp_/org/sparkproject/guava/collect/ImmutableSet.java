package org.sparkproject.guava.collect;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.concurrent.LazyInit;
import com.google.j2objc.annotations.RetainedWith;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import java.util.SortedSet;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Collector;
import javax.annotation.CheckForNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.sparkproject.guava.annotations.GwtCompatible;
import org.sparkproject.guava.annotations.GwtIncompatible;
import org.sparkproject.guava.annotations.J2ktIncompatible;
import org.sparkproject.guava.annotations.VisibleForTesting;
import org.sparkproject.guava.base.Preconditions;
import org.sparkproject.guava.math.IntMath;

@ElementTypesAreNonnullByDefault
@GwtCompatible(
   serializable = true,
   emulated = true
)
public abstract class ImmutableSet extends ImmutableCollection implements Set {
   static final int SPLITERATOR_CHARACTERISTICS = 1297;
   static final int MAX_TABLE_SIZE = 1073741824;
   private static final double DESIRED_LOAD_FACTOR = 0.7;
   private static final int CUTOFF = 751619276;
   private static final long serialVersionUID = -889275714L;

   public static Collector toImmutableSet() {
      return CollectCollectors.toImmutableSet();
   }

   public static ImmutableSet of() {
      return RegularImmutableSet.EMPTY;
   }

   public static ImmutableSet of(Object e1) {
      return new SingletonImmutableSet(e1);
   }

   public static ImmutableSet of(Object e1, Object e2) {
      return (new RegularSetBuilderImpl(2)).add(e1).add(e2).review().build();
   }

   public static ImmutableSet of(Object e1, Object e2, Object e3) {
      return (new RegularSetBuilderImpl(3)).add(e1).add(e2).add(e3).review().build();
   }

   public static ImmutableSet of(Object e1, Object e2, Object e3, Object e4) {
      return (new RegularSetBuilderImpl(4)).add(e1).add(e2).add(e3).add(e4).review().build();
   }

   public static ImmutableSet of(Object e1, Object e2, Object e3, Object e4, Object e5) {
      return (new RegularSetBuilderImpl(5)).add(e1).add(e2).add(e3).add(e4).add(e5).review().build();
   }

   @SafeVarargs
   public static ImmutableSet of(Object e1, Object e2, Object e3, Object e4, Object e5, Object e6, Object... others) {
      Preconditions.checkArgument(others.length <= 2147483641, "the total number of elements must fit in an int");
      SetBuilderImpl<E> builder = new RegularSetBuilderImpl(6 + others.length);
      builder = builder.add(e1).add(e2).add(e3).add(e4).add(e5).add(e6);

      for(int i = 0; i < others.length; ++i) {
         builder = builder.add(others[i]);
      }

      return builder.review().build();
   }

   public static ImmutableSet copyOf(Collection elements) {
      if (elements instanceof ImmutableSet && !(elements instanceof SortedSet)) {
         ImmutableSet<E> set = (ImmutableSet)elements;
         if (!set.isPartialView()) {
            return set;
         }
      } else if (elements instanceof EnumSet) {
         return copyOfEnumSet((EnumSet)elements);
      }

      if (elements.isEmpty()) {
         return of();
      } else {
         E[] array = (E[])elements.toArray();
         int expectedSize = elements instanceof Set ? array.length : estimatedSizeForUnknownDuplication(array.length);
         return fromArrayWithExpectedSize(array, expectedSize);
      }
   }

   public static ImmutableSet copyOf(Iterable elements) {
      return elements instanceof Collection ? copyOf((Collection)elements) : copyOf(elements.iterator());
   }

   public static ImmutableSet copyOf(Iterator elements) {
      if (!elements.hasNext()) {
         return of();
      } else {
         E first = (E)elements.next();
         return !elements.hasNext() ? of(first) : (new Builder()).add(first).addAll(elements).build();
      }
   }

   public static ImmutableSet copyOf(Object[] elements) {
      return fromArrayWithExpectedSize(elements, estimatedSizeForUnknownDuplication(elements.length));
   }

   private static ImmutableSet fromArrayWithExpectedSize(Object[] elements, int expectedSize) {
      switch (elements.length) {
         case 0:
            return of();
         case 1:
            return of(elements[0]);
         default:
            SetBuilderImpl<E> builder = new RegularSetBuilderImpl(expectedSize);

            for(int i = 0; i < elements.length; ++i) {
               builder = builder.add(elements[i]);
            }

            return builder.review().build();
      }
   }

   private static ImmutableSet copyOfEnumSet(EnumSet enumSet) {
      return ImmutableEnumSet.asImmutable(EnumSet.copyOf(enumSet));
   }

   ImmutableSet() {
   }

   boolean isHashCodeFast() {
      return false;
   }

   public boolean equals(@CheckForNull Object object) {
      if (object == this) {
         return true;
      } else {
         return object instanceof ImmutableSet && this.isHashCodeFast() && ((ImmutableSet)object).isHashCodeFast() && this.hashCode() != object.hashCode() ? false : Sets.equalsImpl(this, object);
      }
   }

   public int hashCode() {
      return Sets.hashCodeImpl(this);
   }

   public abstract UnmodifiableIterator iterator();

   @J2ktIncompatible
   Object writeReplace() {
      return new SerializedForm(this.toArray());
   }

   @J2ktIncompatible
   private void readObject(ObjectInputStream stream) throws InvalidObjectException {
      throw new InvalidObjectException("Use SerializedForm");
   }

   public static Builder builder() {
      return new Builder();
   }

   public static Builder builderWithExpectedSize(int expectedSize) {
      CollectPreconditions.checkNonnegative(expectedSize, "expectedSize");
      return new Builder(expectedSize);
   }

   static int chooseTableSize(int setSize) {
      setSize = Math.max(setSize, 2);
      if (setSize >= 751619276) {
         Preconditions.checkArgument(setSize < 1073741824, "collection too large");
         return 1073741824;
      } else {
         int tableSize;
         for(tableSize = Integer.highestOneBit(setSize - 1) << 1; (double)tableSize * 0.7 < (double)setSize; tableSize <<= 1) {
         }

         return tableSize;
      }
   }

   private static int estimatedSizeForUnknownDuplication(int inputElementsIncludingAnyDuplicates) {
      return inputElementsIncludingAnyDuplicates < 4 ? inputElementsIncludingAnyDuplicates : Math.max(4, IntMath.sqrt(inputElementsIncludingAnyDuplicates, RoundingMode.CEILING));
   }

   @GwtCompatible
   abstract static class CachingAsList extends ImmutableSet {
      @LazyInit
      @CheckForNull
      @RetainedWith
      private transient ImmutableList asList;

      public ImmutableList asList() {
         ImmutableList<E> result = this.asList;
         return result == null ? (this.asList = this.createAsList()) : result;
      }

      ImmutableList createAsList() {
         return new RegularImmutableAsList(this, this.toArray());
      }

      @J2ktIncompatible
      @GwtIncompatible
      Object writeReplace() {
         return super.writeReplace();
      }
   }

   abstract static class Indexed extends CachingAsList {
      abstract Object get(int index);

      public UnmodifiableIterator iterator() {
         return this.asList().iterator();
      }

      public Spliterator spliterator() {
         return CollectSpliterators.indexed(this.size(), 1297, this::get);
      }

      public void forEach(Consumer consumer) {
         Preconditions.checkNotNull(consumer);
         int n = this.size();

         for(int i = 0; i < n; ++i) {
            consumer.accept(this.get(i));
         }

      }

      int copyIntoArray(@Nullable Object[] dst, int offset) {
         return this.asList().copyIntoArray(dst, offset);
      }

      ImmutableList createAsList() {
         return new ImmutableAsList() {
            public Object get(int index) {
               return Indexed.this.get(index);
            }

            Indexed delegateCollection() {
               return Indexed.this;
            }

            @J2ktIncompatible
            @GwtIncompatible
            Object writeReplace() {
               return super.writeReplace();
            }
         };
      }

      @J2ktIncompatible
      @GwtIncompatible
      Object writeReplace() {
         return super.writeReplace();
      }
   }

   @J2ktIncompatible
   private static class SerializedForm implements Serializable {
      final Object[] elements;
      private static final long serialVersionUID = 0L;

      SerializedForm(Object[] elements) {
         this.elements = elements;
      }

      Object readResolve() {
         return ImmutableSet.copyOf(this.elements);
      }
   }

   public static class Builder extends ImmutableCollection.Builder {
      @CheckForNull
      private SetBuilderImpl impl;
      boolean forceCopy;

      public Builder() {
         this(0);
      }

      Builder(int capacity) {
         if (capacity > 0) {
            this.impl = new RegularSetBuilderImpl(capacity);
         } else {
            this.impl = ImmutableSet.EmptySetBuilderImpl.instance();
         }

      }

      Builder(boolean subclass) {
         this.impl = null;
      }

      @VisibleForTesting
      void forceJdk() {
         Objects.requireNonNull(this.impl);
         this.impl = new JdkBackedSetBuilderImpl(this.impl);
      }

      final void copyIfNecessary() {
         if (this.forceCopy) {
            this.copy();
            this.forceCopy = false;
         }

      }

      void copy() {
         Objects.requireNonNull(this.impl);
         this.impl = this.impl.copy();
      }

      @CanIgnoreReturnValue
      public Builder add(Object element) {
         Objects.requireNonNull(this.impl);
         Preconditions.checkNotNull(element);
         this.copyIfNecessary();
         this.impl = this.impl.add(element);
         return this;
      }

      @CanIgnoreReturnValue
      public Builder add(Object... elements) {
         super.add(elements);
         return this;
      }

      @CanIgnoreReturnValue
      public Builder addAll(Iterable elements) {
         super.addAll(elements);
         return this;
      }

      @CanIgnoreReturnValue
      public Builder addAll(Iterator elements) {
         super.addAll(elements);
         return this;
      }

      @CanIgnoreReturnValue
      Builder combine(Builder other) {
         Objects.requireNonNull(this.impl);
         Objects.requireNonNull(other.impl);
         this.copyIfNecessary();
         this.impl = this.impl.combine(other.impl);
         return this;
      }

      public ImmutableSet build() {
         Objects.requireNonNull(this.impl);
         this.forceCopy = true;
         this.impl = this.impl.review();
         return this.impl.build();
      }
   }

   private abstract static class SetBuilderImpl {
      Object[] dedupedElements;
      int distinct;

      SetBuilderImpl(int expectedCapacity) {
         this.dedupedElements = new Object[expectedCapacity];
         this.distinct = 0;
      }

      SetBuilderImpl(SetBuilderImpl toCopy) {
         this.dedupedElements = Arrays.copyOf(toCopy.dedupedElements, toCopy.dedupedElements.length);
         this.distinct = toCopy.distinct;
      }

      private void ensureCapacity(int minCapacity) {
         if (minCapacity > this.dedupedElements.length) {
            int newCapacity = ImmutableCollection.Builder.expandedCapacity(this.dedupedElements.length, minCapacity);
            this.dedupedElements = Arrays.copyOf(this.dedupedElements, newCapacity);
         }

      }

      final void addDedupedElement(Object e) {
         this.ensureCapacity(this.distinct + 1);
         this.dedupedElements[this.distinct++] = e;
      }

      abstract SetBuilderImpl add(Object e);

      final SetBuilderImpl combine(SetBuilderImpl other) {
         SetBuilderImpl<E> result = this;

         for(int i = 0; i < other.distinct; ++i) {
            result = result.add(Objects.requireNonNull(other.dedupedElements[i]));
         }

         return result;
      }

      abstract SetBuilderImpl copy();

      SetBuilderImpl review() {
         return this;
      }

      abstract ImmutableSet build();
   }

   private static final class EmptySetBuilderImpl extends SetBuilderImpl {
      private static final EmptySetBuilderImpl INSTANCE = new EmptySetBuilderImpl();

      static SetBuilderImpl instance() {
         return INSTANCE;
      }

      private EmptySetBuilderImpl() {
         super(0);
      }

      SetBuilderImpl add(Object e) {
         return (new RegularSetBuilderImpl(4)).add(e);
      }

      SetBuilderImpl copy() {
         return this;
      }

      ImmutableSet build() {
         return ImmutableSet.of();
      }
   }

   private static final class RegularSetBuilderImpl extends SetBuilderImpl {
      @CheckForNull
      private @Nullable Object[] hashTable;
      private int maxRunBeforeFallback;
      private int expandTableThreshold;
      private int hashCode;
      static final int MAX_RUN_MULTIPLIER = 13;

      RegularSetBuilderImpl(int expectedCapacity) {
         super(expectedCapacity);
         this.hashTable = null;
         this.maxRunBeforeFallback = 0;
         this.expandTableThreshold = 0;
      }

      RegularSetBuilderImpl(RegularSetBuilderImpl toCopy) {
         super(toCopy);
         this.hashTable = toCopy.hashTable == null ? null : (Object[])toCopy.hashTable.clone();
         this.maxRunBeforeFallback = toCopy.maxRunBeforeFallback;
         this.expandTableThreshold = toCopy.expandTableThreshold;
         this.hashCode = toCopy.hashCode;
      }

      SetBuilderImpl add(Object e) {
         Preconditions.checkNotNull(e);
         if (this.hashTable == null) {
            if (this.distinct == 0) {
               this.addDedupedElement(e);
               return this;
            } else {
               this.ensureTableCapacity(this.dedupedElements.length);
               E elem = (E)this.dedupedElements[0];
               --this.distinct;
               return this.insertInHashTable(elem).add(e);
            }
         } else {
            return this.insertInHashTable(e);
         }
      }

      private SetBuilderImpl insertInHashTable(Object e) {
         Objects.requireNonNull(this.hashTable);
         int eHash = e.hashCode();
         int i0 = Hashing.smear(eHash);
         int mask = this.hashTable.length - 1;

         for(int i = i0; i - i0 < this.maxRunBeforeFallback; ++i) {
            int index = i & mask;
            Object tableEntry = this.hashTable[index];
            if (tableEntry == null) {
               this.addDedupedElement(e);
               this.hashTable[index] = e;
               this.hashCode += eHash;
               this.ensureTableCapacity(this.distinct);
               return this;
            }

            if (tableEntry.equals(e)) {
               return this;
            }
         }

         return (new JdkBackedSetBuilderImpl(this)).add(e);
      }

      SetBuilderImpl copy() {
         return new RegularSetBuilderImpl(this);
      }

      SetBuilderImpl review() {
         if (this.hashTable == null) {
            return this;
         } else {
            int targetTableSize = ImmutableSet.chooseTableSize(this.distinct);
            if (targetTableSize * 2 < this.hashTable.length) {
               this.hashTable = rebuildHashTable(targetTableSize, this.dedupedElements, this.distinct);
               this.maxRunBeforeFallback = maxRunBeforeFallback(targetTableSize);
               this.expandTableThreshold = (int)(0.7 * (double)targetTableSize);
            }

            return (SetBuilderImpl)(hashFloodingDetected(this.hashTable) ? new JdkBackedSetBuilderImpl(this) : this);
         }
      }

      ImmutableSet build() {
         switch (this.distinct) {
            case 0:
               return ImmutableSet.of();
            case 1:
               return ImmutableSet.of(Objects.requireNonNull(this.dedupedElements[0]));
            default:
               Object[] elements = this.distinct == this.dedupedElements.length ? this.dedupedElements : Arrays.copyOf(this.dedupedElements, this.distinct);
               return new RegularImmutableSet(elements, this.hashCode, Objects.requireNonNull(this.hashTable), this.hashTable.length - 1);
         }
      }

      static Object[] rebuildHashTable(int newTableSize, Object[] elements, int n) {
         Object[] hashTable = new Object[newTableSize];
         int mask = hashTable.length - 1;

         for(int i = 0; i < n; ++i) {
            Object e = Objects.requireNonNull(elements[i]);
            int j0 = Hashing.smear(e.hashCode());
            int j = j0;

            while(true) {
               int index = j & mask;
               if (hashTable[index] == null) {
                  hashTable[index] = e;
                  break;
               }

               ++j;
            }
         }

         return hashTable;
      }

      void ensureTableCapacity(int minCapacity) {
         int newTableSize;
         if (this.hashTable == null) {
            newTableSize = ImmutableSet.chooseTableSize(minCapacity);
            this.hashTable = new Object[newTableSize];
         } else {
            if (minCapacity <= this.expandTableThreshold || this.hashTable.length >= 1073741824) {
               return;
            }

            newTableSize = this.hashTable.length * 2;
            this.hashTable = rebuildHashTable(newTableSize, this.dedupedElements, this.distinct);
         }

         this.maxRunBeforeFallback = maxRunBeforeFallback(newTableSize);
         this.expandTableThreshold = (int)(0.7 * (double)newTableSize);
      }

      static boolean hashFloodingDetected(@Nullable Object[] hashTable) {
         int maxRunBeforeFallback = maxRunBeforeFallback(hashTable.length);
         int mask = hashTable.length - 1;
         int knownRunStart = 0;
         int knownRunEnd = 0;

         label35:
         while(knownRunStart < hashTable.length) {
            if (knownRunStart == knownRunEnd && hashTable[knownRunStart] == null) {
               if (hashTable[knownRunStart + maxRunBeforeFallback - 1 & mask] == null) {
                  knownRunStart += maxRunBeforeFallback;
               } else {
                  ++knownRunStart;
               }

               knownRunEnd = knownRunStart;
            } else {
               for(int j = knownRunStart + maxRunBeforeFallback - 1; j >= knownRunEnd; --j) {
                  if (hashTable[j & mask] == null) {
                     knownRunEnd = knownRunStart + maxRunBeforeFallback;
                     knownRunStart = j + 1;
                     continue label35;
                  }
               }

               return true;
            }
         }

         return false;
      }

      static int maxRunBeforeFallback(int tableSize) {
         return 13 * IntMath.log2(tableSize, RoundingMode.UNNECESSARY);
      }
   }

   private static final class JdkBackedSetBuilderImpl extends SetBuilderImpl {
      private final Set delegate;

      JdkBackedSetBuilderImpl(SetBuilderImpl toCopy) {
         super(toCopy);
         this.delegate = Sets.newHashSetWithExpectedSize(this.distinct);

         for(int i = 0; i < this.distinct; ++i) {
            this.delegate.add(Objects.requireNonNull(this.dedupedElements[i]));
         }

      }

      SetBuilderImpl add(Object e) {
         Preconditions.checkNotNull(e);
         if (this.delegate.add(e)) {
            this.addDedupedElement(e);
         }

         return this;
      }

      SetBuilderImpl copy() {
         return new JdkBackedSetBuilderImpl(this);
      }

      ImmutableSet build() {
         switch (this.distinct) {
            case 0:
               return ImmutableSet.of();
            case 1:
               return ImmutableSet.of(Objects.requireNonNull(this.dedupedElements[0]));
            default:
               return new JdkBackedImmutableSet(this.delegate, ImmutableList.asImmutableList(this.dedupedElements, this.distinct));
         }
      }
   }
}
