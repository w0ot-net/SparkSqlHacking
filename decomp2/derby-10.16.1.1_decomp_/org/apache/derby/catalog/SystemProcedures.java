package org.apache.derby.catalog;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Random;
import java.util.StringTokenizer;
import org.apache.derby.iapi.db.ConsistencyChecker;
import org.apache.derby.iapi.db.Factory;
import org.apache.derby.iapi.jdbc.InternalDriver;
import org.apache.derby.iapi.security.Securable;
import org.apache.derby.iapi.security.SecurityUtil;
import org.apache.derby.iapi.services.cache.CacheManager;
import org.apache.derby.iapi.services.monitor.ModuleFactory;
import org.apache.derby.iapi.services.monitor.Monitor;
import org.apache.derby.iapi.services.property.PropertyUtil;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.conn.Authorizer;
import org.apache.derby.iapi.sql.conn.ConnectionUtil;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.dictionary.DataDescriptorGenerator;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.PasswordHasher;
import org.apache.derby.iapi.sql.dictionary.SchemaDescriptor;
import org.apache.derby.iapi.sql.dictionary.TableDescriptor;
import org.apache.derby.iapi.sql.dictionary.TupleDescriptor;
import org.apache.derby.iapi.sql.dictionary.UserDescriptor;
import org.apache.derby.iapi.sql.execute.RunTimeStatistics;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.iapi.util.IdUtil;
import org.apache.derby.iapi.util.StringUtil;
import org.apache.derby.impl.jdbc.EmbedDatabaseMetaData;
import org.apache.derby.impl.jdbc.Util;
import org.apache.derby.impl.load.Export;
import org.apache.derby.impl.load.Import;
import org.apache.derby.impl.sql.catalog.XPLAINResultSetDescriptor;
import org.apache.derby.impl.sql.catalog.XPLAINResultSetTimingsDescriptor;
import org.apache.derby.impl.sql.catalog.XPLAINScanPropsDescriptor;
import org.apache.derby.impl.sql.catalog.XPLAINSortPropsDescriptor;
import org.apache.derby.impl.sql.catalog.XPLAINStatementDescriptor;
import org.apache.derby.impl.sql.catalog.XPLAINStatementTimingsDescriptor;
import org.apache.derby.impl.sql.catalog.XPLAINTableDescriptor;
import org.apache.derby.impl.sql.execute.JarUtil;
import org.apache.derby.shared.common.error.MessageUtils;
import org.apache.derby.shared.common.error.PublicAPI;
import org.apache.derby.shared.common.error.StandardException;

public class SystemProcedures {
   private static final int SQL_BEST_ROWID = 1;
   private static final int SQL_ROWVER = 2;
   private static final String DRIVER_TYPE_OPTION = "DATATYPE";
   private static final String ODBC_DRIVER_OPTION = "'ODBC'";

   public static void SQLCAMESSAGE(int var0, short var1, String var2, String var3, int var4, int var5, int var6, int var7, int var8, int var9, String var10, String var11, String var12, String var13, String[] var14, int[] var15) {
      int var16 = 1;

      for(int var17 = 0; var2.indexOf(MessageUtils.SQLERRMC_MESSAGE_DELIMITER, var17) != -1; ++var16) {
         var17 = var2.indexOf(MessageUtils.SQLERRMC_MESSAGE_DELIMITER, var17) + MessageUtils.SQLERRMC_MESSAGE_DELIMITER.length();
      }

      if (var16 == 1) {
         MessageUtils.getLocalizedMessage(var0, var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15);
      } else {
         int var22 = 0;
         String[] var20 = new String[2];

         for(int var21 = 0; var21 < var16; ++var21) {
            int var18 = var2.indexOf(MessageUtils.SQLERRMC_MESSAGE_DELIMITER, var22);
            String var19;
            if (var21 == var16 - 1) {
               var19 = var2.substring(var22);
            } else {
               var19 = var2.substring(var22, var18);
            }

            if (var21 > 0) {
               var11 = var19.substring(0, 5);
               var19 = var19.substring(6);
               var14[0] = var14[0] + " SQLSTATE: " + var11 + ": ";
            }

            MessageUtils.getLocalizedMessage(var0, (short)var19.length(), var19, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var20, var15);
            if (var15[0] == 0) {
               if (var21 == 0) {
                  var14[0] = var20[0];
               } else {
                  var14[0] = var14[0] + var20[0];
               }
            }

            var22 = var18 + MessageUtils.SQLERRMC_MESSAGE_DELIMITER.length();
         }
      }

   }

   private static Connection getDefaultConn() throws SQLException {
      InternalDriver var0 = InternalDriver.activeDriver();
      if (var0 != null) {
         Connection var1 = var0.connect("jdbc:default:connection", (Properties)null, 0);
         if (var1 != null) {
            return var1;
         }
      }

      throw Util.noCurrentConnection();
   }

   private static DatabaseMetaData getDMD() throws SQLException {
      Connection var0 = getDefaultConn();
      return var0.getMetaData();
   }

   public static void SQLPROCEDURES(String var0, String var1, String var2, String var3, ResultSet[] var4) throws SQLException {
      var4[0] = isForODBC(var3) ? ((EmbedDatabaseMetaData)getDMD()).getProceduresForODBC(var0, var1, var2) : getDMD().getProcedures(var0, var1, var2);
   }

