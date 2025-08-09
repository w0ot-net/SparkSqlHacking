package org.apache.derby.iapi.types;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.sql.Blob;
import java.sql.SQLException;
import org.apache.derby.shared.common.error.StandardException;

public class HarmonySerialBlob implements Blob, Serializable, Cloneable {
   private static final long serialVersionUID = -8144641928112860441L;
   private byte[] buf;
   private Blob blob;
   private long len;
   private long origLen;

   public HarmonySerialBlob(Blob var1) throws SQLException {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.blob = var1;
         this.buf = var1.getBytes(1L, (int)var1.length());
         this.len = (long)this.buf.length;
         this.origLen = this.len;
      }
   }

   public HarmonySerialBlob(byte[] var1) {
      this.buf = new byte[var1.length];
      this.len = (long)var1.length;
      this.origLen = this.len;
      System.arraycopy(var1, 0, this.buf, 0, (int)this.len);
   }

   public InputStream getBinaryStream() throws SQLException {
      return new ByteArrayInputStream(this.buf);
   }

   public byte[] getBytes(long var1, int var3) throws SQLException {
      if (var1 >= 1L && var1 <= this.len) {
         if (var3 < 0) {
            throw makeSQLException("XJ071.S", new Object[]{var3});
         } else {
            if ((long)var3 > this.len - var1 + 1L) {
               var3 = (int)(this.len - var1 + 1L);
            }

            byte[] var4 = new byte[var3];
            System.arraycopy(this.buf, (int)var1 - 1, var4, 0, var3);
            return var4;
         }
      } else {
         throw makeSQLException("XJ070.S", new Object[]{var1});
      }
   }

   public long length() throws SQLException {
      return this.len;
   }

   public long position(Blob var1, long var2) throws SQLException {
      byte[] var4 = var1.getBytes(1L, (int)var1.length());
      return this.position(var4, var2);
   }

   public long position(byte[] var1, long var2) throws SQLException {
      if (var2 >= 1L && this.len - (var2 - 1L) >= (long)var1.length) {
         for(int var4 = (int)(var2 - 1L); (long)var4 <= this.len - (long)var1.length; ++var4) {
            if (this.match(this.buf, var4, var1)) {
               return (long)(var4 + 1);
            }
         }

         return -1L;
      } else {
         return -1L;
      }
   }

   private boolean match(byte[] var1, int var2, byte[] var3) {
      int var4 = 0;

      while(var4 < var3.length) {
         if (var1[var2++] != var3[var4++]) {
            return false;
         }
      }

      return true;
   }

   public OutputStream setBinaryStream(long var1) throws SQLException {
      if (this.blob == null) {
         throw new IllegalStateException();
      } else {
         OutputStream var3 = this.blob.setBinaryStream(var1);
         if (var3 == null) {
            throw new IllegalStateException();
         } else {
            return var3;
         }
      }
   }

   public int setBytes(long var1, byte[] var3) throws SQLException {
      return this.setBytes(var1, var3, 0, var3.length);
   }

   public int setBytes(long var1, byte[] var3, int var4, int var5) throws SQLException {
      if (var1 >= 1L && var5 >= 0 && var1 <= this.len - (long)var5 + 1L) {
         if (var4 >= 0 && var5 >= 0 && var4 <= var3.length - var5) {
            System.arraycopy(var3, var4, this.buf, (int)var1 - 1, var5);
            return var5;
         } else {
            throw makeSQLException("XJ078.S", new Object[]{var4});
         }
      } else {
         throw makeSQLException("XJ070.S", new Object[]{var1});
      }
   }

   public void truncate(long var1) throws SQLException {
      if (var1 > this.len) {
         throw makeSQLException("XJ079.S", new Object[]{this.len});
      } else {
         this.buf = this.getBytes(1L, (int)var1);
         this.len = var1;
      }
   }

   public void free() throws SQLException {
      throw new UnsupportedOperationException("Not supported");
   }

   public InputStream getBinaryStream(long var1, long var3) throws SQLException {
      if (this.len < 0L) {
         throw makeSQLException("XJ071.S", new Object[]{this.len});
      } else if (var3 < 0L) {
         throw makeSQLException("XJ071.S", new Object[]{var3});
      } else if (var1 >= 1L && var1 + var3 <= this.len) {
         return new ByteArrayInputStream(this.buf, (int)(var1 - 1L), (int)var3);
      } else {
         throw makeSQLException("XJ087.S", new Object[]{var1, var3});
      }
   }

   public static SQLException makeSQLException(String var0, Object[] var1) {
      StandardException var2 = StandardException.newException(var0, var1);
      return new SQLException(var2.getMessage(), var2.getSQLState());
   }
}
