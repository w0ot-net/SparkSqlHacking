package org.sparkproject.jetty.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeprecationWarning implements Decorator {
   private static final Logger LOG = LoggerFactory.getLogger(DeprecationWarning.class);

   public Object decorate(Object o) {
      if (o == null) {
         return null;
      } else {
         Class<?> clazz = o.getClass();

         try {
            Deprecated depr = (Deprecated)clazz.getAnnotation(Deprecated.class);
            if (depr != null) {
               LOG.warn("Using @Deprecated Class {}", clazz.getName());
            }
         } catch (Throwable t) {
            LOG.trace("IGNORED", t);
         }

         this.verifyIndirectTypes(clazz.getSuperclass(), clazz, "Class");

         for(Class ifaceClazz : clazz.getInterfaces()) {
            this.verifyIndirectTypes(ifaceClazz, clazz, "Interface");
         }

         return o;
      }
   }

   private void verifyIndirectTypes(Class superClazz, Class clazz, String typeName) {
      try {
         for(; superClazz != null && superClazz != Object.class; superClazz = superClazz.getSuperclass()) {
            Deprecated supDepr = (Deprecated)superClazz.getAnnotation(Deprecated.class);
            if (supDepr != null) {
               LOG.warn("Using indirect @Deprecated {} {} - (seen from {})", new Object[]{typeName, superClazz.getName(), clazz});
            }
         }
      } catch (Throwable t) {
         LOG.trace("IGNORED", t);
      }

   }

   public void destroy(Object o) {
   }
}
