package org.datanucleus.cache;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.util.SoftValueMap;

public class SoftRefCache implements Level1Cache {
   private Map softCache = new SoftValueMap();

   public ObjectProvider put(Object key, ObjectProvider value) {
      return (ObjectProvider)this.softCache.put(key, value);
   }

   public ObjectProvider get(Object key) {
      return (ObjectProvider)this.softCache.get(key);
   }

   public boolean containsKey(Object key) {
      return this.softCache.containsKey(key);
   }

   public ObjectProvider remove(Object key) {
      return (ObjectProvider)this.softCache.remove(key);
   }

   public void clear() {
      if (!this.isEmpty()) {
         this.softCache.clear();
      }
   }

   public boolean containsValue(Object value) {
      return this.softCache.containsValue(value);
   }

   public Set entrySet() {
      return this.softCache.entrySet();
   }

   public boolean isEmpty() {
      return this.softCache.isEmpty();
   }

   public Set keySet() {
      return this.softCache.keySet();
   }

   public void putAll(Map t) {
      this.softCache.putAll(t);
   }

   public int size() {
      return this.softCache.size();
   }

   public Collection values() {
      return this.softCache.values();
   }
}
