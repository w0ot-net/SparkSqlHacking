package org.apache.derby.iapi.jdbc;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.Ref;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Map;

public class BrokeredCallableStatement extends BrokeredPreparedStatement implements CallableStatement {
   public BrokeredCallableStatement(BrokeredStatementControl var1, String var2) throws SQLException {
      super(var1, var2, (Object)null);
   }

   public final void registerOutParameter(int var1, int var2) throws SQLException {
      this.getCallableStatement().registerOutParameter(var1, var2);
   }

   public final void registerOutParameter(int var1, int var2, int var3) throws SQLException {
      this.getCallableStatement().registerOutParameter(var1, var2, var3);
   }

   public final boolean wasNull() throws SQLException {
      return this.getCallableStatement().wasNull();
   }

   public final void close() throws SQLException {
      this.control.closeRealCallableStatement();
   }

   public final String getString(int var1) throws SQLException {
      return this.getCallableStatement().getString(var1);
   }

   public final boolean getBoolean(int var1) throws SQLException {
      return this.getCallableStatement().getBoolean(var1);
   }

   public final byte getByte(int var1) throws SQLException {
      return this.getCallableStatement().getByte(var1);
   }

   public final short getShort(int var1) throws SQLException {
      return this.getCallableStatement().getShort(var1);
   }

   public final int getInt(int var1) throws SQLException {
      return this.getCallableStatement().getInt(var1);
   }

   public final long getLong(int var1) throws SQLException {
      return this.getCallableStatement().getLong(var1);
   }

   public final float getFloat(int var1) throws SQLException {
      return this.getCallableStatement().getFloat(var1);
   }

   public final double getDouble(int var1) throws SQLException {
      return this.getCallableStatement().getDouble(var1);
   }

   /** @deprecated */
   public final BigDecimal getBigDecimal(int var1, int var2) throws SQLException {
      return this.getCallableStatement().getBigDecimal(var1, var2);
   }

   public final byte[] getBytes(int var1) throws SQLException {
      return this.getCallableStatement().getBytes(var1);
   }

   public final Date getDate(int var1) throws SQLException {
      return this.getCallableStatement().getDate(var1);
   }

   public final Date getDate(int var1, Calendar var2) throws SQLException {
      return this.getCallableStatement().getDate(var1, var2);
   }

   public final Time getTime(int var1) throws SQLException {
      return this.getCallableStatement().getTime(var1);
   }

   public final Timestamp getTimestamp(int var1) throws SQLException {
      return this.getCallableStatement().getTimestamp(var1);
   }

   public final Object getObject(int var1) throws SQLException {
      return this.getCallableStatement().getObject(var1);
   }

   public final BigDecimal getBigDecimal(int var1) throws SQLException {
      return this.getCallableStatement().getBigDecimal(var1);
   }

   public final Object getObject(int var1, Map var2) throws SQLException {
      return this.getCallableStatement().getObject(var1, var2);
   }

   public final Ref getRef(int var1) throws SQLException {
      return this.getCallableStatement().getRef(var1);
   }

   public final Blob getBlob(int var1) throws SQLException {
      return this.getCallableStatement().getBlob(var1);
   }

   public final Clob getClob(int var1) throws SQLException {
      return this.getCallableStatement().getClob(var1);
   }

   public final Array getArray(int var1) throws SQLException {
      return this.getCallableStatement().getArray(var1);
   }

   public final Time getTime(int var1, Calendar var2) throws SQLException {
      return this.getCallableStatement().getTime(var1, var2);
   }

   public final Timestamp getTimestamp(int var1, Calendar var2) throws SQLException {
      return this.getCallableStatement().getTimestamp(var1, var2);
   }

   public final void registerOutParameter(int var1, int var2, String var3) throws SQLException {
      this.getCallableStatement().registerOutParameter(var1, var2, var3);
   }

   public final void setURL(String var1, URL var2) throws SQLException {
      this.getCallableStatement().setURL(var1, var2);
   }

