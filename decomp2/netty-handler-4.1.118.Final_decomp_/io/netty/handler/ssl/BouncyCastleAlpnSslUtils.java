package io.netty.handler.ssl;

import io.netty.util.internal.EmptyArrays;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.SuppressJava6Requirement;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;
import java.util.List;
import java.util.function.BiFunction;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;

@SuppressJava6Requirement(
   reason = "Usage guarded by java version check"
)
final class BouncyCastleAlpnSslUtils {
   private static final InternalLogger logger = InternalLoggerFactory.getInstance(BouncyCastleAlpnSslUtils.class);
   private static final Method SET_PARAMETERS;
   private static final Method GET_PARAMETERS;
   private static final Method SET_APPLICATION_PROTOCOLS;
   private static final Method GET_APPLICATION_PROTOCOL;
   private static final Method GET_HANDSHAKE_APPLICATION_PROTOCOL;
   private static final Method SET_HANDSHAKE_APPLICATION_PROTOCOL_SELECTOR;
   private static final Method GET_HANDSHAKE_APPLICATION_PROTOCOL_SELECTOR;
   private static final Class BC_APPLICATION_PROTOCOL_SELECTOR;
   private static final Method BC_APPLICATION_PROTOCOL_SELECTOR_SELECT;

   private BouncyCastleAlpnSslUtils() {
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

   static void setApplicationProtocols(SSLEngine engine, List supportedProtocols) {
      String[] protocolArray = (String[])supportedProtocols.toArray(EmptyArrays.EMPTY_STRINGS);

      try {
         Object bcSslParameters = GET_PARAMETERS.invoke(engine);
         SET_APPLICATION_PROTOCOLS.invoke(bcSslParameters, protocolArray);
         SET_PARAMETERS.invoke(engine, bcSslParameters);
      } catch (UnsupportedOperationException ex) {
         throw ex;
      } catch (Exception ex) {
         throw new IllegalStateException(ex);
      }

      if (PlatformDependent.javaVersion() >= 9) {
         JdkAlpnSslUtils.setApplicationProtocols(engine, supportedProtocols);
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

   static void setHandshakeApplicationProtocolSelector(SSLEngine engine, final BiFunction selector) {
      try {
         Object selectorProxyInstance = Proxy.newProxyInstance(BouncyCastleAlpnSslUtils.class.getClassLoader(), new Class[]{BC_APPLICATION_PROTOCOL_SELECTOR}, new InvocationHandler() {
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
               if (method.getName().equals("select")) {
                  try {
                     return selector.apply((SSLEngine)args[0], (List)args[1]);
                  } catch (ClassCastException e) {
                     throw new RuntimeException("BCApplicationProtocolSelector select method parameter of invalid type.", e);
                  }
               } else {
                  throw new UnsupportedOperationException(String.format("Method '%s' not supported.", method.getName()));
               }
            }
         });
         SET_HANDSHAKE_APPLICATION_PROTOCOL_SELECTOR.invoke(engine, selectorProxyInstance);
      } catch (UnsupportedOperationException ex) {
         throw ex;
      } catch (Exception ex) {
         throw new IllegalStateException(ex);
      }
   }

   static BiFunction getHandshakeApplicationProtocolSelector(SSLEngine engine) {
      try {
         final Object selector = GET_HANDSHAKE_APPLICATION_PROTOCOL_SELECTOR.invoke(engine);
         return new BiFunction() {
            public String apply(SSLEngine sslEngine, List strings) {
               try {
                  return (String)BouncyCastleAlpnSslUtils.BC_APPLICATION_PROTOCOL_SELECTOR_SELECT.invoke(selector, sslEngine, strings);
               } catch (Exception e) {
                  throw new RuntimeException("Could not call getHandshakeApplicationProtocolSelector", e);
               }
            }
         };
      } catch (UnsupportedOperationException ex) {
         throw ex;
      } catch (Exception ex) {
         throw new IllegalStateException(ex);
      }
   }

   static {
      Method getParameters;
      Method setParameters;
      Method setApplicationProtocols;
      Method getApplicationProtocol;
      Method getHandshakeApplicationProtocol;
      Method setHandshakeApplicationProtocolSelector;
      Method getHandshakeApplicationProtocolSelector;
      Method bcApplicationProtocolSelectorSelect;
      final Class bcApplicationProtocolSelector;
      try {
         final Class bcSslEngine = Class.forName("org.bouncycastle.jsse.BCSSLEngine");
         bcApplicationProtocolSelector = Class.forName("org.bouncycastle.jsse.BCApplicationProtocolSelector");
         bcApplicationProtocolSelectorSelect = (Method)AccessController.doPrivileged(new PrivilegedExceptionAction() {
            public Method run() throws Exception {
               return bcApplicationProtocolSelector.getMethod("select", Object.class, List.class);
            }
         });
         SSLContext context = SslUtils.getSSLContext("BCJSSE");
         SSLEngine engine = context.createSSLEngine();
         getParameters = (Method)AccessController.doPrivileged(new PrivilegedExceptionAction() {
            public Method run() throws Exception {
               return bcSslEngine.getMethod("getParameters");
            }
         });
         Object bcSslParameters = getParameters.invoke(engine);
         final Class<?> bCSslParametersClass = bcSslParameters.getClass();
         setParameters = (Method)AccessController.doPrivileged(new PrivilegedExceptionAction() {
            public Method run() throws Exception {
               return bcSslEngine.getMethod("setParameters", bCSslParametersClass);
            }
         });
         setParameters.invoke(engine, bcSslParameters);
         setApplicationProtocols = (Method)AccessController.doPrivileged(new PrivilegedExceptionAction() {
            public Method run() throws Exception {
               return bCSslParametersClass.getMethod("setApplicationProtocols", String[].class);
            }
         });
         setApplicationProtocols.invoke(bcSslParameters, EmptyArrays.EMPTY_STRINGS);
         getApplicationProtocol = (Method)AccessController.doPrivileged(new PrivilegedExceptionAction() {
            public Method run() throws Exception {
               return bcSslEngine.getMethod("getApplicationProtocol");
            }
         });
         getApplicationProtocol.invoke(engine);
         getHandshakeApplicationProtocol = (Method)AccessController.doPrivileged(new PrivilegedExceptionAction() {
            public Method run() throws Exception {
               return bcSslEngine.getMethod("getHandshakeApplicationProtocol");
            }
         });
         getHandshakeApplicationProtocol.invoke(engine);
         setHandshakeApplicationProtocolSelector = (Method)AccessController.doPrivileged(new PrivilegedExceptionAction() {
            public Method run() throws Exception {
               return bcSslEngine.getMethod("setBCHandshakeApplicationProtocolSelector", bcApplicationProtocolSelector);
            }
         });
         getHandshakeApplicationProtocolSelector = (Method)AccessController.doPrivileged(new PrivilegedExceptionAction() {
            public Method run() throws Exception {
               return bcSslEngine.getMethod("getBCHandshakeApplicationProtocolSelector");
            }
         });
         getHandshakeApplicationProtocolSelector.invoke(engine);
      } catch (Throwable t) {
         logger.error("Unable to initialize BouncyCastleAlpnSslUtils.", t);
         setParameters = null;
         getParameters = null;
         setApplicationProtocols = null;
         getApplicationProtocol = null;
         getHandshakeApplicationProtocol = null;
         setHandshakeApplicationProtocolSelector = null;
         getHandshakeApplicationProtocolSelector = null;
         bcApplicationProtocolSelectorSelect = null;
         bcApplicationProtocolSelector = null;
      }

      SET_PARAMETERS = setParameters;
      GET_PARAMETERS = getParameters;
      SET_APPLICATION_PROTOCOLS = setApplicationProtocols;
      GET_APPLICATION_PROTOCOL = getApplicationProtocol;
      GET_HANDSHAKE_APPLICATION_PROTOCOL = getHandshakeApplicationProtocol;
      SET_HANDSHAKE_APPLICATION_PROTOCOL_SELECTOR = setHandshakeApplicationProtocolSelector;
      GET_HANDSHAKE_APPLICATION_PROTOCOL_SELECTOR = getHandshakeApplicationProtocolSelector;
      BC_APPLICATION_PROTOCOL_SELECTOR_SELECT = bcApplicationProtocolSelectorSelect;
      BC_APPLICATION_PROTOCOL_SELECTOR = bcApplicationProtocolSelector;
   }
}
