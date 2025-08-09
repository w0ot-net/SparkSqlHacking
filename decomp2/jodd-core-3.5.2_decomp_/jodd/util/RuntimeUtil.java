package jodd.util;

public class RuntimeUtil {
   public static final Runtime RUNTIME = Runtime.getRuntime();

   public static String currentMethod() {
      StackTraceElement[] ste = (new Exception()).getStackTrace();
      int ndx = ste.length > 1 ? 1 : 0;
      return (new Exception()).getStackTrace()[ndx].toString();
   }

   public static long availableMemory() {
      return RUNTIME.freeMemory() + (RUNTIME.maxMemory() - RUNTIME.totalMemory());
   }

   public static float availableMemoryPercent() {
      return (float)availableMemory() * 100.0F / (float)RUNTIME.maxMemory();
   }

   public static void compactMemory() {
      try {
         byte[][] unused = new byte[128][];

         for(int i = unused.length; i-- != 0; unused[i] = new byte[2000000000]) {
         }
      } catch (OutOfMemoryError var2) {
      }

      System.gc();
   }

   public static String classLocation(Class clazz) {
      return clazz.getProtectionDomain().getCodeSource().getLocation().getPath();
   }

   public static String joddLocation() {
      return classLocation(RuntimeUtil.class);
   }
}
