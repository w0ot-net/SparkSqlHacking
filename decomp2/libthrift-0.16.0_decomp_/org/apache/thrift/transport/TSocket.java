package org.apache.thrift.transport;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import org.apache.thrift.TConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TSocket extends TIOStreamTransport {
   private static final Logger LOGGER = LoggerFactory.getLogger(TSocket.class.getName());
   private Socket socket_;
   private String host_;
   private int port_;
   private int socketTimeout_;
   private int connectTimeout_;

   public TSocket(Socket socket) throws TTransportException {
      super(new TConfiguration());
      this.socket_ = socket;

      try {
         this.socket_.setSoLinger(false, 0);
         this.socket_.setTcpNoDelay(true);
         this.socket_.setKeepAlive(true);
      } catch (SocketException sx) {
         LOGGER.warn("Could not configure socket.", sx);
      }

      if (this.isOpen()) {
         try {
            this.inputStream_ = new BufferedInputStream(this.socket_.getInputStream());
            this.outputStream_ = new BufferedOutputStream(this.socket_.getOutputStream());
         } catch (IOException iox) {
            this.close();
            throw new TTransportException(1, iox);
         }
      }

   }

   public TSocket(TConfiguration config, String host, int port) throws TTransportException {
      this(config, host, port, 0);
   }

   public TSocket(String host, int port) throws TTransportException {
      this(new TConfiguration(), host, port, 0);
   }

   public TSocket(String host, int port, int timeout) throws TTransportException {
      this(new TConfiguration(), host, port, timeout, timeout);
   }

   public TSocket(TConfiguration config, String host, int port, int timeout) throws TTransportException {
      this(config, host, port, timeout, timeout);
   }

   public TSocket(TConfiguration config, String host, int port, int socketTimeout, int connectTimeout) throws TTransportException {
      super(config);
      this.host_ = host;
      this.port_ = port;
      this.socketTimeout_ = socketTimeout;
      this.connectTimeout_ = connectTimeout;
      this.initSocket();
   }

   private void initSocket() {
      this.socket_ = new Socket();

      try {
         this.socket_.setSoLinger(false, 0);
         this.socket_.setTcpNoDelay(true);
         this.socket_.setKeepAlive(true);
         this.socket_.setSoTimeout(this.socketTimeout_);
      } catch (SocketException sx) {
         LOGGER.error("Could not configure socket.", sx);
      }

   }

   public void setTimeout(int timeout) {
      this.setConnectTimeout(timeout);
      this.setSocketTimeout(timeout);
   }

   public void setConnectTimeout(int timeout) {
      this.connectTimeout_ = timeout;
   }

   public void setSocketTimeout(int timeout) {
      this.socketTimeout_ = timeout;

      try {
         this.socket_.setSoTimeout(timeout);
      } catch (SocketException sx) {
         LOGGER.warn("Could not set socket timeout.", sx);
      }

   }

   public Socket getSocket() {
      if (this.socket_ == null) {
         this.initSocket();
      }

      return this.socket_;
   }

   public boolean isOpen() {
      return this.socket_ == null ? false : this.socket_.isConnected();
   }

   public void open() throws TTransportException {
      if (this.isOpen()) {
         throw new TTransportException(2, "Socket already connected.");
      } else if (this.host_ != null && this.host_.length() != 0) {
         if (this.port_ > 0 && this.port_ <= 65535) {
            if (this.socket_ == null) {
               this.initSocket();
            }

            try {
               this.socket_.connect(new InetSocketAddress(this.host_, this.port_), this.connectTimeout_);
               this.inputStream_ = new BufferedInputStream(this.socket_.getInputStream());
               this.outputStream_ = new BufferedOutputStream(this.socket_.getOutputStream());
            } catch (IOException iox) {
               this.close();
               throw new TTransportException(1, iox);
            }
         } else {
            throw new TTransportException(1, "Invalid port " + this.port_);
         }
      } else {
         throw new TTransportException(1, "Cannot open null host.");
      }
   }

   public void close() {
      super.close();
      if (this.socket_ != null) {
         try {
            this.socket_.close();
         } catch (IOException iox) {
            LOGGER.warn("Could not close socket.", iox);
         }

         this.socket_ = null;
      }

   }
}
