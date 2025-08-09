package shaded.parquet.it.unimi.dsi.fastutil.ints;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import shaded.parquet.it.unimi.dsi.fastutil.Size64;
import shaded.parquet.it.unimi.dsi.fastutil.objects.AbstractObjectSet;
import shaded.parquet.it.unimi.dsi.fastutil.objects.ObjectIterator;
import shaded.parquet.it.unimi.dsi.fastutil.objects.ObjectSpliterator;
import shaded.parquet.it.unimi.dsi.fastutil.objects.ObjectSpliterators;

public abstract class AbstractInt2IntMap extends AbstractInt2IntFunction implements Int2IntMap, Serializable {
   private static final long serialVersionUID = -4940583368468432370L;

   protected AbstractInt2IntMap() {
   }

   public boolean containsKey(int k) {
      ObjectIterator<Int2IntMap.Entry> i = this.int2IntEntrySet().iterator();

      while(i.hasNext()) {
         if (((Int2IntMap.Entry)i.next()).getIntKey() == k) {
            return true;
         }
      }

      return false;
   }

   public boolean containsValue(int v) {
      ObjectIterator<Int2IntMap.Entry> i = this.int2IntEntrySet().iterator();

      while(i.hasNext()) {
         if (((Int2IntMap.Entry)i.next()).getIntValue() == v) {
            return true;
         }
      }

      return false;
   }

   public boolean isEmpty() {
      return this.size() == 0;
   }

   public final int mergeInt(int key, int value, IntBinaryOperator remappingFunction) {
      return this.mergeInt(key, value, remappingFunction);
   }

   public IntSet keySet() {
      return new AbstractIntSet() {
         public boolean contains(int k) {
            return AbstractInt2IntMap.this.containsKey(k);
         }

         public int size() {
            return AbstractInt2IntMap.this.size();
         }

         public void clear() {
            AbstractInt2IntMap.this.clear();
         }

         public IntIterator iterator() {
            return new IntIterator() {
               private final ObjectIterator i = Int2IntMaps.fastIterator(AbstractInt2IntMap.this);

               public int nextInt() {
                  return ((Int2IntMap.Entry)this.i.next()).getIntKey();
               }

               public boolean hasNext() {
                  return this.i.hasNext();
               }

               public void remove() {
                  this.i.remove();
               }

               public void forEachRemaining(java.util.function.IntConsumer action) {
                  this.i.forEachRemaining((entry) -> action.accept(entry.getIntKey()));
               }
            };
         }

         public IntSpliterator spliterator() {
            return IntSpliterators.asSpliterator(this.iterator(), Size64.sizeOf((Map)AbstractInt2IntMap.this), 321);
         }
      };
   }

   public IntCollection values() {
      return new AbstractIntCollection() {
         public boolean contains(int k) {
            return AbstractInt2IntMap.this.containsValue(k);
         }

         public int size() {
            return AbstractInt2IntMap.this.size();
         }

         public void clear() {
            AbstractInt2IntMap.this.clear();
         }

         public IntIterator iterator() {
            return new IntIterator() {
               private final ObjectIterator i = Int2IntMaps.fastIterator(AbstractInt2IntMap.this);

               public int nextInt() {
                  return ((Int2IntMap.Entry)this.i.next()).getIntValue();
               }

               public boolean hasNext() {
                  return this.i.hasNext();
               }

               public void remove() {
                  this.i.remove();
               }

               public void forEachRemaining(java.util.function.IntConsumer action) {
                  this.i.forEachRemaining((entry) -> action.accept(entry.getIntValue()));
               }
            };
         }

         public IntSpliterator spliterator() {
            return IntSpliterators.asSpliterator(this.iterator(), Size64.sizeOf((Map)AbstractInt2IntMap.this), 320);
         }
      };
   }

   public void putAll(Map m) {
      if (m instanceof Int2IntMap) {
         ObjectIterator<Int2IntMap.Entry> i = Int2IntMaps.fastIterator((Int2IntMap)m);

         while(i.hasNext()) {
            Int2IntMap.Entry e = (Int2IntMap.Entry)i.next();
            this.put(e.getIntKey(), e.getIntValue());
         }
      } else {
         int n = m.size();
         Iterator<? extends Map.Entry<? extends Integer, ? extends Integer>> i = m.entrySet().iterator();

         while(n-- != 0) {
            Map.Entry<? extends Integer, ? extends Integer> e = (Map.Entry)i.next();
            this.put((Integer)e.getKey(), (Integer)e.getValue());
         }
      }

   }

