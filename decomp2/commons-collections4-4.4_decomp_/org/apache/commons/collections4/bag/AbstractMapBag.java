package org.apache.commons.collections4.bag;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.set.UnmodifiableSet;

public abstract class AbstractMapBag implements Bag {
   private transient Map map;
   private int size;
   private transient int modCount;
   private transient Set uniqueSet;

   protected AbstractMapBag() {
   }

   protected AbstractMapBag(Map map) {
      this.map = map;
   }

   protected Map getMap() {
      return this.map;
   }

   public int size() {
      return this.size;
   }

   public boolean isEmpty() {
      return this.map.isEmpty();
   }

   public int getCount(Object object) {
      MutableInteger count = (MutableInteger)this.map.get(object);
      return count != null ? count.value : 0;
   }

   public boolean contains(Object object) {
      return this.map.containsKey(object);
   }

   public boolean containsAll(Collection coll) {
      return coll instanceof Bag ? this.containsAll((Bag)coll) : this.containsAll((Bag)(new HashBag(coll)));
   }

   boolean containsAll(Bag other) {
      for(Object current : other.uniqueSet()) {
         if (this.getCount(current) < other.getCount(current)) {
            return false;
         }
      }

      return true;
   }

   public Iterator iterator() {
      return new BagIterator(this);
   }

   public boolean add(Object object) {
      return this.add(object, 1);
   }

   public boolean add(Object object, int nCopies) {
      ++this.modCount;
      if (nCopies > 0) {
         MutableInteger mut = (MutableInteger)this.map.get(object);
         this.size += nCopies;
         if (mut == null) {
            this.map.put(object, new MutableInteger(nCopies));
            return true;
         } else {
            mut.value += nCopies;
            return false;
         }
      } else {
         return false;
      }
   }

   public boolean addAll(Collection coll) {
      boolean changed = false;

      boolean added;
      for(Iterator<? extends E> i = coll.iterator(); i.hasNext(); changed = changed || added) {
         added = this.add(i.next());
      }

      return changed;
   }

   public void clear() {
      ++this.modCount;
      this.map.clear();
      this.size = 0;
   }

   public boolean remove(Object object) {
      MutableInteger mut = (MutableInteger)this.map.get(object);
      if (mut == null) {
         return false;
      } else {
         ++this.modCount;
         this.map.remove(object);
         this.size -= mut.value;
         return true;
      }
   }

   public boolean remove(Object object, int nCopies) {
      MutableInteger mut = (MutableInteger)this.map.get(object);
      if (mut == null) {
         return false;
      } else if (nCopies <= 0) {
         return false;
      } else {
         ++this.modCount;
         if (nCopies < mut.value) {
            mut.value -= nCopies;
            this.size -= nCopies;
         } else {
            this.map.remove(object);
            this.size -= mut.value;
         }

         return true;
      }
   }

   public boolean removeAll(Collection coll) {
      boolean result = false;
      boolean changed;
      if (coll != null) {
         for(Iterator<?> i = coll.iterator(); i.hasNext(); result = result || changed) {
            changed = this.remove(i.next(), 1);
         }
      }

      return result;
   }

   public boolean retainAll(Collection coll) {
      return coll instanceof Bag ? this.retainAll((Bag)coll) : this.retainAll((Bag)(new HashBag(coll)));
   }

   boolean retainAll(Bag other) {
      boolean result = false;
      Bag<E> excess = new HashBag();

      for(Object current : this.uniqueSet()) {
         int myCount = this.getCount(current);
         int otherCount = other.getCount(current);
         if (1 <= otherCount && otherCount <= myCount) {
            excess.add(current, myCount - otherCount);
         } else {
            excess.add(current, myCount);
         }
      }

      if (!excess.isEmpty()) {
         result = this.removeAll(excess);
      }

      return result;
   }

   public Object[] toArray() {
      Object[] result = new Object[this.size()];
      int i = 0;

      for(Object current : this.map.keySet()) {
         for(int index = this.getCount(current); index > 0; --index) {
            result[i++] = current;
         }
      }

      return result;
   }

