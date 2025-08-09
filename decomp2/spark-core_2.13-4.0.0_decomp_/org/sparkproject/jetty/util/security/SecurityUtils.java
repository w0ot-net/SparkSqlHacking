package org.sparkproject.jetty.util.security;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.security.Permission;
import java.security.PrivilegedAction;

public class SecurityUtils {
   private static final MethodHandle doPrivileged = lookup();

   private static MethodHandle lookup() {
      try {
         Class<?> klass = ClassLoader.getPlatformClassLoader().loadClass("java.security.AccessController");
         MethodHandles.Lookup lookup = MethodHandles.lookup();
         return lookup.findStatic(klass, "doPrivileged", MethodType.methodType(Object.class, PrivilegedAction.class));
      } catch (Throwable var2) {
         return null;
      }
   }

   public static Object getSecurityManager() {
      try {
         return System.class.getMethod("getSecurityManager").invoke((Object)null);
      } catch (Throwable var1) {
         return null;
      }
   }

   public static void checkPermission(Permission permission) throws SecurityException {
      Object securityManager = getSecurityManager();
      if (securityManager != null) {
         try {
            securityManager.getClass().getMethod("checkPermission").invoke(securityManager, permission);
         } catch (SecurityException x) {
            throw x;
         } catch (Throwable var4) {
         }

      }
   }

   public static Object doPrivileged(PrivilegedAction action) {
      MethodHandle methodHandle = doPrivileged;
      return methodHandle == null ? action.run() : doPrivileged(methodHandle, action);
   }

   private static Object doPrivileged(MethodHandle doPrivileged, PrivilegedAction action) {
      try {
         return doPrivileged.invoke(action);
      } catch (Error | RuntimeException x) {
         throw x;
      } catch (Throwable x) {
         throw new RuntimeException(x);
      }
   }

   private SecurityUtils() {
   }
}
