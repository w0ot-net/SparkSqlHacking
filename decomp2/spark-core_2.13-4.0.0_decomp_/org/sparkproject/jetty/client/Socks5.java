package org.sparkproject.jetty.client;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.io.EndPoint;
import org.sparkproject.jetty.util.BufferUtil;
import org.sparkproject.jetty.util.Callback;
import org.sparkproject.jetty.util.thread.Invocable;

public class Socks5 {
   public static final byte VERSION = 5;
   public static final byte COMMAND_CONNECT = 1;
   public static final byte RESERVED = 0;
   public static final byte ADDRESS_TYPE_IPV4 = 1;
   public static final byte ADDRESS_TYPE_DOMAIN = 3;
   public static final byte ADDRESS_TYPE_IPV6 = 4;

   private Socks5() {
   }

   public static class NoAuthenticationFactory implements Authentication.Factory {
      public static final byte METHOD = 0;

      public byte getMethod() {
         return 0;
      }

      public Authentication newAuthentication() {
         return (endPoint, callback) -> callback.succeeded();
      }
   }

   public static class UsernamePasswordAuthenticationFactory implements Authentication.Factory {
      public static final byte METHOD = 2;
      public static final byte VERSION = 1;
      private static final Logger LOG = LoggerFactory.getLogger(UsernamePasswordAuthenticationFactory.class);
      private final String userName;
      private final String password;
      private final Charset charset;

      public UsernamePasswordAuthenticationFactory(String userName, String password) {
         this(userName, password, StandardCharsets.US_ASCII);
      }

      public UsernamePasswordAuthenticationFactory(String userName, String password, Charset charset) {
         this.userName = (String)Objects.requireNonNull(userName);
         this.password = (String)Objects.requireNonNull(password);
         this.charset = (Charset)Objects.requireNonNull(charset);
      }

      public byte getMethod() {
         return 2;
      }

      public Authentication newAuthentication() {
         return new UsernamePasswordAuthentication(this);
      }

      private static class UsernamePasswordAuthentication implements Authentication, Callback {
         private final ByteBuffer byteBuffer = BufferUtil.allocate(2);
         private final UsernamePasswordAuthenticationFactory factory;
         private EndPoint endPoint;
         private Callback callback;

         private UsernamePasswordAuthentication(UsernamePasswordAuthenticationFactory factory) {
            this.factory = factory;
         }

         public void authenticate(EndPoint endPoint, Callback callback) {
            this.endPoint = endPoint;
            this.callback = callback;
            byte[] userNameBytes = this.factory.userName.getBytes(this.factory.charset);
            byte[] passwordBytes = this.factory.password.getBytes(this.factory.charset);
            ByteBuffer byteBuffer = ByteBuffer.allocate(3 + userNameBytes.length + passwordBytes.length).put((byte)1).put((byte)userNameBytes.length).put(userNameBytes).put((byte)passwordBytes.length).put(passwordBytes).flip();
            endPoint.write(Callback.from(this::authenticationSent, this::failed), byteBuffer);
         }

         private void authenticationSent() {
            if (Socks5.UsernamePasswordAuthenticationFactory.LOG.isDebugEnabled()) {
               Socks5.UsernamePasswordAuthenticationFactory.LOG.debug("Written SOCKS5 username/password authentication request");
            }

            this.endPoint.fillInterested(this);
         }

         public void succeeded() {
            try {
               int filled = this.endPoint.fill(this.byteBuffer);
               if (filled < 0) {
                  throw new ClosedChannelException();
               }

               if (this.byteBuffer.remaining() < 2) {
                  this.endPoint.fillInterested(this);
                  return;
               }

               if (Socks5.UsernamePasswordAuthenticationFactory.LOG.isDebugEnabled()) {
                  Socks5.UsernamePasswordAuthenticationFactory.LOG.debug("Received SOCKS5 username/password authentication response");
               }

               byte version = this.byteBuffer.get();
               if (version != 1) {
                  throw new IOException("Unsupported username/password authentication version: " + version);
               }

               byte status = this.byteBuffer.get();
               if (status != 0) {
                  throw new IOException("SOCK5 username/password authentication failure");
               }

               if (Socks5.UsernamePasswordAuthenticationFactory.LOG.isDebugEnabled()) {
                  Socks5.UsernamePasswordAuthenticationFactory.LOG.debug("SOCKS5 username/password authentication succeeded");
               }

               this.callback.succeeded();
            } catch (Throwable x) {
               this.failed(x);
            }

         }

         public void failed(Throwable x) {
            this.callback.failed(x);
         }

         public Invocable.InvocationType getInvocationType() {
            return Invocable.InvocationType.NON_BLOCKING;
         }
      }
   }

   public interface Authentication {
      void authenticate(EndPoint var1, Callback var2);

      public interface Factory {
         byte getMethod();

         Authentication newAuthentication();
      }
   }
}
