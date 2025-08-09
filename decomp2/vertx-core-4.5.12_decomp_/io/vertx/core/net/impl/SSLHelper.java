package io.vertx.core.net.impl;

import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.handler.ssl.OpenSsl;
import io.netty.handler.ssl.SslProvider;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.VertxException;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.buffer.impl.PartialPooledByteBufAllocator;
import io.vertx.core.file.FileSystem;
import io.vertx.core.http.ClientAuth;
import io.vertx.core.impl.ContextInternal;
import io.vertx.core.net.ClientOptionsBase;
import io.vertx.core.net.JdkSSLEngineOptions;
import io.vertx.core.net.KeyCertOptions;
import io.vertx.core.net.NetClientOptions;
import io.vertx.core.net.NetServerOptions;
import io.vertx.core.net.OpenSSLEngineOptions;
import io.vertx.core.net.SSLEngineOptions;
import io.vertx.core.net.SSLOptions;
import io.vertx.core.net.TCPSSLOptions;
import io.vertx.core.net.TrustOptions;
import io.vertx.core.spi.tls.DefaultSslContextFactory;
import io.vertx.core.spi.tls.SslContextFactory;
import java.io.ByteArrayInputStream;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.TrustManagerFactory;

public class SSLHelper {
   private static final AtomicLong seq = new AtomicLong();
   static final EnumMap CLIENT_AUTH_MAPPING = new EnumMap(ClientAuth.class);
   private final boolean ssl;
   private final boolean sni;
   private final boolean trustAll;
   private final ClientAuth clientAuth;
   private final boolean client;
   private final boolean useAlpn;
   private final String endpointIdentificationAlgorithm;
   private final SSLEngineOptions sslEngineOptions;
   private final List applicationProtocols;
   private KeyManagerFactory keyManagerFactory;
   private TrustManagerFactory trustManagerFactory;
   private Function keyManagerFactoryMapper;
   private Function trustManagerMapper;
   private List crls;
   private Future cachedProvider;

   ByteBufAllocator clientByteBufAllocator(SslContextProvider ctxProvider) {
      return (ByteBufAllocator)(this.usesJDKSSLWithPooledHeapBuffers(ctxProvider) ? PooledByteBufAllocator.DEFAULT : PartialPooledByteBufAllocator.INSTANCE);
   }

   ByteBufAllocator serverByteBufAllocator(SslContextProvider ctxProvider) {
      return (ByteBufAllocator)(this.ssl && !this.usesJDKSSLWithPooledHeapBuffers(ctxProvider) ? PartialPooledByteBufAllocator.INSTANCE : PooledByteBufAllocator.DEFAULT);
   }

   private boolean usesJDKSSLWithPooledHeapBuffers(SslContextProvider ctxProvider) {
      return this.ssl && this.sslEngineOptions instanceof JdkSSLEngineOptions && ctxProvider.jdkSSLProvider() && ((JdkSSLEngineOptions)this.sslEngineOptions).isPooledHeapBuffers();
   }

   public static SSLEngineOptions resolveEngineOptions(SSLEngineOptions engineOptions, boolean useAlpn) {
      if (engineOptions == null && useAlpn) {
         if (JdkSSLEngineOptions.isAlpnAvailable()) {
            engineOptions = new JdkSSLEngineOptions();
         } else if (OpenSSLEngineOptions.isAlpnAvailable()) {
            engineOptions = new OpenSSLEngineOptions();
         }
      }

      if (engineOptions == null) {
         engineOptions = new JdkSSLEngineOptions();
      } else if (engineOptions instanceof OpenSSLEngineOptions && !OpenSsl.isAvailable()) {
         VertxException ex = new VertxException("OpenSSL is not available");
         Throwable cause = OpenSsl.unavailabilityCause();
         if (cause != null) {
            ex.initCause(cause);
         }

         throw ex;
      }

      if (useAlpn) {
         if (engineOptions instanceof JdkSSLEngineOptions && !JdkSSLEngineOptions.isAlpnAvailable()) {
            throw new VertxException("ALPN not available for JDK SSL/TLS engine");
         }

         if (engineOptions instanceof OpenSSLEngineOptions && !OpenSSLEngineOptions.isAlpnAvailable()) {
            throw new VertxException("ALPN is not available for OpenSSL SSL/TLS engine");
         }
      }

      return engineOptions;
   }

