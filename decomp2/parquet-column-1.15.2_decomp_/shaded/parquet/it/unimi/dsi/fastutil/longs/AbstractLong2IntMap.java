package shaded.parquet.it.unimi.dsi.fastutil.longs;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import java.util.function.IntConsumer;
import shaded.parquet.it.unimi.dsi.fastutil.HashCommon;
import shaded.parquet.it.unimi.dsi.fastutil.Size64;
import shaded.parquet.it.unimi.dsi.fastutil.ints.AbstractIntCollection;
import shaded.parquet.it.unimi.dsi.fastutil.ints.IntBinaryOperator;
import shaded.parquet.it.unimi.dsi.fastutil.ints.IntCollection;
import shaded.parquet.it.unimi.dsi.fastutil.ints.IntIterator;
import shaded.parquet.it.unimi.dsi.fastutil.ints.IntSpliterator;
import shaded.parquet.it.unimi.dsi.fastutil.ints.IntSpliterators;
import shaded.parquet.it.unimi.dsi.fastutil.objects.AbstractObjectSet;
import shaded.parquet.it.unimi.dsi.fastutil.objects.ObjectIterator;
import shaded.parquet.it.unimi.dsi.fastutil.objects.ObjectSpliterator;
import shaded.parquet.it.unimi.dsi.fastutil.objects.ObjectSpliterators;

public abstract class AbstractLong2IntMap extends AbstractLong2IntFunction implements Long2IntMap, Serializable {
   private static final long serialVersionUID = -4940583368468432370L;

   protected AbstractLong2IntMap() {
   }

   public boolean containsKey(long k) {
      ObjectIterator<Long2IntMap.Entry> i = this.long2IntEntrySet().iterator();

      while(i.hasNext()) {
         if (((Long2IntMap.Entry)i.next()).getLongKey() == k) {
            return true;
         }
      }

      return false;
   }

   public boolean containsValue(int v) {
      ObjectIterator<Long2IntMap.Entry> i = this.long2IntEntrySet().iterator();

      while(i.hasNext()) {
         if (((Long2IntMap.Entry)i.next()).getIntValue() == v) {
            return true;
         }
      }

      return false;
   }

   public boolean isEmpty() {
      return this.size() == 0;
   }

   public final int mergeInt(long key, int value, IntBinaryOperator remappingFunction) {
      return this.mergeInt(key, value, remappingFunction);
   }

   public LongSet keySet() {
      return new AbstractLongSet() {
         public boolean contains(long k) {
            return AbstractLong2IntMap.this.containsKey(k);
         }

         public int size() {
            return AbstractLong2IntMap.this.size();
         }

         public void clear() {
            AbstractLong2IntMap.this.clear();
         }

         public LongIterator iterator() {
            return new LongIterator() {
               private final ObjectIterator i = Long2IntMaps.fastIterator(AbstractLong2IntMap.this);

               public long nextLong() {
                  return ((Long2IntMap.Entry)this.i.next()).getLongKey();
               }

               public boolean hasNext() {
                  return this.i.hasNext();
               }

               public void remove() {
                  this.i.remove();
               }

               public void forEachRemaining(java.util.function.LongConsumer action) {
                  this.i.forEachRemaining((entry) -> action.accept(entry.getLongKey()));
               }
            };
         }

         public LongSpliterator spliterator() {
            return LongSpliterators.asSpliterator(this.iterator(), Size64.sizeOf((Map)AbstractLong2IntMap.this), 321);
         }
      };
   }

   public IntCollection values() {
      return new AbstractIntCollection() {
         public boolean contains(int k) {
            return AbstractLong2IntMap.this.containsValue(k);
         }

         public int size() {
            return AbstractLong2IntMap.this.size();
         }

         public void clear() {
            AbstractLong2IntMap.this.clear();
         }

         public IntIterator iterator() {
            return new IntIterator() {
               private final ObjectIterator i = Long2IntMaps.fastIterator(AbstractLong2IntMap.this);

               public int nextInt() {
                  return ((Long2IntMap.Entry)this.i.next()).getIntValue();
               }

               public boolean hasNext() {
                  return this.i.hasNext();
               }

               public void remove() {
                  this.i.remove();
               }

               public void forEachRemaining(IntConsumer action) {
                  this.i.forEachRemaining((entry) -> action.accept(entry.getIntValue()));
               }
            };
         }

         public IntSpliterator spliterator() {
            return IntSpliterators.asSpliterator(this.iterator(), Size64.sizeOf((Map)AbstractLong2IntMap.this), 320);
         }
      };
   }

