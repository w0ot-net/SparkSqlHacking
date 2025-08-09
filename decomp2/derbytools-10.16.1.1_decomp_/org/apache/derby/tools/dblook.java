package org.apache.derby.tools;

import java.io.BufferedReader;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.StringTokenizer;
import org.apache.derby.iapi.tools.i18n.LocalizedResource;
import org.apache.derby.impl.tools.dblook.DB_Alias;
import org.apache.derby.impl.tools.dblook.DB_Check;
import org.apache.derby.impl.tools.dblook.DB_GrantRevoke;
import org.apache.derby.impl.tools.dblook.DB_Index;
import org.apache.derby.impl.tools.dblook.DB_Jar;
import org.apache.derby.impl.tools.dblook.DB_Key;
import org.apache.derby.impl.tools.dblook.DB_Roles;
import org.apache.derby.impl.tools.dblook.DB_Schema;
import org.apache.derby.impl.tools.dblook.DB_Sequence;
import org.apache.derby.impl.tools.dblook.DB_Table;
import org.apache.derby.impl.tools.dblook.DB_Trigger;
import org.apache.derby.impl.tools.dblook.DB_View;
import org.apache.derby.impl.tools.dblook.Logs;

public final class dblook {
   private static final int DB2_MAX_NUMBER_OF_TABLES = 30;
   private Connection conn;
   private static PreparedStatement getColNameFromNumberQuery;
   private static HashMap schemaMap;
   private static HashMap tableIdToNameMap;
   private static String sourceDBUrl;
   private static String ddlFileName;
   private static String stmtDelimiter;
   private static boolean appendLogs;
   private static ArrayList tableList;
   private static String schemaParam;
   private static String targetSchema;
   private static boolean skipViews;
   private static boolean verbose;
   private static String sourceDBName;
   private static String lookLogName = "dblook.log";
   private static LocalizedResource langUtil;
   private static boolean sqlAuthorization;
   private static final String[] ignorableSchemaNames = new String[]{"SYSIBM", "SYS", "SYSVISUAL", "SYSCAT", "SYSFUN", "SYSPROC", "SYSSTAT", "NULLID", "SYSCS_ADMIN", "SYSCS_DIAG", "SYSCS_UTIL", "SQLJ"};

   public static void main(String[] var0) {
      try {
         new dblook(var0);
      } catch (Exception var2) {
         var2.printStackTrace();
      }

   }

   public dblook(String[] var1) throws Exception {
      langUtil = LocalizedResource.getInstance();
      this.initState();
      if (!this.parseArgs(var1)) {
         System.out.println(lookupMessage("DBLOOK_Usage"));
      } else {
         this.showVariables();
         if (!this.loadDriver()) {
            Logs.cleanup();
         } else {
            schemaMap = new HashMap();
            tableIdToNameMap = new HashMap();
            this.go();
         }
      }
   }

   private void initState() {
      sourceDBUrl = null;
      ddlFileName = null;
      stmtDelimiter = null;
      appendLogs = false;
      tableList = null;
      targetSchema = null;
      schemaParam = null;
      skipViews = false;
      verbose = false;
      sourceDBName = null;
   }

   private boolean parseArgs(String[] var1) {
      if (var1.length < 2) {
         return false;
      } else {
         int var2 = 0;

         for(int var3 = 0; var3 < var1.length; var3 = var2 + 1) {
            var2 = this.loadParam(var1, var3);
            if (var2 == -1) {
               return false;
            }
         }

         if (sourceDBUrl == null) {
            return false;
         } else {
            boolean var5 = Logs.initLogs(lookLogName, ddlFileName, appendLogs, verbose, stmtDelimiter == null ? ";" : stmtDelimiter);
            sourceDBName = this.extractDBNameFromUrl(sourceDBUrl);
            if (schemaParam != null && schemaParam.length() > 0 && schemaParam.charAt(0) != '"') {
               targetSchema = addQuotes(expandDoubleQuotes(schemaParam.toUpperCase(Locale.ENGLISH)));
            } else {
               targetSchema = addQuotes(expandDoubleQuotes(stripQuotes(schemaParam)));
            }

            return var5;
         }
      }
   }

