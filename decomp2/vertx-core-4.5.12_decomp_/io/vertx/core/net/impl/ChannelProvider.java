package io.vertx.core.net.impl;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.proxy.HttpProxyHandler;
import io.netty.handler.proxy.ProxyConnectionEvent;
import io.netty.handler.proxy.ProxyHandler;
import io.netty.handler.proxy.Socks4ProxyHandler;
import io.netty.handler.proxy.Socks5ProxyHandler;
import io.netty.handler.ssl.SslHandler;
import io.netty.handler.ssl.SslHandshakeCompletionEvent;
import io.netty.resolver.NoopAddressResolverGroup;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.Promise;
import io.vertx.core.Handler;
import io.vertx.core.impl.ContextInternal;
import io.vertx.core.impl.VertxInternal;
import io.vertx.core.net.ProxyOptions;
import io.vertx.core.net.ProxyType;
import io.vertx.core.net.SocketAddress;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import javax.net.ssl.SSLHandshakeException;

public final class ChannelProvider {
   private final Bootstrap bootstrap;
   private final SslChannelProvider sslContextProvider;
   private final ContextInternal context;
   private ProxyOptions proxyOptions;
   private String applicationProtocol;
   private Handler handler;

   public ChannelProvider(Bootstrap bootstrap, SslChannelProvider sslContextProvider, ContextInternal context) {
      this.bootstrap = bootstrap;
      this.context = context;
      this.sslContextProvider = sslContextProvider;
   }

   public ChannelProvider proxyOptions(ProxyOptions proxyOptions) {
      this.proxyOptions = proxyOptions;
      return this;
   }

   public ChannelProvider handler(Handler handler) {
      this.handler = handler;
      return this;
   }

   public String applicationProtocol() {
      return this.applicationProtocol;
   }

   public Future connect(SocketAddress remoteAddress, SocketAddress peerAddress, String serverName, boolean ssl, boolean useAlpn) {
      Promise<Channel> p = this.context.nettyEventLoop().newPromise();
      this.connect(this.handler, remoteAddress, peerAddress, serverName, ssl, useAlpn, p);
      return p;
   }

   private void connect(Handler handler, SocketAddress remoteAddress, SocketAddress peerAddress, String serverName, boolean ssl, boolean useAlpn, Promise p) {
      try {
         this.bootstrap.channelFactory(this.context.owner().transport().channelFactory(remoteAddress.isDomainSocket()));
      } catch (Exception e) {
         p.setFailure(e);
         return;
      }

      if (this.proxyOptions != null) {
         this.handleProxyConnect(handler, remoteAddress, peerAddress, serverName, ssl, useAlpn, p);
      } else {
         this.handleConnect(handler, remoteAddress, peerAddress, serverName, ssl, useAlpn, p);
      }

   }

