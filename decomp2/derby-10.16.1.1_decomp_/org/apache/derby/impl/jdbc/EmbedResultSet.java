package org.apache.derby.impl.jdbc;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.NClob;
import java.sql.Ref;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;
import org.apache.derby.iapi.jdbc.CharacterStreamDescriptor;
import org.apache.derby.iapi.jdbc.EngineResultSet;
import org.apache.derby.iapi.services.io.CloseFilterInputStream;
import org.apache.derby.iapi.services.io.LimitInputStream;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.ParameterValueSet;
import org.apache.derby.iapi.sql.PreparedStatement;
import org.apache.derby.iapi.sql.ResultColumnDescriptor;
import org.apache.derby.iapi.sql.ResultDescription;
import org.apache.derby.iapi.sql.ResultSet;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.conn.StatementContext;
import org.apache.derby.iapi.sql.execute.CursorActivation;
import org.apache.derby.iapi.sql.execute.ExecCursorTableReference;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.sql.execute.ExecutionFactory;
import org.apache.derby.iapi.sql.execute.NoPutResultSet;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.RawToBinaryFormatStream;
import org.apache.derby.iapi.types.ReaderToUTF8Stream;
import org.apache.derby.iapi.types.SQLDecimal;
import org.apache.derby.iapi.types.StringDataValue;
import org.apache.derby.iapi.types.UserDataValue;
import org.apache.derby.iapi.types.VariableSizeDataValue;
import org.apache.derby.iapi.util.IdUtil;
import org.apache.derby.iapi.util.InterruptStatus;
import org.apache.derby.impl.sql.execute.ScrollInsensitiveResultSet;
import org.apache.derby.shared.common.error.StandardException;

public class EmbedResultSet extends ConnectionChild implements EngineResultSet, Comparable {
   private static long fetchedRowBase = 0L;
   protected static final int FIRST = 1;
   protected static final int NEXT = 2;
   protected static final int LAST = 3;
   protected static final int PREVIOUS = 4;
   protected static final int BEFOREFIRST = 5;
   protected static final int AFTERLAST = 6;
   protected static final int ABSOLUTE = 7;
   protected static final int RELATIVE = 8;
   private ExecRow currentRow;
   protected boolean wasNull;
   boolean isClosed;
   private boolean isOnInsertRow;
   private Object currentStream;
   private ResultSet theResults;
   private boolean forMetaData;
   private SQLWarning topWarning;
   Activation singleUseActivation;
   final int order;
   private final ResultDescription resultDescription;
   private long maxRows;
   private final int maxFieldSize;
   private long NumberofFetchedRows;
   private final EmbedStatement stmt;
   private EmbedStatement owningStmt;
   private Statement applicationStmt;
   private final long timeoutMillis;
   private final boolean isAtomic;
   private final int concurrencyOfThisResultSet;
   private final ExecRow updateRow;
   private boolean[] columnGotUpdated;
   private boolean currentRowHasBeenUpdated;
   private int fetchDirection;
   private int fetchSize;
   private boolean[] columnUsedFlags;

   public EmbedResultSet(EmbedConnection var1, ResultSet var2, boolean var3, EmbedStatement var4, boolean var5) throws SQLException {
      super(var1);
      this.NumberofFetchedRows = fetchedRowBase;
      this.theResults = var2;
      if (this.forMetaData = var3) {
         this.singleUseActivation = var2.getActivation();
      }

      this.applicationStmt = this.stmt = this.owningStmt = var4;
      this.timeoutMillis = var4 == null ? 0L : var4.timeoutMillis;
      this.isAtomic = var5;
      if (var4 == null) {
         this.concurrencyOfThisResultSet = 1007;
      } else if (var4.resultSetConcurrency == 1007) {
         this.concurrencyOfThisResultSet = 1007;
      } else if (!this.isForUpdate()) {
         this.concurrencyOfThisResultSet = 1007;
         SQLWarning var6 = StandardException.newWarning("01J06", new Object[0]);
         this.addWarning(var6);
      } else {
         this.concurrencyOfThisResultSet = 1008;
      }

      this.resultDescription = this.theResults.getResultDescription();
      if (this.concurrencyOfThisResultSet == 1008) {
         int var10 = this.resultDescription.getColumnCount();
         ExecutionFactory var7 = this.getLanguageConnectionContext(var1).getLanguageConnectionFactory().getExecutionFactory();

         try {
            this.columnGotUpdated = new boolean[var10];
            this.updateRow = var7.getValueRow(var10);

            for(int var8 = 1; var8 <= var10; ++var8) {
               this.updateRow.setColumn(var8, this.resultDescription.getColumnDescriptor(var8).getType().getNull());
            }

            this.initializeUpdateRowModifiers();
         } catch (StandardException var9) {
            throw noStateChangeException(var9);
         }
      } else {
         this.updateRow = null;
      }

      if (var4 != null) {
         if (var4.resultSetType == 1003) {
            this.maxRows = var4.maxRows;
         }

         this.maxFieldSize = var4.MaxFieldSize;
      } else {
         this.maxFieldSize = 0;
      }

      this.order = var1.getResultSetOrderId();
   }

   public static void setFetchedRowBase(long var0) {
   }

   private void checkNotOnInsertRow() throws SQLException {
      if (this.isOnInsertRow) {
         throw newSQLException("24000", new Object[0]);
      }
   }

   protected final void checkOnRow() throws SQLException {
      if (this.currentRow == null) {
         throw newSQLException("24000", new Object[0]);
      }
   }

   private void initializeUpdateRowModifiers() {
      this.currentRowHasBeenUpdated = false;
      Arrays.fill(this.columnGotUpdated, false);
   }

   final int getColumnType(int var1) throws SQLException {
      if (!this.isOnInsertRow) {
         this.checkOnRow();
      }

      if (var1 >= 1 && var1 <= this.resultDescription.getColumnCount()) {
         return this.resultDescription.getColumnDescriptor(var1).getType().getJDBCTypeId();
      } else {
         throw newSQLException("S0022", new Object[]{var1});
      }
   }

   public boolean next() throws SQLException {
      if (this.maxRows != 0L) {
         ++this.NumberofFetchedRows;
         if (this.NumberofFetchedRows > this.maxRows) {
            this.closeCurrentStream();
            return false;
         }
      }

      return this.movePosition(2, 0, "next");
   }

   protected boolean movePosition(int var1, String var2) throws SQLException {
      return this.movePosition(var1, 0, var2);
   }

   protected boolean movePosition(int var1, int var2, String var3) throws SQLException {
      this.closeCurrentStream();
      this.checkExecIfClosed(var3);
      if (this.isOnInsertRow) {
         this.moveToCurrentRow();
      }

      synchronized(this.getConnectionSynchronization()) {
         this.setupContextStack();

         boolean var9;
         try {
            LanguageConnectionContext var5 = this.getLanguageConnectionContext(this.getEmbedConnection());

            ExecRow var6;
            try {
               StatementContext var7 = var5.pushStatementContext(this.isAtomic, this.concurrencyOfThisResultSet == 1007, this.getSQLText(), this.getParameterValueSet(), false, this.timeoutMillis);
               switch (var1) {
                  case 1 -> var6 = this.theResults.getFirstRow();
                  case 2 -> var6 = this.theResults.getNextRow();
                  case 3 -> var6 = this.theResults.getLastRow();
                  case 4 -> var6 = this.theResults.getPreviousRow();
                  case 5 -> var6 = this.theResults.setBeforeFirstRow();
                  case 6 -> var6 = this.theResults.setAfterLastRow();
                  case 7 -> var6 = this.theResults.getAbsoluteRow(var2);
                  case 8 -> var6 = this.theResults.getRelativeRow(var2);
                  default -> var6 = null;
               }

               var5.popStatementContext(var7, (Throwable)null);
               InterruptStatus.restoreIntrFlagIfSeen(var5);
            } catch (Throwable var15) {
               throw this.closeOnTransactionError(var15);
            }

            SQLWarning var18 = this.theResults.getWarnings();
            if (var18 != null) {
               this.addWarning(var18);
            }

            boolean var8 = (this.currentRow = var6) != null;
            if (!var8 && var1 == 2 && (!this.forMetaData || var5.getActivationCount() <= 1) && this.owningStmt != null && this.owningStmt.getResultSetType() == 1003) {
               this.owningStmt.resultSetClosing(this);
            }

            if (this.columnUsedFlags != null) {
               Arrays.fill(this.columnUsedFlags, false);
            }

            if (this.columnGotUpdated != null && this.currentRowHasBeenUpdated) {
               this.initializeUpdateRowModifiers();
            }

            var9 = var8;
         } finally {
            this.restoreContextStack();
         }

         return var9;
      }
   }

   public void close() throws SQLException {
      if (!this.isClosed) {
         this.closeCurrentStream();
         synchronized(this.getConnectionSynchronization()) {
            try {
               this.setupContextStack();
            } catch (SQLException var10) {
               return;
            }

            try {
               LanguageConnectionContext var2 = this.getLanguageConnectionContext(this.getEmbedConnection());

               try {
                  this.theResults.close();
                  if (this.singleUseActivation != null) {
                     this.singleUseActivation.close();
                     this.singleUseActivation = null;
                  }

                  InterruptStatus.restoreIntrFlagIfSeen(var2);
               } catch (Throwable var11) {
                  throw this.handleException(var11);
               }

               if (this.forMetaData) {
                  if (var2.getActivationCount() <= 1 && this.owningStmt != null) {
                     this.owningStmt.resultSetClosing(this);
                  }
               } else if (this.owningStmt != null) {
                  this.owningStmt.resultSetClosing(this);
               }
            } finally {
               this.markClosed();
               this.restoreContextStack();
            }

            this.currentRow = null;
         }
      }
   }