   public final void setNull(String var1, int var2) throws SQLException {
      this.getCallableStatement().setNull(var1, var2);
   }

   public final void setBoolean(String var1, boolean var2) throws SQLException {
      this.getCallableStatement().setBoolean(var1, var2);
   }

   public final void setByte(String var1, byte var2) throws SQLException {
      this.getCallableStatement().setByte(var1, var2);
   }

   public final void setShort(String var1, short var2) throws SQLException {
      this.getCallableStatement().setShort(var1, var2);
   }

   public final void setInt(String var1, int var2) throws SQLException {
      this.getCallableStatement().setInt(var1, var2);
   }

   public final void setLong(String var1, long var2) throws SQLException {
      this.getCallableStatement().setLong(var1, var2);
   }

   public final void setFloat(String var1, float var2) throws SQLException {
      this.getCallableStatement().setFloat(var1, var2);
   }

   public final void setDouble(String var1, double var2) throws SQLException {
      this.getCallableStatement().setDouble(var1, var2);
   }

   public final void setBigDecimal(String var1, BigDecimal var2) throws SQLException {
      this.getCallableStatement().setBigDecimal(var1, var2);
   }

   public final void setString(String var1, String var2) throws SQLException {
      this.getCallableStatement().setString(var1, var2);
   }

   public final void setBytes(String var1, byte[] var2) throws SQLException {
      this.getCallableStatement().setBytes(var1, var2);
   }

   public final void setDate(String var1, Date var2) throws SQLException {
      this.getCallableStatement().setDate(var1, var2);
   }

   public final void setTime(String var1, Time var2) throws SQLException {
      this.getCallableStatement().setTime(var1, var2);
   }

   public final void setTimestamp(String var1, Timestamp var2) throws SQLException {
      this.getCallableStatement().setTimestamp(var1, var2);
   }

   public final void setAsciiStream(String var1, InputStream var2, int var3) throws SQLException {
      this.getCallableStatement().setAsciiStream(var1, var2, var3);
   }

   public final void setBinaryStream(String var1, InputStream var2, int var3) throws SQLException {
      this.getCallableStatement().setBinaryStream(var1, var2, var3);
   }

   public final void setObject(String var1, Object var2, int var3, int var4) throws SQLException {
      this.getCallableStatement().setObject(var1, var2, var3, var4);
   }

   public final void setObject(String var1, Object var2, int var3) throws SQLException {
      this.getCallableStatement().setObject(var1, var2, var3);
   }

   public final void setObject(String var1, Object var2) throws SQLException {
      this.getCallableStatement().setObject(var1, var2);
   }

   public final void setCharacterStream(String var1, Reader var2, int var3) throws SQLException {
      this.getCallableStatement().setCharacterStream(var1, var2, var3);
   }

   public final void setDate(String var1, Date var2, Calendar var3) throws SQLException {
      this.getCallableStatement().setDate(var1, var2, var3);
   }

   public final void setTime(String var1, Time var2, Calendar var3) throws SQLException {
      this.getCallableStatement().setTime(var1, var2, var3);
   }

   public final void setTimestamp(String var1, Timestamp var2, Calendar var3) throws SQLException {
      this.getCallableStatement().setTimestamp(var1, var2, var3);
   }

   public final void setNull(String var1, int var2, String var3) throws SQLException {
      this.getCallableStatement().setNull(var1, var2, var3);
   }

   public final String getString(String var1) throws SQLException {
      return this.getCallableStatement().getString(var1);
   }

   public final boolean getBoolean(String var1) throws SQLException {
      return this.getCallableStatement().getBoolean(var1);
   }

   public final byte getByte(String var1) throws SQLException {
      return this.getCallableStatement().getByte(var1);
   }

   public final short getShort(String var1) throws SQLException {
      return this.getCallableStatement().getShort(var1);
   }

   public final int getInt(String var1) throws SQLException {
      return this.getCallableStatement().getInt(var1);
   }

   public final long getLong(String var1) throws SQLException {
      return this.getCallableStatement().getLong(var1);
   }

