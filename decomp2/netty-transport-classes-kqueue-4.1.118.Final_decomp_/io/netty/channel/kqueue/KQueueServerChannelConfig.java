package io.netty.channel.kqueue;

import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelException;
import io.netty.channel.ChannelOption;
import io.netty.channel.MessageSizeEstimator;
import io.netty.channel.RecvByteBufAllocator;
import io.netty.channel.ServerChannelRecvByteBufAllocator;
import io.netty.channel.WriteBufferWaterMark;
import io.netty.channel.socket.ServerSocketChannelConfig;
import io.netty.util.NetUtil;
import io.netty.util.internal.ObjectUtil;
import java.io.IOException;
import java.util.Map;

public class KQueueServerChannelConfig extends KQueueChannelConfig implements ServerSocketChannelConfig {
   private volatile int backlog;
   private volatile boolean enableTcpFastOpen;

   KQueueServerChannelConfig(AbstractKQueueChannel channel) {
      super(channel, new ServerChannelRecvByteBufAllocator());
      this.backlog = NetUtil.SOMAXCONN;
   }

   public Map getOptions() {
      return this.getOptions(super.getOptions(), new ChannelOption[]{ChannelOption.SO_RCVBUF, ChannelOption.SO_REUSEADDR, ChannelOption.SO_BACKLOG, ChannelOption.TCP_FASTOPEN});
   }

   public Object getOption(ChannelOption option) {
      if (option == ChannelOption.SO_RCVBUF) {
         return this.getReceiveBufferSize();
      } else if (option == ChannelOption.SO_REUSEADDR) {
         return this.isReuseAddress();
      } else if (option == ChannelOption.SO_BACKLOG) {
         return this.getBacklog();
      } else if (option == ChannelOption.TCP_FASTOPEN) {
         return this.isTcpFastOpen() ? 1 : 0;
      } else {
         return super.getOption(option);
      }
   }

   public boolean setOption(ChannelOption option, Object value) {
      this.validate(option, value);
      if (option == ChannelOption.SO_RCVBUF) {
         this.setReceiveBufferSize((Integer)value);
      } else if (option == ChannelOption.SO_REUSEADDR) {
         this.setReuseAddress((Boolean)value);
      } else if (option == ChannelOption.SO_BACKLOG) {
         this.setBacklog((Integer)value);
      } else {
         if (option != ChannelOption.TCP_FASTOPEN) {
            return super.setOption(option, value);
         }

         this.setTcpFastOpen((Integer)value > 0);
      }

      return true;
   }

   public boolean isReuseAddress() {
      try {
         return ((AbstractKQueueChannel)this.channel).socket.isReuseAddress();
      } catch (IOException e) {
         throw new ChannelException(e);
      }
   }

   public KQueueServerChannelConfig setReuseAddress(boolean reuseAddress) {
      try {
         ((AbstractKQueueChannel)this.channel).socket.setReuseAddress(reuseAddress);
         return this;
      } catch (IOException e) {
         throw new ChannelException(e);
      }
   }

   public int getReceiveBufferSize() {
      try {
         return ((AbstractKQueueChannel)this.channel).socket.getReceiveBufferSize();
      } catch (IOException e) {
         throw new ChannelException(e);
      }
   }

   public KQueueServerChannelConfig setReceiveBufferSize(int receiveBufferSize) {
      try {
         ((AbstractKQueueChannel)this.channel).socket.setReceiveBufferSize(receiveBufferSize);
         return this;
      } catch (IOException e) {
         throw new ChannelException(e);
      }
   }

   public int getBacklog() {
      return this.backlog;
   }

   public KQueueServerChannelConfig setBacklog(int backlog) {
      ObjectUtil.checkPositiveOrZero(backlog, "backlog");
      this.backlog = backlog;
      return this;
   }

   public boolean isTcpFastOpen() {
      return this.enableTcpFastOpen;
   }

   public KQueueServerChannelConfig setTcpFastOpen(boolean enableTcpFastOpen) {
      this.enableTcpFastOpen = enableTcpFastOpen;
      return this;
   }

   public KQueueServerChannelConfig setRcvAllocTransportProvidesGuess(boolean transportProvidesGuess) {
      super.setRcvAllocTransportProvidesGuess(transportProvidesGuess);
      return this;
   }

   public KQueueServerChannelConfig setPerformancePreferences(int connectionTime, int latency, int bandwidth) {
      return this;
   }

   public KQueueServerChannelConfig setConnectTimeoutMillis(int connectTimeoutMillis) {
      super.setConnectTimeoutMillis(connectTimeoutMillis);
      return this;
   }

   /** @deprecated */
   @Deprecated
   public KQueueServerChannelConfig setMaxMessagesPerRead(int maxMessagesPerRead) {
      super.setMaxMessagesPerRead(maxMessagesPerRead);
      return this;
   }

   public KQueueServerChannelConfig setWriteSpinCount(int writeSpinCount) {
      super.setWriteSpinCount(writeSpinCount);
      return this;
   }

   public KQueueServerChannelConfig setAllocator(ByteBufAllocator allocator) {
      super.setAllocator(allocator);
      return this;
   }

   public KQueueServerChannelConfig setRecvByteBufAllocator(RecvByteBufAllocator allocator) {
      super.setRecvByteBufAllocator(allocator);
      return this;
   }

   public KQueueServerChannelConfig setAutoRead(boolean autoRead) {
      super.setAutoRead(autoRead);
      return this;
   }

   /** @deprecated */
   @Deprecated
   public KQueueServerChannelConfig setWriteBufferHighWaterMark(int writeBufferHighWaterMark) {
      super.setWriteBufferHighWaterMark(writeBufferHighWaterMark);
      return this;
   }

   /** @deprecated */
   @Deprecated
   public KQueueServerChannelConfig setWriteBufferLowWaterMark(int writeBufferLowWaterMark) {
      super.setWriteBufferLowWaterMark(writeBufferLowWaterMark);
      return this;
   }

   public KQueueServerChannelConfig setWriteBufferWaterMark(WriteBufferWaterMark writeBufferWaterMark) {
      super.setWriteBufferWaterMark(writeBufferWaterMark);
      return this;
   }

   public KQueueServerChannelConfig setMessageSizeEstimator(MessageSizeEstimator estimator) {
      super.setMessageSizeEstimator(estimator);
      return this;
   }
}