   private void markClosed() {
      if (!this.isClosed) {
         this.isClosed = true;
         if (this.stmt != null) {
            this.stmt.closeMeOnCompletion();
         }

         if (this.owningStmt != null && this.owningStmt != this.stmt) {
            this.owningStmt.closeMeOnCompletion();
         }

      }
   }

   public final boolean wasNull() throws SQLException {
      this.checkIfClosed("wasNull");
      return this.wasNull;
   }

   public final String getString(int var1) throws SQLException {
      this.checkIfClosed("getString");
      int var2 = this.getColumnType(var1);
      if (var2 == 2004 || var2 == 2005) {
         this.checkLOBMultiCall(var1);
      }

      try {
         DataValueDescriptor var3 = this.getColumn(var1);
         if (this.wasNull = var3.isNull()) {
            return null;
         } else {
            String var4 = var3.getString();
            if (this.maxFieldSize > 0 && isMaxFieldSizeType(var2) && var4.length() > this.maxFieldSize) {
               var4 = var4.substring(0, this.maxFieldSize);
            }

            return var4;
         }
      } catch (Throwable var5) {
         throw noStateChangeException(var5);
      }
   }

   public final boolean getBoolean(int var1) throws SQLException {
      this.checkIfClosed("getBoolean");

      try {
         DataValueDescriptor var2 = this.getColumn(var1);
         return (this.wasNull = var2.isNull()) ? false : var2.getBoolean();
      } catch (StandardException var3) {
         throw noStateChangeException(var3);
      }
   }

   public final byte getByte(int var1) throws SQLException {
      this.checkIfClosed("getByte");

      try {
         DataValueDescriptor var2 = this.getColumn(var1);
         return (this.wasNull = var2.isNull()) ? 0 : var2.getByte();
      } catch (StandardException var3) {
         throw noStateChangeException(var3);
      }
   }

   public final short getShort(int var1) throws SQLException {
      this.checkIfClosed("getShort");

      try {
         DataValueDescriptor var2 = this.getColumn(var1);
         return (this.wasNull = var2.isNull()) ? 0 : var2.getShort();
      } catch (StandardException var3) {
         throw noStateChangeException(var3);
      }
   }

   public final int getInt(int var1) throws SQLException {
      this.checkIfClosed("getInt");

      try {
         DataValueDescriptor var2 = this.getColumn(var1);
         return (this.wasNull = var2.isNull()) ? 0 : var2.getInt();
      } catch (StandardException var3) {
         throw noStateChangeException(var3);
      }
   }

   public final long getLong(int var1) throws SQLException {
      this.checkIfClosed("getLong");

      try {
         DataValueDescriptor var2 = this.getColumn(var1);
         return (this.wasNull = var2.isNull()) ? 0L : var2.getLong();
      } catch (StandardException var3) {
         throw noStateChangeException(var3);
      }
   }

   public final float getFloat(int var1) throws SQLException {
      this.checkIfClosed("getFloat");

      try {
         DataValueDescriptor var2 = this.getColumn(var1);
         return (this.wasNull = var2.isNull()) ? 0.0F : var2.getFloat();
      } catch (StandardException var3) {
         throw noStateChangeException(var3);
      }
   }

   public final double getDouble(int var1) throws SQLException {
      this.checkIfClosed("getDouble");

      try {
         DataValueDescriptor var2 = this.getColumn(var1);
         return (this.wasNull = var2.isNull()) ? (double)0.0F : var2.getDouble();
      } catch (StandardException var3) {
         throw noStateChangeException(var3);
      }
   }

   public final byte[] getBytes(int var1) throws SQLException {
      this.checkIfClosed("getBytes");
      int var2 = this.getColumnType(var1);
      if (var2 == 2004) {
         this.checkLOBMultiCall(var1);
      }

      try {
         DataValueDescriptor var3 = this.getColumn(var1);
         if (this.wasNull = var3.isNull()) {
            return null;
         } else {
            byte[] var4 = var3.getBytes();
            if (this.maxFieldSize > 0 && isMaxFieldSizeType(var2) && var4.length > this.maxFieldSize) {
               byte[] var5 = new byte[this.maxFieldSize];
               System.arraycopy(var4, 0, var5, 0, this.maxFieldSize);
               var4 = var5;
            }

            return var4;
         }
      } catch (StandardException var6) {
         throw noStateChangeException(var6);
      }
   }

   public final Date getDate(int var1) throws SQLException {
      return this.getDate(var1, (Calendar)null);
   }

   public final Time getTime(int var1) throws SQLException {
      return this.getTime(var1, (Calendar)null);
   }

   public final Timestamp getTimestamp(int var1) throws SQLException {
      return this.getTimestamp(var1, (Calendar)null);
   }

   public Date getDate(int var1, Calendar var2) throws SQLException {
      this.checkIfClosed("getDate");

      try {
         DataValueDescriptor var3 = this.getColumn(var1);
         if (this.wasNull = var3.isNull()) {
            return null;
         } else {
            if (var2 == null) {
               var2 = this.getCal();
            }

            return var3.getDate(var2);
         }
      } catch (StandardException var4) {
         throw noStateChangeException(var4);
      }
   }

   public Date getDate(String var1, Calendar var2) throws SQLException {
      this.checkIfClosed("getDate");
      return this.getDate(this.findColumnName(var1), var2);
   }

   public Time getTime(int var1, Calendar var2) throws SQLException {
      this.checkIfClosed("getTime");

      try {
         DataValueDescriptor var3 = this.getColumn(var1);
         if (this.wasNull = var3.isNull()) {
            return null;
         } else {
            if (var2 == null) {
               var2 = this.getCal();
            }

            return var3.getTime(var2);
         }
      } catch (StandardException var4) {
         throw noStateChangeException(var4);
      }
   }

   public Time getTime(String var1, Calendar var2) throws SQLException {
      this.checkIfClosed("getTime");
      return this.getTime(this.findColumnName(var1), var2);
   }

   public Timestamp getTimestamp(String var1, Calendar var2) throws SQLException {
      this.checkIfClosed("getTimestamp");
      return this.getTimestamp(this.findColumnName(var1), var2);
   }

   public Timestamp getTimestamp(int var1, Calendar var2) throws SQLException {
      this.checkIfClosed("getTimestamp");

      try {
         DataValueDescriptor var3 = this.getColumn(var1);
         if (this.wasNull = var3.isNull()) {
            return null;
         } else {
            if (var2 == null) {
               var2 = this.getCal();
            }

            return var3.getTimestamp(var2);
         }
      } catch (StandardException var4) {
         throw noStateChangeException(var4);
      }
   }

   public final Reader getCharacterStream(int var1) throws SQLException {
      this.checkIfClosed("getCharacterStream");
      int var3 = this.getColumnType(var1);
      int var2;
      switch (var3) {
         case -4:
         case -3:
         case -2:
         case 2004:
            try {
               InputStream var20 = this.getBinaryStream(var1);
               if (var20 == null) {
                  return null;
               }

               InputStreamReader var5 = new InputStreamReader(var20, "UTF-16BE");
               this.currentStream = var5;
               return var5;
            } catch (UnsupportedEncodingException var16) {
               throw new SQLException(var16.getMessage());
            }
         case -1:
         case 1:
         case 12:
            var2 = this.maxFieldSize;
            break;
         case 2005:
            var2 = 0;
            break;
         default:
            throw this.dataTypeConversion("java.io.Reader", var1);
      }

      Object var4 = this.getConnectionSynchronization();
      synchronized(var4) {
         boolean var6 = false;

         Object var8;
         try {
            this.useStreamOrLOB(var1);
            StringDataValue var7 = (StringDataValue)this.getColumn(var1);
            if (!(this.wasNull = var7.isNull())) {
               var6 = true;
               this.setupContextStack();
               if (var7.hasStream()) {
                  CharacterStreamDescriptor var9 = var7.getStreamWithDescriptor();
                  if (var2 > 0) {
                     var9 = (new CharacterStreamDescriptor.Builder()).copyState(var9).maxCharLength((long)var2).build();
                  }

                  var8 = new UTF8Reader(var9, this, var4);
               } else {
                  String var22 = var7.getString();
                  if (var2 > 0 && var22.length() > var2) {
                     var22 = var22.substring(0, var2);
                  }

                  var8 = new StringReader(var22);
               }

               this.currentStream = var8;
               Object var23 = var8;
               return (Reader)var23;
            }

            var8 = null;
         } catch (Throwable var17) {
            throw noStateChangeException(var17);
         } finally {
            if (var6) {
               this.restoreContextStack();
            }

         }

         return (Reader)var8;
      }
   }

   public final InputStream getAsciiStream(int var1) throws SQLException {
      this.checkIfClosed("getAsciiStream");
      int var2 = this.getColumnType(var1);
      switch (var2) {
         case -4:
         case -3:
         case -2:
         case 2004:
            return this.getBinaryStream(var1);
         case -1:
         case 1:
         case 12:
         case 2005:
            Reader var3 = this.getCharacterStream(var1);
            if (var3 == null) {
               return null;
            }

            return new ReaderToAscii(var3);
         default:
            throw this.dataTypeConversion("java.io.InputStream(ASCII)", var1);
      }
   }