   public static void SQLFUNCTIONS(String var0, String var1, String var2, String var3, ResultSet[] var4) throws SQLException {
      var4[0] = ((EmbedDatabaseMetaData)getDMD()).getFunctions(var0, var1, var2);
   }

   public static void SQLTABLES(String var0, String var1, String var2, String var3, String var4, ResultSet[] var5) throws SQLException {
      String var6 = getOption("GETCATALOGS", var4);
      if (var6 != null && var6.trim().equals("1")) {
         var5[0] = getDMD().getCatalogs();
      } else {
         var6 = getOption("GETTABLETYPES", var4);
         if (var6 != null && var6.trim().equals("1")) {
            var5[0] = getDMD().getTableTypes();
         } else {
            var6 = getOption("GETSCHEMAS", var4);
            if (var6 != null) {
               var6 = var6.trim();
               if (var6.equals("1")) {
                  var5[0] = getDMD().getSchemas();
                  return;
               }

               if (var6.equals("2")) {
                  EmbedDatabaseMetaData var13 = (EmbedDatabaseMetaData)getDMD();
                  var5[0] = var13.getSchemas(var0, var1);
                  return;
               }
            }

            String[] var7 = null;
            if (var3 != null) {
               StringTokenizer var8 = new StringTokenizer(var3, "',");
               var7 = new String[var8.countTokens()];

               for(int var9 = 0; var8.hasMoreTokens(); ++var9) {
                  var7[var9] = var8.nextToken();
               }
            }

            var5[0] = getDMD().getTables(var0, var1, var2, var7);
         }
      }
   }

   public static void SQLFOREIGNKEYS(String var0, String var1, String var2, String var3, String var4, String var5, String var6, ResultSet[] var7) throws SQLException {
      String var8 = getOption("EXPORTEDKEY", var6);
      String var9 = getOption("IMPORTEDKEY", var6);
      if (var9 != null && var9.trim().equals("1")) {
         var7[0] = getDMD().getImportedKeys(var3, var4, var5);
      } else if (var8 != null && var8.trim().equals("1")) {
         var7[0] = getDMD().getExportedKeys(var0, var1, var2);
      } else {
         var7[0] = isForODBC(var6) ? ((EmbedDatabaseMetaData)getDMD()).getCrossReferenceForODBC(var0, var1, var2, var3, var4, var5) : getDMD().getCrossReference(var0, var1, var2, var3, var4, var5);
      }

   }

   private static String getOption(String var0, String var1) {
      if (var1 == null) {
         return null;
      } else {
         int var2 = var1.lastIndexOf(var0);
         if (var2 < 0) {
            return null;
         } else {
            int var3 = var1.indexOf(61, var2);
            if (var3 < 0) {
               return null;
            } else {
               int var4 = var1.indexOf(59, var3);
               return var4 < 0 ? var1.substring(var3 + 1) : var1.substring(var3 + 1, var4);
            }
         }
      }
   }

   public static void SQLPROCEDURECOLS(String var0, String var1, String var2, String var3, String var4, ResultSet[] var5) throws SQLException {
      var5[0] = isForODBC(var4) ? ((EmbedDatabaseMetaData)getDMD()).getProcedureColumnsForODBC(var0, var1, var2, var3) : getDMD().getProcedureColumns(var0, var1, var2, var3);
   }

   public static void SQLFUNCTIONPARAMS(String var0, String var1, String var2, String var3, String var4, ResultSet[] var5) throws SQLException {
      var5[0] = ((EmbedDatabaseMetaData)getDMD()).getFunctionColumns(var0, var1, var2, var3);
   }

   public static void SQLCOLUMNS(String var0, String var1, String var2, String var3, String var4, ResultSet[] var5) throws SQLException {
      var5[0] = isForODBC(var4) ? ((EmbedDatabaseMetaData)getDMD()).getColumnsForODBC(var0, var1, var2, var3) : getDMD().getColumns(var0, var1, var2, var3);
   }

   public static void SQLCOLPRIVILEGES(String var0, String var1, String var2, String var3, String var4, ResultSet[] var5) throws SQLException {
      var5[0] = getDMD().getColumnPrivileges(var0, var1, var2, var3);
   }

   public static void SQLTABLEPRIVILEGES(String var0, String var1, String var2, String var3, ResultSet[] var4) throws SQLException {
      var4[0] = getDMD().getTablePrivileges(var0, var1, var2);
   }

   public static void SQLPRIMARYKEYS(String var0, String var1, String var2, String var3, ResultSet[] var4) throws SQLException {
      var4[0] = getDMD().getPrimaryKeys(var0, var1, var2);
   }

   public static void SQLGETTYPEINFO(short var0, String var1, ResultSet[] var2) throws SQLException {
      var2[0] = isForODBC(var1) ? ((EmbedDatabaseMetaData)getDMD()).getTypeInfoForODBC() : getDMD().getTypeInfo();
   }

