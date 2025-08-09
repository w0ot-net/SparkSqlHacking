package org.apache.hive.jdbc;

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
import java.sql.SQLFeatureNotSupportedException;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import org.apache.hive.service.rpc.thrift.TCLIService;
import org.apache.hive.service.rpc.thrift.TSessionHandle;

public class HivePreparedStatement extends HiveStatement implements PreparedStatement {
   private final String sql;
   private final HashMap parameters = new HashMap();

   public HivePreparedStatement(HiveConnection connection, TCLIService.Iface client, TSessionHandle sessHandle, String sql) {
      super(connection, client, sessHandle);
      this.sql = sql;
   }

   public void addBatch() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void clearParameters() throws SQLException {
      this.parameters.clear();
   }

   public boolean execute() throws SQLException {
      return super.execute(this.updateSql(this.sql, this.parameters));
   }

   public ResultSet executeQuery() throws SQLException {
      return super.executeQuery(this.updateSql(this.sql, this.parameters));
   }

   public int executeUpdate() throws SQLException {
      super.executeUpdate(this.updateSql(this.sql, this.parameters));
      return 0;
   }

   private String updateSql(String sql, HashMap parameters) throws SQLException {
      List<String> parts = this.splitSqlStatement(sql);
      StringBuilder newSql = new StringBuilder((String)parts.get(0));

      for(int i = 1; i < parts.size(); ++i) {
         if (!parameters.containsKey(i)) {
            throw new SQLException("Parameter #" + i + " is unset");
         }

         newSql.append((String)parameters.get(i));
         newSql.append((String)parts.get(i));
      }

      return newSql.toString();
   }

   private List splitSqlStatement(String sql) {
      List<String> parts = new ArrayList();
      int apCount = 0;
      int off = 0;
      boolean skip = false;

      for(int i = 0; i < sql.length(); ++i) {
         char c = sql.charAt(i);
         if (skip) {
            skip = false;
         } else {
            switch (c) {
               case '\'':
                  ++apCount;
                  break;
               case '?':
                  if ((apCount & 1) == 0) {
                     parts.add(sql.substring(off, i));
                     off = i + 1;
                  }
                  break;
               case '\\':
                  skip = true;
            }
         }
      }

      parts.add(sql.substring(off, sql.length()));
      return parts;
   }

   public ResultSetMetaData getMetaData() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public ParameterMetaData getParameterMetaData() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setArray(int i, Array x) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setAsciiStream(int parameterIndex, InputStream x) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setAsciiStream(int parameterIndex, InputStream x, int length) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setAsciiStream(int parameterIndex, InputStream x, long length) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setBigDecimal(int parameterIndex, BigDecimal x) throws SQLException {
      this.parameters.put(parameterIndex, x.toString());
   }

   public void setBinaryStream(int parameterIndex, InputStream x) throws SQLException {
      String str = (new Scanner(x, "UTF-8")).useDelimiter("\\A").next();
      this.setString(parameterIndex, str);
   }

   public void setBinaryStream(int parameterIndex, InputStream x, int length) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setBinaryStream(int parameterIndex, InputStream x, long length) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setBlob(int i, Blob x) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setBlob(int parameterIndex, InputStream inputStream) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setBlob(int parameterIndex, InputStream inputStream, long length) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setBoolean(int parameterIndex, boolean x) throws SQLException {
      this.parameters.put(parameterIndex, "" + x);
   }

   public void setByte(int parameterIndex, byte x) throws SQLException {
      this.parameters.put(parameterIndex, "" + x);
   }

   public void setBytes(int parameterIndex, byte[] x) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setCharacterStream(int parameterIndex, Reader reader) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setCharacterStream(int parameterIndex, Reader reader, int length) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setCharacterStream(int parameterIndex, Reader reader, long length) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setClob(int i, Clob x) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setClob(int parameterIndex, Reader reader) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setClob(int parameterIndex, Reader reader, long length) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setDate(int parameterIndex, Date x) throws SQLException {
      this.parameters.put(parameterIndex, "'" + x.toString() + "'");
   }

