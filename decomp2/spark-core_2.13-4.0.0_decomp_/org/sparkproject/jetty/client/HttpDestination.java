package org.sparkproject.jetty.client;

import java.io.Closeable;
import java.io.IOException;
import java.nio.channels.AsynchronousCloseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.client.api.Connection;
import org.sparkproject.jetty.client.api.Destination;
import org.sparkproject.jetty.client.api.Request;
import org.sparkproject.jetty.client.api.Response;
import org.sparkproject.jetty.http.HttpField;
import org.sparkproject.jetty.http.HttpHeader;
import org.sparkproject.jetty.io.ClientConnectionFactory;
import org.sparkproject.jetty.io.CyclicTimeouts;
import org.sparkproject.jetty.util.BlockingArrayQueue;
import org.sparkproject.jetty.util.Callback;
import org.sparkproject.jetty.util.HostPort;
import org.sparkproject.jetty.util.NanoTime;
import org.sparkproject.jetty.util.Promise;
import org.sparkproject.jetty.util.annotation.ManagedAttribute;
import org.sparkproject.jetty.util.annotation.ManagedObject;
import org.sparkproject.jetty.util.component.ContainerLifeCycle;
import org.sparkproject.jetty.util.component.Dumpable;
import org.sparkproject.jetty.util.component.DumpableCollection;
import org.sparkproject.jetty.util.component.LifeCycle;
import org.sparkproject.jetty.util.ssl.SslContextFactory;
import org.sparkproject.jetty.util.thread.AutoLock;
import org.sparkproject.jetty.util.thread.Scheduler;
import org.sparkproject.jetty.util.thread.Sweeper;

@ManagedObject
public abstract class HttpDestination extends ContainerLifeCycle implements Destination, Closeable, Callback, Dumpable, Sweeper.Sweepable {
   private static final Logger LOG = LoggerFactory.getLogger(HttpDestination.class);
   private final HttpClient client;
   private final Origin origin;
   private final Queue exchanges;
   private final RequestNotifier requestNotifier;
   private final ResponseNotifier responseNotifier;
   private final ProxyConfiguration.Proxy proxy;
   private final ClientConnectionFactory connectionFactory;
   private final HttpField hostField;
   private final RequestTimeouts requestTimeouts;
   private final AutoLock staleLock = new AutoLock();
   private ConnectionPool connectionPool;
   private boolean stale;
   private long activeNanoTime;

   public HttpDestination(HttpClient client, Origin origin, boolean intrinsicallySecure) {
      this.client = client;
      this.origin = origin;
      this.exchanges = this.newExchangeQueue(client);
      this.requestNotifier = new RequestNotifier(client);
      this.responseNotifier = new ResponseNotifier();
      this.requestTimeouts = new RequestTimeouts(client.getScheduler());
      String host = HostPort.normalizeHost(this.getHost());
      if (!client.isDefaultPort(this.getScheme(), this.getPort())) {
         host = host + ":" + this.getPort();
      }

      this.hostField = new HttpField(HttpHeader.HOST, host);
      ProxyConfiguration proxyConfig = client.getProxyConfiguration();
      this.proxy = proxyConfig.match(origin);
      ClientConnectionFactory connectionFactory = client.getTransport();
      if (this.proxy != null) {
         connectionFactory = this.proxy.newClientConnectionFactory(connectionFactory);
         if (!intrinsicallySecure && this.proxy.isSecure()) {
            connectionFactory = this.newSslClientConnectionFactory(this.proxy.getSslContextFactory(), connectionFactory);
         }
      } else if (!intrinsicallySecure && this.isSecure()) {
         connectionFactory = this.newSslClientConnectionFactory((SslContextFactory.Client)null, connectionFactory);
      }

      Object tag = origin.getTag();
      if (tag instanceof ClientConnectionFactory.Decorator) {
         connectionFactory = ((ClientConnectionFactory.Decorator)tag).apply(connectionFactory);
      }

      this.connectionFactory = connectionFactory;
   }

