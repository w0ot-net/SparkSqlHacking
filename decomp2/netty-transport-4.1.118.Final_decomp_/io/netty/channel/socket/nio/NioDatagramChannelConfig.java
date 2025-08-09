package io.netty.channel.socket.nio;

import io.netty.channel.ChannelException;
import io.netty.channel.ChannelOption;
import io.netty.channel.socket.DatagramChannelConfig;
import io.netty.channel.socket.DefaultDatagramChannelConfig;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.SocketUtils;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.nio.channels.DatagramChannel;
import java.util.Enumeration;
import java.util.Map;

class NioDatagramChannelConfig extends DefaultDatagramChannelConfig {
   private static final Object IP_MULTICAST_TTL;
   private static final Object IP_MULTICAST_IF;
   private static final Object IP_MULTICAST_LOOP;
   private static final Method GET_OPTION;
   private static final Method SET_OPTION;
   private final DatagramChannel javaChannel;

   NioDatagramChannelConfig(NioDatagramChannel channel, DatagramChannel javaChannel) {
      super(channel, javaChannel.socket());
      this.javaChannel = javaChannel;
   }

   public int getTimeToLive() {
      return (Integer)this.getOption0(IP_MULTICAST_TTL);
   }

   public DatagramChannelConfig setTimeToLive(int ttl) {
      this.setOption0(IP_MULTICAST_TTL, ttl);
      return this;
   }

   public InetAddress getInterface() {
      NetworkInterface inf = this.getNetworkInterface();
      if (inf != null) {
         Enumeration<InetAddress> addresses = SocketUtils.addressesFromNetworkInterface(inf);
         if (addresses.hasMoreElements()) {
            return (InetAddress)addresses.nextElement();
         }
      }

      return null;
   }

   public DatagramChannelConfig setInterface(InetAddress interfaceAddress) {
      try {
         this.setNetworkInterface(NetworkInterface.getByInetAddress(interfaceAddress));
         return this;
      } catch (SocketException e) {
         throw new ChannelException(e);
      }
   }

   public NetworkInterface getNetworkInterface() {
      return (NetworkInterface)this.getOption0(IP_MULTICAST_IF);
   }

   public DatagramChannelConfig setNetworkInterface(NetworkInterface networkInterface) {
      this.setOption0(IP_MULTICAST_IF, networkInterface);
      return this;
   }

   public boolean isLoopbackModeDisabled() {
      return (Boolean)this.getOption0(IP_MULTICAST_LOOP);
   }

   public DatagramChannelConfig setLoopbackModeDisabled(boolean loopbackModeDisabled) {
      this.setOption0(IP_MULTICAST_LOOP, loopbackModeDisabled);
      return this;
   }

   public DatagramChannelConfig setAutoRead(boolean autoRead) {
      super.setAutoRead(autoRead);
      return this;
   }

   protected void autoReadCleared() {
      ((NioDatagramChannel)this.channel).clearReadPending0();
   }

   private Object getOption0(Object option) {
      if (GET_OPTION == null) {
         throw new UnsupportedOperationException();
      } else {
         try {
            return GET_OPTION.invoke(this.javaChannel, option);
         } catch (Exception e) {
            throw new ChannelException(e);
         }
      }
   }

   private void setOption0(Object option, Object value) {
      if (SET_OPTION == null) {
         throw new UnsupportedOperationException();
      } else {
         try {
            SET_OPTION.invoke(this.javaChannel, option, value);
         } catch (Exception e) {
            throw new ChannelException(e);
         }
      }
   }

   public boolean setOption(ChannelOption option, Object value) {
      return PlatformDependent.javaVersion() >= 7 && option instanceof NioChannelOption ? NioChannelOption.setOption(this.javaChannel, (NioChannelOption)option, value) : super.setOption(option, value);
   }

   public Object getOption(ChannelOption option) {
      return PlatformDependent.javaVersion() >= 7 && option instanceof NioChannelOption ? NioChannelOption.getOption(this.javaChannel, (NioChannelOption)option) : super.getOption(option);
   }

   public Map getOptions() {
      return PlatformDependent.javaVersion() >= 7 ? this.getOptions(super.getOptions(), NioChannelOption.getOptions(this.javaChannel)) : super.getOptions();
   }

   static {
      ClassLoader classLoader = PlatformDependent.getClassLoader(DatagramChannel.class);
      Class<?> socketOptionType = null;

      try {
         socketOptionType = Class.forName("java.net.SocketOption", true, classLoader);
      } catch (Exception var17) {
      }

      Class<?> stdSocketOptionType = null;

      try {
         stdSocketOptionType = Class.forName("java.net.StandardSocketOptions", true, classLoader);
      } catch (Exception var16) {
      }

      Object ipMulticastTtl = null;
      Object ipMulticastIf = null;
      Object ipMulticastLoop = null;
      Method getOption = null;
      Method setOption = null;
      if (socketOptionType != null) {
         try {
            ipMulticastTtl = stdSocketOptionType.getDeclaredField("IP_MULTICAST_TTL").get((Object)null);
         } catch (Exception e) {
            throw new Error("cannot locate the IP_MULTICAST_TTL field", e);
         }

         try {
            ipMulticastIf = stdSocketOptionType.getDeclaredField("IP_MULTICAST_IF").get((Object)null);
         } catch (Exception e) {
            throw new Error("cannot locate the IP_MULTICAST_IF field", e);
         }

         try {
            ipMulticastLoop = stdSocketOptionType.getDeclaredField("IP_MULTICAST_LOOP").get((Object)null);
         } catch (Exception e) {
            throw new Error("cannot locate the IP_MULTICAST_LOOP field", e);
         }

         Class<?> networkChannelClass = null;

         try {
            networkChannelClass = Class.forName("java.nio.channels.NetworkChannel", true, classLoader);
         } catch (Throwable var12) {
         }

         if (networkChannelClass == null) {
            getOption = null;
            setOption = null;
         } else {
            try {
               getOption = networkChannelClass.getDeclaredMethod("getOption", socketOptionType);
            } catch (Exception e) {
               throw new Error("cannot locate the getOption() method", e);
            }

            try {
               setOption = networkChannelClass.getDeclaredMethod("setOption", socketOptionType, Object.class);
            } catch (Exception e) {
               throw new Error("cannot locate the setOption() method", e);
            }
         }
      }

      IP_MULTICAST_TTL = ipMulticastTtl;
      IP_MULTICAST_IF = ipMulticastIf;
      IP_MULTICAST_LOOP = ipMulticastLoop;
      GET_OPTION = getOption;
      SET_OPTION = setOption;
   }
}
