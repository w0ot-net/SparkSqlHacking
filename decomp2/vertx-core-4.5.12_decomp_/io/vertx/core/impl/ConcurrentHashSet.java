package io.vertx.core.impl;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashSet implements Set {
   private final Map map;
   private static final Object OBJ = new Object();

   public ConcurrentHashSet(int size) {
      this.map = new ConcurrentHashMap(size);
   }

   public ConcurrentHashSet() {
      this.map = new ConcurrentHashMap();
   }

   public int size() {
      return this.map.size();
   }

   public boolean isEmpty() {
      return this.map.isEmpty();
   }

   public boolean contains(Object o) {
      return this.map.containsKey(o);
   }

   public Iterator iterator() {
      return this.map.keySet().iterator();
   }

   public Object[] toArray() {
      return this.map.keySet().toArray();
   }

   public Object[] toArray(Object[] a) {
      return this.map.keySet().toArray(a);
   }

   public boolean add(Object e) {
      return this.map.put(e, OBJ) == null;
   }

   public boolean remove(Object o) {
      return this.map.remove(o) != null;
   }

   public boolean containsAll(Collection c) {
      return this.map.keySet().containsAll(c);
   }

   public boolean addAll(Collection c) {
      boolean changed = false;

      for(Object e : c) {
         if (this.map.put(e, OBJ) == null) {
            changed = true;
         }
      }

      return changed;
   }

   public boolean retainAll(Collection c) {
      throw new UnsupportedOperationException();
   }

   public boolean removeAll(Collection c) {
      throw new UnsupportedOperationException();
   }

   public void clear() {
      this.map.clear();
   }
}
