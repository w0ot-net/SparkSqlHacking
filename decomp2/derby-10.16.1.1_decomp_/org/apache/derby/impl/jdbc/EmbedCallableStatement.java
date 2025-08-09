package org.apache.derby.impl.jdbc;

import java.io.ByteArrayInputStream;
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
import java.sql.Date;
import java.sql.NClob;
import java.sql.Ref;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Map;
import org.apache.derby.iapi.jdbc.CharacterStreamDescriptor;
import org.apache.derby.iapi.jdbc.EngineCallableStatement;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.ParameterValueSet;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.SQLDecimal;
import org.apache.derby.iapi.types.StringDataValue;
import org.apache.derby.shared.common.error.StandardException;

public class EmbedCallableStatement extends EmbedPreparedStatement implements EngineCallableStatement {
   private boolean hasReturnOutputParameter;
   protected boolean wasNull;

   public EmbedCallableStatement(EmbedConnection var1, String var2, int var3, int var4, int var5) throws SQLException {
      super(var1, var2, false, var3, var4, var5, 2, (int[])null, (String[])null);
      ParameterValueSet var6 = this.getParms();
      this.hasReturnOutputParameter = var6.hasReturnOutputParameter();
   }

   protected void checkRequiresCallableStatement(Activation var1) {
   }

   protected final boolean executeStatement(Activation var1, boolean var2, boolean var3) throws SQLException {
      this.checkExecStatus();
      synchronized(this.getConnectionSynchronization()) {
         this.wasNull = false;

         try {
            this.getParms().validate();
         } catch (StandardException var17) {
            throw EmbedResultSet.noStateChangeException(var17);
         }

         boolean var5 = super.executeStatement(var1, var2, var3 && !this.hasReturnOutputParameter);
         ParameterValueSet var6 = this.getParms();
         if (this.hasReturnOutputParameter) {
            boolean var7 = this.results.next();

            try {
               DataValueDescriptor var8 = var6.getReturnValueForSet();
               var8.setValueFromResultSet(this.results, 1, true);
            } catch (StandardException var15) {
               throw EmbedResultSet.noStateChangeException(var15);
            } finally {
               this.results.close();
               this.results = null;
            }

            var5 = false;
         }

         return var5;
      }
   }

   public final void registerOutParameter(int var1, int var2) throws SQLException {
      this.checkStatus();

      try {
         this.getParms().registerOutParameter(var1 - 1, var2, -1);
      } catch (StandardException var4) {
         throw EmbedResultSet.noStateChangeException(var4);
      }
   }

   public final void registerOutParameter(int var1, int var2, int var3) throws SQLException {
      this.checkStatus();
      if (var3 < 0) {
         throw newSQLException("XJ044.S", new Object[]{var3});
      } else {
         try {
            this.getParms().registerOutParameter(var1 - 1, var2, var3);
         } catch (StandardException var5) {
            throw EmbedResultSet.noStateChangeException(var5);
         }
      }
   }

   public void registerOutParameter(int var1, int var2, String var3) throws SQLException {
      this.registerOutParameter(var1, var2);
   }

   public boolean wasNull() throws SQLException {
      this.checkStatus();
      return this.wasNull;
   }

   public String getString(int var1) throws SQLException {
      this.checkStatus();

      try {
         String var2 = this.getParms().getParameterForGet(var1 - 1).getString();
         this.wasNull = var2 == null;
         return var2;
      } catch (StandardException var3) {
         throw EmbedResultSet.noStateChangeException(var3);
      }
   }

   public boolean getBoolean(int var1) throws SQLException {
      this.checkStatus();

      try {
         DataValueDescriptor var2 = this.getParms().getParameterForGet(var1 - 1);
         boolean var3 = var2.getBoolean();
         this.wasNull = !var3 && var2.isNull();
         return var3;
      } catch (StandardException var4) {
         throw EmbedResultSet.noStateChangeException(var4);
      }
   }

