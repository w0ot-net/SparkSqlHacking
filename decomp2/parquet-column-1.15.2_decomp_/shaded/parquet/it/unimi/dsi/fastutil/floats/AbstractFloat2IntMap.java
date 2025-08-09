package shaded.parquet.it.unimi.dsi.fastutil.floats;

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

public abstract class AbstractFloat2IntMap extends AbstractFloat2IntFunction implements Float2IntMap, Serializable {
   private static final long serialVersionUID = -4940583368468432370L;

   protected AbstractFloat2IntMap() {
   }

   public boolean containsKey(float k) {
      ObjectIterator<Float2IntMap.Entry> i = this.float2IntEntrySet().iterator();

      while(i.hasNext()) {
         if (((Float2IntMap.Entry)i.next()).getFloatKey() == k) {
            return true;
         }
      }

      return false;
   }

   public boolean containsValue(int v) {
      ObjectIterator<Float2IntMap.Entry> i = this.float2IntEntrySet().iterator();

      while(i.hasNext()) {
         if (((Float2IntMap.Entry)i.next()).getIntValue() == v) {
            return true;
         }
      }

      return false;
   }

   public boolean isEmpty() {
      return this.size() == 0;
   }

   public final int mergeInt(float key, int value, IntBinaryOperator remappingFunction) {
      return this.mergeInt(key, value, remappingFunction);
   }

   public FloatSet keySet() {
      return new AbstractFloatSet() {
         public boolean contains(float k) {
            return AbstractFloat2IntMap.this.containsKey(k);
         }

         public int size() {
            return AbstractFloat2IntMap.this.size();
         }

         public void clear() {
            AbstractFloat2IntMap.this.clear();
         }

         public FloatIterator iterator() {
            return new FloatIterator() {
               private final ObjectIterator i = Float2IntMaps.fastIterator(AbstractFloat2IntMap.this);

               public float nextFloat() {
                  return ((Float2IntMap.Entry)this.i.next()).getFloatKey();
               }

               public boolean hasNext() {
                  return this.i.hasNext();
               }

               public void remove() {
                  this.i.remove();
               }

               public void forEachRemaining(FloatConsumer action) {
                  this.i.forEachRemaining((entry) -> action.accept(entry.getFloatKey()));
               }
            };
         }

         public FloatSpliterator spliterator() {
            return FloatSpliterators.asSpliterator(this.iterator(), Size64.sizeOf((Map)AbstractFloat2IntMap.this), 321);
         }
      };
   }

   public IntCollection values() {
      return new AbstractIntCollection() {
         public boolean contains(int k) {
            return AbstractFloat2IntMap.this.containsValue(k);
         }

         public int size() {
            return AbstractFloat2IntMap.this.size();
         }

         public void clear() {
            AbstractFloat2IntMap.this.clear();
         }

         public IntIterator iterator() {
            return new IntIterator() {
               private final ObjectIterator i = Float2IntMaps.fastIterator(AbstractFloat2IntMap.this);

               public int nextInt() {
                  return ((Float2IntMap.Entry)this.i.next()).getIntValue();
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
            return IntSpliterators.asSpliterator(this.iterator(), Size64.sizeOf((Map)AbstractFloat2IntMap.this), 320);
         }
      };
   }

   public void putAll(Map m) {
      if (m instanceof Float2IntMap) {
         ObjectIterator<Float2IntMap.Entry> i = Float2IntMaps.fastIterator((Float2IntMap)m);

         while(i.hasNext()) {
            Float2IntMap.Entry e = (Float2IntMap.Entry)i.next();
            this.put(e.getFloatKey(), e.getIntValue());
         }
      } else {
         int n = m.size();
         Iterator<? extends Map.Entry<? extends Float, ? extends Integer>> i = m.entrySet().iterator();

         while(n-- != 0) {
            Map.Entry<? extends Float, ? extends Integer> e = (Map.Entry)i.next();
            this.put((Float)e.getKey(), (Integer)e.getValue());
         }
      }

   }

   public int hashCode() {
      int h = 0;
      int n = this.size();

      for(ObjectIterator<Float2IntMap.Entry> i = Float2IntMaps.fastIterator(this); n-- != 0; h += ((Float2IntMap.Entry)i.next()).hashCode()) {
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
         return m.size() != this.size() ? false : this.float2IntEntrySet().containsAll(m.entrySet());
      }
   }

   public String toString() {
      StringBuilder s = new StringBuilder();
      ObjectIterator<Float2IntMap.Entry> i = Float2IntMaps.fastIterator(this);
      int n = this.size();
      boolean first = true;
      s.append("{");

      while(n-- != 0) {
         if (first) {
            first = false;
         } else {
            s.append(", ");
         }

         Float2IntMap.Entry e = (Float2IntMap.Entry)i.next();
         s.append(String.valueOf(e.getFloatKey()));
         s.append("=>");
         s.append(String.valueOf(e.getIntValue()));
      }

      s.append("}");
      return s.toString();
   }

   public static class BasicEntry implements Float2IntMap.Entry {
      protected float key;
      protected int value;

      public BasicEntry() {
      }

      public BasicEntry(Float key, Integer value) {
         this.key = key;
         this.value = value;
      }

      public BasicEntry(float key, int value) {
         this.key = key;
         this.value = value;
      }

      public float getFloatKey() {
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
         } else if (o instanceof Float2IntMap.Entry) {
            Float2IntMap.Entry e = (Float2IntMap.Entry)o;
            return Float.floatToIntBits(this.key) == Float.floatToIntBits(e.getFloatKey()) && this.value == e.getIntValue();
         } else {
            Map.Entry<?, ?> e = (Map.Entry)o;
            Object key = e.getKey();
            if (key != null && key instanceof Float) {
               Object value = e.getValue();
               if (value != null && value instanceof Integer) {
                  return Float.floatToIntBits(this.key) == Float.floatToIntBits((Float)key) && this.value == (Integer)value;
               } else {
                  return false;
               }
            } else {
               return false;
            }
         }
      }

      public int hashCode() {
         return HashCommon.float2int(this.key) ^ this.value;
      }

      public String toString() {
         return this.key + "->" + this.value;
      }
   }

   public abstract static class BasicEntrySet extends AbstractObjectSet {
      protected final Float2IntMap map;

      public BasicEntrySet(Float2IntMap map) {
         this.map = map;
      }

      public boolean contains(Object o) {
         if (!(o instanceof Map.Entry)) {
            return false;
         } else if (o instanceof Float2IntMap.Entry) {
            Float2IntMap.Entry e = (Float2IntMap.Entry)o;
            float k = e.getFloatKey();
            return this.map.containsKey(k) && this.map.get(k) == e.getIntValue();
         } else {
            Map.Entry<?, ?> e = (Map.Entry)o;
            Object key = e.getKey();
            if (key != null && key instanceof Float) {
               float k = (Float)key;
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
         } else if (o instanceof Float2IntMap.Entry) {
            Float2IntMap.Entry e = (Float2IntMap.Entry)o;
            return this.map.remove(e.getFloatKey(), e.getIntValue());
         } else {
            Map.Entry<?, ?> e = (Map.Entry)o;
            Object key = e.getKey();
            if (key != null && key instanceof Float) {
               float k = (Float)key;
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
