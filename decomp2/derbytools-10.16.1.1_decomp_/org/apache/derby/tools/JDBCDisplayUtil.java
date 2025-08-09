package org.apache.derby.tools;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import org.apache.derby.iapi.tools.i18n.LocalizedResource;
import org.apache.derby.impl.tools.ij.ijException;

public class JDBCDisplayUtil {
   private static final int MINWIDTH = 4;
   private static int maxWidth = 128;
   private static boolean showSelectCount = false;
   private static final int MAX_RETRIES = 0;

   public static boolean getShowSelectCount() {
      return showSelectCount;
   }

   public static void setShowSelectCount(boolean var0) {
      showSelectCount = var0;
   }

   public static void ShowException(PrintWriter var0, Throwable var1) {
      if (var1 != null) {
         if (var1 instanceof SQLException) {
            ShowSQLException(var0, (SQLException)var1);
         } else {
            var1.printStackTrace(var0);
         }

      }
   }

   public static void ShowSQLException(PrintWriter var0, SQLException var1) {
      String var2;
      if (getSystemBoolean("ij.showErrorCode")) {
         var2 = LocalizedResource.getMessage("UT_Error0", LocalizedResource.getNumber(var1.getErrorCode()));
      } else {
         var2 = "";
      }

      while(var1 != null) {
         String var3 = mapNull(var1.getSQLState(), LocalizedResource.getMessage("UT_NoSqlst"));
         String var4 = mapNull(var1.getMessage(), LocalizedResource.getMessage("UT_NoMessa"));
         var0.println(LocalizedResource.getMessage("UT_Error012", var3, var4, var2));
         doTrace((PrintWriter)var0, var1);
         var1 = var1.getNextException();
      }

   }

   public static void ShowWarnings(PrintWriter var0, Connection var1) {
      try {
         Object var2 = null;
         if (var1 != null) {
            ShowWarnings(var0, var1.getWarnings());
         }

         if (var1 != null) {
            var1.clearWarnings();
         }
      } catch (SQLException var3) {
         ShowSQLException(var0, var3);
      }

   }

   public static void ShowWarnings(PrintWriter var0, SQLWarning var1) {
      while(var1 != null) {
         String var2 = mapNull(var1.getSQLState(), LocalizedResource.getMessage("UT_NoSqlst_7"));
         String var3 = mapNull(var1.getMessage(), LocalizedResource.getMessage("UT_NoMessa_8"));
         var0.println(LocalizedResource.getMessage("UT_Warni01", var2, var3));
         var1 = var1.getNextWarning();
      }

   }

   public static void ShowWarnings(PrintWriter var0, ResultSet var1) {
      try {
         Object var2 = null;
         if (var1 != null) {
            ShowWarnings(var0, var1.getWarnings());
         }

         if (var1 != null) {
            var1.clearWarnings();
         }
      } catch (SQLException var3) {
         ShowSQLException(var0, var3);
      }

   }

   public static void ShowWarnings(PrintWriter var0, Statement var1) {
      try {
         Object var2 = null;
         if (var1 != null) {
            ShowWarnings(var0, var1.getWarnings());
         }

         if (var1 != null) {
            var1.clearWarnings();
         }
      } catch (SQLException var3) {
         ShowSQLException(var0, var3);
      }

   }

   public static void DisplayResults(PrintWriter var0, Statement var1, Connection var2) throws SQLException {
      indent_DisplayResults((PrintWriter)var0, (Statement)var1, var2, 0, (int[])null, (int[])null);
   }

   private static void indent_DisplayResults(PrintWriter var0, Statement var1, Connection var2, int var3, int[] var4, int[] var5) throws SQLException {
      checkNotNull(var1, "Statement");
      ResultSet var6 = var1.getResultSet();
      if (var6 != null) {
         indent_DisplayResults(var0, var6, var2, var3, var4, var5);
         var6.close();
      } else {
         DisplayUpdateCount(var0, var1.getUpdateCount(), var3);
      }

      ShowWarnings(var0, var1);
   }

