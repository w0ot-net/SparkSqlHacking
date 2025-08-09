package org.apache.commons.collections;

import java.util.Collection;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

public class FastTreeMap extends TreeMap {
   protected TreeMap map = null;
   protected boolean fast = false;

   public FastTreeMap() {
      this.map = new TreeMap();
   }

   public FastTreeMap(Comparator comparator) {
      this.map = new TreeMap(comparator);
   }

   public FastTreeMap(Map map) {
      this.map = new TreeMap(map);
   }

   public FastTreeMap(SortedMap map) {
      this.map = new TreeMap(map);
   }

   public boolean getFast() {
      return this.fast;
   }

   public void setFast(boolean fast) {
      this.fast = fast;
   }

   public Object get(Object key) {
      if (this.fast) {
         return this.map.get(key);
      } else {
         synchronized(this.map) {
            return this.map.get(key);
         }
      }
   }

   public int size() {
      if (this.fast) {
         return this.map.size();
      } else {
         synchronized(this.map) {
            return this.map.size();
         }
      }
   }

   public boolean isEmpty() {
      if (this.fast) {
         return this.map.isEmpty();
      } else {
         synchronized(this.map) {
            return this.map.isEmpty();
         }
      }
   }

   public boolean containsKey(Object key) {
      if (this.fast) {
         return this.map.containsKey(key);
      } else {
         synchronized(this.map) {
            return this.map.containsKey(key);
         }
      }
   }

   public boolean containsValue(Object value) {
      if (this.fast) {
         return this.map.containsValue(value);
      } else {
         synchronized(this.map) {
            return this.map.containsValue(value);
         }
      }
   }

   public Comparator comparator() {
      if (this.fast) {
         return this.map.comparator();
      } else {
         synchronized(this.map) {
            return this.map.comparator();
         }
      }
   }

   public Object firstKey() {
      if (this.fast) {
         return this.map.firstKey();
      } else {
         synchronized(this.map) {
            return this.map.firstKey();
         }
      }
   }

   public Object lastKey() {
      if (this.fast) {
         return this.map.lastKey();
      } else {
         synchronized(this.map) {
            return this.map.lastKey();
         }
      }
   }

   public Object put(Object key, Object value) {
      if (this.fast) {
         synchronized(this) {
            TreeMap temp = (TreeMap)this.map.clone();
            Object result = temp.put(key, value);
            this.map = temp;
            return result;
         }
      } else {
         synchronized(this.map) {
            return this.map.put(key, value);
         }
      }
   }

   public void putAll(Map in) {
      if (this.fast) {
         synchronized(this) {
            TreeMap temp = (TreeMap)this.map.clone();
            temp.putAll(in);
            this.map = temp;
         }
      } else {
         synchronized(this.map) {
            this.map.putAll(in);
         }
      }

   }

   public Object remove(Object key) {
      if (this.fast) {
         synchronized(this) {
            TreeMap temp = (TreeMap)this.map.clone();
            Object result = temp.remove(key);
            this.map = temp;
            return result;
         }
      } else {
         synchronized(this.map) {
            return this.map.remove(key);
         }
      }
   }

