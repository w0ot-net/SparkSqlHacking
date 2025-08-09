package org.apache.avro.ipc;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import javax.security.auth.callback.CallbackHandler;
import javax.security.sasl.Sasl;
import javax.security.sasl.SaslException;
import javax.security.sasl.SaslServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SaslSocketServer extends SocketServer {
   private static final Logger LOG = LoggerFactory.getLogger(SaslServer.class);
   private SaslServerFactory factory;

   public SaslSocketServer(Responder responder, SocketAddress addr) throws IOException {
      this(responder, addr, new SaslServerFactory() {
         public SaslServer getServer() {
            return new AnonymousServer();
         }
      });
   }

   public SaslSocketServer(Responder responder, SocketAddress addr, final String mechanism, final String protocol, final String serverName, final Map props, final CallbackHandler cbh) throws IOException {
      this(responder, addr, new SaslServerFactory() {
         public SaslServer getServer() throws SaslException {
            return Sasl.createSaslServer(mechanism, protocol, serverName, props, cbh);
         }
      });
   }

   private SaslSocketServer(Responder responder, SocketAddress addr, SaslServerFactory factory) throws IOException {
      super(responder, addr);
      this.factory = factory;
   }

   protected Transceiver getTransceiver(SocketChannel channel) throws IOException {
      return new SaslSocketTransceiver(channel, this.factory.getServer());
   }

   private abstract static class SaslServerFactory {
      protected abstract SaslServer getServer() throws SaslException;
   }

   private static class AnonymousServer implements SaslServer {
      private String user;

      public String getMechanismName() {
         return "ANONYMOUS";
      }

      public byte[] evaluateResponse(byte[] response) throws SaslException {
         this.user = new String(response, StandardCharsets.UTF_8);
         return null;
      }

      public boolean isComplete() {
         return this.user != null;
      }

      public String getAuthorizationID() {
         return this.user;
      }

      public byte[] unwrap(byte[] incoming, int offset, int len) {
         throw new UnsupportedOperationException();
      }

      public byte[] wrap(byte[] outgoing, int offset, int len) {
         throw new UnsupportedOperationException();
      }

      public Object getNegotiatedProperty(String propName) {
         return null;
      }

      public void dispose() {
      }
   }
}
