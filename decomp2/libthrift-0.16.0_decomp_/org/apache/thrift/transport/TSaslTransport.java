package org.apache.thrift.transport;

import java.nio.charset.StandardCharsets;
import java.util.Objects;
import javax.security.sasl.SaslClient;
import javax.security.sasl.SaslException;
import javax.security.sasl.SaslServer;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TByteArrayOutputStream;
import org.apache.thrift.TConfiguration;
import org.apache.thrift.transport.layered.TFramedTransport;
import org.apache.thrift.transport.sasl.NegotiationStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

abstract class TSaslTransport extends TEndpointTransport {
   private static final Logger LOGGER = LoggerFactory.getLogger(TSaslTransport.class);
   protected static final int DEFAULT_MAX_LENGTH = Integer.MAX_VALUE;
   protected static final int MECHANISM_NAME_BYTES = 1;
   protected static final int STATUS_BYTES = 1;
   protected static final int PAYLOAD_LENGTH_BYTES = 4;
   protected TTransport underlyingTransport;
   private SaslParticipant sasl;
   private boolean shouldWrap = false;
   private TMemoryInputTransport readBuffer;
   private final TByteArrayOutputStream writeBuffer = new TByteArrayOutputStream(1024);
   private final byte[] messageHeader = new byte[5];

   protected TSaslTransport(TTransport underlyingTransport) throws TTransportException {
      super(Objects.isNull(underlyingTransport.getConfiguration()) ? new TConfiguration() : underlyingTransport.getConfiguration());
      this.underlyingTransport = underlyingTransport;
      this.readBuffer = new TMemoryInputTransport(underlyingTransport.getConfiguration());
   }

   protected TSaslTransport(SaslClient saslClient, TTransport underlyingTransport) throws TTransportException {
      super(Objects.isNull(underlyingTransport.getConfiguration()) ? new TConfiguration() : underlyingTransport.getConfiguration());
      this.sasl = new SaslParticipant(saslClient);
      this.underlyingTransport = underlyingTransport;
      this.readBuffer = new TMemoryInputTransport(underlyingTransport.getConfiguration());
   }

   protected void setSaslServer(SaslServer saslServer) {
      this.sasl = new SaslParticipant(saslServer);
   }

   protected void sendSaslMessage(NegotiationStatus status, byte[] payload) throws TTransportException {
      if (payload == null) {
         payload = new byte[0];
      }

      this.messageHeader[0] = status.getValue();
      EncodingUtils.encodeBigEndian(payload.length, this.messageHeader, 1);
      LOGGER.debug("{}: Writing message with status {} and payload length {}", new Object[]{this.getRole(), status, payload.length});
      this.underlyingTransport.write(this.messageHeader);
      this.underlyingTransport.write(payload);
      this.underlyingTransport.flush();
   }

   protected SaslResponse receiveSaslMessage() throws TTransportException {
      this.underlyingTransport.readAll(this.messageHeader, 0, this.messageHeader.length);
      byte statusByte = this.messageHeader[0];
      NegotiationStatus status = NegotiationStatus.byValue(statusByte);
      if (status == null) {
         throw this.sendAndThrowMessage(NegotiationStatus.ERROR, "Invalid status " + statusByte);
      } else {
         int payloadBytes = EncodingUtils.decodeBigEndian(this.messageHeader, 1);
         if (payloadBytes >= 0 && payloadBytes <= this.getConfiguration().getMaxMessageSize()) {
            byte[] payload = new byte[payloadBytes];
            this.underlyingTransport.readAll(payload, 0, payload.length);
            if (status != NegotiationStatus.BAD && status != NegotiationStatus.ERROR) {
               LOGGER.debug("{}: Received message with status {} and payload length {}", new Object[]{this.getRole(), status, payload.length});
               return new SaslResponse(status, payload);
            } else {
               String remoteMessage = new String(payload, StandardCharsets.UTF_8);
               throw new TTransportException("Peer indicated failure: " + remoteMessage);
            }
         } else {
            throw this.sendAndThrowMessage(NegotiationStatus.ERROR, "Invalid payload header length: " + payloadBytes);
         }
      }
   }

