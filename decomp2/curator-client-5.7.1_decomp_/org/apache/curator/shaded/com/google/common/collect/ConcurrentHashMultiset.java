package org.apache.curator.shaded.com.google.common.collect;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.CheckForNull;
import org.apache.curator.shaded.com.google.common.annotations.GwtIncompatible;
import org.apache.curator.shaded.com.google.common.annotations.J2ktIncompatible;
import org.apache.curator.shaded.com.google.common.annotations.VisibleForTesting;
import org.apache.curator.shaded.com.google.common.base.Preconditions;
import org.apache.curator.shaded.com.google.common.math.IntMath;
import org.apache.curator.shaded.com.google.common.primitives.Ints;
import org.apache.curator.shaded.com.google.errorprone.annotations.CanIgnoreReturnValue;

@ElementTypesAreNonnullByDefault
@J2ktIncompatible
@GwtIncompatible
public final class ConcurrentHashMultiset extends AbstractMultiset implements Serializable {
   private final transient ConcurrentMap countMap;
   private static final long serialVersionUID = 1L;

   public static ConcurrentHashMultiset create() {
      return new ConcurrentHashMultiset(new ConcurrentHashMap());
   }

   public static ConcurrentHashMultiset create(Iterable elements) {
      ConcurrentHashMultiset<E> multiset = create();
      Iterables.addAll(multiset, elements);
      return multiset;
   }

   public static ConcurrentHashMultiset create(ConcurrentMap countMap) {
      return new ConcurrentHashMultiset(countMap);
   }

   @VisibleForTesting
   ConcurrentHashMultiset(ConcurrentMap countMap) {
      Preconditions.checkArgument(countMap.isEmpty(), "the backing map (%s) must be empty", (Object)countMap);
      this.countMap = countMap;
   }

   public int count(@CheckForNull Object element) {
      AtomicInteger existingCounter = (AtomicInteger)Maps.safeGet(this.countMap, element);
      return existingCounter == null ? 0 : existingCounter.get();
   }

   public int size() {
      long sum = 0L;

      for(AtomicInteger value : this.countMap.values()) {
         sum += (long)value.get();
      }

      return Ints.saturatedCast(sum);
   }

   public Object[] toArray() {
      return this.snapshot().toArray();
   }

   public Object[] toArray(Object[] array) {
      return this.snapshot().toArray(array);
   }

   private List snapshot() {
      List<E> list = Lists.newArrayListWithExpectedSize(this.size());

      for(Multiset.Entry entry : this.entrySet()) {
         E element = (E)entry.getElement();

         for(int i = entry.getCount(); i > 0; --i) {
            list.add(element);
         }
      }

      return list;
   }

   @CanIgnoreReturnValue
   public int add(Object element, int occurrences) {
      Preconditions.checkNotNull(element);
      if (occurrences == 0) {
         return this.count(element);
      } else {
         CollectPreconditions.checkPositive(occurrences, "occurrences");

         while(true) {
            AtomicInteger existingCounter = (AtomicInteger)Maps.safeGet(this.countMap, element);
            if (existingCounter == null) {
               existingCounter = (AtomicInteger)this.countMap.putIfAbsent(element, new AtomicInteger(occurrences));
               if (existingCounter == null) {
                  return 0;
               }
            }

            while(true) {
               int oldValue = existingCounter.get();
               if (oldValue == 0) {
                  AtomicInteger newCounter = new AtomicInteger(occurrences);
                  if (this.countMap.putIfAbsent(element, newCounter) == null || this.countMap.replace(element, existingCounter, newCounter)) {
                     return 0;
                  }
                  break;
               }

               try {
                  int newValue = IntMath.checkedAdd(oldValue, occurrences);
                  if (existingCounter.compareAndSet(oldValue, newValue)) {
                     return oldValue;
                  }
               } catch (ArithmeticException var6) {
                  throw new IllegalArgumentException("Overflow adding " + occurrences + " occurrences to a count of " + oldValue);
               }
            }
         }
      }
   }

