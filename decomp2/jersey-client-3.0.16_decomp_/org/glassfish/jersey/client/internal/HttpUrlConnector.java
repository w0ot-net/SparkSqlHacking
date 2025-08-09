package org.glassfish.jersey.client.internal;

import jakarta.ws.rs.ProcessingException;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.ProtocolException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import org.glassfish.jersey.client.ClientRequest;
import org.glassfish.jersey.client.ClientResponse;
import org.glassfish.jersey.client.HttpUrlConnectorProvider;
import org.glassfish.jersey.client.JerseyClient;
import org.glassfish.jersey.client.RequestEntityProcessing;
import org.glassfish.jersey.client.innate.ClientProxy;
import org.glassfish.jersey.client.innate.http.SSLParamConfigurator;
import org.glassfish.jersey.client.spi.AsyncConnectorCallback;
import org.glassfish.jersey.client.spi.Connector;
import org.glassfish.jersey.internal.PropertiesResolver;
import org.glassfish.jersey.internal.util.PropertiesHelper;
import org.glassfish.jersey.internal.util.collection.LRU;
import org.glassfish.jersey.internal.util.collection.UnsafeValue;
import org.glassfish.jersey.internal.util.collection.Value;
import org.glassfish.jersey.internal.util.collection.Values;
import org.glassfish.jersey.message.internal.Statuses;

public class HttpUrlConnector implements Connector {
   private static final Logger LOGGER = Logger.getLogger(HttpUrlConnector.class.getName());
   private static final String ALLOW_RESTRICTED_HEADERS_SYSTEM_PROPERTY = "sun.net.http.allowRestrictedHeaders";
   private static final Value DEFAULT_SSL_SOCKET_FACTORY = Values.lazy(() -> HttpsURLConnection.getDefaultSSLSocketFactory());
   private static final String[] restrictedHeaders = new String[]{"Access-Control-Request-Headers", "Access-Control-Request-Method", "Connection", "Content-Length", "Content-Transfer-Encoding", "Host", "Keep-Alive", "Origin", "Trailer", "Transfer-Encoding", "Upgrade", "Via"};
   private static final Set restrictedHeaderSet;
   private final HttpUrlConnectorProvider.ConnectionFactory connectionFactory;
   private final int chunkSize;
   private final boolean fixLengthStreaming;
   private final boolean setMethodWorkaround;
   private final boolean isRestrictedHeaderPropertySet;
   private Value sslSocketFactory;
   private final LRU sslSocketFactoryCache = LRU.create();
   private final ConnectorExtension connectorExtension = new HttpUrlExpect100ContinueConnectorExtension();

   public HttpUrlConnector(final Client client, HttpUrlConnectorProvider.ConnectionFactory connectionFactory, int chunkSize, boolean fixLengthStreaming, boolean setMethodWorkaround) {
      this.connectionFactory = connectionFactory;
      this.chunkSize = chunkSize;
      this.fixLengthStreaming = fixLengthStreaming;
      this.setMethodWorkaround = setMethodWorkaround;
      this.sslSocketFactory = Values.lazy(new Value() {
         public SSLSocketFactory get() {
            return client.getSslContext().getSocketFactory();
         }
      });
      this.isRestrictedHeaderPropertySet = Boolean.valueOf((String)AccessController.doPrivileged(PropertiesHelper.getSystemProperty("sun.net.http.allowRestrictedHeaders", "false")));
      LOGGER.config(this.isRestrictedHeaderPropertySet ? LocalizationMessages.RESTRICTED_HEADER_PROPERTY_SETTING_TRUE("sun.net.http.allowRestrictedHeaders") : LocalizationMessages.RESTRICTED_HEADER_PROPERTY_SETTING_FALSE("sun.net.http.allowRestrictedHeaders"));
   }

