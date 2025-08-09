package org.apache.thrift.transport.sasl;

import java.nio.ByteBuffer;
import org.apache.thrift.transport.TEOFException;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

public abstract class FrameReader {
   private final FrameHeaderReader header;
   private ByteBuffer payload;

   protected FrameReader(FrameHeaderReader header) {
      this.header = header;
   }

   public boolean read(TTransport transport) throws TSaslNegotiationException, TTransportException {
      if (!this.header.isComplete()) {
         if (!this.readHeader(transport)) {
            return false;
         }

         this.payload = ByteBuffer.allocate(this.header.payloadSize());
      }

      return this.header.payloadSize() == 0 ? true : this.readPayload(transport);
   }

   private boolean readHeader(TTransport transport) throws TSaslNegotiationException, TTransportException {
      return this.header.read(transport);
   }

   private boolean readPayload(TTransport transport) throws TTransportException {
      readAvailable(transport, this.payload);
      return this.payload.hasRemaining();
   }

   public FrameHeaderReader getHeader() {
      return this.header;
   }

   public int getHeaderSize() {
      return this.header.toBytes().length;
   }

   public byte[] getPayload() {
      return this.payload.array();
   }

   public int getPayloadSize() {
      return this.header.payloadSize();
   }

   public boolean isComplete() {
      return this.payload != null && !this.payload.hasRemaining();
   }

   public void clear() {
      this.header.clear();
      this.payload = null;
   }

   static int readAvailable(TTransport transport, ByteBuffer recipient) throws TTransportException {
      if (!recipient.hasRemaining()) {
         throw new IllegalStateException("Trying to fill a full recipient with " + recipient.limit() + " bytes");
      } else {
         int currentPosition = recipient.position();
         byte[] bytes = recipient.array();
         int offset = recipient.arrayOffset() + currentPosition;
         int expectedLength = recipient.remaining();
         int got = transport.read(bytes, offset, expectedLength);
         if (got < 0) {
            throw new TEOFException("Transport is closed, while trying to read " + expectedLength + " bytes");
         } else {
            recipient.position(currentPosition + got);
            return got;
         }
      }
   }
}