   public final InputStream getBinaryStream(int var1) throws SQLException {
      this.checkIfClosed("getBinaryStream");
      int var3 = this.getColumnType(var1);
      int var2;
      switch (var3) {
         case -4:
         case -3:
         case -2:
            var2 = this.maxFieldSize;
            break;
         case 2004:
            var2 = 0;
            break;
         default:
            throw this.dataTypeConversion("java.io.InputStream", var1);
      }

      Object var4 = this.getConnectionSynchronization();
      synchronized(var4) {
         boolean var6 = false;

         Object var8;
         try {
            this.useStreamOrLOB(var1);
            DataValueDescriptor var7 = this.getColumn(var1);
            if (!(this.wasNull = var7.isNull())) {
               var6 = true;
               this.setupContextStack();
               if (var7.hasStream()) {
                  var8 = new BinaryToRawStream(var7.getStream(), var7);
               } else {
                  var8 = new ByteArrayInputStream(var7.getBytes());
               }

               if (var2 > 0) {
                  LimitInputStream var9 = new LimitInputStream((InputStream)var8);
                  var9.setLimit(var2);
                  var8 = var9;
               }

               CloseFilterInputStream var19 = new CloseFilterInputStream((InputStream)var8);
               this.currentStream = var19;
               CloseFilterInputStream var20 = var19;
               return var20;
            }

            var8 = null;
         } catch (Throwable var15) {
            throw noStateChangeException(var15);
         } finally {
            if (var6) {
               this.restoreContextStack();
            }

         }

         return (InputStream)var8;
      }
   }

   public final String getString(String var1) throws SQLException {
      this.checkIfClosed("getString");
      return this.getString(this.findColumnName(var1));
   }

   public final boolean getBoolean(String var1) throws SQLException {
      this.checkIfClosed("getBoolean");
      return this.getBoolean(this.findColumnName(var1));
   }

   public final byte getByte(String var1) throws SQLException {
      this.checkIfClosed("getByte");
      return this.getByte(this.findColumnName(var1));
   }

   public final short getShort(String var1) throws SQLException {
      this.checkIfClosed("getShort");
      return this.getShort(this.findColumnName(var1));
   }

   public final int getInt(String var1) throws SQLException {
      this.checkIfClosed("getInt");
      return this.getInt(this.findColumnName(var1));
   }

   public final long getLong(String var1) throws SQLException {
      this.checkIfClosed("getLong");
      return this.getLong(this.findColumnName(var1));
   }

   public final float getFloat(String var1) throws SQLException {
      this.checkIfClosed("getFloat");
      return this.getFloat(this.findColumnName(var1));
   }

   public final double getDouble(String var1) throws SQLException {
      this.checkIfClosed("getDouble");
      return this.getDouble(this.findColumnName(var1));
   }

   /** @deprecated */
   @Deprecated
   public final BigDecimal getBigDecimal(int var1, int var2) throws SQLException {
      BigDecimal var3 = this.getBigDecimal(var1);
      return var3 != null ? var3.setScale(var2, 5) : null;
   }

   public final BigDecimal getBigDecimal(int var1) throws SQLException {
      this.checkIfClosed("getBigDecimal");

      try {
         DataValueDescriptor var2 = this.getColumn(var1);
         return (this.wasNull = var2.isNull()) ? null : SQLDecimal.getBigDecimal(var2);
      } catch (StandardException var3) {
         throw noStateChangeException(var3);
      }
   }

   /** @deprecated */
   @Deprecated
   public final BigDecimal getBigDecimal(String var1, int var2) throws SQLException {
      this.checkIfClosed("getBigDecimal");
      return this.getBigDecimal(this.findColumnName(var1), var2);
   }

   public final BigDecimal getBigDecimal(String var1) throws SQLException {
      this.checkIfClosed("getBigDecimal");
      return this.getBigDecimal(this.findColumnName(var1));
   }

   public final byte[] getBytes(String var1) throws SQLException {
      this.checkIfClosed("getBytes");
      return this.getBytes(this.findColumnName(var1));
   }

   public final Date getDate(String var1) throws SQLException {
      this.checkIfClosed("getDate");
      return this.getDate(this.findColumnName(var1));
   }

   public final Time getTime(String var1) throws SQLException {
      this.checkIfClosed("getTime");
      return this.getTime(this.findColumnName(var1));
   }

   public final Timestamp getTimestamp(String var1) throws SQLException {
      this.checkIfClosed("getTimestamp");
      return this.getTimestamp(this.findColumnName(var1));
   }

   public final Reader getCharacterStream(String var1) throws SQLException {
      this.checkIfClosed("getCharacterStream");
      return this.getCharacterStream(this.findColumnName(var1));
   }

   public final InputStream getAsciiStream(String var1) throws SQLException {
      this.checkIfClosed("getAsciiStream");
      return this.getAsciiStream(this.findColumnName(var1));
   }

   public final InputStream getBinaryStream(String var1) throws SQLException {
      this.checkIfClosed("getBinaryStream");
      return this.getBinaryStream(this.findColumnName(var1));
   }

   /** @deprecated */
   @Deprecated
   public final InputStream getUnicodeStream(int var1) throws SQLException {
      throw Util.notImplemented("getUnicodeStream");
   }

   /** @deprecated */
   @Deprecated
   public final InputStream getUnicodeStream(String var1) throws SQLException {
      throw Util.notImplemented("getUnicodeStream");
   }

   public URL getURL(int var1) throws SQLException {
      throw Util.notImplemented();
   }

   public URL getURL(String var1) throws SQLException {
      throw Util.notImplemented();
   }

   public final SQLWarning getWarnings() throws SQLException {
      this.checkIfClosed("getWarnings");
      return this.topWarning;
   }

   public final void clearWarnings() throws SQLException {
      this.checkIfClosed("clearWarnings");
      this.topWarning = null;
   }

   public final String getCursorName() throws SQLException {
      this.checkIfClosed("getCursorName");
      return this.theResults.getCursorName();
   }

   public final ResultSetMetaData getMetaData() throws SQLException {
      this.checkIfClosed("getMetaData");
      Object var1 = this.resultDescription.getMetaData();
      if (var1 == null) {
         var1 = this.factory.newEmbedResultSetMetaData(this.resultDescription.getColumnInfo());
         this.resultDescription.setMetaData((ResultSetMetaData)var1);
      }

      return (ResultSetMetaData)var1;
   }

   public final int getHoldability() throws SQLException {
      this.checkIfClosed("getHoldability");
      return this.theResults.getActivation().getResultSetHoldability() ? 1 : 2;
   }

   public final Object getObject(int var1) throws SQLException {
      this.checkIfClosed("getObject");
      int var2 = this.getColumnType(var1);
      switch (var2) {
         case -4:
         case -3:
         case -2:
            return this.getBytes(var1);
         case -1:
         case 1:
         case 12:
            return this.getString(var1);
         case 2004:
            return this.getBlob(var1);
         case 2005:
            return this.getClob(var1);
         default:
            try {
               DataValueDescriptor var3 = this.getColumn(var1);
               return (this.wasNull = var3.isNull()) ? null : var3.getObject();
            } catch (StandardException var4) {
               throw noStateChangeException(var4);
            }
      }
   }

   public final Object getObject(String var1) throws SQLException {
      this.checkIfClosed("getObject");
      return this.getObject(this.findColumnName(var1));
   }

   public Object getObject(int var1, Map var2) throws SQLException {
      this.checkIfClosed("getObject");
      if (var2 == null) {
         throw Util.generateCsSQLException("XJ081.S", var2, "map", "java.sql.ResultSet.getObject");
      } else if (!var2.isEmpty()) {
         throw Util.notImplemented();
      } else {
         return this.getObject(var1);
      }
   }

   public Object getObject(String var1, Map var2) throws SQLException {
      this.checkIfClosed("getObject");
      return this.getObject(this.findColumn(var1), var2);
   }

   public final int findColumn(String var1) throws SQLException {
      this.checkIfClosed("findColumn");
      return this.findColumnName(var1);
   }

   public final Statement getStatement() throws SQLException {
      this.checkIfClosed("getStatement");
      return this.applicationStmt;
   }

   public final void setApplicationStatement(Statement var1) {
      this.applicationStmt = var1;
   }

   public final Ref getRef(int var1) throws SQLException {
      throw Util.notImplemented();
   }

   public final Array getArray(int var1) throws SQLException {
      throw Util.notImplemented();
   }

   public final Ref getRef(String var1) throws SQLException {
      throw Util.notImplemented();
   }

   public final Array getArray(String var1) throws SQLException {
      throw Util.notImplemented();
   }

   public boolean isBeforeFirst() throws SQLException {
      return this.checkRowPosition(101, "isBeforeFirst");
   }

   public boolean isAfterLast() throws SQLException {
      return this.checkRowPosition(104, "isAfterLast");
   }

   public boolean isFirst() throws SQLException {
      return this.checkRowPosition(102, "isFirst");
   }

   public boolean isLast() throws SQLException {
      return this.checkRowPosition(103, "isLast");
   }

   public void beforeFirst() throws SQLException {
      this.checkScrollCursor("beforeFirst()");
      this.movePosition(5, "beforeFirst");
   }

   public void afterLast() throws SQLException {
      this.checkScrollCursor("afterLast()");
      this.movePosition(6, "afterLast");
   }

   public boolean first() throws SQLException {
      this.checkScrollCursor("first()");
      return this.movePosition(1, "first");
   }

   public boolean last() throws SQLException {
      this.checkScrollCursor("last()");
      return this.movePosition(3, "last");
   }

   public int getRow() throws SQLException {
      this.checkScrollCursor("getRow()");
      return this.theResults.getRowNumber();
   }

   public boolean absolute(int var1) throws SQLException {
      this.checkScrollCursor("absolute()");
      return this.movePosition(7, var1, "absolute");
   }

   public boolean relative(int var1) throws SQLException {
      this.checkScrollCursor("relative()");
      return this.movePosition(8, var1, "relative");
   }

   public boolean previous() throws SQLException {
      this.checkScrollCursor("previous()");
      return this.movePosition(4, "previous");
   }

   public void setFetchDirection(int var1) throws SQLException {
      this.checkScrollCursor("setFetchDirection()");
      this.fetchDirection = var1;
   }

   public int getFetchDirection() throws SQLException {
      this.checkIfClosed("getFetchDirection");
      return this.fetchDirection == 0 ? this.stmt.getFetchDirection() : this.fetchDirection;
   }