   private int loadParam(String[] var1, int var2) {
      if (var1[var2].length() != 0 && var1[var2].charAt(0) == '-') {
         boolean var3 = var1.length > var2 + 1;
         switch (var1[var2].charAt(1)) {
            case 'a':
               if (var1[var2].equals("-append")) {
                  appendLogs = true;
                  return var2;
               }

               return -1;
            case 'd':
               if (!var3) {
                  return -1;
               } else {
                  if (var1[var2].length() == 2) {
                     ++var2;
                     sourceDBUrl = stripQuotes(var1[var2]);
                     return var2;
                  }

                  return -1;
               }
            case 'n':
               if (var1[var2].equals("-noview")) {
                  skipViews = true;
                  return var2;
               }

               return -1;
            case 'o':
               if (!var3) {
                  return -1;
               } else {
                  if (var1[var2].length() == 2 && var1[var2 + 1].length() > 0) {
                     ++var2;
                     ddlFileName = var1[var2];
                     return var2;
                  }

                  return -1;
               }
            case 't':
               if (!var3) {
                  return -1;
               } else if (var1[var2].equals("-td")) {
                  ++var2;
                  stmtDelimiter = var1[var2];
                  return var2;
               } else {
                  if (var1[var2].equals("-t")) {
                     return this.extractTableNamesFromList(var1, var2 + 1);
                  }

                  return -1;
               }
            case 'v':
               if (var1[var2].equals("-verbose")) {
                  verbose = true;
                  return var2;
               }

               return -1;
            case 'z':
               if (!var3) {
                  return -1;
               } else {
                  if (var1[var2].length() == 2) {
                     ++var2;
                     schemaParam = var1[var2];
                     return var2;
                  }

                  return -1;
               }
            default:
               return -1;
         }
      } else {
         return var2;
      }
   }

   private boolean loadDriver() {
      String var1 = System.getProperty("driver");
      if (var1 == null) {
         if (sourceDBUrl.indexOf(":net://") != -1) {
            var1 = "com.ibm.db2.jcc.DB2Driver";
         } else if (sourceDBUrl.startsWith("jdbc:derby://")) {
            var1 = "org.apache.derby.jdbc.ClientDriver";
         } else {
            var1 = "org.apache.derby.jdbc.EmbeddedDriver";
         }
      }

      try {
         Class var2 = Class.forName(var1);
         if (Driver.class.isAssignableFrom(var2)) {
            var2.getConstructor().newInstance();
            return true;
         } else {
            Logs.debug("TL_notInstanceOf", new String[]{var1, Driver.class.getName()});
            return false;
         }
      } catch (Exception var3) {
         Logs.debug(var3);
         return false;
      }
   }

   private String extractDBNameFromUrl(String var1) {
      if (var1 == null) {
         return "";
      } else {
         int var2 = var1.indexOf("jdbc:derby:");
         if (var2 == -1) {
            return "";
         } else {
            var2 = var1.indexOf("://");
            if (var2 == -1) {
               var2 = var1.indexOf("derby:") + 6;
            } else {
               var2 = var1.indexOf("/", var2 + 3) + 1;
            }

            int var3 = -1;
            if (var1.charAt(var2) == '"') {
               ++var2;
               var3 = var1.indexOf("\"", var2);
            } else {
               var3 = var1.indexOf(":", var2);
               if (var3 != -1 && (var1.charAt(var3 + 1) == '/' || var1.charAt(var3 + 1) == '\\')) {
                  var3 = var1.indexOf(":", var3 + 2);
               }

               int var4 = var1.length();
               if (var3 == -1) {
                  var3 = var1.indexOf(";", var2);
               } else {
                  var4 = var1.indexOf(";", var2);
               }

               var3 = var3 <= var4 ? var3 : var4;
            }

            if (var3 == -1) {
               var3 = var1.length();
            }

            return var1.substring(var2, var3);
         }
      }
   }

   private int extractTableNamesFromList(String[] var1, int var2) {
      int var3 = var2;
      int var4 = 0;
      tableList = new ArrayList();

      while(var3 < var1.length && (var1[var3].length() <= 0 || var1[var3].charAt(0) != '-')) {
         ++var4;
         if (var4 > 30) {
            break;
         }

         if (var1[var3].length() > 0 && var1[var3].charAt(0) == '"') {
            tableList.add(addQuotes(expandDoubleQuotes(stripQuotes(var1[var3++]))));
         } else {
            tableList.add(addQuotes(expandDoubleQuotes(var1[var3++].toUpperCase(Locale.ENGLISH))));
         }
      }

      if (tableList.size() == 0) {
         tableList = null;
      }

      return var3 - 1;
   }

