package org.apache.spark.ui;

import jakarta.servlet.DispatcherType;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.lang.invoke.SerializedLambda;
import java.net.URI;
import java.net.URL;
import java.net.URLDecoder;
import java.util.EnumSet;
import java.util.Map;
import java.util.concurrent.Executor;
import org.apache.spark.SSLOptions;
import org.apache.spark.SparkConf;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.config.ConfigEntry;
import org.apache.spark.internal.config.UI$;
import org.apache.spark.util.Utils$;
import org.json4s.jackson.JsonMethods.;
import org.slf4j.Logger;
import org.sparkproject.jetty.client.HttpClient;
import org.sparkproject.jetty.client.api.Response;
import org.sparkproject.jetty.client.http.HttpClientTransportOverHTTP;
import org.sparkproject.jetty.io.ByteBufferPool;
import org.sparkproject.jetty.proxy.ProxyServlet;
import org.sparkproject.jetty.server.AbstractConnectionFactory;
import org.sparkproject.jetty.server.ConnectionFactory;
import org.sparkproject.jetty.server.HttpConfiguration;
import org.sparkproject.jetty.server.HttpConnectionFactory;
import org.sparkproject.jetty.server.Request;
import org.sparkproject.jetty.server.SecureRequestCustomizer;
import org.sparkproject.jetty.server.Server;
import org.sparkproject.jetty.server.ServerConnector;
import org.sparkproject.jetty.server.handler.AbstractHandler;
import org.sparkproject.jetty.server.handler.ContextHandler;
import org.sparkproject.jetty.server.handler.ContextHandlerCollection;
import org.sparkproject.jetty.server.handler.ErrorHandler;
import org.sparkproject.jetty.servlet.DefaultServlet;
import org.sparkproject.jetty.servlet.FilterHolder;
import org.sparkproject.jetty.servlet.ServletContextHandler;
import org.sparkproject.jetty.servlet.ServletHolder;
import org.sparkproject.jetty.util.ssl.SslContextFactory;
import org.sparkproject.jetty.util.thread.QueuedThreadPool;
import org.sparkproject.jetty.util.thread.ScheduledExecutorScheduler;
import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.collection.mutable.StringBuilder;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.java8.JFunction0;

public final class JettyUtils$ implements Logging {
   public static final JettyUtils$ MODULE$ = new JettyUtils$();
   private static final String SPARK_CONNECTOR_NAME;
   private static final String REDIRECT_CONNECTOR_NAME;
   private static transient Logger org$apache$spark$internal$Logging$$log_;

   static {
      Logging.$init$(MODULE$);
      SPARK_CONNECTOR_NAME = "Spark";
      REDIRECT_CONNECTOR_NAME = "HttpsRedirect";
   }

   public String logName() {
      return Logging.logName$(this);
   }

   public Logger log() {
      return Logging.log$(this);
   }

   public Logging.LogStringContext LogStringContext(final StringContext sc) {
      return Logging.LogStringContext$(this, sc);
   }

   public void withLogContext(final Map context, final Function0 body) {
      Logging.withLogContext$(this, context, body);
   }

   public void logInfo(final Function0 msg) {
      Logging.logInfo$(this, msg);
   }

   public void logInfo(final LogEntry entry) {
      Logging.logInfo$(this, entry);
   }

   public void logInfo(final LogEntry entry, final Throwable throwable) {
      Logging.logInfo$(this, entry, throwable);
   }

   public void logDebug(final Function0 msg) {
      Logging.logDebug$(this, msg);
   }

   public void logDebug(final LogEntry entry) {
      Logging.logDebug$(this, entry);
   }

   public void logDebug(final LogEntry entry, final Throwable throwable) {
      Logging.logDebug$(this, entry, throwable);
   }

   public void logTrace(final Function0 msg) {
      Logging.logTrace$(this, msg);
   }

   public void logTrace(final LogEntry entry) {
      Logging.logTrace$(this, entry);
   }

   public void logTrace(final LogEntry entry, final Throwable throwable) {
      Logging.logTrace$(this, entry, throwable);
   }

   public void logWarning(final Function0 msg) {
      Logging.logWarning$(this, msg);
   }

