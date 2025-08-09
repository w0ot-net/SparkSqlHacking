package org.apache.avro.ipc;

import java.io.EOFException;
import java.io.IOException;
import java.net.SocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import javax.security.sasl.SaslClient;
import javax.security.sasl.SaslException;
import javax.security.sasl.SaslServer;
import org.apache.avro.Protocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SaslSocketTransceiver extends Transceiver {
   private static final Logger LOG = LoggerFactory.getLogger(SaslSocketTransceiver.class);
   private static final ByteBuffer EMPTY = ByteBuffer.allocate(0);
   private SaslParticipant sasl;
   private SocketChannel channel;
   private boolean dataIsWrapped;
   private boolean saslResponsePiggybacked;
   private Protocol remote;
   private ByteBuffer readHeader;
   private ByteBuffer writeHeader;
   private ByteBuffer zeroHeader;

   public SaslSocketTransceiver(SocketAddress address) throws IOException {
      this((SocketAddress)address, (SaslClient)(new AnonymousClient()));
   }

   public SaslSocketTransceiver(SocketAddress address, SaslClient saslClient) throws IOException {
      this.readHeader = ByteBuffer.allocate(4);
      this.writeHeader = ByteBuffer.allocate(4);
      this.zeroHeader = ByteBuffer.allocate(4).putInt(0);
      this.sasl = new SaslParticipant(saslClient);
      this.channel = SocketChannel.open(address);
      this.channel.socket().setTcpNoDelay(true);
      LOG.debug("open to {}", this.getRemoteName());
      this.open(true);
   }

   public SaslSocketTransceiver(SocketChannel channel, SaslServer saslServer) throws IOException {
      this.readHeader = ByteBuffer.allocate(4);
      this.writeHeader = ByteBuffer.allocate(4);
      this.zeroHeader = ByteBuffer.allocate(4).putInt(0);
      this.sasl = new SaslParticipant(saslServer);
      this.channel = channel;
      LOG.debug("open from {}", this.getRemoteName());
      this.open(false);
   }

   public boolean isConnected() {
      return this.remote != null;
   }

   public void setRemote(Protocol remote) {
      this.remote = remote;
   }

   public Protocol getRemote() {
      return this.remote;
   }

   public String getRemoteName() {
      return this.channel.socket().getRemoteSocketAddress().toString();
   }

   public synchronized List transceive(List request) throws IOException {
      if (this.saslResponsePiggybacked) {
         this.saslResponsePiggybacked = false;
         Status status = this.readStatus();
         ByteBuffer frame = this.readFrame();
         switch (status.ordinal()) {
            case 2:
               String var10002 = this.toString(frame);
               throw new SaslException("Fail: " + var10002);
            case 3:
               break;
            default:
               throw new IOException("Unexpected SASL status: " + String.valueOf(status));
         }
      }

      return super.transceive(request);
   }

   private void open(boolean isClient) throws IOException {
      LOG.debug("beginning SASL negotiation");
      if (isClient) {
         ByteBuffer response = EMPTY;
         if (this.sasl.client.hasInitialResponse()) {
            response = ByteBuffer.wrap(this.sasl.evaluate(response.array()));
         }

         this.write(SaslSocketTransceiver.Status.START, this.sasl.getMechanismName(), response);
         if (this.sasl.isComplete()) {
            this.saslResponsePiggybacked = true;
         }
      }

      while(!this.sasl.isComplete()) {
         Status status = this.readStatus();
         ByteBuffer frame = this.readFrame();
         switch (status.ordinal()) {
            case 0:
               String mechanism = this.toString(frame);
               frame = this.readFrame();
               if (!mechanism.equalsIgnoreCase(this.sasl.getMechanismName())) {
                  this.write(SaslSocketTransceiver.Status.FAIL, "Wrong mechanism: " + mechanism);
                  throw new SaslException("Wrong mechanism: " + mechanism);
               }
            case 1:
               byte[] response;
               try {
                  response = this.sasl.evaluate(frame.array());
                  status = this.sasl.isComplete() ? SaslSocketTransceiver.Status.COMPLETE : SaslSocketTransceiver.Status.CONTINUE;
               } catch (SaslException e) {
                  response = e.toString().getBytes(StandardCharsets.UTF_8);
                  status = SaslSocketTransceiver.Status.FAIL;
               }

               this.write(status, response != null ? ByteBuffer.wrap(response) : EMPTY);
               break;
            case 2:
               String var10002 = this.toString(frame);
               throw new SaslException("Fail: " + var10002);
            case 3:
               this.sasl.evaluate(frame.array());
               if (!this.sasl.isComplete()) {
                  throw new SaslException("Expected completion!");
               }
               break;
            default:
               throw new IOException("Unexpected SASL status: " + String.valueOf(status));
         }
      }

      LOG.debug("SASL opened");
      String qop = (String)this.sasl.getNegotiatedProperty("javax.security.sasl.qop");
      LOG.debug("QOP = {}", qop);
      this.dataIsWrapped = qop != null && !qop.equalsIgnoreCase("auth");
   }

   private String toString(ByteBuffer buffer) {
      return new String(buffer.array(), StandardCharsets.UTF_8);
   }

   public synchronized List readBuffers() throws IOException {
      List<ByteBuffer> buffers = new ArrayList();

      while(true) {
         ByteBuffer buffer = this.readFrameAndUnwrap();
         if (((Buffer)buffer).remaining() == 0) {
            return buffers;
         }

         buffers.add(buffer);
      }
   }

   private Status readStatus() throws IOException {
      ByteBuffer buffer = ByteBuffer.allocate(1);
      this.read(buffer);
      int status = buffer.get();
      if (status > SaslSocketTransceiver.Status.values().length) {
         throw new IOException("Unexpected SASL status byte: " + status);
      } else {
         return SaslSocketTransceiver.Status.values()[status];
      }
   }

   private ByteBuffer readFrameAndUnwrap() throws IOException {
      ByteBuffer frame = this.readFrame();
      if (!this.dataIsWrapped) {
         return frame;
      } else {
         ByteBuffer unwrapped = ByteBuffer.wrap(this.sasl.unwrap(frame.array()));
         LOG.debug("unwrapped data of length: {}", unwrapped.remaining());
         return unwrapped;
      }
   }

   private ByteBuffer readFrame() throws IOException {
      this.read(this.readHeader);
      ByteBuffer buffer = ByteBuffer.allocate(this.readHeader.getInt());
      LOG.debug("about to read: {} bytes", buffer.capacity());
      this.read(buffer);
      return buffer;
   }

   private void read(ByteBuffer buffer) throws IOException {
      ((Buffer)buffer).clear();

      while(buffer.hasRemaining()) {
         if (this.channel.read(buffer) == -1) {
            throw new EOFException();
         }
      }

      ((Buffer)buffer).flip();
   }

   public synchronized void writeBuffers(List buffers) throws IOException {
      if (buffers != null) {
         List<ByteBuffer> writes = new ArrayList(buffers.size() * 2 + 1);
         int currentLength = 0;
         ByteBuffer currentHeader = this.writeHeader;

         for(ByteBuffer buffer : buffers) {
            if (buffer.remaining() != 0) {
               if (this.dataIsWrapped) {
                  LOG.debug("wrapping data of length: {}", buffer.remaining());
                  buffer = ByteBuffer.wrap(this.sasl.wrap(buffer.array(), buffer.position(), buffer.remaining()));
               }

               int length = buffer.remaining();
               if (!this.dataIsWrapped && currentLength + length <= 8192) {
                  if (currentLength == 0) {
                     writes.add(currentHeader);
                  }

                  currentLength += length;
                  ((Buffer)currentHeader).clear();
                  currentHeader.putInt(currentLength);
                  LOG.debug("adding {} to write, total now {}", length, currentLength);
               } else {
                  currentLength = length;
                  currentHeader = ByteBuffer.allocate(4).putInt(length);
                  writes.add(currentHeader);
                  LOG.debug("planning write of {}", length);
               }

               ((Buffer)currentHeader).flip();
               writes.add(buffer);
            }
         }

         this.zeroHeader.flip();
         writes.add(this.zeroHeader);
         this.writeFully((ByteBuffer[])writes.toArray(new ByteBuffer[0]));
      }
   }

   private void write(Status status, String prefix, ByteBuffer response) throws IOException {
      LOG.debug("write status: {} {}", status, prefix);
      this.write(status, prefix);
      this.write(response);
   }

   private void write(Status status, String response) throws IOException {
      this.write(status, ByteBuffer.wrap(response.getBytes(StandardCharsets.UTF_8)));
   }

   private void write(Status status, ByteBuffer response) throws IOException {
      LOG.debug("write status: {}", status);
      ByteBuffer statusBuffer = ByteBuffer.allocate(1);
      ((Buffer)statusBuffer).clear();
      statusBuffer.put((byte)status.ordinal()).flip();
      this.writeFully(statusBuffer);
      this.write(response);
   }

   private void write(ByteBuffer response) throws IOException {
      LOG.debug("writing: {}", response.remaining());
      this.writeHeader.clear();
      this.writeHeader.putInt(response.remaining()).flip();
      this.writeFully(this.writeHeader, response);
   }

   private void writeFully(ByteBuffer... buffers) throws IOException {
      int length = buffers.length;
      int start = 0;

      while(true) {
         this.channel.write(buffers, start, length - start);

         while(buffers[start].remaining() == 0) {
            ++start;
            if (start == length) {
               return;
            }
         }
      }
   }

   public void close() throws IOException {
      if (this.channel.isOpen()) {
         LOG.info("closing to " + this.getRemoteName());
         this.channel.close();
      }

      this.sasl.dispose();
   }

   private static enum Status {
      START,
      CONTINUE,
      FAIL,
      COMPLETE;

      // $FF: synthetic method
      private static Status[] $values() {
         return new Status[]{START, CONTINUE, FAIL, COMPLETE};
      }
   }

   private static class SaslParticipant {
      public SaslServer server;
      public SaslClient client;

      public SaslParticipant(SaslServer server) {
         this.server = server;
      }

      public SaslParticipant(SaslClient client) {
         this.client = client;
      }

      public String getMechanismName() {
         return this.client != null ? this.client.getMechanismName() : this.server.getMechanismName();
      }

      public boolean isComplete() {
         return this.client != null ? this.client.isComplete() : this.server.isComplete();
      }

      public void dispose() throws SaslException {
         if (this.client != null) {
            this.client.dispose();
         } else {
            this.server.dispose();
         }

      }

      public byte[] unwrap(byte[] buf) throws SaslException {
         return this.client != null ? this.client.unwrap(buf, 0, buf.length) : this.server.unwrap(buf, 0, buf.length);
      }

      public byte[] wrap(byte[] buf, int start, int len) throws SaslException {
         return this.client != null ? this.client.wrap(buf, start, len) : this.server.wrap(buf, start, len);
      }

      public Object getNegotiatedProperty(String propName) {
         return this.client != null ? this.client.getNegotiatedProperty(propName) : this.server.getNegotiatedProperty(propName);
      }

      public byte[] evaluate(byte[] buf) throws SaslException {
         return this.client != null ? this.client.evaluateChallenge(buf) : this.server.evaluateResponse(buf);
      }
   }

   private static class AnonymousClient implements SaslClient {
      public String getMechanismName() {
         return "ANONYMOUS";
      }

      public boolean hasInitialResponse() {
         return true;
      }

      public byte[] evaluateChallenge(byte[] challenge) throws SaslException {
         return System.getProperty("user.name").getBytes(StandardCharsets.UTF_8);
      }

      public boolean isComplete() {
         return true;
      }

      public byte[] unwrap(byte[] incoming, int offset, int len) {
         throw new UnsupportedOperationException();
      }

      public byte[] wrap(byte[] outgoing, int offset, int len) {
         throw new UnsupportedOperationException();
      }

      public Object getNegotiatedProperty(String propName) {
         return null;
      }

      public void dispose() {
      }
   }
}