   public static void SQLSTATISTICS(String var0, String var1, String var2, short var3, short var4, String var5, ResultSet[] var6) throws SQLException {
      boolean var7 = var3 == 0;
      boolean var8 = var4 == 1;
      var6[0] = isForODBC(var5) ? ((EmbedDatabaseMetaData)getDMD()).getIndexInfoForODBC(var0, var1, var2, var7, var8) : getDMD().getIndexInfo(var0, var1, var2, var7, var8);
   }

   public static void SQLSPECIALCOLUMNS(short var0, String var1, String var2, String var3, short var4, short var5, String var6, ResultSet[] var7) throws SQLException {
      boolean var8 = var5 == 1;
      if (var0 == 1) {
         var7[0] = isForODBC(var6) ? ((EmbedDatabaseMetaData)getDMD()).getBestRowIdentifierForODBC(var1, var2, var3, var4, var8) : getDMD().getBestRowIdentifier(var1, var2, var3, var4, var8);
      } else {
         var7[0] = isForODBC(var6) ? ((EmbedDatabaseMetaData)getDMD()).getVersionColumnsForODBC(var1, var2, var3) : getDMD().getVersionColumns(var1, var2, var3);
      }

   }

   public static void SQLUDTS(String var0, String var1, String var2, String var3, String var4, ResultSet[] var5) throws SQLException {
      int[] var6 = null;
      if (var3 != null && var3.length() > 0) {
         StringTokenizer var7 = new StringTokenizer(var3, " \t\n\t,");
         int var8 = var7.countTokens();
         var6 = new int[var8];
         String var9 = "";

         try {
            for(int var10 = 0; var10 < var8; ++var10) {
               var9 = var7.nextToken();
               var6[var10] = Integer.parseInt(var9);
            }
         } catch (NumberFormatException var11) {
            throw new SQLException("Invalid type, " + var9 + ", passed to getUDTs.");
         } catch (NoSuchElementException var12) {
            throw new SQLException("Internal failure: NoSuchElementException in getUDTs.");
         }
      }

      var5[0] = getDMD().getUDTs(var0, var1, var2, var6);
   }

   public static void METADATA(ResultSet[] var0) throws SQLException {
      var0[0] = ((EmbedDatabaseMetaData)getDMD()).getClientCachedMetaData();
   }

   private static boolean isForODBC(String var0) {
      String var1 = getOption("DATATYPE", var0);
      return var1 != null && var1.toUpperCase().equals("'ODBC'");
   }

   public static void SYSCS_SET_DATABASE_PROPERTY(String var0, String var1) throws SQLException {
      setDatabaseProperty(var0, var1, Securable.SET_DATABASE_PROPERTY);
   }

   private static void setDatabaseProperty(String var0, String var1, Securable var2) throws SQLException {
      LanguageConnectionContext var3 = ConnectionUtil.getCurrentLCC();

      try {
         if (var2 != null) {
            SecurityUtil.authorize(var2);
         }

         Authorizer var4 = var3.getAuthorizer();
         var4.authorize((Activation)null, 5);
         TransactionController var5 = var3.getTransactionExecute();
         var5.setProperty(var0, var1, false);
      } catch (StandardException var6) {
         throw PublicAPI.wrapStandardException(var6);
      }
   }

   public static String SYSCS_GET_DATABASE_PROPERTY(String var0) throws SQLException {
      return getProperty(var0, Securable.GET_DATABASE_PROPERTY);
   }

   private static String getProperty(String var0, Securable var1) throws SQLException {
      LanguageConnectionContext var2 = ConnectionUtil.getCurrentLCC();

      try {
         if (var1 != null) {
            SecurityUtil.authorize(var1);
         }

         return PropertyUtil.getDatabaseProperty(var2.getTransactionExecute(), var0);
      } catch (StandardException var4) {
         throw PublicAPI.wrapStandardException(var4);
      }
   }

   public static void SYSCS_UPDATE_STATISTICS(String var0, String var1, String var2) throws SQLException {
      StringBuffer var3 = new StringBuffer();
      var3.append("alter table ");
      var3.append(basicSchemaTableValidation(var0, var1));
      if (var2 != null && var2.length() == 0) {
         throw PublicAPI.wrapStandardException(StandardException.newException("42X65", new Object[]{var2}));
      } else {
         if (var2 == null) {
            var3.append(" all update statistics ");
         } else {
            var3.append(" update statistics " + IdUtil.normalToDelimited(var2));
         }

         Connection var4 = getDefaultConn();
         PreparedStatement var5 = var4.prepareStatement(var3.toString());
         var5.executeUpdate();
         var5.close();
         var4.close();
      }
   }

   public static void SYSCS_DROP_STATISTICS(String var0, String var1, String var2) throws SQLException {
      StringBuffer var3 = new StringBuffer();
      var3.append("alter table ");
      var3.append(basicSchemaTableValidation(var0, var1));
      if (var2 != null && var2.length() == 0) {
         throw PublicAPI.wrapStandardException(StandardException.newException("42X65", new Object[]{var2}));
      } else {
         if (var2 == null) {
            var3.append(" all drop statistics ");
         } else {
            var3.append(" statistics drop " + IdUtil.normalToDelimited(var2));
         }

         Connection var4 = getDefaultConn();
         PreparedStatement var5 = var4.prepareStatement(var3.toString());
         var5.executeUpdate();
         var5.close();
         var4.close();
      }
   }

