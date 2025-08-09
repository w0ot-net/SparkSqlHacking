package org.apache.commons.collections.map;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import org.apache.commons.collections.IterableMap;
import org.apache.commons.collections.MapIterator;
import org.apache.commons.collections.keyvalue.MultiKey;

public class MultiKeyMap implements IterableMap, Serializable {
   private static final long serialVersionUID = -1788199231038721040L;
   protected final AbstractHashedMap map;

   public static MultiKeyMap decorate(AbstractHashedMap map) {
      if (map == null) {
         throw new IllegalArgumentException("Map must not be null");
      } else if (map.size() > 0) {
         throw new IllegalArgumentException("Map must be empty");
      } else {
         return new MultiKeyMap(map);
      }
   }

   public MultiKeyMap() {
      this.map = new HashedMap();
   }

   protected MultiKeyMap(AbstractHashedMap map) {
      this.map = map;
   }

   public Object get(Object key1, Object key2) {
      int hashCode = this.hash(key1, key2);

      for(AbstractHashedMap.HashEntry entry = this.map.data[this.map.hashIndex(hashCode, this.map.data.length)]; entry != null; entry = entry.next) {
         if (entry.hashCode == hashCode && this.isEqualKey(entry, key1, key2)) {
            return entry.getValue();
         }
      }

      return null;
   }

   public boolean containsKey(Object key1, Object key2) {
      int hashCode = this.hash(key1, key2);

      for(AbstractHashedMap.HashEntry entry = this.map.data[this.map.hashIndex(hashCode, this.map.data.length)]; entry != null; entry = entry.next) {
         if (entry.hashCode == hashCode && this.isEqualKey(entry, key1, key2)) {
            return true;
         }
      }

      return false;
   }

   public Object put(Object key1, Object key2, Object value) {
      int hashCode = this.hash(key1, key2);
      int index = this.map.hashIndex(hashCode, this.map.data.length);

      for(AbstractHashedMap.HashEntry entry = this.map.data[index]; entry != null; entry = entry.next) {
         if (entry.hashCode == hashCode && this.isEqualKey(entry, key1, key2)) {
            Object oldValue = entry.getValue();
            this.map.updateEntry(entry, value);
            return oldValue;
         }
      }

      this.map.addMapping(index, hashCode, new MultiKey(key1, key2), value);
      return null;
   }

   public Object remove(Object key1, Object key2) {
      int hashCode = this.hash(key1, key2);
      int index = this.map.hashIndex(hashCode, this.map.data.length);
      AbstractHashedMap.HashEntry entry = this.map.data[index];

      for(AbstractHashedMap.HashEntry previous = null; entry != null; entry = entry.next) {
         if (entry.hashCode == hashCode && this.isEqualKey(entry, key1, key2)) {
            Object oldValue = entry.getValue();
            this.map.removeMapping(entry, index, previous);
            return oldValue;
         }

         previous = entry;
      }

      return null;
   }

   protected int hash(Object key1, Object key2) {
      int h = 0;
      if (key1 != null) {
         h ^= key1.hashCode();
      }

      if (key2 != null) {
         h ^= key2.hashCode();
      }

      h += ~(h << 9);
      h ^= h >>> 14;
      h += h << 4;
      h ^= h >>> 10;
      return h;
   }

   protected boolean isEqualKey(AbstractHashedMap.HashEntry entry, Object key1, Object key2) {
      boolean var10000;
      label38: {
         MultiKey multi = (MultiKey)entry.getKey();
         if (multi.size() == 2) {
            label32: {
               if (key1 == null) {
                  if (multi.getKey(0) != null) {
                     break label32;
                  }
               } else if (!key1.equals(multi.getKey(0))) {
                  break label32;
               }

               if (key2 == null) {
                  if (multi.getKey(1) == null) {
                     break label38;
                  }
               } else if (key2.equals(multi.getKey(1))) {
                  break label38;
               }
            }
         }

         var10000 = false;
         return var10000;
      }

      var10000 = true;
      return var10000;
   }

