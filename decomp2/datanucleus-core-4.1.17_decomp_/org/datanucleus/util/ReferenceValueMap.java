package org.datanucleus.util;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public abstract class ReferenceValueMap implements Map, Cloneable {
   private LinkedHashMap map;
   private ReferenceQueue reaped = new ReferenceQueue();

   public ReferenceValueMap() {
      this.map = new LinkedHashMap();
   }

   public ReferenceValueMap(int initial_capacity) {
      this.map = new LinkedHashMap(initial_capacity);
   }

   public ReferenceValueMap(int initial_capacity, float load_factor) {
      this.map = new LinkedHashMap(initial_capacity, load_factor);
   }

   public ReferenceValueMap(Map m) {
      this.map = new LinkedHashMap();
      this.putAll(m);
   }

   public Object clone() {
      this.reap();
      ReferenceValueMap rvm = null;

      try {
         rvm = (ReferenceValueMap)super.clone();
      } catch (CloneNotSupportedException var3) {
      }

      rvm.map = (LinkedHashMap)this.map.clone();
      rvm.map.clear();
      rvm.reaped = new ReferenceQueue();
      rvm.putAll(this.entrySet());
      return rvm;
   }

   protected abstract ValueReference newValueReference(Object var1, Object var2, ReferenceQueue var3);

   public Object put(Object key, Object value) {
      this.reap();
      return this.unwrapReference(this.map.put(key, this.newValueReference(key, value, this.reaped)));
   }

   public void putAll(Map m) {
      this.putAll(m.entrySet());
   }

   private void putAll(Set entrySet) {
      for(Map.Entry entry : entrySet) {
         this.put(entry.getKey(), entry.getValue());
      }

   }

   public Object get(Object key) {
      this.reap();
      return this.unwrapReference(this.map.get(key));
   }

   public void clear() {
      this.reap();
      this.map.clear();
   }

   public int size() {
      this.reap();
      return this.map.size();
   }

   public boolean containsKey(Object obj) {
      this.reap();
      return this.map.containsKey(obj);
   }

   public boolean containsValue(Object obj) {
      this.reap();
      if (obj != null) {
         for(Reference ref : this.map.values()) {
            if (obj.equals(ref.get())) {
               return true;
            }
         }
      }

      return false;
   }

   public boolean isEmpty() {
      this.reap();
      return this.map.isEmpty();
   }

   public Set keySet() {
      this.reap();
      return this.map.keySet();
   }

   public Collection values() {
      this.reap();
      Collection c = this.map.values();
      Iterator i = c.iterator();
      ArrayList l = new ArrayList(c.size());

      while(i.hasNext()) {
         Reference ref = (Reference)i.next();
         Object obj = ref.get();
         if (obj != null) {
            l.add(obj);
         }
      }

      return Collections.unmodifiableList(l);
   }

   public Set entrySet() {
      this.reap();
      Set s = this.map.entrySet();
      Iterator i = s.iterator();
      HashMap m = new HashMap(s.size());

      while(i.hasNext()) {
         Map.Entry entry = (Map.Entry)i.next();
         Reference ref = (Reference)entry.getValue();
         Object obj = ref.get();
         if (obj != null) {
            m.put(entry.getKey(), obj);
         }
      }

      return Collections.unmodifiableSet(m.entrySet());
   }

   public Object remove(Object key) {
      this.reap();
      return this.unwrapReference(this.map.remove(key));
   }

   public int hashCode() {
      this.reap();
      return this.map.hashCode();
   }

   public boolean equals(Object o) {
      this.reap();
      return this.map.equals(o);
   }

   public void reap() {
      ValueReference ref;
      while((ref = (ValueReference)this.reaped.poll()) != null) {
         this.map.remove(ref.getKey());
      }

   }

   private Object unwrapReference(Object obj) {
      if (obj == null) {
         return null;
      } else {
         Reference ref = (Reference)obj;
         return ref.get();
      }
   }

   public interface ValueReference {
      Object getKey();
   }
}
