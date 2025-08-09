package org.apache.derby.iapi.types;

import java.io.CharArrayReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Serializable;
import java.io.Writer;
import java.sql.Clob;
import java.sql.SQLException;

public class HarmonySerialClob implements Clob, Serializable, Cloneable {
   private static final long serialVersionUID = -1662519690087375313L;
   private char[] buf;
   private Clob clob;
   private long len;
   private long origLen;

   public HarmonySerialClob(String var1) {
      this(var1.toCharArray());
   }

   public HarmonySerialClob(char[] var1) {
      this.buf = new char[var1.length];
      this.origLen = (long)var1.length;
      this.len = this.origLen;
      System.arraycopy(var1, 0, this.buf, 0, (int)this.len);
   }

   public HarmonySerialClob(Clob var1) throws SQLException {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         Reader var2;
         if ((var2 = var1.getCharacterStream()) == null && var1.getAsciiStream() == null) {
            throw new IllegalArgumentException();
         } else {
            this.clob = var1;
            this.origLen = var1.length();
            this.len = this.origLen;
            this.buf = new char[(int)this.len];

            try {
               var2.read(this.buf);
            } catch (IOException var5) {
               SQLException var4 = new SQLException("SerialClob: " + var5.getMessage());
               var4.initCause(var5);
               throw var4;
            }
         }
      }
   }

   public long length() throws SQLException {
      this.checkValidation();
      return this.len;
   }

   public InputStream getAsciiStream() throws SQLException {
      this.checkValidation();
      if (this.clob == null) {
         throw new IllegalStateException();
      } else {
         return this.clob.getAsciiStream();
      }
   }

   public Reader getCharacterStream() throws SQLException {
      this.checkValidation();
      return new CharArrayReader(this.buf);
   }

   public String getSubString(long var1, int var3) throws SQLException {
      this.checkValidation();
      if (var3 < 0) {
         throw HarmonySerialBlob.makeSQLException("XJ071.S", new Object[]{var3});
      } else if (var1 >= 1L && var1 <= this.len && var1 + (long)var3 <= this.len + 1L) {
         try {
            return new String(this.buf, (int)(var1 - 1L), var3);
         } catch (StringIndexOutOfBoundsException var5) {
            throw new SQLException();
         }
      } else {
         throw HarmonySerialBlob.makeSQLException("XJ070.S", new Object[]{var1});
      }
   }

   public long position(Clob var1, long var2) throws SQLException {
      this.checkValidation();
      String var4 = var1.getSubString(1L, (int)var1.length());
      return this.position(var4, var2);
   }

   public long position(String var1, long var2) throws SQLException, SQLException {
      this.checkValidation();
      if (var2 >= 1L && this.len - (var2 - 1L) >= (long)var1.length()) {
         char[] var4 = var1.toCharArray();

         for(int var5 = (int)var2 - 1; (long)var5 < this.len; ++var5) {
            if (this.match(this.buf, var5, var4)) {
               return (long)(var5 + 1);
            }
         }

         return -1L;
      } else {
         return -1L;
      }
   }

   private boolean match(char[] var1, int var2, char[] var3) {
      int var4 = 0;

      while(var4 < var3.length) {
         if (var1[var2++] != var3[var4++]) {
            return false;
         }
      }

      return true;
   }

   public OutputStream setAsciiStream(long var1) throws SQLException {
      this.checkValidation();
      if (this.clob == null) {
         throw new IllegalStateException();
      } else {
         OutputStream var3 = this.clob.setAsciiStream(var1);
         if (var3 == null) {
            throw new IllegalStateException();
         } else {
            return var3;
         }
      }
   }

   public Writer setCharacterStream(long var1) throws SQLException {
      this.checkValidation();
      if (this.clob == null) {
         throw new IllegalStateException();
      } else {
         Writer var3 = this.clob.setCharacterStream(var1);
         if (var3 == null) {
            throw new IllegalStateException();
         } else {
            return var3;
         }
      }
   }

   public int setString(long var1, String var3) throws SQLException {
      this.checkValidation();
      return this.setString(var1, var3, 0, var3.length());
   }

   public int setString(long var1, String var3, int var4, int var5) throws SQLException {
      this.checkValidation();
      if (var1 < 1L) {
         throw HarmonySerialBlob.makeSQLException("XJ070.S", new Object[]{var1});
      } else if (var5 < 0) {
         throw HarmonySerialBlob.makeSQLException("XJ071.S", (Object[])null);
      } else if (var1 > this.len - (long)var5 + 1L) {
         throw HarmonySerialBlob.makeSQLException("XJ076.S", (Object[])null);
      } else if (var4 >= 0 && var4 <= var3.length() - var5) {
         if ((long)var5 > this.len + (long)var4) {
            throw HarmonySerialBlob.makeSQLException("XJ078.S", (Object[])null);
         } else {
            var3.getChars(var4, var4 + var5, this.buf, (int)var1 - 1);
            return var5;
         }
      } else {
         throw HarmonySerialBlob.makeSQLException("XJ078.S", (Object[])null);
      }
   }

   public void truncate(long var1) throws SQLException {
      this.checkValidation();
      if (var1 < 0L) {
         throw HarmonySerialBlob.makeSQLException("XJ071.S", new Object[]{var1});
      } else if (var1 > this.len) {
         throw HarmonySerialBlob.makeSQLException("XJ079.S", new Object[]{var1});
      } else {
         char[] var3 = new char[(int)var1];
         System.arraycopy(this.buf, 0, var3, 0, (int)var1);
         this.buf = var3;
         this.len = var1;
      }
   }

   public void free() throws SQLException {
      if (this.len != -1L) {
         this.len = -1L;
         this.clob = null;
         this.buf = null;
      }

   }

   public Reader getCharacterStream(long var1, long var3) throws SQLException {
      this.checkValidation();
      return new CharArrayReader(this.buf, (int)var1, (int)var3);
   }

   private void checkValidation() throws SQLException {
      if (this.len == -1L) {
         throw HarmonySerialBlob.makeSQLException("XJ215.S", (Object[])null);
      }
   }
}
