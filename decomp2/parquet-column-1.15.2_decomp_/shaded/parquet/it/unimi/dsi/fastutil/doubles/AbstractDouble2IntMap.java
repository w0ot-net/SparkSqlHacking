package shaded.parquet.it.unimi.dsi.fastutil.doubles;

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

public abstract class AbstractDouble2IntMap extends AbstractDouble2IntFunction implements Double2IntMap, Serializable {
   private static final long serialVersionUID = -4940583368468432370L;

   protected AbstractDouble2IntMap() {
   }

   public boolean containsKey(double k) {
      ObjectIterator<Double2IntMap.Entry> i = this.double2IntEntrySet().iterator();

      while(i.hasNext()) {
         if (((Double2IntMap.Entry)i.next()).getDoubleKey() == k) {
            return true;
         }
      }

      return false;
   }

   public boolean containsValue(int v) {
      ObjectIterator<Double2IntMap.Entry> i = this.double2IntEntrySet().iterator();

      while(i.hasNext()) {
         if (((Double2IntMap.Entry)i.next()).getIntValue() == v) {
            return true;
         }
      }

      return false;
   }

   public boolean isEmpty() {
      return this.size() == 0;
   }

   public final int mergeInt(double key, int value, IntBinaryOperator remappingFunction) {
      return this.mergeInt(key, value, remappingFunction);
   }

   public DoubleSet keySet() {
      return new AbstractDoubleSet() {
         public boolean contains(double k) {
            return AbstractDouble2IntMap.this.containsKey(k);
         }

         public int size() {
            return AbstractDouble2IntMap.this.size();
         }

         public void clear() {
            AbstractDouble2IntMap.this.clear();
         }

         public DoubleIterator iterator() {
            return new DoubleIterator() {
               private final ObjectIterator i = Double2IntMaps.fastIterator(AbstractDouble2IntMap.this);

               public double nextDouble() {
                  return ((Double2IntMap.Entry)this.i.next()).getDoubleKey();
               }

               public boolean hasNext() {
                  return this.i.hasNext();
               }

               public void remove() {
                  this.i.remove();
               }

               public void forEachRemaining(java.util.function.DoubleConsumer action) {
                  this.i.forEachRemaining((entry) -> action.accept(entry.getDoubleKey()));
               }
            };
         }

         public DoubleSpliterator spliterator() {
            return DoubleSpliterators.asSpliterator(this.iterator(), Size64.sizeOf((Map)AbstractDouble2IntMap.this), 321);
         }
      };
   }

   public IntCollection values() {
      return new AbstractIntCollection() {
         public boolean contains(int k) {
            return AbstractDouble2IntMap.this.containsValue(k);
         }

         public int size() {
            return AbstractDouble2IntMap.this.size();
         }

         public void clear() {
            AbstractDouble2IntMap.this.clear();
         }

         public IntIterator iterator() {
            return new IntIterator() {
               private final ObjectIterator i = Double2IntMaps.fastIterator(AbstractDouble2IntMap.this);

               public int nextInt() {
                  return ((Double2IntMap.Entry)this.i.next()).getIntValue();
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
            return IntSpliterators.asSpliterator(this.iterator(), Size64.sizeOf((Map)AbstractDouble2IntMap.this), 320);
         }
      };
   }

   public void putAll(Map m) {
      if (m instanceof Double2IntMap) {
         ObjectIterator<Double2IntMap.Entry> i = Double2IntMaps.fastIterator((Double2IntMap)m);

         while(i.hasNext()) {
            Double2IntMap.Entry e = (Double2IntMap.Entry)i.next();
            this.put(e.getDoubleKey(), e.getIntValue());
         }
      } else {
         int n = m.size();
         Iterator<? extends Map.Entry<? extends Double, ? extends Integer>> i = m.entrySet().iterator();

         while(n-- != 0) {
            Map.Entry<? extends Double, ? extends Integer> e = (Map.Entry)i.next();
            this.put((Double)e.getKey(), (Integer)e.getValue());
         }
      }

   }

   public int hashCode() {
      int h = 0;
      int n = this.size();

      for(ObjectIterator<Double2IntMap.Entry> i = Double2IntMaps.fastIterator(this); n-- != 0; h += ((Double2IntMap.Entry)i.next()).hashCode()) {
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
         return m.size() != this.size() ? false : this.double2IntEntrySet().containsAll(m.entrySet());
      }
   }

   public String toString() {
      StringBuilder s = new StringBuilder();
      ObjectIterator<Double2IntMap.Entry> i = Double2IntMaps.fastIterator(this);
      int n = this.size();
      boolean first = true;
      s.append("{");

      while(n-- != 0) {
         if (first) {
            first = false;
         } else {
            s.append(", ");
         }

         Double2IntMap.Entry e = (Double2IntMap.Entry)i.next();
         s.append(String.valueOf(e.getDoubleKey()));
         s.append("=>");
         s.append(String.valueOf(e.getIntValue()));
      }

      s.append("}");
      return s.toString();
   }

   public static class BasicEntry implements Double2IntMap.Entry {
      protected double key;
      protected int value;

      public BasicEntry() {
      }

      public BasicEntry(Double key, Integer value) {
         this.key = key;
         this.value = value;
      }

      public BasicEntry(double key, int value) {
         this.key = key;
         this.value = value;
      }

      public double getDoubleKey() {
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
         } else if (o instanceof Double2IntMap.Entry) {
            Double2IntMap.Entry e = (Double2IntMap.Entry)o;
            return Double.doubleToLongBits(this.key) == Double.doubleToLongBits(e.getDoubleKey()) && this.value == e.getIntValue();
         } else {
            Map.Entry<?, ?> e = (Map.Entry)o;
            Object key = e.getKey();
            if (key != null && key instanceof Double) {
               Object value = e.getValue();
               if (value != null && value instanceof Integer) {
                  return Double.doubleToLongBits(this.key) == Double.doubleToLongBits((Double)key) && this.value == (Integer)value;
               } else {
                  return false;
               }
            } else {
               return false;
            }
         }
      }

      public int hashCode() {
         return HashCommon.double2int(this.key) ^ this.value;
      }

      public String toString() {
         return this.key + "->" + this.value;
      }
   }

   public abstract static class BasicEntrySet extends AbstractObjectSet {
      protected final Double2IntMap map;

      public BasicEntrySet(Double2IntMap map) {
         this.map = map;
      }

      public boolean contains(Object o) {
         if (!(o instanceof Map.Entry)) {
            return false;
         } else if (o instanceof Double2IntMap.Entry) {
            Double2IntMap.Entry e = (Double2IntMap.Entry)o;
            double k = e.getDoubleKey();
            return this.map.containsKey(k) && this.map.get(k) == e.getIntValue();
         } else {
            Map.Entry<?, ?> e = (Map.Entry)o;
            Object key = e.getKey();
            if (key != null && key instanceof Double) {
               double k = (Double)key;
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
         } else if (o instanceof Double2IntMap.Entry) {
            Double2IntMap.Entry e = (Double2IntMap.Entry)o;
            return this.map.remove(e.getDoubleKey(), e.getIntValue());
         } else {
            Map.Entry<?, ?> e = (Map.Entry)o;
            Object key = e.getKey();
            if (key != null && key instanceof Double) {
               double k = (Double)key;
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