   protected TTransportException sendAndThrowMessage(NegotiationStatus status, String message) throws TTransportException {
      try {
         this.sendSaslMessage(status, message.getBytes(StandardCharsets.UTF_8));
      } catch (Exception e) {
         LOGGER.warn("Could not send failure response", e);
         message = message + "\nAlso, could not send response: " + e.toString();
      }

      throw new TTransportException(message);
   }

   protected abstract void handleSaslStartMessage() throws TTransportException, SaslException;

   protected abstract SaslRole getRole();

   public void open() throws TTransportException {
      boolean readSaslHeader = false;
      LOGGER.debug("opening transport {}", this);
      if (this.sasl != null && this.sasl.isComplete()) {
         throw new TTransportException("SASL transport already open");
      } else {
         if (!this.underlyingTransport.isOpen()) {
            this.underlyingTransport.open();
         }

         try {
            this.handleSaslStartMessage();
            readSaslHeader = true;
            LOGGER.debug("{}: Start message handled", this.getRole());
            SaslResponse message = null;

            while(!this.sasl.isComplete()) {
               message = this.receiveSaslMessage();
               if (message.status != NegotiationStatus.COMPLETE && message.status != NegotiationStatus.OK) {
                  throw new TTransportException("Expected COMPLETE or OK, got " + message.status);
               }

               byte[] challenge = this.sasl.evaluateChallengeOrResponse(message.payload);
               if (message.status == NegotiationStatus.COMPLETE && this.getRole() == TSaslTransport.SaslRole.CLIENT) {
                  LOGGER.debug("{}: All done!", this.getRole());
               } else {
                  this.sendSaslMessage(this.sasl.isComplete() ? NegotiationStatus.COMPLETE : NegotiationStatus.OK, challenge);
               }
            }

            LOGGER.debug("{}: Main negotiation loop complete", this.getRole());
            if (this.getRole() == TSaslTransport.SaslRole.CLIENT && (message == null || message.status == NegotiationStatus.OK)) {
               LOGGER.debug("{}: SASL Client receiving last message", this.getRole());
               message = this.receiveSaslMessage();
               if (message.status != NegotiationStatus.COMPLETE) {
                  throw new TTransportException("Expected SASL COMPLETE, but got " + message.status);
               }
            }
         } catch (SaslException var9) {
            SaslException e = var9;

            try {
               LOGGER.error("SASL negotiation failure", e);
               throw this.sendAndThrowMessage(NegotiationStatus.BAD, e.getMessage());
            } finally {
               this.underlyingTransport.close();
            }
         } catch (TTransportException var10) {
            if (!readSaslHeader && var10.getType() == 4) {
               this.underlyingTransport.close();
               LOGGER.debug("No data or no sasl data in the stream during negotiation");
            }

            throw var10;
         }

         String qop = (String)this.sasl.getNegotiatedProperty("javax.security.sasl.qop");
         if (qop != null && !qop.equalsIgnoreCase("auth")) {
            this.shouldWrap = true;
         }

      }
   }

   public SaslClient getSaslClient() {
      return this.sasl.saslClient;
   }

   public TTransport getUnderlyingTransport() {
      return this.underlyingTransport;
   }

   public SaslServer getSaslServer() {
      return this.sasl.saslServer;
   }

   protected int readLength() throws TTransportException {
      byte[] lenBuf = new byte[4];
      this.underlyingTransport.readAll(lenBuf, 0, lenBuf.length);
      return EncodingUtils.decodeBigEndian(lenBuf);
   }

   protected void writeLength(int length) throws TTransportException {
      byte[] lenBuf = new byte[4];
      TFramedTransport.encodeFrameSize(length, lenBuf);
      this.underlyingTransport.write(lenBuf);
   }

   public void close() {
      this.underlyingTransport.close();

      try {
         this.sasl.dispose();
      } catch (SaslException e) {
         LOGGER.warn("Failed to dispose sasl participant.", e);
      }

   }

