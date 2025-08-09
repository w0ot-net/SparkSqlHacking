package com.codahale.metrics.graphite;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import javax.net.SocketFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Graphite implements GraphiteSender {
   private final String hostname;
   private final int port;
   private final InetSocketAddress address;
   private final SocketFactory socketFactory;
   private final Charset charset;
   private Socket socket;
   private Writer writer;
   private int failures;
   private static final Logger LOGGER = LoggerFactory.getLogger(Graphite.class);

   public Graphite(String hostname, int port) {
      this(hostname, port, SocketFactory.getDefault());
   }

   public Graphite(String hostname, int port, SocketFactory socketFactory) {
      this(hostname, port, socketFactory, StandardCharsets.UTF_8);
   }

   public Graphite(String hostname, int port, SocketFactory socketFactory, Charset charset) {
      if (hostname != null && !hostname.isEmpty()) {
         if (port >= 0 && port <= 65535) {
            this.hostname = hostname;
            this.port = port;
            this.address = null;
            this.socketFactory = (SocketFactory)Objects.requireNonNull(socketFactory, "socketFactory must not be null");
            this.charset = (Charset)Objects.requireNonNull(charset, "charset must not be null");
         } else {
            throw new IllegalArgumentException("port must be a valid IP port (0-65535)");
         }
      } else {
         throw new IllegalArgumentException("hostname must not be null or empty");
      }
   }

   public Graphite(InetSocketAddress address) {
      this(address, SocketFactory.getDefault());
   }

   public Graphite(InetSocketAddress address, SocketFactory socketFactory) {
      this(address, socketFactory, StandardCharsets.UTF_8);
   }

   public Graphite(InetSocketAddress address, SocketFactory socketFactory, Charset charset) {
      this.hostname = null;
      this.port = -1;
      this.address = (InetSocketAddress)Objects.requireNonNull(address, "address must not be null");
      this.socketFactory = (SocketFactory)Objects.requireNonNull(socketFactory, "socketFactory must not be null");
      this.charset = (Charset)Objects.requireNonNull(charset, "charset must not be null");
   }

   public void connect() throws IllegalStateException, IOException {
      if (this.isConnected()) {
         throw new IllegalStateException("Already connected");
      } else {
         InetSocketAddress address = this.address;
         if (address == null || address.getHostName() == null && this.hostname != null) {
            address = new InetSocketAddress(this.hostname, this.port);
         }

         if (address.getAddress() == null) {
            throw new UnknownHostException(address.getHostName());
         } else {
            this.socket = this.socketFactory.createSocket(address.getAddress(), address.getPort());
            this.writer = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream(), this.charset));
         }
      }
   }

   public boolean isConnected() {
      return this.socket != null && this.socket.isConnected() && !this.socket.isClosed();
   }

   public void send(String name, String value, long timestamp) throws IOException {
      try {
         this.writer.write(this.sanitize(name));
         this.writer.write(32);
         this.writer.write(this.sanitize(value));
         this.writer.write(32);
         this.writer.write(Long.toString(timestamp));
         this.writer.write(10);
         this.failures = 0;
      } catch (IOException e) {
         ++this.failures;
         throw e;
      }
   }

   public int getFailures() {
      return this.failures;
   }

   public void flush() throws IOException {
      if (this.writer != null) {
         this.writer.flush();
      }

   }

   public void close() throws IOException {
      try {
         if (this.writer != null) {
            this.writer.close();
         }
      } catch (IOException ex) {
         LOGGER.debug("Error closing writer", ex);
      } finally {
         this.writer = null;
      }

      try {
         if (this.socket != null) {
            this.socket.close();
         }
      } catch (IOException ex) {
         LOGGER.debug("Error closing socket", ex);
      } finally {
         this.socket = null;
      }

   }

   protected String sanitize(String s) {
      return GraphiteSanitize.sanitize(s);
   }
}