   static void DisplayUpdateCount(PrintWriter var0, int var1, int var2) {
      if (var1 == 1) {
         indentedPrintLine(var0, var2, LocalizedResource.getMessage("UT_1RowInserUpdatDelet"));
      } else if (var1 >= 0) {
         indentedPrintLine(var0, var2, LocalizedResource.getMessage("UT_0RowsInserUpdatDelet", LocalizedResource.getNumber(var1)));
      } else {
         indentedPrintLine(var0, var2, LocalizedResource.getMessage("UT_StateExecu"));
      }

   }

   private static int[] getColumnDisplayWidths(ResultSetMetaData var0, int[] var1, boolean var2) throws SQLException {
      int var3 = var1 == null ? var0.getColumnCount() : var1.length;
      int[] var4 = new int[var3];

      for(int var5 = 0; var5 < var3; ++var5) {
         int var6 = var1 == null ? var5 + 1 : var1[var5];
         int var7 = var2 ? LocalizedResource.getInstance().getColumnDisplaySize(var0, var6) : var0.getColumnDisplaySize(var6);
         var4[var5] = Math.min(maxWidth, Math.max(var0.isNullable(var6) == 0 ? 0 : 4, var7));
      }

      return var4;
   }

   public static void DisplayMultipleResults(PrintWriter var0, List var1, Connection var2, int[] var3, int[] var4) throws SQLException {
      indent_DisplayResults((PrintWriter)var0, (List)var1, var2, 0, var3, var4);
   }

   public static void DisplayResults(PrintWriter var0, ResultSet var1, Connection var2, int[] var3, int[] var4) throws SQLException {
      indent_DisplayResults((PrintWriter)var0, (ResultSet)var1, var2, 0, var3, var4);
   }

   private static void indent_DisplayResults(PrintWriter var0, ResultSet var1, Connection var2, int var3, int[] var4, int[] var5) throws SQLException {
      ArrayList var6 = new ArrayList();
      var6.add(var1);
      indent_DisplayResults((PrintWriter)var0, (List)var6, var2, 0, var4, var5);
   }

   private static void indent_DisplayResults(PrintWriter var0, List var1, Connection var2, int var3, int[] var4, int[] var5) throws SQLException {
      ResultSetMetaData var6 = null;
      if (var1 != null && var1.size() > 0) {
         var6 = ((ResultSet)var1.get(0)).getMetaData();
      }

      checkNotNull(var6, "ResultSetMetaData");
      int var8 = 0;
      Vector var7;
      if (!var2.getAutoCommit()) {
         var7 = new Vector();
      } else {
         var7 = null;
      }

      if (var5 == null) {
         var5 = getColumnDisplayWidths(var6, var4, true);
      }

      int var9 = indent_DisplayBanner(var0, var6, var3, var4, var5);
      int var10 = 0;
      Object var11 = null;
      boolean var12 = true;

      for(int var13 = 0; var13 < var1.size(); ++var13) {
         ResultSet var17 = (ResultSet)var1.get(var13);
         var12 = true;

         while(var12) {
            try {
               var12 = var17.next();
               if (var12) {
                  DisplayRow(var0, var17, var6, var9, var7, var2, var3, var4, var5);
                  ShowWarnings(var0, var17);
                  ++var8;
               }
            } catch (SQLException var15) {
               ++var10;
               if (var10 > 0) {
                  throw var15;
               }

               ShowSQLException(var0, var15);
            }
         }
      }

      if (showSelectCount) {
         if (var8 == 1) {
            var0.println();
            indentedPrintLine(var0, var3, LocalizedResource.getMessage("UT_1RowSelec"));
         } else if (var8 >= 0) {
            var0.println();
            indentedPrintLine(var0, var3, LocalizedResource.getMessage("UT_0RowsSelec", LocalizedResource.getNumber(var8)));
         }
      }

      DisplayNestedResults(var0, var7, var2, var3);
      Object var16 = null;
   }

