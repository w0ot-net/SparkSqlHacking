package org.apache.derby.iapi.types;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.sql.Blob;
import java.sql.DataTruncation;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.apache.derby.iapi.services.cache.ClassSize;
import org.apache.derby.iapi.services.io.DerbyIOException;
import org.apache.derby.iapi.services.io.FormatIdInputStream;
import org.apache.derby.iapi.services.io.InputStreamUtil;
import org.apache.derby.iapi.sql.conn.StatementContext;
import org.apache.derby.iapi.util.StringUtil;
import org.apache.derby.shared.common.error.StandardException;
import org.apache.derby.shared.common.i18n.MessageService;

abstract class SQLBinary extends DataType implements BitDataValue {
   static final byte PAD = 32;
   private static final int BASE_MEMORY_USAGE = ClassSize.estimateBaseFromCatalog(SQLBinary.class);
   private static final int LEN_OF_BUFFER_TO_WRITE_BLOB = 1024;
   Blob _blobValue;
   byte[] dataValue;
   InputStream stream;
   int streamValueLength;

   public int estimateMemoryUsage() {
      if (this.dataValue == null) {
         return this.streamValueLength >= 0 ? BASE_MEMORY_USAGE + this.streamValueLength : this.getMaxMemoryUsage();
      } else {
         return BASE_MEMORY_USAGE + this.dataValue.length;
      }
   }

   abstract int getMaxMemoryUsage();

   SQLBinary() {
   }

   SQLBinary(byte[] var1) {
      this.dataValue = var1;
   }

   SQLBinary(Blob var1) {
      this.setValue(var1);
   }

   public final void setValue(byte[] var1) {
      this.dataValue = var1;
      this._blobValue = null;
      this.stream = null;
      this.streamValueLength = -1;
   }

   public final void setValue(Blob var1) {
      this.dataValue = null;
      this._blobValue = var1;
      this.stream = null;
      this.streamValueLength = -1;
   }

   public final String getString() throws StandardException {
      if (this.getValue() == null) {
         return null;
      } else if (this.dataValue.length * 2 < 0) {
         throw StandardException.newException("22001", new Object[]{this.getTypeName(), "", String.valueOf(Integer.MAX_VALUE)});
      } else {
         return StringUtil.toHexString(this.dataValue, 0, this.dataValue.length);
      }
   }

   public final InputStream getStream() throws StandardException {
      if (!this.hasStream()) {
         throw StandardException.newException("42Z12.U", new Object[]{this.getTypeName()});
      } else {
         return this.stream;
      }
   }

   public final byte[] getBytes() throws StandardException {
      return this.getValue();
   }

   byte[] getValue() throws StandardException {
      try {
         if (this.dataValue == null && this._blobValue != null) {
            this.dataValue = this._blobValue.getBytes(1L, this.getBlobLength());
            this._blobValue = null;
            this.stream = null;
            this.streamValueLength = -1;
         } else if (this.dataValue == null && this.stream != null) {
            if (this.stream instanceof FormatIdInputStream) {
               this.readExternal((FormatIdInputStream)this.stream);
            } else {
               this.readExternal(new FormatIdInputStream(this.stream));
            }

            this._blobValue = null;
            this.stream = null;
            this.streamValueLength = -1;
         }
      } catch (IOException var2) {
         this.throwStreamingIOException(var2);
      } catch (SQLException var3) {
         throw StandardException.plainWrapException(var3);
      }

      return this.dataValue;
   }