   private static String basicSchemaTableValidation(String var0, String var1) throws SQLException {
      if (var0 != null && var0.length() == 0) {
         throw PublicAPI.wrapStandardException(StandardException.newException("42Y07", new Object[]{var0}));
      } else if (var1 != null && var1.length() != 0) {
         return IdUtil.mkQualifiedName(var0, var1);
      } else {
         throw PublicAPI.wrapStandardException(StandardException.newException("42X05", new Object[]{var1}));
      }
   }

   public static void SYSCS_COMPRESS_TABLE(String var0, String var1, short var2) throws SQLException {
      StringBuffer var3 = new StringBuffer();
      var3.append("alter table ");
      var3.append(basicSchemaTableValidation(var0, var1));
      var3.append(" compress" + (var2 != 0 ? " sequential" : ""));
      Connection var4 = getDefaultConn();
      PreparedStatement var5 = var4.prepareStatement(var3.toString());
      var5.executeUpdate();
      var5.close();
      var4.close();
   }

   public static void SYSCS_FREEZE_DATABASE() throws SQLException {
      Factory.getDatabaseOfConnection().freeze();
   }

   public static void SYSCS_UNFREEZE_DATABASE() throws SQLException {
      Factory.getDatabaseOfConnection().unfreeze();
   }

   public static void SYSCS_CHECKPOINT_DATABASE() throws SQLException {
      Factory.getDatabaseOfConnection().checkpoint();
   }

   public static void SYSCS_BACKUP_DATABASE(String var0) throws SQLException {
      Factory.getDatabaseOfConnection().backup(var0, true);
   }

   public static void SYSCS_BACKUP_DATABASE_NOWAIT(String var0) throws SQLException {
      Factory.getDatabaseOfConnection().backup(var0, false);
   }

   public static void SYSCS_BACKUP_DATABASE_AND_ENABLE_LOG_ARCHIVE_MODE(String var0, short var1) throws SQLException {
      Factory.getDatabaseOfConnection().backupAndEnableLogArchiveMode(var0, var1 != 0, true);
   }

   public static void SYSCS_BACKUP_DATABASE_AND_ENABLE_LOG_ARCHIVE_MODE_NOWAIT(String var0, short var1) throws SQLException {
      Factory.getDatabaseOfConnection().backupAndEnableLogArchiveMode(var0, var1 != 0, false);
   }

   public static void SYSCS_DISABLE_LOG_ARCHIVE_MODE(short var0) throws SQLException {
      Factory.getDatabaseOfConnection().disableLogArchiveMode(var0 != 0);
   }

   public static void SYSCS_SET_RUNTIMESTATISTICS(short var0) throws SQLException {
      ConnectionUtil.getCurrentLCC().setRunTimeStatisticsMode(var0 != 0);
   }

   public static void SYSCS_SET_STATISTICS_TIMING(short var0) throws SQLException {
      ConnectionUtil.getCurrentLCC().setStatisticsTiming(var0 != 0);
   }

   public static int SYSCS_CHECK_TABLE(String var0, String var1) throws SQLException {
      boolean var2 = ConsistencyChecker.checkTable(var0, var1);
      return var2 ? 1 : 0;
   }

   public static void SYSCS_INPLACE_COMPRESS_TABLE(String var0, String var1, short var2, short var3, short var4) throws SQLException {
      LanguageConnectionContext var5 = ConnectionUtil.getCurrentLCC();
      TransactionController var6 = var5.getTransactionExecute();

      try {
         DataDictionary var7 = var5.getDataDictionary();
         SchemaDescriptor var8 = var7.getSchemaDescriptor(var0, var6, true);
         TableDescriptor var9 = var7.getTableDescriptor(var1, var8, var6);
         if (var9 != null && var9.getTableType() == 5) {
            return;
         }
      } catch (StandardException var12) {
         throw PublicAPI.wrapStandardException(var12);
      }

      String var13 = IdUtil.normalToDelimited(var0);
      String var14 = IdUtil.normalToDelimited(var1);
      String var15 = "alter table " + var13 + "." + var14 + " compress inplace" + (var2 != 0 ? " purge" : "") + (var3 != 0 ? " defragment" : "") + (var4 != 0 ? " truncate_end" : "");
      Connection var10 = getDefaultConn();
      PreparedStatement var11 = var10.prepareStatement(var15);
      var11.executeUpdate();
      var11.close();
      var10.close();
   }

   public static String SYSCS_GET_RUNTIMESTATISTICS() throws SQLException {
      RunTimeStatistics var0 = ConnectionUtil.getCurrentLCC().getRunTimeStatisticsObject();
      return var0 == null ? null : var0.toString();
   }

   public static void INSTALL_JAR(String var0, String var1, int var2) throws SQLException {
      try {
         LanguageConnectionContext var3 = ConnectionUtil.getCurrentLCC();
         String[] var4 = IdUtil.parseMultiPartSQLIdentifier(var1.trim());
         String var5;
         String var6;
         if (var4.length == 1) {
            var5 = var3.getCurrentSchemaName();
            var6 = var4[0];
         } else {
            var5 = var4[0];
            var6 = var4[1];
         }

         checkJarSQLName(var6);
         JarUtil.install(var3, var5, var6, var0);
      } catch (StandardException var7) {
         throw PublicAPI.wrapStandardException(var7);
      }
   }