   public void setFetchSize(int var1) throws SQLException {
      this.checkIfClosed("setFetchSize");
      if (var1 < 0) {
         throw Util.generateCsSQLException("XJ062.S", var1);
      } else {
         if (var1 > 0) {
            this.fetchSize = var1;
         }

      }
   }

   public int getFetchSize() throws SQLException {
      this.checkIfClosed("getFetchSize");
      return this.fetchSize == 0 ? this.stmt.getFetchSize() : this.fetchSize;
   }

   public int getType() throws SQLException {
      this.checkIfClosed("getType");
      return this.stmt.getResultSetType();
   }

   public int getConcurrency() throws SQLException {
      this.checkIfClosed("getConcurrency");
      return this.concurrencyOfThisResultSet;
   }

   public boolean rowUpdated() throws SQLException {
      this.checkIfClosed("rowUpdated");
      this.checkNotOnInsertRow();
      this.checkOnRow();
      boolean var1 = false;

      try {
         if (this.isForUpdate() && this.getType() == 1004) {
            var1 = ((ScrollInsensitiveResultSet)this.theResults).isUpdated();
         }
      } catch (Throwable var3) {
         this.handleException(var3);
      }

      return var1;
   }

   public boolean rowInserted() throws SQLException {
      this.checkIfClosed("rowInserted");
      this.checkNotOnInsertRow();
      this.checkOnRow();
      return false;
   }

   public boolean rowDeleted() throws SQLException {
      this.checkIfClosed("rowDeleted");
      this.checkNotOnInsertRow();
      this.checkOnRow();
      boolean var1 = false;

      try {
         if (this.isForUpdate() && this.getType() == 1004) {
            var1 = ((ScrollInsensitiveResultSet)this.theResults).isDeleted();
         }
      } catch (Throwable var3) {
         this.handleException(var3);
      }

      return var1;
   }

   protected void checksBeforeUpdateXXX(String var1, int var2) throws SQLException {
      this.checksBeforeUpdateOrDelete(var1, var2);
      ResultDescription var3 = this.theResults.getResultDescription();
      if (var2 >= 1 && var2 <= var3.getColumnCount()) {
         if (var3.getColumnDescriptor(var2).getSourceTableName() == null) {
            throw Util.generateCsSQLException("XJ084.U", var1);
         } else if (!this.getMetaData().isWritable(var2)) {
            throw Util.generateCsSQLException("42X31", this.theResults.getResultDescription().getColumnDescriptor(var2).getName(), this.getCursorName());
         }
      } else {
         throw Util.generateCsSQLException("XCL14.S", var2, String.valueOf(var3.getColumnCount()));
      }
   }

   protected void checksBeforeUpdateOrDelete(String var1, int var2) throws SQLException {
      this.checkIfClosed(var1);
      this.checkUpdatableCursor(var1);
      if (!this.isOnInsertRow) {
         this.checkOnRow();
      }

   }

   protected DataValueDescriptor getDVDforColumnToBeUpdated(int var1, String var2) throws StandardException, SQLException {
      this.checksBeforeUpdateXXX(var2, var1);
      this.columnGotUpdated[var1 - 1] = true;
      this.currentRowHasBeenUpdated = true;
      return this.updateRow.getColumn(var1);
   }

   protected void checksBeforeInsert() throws SQLException {
      this.checkIfClosed("insertRow");
      this.checkUpdatableCursor("insertRow");
      if (!this.isOnInsertRow) {
         throw newSQLException("XJ086.S", new Object[0]);
      }
   }

   private void checksBeforeUpdateAsciiStream(int var1) throws SQLException {
      this.checksBeforeUpdateXXX("updateAsciiStream", var1);
      int var2 = this.getColumnType(var1);
      if (!DataTypeDescriptor.isAsciiStreamAssignable(var2)) {
         throw this.dataTypeConversion(var1, "java.io.InputStream");
      }
   }

   private void checksBeforeUpdateBinaryStream(int var1) throws SQLException {
      this.checksBeforeUpdateXXX("updateBinaryStream", var1);
      int var2 = this.getColumnType(var1);
      if (!DataTypeDescriptor.isBinaryStreamAssignable(var2)) {
         throw this.dataTypeConversion(var1, "java.io.InputStream");
      }
   }

   private void checksBeforeUpdateCharacterStream(int var1) throws SQLException {
      this.checksBeforeUpdateXXX("updateCharacterStream", var1);
      int var2 = this.getColumnType(var1);
      if (!DataTypeDescriptor.isCharacterStreamAssignable(var2)) {
         throw this.dataTypeConversion(var1, "java.io.Reader");
      }
   }

   public void updateNull(int var1) throws SQLException {
      try {
         this.getDVDforColumnToBeUpdated(var1, "updateNull").setToNull();
      } catch (StandardException var3) {
         throw noStateChangeException(var3);
      }
   }

   public void updateBoolean(int var1, boolean var2) throws SQLException {
      try {
         this.getDVDforColumnToBeUpdated(var1, "updateBoolean").setValue(var2);
      } catch (StandardException var4) {
         throw noStateChangeException(var4);
      }
   }

   public void updateByte(int var1, byte var2) throws SQLException {
      try {
         this.getDVDforColumnToBeUpdated(var1, "updateByte").setValue(var2);
      } catch (StandardException var4) {
         throw noStateChangeException(var4);
      }
   }

   public void updateShort(int var1, short var2) throws SQLException {
      try {
         this.getDVDforColumnToBeUpdated(var1, "updateShort").setValue(var2);
      } catch (StandardException var4) {
         throw noStateChangeException(var4);
      }
   }

   public void updateInt(int var1, int var2) throws SQLException {
      try {
         this.getDVDforColumnToBeUpdated(var1, "updateInt").setValue(var2);
      } catch (StandardException var4) {
         throw noStateChangeException(var4);
      }
   }

   public void updateLong(int var1, long var2) throws SQLException {
      try {
         this.getDVDforColumnToBeUpdated(var1, "updateLong").setValue(var2);
      } catch (StandardException var5) {
         throw noStateChangeException(var5);
      }
   }

   public void updateFloat(int var1, float var2) throws SQLException {
      try {
         this.getDVDforColumnToBeUpdated(var1, "updateFloat").setValue(var2);
      } catch (StandardException var4) {
         throw noStateChangeException(var4);
      }
   }

   public void updateDouble(int var1, double var2) throws SQLException {
      try {
         this.getDVDforColumnToBeUpdated(var1, "updateDouble").setValue(var2);
      } catch (StandardException var5) {
         throw noStateChangeException(var5);
      }
   }

   public void updateBigDecimal(int var1, BigDecimal var2) throws SQLException {
      try {
         this.getDVDforColumnToBeUpdated(var1, "updateBigDecimal").setBigDecimal(var2);
      } catch (StandardException var4) {
         throw noStateChangeException(var4);
      }
   }

   public void updateBigDecimal(String var1, BigDecimal var2) throws SQLException {
      this.checkIfClosed("updateBigDecimal");
      this.updateBigDecimal(this.findColumnName(var1), var2);
   }

   public void updateString(int var1, String var2) throws SQLException {
      try {
         this.getDVDforColumnToBeUpdated(var1, "updateString").setValue(var2);
      } catch (StandardException var4) {
         throw noStateChangeException(var4);
      }
   }

   public void updateBytes(int var1, byte[] var2) throws SQLException {
      try {
         this.getDVDforColumnToBeUpdated(var1, "updateBytes").setValue(var2);
      } catch (StandardException var4) {
         throw noStateChangeException(var4);
      }
   }

   public void updateDate(int var1, Date var2) throws SQLException {
      try {
         this.getDVDforColumnToBeUpdated(var1, "updateDate").setValue(var2);
      } catch (StandardException var4) {
         throw noStateChangeException(var4);
      }
   }

   public void updateTime(int var1, Time var2) throws SQLException {
      try {
         this.getDVDforColumnToBeUpdated(var1, "updateTime").setValue(var2);
      } catch (StandardException var4) {
         throw noStateChangeException(var4);
      }
   }

   public void updateTimestamp(int var1, Timestamp var2) throws SQLException {
      try {
         this.getDVDforColumnToBeUpdated(var1, "updateTimestamp").setValue(var2);
      } catch (StandardException var4) {
         throw noStateChangeException(var4);
      }
   }

   public void updateAsciiStream(int var1, InputStream var2, long var3) throws SQLException {
      this.checksBeforeUpdateAsciiStream(var1);
      InputStreamReader var5 = null;
      if (var2 != null) {
         try {
            var5 = new InputStreamReader(var2, "ISO-8859-1");
         } catch (UnsupportedEncodingException var7) {
            throw new SQLException(var7.getMessage());
         }
      }

      this.updateCharacterStreamInternal(var1, var5, false, var3, "updateAsciiStream");
   }

   public void updateAsciiStream(int var1, InputStream var2) throws SQLException {
      this.checksBeforeUpdateAsciiStream(var1);
      InputStreamReader var3 = null;
      if (var2 != null) {
         try {
            var3 = new InputStreamReader(var2, "ISO-8859-1");
         } catch (UnsupportedEncodingException var5) {
            throw new SQLException(var5.getMessage());
         }
      }

      this.updateCharacterStreamInternal(var1, var3, true, -1L, "updateAsciiStream");
   }

   public void updateBinaryStream(int var1, InputStream var2, long var3) throws SQLException {
      this.checksBeforeUpdateBinaryStream(var1);
      if (var2 == null) {
         this.updateNull(var1);
      } else {
         this.updateBinaryStreamInternal(var1, var2, false, var3, "updateBinaryStream");
      }
   }

