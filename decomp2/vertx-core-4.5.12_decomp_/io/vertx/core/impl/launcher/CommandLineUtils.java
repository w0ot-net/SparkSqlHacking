package io.vertx.core.impl.launcher;

import java.io.File;

public class CommandLineUtils {
   public static String getJar() {
      String segment = getFirstSegmentOfCommand();
      if (segment != null && segment.endsWith(".jar")) {
         return segment;
      } else {
         String classpath = System.getProperty("java.class.path");
         return !classpath.isEmpty() && !classpath.contains(File.pathSeparator) && classpath.endsWith(".jar") ? classpath : null;
      }
   }

   public static String getCommand() {
      return System.getProperty("sun.java.command");
   }

   public static String getFirstSegmentOfCommand() {
      String cmd = getCommand();
      if (cmd != null) {
         String[] segments = cmd.split(" ");
         if (segments.length >= 1) {
            return segments[0];
         }
      }

      return null;
   }
}
