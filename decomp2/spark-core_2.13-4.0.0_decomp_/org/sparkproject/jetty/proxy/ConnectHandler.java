package org.sparkproject.jetty.proxy;

import jakarta.servlet.AsyncContext;
import jakarta.servlet.AsyncEvent;
import jakarta.servlet.AsyncListener;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.Closeable;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Executor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.http.HttpHeader;
import org.sparkproject.jetty.http.HttpHeaderValue;
import org.sparkproject.jetty.http.HttpMethod;
import org.sparkproject.jetty.io.ByteBufferPool;
import org.sparkproject.jetty.io.Connection;
import org.sparkproject.jetty.io.EndPoint;
import org.sparkproject.jetty.io.ManagedSelector;
import org.sparkproject.jetty.io.MappedByteBufferPool;
import org.sparkproject.jetty.io.SelectorManager;
import org.sparkproject.jetty.io.SocketChannelEndPoint;
import org.sparkproject.jetty.server.Handler;
import org.sparkproject.jetty.server.HttpChannel;
import org.sparkproject.jetty.server.HttpTransport;
import org.sparkproject.jetty.server.Request;
import org.sparkproject.jetty.server.handler.HandlerWrapper;
import org.sparkproject.jetty.util.Callback;
import org.sparkproject.jetty.util.HostPort;
import org.sparkproject.jetty.util.Promise;
import org.sparkproject.jetty.util.thread.ScheduledExecutorScheduler;
import org.sparkproject.jetty.util.thread.Scheduler;

public class ConnectHandler extends HandlerWrapper {
   protected static final Logger LOG = LoggerFactory.getLogger(ConnectHandler.class);
   private final Set whiteList;
   private final Set blackList;
   private Executor executor;
   private Scheduler scheduler;
   private ByteBufferPool bufferPool;
   private SelectorManager selector;
   private long connectTimeout;
   private long idleTimeout;
   private int bufferSize;

   public ConnectHandler() {
      this((Handler)null);
   }

   public ConnectHandler(Handler handler) {
      this.whiteList = new HashSet();
      this.blackList = new HashSet();
      this.connectTimeout = 15000L;
      this.idleTimeout = 30000L;
      this.bufferSize = 4096;
      this.setHandler(handler);
   }

   public Executor getExecutor() {
      return this.executor;
   }

   public void setExecutor(Executor executor) {
      this.executor = executor;
   }

   public Scheduler getScheduler() {
      return this.scheduler;
   }

   public void setScheduler(Scheduler scheduler) {
      this.updateBean(this.scheduler, scheduler);
      this.scheduler = scheduler;
   }

   public ByteBufferPool getByteBufferPool() {
      return this.bufferPool;
   }

   public void setByteBufferPool(ByteBufferPool bufferPool) {
      this.updateBean(this.bufferPool, bufferPool);
      this.bufferPool = bufferPool;
   }

   public long getConnectTimeout() {
      return this.connectTimeout;
   }

   public void setConnectTimeout(long connectTimeout) {
      this.connectTimeout = connectTimeout;
   }

   public long getIdleTimeout() {
      return this.idleTimeout;
   }

   public void setIdleTimeout(long idleTimeout) {
      this.idleTimeout = idleTimeout;
   }

   public int getBufferSize() {
      return this.bufferSize;
   }

   public void setBufferSize(int bufferSize) {
      this.bufferSize = bufferSize;
   }

   protected void doStart() throws Exception {
      if (this.executor == null) {
         this.executor = this.getServer().getThreadPool();
      }

      if (this.scheduler == null) {
         this.scheduler = (Scheduler)this.getServer().getBean(Scheduler.class);
         if (this.scheduler == null) {
            this.scheduler = new ScheduledExecutorScheduler(String.format("Proxy-Scheduler-%x", this.hashCode()), false);
         }

         this.addBean(this.scheduler);
      }

      if (this.bufferPool == null) {
         this.bufferPool = new MappedByteBufferPool();
         this.addBean(this.bufferPool);
      }

      this.addBean(this.selector = this.newSelectorManager());
      this.selector.setConnectTimeout(this.getConnectTimeout());
      super.doStart();
   }

   protected SelectorManager newSelectorManager() {
      return new ConnectManager(this.getExecutor(), this.getScheduler(), 1);
   }

