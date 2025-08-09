package org.apache.derby.vti;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import org.apache.derby.iapi.types.HarmonySerialBlob;
import org.apache.derby.iapi.types.HarmonySerialClob;
import org.apache.derby.shared.common.error.StandardException;
import org.apache.derby.shared.common.util.ArrayUtil;

public abstract class StringColumnVTI extends VTITemplate {
   private String[] _columnNames;
   private boolean _lastColumnWasNull;

   protected abstract String getRawColumn(int var1) throws SQLException;

   public StringColumnVTI(String[] var1) {
      if (var1 != null) {
         this._columnNames = (String[])ArrayUtil.copy(var1);
      }

   }

   public void setColumnNames(String[] var1) throws SQLException {
      if (this._columnNames != null) {
         throw this.makeSQLException("X0Y92.S");
      } else {
         this._columnNames = (String[])ArrayUtil.copy(var1);
      }
   }

   public int getColumnCount() {
      return this._columnNames.length;
   }

   public String getColumnName(int var1) {
      return this._columnNames[var1 - 1];
   }

   public boolean wasNull() throws SQLException {
      return this._lastColumnWasNull;
   }

   public int findColumn(String var1) throws SQLException {
      int var2 = this._columnNames.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         if (this._columnNames[var3].equals(var1)) {
            return var3 + 1;
         }
      }

      throw new SQLException("Unknown column name.");
   }

   public String getString(int var1) throws SQLException {
      String var2 = this.getRawColumn(var1);
      this.checkNull(var2);
      return var2;
   }

   public boolean getBoolean(int var1) throws SQLException {
      String var2 = this.getString(var1);
      return var2 == null ? false : Boolean.valueOf(var2);
   }

   public byte getByte(int var1) throws SQLException {
      String var2 = this.getString(var1);
      if (var2 == null) {
         return 0;
      } else {
         try {
            return Byte.valueOf(var2);
         } catch (NumberFormatException var4) {
            throw this.wrap(var4);
         }
      }
   }

   public short getShort(int var1) throws SQLException {
      String var2 = this.getString(var1);
      if (var2 == null) {
         return 0;
      } else {
         try {
            return Short.valueOf(var2);
         } catch (NumberFormatException var4) {
            throw this.wrap(var4);
         }
      }
   }

   public int getInt(int var1) throws SQLException {
      String var2 = this.getString(var1);
      if (var2 == null) {
         return 0;
      } else {
         try {
            return Integer.parseInt(var2);
         } catch (NumberFormatException var4) {
            throw this.wrap(var4);
         }
      }
   }

   public long getLong(int var1) throws SQLException {
      String var2 = this.getString(var1);
      if (var2 == null) {
         return 0L;
      } else {
         try {
            return Long.valueOf(var2);
         } catch (NumberFormatException var4) {
            throw this.wrap(var4);
         }
      }
   }

   public float getFloat(int var1) throws SQLException {
      String var2 = this.getString(var1);
      if (var2 == null) {
         return 0.0F;
      } else {
         try {
            return Float.parseFloat(var2);
         } catch (NumberFormatException var4) {
            throw this.wrap(var4);
         }
      }
   }

   public double getDouble(int var1) throws SQLException {
      String var2 = this.getString(var1);
      if (var2 == null) {
         return (double)0.0F;
      } else {
         try {
            return Double.parseDouble(var2);
         } catch (NumberFormatException var4) {
            throw this.wrap(var4);
         }
      }
   }

   public BigDecimal getBigDecimal(int var1) throws SQLException {
      String var2 = this.getString(var1);
      if (var2 == null) {
         return null;
      } else {
         try {
            return new BigDecimal(var2);
         } catch (NumberFormatException var4) {
            throw this.wrap(var4);
         }
      }
   }

   public byte[] getBytes(int var1) throws SQLException {
      String var2 = this.getString(var1);
      if (var2 == null) {
         return null;
      } else {
         try {
            return var2.getBytes("UTF-8");
         } catch (Throwable var4) {
            throw new SQLException(var4.getMessage());
         }
      }
   }

   public Date getDate(int var1) throws SQLException {
      String var2 = this.getString(var1);
      return var2 == null ? null : new Date(this.parseDateTime(var2));
   }

   public Time getTime(int var1) throws SQLException {
      String var2 = this.getString(var1);
      return var2 == null ? null : new Time(this.parseDateTime(var2));
   }

   public Timestamp getTimestamp(int var1) throws SQLException {
      String var2 = this.getString(var1);
      return var2 == null ? null : new Timestamp(this.parseDateTime(var2));
   }

   public InputStream getAsciiStream(int var1) throws SQLException {
      String var2 = this.getString(var1);
      return this.getEncodedStream(var2, "US-ASCII");
   }

   public InputStream getBinaryStream(int var1) throws SQLException {
      String var2 = this.getString(var1);
      return var2 == null ? null : new ByteArrayInputStream(this.getBytes(var1));
   }

   public Blob getBlob(int var1) throws SQLException {
      String var2 = this.getString(var1);
      return var2 == null ? null : new HarmonySerialBlob(this.getBytes(var1));
   }

   public Clob getClob(int var1) throws SQLException {
      String var2 = this.getString(var1);
      return var2 == null ? null : new HarmonySerialClob(this.getString(var1));
   }

   private void checkNull(String var1) {
      this._lastColumnWasNull = var1 == null;
   }

   private SQLException wrap(Throwable var1) {
      return new SQLException(var1.getMessage());
   }

   private long parseDateTime(String var1) throws SQLException {
      try {
         DateFormat var2 = DateFormat.getDateTimeInstance();
         java.util.Date var3 = var2.parse(var1);
         return var3.getTime();
      } catch (ParseException var4) {
         throw this.wrap(var4);
      }
   }

   private InputStream getEncodedStream(String var1, String var2) throws SQLException {
      if (var1 == null) {
         return null;
      } else {
         try {
            byte[] var3 = var1.getBytes(var2);
            return new ByteArrayInputStream(var3);
         } catch (UnsupportedEncodingException var4) {
            throw this.wrap(var4);
         }
      }
   }

   private SQLException makeSQLException(String var1, Object... var2) {
      StandardException var3 = StandardException.newException(var1, var2);
      return new SQLException(var3.getMessage(), var3.getSQLState());
   }
}