   @CanIgnoreReturnValue
   public int remove(@CheckForNull Object element, int occurrences) {
      if (occurrences == 0) {
         return this.count(element);
      } else {
         CollectPreconditions.checkPositive(occurrences, "occurrences");
         AtomicInteger existingCounter = (AtomicInteger)Maps.safeGet(this.countMap, element);
         if (existingCounter == null) {
            return 0;
         } else {
            int oldValue;
            int newValue;
            do {
               oldValue = existingCounter.get();
               if (oldValue == 0) {
                  return 0;
               }

               newValue = Math.max(0, oldValue - occurrences);
            } while(!existingCounter.compareAndSet(oldValue, newValue));

            if (newValue == 0) {
               this.countMap.remove(element, existingCounter);
            }

            return oldValue;
         }
      }
   }

   @CanIgnoreReturnValue
   public boolean removeExactly(@CheckForNull Object element, int occurrences) {
      if (occurrences == 0) {
         return true;
      } else {
         CollectPreconditions.checkPositive(occurrences, "occurrences");
         AtomicInteger existingCounter = (AtomicInteger)Maps.safeGet(this.countMap, element);
         if (existingCounter == null) {
            return false;
         } else {
            int oldValue;
            int newValue;
            do {
               oldValue = existingCounter.get();
               if (oldValue < occurrences) {
                  return false;
               }

               newValue = oldValue - occurrences;
            } while(!existingCounter.compareAndSet(oldValue, newValue));

            if (newValue == 0) {
               this.countMap.remove(element, existingCounter);
            }

            return true;
         }
      }
   }

   @CanIgnoreReturnValue
   public int setCount(Object element, int count) {
      Preconditions.checkNotNull(element);
      CollectPreconditions.checkNonnegative(count, "count");

      label40:
      while(true) {
         AtomicInteger existingCounter = (AtomicInteger)Maps.safeGet(this.countMap, element);
         if (existingCounter == null) {
            if (count == 0) {
               return 0;
            }

            existingCounter = (AtomicInteger)this.countMap.putIfAbsent(element, new AtomicInteger(count));
            if (existingCounter == null) {
               return 0;
            }
         }

         int oldValue;
         do {
            oldValue = existingCounter.get();
            if (oldValue == 0) {
               if (count == 0) {
                  return 0;
               }

               AtomicInteger newCounter = new AtomicInteger(count);
               if (this.countMap.putIfAbsent(element, newCounter) == null || this.countMap.replace(element, existingCounter, newCounter)) {
                  return 0;
               }
               continue label40;
            }
         } while(!existingCounter.compareAndSet(oldValue, count));

         if (count == 0) {
            this.countMap.remove(element, existingCounter);
         }

         return oldValue;
      }
   }

   @CanIgnoreReturnValue
   public boolean setCount(Object element, int expectedOldCount, int newCount) {
      Preconditions.checkNotNull(element);
      CollectPreconditions.checkNonnegative(expectedOldCount, "oldCount");
      CollectPreconditions.checkNonnegative(newCount, "newCount");
      AtomicInteger existingCounter = (AtomicInteger)Maps.safeGet(this.countMap, element);
      if (existingCounter == null) {
         if (expectedOldCount != 0) {
            return false;
         } else if (newCount == 0) {
            return true;
         } else {
            return this.countMap.putIfAbsent(element, new AtomicInteger(newCount)) == null;
         }
      } else {
         int oldValue = existingCounter.get();
         if (oldValue == expectedOldCount) {
            if (oldValue == 0) {
               if (newCount == 0) {
                  this.countMap.remove(element, existingCounter);
                  return true;
               }

               AtomicInteger newCounter = new AtomicInteger(newCount);
               return this.countMap.putIfAbsent(element, newCounter) == null || this.countMap.replace(element, existingCounter, newCounter);
            }

            if (existingCounter.compareAndSet(oldValue, newCount)) {
               if (newCount == 0) {
                  this.countMap.remove(element, existingCounter);
               }

               return true;
            }
         }

         return false;
      }
   }

