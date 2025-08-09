package org.apache.spark.network.util;

import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.epoll.EpollSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.DefaultThreadFactory;
import io.netty.util.internal.PlatformDependent;
import java.util.concurrent.ThreadFactory;

public class NettyUtils {
   private static int MAX_DEFAULT_NETTY_THREADS = 8;
   private static final PooledByteBufAllocator[] _sharedPooledByteBufAllocator = new PooledByteBufAllocator[2];

   public static long freeDirectMemory() {
      return PlatformDependent.maxDirectMemory() - PlatformDependent.usedDirectMemory();
   }

   public static ThreadFactory createThreadFactory(String threadPoolPrefix) {
      return new DefaultThreadFactory(threadPoolPrefix, true);
   }

   public static EventLoopGroup createEventLoop(IOMode mode, int numThreads, String threadPrefix) {
      ThreadFactory threadFactory = createThreadFactory(threadPrefix);
      Object var10000;
      switch (mode) {
         case NIO -> var10000 = new NioEventLoopGroup(numThreads, threadFactory);
         case EPOLL -> var10000 = new EpollEventLoopGroup(numThreads, threadFactory);
         default -> throw new IncompatibleClassChangeError();
      }

      return (EventLoopGroup)var10000;
   }

   public static Class getClientChannelClass(IOMode mode) {
      Class var10000;
      switch (mode) {
         case NIO -> var10000 = NioSocketChannel.class;
         case EPOLL -> var10000 = EpollSocketChannel.class;
         default -> throw new IncompatibleClassChangeError();
      }

      return var10000;
   }

   public static Class getServerChannelClass(IOMode mode) {
      Class var10000;
      switch (mode) {
         case NIO -> var10000 = NioServerSocketChannel.class;
         case EPOLL -> var10000 = EpollServerSocketChannel.class;
         default -> throw new IncompatibleClassChangeError();
      }

      return var10000;
   }

   public static TransportFrameDecoder createFrameDecoder() {
      return new TransportFrameDecoder();
   }

   public static String getRemoteAddress(Channel channel) {
      return channel != null && channel.remoteAddress() != null ? channel.remoteAddress().toString() : "<unknown remote>";
   }

   public static int defaultNumThreads(int numUsableCores) {
      int availableCores;
      if (numUsableCores > 0) {
         availableCores = numUsableCores;
      } else {
         availableCores = Runtime.getRuntime().availableProcessors();
      }

      return Math.min(availableCores, MAX_DEFAULT_NETTY_THREADS);
   }

   public static synchronized PooledByteBufAllocator getSharedPooledByteBufAllocator(boolean allowDirectBufs, boolean allowCache) {
      int index = allowCache ? 0 : 1;
      if (_sharedPooledByteBufAllocator[index] == null) {
         _sharedPooledByteBufAllocator[index] = createPooledByteBufAllocator(allowDirectBufs, allowCache, defaultNumThreads(0));
      }

      return _sharedPooledByteBufAllocator[index];
   }

   public static PooledByteBufAllocator createPooledByteBufAllocator(boolean allowDirectBufs, boolean allowCache, int numCores) {
      if (numCores == 0) {
         numCores = Runtime.getRuntime().availableProcessors();
      }

      return new PooledByteBufAllocator(allowDirectBufs && PlatformDependent.directBufferPreferred(), Math.min(PooledByteBufAllocator.defaultNumHeapArena(), numCores), Math.min(PooledByteBufAllocator.defaultNumDirectArena(), allowDirectBufs ? numCores : 0), PooledByteBufAllocator.defaultPageSize(), PooledByteBufAllocator.defaultMaxOrder(), allowCache ? PooledByteBufAllocator.defaultSmallCacheSize() : 0, allowCache ? PooledByteBufAllocator.defaultNormalCacheSize() : 0, allowCache ? PooledByteBufAllocator.defaultUseCacheForAllThreads() : false);
   }

   public static boolean preferDirectBufs(TransportConf conf) {
      boolean allowDirectBufs;
      if (conf.sharedByteBufAllocators()) {
         allowDirectBufs = conf.preferDirectBufsForSharedByteBufAllocators();
      } else {
         allowDirectBufs = conf.preferDirectBufs();
      }

      return allowDirectBufs && PlatformDependent.directBufferPreferred();
   }
}
