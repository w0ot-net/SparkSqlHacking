package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Ints;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.ObjIntConsumer;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@GwtCompatible(
   emulated = true
)
@J2ktIncompatible
public final class EnumMultiset extends AbstractMultiset implements Serializable {
   private transient Class type;
   private transient Enum[] enumConstants;
   private transient int[] counts;
   private transient int distinctElements;
   private transient long size;
   @GwtIncompatible
   private static final long serialVersionUID = 0L;

   public static EnumMultiset create(Class type) {
      return new EnumMultiset(type);
   }

   public static EnumMultiset create(Iterable elements) {
      Iterator<E> iterator = elements.iterator();
      Preconditions.checkArgument(iterator.hasNext(), "EnumMultiset constructor passed empty Iterable");
      EnumMultiset<E> multiset = new EnumMultiset(((Enum)iterator.next()).getDeclaringClass());
      Iterables.addAll(multiset, elements);
      return multiset;
   }

   public static EnumMultiset create(Iterable elements, Class type) {
      EnumMultiset<E> result = create(type);
      Iterables.addAll(result, elements);
      return result;
   }

   private EnumMultiset(Class type) {
      this.type = type;
      Preconditions.checkArgument(type.isEnum());
      this.enumConstants = (Enum[])type.getEnumConstants();
      this.counts = new int[this.enumConstants.length];
   }

   private boolean isActuallyE(@CheckForNull Object o) {
      if (!(o instanceof Enum)) {
         return false;
      } else {
         Enum<?> e = (Enum)o;
         int index = e.ordinal();
         return index < this.enumConstants.length && this.enumConstants[index] == e;
      }
   }

   private void checkIsE(Object element) {
      Preconditions.checkNotNull(element);
      if (!this.isActuallyE(element)) {
         throw new ClassCastException("Expected an " + this.type + " but got " + element);
      }
   }

   int distinctElements() {
      return this.distinctElements;
   }

   public int size() {
      return Ints.saturatedCast(this.size);
   }

   public int count(@CheckForNull Object element) {
      if (element != null && this.isActuallyE(element)) {
         Enum<?> e = (Enum)element;
         return this.counts[e.ordinal()];
      } else {
         return 0;
      }
   }

   @CanIgnoreReturnValue
   public int add(Enum element, int occurrences) {
      this.checkIsE(element);
      CollectPreconditions.checkNonnegative(occurrences, "occurrences");
      if (occurrences == 0) {
         return this.count(element);
      } else {
         int index = element.ordinal();
         int oldCount = this.counts[index];
         long newCount = (long)oldCount + (long)occurrences;
         Preconditions.checkArgument(newCount <= 2147483647L, "too many occurrences: %s", newCount);
         this.counts[index] = (int)newCount;
         if (oldCount == 0) {
            ++this.distinctElements;
         }

         this.size += (long)occurrences;
         return oldCount;
      }
   }

   @CanIgnoreReturnValue
   public int remove(@CheckForNull Object element, int occurrences) {
      if (element != null && this.isActuallyE(element)) {
         Enum<?> e = (Enum)element;
         CollectPreconditions.checkNonnegative(occurrences, "occurrences");
         if (occurrences == 0) {
            return this.count(element);
         } else {
            int index = e.ordinal();
            int oldCount = this.counts[index];
            if (oldCount == 0) {
               return 0;
            } else {
               if (oldCount <= occurrences) {
                  this.counts[index] = 0;
                  --this.distinctElements;
                  this.size -= (long)oldCount;
               } else {
                  this.counts[index] = oldCount - occurrences;
                  this.size -= (long)occurrences;
               }

               return oldCount;
            }
         }
      } else {
         return 0;
      }
   }

   @CanIgnoreReturnValue
   public int setCount(Enum element, int count) {
      this.checkIsE(element);
      CollectPreconditions.checkNonnegative(count, "count");
      int index = element.ordinal();
      int oldCount = this.counts[index];
      this.counts[index] = count;
      this.size += (long)(count - oldCount);
      if (oldCount == 0 && count > 0) {
         ++this.distinctElements;
      } else if (oldCount > 0 && count == 0) {
         --this.distinctElements;
      }

      return oldCount;
   }

   public void clear() {
      Arrays.fill(this.counts, 0);
      this.size = 0L;
      this.distinctElements = 0;
   }

   Iterator elementIterator() {
      return new Itr() {
         Enum output(int index) {
            return EnumMultiset.this.enumConstants[index];
         }
      };
   }

   Iterator entryIterator() {
      return new Itr() {
         Multiset.Entry output(final int index) {
            return new Multisets.AbstractEntry() {
               public Enum getElement() {
                  return EnumMultiset.this.enumConstants[index];
               }

               public int getCount() {
                  return EnumMultiset.this.counts[index];
               }
            };
         }
      };
   }

   public void forEachEntry(ObjIntConsumer action) {
      Preconditions.checkNotNull(action);

      for(int i = 0; i < this.enumConstants.length; ++i) {
         if (this.counts[i] > 0) {
            action.accept(this.enumConstants[i], this.counts[i]);
         }
      }

   }

   public Iterator iterator() {
      return Multisets.iteratorImpl(this);
   }

   @GwtIncompatible
   private void writeObject(ObjectOutputStream stream) throws IOException {
      stream.defaultWriteObject();
      stream.writeObject(this.type);
      Serialization.writeMultiset(this, stream);
   }

   @GwtIncompatible
   private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
      stream.defaultReadObject();
      Class<E> localType = (Class)Objects.requireNonNull(stream.readObject());
      this.type = localType;
      this.enumConstants = (Enum[])this.type.getEnumConstants();
      this.counts = new int[this.enumConstants.length];
      Serialization.populateMultiset(this, stream);
   }

   abstract class Itr implements Iterator {
      int index = 0;
      int toRemove = -1;

      abstract Object output(int index);

      public boolean hasNext() {
         while(this.index < EnumMultiset.this.enumConstants.length) {
            if (EnumMultiset.this.counts[this.index] > 0) {
               return true;
            }

            ++this.index;
         }

         return false;
      }

      public Object next() {
         if (!this.hasNext()) {
            throw new NoSuchElementException();
         } else {
            T result = (T)this.output(this.index);
            this.toRemove = this.index++;
            return result;
         }
      }

      public void remove() {
         CollectPreconditions.checkRemove(this.toRemove >= 0);
         if (EnumMultiset.this.counts[this.toRemove] > 0) {
            EnumMultiset.this.distinctElements--;
            EnumMultiset.this.size = (long)EnumMultiset.this.counts[this.toRemove];
            EnumMultiset.this.counts[this.toRemove] = 0;
         }

         this.toRemove = -1;
      }
   }
}
