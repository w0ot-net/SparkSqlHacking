package org.apache.thrift.transport;

import java.lang.ref.WeakReference;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;
import javax.security.auth.callback.CallbackHandler;
import javax.security.sasl.Sasl;
import javax.security.sasl.SaslException;
import javax.security.sasl.SaslServer;
import org.apache.thrift.transport.sasl.NegotiationStatus;
import org.apache.thrift.transport.sasl.TSaslServerDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TSaslServerTransport extends TSaslTransport {
   private static final Logger LOGGER = LoggerFactory.getLogger(TSaslServerTransport.class);
   private Map serverDefinitionMap;

   public TSaslServerTransport(TTransport transport) throws TTransportException {
      super(transport);
      this.serverDefinitionMap = new HashMap();
   }

   public TSaslServerTransport(String mechanism, String protocol, String serverName, Map props, CallbackHandler cbh, TTransport transport) throws TTransportException {
      super(transport);
      this.serverDefinitionMap = new HashMap();
      this.addServerDefinition(mechanism, protocol, serverName, props, cbh);
   }

   private TSaslServerTransport(Map serverDefinitionMap, TTransport transport) throws TTransportException {
      super(transport);
      this.serverDefinitionMap = new HashMap();
      this.serverDefinitionMap.putAll(serverDefinitionMap);
   }

   public void addServerDefinition(String mechanism, String protocol, String serverName, Map props, CallbackHandler cbh) {
      this.serverDefinitionMap.put(mechanism, new TSaslServerDefinition(mechanism, protocol, serverName, props, cbh));
   }

   protected TSaslTransport.SaslRole getRole() {
      return TSaslTransport.SaslRole.SERVER;
   }

   protected void handleSaslStartMessage() throws TTransportException, SaslException {
      TSaslTransport.SaslResponse message = this.receiveSaslMessage();
      LOGGER.debug("Received start message with status {}", message.status);
      if (message.status != NegotiationStatus.START) {
         throw this.sendAndThrowMessage(NegotiationStatus.ERROR, "Expecting START status, received " + message.status);
      } else {
         String mechanismName = new String(message.payload, StandardCharsets.UTF_8);
         TSaslServerDefinition serverDefinition = (TSaslServerDefinition)this.serverDefinitionMap.get(mechanismName);
         LOGGER.debug("Received mechanism name '{}'", mechanismName);
         if (serverDefinition == null) {
            throw this.sendAndThrowMessage(NegotiationStatus.BAD, "Unsupported mechanism type " + mechanismName);
         } else {
            SaslServer saslServer = Sasl.createSaslServer(serverDefinition.mechanism, serverDefinition.protocol, serverDefinition.serverName, serverDefinition.props, serverDefinition.cbh);
            this.setSaslServer(saslServer);
         }
      }
   }

   public static class Factory extends TTransportFactory {
      private static Map transportMap = Collections.synchronizedMap(new WeakHashMap());
      private Map serverDefinitionMap = new HashMap();

      public Factory() {
      }

      public Factory(String mechanism, String protocol, String serverName, Map props, CallbackHandler cbh) {
         this.addServerDefinition(mechanism, protocol, serverName, props, cbh);
      }

      public void addServerDefinition(String mechanism, String protocol, String serverName, Map props, CallbackHandler cbh) {
         this.serverDefinitionMap.put(mechanism, new TSaslServerDefinition(mechanism, protocol, serverName, props, cbh));
      }

      public TTransport getTransport(TTransport base) throws TTransportException {
         WeakReference<TSaslServerTransport> ret = (WeakReference)transportMap.get(base);
         if (ret != null && ret.get() != null) {
            TSaslServerTransport.LOGGER.debug("transport map does contain key {}", base);
         } else {
            TSaslServerTransport.LOGGER.debug("transport map does not contain key", base);
            ret = new WeakReference(new TSaslServerTransport(this.serverDefinitionMap, base));

            try {
               ((TSaslServerTransport)ret.get()).open();
            } catch (TTransportException e) {
               TSaslServerTransport.LOGGER.debug("failed to open server transport", e);
               throw new RuntimeException(e);
            }

            transportMap.put(base, ret);
         }

         return (TTransport)ret.get();
      }
   }
}
