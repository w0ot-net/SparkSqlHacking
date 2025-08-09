package org.sparkproject.jetty.proxy;

import jakarta.servlet.AsyncContext;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.UnavailableException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.net.InetAddress;
import java.net.URI;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.client.ContinueProtocolHandler;
import org.sparkproject.jetty.client.HttpClient;
import org.sparkproject.jetty.client.ProtocolHandlers;
import org.sparkproject.jetty.client.api.Request;
import org.sparkproject.jetty.client.api.Response;
import org.sparkproject.jetty.client.dynamic.HttpClientTransportDynamic;
import org.sparkproject.jetty.http.HttpField;
import org.sparkproject.jetty.http.HttpFields;
import org.sparkproject.jetty.http.HttpHeader;
import org.sparkproject.jetty.http.HttpHeaderValue;
import org.sparkproject.jetty.http.HttpScheme;
import org.sparkproject.jetty.io.ClientConnectionFactory;
import org.sparkproject.jetty.io.ClientConnector;
import org.sparkproject.jetty.util.HttpCookieStore;
import org.sparkproject.jetty.util.StringUtil;
import org.sparkproject.jetty.util.component.LifeCycle;
import org.sparkproject.jetty.util.ssl.SslContextFactory;
import org.sparkproject.jetty.util.thread.QueuedThreadPool;

public abstract class AbstractProxyServlet extends HttpServlet {
   protected static final String CLIENT_REQUEST_ATTRIBUTE = "org.sparkproject.jetty.proxy.clientRequest";
   protected static final Set HOP_HEADERS = Set.of("connection", "keep-alive", "proxy-authorization", "proxy-authenticate", "proxy-connection", "transfer-encoding", "te", "trailer", "upgrade");
   private final Set _whiteList = new HashSet();
   private final Set _blackList = new HashSet();
   protected Logger _log;
   private boolean _preserveHost;
   private String _hostHeader;
   private String _viaHost;
   private HttpClient _client;
   private long _timeout;

   public void init() throws ServletException {
      this._log = this.createLogger();
      ServletConfig config = this.getServletConfig();
      this._preserveHost = Boolean.parseBoolean(config.getInitParameter("preserveHost"));
      this._hostHeader = config.getInitParameter("hostHeader");
      this._viaHost = config.getInitParameter("viaHost");
      if (this._viaHost == null) {
         this._viaHost = viaHost();
      }

      try {
         this._client = this.createHttpClient();
         this.getServletContext().setAttribute(config.getServletName() + ".HttpClient", this._client);
         String whiteList = config.getInitParameter("whiteList");
         if (whiteList != null) {
            this.getWhiteListHosts().addAll(this.parseList(whiteList));
         }

         String blackList = config.getInitParameter("blackList");
         if (blackList != null) {
            this.getBlackListHosts().addAll(this.parseList(blackList));
         }

      } catch (Exception e) {
         throw new ServletException(e);
      }
   }

   public void destroy() {
      try {
         LifeCycle.stop(this._client);
      } catch (Exception x) {
         if (this._log == null) {
            x.printStackTrace();
         } else if (this._log.isDebugEnabled()) {
            this._log.debug("Failed to stop client", x);
         }
      }

   }

   public String getHostHeader() {
      return this._hostHeader;
   }

   public String getViaHost() {
      return this._viaHost;
   }

   private static String viaHost() {
      try {
         return InetAddress.getLocalHost().getHostName();
      } catch (UnknownHostException var1) {
         return "localhost";
      }
   }

   public long getTimeout() {
      return this._timeout;
   }

   public void setTimeout(long timeout) {
      this._timeout = timeout;
   }

   public Set getWhiteListHosts() {
      return this._whiteList;
   }

   public Set getBlackListHosts() {
      return this._blackList;
   }

   protected Logger createLogger() {
      String servletName = this.getServletConfig().getServletName();
      servletName = StringUtil.replace(servletName, '-', '.');
      if (this.getClass().getPackage() != null && !servletName.startsWith(this.getClass().getPackage().getName())) {
         String var10000 = this.getClass().getName();
         servletName = var10000 + "." + servletName;
      }

      return LoggerFactory.getLogger(servletName);
   }