   public Object get(Object key1, Object key2, Object key3) {
      int hashCode = this.hash(key1, key2, key3);

      for(AbstractHashedMap.HashEntry entry = this.map.data[this.map.hashIndex(hashCode, this.map.data.length)]; entry != null; entry = entry.next) {
         if (entry.hashCode == hashCode && this.isEqualKey(entry, key1, key2, key3)) {
            return entry.getValue();
         }
      }

      return null;
   }

   public boolean containsKey(Object key1, Object key2, Object key3) {
      int hashCode = this.hash(key1, key2, key3);

      for(AbstractHashedMap.HashEntry entry = this.map.data[this.map.hashIndex(hashCode, this.map.data.length)]; entry != null; entry = entry.next) {
         if (entry.hashCode == hashCode && this.isEqualKey(entry, key1, key2, key3)) {
            return true;
         }
      }

      return false;
   }

   public Object put(Object key1, Object key2, Object key3, Object value) {
      int hashCode = this.hash(key1, key2, key3);
      int index = this.map.hashIndex(hashCode, this.map.data.length);

      for(AbstractHashedMap.HashEntry entry = this.map.data[index]; entry != null; entry = entry.next) {
         if (entry.hashCode == hashCode && this.isEqualKey(entry, key1, key2, key3)) {
            Object oldValue = entry.getValue();
            this.map.updateEntry(entry, value);
            return oldValue;
         }
      }

      this.map.addMapping(index, hashCode, new MultiKey(key1, key2, key3), value);
      return null;
   }

   public Object remove(Object key1, Object key2, Object key3) {
      int hashCode = this.hash(key1, key2, key3);
      int index = this.map.hashIndex(hashCode, this.map.data.length);
      AbstractHashedMap.HashEntry entry = this.map.data[index];

      for(AbstractHashedMap.HashEntry previous = null; entry != null; entry = entry.next) {
         if (entry.hashCode == hashCode && this.isEqualKey(entry, key1, key2, key3)) {
            Object oldValue = entry.getValue();
            this.map.removeMapping(entry, index, previous);
            return oldValue;
         }

         previous = entry;
      }

      return null;
   }

   protected int hash(Object key1, Object key2, Object key3) {
      int h = 0;
      if (key1 != null) {
         h ^= key1.hashCode();
      }

      if (key2 != null) {
         h ^= key2.hashCode();
      }

      if (key3 != null) {
         h ^= key3.hashCode();
      }

      h += ~(h << 9);
      h ^= h >>> 14;
      h += h << 4;
      h ^= h >>> 10;
      return h;
   }

   protected boolean isEqualKey(AbstractHashedMap.HashEntry entry, Object key1, Object key2, Object key3) {
      boolean var10000;
      label46: {
         MultiKey multi = (MultiKey)entry.getKey();
         if (multi.size() == 3) {
            label40: {
               if (key1 == null) {
                  if (multi.getKey(0) != null) {
                     break label40;
                  }
               } else if (!key1.equals(multi.getKey(0))) {
                  break label40;
               }

               if (key2 == null) {
                  if (multi.getKey(1) != null) {
                     break label40;
                  }
               } else if (!key2.equals(multi.getKey(1))) {
                  break label40;
               }

               if (key3 == null) {
                  if (multi.getKey(2) == null) {
                     break label46;
                  }
               } else if (key3.equals(multi.getKey(2))) {
                  break label46;
               }
            }
         }

         var10000 = false;
         return var10000;
      }

      var10000 = true;
      return var10000;
   }

   public Object get(Object key1, Object key2, Object key3, Object key4) {
      int hashCode = this.hash(key1, key2, key3, key4);

      for(AbstractHashedMap.HashEntry entry = this.map.data[this.map.hashIndex(hashCode, this.map.data.length)]; entry != null; entry = entry.next) {
         if (entry.hashCode == hashCode && this.isEqualKey(entry, key1, key2, key3, key4)) {
            return entry.getValue();
         }
      }

      return null;
   }

