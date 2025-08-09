package io.vertx.core.eventbus.impl.clustered;

import io.netty.util.CharsetUtil;
import io.vertx.core.MultiMap;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.MessageCodec;
import io.vertx.core.eventbus.ReplyException;
import io.vertx.core.eventbus.ReplyFailure;
import io.vertx.core.eventbus.impl.CodecManager;
import io.vertx.core.eventbus.impl.EventBusImpl;
import io.vertx.core.eventbus.impl.MessageImpl;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import java.util.Map;

public class ClusteredMessage extends MessageImpl {
   private static final Logger log = LoggerFactory.getLogger(ClusteredMessage.class);
   private static final byte WIRE_PROTOCOL_VERSION = 2;
   private String sender;
   private String repliedTo;
   private Buffer wireBuffer;
   private int bodyPos;
   private int headersPos;
   private boolean fromWire;
   private boolean toWire;
   private String failure;

   public ClusteredMessage(EventBusImpl bus) {
      super(bus);
   }

   public ClusteredMessage(String sender, String address, MultiMap headers, Object sentBody, MessageCodec messageCodec, boolean send, EventBusImpl bus) {
      super(address, headers, sentBody, messageCodec, send, bus);
      this.sender = sender;
   }

   protected ClusteredMessage(ClusteredMessage other) {
      super((MessageImpl)other);
      this.sender = other.sender;
      if (other.sentBody == null) {
         this.wireBuffer = other.wireBuffer;
         this.bodyPos = other.bodyPos;
         this.headersPos = other.headersPos;
      }

      this.fromWire = other.fromWire;
   }

   protected MessageImpl createReply(Object message, DeliveryOptions options) {
      ClusteredMessage reply = (ClusteredMessage)super.createReply(message, options);
      reply.repliedTo = this.sender;
      return reply;
   }

   public ClusteredMessage copyBeforeReceive() {
      return new ClusteredMessage(this);
   }

   public MultiMap headers() {
      if (this.headers == null) {
         if (this.headersPos != 0) {
            this.decodeHeaders();
         }

         if (this.headers == null) {
            this.headers = MultiMap.caseInsensitiveMultiMap();
         }
      }

      return this.headers;
   }

   public Object body() {
      if (this.receivedBody == null && this.bodyPos != 0) {
         this.decodeBody();
      }

      return this.receivedBody;
   }

   public String replyAddress() {
      return this.replyAddress;
   }

   public Buffer encodeToWire() {
      this.toWire = true;
      int length = 1024;
      Buffer buffer = Buffer.buffer(length);
      buffer.appendInt(0);
      buffer.appendByte((byte)2);
      byte systemCodecID = this.messageCodec.systemCodecID();
      buffer.appendByte(systemCodecID);
      if (systemCodecID == -1) {
         this.writeString(buffer, this.messageCodec.name());
      }

      buffer.appendByte((byte)(this.send ? 0 : 1));
      this.writeString(buffer, this.address);
      if (this.replyAddress != null) {
         this.writeString(buffer, this.replyAddress);
      } else {
         buffer.appendInt(0);
      }

      this.writeString(buffer, this.sender);
      this.encodeHeaders(buffer);
      this.writeBody(buffer);
      buffer.setInt(0, buffer.length() - 4);
      return buffer;
   }

