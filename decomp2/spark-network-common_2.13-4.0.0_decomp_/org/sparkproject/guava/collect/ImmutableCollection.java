package org.sparkproject.guava.collect;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.DoNotCall;
import com.google.errorprone.annotations.DoNotMock;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Predicate;
import javax.annotation.CheckForNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.sparkproject.guava.annotations.GwtCompatible;
import org.sparkproject.guava.annotations.GwtIncompatible;
import org.sparkproject.guava.annotations.J2ktIncompatible;
import org.sparkproject.guava.base.Preconditions;

@DoNotMock("Use ImmutableList.of or another implementation")
@ElementTypesAreNonnullByDefault
@GwtCompatible(
   emulated = true
)
public abstract class ImmutableCollection extends AbstractCollection implements Serializable {
   static final int SPLITERATOR_CHARACTERISTICS = 1296;
   private static final Object[] EMPTY_ARRAY = new Object[0];
   private static final long serialVersionUID = -889275714L;

   ImmutableCollection() {
   }

   public abstract UnmodifiableIterator iterator();

   public Spliterator spliterator() {
      return Spliterators.spliterator(this, 1296);
   }

   @J2ktIncompatible
   public final Object[] toArray() {
      return this.toArray(EMPTY_ARRAY);
   }

   @CanIgnoreReturnValue
   public final Object[] toArray(Object[] other) {
      Preconditions.checkNotNull(other);
      int size = this.size();
      if (other.length < size) {
         Object[] internal = this.internalArray();
         if (internal != null) {
            return Platform.copy(internal, this.internalArrayStart(), this.internalArrayEnd(), other);
         }

         other = (T[])ObjectArrays.newArray(other, size);
      } else if (other.length > size) {
         other[size] = null;
      }

      this.copyIntoArray(other, 0);
      return other;
   }

   @CheckForNull
   Object[] internalArray() {
      return null;
   }

   int internalArrayStart() {
      throw new UnsupportedOperationException();
   }

   int internalArrayEnd() {
      throw new UnsupportedOperationException();
   }

   public abstract boolean contains(@CheckForNull Object object);

   /** @deprecated */
   @Deprecated
   @CanIgnoreReturnValue
   @DoNotCall("Always throws UnsupportedOperationException")
   public final boolean add(Object e) {
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
   public final boolean removeIf(Predicate filter) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
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

   public ImmutableList asList() {
      switch (this.size()) {
         case 0:
            return ImmutableList.of();
         case 1:
            return ImmutableList.of(this.iterator().next());
         default:
            return new RegularImmutableAsList(this, this.toArray());
      }
   }

   abstract boolean isPartialView();

   @CanIgnoreReturnValue
   int copyIntoArray(@Nullable Object[] dst, int offset) {
      for(Object e : this) {
         dst[offset++] = e;
      }

      return offset;
   }

   @J2ktIncompatible
   @GwtIncompatible
   Object writeReplace() {
      return new ImmutableList.SerializedForm(this.toArray());
   }

   @J2ktIncompatible
   private void readObject(ObjectInputStream stream) throws InvalidObjectException {
      throw new InvalidObjectException("Use SerializedForm");
   }

   @DoNotMock
   public abstract static class Builder {
      static final int DEFAULT_INITIAL_CAPACITY = 4;

      static int expandedCapacity(int oldCapacity, int minCapacity) {
         if (minCapacity < 0) {
            throw new IllegalArgumentException("cannot store more than Integer.MAX_VALUE elements");
         } else if (minCapacity <= oldCapacity) {
            return oldCapacity;
         } else {
            int newCapacity = oldCapacity + (oldCapacity >> 1) + 1;
            if (newCapacity < minCapacity) {
               newCapacity = Integer.highestOneBit(minCapacity - 1) << 1;
            }

            if (newCapacity < 0) {
               newCapacity = Integer.MAX_VALUE;
            }

            return newCapacity;
         }
      }

      Builder() {
      }

      @CanIgnoreReturnValue
      public abstract Builder add(Object element);

      @CanIgnoreReturnValue
      public Builder add(Object... elements) {
         for(Object element : elements) {
            this.add(element);
         }

         return this;
      }

      @CanIgnoreReturnValue
      public Builder addAll(Iterable elements) {
         for(Object element : elements) {
            this.add(element);
         }

         return this;
      }

      @CanIgnoreReturnValue
      public Builder addAll(Iterator elements) {
         while(elements.hasNext()) {
            this.add(elements.next());
         }

         return this;
      }

      public abstract ImmutableCollection build();
   }
}
