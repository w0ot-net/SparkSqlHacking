package org.sparkproject.jetty.server;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.http.BadMessageException;
import org.sparkproject.jetty.http.DateGenerator;
import org.sparkproject.jetty.http.HttpField;
import org.sparkproject.jetty.http.HttpGenerator;
import org.sparkproject.jetty.http.HttpHeader;
import org.sparkproject.jetty.http.HttpMethod;
import org.sparkproject.jetty.http.HttpURI;
import org.sparkproject.jetty.http.PreEncodedHttpField;
import org.sparkproject.jetty.server.handler.ContextHandler;
import org.sparkproject.jetty.server.handler.ErrorHandler;
import org.sparkproject.jetty.server.handler.HandlerWrapper;
import org.sparkproject.jetty.util.Attributes;
import org.sparkproject.jetty.util.Jetty;
import org.sparkproject.jetty.util.MultiException;
import org.sparkproject.jetty.util.MultiMap;
import org.sparkproject.jetty.util.NanoTime;
import org.sparkproject.jetty.util.StringUtil;
import org.sparkproject.jetty.util.URIUtil;
import org.sparkproject.jetty.util.Uptime;
import org.sparkproject.jetty.util.annotation.ManagedAttribute;
import org.sparkproject.jetty.util.annotation.ManagedObject;
import org.sparkproject.jetty.util.annotation.Name;
import org.sparkproject.jetty.util.component.AbstractLifeCycle;
import org.sparkproject.jetty.util.component.AttributeContainerMap;
import org.sparkproject.jetty.util.component.Container;
import org.sparkproject.jetty.util.component.Graceful;
import org.sparkproject.jetty.util.component.LifeCycle;
import org.sparkproject.jetty.util.thread.AutoLock;
import org.sparkproject.jetty.util.thread.QueuedThreadPool;
import org.sparkproject.jetty.util.thread.ShutdownThread;
import org.sparkproject.jetty.util.thread.ThreadPool;

@ManagedObject("Jetty HTTP Servlet server")
public class Server extends HandlerWrapper implements Attributes {
   private static final Logger LOG = LoggerFactory.getLogger(Server.class);
   private final AttributeContainerMap _attributes;
   private final ThreadPool _threadPool;
   private final List _connectors;
   private SessionIdManager _sessionIdManager;
   private boolean _stopAtShutdown;
   private boolean _dumpAfterStart;
   private boolean _dumpBeforeStop;
   private ErrorHandler _errorHandler;
   private RequestLog _requestLog;
   private boolean _dryRun;
   private final AutoLock _dateLock;
   private volatile DateField _dateField;
   private long _stopTimeout;

   public Server() {
      this((ThreadPool)null);
   }

   public Server(@Name("port") int port) {
      this((ThreadPool)null);
      ServerConnector connector = new ServerConnector(this);
      connector.setPort(port);
      this.setConnectors(new Connector[]{connector});
   }

   public Server(@Name("address") InetSocketAddress addr) {
      this((ThreadPool)null);
      ServerConnector connector = new ServerConnector(this);
      connector.setHost(addr.getHostName());
      connector.setPort(addr.getPort());
      this.setConnectors(new Connector[]{connector});
   }

   public Server(@Name("threadpool") ThreadPool pool) {
      this._attributes = new AttributeContainerMap();
      this._connectors = new CopyOnWriteArrayList();
      this._dateLock = new AutoLock();
      this._threadPool = (ThreadPool)(pool != null ? pool : new QueuedThreadPool());
      this.addBean(this._threadPool);
      this.addBean(this._attributes);
      this.setServer(this);
   }

   public boolean isDryRun() {
      return this._dryRun;
   }

   public void setDryRun(boolean dryRun) {
      this._dryRun = dryRun;
   }

   public RequestLog getRequestLog() {
      return this._requestLog;
   }

   public ErrorHandler getErrorHandler() {
      return this._errorHandler;
   }

