package org.apache.thrift.transport.sasl;

import javax.security.sasl.SaslException;
import javax.security.sasl.SaslServer;
import org.apache.thrift.transport.TTransportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerSaslPeer implements SaslPeer {
   private static final Logger LOGGER = LoggerFactory.getLogger(ServerSaslPeer.class);
   private static final String QOP_AUTH_INT = "auth-int";
   private static final String QOP_AUTH_CONF = "auth-conf";
   private final SaslServer saslServer;

   public ServerSaslPeer(SaslServer saslServer) {
      this.saslServer = saslServer;
   }

   public byte[] evaluate(byte[] negotiationMessage) throws TSaslNegotiationException {
      try {
         return this.saslServer.evaluateResponse(negotiationMessage);
      } catch (SaslException e) {
         throw new TSaslNegotiationException(TSaslNegotiationException.ErrorType.AUTHENTICATION_FAILURE, "Authentication failed with " + this.saslServer.getMechanismName(), e);
      }
   }

   public boolean isAuthenticated() {
      return this.saslServer.isComplete();
   }

   public boolean isDataProtected() {
      Object qop = this.saslServer.getNegotiatedProperty("javax.security.sasl.qop");
      if (qop == null) {
         return false;
      } else {
         for(String word : qop.toString().split("\\s*,\\s*")) {
            String lowerCaseWord = word.toLowerCase();
            if ("auth-int".equals(lowerCaseWord) || "auth-conf".equals(lowerCaseWord)) {
               return true;
            }
         }

         return false;
      }
   }

   public byte[] wrap(byte[] data, int offset, int length) throws TTransportException {
      try {
         return this.saslServer.wrap(data, offset, length);
      } catch (SaslException e) {
         throw new TTransportException("Failed to wrap data", e);
      }
   }

   public byte[] unwrap(byte[] data, int offset, int length) throws TTransportException {
      try {
         return this.saslServer.unwrap(data, offset, length);
      } catch (SaslException e) {
         throw new TTransportException(5, "Failed to unwrap data", e);
      }
   }

   public void dispose() {
      try {
         this.saslServer.dispose();
      } catch (Exception e) {
         LOGGER.warn("Failed to close sasl server " + this.saslServer.getMechanismName(), e);
      }

   }

   SaslServer getSaslServer() {
      return this.saslServer;
   }
}
