package org.sparkproject.jetty.client;

import java.io.IOException;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.CookieStore;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.URI;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.client.api.AuthenticationStore;
import org.sparkproject.jetty.client.api.Connection;
import org.sparkproject.jetty.client.api.ContentResponse;
import org.sparkproject.jetty.client.api.Destination;
import org.sparkproject.jetty.client.api.Request;
import org.sparkproject.jetty.client.api.Response;
import org.sparkproject.jetty.client.http.HttpClientTransportOverHTTP;
import org.sparkproject.jetty.client.util.FormRequestContent;
import org.sparkproject.jetty.http.HttpCompliance;
import org.sparkproject.jetty.http.HttpField;
import org.sparkproject.jetty.http.HttpHeader;
import org.sparkproject.jetty.http.HttpMethod;
import org.sparkproject.jetty.http.HttpScheme;
import org.sparkproject.jetty.io.ArrayRetainableByteBufferPool;
import org.sparkproject.jetty.io.ByteBufferPool;
import org.sparkproject.jetty.io.ClientConnectionFactory;
import org.sparkproject.jetty.io.ClientConnector;
import org.sparkproject.jetty.io.MappedByteBufferPool;
import org.sparkproject.jetty.io.RetainableByteBufferPool;
import org.sparkproject.jetty.io.ssl.SslClientConnectionFactory;
import org.sparkproject.jetty.util.Fields;
import org.sparkproject.jetty.util.Jetty;
import org.sparkproject.jetty.util.ProcessorUtils;
import org.sparkproject.jetty.util.Promise;
import org.sparkproject.jetty.util.SocketAddressResolver;
import org.sparkproject.jetty.util.annotation.ManagedAttribute;
import org.sparkproject.jetty.util.annotation.ManagedObject;
import org.sparkproject.jetty.util.component.ContainerLifeCycle;
import org.sparkproject.jetty.util.component.DumpableCollection;
import org.sparkproject.jetty.util.ssl.SslContextFactory;
import org.sparkproject.jetty.util.thread.QueuedThreadPool;
import org.sparkproject.jetty.util.thread.ScheduledExecutorScheduler;
import org.sparkproject.jetty.util.thread.Scheduler;
import org.sparkproject.jetty.util.thread.Sweeper;
import org.sparkproject.jetty.util.thread.ThreadPool;

@ManagedObject("The HTTP client")
public class HttpClient extends ContainerLifeCycle {
   public static final String USER_AGENT;
   private static final Logger LOG;
   private final ConcurrentMap destinations;
   private final ProtocolHandlers handlers;
   private final List requestListeners;
   private final Set decoderFactories;
   private final ProxyConfiguration proxyConfig;
   private final HttpClientTransport transport;
   private final ClientConnector connector;
   private AuthenticationStore authenticationStore;
   private CookieManager cookieManager;
   private CookieStore cookieStore;
   private SocketAddressResolver resolver;
   private HttpField agentField;
   private boolean followRedirects;
   private int maxConnectionsPerDestination;
   private int maxRequestsQueuedPerDestination;
   private int requestBufferSize;
   private int responseBufferSize;
   private int maxRedirects;
   private long addressResolutionTimeout;
   private boolean tcpNoDelay;
   private boolean strictEventOrdering;
   private HttpField encodingField;
   private long destinationIdleTimeout;
   private String name;
   private HttpCompliance httpCompliance;
   private String defaultRequestContentType;
   private boolean useInputDirectByteBuffers;
   private boolean useOutputDirectByteBuffers;
   private int maxResponseHeadersSize;
   private Sweeper destinationSweeper;

   public HttpClient() {
      this(new HttpClientTransportOverHTTP());
   }