   public void readFromWire(Buffer buffer, CodecManager codecManager) {
      int pos = 0;
      byte protocolVersion = buffer.getByte(pos);
      if (protocolVersion > 2) {
         this.setFailure("Invalid wire protocol version " + protocolVersion + " should be <= " + 2);
      }

      ++pos;
      byte systemCodecCode = buffer.getByte(pos);
      ++pos;
      if (systemCodecCode == -1) {
         int length = buffer.getInt(pos);
         pos += 4;
         byte[] bytes = buffer.getBytes(pos, pos + length);
         String codecName = new String(bytes, CharsetUtil.UTF_8);
         this.messageCodec = codecManager.getCodec(codecName);
         if (this.messageCodec == null) {
            this.setFailure("No message codec registered with name " + codecName);
         }

         pos += length;
      } else {
         this.messageCodec = codecManager.systemCodecs()[systemCodecCode];
      }

      byte bsend = buffer.getByte(pos);
      this.send = bsend == 0;
      ++pos;
      int length = buffer.getInt(pos);
      pos += 4;
      byte[] bytes = buffer.getBytes(pos, pos + length);
      this.address = new String(bytes, CharsetUtil.UTF_8);
      pos += length;
      length = buffer.getInt(pos);
      pos += 4;
      if (length != 0) {
         bytes = buffer.getBytes(pos, pos + length);
         this.replyAddress = new String(bytes, CharsetUtil.UTF_8);
         pos += length;
      }

      length = buffer.getInt(pos);
      pos += 4;
      bytes = buffer.getBytes(pos, pos + length);
      this.sender = new String(bytes, CharsetUtil.UTF_8);
      pos += length;
      this.headersPos = pos;
      int headersLength = buffer.getInt(pos);
      pos += headersLength;
      this.bodyPos = pos;
      this.wireBuffer = buffer;
      this.fromWire = true;
   }

   private void setFailure(String s) {
      if (this.failure == null) {
         this.failure = s;
      }

   }

   private void decodeBody() {
      this.receivedBody = this.messageCodec.decodeFromWire(this.bodyPos, this.wireBuffer);
      this.bodyPos = 0;
   }

   private void encodeHeaders(Buffer buffer) {
      if (this.headers != null && !this.headers.isEmpty()) {
         int headersLengthPos = buffer.length();
         buffer.appendInt(0);
         buffer.appendInt(this.headers.entries().size());

         for(Map.Entry entry : this.headers.entries()) {
            this.writeString(buffer, (String)entry.getKey());
            this.writeString(buffer, (String)entry.getValue());
         }

         int headersEndPos = buffer.length();
         buffer.setInt(headersLengthPos, headersEndPos - headersLengthPos);
      } else {
         buffer.appendInt(4);
      }

   }

   private void decodeHeaders() {
      int length = this.wireBuffer.getInt(this.headersPos);
      if (length != 4) {
         this.headersPos += 4;
         int numHeaders = this.wireBuffer.getInt(this.headersPos);
         this.headersPos += 4;
         this.headers = MultiMap.caseInsensitiveMultiMap();

         for(int i = 0; i < numHeaders; ++i) {
            int keyLength = this.wireBuffer.getInt(this.headersPos);
            this.headersPos += 4;
            byte[] bytes = this.wireBuffer.getBytes(this.headersPos, this.headersPos + keyLength);
            String key = new String(bytes, CharsetUtil.UTF_8);
            this.headersPos += keyLength;
            int valLength = this.wireBuffer.getInt(this.headersPos);
            this.headersPos += 4;
            bytes = this.wireBuffer.getBytes(this.headersPos, this.headersPos + valLength);
            String val = new String(bytes, CharsetUtil.UTF_8);
            this.headersPos += valLength;
            this.headers.add(key, val);
         }
      }

      this.headersPos = 0;
   }

   private void writeBody(Buffer buff) {
      this.messageCodec.encodeToWire(buff, this.sentBody);
   }

   private void writeString(Buffer buff, String str) {
      byte[] strBytes = str.getBytes(CharsetUtil.UTF_8);
      buff.appendInt(strBytes.length);
      buff.appendBytes(strBytes);
   }

   String getSender() {
      return this.sender;
   }

   String getRepliedTo() {
      return this.repliedTo;
   }

   public boolean isFromWire() {
      return this.fromWire;
   }

   public boolean isToWire() {
      return this.toWire;
   }

   protected boolean isLocal() {
      return !this.isFromWire();
   }

   boolean hasFailure() {
      return this.failure != null;
   }

   void internalError() {
      if (this.replyAddress != null) {
         this.reply(new ReplyException(ReplyFailure.ERROR, this.failure));
      } else {
         log.error(this.failure);
      }

   }
}