   public void setRequestLog(RequestLog requestLog) {
      this.updateBean(this._requestLog, requestLog);
      this._requestLog = requestLog;
   }

   public void setErrorHandler(ErrorHandler errorHandler) {
      if (errorHandler instanceof ErrorHandler.ErrorPageMapper) {
         throw new IllegalArgumentException("ErrorPageMapper is applicable only to ContextHandler");
      } else {
         this.updateBean(this._errorHandler, errorHandler);
         this._errorHandler = errorHandler;
         if (errorHandler != null) {
            errorHandler.setServer(this);
         }

      }
   }

   @ManagedAttribute("version of this server")
   public static String getVersion() {
      return Jetty.VERSION;
   }

   public void setStopTimeout(long stopTimeout) {
      this._stopTimeout = stopTimeout;
   }

   public long getStopTimeout() {
      return this._stopTimeout;
   }

   public boolean getStopAtShutdown() {
      return this._stopAtShutdown;
   }

   public void setStopAtShutdown(boolean stop) {
      if (stop) {
         if (!this._stopAtShutdown && this.isStarted()) {
            ShutdownThread.register(this);
         }
      } else {
         ShutdownThread.deregister(this);
      }

      this._stopAtShutdown = stop;
   }

   @ManagedAttribute(
      value = "connectors for this server",
      readonly = true
   )
   public Connector[] getConnectors() {
      List<Connector> connectors = new ArrayList(this._connectors);
      return (Connector[])connectors.toArray(new Connector[connectors.size()]);
   }

   public void addConnector(Connector connector) {
      if (connector.getServer() != this) {
         String var10002 = String.valueOf(connector);
         throw new IllegalArgumentException("Connector " + var10002 + " cannot be shared among server " + String.valueOf(connector.getServer()) + " and server " + String.valueOf(this));
      } else {
         this._connectors.add(connector);
         this.addBean(connector);
      }
   }

   public void removeConnector(Connector connector) {
      if (this._connectors.remove(connector)) {
         this.removeBean(connector);
      }

   }

   public void setConnectors(Connector[] connectors) {
      if (connectors != null) {
         for(Connector connector : connectors) {
            if (connector.getServer() != this) {
               String var10002 = String.valueOf(connector);
               throw new IllegalArgumentException("Connector " + var10002 + " cannot be shared among server " + String.valueOf(connector.getServer()) + " and server " + String.valueOf(this));
            }
         }
      }

      Connector[] oldConnectors = this.getConnectors();
      this.updateBeans(oldConnectors, connectors);
      this._connectors.removeAll(Arrays.asList(oldConnectors));
      if (connectors != null) {
         this._connectors.addAll(Arrays.asList(connectors));
      }

   }

   public void addBeanToAllConnectors(Object bean) {
      for(Connector connector : this.getConnectors()) {
         connector.addBean(bean);
      }

   }

   @ManagedAttribute("the server thread pool")
   public ThreadPool getThreadPool() {
      return this._threadPool;
   }

   @ManagedAttribute("dump state to stderr after start")
   public boolean isDumpAfterStart() {
      return this._dumpAfterStart;
   }

   public void setDumpAfterStart(boolean dumpAfterStart) {
      this._dumpAfterStart = dumpAfterStart;
   }

   @ManagedAttribute("dump state to stderr before stop")
   public boolean isDumpBeforeStop() {
      return this._dumpBeforeStop;
   }

   public void setDumpBeforeStop(boolean dumpBeforeStop) {
      this._dumpBeforeStop = dumpBeforeStop;
   }

   public HttpField getDateField() {
      long now = System.currentTimeMillis();
      long seconds = now / 1000L;
      DateField df = this._dateField;
      if (df == null || df._seconds != seconds) {
         try (AutoLock lock = this._dateLock.lock()) {
            df = this._dateField;
            if (df != null && df._seconds == seconds) {
               return df._dateField;
            } else {
               HttpField field = new PreEncodedHttpField(HttpHeader.DATE, DateGenerator.formatDate(now));
               this._dateField = new DateField(seconds, field);
               return field;
            }
         }
      } else {
         return df._dateField;
      }
   }

