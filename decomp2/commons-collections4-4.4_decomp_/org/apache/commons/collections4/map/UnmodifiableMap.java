package org.apache.commons.collections4.map;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import org.apache.commons.collections4.IterableMap;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.Unmodifiable;
import org.apache.commons.collections4.collection.UnmodifiableCollection;
import org.apache.commons.collections4.iterators.EntrySetMapIterator;
import org.apache.commons.collections4.iterators.UnmodifiableMapIterator;
import org.apache.commons.collections4.set.UnmodifiableSet;

public final class UnmodifiableMap extends AbstractMapDecorator implements Unmodifiable, Serializable {
   private static final long serialVersionUID = 2737023427269031941L;

   public static Map unmodifiableMap(Map map) {
      return (Map)(map instanceof Unmodifiable ? map : new UnmodifiableMap(map));
   }

   private UnmodifiableMap(Map map) {
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

   public void clear() {
      throw new UnsupportedOperationException();
   }

   public Object put(Object key, Object value) {
      throw new UnsupportedOperationException();
   }

   public void putAll(Map mapToCopy) {
      throw new UnsupportedOperationException();
   }

   public Object remove(Object key) {
      throw new UnsupportedOperationException();
   }

   public MapIterator mapIterator() {
      if (this.map instanceof IterableMap) {
         MapIterator<K, V> it = ((IterableMap)this.map).mapIterator();
         return UnmodifiableMapIterator.unmodifiableMapIterator(it);
      } else {
         MapIterator<K, V> it = new EntrySetMapIterator(this.map);
         return UnmodifiableMapIterator.unmodifiableMapIterator(it);
      }
   }

   public Set entrySet() {
      Set<Map.Entry<K, V>> set = super.entrySet();
      return UnmodifiableEntrySet.unmodifiableEntrySet(set);
   }

   public Set keySet() {
      Set<K> set = super.keySet();
      return UnmodifiableSet.unmodifiableSet(set);
   }

   public Collection values() {
      Collection<V> coll = super.values();
      return UnmodifiableCollection.unmodifiableCollection(coll);
   }
}