   protected HttpClient createHttpClient() throws ServletException {
      ServletConfig config = this.getServletConfig();
      HttpClient client = this.newHttpClient();
      client.setFollowRedirects(false);
      client.setCookieStore(new HttpCookieStore.Empty());
      String value = config.getInitParameter("maxThreads");
      Executor executor;
      if (value != null && !"-".equals(value)) {
         QueuedThreadPool qtp = new QueuedThreadPool(Integer.parseInt(value));
         String servletName = config.getServletName();
         int dot = servletName.lastIndexOf(46);
         if (dot >= 0) {
            servletName = servletName.substring(dot + 1);
         }

         qtp.setName(servletName);
         executor = qtp;
      } else {
         executor = (Executor)this.getServletContext().getAttribute("org.sparkproject.jetty.server.Executor");
         if (executor == null) {
            throw new IllegalStateException("No server executor for proxy");
         }
      }

      client.setExecutor(executor);
      value = config.getInitParameter("maxConnections");
      if (value == null) {
         value = "256";
      }

      client.setMaxConnectionsPerDestination(Integer.parseInt(value));
      value = config.getInitParameter("idleTimeout");
      if (value == null) {
         value = "30000";
      }

      client.setIdleTimeout(Long.parseLong(value));
      value = config.getInitParameter("timeout");
      if (value == null) {
         value = "60000";
      }

      this._timeout = Long.parseLong(value);
      value = config.getInitParameter("requestBufferSize");
      if (value != null) {
         client.setRequestBufferSize(Integer.parseInt(value));
      }

      value = config.getInitParameter("responseBufferSize");
      if (value != null) {
         client.setResponseBufferSize(Integer.parseInt(value));
      }

      try {
         client.start();
         client.getContentDecoderFactories().clear();
         ProtocolHandlers protocolHandlers = client.getProtocolHandlers();
         protocolHandlers.clear();
         protocolHandlers.put(new ProxyContinueProtocolHandler());
         return client;
      } catch (Exception x) {
         throw new ServletException(x);
      }
   }

   protected HttpClient newHttpClient() {
      int selectors = 1;
      String value = this.getServletConfig().getInitParameter("selectors");
      if (value != null) {
         selectors = Integer.parseInt(value);
      }

      ClientConnector clientConnector = this.newClientConnector();
      clientConnector.setSelectors(selectors);
      return this.newHttpClient(clientConnector);
   }

   protected HttpClient newHttpClient(ClientConnector clientConnector) {
      return new HttpClient(new HttpClientTransportDynamic(clientConnector, new ClientConnectionFactory.Info[0]));
   }

   protected ClientConnector newClientConnector() {
      ClientConnector clientConnector = new ClientConnector();
      clientConnector.setSslContextFactory(new SslContextFactory.Client());
      return clientConnector;
   }

   protected HttpClient getHttpClient() {
      return this._client;
   }

   private Set parseList(String list) {
      Set<String> result = new HashSet();
      String[] hosts = list.split(",");

      for(String host : hosts) {
         host = host.trim();
         if (host.length() != 0) {
            result.add(host);
         }
      }

      return result;
   }

   public boolean validateDestination(String host, int port) {
      String hostPort = host + ":" + port;
      if (!this._whiteList.isEmpty() && !this._whiteList.contains(hostPort)) {
         if (this._log.isDebugEnabled()) {
            this._log.debug("Host {}:{} not whitelisted", host, port);
         }

         return false;
      } else if (!this._blackList.isEmpty() && this._blackList.contains(hostPort)) {
         if (this._log.isDebugEnabled()) {
            this._log.debug("Host {}:{} blacklisted", host, port);
         }

         return false;
      } else {
         return true;
      }
   }

   protected String rewriteTarget(HttpServletRequest clientRequest) {
      if (!this.validateDestination(clientRequest.getServerName(), clientRequest.getServerPort())) {
         return null;
      } else {
         StringBuffer target = clientRequest.getRequestURL();
         if (HttpScheme.HTTPS.is(target.substring(0, 5))) {
            target.replace(4, 5, "");
         }

         String query = clientRequest.getQueryString();
         if (query != null) {
            target.append("?").append(query);
         }

         return target.toString();
      }
   }

   protected void onProxyRewriteFailed(HttpServletRequest clientRequest, HttpServletResponse proxyResponse) {
      this.sendProxyResponseError(clientRequest, proxyResponse, 403);
   }

   protected boolean hasContent(HttpServletRequest clientRequest) {
      return clientRequest.getContentLength() > 0 || clientRequest.getContentType() != null || clientRequest.getHeader(HttpHeader.TRANSFER_ENCODING.asString()) != null;
   }

