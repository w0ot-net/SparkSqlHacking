package org.apache.commons.collections;

import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class FastHashMap extends HashMap {
   protected HashMap map = null;
   protected boolean fast = false;

   public FastHashMap() {
      this.map = new HashMap();
   }

   public FastHashMap(int capacity) {
      this.map = new HashMap(capacity);
   }

   public FastHashMap(int capacity, float factor) {
      this.map = new HashMap(capacity, factor);
   }

   public FastHashMap(Map map) {
      this.map = new HashMap(map);
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

   public Object put(Object key, Object value) {
      if (this.fast) {
         synchronized(this) {
            HashMap temp = (HashMap)this.map.clone();
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
            HashMap temp = (HashMap)this.map.clone();
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
            HashMap temp = (HashMap)this.map.clone();
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
            this.map = new HashMap();
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
      FastHashMap results = null;
      if (this.fast) {
         results = new FastHashMap(this.map);
      } else {
         synchronized(this.map) {
            results = new FastHashMap(this.map);
         }
      }

      results.setFast(this.getFast());
      return results;
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
         if (FastHashMap.this.fast) {
            synchronized(FastHashMap.this) {
               FastHashMap.this.map = new HashMap();
            }
         } else {
            synchronized(FastHashMap.this.map) {
               this.get(FastHashMap.this.map).clear();
            }
         }

      }

      public boolean remove(Object o) {
         if (FastHashMap.this.fast) {
            synchronized(FastHashMap.this) {
               HashMap temp = (HashMap)FastHashMap.this.map.clone();
               boolean r = this.get(temp).remove(o);
               FastHashMap.this.map = temp;
               return r;
            }
         } else {
            synchronized(FastHashMap.this.map) {
               return this.get(FastHashMap.this.map).remove(o);
            }
         }
      }

      public boolean removeAll(Collection o) {
         if (FastHashMap.this.fast) {
            synchronized(FastHashMap.this) {
               HashMap temp = (HashMap)FastHashMap.this.map.clone();
               boolean r = this.get(temp).removeAll(o);
               FastHashMap.this.map = temp;
               return r;
            }
         } else {
            synchronized(FastHashMap.this.map) {
               return this.get(FastHashMap.this.map).removeAll(o);
            }
         }
      }

      public boolean retainAll(Collection o) {
         if (FastHashMap.this.fast) {
            synchronized(FastHashMap.this) {
               HashMap temp = (HashMap)FastHashMap.this.map.clone();
               boolean r = this.get(temp).retainAll(o);
               FastHashMap.this.map = temp;
               return r;
            }
         } else {
            synchronized(FastHashMap.this.map) {
               return this.get(FastHashMap.this.map).retainAll(o);
            }
         }
      }

      public int size() {
         if (FastHashMap.this.fast) {
            return this.get(FastHashMap.this.map).size();
         } else {
            synchronized(FastHashMap.this.map) {
               return this.get(FastHashMap.this.map).size();
            }
         }
      }

      public boolean isEmpty() {
         if (FastHashMap.this.fast) {
            return this.get(FastHashMap.this.map).isEmpty();
         } else {
            synchronized(FastHashMap.this.map) {
               return this.get(FastHashMap.this.map).isEmpty();
            }
         }
      }

      public boolean contains(Object o) {
         if (FastHashMap.this.fast) {
            return this.get(FastHashMap.this.map).contains(o);
         } else {
            synchronized(FastHashMap.this.map) {
               return this.get(FastHashMap.this.map).contains(o);
            }
         }
      }

      public boolean containsAll(Collection o) {
         if (FastHashMap.this.fast) {
            return this.get(FastHashMap.this.map).containsAll(o);
         } else {
            synchronized(FastHashMap.this.map) {
               return this.get(FastHashMap.this.map).containsAll(o);
            }
         }
      }

      public Object[] toArray(Object[] o) {
         if (FastHashMap.this.fast) {
            return this.get(FastHashMap.this.map).toArray(o);
         } else {
            synchronized(FastHashMap.this.map) {
               return this.get(FastHashMap.this.map).toArray(o);
            }
         }
      }

      public Object[] toArray() {
         if (FastHashMap.this.fast) {
            return this.get(FastHashMap.this.map).toArray();
         } else {
            synchronized(FastHashMap.this.map) {
               return this.get(FastHashMap.this.map).toArray();
            }
         }
      }

      public boolean equals(Object o) {
         if (o == this) {
            return true;
         } else if (FastHashMap.this.fast) {
            return this.get(FastHashMap.this.map).equals(o);
         } else {
            synchronized(FastHashMap.this.map) {
               return this.get(FastHashMap.this.map).equals(o);
            }
         }
      }

      public int hashCode() {
         if (FastHashMap.this.fast) {
            return this.get(FastHashMap.this.map).hashCode();
         } else {
            synchronized(FastHashMap.this.map) {
               return this.get(FastHashMap.this.map).hashCode();
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
            this.expected = FastHashMap.this.map;
            this.iterator = this.expected.entrySet().iterator();
         }

         public boolean hasNext() {
            if (this.expected != FastHashMap.this.map) {
               throw new ConcurrentModificationException();
            } else {
               return this.iterator.hasNext();
            }
         }

         public Object next() {
            if (this.expected != FastHashMap.this.map) {
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
               if (FastHashMap.this.fast) {
                  synchronized(FastHashMap.this) {
                     if (this.expected != FastHashMap.this.map) {
                        throw new ConcurrentModificationException();
                     }

                     FastHashMap.this.remove(this.lastReturned.getKey());
                     this.lastReturned = null;
                     this.expected = FastHashMap.this.map;
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
