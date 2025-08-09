package org.datanucleus.util;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class MultiMap extends HashMap {
   private static final long serialVersionUID = -3473757184670196986L;
   private transient Collection values = null;

   public MultiMap() {
   }

   public MultiMap(int initialCapacity) {
      super(initialCapacity);
   }

   public MultiMap(int initialCapacity, float loadFactor) {
      super(initialCapacity, loadFactor);
   }

   public MultiMap(MultiMap map) {
      if (map != null) {
         for(Map.Entry entry : map.entrySet()) {
            super.put(entry.getKey(), new ArrayList((List)entry.getValue()));
         }
      }

   }

   public boolean containsValue(Object value) {
      for(Map.Entry keyValuePair : super.entrySet()) {
         Collection coll = (Collection)keyValuePair.getValue();
         if (coll.contains(value)) {
            return true;
         }
      }

      return false;
   }

   public Object put(Object key, Object value) {
      Collection c = (Collection)super.get(key);
      if (c == null) {
         c = this.createCollection((Collection)null);
         super.put(key, c);
      }

      boolean results = c.add(value);
      return results ? value : null;
   }

   public Object removeKeyValue(Object key, Object item) {
      Collection valuesForKey = (Collection)super.get(key);
      if (valuesForKey == null) {
         return null;
      } else {
         valuesForKey.remove(item);
         if (valuesForKey.isEmpty()) {
            this.remove(key);
         }

         return item;
      }
   }

   public void clear() {
      for(Map.Entry keyValuePair : super.entrySet()) {
         Collection coll = (Collection)keyValuePair.getValue();
         coll.clear();
      }

      super.clear();
   }

   public Collection values() {
      Collection vs = this.values;
      return vs != null ? vs : (this.values = new ValueElement());
   }

   public Object clone() {
      MultiMap obj = (MultiMap)super.clone();

      for(Map.Entry entry : this.entrySet()) {
         Collection coll = (Collection)entry.getValue();
         Collection newColl = this.createCollection(coll);
         entry.setValue(newColl);
      }

      return obj;
   }

   protected Collection createCollection(Collection c) {
      return c == null ? new ArrayList() : new ArrayList(c);
   }

   private class ValueElement extends AbstractCollection {
      private ValueElement() {
      }

      public Iterator iterator() {
         return MultiMap.this.new ValueElementIter();
      }

      public int size() {
         int i = 0;

         for(Iterator iter = this.iterator(); iter.hasNext(); ++i) {
            iter.next();
         }

         return i;
      }

      public void clear() {
         MultiMap.this.clear();
      }
   }

   private class ValueElementIter implements Iterator {
      private Iterator backing;
      private Iterator temp;

      private ValueElementIter() {
         this.backing = MultiMap.super.values().iterator();
      }

      private boolean searchNextIterator() {
         while(this.temp == null || !this.temp.hasNext()) {
            if (!this.backing.hasNext()) {
               return false;
            }

            this.temp = ((Collection)this.backing.next()).iterator();
         }

         return true;
      }

      public boolean hasNext() {
         return this.searchNextIterator();
      }

      public Object next() {
         if (!this.searchNextIterator()) {
            throw new NoSuchElementException();
         } else {
            return this.temp.next();
         }
      }

      public void remove() {
         if (this.temp == null) {
            throw new IllegalStateException();
         } else {
            this.temp.remove();
         }
      }
   }
}
