package org.apache.derby.iapi.db;

import java.io.PrintWriter;
import java.io.StringWriter;
import org.apache.derby.iapi.sql.compile.OptTrace;
import org.apache.derby.iapi.sql.conn.ConnectionUtil;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.impl.sql.compile.DefaultOptTrace;

public class OptimizerTrace {
   public static void setOptimizerTrace(boolean var0) {
      DefaultOptTrace var1 = var0 ? new DefaultOptTrace() : null;
      setOptimizerTracer(var1);
   }

   public static void setOptimizerTracer(OptTrace var0) {
      try {
         ConnectionUtil.getCurrentLCC().setOptimizerTracer(var0);
      } catch (Throwable var2) {
      }

   }

   public static OptTrace getOptimizerTracer() {
      try {
         return ConnectionUtil.getCurrentLCC().getOptimizerTracer();
      } catch (Throwable var1) {
         return null;
      }
   }

   public static String getOptimizerTraceOutput() {
      String var0 = null;

      try {
         LanguageConnectionContext var1 = ConnectionUtil.getCurrentLCC();
         OptTrace var2 = var1.getOptimizerTracer();
         if (var2 != null) {
            StringWriter var3 = new StringWriter();
            PrintWriter var4 = new PrintWriter(var3);
            var2.printToWriter(var4);
            var4.flush();
            var3.flush();
            var0 = var3.toString();
         }
      } catch (Throwable var5) {
      }

      return var0;
   }
}