   public SSLHelper(TCPSSLOptions options, List applicationProtocols) {
      this.sslEngineOptions = options.getSslEngineOptions();
      this.ssl = options.isSsl();
      this.useAlpn = options.isUseAlpn();
      this.client = options instanceof ClientOptionsBase;
      this.trustAll = options instanceof ClientOptionsBase && ((ClientOptionsBase)options).isTrustAll();
      this.clientAuth = options instanceof NetServerOptions ? ((NetServerOptions)options).getClientAuth() : ClientAuth.NONE;
      this.endpointIdentificationAlgorithm = options instanceof NetClientOptions ? ((NetClientOptions)options).getHostnameVerificationAlgorithm() : "";
      this.sni = options instanceof NetServerOptions && ((NetServerOptions)options).isSni();
      this.applicationProtocols = applicationProtocols;
   }

   public synchronized int sniEntrySize() {
      CachedProvider res = (CachedProvider)this.cachedProvider.result();
      return res != null ? res.sslChannelProvider.sniEntrySize() : 0;
   }

   public Future updateSslContext(SSLOptions options, boolean force, ContextInternal ctx) {
      long id = seq.getAndIncrement();
      synchronized(this) {
         if (this.cachedProvider == null) {
            this.cachedProvider = this.buildChannelProvider(options, ctx).map((Function)((a) -> new CachedProvider(options, id, a, (Throwable)null)));
         } else {
            this.cachedProvider = this.cachedProvider.transform((prev) -> !force && prev.succeeded() && ((CachedProvider)prev.result()).options.equals(options) ? Future.succeededFuture(prev.result()) : this.buildChannelProvider(options, ctx).transform((ar) -> {
                  if (ar.succeeded()) {
                     return ctx.succeededFuture(new CachedProvider(options, id, (SslChannelProvider)ar.result(), (Throwable)null));
                  } else {
                     return prev.succeeded() ? ctx.succeededFuture(new CachedProvider(((CachedProvider)prev.result()).options, ((CachedProvider)prev.result()).id, ((CachedProvider)prev.result()).sslChannelProvider, ar.cause())) : ctx.failedFuture(prev.cause());
                  }
               }));
         }

         return this.cachedProvider.map((Function)((c) -> new SslContextUpdate(c.sslChannelProvider, c.id == id, c.failure)));
      }
   }

   public Future buildContextProvider(SSLOptions sslOptions, ContextInternal ctx) {
      return this.build(new SSLOptions(sslOptions), ctx).map(EngineConfig::sslContextProvider);
   }

   public Future buildChannelProvider(SSLOptions sslOptions, ContextInternal ctx) {
      return this.build(new SSLOptions(sslOptions), ctx).map((Function)((c) -> new SslChannelProvider(c.sslContextProvider(), c.sslOptions.getSslHandshakeTimeout(), c.sslOptions.getSslHandshakeTimeoutUnit(), this.sni, this.trustAll, this.useAlpn, ctx.owner().getInternalWorkerPool().executor(), c.useWorkerPool)));
   }

