package dev.ludovic.netlib.blas;

import java.util.logging.Logger;

final class InstanceBuilder {
   private static final Logger log = Logger.getLogger(InstanceBuilder.class.getName());
   private static final BLAS blas;
   private static final NativeBLAS nativeBlas = initializeNative();
   private static final JavaBLAS javaBlas = initializeJava();

   public static BLAS blas() {
      return blas;
   }

   private static NativeBLAS initializeNative() {
      try {
         return JNIBLAS.getInstance();
      } catch (Throwable var1) {
         log.warning("Failed to load implementation from:" + JNIBLAS.class.getName());
         return null;
      }
   }

   public static NativeBLAS nativeBlas() {
      if (nativeBlas == null) {
         throw new RuntimeException("Unable to load native implementation");
      } else {
         return nativeBlas;
      }
   }

   private static JavaBLAS initializeJava() {
      String[] fullVersion = System.getProperty("java.version").split("[+.\\-]+", 2);
      int major = Integer.parseInt(fullVersion[0]);
      if (major >= 16) {
         try {
            log.finest("trying to return java 16 instance");
            return VectorBLAS.getInstance();
         } catch (Throwable var3) {
            log.warning("Failed to load implementation from:" + VectorBLAS.class.getName());
         }
      }

      if (major >= 11) {
         log.finest("return java 11 instance");
         return Java11BLAS.getInstance();
      } else {
         log.finest("return java 8 instance");
         return Java8BLAS.getInstance();
      }
   }

   public static JavaBLAS javaBlas() {
      return javaBlas;
   }

   static {
      blas = (BLAS)(nativeBlas != null ? nativeBlas : javaBlas);
   }
}