   private static InputStream getInputStream(final HttpURLConnection uc, final ClientRequest clientRequest) throws IOException {
      return new InputStream() {
         private final UnsafeValue in = Values.lazy(new UnsafeValue() {
            public InputStream get() throws IOException {
               if (uc.getResponseCode() < Status.BAD_REQUEST.getStatusCode()) {
                  return uc.getInputStream();
               } else {
                  InputStream ein = uc.getErrorStream();
                  return (InputStream)(ein != null ? ein : new ByteArrayInputStream(new byte[0]));
               }
            }
         });
         private volatile boolean closed = false;

         private void throwIOExceptionIfClosed() throws IOException {
            if (this.closed) {
               throw new IOException("Stream closed");
            } else if (clientRequest.isCancelled()) {
               this.close();
               throw new IOException(new CancellationException());
            }
         }

         public int read() throws IOException {
            int result = ((InputStream)this.in.get()).read();
            this.throwIOExceptionIfClosed();
            return result;
         }

         public int read(byte[] b) throws IOException {
            int result = ((InputStream)this.in.get()).read(b);
            this.throwIOExceptionIfClosed();
            return result;
         }

         public int read(byte[] b, int off, int len) throws IOException {
            int result = ((InputStream)this.in.get()).read(b, off, len);
            this.throwIOExceptionIfClosed();
            return result;
         }

         public long skip(long n) throws IOException {
            long result = ((InputStream)this.in.get()).skip(n);
            this.throwIOExceptionIfClosed();
            return result;
         }

         public int available() throws IOException {
            int result = ((InputStream)this.in.get()).available();
            this.throwIOExceptionIfClosed();
            return result;
         }

         public void close() throws IOException {
            try {
               ((InputStream)this.in.get()).close();
            } finally {
               this.closed = true;
            }

         }

         public void mark(int readLimit) {
            try {
               ((InputStream)this.in.get()).mark(readLimit);
            } catch (IOException e) {
               throw new IllegalStateException("Unable to retrieve the underlying input stream.", e);
            }
         }

         public void reset() throws IOException {
            ((InputStream)this.in.get()).reset();
            this.throwIOExceptionIfClosed();
         }

         public boolean markSupported() {
            try {
               return ((InputStream)this.in.get()).markSupported();
            } catch (IOException e) {
               throw new IllegalStateException("Unable to retrieve the underlying input stream.", e);
            }
         }
      };
   }

   public ClientResponse apply(ClientRequest request) {
      try {
         return this._apply(request);
      } catch (IOException ex) {
         throw new ProcessingException(ex);
      }
   }

   public Future apply(ClientRequest request, AsyncConnectorCallback callback) {
      try {
         callback.response(this._apply(request));
      } catch (IOException ex) {
         callback.failure(new ProcessingException(ex));
      } catch (Throwable t) {
         callback.failure(t);
      }

      return CompletableFuture.completedFuture((Object)null);
   }

   public void close() {
   }

   protected void secureConnection(JerseyClient client, HttpURLConnection uc) {
      if (uc instanceof HttpsURLConnection) {
         HttpsURLConnection suc = (HttpsURLConnection)uc;
         HostnameVerifier verifier = client.getHostnameVerifier();
         if (verifier != null) {
            suc.setHostnameVerifier(verifier);
         }

         if (DEFAULT_SSL_SOCKET_FACTORY.get() == suc.getSSLSocketFactory()) {
            suc.setSSLSocketFactory((SSLSocketFactory)this.sslSocketFactory.get());
         }
      }

   }

   private void secureConnection(ClientRequest clientRequest, HttpURLConnection uc, SSLParamConfigurator sniConfig) {
      this.setSslContextFactory(clientRequest.getClient(), clientRequest);
      this.secureConnection(clientRequest.getClient(), uc);
      if (sniConfig.isSNIRequired() && uc instanceof HttpsURLConnection) {
         HttpsURLConnection suc = (HttpsURLConnection)uc;
         SniSSLSocketFactory socketFactory = new SniSSLSocketFactory(suc.getSSLSocketFactory());
         socketFactory.setSniConfig(sniConfig);
         suc.setSSLSocketFactory(socketFactory);
      }

   }