   private Future build(SSLOptions sslOptions, ContextInternal ctx) {
      KeyCertOptions keyCertOptions = sslOptions.getKeyCertOptions();
      TrustOptions trustOptions = sslOptions.getTrustOptions();
      Future<EngineConfig> sslContextFactorySupplier;
      if (keyCertOptions == null && trustOptions == null && !this.trustAll && !this.ssl) {
         sslContextFactorySupplier = Future.succeededFuture(new EngineConfig(true, sslOptions, () -> new DefaultSslContextFactory(SslProvider.JDK, false), false));
      } else {
         Promise<EngineConfig> promise = Promise.promise();
         sslContextFactorySupplier = promise.future();
         ctx.executeBlockingInternal((Handler)((p) -> {
            try {
               if (sslOptions.getKeyCertOptions() != null) {
                  this.keyManagerFactory = sslOptions.getKeyCertOptions().getKeyManagerFactory(ctx.owner());
                  this.keyManagerFactoryMapper = sslOptions.getKeyCertOptions().keyManagerFactoryMapper(ctx.owner());
               }

               if (sslOptions.getTrustOptions() != null) {
                  this.trustManagerFactory = sslOptions.getTrustOptions().getTrustManagerFactory(ctx.owner());
                  this.trustManagerMapper = sslOptions.getTrustOptions().trustManagerMapper(ctx.owner());
               }

               this.crls = new ArrayList();
               List<Buffer> tmp = new ArrayList();
               if (sslOptions.getCrlPaths() != null) {
                  Stream var10001 = sslOptions.getCrlPaths().stream().map((path) -> ctx.owner().resolveFile(path).getAbsolutePath());
                  FileSystem var10002 = ctx.owner().fileSystem();
                  var10002.getClass();
                  tmp.addAll((Collection)var10001.map(var10002::readFileBlocking).collect(Collectors.toList()));
               }

               if (sslOptions.getCrlValues() != null) {
                  tmp.addAll(sslOptions.getCrlValues());
               }

               CertificateFactory certificatefactory = CertificateFactory.getInstance("X.509");

               for(Buffer crlValue : tmp) {
                  this.crls.addAll(certificatefactory.generateCRLs(new ByteArrayInputStream(crlValue.getBytes())));
               }
            } catch (Exception e) {
               p.fail((Throwable)e);
               return;
            }

            if (!this.client && sslOptions.getKeyCertOptions() == null) {
               p.fail("Key/certificate is mandatory for SSL");
            } else {
               p.complete();
            }

         })).compose((v2) -> ctx.executeBlockingInternal((Handler)((p) -> {
               Supplier<SslContextFactory> supplier;
               boolean useWorkerPool;
               boolean jdkSSLProvider;
               try {
                  SSLEngineOptions resolvedEngineOptions = resolveEngineOptions(this.sslEngineOptions, this.useAlpn);
                  supplier = resolvedEngineOptions::sslContextFactory;
                  useWorkerPool = resolvedEngineOptions.getUseWorkerThread();
                  jdkSSLProvider = resolvedEngineOptions instanceof JdkSSLEngineOptions;
               } catch (Exception e) {
                  p.fail((Throwable)e);
                  return;
               }

               p.complete(new EngineConfig(jdkSSLProvider, sslOptions, supplier, useWorkerPool));
            }))).onComplete(promise);
      }

      return sslContextFactorySupplier;
   }

   static {
      CLIENT_AUTH_MAPPING.put(ClientAuth.REQUIRED, io.netty.handler.ssl.ClientAuth.REQUIRE);
      CLIENT_AUTH_MAPPING.put(ClientAuth.REQUEST, io.netty.handler.ssl.ClientAuth.OPTIONAL);
      CLIENT_AUTH_MAPPING.put(ClientAuth.NONE, io.netty.handler.ssl.ClientAuth.NONE);
   }

   private static class CachedProvider {
      final SSLOptions options;
      final long id;
      final SslChannelProvider sslChannelProvider;
      final Throwable failure;

      CachedProvider(SSLOptions options, long id, SslChannelProvider sslChannelProvider, Throwable failure) {
         this.options = options;
         this.id = id;
         this.sslChannelProvider = sslChannelProvider;
         this.failure = failure;
      }
   }

   private class EngineConfig {
      private final boolean jdkSSLProvider;
      private final SSLOptions sslOptions;
      private final Supplier supplier;
      private final boolean useWorkerPool;

      public EngineConfig(boolean jdkSSLProvider, SSLOptions sslOptions, Supplier supplier, boolean useWorkerPool) {
         this.jdkSSLProvider = jdkSSLProvider;
         this.sslOptions = sslOptions;
         this.supplier = supplier;
         this.useWorkerPool = useWorkerPool;
      }

      SslContextProvider sslContextProvider() {
         return new SslContextProvider(this.jdkSSLProvider, SSLHelper.this.clientAuth, SSLHelper.this.endpointIdentificationAlgorithm, SSLHelper.this.applicationProtocols, this.sslOptions.getEnabledCipherSuites(), this.sslOptions.getEnabledSecureTransportProtocols(), SSLHelper.this.keyManagerFactory, SSLHelper.this.keyManagerFactoryMapper, SSLHelper.this.trustManagerFactory, SSLHelper.this.trustManagerMapper, SSLHelper.this.crls, this.supplier);
      }
   }
}
