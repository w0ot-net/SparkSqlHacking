package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Ints;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.InvalidObjectException;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.ObjIntConsumer;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@GwtCompatible(
   emulated = true
)
abstract class AbstractMapBasedMultiset extends AbstractMultiset implements Serializable {
   private transient Map backingMap;
   private transient long size;
   @GwtIncompatible
   @J2ktIncompatible
   private static final long serialVersionUID = -2250766705698539974L;

   protected AbstractMapBasedMultiset(Map backingMap) {
      Preconditions.checkArgument(backingMap.isEmpty());
      this.backingMap = backingMap;
   }

   void setBackingMap(Map backingMap) {
      this.backingMap = backingMap;
   }

   public Set entrySet() {
      return super.entrySet();
   }

   Iterator elementIterator() {
      final Iterator<Map.Entry<E, Count>> backingEntries = this.backingMap.entrySet().iterator();
      return new Iterator() {
         @CheckForNull
         Map.Entry toRemove;

         public boolean hasNext() {
            return backingEntries.hasNext();
         }

         @ParametricNullness
         public Object next() {
            Map.Entry<E, Count> mapEntry = (Map.Entry)backingEntries.next();
            this.toRemove = mapEntry;
            return mapEntry.getKey();
         }

         public void remove() {
            Preconditions.checkState(this.toRemove != null, "no calls to next() since the last call to remove()");
            AbstractMapBasedMultiset.this.size = (long)((Count)this.toRemove.getValue()).getAndSet(0);
            backingEntries.remove();
            this.toRemove = null;
         }
      };
   }

   Iterator entryIterator() {
      final Iterator<Map.Entry<E, Count>> backingEntries = this.backingMap.entrySet().iterator();
      return new Iterator() {
         @CheckForNull
         Map.Entry toRemove;

         public boolean hasNext() {
            return backingEntries.hasNext();
         }

         public Multiset.Entry next() {
            final Map.Entry<E, Count> mapEntry = (Map.Entry)backingEntries.next();
            this.toRemove = mapEntry;
            return new Multisets.AbstractEntry() {
               @ParametricNullness
               public Object getElement() {
                  return mapEntry.getKey();
               }

               public int getCount() {
                  Count count = (Count)mapEntry.getValue();
                  if (count == null || count.get() == 0) {
                     Count frequency = (Count)AbstractMapBasedMultiset.this.backingMap.get(this.getElement());
                     if (frequency != null) {
                        return frequency.get();
                     }
                  }

                  return count == null ? 0 : count.get();
               }
            };
         }

         public void remove() {
            Preconditions.checkState(this.toRemove != null, "no calls to next() since the last call to remove()");
            AbstractMapBasedMultiset.this.size = (long)((Count)this.toRemove.getValue()).getAndSet(0);
            backingEntries.remove();
            this.toRemove = null;
         }
      };
   }

   public void forEachEntry(ObjIntConsumer action) {
      Preconditions.checkNotNull(action);
      this.backingMap.forEach((element, count) -> action.accept(element, count.get()));
   }

   public void clear() {
      for(Count frequency : this.backingMap.values()) {
         frequency.set(0);
      }

      this.backingMap.clear();
      this.size = 0L;
   }

   int distinctElements() {
      return this.backingMap.size();
   }

   public int size() {
      return Ints.saturatedCast(this.size);
   }

   public Iterator iterator() {
      return new MapBasedMultisetIterator();
   }

   public int count(@CheckForNull Object element) {
      Count frequency = (Count)Maps.safeGet(this.backingMap, element);
      return frequency == null ? 0 : frequency.get();
   }

   @CanIgnoreReturnValue
   public int add(@ParametricNullness Object element, int occurrences) {
      if (occurrences == 0) {
         return this.count(element);
      } else {
         Preconditions.checkArgument(occurrences > 0, "occurrences cannot be negative: %s", occurrences);
         Count frequency = (Count)this.backingMap.get(element);
         int oldCount;
         if (frequency == null) {
            oldCount = 0;
            this.backingMap.put(element, new Count(occurrences));
         } else {
            oldCount = frequency.get();
            long newCount = (long)oldCount + (long)occurrences;
            Preconditions.checkArgument(newCount <= 2147483647L, "too many occurrences: %s", newCount);
            frequency.add(occurrences);
         }

         this.size += (long)occurrences;
         return oldCount;
      }
   }

   @CanIgnoreReturnValue
   public int remove(@CheckForNull Object element, int occurrences) {
      if (occurrences == 0) {
         return this.count(element);
      } else {
         Preconditions.checkArgument(occurrences > 0, "occurrences cannot be negative: %s", occurrences);
         Count frequency = (Count)this.backingMap.get(element);
         if (frequency == null) {
            return 0;
         } else {
            int oldCount = frequency.get();
            int numberRemoved;
            if (oldCount > occurrences) {
               numberRemoved = occurrences;
            } else {
               numberRemoved = oldCount;
               this.backingMap.remove(element);
            }

            frequency.add(-numberRemoved);
            this.size -= (long)numberRemoved;
            return oldCount;
         }
      }
   }

   @CanIgnoreReturnValue
   public int setCount(@ParametricNullness Object element, int count) {
      CollectPreconditions.checkNonnegative(count, "count");
      int oldCount;
      if (count == 0) {
         Count existingCounter = (Count)this.backingMap.remove(element);
         oldCount = getAndSet(existingCounter, count);
      } else {
         Count existingCounter = (Count)this.backingMap.get(element);
         oldCount = getAndSet(existingCounter, count);
         if (existingCounter == null) {
            this.backingMap.put(element, new Count(count));
         }
      }

      this.size += (long)(count - oldCount);
      return oldCount;
   }

   private static int getAndSet(@CheckForNull Count i, int count) {
      return i == null ? 0 : i.getAndSet(count);
   }

   @GwtIncompatible
   @J2ktIncompatible
   private void readObjectNoData() throws ObjectStreamException {
      throw new InvalidObjectException("Stream data required");
   }

   private class MapBasedMultisetIterator implements Iterator {
      final Iterator entryIterator;
      @CheckForNull
      Map.Entry currentEntry;
      int occurrencesLeft;
      boolean canRemove;

      MapBasedMultisetIterator() {
         this.entryIterator = AbstractMapBasedMultiset.this.backingMap.entrySet().iterator();
      }

      public boolean hasNext() {
         return this.occurrencesLeft > 0 || this.entryIterator.hasNext();
      }

      @ParametricNullness
      public Object next() {
         if (this.occurrencesLeft == 0) {
            this.currentEntry = (Map.Entry)this.entryIterator.next();
            this.occurrencesLeft = ((Count)this.currentEntry.getValue()).get();
         }

         --this.occurrencesLeft;
         this.canRemove = true;
         return ((Map.Entry)Objects.requireNonNull(this.currentEntry)).getKey();
      }

      public void remove() {
         CollectPreconditions.checkRemove(this.canRemove);
         int frequency = ((Count)((Map.Entry)Objects.requireNonNull(this.currentEntry)).getValue()).get();
         if (frequency <= 0) {
            throw new ConcurrentModificationException();
         } else {
            if (((Count)this.currentEntry.getValue()).addAndGet(-1) == 0) {
               this.entryIterator.remove();
            }

            AbstractMapBasedMultiset.this.size--;
            this.canRemove = false;
         }
      }
   }
}