   private void showVariables() {
      if (ddlFileName != null) {
         Logs.reportString("============================\n");
         Logs.reportMessage("DBLOOK_FileCreation");
         if (verbose) {
            writeVerboseOutput("DBLOOK_OutputLocation", ddlFileName);
         }
      }

      Logs.reportMessage("DBLOOK_Timestamp", (new Timestamp(System.currentTimeMillis())).toString());
      Logs.reportMessage("DBLOOK_DBName", sourceDBName);
      Logs.reportMessage("DBLOOK_DBUrl", sourceDBUrl);
      if (tableList != null) {
         Logs.reportMessage("DBLOOK_TargetTables");
      }

      if (schemaParam != null) {
         Logs.reportMessage("DBLOOK_TargetSchema", stripQuotes(schemaParam));
      }

      Logs.reportString("appendLogs: " + appendLogs + "\n");
   }

   private void go() throws Exception {
      try {
         this.conn = DriverManager.getConnection(sourceDBUrl);
         this.prepForDump();
         boolean var1 = atVersion(this.conn, 10, 6);
         boolean var2 = atVersion(this.conn, 10, 9);
         boolean var3 = atVersion(this.conn, 10, 11);
         DB_Schema.doSchemas(this.conn, tableList != null && targetSchema == null);
         if (var1) {
            DB_Sequence.doSequences(this.conn);
         }

         if (tableList == null) {
            DB_Jar.doJars(sourceDBName, this.conn, var2);
            DB_Alias.doPFAU(this.conn, var1);
         }

         DB_Table.doTables(this.conn, tableIdToNameMap);
         DB_Index.doIndexes(this.conn);
         DB_Alias.doSynonyms(this.conn);
         DB_Key.doKeys(this.conn);
         DB_Check.doChecks(this.conn);
         if (!skipViews) {
            DB_View.doViews(this.conn);
         }

         DB_Trigger.doTriggers(this.conn, var3);
         DB_Roles.doRoles(this.conn);
         DB_GrantRevoke.doAuthorizations(this.conn, var1);
         if (getColNameFromNumberQuery != null) {
            getColNameFromNumberQuery.close();
         }

         Logs.cleanup();
         return;
      } catch (SQLException var8) {
         Logs.debug(var8);
         Logs.debug(Logs.unRollExceptions(var8), (String)null);
         Logs.cleanup();
      } catch (Exception var9) {
         Logs.debug(var9);
         Logs.cleanup();
         return;
      } finally {
         if (this.conn != null) {
            this.conn.commit();
            this.conn.close();
         }

      }

   }

   private void prepForDump() throws Exception {
      this.conn.setAutoCommit(false);
      Statement var1 = this.conn.createStatement();
      var1.executeUpdate("SET SCHEMA SYS");
      getColNameFromNumberQuery = this.conn.prepareStatement("SELECT COLUMNNAME FROM SYS.SYSCOLUMNS WHERE REFERENCEID = ? AND COLUMNNUMBER = ?");
      ResultSet var2 = var1.executeQuery("SELECT T.TABLEID, T.TABLENAME, S.SCHEMANAME FROM SYS.SYSTABLES T, SYS.SYSSCHEMAS S WHERE T.TABLETYPE = 'T' AND T.SCHEMAID = S.SCHEMAID");

      while(var2.next()) {
         String var3 = addQuotes(expandDoubleQuotes(var2.getString(2)));
         String var4 = addQuotes(expandDoubleQuotes(var2.getString(3)));
         tableIdToNameMap.put(var2.getString(1), var4 + "." + var3);
      }

      var2 = var1.executeQuery("SELECT SCHEMAID, SCHEMANAME FROM SYS.SYSSCHEMAS");

      while(var2.next()) {
         schemaMap.put(var2.getString(1), addQuotes(expandDoubleQuotes(var2.getString(2))));
      }

      var2 = var1.executeQuery("VALUES SYSCS_UTIL.SYSCS_GET_DATABASE_PROPERTY('derby.database.sqlAuthorization')");
      if (var2.next()) {
         String var7 = var2.getString(1);
         if (Boolean.valueOf(var7)) {
            sqlAuthorization = true;
         }
      }

      var1.close();
   }

