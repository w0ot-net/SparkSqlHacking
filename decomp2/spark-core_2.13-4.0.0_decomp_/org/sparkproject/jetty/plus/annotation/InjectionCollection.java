package org.sparkproject.jetty.plus.annotation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArraySet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InjectionCollection {
   private static final Logger LOG = LoggerFactory.getLogger(InjectionCollection.class);
   public static final String INJECTION_COLLECTION = "org.sparkproject.jetty.injectionCollection";
   private final ConcurrentMap _injectionMap = new ConcurrentHashMap();

   public void add(Injection injection) {
      if (injection == null) {
         if (LOG.isDebugEnabled()) {
            LOG.debug("Ignoring null Injection");
         }

      } else {
         String name = injection.getTargetClass().getName();
         Set<Injection> injections = (Set)this._injectionMap.get(name);
         if (injections == null) {
            injections = new CopyOnWriteArraySet();
            Set<Injection> tmp = (Set)this._injectionMap.putIfAbsent(name, injections);
            if (tmp != null) {
               injections = tmp;
            }
         }

         boolean added = injections.add(injection);
         if (LOG.isDebugEnabled()) {
            LOG.debug("Adding injection for class={} on {} added={}", new Object[]{name, injection.getTarget().getName(), added});
         }

      }
   }

   public Set getInjections(String className) {
      return className == null ? null : (Set)this._injectionMap.get(className);
   }

   public Injection getInjection(String jndiName, Class clazz, Field field) {
      if (field != null && clazz != null) {
         Set<Injection> injections = this.getInjections(clazz.getName());
         if (injections == null) {
            return null;
         } else {
            Iterator<Injection> itor = injections.iterator();
            Injection injection = null;

            while(itor.hasNext() && injection == null) {
               Injection i = (Injection)itor.next();
               if (i.isField() && field.getName().equals(i.getTarget().getName())) {
                  injection = i;
               }
            }

            return injection;
         }
      } else {
         return null;
      }
   }

   public Injection getInjection(String jndiName, Class clazz, Method method, Class paramClass) {
      if (clazz != null && method != null && paramClass != null) {
         Set<Injection> injections = this.getInjections(clazz.getName());
         if (injections == null) {
            return null;
         } else {
            Iterator<Injection> itor = injections.iterator();
            Injection injection = null;

            while(itor.hasNext() && injection == null) {
               Injection i = (Injection)itor.next();
               if (i.isMethod() && i.getTarget().getName().equals(method.getName()) && paramClass.equals(i.getParamClass())) {
                  injection = i;
               }
            }

            return injection;
         }
      } else {
         return null;
      }
   }

   public void inject(Object injectable) {
      if (injectable != null) {
         for(Class<?> clazz = injectable.getClass(); clazz != null; clazz = clazz.getSuperclass()) {
            Set<Injection> injections = (Set)this._injectionMap.get(clazz.getName());
            if (injections != null) {
               for(Injection i : injections) {
                  i.inject(injectable);
               }
            }
         }

      }
   }
}