   protected boolean expects100Continue(HttpServletRequest request) {
      return HttpHeaderValue.CONTINUE.is(request.getHeader(HttpHeader.EXPECT.asString()));
   }

   protected Request newProxyRequest(HttpServletRequest request, String rewrittenTarget) {
      return this.getHttpClient().newRequest(rewrittenTarget).method(request.getMethod()).attribute("org.sparkproject.jetty.proxy.clientRequest", request);
   }

   protected void copyRequestHeaders(HttpServletRequest clientRequest, Request proxyRequest) {
      HttpFields.Mutable newHeaders = HttpFields.build();
      Set<String> headersToRemove = this.findConnectionHeaders(clientRequest);
      Enumeration<String> headerNames = clientRequest.getHeaderNames();

      while(headerNames.hasMoreElements()) {
         String headerName = (String)headerNames.nextElement();
         String lowerHeaderName = headerName.toLowerCase(Locale.ENGLISH);
         if ((!HttpHeader.HOST.is(headerName) || this._preserveHost) && !HOP_HEADERS.contains(lowerHeaderName) && (headersToRemove == null || !headersToRemove.contains(lowerHeaderName))) {
            Enumeration<String> headerValues = clientRequest.getHeaders(headerName);

            while(headerValues.hasMoreElements()) {
               String headerValue = (String)headerValues.nextElement();
               if (headerValue != null) {
                  newHeaders.add(headerName, headerValue);
               }
            }
         }
      }

      if (this._hostHeader != null) {
         newHeaders.add(HttpHeader.HOST, this._hostHeader);
      }

      proxyRequest.headers((headers) -> headers.clear().add((HttpFields)newHeaders));
   }

   protected Set findConnectionHeaders(HttpServletRequest clientRequest) {
      Set<String> hopHeaders = null;
      Enumeration<String> connectionHeaders = clientRequest.getHeaders(HttpHeader.CONNECTION.asString());

      while(connectionHeaders.hasMoreElements()) {
         String value = (String)connectionHeaders.nextElement();
         String[] values = value.split(",");

         for(String name : values) {
            name = name.trim().toLowerCase(Locale.ENGLISH);
            if (hopHeaders == null) {
               hopHeaders = new HashSet();
            }

            hopHeaders.add(name);
         }
      }

      return hopHeaders;
   }

   protected void addProxyHeaders(HttpServletRequest clientRequest, Request proxyRequest) {
      this.addViaHeader(proxyRequest);
      this.addXForwardedHeaders(clientRequest, proxyRequest);
   }

   protected void addViaHeader(Request proxyRequest) {
      HttpServletRequest clientRequest = (HttpServletRequest)proxyRequest.getAttributes().get("org.sparkproject.jetty.proxy.clientRequest");
      this.addViaHeader(clientRequest, proxyRequest);
   }

   protected void addViaHeader(HttpServletRequest clientRequest, Request proxyRequest) {
      String protocol = clientRequest.getProtocol();
      String[] parts = protocol.split("/", 2);
      String protocolPart = parts.length == 2 && "HTTP".equalsIgnoreCase(parts[0]) ? parts[1] : protocol;
      String viaHeaderValue = protocolPart + " " + this.getViaHost();
      proxyRequest.headers((headers) -> headers.computeField((HttpHeader)HttpHeader.VIA, (header, viaFields) -> {
            if (viaFields != null && !viaFields.isEmpty()) {
               String separator = ", ";
               String newValue = (String)viaFields.stream().flatMap((field) -> Stream.of(field.getValues())).filter((value) -> !StringUtil.isBlank(value)).collect(Collectors.joining(separator));
               if (newValue.length() > 0) {
                  newValue = newValue + separator;
               }

               newValue = newValue + viaHeaderValue;
               return new HttpField(HttpHeader.VIA, newValue);
            } else {
               return new HttpField(header, viaHeaderValue);
            }
         }));
   }

   protected void addXForwardedHeaders(HttpServletRequest clientRequest, Request proxyRequest) {
      proxyRequest.headers((headers) -> headers.add(HttpHeader.X_FORWARDED_FOR, clientRequest.getRemoteAddr()));
      proxyRequest.headers((headers) -> headers.add(HttpHeader.X_FORWARDED_PROTO, clientRequest.getScheme()));
      String hostHeader = clientRequest.getHeader(HttpHeader.HOST.asString());
      if (hostHeader != null) {
         proxyRequest.headers((headers) -> headers.add(HttpHeader.X_FORWARDED_HOST, hostHeader));
      }

      String localName = clientRequest.getLocalName();
      if (localName != null) {
         proxyRequest.headers((headers) -> headers.add(HttpHeader.X_FORWARDED_SERVER, localName));
      }

   }

