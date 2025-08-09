package org.apache.commons.collections4.map;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import org.apache.commons.collections4.Unmodifiable;
import org.apache.commons.collections4.iterators.AbstractIteratorDecorator;
import org.apache.commons.collections4.keyvalue.AbstractMapEntryDecorator;
import org.apache.commons.collections4.set.AbstractSetDecorator;

public final class UnmodifiableEntrySet extends AbstractSetDecorator implements Unmodifiable {
   private static final long serialVersionUID = 1678353579659253473L;

   public static Set unmodifiableEntrySet(Set set) {
      return (Set)(set instanceof Unmodifiable ? set : new UnmodifiableEntrySet(set));
   }

   private UnmodifiableEntrySet(Set set) {
      super(set);
   }

   public boolean add(Map.Entry object) {
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

   public boolean removeIf(Predicate filter) {
      throw new UnsupportedOperationException();
   }

   public boolean removeAll(Collection coll) {
      throw new UnsupportedOperationException();
   }

   public boolean retainAll(Collection coll) {
      throw new UnsupportedOperationException();
   }

   public Iterator iterator() {
      return new UnmodifiableEntrySetIterator(this.decorated().iterator());
   }

   public Object[] toArray() {
      Object[] array = this.decorated().toArray();

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

      result = this.decorated().toArray(result);

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

   private class UnmodifiableEntrySetIterator extends AbstractIteratorDecorator {
      protected UnmodifiableEntrySetIterator(Iterator iterator) {
         super(iterator);
      }

      public Map.Entry next() {
         return UnmodifiableEntrySet.this.new UnmodifiableEntry((Map.Entry)this.getIterator().next());
      }

      public void remove() {
         throw new UnsupportedOperationException();
      }
   }

   private class UnmodifiableEntry extends AbstractMapEntryDecorator {
      protected UnmodifiableEntry(Map.Entry entry) {
         super(entry);
      }

      public Object setValue(Object obj) {
         throw new UnsupportedOperationException();
      }
   }
}
