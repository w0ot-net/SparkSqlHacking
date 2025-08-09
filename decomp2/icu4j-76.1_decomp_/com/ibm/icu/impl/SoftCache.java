package com.ibm.icu.impl;

import java.util.concurrent.ConcurrentHashMap;

public abstract class SoftCache extends CacheBase {
   private ConcurrentHashMap map = new ConcurrentHashMap();

   public final Object getInstance(Object key, Object data) {
      Object mapValue = this.map.get(key);
      if (mapValue != null) {
         if (!(mapValue instanceof CacheValue)) {
            return mapValue;
         } else {
            CacheValue<V> cv = (CacheValue)mapValue;
            if (cv.isNull()) {
               return null;
            } else {
               V value = (V)cv.get();
               if (value != null) {
                  return value;
               } else {
                  value = (V)this.createInstance(key, data);
                  return cv.resetIfCleared(value);
               }
            }
         }
      } else {
         V value = (V)this.createInstance(key, data);
         mapValue = value != null && CacheValue.futureInstancesWillBeStrong() ? value : CacheValue.getInstance(value);
         mapValue = this.map.putIfAbsent(key, mapValue);
         if (mapValue == null) {
            return value;
         } else if (!(mapValue instanceof CacheValue)) {
            return mapValue;
         } else {
            CacheValue<V> cv = (CacheValue)mapValue;
            return cv.resetIfCleared(value);
         }
      }
   }
}
