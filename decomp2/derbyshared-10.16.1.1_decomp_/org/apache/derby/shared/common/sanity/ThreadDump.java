package org.apache.derby.shared.common.sanity;

import java.util.Map;

public class ThreadDump {
   public static String getStackDumpString() {
      StringBuffer var0 = new StringBuffer();
      Map var1 = Thread.getAllStackTraces();

      for(Map.Entry var3 : var1.entrySet()) {
         StackTraceElement[] var4 = (StackTraceElement[])var3.getValue();
         Thread var5 = (Thread)var3.getKey();
         String var10001 = var5.getName();
         var0.append("Thread name=" + var10001 + " id=" + var5.getId() + " priority=" + var5.getPriority() + " state=" + var5.getState() + " isdaemon=" + var5.isDaemon() + "\n");

         for(int var6 = 0; var6 < var4.length; ++var6) {
            var0.append("\t" + var4[var6] + "\n");
         }

         var0.append("\n");
      }

      return var0.toString();
   }
}