   public byte getByte(int var1) throws SQLException {
      this.checkStatus();

      try {
         DataValueDescriptor var2 = this.getParms().getParameterForGet(var1 - 1);
         byte var3 = var2.getByte();
         this.wasNull = var3 == 0 && var2.isNull();
         return var3;
      } catch (StandardException var4) {
         throw EmbedResultSet.noStateChangeException(var4);
      }
   }

   public short getShort(int var1) throws SQLException {
      this.checkStatus();

      try {
         DataValueDescriptor var2 = this.getParms().getParameterForGet(var1 - 1);
         short var3 = var2.getShort();
         this.wasNull = var3 == 0 && var2.isNull();
         return var3;
      } catch (StandardException var4) {
         throw EmbedResultSet.noStateChangeException(var4);
      }
   }

   public int getInt(int var1) throws SQLException {
      this.checkStatus();

      try {
         DataValueDescriptor var2 = this.getParms().getParameterForGet(var1 - 1);
         int var3 = var2.getInt();
         this.wasNull = var3 == 0 && var2.isNull();
         return var3;
      } catch (StandardException var4) {
         throw EmbedResultSet.noStateChangeException(var4);
      }
   }

   public long getLong(int var1) throws SQLException {
      this.checkStatus();

      try {
         DataValueDescriptor var2 = this.getParms().getParameterForGet(var1 - 1);
         long var3 = var2.getLong();
         this.wasNull = var3 == 0L && var2.isNull();
         return var3;
      } catch (StandardException var5) {
         throw EmbedResultSet.noStateChangeException(var5);
      }
   }

   public final BigDecimal getBigDecimal(int var1) throws SQLException {
      this.checkStatus();

      try {
         DataValueDescriptor var2 = this.getParms().getParameterForGet(var1 - 1);
         return (this.wasNull = var2.isNull()) ? null : SQLDecimal.getBigDecimal(var2);
      } catch (StandardException var3) {
         throw EmbedResultSet.noStateChangeException(var3);
      }
   }

   /** @deprecated */
   public final BigDecimal getBigDecimal(int var1, int var2) throws SQLException {
      BigDecimal var3 = this.getBigDecimal(var1);
      if (var3 != null) {
         var3 = var3.setScale(var2, 5);
      }

      return var3;
   }

   public float getFloat(int var1) throws SQLException {
      this.checkStatus();

      try {
         DataValueDescriptor var2 = this.getParms().getParameterForGet(var1 - 1);
         float var3 = var2.getFloat();
         this.wasNull = (double)var3 == (double)0.0F && var2.isNull();
         return var3;
      } catch (StandardException var4) {
         throw EmbedResultSet.noStateChangeException(var4);
      }
   }

   public double getDouble(int var1) throws SQLException {
      this.checkStatus();

      try {
         DataValueDescriptor var2 = this.getParms().getParameterForGet(var1 - 1);
         double var3 = var2.getDouble();
         this.wasNull = var3 == (double)0.0F && var2.isNull();
         return var3;
      } catch (StandardException var5) {
         throw EmbedResultSet.noStateChangeException(var5);
      }
   }

   public byte[] getBytes(int var1) throws SQLException {
      this.checkStatus();

      try {
         byte[] var2 = this.getParms().getParameterForGet(var1 - 1).getBytes();
         this.wasNull = var2 == null;
         return var2;
      } catch (StandardException var3) {
         throw EmbedResultSet.noStateChangeException(var3);
      }
   }

   public Date getDate(int var1, Calendar var2) throws SQLException {
      this.checkStatus();

      try {
         Date var3 = this.getParms().getParameterForGet(var1 - 1).getDate(var2);
         this.wasNull = var3 == null;
         return var3;
      } catch (StandardException var4) {
         throw EmbedResultSet.noStateChangeException(var4);
      }
   }

