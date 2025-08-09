package jodd.util.cl;

import java.util.Map;
import java.util.WeakHashMap;

public class ClassLoaderLocal {
   private final Map weakMap = new WeakHashMap();
   private Object value;
   private boolean initialized;

   protected Object initialValue() {
      return null;
   }

   public synchronized Object get() {
      ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
      if (contextClassLoader != null) {
         T value = (T)this.weakMap.get(contextClassLoader);
         if (value == null && !this.weakMap.containsKey(contextClassLoader)) {
            value = (T)this.initialValue();
            this.weakMap.put(contextClassLoader, value);
         }

         return value;
      } else {
         if (!this.initialized) {
            this.value = this.initialValue();
            this.initialized = true;
         }

         return this.value;
      }
   }

   public synchronized void set(Object value) {
      ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
      if (contextClassLoader != null) {
         this.weakMap.put(contextClassLoader, value);
      } else {
         this.value = value;
         this.initialized = true;
      }
   }

   public synchronized void remove() {
      ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
      this.weakMap.remove(contextClassLoader);
   }
}