   public void accept(Connection connection) {
      this.connectionPool.accept(connection);
   }

   public boolean stale() {
      try (AutoLock l = this.staleLock.lock()) {
         boolean stale = this.stale;
         if (!stale) {
            this.activeNanoTime = NanoTime.now();
         }

         if (LOG.isDebugEnabled()) {
            LOG.debug("Stale check done with result {} on {}", stale, this);
         }

         return stale;
      }
   }

   public boolean sweep() {
      if (LOG.isDebugEnabled()) {
         LOG.debug("Sweep check in progress on {}", this);
      }

      boolean remove = false;

      try (AutoLock l = this.staleLock.lock()) {
         boolean stale = this.exchanges.isEmpty() && this.connectionPool.isEmpty();
         if (!stale) {
            this.activeNanoTime = NanoTime.now();
         } else if (NanoTime.millisSince(this.activeNanoTime) >= this.getHttpClient().getDestinationIdleTimeout()) {
            this.stale = true;
            remove = true;
         }
      }

      if (remove) {
         this.getHttpClient().removeDestination(this);
         LifeCycle.stop(this);
      }

      if (LOG.isDebugEnabled()) {
         LOG.debug("Sweep check done with result {} on {}", remove, this);
      }

      return remove;
   }

   protected void doStart() throws Exception {
      this.connectionPool = this.newConnectionPool(this.client);
      this.addBean(this.connectionPool, true);
      super.doStart();
      Sweeper connectionPoolSweeper = (Sweeper)this.client.getBean(Sweeper.class);
      if (connectionPoolSweeper != null && this.connectionPool instanceof Sweeper.Sweepable) {
         connectionPoolSweeper.offer((Sweeper.Sweepable)this.connectionPool);
      }

      Sweeper destinationSweeper = this.getHttpClient().getDestinationSweeper();
      if (destinationSweeper != null) {
         destinationSweeper.offer(this);
      }

   }

   protected void doStop() throws Exception {
      Sweeper destinationSweeper = this.getHttpClient().getDestinationSweeper();
      if (destinationSweeper != null) {
         destinationSweeper.remove(this);
      }

      Sweeper connectionPoolSweeper = (Sweeper)this.client.getBean(Sweeper.class);
      if (connectionPoolSweeper != null && this.connectionPool instanceof Sweeper.Sweepable) {
         connectionPoolSweeper.remove((Sweeper.Sweepable)this.connectionPool);
      }

      super.doStop();
      this.removeBean(this.connectionPool);
   }

   protected ConnectionPool newConnectionPool(HttpClient client) {
      return client.getTransport().getConnectionPoolFactory().newConnectionPool(this);
   }

   protected Queue newExchangeQueue(HttpClient client) {
      int maxCapacity = client.getMaxRequestsQueuedPerDestination();
      return maxCapacity > 32 ? new BlockingArrayQueue(32, 32, maxCapacity) : new BlockingArrayQueue(maxCapacity);
   }

   protected ClientConnectionFactory newSslClientConnectionFactory(SslContextFactory.Client sslContextFactory, ClientConnectionFactory connectionFactory) {
      return this.client.newSslClientConnectionFactory(sslContextFactory, connectionFactory);
   }

   public boolean isSecure() {
      return HttpClient.isSchemeSecure(this.getScheme());
   }

   public HttpClient getHttpClient() {
      return this.client;
   }

   public Origin getOrigin() {
      return this.origin;
   }

   public Queue getHttpExchanges() {
      return this.exchanges;
   }

   public RequestNotifier getRequestNotifier() {
      return this.requestNotifier;
   }

   public ResponseNotifier getResponseNotifier() {
      return this.responseNotifier;
   }

   public ProxyConfiguration.Proxy getProxy() {
      return this.proxy;
   }

   public ClientConnectionFactory getClientConnectionFactory() {
      return this.connectionFactory;
   }

   @ManagedAttribute(
      value = "The destination scheme",
      readonly = true
   )
   public String getScheme() {
      return this.getOrigin().getScheme();
   }

