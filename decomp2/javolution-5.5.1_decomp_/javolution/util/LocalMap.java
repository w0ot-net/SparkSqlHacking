package javolution.util;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import javolution.context.LocalContext;

public final class LocalMap implements Map {
   private final LocalContext.Reference _mapRef = new LocalContext.Reference((new FastMap()).shared());

   public LocalMap setKeyComparator(FastComparator keyComparator) {
      this.localMap().setKeyComparator(keyComparator);
      return this;
   }

   public LocalMap setValueComparator(FastComparator valueComparator) {
      this.localMap().setValueComparator(valueComparator);
      return this;
   }

   public Object putDefault(Object key, Object defaultValue) {
      return ((FastMap)this._mapRef.getDefault()).put(key, defaultValue);
   }

   public Object getDefault(Object key) {
      return ((FastMap)this._mapRef.getDefault()).get(key);
   }

   public int size() {
      return ((FastMap)this._mapRef.get()).size();
   }

   public boolean isEmpty() {
      return ((FastMap)this._mapRef.get()).isEmpty();
   }

   public boolean containsKey(Object key) {
      return ((FastMap)this._mapRef.get()).containsKey(key);
   }

   public boolean containsValue(Object value) {
      return ((FastMap)this._mapRef.get()).containsValue(value);
   }

   public Object get(Object key) {
      return ((FastMap)this._mapRef.get()).get(key);
   }

   public Object put(Object key, Object value) {
      return this.localMap().put(key, value);
   }

   public void putAll(Map map) {
      this.localMap().putAll(map);
   }

   public Object remove(Object key) {
      return this.put(key, (Object)null);
   }

   public void clear() {
      FastMap localMap = this.localMap();
      FastMap.Entry e = localMap.head();
      FastMap.Entry end = localMap.tail();

      while((e = e.getNext()) != end) {
         e.setValue((Object)null);
      }

   }

   public Set keySet() {
      return this.localMap().keySet();
   }

   public Collection values() {
      return this.localMap().values();
   }

   public Set entrySet() {
      return this.localMap().entrySet();
   }

   private FastMap localMap() {
      FastMap localMap = (FastMap)this._mapRef.getLocal();
      return localMap != null ? localMap : this.newLocalMap();
   }

   private FastMap newLocalMap() {
      FastMap parentMap = (FastMap)this._mapRef.get();
      FastMap localMap = FastMap.newInstance();
      localMap.shared();
      localMap.setKeyComparator(parentMap.getKeyComparator());
      localMap.setValueComparator(parentMap.getValueComparator());
      localMap.putAll(parentMap);
      this._mapRef.set(localMap);
      return localMap;
   }
}