   public boolean containsKey(Object key1, Object key2, Object key3, Object key4) {
      int hashCode = this.hash(key1, key2, key3, key4);

      for(AbstractHashedMap.HashEntry entry = this.map.data[this.map.hashIndex(hashCode, this.map.data.length)]; entry != null; entry = entry.next) {
         if (entry.hashCode == hashCode && this.isEqualKey(entry, key1, key2, key3, key4)) {
            return true;
         }
      }

      return false;
   }

   public Object put(Object key1, Object key2, Object key3, Object key4, Object value) {
      int hashCode = this.hash(key1, key2, key3, key4);
      int index = this.map.hashIndex(hashCode, this.map.data.length);

      for(AbstractHashedMap.HashEntry entry = this.map.data[index]; entry != null; entry = entry.next) {
         if (entry.hashCode == hashCode && this.isEqualKey(entry, key1, key2, key3, key4)) {
            Object oldValue = entry.getValue();
            this.map.updateEntry(entry, value);
            return oldValue;
         }
      }

      this.map.addMapping(index, hashCode, new MultiKey(key1, key2, key3, key4), value);
      return null;
   }

   public Object remove(Object key1, Object key2, Object key3, Object key4) {
      int hashCode = this.hash(key1, key2, key3, key4);
      int index = this.map.hashIndex(hashCode, this.map.data.length);
      AbstractHashedMap.HashEntry entry = this.map.data[index];

      for(AbstractHashedMap.HashEntry previous = null; entry != null; entry = entry.next) {
         if (entry.hashCode == hashCode && this.isEqualKey(entry, key1, key2, key3, key4)) {
            Object oldValue = entry.getValue();
            this.map.removeMapping(entry, index, previous);
            return oldValue;
         }

         previous = entry;
      }

      return null;
   }

   protected int hash(Object key1, Object key2, Object key3, Object key4) {
      int h = 0;
      if (key1 != null) {
         h ^= key1.hashCode();
      }

      if (key2 != null) {
         h ^= key2.hashCode();
      }

      if (key3 != null) {
         h ^= key3.hashCode();
      }

      if (key4 != null) {
         h ^= key4.hashCode();
      }

      h += ~(h << 9);
      h ^= h >>> 14;
      h += h << 4;
      h ^= h >>> 10;
      return h;
   }

   protected boolean isEqualKey(AbstractHashedMap.HashEntry entry, Object key1, Object key2, Object key3, Object key4) {
      boolean var10000;
      label54: {
         MultiKey multi = (MultiKey)entry.getKey();
         if (multi.size() == 4) {
            label48: {
               if (key1 == null) {
                  if (multi.getKey(0) != null) {
                     break label48;
                  }
               } else if (!key1.equals(multi.getKey(0))) {
                  break label48;
               }

               if (key2 == null) {
                  if (multi.getKey(1) != null) {
                     break label48;
                  }
               } else if (!key2.equals(multi.getKey(1))) {
                  break label48;
               }

               if (key3 == null) {
                  if (multi.getKey(2) != null) {
                     break label48;
                  }
               } else if (!key3.equals(multi.getKey(2))) {
                  break label48;
               }

               if (key4 == null) {
                  if (multi.getKey(3) == null) {
                     break label54;
                  }
               } else if (key4.equals(multi.getKey(3))) {
                  break label54;
               }
            }
         }

         var10000 = false;
         return var10000;
      }

      var10000 = true;
      return var10000;
   }

   public Object get(Object key1, Object key2, Object key3, Object key4, Object key5) {
      int hashCode = this.hash(key1, key2, key3, key4, key5);

      for(AbstractHashedMap.HashEntry entry = this.map.data[this.map.hashIndex(hashCode, this.map.data.length)]; entry != null; entry = entry.next) {
         if (entry.hashCode == hashCode && this.isEqualKey(entry, key1, key2, key3, key4, key5)) {
            return entry.getValue();
         }
      }

      return null;
   }

   public boolean containsKey(Object key1, Object key2, Object key3, Object key4, Object key5) {
      int hashCode = this.hash(key1, key2, key3, key4, key5);

      for(AbstractHashedMap.HashEntry entry = this.map.data[this.map.hashIndex(hashCode, this.map.data.length)]; entry != null; entry = entry.next) {
         if (entry.hashCode == hashCode && this.isEqualKey(entry, key1, key2, key3, key4, key5)) {
            return true;
         }
      }

      return false;
   }