   public Time getTime(int var1, Calendar var2) throws SQLException {
      this.checkStatus();

      try {
         Time var3 = this.getParms().getParameterForGet(var1 - 1).getTime(var2);
         this.wasNull = var3 == null;
         return var3;
      } catch (StandardException var4) {
         throw EmbedResultSet.noStateChangeException(var4);
      }
   }

   public Timestamp getTimestamp(int var1, Calendar var2) throws SQLException {
      this.checkStatus();

      try {
         Timestamp var3 = this.getParms().getParameterForGet(var1 - 1).getTimestamp(var2);
         this.wasNull = var3 == null;
         return var3;
      } catch (StandardException var4) {
         throw EmbedResultSet.noStateChangeException(var4);
      }
   }

   public Date getDate(int var1) throws SQLException {
      return this.getDate(var1, this.getCal());
   }

   public Time getTime(int var1) throws SQLException {
      return this.getTime(var1, this.getCal());
   }

   public Timestamp getTimestamp(int var1) throws SQLException {
      return this.getTimestamp(var1, this.getCal());
   }

   public final Object getObject(int var1) throws SQLException {
      this.checkStatus();

      try {
         Object var2 = this.getParms().getParameterForGet(var1 - 1).getObject();
         this.wasNull = var2 == null;
         return var2;
      } catch (StandardException var3) {
         throw EmbedResultSet.noStateChangeException(var3);
      }
   }

   public URL getURL(int var1) throws SQLException {
      throw Util.notImplemented();
   }

   public void setURL(String var1, URL var2) throws SQLException {
      throw Util.notImplemented();
   }

   public URL getURL(String var1) throws SQLException {
      throw Util.notImplemented();
   }

   public Blob getBlob(int var1) throws SQLException {
      Object var2 = this.getObject(var1);
      if (var2 != null && !(var2 instanceof Blob)) {
         throw newSQLException("22005", new Object[]{Blob.class.getName(), Util.typeName(this.getParameterJDBCType(var1))});
      } else {
         return (Blob)var2;
      }
   }

   public Clob getClob(int var1) throws SQLException {
      Object var2 = this.getObject(var1);
      if (var2 != null && !(var2 instanceof Clob)) {
         throw newSQLException("22005", new Object[]{Clob.class.getName(), Util.typeName(this.getParameterJDBCType(var1))});
      } else {
         return (Clob)var2;
      }
   }

   public void addBatch() throws SQLException {
      this.checkStatus();
      ParameterValueSet var1 = this.getParms();
      int var2 = var1.getParameterCount();

      for(int var3 = 1; var3 <= var2; ++var3) {
         switch (var1.getParameterMode(var3)) {
            case 0:
            case 1:
            case 3:
            default:
            case 2:
            case 4:
               throw newSQLException("XJ04C.S", new Object[0]);
         }
      }

      super.addBatch();
   }

   public final Object getObject(int var1, Map var2) throws SQLException {
      this.checkStatus();
      if (var2 == null) {
         throw Util.generateCsSQLException("XJ081.S", var2, "map", "java.sql.CallableStatement.getObject");
      } else if (!var2.isEmpty()) {
         throw Util.notImplemented();
      } else {
         return this.getObject(var1);
      }
   }

   public final Ref getRef(int var1) throws SQLException {
      throw Util.notImplemented();
   }

   public final Array getArray(int var1) throws SQLException {
      throw Util.notImplemented();
   }

   public final void registerOutParameter(String var1, int var2) throws SQLException {
      throw Util.notImplemented();
   }

   public final void registerOutParameter(String var1, int var2, String var3) throws SQLException {
      throw Util.notImplemented();
   }

   public final void registerOutParameter(String var1, int var2, int var3) throws SQLException {
      throw Util.notImplemented();
   }

   public final Ref getRef(String var1) throws SQLException {
      throw Util.notImplemented();
   }

   public final Blob getBlob(String var1) throws SQLException {
      throw Util.notImplemented();
   }

   public final Clob getClob(String var1) throws SQLException {
      throw Util.notImplemented();
   }

   public final Array getArray(String var1) throws SQLException {
      throw Util.notImplemented();
   }

