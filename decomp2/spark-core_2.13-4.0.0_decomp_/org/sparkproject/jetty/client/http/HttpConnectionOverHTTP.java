package org.sparkproject.jetty.client.http;

import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousCloseException;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.client.HttpConnection;
import org.sparkproject.jetty.client.HttpConversation;
import org.sparkproject.jetty.client.HttpDestination;
import org.sparkproject.jetty.client.HttpExchange;
import org.sparkproject.jetty.client.HttpProxy;
import org.sparkproject.jetty.client.HttpRequest;
import org.sparkproject.jetty.client.HttpUpgrader;
import org.sparkproject.jetty.client.IConnection;
import org.sparkproject.jetty.client.SendFailure;
import org.sparkproject.jetty.client.api.Connection;
import org.sparkproject.jetty.client.api.Request;
import org.sparkproject.jetty.client.api.Response;
import org.sparkproject.jetty.http.HttpHeader;
import org.sparkproject.jetty.http.HttpVersion;
import org.sparkproject.jetty.io.AbstractConnection;
import org.sparkproject.jetty.io.EndPoint;
import org.sparkproject.jetty.util.Attachable;
import org.sparkproject.jetty.util.Promise;
import org.sparkproject.jetty.util.thread.Sweeper;

public class HttpConnectionOverHTTP extends AbstractConnection implements IConnection, org.sparkproject.jetty.io.Connection.UpgradeFrom, Sweeper.Sweepable, Attachable {
   private static final Logger LOG = LoggerFactory.getLogger(HttpConnectionOverHTTP.class);
   private final AtomicBoolean closed;
   private final AtomicInteger sweeps;
   private final Promise promise;
   private final Delegate delegate;
   private final HttpChannelOverHTTP channel;
   private final LongAdder bytesIn;
   private final LongAdder bytesOut;
   private long idleTimeout;

   public HttpConnectionOverHTTP(EndPoint endPoint, Map context) {
      this(endPoint, destinationFrom(context), promiseFrom(context));
   }

   private static HttpDestination destinationFrom(Map context) {
      return (HttpDestination)context.get("org.sparkproject.jetty.client.destination");
   }

   private static Promise promiseFrom(Map context) {
      return (Promise)context.get("org.sparkproject.jetty.client.connection.promise");
   }

   public HttpConnectionOverHTTP(EndPoint endPoint, HttpDestination destination, Promise promise) {
      super(endPoint, destination.getHttpClient().getExecutor());
      this.closed = new AtomicBoolean();
      this.sweeps = new AtomicInteger();
      this.bytesIn = new LongAdder();
      this.bytesOut = new LongAdder();
      this.promise = promise;
      this.delegate = new Delegate(destination);
      this.channel = this.newHttpChannel();
   }

   protected HttpChannelOverHTTP newHttpChannel() {
      return new HttpChannelOverHTTP(this);
   }

   public HttpChannelOverHTTP getHttpChannel() {
      return this.channel;
   }

   public HttpDestination getHttpDestination() {
      return this.delegate.getHttpDestination();
   }

   public SocketAddress getLocalSocketAddress() {
      return this.delegate.getLocalSocketAddress();
   }

   public SocketAddress getRemoteSocketAddress() {
      return this.delegate.getRemoteSocketAddress();
   }

   public long getBytesIn() {
      return this.bytesIn.longValue();
   }

   protected void addBytesIn(long bytesIn) {
      this.bytesIn.add(bytesIn);
   }

   public long getBytesOut() {
      return this.bytesOut.longValue();
   }

   protected void addBytesOut(long bytesOut) {
      this.bytesOut.add(bytesOut);
   }

   public long getMessagesIn() {
      return this.getHttpChannel().getMessagesIn();
   }

   public long getMessagesOut() {
      return this.getHttpChannel().getMessagesOut();
   }

   public void send(Request request, Response.CompleteListener listener) {
      this.delegate.send(request, listener);
   }

   public SendFailure send(HttpExchange exchange) {
      return this.delegate.send(exchange);
   }

   public void onOpen() {
      super.onOpen();
      this.fillInterested();
      this.promise.succeeded(this);
   }

   public boolean isClosed() {
      return this.closed.get();
   }

   public void setAttachment(Object obj) {
      this.delegate.setAttachment(obj);
   }

   public Object getAttachment() {
      return this.delegate.getAttachment();
   }

   public boolean onIdleExpired() {
      long idleTimeout = this.getEndPoint().getIdleTimeout();
      boolean close = this.onIdleTimeout(idleTimeout);
      if (close) {
         this.close(new TimeoutException("Idle timeout " + idleTimeout + " ms"));
      }

      return false;
   }

