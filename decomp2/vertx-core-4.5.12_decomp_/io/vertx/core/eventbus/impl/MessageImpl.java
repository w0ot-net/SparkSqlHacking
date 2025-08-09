package io.vertx.core.eventbus.impl;

import io.vertx.core.Future;
import io.vertx.core.MultiMap;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.Message;
import io.vertx.core.eventbus.MessageCodec;
import java.util.List;
import java.util.Map;

public class MessageImpl implements Message {
   protected MessageCodec messageCodec;
   protected final EventBusImpl bus;
   protected String address;
   protected String replyAddress;
   protected MultiMap headers;
   protected Object sentBody;
   protected Object receivedBody;
   protected boolean send;
   protected Object trace;

   public MessageImpl(EventBusImpl bus) {
      this.bus = bus;
   }

   public MessageImpl(String address, MultiMap headers, Object sentBody, MessageCodec messageCodec, boolean send, EventBusImpl bus) {
      this.messageCodec = messageCodec;
      this.address = address;
      this.headers = headers;
      this.sentBody = sentBody;
      this.send = send;
      this.bus = bus;
   }

   protected MessageImpl(MessageImpl other) {
      this.bus = other.bus;
      this.address = other.address;
      this.replyAddress = other.replyAddress;
      this.messageCodec = other.messageCodec;
      if (other.headers != null) {
         List<Map.Entry<String, String>> entries = other.headers.entries();
         this.headers = MultiMap.caseInsensitiveMultiMap();

         for(Map.Entry entry : entries) {
            this.headers.add((String)entry.getKey(), (String)entry.getValue());
         }
      }

      if (other.sentBody != null) {
         this.sentBody = other.sentBody;
         this.receivedBody = this.messageCodec.transform(other.sentBody);
      }

      this.send = other.send;
   }

   public MessageImpl copyBeforeReceive() {
      return new MessageImpl(this);
   }

   public String address() {
      return this.address;
   }

   public MultiMap headers() {
      if (this.headers == null) {
         this.headers = MultiMap.caseInsensitiveMultiMap();
      }

      return this.headers;
   }

   public Object body() {
      if (this.receivedBody == null && this.sentBody != null) {
         this.receivedBody = this.messageCodec.transform(this.sentBody);
      }

      return this.receivedBody;
   }

   public String replyAddress() {
      return this.replyAddress;
   }

   public void reply(Object message, DeliveryOptions options) {
      if (this.replyAddress != null) {
         MessageImpl reply = this.createReply(message, options);
         this.bus.sendReply(reply, options, (ReplyHandler)null);
      }

   }

   public Future replyAndRequest(Object message, DeliveryOptions options) {
      if (this.replyAddress != null) {
         MessageImpl reply = this.createReply(message, options);
         ReplyHandler<R> handler = this.bus.createReplyHandler(reply, false, options);
         this.bus.sendReply(reply, options, handler);
         return handler.result();
      } else {
         throw new IllegalStateException();
      }
   }

   protected MessageImpl createReply(Object message, DeliveryOptions options) {
      MessageImpl reply = this.bus.createMessage(true, this.isLocal(), this.replyAddress, options.getHeaders(), message, options.getCodecName());
      reply.trace = this.trace;
      return reply;
   }

   public boolean isSend() {
      return this.send;
   }

   public void setReplyAddress(String replyAddress) {
      this.replyAddress = replyAddress;
   }

   public MessageCodec codec() {
      return this.messageCodec;
   }

   protected boolean isLocal() {
      return true;
   }
}
