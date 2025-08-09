package org.apache.thrift.transport;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TServerSocket extends TServerTransport {
   private static final Logger LOGGER = LoggerFactory.getLogger(TServerSocket.class.getName());
   private ServerSocket serverSocket_;
   private int clientTimeout_;

   public TServerSocket(ServerSocket serverSocket) throws TTransportException {
      this((ServerSocket)serverSocket, 0);
   }

   public TServerSocket(ServerSocket serverSocket, int clientTimeout) throws TTransportException {
      this((ServerSocketTransportArgs)(new ServerSocketTransportArgs()).serverSocket(serverSocket).clientTimeout(clientTimeout));
   }

   public TServerSocket(int port) throws TTransportException {
      this(port, 0);
   }

   public TServerSocket(int port, int clientTimeout) throws TTransportException {
      this(new InetSocketAddress(port), clientTimeout);
   }

   public TServerSocket(InetSocketAddress bindAddr) throws TTransportException {
      this((InetSocketAddress)bindAddr, 0);
   }

   public TServerSocket(InetSocketAddress bindAddr, int clientTimeout) throws TTransportException {
      this((ServerSocketTransportArgs)((ServerSocketTransportArgs)(new ServerSocketTransportArgs()).bindAddr(bindAddr)).clientTimeout(clientTimeout));
   }

   public TServerSocket(ServerSocketTransportArgs args) throws TTransportException {
      this.serverSocket_ = null;
      this.clientTimeout_ = 0;
      this.clientTimeout_ = args.clientTimeout;
      if (args.serverSocket != null) {
         this.serverSocket_ = args.serverSocket;
      } else {
         try {
            this.serverSocket_ = new ServerSocket();
            this.serverSocket_.setReuseAddress(true);
            this.serverSocket_.bind(args.bindAddr, args.backlog);
         } catch (IOException ioe) {
            this.close();
            throw new TTransportException("Could not create ServerSocket on address " + args.bindAddr.toString() + ".", ioe);
         }
      }
   }

   public void listen() throws TTransportException {
      if (this.serverSocket_ != null) {
         try {
            this.serverSocket_.setSoTimeout(0);
         } catch (SocketException sx) {
            LOGGER.error("Could not set socket timeout.", sx);
         }
      }

   }

   public TSocket accept() throws TTransportException {
      if (this.serverSocket_ == null) {
         throw new TTransportException(1, "No underlying server socket.");
      } else {
         Socket result;
         try {
            result = this.serverSocket_.accept();
         } catch (Exception e) {
            throw new TTransportException(e);
         }

         if (result == null) {
            throw new TTransportException("Blocking server's accept() may not return NULL");
         } else {
            TSocket socket = new TSocket(result);
            socket.setTimeout(this.clientTimeout_);
            return socket;
         }
      }
   }

   public void close() {
      if (this.serverSocket_ != null) {
         try {
            this.serverSocket_.close();
         } catch (IOException iox) {
            LOGGER.warn("Could not close server socket.", iox);
         }

         this.serverSocket_ = null;
      }

   }

   public void interrupt() {
      this.close();
   }

   public ServerSocket getServerSocket() {
      return this.serverSocket_;
   }

   public static class ServerSocketTransportArgs extends TServerTransport.AbstractServerTransportArgs {
      ServerSocket serverSocket;

      public ServerSocketTransportArgs serverSocket(ServerSocket serverSocket) {
         this.serverSocket = serverSocket;
         return this;
      }
   }
}
