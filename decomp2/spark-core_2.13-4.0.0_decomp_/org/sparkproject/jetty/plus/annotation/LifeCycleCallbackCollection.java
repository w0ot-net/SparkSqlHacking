package org.sparkproject.jetty.plus.annotation;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArraySet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LifeCycleCallbackCollection {
   private static final Logger LOG = LoggerFactory.getLogger(LifeCycleCallbackCollection.class);
   public static final String LIFECYCLE_CALLBACK_COLLECTION = "org.sparkproject.jetty.lifecyleCallbackCollection";
   private final ConcurrentMap postConstructCallbacksMap = new ConcurrentHashMap();
   private final ConcurrentMap preDestroyCallbacksMap = new ConcurrentHashMap();

   public void add(LifeCycleCallback callback) {
      if (callback == null) {
         if (LOG.isDebugEnabled()) {
            LOG.debug("Ignoring empty LifeCycleCallback");
         }

      } else {
         Map<String, Set<LifeCycleCallback>> map = null;
         if (callback instanceof PreDestroyCallback) {
            map = this.preDestroyCallbacksMap;
         } else {
            if (!(callback instanceof PostConstructCallback)) {
               throw new IllegalArgumentException("Unsupported lifecycle callback type: " + String.valueOf(callback));
            }

            map = this.postConstructCallbacksMap;
         }

         Set<LifeCycleCallback> callbacks = (Set)map.get(callback.getTargetClassName());
         if (callbacks == null) {
            callbacks = new CopyOnWriteArraySet();
            Set<LifeCycleCallback> tmp = (Set)map.putIfAbsent(callback.getTargetClassName(), callbacks);
            if (tmp != null) {
               callbacks = tmp;
            }
         }

         boolean added = callbacks.add(callback);
         if (LOG.isDebugEnabled()) {
            LOG.debug("Adding callback for class={} on method={} added={}", new Object[]{callback.getTargetClassName(), callback.getMethodName(), added});
         }

      }
   }

   public Set getPreDestroyCallbacks(Object o) {
      if (o == null) {
         return null;
      } else {
         Class<? extends Object> clazz = o.getClass();
         return (Set)this.preDestroyCallbacksMap.get(clazz.getName());
      }
   }

   public Collection getPreDestroyCallbacks() {
      Set<LifeCycleCallback> set = new HashSet();

      for(String s : this.preDestroyCallbacksMap.keySet()) {
         set.addAll((Collection)this.preDestroyCallbacksMap.get(s));
      }

      return Collections.unmodifiableCollection(set);
   }

   public Set getPostConstructCallbacks(Object o) {
      if (o == null) {
         return null;
      } else {
         Class<? extends Object> clazz = o.getClass();
         return (Set)this.postConstructCallbacksMap.get(clazz.getName());
      }
   }

   public Collection getPostConstructCallbacks() {
      Set<LifeCycleCallback> set = new HashSet();

      for(String s : this.postConstructCallbacksMap.keySet()) {
         set.addAll((Collection)this.postConstructCallbacksMap.get(s));
      }

      return Collections.unmodifiableCollection(set);
   }

   public void callPostConstructCallback(Object o) throws Exception {
      if (o != null) {
         Class<? extends Object> clazz = o.getClass();
         Set<LifeCycleCallback> callbacks = (Set)this.postConstructCallbacksMap.get(clazz.getName());
         if (callbacks != null) {
            for(LifeCycleCallback l : callbacks) {
               l.callback(o);
            }

         }
      }
   }

   public void callPreDestroyCallback(Object o) throws Exception {
      if (o != null) {
         Class<? extends Object> clazz = o.getClass();
         Set<LifeCycleCallback> callbacks = (Set)this.preDestroyCallbacksMap.get(clazz.getName());
         if (callbacks != null) {
            for(LifeCycleCallback l : callbacks) {
               l.callback(o);
            }

         }
      }
   }

   public Map getPostConstructCallbackMap() {
      return Collections.unmodifiableMap(this.postConstructCallbacksMap);
   }

   public Map getPreDestroyCallbackMap() {
      return Collections.unmodifiableMap(this.preDestroyCallbacksMap);
   }
}
