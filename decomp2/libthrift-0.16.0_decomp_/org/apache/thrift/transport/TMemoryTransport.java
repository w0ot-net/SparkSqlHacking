package org.apache.thrift.transport;

import java.nio.ByteBuffer;
import org.apache.thrift.TByteArrayOutputStream;
import org.apache.thrift.TConfiguration;

public class TMemoryTransport extends TEndpointTransport {
   private final ByteBuffer inputBuffer;
   private final TByteArrayOutputStream outputBuffer;

   public TMemoryTransport(byte[] input) throws TTransportException {
      super(new TConfiguration());
      this.inputBuffer = ByteBuffer.wrap(input);
      this.outputBuffer = new TByteArrayOutputStream(1024);
      this.updateKnownMessageSize((long)input.length);
   }

   public TMemoryTransport(TConfiguration config, byte[] input) throws TTransportException {
      super(config);
      this.inputBuffer = ByteBuffer.wrap(input);
      this.outputBuffer = new TByteArrayOutputStream(1024);
      this.updateKnownMessageSize((long)input.length);
   }

   public boolean isOpen() {
      return true;
   }

   public void open() {
   }

   public void close() {
   }

   public int read(byte[] buf, int off, int len) throws TTransportException {
      this.checkReadBytesAvailable((long)len);
      int remaining = this.inputBuffer.remaining();
      if (remaining < len) {
         throw new TTransportException(4, "There's only " + remaining + "bytes, but it asks for " + len);
      } else {
         this.inputBuffer.get(buf, off, len);
         return len;
      }
   }

   public void write(byte[] buf, int off, int len) throws TTransportException {
      this.outputBuffer.write(buf, off, len);
   }

   public TByteArrayOutputStream getOutput() {
      return this.outputBuffer;
   }
}
