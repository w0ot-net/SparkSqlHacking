package org.apache.thrift.transport.sasl;

import java.util.Map;
import javax.security.auth.callback.CallbackHandler;

public class TSaslServerDefinition {
   public final String mechanism;
   public final String protocol;
   public final String serverName;
   public final Map props;
   public final CallbackHandler cbh;

   public TSaslServerDefinition(String mechanism, String protocol, String serverName, Map props, CallbackHandler cbh) {
      this.mechanism = mechanism;
      this.protocol = protocol;
      this.serverName = serverName;
      this.props = props;
      this.cbh = cbh;
   }
}