   public void putAll(Map m) {
      if (m instanceof Long2IntMap) {
         ObjectIterator<Long2IntMap.Entry> i = Long2IntMaps.fastIterator((Long2IntMap)m);

         while(i.hasNext()) {
            Long2IntMap.Entry e = (Long2IntMap.Entry)i.next();
            this.put(e.getLongKey(), e.getIntValue());
         }
      } else {
         int n = m.size();
         Iterator<? extends Map.Entry<? extends Long, ? extends Integer>> i = m.entrySet().iterator();

         while(n-- != 0) {
            Map.Entry<? extends Long, ? extends Integer> e = (Map.Entry)i.next();
            this.put((Long)e.getKey(), (Integer)e.getValue());
         }
      }

   }

   public int hashCode() {
      int h = 0;
      int n = this.size();

      for(ObjectIterator<Long2IntMap.Entry> i = Long2IntMaps.fastIterator(this); n-- != 0; h += ((Long2IntMap.Entry)i.next()).hashCode()) {
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
         return m.size() != this.size() ? false : this.long2IntEntrySet().containsAll(m.entrySet());
      }
   }

   public String toString() {
      StringBuilder s = new StringBuilder();
      ObjectIterator<Long2IntMap.Entry> i = Long2IntMaps.fastIterator(this);
      int n = this.size();
      boolean first = true;
      s.append("{");

      while(n-- != 0) {
         if (first) {
            first = false;
         } else {
            s.append(", ");
         }

         Long2IntMap.Entry e = (Long2IntMap.Entry)i.next();
         s.append(String.valueOf(e.getLongKey()));
         s.append("=>");
         s.append(String.valueOf(e.getIntValue()));
      }

      s.append("}");
      return s.toString();
   }

   public static class BasicEntry implements Long2IntMap.Entry {
      protected long key;
      protected int value;

      public BasicEntry() {
      }

      public BasicEntry(Long key, Integer value) {
         this.key = key;
         this.value = value;
      }

      public BasicEntry(long key, int value) {
         this.key = key;
         this.value = value;
      }

      public long getLongKey() {
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
         } else if (o instanceof Long2IntMap.Entry) {
            Long2IntMap.Entry e = (Long2IntMap.Entry)o;
            return this.key == e.getLongKey() && this.value == e.getIntValue();
         } else {
            Map.Entry<?, ?> e = (Map.Entry)o;
            Object key = e.getKey();
            if (key != null && key instanceof Long) {
               Object value = e.getValue();
               if (value != null && value instanceof Integer) {
                  return this.key == (Long)key && this.value == (Integer)value;
               } else {
                  return false;
               }
            } else {
               return false;
            }
         }
      }

      public int hashCode() {
         return HashCommon.long2int(this.key) ^ this.value;
      }

      public String toString() {
         return this.key + "->" + this.value;
      }
   }

   public abstract static class BasicEntrySet extends AbstractObjectSet {
      protected final Long2IntMap map;

      public BasicEntrySet(Long2IntMap map) {
         this.map = map;
      }

      public boolean contains(Object o) {
         if (!(o instanceof Map.Entry)) {
            return false;
         } else if (o instanceof Long2IntMap.Entry) {
            Long2IntMap.Entry e = (Long2IntMap.Entry)o;
            long k = e.getLongKey();
            return this.map.containsKey(k) && this.map.get(k) == e.getIntValue();
         } else {
            Map.Entry<?, ?> e = (Map.Entry)o;
            Object key = e.getKey();
            if (key != null && key instanceof Long) {
               long k = (Long)key;
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
         } else if (o instanceof Long2IntMap.Entry) {
            Long2IntMap.Entry e = (Long2IntMap.Entry)o;
            return this.map.remove(e.getLongKey(), e.getIntValue());
         } else {
            Map.Entry<?, ?> e = (Map.Entry)o;
            Object key = e.getKey();
            if (key != null && key instanceof Long) {
               long k = (Long)key;
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
