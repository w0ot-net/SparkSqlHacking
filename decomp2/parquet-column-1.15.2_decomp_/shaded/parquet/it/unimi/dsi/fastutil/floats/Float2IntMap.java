package shaded.parquet.it.unimi.dsi.fastutil.floats;

import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.DoubleFunction;
import java.util.function.DoubleToIntFunction;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import shaded.parquet.it.unimi.dsi.fastutil.ints.IntCollection;
import shaded.parquet.it.unimi.dsi.fastutil.objects.ObjectIterator;
import shaded.parquet.it.unimi.dsi.fastutil.objects.ObjectSet;

public interface Float2IntMap extends Float2IntFunction, Map {
   int size();

   default void clear() {
      throw new UnsupportedOperationException();
   }

   void defaultReturnValue(int var1);

   int defaultReturnValue();

   ObjectSet float2IntEntrySet();

   /** @deprecated */
   @Deprecated
   default ObjectSet entrySet() {
      return this.float2IntEntrySet();
   }

   /** @deprecated */
   @Deprecated
   default Integer put(Float key, Integer value) {
      return Float2IntFunction.super.put(key, value);
   }

   /** @deprecated */
   @Deprecated
   default Integer get(Object key) {
      return Float2IntFunction.super.get(key);
   }

   /** @deprecated */
   @Deprecated
   default Integer remove(Object key) {
      return Float2IntFunction.super.remove(key);
   }

   FloatSet keySet();

   IntCollection values();

   boolean containsKey(float var1);

   /** @deprecated */
   @Deprecated
   default boolean containsKey(Object key) {
      return Float2IntFunction.super.containsKey(key);
   }

   boolean containsValue(int var1);

   /** @deprecated */
   @Deprecated
   default boolean containsValue(Object value) {
      return value == null ? false : this.containsValue((Integer)value);
   }

   default void forEach(BiConsumer consumer) {
      ObjectSet<Entry> entrySet = this.float2IntEntrySet();
      Consumer<Entry> wrappingConsumer = (entry) -> consumer.accept(entry.getFloatKey(), entry.getIntValue());
      if (entrySet instanceof FastEntrySet) {
         ((FastEntrySet)entrySet).fastForEach(wrappingConsumer);
      } else {
         entrySet.forEach(wrappingConsumer);
      }

   }

   default int getOrDefault(float key, int defaultValue) {
      int v;
      return (v = this.get(key)) == this.defaultReturnValue() && !this.containsKey(key) ? defaultValue : v;
   }

   /** @deprecated */
   @Deprecated
   default Integer getOrDefault(Object key, Integer defaultValue) {
      return (Integer)super.getOrDefault(key, defaultValue);
   }

   default int putIfAbsent(float key, int value) {
      int v = this.get(key);
      int drv = this.defaultReturnValue();
      if (v == drv && !this.containsKey(key)) {
         this.put(key, value);
         return drv;
      } else {
         return v;
      }
   }

   default boolean remove(float key, int value) {
      int curValue = this.get(key);
      if (curValue == value && (curValue != this.defaultReturnValue() || this.containsKey(key))) {
         this.remove(key);
         return true;
      } else {
         return false;
      }
   }

   default boolean replace(float key, int oldValue, int newValue) {
      int curValue = this.get(key);
      if (curValue == oldValue && (curValue != this.defaultReturnValue() || this.containsKey(key))) {
         this.put(key, newValue);
         return true;
      } else {
         return false;
      }
   }

   default int replace(float key, int value) {
      return this.containsKey(key) ? this.put(key, value) : this.defaultReturnValue();
   }

   default int computeIfAbsent(float key, DoubleToIntFunction mappingFunction) {
      Objects.requireNonNull(mappingFunction);
      int v = this.get(key);
      if (v == this.defaultReturnValue() && !this.containsKey(key)) {
         int newValue = mappingFunction.applyAsInt((double)key);
         this.put(key, newValue);
         return newValue;
      } else {
         return v;
      }
   }

   default int computeIfAbsentNullable(float key, DoubleFunction mappingFunction) {
      Objects.requireNonNull(mappingFunction);
      int v = this.get(key);
      int drv = this.defaultReturnValue();
      if (v == drv && !this.containsKey(key)) {
         Integer mappedValue = (Integer)mappingFunction.apply((double)key);
         if (mappedValue == null) {
            return drv;
         } else {
            int newValue = mappedValue;
            this.put(key, newValue);
            return newValue;
         }
      } else {
         return v;
      }
   }

