package org.apache.thrift.transport.sasl;

import java.util.HashMap;
import java.util.Map;
import javax.security.auth.callback.CallbackHandler;
import javax.security.sasl.Sasl;
import javax.security.sasl.SaslException;
import javax.security.sasl.SaslServer;

public class TSaslServerFactory {
   private final Map saslMechanisms = new HashMap();

   public void addSaslMechanism(String mechanism, String protocol, String serverName, Map props, CallbackHandler cbh) {
      TSaslServerDefinition definition = new TSaslServerDefinition(mechanism, protocol, serverName, props, cbh);
      this.saslMechanisms.put(definition.mechanism, definition);
   }

   public ServerSaslPeer getSaslPeer(String mechanism) throws TSaslNegotiationException {
      if (!this.saslMechanisms.containsKey(mechanism)) {
         throw new TSaslNegotiationException(TSaslNegotiationException.ErrorType.MECHANISME_MISMATCH, "Unsupported mechanism " + mechanism);
      } else {
         TSaslServerDefinition saslDef = (TSaslServerDefinition)this.saslMechanisms.get(mechanism);

         try {
            SaslServer saslServer = Sasl.createSaslServer(saslDef.mechanism, saslDef.protocol, saslDef.serverName, saslDef.props, saslDef.cbh);
            return new ServerSaslPeer(saslServer);
         } catch (SaslException e) {
            throw new TSaslNegotiationException(TSaslNegotiationException.ErrorType.PROTOCOL_ERROR, "Fail to create sasl server " + mechanism, e);
         }
      }
   }
}