   public static void REPLACE_JAR(String var0, String var1) throws SQLException {
      try {
         LanguageConnectionContext var2 = ConnectionUtil.getCurrentLCC();
         String[] var3 = IdUtil.parseMultiPartSQLIdentifier(var1.trim());
         String var4;
         String var5;
         if (var3.length == 1) {
            var4 = var2.getCurrentSchemaName();
            var5 = var3[0];
         } else {
            var4 = var3[0];
            var5 = var3[1];
         }

         checkJarSQLName(var5);
         JarUtil.replace(var2, var4, var5, var0);
      } catch (StandardException var6) {
         throw PublicAPI.wrapStandardException(var6);
      }
   }

   public static void REMOVE_JAR(String var0, int var1) throws SQLException {
      try {
         LanguageConnectionContext var2 = ConnectionUtil.getCurrentLCC();
         String[] var3 = IdUtil.parseMultiPartSQLIdentifier(var0.trim());
         String var4;
         String var5;
         if (var3.length == 1) {
            var4 = var2.getCurrentSchemaName();
            var5 = var3[0];
         } else {
            var4 = var3[0];
            var5 = var3[1];
         }

         checkJarSQLName(var5);
         JarUtil.drop(var2, var4, var5);
      } catch (StandardException var6) {
         throw PublicAPI.wrapStandardException(var6);
      }
   }

   private static void checkJarSQLName(String var0) throws StandardException {
      if (var0.length() == 0 || var0.indexOf(58) != -1) {
         throw StandardException.newException("XCXA0.S", new Object[0]);
      }
   }

   public static void SYSCS_EXPORT_TABLE(String var0, String var1, String var2, String var3, String var4, String var5) throws SQLException {
      Connection var6 = getDefaultConn();
      Export.exportTable(var6, var0, var1, var2, var3, var4, var5);
      var6.commit();
   }

   public static void SYSCS_EXPORT_TABLE_LOBS_TO_EXTFILE(String var0, String var1, String var2, String var3, String var4, String var5, String var6) throws SQLException {
      Connection var7 = getDefaultConn();
      Export.exportTable(var7, var0, var1, var2, var3, var4, var5, var6);
      var7.commit();
   }

   public static void SYSCS_EXPORT_QUERY(String var0, String var1, String var2, String var3, String var4) throws SQLException {
      Connection var5 = getDefaultConn();
      Export.exportQuery(var5, var0, var1, var2, var3, var4);
      var5.commit();
   }

   public static void SYSCS_EXPORT_QUERY_LOBS_TO_EXTFILE(String var0, String var1, String var2, String var3, String var4, String var5) throws SQLException {
      Connection var6 = getDefaultConn();
      Export.exportQuery(var6, var0, var1, var2, var3, var4, var5);
      var6.commit();
   }

   public static void SYSCS_IMPORT_TABLE(String var0, String var1, String var2, String var3, String var4, String var5, short var6) throws SQLException {
      Connection var7 = getDefaultConn();

      try {
         Import.importTable(var7, var0, var1, var2, var3, var4, var5, var6, false);
      } catch (SQLException var9) {
         rollBackAndThrowSQLException(var7, var9);
      }

      var7.commit();
   }

   public static void SYSCS_IMPORT_TABLE_BULK(String var0, String var1, String var2, String var3, String var4, String var5, short var6, short var7) throws SQLException {
      Connection var8 = getDefaultConn();

      try {
         Import.importTable(var8, var0, var1, var2, var3, var4, var5, var6, false, var7);
      } catch (SQLException var10) {
         rollBackAndThrowSQLException(var8, var10);
      }

      var8.commit();
   }

   private static void rollBackAndThrowSQLException(Connection var0, SQLException var1) throws SQLException {
      try {
         var0.rollback();
      } catch (SQLException var3) {
         var1.setNextException(var3);
      }

      throw var1;
   }

   public static void SYSCS_IMPORT_TABLE_LOBS_FROM_EXTFILE(String var0, String var1, String var2, String var3, String var4, String var5, short var6) throws SQLException {
      Connection var7 = getDefaultConn();

      try {
         Import.importTable(var7, var0, var1, var2, var3, var4, var5, var6, true);
      } catch (SQLException var9) {
         rollBackAndThrowSQLException(var7, var9);
      }

      var7.commit();
   }

   public static void SYSCS_IMPORT_DATA(String var0, String var1, String var2, String var3, String var4, String var5, String var6, String var7, short var8) throws SQLException {
      Connection var9 = getDefaultConn();

      try {
         Import.importData(var9, var0, var1, var2, var3, var4, var5, var6, var7, var8, false);
      } catch (SQLException var11) {
         rollBackAndThrowSQLException(var9, var11);
      }

      var9.commit();
   }

   public static void SYSCS_IMPORT_DATA_BULK(String var0, String var1, String var2, String var3, String var4, String var5, String var6, String var7, short var8, short var9) throws SQLException {
      Connection var10 = getDefaultConn();

      try {
         Import.importData(var10, var0, var1, var2, var3, var4, var5, var6, var7, var8, false, var9);
      } catch (SQLException var12) {
         rollBackAndThrowSQLException(var10, var12);
      }

      var10.commit();
   }

