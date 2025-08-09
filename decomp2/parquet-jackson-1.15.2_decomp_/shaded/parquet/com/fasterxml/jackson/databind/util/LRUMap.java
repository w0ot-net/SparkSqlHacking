package shaded.parquet.com.fasterxml.jackson.databind.util;

import java.io.Serializable;
import java.util.Map;
import java.util.function.BiConsumer;
import shaded.parquet.com.fasterxml.jackson.databind.util.internal.PrivateMaxEntriesMap;

public class LRUMap implements LookupCache, Serializable {
   private static final long serialVersionUID = 2L;
   protected final int _initialEntries;
   protected final int _maxEntries;
   protected final transient PrivateMaxEntriesMap _map;

   public LRUMap(int initialEntries, int maxEntries) {
      this._initialEntries = initialEntries;
      this._maxEntries = maxEntries;
      this._map = (new PrivateMaxEntriesMap.Builder()).initialCapacity(initialEntries).maximumCapacity((long)maxEntries).concurrencyLevel(4).build();
   }

   public LookupCache emptyCopy() {
      return new LRUMap(this._initialEntries, this._maxEntries);
   }

   public Object put(Object key, Object value) {
      return this._map.put(key, value);
   }

   public Object putIfAbsent(Object key, Object value) {
      return this._map.putIfAbsent(key, value);
   }

   public Object get(Object key) {
      return this._map.get(key);
   }

   public void clear() {
      this._map.clear();
   }

   public int size() {
      return this._map.size();
   }

   public void contents(BiConsumer consumer) {
      for(Map.Entry entry : this._map.entrySet()) {
         consumer.accept(entry.getKey(), entry.getValue());
      }

   }

   protected Object readResolve() {
      return new LRUMap(this._initialEntries, this._maxEntries);
   }
}
