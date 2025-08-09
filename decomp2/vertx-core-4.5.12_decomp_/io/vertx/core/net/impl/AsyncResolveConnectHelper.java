package io.vertx.core.net.impl;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.Promise;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.impl.VertxInternal;
import io.vertx.core.net.SocketAddress;
import java.net.InetAddress;
import java.net.InetSocketAddress;

public class AsyncResolveConnectHelper {
   public static Future doBind(VertxInternal vertx, SocketAddress socketAddress, ServerBootstrap bootstrap) {
      Promise<Channel> promise = vertx.getAcceptorEventLoopGroup().next().newPromise();

      try {
         bootstrap.channelFactory(vertx.transport().serverChannelFactory(socketAddress.isDomainSocket()));
      } catch (Exception e) {
         promise.setFailure(e);
         return promise;
      }

      if (socketAddress.isDomainSocket()) {
         java.net.SocketAddress converted = vertx.transport().convert(socketAddress);
         ChannelFuture future = bootstrap.bind(converted);
         future.addListener((f) -> {
            if (f.isSuccess()) {
               promise.setSuccess(future.channel());
            } else {
               promise.setFailure(f.cause());
            }

         });
      } else {
         SocketAddressImpl impl = (SocketAddressImpl)socketAddress;
         Handler<AsyncResult<InetAddress>> cont = (res) -> {
            if (res.succeeded()) {
               InetSocketAddress t = new InetSocketAddress((InetAddress)res.result(), socketAddress.port());
               ChannelFuture future = bootstrap.bind(t);
               future.addListener((f) -> {
                  if (f.isSuccess()) {
                     promise.setSuccess(future.channel());
                  } else {
                     promise.setFailure(f.cause());
                  }

               });
            } else {
               promise.setFailure(res.cause());
            }

         };
         if (impl.ipAddress() != null) {
            cont.handle(io.vertx.core.Future.succeededFuture(impl.ipAddress()));
         } else {
            vertx.resolveAddress(socketAddress.host(), cont);
         }
      }

      return promise;
   }
}
