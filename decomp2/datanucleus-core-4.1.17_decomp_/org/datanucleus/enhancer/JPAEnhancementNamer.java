package org.datanucleus.enhancer;

public class JPAEnhancementNamer extends JDOEnhancementNamer {
   private static JPAEnhancementNamer instance = null;
   private static final String ACN_DetachedFieldAccessException = IllegalAccessException.class.getName().replace('.', '/');
   private static final String ACN_FatalInternalException = IllegalStateException.class.getName().replace('.', '/');

   public static JPAEnhancementNamer getInstance() {
      if (instance == null) {
         instance = new JPAEnhancementNamer();
      }

      return instance;
   }

   protected JPAEnhancementNamer() {
   }

   public String getDetachedFieldAccessExceptionAsmClassName() {
      return ACN_DetachedFieldAccessException;
   }

   public String getFatalInternalExceptionAsmClassName() {
      return ACN_FatalInternalException;
   }
}
