package dev.ludovic.netlib.lapack;

import java.util.logging.Logger;

final class InstanceBuilder {
   private static final Logger log = Logger.getLogger(InstanceBuilder.class.getName());
   private static final LAPACK lapack;
   private static final NativeLAPACK nativeLapack = initializeNative();
   private static final JavaLAPACK javaLapack = initializeJava();

   public static LAPACK lapack() {
      return lapack;
   }

   private static NativeLAPACK initializeNative() {
      try {
         return JNILAPACK.getInstance();
      } catch (Throwable var1) {
         log.warning("Failed to load implementation from:" + JNILAPACK.class.getName());
         return null;
      }
   }

   public static NativeLAPACK nativeLapack() {
      if (nativeLapack == null) {
         throw new RuntimeException("Unable to load native implementation");
      } else {
         return nativeLapack;
      }
   }

   private static JavaLAPACK initializeJava() {
      return F2jLAPACK.getInstance();
   }

   public static JavaLAPACK javaLapack() {
      return javaLapack;
   }

   static {
      lapack = (LAPACK)(nativeLapack != null ? nativeLapack : javaLapack);
   }
}