   public Object put(Object key1, Object key2, Object key3, Object key4, Object key5, Object value) {
      int hashCode = this.hash(key1, key2, key3, key4, key5);
      int index = this.map.hashIndex(hashCode, this.map.data.length);

      for(AbstractHashedMap.HashEntry entry = this.map.data[index]; entry != null; entry = entry.next) {
         if (entry.hashCode == hashCode && this.isEqualKey(entry, key1, key2, key3, key4, key5)) {
            Object oldValue = entry.getValue();
            this.map.updateEntry(entry, value);
            return oldValue;
         }
      }

      this.map.addMapping(index, hashCode, new MultiKey(key1, key2, key3, key4, key5), value);
      return null;
   }

   public Object remove(Object key1, Object key2, Object key3, Object key4, Object key5) {
      int hashCode = this.hash(key1, key2, key3, key4, key5);
      int index = this.map.hashIndex(hashCode, this.map.data.length);
      AbstractHashedMap.HashEntry entry = this.map.data[index];

      for(AbstractHashedMap.HashEntry previous = null; entry != null; entry = entry.next) {
         if (entry.hashCode == hashCode && this.isEqualKey(entry, key1, key2, key3, key4, key5)) {
            Object oldValue = entry.getValue();
            this.map.removeMapping(entry, index, previous);
            return oldValue;
         }

         previous = entry;
      }

      return null;
   }

   protected int hash(Object key1, Object key2, Object key3, Object key4, Object key5) {
      int h = 0;
      if (key1 != null) {
         h ^= key1.hashCode();
      }

      if (key2 != null) {
         h ^= key2.hashCode();
      }

      if (key3 != null) {
         h ^= key3.hashCode();
      }

      if (key4 != null) {
         h ^= key4.hashCode();
      }

      if (key5 != null) {
         h ^= key5.hashCode();
      }

      h += ~(h << 9);
      h ^= h >>> 14;
      h += h << 4;
      h ^= h >>> 10;
      return h;
   }

   protected boolean isEqualKey(AbstractHashedMap.HashEntry entry, Object key1, Object key2, Object key3, Object key4, Object key5) {
      boolean var10000;
      label62: {
         MultiKey multi = (MultiKey)entry.getKey();
         if (multi.size() == 5) {
            label56: {
               if (key1 == null) {
                  if (multi.getKey(0) != null) {
                     break label56;
                  }
               } else if (!key1.equals(multi.getKey(0))) {
                  break label56;
               }

               if (key2 == null) {
                  if (multi.getKey(1) != null) {
                     break label56;
                  }
               } else if (!key2.equals(multi.getKey(1))) {
                  break label56;
               }

               if (key3 == null) {
                  if (multi.getKey(2) != null) {
                     break label56;
                  }
               } else if (!key3.equals(multi.getKey(2))) {
                  break label56;
               }

               if (key4 == null) {
                  if (multi.getKey(3) != null) {
                     break label56;
                  }
               } else if (!key4.equals(multi.getKey(3))) {
                  break label56;
               }

               if (key5 == null) {
                  if (multi.getKey(4) == null) {
                     break label62;
                  }
               } else if (key5.equals(multi.getKey(4))) {
                  break label62;
               }
            }
         }

         var10000 = false;
         return var10000;
      }

      var10000 = true;
      return var10000;
   }

   public boolean removeAll(Object key1) {
      boolean modified = false;
      MapIterator it = this.mapIterator();

      while(it.hasNext()) {
         MultiKey multi = (MultiKey)it.next();
         if (multi.size() >= 1) {
            if (key1 == null) {
               if (multi.getKey(0) != null) {
                  continue;
               }
            } else if (!key1.equals(multi.getKey(0))) {
               continue;
            }

            it.remove();
            modified = true;
         }
      }

      return modified;
   }

