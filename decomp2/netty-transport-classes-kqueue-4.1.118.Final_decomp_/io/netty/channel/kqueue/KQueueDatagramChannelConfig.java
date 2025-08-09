package io.netty.channel.kqueue;

import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelException;
import io.netty.channel.ChannelOption;
import io.netty.channel.FixedRecvByteBufAllocator;
import io.netty.channel.MessageSizeEstimator;
import io.netty.channel.RecvByteBufAllocator;
import io.netty.channel.WriteBufferWaterMark;
import io.netty.channel.socket.DatagramChannelConfig;
import io.netty.channel.unix.UnixChannelOption;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Map;

public final class KQueueDatagramChannelConfig extends KQueueChannelConfig implements DatagramChannelConfig {
   private boolean activeOnOpen;

   KQueueDatagramChannelConfig(KQueueDatagramChannel channel) {
      super(channel, new FixedRecvByteBufAllocator(2048));
   }

   public Map getOptions() {
      return this.getOptions(super.getOptions(), new ChannelOption[]{ChannelOption.SO_BROADCAST, ChannelOption.SO_RCVBUF, ChannelOption.SO_SNDBUF, ChannelOption.SO_REUSEADDR, ChannelOption.IP_MULTICAST_LOOP_DISABLED, ChannelOption.IP_MULTICAST_ADDR, ChannelOption.IP_MULTICAST_IF, ChannelOption.IP_MULTICAST_TTL, ChannelOption.IP_TOS, ChannelOption.DATAGRAM_CHANNEL_ACTIVE_ON_REGISTRATION, UnixChannelOption.SO_REUSEPORT});
   }

   public Object getOption(ChannelOption option) {
      if (option == ChannelOption.SO_BROADCAST) {
         return this.isBroadcast();
      } else if (option == ChannelOption.SO_RCVBUF) {
         return this.getReceiveBufferSize();
      } else if (option == ChannelOption.SO_SNDBUF) {
         return this.getSendBufferSize();
      } else if (option == ChannelOption.SO_REUSEADDR) {
         return this.isReuseAddress();
      } else if (option == ChannelOption.IP_MULTICAST_LOOP_DISABLED) {
         return this.isLoopbackModeDisabled();
      } else if (option == ChannelOption.IP_MULTICAST_ADDR) {
         return this.getInterface();
      } else if (option == ChannelOption.IP_MULTICAST_IF) {
         return this.getNetworkInterface();
      } else if (option == ChannelOption.IP_MULTICAST_TTL) {
         return this.getTimeToLive();
      } else if (option == ChannelOption.IP_TOS) {
         return this.getTrafficClass();
      } else if (option == ChannelOption.DATAGRAM_CHANNEL_ACTIVE_ON_REGISTRATION) {
         return this.activeOnOpen;
      } else {
         return option == UnixChannelOption.SO_REUSEPORT ? this.isReusePort() : super.getOption(option);
      }
   }

   public boolean setOption(ChannelOption option, Object value) {
      this.validate(option, value);
      if (option == ChannelOption.SO_BROADCAST) {
         this.setBroadcast((Boolean)value);
      } else if (option == ChannelOption.SO_RCVBUF) {
         this.setReceiveBufferSize((Integer)value);
      } else if (option == ChannelOption.SO_SNDBUF) {
         this.setSendBufferSize((Integer)value);
      } else if (option == ChannelOption.SO_REUSEADDR) {
         this.setReuseAddress((Boolean)value);
      } else if (option == ChannelOption.IP_MULTICAST_LOOP_DISABLED) {
         this.setLoopbackModeDisabled((Boolean)value);
      } else if (option == ChannelOption.IP_MULTICAST_ADDR) {
         this.setInterface((InetAddress)value);
      } else if (option == ChannelOption.IP_MULTICAST_IF) {
         this.setNetworkInterface((NetworkInterface)value);
      } else if (option == ChannelOption.IP_MULTICAST_TTL) {
         this.setTimeToLive((Integer)value);
      } else if (option == ChannelOption.IP_TOS) {
         this.setTrafficClass((Integer)value);
      } else if (option == ChannelOption.DATAGRAM_CHANNEL_ACTIVE_ON_REGISTRATION) {
         this.setActiveOnOpen((Boolean)value);
      } else {
         if (option != UnixChannelOption.SO_REUSEPORT) {
            return super.setOption(option, value);
         }

         this.setReusePort((Boolean)value);
      }

      return true;
   }

   private void setActiveOnOpen(boolean activeOnOpen) {
      if (this.channel.isRegistered()) {
         throw new IllegalStateException("Can only changed before channel was registered");
      } else {
         this.activeOnOpen = activeOnOpen;
      }
   }

   boolean getActiveOnOpen() {
      return this.activeOnOpen;
   }

   public boolean isReusePort() {
      try {
         return ((KQueueDatagramChannel)this.channel).socket.isReusePort();
      } catch (IOException e) {
         throw new ChannelException(e);
      }
   }

   public KQueueDatagramChannelConfig setReusePort(boolean reusePort) {
      try {
         ((KQueueDatagramChannel)this.channel).socket.setReusePort(reusePort);
         return this;
      } catch (IOException e) {
         throw new ChannelException(e);
      }
   }

   public KQueueDatagramChannelConfig setRcvAllocTransportProvidesGuess(boolean transportProvidesGuess) {
      super.setRcvAllocTransportProvidesGuess(transportProvidesGuess);
      return this;
   }

   public KQueueDatagramChannelConfig setMessageSizeEstimator(MessageSizeEstimator estimator) {
      super.setMessageSizeEstimator(estimator);
      return this;
   }

