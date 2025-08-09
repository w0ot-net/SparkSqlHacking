package io.vertx.core.impl;

import io.netty.channel.EventLoop;
import io.netty.resolver.AddressResolverGroup;
import io.netty.util.concurrent.Future;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.dns.AddressResolverOptions;
import io.vertx.core.impl.future.PromiseInternal;
import io.vertx.core.impl.launcher.commands.ExecUtils;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.core.spi.resolver.ResolverProvider;
import java.io.File;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddressResolver {
   private static final Logger log = LoggerFactory.getLogger(AddressResolver.class);
   private static final Pattern NDOTS_OPTIONS_PATTERN = resolvOption("ndots:[ \\t\\f]*(\\d)+");
   private static final Pattern ROTATE_OPTIONS_PATTERN = resolvOption("rotate");
   public static final int DEFAULT_NDOTS_RESOLV_OPTION;
   public static final boolean DEFAULT_ROTATE_RESOLV_OPTION;
   private final Vertx vertx;
   private final AddressResolverGroup resolverGroup;
   private final ResolverProvider provider;

   private static Pattern resolvOption(String regex) {
      return Pattern.compile("^[ \\t\\f]*options[^\n]+" + regex + "(?=$|\\s)", 8);
   }

   public AddressResolver(Vertx vertx, AddressResolverOptions options) {
      this.provider = ResolverProvider.factory(vertx, options);
      this.resolverGroup = this.provider.resolver(options);
      this.vertx = vertx;
   }

   public void resolveHostname(String hostname, Handler resultHandler) {
      ContextInternal context = (ContextInternal)this.vertx.getOrCreateContext();
      Future<InetSocketAddress> fut = this.resolveHostname(context.nettyEventLoop(), hostname);
      PromiseInternal<InetSocketAddress> promise = context.promise();
      fut.addListener(promise);
      promise.future().map(InetSocketAddress::getAddress).onComplete(resultHandler);
   }

   public Future resolveHostname(EventLoop eventLoop, String hostname) {
      io.netty.resolver.AddressResolver<InetSocketAddress> resolver = this.getResolver(eventLoop);
      return resolver.resolve(InetSocketAddress.createUnresolved(hostname, 0));
   }

   public void resolveHostnameAll(String hostname, Handler resultHandler) {
      ContextInternal context = (ContextInternal)this.vertx.getOrCreateContext();
      Future<List<InetSocketAddress>> fut = this.resolveHostnameAll(context.nettyEventLoop(), hostname);
      PromiseInternal<List<InetSocketAddress>> promise = context.promise();
      fut.addListener(promise);
      promise.future().onComplete(resultHandler);
   }

   public Future resolveHostnameAll(EventLoop eventLoop, String hostname) {
      io.netty.resolver.AddressResolver<InetSocketAddress> resolver = this.getResolver(eventLoop);
      return resolver.resolveAll(InetSocketAddress.createUnresolved(hostname, 0));
   }

   public io.netty.resolver.AddressResolver getResolver(EventLoop eventLoop) {
      return this.resolverGroup.getResolver(eventLoop);
   }

   AddressResolverGroup nettyAddressResolverGroup() {
      return this.resolverGroup;
   }

   public void close(Handler doneHandler) {
      this.provider.close(doneHandler);
   }

   public static int parseNdotsOptionFromResolvConf(String s) {
      int ndots = -1;

      for(Matcher matcher = NDOTS_OPTIONS_PATTERN.matcher(s); matcher.find(); ndots = Integer.parseInt(matcher.group(1))) {
      }

      return ndots;
   }

   public static boolean parseRotateOptionFromResolvConf(String s) {
      Matcher matcher = ROTATE_OPTIONS_PATTERN.matcher(s);
      return matcher.find();
   }

   static {
      int ndots = 1;
      boolean rotate = false;
      if (ExecUtils.isLinux()) {
         File f = new File("/etc/resolv.conf");

         try {
            if (f.exists() && f.isFile()) {
               String conf = new String(Files.readAllBytes(f.toPath()));
               int ndotsOption = parseNdotsOptionFromResolvConf(conf);
               if (ndotsOption != -1) {
                  ndots = ndotsOption;
               }

               rotate = parseRotateOptionFromResolvConf(conf);
            }
         } catch (Throwable t) {
            log.debug("Failed to load options from /etc/resolv/.conf", t);
         }
      }

      DEFAULT_NDOTS_RESOLV_OPTION = ndots;
      DEFAULT_ROTATE_RESOLV_OPTION = rotate;
   }
}
