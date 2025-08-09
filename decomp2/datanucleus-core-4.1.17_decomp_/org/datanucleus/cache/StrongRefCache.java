package org.datanucleus.cache;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.datanucleus.state.ObjectProvider;

public class StrongRefCache implements Level1Cache {
   private Map cache = new HashMap();

   public ObjectProvider put(Object key, ObjectProvider value) {
      return (ObjectProvider)this.cache.put(key, value);
   }

   public ObjectProvider get(Object key) {
      return (ObjectProvider)this.cache.get(key);
   }

   public boolean containsKey(Object key) {
      return this.cache.containsKey(key);
   }

   public ObjectProvider remove(Object key) {
      return (ObjectProvider)this.cache.remove(key);
   }

   public void clear() {
      this.cache.clear();
   }

   public boolean containsValue(Object value) {
      return this.cache.containsValue(value);
   }

   public Set entrySet() {
      return this.cache.entrySet();
   }

   public boolean isEmpty() {
      return this.cache.isEmpty();
   }

   public Set keySet() {
      return this.cache.keySet();
   }

   public void putAll(Map t) {
      this.cache.putAll(t);
   }

   public int size() {
      return this.cache.size();
   }

   public Collection values() {
      return this.cache.values();
   }
}
