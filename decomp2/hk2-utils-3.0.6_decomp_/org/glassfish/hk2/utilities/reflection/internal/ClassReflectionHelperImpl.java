package org.glassfish.hk2.utilities.reflection.internal;

import java.lang.reflect.Method;
import java.util.Set;
import org.glassfish.hk2.utilities.cache.Computable;
import org.glassfish.hk2.utilities.cache.HybridCacheEntry;
import org.glassfish.hk2.utilities.cache.LRUHybridCache;
import org.glassfish.hk2.utilities.reflection.ClassReflectionHelper;
import org.glassfish.hk2.utilities.reflection.MethodWrapper;

public class ClassReflectionHelperImpl implements ClassReflectionHelper {
   private final int MAX_CACHE_SIZE = 20000;
   private final LRUHybridCache postConstructCache = new LRUHybridCache(20000, new Computable() {
      public HybridCacheEntry compute(LifecycleKey key) {
         return ClassReflectionHelperImpl.this.postConstructCache.createCacheEntry(key, ClassReflectionHelperImpl.this.getPostConstructMethod(key.clazz, key.matchingClass), false);
      }
   });
   private final LRUHybridCache preDestroyCache = new LRUHybridCache(20000, new Computable() {
      public HybridCacheEntry compute(LifecycleKey key) {
         return ClassReflectionHelperImpl.this.preDestroyCache.createCacheEntry(key, ClassReflectionHelperImpl.this.getPreDestroyMethod(key.clazz, key.matchingClass), false);
      }
   });
   private final LRUHybridCache methodCache = new LRUHybridCache(20000, new Computable() {
      public HybridCacheEntry compute(Class key) {
         return ClassReflectionHelperImpl.this.methodCache.createCacheEntry(key, ClassReflectionHelperUtilities.getAllMethodWrappers(key), false);
      }
   });
   private final LRUHybridCache fieldCache = new LRUHybridCache(20000, new Computable() {
      public HybridCacheEntry compute(Class key) {
         return ClassReflectionHelperImpl.this.fieldCache.createCacheEntry(key, ClassReflectionHelperUtilities.getAllFieldWrappers(key), false);
      }
   });

   public Set getAllMethods(Class clazz) {
      return (Set)this.methodCache.compute(clazz).getValue();
   }

   public Set getAllFields(Class clazz) {
      return (Set)this.fieldCache.compute(clazz).getValue();
   }

   public Method findPostConstruct(Class clazz, Class matchingClass) throws IllegalArgumentException {
      return (Method)this.postConstructCache.compute(new LifecycleKey(clazz, matchingClass)).getValue();
   }

   public Method findPreDestroy(Class clazz, Class matchingClass) throws IllegalArgumentException {
      return (Method)this.preDestroyCache.compute(new LifecycleKey(clazz, matchingClass)).getValue();
   }

   public void clean(Class clazz) {
      while(clazz != null && !Object.class.equals(clazz)) {
         this.postConstructCache.remove(new LifecycleKey(clazz, (Class)null));
         this.preDestroyCache.remove(new LifecycleKey(clazz, (Class)null));
         this.methodCache.remove(clazz);
         this.fieldCache.remove(clazz);
         clazz = clazz.getSuperclass();
      }

   }

   public MethodWrapper createMethodWrapper(Method m) {
      return new MethodWrapperImpl(m);
   }

   public void dispose() {
      this.postConstructCache.clear();
      this.preDestroyCache.clear();
      this.methodCache.clear();
      this.fieldCache.clear();
   }

   public int size() {
      return this.postConstructCache.size() + this.preDestroyCache.size() + this.methodCache.size() + this.fieldCache.size();
   }

   private Method getPostConstructMethod(Class clazz, Class matchingClass) {
      if (clazz != null && !Object.class.equals(clazz)) {
         if (matchingClass.isAssignableFrom(clazz)) {
            Method retVal;
            try {
               retVal = clazz.getMethod("postConstruct");
            } catch (NoSuchMethodException var6) {
               retVal = null;
            }

            return retVal;
         } else {
            for(MethodWrapper wrapper : this.getAllMethods(clazz)) {
               Method m = wrapper.getMethod();
               if (ClassReflectionHelperUtilities.isPostConstruct(m)) {
                  return m;
               }
            }

            return null;
         }
      } else {
         return null;
      }
   }

   private Method getPreDestroyMethod(Class clazz, Class matchingClass) {
      if (clazz != null && !Object.class.equals(clazz)) {
         if (matchingClass.isAssignableFrom(clazz)) {
            Method retVal;
            try {
               retVal = clazz.getMethod("preDestroy");
            } catch (NoSuchMethodException var6) {
               retVal = null;
            }

            return retVal;
         } else {
            for(MethodWrapper wrapper : this.getAllMethods(clazz)) {
               Method m = wrapper.getMethod();
               if (ClassReflectionHelperUtilities.isPreDestroy(m)) {
                  return m;
               }
            }

            return null;
         }
      } else {
         return null;
      }
   }

   public String toString() {
      return "ClassReflectionHelperImpl(" + System.identityHashCode(this) + ")";
   }

   private static final class LifecycleKey {
      private final Class clazz;
      private final Class matchingClass;
      private final int hash;

      private LifecycleKey(Class clazz, Class matchingClass) {
         this.clazz = clazz;
         this.matchingClass = matchingClass;
         this.hash = clazz.hashCode();
      }

      public int hashCode() {
         return this.hash;
      }

      public boolean equals(Object o) {
         if (o == null) {
            return false;
         } else {
            return !(o instanceof LifecycleKey) ? false : this.clazz.equals(((LifecycleKey)o).clazz);
         }
      }
   }
}