   public boolean removeAll(Object key1, Object key2) {
      boolean modified = false;
      MapIterator it = this.mapIterator();

      while(it.hasNext()) {
         MultiKey multi = (MultiKey)it.next();
         if (multi.size() >= 2) {
            if (key1 == null) {
               if (multi.getKey(0) != null) {
                  continue;
               }
            } else if (!key1.equals(multi.getKey(0))) {
               continue;
            }

            if (key2 == null) {
               if (multi.getKey(1) != null) {
                  continue;
               }
            } else if (!key2.equals(multi.getKey(1))) {
               continue;
            }

            it.remove();
            modified = true;
         }
      }

      return modified;
   }

   public boolean removeAll(Object key1, Object key2, Object key3) {
      boolean modified = false;
      MapIterator it = this.mapIterator();

      while(it.hasNext()) {
         MultiKey multi = (MultiKey)it.next();
         if (multi.size() >= 3) {
            if (key1 == null) {
               if (multi.getKey(0) != null) {
                  continue;
               }
            } else if (!key1.equals(multi.getKey(0))) {
               continue;
            }

            if (key2 == null) {
               if (multi.getKey(1) != null) {
                  continue;
               }
            } else if (!key2.equals(multi.getKey(1))) {
               continue;
            }

            if (key3 == null) {
               if (multi.getKey(2) != null) {
                  continue;
               }
            } else if (!key3.equals(multi.getKey(2))) {
               continue;
            }

            it.remove();
            modified = true;
         }
      }

      return modified;
   }

   public boolean removeAll(Object key1, Object key2, Object key3, Object key4) {
      boolean modified = false;
      MapIterator it = this.mapIterator();

      while(it.hasNext()) {
         MultiKey multi = (MultiKey)it.next();
         if (multi.size() >= 4) {
            if (key1 == null) {
               if (multi.getKey(0) != null) {
                  continue;
               }
            } else if (!key1.equals(multi.getKey(0))) {
               continue;
            }

            if (key2 == null) {
               if (multi.getKey(1) != null) {
                  continue;
               }
            } else if (!key2.equals(multi.getKey(1))) {
               continue;
            }

            if (key3 == null) {
               if (multi.getKey(2) != null) {
                  continue;
               }
            } else if (!key3.equals(multi.getKey(2))) {
               continue;
            }

            if (key4 == null) {
               if (multi.getKey(3) != null) {
                  continue;
               }
            } else if (!key4.equals(multi.getKey(3))) {
               continue;
            }

            it.remove();
            modified = true;
         }
      }

      return modified;
   }

   protected void checkKey(Object key) {
      if (key == null) {
         throw new NullPointerException("Key must not be null");
      } else if (!(key instanceof MultiKey)) {
         throw new ClassCastException("Key must be a MultiKey");
      }
   }

   public Object clone() {
      return new MultiKeyMap((AbstractHashedMap)this.map.clone());
   }

   public Object put(Object key, Object value) {
      this.checkKey(key);
      return this.map.put(key, value);
   }

   public void putAll(Map mapToCopy) {
      for(Object key : mapToCopy.keySet()) {
         this.checkKey(key);
      }

      this.map.putAll(mapToCopy);
   }

   public MapIterator mapIterator() {
      return this.map.mapIterator();
   }

   public int size() {
      return this.map.size();
   }

   public boolean isEmpty() {
      return this.map.isEmpty();
   }

   public boolean containsKey(Object key) {
      return this.map.containsKey(key);
   }

   public boolean containsValue(Object value) {
      return this.map.containsValue(value);
   }

   public Object get(Object key) {
      return this.map.get(key);
   }

   public Object remove(Object key) {
      return this.map.remove(key);
   }

   public void clear() {
      this.map.clear();
   }

   public Set keySet() {
      return this.map.keySet();
   }

   public Collection values() {
      return this.map.values();
   }

   public Set entrySet() {
      return this.map.entrySet();
   }

   public boolean equals(Object obj) {
      return obj == this ? true : this.map.equals(obj);
   }

   public int hashCode() {
      return this.map.hashCode();
   }

   public String toString() {
      return this.map.toString();
   }
}
