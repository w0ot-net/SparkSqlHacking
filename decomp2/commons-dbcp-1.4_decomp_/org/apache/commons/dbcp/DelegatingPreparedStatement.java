package org.apache.commons.dbcp;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
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

public class DelegatingPreparedStatement extends DelegatingStatement implements PreparedStatement {
   public DelegatingPreparedStatement(DelegatingConnection c, PreparedStatement s) {
      super(c, s);
   }

   public boolean equals(Object obj) {
      PreparedStatement delegate = (PreparedStatement)this.getInnermostDelegate();
      if (delegate == null) {
         return false;
      } else if (obj instanceof DelegatingPreparedStatement) {
         DelegatingPreparedStatement s = (DelegatingPreparedStatement)obj;
         return delegate.equals(s.getInnermostDelegate());
      } else {
         return delegate.equals(obj);
      }
   }

   public void setDelegate(PreparedStatement s) {
      super.setDelegate(s);
      this._stmt = s;
   }

   public ResultSet executeQuery() throws SQLException {
      this.checkOpen();

      try {
         return DelegatingResultSet.wrapResultSet((Statement)this, ((PreparedStatement)this._stmt).executeQuery());
      } catch (SQLException e) {
         this.handleException(e);
         throw new AssertionError();
      }
   }

   public int executeUpdate() throws SQLException {
      this.checkOpen();

      try {
         return ((PreparedStatement)this._stmt).executeUpdate();
      } catch (SQLException e) {
         this.handleException(e);
         return 0;
      }
   }