   protected void setSslContextFactory(Client client, ClientRequest request) {
      final Supplier<SSLContext> supplier = (Supplier)request.resolveProperty("jersey.config.client.ssl.context.supplier", Supplier.class);
      if (supplier != null) {
         this.sslSocketFactory = Values.lazy(new Value() {
            public SSLSocketFactory get() {
               SSLContext sslContext = (SSLContext)supplier.get();
               SSLSocketFactory factory = (SSLSocketFactory)HttpUrlConnector.this.sslSocketFactoryCache.getIfPresent(sslContext);
               if (factory == null) {
                  factory = sslContext.getSocketFactory();
                  HttpUrlConnector.this.sslSocketFactoryCache.put(sslContext, factory);
               }

               return factory;
            }
         });
      }

   }

   private ClientResponse _apply(ClientRequest request) throws IOException {
      Optional<ClientProxy> proxy = ClientProxy.proxyFromRequest(request);
      SSLParamConfigurator sniConfig = SSLParamConfigurator.builder().request(request).setSNIHostName((PropertiesResolver)request).build();
      URI sniUri;
      if (sniConfig.isSNIRequired()) {
         sniUri = sniConfig.toIPRequestUri();
         LOGGER.fine(LocalizationMessages.SNI_URI_REPLACED(sniUri.getHost(), request.getUri().getHost()));
      } else {
         sniUri = request.getUri();
      }

      proxy.ifPresent((clientProxy) -> ClientProxy.setBasicAuthorizationHeader(request.getHeaders(), (ClientProxy)proxy.get()));
      HttpURLConnection uc = this.connectionFactory.getConnection(sniUri.toURL(), proxy.isPresent() ? ((ClientProxy)proxy.get()).proxy() : null);
      uc.setDoInput(true);
      String httpMethod = request.getMethod();
      if ((Boolean)request.resolveProperty("jersey.config.client.httpUrlConnection.setMethodWorkaround", (Object)this.setMethodWorkaround)) {
         setRequestMethodViaJreBugWorkaround(uc, httpMethod);
      } else {
         uc.setRequestMethod(httpMethod);
      }

      uc.setInstanceFollowRedirects((Boolean)request.resolveProperty("jersey.config.client.followRedirects", (Object)true));
      uc.setConnectTimeout((Integer)request.resolveProperty("jersey.config.client.connectTimeout", (Object)uc.getConnectTimeout()));
      uc.setReadTimeout((Integer)request.resolveProperty("jersey.config.client.readTimeout", (Object)uc.getReadTimeout()));
      this.secureConnection(request, uc, sniConfig);
      Object entity = request.getEntity();
      Exception storedException = null;

      try {
         if (entity != null) {
            RequestEntityProcessing entityProcessing = (RequestEntityProcessing)request.resolveProperty("jersey.config.client.request.entity.processing", RequestEntityProcessing.class);
            long length = request.getLengthLong();
            if (entityProcessing == null || entityProcessing != RequestEntityProcessing.BUFFERED) {
               if (this.fixLengthStreaming && length > 0L) {
                  uc.setFixedLengthStreamingMode(length);
               } else if (entityProcessing == RequestEntityProcessing.CHUNKED) {
                  uc.setChunkedStreamingMode(this.chunkSize);
               }
            }

            uc.setDoOutput(true);
            if ("GET".equalsIgnoreCase(httpMethod)) {
               Logger logger = Logger.getLogger(HttpUrlConnector.class.getName());
               if (logger.isLoggable(Level.INFO)) {
                  logger.log(Level.INFO, LocalizationMessages.HTTPURLCONNECTION_REPLACES_GET_WITH_ENTITY());
               }
            }

            this.processExtensions(request, uc);
            request.setStreamProvider((contentLength) -> {
               this.setOutboundHeaders(request.getStringHeaders(), uc);
               return uc.getOutputStream();
            });
            request.writeEntity();
         } else {
            this.setOutboundHeaders(request.getStringHeaders(), uc);
         }
      } catch (IOException ioe) {
         storedException = this.handleException(request, ioe, uc);
      }

      int code = uc.getResponseCode();
      String reasonPhrase = uc.getResponseMessage();
      Response.StatusType status = reasonPhrase == null ? Statuses.from(code) : Statuses.from(code, reasonPhrase);
      URI resolvedRequestUri = null;

      try {
         resolvedRequestUri = uc.getURL().toURI();
      } catch (URISyntaxException e) {
         if (storedException == null) {
            storedException = e;
         } else {
            storedException.addSuppressed(e);
         }
      }

      ClientResponse responseContext = new ClientResponse(status, request, resolvedRequestUri);
      responseContext.headers((Map)uc.getHeaderFields().entrySet().stream().filter((stringListEntry) -> stringListEntry.getKey() != null).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));