   public final int getLength() throws StandardException {
      if (this._blobValue != null) {
         return this.getBlobLength();
      } else {
         if (this.stream != null) {
            if (this.streamValueLength != -1) {
               return this.streamValueLength;
            }

            if (this.stream instanceof Resetable) {
               label104: {
                  int var1;
                  try {
                     this.streamValueLength = readBinaryLength((ObjectInput)this.stream);
                     if (this.streamValueLength == 0) {
                        this.streamValueLength = (int)InputStreamUtil.skipUntilEOF(this.stream);
                     }

                     var1 = this.streamValueLength;
                  } catch (IOException var11) {
                     this.throwStreamingIOException(var11);
                     break label104;
                  } finally {
                     try {
                        ((Resetable)this.stream).resetStream();
                     } catch (IOException var10) {
                        this.throwStreamingIOException(var10);
                     }

                  }

                  return var1;
               }
            }
         }

         byte[] var13 = this.getBytes();
         return var13 == null ? 0 : var13.length;
      }
   }

   private void throwStreamingIOException(IOException var1) throws StandardException {
      throw StandardException.newException("XCL30.S", var1, new Object[]{this.getTypeName()});
   }

   public final boolean isNull() {
      return this.dataValue == null && this.stream == null && this._blobValue == null;
   }

   public final void writeExternal(ObjectOutput var1) throws IOException {
      if (this._blobValue != null) {
         this.writeBlob(var1);
      } else {
         int var2 = this.dataValue.length;
         this.writeLength(var1, var2);
         var1.write(this.dataValue, 0, this.dataValue.length);
      }
   }

   private void writeBlob(ObjectOutput var1) throws IOException {
      try {
         int var2 = this.getBlobLength();
         InputStream var3 = this._blobValue.getBinaryStream();
         this.writeLength(var1, var2);
         int var4 = 0;
         int var5 = 0;

         for(byte[] var6 = new byte[Math.min(var2, 1024)]; var4 < var2; var4 += var5) {
            var5 = var3.read(var6);
            if (var5 == -1) {
               throw new DerbyIOException(MessageService.getTextMessage("XJ023.S", new Object[0]), "XJ023.S");
            }

            var1.write(var6, 0, var5);
         }

      } catch (StandardException var7) {
         throw new IOException(var7.getMessage());
      } catch (SQLException var8) {
         throw new IOException(var8.getMessage());
      }
   }

   private void writeLength(ObjectOutput var1, int var2) throws IOException {
      if (var2 <= 31) {
         var1.write((byte)(128 | var2 & 255));
      } else if (var2 <= 65535) {
         var1.write(-96);
         var1.writeShort((short)var2);
      } else {
         var1.write(-64);
         var1.writeInt(var2);
      }

   }

   public final void readExternal(ObjectInput var1) throws IOException {
      this.stream = null;
      this.streamValueLength = -1;
      this._blobValue = null;
      int var2 = readBinaryLength(var1);
      if (var2 != 0) {
         this.dataValue = new byte[var2];
         var1.readFully(this.dataValue);
      } else {
         this.readFromStream((InputStream)var1);
      }

   }

   private static int readBinaryLength(ObjectInput var0) throws IOException {
      int var1 = var0.read();
      if (var1 == -1) {
         throw new EOFException();
      } else {
         byte var2 = (byte)var1;
         int var3;
         if ((var2 & -128) != 0) {
            if (var2 == -64) {
               var3 = var0.readInt();
            } else if (var2 == -96) {
               var3 = var0.readUnsignedShort();
            } else {
               var3 = var2 & 31;
            }
         } else {
            int var4 = var0.read();
            int var5 = var0.read();
            int var6 = var0.read();
            if (var4 == -1 || var5 == -1 || var6 == -1) {
               throw new EOFException();
            }

            int var7 = (var1 & 255) << 24 | (var4 & 255) << 16 | (var5 & 255) << 8 | var6 & 255;
            var3 = var7 / 8;
            if (var7 % 8 != 0) {
               ++var3;
            }
         }

         return var3;
      }
   }

