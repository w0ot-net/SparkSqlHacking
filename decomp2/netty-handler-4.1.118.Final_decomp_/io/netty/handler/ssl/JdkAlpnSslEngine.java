package io.netty.handler.ssl;

import io.netty.util.internal.SuppressJava6Requirement;
import java.nio.ByteBuffer;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLEngineResult;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLEngineResult.HandshakeStatus;

@SuppressJava6Requirement(
   reason = "Usage guarded by java version check"
)
class JdkAlpnSslEngine extends JdkSslEngine {
   private final JdkApplicationProtocolNegotiator.ProtocolSelectionListener selectionListener;
   private final AlpnSelector alpnSelector;

   JdkAlpnSslEngine(SSLEngine engine, JdkApplicationProtocolNegotiator applicationNegotiator, boolean isServer, BiConsumer setHandshakeApplicationProtocolSelector, BiConsumer setApplicationProtocols) {
      super(engine);
      if (isServer) {
         this.selectionListener = null;
         this.alpnSelector = new AlpnSelector(applicationNegotiator.protocolSelectorFactory().newSelector(this, new LinkedHashSet(applicationNegotiator.protocols())));
         setHandshakeApplicationProtocolSelector.accept(engine, this.alpnSelector);
      } else {
         this.selectionListener = applicationNegotiator.protocolListenerFactory().newListener(this, applicationNegotiator.protocols());
         this.alpnSelector = null;
         setApplicationProtocols.accept(engine, applicationNegotiator.protocols());
      }

   }

   JdkAlpnSslEngine(SSLEngine engine, JdkApplicationProtocolNegotiator applicationNegotiator, boolean isServer) {
      this(engine, applicationNegotiator, isServer, new BiConsumer() {
         public void accept(SSLEngine e, AlpnSelector s) {
            JdkAlpnSslUtils.setHandshakeApplicationProtocolSelector(e, s);
         }
      }, new BiConsumer() {
         public void accept(SSLEngine e, List p) {
            JdkAlpnSslUtils.setApplicationProtocols(e, p);
         }
      });
   }

   private SSLEngineResult verifyProtocolSelection(SSLEngineResult result) throws SSLException {
      if (result.getHandshakeStatus() == HandshakeStatus.FINISHED) {
         if (this.alpnSelector == null) {
            try {
               String protocol = this.getApplicationProtocol();

               assert protocol != null;

               if (protocol.isEmpty()) {
                  this.selectionListener.unsupported();
               } else {
                  this.selectionListener.selected(protocol);
               }
            } catch (Throwable e) {
               throw SslUtils.toSSLHandshakeException(e);
            }
         } else {
            assert this.selectionListener == null;

            this.alpnSelector.checkUnsupported();
         }
      }

      return result;
   }

   public SSLEngineResult wrap(ByteBuffer src, ByteBuffer dst) throws SSLException {
      return this.verifyProtocolSelection(super.wrap(src, dst));
   }

   public SSLEngineResult wrap(ByteBuffer[] srcs, ByteBuffer dst) throws SSLException {
      return this.verifyProtocolSelection(super.wrap(srcs, dst));
   }

   public SSLEngineResult wrap(ByteBuffer[] srcs, int offset, int len, ByteBuffer dst) throws SSLException {
      return this.verifyProtocolSelection(super.wrap(srcs, offset, len, dst));
   }

   public SSLEngineResult unwrap(ByteBuffer src, ByteBuffer dst) throws SSLException {
      return this.verifyProtocolSelection(super.unwrap(src, dst));
   }

   public SSLEngineResult unwrap(ByteBuffer src, ByteBuffer[] dsts) throws SSLException {
      return this.verifyProtocolSelection(super.unwrap(src, dsts));
   }

   public SSLEngineResult unwrap(ByteBuffer src, ByteBuffer[] dst, int offset, int len) throws SSLException {
      return this.verifyProtocolSelection(super.unwrap(src, dst, offset, len));
   }

   void setNegotiatedApplicationProtocol(String applicationProtocol) {
   }

   public String getNegotiatedApplicationProtocol() {
      String protocol = this.getApplicationProtocol();
      if (protocol != null) {
         return protocol.isEmpty() ? null : protocol;
      } else {
         return null;
      }
   }

   public String getApplicationProtocol() {
      return JdkAlpnSslUtils.getApplicationProtocol(this.getWrappedEngine());
   }

   public String getHandshakeApplicationProtocol() {
      return JdkAlpnSslUtils.getHandshakeApplicationProtocol(this.getWrappedEngine());
   }

   public void setHandshakeApplicationProtocolSelector(BiFunction selector) {
      JdkAlpnSslUtils.setHandshakeApplicationProtocolSelector(this.getWrappedEngine(), selector);
   }

   public BiFunction getHandshakeApplicationProtocolSelector() {
      return JdkAlpnSslUtils.getHandshakeApplicationProtocolSelector(this.getWrappedEngine());
   }

   final class AlpnSelector implements BiFunction {
      private final JdkApplicationProtocolNegotiator.ProtocolSelector selector;
      private boolean called;

      AlpnSelector(JdkApplicationProtocolNegotiator.ProtocolSelector selector) {
         this.selector = selector;
      }

      public String apply(SSLEngine sslEngine, List strings) {
         assert !this.called;

         this.called = true;

         try {
            String selected = this.selector.select(strings);
            return selected == null ? "" : selected;
         } catch (Exception var4) {
            return null;
         }
      }

      void checkUnsupported() {
         if (!this.called) {
            String protocol = JdkAlpnSslEngine.this.getApplicationProtocol();

            assert protocol != null;

            if (protocol.isEmpty()) {
               this.selector.unsupported();
            }

         }
      }
   }
}