   /** @deprecated */
   @Deprecated
   public KQueueDatagramChannelConfig setWriteBufferLowWaterMark(int writeBufferLowWaterMark) {
      super.setWriteBufferLowWaterMark(writeBufferLowWaterMark);
      return this;
   }

   /** @deprecated */
   @Deprecated
   public KQueueDatagramChannelConfig setWriteBufferHighWaterMark(int writeBufferHighWaterMark) {
      super.setWriteBufferHighWaterMark(writeBufferHighWaterMark);
      return this;
   }

   public KQueueDatagramChannelConfig setWriteBufferWaterMark(WriteBufferWaterMark writeBufferWaterMark) {
      super.setWriteBufferWaterMark(writeBufferWaterMark);
      return this;
   }

   public KQueueDatagramChannelConfig setAutoClose(boolean autoClose) {
      super.setAutoClose(autoClose);
      return this;
   }

   public KQueueDatagramChannelConfig setAutoRead(boolean autoRead) {
      super.setAutoRead(autoRead);
      return this;
   }

   public KQueueDatagramChannelConfig setRecvByteBufAllocator(RecvByteBufAllocator allocator) {
      super.setRecvByteBufAllocator(allocator);
      return this;
   }

   public KQueueDatagramChannelConfig setWriteSpinCount(int writeSpinCount) {
      super.setWriteSpinCount(writeSpinCount);
      return this;
   }

   public KQueueDatagramChannelConfig setAllocator(ByteBufAllocator allocator) {
      super.setAllocator(allocator);
      return this;
   }

   public KQueueDatagramChannelConfig setConnectTimeoutMillis(int connectTimeoutMillis) {
      super.setConnectTimeoutMillis(connectTimeoutMillis);
      return this;
   }

   /** @deprecated */
   @Deprecated
   public KQueueDatagramChannelConfig setMaxMessagesPerRead(int maxMessagesPerRead) {
      super.setMaxMessagesPerRead(maxMessagesPerRead);
      return this;
   }

   public int getSendBufferSize() {
      try {
         return ((KQueueDatagramChannel)this.channel).socket.getSendBufferSize();
      } catch (IOException e) {
         throw new ChannelException(e);
      }
   }

   public KQueueDatagramChannelConfig setSendBufferSize(int sendBufferSize) {
      try {
         ((KQueueDatagramChannel)this.channel).socket.setSendBufferSize(sendBufferSize);
         return this;
      } catch (IOException e) {
         throw new ChannelException(e);
      }
   }

   public int getReceiveBufferSize() {
      try {
         return ((KQueueDatagramChannel)this.channel).socket.getReceiveBufferSize();
      } catch (IOException e) {
         throw new ChannelException(e);
      }
   }

   public KQueueDatagramChannelConfig setReceiveBufferSize(int receiveBufferSize) {
      try {
         ((KQueueDatagramChannel)this.channel).socket.setReceiveBufferSize(receiveBufferSize);
         return this;
      } catch (IOException e) {
         throw new ChannelException(e);
      }
   }

   public int getTrafficClass() {
      try {
         return ((KQueueDatagramChannel)this.channel).socket.getTrafficClass();
      } catch (IOException e) {
         throw new ChannelException(e);
      }
   }

   public KQueueDatagramChannelConfig setTrafficClass(int trafficClass) {
      try {
         ((KQueueDatagramChannel)this.channel).socket.setTrafficClass(trafficClass);
         return this;
      } catch (IOException e) {
         throw new ChannelException(e);
      }
   }

   public boolean isReuseAddress() {
      try {
         return ((KQueueDatagramChannel)this.channel).socket.isReuseAddress();
      } catch (IOException e) {
         throw new ChannelException(e);
      }
   }

   public KQueueDatagramChannelConfig setReuseAddress(boolean reuseAddress) {
      try {
         ((KQueueDatagramChannel)this.channel).socket.setReuseAddress(reuseAddress);
         return this;
      } catch (IOException e) {
         throw new ChannelException(e);
      }
   }

   public boolean isBroadcast() {
      try {
         return ((KQueueDatagramChannel)this.channel).socket.isBroadcast();
      } catch (IOException e) {
         throw new ChannelException(e);
      }
   }

   public KQueueDatagramChannelConfig setBroadcast(boolean broadcast) {
      try {
         ((KQueueDatagramChannel)this.channel).socket.setBroadcast(broadcast);
         return this;
      } catch (IOException e) {
         throw new ChannelException(e);
      }
   }

   public boolean isLoopbackModeDisabled() {
      return false;
   }

   public DatagramChannelConfig setLoopbackModeDisabled(boolean loopbackModeDisabled) {
      throw new UnsupportedOperationException("Multicast not supported");
   }

   public int getTimeToLive() {
      return -1;
   }

   public KQueueDatagramChannelConfig setTimeToLive(int ttl) {
      throw new UnsupportedOperationException("Multicast not supported");
   }

   public InetAddress getInterface() {
      return null;
   }

   public KQueueDatagramChannelConfig setInterface(InetAddress interfaceAddress) {
      throw new UnsupportedOperationException("Multicast not supported");
   }

   public NetworkInterface getNetworkInterface() {
      return null;
   }

   public KQueueDatagramChannelConfig setNetworkInterface(NetworkInterface networkInterface) {
      throw new UnsupportedOperationException("Multicast not supported");
   }

   public KQueueDatagramChannelConfig setMaxMessagesPerWrite(int maxMessagesPerWrite) {
      super.setMaxMessagesPerWrite(maxMessagesPerWrite);
      return this;
   }
}
