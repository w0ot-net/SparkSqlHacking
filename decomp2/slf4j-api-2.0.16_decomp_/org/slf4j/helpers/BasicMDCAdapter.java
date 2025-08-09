package org.slf4j.helpers;

import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.slf4j.spi.MDCAdapter;

public class BasicMDCAdapter implements MDCAdapter {
   private final ThreadLocalMapOfStacks threadLocalMapOfDeques = new ThreadLocalMapOfStacks();
   private final InheritableThreadLocal inheritableThreadLocalMap = new InheritableThreadLocal() {
      protected Map childValue(Map parentValue) {
         return parentValue == null ? null : new HashMap(parentValue);
      }
   };

   public void put(String key, String val) {
      if (key == null) {
         throw new IllegalArgumentException("key cannot be null");
      } else {
         Map<String, String> map = (Map)this.inheritableThreadLocalMap.get();
         if (map == null) {
            map = new HashMap();
            this.inheritableThreadLocalMap.set(map);
         }

         map.put(key, val);
      }
   }

   public String get(String key) {
      Map<String, String> map = (Map)this.inheritableThreadLocalMap.get();
      return map != null && key != null ? (String)map.get(key) : null;
   }

   public void remove(String key) {
      Map<String, String> map = (Map)this.inheritableThreadLocalMap.get();
      if (map != null) {
         map.remove(key);
      }

   }

   public void clear() {
      Map<String, String> map = (Map)this.inheritableThreadLocalMap.get();
      if (map != null) {
         map.clear();
         this.inheritableThreadLocalMap.remove();
      }

   }

   public Set getKeys() {
      Map<String, String> map = (Map)this.inheritableThreadLocalMap.get();
      return map != null ? map.keySet() : null;
   }

   public Map getCopyOfContextMap() {
      Map<String, String> oldMap = (Map)this.inheritableThreadLocalMap.get();
      return oldMap != null ? new HashMap(oldMap) : null;
   }

   public void setContextMap(Map contextMap) {
      Map<String, String> copy = null;
      if (contextMap != null) {
         copy = new HashMap(contextMap);
      }

      this.inheritableThreadLocalMap.set(copy);
   }

   public void pushByKey(String key, String value) {
      this.threadLocalMapOfDeques.pushByKey(key, value);
   }

   public String popByKey(String key) {
      return this.threadLocalMapOfDeques.popByKey(key);
   }

   public Deque getCopyOfDequeByKey(String key) {
      return this.threadLocalMapOfDeques.getCopyOfDequeByKey(key);
   }

   public void clearDequeByKey(String key) {
      this.threadLocalMapOfDeques.clearDequeByKey(key);
   }
}
