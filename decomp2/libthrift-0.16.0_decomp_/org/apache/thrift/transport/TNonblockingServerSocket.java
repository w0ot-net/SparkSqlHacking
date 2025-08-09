package org.apache.thrift.transport;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.SocketException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TNonblockingServerSocket extends TNonblockingServerTransport {
   private static final Logger LOGGER = LoggerFactory.getLogger(TNonblockingServerSocket.class.getName());
   private ServerSocketChannel serverSocketChannel;
   private ServerSocket serverSocket_;
   private int clientTimeout_;

   public TNonblockingServerSocket(int port) throws TTransportException {
      this(port, 0);
   }

   public TNonblockingServerSocket(int port, int clientTimeout) throws TTransportException {
      this((NonblockingAbstractServerSocketArgs)((NonblockingAbstractServerSocketArgs)(new NonblockingAbstractServerSocketArgs()).port(port)).clientTimeout(clientTimeout));
   }

   public TNonblockingServerSocket(InetSocketAddress bindAddr) throws TTransportException {
      this(bindAddr, 0);
   }

   public TNonblockingServerSocket(InetSocketAddress bindAddr, int clientTimeout) throws TTransportException {
      this((NonblockingAbstractServerSocketArgs)((NonblockingAbstractServerSocketArgs)(new NonblockingAbstractServerSocketArgs()).bindAddr(bindAddr)).clientTimeout(clientTimeout));
   }

   public TNonblockingServerSocket(NonblockingAbstractServerSocketArgs args) throws TTransportException {
      this.serverSocketChannel = null;
      this.serverSocket_ = null;
      this.clientTimeout_ = 0;
      this.clientTimeout_ = args.clientTimeout;

      try {
         this.serverSocketChannel = ServerSocketChannel.open();
         this.serverSocketChannel.configureBlocking(false);
         this.serverSocket_ = this.serverSocketChannel.socket();
         this.serverSocket_.setReuseAddress(true);
         this.serverSocket_.bind(args.bindAddr, args.backlog);
      } catch (IOException ioe) {
         this.serverSocket_ = null;
         throw new TTransportException("Could not create ServerSocket on address " + args.bindAddr.toString() + ".", ioe);
      }
   }

   public void listen() throws TTransportException {
      if (this.serverSocket_ != null) {
         try {
            this.serverSocket_.setSoTimeout(0);
         } catch (SocketException sx) {
            LOGGER.error("Socket exception while setting socket timeout", sx);
         }
      }

   }

   public TNonblockingSocket accept() throws TTransportException {
      if (this.serverSocket_ == null) {
         throw new TTransportException(1, "No underlying server socket.");
      } else {
         try {
            SocketChannel socketChannel = this.serverSocketChannel.accept();
            if (socketChannel == null) {
               return null;
            } else {
               TNonblockingSocket tsocket = new TNonblockingSocket(socketChannel);
               tsocket.setTimeout(this.clientTimeout_);
               return tsocket;
            }
         } catch (IOException iox) {
            throw new TTransportException(iox);
         }
      }
   }

   public void registerSelector(Selector selector) {
      try {
         this.serverSocketChannel.register(selector, 16);
      } catch (ClosedChannelException var3) {
      }

   }

   public void close() {
      if (this.serverSocket_ != null) {
         try {
            this.serverSocket_.close();
         } catch (IOException iox) {
            LOGGER.warn("WARNING: Could not close server socket: " + iox.getMessage());
         }

         this.serverSocket_ = null;
      }

   }

   public void interrupt() {
      this.close();
   }

   public int getPort() {
      return this.serverSocket_ == null ? -1 : this.serverSocket_.getLocalPort();
   }

   ServerSocketChannel getServerSocketChannel() {
      return this.serverSocketChannel;
   }

   public static class NonblockingAbstractServerSocketArgs extends TServerTransport.AbstractServerTransportArgs {
   }
}