   Set createElementSet() {
      final Set<E> delegate = this.countMap.keySet();
      return new ForwardingSet() {
         protected Set delegate() {
            return delegate;
         }

         public boolean contains(@CheckForNull Object object) {
            return object != null && Collections2.safeContains(delegate, object);
         }

         public boolean containsAll(Collection collection) {
            return this.standardContainsAll(collection);
         }

         public boolean remove(@CheckForNull Object object) {
            return object != null && Collections2.safeRemove(delegate, object);
         }

         public boolean removeAll(Collection c) {
            return this.standardRemoveAll(c);
         }
      };
   }

   Iterator elementIterator() {
      throw new AssertionError("should never be called");
   }

   /** @deprecated */
   @Deprecated
   public Set createEntrySet() {
      return new EntrySet();
   }

   int distinctElements() {
      return this.countMap.size();
   }

   public boolean isEmpty() {
      return this.countMap.isEmpty();
   }

   Iterator entryIterator() {
      final Iterator<Multiset.Entry<E>> readOnlyIterator = new AbstractIterator() {
         private final Iterator mapEntries;

         {
            this.mapEntries = ConcurrentHashMultiset.this.countMap.entrySet().iterator();
         }

         @CheckForNull
         protected Multiset.Entry computeNext() {
            while(this.mapEntries.hasNext()) {
               Map.Entry<E, AtomicInteger> mapEntry = (Map.Entry)this.mapEntries.next();
               int count = ((AtomicInteger)mapEntry.getValue()).get();
               if (count != 0) {
                  return Multisets.immutableEntry(mapEntry.getKey(), count);
               }
            }

            return (Multiset.Entry)this.endOfData();
         }
      };
      return new ForwardingIterator() {
         @CheckForNull
         private Multiset.Entry last;

         protected Iterator delegate() {
            return readOnlyIterator;
         }

         public Multiset.Entry next() {
            this.last = (Multiset.Entry)super.next();
            return this.last;
         }

         public void remove() {
            Preconditions.checkState(this.last != null, "no calls to next() since the last call to remove()");
            ConcurrentHashMultiset.this.setCount(this.last.getElement(), 0);
            this.last = null;
         }
      };
   }

   public Iterator iterator() {
      return Multisets.iteratorImpl(this);
   }

   public void clear() {
      this.countMap.clear();
   }

   private void writeObject(ObjectOutputStream stream) throws IOException {
      stream.defaultWriteObject();
      stream.writeObject(this.countMap);
   }

   @J2ktIncompatible
   private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
      stream.defaultReadObject();
      ConcurrentMap<E, Integer> deserializedCountMap = (ConcurrentMap)stream.readObject();
      ConcurrentHashMultiset.FieldSettersHolder.COUNT_MAP_FIELD_SETTER.set(this, deserializedCountMap);
   }

   private static class FieldSettersHolder {
      static final Serialization.FieldSetter COUNT_MAP_FIELD_SETTER = Serialization.getFieldSetter(ConcurrentHashMultiset.class, "countMap");
   }

   private class EntrySet extends AbstractMultiset.EntrySet {
      private EntrySet() {
      }

      ConcurrentHashMultiset multiset() {
         return ConcurrentHashMultiset.this;
      }

      public Object[] toArray() {
         return this.snapshot().toArray();
      }

      public Object[] toArray(Object[] array) {
         return this.snapshot().toArray(array);
      }

      private List snapshot() {
         List<Multiset.Entry<E>> list = Lists.newArrayListWithExpectedSize(this.size());
         Iterators.addAll(list, this.iterator());
         return list;
      }
   }
}