   public void handle(String target, Request jettyRequest, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String tunnelProtocol = jettyRequest.getMetaData().getProtocol();
      if (HttpMethod.CONNECT.is(request.getMethod()) && tunnelProtocol == null) {
         String serverAddress = jettyRequest.getHttpURI().getAuthority();
         if (LOG.isDebugEnabled()) {
            LOG.debug("CONNECT request for {}", serverAddress);
         }

         this.handleConnect(jettyRequest, request, response, serverAddress);
      } else {
         super.handle(target, jettyRequest, request, response);
      }

   }

   protected void handleConnect(Request baseRequest, final HttpServletRequest request, final HttpServletResponse response, String serverAddress) {
      baseRequest.setHandled(true);

      try {
         boolean proceed = this.handleAuthentication(request, response, serverAddress);
         if (!proceed) {
            if (LOG.isDebugEnabled()) {
               LOG.debug("Missing proxy authentication");
            }

            this.sendConnectResponse(request, response, 407);
            return;
         }

         HostPort hostPort = new HostPort(serverAddress);
         String host = hostPort.getHost();
         int port = hostPort.getPort(80);
         if (!this.validateDestination(host, port)) {
            if (LOG.isDebugEnabled()) {
               LOG.debug("Destination {}:{} forbidden", host, port);
            }

            this.sendConnectResponse(request, response, 403);
            return;
         }

         final HttpChannel httpChannel = baseRequest.getHttpChannel();
         if (!httpChannel.isTunnellingSupported()) {
            if (LOG.isDebugEnabled()) {
               LOG.debug("CONNECT not supported for {}", httpChannel);
            }

            this.sendConnectResponse(request, response, 403);
            return;
         }

         final AsyncContext asyncContext = request.startAsync();
         asyncContext.setTimeout(0L);
         if (LOG.isDebugEnabled()) {
            LOG.debug("Connecting to {}:{}", host, port);
         }

         this.connectToServer(request, host, port, new Promise() {
            public void succeeded(SocketChannel channel) {
               ConnectContext connectContext = new ConnectContext(request, response, asyncContext, httpChannel.getTunnellingEndPoint());
               if (channel.isConnected()) {
                  ConnectHandler.this.selector.accept(channel, connectContext);
               } else {
                  ConnectHandler.this.selector.connect(channel, connectContext);
               }

            }

            public void failed(Throwable x) {
               ConnectHandler.this.onConnectFailure(request, response, asyncContext, x);
            }
         });
      } catch (Exception x) {
         this.onConnectFailure(request, response, (AsyncContext)null, x);
      }

   }

   protected void connectToServer(HttpServletRequest request, String host, int port, Promise promise) {
      SocketChannel channel = null;

      try {
         channel = SocketChannel.open();
         channel.socket().setTcpNoDelay(true);
         channel.configureBlocking(false);
         InetSocketAddress address = this.newConnectAddress(host, port);
         channel.connect(address);
         promise.succeeded(channel);
      } catch (Throwable x) {
         this.close(channel);
         promise.failed(x);
      }

   }

   private void close(Closeable closeable) {
      try {
         if (closeable != null) {
            closeable.close();
         }
      } catch (Throwable x) {
         LOG.trace("IGNORED", x);
      }

   }

   protected InetSocketAddress newConnectAddress(String host, int port) {
      return new InetSocketAddress(host, port);
   }

   protected void onConnectSuccess(ConnectContext connectContext, UpstreamConnection upstreamConnection) {
      ConcurrentMap<String, Object> context = connectContext.getContext();
      HttpServletRequest request = connectContext.getRequest();
      this.prepareContext(request, context);
      EndPoint downstreamEndPoint = connectContext.getEndPoint();
      DownstreamConnection downstreamConnection = this.newDownstreamConnection(downstreamEndPoint, context);
      downstreamConnection.setInputBufferSize(this.getBufferSize());
      upstreamConnection.setConnection(downstreamConnection);
      downstreamConnection.setConnection(upstreamConnection);
      if (LOG.isDebugEnabled()) {
         LOG.debug("Connection setup completed: {}<->{}", downstreamConnection, upstreamConnection);
      }

      HttpServletResponse response = connectContext.getResponse();
      this.sendConnectResponse(request, response, 200);
      this.upgradeConnection(request, response, downstreamConnection);
      connectContext.getAsyncContext().complete();
   }

