package org.apache.commons.collections4.map;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import org.apache.commons.collections4.BoundedMap;
import org.apache.commons.collections4.collection.UnmodifiableCollection;
import org.apache.commons.collections4.set.UnmodifiableSet;

public class FixedSizeMap extends AbstractMapDecorator implements BoundedMap, Serializable {
   private static final long serialVersionUID = 7450927208116179316L;

   public static FixedSizeMap fixedSizeMap(Map map) {
      return new FixedSizeMap(map);
   }

   protected FixedSizeMap(Map map) {
      super(map);
   }

   private void writeObject(ObjectOutputStream out) throws IOException {
      out.defaultWriteObject();
      out.writeObject(this.map);
   }

   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
      in.defaultReadObject();
      this.map = (Map)in.readObject();
   }

   public Object put(Object key, Object value) {
      if (!this.map.containsKey(key)) {
         throw new IllegalArgumentException("Cannot put new key/value pair - Map is fixed size");
      } else {
         return this.map.put(key, value);
      }
   }

   public void putAll(Map mapToCopy) {
      for(Object key : mapToCopy.keySet()) {
         if (!this.containsKey(key)) {
            throw new IllegalArgumentException("Cannot put new key/value pair - Map is fixed size");
         }
      }

      this.map.putAll(mapToCopy);
   }

   public void clear() {
      throw new UnsupportedOperationException("Map is fixed size");
   }

   public Object remove(Object key) {
      throw new UnsupportedOperationException("Map is fixed size");
   }

   public Set entrySet() {
      Set<Map.Entry<K, V>> set = this.map.entrySet();
      return UnmodifiableSet.unmodifiableSet(set);
   }

   public Set keySet() {
      Set<K> set = this.map.keySet();
      return UnmodifiableSet.unmodifiableSet(set);
   }

   public Collection values() {
      Collection<V> coll = this.map.values();
      return UnmodifiableCollection.unmodifiableCollection(coll);
   }

   public boolean isFull() {
      return true;
   }

   public int maxSize() {
      return this.size();
   }
}