   private void readFromStream(InputStream var1) throws IOException {
      this.dataValue = null;
      byte[] var2 = new byte['耀'];
      int var3 = 0;

      while(true) {
         int var4 = var1.read(var2, var3, var2.length - var3);
         if (var4 == -1) {
            this.dataValue = new byte[var3];
            System.arraycopy(var2, 0, this.dataValue, 0, var3);
            return;
         }

         var3 += var4;
         int var5 = Math.max(1, var1.available());
         int var6 = var5 - (var2.length - var3);
         if (var6 > 0) {
            int var7 = var2.length * 2;
            if (var6 > var2.length) {
               var7 += var6;
            }

            byte[] var8 = new byte[var7];
            System.arraycopy(var2, 0, var8, 0, var3);
            var2 = var8;
         }
      }
   }

   public final void restoreToNull() {
      this.dataValue = null;
      this._blobValue = null;
      this.stream = null;
      this.streamValueLength = -1;
   }

   public final boolean compare(int var1, DataValueDescriptor var2, boolean var3, boolean var4) throws StandardException {
      return var3 || !this.isNull() && !var2.isNull() ? super.compare(var1, var2, var3, var4) : var4;
   }

   public final int compare(DataValueDescriptor var1) throws StandardException {
      if (this.typePrecedence() < var1.typePrecedence()) {
         return -var1.compare(this);
      } else if (!this.isNull() && !var1.isNull()) {
         return compare(this.getBytes(), var1.getBytes());
      } else if (!this.isNull()) {
         return -1;
      } else {
         return !var1.isNull() ? 1 : 0;
      }
   }

   public final DataValueDescriptor cloneHolder() {
      if (this.stream == null && this._blobValue == null) {
         return this.cloneValue(false);
      } else {
         SQLBinary var1 = (SQLBinary)this.getNewNull();
         if (this.stream != null) {
            var1.setValue(this.stream, this.streamValueLength);
         } else {
            if (this._blobValue == null) {
               throw new IllegalStateException("unknown BLOB value repr");
            }

            var1.setValue(this._blobValue);
         }

         return var1;
      }
   }

   public DataValueDescriptor cloneValue(boolean var1) {
      try {
         DataValueDescriptor var2 = this.getNewNull();
         var2.setValue(this.getValue());
         return var2;
      } catch (StandardException var3) {
         return null;
      }
   }

   public final InputStream returnStream() {
      return this.stream;
   }

   public final void setStream(InputStream var1) {
      this.dataValue = null;
      this._blobValue = null;
      this.stream = var1;
      this.streamValueLength = -1;
   }

   public final void loadStream() throws StandardException {
      this.getValue();
   }

   boolean objectNull(Object var1) {
      if (var1 == null) {
         this.setToNull();
         return true;
      } else {
         return false;
      }
   }

   public final void setValue(InputStream var1, int var2) {
      this.dataValue = null;
      this._blobValue = null;
      this.stream = var1;
      this.streamValueLength = var2;
   }

   protected final void setFrom(DataValueDescriptor var1) throws StandardException {
      if (var1 instanceof SQLBinary var2) {
         this.dataValue = var2.dataValue;
         this._blobValue = var2._blobValue;
         this.stream = var2.stream;
         this.streamValueLength = var2.streamValueLength;
      } else {
         this.setValue(var1.getBytes());
      }

   }

   public final BooleanDataValue equals(DataValueDescriptor var1, DataValueDescriptor var2) throws StandardException {
      boolean var3;
      if (!var1.isNull() && !var2.isNull()) {
         var3 = compare(var1.getBytes(), var2.getBytes()) == 0;
      } else {
         var3 = false;
      }

      return SQLBoolean.truthValue(var1, var2, var3);
   }

   public final BooleanDataValue notEquals(DataValueDescriptor var1, DataValueDescriptor var2) throws StandardException {
      boolean var3;
      if (!var1.isNull() && !var2.isNull()) {
         var3 = compare(var1.getBytes(), var2.getBytes()) != 0;
      } else {
         var3 = false;
      }

      return SQLBoolean.truthValue(var1, var2, var3);
   }