   protected void onConnectFailure(HttpServletRequest request, HttpServletResponse response, AsyncContext asyncContext, Throwable failure) {
      if (LOG.isDebugEnabled()) {
         LOG.debug("CONNECT failed", failure);
      }

      this.sendConnectResponse(request, response, 500);
      if (asyncContext != null) {
         asyncContext.complete();
      }

   }

   private void sendConnectResponse(HttpServletRequest request, HttpServletResponse response, int statusCode) {
      try {
         response.setStatus(statusCode);
         response.setContentLength(0);
         if (statusCode != 200) {
            response.setHeader(HttpHeader.CONNECTION.asString(), HttpHeaderValue.CLOSE.asString());
         }

         if (LOG.isDebugEnabled()) {
            LOG.debug("CONNECT response sent {} {}", request.getProtocol(), response.getStatus());
         }
      } catch (Throwable x) {
         if (LOG.isDebugEnabled()) {
            LOG.debug("Could not send CONNECT response", x);
         }
      }

   }

   protected boolean handleAuthentication(HttpServletRequest request, HttpServletResponse response, String address) {
      return true;
   }

   protected DownstreamConnection newDownstreamConnection(EndPoint endPoint, ConcurrentMap context) {
      return new DownstreamConnection(endPoint, this.getExecutor(), this.getByteBufferPool(), context);
   }

   protected UpstreamConnection newUpstreamConnection(EndPoint endPoint, ConnectContext connectContext) {
      return new UpstreamConnection(endPoint, this.getExecutor(), this.getByteBufferPool(), connectContext);
   }

   protected void prepareContext(HttpServletRequest request, ConcurrentMap context) {
   }

   private void upgradeConnection(HttpServletRequest request, HttpServletResponse response, Connection connection) {
      request.setAttribute(HttpTransport.UPGRADE_CONNECTION_ATTRIBUTE, connection);
      if (LOG.isDebugEnabled()) {
         LOG.debug("Upgraded connection to {}", connection);
      }

   }

   protected int read(EndPoint endPoint, ByteBuffer buffer, ConcurrentMap context) throws IOException {
      int read = endPoint.fill(buffer);
      if (LOG.isDebugEnabled()) {
         LOG.debug("{} read {} bytes", this, read);
      }

      return read;
   }

   protected void write(EndPoint endPoint, ByteBuffer buffer, Callback callback, ConcurrentMap context) {
      if (LOG.isDebugEnabled()) {
         LOG.debug("{} writing {} bytes", this, buffer.remaining());
      }

      endPoint.write(callback, buffer);
   }

   public Set getWhiteListHosts() {
      return this.whiteList;
   }

   public Set getBlackListHosts() {
      return this.blackList;
   }

   public boolean validateDestination(String host, int port) {
      String hostPort = host + ":" + port;
      if (!this.whiteList.isEmpty() && !this.whiteList.contains(hostPort)) {
         if (LOG.isDebugEnabled()) {
            LOG.debug("Host {}:{} not whitelisted", host, port);
         }

         return false;
      } else if (!this.blackList.isEmpty() && this.blackList.contains(hostPort)) {
         if (LOG.isDebugEnabled()) {
            LOG.debug("Host {}:{} blacklisted", host, port);
         }

         return false;
      } else {
         return true;
      }
   }

   protected class ConnectManager extends SelectorManager {
      protected ConnectManager(Executor executor, Scheduler scheduler, int selectors) {
         super(executor, scheduler, selectors);
      }

      protected EndPoint newEndPoint(SelectableChannel channel, ManagedSelector selector, SelectionKey key) {
         SocketChannelEndPoint endPoint = new SocketChannelEndPoint((SocketChannel)channel, selector, key, this.getScheduler());
         endPoint.setIdleTimeout(ConnectHandler.this.getIdleTimeout());
         return endPoint;
      }

      public Connection newConnection(SelectableChannel channel, EndPoint endpoint, Object attachment) throws IOException {
         if (ConnectHandler.LOG.isDebugEnabled()) {
            ConnectHandler.LOG.debug("Connected to {}", ((SocketChannel)channel).getRemoteAddress());
         }

         ConnectContext connectContext = (ConnectContext)attachment;
         UpstreamConnection connection = ConnectHandler.this.newUpstreamConnection(endpoint, connectContext);
         connection.setInputBufferSize(ConnectHandler.this.getBufferSize());
         return connection;
      }