   private static void DisplayNestedResults(PrintWriter var0, Vector var1, Connection var2, int var3) throws SQLException {
      if (var1 != null) {
         String var4 = LocalizedResource.getMessage("UT_JDBCDisplayUtil_16");
         String var5 = "0";

         for(int var6 = 0; var6 < var1.size(); ++var6) {
            LocalizedResource.OutputWriter().println();
            String var7 = Integer.toString(var6);
            if (var7.length() > var5.length()) {
               var5 = var7;
               var4 = var4 + LocalizedResource.getMessage("UT_JDBCDisplayUtil_17");
            }

            LocalizedResource.OutputWriter().println(var4);
            LocalizedResource.OutputWriter().println(LocalizedResource.getMessage("UT_Resul0", LocalizedResource.getNumber(var6)));
            LocalizedResource.OutputWriter().println(var4);
            indent_DisplayResults((PrintWriter)var0, (ResultSet)((ResultSet)var1.elementAt(var6)), var2, var3, (int[])null, (int[])null);
         }

      }
   }

   public static void DisplayNextRow(PrintWriter var0, ResultSet var1, Connection var2) throws SQLException {
      indent_DisplayNextRow((PrintWriter)var0, var1, var2, 0, (int[])null, var1 == null ? null : getColumnDisplayWidths(var1.getMetaData(), (int[])null, true));
   }

   private static void indent_DisplayNextRow(PrintWriter var0, ResultSet var1, Connection var2, int var3, int[] var4, int[] var5) throws SQLException {
      Vector var6;
      if (!var2.getAutoCommit()) {
         var6 = new Vector();
      } else {
         var6 = null;
      }

      checkNotNull(var1, "ResultSet");
      ResultSetMetaData var7 = var1.getMetaData();
      checkNotNull(var7, "ResultSetMetaData");
      if (var1.next()) {
         int var8 = indent_DisplayBanner(var0, var7, var3, var4, var5);
         DisplayRow((PrintWriter)var0, var1, var7, var8, var6, var2, var3, (int[])null, (int[])null);
      } else {
         indentedPrintLine(var0, var3, LocalizedResource.getMessage("UT_NoCurreRow"));
      }

      ShowWarnings(var0, var1);
      DisplayNestedResults(var0, var6, var2, var3);
      Object var9 = null;
   }

   public static void DisplayCurrentRow(PrintWriter var0, ResultSet var1, Connection var2) throws SQLException {
      indent_DisplayCurrentRow((PrintWriter)var0, var1, var2, 0, (int[])null, var1 == null ? null : getColumnDisplayWidths(var1.getMetaData(), (int[])null, true));
   }

   private static void indent_DisplayCurrentRow(PrintWriter var0, ResultSet var1, Connection var2, int var3, int[] var4, int[] var5) throws SQLException {
      if (var1 == null) {
         indentedPrintLine(var0, var3, LocalizedResource.getMessage("UT_NoCurreRow_19"));
      } else {
         Vector var6;
         if (!var2.getAutoCommit()) {
            var6 = new Vector();
         } else {
            var6 = null;
         }

         ResultSetMetaData var7 = var1.getMetaData();
         checkNotNull(var7, "ResultSetMetaData");
         int var8 = indent_DisplayBanner(var0, var7, var3, var4, var5);
         DisplayRow(var0, var1, var7, var8, var6, var2, var3, var4, var5);
         ShowWarnings(var0, var1);
         DisplayNestedResults(var0, var6, var2, var3);
         Object var9 = null;
      }
   }

   public static int DisplayBanner(PrintWriter var0, ResultSetMetaData var1) throws SQLException {
      return indent_DisplayBanner((PrintWriter)var0, var1, 0, (int[])null, getColumnDisplayWidths(var1, (int[])null, true));
   }

