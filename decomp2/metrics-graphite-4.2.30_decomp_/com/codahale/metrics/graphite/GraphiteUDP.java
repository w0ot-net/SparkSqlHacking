package com.codahale.metrics.graphite;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.charset.StandardCharsets;

public class GraphiteUDP implements GraphiteSender {
   private final String hostname;
   private final int port;
   private InetSocketAddress address;
   private DatagramChannel datagramChannel = null;
   private int failures;

   public GraphiteUDP(String hostname, int port) {
      this.hostname = hostname;
      this.port = port;
      this.address = null;
   }

   public GraphiteUDP(InetSocketAddress address) {
      this.hostname = null;
      this.port = -1;
      this.address = address;
   }

   public void connect() throws IllegalStateException, IOException {
      if (this.isConnected()) {
         throw new IllegalStateException("Already connected");
      } else {
         if (this.hostname != null) {
            this.address = new InetSocketAddress(InetAddress.getByName(this.hostname), this.port);
         }

         this.datagramChannel = DatagramChannel.open();
      }
   }

   public boolean isConnected() {
      return this.datagramChannel != null && !this.datagramChannel.socket().isClosed();
   }

   public void send(String name, String value, long timestamp) throws IOException {
      try {
         String str = this.sanitize(name) + ' ' + this.sanitize(value) + ' ' + Long.toString(timestamp) + '\n';
         ByteBuffer byteBuffer = ByteBuffer.wrap(str.getBytes(StandardCharsets.UTF_8));
         this.datagramChannel.send(byteBuffer, this.address);
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
   }

   public void close() throws IOException {
      if (this.datagramChannel != null) {
         try {
            this.datagramChannel.close();
         } finally {
            this.datagramChannel = null;
         }
      }

   }

   protected String sanitize(String s) {
      return GraphiteSanitize.sanitize(s);
   }

   DatagramChannel getDatagramChannel() {
      return this.datagramChannel;
   }

   void setDatagramChannel(DatagramChannel datagramChannel) {
      this.datagramChannel = datagramChannel;
   }

   InetSocketAddress getAddress() {
      return this.address;
   }

   void setAddress(InetSocketAddress address) {
      this.address = address;
   }
}
