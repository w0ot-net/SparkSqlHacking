package org.apache.logging.log4j.core.impl;

import java.lang.reflect.Constructor;
import java.util.Map;
import org.apache.logging.log4j.core.util.Loader;
import org.apache.logging.log4j.util.IndexedStringMap;
import org.apache.logging.log4j.util.PropertiesUtil;
import org.apache.logging.log4j.util.ReadOnlyStringMap;
import org.apache.logging.log4j.util.SortedArrayStringMap;
import org.apache.logging.log4j.util.StringMap;

public class ContextDataFactory {
   private static final String CLASS_NAME = PropertiesUtil.getProperties().getStringProperty("log4j2.ContextData");
   private static final Class CACHED_CLASS;
   private static final Constructor DEFAULT_CONSTRUCTOR;
   private static final Constructor INITIAL_CAPACITY_CONSTRUCTOR;
   private static final StringMap EMPTY_STRING_MAP;

   private static Class createCachedClass(final String className) {
      if (className == null) {
         return null;
      } else {
         try {
            return Loader.loadClass(className).asSubclass(IndexedStringMap.class);
         } catch (Exception var2) {
            return null;
         }
      }
   }

   private static Constructor createDefaultConstructor(final Class cachedClass) {
      if (cachedClass == null) {
         return null;
      } else {
         try {
            return cachedClass.getConstructor();
         } catch (IllegalAccessError | NoSuchMethodException var2) {
            return null;
         }
      }
   }

   private static Constructor createInitialCapacityConstructor(final Class cachedClass) {
      if (cachedClass == null) {
         return null;
      } else {
         try {
            return cachedClass.getConstructor(Integer.TYPE);
         } catch (IllegalAccessError | NoSuchMethodException var2) {
            return null;
         }
      }
   }

   public static StringMap createContextData() {
      if (DEFAULT_CONSTRUCTOR == null) {
         return new SortedArrayStringMap();
      } else {
         try {
            return (StringMap)DEFAULT_CONSTRUCTOR.newInstance();
         } catch (Throwable var1) {
            return new SortedArrayStringMap();
         }
      }
   }

   public static StringMap createContextData(final int initialCapacity) {
      if (INITIAL_CAPACITY_CONSTRUCTOR == null) {
         return new SortedArrayStringMap(initialCapacity);
      } else {
         try {
            return (StringMap)INITIAL_CAPACITY_CONSTRUCTOR.newInstance(initialCapacity);
         } catch (Throwable var2) {
            return new SortedArrayStringMap(initialCapacity);
         }
      }
   }

   public static StringMap createContextData(final Map context) {
      StringMap contextData = createContextData(context.size());

      for(Map.Entry entry : context.entrySet()) {
         contextData.putValue((String)entry.getKey(), entry.getValue());
      }

      return contextData;
   }

   public static StringMap createContextData(final ReadOnlyStringMap readOnlyStringMap) {
      StringMap contextData = createContextData(readOnlyStringMap.size());
      contextData.putAll(readOnlyStringMap);
      return contextData;
   }

   public static StringMap emptyFrozenContextData() {
      return EMPTY_STRING_MAP;
   }

   static {
      CACHED_CLASS = createCachedClass(CLASS_NAME);
      DEFAULT_CONSTRUCTOR = createDefaultConstructor(CACHED_CLASS);
      INITIAL_CAPACITY_CONSTRUCTOR = createInitialCapacityConstructor(CACHED_CLASS);
      EMPTY_STRING_MAP = createContextData(0);
      EMPTY_STRING_MAP.freeze();
   }
}