   public void updateBinaryStream(int var1, InputStream var2) throws SQLException {
      this.checksBeforeUpdateBinaryStream(var1);
      this.updateBinaryStreamInternal(var1, var2, true, -1L, "updateBinaryStream");
   }

   private void updateBinaryStreamInternal(int var1, InputStream var2, boolean var3, long var4, String var6) throws SQLException {
      RawToBinaryFormatStream var7;
      if (!var3) {
         if (var4 < 0L) {
            throw newSQLException("XJ025.S", new Object[0]);
         }

         if (var4 > 2147483647L) {
            throw newSQLException("22003", new Object[]{this.getColumnSQLType(var1)});
         }

         var7 = new RawToBinaryFormatStream(var2, (int)var4);
      } else {
         var4 = -1L;
         var7 = new RawToBinaryFormatStream(var2, this.getMaxColumnWidth(var1), this.getColumnSQLType(var1));
      }

      try {
         this.getDVDforColumnToBeUpdated(var1, var6).setValue(var7, (int)var4);
      } catch (StandardException var9) {
         throw noStateChangeException(var9);
      }
   }

   public void updateCharacterStream(int var1, Reader var2, long var3) throws SQLException {
      this.checksBeforeUpdateCharacterStream(var1);
      this.updateCharacterStreamInternal(var1, var2, false, var3, "updateCharacterStream");
   }

   public void updateCharacterStream(int var1, Reader var2) throws SQLException {
      this.checksBeforeUpdateCharacterStream(var1);
      this.updateCharacterStreamInternal(var1, var2, true, -1L, "updateCharacterStream");
   }

   private void updateCharacterStreamInternal(int var1, Reader var2, boolean var3, long var4, String var6) throws SQLException {
      try {
         if (var2 == null) {
            this.updateNull(var1);
         } else {
            StringDataValue var7 = (StringDataValue)this.getDVDforColumnToBeUpdated(var1, var6);
            var7.setStreamHeaderFormat(!this.getEmbedConnection().getDatabase().getDataDictionary().checkVersion(170, (String)null));
            int var9 = -1;
            ReaderToUTF8Stream var8;
            if (!var3) {
               if (var4 < 0L) {
                  throw newSQLException("XJ025.S", new Object[0]);
               }

               if (var4 > 2147483647L) {
                  throw newSQLException("22003", new Object[]{this.getColumnSQLType(var1)});
               }

               var9 = (int)var4;
               int var10 = 0;
               if (this.getColumnType(var1) == 2005) {
                  int var11 = this.getMaxColumnWidth(var1);
                  if (var9 > var11) {
                     var10 = var9 - var11;
                     var9 = var11;
                  }
               }

               var8 = new ReaderToUTF8Stream(var2, var9, var10, this.getColumnSQLType(var1), var7.getStreamHeaderGenerator());
            } else {
               int var13 = this.getMaxColumnWidth(var1);
               var8 = new ReaderToUTF8Stream(var2, var13, this.getColumnSQLType(var1), var7.getStreamHeaderGenerator());
            }

            var7.setValue(var8, var9);
         }
      } catch (StandardException var12) {
         throw noStateChangeException(var12);
      }
   }

   public void updateObject(int var1, Object var2, int var3) throws SQLException {
      this.updateObject(var1, var2);
      this.adjustScale(var1, var3);
   }

   protected void adjustScale(int var1, int var2) throws SQLException {
      int var3 = this.getColumnType(var1);
      if (var3 == 3 || var3 == 2) {
         if (var2 < 0) {
            throw newSQLException("XJ044.S", new Object[]{var2});
         }

         try {
            DataValueDescriptor var4 = this.updateRow.getColumn(var1);
            int var5 = var4.getLength();
            ((VariableSizeDataValue)var4).setWidth(-1, var2, false);
         } catch (StandardException var6) {
            throw noStateChangeException(var6);
         }
      }

   }

   public void updateObject(int var1, Object var2) throws SQLException {
      this.checksBeforeUpdateXXX("updateObject", var1);
      int var3 = this.getColumnType(var1);
      if (var3 == 2000) {
         try {
            ((UserDataValue)this.getDVDforColumnToBeUpdated(var1, "updateObject")).setValue(var2);
         } catch (StandardException var5) {
            throw noStateChangeException(var5);
         }
      } else if (var2 == null) {
         this.updateNull(var1);
      } else if (var2 instanceof String) {
         this.updateString(var1, (String)var2);
      } else if (var2 instanceof Boolean) {
         this.updateBoolean(var1, (Boolean)var2);
      } else if (var2 instanceof Short) {
         this.updateShort(var1, (Short)var2);
      } else if (var2 instanceof Integer) {
         this.updateInt(var1, (Integer)var2);
      } else if (var2 instanceof Long) {
         this.updateLong(var1, (Long)var2);
      } else if (var2 instanceof Float) {
         this.updateFloat(var1, (Float)var2);
      } else if (var2 instanceof Double) {
         this.updateDouble(var1, (Double)var2);
      } else if (var2 instanceof BigDecimal) {
         this.updateBigDecimal(var1, (BigDecimal)var2);
      } else if (var2 instanceof byte[]) {
         this.updateBytes(var1, (byte[])var2);
      } else if (var2 instanceof Date) {
         this.updateDate(var1, (Date)var2);
      } else if (var2 instanceof Time) {
         this.updateTime(var1, (Time)var2);
      } else if (var2 instanceof Timestamp) {
         this.updateTimestamp(var1, (Timestamp)var2);
      } else if (var2 instanceof Blob) {
         this.updateBlob(var1, (Blob)var2);
      } else if (var2 instanceof Clob) {
         this.updateClob(var1, (Clob)var2);
      } else {
         throw this.dataTypeConversion(var1, var2.getClass().getName());
      }
   }

   public void updateNull(String var1) throws SQLException {
      this.checkIfClosed("updateNull");
      this.updateNull(this.findColumnName(var1));
   }

   public void updateBoolean(String var1, boolean var2) throws SQLException {
      this.checkIfClosed("updateBoolean");
      this.updateBoolean(this.findColumnName(var1), var2);
   }

   public void updateByte(String var1, byte var2) throws SQLException {
      this.checkIfClosed("updateByte");
      this.updateByte(this.findColumnName(var1), var2);
   }

   public void updateShort(String var1, short var2) throws SQLException {
      this.checkIfClosed("updateShort");
      this.updateShort(this.findColumnName(var1), var2);
   }

   public void updateInt(String var1, int var2) throws SQLException {
      this.checkIfClosed("updateInt");
      this.updateInt(this.findColumnName(var1), var2);
   }

   public void updateLong(String var1, long var2) throws SQLException {
      this.checkIfClosed("updateLong");
      this.updateLong(this.findColumnName(var1), var2);
   }

   public void updateFloat(String var1, float var2) throws SQLException {
      this.checkIfClosed("updateFloat");
      this.updateFloat(this.findColumnName(var1), var2);
   }

   public void updateDouble(String var1, double var2) throws SQLException {
      this.checkIfClosed("updateDouble");
      this.updateDouble(this.findColumnName(var1), var2);
   }

   public void updateString(String var1, String var2) throws SQLException {
      this.checkIfClosed("updateString");
      this.updateString(this.findColumnName(var1), var2);
   }

   public void updateBytes(String var1, byte[] var2) throws SQLException {
      this.checkIfClosed("updateBytes");
      this.updateBytes(this.findColumnName(var1), var2);
   }

   public void updateDate(String var1, Date var2) throws SQLException {
      this.checkIfClosed("updateDate");
      this.updateDate(this.findColumnName(var1), var2);
   }

   public void updateTime(String var1, Time var2) throws SQLException {
      this.checkIfClosed("updateTime");
      this.updateTime(this.findColumnName(var1), var2);
   }

   public void updateTimestamp(String var1, Timestamp var2) throws SQLException {
      this.checkIfClosed("updateTimestamp");
      this.updateTimestamp(this.findColumnName(var1), var2);
   }

   public void updateAsciiStream(String var1, InputStream var2, int var3) throws SQLException {
      this.checkIfClosed("updateAsciiStream");
      this.updateAsciiStream(this.findColumnName(var1), var2, var3);
   }

   public void updateBinaryStream(String var1, InputStream var2, int var3) throws SQLException {
      this.checkIfClosed("updateBinaryStream");
      this.updateBinaryStream(this.findColumnName(var1), var2, var3);
   }

   public void updateCharacterStream(String var1, Reader var2, int var3) throws SQLException {
      this.checkIfClosed("updateCharacterStream");
      this.updateCharacterStream(this.findColumnName(var1), var2, var3);
   }

   public void updateObject(String var1, Object var2, int var3) throws SQLException {
      this.checkIfClosed("updateObject");
      this.updateObject(this.findColumnName(var1), var2, var3);
   }

   public void updateObject(String var1, Object var2) throws SQLException {
      this.checkIfClosed("updateObject");
      this.updateObject(this.findColumnName(var1), var2);
   }