   private static int indent_DisplayBanner(PrintWriter var0, ResultSetMetaData var1, int var2, int[] var3, int[] var4) throws SQLException {
      StringBuffer var5 = new StringBuffer();
      int var6 = var4.length;
      int var7 = var6 - 1;

      for(int var8 = 1; var8 <= var6; ++var8) {
         var7 += var4[var8 - 1];
      }

      var5.ensureCapacity(var7);

      for(int var14 = 1; var14 <= var6; ++var14) {
         int var9 = var3 == null ? var14 : var3[var14 - 1];
         if (var14 > 1) {
            var5.append('|');
         }

         String var10 = var1.getColumnLabel(var9);
         int var11 = var4[var14 - 1];
         if (var10.length() < var11) {
            var5.append(var10);

            int var12;
            for(var12 = var11 - var10.length(); var12 >= 64; var12 -= 64) {
               var5.append("                                                                ");
            }

            while(var12 >= 16) {
               var5.append("                ");
               var12 -= 16;
            }

            while(var12 >= 4) {
               var5.append("    ");
               var12 -= 4;
            }

            while(var12 > 0) {
               var5.append(' ');
               --var12;
            }
         } else if (var10.length() > var11) {
            if (var11 > 1) {
               var5.append(var10.substring(0, var11 - 1));
            }

            if (var11 > 0) {
               var5.append('&');
            }
         } else {
            var5.append(var10);
         }
      }

      var5.setLength(Math.min(var7, 1024));
      indentedPrintLine(var0, var2, var5);

      for(int var15 = 0; var15 < Math.min(var7, 1024); ++var15) {
         var5.setCharAt(var15, '-');
      }

      indentedPrintLine(var0, var2, var5);
      Object var13 = null;
      return var7;
   }

   private static void DisplayRow(PrintWriter var0, ResultSet var1, ResultSetMetaData var2, int var3, Vector var4, Connection var5, int var6, int[] var7, int[] var8) throws SQLException {
      StringBuffer var9 = new StringBuffer();
      var9.ensureCapacity(var3);
      int var10 = var8.length;

      for(int var11 = 1; var11 <= var10; ++var11) {
         int var12 = var7 == null ? var11 : var7[var11 - 1];
         if (var11 > 1) {
            var9.append('|');
         }

         String var13;
         switch (var2.getColumnType(var12)) {
            case 1111:
            case 2000:
               Object var14 = var1.getObject(var12);
               if (var14 == null) {
                  var13 = "NULL";
               } else if (var14 instanceof ResultSet && var4 != null) {
                  var13 = LocalizedResource.getMessage("UT_Resul0_20", LocalizedResource.getNumber(var4.size()));
                  var4.addElement((ResultSet)var14);
               } else {
                  try {
                     var13 = var1.getString(var12);
                  } catch (SQLException var17) {
                     var13 = var14.toString();
                  }
               }
               break;
            default:
               var13 = LocalizedResource.getInstance().getLocalizedString(var1, var2, var12);
         }

         if (var13 == null) {
            var13 = "NULL";
         }

         int var18 = var8[var11 - 1];
         if (var13.length() >= var18) {
            if (var13.length() > var18) {
               String var10000 = var13.substring(0, var18 - 1);
               var13 = var10000 + "&";
            }
         } else {
            StringBuffer var15 = new StringBuffer(var13);
            var15.ensureCapacity(var18);

            for(int var16 = var13.length(); var16 < var18; ++var16) {
               var15.append(' ');
            }

            var13 = var15.toString();
         }

         var9.append(var13);
      }

      indentedPrintLine(var0, var6, var9);
   }

   public static void checkNotNull(Object var0, String var1) {
      if (var0 == null) {
         throw ijException.objectWasNull(var1);
      }
   }

   public static String mapNull(String var0, String var1) {
      return var0 == null ? var1 : var0;
   }

   public static void doTrace(PrintWriter var0, Exception var1) {
      if (getSystemBoolean("ij.exceptionTrace")) {
         var1.printStackTrace(var0);
         var0.flush();
      }

   }

   public static void setMaxDisplayWidth(int var0) {
      maxWidth = var0;
   }

   private static void indentedPrintLine(PrintWriter var0, int var1, String var2) {
      indent(var0, var1);
      var0.println(var2);
   }

   private static void indentedPrintLine(PrintWriter var0, int var1, StringBuffer var2) {
      indent(var0, var1);
      var0.println(var2);
   }

   private static void indent(PrintWriter var0, int var1) {
      for(int var2 = 0; var2 < var1; ++var2) {
         var0.print("  ");
      }

   }

   public static void ShowException(PrintStream var0, Throwable var1) {
      if (var1 != null) {
         if (var1 instanceof SQLException) {
            ShowSQLException(var0, (SQLException)var1);
         } else {
            var1.printStackTrace(var0);
         }

      }
   }

