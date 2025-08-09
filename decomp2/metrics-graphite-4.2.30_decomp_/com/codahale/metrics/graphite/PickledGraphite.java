package com.codahale.metrics.graphite;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import javax.net.SocketFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PickledGraphite implements GraphiteSender {
   private static final char MARK = '(';
   private static final char STOP = '.';
   private static final char LONG = 'L';
   private static final char STRING = 'S';
   private static final char APPEND = 'a';
   private static final char LIST = 'l';
   private static final char TUPLE = 't';
   private static final char QUOTE = '\'';
   private static final char LF = '\n';
   private static final Logger LOGGER = LoggerFactory.getLogger(PickledGraphite.class);
   private static final int DEFAULT_BATCH_SIZE = 100;
   private int batchSize;
   private List metrics;
   private final String hostname;
   private final int port;
   private final InetSocketAddress address;
   private final SocketFactory socketFactory;
   private final Charset charset;
   private Socket socket;
   private Writer writer;
   private int failures;

   public PickledGraphite(InetSocketAddress address) {
      this((InetSocketAddress)address, 100);
   }

   public PickledGraphite(InetSocketAddress address, int batchSize) {
      this(address, SocketFactory.getDefault(), batchSize);
   }

   public PickledGraphite(InetSocketAddress address, SocketFactory socketFactory, int batchSize) {
      this(address, socketFactory, StandardCharsets.UTF_8, batchSize);
   }

   public PickledGraphite(InetSocketAddress address, SocketFactory socketFactory, Charset charset, int batchSize) {
      this.metrics = new ArrayList();
      this.address = address;
      this.hostname = null;
      this.port = -1;
      this.socketFactory = socketFactory;
      this.charset = charset;
      this.batchSize = batchSize;
   }

   public PickledGraphite(String hostname, int port) {
      this(hostname, port, 100);
   }

   public PickledGraphite(String hostname, int port, int batchSize) {
      this(hostname, port, SocketFactory.getDefault(), batchSize);
   }

   public PickledGraphite(String hostname, int port, SocketFactory socketFactory, int batchSize) {
      this(hostname, port, socketFactory, StandardCharsets.UTF_8, batchSize);
   }

   public PickledGraphite(String hostname, int port, SocketFactory socketFactory, Charset charset, int batchSize) {
      this.metrics = new ArrayList();
      this.address = null;
      this.hostname = hostname;
      this.port = port;
      this.socketFactory = socketFactory;
      this.charset = charset;
      this.batchSize = batchSize;
   }

   public void connect() throws IllegalStateException, IOException {
      if (this.isConnected()) {
         throw new IllegalStateException("Already connected");
      } else {
         InetSocketAddress address = this.address;
         if (address == null) {
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
      this.metrics.add(new MetricTuple(this.sanitize(name), timestamp, this.sanitize(value)));
      if (this.metrics.size() >= this.batchSize) {
         this.writeMetrics();
      }

   }

   public void flush() throws IOException {
      this.writeMetrics();
      if (this.writer != null) {
         this.writer.flush();
      }

   }

   public void close() throws IOException {
      try {
         this.flush();
         if (this.writer != null) {
            this.writer.close();
         }
      } catch (IOException var5) {
         if (this.socket != null) {
            this.socket.close();
         }
      } finally {
         this.socket = null;
         this.writer = null;
      }

   }

   public int getFailures() {
      return this.failures;
   }

   private void writeMetrics() throws IOException {
      if (this.metrics.size() > 0) {
         try {
            byte[] payload = this.pickleMetrics(this.metrics);
            byte[] header = ByteBuffer.allocate(4).putInt(payload.length).array();
            OutputStream outputStream = this.socket.getOutputStream();
            outputStream.write(header);
            outputStream.write(payload);
            outputStream.flush();
            if (LOGGER.isDebugEnabled()) {
               LOGGER.debug("Wrote {} metrics", this.metrics.size());
            }
         } catch (IOException e) {
            ++this.failures;
            throw e;
         } finally {
            this.metrics.clear();
         }
      }

   }

   byte[] pickleMetrics(List metrics) throws IOException {
      ByteArrayOutputStream out = new ByteArrayOutputStream(metrics.size() * 75);
      Writer pickled = new OutputStreamWriter(out, this.charset);
      pickled.append('(');
      pickled.append('l');

      for(MetricTuple tuple : metrics) {
         pickled.append('(');
         pickled.append('S');
         pickled.append('\'');
         pickled.append(tuple.name);
         pickled.append('\'');
         pickled.append('\n');
         pickled.append('(');
         pickled.append('L');
         pickled.append(Long.toString(tuple.timestamp));
         pickled.append('L');
         pickled.append('\n');
         pickled.append('S');
         pickled.append('\'');
         pickled.append(tuple.value);
         pickled.append('\'');
         pickled.append('\n');
         pickled.append('t');
         pickled.append('t');
         pickled.append('a');
      }

      pickled.append('.');
      pickled.flush();
      return out.toByteArray();
   }

   protected String sanitize(String s) {
      return GraphiteSanitize.sanitize(s);
   }

   static class MetricTuple {
      String name;
      long timestamp;
      String value;

      MetricTuple(String name, long timestamp, String value) {
         this.name = name;
         this.timestamp = timestamp;
         this.value = value;
      }
   }
}