   @ManagedAttribute(
      value = "The destination host",
      readonly = true
   )
   public String getHost() {
      return this.getOrigin().getAddress().getHost();
   }

   @ManagedAttribute(
      value = "The destination port",
      readonly = true
   )
   public int getPort() {
      return this.getOrigin().getAddress().getPort();
   }

   @ManagedAttribute(
      value = "The number of queued requests",
      readonly = true
   )
   public int getQueuedRequestCount() {
      return this.exchanges.size();
   }

   public Origin.Address getConnectAddress() {
      return this.proxy == null ? this.getOrigin().getAddress() : this.proxy.getAddress();
   }

   public HttpField getHostField() {
      return this.hostField;
   }

   @ManagedAttribute(
      value = "The connection pool",
      readonly = true
   )
   public ConnectionPool getConnectionPool() {
      return this.connectionPool;
   }

   public void succeeded() {
      this.send(false);
   }

   public void failed(Throwable x) {
      this.abort(x);
   }

   public void send(Request request, Response.CompleteListener listener) {
      ((HttpRequest)request).sendAsync(this, listener);
   }

   protected void send(HttpRequest request, List listeners) {
      this.send(new HttpExchange(this, request, listeners));
   }

   public void send(HttpExchange exchange) {
      HttpRequest request = exchange.getRequest();
      if (this.client.isRunning()) {
         if (this.enqueue(this.exchanges, exchange)) {
            request.sent();
            this.requestTimeouts.schedule(exchange);
            if (!this.client.isRunning() && this.exchanges.remove(exchange)) {
               request.abort(new RejectedExecutionException(String.valueOf(this.client) + " is stopping"));
            } else {
               if (LOG.isDebugEnabled()) {
                  LOG.debug("Queued {} for {}", request, this);
               }

               this.requestNotifier.notifyQueued(request);
               this.send();
            }
         } else {
            if (LOG.isDebugEnabled()) {
               LOG.debug("Max queue size {} exceeded by {} for {}", new Object[]{this.client.getMaxRequestsQueuedPerDestination(), request, this});
            }

            int var10003 = this.client.getMaxRequestsQueuedPerDestination();
            request.abort(new RejectedExecutionException("Max requests queued per destination " + var10003 + " exceeded for " + String.valueOf(this)));
         }
      } else {
         request.abort(new RejectedExecutionException(String.valueOf(this.client) + " is stopped"));
      }

   }

   protected boolean enqueue(Queue queue, HttpExchange exchange) {
      return queue.offer(exchange);
   }

   public void send() {
      this.send(true);
   }

   private void send(boolean create) {
      if (!this.getHttpExchanges().isEmpty()) {
         this.process(create);
      }

   }

   private void process(boolean create) {
      while(true) {
         Connection connection = this.connectionPool.acquire(create);
         if (connection != null) {
            boolean proceed = this.process(connection);
            if (proceed) {
               create = false;
               continue;
            }
         }

         return;
      }
   }

   private boolean process(Connection connection) {
      HttpClient client = this.getHttpClient();
      HttpExchange exchange = (HttpExchange)this.getHttpExchanges().poll();
      if (LOG.isDebugEnabled()) {
         LOG.debug("Processing exchange {} on {} of {}", new Object[]{exchange, connection, this});
      }

      if (exchange == null) {
         if (!this.connectionPool.release(connection)) {
            connection.close();
         }

         if (!client.isRunning()) {
            if (LOG.isDebugEnabled()) {
               LOG.debug("{} is stopping", client);
            }

            connection.close();
         }

         return false;
      } else {
         Request request = exchange.getRequest();
         Throwable cause = request.getAbortCause();
         if (cause != null) {
            if (LOG.isDebugEnabled()) {
               LOG.debug("Aborted before processing {}: {}", exchange, cause);
            }

            boolean released = this.connectionPool.release(connection);
            if (!released) {
               connection.close();
            }

            exchange.abort(cause);
            return this.getQueuedRequestCount() > 0;
         } else {
            SendFailure failure = this.send((IConnection)connection, exchange);
            if (failure == null) {
               return this.getQueuedRequestCount() > 0;
            } else {
               if (LOG.isDebugEnabled()) {
                  LOG.debug("Send failed {} for {}", failure, exchange);
               }

               if (failure.retry) {
                  this.send(exchange);
                  return false;
               } else {
                  request.abort(failure.failure);
                  return this.getQueuedRequestCount() > 0;
               }
            }
         }
      }
   }