   public HttpClient(HttpClientTransport transport) {
      this.destinations = new ConcurrentHashMap();
      this.handlers = new ProtocolHandlers();
      this.requestListeners = new ArrayList();
      this.decoderFactories = new ContentDecoderFactorySet();
      this.proxyConfig = new ProxyConfiguration();
      this.authenticationStore = new HttpAuthenticationStore();
      this.agentField = new HttpField(HttpHeader.USER_AGENT, USER_AGENT);
      this.followRedirects = true;
      this.maxConnectionsPerDestination = 64;
      this.maxRequestsQueuedPerDestination = 1024;
      this.requestBufferSize = 4096;
      this.responseBufferSize = 16384;
      this.maxRedirects = 8;
      this.addressResolutionTimeout = 15000L;
      this.tcpNoDelay = true;
      this.strictEventOrdering = false;
      String var10001 = this.getClass().getSimpleName();
      this.name = var10001 + "@" + Integer.toHexString(this.hashCode());
      this.httpCompliance = HttpCompliance.RFC7230;
      this.defaultRequestContentType = "application/octet-stream";
      this.useInputDirectByteBuffers = true;
      this.useOutputDirectByteBuffers = true;
      this.maxResponseHeadersSize = -1;
      this.transport = (HttpClientTransport)Objects.requireNonNull(transport);
      this.addBean(transport);
      this.connector = (ClientConnector)((AbstractHttpClientTransport)transport).getContainedBeans(ClientConnector.class).stream().findFirst().orElseThrow();
      this.addBean(this.handlers);
      this.addBean(this.decoderFactories);
   }

   public void dump(Appendable out, String indent) throws IOException {
      this.dumpObjects(out, indent, new Object[]{new DumpableCollection("requestListeners", this.requestListeners)});
   }

   public HttpClientTransport getTransport() {
      return this.transport;
   }

   public SslContextFactory.Client getSslContextFactory() {
      return this.connector.getSslContextFactory();
   }

   protected void doStart() throws Exception {
      Executor executor = this.getExecutor();
      if (executor == null) {
         QueuedThreadPool threadPool = new QueuedThreadPool();
         threadPool.setName(this.name);
         executor = threadPool;
         this.setExecutor(threadPool);
      }

      int maxBucketSize = executor instanceof ThreadPool.SizedThreadPool ? ((ThreadPool.SizedThreadPool)executor).getMaxThreads() / 2 : ProcessorUtils.availableProcessors() * 2;
      ByteBufferPool byteBufferPool = this.getByteBufferPool();
      if (byteBufferPool == null) {
         this.setByteBufferPool(new MappedByteBufferPool(2048, maxBucketSize));
      }

      if (this.getBean(RetainableByteBufferPool.class) == null) {
         this.addBean(new ArrayRetainableByteBufferPool(0, 2048, 65536, maxBucketSize));
      }

      Scheduler scheduler = this.getScheduler();
      if (scheduler == null) {
         scheduler = new ScheduledExecutorScheduler(this.name + "-scheduler", false);
         this.setScheduler(scheduler);
      }

      if (this.resolver == null) {
         this.setSocketAddressResolver(new SocketAddressResolver.Async(this.getExecutor(), scheduler, this.getAddressResolutionTimeout()));
      }

      this.handlers.put(new ContinueProtocolHandler());
      this.handlers.put(new RedirectProtocolHandler(this));
      this.handlers.put(new WWWAuthenticationProtocolHandler(this));
      this.handlers.put(new ProxyAuthenticationProtocolHandler(this));
      this.handlers.put(new UpgradeProtocolHandler());
      this.decoderFactories.add(new GZIPContentDecoder.Factory(byteBufferPool));
      this.cookieManager = this.newCookieManager();
      this.cookieStore = this.cookieManager.getCookieStore();
      this.transport.setHttpClient(this);
      super.doStart();
      if (this.getDestinationIdleTimeout() > 0L) {
         this.destinationSweeper = new Sweeper(scheduler, 1000L);
         this.destinationSweeper.start();
      }

   }

   private CookieManager newCookieManager() {
      return new CookieManager(this.getCookieStore(), CookiePolicy.ACCEPT_ALL);
   }

   protected void doStop() throws Exception {
      if (this.destinationSweeper != null) {
         this.destinationSweeper.stop();
         this.destinationSweeper = null;
      }

      this.decoderFactories.clear();
      this.handlers.clear();
      this.destinations.values().forEach(HttpDestination::close);
      this.destinations.clear();
      this.requestListeners.clear();
      this.authenticationStore.clearAuthentications();
      this.authenticationStore.clearAuthenticationResults();
      super.doStop();
   }

   public List getRequestListeners() {
      return this.requestListeners;
   }

   public CookieStore getCookieStore() {
      return this.cookieStore;
   }