   public final float getFloat(String var1) throws SQLException {
      return this.getCallableStatement().getFloat(var1);
   }

   public final double getDouble(String var1) throws SQLException {
      return this.getCallableStatement().getDouble(var1);
   }

   public final byte[] getBytes(String var1) throws SQLException {
      return this.getCallableStatement().getBytes(var1);
   }

   public final Date getDate(String var1) throws SQLException {
      return this.getCallableStatement().getDate(var1);
   }

   public final Time getTime(String var1) throws SQLException {
      return this.getCallableStatement().getTime(var1);
   }

   public final Timestamp getTimestamp(String var1) throws SQLException {
      return this.getCallableStatement().getTimestamp(var1);
   }

   public final Object getObject(String var1) throws SQLException {
      return this.getCallableStatement().getObject(var1);
   }

   public final BigDecimal getBigDecimal(String var1) throws SQLException {
      return this.getCallableStatement().getBigDecimal(var1);
   }

   public final Object getObject(String var1, Map var2) throws SQLException {
      return this.getCallableStatement().getObject(var1, var2);
   }

   public final Ref getRef(String var1) throws SQLException {
      return this.getCallableStatement().getRef(var1);
   }

   public final Blob getBlob(String var1) throws SQLException {
      return this.getCallableStatement().getBlob(var1);
   }

   public final Clob getClob(String var1) throws SQLException {
      return this.getCallableStatement().getClob(var1);
   }

   public final Array getArray(String var1) throws SQLException {
      return this.getCallableStatement().getArray(var1);
   }

   public final Date getDate(String var1, Calendar var2) throws SQLException {
      return this.getCallableStatement().getDate(var1, var2);
   }

   public final Time getTime(String var1, Calendar var2) throws SQLException {
      return this.getCallableStatement().getTime(var1, var2);
   }

   public final Timestamp getTimestamp(String var1, Calendar var2) throws SQLException {
      return this.getCallableStatement().getTimestamp(var1, var2);
   }

   public final URL getURL(String var1) throws SQLException {
      return this.getCallableStatement().getURL(var1);
   }

   public final URL getURL(int var1) throws SQLException {
      return this.getCallableStatement().getURL(var1);
   }

   public final void registerOutParameter(String var1, int var2) throws SQLException {
      this.getCallableStatement().registerOutParameter(var1, var2);
   }

   public final void registerOutParameter(String var1, int var2, int var3) throws SQLException {
      this.getCallableStatement().registerOutParameter(var1, var2, var3);
   }

   public final void registerOutParameter(String var1, int var2, String var3) throws SQLException {
      this.getCallableStatement().registerOutParameter(var1, var2, var3);
   }

   public final Reader getCharacterStream(int var1) throws SQLException {
      return this.getCallableStatement().getCharacterStream(var1);
   }

   public final Reader getCharacterStream(String var1) throws SQLException {
      return this.getCallableStatement().getCharacterStream(var1);
   }

   public final Reader getNCharacterStream(int var1) throws SQLException {
      return this.getCallableStatement().getNCharacterStream(var1);
   }

   public final Reader getNCharacterStream(String var1) throws SQLException {
      return this.getCallableStatement().getNCharacterStream(var1);
   }

   public final String getNString(int var1) throws SQLException {
      return this.getCallableStatement().getNString(var1);
   }

   public final String getNString(String var1) throws SQLException {
      return this.getCallableStatement().getNString(var1);
   }

   public final RowId getRowId(int var1) throws SQLException {
      return this.getCallableStatement().getRowId(var1);
   }

   public final RowId getRowId(String var1) throws SQLException {
      return this.getCallableStatement().getRowId(var1);
   }

   public final void setRowId(String var1, RowId var2) throws SQLException {
      this.getCallableStatement().setRowId(var1, var2);
   }

   public final void setBlob(String var1, Blob var2) throws SQLException {
      this.getCallableStatement().setBlob(var1, var2);
   }