   default int computeIfAbsent(float key, Float2IntFunction mappingFunction) {
      Objects.requireNonNull(mappingFunction);
      int v = this.get(key);
      int drv = this.defaultReturnValue();
      if (v == drv && !this.containsKey(key)) {
         if (!mappingFunction.containsKey(key)) {
            return drv;
         } else {
            int newValue = mappingFunction.get(key);
            this.put(key, newValue);
            return newValue;
         }
      } else {
         return v;
      }
   }

   /** @deprecated */
   @Deprecated
   default int computeIfAbsentPartial(float key, Float2IntFunction mappingFunction) {
      return this.computeIfAbsent(key, mappingFunction);
   }

   default int computeIfPresent(float key, BiFunction remappingFunction) {
      Objects.requireNonNull(remappingFunction);
      int oldValue = this.get(key);
      int drv = this.defaultReturnValue();
      if (oldValue == drv && !this.containsKey(key)) {
         return drv;
      } else {
         Integer newValue = (Integer)remappingFunction.apply(key, oldValue);
         if (newValue == null) {
            this.remove(key);
            return drv;
         } else {
            int newVal = newValue;
            this.put(key, newVal);
            return newVal;
         }
      }
   }

   default int compute(float key, BiFunction remappingFunction) {
      Objects.requireNonNull(remappingFunction);
      int oldValue = this.get(key);
      int drv = this.defaultReturnValue();
      boolean contained = oldValue != drv || this.containsKey(key);
      Integer newValue = (Integer)remappingFunction.apply(key, contained ? oldValue : null);
      if (newValue == null) {
         if (contained) {
            this.remove(key);
         }

         return drv;
      } else {
         int newVal = newValue;
         this.put(key, newVal);
         return newVal;
      }
   }

   default int merge(float key, int value, BiFunction remappingFunction) {
      Objects.requireNonNull(remappingFunction);
      int oldValue = this.get(key);
      int drv = this.defaultReturnValue();
      int newValue;
      if (oldValue == drv && !this.containsKey(key)) {
         newValue = value;
      } else {
         Integer mergedValue = (Integer)remappingFunction.apply(oldValue, value);
         if (mergedValue == null) {
            this.remove(key);
            return drv;
         }

         newValue = mergedValue;
      }

      this.put(key, newValue);
      return newValue;
   }

   default int mergeInt(float key, int value, IntBinaryOperator remappingFunction) {
      Objects.requireNonNull(remappingFunction);
      int oldValue = this.get(key);
      int drv = this.defaultReturnValue();
      int newValue = oldValue == drv && !this.containsKey(key) ? value : remappingFunction.applyAsInt(oldValue, value);
      this.put(key, newValue);
      return newValue;
   }

   default int mergeInt(float key, int value, shaded.parquet.it.unimi.dsi.fastutil.ints.IntBinaryOperator remappingFunction) {
      return this.mergeInt(key, value, (IntBinaryOperator)remappingFunction);
   }

   /** @deprecated */
   @Deprecated
   default Integer putIfAbsent(Float key, Integer value) {
      return (Integer)super.putIfAbsent(key, value);
   }

   /** @deprecated */
   @Deprecated
   default boolean remove(Object key, Object value) {
      return super.remove(key, value);
   }

   /** @deprecated */
   @Deprecated
   default boolean replace(Float key, Integer oldValue, Integer newValue) {
      return super.replace(key, oldValue, newValue);
   }

   /** @deprecated */
   @Deprecated
   default Integer replace(Float key, Integer value) {
      return (Integer)super.replace(key, value);
   }

   /** @deprecated */
   @Deprecated
   default Integer computeIfAbsent(Float key, Function mappingFunction) {
      return (Integer)super.computeIfAbsent(key, mappingFunction);
   }

   /** @deprecated */
   @Deprecated
   default Integer computeIfPresent(Float key, BiFunction remappingFunction) {
      return (Integer)super.computeIfPresent(key, remappingFunction);
   }

   /** @deprecated */
   @Deprecated
   default Integer compute(Float key, BiFunction remappingFunction) {
      return (Integer)super.compute(key, remappingFunction);
   }

   /** @deprecated */
   @Deprecated
   default Integer merge(Float key, Integer value, BiFunction remappingFunction) {
      return (Integer)super.merge(key, value, remappingFunction);
   }

   public interface FastEntrySet extends ObjectSet {
      ObjectIterator fastIterator();

      default void fastForEach(Consumer consumer) {
         this.forEach(consumer);
      }
   }

   public interface Entry extends Map.Entry {
      float getFloatKey();

      /** @deprecated */
      @Deprecated
      default Float getKey() {
         return this.getFloatKey();
      }

      int getIntValue();

      int setValue(int var1);

      /** @deprecated */
      @Deprecated
      default Integer getValue() {
         return this.getIntValue();
      }

      /** @deprecated */
      @Deprecated
      default Integer setValue(Integer value) {
         return this.setValue(value);
      }
   }
}
