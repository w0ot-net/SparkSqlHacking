package org.apache.derby.shared.common.error;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;

public class ExceptionUtil {
   public static String getSQLStateFromIdentifier(String var0) {
      return var0.length() == 5 ? var0 : var0.substring(0, 5);
   }

   public static int getSeverityFromIdentifier(String var0) {
      char var1 = 0;
      switch (var0.length()) {
         case 5:
            switch (var0.charAt(0)) {
               case '0':
                  switch (var0.charAt(1)) {
                     case '1':
                        var1 = 10000;
                        return var1;
                     case '7':
                     case 'A':
                        var1 = 20000;
                        return var1;
                     case '8':
                        var1 = '鱀';
                        return var1;
                  }
               case '1':
               default:
                  return var1;
               case '2':
               case '3':
                  var1 = 20000;
                  return var1;
               case '4':
                  switch (var0.charAt(1)) {
                     case '0':
                        var1 = 30000;
                        return var1;
                     case '2':
                        var1 = 20000;
                        return var1;
                     default:
                        return var1;
                  }
            }
         default:
            switch (var0.charAt(6)) {
               case 'C':
                  var1 = '鱀';
                  break;
               case 'D':
                  var1 = '꿈';
               case 'E':
               case 'F':
               case 'G':
               case 'H':
               case 'I':
               case 'J':
               case 'K':
               case 'L':
               case 'N':
               case 'O':
               case 'P':
               case 'Q':
               case 'R':
               default:
                  break;
               case 'M':
                  var1 = '썐';
                  break;
               case 'S':
                  var1 = 20000;
                  break;
               case 'T':
                  var1 = 30000;
                  break;
               case 'U':
                  var1 = 0;
            }
      }

      return var1;
   }

   public static String dumpThreads() {
      StringWriter var0 = new StringWriter();
      PrintWriter var1 = new PrintWriter(var0, true);

      try {
         Thread.class.getMethod("getAllStackTraces");
         Class var2 = Class.forName("org.apache.derby.shared.common.sanity.ThreadDump");
         Method var3 = var2.getMethod("getStackDumpString");
         String var4 = (String)var3.invoke((Object)null, (Object[])null);
         var1.print("---------------\nStack traces for all live threads:");
         var1.println("\n" + var4);
         var1.println("---------------");
      } catch (NoSuchMethodException var5) {
         var1.println("(Skipping thread dump because it is not supported on JVM 1.4)");
      } catch (Exception var6) {
         var1.println("\nAssertFailure tried to do a thread dump, but there was an error:");
         var6.printStackTrace(var1);
      }

      return var0.toString();
   }

   public static boolean isDeferredConstraintViolation(String var0) {
      return var0.equals(getSQLStateFromIdentifier("23506.T.1")) || var0.equals(getSQLStateFromIdentifier("23514.T.1"));
   }
}
