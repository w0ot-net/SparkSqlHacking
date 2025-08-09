package io.vertx.core.net.impl;

import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelHandler;
import io.netty.handler.ssl.SniHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslHandler;
import io.netty.util.AsyncMapping;
import io.netty.util.concurrent.ImmediateExecutor;
import io.vertx.core.VertxException;
import io.vertx.core.net.HostAndPort;
import io.vertx.core.net.SocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.TrustManager;

public class SslChannelProvider {
   private final long sslHandshakeTimeout;
   private final TimeUnit sslHandshakeTimeoutUnit;
   private final Executor workerPool;
   private final boolean useWorkerPool;
   private final boolean sni;
   private final boolean useAlpn;
   private final boolean trustAll;
   private final SslContextProvider sslContextProvider;
   private final SslContext[] sslContexts = new SslContext[2];
   private final Map[] sslContextMaps = new Map[]{new ConcurrentHashMap(), new ConcurrentHashMap()};

   public SslChannelProvider(SslContextProvider sslContextProvider, long sslHandshakeTimeout, TimeUnit sslHandshakeTimeoutUnit, boolean sni, boolean trustAll, boolean useAlpn, Executor workerPool, boolean useWorkerPool) {
      this.workerPool = workerPool;
      this.useWorkerPool = useWorkerPool;
      this.useAlpn = useAlpn;
      this.sni = sni;
      this.trustAll = trustAll;
      this.sslHandshakeTimeout = sslHandshakeTimeout;
      this.sslHandshakeTimeoutUnit = sslHandshakeTimeoutUnit;
      this.sslContextProvider = sslContextProvider;
   }

   public int sniEntrySize() {
      return this.sslContextMaps[0].size() + this.sslContextMaps[1].size();
   }

   public SslContextProvider sslContextProvider() {
      return this.sslContextProvider;
   }

   public SslContext sslClientContext(String serverName, boolean useAlpn) {
      return this.sslClientContext(serverName, useAlpn, this.trustAll);
   }

   public SslContext sslClientContext(String serverName, boolean useAlpn, boolean trustAll) {
      try {
         return this.sslContext(serverName, useAlpn, false, trustAll);
      } catch (Exception e) {
         throw new VertxException(e);
      }
   }

   public SslContext sslContext(String serverName, boolean useAlpn, boolean server, boolean trustAll) throws Exception {
      int idx = idx(useAlpn);
      if (serverName != null) {
         KeyManagerFactory kmf = this.sslContextProvider.resolveKeyManagerFactory(serverName);
         TrustManager[] trustManagers = trustAll ? null : this.sslContextProvider.resolveTrustManagers(serverName);
         if (kmf != null || trustManagers != null || !server) {
            return (SslContext)this.sslContextMaps[idx].computeIfAbsent(serverName, (s) -> this.sslContextProvider.createContext(server, kmf, trustManagers, s, useAlpn, trustAll));
         }
      }

      if (this.sslContexts[idx] == null) {
         SslContext context = this.sslContextProvider.createContext(server, (KeyManagerFactory)null, (TrustManager[])null, serverName, useAlpn, trustAll);
         this.sslContexts[idx] = context;
      }

      return this.sslContexts[idx];
   }

   public SslContext sslServerContext(boolean useAlpn) {
      try {
         return this.sslContext((String)null, useAlpn, true, false);
      } catch (Exception e) {
         throw new VertxException(e);
      }
   }

   public AsyncMapping serverNameMapping() {
      return (serverName, promise) -> {
         this.workerPool.execute(() -> {
            SslContext sslContext;
            try {
               sslContext = this.sslContext(serverName, this.useAlpn, true, false);
            } catch (Exception e) {
               promise.setFailure(e);
               return;
            }

            promise.setSuccess(sslContext);
         });
         return promise;
      };
   }

   public SslHandler createClientSslHandler(SocketAddress remoteAddress, String serverName, boolean useAlpn) {
      SslContext sslContext = this.sslClientContext(serverName, useAlpn);
      Executor delegatedTaskExec = (Executor)(this.useWorkerPool ? this.workerPool : ImmediateExecutor.INSTANCE);
      SslHandler sslHandler;
      if (remoteAddress.isDomainSocket()) {
         sslHandler = sslContext.newHandler(ByteBufAllocator.DEFAULT, delegatedTaskExec);
      } else {
         sslHandler = sslContext.newHandler(ByteBufAllocator.DEFAULT, remoteAddress.host(), remoteAddress.port(), delegatedTaskExec);
      }

      sslHandler.setHandshakeTimeout(this.sslHandshakeTimeout, this.sslHandshakeTimeoutUnit);
      return sslHandler;
   }

   public ChannelHandler createServerHandler(HostAndPort remoteAddress) {
      return (ChannelHandler)(this.sni ? this.createSniHandler(remoteAddress) : this.createServerSslHandler(this.useAlpn, remoteAddress));
   }

   private SslHandler createServerSslHandler(boolean useAlpn, HostAndPort remoteAddress) {
      SslContext sslContext = this.sslServerContext(useAlpn);
      Executor delegatedTaskExec = (Executor)(this.useWorkerPool ? this.workerPool : ImmediateExecutor.INSTANCE);
      SslHandler sslHandler;
      if (remoteAddress != null) {
         sslHandler = sslContext.newHandler(ByteBufAllocator.DEFAULT, remoteAddress.host(), remoteAddress.port(), delegatedTaskExec);
      } else {
         sslHandler = sslContext.newHandler(ByteBufAllocator.DEFAULT, delegatedTaskExec);
      }

      sslHandler.setHandshakeTimeout(this.sslHandshakeTimeout, this.sslHandshakeTimeoutUnit);
      return sslHandler;
   }

   private SniHandler createSniHandler(HostAndPort remoteAddress) {
      Executor delegatedTaskExec = (Executor)(this.useWorkerPool ? this.workerPool : ImmediateExecutor.INSTANCE);
      return new VertxSniHandler(this.serverNameMapping(), this.sslHandshakeTimeoutUnit.toMillis(this.sslHandshakeTimeout), delegatedTaskExec, remoteAddress);
   }

   private static int idx(boolean useAlpn) {
      return useAlpn ? 0 : 1;
   }
}
