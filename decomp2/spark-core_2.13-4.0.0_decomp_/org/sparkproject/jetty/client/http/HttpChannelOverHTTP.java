package org.sparkproject.jetty.client.http;

import java.util.concurrent.atomic.LongAdder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.client.HttpChannel;
import org.sparkproject.jetty.client.HttpExchange;
import org.sparkproject.jetty.client.api.Connection;
import org.sparkproject.jetty.client.api.Response;
import org.sparkproject.jetty.client.api.Result;
import org.sparkproject.jetty.http.HttpFields;
import org.sparkproject.jetty.http.HttpHeader;
import org.sparkproject.jetty.http.HttpHeaderValue;
import org.sparkproject.jetty.http.HttpVersion;
import org.sparkproject.jetty.http.MetaData;

public class HttpChannelOverHTTP extends HttpChannel {
   private static final Logger LOG = LoggerFactory.getLogger(HttpChannelOverHTTP.class);
   private final HttpConnectionOverHTTP connection;
   private final HttpSenderOverHTTP sender;
   private final HttpReceiverOverHTTP receiver;
   private final LongAdder outMessages = new LongAdder();

   public HttpChannelOverHTTP(HttpConnectionOverHTTP connection) {
      super(connection.getHttpDestination());
      this.connection = connection;
      this.sender = this.newHttpSender();
      this.receiver = this.newHttpReceiver();
   }

   protected HttpSenderOverHTTP newHttpSender() {
      return new HttpSenderOverHTTP(this);
   }

   protected HttpReceiverOverHTTP newHttpReceiver() {
      return new HttpReceiverOverHTTP(this);
   }

   protected Connection getConnection() {
      return this.connection;
   }

   protected HttpSenderOverHTTP getHttpSender() {
      return this.sender;
   }

   protected HttpReceiverOverHTTP getHttpReceiver() {
      return this.receiver;
   }

   public HttpConnectionOverHTTP getHttpConnection() {
      return this.connection;
   }

   public void send(HttpExchange exchange) {
      this.outMessages.increment();
      this.sender.send(exchange);
   }

   public void release() {
      this.connection.release();
   }

   public void receive() {
      this.receiver.receive();
   }

   public void exchangeTerminated(HttpExchange exchange, Result result) {
      super.exchangeTerminated(exchange, result);
      String method = exchange.getRequest().getMethod();
      Response response = result.getResponse();
      int status = response.getStatus();
      HttpFields responseHeaders = response.getHeaders();
      boolean isTunnel = this.isTunnel(method, status);
      String closeReason = null;
      if (result.isFailed()) {
         closeReason = "failure";
      } else if (this.receiver.isShutdown()) {
         closeReason = "server close";
      } else if (this.sender.isShutdown() && status != 101) {
         closeReason = "client close";
      }

      if (closeReason == null) {
         if (response.getVersion().compareTo(HttpVersion.HTTP_1_1) < 0) {
            boolean keepAlive = responseHeaders.contains(HttpHeader.CONNECTION, HttpHeaderValue.KEEP_ALIVE.asString());
            if (!keepAlive && !isTunnel) {
               closeReason = "http/1.0";
            }
         } else if (responseHeaders.contains(HttpHeader.CONNECTION, HttpHeaderValue.CLOSE.asString()) && !isTunnel) {
            closeReason = "http/1.1";
         }
      }

      if (closeReason != null) {
         if (LOG.isDebugEnabled()) {
            LOG.debug("Closing, reason: {} - {}", closeReason, this.connection);
         }

         if (result.isFailed()) {
            this.connection.close(result.getFailure());
         } else {
            this.connection.close();
         }
      } else if (status != 101 && !isTunnel) {
         this.release();
      } else {
         this.connection.remove();
      }

   }

   protected long getMessagesIn() {
      return this.receiver.getMessagesIn();
   }

   protected long getMessagesOut() {
      return this.outMessages.longValue();
   }

   boolean isTunnel(String method, int status) {
      return MetaData.isTunnel(method, status);
   }

   public String toString() {
      return String.format("%s[send=%s,recv=%s]", super.toString(), this.sender, this.receiver);
   }
}
