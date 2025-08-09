package io.netty.channel.epoll;

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

public class EpollServerChannelConfig extends EpollChannelConfig implements ServerSocketChannelConfig {
   private volatile int backlog;
   private volatile int pendingFastOpenRequestsThreshold;

   EpollServerChannelConfig(AbstractEpollChannel channel) {
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
      } else {
         return option == ChannelOption.TCP_FASTOPEN ? this.getTcpFastopen() : super.getOption(option);
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

         this.setTcpFastopen((Integer)value);
      }

      return true;
   }

   public boolean isReuseAddress() {
      try {
         return ((AbstractEpollChannel)this.channel).socket.isReuseAddress();
      } catch (IOException e) {
         throw new ChannelException(e);
      }
   }

   public EpollServerChannelConfig setReuseAddress(boolean reuseAddress) {
      try {
         ((AbstractEpollChannel)this.channel).socket.setReuseAddress(reuseAddress);
         return this;
      } catch (IOException e) {
         throw new ChannelException(e);
      }
   }

   public int getReceiveBufferSize() {
      try {
         return ((AbstractEpollChannel)this.channel).socket.getReceiveBufferSize();
      } catch (IOException e) {
         throw new ChannelException(e);
      }
   }

   public EpollServerChannelConfig setReceiveBufferSize(int receiveBufferSize) {
      try {
         ((AbstractEpollChannel)this.channel).socket.setReceiveBufferSize(receiveBufferSize);
         return this;
      } catch (IOException e) {
         throw new ChannelException(e);
      }
   }

   public int getBacklog() {
      return this.backlog;
   }

   public EpollServerChannelConfig setBacklog(int backlog) {
      ObjectUtil.checkPositiveOrZero(backlog, "backlog");
      this.backlog = backlog;
      return this;
   }

   public int getTcpFastopen() {
      return this.pendingFastOpenRequestsThreshold;
   }

   public EpollServerChannelConfig setTcpFastopen(int pendingFastOpenRequestsThreshold) {
      this.pendingFastOpenRequestsThreshold = ObjectUtil.checkPositiveOrZero(pendingFastOpenRequestsThreshold, "pendingFastOpenRequestsThreshold");
      return this;
   }

   public EpollServerChannelConfig setPerformancePreferences(int connectionTime, int latency, int bandwidth) {
      return this;
   }

   public EpollServerChannelConfig setConnectTimeoutMillis(int connectTimeoutMillis) {
      super.setConnectTimeoutMillis(connectTimeoutMillis);
      return this;
   }

   /** @deprecated */
   @Deprecated
   public EpollServerChannelConfig setMaxMessagesPerRead(int maxMessagesPerRead) {
      super.setMaxMessagesPerRead(maxMessagesPerRead);
      return this;
   }

   public EpollServerChannelConfig setWriteSpinCount(int writeSpinCount) {
      super.setWriteSpinCount(writeSpinCount);
      return this;
   }

   public EpollServerChannelConfig setAllocator(ByteBufAllocator allocator) {
      super.setAllocator(allocator);
      return this;
   }

   public EpollServerChannelConfig setRecvByteBufAllocator(RecvByteBufAllocator allocator) {
      super.setRecvByteBufAllocator(allocator);
      return this;
   }

   public EpollServerChannelConfig setAutoRead(boolean autoRead) {
      super.setAutoRead(autoRead);
      return this;
   }

   /** @deprecated */
   @Deprecated
   public EpollServerChannelConfig setWriteBufferHighWaterMark(int writeBufferHighWaterMark) {
      super.setWriteBufferHighWaterMark(writeBufferHighWaterMark);
      return this;
   }

   /** @deprecated */
   @Deprecated
   public EpollServerChannelConfig setWriteBufferLowWaterMark(int writeBufferLowWaterMark) {
      super.setWriteBufferLowWaterMark(writeBufferLowWaterMark);
      return this;
   }

   public EpollServerChannelConfig setWriteBufferWaterMark(WriteBufferWaterMark writeBufferWaterMark) {
      super.setWriteBufferWaterMark(writeBufferWaterMark);
      return this;
   }

   public EpollServerChannelConfig setMessageSizeEstimator(MessageSizeEstimator estimator) {
      super.setMessageSizeEstimator(estimator);
      return this;
   }

   public EpollServerChannelConfig setEpollMode(EpollMode mode) {
      super.setEpollMode(mode);
      return this;
   }
}