   public void clear() {
      if (this.fast) {
         synchronized(this) {
            this.map = new TreeMap();
         }
      } else {
         synchronized(this.map) {
            this.map.clear();
         }
      }

   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof Map)) {
         return false;
      } else {
         Map mo = (Map)o;
         if (this.fast) {
            if (mo.size() != this.map.size()) {
               return false;
            } else {
               for(Map.Entry e : this.map.entrySet()) {
                  Object key = e.getKey();
                  Object value = e.getValue();
                  if (value == null) {
                     if (mo.get(key) != null || !mo.containsKey(key)) {
                        return false;
                     }
                  } else if (!value.equals(mo.get(key))) {
                     return false;
                  }
               }

               return true;
            }
         } else {
            synchronized(this.map) {
               if (mo.size() != this.map.size()) {
                  return false;
               } else {
                  for(Map.Entry e : this.map.entrySet()) {
                     Object key = e.getKey();
                     Object value = e.getValue();
                     if (value == null) {
                        if (mo.get(key) != null || !mo.containsKey(key)) {
                           return false;
                        }
                     } else if (!value.equals(mo.get(key))) {
                        return false;
                     }
                  }

                  return true;
               }
            }
         }
      }
   }

   public int hashCode() {
      if (this.fast) {
         int h = 0;

         for(Iterator i = this.map.entrySet().iterator(); i.hasNext(); h += i.next().hashCode()) {
         }

         return h;
      } else {
         synchronized(this.map) {
            int h = 0;

            for(Iterator i = this.map.entrySet().iterator(); i.hasNext(); h += i.next().hashCode()) {
            }

            return h;
         }
      }
   }

   public Object clone() {
      FastTreeMap results = null;
      if (this.fast) {
         results = new FastTreeMap(this.map);
      } else {
         synchronized(this.map) {
            results = new FastTreeMap(this.map);
         }
      }

      results.setFast(this.getFast());
      return results;
   }

   public SortedMap headMap(Object key) {
      if (this.fast) {
         return this.map.headMap(key);
      } else {
         synchronized(this.map) {
            return this.map.headMap(key);
         }
      }
   }

   public SortedMap subMap(Object fromKey, Object toKey) {
      if (this.fast) {
         return this.map.subMap(fromKey, toKey);
      } else {
         synchronized(this.map) {
            return this.map.subMap(fromKey, toKey);
         }
      }
   }

   public SortedMap tailMap(Object key) {
      if (this.fast) {
         return this.map.tailMap(key);
      } else {
         synchronized(this.map) {
            return this.map.tailMap(key);
         }
      }
   }

   public Set entrySet() {
      return new EntrySet();
   }

   public Set keySet() {
      return new KeySet();
   }

   public Collection values() {
      return new Values();
   }

   private abstract class CollectionView implements Collection {
      public CollectionView() {
      }

      protected abstract Collection get(Map var1);

      protected abstract Object iteratorNext(Map.Entry var1);

      public void clear() {
         if (FastTreeMap.this.fast) {
            synchronized(FastTreeMap.this) {
               FastTreeMap.this.map = new TreeMap();
            }
         } else {
            synchronized(FastTreeMap.this.map) {
               this.get(FastTreeMap.this.map).clear();
            }
         }

      }

      public boolean remove(Object o) {
         if (FastTreeMap.this.fast) {
            synchronized(FastTreeMap.this) {
               TreeMap temp = (TreeMap)FastTreeMap.this.map.clone();
               boolean r = this.get(temp).remove(o);
               FastTreeMap.this.map = temp;
               return r;
            }
         } else {
            synchronized(FastTreeMap.this.map) {
               return this.get(FastTreeMap.this.map).remove(o);
            }
         }
      }

      public boolean removeAll(Collection o) {
         if (FastTreeMap.this.fast) {
            synchronized(FastTreeMap.this) {
               TreeMap temp = (TreeMap)FastTreeMap.this.map.clone();
               boolean r = this.get(temp).removeAll(o);
               FastTreeMap.this.map = temp;
               return r;
            }
         } else {
            synchronized(FastTreeMap.this.map) {
               return this.get(FastTreeMap.this.map).removeAll(o);
            }
         }
      }

      public boolean retainAll(Collection o) {
         if (FastTreeMap.this.fast) {
            synchronized(FastTreeMap.this) {
               TreeMap temp = (TreeMap)FastTreeMap.this.map.clone();
               boolean r = this.get(temp).retainAll(o);
               FastTreeMap.this.map = temp;
               return r;
            }
         } else {
            synchronized(FastTreeMap.this.map) {
               return this.get(FastTreeMap.this.map).retainAll(o);
            }
         }
      }

      public int size() {
         if (FastTreeMap.this.fast) {
            return this.get(FastTreeMap.this.map).size();
         } else {
            synchronized(FastTreeMap.this.map) {
               return this.get(FastTreeMap.this.map).size();
            }
         }
      }

      public boolean isEmpty() {
         if (FastTreeMap.this.fast) {
            return this.get(FastTreeMap.this.map).isEmpty();
         } else {
            synchronized(FastTreeMap.this.map) {
               return this.get(FastTreeMap.this.map).isEmpty();
            }
         }
      }

      public boolean contains(Object o) {
         if (FastTreeMap.this.fast) {
            return this.get(FastTreeMap.this.map).contains(o);
         } else {
            synchronized(FastTreeMap.this.map) {
               return this.get(FastTreeMap.this.map).contains(o);
            }
         }
      }

      public boolean containsAll(Collection o) {
         if (FastTreeMap.this.fast) {
            return this.get(FastTreeMap.this.map).containsAll(o);
         } else {
            synchronized(FastTreeMap.this.map) {
               return this.get(FastTreeMap.this.map).containsAll(o);
            }
         }
      }

      public Object[] toArray(Object[] o) {
         if (FastTreeMap.this.fast) {
            return this.get(FastTreeMap.this.map).toArray(o);
         } else {
            synchronized(FastTreeMap.this.map) {
               return this.get(FastTreeMap.this.map).toArray(o);
            }
         }
      }

      public Object[] toArray() {
         if (FastTreeMap.this.fast) {
            return this.get(FastTreeMap.this.map).toArray();
         } else {
            synchronized(FastTreeMap.this.map) {
               return this.get(FastTreeMap.this.map).toArray();
            }
         }
      }

      public boolean equals(Object o) {
         if (o == this) {
            return true;
         } else if (FastTreeMap.this.fast) {
            return this.get(FastTreeMap.this.map).equals(o);
         } else {
            synchronized(FastTreeMap.this.map) {
               return this.get(FastTreeMap.this.map).equals(o);
            }
         }
      }

      public int hashCode() {
         if (FastTreeMap.this.fast) {
            return this.get(FastTreeMap.this.map).hashCode();
         } else {
            synchronized(FastTreeMap.this.map) {
               return this.get(FastTreeMap.this.map).hashCode();
            }
         }
      }

      public boolean add(Object o) {
         throw new UnsupportedOperationException();
      }

      public boolean addAll(Collection c) {
         throw new UnsupportedOperationException();
      }

      public Iterator iterator() {
         return new CollectionViewIterator();
      }

      private class CollectionViewIterator implements Iterator {
         private Map expected;
         private Map.Entry lastReturned = null;
         private Iterator iterator;

         public CollectionViewIterator() {
            this.expected = FastTreeMap.this.map;
            this.iterator = this.expected.entrySet().iterator();
         }

         public boolean hasNext() {
            if (this.expected != FastTreeMap.this.map) {
               throw new ConcurrentModificationException();
            } else {
               return this.iterator.hasNext();
            }
         }

         public Object next() {
            if (this.expected != FastTreeMap.this.map) {
               throw new ConcurrentModificationException();
            } else {
               this.lastReturned = (Map.Entry)this.iterator.next();
               return CollectionView.this.iteratorNext(this.lastReturned);
            }
         }

         public void remove() {
            if (this.lastReturned == null) {
               throw new IllegalStateException();
            } else {
               if (FastTreeMap.this.fast) {
                  synchronized(FastTreeMap.this) {
                     if (this.expected != FastTreeMap.this.map) {
                        throw new ConcurrentModificationException();
                     }

                     FastTreeMap.this.remove(this.lastReturned.getKey());
                     this.lastReturned = null;
                     this.expected = FastTreeMap.this.map;
                  }
               } else {
                  this.iterator.remove();
                  this.lastReturned = null;
               }

            }
         }
      }
   }

   private class KeySet extends CollectionView implements Set {
      private KeySet() {
      }

      protected Collection get(Map map) {
         return map.keySet();
      }

      protected Object iteratorNext(Map.Entry entry) {
         return entry.getKey();
      }
   }

   private class Values extends CollectionView {
      private Values() {
      }

      protected Collection get(Map map) {
         return map.values();
      }

      protected Object iteratorNext(Map.Entry entry) {
         return entry.getValue();
      }
   }

   private class EntrySet extends CollectionView implements Set {
      private EntrySet() {
      }

      protected Collection get(Map map) {
         return map.entrySet();
      }

      protected Object iteratorNext(Map.Entry entry) {
         return entry;
      }
   }
}