   protected void sendProxyRequest(HttpServletRequest clientRequest, HttpServletResponse proxyResponse, Request proxyRequest) {
      if (this._log.isDebugEnabled()) {
         StringBuilder builder = new StringBuilder(clientRequest.getMethod());
         builder.append(" ").append(clientRequest.getRequestURI());
         String query = clientRequest.getQueryString();
         if (query != null) {
            builder.append("?").append(query);
         }

         builder.append(" ").append(clientRequest.getProtocol()).append(System.lineSeparator());
         Enumeration<String> headerNames = clientRequest.getHeaderNames();

         while(headerNames.hasMoreElements()) {
            String headerName = (String)headerNames.nextElement();
            builder.append(headerName).append(": ");
            Enumeration<String> headerValues = clientRequest.getHeaders(headerName);

            while(headerValues.hasMoreElements()) {
               String headerValue = (String)headerValues.nextElement();
               if (headerValue != null) {
                  builder.append(headerValue);
               }

               if (headerValues.hasMoreElements()) {
                  builder.append(",");
               }
            }

            builder.append(System.lineSeparator());
         }

         builder.append(System.lineSeparator());
         this._log.debug("{} proxying to upstream:{}{}{}{}{}", new Object[]{this.getRequestId(clientRequest), System.lineSeparator(), builder, proxyRequest, System.lineSeparator(), proxyRequest.getHeaders().toString().trim()});
      }

      proxyRequest.send(this.newProxyResponseListener(clientRequest, proxyResponse));
   }

   protected abstract Response.CompleteListener newProxyResponseListener(HttpServletRequest var1, HttpServletResponse var2);

   protected void onClientRequestFailure(HttpServletRequest clientRequest, Request proxyRequest, HttpServletResponse proxyResponse, Throwable failure) {
      boolean aborted = proxyRequest.abort(failure);
      if (!aborted) {
         int status = this.clientRequestStatus(failure);
         this.sendProxyResponseError(clientRequest, proxyResponse, status);
      }

   }

   protected int clientRequestStatus(Throwable failure) {
      return failure instanceof TimeoutException ? 408 : 500;
   }

   protected void onServerResponseHeaders(HttpServletRequest clientRequest, HttpServletResponse proxyResponse, Response serverResponse) {
      for(HttpField field : serverResponse.getHeaders()) {
         String headerName = field.getName();
         String lowerHeaderName = headerName.toLowerCase(Locale.ENGLISH);
         if (!HOP_HEADERS.contains(lowerHeaderName)) {
            String newHeaderValue = this.filterServerResponseHeader(clientRequest, serverResponse, headerName, field.getValue());
            if (newHeaderValue != null) {
               proxyResponse.addHeader(headerName, newHeaderValue);
            }
         }
      }

      if (this._log.isDebugEnabled()) {
         StringBuilder builder = new StringBuilder(System.lineSeparator());
         builder.append(clientRequest.getProtocol()).append(" ").append(proxyResponse.getStatus()).append(" ").append(serverResponse.getReason()).append(System.lineSeparator());

         for(String headerName : proxyResponse.getHeaderNames()) {
            builder.append(headerName).append(": ");
            Iterator<String> headerValues = proxyResponse.getHeaders(headerName).iterator();

            while(headerValues.hasNext()) {
               String headerValue = (String)headerValues.next();
               if (headerValue != null) {
                  builder.append(headerValue);
               }

               if (headerValues.hasNext()) {
                  builder.append(",");
               }
            }

            builder.append(System.lineSeparator());
         }

         this._log.debug("{} proxying to downstream:{}{}", new Object[]{this.getRequestId(clientRequest), System.lineSeparator(), builder});
      }

   }

   protected String filterServerResponseHeader(HttpServletRequest clientRequest, Response serverResponse, String headerName, String headerValue) {
      return headerValue;
   }

   protected void onProxyResponseSuccess(HttpServletRequest clientRequest, HttpServletResponse proxyResponse, Response serverResponse) {
      if (this._log.isDebugEnabled()) {
         this._log.debug("{} proxying successful", this.getRequestId(clientRequest));
      }

      AsyncContext asyncContext = clientRequest.getAsyncContext();
      asyncContext.complete();
   }

