package org.apache.derby.impl.jdbc;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import org.apache.derby.shared.common.error.StandardException;

final class AutoPositioningStream extends BinaryToRawStream {
   private final ConnectionChild conChild;
   private long pos;
   private final PositionedStoreStream positionedStream;

   AutoPositioningStream(ConnectionChild var1, InputStream var2, Object var3) throws IOException {
      super(var2, var3);
      this.positionedStream = (PositionedStoreStream)var2;
      this.pos = this.positionedStream.getPosition();
      this.conChild = var1;
   }

   public int read() throws IOException {
      synchronized(this.conChild.getConnectionSynchronization()) {
         try {
            this.setPosition();
         } catch (EOFException var4) {
            return -1;
         }

         int var2 = this.positionedStream.read();
         if (var2 >= 0) {
            ++this.pos;
         }

         return var2;
      }
   }

   public int read(byte[] var1, int var2, int var3) throws IOException {
      synchronized(this.conChild.getConnectionSynchronization()) {
         try {
            this.setPosition();
         } catch (EOFException var7) {
            return -1;
         }

         int var5 = this.positionedStream.read(var1, var2, var3);
         if (var5 > 0) {
            this.pos += (long)var5;
         }

         return var5;
      }
   }

   public long skip(long var1) throws IOException {
      synchronized(this.conChild.getConnectionSynchronization()) {
         this.setPosition();
         long var4 = this.positionedStream.skip(var1);
         this.pos += var4;
         return var4;
      }
   }

   public int read(byte[] var1) throws IOException {
      return this.read(var1, 0, var1.length);
   }

   private void setPosition() throws IOException {
      try {
         if (this.pos != this.positionedStream.getPosition()) {
            this.positionedStream.reposition(this.pos);
         }

      } catch (StandardException var2) {
         throw Util.newIOException(var2);
      }
   }
}
