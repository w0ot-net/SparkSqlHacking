package org.apache.orc.impl;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.EnumSet;
import java.util.IdentityHashMap;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.ReadOption;
import org.apache.hadoop.io.ByteBufferPool;

class ZeroCopyShims {
   public static HadoopShims.ZeroCopyReaderShim getZeroCopyReader(FSDataInputStream in, HadoopShims.ByteBufferPoolShim pool) throws IOException {
      return new ZeroCopyAdapter(in, pool);
   }

   private static final class ByteBufferPoolAdapter implements ByteBufferPool {
      private HadoopShims.ByteBufferPoolShim pool;

      ByteBufferPoolAdapter(HadoopShims.ByteBufferPoolShim pool) {
         this.pool = pool;
      }

      public ByteBuffer getBuffer(boolean direct, int length) {
         return this.pool.getBuffer(direct, length);
      }

      public void putBuffer(ByteBuffer buffer) {
         this.pool.putBuffer(buffer);
      }
   }

   private static final class ZeroCopyAdapter implements HadoopShims.ZeroCopyReaderShim {
      private final FSDataInputStream in;
      private final ByteBufferPoolAdapter pool;
      private static final EnumSet CHECK_SUM = EnumSet.noneOf(ReadOption.class);
      private static final EnumSet NO_CHECK_SUM;
      private final IdentityHashMap readBuffers = new IdentityHashMap(0);

      ZeroCopyAdapter(FSDataInputStream in, HadoopShims.ByteBufferPoolShim poolshim) {
         this.in = in;
         if (poolshim != null) {
            this.pool = new ByteBufferPoolAdapter(poolshim);
         } else {
            this.pool = null;
         }

      }

      public ByteBuffer readBuffer(int maxLength, boolean verifyChecksums) throws IOException {
         EnumSet<ReadOption> options = NO_CHECK_SUM;
         if (verifyChecksums) {
            options = CHECK_SUM;
         }

         ByteBuffer bb = this.in.read(this.pool, maxLength, options);
         this.readBuffers.put(bb, (Object)null);
         return bb;
      }

      /** @deprecated */
      @Deprecated
      public void releaseBuffer(ByteBuffer buffer) {
         this.in.releaseBuffer(buffer);
      }

      public void releaseAllBuffers() {
         this.readBuffers.forEach((k, v) -> this.in.releaseBuffer(k));
         this.readBuffers.clear();
      }

      public void close() throws IOException {
         this.releaseAllBuffers();
         this.in.close();
      }

      static {
         NO_CHECK_SUM = EnumSet.of(ReadOption.SKIP_CHECKSUMS);
      }
   }
}