   public static void ShowSQLException(PrintStream var0, SQLException var1) {
      String var2;
      if (getSystemBoolean("ij.showErrorCode")) {
         var2 = " (errorCode = " + var1.getErrorCode() + ")";
      } else {
         var2 = "";
      }

      while(var1 != null) {
         String var10001 = mapNull(var1.getSQLState(), "(no SQLState)");
         var0.println("ERROR " + var10001 + ": " + mapNull(var1.getMessage(), "(no message)") + var2);
         doTrace((PrintStream)var0, var1);
         var1 = var1.getNextException();
      }

   }

   public static void ShowWarnings(PrintStream var0, Connection var1) {
      try {
         Object var2 = null;
         if (var1 != null) {
            ShowWarnings(var0, var1.getWarnings());
         }

         if (var1 != null) {
            var1.clearWarnings();
         }
      } catch (SQLException var3) {
         ShowSQLException(var0, var3);
      }

   }

   public static void ShowWarnings(PrintStream var0, SQLWarning var1) {
      while(var1 != null) {
         String var10001 = mapNull(var1.getSQLState(), "(no SQLState)");
         var0.println("WARNING " + var10001 + ": " + mapNull(var1.getMessage(), "(no message)"));
         var1 = var1.getNextWarning();
      }

   }

   public static void ShowWarnings(PrintStream var0, ResultSet var1) {
      try {
         Object var2 = null;
         if (var1 != null) {
            ShowWarnings(var0, var1.getWarnings());
         }

         if (var1 != null) {
            var1.clearWarnings();
         }
      } catch (SQLException var3) {
         ShowSQLException(var0, var3);
      }

   }

   public static void ShowWarnings(PrintStream var0, Statement var1) {
      try {
         Object var2 = null;
         if (var1 != null) {
            ShowWarnings(var0, var1.getWarnings());
         }

         if (var1 != null) {
            var1.clearWarnings();
         }
      } catch (SQLException var3) {
         ShowSQLException(var0, var3);
      }

   }

   public static void DisplayResults(PrintStream var0, Statement var1, Connection var2) throws SQLException {
      indent_DisplayResults((PrintStream)var0, (Statement)var1, var2, 0, (int[])null, (int[])null);
   }

   private static void indent_DisplayResults(PrintStream var0, Statement var1, Connection var2, int var3, int[] var4, int[] var5) throws SQLException {
      checkNotNull(var1, "Statement");
      ResultSet var6 = var1.getResultSet();
      if (var6 != null) {
         indent_DisplayResults(var0, var6, var2, var3, var4, var5);
         var6.close();
      } else {
         DisplayUpdateCount(var0, var1.getUpdateCount(), var3);
      }

      ShowWarnings(var0, var1);
   }

   static void DisplayUpdateCount(PrintStream var0, int var1, int var2) {
      if (var1 == 1) {
         indentedPrintLine(var0, var2, "1 row inserted/updated/deleted");
      } else if (var1 >= 0) {
         indentedPrintLine(var0, var2, var1 + " rows inserted/updated/deleted");
      } else {
         indentedPrintLine(var0, var2, "Statement executed.");
      }

   }

   public static void DisplayResults(PrintStream var0, ResultSet var1, Connection var2) throws SQLException {
      indent_DisplayResults((PrintStream)var0, (ResultSet)var1, var2, 0, (int[])null, (int[])null);
   }

   private static void indent_DisplayResults(PrintStream var0, ResultSet var1, Connection var2, int var3, int[] var4, int[] var5) throws SQLException {
      ResultSetMetaData var6 = var1.getMetaData();
      checkNotNull(var6, "ResultSetMetaData");
      int var8 = 0;
      Vector var7;
      if (!var2.getAutoCommit()) {
         var7 = new Vector();
      } else {
         var7 = null;
      }

      if (var5 == null) {
         var5 = getColumnDisplayWidths(var6, var4, false);
      }

      int var9 = indent_DisplayBanner(var0, var6, var3, var4, var5);
      boolean var10 = true;
      int var11 = 0;

      while(var10) {
         try {
            var10 = var1.next();
            if (var10) {
               DisplayRow(var0, var1, var6, var9, var7, var2, var3, var4, var5);
               ShowWarnings(var0, var1);
               ++var8;
            }
         } catch (SQLException var13) {
            ++var11;
            if (var11 > 0) {
               throw var13;
            }

            ShowSQLException(var0, var13);
         }
      }

      if (showSelectCount) {
         if (var8 == 1) {
            var0.println();
            indentedPrintLine(var0, var3, "1 row selected");
         } else if (var8 >= 0) {
            var0.println();
            indentedPrintLine(var0, var3, var8 + " rows selected");
         }
      }

      DisplayNestedResults(var0, var7, var2, var3);
      Object var14 = null;
   }

