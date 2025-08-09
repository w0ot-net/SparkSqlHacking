package org.apache.avro.ipc;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import org.slf4j.LoggerFactory;

public class Ipc {
   static boolean warned = false;

   private Ipc() {
   }

   public static Transceiver createTransceiver(URI uri) throws IOException {
      if ("http".equals(uri.getScheme())) {
         return new HttpTransceiver(uri.toURL());
      } else if ("avro".equals(uri.getScheme())) {
         return new SaslSocketTransceiver(new InetSocketAddress(uri.getHost(), uri.getPort()));
      } else {
         throw new IOException("unknown uri scheme: " + String.valueOf(uri));
      }
   }

   public static Server createServer(Responder responder, URI uri) throws IOException {
      if ("avro".equals(uri.getScheme())) {
         return new SaslSocketServer(responder, new InetSocketAddress(uri.getHost(), uri.getPort()));
      } else {
         if ("http".equals(uri.getScheme())) {
            if (!warned) {
               LoggerFactory.getLogger(Ipc.class).error("Using Ipc.createServer to create http instances is deprecated.  Create  an instance of org.apache.avro.ipc.jetty.HttpServer directly.");
               warned = true;
            }

            try {
               Class<?> cls = Class.forName("org.apache.avro.ipc.jetty.HttpServer");
               return (Server)cls.getConstructor(Responder.class, Integer.TYPE).newInstance(responder, uri.getPort());
            } catch (Throwable var3) {
            }
         }

         throw new IOException("unknown uri scheme: " + String.valueOf(uri));
      }
   }
}