   protected void doStart() throws Exception {
      try {
         if (this._errorHandler == null) {
            this._errorHandler = (ErrorHandler)this.getBean(ErrorHandler.class);
         }

         if (this._errorHandler == null) {
            this.setErrorHandler(new ErrorHandler());
         }

         if (this._errorHandler instanceof ErrorHandler.ErrorPageMapper) {
            LOG.warn("ErrorPageMapper not supported for Server level Error Handling");
         }

         this._errorHandler.setServer(this);
         if (this.getStopAtShutdown()) {
            ShutdownThread.register(this);
         }

         ShutdownMonitor.register(this);
         ShutdownMonitor.getInstance().start();
         String gitHash = Jetty.GIT_HASH;
         String timestamp = Jetty.BUILD_TIMESTAMP;
         LOG.info("jetty-{}; built: {}; git: {}; jvm {}", new Object[]{getVersion(), timestamp, gitHash, System.getProperty("java.runtime.version", System.getProperty("java.version"))});
         if (!Jetty.STABLE) {
            LOG.warn("THIS IS NOT A STABLE RELEASE! DO NOT USE IN PRODUCTION!");
            LOG.warn("Download a stable release from https://download.eclipse.org/jetty/");
         }

         HttpGenerator.setJettyVersion(HttpConfiguration.SERVER_VERSION);
         MultiException mex = new MultiException();
         if (!this._dryRun) {
            Stream var15 = this._connectors.stream();
            Objects.requireNonNull(NetworkConnector.class);
            var15 = var15.filter(NetworkConnector.class::isInstance);
            Objects.requireNonNull(NetworkConnector.class);
            var15.map(NetworkConnector.class::cast).forEach((connectorx) -> {
               try {
                  connectorx.open();
               } catch (Throwable th) {
                  mex.add(th);
               }

            });
            mex.ifExceptionThrow();
         }

         super.doStart();
         if (this._dryRun) {
            LOG.info(String.format("Started(dry run) %s @%dms", this, Uptime.getUptime()));
            throw new AbstractLifeCycle.StopException();
         }

         for(Connector connector : this._connectors) {
            try {
               connector.start();
            } catch (Throwable e) {
               mex.add(e);
               Stream var17 = this._connectors.stream().filter(LifeCycle::isRunning);
               Objects.requireNonNull(Object.class);
               var17.map(Object.class::cast).forEach(LifeCycle::stop);
            }
         }

         mex.ifExceptionThrow();
         LOG.info(String.format("Started %s @%dms", this, Uptime.getUptime()));
      } catch (Throwable th) {
         Stream var10000 = this._connectors.stream();
         Objects.requireNonNull(NetworkConnector.class);
         var10000 = var10000.filter(NetworkConnector.class::isInstance);
         Objects.requireNonNull(NetworkConnector.class);
         var10000.map(NetworkConnector.class::cast).forEach((nc) -> {
            try {
               nc.close();
            } catch (Throwable th2) {
               if (th != th2) {
                  th.addSuppressed(th2);
               }
            }

         });
         throw th;
      } finally {
         if (this.isDumpAfterStart() && (!this._dryRun || !this.isDumpBeforeStop())) {
            this.dumpStdErr();
         }

      }

   }

   protected void start(LifeCycle l) throws Exception {
      if (!(l instanceof Connector)) {
         super.start(l);
      }

   }

