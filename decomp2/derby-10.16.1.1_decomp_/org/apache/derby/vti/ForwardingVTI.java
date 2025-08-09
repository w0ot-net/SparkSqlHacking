package org.apache.derby.vti;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;

public class ForwardingVTI extends VTITemplate {
   private ResultSet _wrappedResultSet;

   public final void wrapResultSet(ResultSet var1) {
      this._wrappedResultSet = var1;
   }

   public final ResultSet getWrappedResultSet() {
      return this._wrappedResultSet;
   }

   protected int mapColumnNumber(int var1) {
      return var1;
   }

   public void close() throws SQLException {
      this._wrappedResultSet.close();
   }

   public boolean next() throws SQLException {
      return this._wrappedResultSet.next();
   }

   public boolean isClosed() throws SQLException {
      return this._wrappedResultSet.isClosed();
   }

   public boolean wasNull() throws SQLException {
      return this._wrappedResultSet.wasNull();
   }

   public ResultSetMetaData getMetaData() throws SQLException {
      return this._wrappedResultSet.getMetaData();
   }

   public InputStream getAsciiStream(int var1) throws SQLException {
      return this._wrappedResultSet.getAsciiStream(this.mapColumnNumber(var1));
   }

   public BigDecimal getBigDecimal(int var1) throws SQLException {
      return this._wrappedResultSet.getBigDecimal(this.mapColumnNumber(var1));
   }

   /** @deprecated */
   @Deprecated
   public BigDecimal getBigDecimal(int var1, int var2) throws SQLException {
      return this._wrappedResultSet.getBigDecimal(this.mapColumnNumber(var1), var2);
   }

   public InputStream getBinaryStream(int var1) throws SQLException {
      return this._wrappedResultSet.getBinaryStream(this.mapColumnNumber(var1));
   }

   public Blob getBlob(int var1) throws SQLException {
      return this._wrappedResultSet.getBlob(this.mapColumnNumber(var1));
   }

   public boolean getBoolean(int var1) throws SQLException {
      return this._wrappedResultSet.getBoolean(this.mapColumnNumber(var1));
   }

   public byte getByte(int var1) throws SQLException {
      return this._wrappedResultSet.getByte(this.mapColumnNumber(var1));
   }

   public byte[] getBytes(int var1) throws SQLException {
      return this._wrappedResultSet.getBytes(this.mapColumnNumber(var1));
   }

   public Reader getCharacterStream(int var1) throws SQLException {
      return this._wrappedResultSet.getCharacterStream(this.mapColumnNumber(var1));
   }

   public Clob getClob(int var1) throws SQLException {
      return this._wrappedResultSet.getClob(this.mapColumnNumber(var1));
   }

   public Date getDate(int var1) throws SQLException {
      return this._wrappedResultSet.getDate(this.mapColumnNumber(var1));
   }

   public Date getDate(int var1, Calendar var2) throws SQLException {
      return this._wrappedResultSet.getDate(this.mapColumnNumber(var1), var2);
   }

   public double getDouble(int var1) throws SQLException {
      return this._wrappedResultSet.getDouble(this.mapColumnNumber(var1));
   }

   public float getFloat(int var1) throws SQLException {
      return this._wrappedResultSet.getFloat(this.mapColumnNumber(var1));
   }

   public int getInt(int var1) throws SQLException {
      return this._wrappedResultSet.getInt(this.mapColumnNumber(var1));
   }

   public long getLong(int var1) throws SQLException {
      return this._wrappedResultSet.getLong(this.mapColumnNumber(var1));
   }

   public Object getObject(int var1) throws SQLException {
      return this._wrappedResultSet.getObject(this.mapColumnNumber(var1));
   }

   public short getShort(int var1) throws SQLException {
      return this._wrappedResultSet.getShort(this.mapColumnNumber(var1));
   }

   public String getString(int var1) throws SQLException {
      return this._wrappedResultSet.getString(this.mapColumnNumber(var1));
   }

   public Time getTime(int var1) throws SQLException {
      return this._wrappedResultSet.getTime(this.mapColumnNumber(var1));
   }

   public Time getTime(int var1, Calendar var2) throws SQLException {
      return this._wrappedResultSet.getTime(this.mapColumnNumber(var1), var2);
   }

   public Timestamp getTimestamp(int var1) throws SQLException {
      return this._wrappedResultSet.getTimestamp(this.mapColumnNumber(var1));
   }

   public Timestamp getTimestamp(int var1, Calendar var2) throws SQLException {
      return this._wrappedResultSet.getTimestamp(this.mapColumnNumber(var1), var2);
   }
}