   public static void SYSCS_IMPORT_DATA_LOBS_FROM_EXTFILE(String var0, String var1, String var2, String var3, String var4, String var5, String var6, String var7, short var8) throws SQLException {
      Connection var9 = getDefaultConn();

      try {
         Import.importData(var9, var0, var1, var2, var3, var4, var5, var6, var7, var8, true);
      } catch (SQLException var11) {
         rollBackAndThrowSQLException(var9, var11);
      }

      var9.commit();
   }

   public static void SYSCS_BULK_INSERT(String var0, String var1, String var2, String var3) throws SQLException {
      try {
         SecurityUtil.authorize(Securable.BULK_INSERT);
      } catch (StandardException var8) {
         throw PublicAPI.wrapStandardException(var8);
      }

      Connection var4 = getDefaultConn();
      String var5 = IdUtil.mkQualifiedName(var0, var1);
      String var6 = "insert into " + var5 + " --DERBY-PROPERTIES insertMode=bulkInsert \nselect * from new " + IdUtil.normalToDelimited(var2) + "(" + StringUtil.quoteStringLiteral(var0) + ", " + StringUtil.quoteStringLiteral(var1) + ", " + StringUtil.quoteStringLiteral(var3) + ") as t";
      PreparedStatement var7 = var4.prepareStatement(var6);
      var7.executeUpdate();
      var7.close();
   }

   public static void SYSCS_RELOAD_SECURITY_POLICY() throws SQLException {
      throw PublicAPI.wrapStandardException(StandardException.newException("XK002.S", new Object[0]));
   }

   public static double PI() {
      return Math.PI;
   }

   public static double COT(double var0) {
      return (double)1.0F / StrictMath.tan(var0);
   }

   public static int SIGN(double var0) {
      return var0 < (double)0.0F ? -1 : (var0 > (double)0.0F ? 1 : 0);
   }

   public static double RAND(int var0) {
      return (new Random((long)var0)).nextDouble();
   }

   public static void SYSCS_SET_USER_ACCESS(String var0, String var1) throws SQLException {
      try {
         if (var0 == null) {
            throw StandardException.newException("28502", new Object[]{var0});
         } else {
            String var2;
            if ("FULLACCESS".equals(var1)) {
               var2 = "derby.database.fullAccessUsers";
            } else if ("READONLYACCESS".equals(var1)) {
               var2 = "derby.database.readOnlyAccessUsers";
            } else {
               if (var1 != null) {
                  throw StandardException.newException("XCZ00.S", new Object[]{var1});
               }

               var2 = null;
            }

            removeFromAccessList("derby.database.fullAccessUsers", var0);
            removeFromAccessList("derby.database.readOnlyAccessUsers", var0);
            if (var2 != null) {
               String var3 = getProperty(var2, Securable.SET_USER_ACCESS);
               setDatabaseProperty(var2, IdUtil.appendNormalToList(var0, var3), (Securable)null);
            }

         }
      } catch (StandardException var4) {
         throw PublicAPI.wrapStandardException(var4);
      }
   }

   private static void removeFromAccessList(String var0, String var1) throws SQLException, StandardException {
      String var2 = getProperty(var0, Securable.SET_USER_ACCESS);
      if (var2 != null) {
         setDatabaseProperty(var0, IdUtil.deleteId(var1, var2), (Securable)null);
      }

   }

   public static String SYSCS_GET_USER_ACCESS(String var0) throws SQLException {
      try {
         if (var0 == null) {
            throw StandardException.newException("28502", new Object[]{var0});
         } else {
            String var1 = getProperty("derby.database.fullAccessUsers", Securable.GET_USER_ACCESS);
            if (IdUtil.idOnList(var0, var1)) {
               return "FULLACCESS";
            } else {
               String var2 = getProperty("derby.database.readOnlyAccessUsers", Securable.GET_USER_ACCESS);
               if (IdUtil.idOnList(var0, var2)) {
                  return "READONLYACCESS";
               } else {
                  String var3 = getProperty("derby.database.defaultConnectionMode", Securable.GET_USER_ACCESS);
                  if (var3 != null) {
                     var3 = StringUtil.SQLToUpperCase(var3);
                  } else {
                     var3 = "FULLACCESS";
                  }

                  return var3;
               }
            }
         }
      } catch (StandardException var4) {
         throw PublicAPI.wrapStandardException(var4);
      }
   }

   public static void SYSCS_INVALIDATE_STORED_STATEMENTS() throws SQLException {
      LanguageConnectionContext var0 = ConnectionUtil.getCurrentLCC();
      DataDictionary var1 = var0.getDataDictionary();

      try {
         SecurityUtil.authorize(Securable.INVALIDATE_STORED_STATEMENTS);
         var1.invalidateAllSPSPlans(var0);
      } catch (StandardException var3) {
         throw PublicAPI.wrapStandardException(var3);
      }
   }