   public void insertRow() throws SQLException {
      synchronized(this.getConnectionSynchronization()) {
         this.checksBeforeInsert();
         this.setupContextStack();
         LanguageConnectionContext var2 = this.getLanguageConnectionContext(this.getEmbedConnection());
         StatementContext var3 = null;

         try {
            boolean var4 = false;
            StringBuffer var5 = new StringBuffer("INSERT INTO ");
            StringBuffer var6 = new StringBuffer("VALUES (");
            CursorActivation var7 = var2.lookupCursorActivation(this.getCursorName());
            ExecCursorTableReference var8 = var7.getPreparedStatement().getTargetTable();
            var5.append(this.getFullBaseTableName(var8));
            ResultDescription var9 = this.theResults.getResultDescription();
            var5.append(" (");

            for(int var10 = 1; var10 <= var9.getColumnCount(); ++var10) {
               if (var4) {
                  var5.append(",");
                  var6.append(",");
               }

               var5.append(IdUtil.normalToDelimited(var9.getColumnDescriptor(var10).getName()));
               if (this.columnGotUpdated[var10 - 1]) {
                  var6.append("?");
               } else {
                  var6.append("DEFAULT");
               }

               var4 = true;
            }

            var5.append(") ");
            var6.append(") ");
            var5.append(var6);
            StatementContext var24 = var2.getStatementContext();
            Activation var11 = null;
            if (var24 != null) {
               var11 = var24.getActivation();
            }

            var3 = var2.pushStatementContext(this.isAtomic, false, var5.toString(), (ParameterValueSet)null, false, 0L);
            var3.setActivation(var11);
            PreparedStatement var12 = var2.prepareInternalStatement(var5.toString());
            Activation var13 = var12.getActivation(var2, false);
            var3.setActivation(var13);
            int var14 = 1;

            for(int var15 = 0; var14 <= var9.getColumnCount(); ++var14) {
               if (this.columnGotUpdated[var14 - 1]) {
                  var13.getParameterValueSet().getParameterForSet(var15++).setValue(this.updateRow.getColumn(var14));
               }
            }

            var12.executeSubStatement(var7, var13, true, 0L);
            var13.close();
            var2.popStatementContext(var3, (Throwable)null);
            InterruptStatus.restoreIntrFlagIfSeen(var2);
         } catch (Throwable var21) {
            throw this.closeOnTransactionError(var21);
         } finally {
            if (var3 != null) {
               var2.popStatementContext(var3, (Throwable)null);
            }

            this.restoreContextStack();
         }

      }
   }

   public void updateRow() throws SQLException {
      synchronized(this.getConnectionSynchronization()) {
         this.checksBeforeUpdateOrDelete("updateRow", -1);
         this.checkNotOnInsertRow();
         this.setupContextStack();
         LanguageConnectionContext var2 = this.getLanguageConnectionContext(this.getEmbedConnection());
         StatementContext var3 = null;

         try {
            if (this.currentRowHasBeenUpdated) {
               boolean var4 = false;
               StringBuffer var5 = new StringBuffer("UPDATE ");
               CursorActivation var6 = var2.lookupCursorActivation(this.getCursorName());
               ExecCursorTableReference var7 = var6.getPreparedStatement().getTargetTable();
               var5.append(this.getFullBaseTableName(var7));
               var5.append(" SET ");
               ResultDescription var8 = this.theResults.getResultDescription();

               for(int var9 = 1; var9 <= var8.getColumnCount(); ++var9) {
                  if (this.columnGotUpdated[var9 - 1]) {
                     if (var4) {
                        var5.append(",");
                     }

                     ResultColumnDescriptor var10001 = var8.getColumnDescriptor(var9);
                     var5.append(IdUtil.normalToDelimited(var10001.getName()) + "=?");
                     var4 = true;
                  }
               }

               var5.append(" WHERE CURRENT OF " + IdUtil.normalToDelimited(this.getCursorName()));
               StatementContext var23 = var2.getStatementContext();
               Activation var10 = null;
               if (var23 != null) {
                  var10 = var23.getActivation();
               }

               var3 = var2.pushStatementContext(this.isAtomic, false, var5.toString(), (ParameterValueSet)null, false, 0L);
               var3.setActivation(var10);
               PreparedStatement var11 = var2.prepareInternalStatement(var5.toString());
               Activation var12 = var11.getActivation(var2, false);
               var3.setActivation(var12);
               int var13 = 1;

               for(int var14 = 0; var13 <= var8.getColumnCount(); ++var13) {
                  if (this.columnGotUpdated[var13 - 1]) {
                     var12.getParameterValueSet().getParameterForSet(var14++).setValue(this.updateRow.getColumn(var13));
                  }
               }

               var11.executeSubStatement(var6, var12, true, 0L);
               SQLWarning var24 = var12.getWarnings();
               if (var24 != null) {
                  this.addWarning(var24);
               }

               var12.close();
               if (this.getType() == 1003) {
                  this.currentRow = null;
               } else {
                  this.movePosition(8, 0, "relative");
               }

               var2.popStatementContext(var3, (Throwable)null);
               InterruptStatus.restoreIntrFlagIfSeen(var2);
               return;
            }
         } catch (Throwable var20) {
            throw this.closeOnTransactionError(var20);
         } finally {
            if (var3 != null) {
               var2.popStatementContext(var3, (Throwable)null);
            }

            this.restoreContextStack();
            this.initializeUpdateRowModifiers();
         }

      }
   }

   public void deleteRow() throws SQLException {
      synchronized(this.getConnectionSynchronization()) {
         this.checksBeforeUpdateOrDelete("deleteRow", -1);
         this.checkNotOnInsertRow();
         this.setupContextStack();
         LanguageConnectionContext var2 = this.getLanguageConnectionContext(this.getEmbedConnection());
         StatementContext var3 = null;

         try {
            StringBuffer var4 = new StringBuffer("DELETE FROM ");
            CursorActivation var5 = var2.lookupCursorActivation(this.getCursorName());
            var4.append(this.getFullBaseTableName(var5.getPreparedStatement().getTargetTable()));
            var4.append(" WHERE CURRENT OF " + IdUtil.normalToDelimited(this.getCursorName()));
            StatementContext var6 = var2.getStatementContext();
            Activation var7 = null;
            if (var6 != null) {
               var7 = var6.getActivation();
            }

            var3 = var2.pushStatementContext(this.isAtomic, false, var4.toString(), (ParameterValueSet)null, false, 0L);
            var3.setActivation(var7);
            PreparedStatement var8 = var2.prepareInternalStatement(var4.toString());
            Activation var9 = var8.getActivation(var2, false);
            var3.setActivation(var9);
            var8.executeSubStatement(var5, var9, true, 0L);
            SQLWarning var11 = var9.getWarnings();
            if (var11 != null) {
               this.addWarning(var11);
            }

            var9.close();
            this.currentRow = null;
            var2.popStatementContext(var3, (Throwable)null);
            InterruptStatus.restoreIntrFlagIfSeen(var2);
         } catch (Throwable var17) {
            throw this.closeOnTransactionError(var17);
         } finally {
            if (var3 != null) {
               var2.popStatementContext(var3, (Throwable)null);
            }

            this.restoreContextStack();
            this.initializeUpdateRowModifiers();
         }

      }
   }

   private String getFullBaseTableName(ExecCursorTableReference var1) {
      return IdUtil.mkQualifiedName(var1.getSchemaName(), var1.getBaseName());
   }

   public void refreshRow() throws SQLException {
      throw Util.notImplemented();
   }

   public void cancelRowUpdates() throws SQLException {
      this.checksBeforeUpdateOrDelete("cancelRowUpdates", -1);
      this.checkNotOnInsertRow();
      this.initializeUpdateRowModifiers();
   }

   public void moveToInsertRow() throws SQLException {
      this.checkExecIfClosed("moveToInsertRow");
      this.checkUpdatableCursor("moveToInsertRow");
      synchronized(this.getConnectionSynchronization()) {
         try {
            this.setupContextStack();
            this.initializeUpdateRowModifiers();
            this.isOnInsertRow = true;

            for(int var2 = 1; var2 <= this.columnGotUpdated.length; ++var2) {
               this.updateRow.setColumn(var2, this.resultDescription.getColumnDescriptor(var2).getType().getNull());
            }

            InterruptStatus.restoreIntrFlagIfSeen(this.getLanguageConnectionContext(this.getEmbedConnection()));
         } catch (Throwable var8) {
            this.handleException(var8);
         } finally {
            this.restoreContextStack();
         }

      }
   }

   public void moveToCurrentRow() throws SQLException {
      this.checkExecIfClosed("moveToCurrentRow");
      this.checkUpdatableCursor("moveToCurrentRow");
      synchronized(this.getConnectionSynchronization()) {
         try {
            if (this.isOnInsertRow) {
               this.initializeUpdateRowModifiers();
               this.isOnInsertRow = false;
            }

            InterruptStatus.restoreIntrFlagIfSeen();
         } catch (Throwable var4) {
            this.handleException(var4);
         }

      }
   }

   public Blob getBlob(int var1) throws SQLException {
      this.closeCurrentStream();
      this.checkIfClosed("getBlob");
      this.useStreamOrLOB(var1);
      synchronized(this.getConnectionSynchronization()) {
         int var3 = this.getColumnType(var1);
         if (var3 != 2004) {
            throw this.dataTypeConversion("java.sql.Blob", var1);
         } else {
            boolean var4 = false;

            EmbedBlob var7;
            try {
               DataValueDescriptor var5 = this.getColumn(var1);
               EmbedConnection var6 = this.getEmbedConnection();
               if (!(this.wasNull = var5.isNull())) {
                  if (var5.hasStream()) {
                     var4 = true;
                  }

                  if (var4) {
                     this.setupContextStack();
                  }

                  var7 = new EmbedBlob(var5, var6);
                  restoreIntrFlagIfSeen(var4, var6);
                  EmbedBlob var8 = var7;
                  return var8;
               }

               InterruptStatus.restoreIntrFlagIfSeen();
               var7 = null;
            } catch (Throwable var14) {
               throw this.handleException(var14);
            } finally {
               if (var4) {
                  this.restoreContextStack();
               }

            }

            return var7;
         }
      }
   }

