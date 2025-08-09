package org.apache.thrift.transport.sasl;

import java.nio.ByteBuffer;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.apache.thrift.utils.StringUtils;

public abstract class FixedSizeHeaderReader implements FrameHeaderReader {
   protected final ByteBuffer byteBuffer = ByteBuffer.allocate(this.headerSize());

   public boolean isComplete() {
      return !this.byteBuffer.hasRemaining();
   }

   public void clear() {
      this.byteBuffer.clear();
   }

   public byte[] toBytes() {
      if (!this.isComplete()) {
         throw new IllegalStateException("Header is not yet complete " + StringUtils.bytesToHexString(this.byteBuffer.array(), 0, this.byteBuffer.position()));
      } else {
         return this.byteBuffer.array();
      }
   }

   public boolean read(TTransport transport) throws TTransportException {
      FrameReader.readAvailable(transport, this.byteBuffer);
      if (this.byteBuffer.hasRemaining()) {
         return false;
      } else {
         this.onComplete();
         return true;
      }
   }

   protected abstract int headerSize();

   protected abstract void onComplete() throws TTransportException;
}