      protected void connectionFailed(SelectableChannel channel, Throwable ex, Object attachment) {
         ConnectHandler.this.close(channel);
         ConnectContext connectContext = (ConnectContext)attachment;
         ConnectHandler.this.onConnectFailure(connectContext.request, connectContext.response, connectContext.asyncContext, ex);
      }
   }

   protected static class ConnectContext {
      private final ConcurrentMap context = new ConcurrentHashMap();
      private final HttpServletRequest request;
      private final HttpServletResponse response;
      private final AsyncContext asyncContext;
      private final EndPoint endPoint;

      public ConnectContext(HttpServletRequest request, HttpServletResponse response, AsyncContext asyncContext, EndPoint endPoint) {
         this.request = request;
         this.response = response;
         this.asyncContext = asyncContext;
         this.endPoint = endPoint;
      }

      public ConcurrentMap getContext() {
         return this.context;
      }

      public HttpServletRequest getRequest() {
         return this.request;
      }

      public HttpServletResponse getResponse() {
         return this.response;
      }

      public AsyncContext getAsyncContext() {
         return this.asyncContext;
      }

      public EndPoint getEndPoint() {
         return this.endPoint;
      }
   }

   public class UpstreamConnection extends ProxyConnection implements AsyncListener {
      private final ConnectContext connectContext;

      public UpstreamConnection(EndPoint endPoint, Executor executor, ByteBufferPool bufferPool, ConnectContext connectContext) {
         super(endPoint, executor, bufferPool, connectContext.getContext());
         this.connectContext = connectContext;
      }

      public void onOpen() {
         super.onOpen();
         this.connectContext.asyncContext.addListener(this);
         ConnectHandler.this.onConnectSuccess(this.connectContext, this);
      }

      protected int read(EndPoint endPoint, ByteBuffer buffer) throws IOException {
         return ConnectHandler.this.read(endPoint, buffer, this.getContext());
      }

      protected void write(EndPoint endPoint, ByteBuffer buffer, Callback callback) {
         ConnectHandler.this.write(endPoint, buffer, callback, this.getContext());
      }

      public void onComplete(AsyncEvent event) {
         this.fillInterested();
      }

      public void onTimeout(AsyncEvent event) {
      }

      public void onError(AsyncEvent event) {
         this.close(event.getThrowable());
      }

      public void onStartAsync(AsyncEvent event) {
      }
   }

   public class DownstreamConnection extends ProxyConnection implements Connection.UpgradeTo {
      private ByteBuffer buffer;

      public DownstreamConnection(EndPoint endPoint, Executor executor, ByteBufferPool bufferPool, ConcurrentMap context) {
         super(endPoint, executor, bufferPool, context);
      }

      public void onUpgradeTo(ByteBuffer buffer) {
         this.buffer = buffer;
      }

      public void onOpen() {
         super.onOpen();
         if (this.buffer == null) {
            this.fillInterested();
         } else {
            final int remaining = this.buffer.remaining();
            this.write(this.getConnection().getEndPoint(), this.buffer, new Callback() {
               public void succeeded() {
                  DownstreamConnection.this.buffer = null;
                  if (ProxyConnection.LOG.isDebugEnabled()) {
                     ProxyConnection.LOG.debug("{} wrote initial {} bytes to server", DownstreamConnection.this, remaining);
                  }

                  DownstreamConnection.this.fillInterested();
               }

               public void failed(Throwable x) {
                  DownstreamConnection.this.buffer = null;
                  if (ProxyConnection.LOG.isDebugEnabled()) {
                     ProxyConnection.LOG.debug("{} failed to write initial {} bytes to server", new Object[]{this, remaining, x});
                  }

                  DownstreamConnection.this.close();
                  DownstreamConnection.this.getConnection().close();
               }
            });
         }
      }

      protected int read(EndPoint endPoint, ByteBuffer buffer) throws IOException {
         return ConnectHandler.this.read(endPoint, buffer, this.getContext());
      }

      protected void write(EndPoint endPoint, ByteBuffer buffer, Callback callback) {
         ConnectHandler.this.write(endPoint, buffer, callback, this.getContext());
      }
   }
}
