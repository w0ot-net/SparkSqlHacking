package io.netty.channel.kqueue;

import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelOption;
import io.netty.channel.MessageSizeEstimator;
import io.netty.channel.RecvByteBufAllocator;
import io.netty.channel.WriteBufferWaterMark;
import io.netty.channel.socket.DuplexChannelConfig;
import io.netty.channel.unix.DomainSocketChannelConfig;
import io.netty.channel.unix.DomainSocketReadMode;
import io.netty.channel.unix.UnixChannelOption;
import io.netty.util.internal.ObjectUtil;
import java.io.IOException;
import java.util.Map;

public final class KQueueDomainSocketChannelConfig extends KQueueChannelConfig implements DomainSocketChannelConfig, DuplexChannelConfig {
   private volatile DomainSocketReadMode mode;
   private volatile boolean allowHalfClosure;

   KQueueDomainSocketChannelConfig(AbstractKQueueChannel channel) {
      super(channel);
      this.mode = DomainSocketReadMode.BYTES;
   }

   public Map getOptions() {
      return this.getOptions(super.getOptions(), new ChannelOption[]{UnixChannelOption.DOMAIN_SOCKET_READ_MODE, ChannelOption.ALLOW_HALF_CLOSURE, ChannelOption.SO_SNDBUF, ChannelOption.SO_RCVBUF});
   }

   public Object getOption(ChannelOption option) {
      if (option == UnixChannelOption.DOMAIN_SOCKET_READ_MODE) {
         return this.getReadMode();
      } else if (option == ChannelOption.ALLOW_HALF_CLOSURE) {
         return this.isAllowHalfClosure();
      } else if (option == ChannelOption.SO_SNDBUF) {
         return this.getSendBufferSize();
      } else {
         return option == ChannelOption.SO_RCVBUF ? this.getReceiveBufferSize() : super.getOption(option);
      }
   }

   public boolean setOption(ChannelOption option, Object value) {
      this.validate(option, value);
      if (option == UnixChannelOption.DOMAIN_SOCKET_READ_MODE) {
         this.setReadMode((DomainSocketReadMode)value);
      } else if (option == ChannelOption.ALLOW_HALF_CLOSURE) {
         this.setAllowHalfClosure((Boolean)value);
      } else if (option == ChannelOption.SO_SNDBUF) {
         this.setSendBufferSize((Integer)value);
      } else {
         if (option != ChannelOption.SO_RCVBUF) {
            return super.setOption(option, value);
         }

         this.setReceiveBufferSize((Integer)value);
      }

      return true;
   }

   public KQueueDomainSocketChannelConfig setRcvAllocTransportProvidesGuess(boolean transportProvidesGuess) {
      super.setRcvAllocTransportProvidesGuess(transportProvidesGuess);
      return this;
   }

   /** @deprecated */
   @Deprecated
   public KQueueDomainSocketChannelConfig setMaxMessagesPerRead(int maxMessagesPerRead) {
      super.setMaxMessagesPerRead(maxMessagesPerRead);
      return this;
   }

   public KQueueDomainSocketChannelConfig setConnectTimeoutMillis(int connectTimeoutMillis) {
      super.setConnectTimeoutMillis(connectTimeoutMillis);
      return this;
   }

   public KQueueDomainSocketChannelConfig setWriteSpinCount(int writeSpinCount) {
      super.setWriteSpinCount(writeSpinCount);
      return this;
   }

   public KQueueDomainSocketChannelConfig setRecvByteBufAllocator(RecvByteBufAllocator allocator) {
      super.setRecvByteBufAllocator(allocator);
      return this;
   }

   public KQueueDomainSocketChannelConfig setAllocator(ByteBufAllocator allocator) {
      super.setAllocator(allocator);
      return this;
   }

   public KQueueDomainSocketChannelConfig setAutoClose(boolean autoClose) {
      super.setAutoClose(autoClose);
      return this;
   }

   public KQueueDomainSocketChannelConfig setMessageSizeEstimator(MessageSizeEstimator estimator) {
      super.setMessageSizeEstimator(estimator);
      return this;
   }

   /** @deprecated */
   @Deprecated
   public KQueueDomainSocketChannelConfig setWriteBufferLowWaterMark(int writeBufferLowWaterMark) {
      super.setWriteBufferLowWaterMark(writeBufferLowWaterMark);
      return this;
   }

   /** @deprecated */
   @Deprecated
   public KQueueDomainSocketChannelConfig setWriteBufferHighWaterMark(int writeBufferHighWaterMark) {
      super.setWriteBufferHighWaterMark(writeBufferHighWaterMark);
      return this;
   }

   public KQueueDomainSocketChannelConfig setWriteBufferWaterMark(WriteBufferWaterMark writeBufferWaterMark) {
      super.setWriteBufferWaterMark(writeBufferWaterMark);
      return this;
   }

   public KQueueDomainSocketChannelConfig setAutoRead(boolean autoRead) {
      super.setAutoRead(autoRead);
      return this;
   }

   public KQueueDomainSocketChannelConfig setReadMode(DomainSocketReadMode mode) {
      this.mode = (DomainSocketReadMode)ObjectUtil.checkNotNull(mode, "mode");
      return this;
   }

   public DomainSocketReadMode getReadMode() {
      return this.mode;
   }

   public int getSendBufferSize() {
      try {
         return ((KQueueDomainSocketChannel)this.channel).socket.getSendBufferSize();
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   public KQueueDomainSocketChannelConfig setSendBufferSize(int sendBufferSize) {
      try {
         ((KQueueDomainSocketChannel)this.channel).socket.setSendBufferSize(sendBufferSize);
         return this;
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   public int getReceiveBufferSize() {
      try {
         return ((KQueueDomainSocketChannel)this.channel).socket.getReceiveBufferSize();
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   public KQueueDomainSocketChannelConfig setReceiveBufferSize(int receiveBufferSize) {
      try {
         ((KQueueDomainSocketChannel)this.channel).socket.setReceiveBufferSize(receiveBufferSize);
         return this;
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   public boolean isAllowHalfClosure() {
      return this.allowHalfClosure;
   }

   public KQueueDomainSocketChannelConfig setAllowHalfClosure(boolean allowHalfClosure) {
      this.allowHalfClosure = allowHalfClosure;
      return this;
   }
}
