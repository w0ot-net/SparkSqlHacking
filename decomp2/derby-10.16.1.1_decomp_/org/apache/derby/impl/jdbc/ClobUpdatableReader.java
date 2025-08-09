package org.apache.derby.impl.jdbc;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;

final class ClobUpdatableReader extends Reader {
   private Reader streamReader;
   private long pos;
   private long lastUpdateCount;
   private final EmbedClob clob;
   private InternalClob iClob;
   private final long maxPos;
   private volatile boolean closed;

   public ClobUpdatableReader(EmbedClob var1) throws IOException, SQLException {
      this(var1, 1L, Long.MAX_VALUE);
   }

   public ClobUpdatableReader(EmbedClob var1, long var2, long var4) throws IOException, SQLException {
      this.lastUpdateCount = -1L;
      this.closed = false;
      this.clob = var1;
      this.iClob = var1.getInternalClob();
      this.pos = var2;
      long var6 = var2 + var4;
      if (var6 < var4 || var6 < var2) {
         var6 = Long.MAX_VALUE;
      }

      this.maxPos = var6;
   }

   public int read() throws IOException {
      if (this.closed) {
         throw new IOException("Reader closed");
      } else if (this.pos >= this.maxPos) {
         return -1;
      } else {
         this.updateReaderIfRequired();
         int var1 = this.streamReader.read();
         if (var1 > 0) {
            ++this.pos;
         }

         return var1;
      }
   }

   public int read(char[] var1, int var2, int var3) throws IOException {
      if (this.closed) {
         throw new IOException("Reader closed");
      } else if (this.pos >= this.maxPos) {
         return -1;
      } else {
         this.updateReaderIfRequired();
         int var4 = (int)Math.min((long)var3, this.maxPos - this.pos);
         int var5 = this.streamReader.read(var1, var2, var4);
         if (var5 > 0) {
            this.pos += (long)var5;
         }

         return var5;
      }
   }

   public long skip(long var1) throws IOException {
      if (this.closed) {
         throw new IOException("Reader closed");
      } else if (this.pos >= this.maxPos) {
         return 0L;
      } else {
         this.updateReaderIfRequired();
         long var3 = Math.min(var1, this.maxPos - this.pos);
         long var5 = this.streamReader.skip(var3);
         if (var5 > 0L) {
            this.pos += var5;
         }

         return var5;
      }
   }

   public void close() throws IOException {
      if (!this.closed) {
         this.closed = true;
         if (this.streamReader != null) {
            this.streamReader.close();
         }
      }

   }

   private void updateReaderIfRequired() throws IOException {
      if (this.iClob.isReleased()) {
         this.iClob = this.clob.getInternalClob();
         this.lastUpdateCount = -1L;
         if (this.iClob.isReleased()) {
            this.close();
            return;
         }
      }

      if (this.lastUpdateCount != this.iClob.getUpdateCount()) {
         this.lastUpdateCount = this.iClob.getUpdateCount();

         try {
            this.streamReader = this.iClob.getReader(this.pos);
         } catch (SQLException var2) {
            throw new IOException(var2.getMessage());
         }
      }

   }
}