   protected void doStop() throws Exception {
      if (this.isDumpBeforeStop()) {
         this.dumpStdErr();
      }

      LOG.info(String.format("Stopped %s", this));
      if (LOG.isDebugEnabled()) {
         LOG.debug("doStop {}", this);
      }

      MultiException mex = new MultiException();
      if (this.getStopTimeout() > 0L) {
         long end = NanoTime.now() + TimeUnit.MILLISECONDS.toNanos(this.getStopTimeout());

         try {
            Graceful.shutdown((Container)this).get(this.getStopTimeout(), TimeUnit.MILLISECONDS);
         } catch (Throwable e) {
            mex.add(e);
         }

         QueuedThreadPool qtp = (QueuedThreadPool)this.getBean(QueuedThreadPool.class);
         if (qtp != null) {
            qtp.setStopTimeout(Math.max(1000L, NanoTime.millisUntil(end)));
         }
      }

      for(Connector connector : this._connectors) {
         try {
            connector.stop();
         } catch (Throwable e) {
            mex.add(e);
         }
      }

      try {
         super.doStop();
      } catch (Throwable e) {
         mex.add(e);
      }

      if (this.getStopAtShutdown()) {
         ShutdownThread.deregister(this);
      }

      ShutdownMonitor.deregister(this);
      mex.ifExceptionThrow();
   }

   public void handle(HttpChannel channel) throws IOException, ServletException {
      String target = channel.getRequest().getPathInfo();
      Request request = channel.getRequest();
      Response response = channel.getResponse();
      if (LOG.isDebugEnabled()) {
         LOG.debug("{} {} {} ?{} on {}", new Object[]{request.getDispatcherType(), request.getMethod(), target, request.getQueryString(), channel});
      }

      if (!HttpMethod.OPTIONS.is(request.getMethod()) && !"*".equals(target)) {
         this.handle(target, request, request, response);
      } else if (!HttpMethod.OPTIONS.is(request.getMethod())) {
         request.setHandled(true);
         response.sendError(400);
      } else {
         this.handleOptions(request, response);
         if (!request.isHandled()) {
            this.handle(target, request, request, response);
         }
      }

      if (LOG.isDebugEnabled()) {
         LOG.debug("handled={} async={} committed={} on {}", new Object[]{request.isHandled(), request.isAsyncStarted(), response.isCommitted(), channel});
      }

   }

   protected void handleOptions(Request request, Response response) throws IOException {
   }

   public void handleAsync(HttpChannel channel) throws IOException, ServletException {
      HttpChannelState state = channel.getRequest().getHttpChannelState();
      AsyncContextEvent event = state.getAsyncContextEvent();
      Request baseRequest = channel.getRequest();
      HttpURI baseUri = event.getBaseURI();
      String encodedPathQuery = event.getDispatchPath();
      if (encodedPathQuery == null && baseUri == null) {
         this.handleAsync(channel, event, baseRequest);
      } else {
         HttpURI oldUri = baseRequest.getHttpURI();
         MultiMap<String> oldQueryParams = baseRequest.getQueryParameters();

         try {
            if (encodedPathQuery == null) {
               baseRequest.setHttpURI(baseUri);
            } else {
               ServletContext servletContext = event.getServletContext();
               if (servletContext != null) {
                  String encodedContextPath = servletContext instanceof ContextHandler.Context ? ((ContextHandler.Context)servletContext).getContextHandler().getContextPathEncoded() : URIUtil.encodePath(servletContext.getContextPath());
                  if (!StringUtil.isEmpty(encodedContextPath)) {
                     encodedPathQuery = URIUtil.canonicalPath(URIUtil.addEncodedPaths(encodedContextPath, encodedPathQuery));
                     if (encodedPathQuery == null) {
                        throw new BadMessageException(500, "Bad dispatch path");
                     }
                  }
               }

               if (baseUri == null) {
                  baseUri = oldUri;
               }

               HttpURI.Mutable builder = HttpURI.build(baseUri, encodedPathQuery);
               if (StringUtil.isEmpty(builder.getParam())) {
                  builder.param(baseUri.getParam());
               }

               if (StringUtil.isEmpty(builder.getQuery())) {
                  builder.query(baseUri.getQuery());
               }

               baseRequest.setHttpURI(builder);
               if (baseUri.getQuery() != null && baseRequest.getQueryString() != null) {
                  baseRequest.mergeQueryParameters(oldUri.getQuery(), baseRequest.getQueryString());
               }
            }

            baseRequest.setContext((ContextHandler.Context)null, baseRequest.getHttpURI().getDecodedPath());
            this.handleAsync(channel, event, baseRequest);
         } finally {
            baseRequest.setHttpURI(oldUri);
            baseRequest.setQueryParameters(oldQueryParams);
            baseRequest.resetParameters();
         }

      }
   }

