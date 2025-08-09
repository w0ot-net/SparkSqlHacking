package io.netty.handler.ssl;

import io.netty.util.internal.EmptyArrays;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.SuppressJava6Requirement;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;
import java.security.SecureRandom;
import java.util.List;
import java.util.function.BiFunction;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.TrustManager;

@SuppressJava6Requirement(
   reason = "Usage guarded by java version check"
)
final class JdkAlpnSslUtils {
   private static final InternalLogger logger = InternalLoggerFactory.getInstance(JdkAlpnSslUtils.class);
   private static final Method SET_APPLICATION_PROTOCOLS;
   private static final Method GET_APPLICATION_PROTOCOL;
   private static final Method GET_HANDSHAKE_APPLICATION_PROTOCOL;
   private static final Method SET_HANDSHAKE_APPLICATION_PROTOCOL_SELECTOR;
   private static final Method GET_HANDSHAKE_APPLICATION_PROTOCOL_SELECTOR;

   private JdkAlpnSslUtils() {
   }

   static boolean supportsAlpn() {
      return GET_APPLICATION_PROTOCOL != null;
   }

   static String getApplicationProtocol(SSLEngine sslEngine) {
      try {
         return (String)GET_APPLICATION_PROTOCOL.invoke(sslEngine);
      } catch (UnsupportedOperationException ex) {
         throw ex;
      } catch (Exception ex) {
         throw new IllegalStateException(ex);
      }
   }

   static String getHandshakeApplicationProtocol(SSLEngine sslEngine) {
      try {
         return (String)GET_HANDSHAKE_APPLICATION_PROTOCOL.invoke(sslEngine);
      } catch (UnsupportedOperationException ex) {
         throw ex;
      } catch (Exception ex) {
         throw new IllegalStateException(ex);
      }
   }

   static void setApplicationProtocols(SSLEngine engine, List supportedProtocols) {
      SSLParameters parameters = engine.getSSLParameters();
      String[] protocolArray = (String[])supportedProtocols.toArray(EmptyArrays.EMPTY_STRINGS);

      try {
         SET_APPLICATION_PROTOCOLS.invoke(parameters, protocolArray);
      } catch (UnsupportedOperationException ex) {
         throw ex;
      } catch (Exception ex) {
         throw new IllegalStateException(ex);
      }

      engine.setSSLParameters(parameters);
   }

   static void setHandshakeApplicationProtocolSelector(SSLEngine engine, BiFunction selector) {
      try {
         SET_HANDSHAKE_APPLICATION_PROTOCOL_SELECTOR.invoke(engine, selector);
      } catch (UnsupportedOperationException ex) {
         throw ex;
      } catch (Exception ex) {
         throw new IllegalStateException(ex);
      }
   }

   static BiFunction getHandshakeApplicationProtocolSelector(SSLEngine engine) {
      try {
         return (BiFunction)GET_HANDSHAKE_APPLICATION_PROTOCOL_SELECTOR.invoke(engine);
      } catch (UnsupportedOperationException ex) {
         throw ex;
      } catch (Exception ex) {
         throw new IllegalStateException(ex);
      }
   }

   static {
      Method getHandshakeApplicationProtocol;
      Method getApplicationProtocol;
      Method setApplicationProtocols;
      Method setHandshakeApplicationProtocolSelector;
      Method getHandshakeApplicationProtocolSelector;
      try {
         SSLContext context = SSLContext.getInstance("TLS");
         context.init((KeyManager[])null, (TrustManager[])null, (SecureRandom)null);
         SSLEngine engine = context.createSSLEngine();
         getHandshakeApplicationProtocol = (Method)AccessController.doPrivileged(new PrivilegedExceptionAction() {
            public Method run() throws Exception {
               return SSLEngine.class.getMethod("getHandshakeApplicationProtocol");
            }
         });
         getHandshakeApplicationProtocol.invoke(engine);
         getApplicationProtocol = (Method)AccessController.doPrivileged(new PrivilegedExceptionAction() {
            public Method run() throws Exception {
               return SSLEngine.class.getMethod("getApplicationProtocol");
            }
         });
         getApplicationProtocol.invoke(engine);
         setApplicationProtocols = (Method)AccessController.doPrivileged(new PrivilegedExceptionAction() {
            public Method run() throws Exception {
               return SSLParameters.class.getMethod("setApplicationProtocols", String[].class);
            }
         });
         setApplicationProtocols.invoke(engine.getSSLParameters(), EmptyArrays.EMPTY_STRINGS);
         setHandshakeApplicationProtocolSelector = (Method)AccessController.doPrivileged(new PrivilegedExceptionAction() {
            public Method run() throws Exception {
               return SSLEngine.class.getMethod("setHandshakeApplicationProtocolSelector", BiFunction.class);
            }
         });
         setHandshakeApplicationProtocolSelector.invoke(engine, new BiFunction() {
            public String apply(SSLEngine sslEngine, List strings) {
               return null;
            }
         });
         getHandshakeApplicationProtocolSelector = (Method)AccessController.doPrivileged(new PrivilegedExceptionAction() {
            public Method run() throws Exception {
               return SSLEngine.class.getMethod("getHandshakeApplicationProtocolSelector");
            }
         });
         getHandshakeApplicationProtocolSelector.invoke(engine);
      } catch (Throwable t) {
         int version = PlatformDependent.javaVersion();
         if (version >= 9) {
            logger.error("Unable to initialize JdkAlpnSslUtils, but the detected java version was: {}", version, t);
         }

         getHandshakeApplicationProtocol = null;
         getApplicationProtocol = null;
         setApplicationProtocols = null;
         setHandshakeApplicationProtocolSelector = null;
         getHandshakeApplicationProtocolSelector = null;
      }

      GET_HANDSHAKE_APPLICATION_PROTOCOL = getHandshakeApplicationProtocol;
      GET_APPLICATION_PROTOCOL = getApplicationProtocol;
      SET_APPLICATION_PROTOCOLS = setApplicationProtocols;
      SET_HANDSHAKE_APPLICATION_PROTOCOL_SELECTOR = setHandshakeApplicationProtocolSelector;
      GET_HANDSHAKE_APPLICATION_PROTOCOL_SELECTOR = getHandshakeApplicationProtocolSelector;
   }
}