   public final BooleanDataValue lessThan(DataValueDescriptor var1, DataValueDescriptor var2) throws StandardException {
      boolean var3;
      if (!var1.isNull() && !var2.isNull()) {
         var3 = compare(var1.getBytes(), var2.getBytes()) < 0;
      } else {
         var3 = false;
      }

      return SQLBoolean.truthValue(var1, var2, var3);
   }

   public final BooleanDataValue greaterThan(DataValueDescriptor var1, DataValueDescriptor var2) throws StandardException {
      boolean var3 = false;
      if (!var1.isNull() && !var2.isNull()) {
         var3 = compare(var1.getBytes(), var2.getBytes()) > 0;
      } else {
         var3 = false;
      }

      return SQLBoolean.truthValue(var1, var2, var3);
   }

   public final BooleanDataValue lessOrEquals(DataValueDescriptor var1, DataValueDescriptor var2) throws StandardException {
      boolean var3 = false;
      if (!var1.isNull() && !var2.isNull()) {
         var3 = compare(var1.getBytes(), var2.getBytes()) <= 0;
      } else {
         var3 = false;
      }

      return SQLBoolean.truthValue(var1, var2, var3);
   }

   public final BooleanDataValue greaterOrEquals(DataValueDescriptor var1, DataValueDescriptor var2) throws StandardException {
      boolean var3 = false;
      if (!var1.isNull() && !var2.isNull()) {
         var3 = compare(var1.getBytes(), var2.getBytes()) >= 0;
      } else {
         var3 = false;
      }

      return SQLBoolean.truthValue(var1, var2, var3);
   }

   public final NumberDataValue charLength(NumberDataValue var1) throws StandardException {
      if (var1 == null) {
         var1 = new SQLInteger();
      }

      if (this.isNull()) {
         ((NumberDataValue)var1).setToNull();
         return (NumberDataValue)var1;
      } else {
         ((NumberDataValue)var1).setValue(this.getValue().length);
         return (NumberDataValue)var1;
      }
   }

   public final BitDataValue concatenate(BitDataValue var1, BitDataValue var2, BitDataValue var3) throws StandardException {
      if (var3 == null) {
         var3 = (BitDataValue)this.getNewNull();
      }

      if (!var1.isNull() && !var2.isNull()) {
         byte[] var4 = var1.getBytes();
         byte[] var5 = var2.getBytes();
         byte[] var6 = new byte[var4.length + var5.length];
         System.arraycopy(var4, 0, var6, 0, var4.length);
         System.arraycopy(var5, 0, var6, var4.length, var5.length);
         var3.setValue(var6);
         return var3;
      } else {
         var3.setToNull();
         return var3;
      }
   }

   public final ConcatableDataValue substring(NumberDataValue var1, NumberDataValue var2, ConcatableDataValue var3, int var4) throws StandardException {
      if (var3 == null) {
         var3 = new SQLVarbit();
      }

      BitDataValue var7 = (BitDataValue)var3;
      if (!this.isNull() && !var1.isNull() && (var2 == null || !var2.isNull())) {
         int var5 = var1.getInt();
         int var6;
         if (var2 != null) {
            var6 = var2.getInt();
         } else {
            var6 = this.getLength() - var5 + 1;
         }

         if (var5 > 0 && var6 >= 0 && var5 <= this.getLength() && var6 <= this.getLength() - var5 + 1) {
            if (var6 < 0) {
               var7.setToNull();
               return var7;
            } else {
               if (var5 < 0) {
                  var5 += this.getLength();
                  if (var5 < 0) {
                     var6 += var5;
                     var5 = 0;
                  }

                  if (var6 + var5 > 0) {
                     var6 += var5;
                  } else {
                     var6 = 0;
                  }
               } else if (var5 > 0) {
                  --var5;
               }

               if (var6 != 0 && var6 > 0 - var5 && var5 <= this.getLength()) {
                  if (var6 >= this.getLength() - var5) {
                     byte[] var8 = new byte[this.dataValue.length - var5];
                     System.arraycopy(this.dataValue, var5, var8, 0, var8.length);
                     var7.setValue(var8);
                  } else {
                     byte[] var9 = new byte[var6];
                     System.arraycopy(this.dataValue, var5, var9, 0, var9.length);
                     var7.setValue(var9);
                  }

                  return var7;
               } else {
                  var7.setValue(new byte[0]);
                  return var7;
               }
            }
         } else {
            throw StandardException.newException("22011", new Object[0]);
         }
      } else {
         var7.setToNull();
         return var7;
      }
   }