   public static void SYSCS_EMPTY_STATEMENT_CACHE() throws SQLException {
      try {
         SecurityUtil.authorize(Securable.EMPTY_STATEMENT_CACHE);
      } catch (StandardException var2) {
         throw PublicAPI.wrapStandardException(var2);
      }

      LanguageConnectionContext var0 = ConnectionUtil.getCurrentLCC();
      CacheManager var1 = var0.getLanguageConnectionFactory().getStatementCache();
      if (var1 != null) {
         var1.ageOut();
      }

   }

   public static void SYSCS_SET_XPLAIN_MODE(int var0) throws SQLException, StandardException {
      try {
         SecurityUtil.authorize(Securable.SET_XPLAIN_MODE);
      } catch (StandardException var2) {
         throw PublicAPI.wrapStandardException(var2);
      }

      ConnectionUtil.getCurrentLCC().setXplainOnlyMode(var0 != 0);
   }

   public static int SYSCS_GET_XPLAIN_MODE() throws SQLException, StandardException {
      try {
         SecurityUtil.authorize(Securable.GET_XPLAIN_MODE);
      } catch (StandardException var1) {
         throw PublicAPI.wrapStandardException(var1);
      }

      return ConnectionUtil.getCurrentLCC().getXplainOnlyMode() ? 1 : 0;
   }

   public static void SYSCS_SET_XPLAIN_SCHEMA(String var0) throws SQLException, StandardException {
      try {
         SecurityUtil.authorize(Securable.SET_XPLAIN_SCHEMA);
      } catch (StandardException var4) {
         throw PublicAPI.wrapStandardException(var4);
      }

      LanguageConnectionContext var1 = ConnectionUtil.getCurrentLCC();
      TransactionController var2 = var1.getTransactionExecute();
      if (var0 != null && var0.trim().length() != 0) {
         boolean var3 = var1.getRunTimeStatisticsMode();
         var1.setRunTimeStatisticsMode(false);
         createXplainSchema(var0);
         createXplainTable(var1, var0, new XPLAINStatementDescriptor());
         createXplainTable(var1, var0, new XPLAINStatementTimingsDescriptor());
         createXplainTable(var1, var0, new XPLAINResultSetDescriptor());
         createXplainTable(var1, var0, new XPLAINResultSetTimingsDescriptor());
         createXplainTable(var1, var0, new XPLAINScanPropsDescriptor());
         createXplainTable(var1, var0, new XPLAINSortPropsDescriptor());
         var1.setRunTimeStatisticsMode(var3);
         var1.setXplainSchema(var0);
      } else {
         var1.setXplainSchema((String)null);
      }
   }

   private static boolean hasSchema(Connection var0, String var1) throws SQLException {
      ResultSet var2 = var0.getMetaData().getSchemas();

      boolean var3;
      for(var3 = false; var2.next() && !var3; var3 = var1.equals(var2.getString("TABLE_SCHEM"))) {
      }

      var2.close();
      return var3;
   }

   private static boolean hasTable(Connection var0, String var1, String var2) throws SQLException {
      ResultSet var3 = var0.getMetaData().getTables((String)null, var1, var2, new String[]{"TABLE"});
      boolean var4 = var3.next();
      var3.close();
      return var4;
   }

   private static void createXplainSchema(String var0) throws SQLException {
      Connection var1 = getDefaultConn();
      if (!hasSchema(var1, var0)) {
         String var2 = IdUtil.normalToDelimited(var0);
         Statement var3 = var1.createStatement();
         var3.executeUpdate("CREATE SCHEMA " + var2);
         var3.close();
      }

      var1.close();
   }

   private static void createXplainTable(LanguageConnectionContext var0, String var1, XPLAINTableDescriptor var2) throws SQLException {
      String[] var3 = var2.getTableDDL(var1);
      Connection var4 = getDefaultConn();
      if (!hasTable(var4, var1, var2.getCatalogName())) {
         Statement var5 = var4.createStatement();

         for(int var6 = 0; var6 < var3.length; ++var6) {
            var5.executeUpdate(var3[var6]);
         }

         var5.close();
      }

      String var7 = var2.getTableInsert();
      var4.prepareStatement(var7).close();
      var4.close();
      var0.setXplainStatement(var2.getCatalogName(), var7);
   }

   public static String SYSCS_GET_XPLAIN_SCHEMA() throws SQLException, StandardException {
      try {
         SecurityUtil.authorize(Securable.GET_XPLAIN_SCHEMA);
      } catch (StandardException var1) {
         throw PublicAPI.wrapStandardException(var1);
      }

      String var0 = ConnectionUtil.getCurrentLCC().getXplainSchema();
      return var0 == null ? "" : var0;
   }

   public static void SYSCS_CREATE_USER(String var0, String var1) throws SQLException {
      var0 = normalizeUserName(var0);
      LanguageConnectionContext var2 = ConnectionUtil.getCurrentLCC();
      TransactionController var3 = var2.getTransactionExecute();

      try {
         SecurityUtil.authorize(Securable.CREATE_USER);
         DataDictionary var4 = var2.getDataDictionary();
         String var5 = var4.getAuthorizationDatabaseOwner();
         if (!var5.equals(var0)) {
            if (var4.getUser(var5) == null) {
               throw StandardException.newException("4251K", new Object[0]);
            }
         } else {
            String var6 = var2.getStatementContext().getSQLSessionContext().getCurrentUser();
            if (!var5.equals(var6)) {
               throw StandardException.newException("4251D", new Object[0]);
            }
         }
      } catch (StandardException var7) {
         throw PublicAPI.wrapStandardException(var7);
      }

      addUser(var0, var1, var3);
   }

