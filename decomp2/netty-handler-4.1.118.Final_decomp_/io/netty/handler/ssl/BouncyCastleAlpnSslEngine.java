package io.netty.handler.ssl;

import io.netty.util.internal.SuppressJava6Requirement;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import javax.net.ssl.SSLEngine;

@SuppressJava6Requirement(
   reason = "Usage guarded by java version check"
)
final class BouncyCastleAlpnSslEngine extends JdkAlpnSslEngine {
   BouncyCastleAlpnSslEngine(SSLEngine engine, JdkApplicationProtocolNegotiator applicationNegotiator, boolean isServer) {
      super(engine, applicationNegotiator, isServer, new BiConsumer() {
         public void accept(SSLEngine e, JdkAlpnSslEngine.AlpnSelector s) {
            BouncyCastleAlpnSslUtils.setHandshakeApplicationProtocolSelector(e, s);
         }
      }, new BiConsumer() {
         public void accept(SSLEngine e, List p) {
            BouncyCastleAlpnSslUtils.setApplicationProtocols(e, p);
         }
      });
   }

   public String getApplicationProtocol() {
      return BouncyCastleAlpnSslUtils.getApplicationProtocol(this.getWrappedEngine());
   }

   public String getHandshakeApplicationProtocol() {
      return BouncyCastleAlpnSslUtils.getHandshakeApplicationProtocol(this.getWrappedEngine());
   }

   public void setHandshakeApplicationProtocolSelector(BiFunction selector) {
      BouncyCastleAlpnSslUtils.setHandshakeApplicationProtocolSelector(this.getWrappedEngine(), selector);
   }

   public BiFunction getHandshakeApplicationProtocolSelector() {
      return BouncyCastleAlpnSslUtils.getHandshakeApplicationProtocolSelector(this.getWrappedEngine());
   }
}