   public final void checkHostVariable(int var1) throws StandardException {
      int var2 = -1;
      if (this._blobValue != null) {
         var2 = -1;
      } else if (this.stream == null) {
         if (this.dataValue != null) {
            var2 = this.dataValue.length;
         }
      } else {
         var2 = this.streamValueLength;
      }

      if (var2 != -1 && var2 > var1) {
         throw StandardException.newException("22001", new Object[]{this.getTypeName(), MessageService.getTextMessage("BIN01", new Object[0]), String.valueOf(var1)});
      }
   }

   public final String toString() {
      if (this.dataValue == null) {
         return this.stream == null && this._blobValue == null ? "NULL" : "";
      } else {
         return StringUtil.toHexString(this.dataValue, 0, this.dataValue.length);
      }
   }

   public final int hashCode() {
      try {
         if (this.getValue() == null) {
            return 0;
         }
      } catch (StandardException var5) {
         return 0;
      }

      byte[] var1 = this.dataValue;

      int var2;
      for(var2 = var1.length - 1; var2 >= 0 && var1[var2] == 32; --var2) {
      }

      int var3 = 0;

      for(int var4 = 0; var4 <= var2; ++var4) {
         var3 = var3 * 31 + var1[var4];
      }

      return var3;
   }

   private static int compare(byte[] var0, byte[] var1) {
      int var2 = var0.length;
      byte[] var3 = var1;
      if (var1.length < var2) {
         var2 = var1.length;
         var3 = var0;
      }

      for(int var4 = 0; var4 < var2; ++var4) {
         int var5 = var0[var4] & 255;
         int var6 = var1[var4] & 255;
         if (var5 != var6) {
            return var5 - var6;
         }
      }

      for(int var7 = var2; var7 < var3.length; ++var7) {
         byte var8 = var3[var7];
         if (var8 != 32) {
            if (var0 == var3) {
               return 1;
            }

            return -1;
         }
      }

      return 0;
   }

   public void setInto(PreparedStatement var1, int var2) throws SQLException, StandardException {
      var1.setBytes(var2, this.getBytes());
   }

   public final String getTraceString() throws StandardException {
      if (this.isNull()) {
         return "NULL";
      } else if (this.hasStream()) {
         String var1 = this.getTypeName();
         return var1 + "(" + this.getStream().toString() + ")";
      } else {
         String var10000 = this.getTypeName();
         return var10000 + ":Length=" + this.getLength();
      }
   }

   private int getBlobLength() throws StandardException {
      try {
         long var1 = 2147483647L;
         long var3 = this._blobValue.length();
         if (var3 > 2147483647L) {
            throw StandardException.newException("XJ093.S", new Object[]{Long.toString(var3), Long.toString(var1)});
         } else {
            return (int)var3;
         }
      } catch (SQLException var5) {
         throw StandardException.plainWrapException(var5);
      }
   }

   void truncate(int var1, int var2, boolean var3) throws StandardException {
      if (var3) {
         DataTruncation var4 = new DataTruncation(-1, false, true, this.getLength(), var2);
         StatementContext var5 = (StatementContext)DataValueFactoryImpl.getContext("StatementContext");
         var5.getActivation().getResultSet().addWarning(var4);
      }

      byte[] var6 = new byte[var2];
      System.arraycopy(this.getValue(), 0, var6, 0, var2);
      this.setValue(var6);
   }
}