   public final void setNull(String var1, int var2) throws SQLException {
      throw Util.notImplemented();
   }

   public final void setNull(String var1, int var2, String var3) throws SQLException {
      throw Util.notImplemented();
   }

   public final void setBoolean(String var1, boolean var2) throws SQLException {
      throw Util.notImplemented();
   }

   public final boolean getBoolean(String var1) throws SQLException {
      throw Util.notImplemented();
   }

   public final void setByte(String var1, byte var2) throws SQLException {
      throw Util.notImplemented();
   }

   public final byte getByte(String var1) throws SQLException {
      throw Util.notImplemented();
   }

   public final void setShort(String var1, short var2) throws SQLException {
      throw Util.notImplemented();
   }

   public final short getShort(String var1) throws SQLException {
      throw Util.notImplemented();
   }

   public final void setInt(String var1, int var2) throws SQLException {
      throw Util.notImplemented();
   }

   public final int getInt(String var1) throws SQLException {
      throw Util.notImplemented();
   }

   public final void setLong(String var1, long var2) throws SQLException {
      throw Util.notImplemented();
   }

   public final long getLong(String var1) throws SQLException {
      throw Util.notImplemented();
   }

   public final void setFloat(String var1, float var2) throws SQLException {
      throw Util.notImplemented();
   }

   public final float getFloat(String var1) throws SQLException {
      throw Util.notImplemented();
   }

   public final void setDouble(String var1, double var2) throws SQLException {
      throw Util.notImplemented();
   }

   public final double getDouble(String var1) throws SQLException {
      throw Util.notImplemented();
   }

   public final void setBigDecimal(String var1, BigDecimal var2) throws SQLException {
      throw Util.notImplemented();
   }

   public final BigDecimal getBigDecimal(String var1) throws SQLException {
      throw Util.notImplemented();
   }

   public final void setString(String var1, String var2) throws SQLException {
      throw Util.notImplemented();
   }

   public final String getString(String var1) throws SQLException {
      throw Util.notImplemented();
   }

   public final void setBytes(String var1, byte[] var2) throws SQLException {
      throw Util.notImplemented();
   }

   public final byte[] getBytes(String var1) throws SQLException {
      throw Util.notImplemented();
   }

   public final void setDate(String var1, Date var2) throws SQLException {
      throw Util.notImplemented();
   }

   public final void setDate(String var1, Date var2, Calendar var3) throws SQLException {
      throw Util.notImplemented();
   }

   public final Date getDate(String var1) throws SQLException {
      throw Util.notImplemented();
   }

   public final Date getDate(String var1, Calendar var2) throws SQLException {
      throw Util.notImplemented();
   }

   public final void setTime(String var1, Time var2) throws SQLException {
      throw Util.notImplemented();
   }

   public final Time getTime(String var1) throws SQLException {
      throw Util.notImplemented();
   }

   public final Time getTime(String var1, Calendar var2) throws SQLException {
      throw Util.notImplemented();
   }

   public final void setTime(String var1, Time var2, Calendar var3) throws SQLException {
      throw Util.notImplemented();
   }

   public final void setTimestamp(String var1, Timestamp var2) throws SQLException {
      throw Util.notImplemented();
   }

   public final void setTimestamp(String var1, Timestamp var2, Calendar var3) throws SQLException {
      throw Util.notImplemented();
   }

   public final Timestamp getTimestamp(String var1) throws SQLException {
      throw Util.notImplemented();
   }

   public final Timestamp getTimestamp(String var1, Calendar var2) throws SQLException {
      throw Util.notImplemented();
   }

   public final void setAsciiStream(String var1, InputStream var2, int var3) throws SQLException {
      throw Util.notImplemented();
   }

   public final void setBinaryStream(String var1, InputStream var2, int var3) throws SQLException {
      throw Util.notImplemented();
   }

   public final void setCharacterStream(String var1, Reader var2, int var3) throws SQLException {
      throw Util.notImplemented();
   }

