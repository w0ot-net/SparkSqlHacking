package org.apache.logging.log4j.util;

public final class Constants {
   public static final boolean IS_WEB_APP = PropertiesUtil.getProperties().getBooleanProperty("log4j2.is.webapp", isClassAvailable("javax.servlet.Servlet") || isClassAvailable("jakarta.servlet.Servlet"));
   public static final boolean ENABLE_THREADLOCALS;
   public static final int JAVA_MAJOR_VERSION;
   public static final int MAX_REUSABLE_MESSAGE_SIZE;
   public static final String LOG4J2_DEBUG = "log4j2.debug";
   public static final Object[] EMPTY_OBJECT_ARRAY;
   public static final byte[] EMPTY_BYTE_ARRAY;

   private static int size(final String property, final int defaultValue) {
      return PropertiesUtil.getProperties().getIntegerProperty(property, defaultValue);
   }

   private static boolean isClassAvailable(final String className) {
      try {
         return LoaderUtil.loadClass(className) != null;
      } catch (Throwable var2) {
         return false;
      }
   }

   private Constants() {
   }

   private static int getMajorVersion() {
      return getMajorVersion(System.getProperty("java.version"));
   }

   static int getMajorVersion(final String version) {
      String[] parts = version.split("-|\\.", 3);

      try {
         int token = Integer.parseInt(parts[0]);
         boolean isJEP223 = token != 1;
         return isJEP223 ? token : Integer.parseInt(parts[1]);
      } catch (Exception var4) {
         return 0;
      }
   }

   static {
      ENABLE_THREADLOCALS = PropertiesUtil.getProperties().getBooleanProperty("log4j2.enable.threadlocals", !IS_WEB_APP);
      JAVA_MAJOR_VERSION = getMajorVersion();
      MAX_REUSABLE_MESSAGE_SIZE = size("log4j.maxReusableMsgSize", 518);
      EMPTY_OBJECT_ARRAY = new Object[0];
      EMPTY_BYTE_ARRAY = new byte[0];
   }
}