   private void initSSL(final Handler handler, SocketAddress peerAddress, String serverName, boolean ssl, boolean useAlpn, final Channel ch, final Promise channelHandler) {
      if (ssl) {
         final SslHandler sslHandler = this.sslContextProvider.createClientSslHandler(peerAddress, serverName, useAlpn);
         ChannelPipeline pipeline = ch.pipeline();
         pipeline.addLast("ssl", sslHandler);
         pipeline.addLast(new ChannelHandler[]{new ChannelInboundHandlerAdapter() {
            public void userEventTriggered(ChannelHandlerContext ctx, Object evt) {
               if (evt instanceof SslHandshakeCompletionEvent) {
                  SslHandshakeCompletionEvent completion = (SslHandshakeCompletionEvent)evt;
                  if (completion.isSuccess()) {
                     ctx.pipeline().remove(this);
                     ChannelProvider.this.applicationProtocol = sslHandler.applicationProtocol();
                     if (handler != null) {
                        ChannelProvider.this.context.dispatch(ch, handler);
                     }

                     channelHandler.setSuccess(ctx.channel());
                  } else {
                     SSLHandshakeException sslException = new SSLHandshakeException("Failed to create SSL connection");
                     sslException.initCause(completion.cause());
                     channelHandler.setFailure(sslException);
                  }
               }

               ctx.fireUserEventTriggered(evt);
            }

            public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
            }
         }});
      }

   }

   private void handleConnect(final Handler handler, SocketAddress remoteAddress, final SocketAddress peerAddress, final String serverName, final boolean ssl, final boolean useAlpn, final Promise channelHandler) {
      VertxInternal vertx = this.context.owner();
      this.bootstrap.resolver(vertx.nettyAddressResolverGroup());
      this.bootstrap.handler(new ChannelInitializer() {
         protected void initChannel(Channel ch) {
            ChannelProvider.this.initSSL(handler, peerAddress, serverName, ssl, useAlpn, ch, channelHandler);
         }
      });
      ChannelFuture fut = this.bootstrap.connect(vertx.transport().convert(remoteAddress));
      fut.addListener((res) -> {
         if (res.isSuccess()) {
            this.connected(handler, fut.channel(), ssl, channelHandler);
         } else {
            channelHandler.setFailure(res.cause());
         }

      });
   }

   private void connected(Handler handler, Channel channel, boolean ssl, Promise channelHandler) {
      if (!ssl) {
         if (handler != null) {
            this.context.dispatch(channel, handler);
         }

         channelHandler.setSuccess(channel);
      }

   }

   private void handleProxyConnect(Handler handler, SocketAddress remoteAddress, SocketAddress peerAddress, String serverName, boolean ssl, boolean useAlpn, Promise channelHandler) {
      VertxInternal vertx = this.context.owner();
      String proxyHost = this.proxyOptions.getHost();
      int proxyPort = this.proxyOptions.getPort();
      String proxyUsername = this.proxyOptions.getUsername();
      String proxyPassword = this.proxyOptions.getPassword();
      ProxyType proxyType = this.proxyOptions.getType();
      vertx.resolveAddress(proxyHost, (dnsRes) -> {
         if (dnsRes.succeeded()) {
            InetAddress address = (InetAddress)dnsRes.result();
            InetSocketAddress proxyAddr = new InetSocketAddress(address, proxyPort);
            final ProxyHandler proxy;
            switch (proxyType) {
               case HTTP:
               default:
                  proxy = proxyUsername != null && proxyPassword != null ? new HttpProxyHandler(proxyAddr, proxyUsername, proxyPassword) : new HttpProxyHandler(proxyAddr);
                  break;
               case SOCKS5:
                  proxy = proxyUsername != null && proxyPassword != null ? new Socks5ProxyHandler(proxyAddr, proxyUsername, proxyPassword) : new Socks5ProxyHandler(proxyAddr);
                  break;
               case SOCKS4:
                  proxy = proxyUsername != null ? new Socks4ProxyHandler(proxyAddr, proxyUsername) : new Socks4ProxyHandler(proxyAddr);
            }

            this.bootstrap.resolver(NoopAddressResolverGroup.INSTANCE);
            java.net.SocketAddress targetAddress = vertx.transport().convert(remoteAddress);
            this.bootstrap.handler(new ChannelInitializer() {
               protected void initChannel(final Channel ch) throws Exception {
                  final ChannelPipeline pipeline = ch.pipeline();
                  pipeline.addFirst("proxy", proxy);
                  pipeline.addLast(new ChannelHandler[]{new ChannelInboundHandlerAdapter() {
                     public void userEventTriggered(ChannelHandlerContext ctx, Object evt) {
                        if (evt instanceof ProxyConnectionEvent) {
                           pipeline.remove(proxy);
                           pipeline.remove(this);
                           ChannelProvider.this.initSSL(handler, peerAddress, serverName, ssl, useAlpn, ch, channelHandler);
                           ChannelProvider.this.connected(handler, ch, ssl, channelHandler);
                        }

                        ctx.fireUserEventTriggered(evt);
                     }

                     public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
                        channelHandler.setFailure(cause);
                     }
                  }});
               }
            });
            ChannelFuture future = this.bootstrap.connect(targetAddress);
            future.addListener((res) -> {
               if (!res.isSuccess()) {
                  channelHandler.setFailure(res.cause());
               }

            });
         } else {
            channelHandler.setFailure(dnsRes.cause());
         }

      });
   }
}
