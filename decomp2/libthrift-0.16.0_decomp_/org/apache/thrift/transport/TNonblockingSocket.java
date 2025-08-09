package org.apache.thrift.transport;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import org.apache.thrift.TConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TNonblockingSocket extends TNonblockingTransport {
   private static final Logger LOGGER = LoggerFactory.getLogger(TNonblockingSocket.class.getName());
   private final SocketAddress socketAddress_;
   private final SocketChannel socketChannel_;

   public TNonblockingSocket(String host, int port) throws IOException, TTransportException {
      this(host, port, 0);
   }

   public TNonblockingSocket(String host, int port, int timeout) throws IOException, TTransportException {
      this(SocketChannel.open(), timeout, new InetSocketAddress(host, port));
   }

   public TNonblockingSocket(SocketChannel socketChannel) throws IOException, TTransportException {
      this(socketChannel, 0, (SocketAddress)null);
      if (!socketChannel.isConnected()) {
         throw new IOException("Socket must already be connected");
      }
   }

   private TNonblockingSocket(SocketChannel socketChannel, int timeout, SocketAddress socketAddress) throws IOException, TTransportException {
      this(new TConfiguration(), socketChannel, timeout, socketAddress);
   }

   private TNonblockingSocket(TConfiguration config, SocketChannel socketChannel, int timeout, SocketAddress socketAddress) throws IOException, TTransportException {
      super(config);
      this.socketChannel_ = socketChannel;
      this.socketAddress_ = socketAddress;
      socketChannel.configureBlocking(false);
      Socket socket = socketChannel.socket();
      socket.setSoLinger(false, 0);
      socket.setTcpNoDelay(true);
      socket.setKeepAlive(true);
      this.setTimeout(timeout);
   }

   public SelectionKey registerSelector(Selector selector, int interests) throws IOException {
      return this.socketChannel_.register(selector, interests);
   }

   public void setTimeout(int timeout) {
      try {
         this.socketChannel_.socket().setSoTimeout(timeout);
      } catch (SocketException sx) {
         LOGGER.warn("Could not set socket timeout.", sx);
      }

   }

   public SocketChannel getSocketChannel() {
      return this.socketChannel_;
   }

   public boolean isOpen() {
      return this.socketChannel_.isOpen() && this.socketChannel_.isConnected();
   }

   public void open() throws TTransportException {
      throw new RuntimeException("open() is not implemented for TNonblockingSocket");
   }

   public int read(ByteBuffer buffer) throws TTransportException {
      try {
         return this.socketChannel_.read(buffer);
      } catch (IOException iox) {
         throw new TTransportException(0, iox);
      }
   }

   public int read(byte[] buf, int off, int len) throws TTransportException {
      if ((this.socketChannel_.validOps() & 1) != 1) {
         throw new TTransportException(1, "Cannot read from write-only socket channel");
      } else {
         try {
            return this.socketChannel_.read(ByteBuffer.wrap(buf, off, len));
         } catch (IOException iox) {
            throw new TTransportException(0, iox);
         }
      }
   }

   public int write(ByteBuffer buffer) throws TTransportException {
      try {
         return this.socketChannel_.write(buffer);
      } catch (IOException iox) {
         throw new TTransportException(0, iox);
      }
   }

   public void write(byte[] buf, int off, int len) throws TTransportException {
      if ((this.socketChannel_.validOps() & 4) != 4) {
         throw new TTransportException(1, "Cannot write to write-only socket channel");
      } else {
         this.write(ByteBuffer.wrap(buf, off, len));
      }
   }

   public void flush() throws TTransportException {
   }

   public void close() {
      try {
         this.socketChannel_.close();
      } catch (IOException iox) {
         LOGGER.warn("Could not close socket.", iox);
      }

   }

   public boolean startConnect() throws IOException {
      return this.socketChannel_.connect(this.socketAddress_);
   }

   public boolean finishConnect() throws IOException {
      return this.socketChannel_.finishConnect();
   }

   public String toString() {
      return "[remote: " + this.socketChannel_.socket().getRemoteSocketAddress() + ", local: " + this.socketChannel_.socket().getLocalAddress() + "]";
   }
}
