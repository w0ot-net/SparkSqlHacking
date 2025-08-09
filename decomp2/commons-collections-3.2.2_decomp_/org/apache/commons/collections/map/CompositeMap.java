package org.apache.commons.collections.map;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.collection.CompositeCollection;
import org.apache.commons.collections.set.CompositeSet;

public class CompositeMap implements Map {
   private Map[] composite;
   private MapMutator mutator;

   public CompositeMap() {
      this((Map[])(new Map[0]), (MapMutator)null);
   }

   public CompositeMap(Map one, Map two) {
      this((Map[])(new Map[]{one, two}), (MapMutator)null);
   }

   public CompositeMap(Map one, Map two, MapMutator mutator) {
      this(new Map[]{one, two}, mutator);
   }

   public CompositeMap(Map[] composite) {
      this((Map[])composite, (MapMutator)null);
   }

   public CompositeMap(Map[] composite, MapMutator mutator) {
      this.mutator = mutator;
      this.composite = new Map[0];

      for(int i = composite.length - 1; i >= 0; --i) {
         this.addComposited(composite[i]);
      }

   }

   public void setMutator(MapMutator mutator) {
      this.mutator = mutator;
   }

   public synchronized void addComposited(Map map) throws IllegalArgumentException {
      for(int i = this.composite.length - 1; i >= 0; --i) {
         Collection intersect = CollectionUtils.intersection(this.composite[i].keySet(), map.keySet());
         if (intersect.size() != 0) {
            if (this.mutator == null) {
               throw new IllegalArgumentException("Key collision adding Map to CompositeMap");
            }

            this.mutator.resolveCollision(this, this.composite[i], map, intersect);
         }
      }

      Map[] temp = new Map[this.composite.length + 1];
      System.arraycopy(this.composite, 0, temp, 0, this.composite.length);
      temp[temp.length - 1] = map;
      this.composite = temp;
   }

   public synchronized Map removeComposited(Map map) {
      int size = this.composite.length;

      for(int i = 0; i < size; ++i) {
         if (this.composite[i].equals(map)) {
            Map[] temp = new Map[size - 1];
            System.arraycopy(this.composite, 0, temp, 0, i);
            System.arraycopy(this.composite, i + 1, temp, i, size - i - 1);
            this.composite = temp;
            return map;
         }
      }

      return null;
   }

   public void clear() {
      for(int i = this.composite.length - 1; i >= 0; --i) {
         this.composite[i].clear();
      }

   }

   public boolean containsKey(Object key) {
      for(int i = this.composite.length - 1; i >= 0; --i) {
         if (this.composite[i].containsKey(key)) {
            return true;
         }
      }

      return false;
   }

   public boolean containsValue(Object value) {
      for(int i = this.composite.length - 1; i >= 0; --i) {
         if (this.composite[i].containsValue(value)) {
            return true;
         }
      }

      return false;
   }

   public Set entrySet() {
      CompositeSet entries = new CompositeSet();

      for(int i = this.composite.length - 1; i >= 0; --i) {
         entries.addComposited((Collection)this.composite[i].entrySet());
      }

      return entries;
   }

   public Object get(Object key) {
      for(int i = this.composite.length - 1; i >= 0; --i) {
         if (this.composite[i].containsKey(key)) {
            return this.composite[i].get(key);
         }
      }

      return null;
   }

   public boolean isEmpty() {
      for(int i = this.composite.length - 1; i >= 0; --i) {
         if (!this.composite[i].isEmpty()) {
            return false;
         }
      }

      return true;
   }

   public Set keySet() {
      CompositeSet keys = new CompositeSet();

      for(int i = this.composite.length - 1; i >= 0; --i) {
         keys.addComposited((Collection)this.composite[i].keySet());
      }

      return keys;
   }

   public Object put(Object key, Object value) {
      if (this.mutator == null) {
         throw new UnsupportedOperationException("No mutator specified");
      } else {
         return this.mutator.put(this, this.composite, key, value);
      }
   }

   public void putAll(Map map) {
      if (this.mutator == null) {
         throw new UnsupportedOperationException("No mutator specified");
      } else {
         this.mutator.putAll(this, this.composite, map);
      }
   }

   public Object remove(Object key) {
      for(int i = this.composite.length - 1; i >= 0; --i) {
         if (this.composite[i].containsKey(key)) {
            return this.composite[i].remove(key);
         }
      }

      return null;
   }

   public int size() {
      int size = 0;

      for(int i = this.composite.length - 1; i >= 0; --i) {
         size += this.composite[i].size();
      }

      return size;
   }

   public Collection values() {
      CompositeCollection keys = new CompositeCollection();

      for(int i = this.composite.length - 1; i >= 0; --i) {
         keys.addComposited(this.composite[i].values());
      }

      return keys;
   }

   public boolean equals(Object obj) {
      if (obj instanceof Map) {
         Map map = (Map)obj;
         return this.entrySet().equals(map.entrySet());
      } else {
         return false;
      }
   }

   public int hashCode() {
      int code = 0;

      for(Iterator i = this.entrySet().iterator(); i.hasNext(); code += i.next().hashCode()) {
      }

      return code;
   }

   public interface MapMutator {
      void resolveCollision(CompositeMap var1, Map var2, Map var3, Collection var4);

      Object put(CompositeMap var1, Map[] var2, Object var3, Object var4);

      void putAll(CompositeMap var1, Map[] var2, Map var3);
   }
}
