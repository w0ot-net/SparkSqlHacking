package org.apache.derby.impl.load;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;
import java.sql.Clob;
import java.sql.SQLException;
import org.apache.derby.iapi.services.io.LimitReader;

class ImportClob implements Clob {
   private ImportLobFile lobFile;
   private long position;
   private long length;
   private long clobLength;
   private String clobData = null;

   public ImportClob(ImportLobFile var1, long var2, long var4) throws IOException {
      this.lobFile = var1;
      this.position = var2;
      this.length = var4;
      this.clobLength = var1.getClobDataLength(var2, var4);
   }

   public ImportClob(String var1) {
      this.clobData = var1;
      this.clobLength = (long)var1.length();
   }

   public long length() throws SQLException {
      return this.clobLength;
   }

   public Reader getCharacterStream() throws SQLException {
      try {
         if (this.clobData != null) {
            StringReader var1 = new StringReader(this.clobData);
            LimitReader var2 = new LimitReader(var1);
            var2.setLimit((int)this.clobLength);
            return var2;
         } else {
            return this.lobFile.getCharacterStream(this.position, this.length);
         }
      } catch (Exception var3) {
         throw LoadError.unexpectedError(var3);
      }
   }

   public String getSubString(long var1, int var3) throws SQLException {
      throw this.methodNotImplemented();
   }

   public InputStream getAsciiStream() throws SQLException {
      throw this.methodNotImplemented();
   }

   public long position(String var1, long var2) throws SQLException {
      throw this.methodNotImplemented();
   }

   public long position(Clob var1, long var2) throws SQLException {
      throw this.methodNotImplemented();
   }

   public int setString(long var1, String var3) throws SQLException {
      throw this.methodNotImplemented();
   }

   public int setString(long var1, String var3, int var4, int var5) throws SQLException {
      throw this.methodNotImplemented();
   }

   public OutputStream setAsciiStream(long var1) throws SQLException {
      throw this.methodNotImplemented();
   }

   public Writer setCharacterStream(long var1) throws SQLException {
      throw this.methodNotImplemented();
   }

   public void truncate(long var1) throws SQLException {
      throw this.methodNotImplemented();
   }

   public Reader getCharacterStream(long var1, long var3) throws SQLException {
      throw this.methodNotImplemented();
   }

   public void free() throws SQLException {
      throw this.methodNotImplemented();
   }

   private SQLException methodNotImplemented() {
      return LoadError.unexpectedError(new Exception("Method not implemented"));
   }
}