   public void logWarning(final LogEntry entry) {
      Logging.logWarning$(this, entry);
   }

   public void logWarning(final LogEntry entry, final Throwable throwable) {
      Logging.logWarning$(this, entry, throwable);
   }

   public void logError(final Function0 msg) {
      Logging.logError$(this, msg);
   }

   public void logError(final LogEntry entry) {
      Logging.logError$(this, entry);
   }

   public void logError(final LogEntry entry, final Throwable throwable) {
      Logging.logError$(this, entry, throwable);
   }

   public void logInfo(final Function0 msg, final Throwable throwable) {
      Logging.logInfo$(this, msg, throwable);
   }

   public void logDebug(final Function0 msg, final Throwable throwable) {
      Logging.logDebug$(this, msg, throwable);
   }

   public void logTrace(final Function0 msg, final Throwable throwable) {
      Logging.logTrace$(this, msg, throwable);
   }

   public void logWarning(final Function0 msg, final Throwable throwable) {
      Logging.logWarning$(this, msg, throwable);
   }

   public void logError(final Function0 msg, final Throwable throwable) {
      Logging.logError$(this, msg, throwable);
   }

   public boolean isTraceEnabled() {
      return Logging.isTraceEnabled$(this);
   }

   public void initializeLogIfNecessary(final boolean isInterpreter) {
      Logging.initializeLogIfNecessary$(this, isInterpreter);
   }

   public boolean initializeLogIfNecessary(final boolean isInterpreter, final boolean silent) {
      return Logging.initializeLogIfNecessary$(this, isInterpreter, silent);
   }

   public boolean initializeLogIfNecessary$default$2() {
      return Logging.initializeLogIfNecessary$default$2$(this);
   }

   public void initializeForcefully(final boolean isInterpreter, final boolean silent) {
      Logging.initializeForcefully$(this, isInterpreter, silent);
   }

   public Logger org$apache$spark$internal$Logging$$log_() {
      return org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      org$apache$spark$internal$Logging$$log_ = x$1;
   }

   public String SPARK_CONNECTOR_NAME() {
      return SPARK_CONNECTOR_NAME;
   }

   public String REDIRECT_CONNECTOR_NAME() {
      return REDIRECT_CONNECTOR_NAME;
   }

   public JettyUtils.ServletParams jsonResponderToServlet(final Function1 responder) {
      return new JettyUtils.ServletParams(responder, "text/json", (in) -> .MODULE$.pretty(.MODULE$.render(in, .MODULE$.render$default$2(), .MODULE$.render$default$3())));
   }

   public JettyUtils.ServletParams htmlResponderToServlet(final Function1 responder) {
      return new JettyUtils.ServletParams(responder, "text/html", (in) -> "<!DOCTYPE html>" + in.toString());
   }

   public JettyUtils.ServletParams textResponderToServlet(final Function1 responder) {
      return new JettyUtils.ServletParams(responder, "text/plain", JettyUtils.ServletParams$.MODULE$.$lessinit$greater$default$3());
   }