   public void setNull(int parameterIndex, int sqlType) throws SQLException {
      this.checkOpen();

      try {
         ((PreparedStatement)this._stmt).setNull(parameterIndex, sqlType);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void setBoolean(int parameterIndex, boolean x) throws SQLException {
      this.checkOpen();

      try {
         ((PreparedStatement)this._stmt).setBoolean(parameterIndex, x);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void setByte(int parameterIndex, byte x) throws SQLException {
      this.checkOpen();

      try {
         ((PreparedStatement)this._stmt).setByte(parameterIndex, x);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void setShort(int parameterIndex, short x) throws SQLException {
      this.checkOpen();

      try {
         ((PreparedStatement)this._stmt).setShort(parameterIndex, x);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void setInt(int parameterIndex, int x) throws SQLException {
      this.checkOpen();

      try {
         ((PreparedStatement)this._stmt).setInt(parameterIndex, x);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void setLong(int parameterIndex, long x) throws SQLException {
      this.checkOpen();

      try {
         ((PreparedStatement)this._stmt).setLong(parameterIndex, x);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void setFloat(int parameterIndex, float x) throws SQLException {
      this.checkOpen();

      try {
         ((PreparedStatement)this._stmt).setFloat(parameterIndex, x);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void setDouble(int parameterIndex, double x) throws SQLException {
      this.checkOpen();

      try {
         ((PreparedStatement)this._stmt).setDouble(parameterIndex, x);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void setBigDecimal(int parameterIndex, BigDecimal x) throws SQLException {
      this.checkOpen();

      try {
         ((PreparedStatement)this._stmt).setBigDecimal(parameterIndex, x);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void setString(int parameterIndex, String x) throws SQLException {
      this.checkOpen();

      try {
         ((PreparedStatement)this._stmt).setString(parameterIndex, x);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void setBytes(int parameterIndex, byte[] x) throws SQLException {
      this.checkOpen();

      try {
         ((PreparedStatement)this._stmt).setBytes(parameterIndex, x);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void setDate(int parameterIndex, Date x) throws SQLException {
      this.checkOpen();

      try {
         ((PreparedStatement)this._stmt).setDate(parameterIndex, x);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void setTime(int parameterIndex, Time x) throws SQLException {
      this.checkOpen();

      try {
         ((PreparedStatement)this._stmt).setTime(parameterIndex, x);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void setTimestamp(int parameterIndex, Timestamp x) throws SQLException {
      this.checkOpen();

      try {
         ((PreparedStatement)this._stmt).setTimestamp(parameterIndex, x);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void setAsciiStream(int parameterIndex, InputStream x, int length) throws SQLException {
      this.checkOpen();

      try {
         ((PreparedStatement)this._stmt).setAsciiStream(parameterIndex, x, length);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   /** @deprecated */
   public void setUnicodeStream(int parameterIndex, InputStream x, int length) throws SQLException {
      this.checkOpen();

      try {
         ((PreparedStatement)this._stmt).setUnicodeStream(parameterIndex, x, length);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void setBinaryStream(int parameterIndex, InputStream x, int length) throws SQLException {
      this.checkOpen();

      try {
         ((PreparedStatement)this._stmt).setBinaryStream(parameterIndex, x, length);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void clearParameters() throws SQLException {
      this.checkOpen();

      try {
         ((PreparedStatement)this._stmt).clearParameters();
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void setObject(int parameterIndex, Object x, int targetSqlType, int scale) throws SQLException {
      this.checkOpen();

      try {
         ((PreparedStatement)this._stmt).setObject(parameterIndex, x, targetSqlType, scale);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void setObject(int parameterIndex, Object x, int targetSqlType) throws SQLException {
      this.checkOpen();

      try {
         ((PreparedStatement)this._stmt).setObject(parameterIndex, x, targetSqlType);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void setObject(int parameterIndex, Object x) throws SQLException {
      this.checkOpen();

      try {
         ((PreparedStatement)this._stmt).setObject(parameterIndex, x);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public boolean execute() throws SQLException {
      this.checkOpen();

      try {
         return ((PreparedStatement)this._stmt).execute();
      } catch (SQLException e) {
         this.handleException(e);
         return false;
      }
   }

   public void addBatch() throws SQLException {
      this.checkOpen();

      try {
         ((PreparedStatement)this._stmt).addBatch();
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void setCharacterStream(int parameterIndex, Reader reader, int length) throws SQLException {
      this.checkOpen();

      try {
         ((PreparedStatement)this._stmt).setCharacterStream(parameterIndex, reader, length);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void setRef(int i, Ref x) throws SQLException {
      this.checkOpen();

      try {
         ((PreparedStatement)this._stmt).setRef(i, x);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void setBlob(int i, Blob x) throws SQLException {
      this.checkOpen();

      try {
         ((PreparedStatement)this._stmt).setBlob(i, x);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void setClob(int i, Clob x) throws SQLException {
      this.checkOpen();

      try {
         ((PreparedStatement)this._stmt).setClob(i, x);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void setArray(int i, Array x) throws SQLException {
      this.checkOpen();

      try {
         ((PreparedStatement)this._stmt).setArray(i, x);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public ResultSetMetaData getMetaData() throws SQLException {
      this.checkOpen();

      try {
         return ((PreparedStatement)this._stmt).getMetaData();
      } catch (SQLException e) {
         this.handleException(e);
         throw new AssertionError();
      }
   }

   public void setDate(int parameterIndex, Date x, Calendar cal) throws SQLException {
      this.checkOpen();

      try {
         ((PreparedStatement)this._stmt).setDate(parameterIndex, x, cal);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void setTime(int parameterIndex, Time x, Calendar cal) throws SQLException {
      this.checkOpen();

      try {
         ((PreparedStatement)this._stmt).setTime(parameterIndex, x, cal);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void setTimestamp(int parameterIndex, Timestamp x, Calendar cal) throws SQLException {
      this.checkOpen();

      try {
         ((PreparedStatement)this._stmt).setTimestamp(parameterIndex, x, cal);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void setNull(int paramIndex, int sqlType, String typeName) throws SQLException {
      this.checkOpen();

      try {
         ((PreparedStatement)this._stmt).setNull(paramIndex, sqlType, typeName);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public String toString() {
      return this._stmt.toString();
   }

   public void setURL(int parameterIndex, URL x) throws SQLException {
      this.checkOpen();

      try {
         ((PreparedStatement)this._stmt).setURL(parameterIndex, x);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public ParameterMetaData getParameterMetaData() throws SQLException {
      this.checkOpen();

      try {
         return ((PreparedStatement)this._stmt).getParameterMetaData();
      } catch (SQLException e) {
         this.handleException(e);
         throw new AssertionError();
      }
   }

   public void setRowId(int parameterIndex, RowId value) throws SQLException {
      this.checkOpen();

      try {
         ((PreparedStatement)this._stmt).setRowId(parameterIndex, value);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void setNString(int parameterIndex, String value) throws SQLException {
      this.checkOpen();

      try {
         ((PreparedStatement)this._stmt).setNString(parameterIndex, value);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void setNCharacterStream(int parameterIndex, Reader value, long length) throws SQLException {
      this.checkOpen();

      try {
         ((PreparedStatement)this._stmt).setNCharacterStream(parameterIndex, value, length);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void setNClob(int parameterIndex, NClob value) throws SQLException {
      this.checkOpen();

      try {
         ((PreparedStatement)this._stmt).setNClob(parameterIndex, value);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void setClob(int parameterIndex, Reader reader, long length) throws SQLException {
      this.checkOpen();

      try {
         ((PreparedStatement)this._stmt).setClob(parameterIndex, reader, length);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void setBlob(int parameterIndex, InputStream inputStream, long length) throws SQLException {
      this.checkOpen();

      try {
         ((PreparedStatement)this._stmt).setBlob(parameterIndex, inputStream, length);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void setNClob(int parameterIndex, Reader reader, long length) throws SQLException {
      this.checkOpen();

      try {
         ((PreparedStatement)this._stmt).setNClob(parameterIndex, reader, length);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void setSQLXML(int parameterIndex, SQLXML value) throws SQLException {
      this.checkOpen();

      try {
         ((PreparedStatement)this._stmt).setSQLXML(parameterIndex, value);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void setAsciiStream(int parameterIndex, InputStream inputStream, long length) throws SQLException {
      this.checkOpen();

      try {
         ((PreparedStatement)this._stmt).setAsciiStream(parameterIndex, inputStream, length);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void setBinaryStream(int parameterIndex, InputStream inputStream, long length) throws SQLException {
      this.checkOpen();

      try {
         ((PreparedStatement)this._stmt).setBinaryStream(parameterIndex, inputStream, length);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void setCharacterStream(int parameterIndex, Reader reader, long length) throws SQLException {
      this.checkOpen();

      try {
         ((PreparedStatement)this._stmt).setCharacterStream(parameterIndex, reader, length);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void setAsciiStream(int parameterIndex, InputStream inputStream) throws SQLException {
      this.checkOpen();

      try {
         ((PreparedStatement)this._stmt).setAsciiStream(parameterIndex, inputStream);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void setBinaryStream(int parameterIndex, InputStream inputStream) throws SQLException {
      this.checkOpen();

      try {
         ((PreparedStatement)this._stmt).setBinaryStream(parameterIndex, inputStream);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void setCharacterStream(int parameterIndex, Reader reader) throws SQLException {
      this.checkOpen();

      try {
         ((PreparedStatement)this._stmt).setCharacterStream(parameterIndex, reader);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void setNCharacterStream(int parameterIndex, Reader reader) throws SQLException {
      this.checkOpen();

      try {
         ((PreparedStatement)this._stmt).setNCharacterStream(parameterIndex, reader);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void setClob(int parameterIndex, Reader reader) throws SQLException {
      this.checkOpen();

      try {
         ((PreparedStatement)this._stmt).setClob(parameterIndex, reader);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void setBlob(int parameterIndex, InputStream inputStream) throws SQLException {
      this.checkOpen();

      try {
         ((PreparedStatement)this._stmt).setBlob(parameterIndex, inputStream);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }

   public void setNClob(int parameterIndex, Reader reader) throws SQLException {
      this.checkOpen();

      try {
         ((PreparedStatement)this._stmt).setNClob(parameterIndex, reader);
      } catch (SQLException e) {
         this.handleException(e);
      }

   }
}
