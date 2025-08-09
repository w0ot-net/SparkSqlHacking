package io.netty.util.internal;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

public final class ThrowableUtil {
   private ThrowableUtil() {
   }

   public static Throwable unknownStackTrace(Throwable cause, Class clazz, String method) {
      cause.setStackTrace(new StackTraceElement[]{new StackTraceElement(clazz.getName(), method, (String)null, -1)});
      return cause;
   }

   public static String stackTraceToString(Throwable cause) {
      ByteArrayOutputStream out = new ByteArrayOutputStream();
      PrintStream pout = new PrintStream(out);
      cause.printStackTrace(pout);
      pout.flush();

      String var3;
      try {
         var3 = new String(out.toByteArray());
      } finally {
         try {
            out.close();
         } catch (IOException var10) {
         }

      }

      return var3;
   }

   public static boolean haveSuppressed() {
      return PlatformDependent.javaVersion() >= 7;
   }

   @SuppressJava6Requirement(
      reason = "Throwable addSuppressed is only available for >= 7. Has check for < 7."
   )
   public static void addSuppressed(Throwable target, Throwable suppressed) {
      if (haveSuppressed()) {
         target.addSuppressed(suppressed);
      }
   }

   public static void addSuppressedAndClear(Throwable target, List suppressed) {
      addSuppressed(target, suppressed);
      suppressed.clear();
   }

   public static void addSuppressed(Throwable target, List suppressed) {
      for(Throwable t : suppressed) {
         addSuppressed(target, t);
      }

   }

   @SuppressJava6Requirement(
      reason = "Throwable getSuppressed is only available for >= 7. Has check for < 7."
   )
   public static Throwable[] getSuppressed(Throwable source) {
      return !haveSuppressed() ? EmptyArrays.EMPTY_THROWABLES : source.getSuppressed();
   }
}
