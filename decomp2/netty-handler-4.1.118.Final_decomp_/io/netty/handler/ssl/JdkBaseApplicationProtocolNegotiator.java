package io.netty.handler.ssl;

import io.netty.util.internal.ObjectUtil;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLHandshakeException;

class JdkBaseApplicationProtocolNegotiator implements JdkApplicationProtocolNegotiator {
   private final List protocols;
   private final JdkApplicationProtocolNegotiator.ProtocolSelectorFactory selectorFactory;
   private final JdkApplicationProtocolNegotiator.ProtocolSelectionListenerFactory listenerFactory;
   private final JdkApplicationProtocolNegotiator.SslEngineWrapperFactory wrapperFactory;
   static final JdkApplicationProtocolNegotiator.ProtocolSelectorFactory FAIL_SELECTOR_FACTORY = new JdkApplicationProtocolNegotiator.ProtocolSelectorFactory() {
      public JdkApplicationProtocolNegotiator.ProtocolSelector newSelector(SSLEngine engine, Set supportedProtocols) {
         return new FailProtocolSelector((JdkSslEngine)engine, supportedProtocols);
      }
   };
   static final JdkApplicationProtocolNegotiator.ProtocolSelectorFactory NO_FAIL_SELECTOR_FACTORY = new JdkApplicationProtocolNegotiator.ProtocolSelectorFactory() {
      public JdkApplicationProtocolNegotiator.ProtocolSelector newSelector(SSLEngine engine, Set supportedProtocols) {
         return new NoFailProtocolSelector((JdkSslEngine)engine, supportedProtocols);
      }
   };
   static final JdkApplicationProtocolNegotiator.ProtocolSelectionListenerFactory FAIL_SELECTION_LISTENER_FACTORY = new JdkApplicationProtocolNegotiator.ProtocolSelectionListenerFactory() {
      public JdkApplicationProtocolNegotiator.ProtocolSelectionListener newListener(SSLEngine engine, List supportedProtocols) {
         return new FailProtocolSelectionListener((JdkSslEngine)engine, supportedProtocols);
      }
   };
   static final JdkApplicationProtocolNegotiator.ProtocolSelectionListenerFactory NO_FAIL_SELECTION_LISTENER_FACTORY = new JdkApplicationProtocolNegotiator.ProtocolSelectionListenerFactory() {
      public JdkApplicationProtocolNegotiator.ProtocolSelectionListener newListener(SSLEngine engine, List supportedProtocols) {
         return new NoFailProtocolSelectionListener((JdkSslEngine)engine, supportedProtocols);
      }
   };

   JdkBaseApplicationProtocolNegotiator(JdkApplicationProtocolNegotiator.SslEngineWrapperFactory wrapperFactory, JdkApplicationProtocolNegotiator.ProtocolSelectorFactory selectorFactory, JdkApplicationProtocolNegotiator.ProtocolSelectionListenerFactory listenerFactory, Iterable protocols) {
      this(wrapperFactory, selectorFactory, listenerFactory, ApplicationProtocolUtil.toList(protocols));
   }

   JdkBaseApplicationProtocolNegotiator(JdkApplicationProtocolNegotiator.SslEngineWrapperFactory wrapperFactory, JdkApplicationProtocolNegotiator.ProtocolSelectorFactory selectorFactory, JdkApplicationProtocolNegotiator.ProtocolSelectionListenerFactory listenerFactory, String... protocols) {
      this(wrapperFactory, selectorFactory, listenerFactory, ApplicationProtocolUtil.toList(protocols));
   }

   private JdkBaseApplicationProtocolNegotiator(JdkApplicationProtocolNegotiator.SslEngineWrapperFactory wrapperFactory, JdkApplicationProtocolNegotiator.ProtocolSelectorFactory selectorFactory, JdkApplicationProtocolNegotiator.ProtocolSelectionListenerFactory listenerFactory, List protocols) {
      this.wrapperFactory = (JdkApplicationProtocolNegotiator.SslEngineWrapperFactory)ObjectUtil.checkNotNull(wrapperFactory, "wrapperFactory");
      this.selectorFactory = (JdkApplicationProtocolNegotiator.ProtocolSelectorFactory)ObjectUtil.checkNotNull(selectorFactory, "selectorFactory");
      this.listenerFactory = (JdkApplicationProtocolNegotiator.ProtocolSelectionListenerFactory)ObjectUtil.checkNotNull(listenerFactory, "listenerFactory");
      this.protocols = Collections.unmodifiableList((List)ObjectUtil.checkNotNull(protocols, "protocols"));
   }

   public List protocols() {
      return this.protocols;
   }

   public JdkApplicationProtocolNegotiator.ProtocolSelectorFactory protocolSelectorFactory() {
      return this.selectorFactory;
   }

   public JdkApplicationProtocolNegotiator.ProtocolSelectionListenerFactory protocolListenerFactory() {
      return this.listenerFactory;
   }

   public JdkApplicationProtocolNegotiator.SslEngineWrapperFactory wrapperFactory() {
      return this.wrapperFactory;
   }

   static class NoFailProtocolSelector implements JdkApplicationProtocolNegotiator.ProtocolSelector {
      private final JdkSslEngine engineWrapper;
      private final Set supportedProtocols;

      NoFailProtocolSelector(JdkSslEngine engineWrapper, Set supportedProtocols) {
         this.engineWrapper = engineWrapper;
         this.supportedProtocols = supportedProtocols;
      }

      public void unsupported() {
         this.engineWrapper.setNegotiatedApplicationProtocol((String)null);
      }

      public String select(List protocols) throws Exception {
         for(String p : this.supportedProtocols) {
            if (protocols.contains(p)) {
               this.engineWrapper.setNegotiatedApplicationProtocol(p);
               return p;
            }
         }

         return this.noSelectMatchFound();
      }

      public String noSelectMatchFound() throws Exception {
         this.engineWrapper.setNegotiatedApplicationProtocol((String)null);
         return null;
      }
   }

   private static final class FailProtocolSelector extends NoFailProtocolSelector {
      FailProtocolSelector(JdkSslEngine engineWrapper, Set supportedProtocols) {
         super(engineWrapper, supportedProtocols);
      }

      public String noSelectMatchFound() throws Exception {
         throw new SSLHandshakeException("Selected protocol is not supported");
      }
   }

   private static class NoFailProtocolSelectionListener implements JdkApplicationProtocolNegotiator.ProtocolSelectionListener {
      private final JdkSslEngine engineWrapper;
      private final List supportedProtocols;

      NoFailProtocolSelectionListener(JdkSslEngine engineWrapper, List supportedProtocols) {
         this.engineWrapper = engineWrapper;
         this.supportedProtocols = supportedProtocols;
      }

      public void unsupported() {
         this.engineWrapper.setNegotiatedApplicationProtocol((String)null);
      }

      public void selected(String protocol) throws Exception {
         if (this.supportedProtocols.contains(protocol)) {
            this.engineWrapper.setNegotiatedApplicationProtocol(protocol);
         } else {
            this.noSelectedMatchFound(protocol);
         }

      }

      protected void noSelectedMatchFound(String protocol) throws Exception {
      }
   }

   private static final class FailProtocolSelectionListener extends NoFailProtocolSelectionListener {
      FailProtocolSelectionListener(JdkSslEngine engineWrapper, List supportedProtocols) {
         super(engineWrapper, supportedProtocols);
      }

      protected void noSelectedMatchFound(String protocol) throws Exception {
         throw new SSLHandshakeException("No compatible protocols found");
      }
   }
}
