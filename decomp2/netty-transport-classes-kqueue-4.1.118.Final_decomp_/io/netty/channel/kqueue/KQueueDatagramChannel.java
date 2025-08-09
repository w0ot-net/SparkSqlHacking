package io.netty.channel.kqueue;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.AddressedEnvelope;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.ChannelPromise;
import io.netty.channel.DefaultAddressedEnvelope;
import io.netty.channel.socket.DatagramChannel;
import io.netty.channel.socket.DatagramChannelConfig;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.InternetProtocolFamily;
import io.netty.channel.unix.DatagramSocketAddress;
import io.netty.channel.unix.Errors;
import io.netty.channel.unix.IovArray;
import io.netty.channel.unix.UnixChannelUtil;
import io.netty.util.UncheckedBooleanSupplier;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.StringUtil;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.PortUnreachableException;
import java.net.SocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.UnresolvedAddressException;

public final class KQueueDatagramChannel extends AbstractKQueueDatagramChannel implements DatagramChannel {
   private static final String EXPECTED_TYPES = " (expected: " + StringUtil.simpleClassName(DatagramPacket.class) + ", " + StringUtil.simpleClassName(AddressedEnvelope.class) + '<' + StringUtil.simpleClassName(ByteBuf.class) + ", " + StringUtil.simpleClassName(InetSocketAddress.class) + ">, " + StringUtil.simpleClassName(ByteBuf.class) + ')';
   private volatile boolean connected;
   private final KQueueDatagramChannelConfig config;

   public KQueueDatagramChannel() {
      super((Channel)null, BsdSocket.newSocketDgram(), false);
      this.config = new KQueueDatagramChannelConfig(this);
   }

   public KQueueDatagramChannel(InternetProtocolFamily protocol) {
      super((Channel)null, BsdSocket.newSocketDgram(protocol), false);
      this.config = new KQueueDatagramChannelConfig(this);
   }

   public KQueueDatagramChannel(int fd) {
      this(new BsdSocket(fd), true);
   }

   KQueueDatagramChannel(BsdSocket socket, boolean active) {
      super((Channel)null, socket, active);
      this.config = new KQueueDatagramChannelConfig(this);
   }

   public InetSocketAddress remoteAddress() {
      return (InetSocketAddress)super.remoteAddress();
   }

   public InetSocketAddress localAddress() {
      return (InetSocketAddress)super.localAddress();
   }

   public boolean isActive() {
      return this.socket.isOpen() && (this.config.getActiveOnOpen() && this.isRegistered() || this.active);
   }

   public boolean isConnected() {
      return this.connected;
   }

   public ChannelFuture joinGroup(InetAddress multicastAddress) {
      return this.joinGroup(multicastAddress, this.newPromise());
   }