   private static void DisplayNestedResults(PrintStream var0, Vector var1, Connection var2, int var3) throws SQLException {
      if (var1 != null) {
         String var4 = "+ ResultSet #";
         String var5 = "++++++++++++++++";
         String var6 = "0";

         for(int var7 = 0; var7 < var1.size(); ++var7) {
            System.out.println();
            String var8 = Integer.toString(var7);
            if (var8.length() > var6.length()) {
               var6 = var8;
               var5 = var5 + "+";
            }

            System.out.println(var5);
            System.out.println(var4 + var7 + " +");
            System.out.println(var5);
            indent_DisplayResults((PrintStream)var0, (ResultSet)((ResultSet)var1.elementAt(var7)), var2, var3, (int[])null, (int[])null);
         }

      }
   }

   public static void DisplayNextRow(PrintStream var0, ResultSet var1, Connection var2) throws SQLException {
      indent_DisplayNextRow((PrintStream)var0, var1, var2, 0, (int[])null, var1 == null ? null : getColumnDisplayWidths(var1.getMetaData(), (int[])null, false));
   }

   private static void indent_DisplayNextRow(PrintStream var0, ResultSet var1, Connection var2, int var3, int[] var4, int[] var5) throws SQLException {
      Vector var6;
      if (!var2.getAutoCommit()) {
         var6 = new Vector();
      } else {
         var6 = null;
      }

      checkNotNull(var1, "ResultSet");
      ResultSetMetaData var7 = var1.getMetaData();
      checkNotNull(var7, "ResultSetMetaData");
      if (var1.next()) {
         int var8 = indent_DisplayBanner((PrintStream)var0, var7, var3, (int[])null, (int[])null);
         DisplayRow(var0, var1, var7, var8, var6, var2, var3, var4, var5);
      } else {
         indentedPrintLine(var0, var3, LocalizedResource.getMessage("UT_NoCurreRow"));
      }

      ShowWarnings(var0, var1);
      DisplayNestedResults(var0, var6, var2, var3);
      Object var9 = null;
   }

   public static void DisplayCurrentRow(PrintStream var0, ResultSet var1, Connection var2) throws SQLException {
      indent_DisplayCurrentRow((PrintStream)var0, var1, var2, 0, (int[])null, var1 == null ? null : getColumnDisplayWidths(var1.getMetaData(), (int[])null, false));
   }

   private static void indent_DisplayCurrentRow(PrintStream var0, ResultSet var1, Connection var2, int var3, int[] var4, int[] var5) throws SQLException {
      if (var1 == null) {
         indentedPrintLine(var0, var3, LocalizedResource.getMessage("UT_NoCurreRow_19"));
      } else {
         Vector var6;
         if (!var2.getAutoCommit()) {
            var6 = new Vector();
         } else {
            var6 = null;
         }

         ResultSetMetaData var7 = var1.getMetaData();
         checkNotNull(var7, "ResultSetMetaData");
         int var8 = indent_DisplayBanner(var0, var7, var3, var4, var5);
         DisplayRow(var0, var1, var7, var8, var6, var2, var3, var4, var5);
         ShowWarnings(var0, var1);
         DisplayNestedResults(var0, var6, var2, var3);
         Object var9 = null;
      }
   }

   public static int DisplayBanner(PrintStream var0, ResultSetMetaData var1) throws SQLException {
      return indent_DisplayBanner((PrintStream)var0, var1, 0, (int[])null, getColumnDisplayWidths(var1, (int[])null, false));
   }