   public final void setObject(String var1, Object var2, int var3, int var4) throws SQLException {
      throw Util.notImplemented();
   }

   public final Object getObject(String var1) throws SQLException {
      throw Util.notImplemented();
   }

   public final Object getObject(String var1, Map var2) throws SQLException {
      this.checkStatus();
      if (var2 == null) {
         throw Util.generateCsSQLException("XJ081.S", var2, "map", "java.sql.CallableStatement.getObject");
      } else if (!var2.isEmpty()) {
         throw Util.notImplemented();
      } else {
         return this.getObject(var1);
      }
   }

   public final void setObject(String var1, Object var2, int var3) throws SQLException {
      throw Util.notImplemented();
   }

   public final void setObject(String var1, Object var2) throws SQLException {
      throw Util.notImplemented();
   }

   public final Reader getCharacterStream(int var1) throws SQLException {
      this.checkStatus();
      switch (this.getParms().getParameterMode(var1)) {
         case 0:
         case 1:
            throw newSQLException("XCL26.S", new Object[]{Integer.toString(var1)});
         default:
            Object var2 = null;
            int var3 = this.getParameterJDBCType(var1);
            switch (var3) {
               case -4:
               case -3:
               case -2:
               case 2004:
                  try {
                     InputStream var6 = this.getBinaryStream(var1);
                     if (var6 != null) {
                        var2 = new InputStreamReader(var6, "UTF-16BE");
                     }
                     break;
                  } catch (UnsupportedEncodingException var15) {
                     throw newSQLException(var15.getMessage(), new Object[0]);
                  }
               case -1:
               case 1:
               case 12:
               case 2005:
                  boolean var4 = false;
                  Object var5 = this.getConnectionSynchronization();
                  synchronized(var5) {
                     try {
                        StringDataValue var7 = (StringDataValue)this.getParms().getParameterForGet(var1 - 1);
                        if (!var7.isNull()) {
                           var4 = true;
                           this.setupContextStack();
                           if (var7.hasStream()) {
                              CharacterStreamDescriptor var8 = var7.getStreamWithDescriptor();
                              var2 = new UTF8Reader(var8, this, var5);
                           } else {
                              var2 = new StringReader(var7.getString());
                           }
                        }
                        break;
                     } catch (Throwable var16) {
                        throw EmbedResultSet.noStateChangeException(var16);
                     } finally {
                        if (var4) {
                           this.restoreContextStack();
                        }

                     }
                  }
               default:
                  throw newSQLException("22005", new Object[]{"java.io.Reader", Util.typeName(var3)});
            }

            this.wasNull = var2 == null;
            return (Reader)var2;
      }
   }

   private InputStream getBinaryStream(int var1) throws SQLException {
      int var2 = this.getParameterJDBCType(var1);
      switch (var2) {
         case -4:
         case -3:
         case -2:
         case 2004:
            boolean var3 = false;
            synchronized(this.getConnectionSynchronization()) {
               Object var6;
               try {
                  DataValueDescriptor var5 = this.getParms().getParameterForGet(var1 - 1);
                  this.wasNull = var5.isNull();
                  if (!this.wasNull) {
                     var3 = true;
                     this.setupContextStack();
                     if (var5.hasStream()) {
                        var6 = new BinaryToRawStream(var5.getStream(), var5);
                     } else {
                        var6 = new ByteArrayInputStream(var5.getBytes());
                     }

                     Object var7 = var6;
                     return (InputStream)var7;
                  }

                  var6 = null;
               } catch (Throwable var13) {
                  throw EmbedResultSet.noStateChangeException(var13);
               } finally {
                  if (var3) {
                     this.restoreContextStack();
                  }

               }

               return (InputStream)var6;
            }
         default:
            throw newSQLException("22005", new Object[]{"java.io.InputStream", Util.typeName(var2)});
      }
   }

   public final Reader getCharacterStream(String var1) throws SQLException {
      throw Util.notImplemented();
   }

