package org.datanucleus.cache;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.util.WeakValueMap;

public class WeakRefCache implements Level1Cache {
   private Map weakCache = new WeakValueMap();

   public ObjectProvider put(Object key, ObjectProvider value) {
      return (ObjectProvider)this.weakCache.put(key, value);
   }

   public ObjectProvider get(Object key) {
      return (ObjectProvider)this.weakCache.get(key);
   }

   public boolean containsKey(Object key) {
      return this.weakCache.containsKey(key);
   }

   public ObjectProvider remove(Object key) {
      return (ObjectProvider)this.weakCache.remove(key);
   }

   public void clear() {
      this.weakCache.clear();
   }

   public boolean containsValue(Object value) {
      return this.weakCache.containsValue(value);
   }

   public Set entrySet() {
      return this.weakCache.entrySet();
   }

   public boolean isEmpty() {
      return this.weakCache.isEmpty();
   }

   public Set keySet() {
      return this.weakCache.keySet();
   }

   public void putAll(Map t) {
      this.weakCache.putAll(t);
   }

   public int size() {
      return this.weakCache.size();
   }

   public Collection values() {
      return this.weakCache.values();
   }
}