   protected boolean onIdleTimeout(long idleTimeout) {
      TimeoutException failure = new TimeoutException("Idle timeout " + idleTimeout + " ms");
      return this.delegate.onIdleTimeout(idleTimeout, failure);
   }

   public void onFillable() {
      this.channel.receive();
   }

   public ByteBuffer onUpgradeFrom() {
      HttpReceiverOverHTTP receiver = this.channel.getHttpReceiver();
      return receiver.onUpgradeFrom();
   }

   void onResponseHeaders(HttpExchange exchange) {
      HttpRequest request = exchange.getRequest();
      if (request instanceof HttpProxy.TunnelRequest) {
         this.getEndPoint().setIdleTimeout(this.idleTimeout);
      }

   }

   public void release() {
      this.getEndPoint().setIdleTimeout(this.idleTimeout);
      this.getHttpDestination().release(this);
   }

   public void remove() {
      this.getHttpDestination().remove((Connection)this);
   }

   public void close() {
      this.close(new AsynchronousCloseException());
   }

   protected void close(Throwable failure) {
      if (this.closed.compareAndSet(false, true)) {
         this.getHttpDestination().remove((Connection)this);
         this.abort(failure);
         this.channel.destroy();
         this.getEndPoint().shutdownOutput();
         if (LOG.isDebugEnabled()) {
            LOG.debug("Shutdown {}", this);
         }

         this.getEndPoint().close();
         if (LOG.isDebugEnabled()) {
            LOG.debug("Closed {}", this);
         }

         this.delegate.destroy();
      }

   }

   protected boolean abort(Throwable failure) {
      HttpExchange exchange = this.channel.getHttpExchange();
      return exchange != null && exchange.getRequest().abort(failure);
   }

   public boolean sweep() {
      if (!this.closed.get()) {
         return false;
      } else {
         return this.sweeps.incrementAndGet() > 3;
      }
   }

   public String toConnectionString() {
      return String.format("%s@%x(l:%s <-> r:%s,closed=%b)=>%s", this.getClass().getSimpleName(), this.hashCode(), this.getEndPoint().getLocalSocketAddress(), this.getEndPoint().getRemoteSocketAddress(), this.closed.get(), this.channel);
   }

   private class Delegate extends HttpConnection {
      private Delegate(HttpDestination destination) {
         super(destination);
      }

      protected Iterator getHttpChannels() {
         return Collections.singleton(HttpConnectionOverHTTP.this.channel).iterator();
      }

      public SocketAddress getLocalSocketAddress() {
         return HttpConnectionOverHTTP.this.getEndPoint().getLocalSocketAddress();
      }

      public SocketAddress getRemoteSocketAddress() {
         return HttpConnectionOverHTTP.this.getEndPoint().getRemoteSocketAddress();
      }

      public SendFailure send(HttpExchange exchange) {
         HttpRequest request = exchange.getRequest();
         this.normalizeRequest(request);
         EndPoint endPoint = HttpConnectionOverHTTP.this.getEndPoint();
         HttpConnectionOverHTTP.this.idleTimeout = endPoint.getIdleTimeout();
         long requestIdleTimeout = request.getIdleTimeout();
         if (requestIdleTimeout >= 0L) {
            endPoint.setIdleTimeout(requestIdleTimeout);
         }

         return this.send(HttpConnectionOverHTTP.this.channel, exchange);
      }

      protected void normalizeRequest(HttpRequest request) {
         super.normalizeRequest(request);
         if (request instanceof HttpProxy.TunnelRequest) {
            request.idleTimeout(2L * this.getHttpClient().getConnectTimeout(), TimeUnit.MILLISECONDS);
         }

         HttpConversation conversation = request.getConversation();
         HttpUpgrader upgrader = (HttpUpgrader)conversation.getAttribute(HttpUpgrader.class.getName());
         if (upgrader == null) {
            if (request instanceof HttpUpgrader.Factory) {
               upgrader = ((HttpUpgrader.Factory)request).newHttpUpgrader(HttpVersion.HTTP_1_1);
               conversation.setAttribute(HttpUpgrader.class.getName(), upgrader);
               upgrader.prepare(request);
            } else {
               String protocol = request.getHeaders().get(HttpHeader.UPGRADE);
               if (protocol != null) {
                  upgrader = new ProtocolHttpUpgrader(this.getHttpDestination(), protocol);
                  conversation.setAttribute(HttpUpgrader.class.getName(), upgrader);
                  upgrader.prepare(request);
               }
            }
         }

      }

      public void close() {
         HttpConnectionOverHTTP.this.close();
      }

      public boolean isClosed() {
         return HttpConnectionOverHTTP.this.isClosed();
      }

      public String toString() {
         return HttpConnectionOverHTTP.this.toString();
      }
   }
}
