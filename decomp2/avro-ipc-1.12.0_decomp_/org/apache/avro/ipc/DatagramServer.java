package org.apache.avro.ipc;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.DatagramChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatagramServer extends Thread implements Server {
   private static final Logger LOG = LoggerFactory.getLogger(DatagramServer.class);
   private final Responder responder;
   private final DatagramChannel channel;
   private final Transceiver transceiver;

   public DatagramServer(Responder responder, SocketAddress addr) throws IOException {
      String name = "DatagramServer on " + String.valueOf(addr);
      this.responder = responder;
      this.channel = DatagramChannel.open();
      this.channel.socket().bind(addr);
      this.transceiver = new DatagramTransceiver(this.channel);
      this.setName(name);
      this.setDaemon(true);
   }

   public int getPort() {
      return this.channel.socket().getLocalPort();
   }

   public void run() {
      while(true) {
         try {
            this.transceiver.writeBuffers(this.responder.respond(this.transceiver.readBuffers()));
         } catch (ClosedChannelException var2) {
            return;
         } catch (IOException e) {
            LOG.warn("unexpected error", e);
            throw new RuntimeException(e);
         }
      }
   }

   public void close() {
      this.interrupt();
   }

   public static void main(String[] arg) throws Exception {
      DatagramServer server = new DatagramServer((Responder)null, new InetSocketAddress(0));
      server.start();
      System.out.println("started");
      server.join();
   }
}