   public void setCookieStore(CookieStore cookieStore) {
      if (this.isStarted()) {
         throw new IllegalStateException();
      } else {
         this.cookieStore = (CookieStore)Objects.requireNonNull(cookieStore);
         this.cookieManager = this.newCookieManager();
      }
   }

   CookieManager getCookieManager() {
      return this.cookieManager;
   }

   Sweeper getDestinationSweeper() {
      return this.destinationSweeper;
   }

   public AuthenticationStore getAuthenticationStore() {
      return this.authenticationStore;
   }

   public void setAuthenticationStore(AuthenticationStore authenticationStore) {
      if (this.isStarted()) {
         throw new IllegalStateException();
      } else {
         this.authenticationStore = authenticationStore;
      }
   }

   public Set getContentDecoderFactories() {
      return this.decoderFactories;
   }

   public ContentResponse GET(String uri) throws InterruptedException, ExecutionException, TimeoutException {
      return this.GET(URI.create(uri));
   }

   public ContentResponse GET(URI uri) throws InterruptedException, ExecutionException, TimeoutException {
      return this.newRequest(uri).send();
   }

   public ContentResponse FORM(String uri, Fields fields) throws InterruptedException, ExecutionException, TimeoutException {
      return this.FORM(URI.create(uri), fields);
   }

   public ContentResponse FORM(URI uri, Fields fields) throws InterruptedException, ExecutionException, TimeoutException {
      return this.POST(uri).body(new FormRequestContent(fields)).send();
   }

   public Request POST(String uri) {
      return this.POST(URI.create(uri));
   }

   public Request POST(URI uri) {
      return this.newRequest(uri).method(HttpMethod.POST);
   }

   public Request newRequest(String host, int port) {
      return this.newRequest((new Origin("http", host, port)).asString());
   }

   public Request newRequest(String uri) {
      return this.newRequest(URI.create(uri));
   }

   public Request newRequest(URI uri) {
      return this.newHttpRequest(this.newConversation(), uri);
   }

   protected Request copyRequest(HttpRequest oldRequest, URI newURI) {
      return oldRequest.copy(newURI);
   }

   protected HttpRequest newHttpRequest(HttpConversation conversation, URI uri) {
      return new HttpRequest(this, conversation, uri);
   }

   public Destination resolveDestination(Request request) {
      HttpClientTransport transport = this.getTransport();
      Origin origin = transport.newOrigin((HttpRequest)request);
      HttpDestination destination = this.resolveDestination(origin);
      if (LOG.isDebugEnabled()) {
         LOG.debug("Resolved {} for {}", destination, request);
      }

      return destination;
   }

   public Origin createOrigin(HttpRequest request, Origin.Protocol protocol) {
      String scheme = request.getScheme();
      if (!HttpScheme.HTTP.is(scheme) && !HttpScheme.HTTPS.is(scheme) && !HttpScheme.WS.is(scheme) && !HttpScheme.WSS.is(scheme)) {
         throw new IllegalArgumentException("Invalid protocol " + scheme);
      } else {
         scheme = scheme.toLowerCase(Locale.ENGLISH);
         String host = request.getHost();
         host = host.toLowerCase(Locale.ENGLISH);
         int port = request.getPort();
         port = normalizePort(scheme, port);
         return new Origin(scheme, host, port, request.getTag(), protocol);
      }
   }

   public HttpDestination resolveDestination(Origin origin) {
      return (HttpDestination)this.destinations.compute(origin, (k, v) -> {
         if (v != null && !v.stale()) {
            return v;
         } else {
            HttpDestination newDestination = this.getTransport().newHttpDestination(k);
            this.addManaged(newDestination);
            if (LOG.isDebugEnabled()) {
               LOG.debug("Created {}; existing: '{}'", newDestination, v);
            }

            return newDestination;
         }
      });
   }

   protected boolean removeDestination(HttpDestination destination) {
      boolean removed = this.destinations.remove(destination.getOrigin(), destination);
      this.removeBean(destination);
      if (LOG.isDebugEnabled()) {
         LOG.debug("Removed {}; result: {}", destination, removed);
      }

      return removed;
   }

   public List getDestinations() {
      return new ArrayList(this.destinations.values());
   }