   public boolean isOpen() {
      return this.underlyingTransport.isOpen() && this.sasl != null && this.sasl.isComplete();
   }

   public int read(byte[] buf, int off, int len) throws TTransportException {
      if (!this.isOpen()) {
         throw new TTransportException("SASL authentication not complete");
      } else {
         int got = this.readBuffer.read(buf, off, len);
         if (got > 0) {
            return got;
         } else {
            try {
               this.readFrame();
            } catch (SaslException e) {
               throw new TTransportException(e);
            } catch (TTransportException transportException) {
               if (transportException.getType() == 4) {
                  LOGGER.debug("No data or no sasl data in the stream during negotiation");
               }

               throw transportException;
            }

            return this.readBuffer.read(buf, off, len);
         }
      }
   }

   private void readFrame() throws TTransportException, SaslException {
      int dataLength = this.readLength();
      if (dataLength < 0) {
         throw new TTransportException("Read a negative frame size (" + dataLength + ")!");
      } else {
         byte[] buff = new byte[dataLength];
         LOGGER.debug("{}: reading data length: {}", this.getRole(), dataLength);
         this.underlyingTransport.readAll(buff, 0, dataLength);
         if (this.shouldWrap) {
            buff = this.sasl.unwrap(buff, 0, buff.length);
            LOGGER.debug("data length after unwrap: {}", buff.length);
         }

         this.readBuffer.reset(buff);
      }
   }

   public void write(byte[] buf, int off, int len) throws TTransportException {
      if (!this.isOpen()) {
         throw new TTransportException("SASL authentication not complete");
      } else {
         this.writeBuffer.write(buf, off, len);
      }
   }

   public void flush() throws TTransportException {
      byte[] buf = this.writeBuffer.get();
      int dataLength = this.writeBuffer.len();
      this.writeBuffer.reset();
      if (this.shouldWrap) {
         LOGGER.debug("data length before wrap: {}", dataLength);

         try {
            buf = this.sasl.wrap(buf, 0, dataLength);
         } catch (SaslException e) {
            throw new TTransportException(e);
         }

         dataLength = buf.length;
      }

      LOGGER.debug("writing data length: {}", dataLength);
      this.writeLength(dataLength);
      this.underlyingTransport.write(buf, 0, dataLength);
      this.underlyingTransport.flush();
   }

   protected static enum SaslRole {
      SERVER,
      CLIENT;
   }

   protected static class SaslResponse {
      public NegotiationStatus status;
      public byte[] payload;

      public SaslResponse(NegotiationStatus status, byte[] payload) {
         this.status = status;
         this.payload = payload;
      }
   }

   private static class SaslParticipant {
      public SaslServer saslServer;
      public SaslClient saslClient;

      public SaslParticipant(SaslServer saslServer) {
         this.saslServer = saslServer;
      }

      public SaslParticipant(SaslClient saslClient) {
         this.saslClient = saslClient;
      }

      public byte[] evaluateChallengeOrResponse(byte[] challengeOrResponse) throws SaslException {
         return this.saslClient != null ? this.saslClient.evaluateChallenge(challengeOrResponse) : this.saslServer.evaluateResponse(challengeOrResponse);
      }

      public boolean isComplete() {
         return this.saslClient != null ? this.saslClient.isComplete() : this.saslServer.isComplete();
      }

      public void dispose() throws SaslException {
         if (this.saslClient != null) {
            this.saslClient.dispose();
         } else {
            this.saslServer.dispose();
         }

      }

      public byte[] unwrap(byte[] buf, int off, int len) throws SaslException {
         return this.saslClient != null ? this.saslClient.unwrap(buf, off, len) : this.saslServer.unwrap(buf, off, len);
      }

      public byte[] wrap(byte[] buf, int off, int len) throws SaslException {
         return this.saslClient != null ? this.saslClient.wrap(buf, off, len) : this.saslServer.wrap(buf, off, len);
      }

      public Object getNegotiatedProperty(String propName) {
         return this.saslClient != null ? this.saslClient.getNegotiatedProperty(propName) : this.saslServer.getNegotiatedProperty(propName);
      }
   }
}
