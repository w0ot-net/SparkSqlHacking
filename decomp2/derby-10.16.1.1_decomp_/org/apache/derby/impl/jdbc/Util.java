package org.apache.derby.impl.jdbc;

import java.io.IOException;
import java.sql.SQLException;
import org.apache.derby.iapi.services.monitor.Monitor;
import org.apache.derby.iapi.services.property.PropertyUtil;
import org.apache.derby.shared.common.error.ErrorStringBuilder;
import org.apache.derby.shared.common.error.ExceptionFactory;
import org.apache.derby.shared.common.error.StandardException;
import org.apache.derby.shared.common.i18n.MessageService;
import org.apache.derby.shared.common.stream.HeaderPrintWriter;

public abstract class Util {
   private static int logSeverityLevel = PropertyUtil.getSystemInt("derby.stream.error.logSeverityLevel", 40000);

   public static void logAndThrowSQLException(SQLException var0) throws SQLException {
      if (var0.getErrorCode() >= logSeverityLevel) {
         logSQLException(var0);
      }

      throw var0;
   }

   public static void logSQLException(SQLException var0) {
      if (var0 != null) {
         String var1 = var0.getMessage();
         String var2 = var0.getSQLState();
         if (var2 == null || !var2.equals("08004") || var1 == null || !var1.equals("Connection refused : java.lang.OutOfMemoryError")) {
            logError("\nERROR " + var0.getSQLState() + ": " + var0.getMessage() + "\n", var0);
         }
      }
   }

   private static void logError(String var0, Throwable var1) {
      HeaderPrintWriter var2 = Monitor.getStream();
      if (var2 == null) {
         var1.printStackTrace();
      } else {
         ErrorStringBuilder var3 = new ErrorStringBuilder(var2.getHeader());
         var3.append(var0);
         var3.stackTrace(var1);
         var2.print(var3.get().toString());
         var2.flush();
         var3.reset();
      }
   }

   public static void ASSERT(EmbedConnection var0, boolean var1, String var2) throws SQLException {
   }

   public static void checkForSupportedDataType(int var0) throws SQLException {
      if (!isSupportedType(var0)) {
         throw generateCsSQLException("0A000.S.7", typeName(var0));
      }
   }

   public static void checkSupportedRaiseStandard(int var0) throws StandardException {
      if (!isSupportedType(var0)) {
         throw StandardException.newException("0A000.S.7", new Object[]{typeName(var0)});
      }
   }

   private static boolean isSupportedType(int var0) {
      switch (var0) {
         case -16:
         case -15:
         case -9:
         case -8:
         case 0:
         case 70:
         case 1111:
         case 2001:
         case 2002:
         case 2003:
         case 2006:
         case 2009:
         case 2011:
         case 2012:
            return false;
         default:
            return true;
      }
   }

   public static SQLException generateCsSQLException(String var0, Object... var1) {
      return generateCsSQLException(var0, (Throwable)null, var1);
   }

   static SQLException generateCsSQLException(String var0, Throwable var1, Object... var2) {
      return ExceptionFactory.getInstance().getSQLException(var0, (SQLException)null, var1, var2);
   }

   public static SQLException generateCsSQLException(StandardException var0) {
      return ExceptionFactory.getInstance().getSQLException(var0.getMessage(), var0.getMessageId(), (SQLException)null, var0.getSeverity(), var0, var0.getArguments());
   }

   public static SQLException noCurrentConnection() {
      return generateCsSQLException("08003");
   }

   static SQLException seeNextException(String var0, SQLException var1, Throwable var2, Object... var3) {
      return ExceptionFactory.getInstance().getSQLException(var0, var1, var2, var3);
   }

   public static SQLException javaException(Throwable var0) {
      String var2 = var0.getMessage();
      if (var2 == null) {
         var2 = "";
      }

      String var1 = var0.getClass().getName();
      SQLException var3 = null;
      Throwable var4 = var0.getCause();
      if (var4 != null) {
         if (var4 instanceof SQLException) {
            var3 = (SQLException)var4;
         } else if (var4 instanceof StandardException) {
            var3 = generateCsSQLException((StandardException)var4);
         } else {
            var3 = javaException(var4);
         }
      }

      SQLException var5 = seeNextException("XJ001.U", var3, var0, var1, var2);
      if (var5.getErrorCode() >= logSeverityLevel) {
         logSQLException(var5);
      }

      return var5;
   }

   public static SQLException notImplemented() {
      return notImplemented(MessageService.getTextMessage("J008", new Object[0]));
   }

   public static SQLException notImplemented(String var0) {
      return generateCsSQLException("0A000.S", var0);
   }

   static SQLException setStreamFailure(IOException var0) {
      String var1 = var0.getMessage();
      if (var1 == null) {
         var1 = var0.getClass().getName();
      }

      return generateCsSQLException("XJ022.S", var0, var1);
   }

   static SQLException typeMisMatch(int var0) {
      return generateCsSQLException("XJ020.S", typeName(var0));
   }

   public static int[] squashLongs(long[] var0) {
      int var1 = var0 == null ? 0 : var0.length;
      int[] var2 = new int[var1];

      for(int var3 = 0; var3 < var1; ++var3) {
         var2[var3] = (int)var0[var3];
      }

      return var2;
   }

   static IOException newIOException(Throwable var0) {
      return new IOException(var0);
   }

   public static String typeName(int var0) {
      switch (var0) {
         case -8 -> {
            return "ROWID";
         }
         case -7 -> {
            return "CHAR () FOR BIT DATA";
         }
         case -6 -> {
            return "TINYINT";
         }
         case -5 -> {
            return "BIGINT";
         }
         case -4 -> {
            return "LONGVARBINARY";
         }
         case -3 -> {
            return "VARBINARY";
         }
         case -2 -> {
            return "BINARY";
         }
         case -1 -> {
            return "LONGVARCHAR";
         }
         case 1 -> {
            return "CHAR";
         }
         case 2 -> {
            return "NUMERIC";
         }
         case 3 -> {
            return "DECIMAL";
         }
         case 4 -> {
            return "INTEGER";
         }
         case 5 -> {
            return "SMALLINT";
         }
         case 6 -> {
            return "FLOAT";
         }
         case 7 -> {
            return "REAL";
         }
         case 8 -> {
            return "DOUBLE";
         }
         case 12 -> {
            return "VARCHAR";
         }
         case 16 -> {
            return "BOOLEAN";
         }
         case 70 -> {
            return "DATALINK";
         }
         case 91 -> {
            return "DATE";
         }
         case 92 -> {
            return "TIME";
         }
         case 93 -> {
            return "TIMESTAMP";
         }
         case 456 -> {
            return "XML";
         }
         case 1111 -> {
            return "OTHER";
         }
         case 2000 -> {
            return "Types.JAVA_OBJECT";
         }
         case 2002 -> {
            return "STRUCT";
         }
         case 2003 -> {
            return "ARRAY";
         }
         case 2004 -> {
            return "BLOB";
         }
         case 2005 -> {
            return "CLOB";
         }
         case 2006 -> {
            return "REF";
         }
         case 2009 -> {
            return "SQLXML";
         }
         case 2012 -> {
            return "REF CURSOR";
         }
         default -> {
            return String.valueOf(var0);
         }
      }
   }
}
