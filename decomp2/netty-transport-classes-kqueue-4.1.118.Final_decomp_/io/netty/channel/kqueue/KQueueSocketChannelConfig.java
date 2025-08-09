package io.netty.channel.kqueue;

import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelException;
import io.netty.channel.ChannelOption;
import io.netty.channel.MessageSizeEstimator;
import io.netty.channel.RecvByteBufAllocator;
import io.netty.channel.WriteBufferWaterMark;
import io.netty.channel.socket.SocketChannelConfig;
import io.netty.util.internal.PlatformDependent;
import java.io.IOException;
import java.util.Map;

public final class KQueueSocketChannelConfig extends KQueueChannelConfig implements SocketChannelConfig {
   private volatile boolean allowHalfClosure;
   private volatile boolean tcpFastopen;

   KQueueSocketChannelConfig(KQueueSocketChannel channel) {
      super(channel);
      if (PlatformDependent.canEnableTcpNoDelayByDefault()) {
         this.setTcpNoDelay(true);
      }

      this.calculateMaxBytesPerGatheringWrite();
   }

   public Map getOptions() {
      return this.getOptions(super.getOptions(), new ChannelOption[]{ChannelOption.SO_RCVBUF, ChannelOption.SO_SNDBUF, ChannelOption.TCP_NODELAY, ChannelOption.SO_KEEPALIVE, ChannelOption.SO_REUSEADDR, ChannelOption.SO_LINGER, ChannelOption.IP_TOS, ChannelOption.ALLOW_HALF_CLOSURE, KQueueChannelOption.SO_SNDLOWAT, KQueueChannelOption.TCP_NOPUSH});
   }

   public Object getOption(ChannelOption option) {
      if (option == ChannelOption.SO_RCVBUF) {
         return this.getReceiveBufferSize();
      } else if (option == ChannelOption.SO_SNDBUF) {
         return this.getSendBufferSize();
      } else if (option == ChannelOption.TCP_NODELAY) {
         return this.isTcpNoDelay();
      } else if (option == ChannelOption.SO_KEEPALIVE) {
         return this.isKeepAlive();
      } else if (option == ChannelOption.SO_REUSEADDR) {
         return this.isReuseAddress();
      } else if (option == ChannelOption.SO_LINGER) {
         return this.getSoLinger();
      } else if (option == ChannelOption.IP_TOS) {
         return this.getTrafficClass();
      } else if (option == ChannelOption.ALLOW_HALF_CLOSURE) {
         return this.isAllowHalfClosure();
      } else if (option == KQueueChannelOption.SO_SNDLOWAT) {
         return this.getSndLowAt();
      } else if (option == KQueueChannelOption.TCP_NOPUSH) {
         return this.isTcpNoPush();
      } else {
         return option == ChannelOption.TCP_FASTOPEN_CONNECT ? this.isTcpFastOpenConnect() : super.getOption(option);
      }
   }

   public boolean setOption(ChannelOption option, Object value) {
      this.validate(option, value);
      if (option == ChannelOption.SO_RCVBUF) {
         this.setReceiveBufferSize((Integer)value);
      } else if (option == ChannelOption.SO_SNDBUF) {
         this.setSendBufferSize((Integer)value);
      } else if (option == ChannelOption.TCP_NODELAY) {
         this.setTcpNoDelay((Boolean)value);
      } else if (option == ChannelOption.SO_KEEPALIVE) {
         this.setKeepAlive((Boolean)value);
      } else if (option == ChannelOption.SO_REUSEADDR) {
         this.setReuseAddress((Boolean)value);
      } else if (option == ChannelOption.SO_LINGER) {
         this.setSoLinger((Integer)value);
      } else if (option == ChannelOption.IP_TOS) {
         this.setTrafficClass((Integer)value);
      } else if (option == ChannelOption.ALLOW_HALF_CLOSURE) {
         this.setAllowHalfClosure((Boolean)value);
      } else if (option == KQueueChannelOption.SO_SNDLOWAT) {
         this.setSndLowAt((Integer)value);
      } else if (option == KQueueChannelOption.TCP_NOPUSH) {
         this.setTcpNoPush((Boolean)value);
      } else {
         if (option != ChannelOption.TCP_FASTOPEN_CONNECT) {
            return super.setOption(option, value);
         }

         this.setTcpFastOpenConnect((Boolean)value);
      }

      return true;
   }