   private static int indent_DisplayBanner(PrintStream var0, ResultSetMetaData var1, int var2, int[] var3, int[] var4) throws SQLException {
      StringBuffer var5 = new StringBuffer();
      int var6 = var4.length;
      int var7 = var6 - 1;

      for(int var8 = 1; var8 <= var6; ++var8) {
         var7 += var4[var8 - 1];
      }

      var5.ensureCapacity(var7);

      for(int var15 = 1; var15 <= var6; ++var15) {
         int var9 = var3 == null ? var15 : var3[var15 - 1];
         if (var15 > 1) {
            var5.append('|');
         }

         String var10 = var1.getColumnLabel(var9);
         int var11 = var4[var15 - 1];
         if (var10.length() < var11) {
            StringBuffer var12 = new StringBuffer(var10);
            var12.ensureCapacity(var11);

            for(int var13 = var12.length() + 64; var13 <= var11; var13 += 64) {
               var12.append("                                                                ");
            }

            for(int var17 = var12.length() + 16; var17 <= var11; var17 += 16) {
               var12.append("                ");
            }

            for(int var18 = var12.length() + 4; var18 <= var11; var18 += 4) {
               var12.append("    ");
            }

            for(int var19 = var12.length(); var19 < var11; ++var19) {
               var12.append(' ');
            }

            var5.append(var12);
         } else if (var10.length() > var11) {
            if (var11 > 1) {
               var5.append(var10.substring(0, var11 - 1));
            }

            if (var11 > 0) {
               var5.append('&');
            }
         } else {
            var5.append(var10);
         }
      }

      var5.setLength(Math.min(var7, 1024));
      indentedPrintLine(var0, var2, var5);

      for(int var16 = 0; var16 < Math.min(var7, 1024); ++var16) {
         var5.setCharAt(var16, '-');
      }

      indentedPrintLine(var0, var2, var5);
      Object var14 = null;
      return var7;
   }

   private static void DisplayRow(PrintStream var0, ResultSet var1, ResultSetMetaData var2, int var3, Vector var4, Connection var5, int var6, int[] var7, int[] var8) throws SQLException {
      StringBuffer var9 = new StringBuffer();
      var9.ensureCapacity(var3);
      int var10 = var8.length;

      for(int var11 = 1; var11 <= var10; ++var11) {
         int var12 = var7 == null ? var11 : var7[var11 - 1];
         if (var11 > 1) {
            var9.append('|');
         }

         String var13;
         switch (var2.getColumnType(var12)) {
            case 1111:
            case 2000:
               Object var14 = var1.getObject(var12);
               if (var14 == null) {
                  var13 = "NULL";
               } else if (var14 instanceof ResultSet && var4 != null) {
                  var13 = "ResultSet #" + var4.size();
                  var4.addElement((ResultSet)var14);
               } else {
                  try {
                     var13 = var1.getString(var12);
                  } catch (SQLException var17) {
                     var13 = var14.toString();
                  }
               }
               break;
            default:
               var13 = var1.getString(var12);
         }

         if (var13 == null) {
            var13 = "NULL";
         }

         int var18 = var8[var11 - 1];
         if (var13.length() >= var18) {
            if (var13.length() > var18) {
               String var10000 = var13.substring(0, var18 - 1);
               var13 = var10000 + "&";
            }
         } else {
            StringBuffer var15 = new StringBuffer(var13);
            var15.ensureCapacity(var18);

            for(int var16 = var13.length(); var16 < var18; ++var16) {
               var15.append(' ');
            }

            var13 = var15.toString();
         }

         var9.append(var13);
      }

      indentedPrintLine(var0, var6, var9);
   }

   public static void doTrace(PrintStream var0, Exception var1) {
      if (getSystemBoolean("ij.exceptionTrace")) {
         var1.printStackTrace(var0);
         var0.flush();
      }

   }

   private static void indentedPrintLine(PrintStream var0, int var1, String var2) {
      indent(var0, var1);
      var0.println(var2);
   }

   private static void indentedPrintLine(PrintStream var0, int var1, StringBuffer var2) {
      indent(var0, var1);
      var0.println(var2);
   }

   private static void indent(PrintStream var0, int var1) {
      for(int var2 = 0; var2 < var1; ++var2) {
         var0.print("  ");
      }

   }

   private static boolean getSystemBoolean(String var0) {
      return Boolean.getBoolean(var0);
   }

   static {
      LocalizedResource.getInstance();
   }
}