   protected void send(HttpRequest request, List listeners) {
      HttpDestination destination = (HttpDestination)this.resolveDestination((Request)request);
      destination.send(request, listeners);
   }

   protected void newConnection(HttpDestination destination, final Promise promise) {
      final Map<String, Object> context = new ConcurrentHashMap();
      context.put("org.sparkproject.jetty.client", this);
      context.put("org.sparkproject.jetty.client.destination", destination);
      Origin.Protocol protocol = destination.getOrigin().getProtocol();
      List<String> protocols = protocol != null ? protocol.getProtocols() : List.of("http/1.1");
      context.put("org.sparkproject.jetty.client.connector.applicationProtocols", protocols);
      Origin.Address address = destination.getConnectAddress();
      this.resolver.resolve(address.getHost(), address.getPort(), new Promise() {
         public void succeeded(List socketAddresses) {
            this.connect(socketAddresses, 0, context);
         }

         public void failed(Throwable x) {
            promise.failed(x);
         }

         private void connect(final List socketAddresses, final int index, final Map contextx) {
            context.put("org.sparkproject.jetty.client.connection.promise", new Promise.Wrapper(promise) {
               public void failed(Throwable x) {
                  int nextIndex = index + 1;
                  if (nextIndex == socketAddresses.size()) {
                     super.failed(x);
                  } else {
                     connect(socketAddresses, nextIndex, context);
                  }

               }
            });
            HttpClient.this.transport.connect((SocketAddress)socketAddresses.get(index), context);
         }
      });
   }

   private HttpConversation newConversation() {
      return new HttpConversation();
   }

   public ProtocolHandlers getProtocolHandlers() {
      return this.handlers;
   }

   protected ProtocolHandler findProtocolHandler(Request request, Response response) {
      return this.handlers.find(request, response);
   }

   public ByteBufferPool getByteBufferPool() {
      return this.connector.getByteBufferPool();
   }

   public void setByteBufferPool(ByteBufferPool byteBufferPool) {
      this.connector.setByteBufferPool(byteBufferPool);
   }

   @ManagedAttribute("The name of this HttpClient")
   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   @ManagedAttribute("The timeout, in milliseconds, for connect() operations")
   public long getConnectTimeout() {
      return this.connector.getConnectTimeout().toMillis();
   }

   public void setConnectTimeout(long connectTimeout) {
      this.connector.setConnectTimeout(Duration.ofMillis(connectTimeout));
   }

   public long getAddressResolutionTimeout() {
      return this.addressResolutionTimeout;
   }

   public void setAddressResolutionTimeout(long addressResolutionTimeout) {
      this.addressResolutionTimeout = addressResolutionTimeout;
   }

   @ManagedAttribute("The timeout, in milliseconds, to close idle connections")
   public long getIdleTimeout() {
      return this.connector.getIdleTimeout().toMillis();
   }

   public void setIdleTimeout(long idleTimeout) {
      this.connector.setIdleTimeout(Duration.ofMillis(idleTimeout));
   }

   public SocketAddress getBindAddress() {
      return this.connector.getBindAddress();
   }

   public void setBindAddress(SocketAddress bindAddress) {
      this.connector.setBindAddress(bindAddress);
   }

   public HttpField getUserAgentField() {
      return this.agentField;
   }

   public void setUserAgentField(HttpField agent) {
      if (agent != null && agent.getHeader() != HttpHeader.USER_AGENT) {
         throw new IllegalArgumentException();
      } else {
         this.agentField = agent;
      }
   }

   @ManagedAttribute("Whether HTTP redirects are followed")
   public boolean isFollowRedirects() {
      return this.followRedirects;
   }

   public void setFollowRedirects(boolean follow) {
      this.followRedirects = follow;
   }

   public Executor getExecutor() {
      return this.connector.getExecutor();
   }

   public void setExecutor(Executor executor) {
      this.connector.setExecutor(executor);
   }

   public Scheduler getScheduler() {
      return this.connector.getScheduler();
   }

   public void setScheduler(Scheduler scheduler) {
      this.connector.setScheduler(scheduler);
   }

   public SocketAddressResolver getSocketAddressResolver() {
      return this.resolver;
   }