   public final Reader getNCharacterStream(int var1) throws SQLException {
      throw Util.notImplemented();
   }

   public final Reader getNCharacterStream(String var1) throws SQLException {
      throw Util.notImplemented();
   }

   public final String getNString(int var1) throws SQLException {
      throw Util.notImplemented();
   }

   public final String getNString(String var1) throws SQLException {
      throw Util.notImplemented();
   }

   public final void setBlob(String var1, Blob var2) throws SQLException {
      throw Util.notImplemented();
   }

   public final void setClob(String var1, Clob var2) throws SQLException {
      throw Util.notImplemented();
   }

   public final RowId getRowId(int var1) throws SQLException {
      throw Util.notImplemented();
   }

   public final RowId getRowId(String var1) throws SQLException {
      throw Util.notImplemented();
   }

   public final void setRowId(String var1, RowId var2) throws SQLException {
      throw Util.notImplemented();
   }

   public final void setNString(String var1, String var2) throws SQLException {
      throw Util.notImplemented();
   }

   public final void setNCharacterStream(String var1, Reader var2, long var3) throws SQLException {
      throw Util.notImplemented();
   }

   public final void setNClob(String var1, NClob var2) throws SQLException {
      throw Util.notImplemented();
   }

   public final void setClob(String var1, Reader var2, long var3) throws SQLException {
      throw Util.notImplemented();
   }

   public final void setBlob(String var1, InputStream var2, long var3) throws SQLException {
      throw Util.notImplemented();
   }

   public final void setNClob(String var1, Reader var2, long var3) throws SQLException {
      throw Util.notImplemented();
   }

   public final NClob getNClob(int var1) throws SQLException {
      throw Util.notImplemented();
   }

   public final NClob getNClob(String var1) throws SQLException {
      throw Util.notImplemented();
   }

   public final void setSQLXML(String var1, SQLXML var2) throws SQLException {
      throw Util.notImplemented();
   }

   public final SQLXML getSQLXML(int var1) throws SQLException {
      throw Util.notImplemented();
   }

   public final SQLXML getSQLXML(String var1) throws SQLException {
      throw Util.notImplemented();
   }

   public final void setAsciiStream(String var1, InputStream var2) throws SQLException {
      throw Util.notImplemented("setAsciiStream(String,InputStream)");
   }

   public final void setBinaryStream(String var1, InputStream var2) throws SQLException {
      throw Util.notImplemented("setBinaryStream(String,InputStream)");
   }

   public final void setBlob(String var1, InputStream var2) throws SQLException {
      throw Util.notImplemented("setBlob(String,InputStream)");
   }

   public final void setCharacterStream(String var1, Reader var2) throws SQLException {
      throw Util.notImplemented("setCharacterStream(String,Reader)");
   }

   public final void setClob(String var1, Reader var2) throws SQLException {
      throw Util.notImplemented("setClob(String,Reader)");
   }

   public final void setNCharacterStream(String var1, Reader var2) throws SQLException {
      throw Util.notImplemented("setNCharacterStream(String,Reader)");
   }

   public final void setNClob(String var1, Reader var2) throws SQLException {
      throw Util.notImplemented("setNClob(String,Reader)");
   }

   public final void setAsciiStream(String var1, InputStream var2, long var3) throws SQLException {
      throw Util.notImplemented();
   }

   public final void setBinaryStream(String var1, InputStream var2, long var3) throws SQLException {
      throw Util.notImplemented();
   }

   public final void setCharacterStream(String var1, Reader var2, long var3) throws SQLException {
      throw Util.notImplemented();
   }

   public final Object getObject(int var1, Class var2) throws SQLException {
      this.checkStatus();
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
      String var3 = this.getParameterMetaData().getParameterTypeName(var2);
      return newSQLException("22005", new Object[]{var1, var3});
   }

   public final Object getObject(String var1, Class var2) throws SQLException {
      throw Util.notImplemented();
   }
}