   public final void setClob(String var1, Clob var2) throws SQLException {
      this.getCallableStatement().setClob(var1, var2);
   }

   public final void setNString(String var1, String var2) throws SQLException {
      this.getCallableStatement().setNString(var1, var2);
   }

   public final void setNCharacterStream(String var1, Reader var2) throws SQLException {
      this.getCallableStatement().setNCharacterStream(var1, var2);
   }

   public final void setNCharacterStream(String var1, Reader var2, long var3) throws SQLException {
      this.getCallableStatement().setNCharacterStream(var1, var2, var3);
   }

   public final void setNClob(String var1, NClob var2) throws SQLException {
      this.getCallableStatement().setNClob(var1, var2);
   }

   public final void setClob(String var1, Reader var2) throws SQLException {
      this.getCallableStatement().setClob(var1, var2);
   }

   public final void setClob(String var1, Reader var2, long var3) throws SQLException {
      this.getCallableStatement().setClob(var1, var2, var3);
   }

   public final void setBlob(String var1, InputStream var2) throws SQLException {
      this.getCallableStatement().setBlob(var1, var2);
   }

   public final void setBlob(String var1, InputStream var2, long var3) throws SQLException {
      this.getCallableStatement().setBlob(var1, var2, var3);
   }

   public final void setNClob(String var1, Reader var2) throws SQLException {
      this.getCallableStatement().setNClob(var1, var2);
   }

   public final void setNClob(String var1, Reader var2, long var3) throws SQLException {
      this.getCallableStatement().setNClob(var1, var2, var3);
   }

   public NClob getNClob(int var1) throws SQLException {
      return this.getCallableStatement().getNClob(var1);
   }

   public NClob getNClob(String var1) throws SQLException {
      return this.getCallableStatement().getNClob(var1);
   }

   public final void setSQLXML(String var1, SQLXML var2) throws SQLException {
      this.getCallableStatement().setSQLXML(var1, var2);
   }

   public SQLXML getSQLXML(int var1) throws SQLException {
      return this.getCallableStatement().getSQLXML(var1);
   }

   public SQLXML getSQLXML(String var1) throws SQLException {
      return this.getCallableStatement().getSQLXML(var1);
   }

   public final void setAsciiStream(String var1, InputStream var2) throws SQLException {
      this.getCallableStatement().setAsciiStream(var1, var2);
   }

   public final void setAsciiStream(String var1, InputStream var2, long var3) throws SQLException {
      this.getCallableStatement().setAsciiStream(var1, var2, var3);
   }

   public final void setBinaryStream(String var1, InputStream var2) throws SQLException {
      this.getCallableStatement().setBinaryStream(var1, var2);
   }

   public final void setBinaryStream(String var1, InputStream var2, long var3) throws SQLException {
      this.getCallableStatement().setBinaryStream(var1, var2, var3);
   }

   public final void setCharacterStream(String var1, Reader var2) throws SQLException {
      this.getCallableStatement().setCharacterStream(var1, var2);
   }

   public final void setCharacterStream(String var1, Reader var2, long var3) throws SQLException {
      this.getCallableStatement().setCharacterStream(var1, var2, var3);
   }

   public final Object getObject(int var1, Class var2) throws SQLException {
      return ((EngineCallableStatement)this.getCallableStatement()).getObject(var1, var2);
   }

   public final Object getObject(String var1, Class var2) throws SQLException {
      return ((EngineCallableStatement)this.getCallableStatement()).getObject(var1, var2);
   }

   final CallableStatement getCallableStatement() throws SQLException {
      return this.control.getRealCallableStatement();
   }

   final PreparedStatement getPreparedStatement() throws SQLException {
      return this.getCallableStatement();
   }

   public CallableStatement createDuplicateStatement(Connection var1, CallableStatement var2) throws SQLException {
      CallableStatement var3 = var1.prepareCall(this.sql, this.resultSetType, this.resultSetConcurrency, this.resultSetHoldability);
      this.setStatementState(var2, var3);
      return var3;
   }
}
