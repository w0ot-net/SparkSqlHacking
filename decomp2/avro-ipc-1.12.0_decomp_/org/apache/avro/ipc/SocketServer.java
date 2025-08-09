package org.apache.avro.ipc;

import java.io.EOFException;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import org.apache.avro.AvroRemoteException;
import org.apache.avro.Protocol;
import org.apache.avro.ipc.generic.GenericResponder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** @deprecated */
@Deprecated
public class SocketServer extends Thread implements Server {
   private static final Logger LOG = LoggerFactory.getLogger(SocketServer.class);
   private Responder responder;
   private ServerSocketChannel channel;
   private ThreadGroup group;

   public SocketServer(Responder responder, SocketAddress addr) throws IOException {
      String name = "SocketServer on " + String.valueOf(addr);
      this.responder = responder;
      this.group = new ThreadGroup(name);
      this.channel = ServerSocketChannel.open();
      this.channel.socket().bind(addr);
      this.setName(name);
      this.setDaemon(true);
   }

   public int getPort() {
      return this.channel.socket().getLocalPort();
   }

   public void run() {
      LOG.info("starting " + String.valueOf(this.channel.socket().getInetAddress()));

      try {
         while(true) {
            try {
               new Connection(this.channel.accept());
            } catch (ClosedChannelException var11) {
               return;
            } catch (IOException e) {
               LOG.warn("unexpected error", e);
               throw new RuntimeException(e);
            }
         }
      } finally {
         LOG.info("stopping " + String.valueOf(this.channel.socket().getInetAddress()));

         try {
            this.channel.close();
         } catch (IOException var10) {
         }

      }
   }

   public void close() {
      this.interrupt();
      this.group.interrupt();
   }

   protected Transceiver getTransceiver(SocketChannel channel) throws IOException {
      return new SocketTransceiver(channel);
   }

   public static void main(String[] arg) throws Exception {
      Responder responder = new GenericResponder(Protocol.parse("{\"protocol\": \"X\"}")) {
         public Object respond(Protocol.Message message, Object request) throws Exception {
            throw new AvroRemoteException("no messages!");
         }
      };
      SocketServer server = new SocketServer(responder, new InetSocketAddress(0));
      server.start();
      System.out.println("server started on port: " + server.getPort());
      server.join();
   }

   private class Connection implements Runnable {
      SocketChannel channel;
      Transceiver xc;

      public Connection(SocketChannel channel) throws IOException {
         this.channel = channel;
         Thread thread = new Thread(SocketServer.this.group, this);
         thread.setName("Connection to " + String.valueOf(channel.socket().getRemoteSocketAddress()));
         thread.setDaemon(true);
         thread.start();
      }

      public void run() {
         try {
            try {
               this.xc = SocketServer.this.getTransceiver(this.channel);

               while(true) {
                  this.xc.writeBuffers(SocketServer.this.responder.respond(this.xc.readBuffers(), this.xc));
               }
            } catch (ClosedChannelException | EOFException var6) {
            } finally {
               this.xc.close();
            }
         } catch (IOException e) {
            SocketServer.LOG.warn("unexpected error", e);
         }

      }
   }
}
