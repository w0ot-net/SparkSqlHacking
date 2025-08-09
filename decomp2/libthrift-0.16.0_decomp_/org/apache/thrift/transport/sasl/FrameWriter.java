package org.apache.thrift.transport.sasl;

import java.nio.ByteBuffer;
import org.apache.thrift.transport.TNonblockingTransport;
import org.apache.thrift.transport.TTransportException;

public abstract class FrameWriter {
   protected ByteBuffer frameBytes;

   public void withHeaderAndPayload(byte[] header, byte[] payload) {
      if (payload == null) {
         payload = new byte[0];
      }

      if (header == null) {
         this.withOnlyPayload(payload);
      } else {
         this.withHeaderAndPayload(header, 0, header.length, payload, 0, payload.length);
      }

   }

   public void withHeaderAndPayload(byte[] header, int headerOffset, int headerLength, byte[] payload, int payloadOffset, int payloadLength) {
      if (!this.isComplete()) {
         throw new IllegalStateException("Previsous write is not yet complete, with " + this.frameBytes.remaining() + " bytes left.");
      } else {
         this.frameBytes = this.buildFrame(header, headerOffset, headerLength, payload, payloadOffset, payloadLength);
      }
   }

   public void withOnlyPayload(byte[] payload) {
      this.withOnlyPayload(payload, 0, payload.length);
   }

   public abstract void withOnlyPayload(byte[] var1, int var2, int var3);

   protected abstract ByteBuffer buildFrame(byte[] var1, int var2, int var3, byte[] var4, int var5, int var6);

   public void write(TNonblockingTransport transport) throws TTransportException {
      transport.write(this.frameBytes);
   }

   public boolean isComplete() {
      return this.frameBytes == null || !this.frameBytes.hasRemaining();
   }

   public void clear() {
      this.frameBytes = null;
   }
}
