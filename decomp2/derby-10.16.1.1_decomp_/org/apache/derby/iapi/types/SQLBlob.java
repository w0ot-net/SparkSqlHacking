package org.apache.derby.iapi.types;

import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.derby.iapi.services.io.CloneableStream;
import org.apache.derby.shared.common.error.StandardException;

public class SQLBlob extends SQLBinary {
   public SQLBlob() {
   }

   public SQLBlob(byte[] var1) {
      super(var1);
   }

   public SQLBlob(Blob var1) {
      super(var1);
   }

   public String getTypeName() {
      return "BLOB";
   }

   int getMaxMemoryUsage() {
      return Integer.MAX_VALUE;
   }

   public boolean hasStream() {
      return this.stream != null;
   }

   public DataValueDescriptor cloneValue(boolean var1) {
      SQLBlob var2 = new SQLBlob();
      if (this.isNull()) {
         return var2;
      } else {
         if (!var1 && this.dataValue == null) {
            if (this.stream != null && this.stream instanceof CloneableStream) {
               var2.setStream(((CloneableStream)this.stream).cloneStream());
               if (this.streamValueLength != -1) {
                  var2.streamValueLength = this.streamValueLength;
               }
            } else if (this._blobValue != null) {
               var2.setValue(this._blobValue);
            }
         }

         if (var2.isNull() || var1) {
            try {
               var2.setValue(this.getBytes());
            } catch (StandardException var4) {
               return null;
            }
         }

         return var2;
      }
   }

   public DataValueDescriptor getNewNull() {
      return new SQLBlob();
   }

   public Object getObject() throws StandardException {
      if (this._blobValue != null) {
         return this._blobValue;
      } else {
         byte[] var1 = this.getBytes();
         return var1 == null ? null : new HarmonySerialBlob(var1);
      }
   }

   public void normalize(DataTypeDescriptor var1, DataValueDescriptor var2) throws StandardException {
      this.setValue(var2);
      this.setWidth(var1.getMaximumWidth(), 0, true);
   }

   public void setWidth(int var1, int var2, boolean var3) throws StandardException {
      if (!this.isNull()) {
         if (!this.isLengthLess()) {
            int var4 = this.getLength();
            if (var4 > var1) {
               if (var3) {
                  throw StandardException.newException("22001", new Object[]{this.getTypeName(), "XXXX", String.valueOf(var1)});
               }

               this.truncate(var4, var1, true);
            }

         }
      }
   }

   public int getTypeFormatId() {
      return 443;
   }

   public void setValueFromResultSet(ResultSet var1, int var2, boolean var3) throws SQLException, StandardException {
      Blob var4 = var1.getBlob(var2);
      if (var4 == null) {
         this.setToNull();
      } else {
         this.setObject(var4);
      }

   }

   public int typePrecedence() {
      return 170;
   }

   public void setInto(PreparedStatement var1, int var2) throws SQLException, StandardException {
      if (this.isNull()) {
         var1.setBlob(var2, (Blob)null);
      } else {
         var1.setBytes(var2, this.getBytes());
      }
   }

   final void setObject(Object var1) throws StandardException {
      Blob var2 = (Blob)var1;

      try {
         long var3 = var2.length();
         if (var3 >= 0L && var3 <= 2147483647L) {
            this.setValue(new RawToBinaryFormatStream(var2.getBinaryStream(), (int)var3), (int)var3);
         } else {
            throw this.outOfRange();
         }
      } catch (SQLException var5) {
         throw this.dataTypeConversion("DAN-438-tmp");
      }
   }

   private final boolean isLengthLess() {
      return this.stream != null && this.streamValueLength < 0;
   }
}