   public int hashCode() {
      int h = 0;
      int n = this.size();

      for(ObjectIterator<Int2IntMap.Entry> i = Int2IntMaps.fastIterator(this); n-- != 0; h += ((Int2IntMap.Entry)i.next()).hashCode()) {
      }

      return h;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof Map)) {
         return false;
      } else {
         Map<?, ?> m = (Map)o;
         return m.size() != this.size() ? false : this.int2IntEntrySet().containsAll(m.entrySet());
      }
   }

   public String toString() {
      StringBuilder s = new StringBuilder();
      ObjectIterator<Int2IntMap.Entry> i = Int2IntMaps.fastIterator(this);
      int n = this.size();
      boolean first = true;
      s.append("{");

      while(n-- != 0) {
         if (first) {
            first = false;
         } else {
            s.append(", ");
         }

         Int2IntMap.Entry e = (Int2IntMap.Entry)i.next();
         s.append(String.valueOf(e.getIntKey()));
         s.append("=>");
         s.append(String.valueOf(e.getIntValue()));
      }

      s.append("}");
      return s.toString();
   }

   public static class BasicEntry implements Int2IntMap.Entry {
      protected int key;
      protected int value;

      public BasicEntry() {
      }

      public BasicEntry(Integer key, Integer value) {
         this.key = key;
         this.value = value;
      }

      public BasicEntry(int key, int value) {
         this.key = key;
         this.value = value;
      }

      public int getIntKey() {
         return this.key;
      }

      public int getIntValue() {
         return this.value;
      }

      public int setValue(int value) {
         throw new UnsupportedOperationException();
      }

      public boolean equals(Object o) {
         if (!(o instanceof Map.Entry)) {
            return false;
         } else if (o instanceof Int2IntMap.Entry) {
            Int2IntMap.Entry e = (Int2IntMap.Entry)o;
            return this.key == e.getIntKey() && this.value == e.getIntValue();
         } else {
            Map.Entry<?, ?> e = (Map.Entry)o;
            Object key = e.getKey();
            if (key != null && key instanceof Integer) {
               Object value = e.getValue();
               if (value != null && value instanceof Integer) {
                  return this.key == (Integer)key && this.value == (Integer)value;
               } else {
                  return false;
               }
            } else {
               return false;
            }
         }
      }

      public int hashCode() {
         return this.key ^ this.value;
      }

      public String toString() {
         return this.key + "->" + this.value;
      }
   }

   public abstract static class BasicEntrySet extends AbstractObjectSet {
      protected final Int2IntMap map;

      public BasicEntrySet(Int2IntMap map) {
         this.map = map;
      }

      public boolean contains(Object o) {
         if (!(o instanceof Map.Entry)) {
            return false;
         } else if (o instanceof Int2IntMap.Entry) {
            Int2IntMap.Entry e = (Int2IntMap.Entry)o;
            int k = e.getIntKey();
            return this.map.containsKey(k) && this.map.get(k) == e.getIntValue();
         } else {
            Map.Entry<?, ?> e = (Map.Entry)o;
            Object key = e.getKey();
            if (key != null && key instanceof Integer) {
               int k = (Integer)key;
               Object value = e.getValue();
               if (value != null && value instanceof Integer) {
                  return this.map.containsKey(k) && this.map.get(k) == (Integer)value;
               } else {
                  return false;
               }
            } else {
               return false;
            }
         }
      }

      public boolean remove(Object o) {
         if (!(o instanceof Map.Entry)) {
            return false;
         } else if (o instanceof Int2IntMap.Entry) {
            Int2IntMap.Entry e = (Int2IntMap.Entry)o;
            return this.map.remove(e.getIntKey(), e.getIntValue());
         } else {
            Map.Entry<?, ?> e = (Map.Entry)o;
            Object key = e.getKey();
            if (key != null && key instanceof Integer) {
               int k = (Integer)key;
               Object value = e.getValue();
               if (value != null && value instanceof Integer) {
                  int v = (Integer)value;
                  return this.map.remove(k, v);
               } else {
                  return false;
               }
            } else {
               return false;
            }
         }
      }

      public int size() {
         return this.map.size();
      }

      public ObjectSpliterator spliterator() {
         return ObjectSpliterators.asSpliterator(this.iterator(), Size64.sizeOf((Map)this.map), 65);
      }
   }
}
