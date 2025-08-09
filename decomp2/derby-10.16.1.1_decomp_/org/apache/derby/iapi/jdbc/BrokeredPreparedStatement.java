package org.apache.derby.iapi.jdbc;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.NClob;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLXML;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;

public class BrokeredPreparedStatement extends BrokeredStatement implements EnginePreparedStatement {
   final String sql;
   private final Object generatedKeys;

   public BrokeredPreparedStatement(BrokeredStatementControl var1, String var2, Object var3) throws SQLException {
      super(var1);
      this.sql = var2;
      this.generatedKeys = var3;
   }

   public final ResultSet executeQuery() throws SQLException {
      return this.wrapResultSet(this.getPreparedStatement().executeQuery());
   }

   public final int executeUpdate() throws SQLException {
      return this.getPreparedStatement().executeUpdate();
   }

   public void close() throws SQLException {
      this.control.closeRealPreparedStatement();
   }

   public final void setNull(int var1, int var2) throws SQLException {
      this.getPreparedStatement().setNull(var1, var2);
   }

   public final void setNull(int var1, int var2, String var3) throws SQLException {
      this.getPreparedStatement().setNull(var1, var2, var3);
   }

   public final void setBoolean(int var1, boolean var2) throws SQLException {
      this.getPreparedStatement().setBoolean(var1, var2);
   }

   public final void setByte(int var1, byte var2) throws SQLException {
      this.getPreparedStatement().setByte(var1, var2);
   }

   public final void setShort(int var1, short var2) throws SQLException {
      this.getPreparedStatement().setShort(var1, var2);
   }

   public final void setInt(int var1, int var2) throws SQLException {
      this.getPreparedStatement().setInt(var1, var2);
   }

   public final void setLong(int var1, long var2) throws SQLException {
      this.getPreparedStatement().setLong(var1, var2);
   }

   public final void setFloat(int var1, float var2) throws SQLException {
      this.getPreparedStatement().setFloat(var1, var2);
   }

   public final void setDouble(int var1, double var2) throws SQLException {
      this.getPreparedStatement().setDouble(var1, var2);
   }

   public final void setBigDecimal(int var1, BigDecimal var2) throws SQLException {
      this.getPreparedStatement().setBigDecimal(var1, var2);
   }

   public final void setString(int var1, String var2) throws SQLException {
      this.getPreparedStatement().setString(var1, var2);
   }

   public final void setBytes(int var1, byte[] var2) throws SQLException {
      this.getPreparedStatement().setBytes(var1, var2);
   }

   public final void setDate(int var1, Date var2) throws SQLException {
      this.getPreparedStatement().setDate(var1, var2);
   }

   public final void setTime(int var1, Time var2) throws SQLException {
      this.getPreparedStatement().setTime(var1, var2);
   }

   public final void setTimestamp(int var1, Timestamp var2) throws SQLException {
      this.getPreparedStatement().setTimestamp(var1, var2);
   }

   public final void setAsciiStream(int var1, InputStream var2, int var3) throws SQLException {
      this.getPreparedStatement().setAsciiStream(var1, var2, var3);
   }

   /** @deprecated */
   public final void setUnicodeStream(int var1, InputStream var2, int var3) throws SQLException {
      this.getPreparedStatement().setUnicodeStream(var1, var2, var3);
   }

   public final void setBinaryStream(int var1, InputStream var2, int var3) throws SQLException {
      this.getPreparedStatement().setBinaryStream(var1, var2, var3);
   }

   public final void addBatch() throws SQLException {
      this.getPreparedStatement().addBatch();
   }

   public final void clearParameters() throws SQLException {
      this.getPreparedStatement().clearParameters();
   }

   public final ResultSetMetaData getMetaData() throws SQLException {
      return this.getPreparedStatement().getMetaData();
   }

   public final void setObject(int var1, Object var2, int var3, int var4) throws SQLException {
      this.getPreparedStatement().setObject(var1, var2, var3, var4);
   }

   public final void setObject(int var1, Object var2, int var3) throws SQLException {
      this.getPreparedStatement().setObject(var1, var2, var3);
   }

   public final void setObject(int var1, Object var2) throws SQLException {
      this.getPreparedStatement().setObject(var1, var2);
   }

   public final boolean execute() throws SQLException {
      return this.getPreparedStatement().execute();
   }

   public final void setCharacterStream(int var1, Reader var2, int var3) throws SQLException {
      this.getPreparedStatement().setCharacterStream(var1, var2, var3);
   }

   public final void setRef(int var1, Ref var2) throws SQLException {
      this.getPreparedStatement().setRef(var1, var2);
   }

   public final void setBlob(int var1, Blob var2) throws SQLException {
      this.getPreparedStatement().setBlob(var1, var2);
   }

   public final void setClob(int var1, Clob var2) throws SQLException {
      this.getPreparedStatement().setClob(var1, var2);
   }