   private void handleAsync(HttpChannel channel, AsyncContextEvent event, Request baseRequest) throws IOException, ServletException {
      String target = baseRequest.getPathInfo();
      HttpServletRequest request = Request.unwrap(event.getSuppliedRequest());
      HttpServletResponse response = Response.unwrap(event.getSuppliedResponse());
      if (LOG.isDebugEnabled()) {
         LOG.debug("{} {} {} on {}", new Object[]{request.getDispatcherType(), request.getMethod(), target, channel});
      }

      this.handle(target, baseRequest, request, response);
      if (LOG.isDebugEnabled()) {
         LOG.debug("handledAsync={} async={} committed={} on {}", new Object[]{channel.getRequest().isHandled(), request.isAsyncStarted(), response.isCommitted(), channel});
      }

   }

   public void join() throws InterruptedException {
      this.getThreadPool().join();
   }

   public SessionIdManager getSessionIdManager() {
      return this._sessionIdManager;
   }

   public void setSessionIdManager(SessionIdManager sessionIdManager) {
      this.updateBean(this._sessionIdManager, sessionIdManager);
      this._sessionIdManager = sessionIdManager;
   }

   public void clearAttributes() {
      this._attributes.clearAttributes();
   }

   public Object getAttribute(String name) {
      return this._attributes.getAttribute(name);
   }

   public Enumeration getAttributeNames() {
      return this._attributes.getAttributeNames();
   }

   public Set getAttributeNameSet() {
      return this._attributes.getAttributeNameSet();
   }

   public void removeAttribute(String name) {
      this._attributes.removeAttribute(name);
   }

   public void setAttribute(String name, Object attribute) {
      this._attributes.setAttribute(name, attribute);
   }

   public URI getURI() {
      NetworkConnector connector = null;

      for(Connector c : this._connectors) {
         if (c instanceof NetworkConnector) {
            connector = (NetworkConnector)c;
            break;
         }
      }

      if (connector == null) {
         return null;
      } else {
         ContextHandler context = (ContextHandler)this.getChildHandlerByClass(ContextHandler.class);

         try {
            String protocol = connector.getDefaultConnectionFactory().getProtocol();
            String scheme = "http";
            if (protocol.startsWith("SSL-") || protocol.equals("SSL")) {
               scheme = "https";
            }

            String host = connector.getHost();
            if (context != null && context.getVirtualHosts() != null && context.getVirtualHosts().length > 0) {
               host = context.getVirtualHosts()[0];
            }

            if (host == null) {
               host = InetAddress.getLocalHost().getHostAddress();
            }

            String path = context == null ? null : context.getContextPath();
            if (path == null) {
               path = "/";
            }

            return new URI(scheme, (String)null, host, connector.getLocalPort(), path, (String)null, (String)null);
         } catch (Exception e) {
            LOG.warn("Unable to build server URI", e);
            return null;
         }
      }
   }

   public String toString() {
      return String.format("%s[%s,sto=%d]", super.toString(), getVersion(), this.getStopTimeout());
   }

   public void dump(Appendable out, String indent) throws IOException {
      this.dumpObjects(out, indent, new Object[]{new ClassLoaderDump(this.getClass().getClassLoader())});
   }

   public static void main(String... args) throws Exception {
      System.err.println(getVersion());
   }

   private static class DateField {
      final long _seconds;
      final HttpField _dateField;

      public DateField(long seconds, HttpField dateField) {
         this._seconds = seconds;
         this._dateField = dateField;
      }
   }
}