   public static String getColumnListFromDescription(String var0, String var1) throws SQLException {
      StringBuffer var2 = new StringBuffer();
      StringTokenizer var3 = new StringTokenizer(var1.substring(var1.indexOf("(") + 1, var1.lastIndexOf(")")), " ,", true);
      boolean var4 = true;

      while(var3.hasMoreTokens()) {
         String var5 = var3.nextToken().trim();
         if (!var5.equals("")) {
            if (var5.equals(",")) {
               var4 = false;
            } else {
               try {
                  String var6 = getColNameFromNumber(var0, Integer.parseInt(var5));
                  if (!var4) {
                     var2.append(", ");
                  }

                  var2.append(var6);
               } catch (NumberFormatException var7) {
                  var5 = var5.toUpperCase();
                  if (!var5.equals("DESC") && !var5.equals("ASC")) {
                     Logs.debug("INTERNAL ERROR: read a non-number (" + var5 + ") when a column number was expected:\n" + var1, (String)null);
                  } else {
                     var2.append(" " + var5);
                  }
               }
            }
         }
      }

      return var2.toString();
   }

   public static String getColNameFromNumber(String var0, int var1) throws SQLException {
      getColNameFromNumberQuery.setString(1, var0);
      getColNameFromNumberQuery.setInt(2, var1);
      ResultSet var2 = getColNameFromNumberQuery.executeQuery();
      if (!var2.next()) {
         Logs.debug("INTERNAL ERROR: Failed column number lookup for table " + lookupTableId(var0) + ", column " + var1, (String)null);
         var2.close();
         return "";
      } else {
         String var3 = addQuotes(expandDoubleQuotes(var2.getString(1)));
         var2.close();
         return var3;
      }
   }

   public static String addQuotes(String var0) {
      return var0 == null ? null : "\"" + var0 + "\"";
   }

   public static String addSingleQuotes(String var0) {
      return var0 == null ? null : "'" + var0 + "'";
   }

   public static String stripQuotes(String var0) {
      if (var0 == null) {
         return null;
      } else if (!var0.startsWith("'") && !var0.startsWith("\"")) {
         return var0;
      } else {
         return !var0.endsWith("'") && !var0.endsWith("\"") ? var0 : var0.substring(1, var0.length() - 1);
      }
   }

   public static boolean isExcludedTable(String var0) {
      if (var0 == null) {
         return true;
      } else {
         int var1 = var0.indexOf(".");
         if (var1 != -1) {
            if (isIgnorableSchema(var0.substring(0, var1))) {
               return true;
            }

            var0 = var0.substring(var1 + 1, var0.length());
         }

         return tableList != null && !tableList.contains(var0);
      }
   }

   public static boolean isIgnorableSchema(String var0) {
      if (targetSchema != null && !var0.equals(targetSchema)) {
         return true;
      } else {
         var0 = stripQuotes(var0);
         boolean var1 = false;
         int var2 = ignorableSchemaNames.length - 1;

         while(var2 >= 0 && !(var1 = ignorableSchemaNames[var2--].equalsIgnoreCase(var0))) {
         }

         return var1;
      }
   }

   public static boolean stringContainsTargetTable(String var0) {
      if (var0 == null) {
         return false;
      } else if (tableList == null) {
         return true;
      } else {
         int var1 = var0.length();

         for(int var2 = 0; var2 < tableList.size(); ++var2) {
            String var3 = (String)tableList.get(var2);
            var3 = expandDoubleQuotes(stripQuotes(var3));
            int var4 = var3.length();
            String var5;
            if (var3.equals(var3.toUpperCase(Locale.ENGLISH))) {
               var5 = var0.toUpperCase();
            } else {
               var5 = var0;
            }

            for(int var6 = var5.indexOf(var3); var6 != -1; var6 = var0.indexOf(var3, var6 + var4)) {
               if (!partOfWord(var0, var6, var4, var1)) {
                  if (var6 < 1 || var5.charAt(var6 - 1) != '"' || var6 + var4 >= var5.length() || var5.charAt(var6 + var4) != '"') {
                     return true;
                  }

                  if (var0.substring(var6, var6 + var4).equals(var3)) {
                     return true;
                  }
               }
            }
         }

         return false;
      }
   }

