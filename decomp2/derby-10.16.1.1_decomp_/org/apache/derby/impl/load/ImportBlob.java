package org.apache.derby.impl.load;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;
import org.apache.derby.iapi.services.io.LimitInputStream;

class ImportBlob implements Blob {
   private ImportLobFile lobFile;
   private long blobPosition;
   private long blobLength;
   private byte[] blobData = null;

   public ImportBlob(ImportLobFile var1, long var2, long var4) {
      this.lobFile = var1;
      this.blobPosition = var2;
      this.blobLength = var4;
   }

   public ImportBlob(byte[] var1) {
      this.blobData = var1;
      this.blobLength = (long)var1.length;
   }

   public long length() throws SQLException {
      return this.blobLength;
   }

   public InputStream getBinaryStream() throws SQLException {
      try {
         if (this.blobData != null) {
            ByteArrayInputStream var1 = new ByteArrayInputStream(this.blobData);
            LimitInputStream var2 = new LimitInputStream(var1);
            var2.setLimit((int)this.blobLength);
            return var2;
         } else {
            return this.lobFile.getBinaryStream(this.blobPosition, this.blobLength);
         }
      } catch (Exception var3) {
         throw LoadError.unexpectedError(var3);
      }
   }

   public byte[] getBytes(long var1, int var3) throws SQLException {
      throw this.methodNotImplemented();
   }

   public long position(byte[] var1, long var2) throws SQLException {
      throw this.methodNotImplemented();
   }

   public long position(Blob var1, long var2) throws SQLException {
      throw this.methodNotImplemented();
   }

   public int setBytes(long var1, byte[] var3) throws SQLException {
      throw this.methodNotImplemented();
   }

   public int setBytes(long var1, byte[] var3, int var4, int var5) throws SQLException {
      throw this.methodNotImplemented();
   }

   public OutputStream setBinaryStream(long var1) throws SQLException {
      throw this.methodNotImplemented();
   }

   public void truncate(long var1) throws SQLException {
      throw this.methodNotImplemented();
   }

   public InputStream getBinaryStream(long var1, long var3) throws SQLException {
      throw this.methodNotImplemented();
   }

   public void free() throws SQLException {
      throw this.methodNotImplemented();
   }

   private SQLException methodNotImplemented() {
      return LoadError.unexpectedError(new Exception("Method not implemented"));
   }
}
