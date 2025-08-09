package io.vertx.core.impl;

import io.netty.util.internal.PlatformDependent;

public class Utils {
   public static String LINE_SEPARATOR = System.getProperty("line.separator");
   private static final boolean isLinux = "linux".equals(PlatformDependent.normalizedOs());
   private static final boolean isWindows = PlatformDependent.isWindows();

   public static boolean isLinux() {
      return isLinux;
   }

   public static boolean isWindows() {
      return isWindows;
   }

   public static void throwAsUnchecked(Throwable t) throws Throwable {
      throw t;
   }
}