   public Object[] toArray(Object[] array) {
      int size = this.size();
      if (array.length < size) {
         T[] unchecked = (T[])((Object[])((Object[])Array.newInstance(array.getClass().getComponentType(), size)));
         array = unchecked;
      }

      int i = 0;

      for(Object current : this.map.keySet()) {
         for(int index = this.getCount(current); index > 0; --index) {
            array[i++] = current;
         }
      }

      while(i < array.length) {
         array[i++] = null;
      }

      return array;
   }

   public Set uniqueSet() {
      if (this.uniqueSet == null) {
         this.uniqueSet = UnmodifiableSet.unmodifiableSet(this.map.keySet());
      }

      return this.uniqueSet;
   }

   protected void doWriteObject(ObjectOutputStream out) throws IOException {
      out.writeInt(this.map.size());

      for(Map.Entry entry : this.map.entrySet()) {
         out.writeObject(entry.getKey());
         out.writeInt(((MutableInteger)entry.getValue()).value);
      }

   }

   protected void doReadObject(Map map, ObjectInputStream in) throws IOException, ClassNotFoundException {
      this.map = map;
      int entrySize = in.readInt();

      for(int i = 0; i < entrySize; ++i) {
         E obj = (E)in.readObject();
         int count = in.readInt();
         map.put(obj, new MutableInteger(count));
         this.size += count;
      }

   }

   public boolean equals(Object object) {
      if (object == this) {
         return true;
      } else if (!(object instanceof Bag)) {
         return false;
      } else {
         Bag<?> other = (Bag)object;
         if (other.size() != this.size()) {
            return false;
         } else {
            for(Object element : this.map.keySet()) {
               if (other.getCount(element) != this.getCount(element)) {
                  return false;
               }
            }

            return true;
         }
      }
   }

   public int hashCode() {
      int total = 0;

      for(Map.Entry entry : this.map.entrySet()) {
         E element = (E)entry.getKey();
         MutableInteger count = (MutableInteger)entry.getValue();
         total += (element == null ? 0 : element.hashCode()) ^ count.value;
      }

      return total;
   }

   public String toString() {
      if (this.size() == 0) {
         return "[]";
      } else {
         StringBuilder buf = new StringBuilder();
         buf.append('[');
         Iterator<E> it = this.uniqueSet().iterator();

         while(it.hasNext()) {
            Object current = it.next();
            int count = this.getCount(current);
            buf.append(count);
            buf.append(':');
            buf.append(current);
            if (it.hasNext()) {
               buf.append(',');
            }
         }

         buf.append(']');
         return buf.toString();
      }
   }

   static class BagIterator implements Iterator {
      private final AbstractMapBag parent;
      private final Iterator entryIterator;
      private Map.Entry current;
      private int itemCount;
      private final int mods;
      private boolean canRemove;

      public BagIterator(AbstractMapBag parent) {
         this.parent = parent;
         this.entryIterator = parent.map.entrySet().iterator();
         this.current = null;
         this.mods = parent.modCount;
         this.canRemove = false;
      }

      public boolean hasNext() {
         return this.itemCount > 0 || this.entryIterator.hasNext();
      }

      public Object next() {
         if (this.parent.modCount != this.mods) {
            throw new ConcurrentModificationException();
         } else {
            if (this.itemCount == 0) {
               this.current = (Map.Entry)this.entryIterator.next();
               this.itemCount = ((MutableInteger)this.current.getValue()).value;
            }

            this.canRemove = true;
            --this.itemCount;
            return this.current.getKey();
         }
      }

      public void remove() {
         if (this.parent.modCount != this.mods) {
            throw new ConcurrentModificationException();
         } else if (!this.canRemove) {
            throw new IllegalStateException();
         } else {
            MutableInteger mut = (MutableInteger)this.current.getValue();
            if (mut.value > 1) {
               --mut.value;
            } else {
               this.entryIterator.remove();
            }

            this.parent.size--;
            this.canRemove = false;
         }
      }
   }

   protected static class MutableInteger {
      protected int value;

      MutableInteger(int value) {
         this.value = value;
      }

      public boolean equals(Object obj) {
         if (!(obj instanceof MutableInteger)) {
            return false;
         } else {
            return ((MutableInteger)obj).value == this.value;
         }
      }

      public int hashCode() {
         return this.value;
      }
   }
}
