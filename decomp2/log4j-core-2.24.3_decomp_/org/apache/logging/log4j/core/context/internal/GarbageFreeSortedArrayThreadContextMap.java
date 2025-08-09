package org.apache.logging.log4j.core.context.internal;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.apache.logging.log4j.spi.ObjectThreadContextMap;
import org.apache.logging.log4j.spi.ReadOnlyThreadContextMap;
import org.apache.logging.log4j.spi.ThreadContextMap;
import org.apache.logging.log4j.util.PropertiesUtil;
import org.apache.logging.log4j.util.ReadOnlyStringMap;
import org.apache.logging.log4j.util.SortedArrayStringMap;
import org.apache.logging.log4j.util.StringMap;

public class GarbageFreeSortedArrayThreadContextMap implements ReadOnlyThreadContextMap, ObjectThreadContextMap {
   public static final String INHERITABLE_MAP = "isThreadContextMapInheritable";
   protected static final int DEFAULT_INITIAL_CAPACITY = 16;
   protected static final String PROPERTY_NAME_INITIAL_CAPACITY = "log4j2.ThreadContext.initial.capacity";
   private final int initialCapacity;
   protected final ThreadLocal localMap;

   public GarbageFreeSortedArrayThreadContextMap() {
      this(PropertiesUtil.getProperties());
   }

   GarbageFreeSortedArrayThreadContextMap(final PropertiesUtil properties) {
      this.initialCapacity = properties.getIntegerProperty("log4j2.ThreadContext.initial.capacity", 16);
      this.localMap = (ThreadLocal)(properties.getBooleanProperty("isThreadContextMapInheritable") ? new InheritableThreadLocal() {
         protected StringMap childValue(final StringMap parentValue) {
            return parentValue != null ? GarbageFreeSortedArrayThreadContextMap.this.createStringMap(parentValue) : null;
         }
      } : new ThreadLocal());
   }

   protected StringMap createStringMap() {
      return new SortedArrayStringMap(this.initialCapacity);
   }

   protected StringMap createStringMap(final ReadOnlyStringMap original) {
      return new SortedArrayStringMap(original);
   }

   private StringMap getThreadLocalMap() {
      StringMap map = (StringMap)this.localMap.get();
      if (map == null) {
         map = this.createStringMap();
         this.localMap.set(map);
      }

      return map;
   }

   public void put(final String key, final String value) {
      this.getThreadLocalMap().putValue(key, value);
   }

   public void putValue(final String key, final Object value) {
      this.getThreadLocalMap().putValue(key, value);
   }

   public void putAll(final Map values) {
      if (values != null && !values.isEmpty()) {
         StringMap map = this.getThreadLocalMap();

         for(Map.Entry entry : values.entrySet()) {
            map.putValue((String)entry.getKey(), entry.getValue());
         }

      }
   }

   public void putAllValues(final Map values) {
      if (values != null && !values.isEmpty()) {
         StringMap map = this.getThreadLocalMap();

         for(Map.Entry entry : values.entrySet()) {
            map.putValue((String)entry.getKey(), entry.getValue());
         }

      }
   }

   public String get(final String key) {
      return (String)this.getValue(key);
   }

   public Object getValue(final String key) {
      StringMap map = (StringMap)this.localMap.get();
      return map == null ? null : map.getValue(key);
   }

   public void remove(final String key) {
      StringMap map = (StringMap)this.localMap.get();
      if (map != null) {
         map.remove(key);
      }

   }

   public void removeAll(final Iterable keys) {
      StringMap map = (StringMap)this.localMap.get();
      if (map != null) {
         for(String key : keys) {
            map.remove(key);
         }
      }

   }

   public void clear() {
      StringMap map = (StringMap)this.localMap.get();
      if (map != null) {
         map.clear();
      }

   }

   public boolean containsKey(final String key) {
      StringMap map = (StringMap)this.localMap.get();
      return map != null && map.containsKey(key);
   }

   public Map getCopy() {
      StringMap map = (StringMap)this.localMap.get();
      return (Map)(map == null ? new HashMap() : map.toMap());
   }

   public StringMap getReadOnlyContextData() {
      StringMap map = (StringMap)this.localMap.get();
      if (map == null) {
         map = this.createStringMap();
         this.localMap.set(map);
      }

      return map;
   }

   public Map getImmutableMapOrNull() {
      StringMap map = (StringMap)this.localMap.get();
      return map == null ? null : Collections.unmodifiableMap(map.toMap());
   }

   public boolean isEmpty() {
      StringMap map = (StringMap)this.localMap.get();
      return map == null || map.isEmpty();
   }

   public String toString() {
      StringMap map = (StringMap)this.localMap.get();
      return map == null ? "{}" : map.toString();
   }

   public int hashCode() {
      int prime = 31;
      int result = 1;
      StringMap map = (StringMap)this.localMap.get();
      result = 31 * result + (map == null ? 0 : map.hashCode());
      return result;
   }

   public boolean equals(final Object obj) {
      if (this == obj) {
         return true;
      } else if (obj == null) {
         return false;
      } else if (!(obj instanceof ThreadContextMap)) {
         return false;
      } else {
         ThreadContextMap other = (ThreadContextMap)obj;
         Map<String, String> map = this.getImmutableMapOrNull();
         Map<String, String> otherMap = other.getImmutableMapOrNull();
         return Objects.equals(map, otherMap);
      }
   }
}