   public final void setArray(int var1, Array var2) throws SQLException {
      this.getPreparedStatement().setArray(var1, var2);
   }

   public final void setDate(int var1, Date var2, Calendar var3) throws SQLException {
      this.getPreparedStatement().setDate(var1, var2, var3);
   }

   public final void setTime(int var1, Time var2, Calendar var3) throws SQLException {
      this.getPreparedStatement().setTime(var1, var2, var3);
   }

   public final void setTimestamp(int var1, Timestamp var2, Calendar var3) throws SQLException {
      this.getPreparedStatement().setTimestamp(var1, var2, var3);
   }

   public long executeLargeUpdate() throws SQLException {
      EnginePreparedStatement var1 = (EnginePreparedStatement)this.getPreparedStatement();
      return var1.executeLargeUpdate();
   }

   public void setBinaryStream(int var1, InputStream var2) throws SQLException {
      this.getPreparedStatement().setBinaryStream(var1, var2);
   }

   public void setCharacterStream(int var1, Reader var2) throws SQLException {
      this.getPreparedStatement().setCharacterStream(var1, var2);
   }

   public final void setURL(int var1, URL var2) throws SQLException {
      this.getPreparedStatement().setURL(var1, var2);
   }

   public final ParameterMetaData getParameterMetaData() throws SQLException {
      return this.getPreparedStatement().getParameterMetaData();
   }

   public final void setRowId(int var1, RowId var2) throws SQLException {
      this.getPreparedStatement().setRowId(var1, var2);
   }

   public final void setNString(int var1, String var2) throws SQLException {
      this.getPreparedStatement().setNString(var1, var2);
   }

   public final void setNCharacterStream(int var1, Reader var2) throws SQLException {
      this.getPreparedStatement().setNCharacterStream(var1, var2);
   }

   public final void setNCharacterStream(int var1, Reader var2, long var3) throws SQLException {
      this.getPreparedStatement().setNCharacterStream(var1, var2, var3);
   }

   public final void setNClob(int var1, NClob var2) throws SQLException {
      this.getPreparedStatement().setNClob(var1, var2);
   }

   public final void setClob(int var1, Reader var2, long var3) throws SQLException {
      this.getPreparedStatement().setClob(var1, var2, var3);
   }

   public final void setBlob(int var1, InputStream var2, long var3) throws SQLException {
      this.getPreparedStatement().setBlob(var1, var2, var3);
   }

   public final void setNClob(int var1, Reader var2) throws SQLException {
      this.getPreparedStatement().setNClob(var1, var2);
   }

   public final void setNClob(int var1, Reader var2, long var3) throws SQLException {
      this.getPreparedStatement().setNClob(var1, var2, var3);
   }

   public final void setSQLXML(int var1, SQLXML var2) throws SQLException {
      this.getPreparedStatement().setSQLXML(var1, var2);
   }

   public final void setAsciiStream(int var1, InputStream var2) throws SQLException {
      this.getPreparedStatement().setAsciiStream(var1, var2);
   }

   public final void setAsciiStream(int var1, InputStream var2, long var3) throws SQLException {
      this.getPreparedStatement().setAsciiStream(var1, var2, var3);
   }

   public final void setBinaryStream(int var1, InputStream var2, long var3) throws SQLException {
      this.getPreparedStatement().setBinaryStream(var1, var2, var3);
   }

   public final void setBlob(int var1, InputStream var2) throws SQLException {
      this.getPreparedStatement().setBlob(var1, var2);
   }

   public final void setCharacterStream(int var1, Reader var2, long var3) throws SQLException {
      this.getPreparedStatement().setCharacterStream(var1, var2, var3);
   }

   public final void setClob(int var1, Reader var2) throws SQLException {
      this.getPreparedStatement().setClob(var1, var2);
   }

   PreparedStatement getPreparedStatement() throws SQLException {
      return this.control.getRealPreparedStatement();
   }

   public final Statement getStatement() throws SQLException {
      return this.getPreparedStatement();
   }

   public PreparedStatement createDuplicateStatement(Connection var1, PreparedStatement var2) throws SQLException {
      PreparedStatement var3;
      if (this.generatedKeys == null) {
         var3 = var1.prepareStatement(this.sql, this.resultSetType, this.resultSetConcurrency, this.resultSetHoldability);
      } else if (this.generatedKeys instanceof Integer) {
         var3 = var1.prepareStatement(this.sql, (Integer)this.generatedKeys);
      } else if (this.generatedKeys instanceof int[]) {
         var3 = var1.prepareStatement(this.sql, (int[])this.generatedKeys);
      } else {
         var3 = var1.prepareStatement(this.sql, (String[])this.generatedKeys);
      }

      this.setStatementState(var2, var3);
      return var3;
   }

   public final long getVersionCounter() throws SQLException {
      return ((EnginePreparedStatement)this.getPreparedStatement()).getVersionCounter();
   }
}
