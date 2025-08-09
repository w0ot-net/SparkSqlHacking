package io.vertx.core.datagram.impl;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.datagram.DatagramPacket;
import io.vertx.core.net.SocketAddress;
import java.net.InetSocketAddress;

final class DatagramPacketImpl implements DatagramPacket {
   private final InetSocketAddress sender;
   private final Buffer buffer;
   private SocketAddress senderAddress;

   DatagramPacketImpl(InetSocketAddress sender, Buffer buffer) {
      this.sender = sender;
      this.buffer = buffer;
   }

   public SocketAddress sender() {
      if (this.senderAddress == null) {
         this.senderAddress = SocketAddress.inetSocketAddress(this.sender);
      }

      return this.senderAddress;
   }

   public Buffer data() {
      return this.buffer;
   }
}
