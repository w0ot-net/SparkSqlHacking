package io.netty.bootstrap;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPromise;
import io.netty.channel.DefaultChannelPromise;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.ReflectiveChannelFactory;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.EventExecutor;
import io.netty.util.concurrent.GlobalEventExecutor;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.SocketUtils;
import io.netty.util.internal.StringUtil;
import io.netty.util.internal.logging.InternalLogger;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractBootstrap implements Cloneable {
   private static final Map.Entry[] EMPTY_OPTION_ARRAY = new Map.Entry[0];
   private static final Map.Entry[] EMPTY_ATTRIBUTE_ARRAY = new Map.Entry[0];
   volatile EventLoopGroup group;
   private volatile ChannelFactory channelFactory;
   private volatile SocketAddress localAddress;
   private final Map options = new LinkedHashMap();
   private final Map attrs = new ConcurrentHashMap();
   private volatile ChannelHandler handler;
   private volatile ClassLoader extensionsClassLoader;

   AbstractBootstrap() {
   }

   AbstractBootstrap(AbstractBootstrap bootstrap) {
      this.group = bootstrap.group;
      this.channelFactory = bootstrap.channelFactory;
      this.handler = bootstrap.handler;
      this.localAddress = bootstrap.localAddress;
      synchronized(bootstrap.options) {
         this.options.putAll(bootstrap.options);
      }

      this.attrs.putAll(bootstrap.attrs);
      this.extensionsClassLoader = bootstrap.extensionsClassLoader;
   }

   public AbstractBootstrap group(EventLoopGroup group) {
      ObjectUtil.checkNotNull(group, "group");
      if (this.group != null) {
         throw new IllegalStateException("group set already");
      } else {
         this.group = group;
         return this.self();
      }
   }

   private AbstractBootstrap self() {
      return this;
   }

   public AbstractBootstrap channel(Class channelClass) {
      return this.channelFactory((io.netty.channel.ChannelFactory)(new ReflectiveChannelFactory((Class)ObjectUtil.checkNotNull(channelClass, "channelClass"))));
   }

   /** @deprecated */
   @Deprecated
   public AbstractBootstrap channelFactory(ChannelFactory channelFactory) {
      ObjectUtil.checkNotNull(channelFactory, "channelFactory");
      if (this.channelFactory != null) {
         throw new IllegalStateException("channelFactory set already");
      } else {
         this.channelFactory = channelFactory;
         return this.self();
      }
   }

   public AbstractBootstrap channelFactory(io.netty.channel.ChannelFactory channelFactory) {
      return this.channelFactory((ChannelFactory)channelFactory);
   }

   public AbstractBootstrap localAddress(SocketAddress localAddress) {
      this.localAddress = localAddress;
      return this.self();
   }

   public AbstractBootstrap localAddress(int inetPort) {
      return this.localAddress(new InetSocketAddress(inetPort));
   }

   public AbstractBootstrap localAddress(String inetHost, int inetPort) {
      return this.localAddress(SocketUtils.socketAddress(inetHost, inetPort));
   }

   public AbstractBootstrap localAddress(InetAddress inetHost, int inetPort) {
      return this.localAddress(new InetSocketAddress(inetHost, inetPort));
   }

   public AbstractBootstrap option(ChannelOption option, Object value) {
      ObjectUtil.checkNotNull(option, "option");
      synchronized(this.options) {
         if (value == null) {
            this.options.remove(option);
         } else {
            this.options.put(option, value);
         }
      }

      return this.self();
   }

   public AbstractBootstrap attr(AttributeKey key, Object value) {
      ObjectUtil.checkNotNull(key, "key");
      if (value == null) {
         this.attrs.remove(key);
      } else {
         this.attrs.put(key, value);
      }

      return this.self();
   }

   public AbstractBootstrap extensionsClassLoader(ClassLoader classLoader) {
      this.extensionsClassLoader = classLoader;
      return this.self();
   }

   public AbstractBootstrap validate() {
      if (this.group == null) {
         throw new IllegalStateException("group not set");
      } else if (this.channelFactory == null) {
         throw new IllegalStateException("channel or channelFactory not set");
      } else {
         return this.self();
      }
   }

   public abstract AbstractBootstrap clone();

   public ChannelFuture register() {
      this.validate();
      return this.initAndRegister();
   }

   public ChannelFuture bind() {
      this.validate();
      SocketAddress localAddress = this.localAddress;
      if (localAddress == null) {
         throw new IllegalStateException("localAddress not set");
      } else {
         return this.doBind(localAddress);
      }
   }

   public ChannelFuture bind(int inetPort) {
      return this.bind(new InetSocketAddress(inetPort));
   }

   public ChannelFuture bind(String inetHost, int inetPort) {
      return this.bind(SocketUtils.socketAddress(inetHost, inetPort));
   }

   public ChannelFuture bind(InetAddress inetHost, int inetPort) {
      return this.bind(new InetSocketAddress(inetHost, inetPort));
   }

   public ChannelFuture bind(SocketAddress localAddress) {
      this.validate();
      return this.doBind((SocketAddress)ObjectUtil.checkNotNull(localAddress, "localAddress"));
   }

   private ChannelFuture doBind(final SocketAddress localAddress) {
      final ChannelFuture regFuture = this.initAndRegister();
      final Channel channel = regFuture.channel();
      if (regFuture.cause() != null) {
         return regFuture;
      } else if (regFuture.isDone()) {
         ChannelPromise promise = channel.newPromise();
         doBind0(regFuture, channel, localAddress, promise);
         return promise;
      } else {
         final PendingRegistrationPromise promise = new PendingRegistrationPromise(channel);
         regFuture.addListener(new ChannelFutureListener() {
            public void operationComplete(ChannelFuture future) throws Exception {
               Throwable cause = future.cause();
               if (cause != null) {
                  promise.setFailure(cause);
               } else {
                  promise.registered();
                  AbstractBootstrap.doBind0(regFuture, channel, localAddress, promise);
               }

            }
         });
         return promise;
      }
   }

   final ChannelFuture initAndRegister() {
      Channel channel = null;

      try {
         channel = this.channelFactory.newChannel();
         this.init(channel);
      } catch (Throwable t) {
         if (channel != null) {
            channel.unsafe().closeForcibly();
            return (new DefaultChannelPromise(channel, GlobalEventExecutor.INSTANCE)).setFailure(t);
         }

         return (new DefaultChannelPromise(new FailedChannel(), GlobalEventExecutor.INSTANCE)).setFailure(t);
      }

      ChannelFuture regFuture = this.config().group().register(channel);
      if (regFuture.cause() != null) {
         if (channel.isRegistered()) {
            channel.close();
         } else {
            channel.unsafe().closeForcibly();
         }
      }

      return regFuture;
   }

   abstract void init(Channel var1) throws Exception;

   Collection getInitializerExtensions() {
      ClassLoader loader = this.extensionsClassLoader;
      if (loader == null) {
         loader = this.getClass().getClassLoader();
      }

      return ChannelInitializerExtensions.getExtensions().extensions(loader);
   }

   private static void doBind0(final ChannelFuture regFuture, final Channel channel, final SocketAddress localAddress, final ChannelPromise promise) {
      channel.eventLoop().execute(new Runnable() {
         public void run() {
            if (regFuture.isSuccess()) {
               channel.bind(localAddress, promise).addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
            } else {
               promise.setFailure(regFuture.cause());
            }

         }
      });
   }

   public AbstractBootstrap handler(ChannelHandler handler) {
      this.handler = (ChannelHandler)ObjectUtil.checkNotNull(handler, "handler");
      return this.self();
   }

   /** @deprecated */
   @Deprecated
   public final EventLoopGroup group() {
      return this.group;
   }

   public abstract AbstractBootstrapConfig config();

   final Map.Entry[] newOptionsArray() {
      return newOptionsArray(this.options);
   }

   static Map.Entry[] newOptionsArray(Map options) {
      synchronized(options) {
         return (Map.Entry[])(new LinkedHashMap(options)).entrySet().toArray(EMPTY_OPTION_ARRAY);
      }
   }

   final Map.Entry[] newAttributesArray() {
      return newAttributesArray(this.attrs0());
   }

   static Map.Entry[] newAttributesArray(Map attributes) {
      return (Map.Entry[])attributes.entrySet().toArray(EMPTY_ATTRIBUTE_ARRAY);
   }

   final Map options0() {
      return this.options;
   }

   final Map attrs0() {
      return this.attrs;
   }

   final SocketAddress localAddress() {
      return this.localAddress;
   }

   final ChannelFactory channelFactory() {
      return this.channelFactory;
   }

   final ChannelHandler handler() {
      return this.handler;
   }

   final Map options() {
      synchronized(this.options) {
         return copiedMap(this.options);
      }
   }

   final Map attrs() {
      return copiedMap(this.attrs);
   }

   static Map copiedMap(Map map) {
      return map.isEmpty() ? Collections.emptyMap() : Collections.unmodifiableMap(new HashMap(map));
   }

   static void setAttributes(Channel channel, Map.Entry[] attrs) {
      for(Map.Entry e : attrs) {
         AttributeKey<Object> key = (AttributeKey)e.getKey();
         channel.attr(key).set(e.getValue());
      }

   }

   static void setChannelOptions(Channel channel, Map.Entry[] options, InternalLogger logger) {
      for(Map.Entry e : options) {
         setChannelOption(channel, (ChannelOption)e.getKey(), e.getValue(), logger);
      }

   }

   private static void setChannelOption(Channel channel, ChannelOption option, Object value, InternalLogger logger) {
      try {
         if (!channel.config().setOption(option, value)) {
            logger.warn("Unknown channel option '{}' for channel '{}'", option, channel);
         }
      } catch (Throwable t) {
         logger.warn("Failed to set channel option '{}' with value '{}' for channel '{}'", new Object[]{option, value, channel, t});
      }

   }

   public String toString() {
      StringBuilder buf = (new StringBuilder()).append(StringUtil.simpleClassName(this)).append('(').append(this.config()).append(')');
      return buf.toString();
   }

   static final class PendingRegistrationPromise extends DefaultChannelPromise {
      private volatile boolean registered;

      PendingRegistrationPromise(Channel channel) {
         super(channel);
      }

      void registered() {
         this.registered = true;
      }

      protected EventExecutor executor() {
         return (EventExecutor)(this.registered ? super.executor() : GlobalEventExecutor.INSTANCE);
      }
   }
}