   public static void addUser(String var0, String var1, TransactionController var2) throws SQLException {
      try {
         LanguageConnectionContext var3 = ConnectionUtil.getCurrentLCC();
         DataDictionary var4 = var3.getDataDictionary();
         var4.startWriting(var3);
         UserDescriptor var5 = makeUserDescriptor(var4, var2, var0, var1);
         var4.addDescriptor(var5, (TupleDescriptor)null, 22, false, var2);
         if (var4.getAuthorizationDatabaseOwner().equals(var0)) {
            var2.setProperty("derby.authentication.provider", "NATIVE::LOCAL", true);
         }

      } catch (StandardException var6) {
         throw PublicAPI.wrapStandardException(var6);
      }
   }

   private static UserDescriptor makeUserDescriptor(DataDictionary var0, TransactionController var1, String var2, String var3) throws StandardException {
      DataDescriptorGenerator var4 = var0.getDataDescriptorGenerator();
      PasswordHasher var5 = var0.makePasswordHasher(var1.getProperties());
      if (var5 == null) {
         throw StandardException.newException("4251G", new Object[0]);
      } else {
         String var6 = var5.encodeHashingScheme();
         String var7 = var5.hashPasswordIntoString(var2, var3);
         Timestamp var8 = new Timestamp((new Date()).getTime());
         UserDescriptor var9 = var4.newUserDescriptor(var2, var6, var7.toCharArray(), var8);
         return var9;
      }
   }

   public static void SYSCS_RESET_PASSWORD(String var0, String var1) throws SQLException {
      try {
         SecurityUtil.authorize(Securable.RESET_PASSWORD);
      } catch (StandardException var3) {
         throw PublicAPI.wrapStandardException(var3);
      }

      resetAuthorizationIDPassword(normalizeUserName(var0), var1);
   }

   private static void resetAuthorizationIDPassword(String var0, String var1) throws SQLException {
      try {
         LanguageConnectionContext var2 = ConnectionUtil.getCurrentLCC();
         DataDictionary var3 = var2.getDataDictionary();
         TransactionController var4 = var2.getTransactionExecute();
         checkLegalUser(var3, var0);
         var3.startWriting(var2);
         UserDescriptor var5 = makeUserDescriptor(var3, var4, var0, var1);
         var3.updateUser(var5, var4);
      } catch (StandardException var6) {
         throw PublicAPI.wrapStandardException(var6);
      }
   }

   public static void SYSCS_MODIFY_PASSWORD(String var0) throws SQLException {
      String var1 = ConnectionUtil.getCurrentLCC().getStatementContext().getSQLSessionContext().getCurrentUser();
      resetAuthorizationIDPassword(var1, var0);
   }

   public static void SYSCS_DROP_USER(String var0) throws SQLException {
      var0 = normalizeUserName(var0);

      try {
         SecurityUtil.authorize(Securable.DROP_USER);
         LanguageConnectionContext var1 = ConnectionUtil.getCurrentLCC();
         DataDictionary var2 = var1.getDataDictionary();
         String var3 = var2.getAuthorizationDatabaseOwner();
         if (var3.equals(var0)) {
            throw StandardException.newException("4251F", new Object[0]);
         } else {
            checkLegalUser(var2, var0);
            var2.startWriting(var1);
            var2.dropUser(var0, var1.getTransactionExecute());
         }
      } catch (StandardException var4) {
         throw PublicAPI.wrapStandardException(var4);
      }
   }

   private static void checkLegalUser(DataDictionary var0, String var1) throws StandardException {
      if (var0.getUser(var1) == null) {
         throw StandardException.newException("XK001.S", new Object[0]);
      }
   }

   private static String normalizeUserName(String var0) throws SQLException {
      try {
         return IdUtil.getUserAuthorizationId(var0);
      } catch (StandardException var2) {
         throw PublicAPI.wrapStandardException(var2);
      }
   }

   public static String SYSCS_GET_DATABASE_NAME() throws SQLException {
      LanguageConnectionContext var0 = ConnectionUtil.getCurrentLCC();

      try {
         return getMonitor().getCanonicalServiceName(var0.getDbname());
      } catch (StandardException var2) {
         throw PublicAPI.wrapStandardException(var2);
      }
   }

   public static Long SYSCS_PEEK_AT_SEQUENCE(String var0, String var1) throws SQLException {
      try {
         return ConnectionUtil.getCurrentLCC().getDataDictionary().peekAtSequence(var0, var1);
      } catch (StandardException var3) {
         throw PublicAPI.wrapStandardException(var3);
      }
   }

   public static Long SYSCS_PEEK_AT_IDENTITY(String var0, String var1) throws SQLException {
      try {
         return ConnectionUtil.getCurrentLCC().getDataDictionary().peekAtIdentity(var0, var1);
      } catch (StandardException var3) {
         throw PublicAPI.wrapStandardException(var3);
      }
   }

   private static ModuleFactory getMonitor() {
      return Monitor.getMonitor();
   }
}