      try {
         InputStream inputStream = getInputStream(uc, request);
         responseContext.setEntityStream(inputStream);
      } catch (IOException ioe) {
         if (storedException == null) {
            storedException = ioe;
         } else {
            storedException.addSuppressed(ioe);
         }
      }

      if (storedException != null) {
         throw new ClientResponseProcessingException(responseContext, storedException);
      } else {
         return responseContext;
      }
   }

   private void setOutboundHeaders(MultivaluedMap headers, HttpURLConnection uc) {
      boolean restrictedSent = false;

      for(Map.Entry header : headers.entrySet()) {
         String headerName = (String)header.getKey();
         List<String> headerValues = (List)header.getValue();
         String headerValue;
         if (headerValues.size() == 1) {
            headerValue = (String)headerValues.get(0);
            uc.setRequestProperty(headerName, headerValue);
         } else {
            StringBuilder b = new StringBuilder();
            boolean add = false;

            for(Object value : headerValues) {
               if (add) {
                  b.append(',');
               }

               add = true;
               b.append(value);
            }

            headerValue = b.toString();
            uc.setRequestProperty(headerName, headerValue);
         }

         if (!this.isRestrictedHeaderPropertySet && !restrictedSent && this.isHeaderRestricted(headerName, headerValue)) {
            restrictedSent = true;
         }
      }

      if (restrictedSent) {
         LOGGER.warning(LocalizationMessages.RESTRICTED_HEADER_POSSIBLY_IGNORED("sun.net.http.allowRestrictedHeaders"));
      }

   }

   private boolean isHeaderRestricted(String name, String value) {
      name = name.toLowerCase(Locale.ROOT);
      return name.startsWith("sec-") || restrictedHeaderSet.contains(name) && (!"connection".equalsIgnoreCase(name) || !"close".equalsIgnoreCase(value));
   }

   private static void setRequestMethodViaJreBugWorkaround(final HttpURLConnection httpURLConnection, final String method) {
      try {
         httpURLConnection.setRequestMethod(method);
      } catch (ProtocolException var6) {
         try {
            AccessController.doPrivileged(new PrivilegedExceptionAction() {
               public Object run() throws NoSuchFieldException, IllegalAccessException {
                  try {
                     httpURLConnection.setRequestMethod(method);
                  } catch (ProtocolException var9) {
                     Class<?> connectionClass = httpURLConnection.getClass();

                     try {
                        Field delegateField = connectionClass.getDeclaredField("delegate");
                        delegateField.setAccessible(true);
                        HttpURLConnection delegateConnection = (HttpURLConnection)delegateField.get(httpURLConnection);
                        HttpUrlConnector.setRequestMethodViaJreBugWorkaround(delegateConnection, method);
                     } catch (NoSuchFieldException var5) {
                     } catch (IllegalAccessException | IllegalArgumentException e) {
                        throw new RuntimeException(e);
                     }

                     try {
                        while(connectionClass != null) {
                           Field methodField;
                           try {
                              methodField = connectionClass.getDeclaredField("method");
                           } catch (NoSuchFieldException var7) {
                              connectionClass = connectionClass.getSuperclass();
                              continue;
                           }

                           methodField.setAccessible(true);
                           methodField.set(httpURLConnection, method);
                           break;
                        }
                     } catch (Exception e) {
                        throw new RuntimeException(e);
                     }
                  }

                  return null;
               }
            });
         } catch (PrivilegedActionException e) {
            Throwable cause = e.getCause();
            if (cause instanceof RuntimeException) {
               throw (RuntimeException)cause;
            }

            throw new RuntimeException(cause);
         }
      }

   }

   private void processExtensions(ClientRequest request, HttpURLConnection uc) {
      this.connectorExtension.invoke(request, uc);
   }

   private IOException handleException(ClientRequest request, IOException ex, HttpURLConnection uc) throws IOException {
      if (this.connectorExtension.handleException(request, uc, ex)) {
         return null;
      } else if (!(ex instanceof SocketTimeoutException) && uc.getResponseCode() != -1) {
         return ex;
      } else {
         throw ex;
      }
   }

   public String getName() {
      return "HttpUrlConnection " + (String)AccessController.doPrivileged(PropertiesHelper.getSystemProperty("java.version"));
   }

   static {
      restrictedHeaderSet = new HashSet(restrictedHeaders.length);

      for(String headerName : restrictedHeaders) {
         restrictedHeaderSet.add(headerName.toLowerCase(Locale.ROOT));
      }

   }

   private static class SniSSLSocketFactory extends SSLSocketFactory {
      private final SSLSocketFactory socketFactory;
      private ThreadLocal sniConfigs;

      public void setSniConfig(SSLParamConfigurator sniConfigs) {
         this.sniConfigs.set(sniConfigs);
      }

      private SniSSLSocketFactory(SSLSocketFactory socketFactory) {
         this.sniConfigs = new ThreadLocal();
         this.socketFactory = socketFactory;
      }

      public String[] getDefaultCipherSuites() {
         return this.socketFactory.getDefaultCipherSuites();
      }

      public String[] getSupportedCipherSuites() {
         return this.socketFactory.getSupportedCipherSuites();
      }

      public Socket createSocket(Socket socket, String s, int i, boolean b) throws IOException {
         Socket superSocket = this.socketFactory.createSocket(socket, s, i, b);
         this.setSNIServerName(superSocket);
         return superSocket;
      }

      public Socket createSocket(String s, int i) throws IOException, UnknownHostException {
         Socket superSocket = this.socketFactory.createSocket(s, i);
         this.setSNIServerName(superSocket);
         return superSocket;
      }

      public Socket createSocket(String s, int i, InetAddress inetAddress, int i1) throws IOException, UnknownHostException {
         Socket superSocket = this.socketFactory.createSocket(s, i, inetAddress, i1);
         this.setSNIServerName(superSocket);
         return superSocket;
      }

      public Socket createSocket(InetAddress inetAddress, int i) throws IOException {
         Socket superSocket = this.socketFactory.createSocket(inetAddress, i);
         this.setSNIServerName(superSocket);
         return superSocket;
      }

      public Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress1, int i1) throws IOException {
         Socket superSocket = this.socketFactory.createSocket(inetAddress, i, inetAddress1, i1);
         this.setSNIServerName(superSocket);
         return superSocket;
      }

      public Socket createSocket(Socket s, InputStream consumed, boolean autoClose) throws IOException {
         Socket superSocket = this.socketFactory.createSocket(s, consumed, autoClose);
         this.setSNIServerName(superSocket);
         return superSocket;
      }

      public Socket createSocket() throws IOException {
         Socket superSocket = this.socketFactory.createSocket();
         this.setSNIServerName(superSocket);
         return superSocket;
      }

      private void setSNIServerName(Socket superSocket) {
         SSLParamConfigurator sniConfig = (SSLParamConfigurator)this.sniConfigs.get();
         if (null != sniConfig && SSLSocket.class.isInstance(superSocket)) {
            sniConfig.setSNIServerName((SSLSocket)superSocket);
         }

         this.sniConfigs.remove();
      }
   }
}
