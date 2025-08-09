package org.apache.zookeeper.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.nio.charset.StandardCharsets;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import org.apache.yetus.audience.InterfaceAudience.Public;
import org.apache.zookeeper.common.ClientX509Util;
import org.apache.zookeeper.common.X509Exception;
import org.apache.zookeeper.common.X509Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Public
public class FourLetterWordMain {
   private static final int DEFAULT_SOCKET_TIMEOUT = 5000;
   protected static final Logger LOG = LoggerFactory.getLogger(FourLetterWordMain.class);

   public static String send4LetterWord(String host, int port, String cmd) throws IOException, X509Exception.SSLContextException {
      return send4LetterWord(host, port, cmd, false, 5000);
   }

   public static String send4LetterWord(String host, int port, String cmd, boolean secure) throws IOException, X509Exception.SSLContextException {
      return send4LetterWord(host, port, cmd, secure, 5000);
   }

   public static String send4LetterWord(String host, int port, String cmd, boolean secure, int timeout) throws IOException, X509Exception.SSLContextException {
      LOG.info("connecting to {} {}", host, port);
      Socket sock = null;
      BufferedReader reader = null;

      String var24;
      try {
         InetSocketAddress hostaddress = host != null ? new InetSocketAddress(host, port) : new InetSocketAddress(InetAddress.getByName((String)null), port);
         if (secure) {
            LOG.info("using secure socket");
            X509Util x509Util = new ClientX509Util();

            try {
               SSLContext sslContext = x509Util.getDefaultSSLContext();
               SSLSocketFactory socketFactory = sslContext.getSocketFactory();
               SSLSocket sslSock = (SSLSocket)socketFactory.createSocket();
               sslSock.connect(hostaddress, timeout);
               sslSock.startHandshake();
               sock = sslSock;
            } catch (Throwable var18) {
               try {
                  x509Util.close();
               } catch (Throwable var17) {
                  var18.addSuppressed(var17);
               }

               throw var18;
            }

            x509Util.close();
         } else {
            sock = new Socket();
            sock.connect(hostaddress, timeout);
         }

         sock.setSoTimeout(timeout);
         OutputStream outstream = sock.getOutputStream();
         outstream.write(cmd.getBytes(StandardCharsets.UTF_8));
         outstream.flush();
         if (!secure) {
            sock.shutdownOutput();
         }

         reader = new BufferedReader(new InputStreamReader(sock.getInputStream()));
         StringBuilder sb = new StringBuilder();

         String line;
         while((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
         }

         var24 = sb.toString();
      } catch (SocketTimeoutException e) {
         throw new IOException("Exception while executing four letter word: " + cmd, e);
      } finally {
         if (sock != null) {
            sock.close();
         }

         if (reader != null) {
            reader.close();
         }

      }

      return var24;
   }

   public static void main(String[] args) throws IOException, X509Exception.SSLContextException {
      if (args.length == 3) {
         System.out.println(send4LetterWord(args[0], Integer.parseInt(args[1]), args[2]));
      } else if (args.length == 4) {
         System.out.println(send4LetterWord(args[0], Integer.parseInt(args[1]), args[2], Boolean.parseBoolean(args[3])));
      } else {
         System.out.println("Usage: FourLetterWordMain <host> <port> <cmd> <secure(optional)>");
      }

   }
}
