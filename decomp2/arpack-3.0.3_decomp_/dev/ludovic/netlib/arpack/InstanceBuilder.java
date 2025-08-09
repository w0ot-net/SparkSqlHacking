package dev.ludovic.netlib.arpack;

import java.util.logging.Logger;

final class InstanceBuilder {
   private static final Logger log = Logger.getLogger(InstanceBuilder.class.getName());
   private static final ARPACK arpack;
   private static final NativeARPACK nativeArpack = initializeNative();
   private static final JavaARPACK javaArpack = initializeJava();

   public static ARPACK arpack() {
      return arpack;
   }

   private static NativeARPACK initializeNative() {
      try {
         return JNIARPACK.getInstance();
      } catch (Throwable var1) {
         log.warning("Failed to load implementation from:" + JNIARPACK.class.getName());
         return null;
      }
   }

   public static NativeARPACK nativeArpack() {
      if (nativeArpack == null) {
         throw new RuntimeException("Unable to load native implementation");
      } else {
         return nativeArpack;
      }
   }

   private static JavaARPACK initializeJava() {
      return F2jARPACK.getInstance();
   }

   public static JavaARPACK javaArpack() {
      return javaArpack;
   }

   static {
      arpack = (ARPACK)(nativeArpack != null ? nativeArpack : javaArpack);
   }
}