   protected SendFailure send(IConnection connection, HttpExchange exchange) {
      return connection.send(exchange);
   }

   public void newConnection(Promise promise) {
      this.createConnection(promise);
   }

   protected void createConnection(Promise promise) {
      this.client.newConnection(this, promise);
   }

   public boolean remove(HttpExchange exchange) {
      return this.exchanges.remove(exchange);
   }

   public void close() {
      this.abort(new AsynchronousCloseException());
      if (LOG.isDebugEnabled()) {
         LOG.debug("Closed {}", this);
      }

      this.connectionPool.close();
      this.requestTimeouts.destroy();
   }

   public void release(Connection connection) {
      if (LOG.isDebugEnabled()) {
         LOG.debug("Released {}", connection);
      }

      HttpClient client = this.getHttpClient();
      if (client.isRunning()) {
         if (this.connectionPool.isActive(connection)) {
            if (this.connectionPool.release(connection)) {
               this.send(false);
            } else {
               connection.close();
               this.send(true);
            }
         } else if (LOG.isDebugEnabled()) {
            LOG.debug("Released explicit {}", connection);
         }
      } else {
         if (LOG.isDebugEnabled()) {
            LOG.debug("{} is stopped", client);
         }

         connection.close();
      }

   }

   public boolean remove(Connection connection) {
      boolean removed = this.connectionPool.remove(connection);
      if (removed) {
         this.send(true);
      }

      return removed;
   }

   public void abort(Throwable cause) {
      for(HttpExchange exchange : new ArrayList(this.exchanges)) {
         exchange.getRequest().abort(cause);
      }

   }

   public void dump(Appendable out, String indent) throws IOException {
      this.dumpObjects(out, indent, new Object[]{new DumpableCollection("exchanges", this.exchanges)});
   }

   public String asString() {
      return this.getOrigin().asString();
   }

   @ManagedAttribute("For how long this destination has been idle in ms")
   public long getIdle() {
      if (this.getHttpClient().getDestinationIdleTimeout() <= 0L) {
         return -1L;
      } else {
         try (AutoLock l = this.staleLock.lock()) {
            return NanoTime.millisSince(this.activeNanoTime);
         }
      }
   }

   @ManagedAttribute("Whether this destinations is stale")
   public boolean isStale() {
      try (AutoLock l = this.staleLock.lock()) {
         return this.stale;
      }
   }

   public String toString() {
      return String.format("%s[%s]@%x%s,state=%s,queue=%d,pool=%s,stale=%b,idle=%d", HttpDestination.class.getSimpleName(), this.getOrigin(), this.hashCode(), this.proxy == null ? "" : "(via " + String.valueOf(this.proxy) + ")", this.getState(), this.getQueuedRequestCount(), this.getConnectionPool(), this.isStale(), this.getIdle());
   }

   private class RequestTimeouts extends CyclicTimeouts {
      private RequestTimeouts(Scheduler scheduler) {
         super(scheduler);
      }

      protected Iterator iterator() {
         return HttpDestination.this.exchanges.iterator();
      }

      protected boolean onExpired(HttpExchange exchange) {
         HttpRequest request = exchange.getRequest();
         request.abort(new TimeoutException("Total timeout " + request.getConversation().getTimeout() + " ms elapsed"));
         return false;
      }
   }

   @FunctionalInterface
   public interface Multiplexed {
      void setMaxRequestsPerConnection(int var1);
   }
}
