package io.netty.handler.ssl;

import io.netty.buffer.ByteBufAllocator;
import java.util.List;
import java.util.Set;
import javax.net.ssl.SSLEngine;

/** @deprecated */
@Deprecated
public interface JdkApplicationProtocolNegotiator extends ApplicationProtocolNegotiator {
   SslEngineWrapperFactory wrapperFactory();

   ProtocolSelectorFactory protocolSelectorFactory();

   ProtocolSelectionListenerFactory protocolListenerFactory();

   public abstract static class AllocatorAwareSslEngineWrapperFactory implements SslEngineWrapperFactory {
      public final SSLEngine wrapSslEngine(SSLEngine engine, JdkApplicationProtocolNegotiator applicationNegotiator, boolean isServer) {
         return this.wrapSslEngine(engine, ByteBufAllocator.DEFAULT, applicationNegotiator, isServer);
      }

      abstract SSLEngine wrapSslEngine(SSLEngine var1, ByteBufAllocator var2, JdkApplicationProtocolNegotiator var3, boolean var4);
   }

   public interface ProtocolSelectionListener {
      void unsupported();

      void selected(String var1) throws Exception;
   }

   public interface ProtocolSelectionListenerFactory {
      ProtocolSelectionListener newListener(SSLEngine var1, List var2);
   }

   public interface ProtocolSelector {
      void unsupported();

      String select(List var1) throws Exception;
   }

   public interface ProtocolSelectorFactory {
      ProtocolSelector newSelector(SSLEngine var1, Set var2);
   }

   public interface SslEngineWrapperFactory {
      SSLEngine wrapSslEngine(SSLEngine var1, JdkApplicationProtocolNegotiator var2, boolean var3);
   }
}