   public int getReceiveBufferSize() {
      try {
         return ((KQueueSocketChannel)this.channel).socket.getReceiveBufferSize();
      } catch (IOException e) {
         throw new ChannelException(e);
      }
   }

   public int getSendBufferSize() {
      try {
         return ((KQueueSocketChannel)this.channel).socket.getSendBufferSize();
      } catch (IOException e) {
         throw new ChannelException(e);
      }
   }

   public int getSoLinger() {
      try {
         return ((KQueueSocketChannel)this.channel).socket.getSoLinger();
      } catch (IOException e) {
         throw new ChannelException(e);
      }
   }

   public int getTrafficClass() {
      try {
         return ((KQueueSocketChannel)this.channel).socket.getTrafficClass();
      } catch (IOException e) {
         throw new ChannelException(e);
      }
   }

   public boolean isKeepAlive() {
      try {
         return ((KQueueSocketChannel)this.channel).socket.isKeepAlive();
      } catch (IOException e) {
         throw new ChannelException(e);
      }
   }

   public boolean isReuseAddress() {
      try {
         return ((KQueueSocketChannel)this.channel).socket.isReuseAddress();
      } catch (IOException e) {
         throw new ChannelException(e);
      }
   }

   public boolean isTcpNoDelay() {
      try {
         return ((KQueueSocketChannel)this.channel).socket.isTcpNoDelay();
      } catch (IOException e) {
         throw new ChannelException(e);
      }
   }

   public int getSndLowAt() {
      try {
         return ((KQueueSocketChannel)this.channel).socket.getSndLowAt();
      } catch (IOException e) {
         throw new ChannelException(e);
      }
   }

   public void setSndLowAt(int sndLowAt) {
      try {
         ((KQueueSocketChannel)this.channel).socket.setSndLowAt(sndLowAt);
      } catch (IOException e) {
         throw new ChannelException(e);
      }
   }

   public boolean isTcpNoPush() {
      try {
         return ((KQueueSocketChannel)this.channel).socket.isTcpNoPush();
      } catch (IOException e) {
         throw new ChannelException(e);
      }
   }

   public void setTcpNoPush(boolean tcpNoPush) {
      try {
         ((KQueueSocketChannel)this.channel).socket.setTcpNoPush(tcpNoPush);
      } catch (IOException e) {
         throw new ChannelException(e);
      }
   }

   public KQueueSocketChannelConfig setKeepAlive(boolean keepAlive) {
      try {
         ((KQueueSocketChannel)this.channel).socket.setKeepAlive(keepAlive);
         return this;
      } catch (IOException e) {
         throw new ChannelException(e);
      }
   }

   public KQueueSocketChannelConfig setReceiveBufferSize(int receiveBufferSize) {
      try {
         ((KQueueSocketChannel)this.channel).socket.setReceiveBufferSize(receiveBufferSize);
         return this;
      } catch (IOException e) {
         throw new ChannelException(e);
      }
   }

   public KQueueSocketChannelConfig setReuseAddress(boolean reuseAddress) {
      try {
         ((KQueueSocketChannel)this.channel).socket.setReuseAddress(reuseAddress);
         return this;
      } catch (IOException e) {
         throw new ChannelException(e);
      }
   }

   public KQueueSocketChannelConfig setSendBufferSize(int sendBufferSize) {
      try {
         ((KQueueSocketChannel)this.channel).socket.setSendBufferSize(sendBufferSize);
         this.calculateMaxBytesPerGatheringWrite();
         return this;
      } catch (IOException e) {
         throw new ChannelException(e);
      }
   }