   public void setSocketAddressResolver(SocketAddressResolver resolver) {
      if (this.isStarted()) {
         throw new IllegalStateException();
      } else {
         this.updateBean(this.resolver, resolver);
         this.resolver = resolver;
      }
   }

   @ManagedAttribute("The max number of connections per each destination")
   public int getMaxConnectionsPerDestination() {
      return this.maxConnectionsPerDestination;
   }

   public void setMaxConnectionsPerDestination(int maxConnectionsPerDestination) {
      this.maxConnectionsPerDestination = maxConnectionsPerDestination;
   }

   @ManagedAttribute("The max number of requests queued per each destination")
   public int getMaxRequestsQueuedPerDestination() {
      return this.maxRequestsQueuedPerDestination;
   }

   public void setMaxRequestsQueuedPerDestination(int maxRequestsQueuedPerDestination) {
      this.maxRequestsQueuedPerDestination = maxRequestsQueuedPerDestination;
   }

   @ManagedAttribute("The request buffer size in bytes")
   public int getRequestBufferSize() {
      return this.requestBufferSize;
   }

   public void setRequestBufferSize(int requestBufferSize) {
      this.requestBufferSize = requestBufferSize;
   }

   @ManagedAttribute("The response buffer size in bytes")
   public int getResponseBufferSize() {
      return this.responseBufferSize;
   }

   public void setResponseBufferSize(int responseBufferSize) {
      this.responseBufferSize = responseBufferSize;
   }

   public int getMaxRedirects() {
      return this.maxRedirects;
   }

   public void setMaxRedirects(int maxRedirects) {
      this.maxRedirects = maxRedirects;
   }

   /** @deprecated */
   @ManagedAttribute(
      value = "Whether the TCP_NODELAY option is enabled",
      name = "tcpNoDelay"
   )
   @Deprecated
   public boolean isTCPNoDelay() {
      return this.tcpNoDelay;
   }

   /** @deprecated */
   @Deprecated
   public void setTCPNoDelay(boolean tcpNoDelay) {
      this.tcpNoDelay = tcpNoDelay;
   }

   public HttpCompliance getHttpCompliance() {
      return this.httpCompliance;
   }

   public void setHttpCompliance(HttpCompliance httpCompliance) {
      this.httpCompliance = httpCompliance;
   }

   @ManagedAttribute("Whether request/response events must be strictly ordered")
   public boolean isStrictEventOrdering() {
      return this.strictEventOrdering;
   }

   public void setStrictEventOrdering(boolean strictEventOrdering) {
      this.strictEventOrdering = strictEventOrdering;
   }

   @ManagedAttribute("The time in ms after which idle destinations are removed, disabled when zero or negative")
   public long getDestinationIdleTimeout() {
      return this.destinationIdleTimeout;
   }

   public void setDestinationIdleTimeout(long destinationIdleTimeout) {
      if (this.isStarted()) {
         throw new IllegalStateException();
      } else {
         this.destinationIdleTimeout = destinationIdleTimeout;
      }
   }

   /** @deprecated */
   @Deprecated
   @ManagedAttribute("Whether idle destinations are removed")
   public boolean isRemoveIdleDestinations() {
      return this.destinationIdleTimeout > 0L;
   }

   /** @deprecated */
   @Deprecated
   public void setRemoveIdleDestinations(boolean removeIdleDestinations) {
      this.setDestinationIdleTimeout(removeIdleDestinations ? 10000L : 0L);
   }

   @ManagedAttribute("Whether the connect() operation is blocking")
   public boolean isConnectBlocking() {
      return this.connector.isConnectBlocking();
   }

   public void setConnectBlocking(boolean connectBlocking) {
      this.connector.setConnectBlocking(connectBlocking);
   }

   @ManagedAttribute("The default content type for request content")
   public String getDefaultRequestContentType() {
      return this.defaultRequestContentType;
   }

   public void setDefaultRequestContentType(String contentType) {
      this.defaultRequestContentType = contentType;
   }

   @ManagedAttribute("Whether to use direct ByteBuffers for reading")
   public boolean isUseInputDirectByteBuffers() {
      return this.useInputDirectByteBuffers;
   }

   public void setUseInputDirectByteBuffers(boolean useInputDirectByteBuffers) {
      this.useInputDirectByteBuffers = useInputDirectByteBuffers;
   }