   private static boolean partOfWord(String var0, int var1, int var2, int var3) {
      boolean var4 = false;
      if (var1 > 0) {
         char var5 = var0.charAt(var1 - 1);
         var4 = var5 == '_' || Character.isLetterOrDigit(var5);
      }

      boolean var7 = false;
      if (var1 + var2 < var3) {
         char var6 = var0.charAt(var1 + var2);
         var7 = var6 == '_' || Character.isLetterOrDigit(var6);
      }

      return var4 || var7;
   }

   public static String expandDoubleQuotes(String var0) {
      if (var0 != null && var0.indexOf("\"") >= 0) {
         char[] var1 = var0.toCharArray();
         char[] var2 = new char[2 * var1.length];
         int var3 = 0;

         for(int var4 = 0; var4 < var1.length; ++var4) {
            if (var1[var4] == '"') {
               var2[var3++] = '"';
               var2[var3++] = '"';
            } else {
               var2[var3++] = var1[var4];
            }
         }

         return new String(var2, 0, var3);
      } else {
         return var0;
      }
   }

   public static String unExpandDoubleQuotes(String var0) {
      if (var0 != null && var0.indexOf("\"") >= 0) {
         char[] var1 = var0.toCharArray();
         char[] var2 = new char[var1.length];
         int var3 = 0;

         for(int var4 = 0; var4 < var1.length; ++var4) {
            if (var1[var4] == '"') {
               var2[var3++] = var1[var4];
               ++var3;
            } else {
               var2[var3++] = var1[var4];
            }
         }

         return new String(var2, 0, var3);
      } else {
         return var0;
      }
   }

   public static String lookupSchemaId(String var0) {
      return (String)schemaMap.get(var0);
   }

   public static String lookupTableId(String var0) {
      return (String)tableIdToNameMap.get(var0);
   }

   public static void writeVerboseOutput(String var0, String var1) {
      if (var1 == null) {
         System.err.println(lookupMessage(var0));
      } else {
         System.err.println(lookupMessage(var0, new String[]{var1}));
      }

   }

   public static String lookupMessage(String var0) {
      return lookupMessage(var0, (String[])null);
   }

   public static String lookupMessage(String var0, String[] var1) {
      String var2 = "";
      if (var1 == null) {
         var2 = langUtil.getTextMessage(var0);
      } else {
         switch (var1.length) {
            case 1 -> var2 = langUtil.getTextMessage(var0, var1[0]);
            case 2 -> var2 = langUtil.getTextMessage(var0, var1[0], var1[1]);
         }
      }

      return var2;
   }

   public static String removeNewlines(String var0) {
      if (var0 == null) {
         return null;
      } else {
         StringBuffer var1 = null;

         try {
            BufferedReader var2 = new BufferedReader(new StringReader(var0));

            for(String var3 = var2.readLine(); var3 != null; var3 = var2.readLine()) {
               if (var1 == null) {
                  var1 = new StringBuffer(var3);
               } else {
                  var1.append(" ");
                  var1.append(var3);
               }
            }

            return var1.toString();
         } catch (Exception var4) {
            return var0;
         }
      }
   }

   private static boolean atVersion(Connection var0, int var1, int var2) throws SQLException {
      PreparedStatement var3 = null;
      ResultSet var4 = null;

      boolean var9;
      try {
         var3 = var0.prepareStatement("values syscs_util.syscs_get_database_property('DataDictionaryVersion')");
         var4 = var3.executeQuery();
         var4.next();
         String var5 = var4.getString(1);
         int var6 = var5.indexOf(46);
         int var7 = Integer.parseInt(var5.substring(0, var6));
         int var8 = Integer.parseInt(var5.substring(var6 + 1, var5.length()));
         if (var7 <= var1) {
            if (var7 < var1) {
               var9 = false;
               return var9;
            }

            var9 = var8 >= var2;
            boolean var10 = var9;
            return var10;
         }

         var9 = true;
      } finally {
         if (var4 != null) {
            var4.close();
         }

         if (var3 != null) {
            var3.close();
         }

      }

      return var9;
   }
}
