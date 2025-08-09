package org.apache.commons.collections.map;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.apache.commons.collections.Unmodifiable;
import org.apache.commons.collections.iterators.AbstractIteratorDecorator;
import org.apache.commons.collections.keyvalue.AbstractMapEntryDecorator;
import org.apache.commons.collections.set.AbstractSetDecorator;

public final class UnmodifiableEntrySet extends AbstractSetDecorator implements Unmodifiable {
   public static Set decorate(Set set) {
      return (Set)(set instanceof Unmodifiable ? set : new UnmodifiableEntrySet(set));
   }

   private UnmodifiableEntrySet(Set set) {
      super(set);
   }

   public boolean add(Object object) {
      throw new UnsupportedOperationException();
   }

   public boolean addAll(Collection coll) {
      throw new UnsupportedOperationException();
   }

   public void clear() {
      throw new UnsupportedOperationException();
   }

   public boolean remove(Object object) {
      throw new UnsupportedOperationException();
   }

   public boolean removeAll(Collection coll) {
      throw new UnsupportedOperationException();
   }

   public boolean retainAll(Collection coll) {
      throw new UnsupportedOperationException();
   }

   public Iterator iterator() {
      return new UnmodifiableEntrySetIterator(this.collection.iterator());
   }

   public Object[] toArray() {
      Object[] array = this.collection.toArray();

      for(int i = 0; i < array.length; ++i) {
         array[i] = new UnmodifiableEntry((Map.Entry)array[i]);
      }

      return array;
   }

   public Object[] toArray(Object[] array) {
      Object[] result = array;
      if (array.length > 0) {
         result = Array.newInstance(array.getClass().getComponentType(), 0);
      }

      result = this.collection.toArray(result);

      for(int i = 0; i < result.length; ++i) {
         result[i] = new UnmodifiableEntry((Map.Entry)result[i]);
      }

      if (result.length > array.length) {
         return result;
      } else {
         System.arraycopy(result, 0, array, 0, result.length);
         if (array.length > result.length) {
            array[result.length] = null;
         }

         return array;
      }
   }

   static final class UnmodifiableEntrySetIterator extends AbstractIteratorDecorator {
      protected UnmodifiableEntrySetIterator(Iterator iterator) {
         super(iterator);
      }

      public Object next() {
         Map.Entry entry = (Map.Entry)this.iterator.next();
         return new UnmodifiableEntry(entry);
      }

      public void remove() {
         throw new UnsupportedOperationException();
      }
   }

   static final class UnmodifiableEntry extends AbstractMapEntryDecorator {
      protected UnmodifiableEntry(Map.Entry entry) {
         super(entry);
      }

      public Object setValue(Object obj) {
         throw new UnsupportedOperationException();
      }
   }
}
