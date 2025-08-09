package org.apache.derby.shared.common.sanity;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;

public class AssertFailure extends RuntimeException {
   private String threadDump = this.dumpThreads();

   public AssertFailure(String var1, Throwable var2) {
      super(var1, var2);
   }

   public AssertFailure(String var1) {
      super(var1);
   }

   public AssertFailure() {
   }

   public String getThreadDump() {
      return this.threadDump;
   }

   public void printStackTrace() {
      this.printStackTrace(System.err);
   }

   public void printStackTrace(PrintStream var1) {
      super.printStackTrace(var1);
      var1.println(this.threadDump);
   }

   public void printStackTrace(PrintWriter var1) {
      super.printStackTrace(var1);
      var1.println(this.threadDump);
   }

   private boolean supportsThreadDump() {
      try {
         Thread.class.getMethod("getAllStackTraces");
         return true;
      } catch (NoSuchMethodException var2) {
         return false;
      }
   }

   private String dumpThreads() {
      if (!this.supportsThreadDump()) {
         return "(Skipping thread dump because it is not supported on JVM 1.4)";
      } else {
         StringWriter var1 = new StringWriter();
         PrintWriter var2 = new PrintWriter(var1, true);

         Method var3;
         try {
            Class var4 = Class.forName("org.apache.derby.shared.common.sanity.ThreadDump");
            var3 = var4.getMethod("getStackDumpString");
         } catch (Exception var6) {
            var2.println("Failed to load class/method required to generate a thread dump:");
            var6.printStackTrace(var2);
            return var1.toString();
         }

         try {
            String var7 = (String)var3.invoke((Object)null, (Object[])null);
            var2.print("---------------\nStack traces for all live threads:");
            var2.println("\n" + var7);
            var2.println("---------------");
         } catch (Exception var5) {
            var2.println("\nAssertFailure tried to do a thread dump, but there was an error:");
            var5.printStackTrace(var2);
         }

         return var1.toString();
      }
   }
}
