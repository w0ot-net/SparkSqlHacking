package org.apache.derby.impl.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import org.apache.derby.iapi.services.io.InputStreamUtil;

class UpdatableBlobStream extends InputStream {
   private boolean materialized;
   private InputStream stream;
   private long pos;
   private final EmbedBlob blob;
   private final long maxPos;

   UpdatableBlobStream(EmbedBlob var1, InputStream var2) throws IOException {
      this(var1, var2, 0L, Long.MAX_VALUE);
   }

   UpdatableBlobStream(EmbedBlob var1, InputStream var2, long var3, long var5) throws IOException {
      this.blob = var1;
      this.stream = var2;
      this.maxPos = var3 + var5;
      if (var3 > 0L) {
         this.skip(var3);
      }

   }

   private void updateIfRequired() throws IOException {
      if (!this.materialized) {
         if (this.blob.isMaterialized()) {
            this.materialized = true;

            try {
               this.stream = this.blob.getBinaryStream();
            } catch (SQLException var2) {
               throw Util.newIOException(var2);
            }

            InputStreamUtil.skipFully(this.stream, this.pos);
         }

      }
   }

   public int read() throws IOException {
      this.updateIfRequired();
      if (this.pos >= this.maxPos) {
         return -1;
      } else {
         int var1 = this.stream.read();
         if (var1 >= 0) {
            ++this.pos;
         }

         return var1;
      }
   }

   public int read(byte[] var1, int var2, int var3) throws IOException {
      this.updateIfRequired();
      long var4 = this.maxPos - this.pos;
      if (var4 == 0L && var3 > 0) {
         return -1;
      } else {
         int var6 = (int)Math.min((long)var3, var4);
         int var7 = this.stream.read(var1, var2, var6);
         if (var7 > 0) {
            this.pos += (long)var7;
         }

         return var7;
      }
   }

   public int read(byte[] var1) throws IOException {
      return this.read(var1, 0, var1.length);
   }

   public long skip(long var1) throws IOException {
      this.updateIfRequired();
      long var3 = this.stream.skip(var1);
      if (var3 > 0L) {
         this.pos += var3;
      }

      return var3;
   }
}
