package io.netty.channel.socket.nio;

import io.netty.buffer.ByteBuf;
import io.netty.channel.AddressedEnvelope;
import io.netty.channel.Channel;
import io.netty.channel.ChannelException;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelMetadata;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelOutboundBuffer;
import io.netty.channel.ChannelPromise;
import io.netty.channel.DefaultAddressedEnvelope;
import io.netty.channel.RecvByteBufAllocator;
import io.netty.channel.nio.AbstractNioMessageChannel;
import io.netty.channel.socket.DatagramChannel;
import io.netty.channel.socket.DatagramChannelConfig;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.InternetProtocolFamily;
import io.netty.util.UncheckedBooleanSupplier;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.SocketUtils;
import io.netty.util.internal.StringUtil;
import io.netty.util.internal.SuppressJava6Requirement;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.SocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.MembershipKey;
import java.nio.channels.UnresolvedAddressException;
import java.nio.channels.spi.SelectorProvider;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class NioDatagramChannel extends AbstractNioMessageChannel implements DatagramChannel {
   private static final ChannelMetadata METADATA = new ChannelMetadata(true, 16);
   private static final SelectorProvider DEFAULT_SELECTOR_PROVIDER = SelectorProvider.provider();
   private static final String EXPECTED_TYPES = " (expected: " + StringUtil.simpleClassName(DatagramPacket.class) + ", " + StringUtil.simpleClassName(AddressedEnvelope.class) + '<' + StringUtil.simpleClassName(ByteBuf.class) + ", " + StringUtil.simpleClassName(SocketAddress.class) + ">, " + StringUtil.simpleClassName(ByteBuf.class) + ')';
   private final DatagramChannelConfig config;
   private Map memberships;

   private static java.nio.channels.DatagramChannel newSocket(SelectorProvider provider) {
      try {
         return provider.openDatagramChannel();
      } catch (IOException e) {
         throw new ChannelException("Failed to open a socket.", e);
      }
   }

   @SuppressJava6Requirement(
      reason = "Usage guarded by java version check"
   )
   private static java.nio.channels.DatagramChannel newSocket(SelectorProvider provider, InternetProtocolFamily ipFamily) {
      if (ipFamily == null) {
         return newSocket(provider);
      } else {
         checkJavaVersion();

         try {
            return provider.openDatagramChannel(ProtocolFamilyConverter.convert(ipFamily));
         } catch (IOException e) {
            throw new ChannelException("Failed to open a socket.", e);
         }
      }
   }

   private static void checkJavaVersion() {
      if (PlatformDependent.javaVersion() < 7) {
         throw new UnsupportedOperationException("Only supported on java 7+.");
      }
   }

   public NioDatagramChannel() {
      this(newSocket(DEFAULT_SELECTOR_PROVIDER));
   }

   public NioDatagramChannel(SelectorProvider provider) {
      this(newSocket(provider));
   }

   public NioDatagramChannel(InternetProtocolFamily ipFamily) {
      this(newSocket(DEFAULT_SELECTOR_PROVIDER, ipFamily));
   }

   public NioDatagramChannel(SelectorProvider provider, InternetProtocolFamily ipFamily) {
      this(newSocket(provider, ipFamily));
   }

   public NioDatagramChannel(java.nio.channels.DatagramChannel socket) {
      super((Channel)null, socket, 1);
      this.config = new NioDatagramChannelConfig(this, socket);
   }

   public ChannelMetadata metadata() {
      return METADATA;
   }

   public DatagramChannelConfig config() {
      return this.config;
   }

   public boolean isActive() {
      java.nio.channels.DatagramChannel ch = this.javaChannel();
      return ch.isOpen() && ((Boolean)this.config.getOption(ChannelOption.DATAGRAM_CHANNEL_ACTIVE_ON_REGISTRATION) && this.isRegistered() || ch.socket().isBound());
   }

   public boolean isConnected() {
      return this.javaChannel().isConnected();
   }

   protected java.nio.channels.DatagramChannel javaChannel() {
      return (java.nio.channels.DatagramChannel)super.javaChannel();
   }

   protected SocketAddress localAddress0() {
      return this.javaChannel().socket().getLocalSocketAddress();
   }

   protected SocketAddress remoteAddress0() {
      return this.javaChannel().socket().getRemoteSocketAddress();
   }

   protected void doBind(SocketAddress localAddress) throws Exception {
      this.doBind0(localAddress);
   }

   private void doBind0(SocketAddress localAddress) throws Exception {
      if (PlatformDependent.javaVersion() >= 7) {
         SocketUtils.bind(this.javaChannel(), localAddress);
      } else {
         this.javaChannel().socket().bind(localAddress);
      }

   }

   protected boolean doConnect(SocketAddress remoteAddress, SocketAddress localAddress) throws Exception {
      if (localAddress != null) {
         this.doBind0(localAddress);
      }

      boolean success = false;

      boolean var4;
      try {
         this.javaChannel().connect(remoteAddress);
         success = true;
         var4 = true;
      } finally {
         if (!success) {
            this.doClose();
         }

      }

      return var4;
   }

   protected void doFinishConnect() throws Exception {
      throw new Error();
   }

   protected void doDisconnect() throws Exception {
      this.javaChannel().disconnect();
   }

   protected void doClose() throws Exception {
      this.javaChannel().close();
   }

   protected int doReadMessages(List buf) throws Exception {
      java.nio.channels.DatagramChannel ch = this.javaChannel();
      DatagramChannelConfig config = this.config();
      RecvByteBufAllocator.Handle allocHandle = this.unsafe().recvBufAllocHandle();
      ByteBuf data = allocHandle.allocate(config.getAllocator());
      allocHandle.attemptedBytesRead(data.writableBytes());
      boolean free = true;

      byte var10;
      try {
         ByteBuffer nioData = data.internalNioBuffer(data.writerIndex(), data.writableBytes());
         int pos = nioData.position();
         InetSocketAddress remoteAddress = (InetSocketAddress)ch.receive(nioData);
         if (remoteAddress != null) {
            allocHandle.lastBytesRead(nioData.position() - pos);
            buf.add(new DatagramPacket(data.writerIndex(data.writerIndex() + allocHandle.lastBytesRead()), this.localAddress(), remoteAddress));
            free = false;
            var10 = 1;
            return var10;
         }

         var10 = 0;
      } catch (Throwable cause) {
         PlatformDependent.throwException(cause);
         byte pos = -1;
         return pos;
      } finally {
         if (free) {
            data.release();
         }

      }

      return var10;
   }

   protected boolean doWriteMessage(Object msg, ChannelOutboundBuffer in) throws Exception {
      SocketAddress remoteAddress;
      ByteBuf data;
      if (msg instanceof AddressedEnvelope) {
         AddressedEnvelope<ByteBuf, SocketAddress> envelope = (AddressedEnvelope)msg;
         remoteAddress = envelope.recipient();
         data = (ByteBuf)envelope.content();
      } else {
         data = (ByteBuf)msg;
         remoteAddress = null;
      }

      int dataLen = data.readableBytes();
      if (dataLen == 0) {
         return true;
      } else {
         ByteBuffer nioData = data.nioBufferCount() == 1 ? data.internalNioBuffer(data.readerIndex(), dataLen) : data.nioBuffer(data.readerIndex(), dataLen);
         int writtenBytes;
         if (remoteAddress != null) {
            writtenBytes = this.javaChannel().send(nioData, remoteAddress);
         } else {
            writtenBytes = this.javaChannel().write(nioData);
         }

         return writtenBytes > 0;
      }
   }

   private static void checkUnresolved(AddressedEnvelope envelope) {
      if (envelope.recipient() instanceof InetSocketAddress && ((InetSocketAddress)envelope.recipient()).isUnresolved()) {
         throw new UnresolvedAddressException();
      }
   }

   protected Object filterOutboundMessage(Object msg) {
      if (msg instanceof DatagramPacket) {
         DatagramPacket p = (DatagramPacket)msg;
         checkUnresolved(p);
         ByteBuf content = (ByteBuf)p.content();
         return isSingleDirectBuffer(content) ? p : new DatagramPacket(this.newDirectBuffer(p, content), (InetSocketAddress)p.recipient());
      } else if (msg instanceof ByteBuf) {
         ByteBuf buf = (ByteBuf)msg;
         return isSingleDirectBuffer(buf) ? buf : this.newDirectBuffer(buf);
      } else {
         if (msg instanceof AddressedEnvelope) {
            AddressedEnvelope<Object, SocketAddress> e = (AddressedEnvelope)msg;
            checkUnresolved(e);
            if (e.content() instanceof ByteBuf) {
               ByteBuf content = (ByteBuf)e.content();
               if (isSingleDirectBuffer(content)) {
                  return e;
               }

               return new DefaultAddressedEnvelope(this.newDirectBuffer(e, content), e.recipient());
            }
         }

         throw new UnsupportedOperationException("unsupported message type: " + StringUtil.simpleClassName(msg) + EXPECTED_TYPES);
      }
   }

   private static boolean isSingleDirectBuffer(ByteBuf buf) {
      return buf.isDirect() && buf.nioBufferCount() == 1;
   }

   protected boolean continueOnWriteError() {
      return true;
   }

   public InetSocketAddress localAddress() {
      return (InetSocketAddress)super.localAddress();
   }

   public InetSocketAddress remoteAddress() {
      return (InetSocketAddress)super.remoteAddress();
   }

   public ChannelFuture joinGroup(InetAddress multicastAddress) {
      return this.joinGroup(multicastAddress, this.newPromise());
   }

   public ChannelFuture joinGroup(InetAddress multicastAddress, ChannelPromise promise) {
      try {
         NetworkInterface iface = this.config.getNetworkInterface();
         if (iface == null) {
            iface = NetworkInterface.getByInetAddress(this.localAddress().getAddress());
         }

         return this.joinGroup(multicastAddress, iface, (InetAddress)null, promise);
      } catch (SocketException e) {
         promise.setFailure(e);
         return promise;
      }
   }

   public ChannelFuture joinGroup(InetSocketAddress multicastAddress, NetworkInterface networkInterface) {
      return this.joinGroup(multicastAddress, networkInterface, this.newPromise());
   }

   public ChannelFuture joinGroup(InetSocketAddress multicastAddress, NetworkInterface networkInterface, ChannelPromise promise) {
      return this.joinGroup(multicastAddress.getAddress(), networkInterface, (InetAddress)null, promise);
   }

   public ChannelFuture joinGroup(InetAddress multicastAddress, NetworkInterface networkInterface, InetAddress source) {
      return this.joinGroup(multicastAddress, networkInterface, source, this.newPromise());
   }

   @SuppressJava6Requirement(
      reason = "Usage guarded by java version check"
   )
   public ChannelFuture joinGroup(InetAddress multicastAddress, NetworkInterface networkInterface, InetAddress source, ChannelPromise promise) {
      checkJavaVersion();
      ObjectUtil.checkNotNull(multicastAddress, "multicastAddress");
      ObjectUtil.checkNotNull(networkInterface, "networkInterface");

      try {
         MembershipKey key;
         if (source == null) {
            key = this.javaChannel().join(multicastAddress, networkInterface);
         } else {
            key = this.javaChannel().join(multicastAddress, networkInterface, source);
         }

         synchronized(this) {
            List<MembershipKey> keys = null;
            if (this.memberships == null) {
               this.memberships = new HashMap();
            } else {
               keys = (List)this.memberships.get(multicastAddress);
            }

            if (keys == null) {
               keys = new ArrayList();
               this.memberships.put(multicastAddress, keys);
            }

            keys.add(key);
         }

         promise.setSuccess();
      } catch (Throwable e) {
         promise.setFailure(e);
      }

      return promise;
   }

   public ChannelFuture leaveGroup(InetAddress multicastAddress) {
      return this.leaveGroup(multicastAddress, this.newPromise());
   }

   public ChannelFuture leaveGroup(InetAddress multicastAddress, ChannelPromise promise) {
      try {
         return this.leaveGroup(multicastAddress, NetworkInterface.getByInetAddress(this.localAddress().getAddress()), (InetAddress)null, promise);
      } catch (SocketException e) {
         promise.setFailure(e);
         return promise;
      }
   }

   public ChannelFuture leaveGroup(InetSocketAddress multicastAddress, NetworkInterface networkInterface) {
      return this.leaveGroup(multicastAddress, networkInterface, this.newPromise());
   }

   public ChannelFuture leaveGroup(InetSocketAddress multicastAddress, NetworkInterface networkInterface, ChannelPromise promise) {
      return this.leaveGroup(multicastAddress.getAddress(), networkInterface, (InetAddress)null, promise);
   }

   public ChannelFuture leaveGroup(InetAddress multicastAddress, NetworkInterface networkInterface, InetAddress source) {
      return this.leaveGroup(multicastAddress, networkInterface, source, this.newPromise());
   }

   @SuppressJava6Requirement(
      reason = "Usage guarded by java version check"
   )
   public ChannelFuture leaveGroup(InetAddress multicastAddress, NetworkInterface networkInterface, InetAddress source, ChannelPromise promise) {
      checkJavaVersion();
      ObjectUtil.checkNotNull(multicastAddress, "multicastAddress");
      ObjectUtil.checkNotNull(networkInterface, "networkInterface");
      synchronized(this) {
         if (this.memberships != null) {
            List<MembershipKey> keys = (List)this.memberships.get(multicastAddress);
            if (keys != null) {
               Iterator<MembershipKey> keyIt = keys.iterator();

               while(keyIt.hasNext()) {
                  MembershipKey key = (MembershipKey)keyIt.next();
                  if (networkInterface.equals(key.networkInterface()) && (source == null && key.sourceAddress() == null || source != null && source.equals(key.sourceAddress()))) {
                     key.drop();
                     keyIt.remove();
                  }
               }

               if (keys.isEmpty()) {
                  this.memberships.remove(multicastAddress);
               }
            }
         }
      }

      promise.setSuccess();
      return promise;
   }

   public ChannelFuture block(InetAddress multicastAddress, NetworkInterface networkInterface, InetAddress sourceToBlock) {
      return this.block(multicastAddress, networkInterface, sourceToBlock, this.newPromise());
   }

   @SuppressJava6Requirement(
      reason = "Usage guarded by java version check"
   )
   public ChannelFuture block(InetAddress multicastAddress, NetworkInterface networkInterface, InetAddress sourceToBlock, ChannelPromise promise) {
      checkJavaVersion();
      ObjectUtil.checkNotNull(multicastAddress, "multicastAddress");
      ObjectUtil.checkNotNull(sourceToBlock, "sourceToBlock");
      ObjectUtil.checkNotNull(networkInterface, "networkInterface");
      synchronized(this) {
         if (this.memberships != null) {
            for(MembershipKey key : (List)this.memberships.get(multicastAddress)) {
               if (networkInterface.equals(key.networkInterface())) {
                  try {
                     key.block(sourceToBlock);
                  } catch (IOException e) {
                     promise.setFailure(e);
                  }
               }
            }
         }
      }

      promise.setSuccess();
      return promise;
   }

   public ChannelFuture block(InetAddress multicastAddress, InetAddress sourceToBlock) {
      return this.block(multicastAddress, sourceToBlock, this.newPromise());
   }

   public ChannelFuture block(InetAddress multicastAddress, InetAddress sourceToBlock, ChannelPromise promise) {
      try {
         return this.block(multicastAddress, NetworkInterface.getByInetAddress(this.localAddress().getAddress()), sourceToBlock, promise);
      } catch (SocketException e) {
         promise.setFailure(e);
         return promise;
      }
   }

   /** @deprecated */
   @Deprecated
   protected void setReadPending(boolean readPending) {
      super.setReadPending(readPending);
   }

   void clearReadPending0() {
      this.clearReadPending();
   }

   protected boolean closeOnReadError(Throwable cause) {
      return cause instanceof SocketException ? false : super.closeOnReadError(cause);
   }

   protected boolean continueReading(RecvByteBufAllocator.Handle allocHandle) {
      return allocHandle instanceof RecvByteBufAllocator.ExtendedHandle ? ((RecvByteBufAllocator.ExtendedHandle)allocHandle).continueReading(UncheckedBooleanSupplier.TRUE_SUPPLIER) : allocHandle.continueReading();
   }
}