   @ManagedAttribute("Whether to use direct ByteBuffers for writing")
   public boolean isUseOutputDirectByteBuffers() {
      return this.useOutputDirectByteBuffers;
   }

   public void setUseOutputDirectByteBuffers(boolean useOutputDirectByteBuffers) {
      this.useOutputDirectByteBuffers = useOutputDirectByteBuffers;
   }

   @ManagedAttribute("The max size in bytes of the response headers")
   public int getMaxResponseHeadersSize() {
      return this.maxResponseHeadersSize;
   }

   public void setMaxResponseHeadersSize(int maxResponseHeadersSize) {
      this.maxResponseHeadersSize = maxResponseHeadersSize;
   }

   public ProxyConfiguration getProxyConfiguration() {
      return this.proxyConfig;
   }

   protected HttpField getAcceptEncodingField() {
      return this.encodingField;
   }

   /** @deprecated */
   @Deprecated
   protected String normalizeHost(String host) {
      return host;
   }

   public static int normalizePort(String scheme, int port) {
      return port > 0 ? port : HttpScheme.getDefaultPort(scheme);
   }

   public boolean isDefaultPort(String scheme, int port) {
      return HttpScheme.getDefaultPort(scheme) == port;
   }

   public static boolean isSchemeSecure(String scheme) {
      return HttpScheme.HTTPS.is(scheme) || HttpScheme.WSS.is(scheme);
   }

   protected ClientConnectionFactory newSslClientConnectionFactory(SslContextFactory.Client sslContextFactory, ClientConnectionFactory connectionFactory) {
      if (sslContextFactory == null) {
         sslContextFactory = this.getSslContextFactory();
      }

      return new SslClientConnectionFactory(sslContextFactory, this.getByteBufferPool(), this.getExecutor(), connectionFactory);
   }

   static {
      USER_AGENT = "Jetty/" + Jetty.VERSION;
      LOG = LoggerFactory.getLogger(HttpClient.class);
   }

   private class ContentDecoderFactorySet implements Set {
      private final Set set = new HashSet();

      public boolean add(ContentDecoder.Factory e) {
         boolean result = this.set.add(e);
         this.invalidate();
         return result;
      }

      public boolean addAll(Collection c) {
         boolean result = this.set.addAll(c);
         this.invalidate();
         return result;
      }

      public boolean remove(Object o) {
         boolean result = this.set.remove(o);
         this.invalidate();
         return result;
      }

      public boolean removeAll(Collection c) {
         boolean result = this.set.removeAll(c);
         this.invalidate();
         return result;
      }

      public boolean retainAll(Collection c) {
         boolean result = this.set.retainAll(c);
         this.invalidate();
         return result;
      }

      public void clear() {
         this.set.clear();
         this.invalidate();
      }

      public int size() {
         return this.set.size();
      }

      public boolean isEmpty() {
         return this.set.isEmpty();
      }

      public boolean contains(Object o) {
         return this.set.contains(o);
      }

      public boolean containsAll(Collection c) {
         return this.set.containsAll(c);
      }

      public Iterator iterator() {
         final Iterator<ContentDecoder.Factory> iterator = this.set.iterator();
         return new Iterator() {
            public boolean hasNext() {
               return iterator.hasNext();
            }

            public ContentDecoder.Factory next() {
               return (ContentDecoder.Factory)iterator.next();
            }

            public void remove() {
               iterator.remove();
               ContentDecoderFactorySet.this.invalidate();
            }
         };
      }

      public Object[] toArray() {
         return this.set.toArray();
      }

      public Object[] toArray(Object[] a) {
         return this.set.toArray(a);
      }

      private void invalidate() {
         if (this.set.isEmpty()) {
            HttpClient.this.encodingField = null;
         } else {
            StringBuilder value = new StringBuilder();
            Iterator<ContentDecoder.Factory> iterator = this.set.iterator();

            while(iterator.hasNext()) {
               ContentDecoder.Factory decoderFactory = (ContentDecoder.Factory)iterator.next();
               value.append(decoderFactory.getEncoding());
               if (iterator.hasNext()) {
                  value.append(",");
               }
            }

            HttpClient.this.encodingField = new HttpField(HttpHeader.ACCEPT_ENCODING, value.toString());
         }

      }
   }
}