   private HttpServlet createServlet(final JettyUtils.ServletParams servletParams, final SparkConf conf) {
      return new HttpServlet(servletParams) {
         private final JettyUtils.ServletParams servletParams$1;

         public void doGet(final HttpServletRequest request, final HttpServletResponse response) {
            try {
               response.setContentType(scala.collection.StringOps..MODULE$.format$extension(scala.Predef..MODULE$.augmentString("%s;charset=utf-8"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{this.servletParams$1.contentType()})));
               response.setStatus(200);
               Object result = this.servletParams$1.responder().apply(request);
               response.getWriter().print((String)this.servletParams$1.extractFn().apply(result));
            } catch (IllegalArgumentException var6) {
               response.sendError(400, var6.getMessage());
            } catch (Exception var7) {
               JettyUtils$.MODULE$.logWarning((LogEntry)org.apache.spark.internal.LogEntry..MODULE$.from(() -> JettyUtils$.MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"GET ", " failed: "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.URI..MODULE$, request.getRequestURI())}))).$plus(JettyUtils$.MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.ERROR..MODULE$, var7)}))))), var7);
               throw var7;
            }

         }

         public void doTrace(final HttpServletRequest req, final HttpServletResponse res) {
            res.sendError(405);
         }

         public {
            this.servletParams$1 = servletParams$1;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   public ServletContextHandler createServletHandler(final String path, final JettyUtils.ServletParams servletParams, final SparkConf conf, final String basePath) {
      return this.createServletHandler(path, this.createServlet(servletParams, conf), basePath);
   }

   public ServletContextHandler createServletHandler(final String path, final HttpServlet servlet, final String basePath) {
      String var10000;
      label26: {
         label25: {
            label24: {
               String var5 = "";
               if (basePath == null) {
                  if (var5 != null) {
                     break label24;
                  }
               } else if (!basePath.equals(var5)) {
                  break label24;
               }

               String var6 = "/";
               if (path == null) {
                  if (var6 == null) {
                     break label25;
                  }
               } else if (path.equals(var6)) {
                  break label25;
               }
            }

            var10000 = scala.collection.StringOps..MODULE$.stripSuffix$extension(scala.Predef..MODULE$.augmentString(basePath + path), "/");
            break label26;
         }

         var10000 = path;
      }

      String prefixedPath = var10000;
      ServletContextHandler contextHandler = new ServletContextHandler();
      ServletHolder holder = new ServletHolder(servlet);
      contextHandler.setContextPath(prefixedPath);
      contextHandler.addServlet(holder, "/");
      return contextHandler;
   }

   public String createServletHandler$default$4() {
      return "";
   }

   public ServletContextHandler createRedirectHandler(final String srcPath, final String destPath, final Function1 beforeRedirect, final String basePath, final Set httpMethods) {
      String prefixedDestPath = basePath + destPath;
      HttpServlet servlet = new HttpServlet(httpMethods, beforeRedirect, prefixedDestPath) {
         private final Set httpMethods$1;
         private final Function1 beforeRedirect$1;
         private final String prefixedDestPath$1;

         public void doGet(final HttpServletRequest request, final HttpServletResponse response) {
            if (this.httpMethods$1.contains("GET")) {
               this.doRequest(request, response);
            } else {
               response.sendError(405);
            }
         }

         public void doPost(final HttpServletRequest request, final HttpServletResponse response) {
            if (this.httpMethods$1.contains("POST")) {
               this.doRequest(request, response);
            } else {
               response.sendError(405);
            }
         }

         private void doRequest(final HttpServletRequest request, final HttpServletResponse response) {
            this.beforeRedirect$1.apply(request);
            URL requestURL = (new URI(request.getRequestURL().toString())).toURL();
            String newUrl = (new URL(requestURL, this.prefixedDestPath$1)).toString();
            response.sendRedirect(newUrl);
         }

         public void doTrace(final HttpServletRequest req, final HttpServletResponse res) {
            res.sendError(405);
         }

         public {
            this.httpMethods$1 = httpMethods$1;
            this.beforeRedirect$1 = beforeRedirect$1;
            this.prefixedDestPath$1 = prefixedDestPath$1;
         }
      };
      return this.createServletHandler(srcPath, servlet, basePath);
   }

   public Function1 createRedirectHandler$default$3() {
      return (x) -> {
         $anonfun$createRedirectHandler$default$3$1(x);
         return BoxedUnit.UNIT;
      };
   }

   public String createRedirectHandler$default$4() {
      return "";
   }

   public Set createRedirectHandler$default$5() {
      return (Set)scala.Predef..MODULE$.Set().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"GET"})));
   }

   public ServletContextHandler createStaticHandler(final String resourceBase, final String path) {
      ServletContextHandler contextHandler = new ServletContextHandler();
      contextHandler.setInitParameter("org.sparkproject.jetty.servlet.Default.gzip", "false");
      DefaultServlet staticHandler = new DefaultServlet();
      ServletHolder holder = new ServletHolder(staticHandler);
      Option var7 = scala.Option..MODULE$.apply(Utils$.MODULE$.getSparkClassLoader().getResource(resourceBase));
      if (var7 instanceof Some var8) {
         URL res = (URL)var8.value();
         holder.setInitParameter("resourceBase", res.toString());
         BoxedUnit var10000 = BoxedUnit.UNIT;
         contextHandler.setContextPath(path);
         contextHandler.addServlet(holder, "/");
         return contextHandler;
      } else if (scala.None..MODULE$.equals(var7)) {
         throw new Exception("Could not find resource path for Web UI: " + resourceBase);
      } else {
         throw new MatchError(var7);
      }
   }

   public ServletContextHandler createProxyHandler(final Function1 idToUiAddress) {
      ProxyServlet servlet = new ProxyServlet(idToUiAddress) {
         private final Function1 idToUiAddress$1;

         public String rewriteTarget(final HttpServletRequest request) {
            String path = request.getPathInfo();
            if (path == null) {
               return null;
            } else {
               int prefixTrailingSlashIndex = path.indexOf(47, 1);
               String prefix = prefixTrailingSlashIndex == -1 ? path : path.substring(0, prefixTrailingSlashIndex);
               String id = scala.collection.StringOps..MODULE$.drop$extension(scala.Predef..MODULE$.augmentString(prefix), 1);
               return (String)((Option)this.idToUiAddress$1.apply(id)).map((x$1) -> JettyUtils$.MODULE$.createProxyURI(prefix, x$1, path, request.getQueryString())).filter((uri) -> BoxesRunTime.boxToBoolean($anonfun$rewriteTarget$2(this, uri))).map((x$2) -> x$2.toString()).orNull(scala..less.colon.less..MODULE$.refl());
            }
         }

         public HttpClient newHttpClient() {
            int numSelectors = scala.math.package..MODULE$.max(1, scala.math.package..MODULE$.min(8, Runtime.getRuntime().availableProcessors() / 2));
            return new HttpClient(new HttpClientTransportOverHTTP(numSelectors));
         }

         public String filterServerResponseHeader(final HttpServletRequest clientRequest, final Response serverResponse, final String headerName, final String headerValue) {
            if (headerName.equalsIgnoreCase("location")) {
               String newHeader = JettyUtils$.MODULE$.createProxyLocationHeader(headerValue, clientRequest, serverResponse.getRequest().getURI());
               if (newHeader != null) {
                  return newHeader;
               }
            }

            return super.filterServerResponseHeader(clientRequest, serverResponse, headerName, headerValue);
         }

         // $FF: synthetic method
         public static final boolean $anonfun$rewriteTarget$2(final Object $this, final URI uri) {
            return uri != null && $this.validateDestination(uri.getHost(), uri.getPort());
         }

         public {
            this.idToUiAddress$1 = idToUiAddress$1;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
      ServletContextHandler contextHandler = new ServletContextHandler();
      ServletHolder holder = new ServletHolder(servlet);
      contextHandler.setContextPath("/proxy");
      contextHandler.addServlet(holder, "/*");
      return contextHandler;
   }

   public ServerInfo startJettyServer(final String hostName, final int port, final SSLOptions sslOptions, final SparkConf conf, final String serverName, final int poolSize) {
      long stopTimeout = BoxesRunTime.unboxToLong(conf.get(UI$.MODULE$.UI_JETTY_STOP_TIMEOUT()));
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Start Jetty ", ":", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.HOST..MODULE$, hostName), new MDC(org.apache.spark.internal.LogKeys.PORT..MODULE$, BoxesRunTime.boxToInteger(port))}))).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" for ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SERVER_NAME..MODULE$, serverName)}))))));
      QueuedThreadPool pool = new QueuedThreadPool(poolSize);
      if (scala.collection.StringOps..MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString(serverName))) {
         pool.setName(serverName);
      }

      pool.setDaemon(true);
      Server server = new Server(pool);
      ErrorHandler errorHandler = new ErrorHandler();
      errorHandler.setShowStacks(true);
      errorHandler.setServer(server);
      server.addBean(errorHandler);
      ContextHandlerCollection collection = new ContextHandlerCollection();
      Option var16 = (Option)conf.get((ConfigEntry)UI$.MODULE$.PROXY_REDIRECT_URI());
      if (var16 instanceof Some var17) {
         String proxyUri = (String)var17.value();
         ProxyRedirectHandler proxyHandler = new ProxyRedirectHandler(proxyUri);
         proxyHandler.setHandler(collection);
         server.setHandler(proxyHandler);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         server.setHandler(collection);
         BoxedUnit var37 = BoxedUnit.UNIT;
      }

      ScheduledExecutorScheduler serverExecutor = new ScheduledExecutorScheduler(serverName + "-JettyScheduler", true);

      try {
         server.setStopTimeout(stopTimeout);
         server.start();
         IntRef minThreads = IntRef.create(1);
         HttpConfiguration httpConfig = new HttpConfiguration();
         int requestHeaderSize = (int)BoxesRunTime.unboxToLong(conf.get(UI$.MODULE$.UI_REQUEST_HEADER_SIZE()));
         this.logDebug((Function0)(() -> "Using requestHeaderSize: " + requestHeaderSize));
         httpConfig.setRequestHeaderSize(requestHeaderSize);
         this.logDebug((Function0)(() -> "Using setSendServerVersion: false"));
         httpConfig.setSendServerVersion(false);
         this.logDebug((Function0)(() -> "Using setSendXPoweredBy: false"));
         httpConfig.setSendXPoweredBy(false);
         Option securePort = sslOptions.createJettySslContextFactoryServer().map((factory) -> BoxesRunTime.boxToInteger($anonfun$startJettyServer$5(httpConfig, sslOptions, port, serverName, conf, server, serverExecutor, hostName, minThreads, factory)));
         Tuple2 var26 = Utils$.MODULE$.startServiceOnPort(port, (currentPort) -> $anonfun$startJettyServer$8(httpConfig, server, serverExecutor, hostName, minThreads, BoxesRunTime.unboxToInt(currentPort)), conf, serverName);
         if (var26 != null) {
            ServerConnector httpConnector = (ServerConnector)var26._1();
            int httpPort = var26._2$mcI$sp();
            Tuple2 var25 = new Tuple2(httpConnector, BoxesRunTime.boxToInteger(httpPort));
            ServerConnector httpConnector = (ServerConnector)var25._1();
            int httpPort = var25._2$mcI$sp();
            if (securePort instanceof Some) {
               Some var32 = (Some)securePort;
               int p = BoxesRunTime.unboxToInt(var32.value());
               httpConnector.setName(this.REDIRECT_CONNECTOR_NAME());
               ContextHandler redirector = this.createRedirectHttpsHandler(p, "https");
               collection.addHandler(redirector);
               redirector.start();
               BoxedUnit var38 = BoxedUnit.UNIT;
            } else {
               if (!scala.None..MODULE$.equals(securePort)) {
                  throw new MatchError(securePort);
               }

               httpConnector.setName(this.SPARK_CONNECTOR_NAME());
               BoxedUnit var39 = BoxedUnit.UNIT;
            }

            server.addConnector(httpConnector);
            pool.setMaxThreads(scala.math.package..MODULE$.max(pool.getMaxThreads(), minThreads.elem));
            return new ServerInfo(server, httpPort, securePort, conf, collection);
         } else {
            throw new MatchError(var26);
         }
      } catch (Exception var36) {
         server.stop();
         if (serverExecutor.isStarted()) {
            serverExecutor.stop();
         }

         if (pool.isStarted()) {
            pool.stop();
         }

         throw var36;
      }
   }

   public String startJettyServer$default$5() {
      return "";
   }

   public int startJettyServer$default$6() {
      return 200;
   }

   private ContextHandler createRedirectHttpsHandler(final int securePort, final String scheme) {
      ContextHandler redirectHandler = new ContextHandler();
      redirectHandler.setContextPath("/");
      redirectHandler.setVirtualHosts(this.toVirtualHosts(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{this.REDIRECT_CONNECTOR_NAME()}))));
      redirectHandler.setHandler(new AbstractHandler(scheme, securePort) {
         private final String scheme$1;
         private final int securePort$1;

         public void handle(final String target, final Request baseRequest, final HttpServletRequest request, final HttpServletResponse response) {
            if (!baseRequest.isSecure()) {
               String httpsURI = JettyUtils$.MODULE$.org$apache$spark$ui$JettyUtils$$createRedirectURI(this.scheme$1, this.securePort$1, baseRequest);
               response.setContentLength(0);
               response.sendRedirect(response.encodeRedirectURL(httpsURI));
               baseRequest.setHandled(true);
            }
         }

         public {
            this.scheme$1 = scheme$1;
            this.securePort$1 = securePort$1;
         }
      });
      return redirectHandler;
   }

   public URI createProxyURI(final String prefix, final String target, final String path, final String query) {
      if (!path.startsWith(prefix)) {
         return null;
      } else {
         StringBuilder uri = new StringBuilder(target);
         String rest = path.substring(prefix.length());
         if (!rest.isEmpty()) {
            if (!rest.startsWith("/") && !uri.endsWith(scala.Predef..MODULE$.wrapString("/"))) {
               uri.append("/");
            } else {
               BoxedUnit var10000 = BoxedUnit.UNIT;
            }

            uri.append(rest);
         } else {
            BoxedUnit var8 = BoxedUnit.UNIT;
         }

         String queryString = query == null ? "" : "?" + query;
         String var9 = uri.toString();
         return URI.create(var9 + queryString).normalize();
      }
   }

   public String createProxyLocationHeader(final String headerValue, final HttpServletRequest clientRequest, final URI targetUri) {
      String var10000 = targetUri.getScheme();
      String toReplace = var10000 + "://" + targetUri.getAuthority();
      if (headerValue.startsWith(toReplace)) {
         String id = scala.collection.StringOps..MODULE$.takeWhile$extension(scala.Predef..MODULE$.augmentString(clientRequest.getPathInfo().substring("/proxy/".length())), (x$5) -> BoxesRunTime.boxToBoolean($anonfun$createProxyLocationHeader$1(BoxesRunTime.unboxToChar(x$5))));
         String headerPath = headerValue.substring(toReplace.length());
         var10000 = clientRequest.getScheme();
         return var10000 + "://" + clientRequest.getHeader("host") + "/proxy/" + id + headerPath;
      } else {
         return null;
      }
   }

   public void addFilter(final ServletContextHandler handler, final String filter, final scala.collection.immutable.Map params) {
      FilterHolder holder = new FilterHolder();
      holder.setClassName(filter);
      params.foreach((x0$1) -> {
         $anonfun$addFilter$1(holder, x0$1);
         return BoxedUnit.UNIT;
      });
      handler.addFilter(holder, "/*", EnumSet.allOf(DispatcherType.class));
   }

   private String decodeURL(final String url, final String encoding) {
      return url == null ? null : URLDecoder.decode(url, encoding);
   }

   public String org$apache$spark$ui$JettyUtils$$createRedirectURI(final String scheme, final int port, final Request request) {
      String server = request.getServerName();
      String redirectServer = server.contains(":") && !server.startsWith("[") ? "[" + server + "]" : server;
      String authority = redirectServer + ":" + port;
      String queryEncoding = request.getQueryEncoding() != null ? request.getQueryEncoding() : "UTF-8";
      String requestURI = this.decodeURL(request.getRequestURI(), queryEncoding);
      String queryString = this.decodeURL(request.getQueryString(), queryEncoding);
      return (new URI(scheme, authority, requestURI, queryString, (String)null)).toString();
   }

   public String[] toVirtualHosts(final Seq connectors) {
      return (String[])((IterableOnceOps)connectors.map((x$6) -> "@" + x$6)).toArray(scala.reflect.ClassTag..MODULE$.apply(String.class));
   }

   // $FF: synthetic method
   public static final void $anonfun$createRedirectHandler$default$3$1(final HttpServletRequest x) {
   }

   private static final Tuple2 newConnector$1(final ConnectionFactory[] connectionFactories, final int port, final Server server$1, final ScheduledExecutorScheduler serverExecutor$1, final String hostName$1, final IntRef minThreads$1) {
      ServerConnector connector = new ServerConnector(server$1, (Executor)null, serverExecutor$1, (ByteBufferPool)null, -1, -1, connectionFactories);
      connector.setPort(port);
      connector.setHost(hostName$1);
      connector.setReuseAddress(!Utils$.MODULE$.isWindows());
      connector.setIdleTimeout(8000L);
      connector.setAcceptQueueSize(scala.math.package..MODULE$.min(connector.getAcceptors(), 8));
      connector.start();
      minThreads$1.elem += connector.getAcceptors() * 2;
      return new Tuple2(connector, BoxesRunTime.boxToInteger(connector.getLocalPort()));
   }

   private static final Tuple2 sslConnect$1(final int currentPort, final ConnectionFactory[] connectionFactories$1, final Server server$1, final ScheduledExecutorScheduler serverExecutor$1, final String hostName$1, final IntRef minThreads$1) {
      return newConnector$1(connectionFactories$1, currentPort, server$1, serverExecutor$1, hostName$1, minThreads$1);
   }

   // $FF: synthetic method
   public static final Tuple2 $anonfun$startJettyServer$7(final ConnectionFactory[] connectionFactories$1, final Server server$1, final ScheduledExecutorScheduler serverExecutor$1, final String hostName$1, final IntRef minThreads$1, final int currentPort) {
      return sslConnect$1(currentPort, connectionFactories$1, server$1, serverExecutor$1, hostName$1, minThreads$1);
   }

   // $FF: synthetic method
   public static final int $anonfun$startJettyServer$5(final HttpConfiguration httpConfig$1, final SSLOptions sslOptions$1, final int port$1, final String serverName$1, final SparkConf conf$1, final Server server$1, final ScheduledExecutorScheduler serverExecutor$1, final String hostName$1, final IntRef minThreads$1, final SslContextFactory.Server factory) {
      SecureRequestCustomizer src = new SecureRequestCustomizer();
      src.setSniHostCheck(false);
      httpConfig$1.addCustomizer(src);
      int securePort = BoxesRunTime.unboxToInt(sslOptions$1.port().getOrElse((JFunction0.mcI.sp)() -> port$1 > 0 ? Utils$.MODULE$.userPort(port$1, 400) : 0));
      String secureServerName = scala.collection.StringOps..MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString(serverName$1)) ? serverName$1 + " (HTTPS)" : serverName$1;
      ConnectionFactory[] connectionFactories = AbstractConnectionFactory.getFactories(factory, new HttpConnectionFactory(httpConfig$1));
      Tuple2 var16 = Utils$.MODULE$.startServiceOnPort(securePort, (currentPort) -> $anonfun$startJettyServer$7(connectionFactories, server$1, serverExecutor$1, hostName$1, minThreads$1, BoxesRunTime.unboxToInt(currentPort)), conf$1, secureServerName);
      if (var16 != null) {
         ServerConnector connector = (ServerConnector)var16._1();
         int boundPort = var16._2$mcI$sp();
         Tuple2 var15 = new Tuple2(connector, BoxesRunTime.boxToInteger(boundPort));
         ServerConnector connector = (ServerConnector)var15._1();
         int boundPort = var15._2$mcI$sp();
         connector.setName(MODULE$.SPARK_CONNECTOR_NAME());
         server$1.addConnector(connector);
         return boundPort;
      } else {
         throw new MatchError(var16);
      }
   }

   private static final Tuple2 httpConnect$1(final int currentPort, final HttpConfiguration httpConfig$1, final Server server$1, final ScheduledExecutorScheduler serverExecutor$1, final String hostName$1, final IntRef minThreads$1) {
      return newConnector$1((ConnectionFactory[])(new ConnectionFactory[]{new HttpConnectionFactory(httpConfig$1)}), currentPort, server$1, serverExecutor$1, hostName$1, minThreads$1);
   }

   // $FF: synthetic method
   public static final Tuple2 $anonfun$startJettyServer$8(final HttpConfiguration httpConfig$1, final Server server$1, final ScheduledExecutorScheduler serverExecutor$1, final String hostName$1, final IntRef minThreads$1, final int currentPort) {
      return httpConnect$1(currentPort, httpConfig$1, server$1, serverExecutor$1, hostName$1, minThreads$1);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$createProxyLocationHeader$1(final char x$5) {
      return x$5 != '/';
   }

   // $FF: synthetic method
   public static final void $anonfun$addFilter$1(final FilterHolder holder$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         String k = (String)x0$1._1();
         String v = (String)x0$1._2();
         holder$1.setInitParameter(k, v);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$1);
      }
   }

   private JettyUtils$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
