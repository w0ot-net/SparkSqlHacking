package org.apache.derby.impl.tools.dblook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import org.apache.derby.tools.dblook;

public class Logs {
   private static PrintWriter logFile = null;
   private static PrintWriter ddlFile = null;
   private static String stmtEnd;
   private static boolean verbose;
   private static boolean atLeastOneDebug;

   public static boolean initLogs(String var0, String var1, boolean var2, boolean var3, String var4) {
      try {
         logFile = new PrintWriter(new FileOutputStream(var0, var2));
         ddlFile = var1 == null ? null : new PrintWriter(new FileOutputStream(var1, var2));
         verbose = var3;
         stmtEnd = var4;
         atLeastOneDebug = false;
         return true;
      } catch (IOException var6) {
         System.out.println("Error initializing log file(s): " + var6);
         return false;
      }
   }

   public static void report(String var0) {
      if (ddlFile == null) {
         System.out.println("-- " + var0);
      } else {
         ddlFile.println("-- " + var0);
      }

   }

   public static void reportString(String var0) {
      report(var0);
   }

   public static void reportMessage(String var0) {
      reportMessage(var0, (String[])null);
   }

   public static void reportMessage(String var0, String var1) {
      reportMessage(var0, new String[]{var1});
   }

   public static void reportMessage(String var0, String[] var1) {
      String var2 = dblook.lookupMessage(var0, var1);
      report(var2);
   }

   public static void debug(Exception var0) {
      var0.printStackTrace(logFile);
      if (verbose) {
         var0.printStackTrace(System.err);
      }

      atLeastOneDebug = true;
   }

   public static void debug(String var0, String var1) {
      String var2 = var0;
      if (var1 != null) {
         var2 = dblook.lookupMessage(var0, new String[]{var1});
      }

      logFile.println("-- **--> DEBUG: " + var2);
      if (verbose) {
         System.err.println("-- **--> DEBUG: " + var2);
      }

      atLeastOneDebug = true;
   }

   public static void debug(String var0, String[] var1) {
      String var2 = var0;
      if (var1 != null) {
         var2 = dblook.lookupMessage(var0, var1);
      }

      logFile.println("-- **--> DEBUG: " + var2);
      if (verbose) {
         System.err.println("-- **--> DEBUG: " + var2);
      }

      atLeastOneDebug = true;
   }

   public static String unRollExceptions(SQLException var0) {
      String var1 = var0.getMessage() + "\n";
      return var0.getNextException() != null ? var1 + unRollExceptions(var0.getNextException()) : var1;
   }

   public static void writeToNewDDL(String var0) {
      if (ddlFile == null) {
         System.out.print(var0);
      } else {
         ddlFile.print(var0);
      }

   }

   public static void writeStmtEndToNewDDL() {
      if (ddlFile == null) {
         System.out.println(stmtEnd);
      } else {
         ddlFile.println(stmtEnd);
      }

   }

   public static void writeNewlineToNewDDL() {
      if (ddlFile == null) {
         System.out.println();
      } else {
         ddlFile.println();
      }

   }

   public static boolean cleanup() {
      try {
         if (atLeastOneDebug) {
            dblook.writeVerboseOutput("DBLOOK_AtLeastOneDebug", (String)null);
         }

         logFile.close();
         if (ddlFile != null) {
            ddlFile.close();
         }

         return true;
      } catch (Exception var1) {
         System.out.println("Error releasing resources: " + var1);
         return false;
      }
   }
}