   public final Clob getClob(int var1) throws SQLException {
      this.closeCurrentStream();
      this.checkIfClosed("getClob");
      this.useStreamOrLOB(var1);
      synchronized(this.getConnectionSynchronization()) {
         int var3 = this.getColumnType(var1);
         if (var3 != 2005) {
            throw this.dataTypeConversion("java.sql.Clob", var1);
         } else {
            boolean var4 = false;
            EmbedConnection var5 = this.getEmbedConnection();

            EmbedClob var8;
            try {
               StringDataValue var6 = (StringDataValue)this.getColumn(var1);
               this.getLanguageConnectionContext(var5);
               if (!(this.wasNull = var6.isNull())) {
                  if (var6.hasStream()) {
                     var4 = true;
                     this.setupContextStack();
                  }

                  var8 = new EmbedClob(var5, var6);
                  restoreIntrFlagIfSeen(var4, var5);
                  EmbedClob var9 = var8;
                  return var9;
               }

               InterruptStatus.restoreIntrFlagIfSeen();
               var8 = null;
            } catch (Throwable var15) {
               throw this.handleException(var15);
            } finally {
               if (var4) {
                  this.restoreContextStack();
               }

            }

            return var8;
         }
      }
   }

   public final Blob getBlob(String var1) throws SQLException {
      this.checkIfClosed("getBlob");
      return this.getBlob(this.findColumnName(var1));
   }

   public final Clob getClob(String var1) throws SQLException {
      this.checkIfClosed("getClob");
      return this.getClob(this.findColumnName(var1));
   }

   public void updateBlob(int var1, Blob var2) throws SQLException {
      this.checksBeforeUpdateXXX("updateBlob", var1);
      int var3 = this.getColumnType(var1);
      if (var3 != 2004) {
         throw this.dataTypeConversion(var1, "java.sql.Blob");
      } else {
         if (var2 == null) {
            this.updateNull(var1);
         } else {
            long var4 = var2.length();
            this.updateBinaryStreamInternal(var1, var2.getBinaryStream(), false, var4, "updateBlob");
         }

      }
   }

   public void updateBlob(String var1, Blob var2) throws SQLException {
      this.checkIfClosed("updateBlob");
      this.updateBlob(this.findColumnName(var1), var2);
   }

   public void updateClob(int var1, Clob var2) throws SQLException {
      this.checksBeforeUpdateXXX("updateClob", var1);
      int var3 = this.getColumnType(var1);
      if (var3 != 2005) {
         throw this.dataTypeConversion(var1, "java.sql.Clob");
      } else {
         if (var2 == null) {
            this.updateNull(var1);
         } else {
            long var4 = var2.length();
            this.updateCharacterStreamInternal(var1, var2.getCharacterStream(), false, var4, "updateClob");
         }

      }
   }

   public void updateClob(String var1, Clob var2) throws SQLException {
      this.checkIfClosed("updateClob");
      this.updateClob(this.findColumnName(var1), var2);
   }

   protected int findColumnName(String var1) throws SQLException {
      if (var1 == null) {
         throw newSQLException("XJ018.S", new Object[0]);
      } else {
         int var2 = this.resultDescription.findColumnInsenstive(var1);
         if (var2 == -1) {
            throw newSQLException("S0022", new Object[]{var1});
         } else {
            return var2;
         }
      }
   }

   private final void closeCurrentStream() {
      if (this.currentStream != null) {
         try {
            synchronized(this) {
               if (this.currentStream != null) {
                  if (this.currentStream instanceof Reader) {
                     ((Reader)this.currentStream).close();
                  } else {
                     ((InputStream)this.currentStream).close();
                  }
               }
            }
         } catch (IOException var8) {
         } finally {
            this.currentStream = null;
         }
      }

   }

   final void checkIfClosed(String var1) throws SQLException {
      if (this.isClosed || this.theResults.isClosed()) {
         if (!this.isClosed) {
            this.closeCurrentStream();
            this.markClosed();
         }

         throw newSQLException("XCL16.S", new Object[]{var1});
      }
   }

   final void checkExecIfClosed(String var1) throws SQLException {
      this.checkIfClosed(var1);
      Connection var2 = this.getEmbedConnection().getApplicationConnection();
      if (var2 == null) {
         throw Util.noCurrentConnection();
      } else if (var2.isClosed()) {
         this.closeCurrentStream();
         this.markClosed();
         throw Util.noCurrentConnection();
      }
   }

   protected String getSQLText() {
      return this.stmt == null ? null : this.stmt.getSQLText();
   }

   protected ParameterValueSet getParameterValueSet() {
      return this.stmt == null ? null : this.stmt.getParameterValueSet();
   }

   private static boolean isMaxFieldSizeType(int var0) {
      return var0 == -2 || var0 == -3 || var0 == -4 || var0 == 1 || var0 == 12 || var0 == -1;
   }

   final SQLException closeOnTransactionError(Throwable var1) throws SQLException {
      SQLException var2 = this.handleException(var1);
      if (var1 instanceof StandardException var3) {
         int var4 = var3.getSeverity();
         if (var4 == 30000) {
            try {
               this.close();
            } catch (Throwable var6) {
               var2.setNextException(this.handleException(var6));
            }
         }
      }

      return var2;
   }

   protected final DataValueDescriptor getColumn(int var1) throws SQLException, StandardException {
      this.closeCurrentStream();
      if (var1 >= 1 && var1 <= this.resultDescription.getColumnCount()) {
         if (!this.isOnInsertRow && (!this.currentRowHasBeenUpdated || !this.columnGotUpdated[var1 - 1])) {
            this.checkOnRow();
            return this.currentRow.getColumn(var1);
         } else {
            return this.updateRow.getColumn(var1);
         }
      } else {
         throw newSQLException("S0022", new Object[]{var1});
      }
   }

   static final SQLException noStateChangeException(Throwable var0) {
      return TransactionResourceImpl.wrapInSQLException(var0);
   }

   void setDynamicResultSet(EmbedStatement var1) {
      if (var1 != null) {
         this.owningStmt = var1;
         this.applicationStmt = var1.applicationStatement;
         this.localConn = var1.getEmbedConnection();
      } else {
         this.localConn = this.localConn.rootConnection;
      }

      this.singleUseActivation = this.theResults.getActivation();
   }

   public final int compareTo(Object var1) {
      EmbedResultSet var2 = (EmbedResultSet)var1;
      return this.order - var2.order;
   }

   private void checkScrollCursor(String var1) throws SQLException {
      this.checkIfClosed(var1);
      if (this.stmt.getResultSetType() == 1003) {
         throw Util.generateCsSQLException("XJ061.S", var1);
      }
   }

   private void checkUpdatableCursor(String var1) throws SQLException {
      if (this.getConcurrency() != 1008) {
         throw Util.generateCsSQLException("XJ083.U", var1);
      }
   }

   private boolean checkRowPosition(int var1, String var2) throws SQLException {
      this.checkScrollCursor(var2);
      synchronized(this.getConnectionSynchronization()) {
         this.setupContextStack();
         LanguageConnectionContext var4 = this.getLanguageConnectionContext(this.getEmbedConnection());

         boolean var7;
         try {
            StatementContext var5 = var4.pushStatementContext(this.isAtomic, this.concurrencyOfThisResultSet == 1007, this.getSQLText(), this.getParameterValueSet(), false, 0L);
            boolean var6 = this.theResults.checkRowPosition(var1);
            var4.popStatementContext(var5, (Throwable)null);
            InterruptStatus.restoreIntrFlagIfSeen(var4);
            var7 = var6;
         } catch (Throwable var13) {
            throw this.closeOnTransactionError(var13);
         } finally {
            this.restoreContextStack();
         }

         return var7;
      }
   }

   public final boolean isForUpdate() {
      return this.theResults instanceof NoPutResultSet ? ((NoPutResultSet)this.theResults).isForUpdate() : false;
   }

   final String getColumnSQLType(int var1) {
      return this.resultDescription.getColumnDescriptor(var1).getType().getTypeId().getSQLTypeName();
   }

   private final int getMaxColumnWidth(int var1) {
      return this.resultDescription.getColumnDescriptor(var1).getType().getMaximumWidth();
   }

   private final SQLException dataTypeConversion(String var1, int var2) {
      return newSQLException("22005", new Object[]{var1, this.getColumnSQLType(var2)});
   }

   private final SQLException dataTypeConversion(int var1, String var2) {
      return newSQLException("22005", new Object[]{this.getColumnSQLType(var1), var2});
   }

   final void useStreamOrLOB(int var1) throws SQLException {
      this.checkLOBMultiCall(var1);
      this.columnUsedFlags[var1 - 1] = true;
   }

   private void checkLOBMultiCall(int var1) throws SQLException {
      if (this.columnUsedFlags == null) {
         this.columnUsedFlags = new boolean[this.getMetaData().getColumnCount()];
      } else if (this.columnUsedFlags[var1 - 1]) {
         throw newSQLException("XCL18.S", new Object[0]);
      }

   }

   public final boolean isClosed() throws SQLException {
      if (this.isClosed) {
         return true;
      } else {
         try {
            this.checkExecIfClosed("");
            return false;
         } catch (SQLException var2) {
            return this.isClosed;
         }
      }
   }

   private void addWarning(SQLWarning var1) {
      if (this.topWarning == null) {
         this.topWarning = var1;
      } else {
         this.topWarning.setNextWarning(var1);
      }

   }

   public void updateAsciiStream(int var1, InputStream var2, int var3) throws SQLException {
      this.checkIfClosed("updateAsciiStream");
      this.updateAsciiStream(var1, var2, (long)var3);
   }

   public void updateBinaryStream(int var1, InputStream var2, int var3) throws SQLException {
      this.checkIfClosed("updateBinaryStream");
      this.updateBinaryStream(var1, var2, (long)var3);
   }

   public void updateCharacterStream(int var1, Reader var2, int var3) throws SQLException {
      this.checkIfClosed("updateCharacterStream");
      this.updateCharacterStream(var1, var2, (long)var3);
   }

   public void updateAsciiStream(String var1, InputStream var2, long var3) throws SQLException {
      this.checkIfClosed("updateAsciiStream");
      this.updateAsciiStream(this.findColumnName(var1), var2, var3);
   }

