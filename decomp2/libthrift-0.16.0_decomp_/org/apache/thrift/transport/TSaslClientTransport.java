package org.apache.thrift.transport;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import javax.security.auth.callback.CallbackHandler;
import javax.security.sasl.Sasl;
import javax.security.sasl.SaslClient;
import javax.security.sasl.SaslException;
import org.apache.thrift.transport.sasl.NegotiationStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TSaslClientTransport extends TSaslTransport {
   private static final Logger LOGGER = LoggerFactory.getLogger(TSaslClientTransport.class);
   private final String mechanism;

   public TSaslClientTransport(SaslClient saslClient, TTransport transport) throws TTransportException {
      super(saslClient, transport);
      this.mechanism = saslClient.getMechanismName();
   }

   public TSaslClientTransport(String mechanism, String authorizationId, String protocol, String serverName, Map props, CallbackHandler cbh, TTransport transport) throws SaslException, TTransportException {
      super(Sasl.createSaslClient(new String[]{mechanism}, authorizationId, protocol, serverName, props, cbh), transport);
      this.mechanism = mechanism;
   }

   protected TSaslTransport.SaslRole getRole() {
      return TSaslTransport.SaslRole.CLIENT;
   }

   protected void handleSaslStartMessage() throws TTransportException, SaslException {
      SaslClient saslClient = this.getSaslClient();
      byte[] initialResponse = new byte[0];
      if (saslClient.hasInitialResponse()) {
         initialResponse = saslClient.evaluateChallenge(initialResponse);
      }

      LOGGER.debug("Sending mechanism name {} and initial response of length {}", this.mechanism, initialResponse.length);
      byte[] mechanismBytes = this.mechanism.getBytes(StandardCharsets.UTF_8);
      this.sendSaslMessage(NegotiationStatus.START, mechanismBytes);
      this.sendSaslMessage(saslClient.isComplete() ? NegotiationStatus.COMPLETE : NegotiationStatus.OK, initialResponse);
      this.underlyingTransport.flush();
   }
}