   protected void onProxyResponseFailure(HttpServletRequest clientRequest, HttpServletResponse proxyResponse, Response serverResponse, Throwable failure) {
      if (this._log.isDebugEnabled()) {
         this._log.debug(this.getRequestId(clientRequest) + " proxying failed", failure);
      }

      int status = this.proxyResponseStatus(failure);
      int serverStatus = serverResponse == null ? status : serverResponse.getStatus();
      if (this.expects100Continue(clientRequest) && serverStatus >= 200) {
         status = serverStatus;
      }

      this.sendProxyResponseError(clientRequest, proxyResponse, status);
   }

   protected int proxyResponseStatus(Throwable failure) {
      return failure instanceof TimeoutException ? 504 : 502;
   }

   protected int getRequestId(HttpServletRequest clientRequest) {
      return System.identityHashCode(clientRequest);
   }

   protected void sendProxyResponseError(HttpServletRequest clientRequest, HttpServletResponse proxyResponse, int status) {
      try {
         if (!proxyResponse.isCommitted()) {
            proxyResponse.resetBuffer();
            proxyResponse.setHeader(HttpHeader.CONNECTION.asString(), HttpHeaderValue.CLOSE.asString());
         }

         proxyResponse.sendError(status);
      } catch (Exception e) {
         this._log.trace("IGNORED", e);

         try {
            proxyResponse.sendError(-1);
         } catch (Exception e2) {
            this._log.trace("IGNORED", e2);
         }
      } finally {
         if (clientRequest.isAsyncStarted()) {
            clientRequest.getAsyncContext().complete();
         }

      }

   }

   protected void onContinue(HttpServletRequest clientRequest, Request proxyRequest) {
      if (this._log.isDebugEnabled()) {
         this._log.debug("{} handling 100 Continue", this.getRequestId(clientRequest));
      }

   }

   protected static class TransparentDelegate {
      private final AbstractProxyServlet proxyServlet;
      private String _proxyTo;
      private String _prefix;

      protected TransparentDelegate(AbstractProxyServlet proxyServlet) {
         this.proxyServlet = proxyServlet;
      }

      protected void init(ServletConfig config) throws ServletException {
         this._proxyTo = config.getInitParameter("proxyTo");
         if (this._proxyTo == null) {
            throw new UnavailableException("Init parameter 'proxyTo' is required.");
         } else {
            String prefix = config.getInitParameter("prefix");
            if (prefix != null) {
               if (!prefix.startsWith("/")) {
                  throw new UnavailableException("Init parameter 'prefix' must start with a '/'.");
               }

               this._prefix = prefix;
            }

            String contextPath = config.getServletContext().getContextPath();
            this._prefix = this._prefix == null ? contextPath : contextPath + this._prefix;
            if (this.proxyServlet._log.isDebugEnabled()) {
               Logger var10000 = this.proxyServlet._log;
               String var10001 = config.getServletName();
               var10000.debug(var10001 + " @ " + this._prefix + " to " + this._proxyTo);
            }

         }
      }

      protected String rewriteTarget(HttpServletRequest request) {
         String path = request.getRequestURI();
         if (!path.startsWith(this._prefix)) {
            return null;
         } else {
            StringBuilder uri = new StringBuilder(this._proxyTo);
            if (this._proxyTo.endsWith("/")) {
               uri.setLength(uri.length() - 1);
            }

            String rest = path.substring(this._prefix.length());
            if (!rest.isEmpty()) {
               if (!rest.startsWith("/")) {
                  uri.append("/");
               }

               uri.append(rest);
            }

            String query = request.getQueryString();
            if (query != null) {
               String separator = "://";
               if (uri.indexOf("/", uri.indexOf(separator) + separator.length()) < 0) {
                  uri.append("/");
               }

               uri.append("?").append(query);
            }

            URI rewrittenURI = URI.create(uri.toString()).normalize();
            return !this.proxyServlet.validateDestination(rewrittenURI.getHost(), rewrittenURI.getPort()) ? null : rewrittenURI.toString();
         }
      }
   }

   class ProxyContinueProtocolHandler extends ContinueProtocolHandler {
      protected void onContinue(Request request) {
         HttpServletRequest clientRequest = (HttpServletRequest)request.getAttributes().get("org.sparkproject.jetty.proxy.clientRequest");
         AbstractProxyServlet.this.onContinue(clientRequest, request);
      }
   }
}