   public void setDate(int parameterIndex, Date x, Calendar cal) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setDouble(int parameterIndex, double x) throws SQLException {
      this.parameters.put(parameterIndex, "" + x);
   }

   public void setFloat(int parameterIndex, float x) throws SQLException {
      this.parameters.put(parameterIndex, "" + x);
   }

   public void setInt(int parameterIndex, int x) throws SQLException {
      this.parameters.put(parameterIndex, "" + x);
   }

   public void setLong(int parameterIndex, long x) throws SQLException {
      this.parameters.put(parameterIndex, "" + x);
   }

   public void setNCharacterStream(int parameterIndex, Reader value) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setNCharacterStream(int parameterIndex, Reader value, long length) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setNClob(int parameterIndex, NClob value) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setNClob(int parameterIndex, Reader reader) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setNClob(int parameterIndex, Reader reader, long length) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setNString(int parameterIndex, String value) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setNull(int parameterIndex, int sqlType) throws SQLException {
      this.parameters.put(parameterIndex, "NULL");
   }

   public void setNull(int paramIndex, int sqlType, String typeName) throws SQLException {
      this.parameters.put(paramIndex, "NULL");
   }

   public void setObject(int parameterIndex, Object x) throws SQLException {
      if (x == null) {
         this.setNull(parameterIndex, 0);
      } else if (x instanceof String) {
         this.setString(parameterIndex, (String)x);
      } else if (x instanceof Short) {
         this.setShort(parameterIndex, (Short)x);
      } else if (x instanceof Integer) {
         this.setInt(parameterIndex, (Integer)x);
      } else if (x instanceof Long) {
         this.setLong(parameterIndex, (Long)x);
      } else if (x instanceof Float) {
         this.setFloat(parameterIndex, (Float)x);
      } else if (x instanceof Double) {
         this.setDouble(parameterIndex, (Double)x);
      } else if (x instanceof Boolean) {
         this.setBoolean(parameterIndex, (Boolean)x);
      } else if (x instanceof Byte) {
         this.setByte(parameterIndex, (Byte)x);
      } else if (x instanceof Character) {
         this.setString(parameterIndex, x.toString());
      } else if (x instanceof Timestamp) {
         this.setTimestamp(parameterIndex, (Timestamp)x);
      } else {
         if (!(x instanceof BigDecimal)) {
            throw new SQLException(MessageFormat.format("Can''t infer the SQL type to use for an instance of {0}. Use setObject() with an explicit Types value to specify the type to use.", x.getClass().getName()));
         }

         this.setString(parameterIndex, x.toString());
      }

   }

   public void setObject(int parameterIndex, Object x, int targetSqlType) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setObject(int parameterIndex, Object x, int targetSqlType, int scale) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setRef(int i, Ref x) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setRowId(int parameterIndex, RowId x) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setSQLXML(int parameterIndex, SQLXML xmlObject) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setShort(int parameterIndex, short x) throws SQLException {
      this.parameters.put(parameterIndex, "" + x);
   }

   private String replaceBackSlashSingleQuote(String x) {
      StringBuffer newX = new StringBuffer();

      for(int i = 0; i < x.length(); ++i) {
         char c = x.charAt(i);
         if (c == '\\' && i < x.length() - 1) {
            char c1 = x.charAt(i + 1);
            if (c1 == '\'') {
               newX.append(c1);
            } else {
               newX.append(c);
               newX.append(c1);
            }

            ++i;
         } else {
            newX.append(c);
         }
      }

      return newX.toString();
   }

   public void setString(int parameterIndex, String x) throws SQLException {
      x = this.replaceBackSlashSingleQuote(x);
      x = x.replace("'", "\\'");
      this.parameters.put(parameterIndex, "'" + x + "'");
   }

   public void setTime(int parameterIndex, Time x) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setTime(int parameterIndex, Time x, Calendar cal) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setTimestamp(int parameterIndex, Timestamp x) throws SQLException {
      this.parameters.put(parameterIndex, "'" + x.toString() + "'");
   }

   public void setTimestamp(int parameterIndex, Timestamp x, Calendar cal) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setURL(int parameterIndex, URL x) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setUnicodeStream(int parameterIndex, InputStream x, int length) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }
}