   public ChannelFuture joinGroup(InetAddress multicastAddress, ChannelPromise promise) {
      try {
         NetworkInterface iface = this.config().getNetworkInterface();
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

   public ChannelFuture joinGroup(InetAddress multicastAddress, NetworkInterface networkInterface, InetAddress source, ChannelPromise promise) {
      ObjectUtil.checkNotNull(multicastAddress, "multicastAddress");
      ObjectUtil.checkNotNull(networkInterface, "networkInterface");
      promise.setFailure(new UnsupportedOperationException("Multicast not supported"));
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

   public ChannelFuture leaveGroup(InetAddress multicastAddress, NetworkInterface networkInterface, InetAddress source, ChannelPromise promise) {
      ObjectUtil.checkNotNull(multicastAddress, "multicastAddress");
      ObjectUtil.checkNotNull(networkInterface, "networkInterface");
      promise.setFailure(new UnsupportedOperationException("Multicast not supported"));
      return promise;
   }

   public ChannelFuture block(InetAddress multicastAddress, NetworkInterface networkInterface, InetAddress sourceToBlock) {
      return this.block(multicastAddress, networkInterface, sourceToBlock, this.newPromise());
   }

   public ChannelFuture block(InetAddress multicastAddress, NetworkInterface networkInterface, InetAddress sourceToBlock, ChannelPromise promise) {
      ObjectUtil.checkNotNull(multicastAddress, "multicastAddress");
      ObjectUtil.checkNotNull(sourceToBlock, "sourceToBlock");
      ObjectUtil.checkNotNull(networkInterface, "networkInterface");
      promise.setFailure(new UnsupportedOperationException("Multicast not supported"));
      return promise;
   }

   public ChannelFuture block(InetAddress multicastAddress, InetAddress sourceToBlock) {
      return this.block(multicastAddress, sourceToBlock, this.newPromise());
   }

   public ChannelFuture block(InetAddress multicastAddress, InetAddress sourceToBlock, ChannelPromise promise) {
      try {
         return this.block(multicastAddress, NetworkInterface.getByInetAddress(this.localAddress().getAddress()), sourceToBlock, promise);
      } catch (Throwable e) {
         promise.setFailure(e);
         return promise;
      }
   }

   protected AbstractKQueueChannel.AbstractKQueueUnsafe newUnsafe() {
      return new KQueueDatagramChannelUnsafe();
   }

   protected void doBind(SocketAddress localAddress) throws Exception {
      super.doBind(localAddress);
      this.active = true;
   }

   protected boolean doWriteMessage(Object msg) throws Exception {
      ByteBuf data;
      InetSocketAddress remoteAddress;
      if (msg instanceof AddressedEnvelope) {
         AddressedEnvelope<ByteBuf, InetSocketAddress> envelope = (AddressedEnvelope)msg;
         data = (ByteBuf)envelope.content();
         remoteAddress = (InetSocketAddress)envelope.recipient();
      } else {
         data = (ByteBuf)msg;
         remoteAddress = null;
      }

      int dataLen = data.readableBytes();
      if (dataLen == 0) {
         return true;
      } else {
         long writtenBytes;
         if (data.hasMemoryAddress()) {
            long memoryAddress = data.memoryAddress();
            if (remoteAddress == null) {
               writtenBytes = (long)this.socket.writeAddress(memoryAddress, data.readerIndex(), data.writerIndex());
            } else {
               writtenBytes = (long)this.socket.sendToAddress(memoryAddress, data.readerIndex(), data.writerIndex(), remoteAddress.getAddress(), remoteAddress.getPort());
            }
         } else if (data.nioBufferCount() > 1) {
            IovArray array = ((KQueueEventLoop)this.eventLoop()).cleanArray();
            array.add(data, data.readerIndex(), data.readableBytes());
            int cnt = array.count();

            assert cnt != 0;

            if (remoteAddress == null) {
               writtenBytes = this.socket.writevAddresses(array.memoryAddress(0), cnt);
            } else {
               writtenBytes = (long)this.socket.sendToAddresses(array.memoryAddress(0), cnt, remoteAddress.getAddress(), remoteAddress.getPort());
            }
         } else {
            ByteBuffer nioData = data.internalNioBuffer(data.readerIndex(), data.readableBytes());
            if (remoteAddress == null) {
               writtenBytes = (long)this.socket.write(nioData, nioData.position(), nioData.limit());
            } else {
               writtenBytes = (long)this.socket.sendTo(nioData, nioData.position(), nioData.limit(), remoteAddress.getAddress(), remoteAddress.getPort());
            }
         }

         return writtenBytes > 0L;
      }
   }

   private static void checkUnresolved(AddressedEnvelope envelope) {
      if (envelope.recipient() instanceof InetSocketAddress && ((InetSocketAddress)envelope.recipient()).isUnresolved()) {
         throw new UnresolvedAddressException();
      }
   }

   protected Object filterOutboundMessage(Object msg) {
      if (msg instanceof DatagramPacket) {
         DatagramPacket packet = (DatagramPacket)msg;
         checkUnresolved(packet);
         ByteBuf content = (ByteBuf)packet.content();
         return UnixChannelUtil.isBufferCopyNeededForWrite(content) ? new DatagramPacket(this.newDirectBuffer(packet, content), (InetSocketAddress)packet.recipient()) : msg;
      } else if (msg instanceof ByteBuf) {
         ByteBuf buf = (ByteBuf)msg;
         return UnixChannelUtil.isBufferCopyNeededForWrite(buf) ? this.newDirectBuffer(buf) : buf;
      } else {
         if (msg instanceof AddressedEnvelope) {
            AddressedEnvelope<Object, SocketAddress> e = (AddressedEnvelope)msg;
            checkUnresolved(e);
            if (e.content() instanceof ByteBuf && (e.recipient() == null || e.recipient() instanceof InetSocketAddress)) {
               ByteBuf content = (ByteBuf)e.content();
               return UnixChannelUtil.isBufferCopyNeededForWrite(content) ? new DefaultAddressedEnvelope(this.newDirectBuffer(e, content), (InetSocketAddress)e.recipient()) : e;
            }
         }

         throw new UnsupportedOperationException("unsupported message type: " + StringUtil.simpleClassName(msg) + EXPECTED_TYPES);
      }
   }

   public KQueueDatagramChannelConfig config() {
      return this.config;
   }

   protected void doDisconnect() throws Exception {
      this.socket.disconnect();
      this.connected = this.active = false;
      this.resetCachedAddresses();
   }

   protected boolean doConnect(SocketAddress remoteAddress, SocketAddress localAddress) throws Exception {
      if (super.doConnect(remoteAddress, localAddress)) {
         this.connected = true;
         return true;
      } else {
         return false;
      }
   }

   protected void doClose() throws Exception {
      super.doClose();
      this.connected = false;
   }

   final class KQueueDatagramChannelUnsafe extends AbstractKQueueChannel.AbstractKQueueUnsafe {
      void readReady(KQueueRecvByteAllocatorHandle allocHandle) {
         assert KQueueDatagramChannel.this.eventLoop().inEventLoop();

         DatagramChannelConfig config = KQueueDatagramChannel.this.config();
         if (KQueueDatagramChannel.this.shouldBreakReadReady(config)) {
            this.clearReadFilter0();
         } else {
            ChannelPipeline pipeline = KQueueDatagramChannel.this.pipeline();
            ByteBufAllocator allocator = config.getAllocator();
            allocHandle.reset(config);
            this.readReadyBefore();
            Throwable exception = null;

            try {
               ByteBuf byteBuf = null;

               try {
                  boolean connected = KQueueDatagramChannel.this.isConnected();

                  do {
                     byteBuf = allocHandle.allocate(allocator);
                     allocHandle.attemptedBytesRead(byteBuf.writableBytes());
                     DatagramPacket packet;
                     if (connected) {
                        try {
                           allocHandle.lastBytesRead(KQueueDatagramChannel.this.doReadBytes(byteBuf));
                        } catch (Errors.NativeIoException e) {
                           if (e.expectedErr() == Errors.ERROR_ECONNREFUSED_NEGATIVE) {
                              PortUnreachableException error = new PortUnreachableException(e.getMessage());
                              error.initCause(e);
                              throw error;
                           }

                           throw e;
                        }

                        if (allocHandle.lastBytesRead() <= 0) {
                           byteBuf.release();
                           ByteBuf var19 = null;
                           break;
                        }

                        packet = new DatagramPacket(byteBuf, (InetSocketAddress)this.localAddress(), (InetSocketAddress)this.remoteAddress());
                     } else {
                        DatagramSocketAddress remoteAddress;
                        if (byteBuf.hasMemoryAddress()) {
                           remoteAddress = KQueueDatagramChannel.this.socket.recvFromAddress(byteBuf.memoryAddress(), byteBuf.writerIndex(), byteBuf.capacity());
                        } else {
                           ByteBuffer nioData = byteBuf.internalNioBuffer(byteBuf.writerIndex(), byteBuf.writableBytes());
                           remoteAddress = KQueueDatagramChannel.this.socket.recvFrom(nioData, nioData.position(), nioData.limit());
                        }

                        if (remoteAddress == null) {
                           allocHandle.lastBytesRead(-1);
                           byteBuf.release();
                           ByteBuf var21 = null;
                           break;
                        }

                        InetSocketAddress localAddress = remoteAddress.localAddress();
                        if (localAddress == null) {
                           localAddress = (InetSocketAddress)this.localAddress();
                        }

                        allocHandle.lastBytesRead(remoteAddress.receivedAmount());
                        byteBuf.writerIndex(byteBuf.writerIndex() + allocHandle.lastBytesRead());
                        packet = new DatagramPacket(byteBuf, localAddress, remoteAddress);
                     }

                     allocHandle.incMessagesRead(1);
                     this.readPending = false;
                     pipeline.fireChannelRead(packet);
                     ByteBuf var20 = null;
                  } while(allocHandle.continueReading(UncheckedBooleanSupplier.TRUE_SUPPLIER));
               } catch (Throwable t) {
                  if (byteBuf != null) {
                     byteBuf.release();
                  }

                  exception = t;
               }

               allocHandle.readComplete();
               pipeline.fireChannelReadComplete();
               if (exception != null) {
                  pipeline.fireExceptionCaught(exception);
               }
            } finally {
               this.readReadyFinally(config);
            }

         }
      }
   }
}
