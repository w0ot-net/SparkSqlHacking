package io.vertx.core.net.impl;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.haproxy.HAProxyMessage;
import io.netty.handler.codec.haproxy.HAProxyProxiedProtocol;
import io.netty.handler.codec.haproxy.HAProxyProxiedProtocol.TransportProtocol;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.concurrent.Promise;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.core.net.SocketAddress;
import java.io.IOException;
import java.util.List;

public class HAProxyMessageCompletionHandler extends MessageToMessageDecoder {
   public static final IOException UNSUPPORTED_PROTOCOL_EXCEPTION = new IOException("Unsupported HA PROXY transport protocol");
   private static final Logger log = LoggerFactory.getLogger(HAProxyMessageCompletionHandler.class);
   private static final boolean proxyProtocolCodecFound;
   private final Promise promise;

   public static boolean canUseProxyProtocol(boolean requested) {
      if (requested && !proxyProtocolCodecFound) {
         log.warn("Proxy protocol support could not be enabled. Make sure that netty-codec-haproxy is included in your classpath");
      }

      return proxyProtocolCodecFound && requested;
   }

   public HAProxyMessageCompletionHandler(Promise promise) {
      this.promise = promise;
   }

   protected void decode(ChannelHandlerContext ctx, HAProxyMessage msg, List out) {
      HAProxyProxiedProtocol protocol = msg.proxiedProtocol();
      if (protocol.transportProtocol().equals(TransportProtocol.DGRAM)) {
         ctx.close();
         this.promise.tryFailure(UNSUPPORTED_PROTOCOL_EXCEPTION);
      } else {
         if (!protocol.equals(HAProxyProxiedProtocol.UNKNOWN)) {
            if (msg.sourceAddress() != null) {
               ctx.channel().attr(ConnectionBase.REMOTE_ADDRESS_OVERRIDE).set(this.createAddress(protocol, msg.sourceAddress(), msg.sourcePort()));
            }

            if (msg.destinationAddress() != null) {
               ctx.channel().attr(ConnectionBase.LOCAL_ADDRESS_OVERRIDE).set(this.createAddress(protocol, msg.destinationAddress(), msg.destinationPort()));
            }
         }

         ctx.pipeline().remove(this);
         this.promise.setSuccess(ctx.channel());
      }

   }

   public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
      this.promise.tryFailure(cause);
   }

   public void userEventTriggered(ChannelHandlerContext ctx, Object evt) {
      if (evt instanceof IdleStateEvent && ((IdleStateEvent)evt).state() == IdleState.ALL_IDLE) {
         ctx.close();
      } else {
         ctx.fireUserEventTriggered(evt);
      }

   }

   private SocketAddress createAddress(HAProxyProxiedProtocol protocol, String sourceAddress, int port) {
      switch (protocol) {
         case TCP4:
         case TCP6:
            return SocketAddress.inetSocketAddress(port, sourceAddress);
         case UNIX_STREAM:
            return SocketAddress.domainSocketAddress(sourceAddress);
         default:
            throw new IllegalStateException("Should never happen");
      }
   }

   static {
      boolean proxyProtocolCodecCheck = true;

      try {
         Class.forName("io.netty.handler.codec.haproxy.HAProxyMessageDecoder");
      } catch (Throwable var2) {
         proxyProtocolCodecCheck = false;
      }

      proxyProtocolCodecFound = proxyProtocolCodecCheck;
   }
}
