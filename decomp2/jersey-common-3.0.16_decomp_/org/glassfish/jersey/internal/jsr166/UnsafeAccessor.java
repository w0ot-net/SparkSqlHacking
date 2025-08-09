package org.glassfish.jersey.internal.jsr166;

import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import sun.misc.Unsafe;

class UnsafeAccessor {
   static Unsafe getUnsafe() {
      try {
         return Unsafe.getUnsafe();
      } catch (SecurityException var2) {
         try {
            return (Unsafe)AccessController.doPrivileged(() -> {
               Class<Unsafe> k = Unsafe.class;

               for(Field f : k.getDeclaredFields()) {
                  f.setAccessible(true);
                  Object x = f.get((Object)null);
                  if (k.isInstance(x)) {
                     return (Unsafe)k.cast(x);
                  }
               }

               throw new NoSuchFieldError("the Unsafe");
            });
         } catch (PrivilegedActionException e) {
            throw new RuntimeException("Could not initialize intrinsics", e.getCause());
         }
      }
   }
}