   public KQueueSocketChannelConfig setSoLinger(int soLinger) {
      try {
         ((KQueueSocketChannel)this.channel).socket.setSoLinger(soLinger);
         return this;
      } catch (IOException e) {
         throw new ChannelException(e);
      }
   }

   public KQueueSocketChannelConfig setTcpNoDelay(boolean tcpNoDelay) {
      try {
         ((KQueueSocketChannel)this.channel).socket.setTcpNoDelay(tcpNoDelay);
         return this;
      } catch (IOException e) {
         throw new ChannelException(e);
      }
   }

   public KQueueSocketChannelConfig setTrafficClass(int trafficClass) {
      try {
         ((KQueueSocketChannel)this.channel).socket.setTrafficClass(trafficClass);
         return this;
      } catch (IOException e) {
         throw new ChannelException(e);
      }
   }

   public boolean isAllowHalfClosure() {
      return this.allowHalfClosure;
   }

   public KQueueSocketChannelConfig setTcpFastOpenConnect(boolean fastOpenConnect) {
      this.tcpFastopen = fastOpenConnect;
      return this;
   }

   public boolean isTcpFastOpenConnect() {
      return this.tcpFastopen;
   }

   public KQueueSocketChannelConfig setRcvAllocTransportProvidesGuess(boolean transportProvidesGuess) {
      super.setRcvAllocTransportProvidesGuess(transportProvidesGuess);
      return this;
   }

   public KQueueSocketChannelConfig setPerformancePreferences(int connectionTime, int latency, int bandwidth) {
      return this;
   }

   public KQueueSocketChannelConfig setAllowHalfClosure(boolean allowHalfClosure) {
      this.allowHalfClosure = allowHalfClosure;
      return this;
   }

   public KQueueSocketChannelConfig setConnectTimeoutMillis(int connectTimeoutMillis) {
      super.setConnectTimeoutMillis(connectTimeoutMillis);
      return this;
   }

   /** @deprecated */
   @Deprecated
   public KQueueSocketChannelConfig setMaxMessagesPerRead(int maxMessagesPerRead) {
      super.setMaxMessagesPerRead(maxMessagesPerRead);
      return this;
   }

   public KQueueSocketChannelConfig setWriteSpinCount(int writeSpinCount) {
      super.setWriteSpinCount(writeSpinCount);
      return this;
   }

   public KQueueSocketChannelConfig setAllocator(ByteBufAllocator allocator) {
      super.setAllocator(allocator);
      return this;
   }

   public KQueueSocketChannelConfig setRecvByteBufAllocator(RecvByteBufAllocator allocator) {
      super.setRecvByteBufAllocator(allocator);
      return this;
   }

   public KQueueSocketChannelConfig setAutoRead(boolean autoRead) {
      super.setAutoRead(autoRead);
      return this;
   }

   public KQueueSocketChannelConfig setAutoClose(boolean autoClose) {
      super.setAutoClose(autoClose);
      return this;
   }

   /** @deprecated */
   @Deprecated
   public KQueueSocketChannelConfig setWriteBufferHighWaterMark(int writeBufferHighWaterMark) {
      super.setWriteBufferHighWaterMark(writeBufferHighWaterMark);
      return this;
   }

   /** @deprecated */
   @Deprecated
   public KQueueSocketChannelConfig setWriteBufferLowWaterMark(int writeBufferLowWaterMark) {
      super.setWriteBufferLowWaterMark(writeBufferLowWaterMark);
      return this;
   }

   public KQueueSocketChannelConfig setWriteBufferWaterMark(WriteBufferWaterMark writeBufferWaterMark) {
      super.setWriteBufferWaterMark(writeBufferWaterMark);
      return this;
   }

   public KQueueSocketChannelConfig setMessageSizeEstimator(MessageSizeEstimator estimator) {
      super.setMessageSizeEstimator(estimator);
      return this;
   }

   private void calculateMaxBytesPerGatheringWrite() {
      int newSendBufferSize = this.getSendBufferSize() << 1;
      if (newSendBufferSize > 0) {
         this.setMaxBytesPerGatheringWrite((long)newSendBufferSize);
      }

   }
}