   public void updateAsciiStream(String var1, InputStream var2) throws SQLException {
      this.checkIfClosed("updateAsciiStream");
      this.updateAsciiStream(this.findColumnName(var1), var2);
   }

   public void updateBinaryStream(String var1, InputStream var2, long var3) throws SQLException {
      this.checkIfClosed("updateBinaryStream");
      this.updateBinaryStream(this.findColumnName(var1), var2, var3);
   }

   public void updateBinaryStream(String var1, InputStream var2) throws SQLException {
      this.checkIfClosed("updateBinaryStream");
      this.updateBinaryStream(this.findColumnName(var1), var2);
   }

   public void updateCharacterStream(String var1, Reader var2, long var3) throws SQLException {
      this.checkIfClosed("updateCharacterStream");
      this.updateCharacterStream(this.findColumnName(var1), var2, var3);
   }

   public void updateCharacterStream(String var1, Reader var2) throws SQLException {
      this.checkIfClosed("updateCharacterStream");
      this.updateCharacterStream(this.findColumnName(var1), var2);
   }

   public void updateBlob(int var1, InputStream var2, long var3) throws SQLException {
      this.checksBeforeUpdateXXX("updateBlob", var1);
      int var5 = this.getColumnType(var1);
      if (var5 != 2004) {
         throw this.dataTypeConversion(var1, "java.sql.Blob");
      } else {
         if (var2 == null) {
            this.updateNull(var1);
         } else {
            this.updateBinaryStreamInternal(var1, var2, false, var3, "updateBlob");
         }

      }
   }

   public void updateBlob(int var1, InputStream var2) throws SQLException {
      this.checksBeforeUpdateXXX("updateBlob", var1);
      int var3 = this.getColumnType(var1);
      if (var3 != 2004) {
         throw this.dataTypeConversion(var1, "java.sql.Blob");
      } else {
         this.updateBinaryStreamInternal(var1, var2, true, -1L, "updateBlob");
      }
   }

   public void updateBlob(String var1, InputStream var2, long var3) throws SQLException {
      this.checkIfClosed("updateBlob");
      this.updateBlob(this.findColumnName(var1), var2, var3);
   }

   public void updateBlob(String var1, InputStream var2) throws SQLException {
      this.checkIfClosed("updateBlob");
      this.updateBlob(this.findColumnName(var1), var2);
   }

   public void updateClob(int var1, Reader var2, long var3) throws SQLException {
      this.checksBeforeUpdateXXX("updateClob", var1);
      int var5 = this.getColumnType(var1);
      if (var5 != 2005) {
         throw this.dataTypeConversion(var1, "java.sql.Clob");
      } else {
         if (var2 == null) {
            this.updateNull(var1);
         } else {
            this.updateCharacterStreamInternal(var1, var2, false, var3, "updateClob");
         }

      }
   }

   public void updateClob(int var1, Reader var2) throws SQLException {
      this.checksBeforeUpdateXXX("updateClob", var1);
      int var3 = this.getColumnType(var1);
      if (var3 != 2005) {
         throw this.dataTypeConversion(var1, "java.sql.Clob");
      } else {
         this.updateCharacterStreamInternal(var1, var2, true, -1L, "updateClob");
      }
   }

   public void updateClob(String var1, Reader var2, long var3) throws SQLException {
      this.checkIfClosed("updateClob");
      this.updateClob(this.findColumnName(var1), var2, var3);
   }

   public void updateClob(String var1, Reader var2) throws SQLException {
      this.checkIfClosed("updateClob");
      this.updateClob(this.findColumnName(var1), var2);
   }

   public void updateRef(int var1, Ref var2) throws SQLException {
      throw Util.notImplemented();
   }

   public void updateRef(String var1, Ref var2) throws SQLException {
      throw Util.notImplemented();
   }

   public void updateArray(int var1, Array var2) throws SQLException {
      throw Util.notImplemented();
   }

   public void updateArray(String var1, Array var2) throws SQLException {
      throw Util.notImplemented();
   }

   public RowId getRowId(int var1) throws SQLException {
      throw Util.notImplemented();
   }

   public RowId getRowId(String var1) throws SQLException {
      throw Util.notImplemented();
   }

   public void updateNCharacterStream(int var1, Reader var2) throws SQLException {
      throw Util.notImplemented();
   }

   public void updateNCharacterStream(int var1, Reader var2, long var3) throws SQLException {
      throw Util.notImplemented();
   }

   public void updateNCharacterStream(String var1, Reader var2) throws SQLException {
      throw Util.notImplemented();
   }

   public void updateNCharacterStream(String var1, Reader var2, long var3) throws SQLException {
      throw Util.notImplemented();
   }

   public void updateNString(int var1, String var2) throws SQLException {
      throw Util.notImplemented();
   }

   public void updateNString(String var1, String var2) throws SQLException {
      throw Util.notImplemented();
   }

   public void updateNClob(int var1, NClob var2) throws SQLException {
      throw Util.notImplemented();
   }

   public void updateNClob(int var1, Reader var2) throws SQLException {
      throw Util.notImplemented();
   }

   public void updateNClob(String var1, NClob var2) throws SQLException {
      throw Util.notImplemented();
   }

   public void updateNClob(String var1, Reader var2) throws SQLException {
      throw Util.notImplemented();
   }

   public Reader getNCharacterStream(int var1) throws SQLException {
      throw Util.notImplemented();
   }

   public Reader getNCharacterStream(String var1) throws SQLException {
      throw Util.notImplemented();
   }

   public NClob getNClob(int var1) throws SQLException {
      throw Util.notImplemented();
   }

   public NClob getNClob(String var1) throws SQLException {
      throw Util.notImplemented();
   }

   public String getNString(int var1) throws SQLException {
      throw Util.notImplemented();
   }

   public String getNString(String var1) throws SQLException {
      throw Util.notImplemented();
   }

   public void updateRowId(int var1, RowId var2) throws SQLException {
      throw Util.notImplemented();
   }

   public void updateRowId(String var1, RowId var2) throws SQLException {
      throw Util.notImplemented();
   }

   public SQLXML getSQLXML(int var1) throws SQLException {
      throw Util.notImplemented();
   }

   public SQLXML getSQLXML(String var1) throws SQLException {
      throw Util.notImplemented();
   }

   public void updateSQLXML(int var1, SQLXML var2) throws SQLException {
      throw Util.notImplemented();
   }

   public void updateSQLXML(String var1, SQLXML var2) throws SQLException {
      throw Util.notImplemented();
   }

   public boolean isWrapperFor(Class var1) throws SQLException {
      this.checkIfClosed("isWrapperFor");
      return var1.isInstance(this);
   }

   public Object unwrap(Class var1) throws SQLException {
      this.checkIfClosed("unwrap");

      try {
         return var1.cast(this);
      } catch (ClassCastException var3) {
         throw newSQLException("XJ128.S", new Object[]{var1});
      }
   }

   public void updateNClob(int var1, Reader var2, long var3) throws SQLException {
      throw Util.notImplemented();
   }

   public void updateNClob(String var1, Reader var2, long var3) throws SQLException {
      throw Util.notImplemented();
   }

   public Object getObject(int var1, Class var2) throws SQLException {
      this.checkIfClosed("getObject");
      if (var2 == null) {
         throw this.mismatchException("NULL", var1);
      } else {
         Object var3;
         if (String.class.equals(var2)) {
            var3 = this.getString(var1);
         } else if (BigDecimal.class.equals(var2)) {
            var3 = this.getBigDecimal(var1);
         } else if (Boolean.class.equals(var2)) {
            var3 = this.getBoolean(var1);
         } else if (Byte.class.equals(var2)) {
            var3 = this.getByte(var1);
         } else if (Short.class.equals(var2)) {
            var3 = this.getShort(var1);
         } else if (Integer.class.equals(var2)) {
            var3 = this.getInt(var1);
         } else if (Long.class.equals(var2)) {
            var3 = this.getLong(var1);
         } else if (Float.class.equals(var2)) {
            var3 = this.getFloat(var1);
         } else if (Double.class.equals(var2)) {
            var3 = this.getDouble(var1);
         } else if (Date.class.equals(var2)) {
            var3 = this.getDate(var1);
         } else if (Time.class.equals(var2)) {
            var3 = this.getTime(var1);
         } else if (Timestamp.class.equals(var2)) {
            var3 = this.getTimestamp(var1);
         } else if (Blob.class.equals(var2)) {
            var3 = this.getBlob(var1);
         } else if (Clob.class.equals(var2)) {
            var3 = this.getClob(var1);
         } else if (var2.isArray() && var2.getComponentType().equals(Byte.TYPE)) {
            var3 = this.getBytes(var1);
         } else {
            var3 = this.getObject(var1);
         }

         if (this.wasNull()) {
            var3 = null;
         }

         if (var3 != null && !var2.isInstance(var3)) {
            throw this.mismatchException(var2.getName(), var1);
         } else {
            return var2.cast(var3);
         }
      }
   }

   private SQLException mismatchException(String var1, int var2) throws SQLException {
      String var3 = this.getMetaData().getColumnTypeName(var2);
      return newSQLException("22005", new Object[]{var1, var3});
   }

   public Object getObject(String var1, Class var2) throws SQLException {
      this.checkIfClosed("getObject");
      return this.getObject(this.findColumn(var1), var2);
   }

   public boolean isNull(int var1) throws SQLException {
      try {
         DataValueDescriptor var2 = this.getColumn(var1);
         return var2.isNull();
      } catch (StandardException var3) {
         throw noStateChangeException(var3);
      }
   }

   public int getLength(int var1) throws SQLException {
      try {
         DataValueDescriptor var2 = this.getColumn(var1);
         return var2.getLength();
      } catch (StandardException var3) {
         throw noStateChangeException(var3);
      }
   }
}
