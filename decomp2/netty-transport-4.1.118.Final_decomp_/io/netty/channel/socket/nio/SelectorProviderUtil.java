package io.netty.channel.socket.nio;

import io.netty.channel.socket.InternetProtocolFamily;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.SuppressJava6Requirement;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ProtocolFamily;
import java.net.StandardProtocolFamily;
import java.nio.channels.Channel;
import java.nio.channels.spi.SelectorProvider;

final class SelectorProviderUtil {
   private static final InternalLogger logger = InternalLoggerFactory.getInstance(SelectorProviderUtil.class);

   @SuppressJava6Requirement(
      reason = "Usage guarded by java version check"
   )
   static Method findOpenMethod(String methodName) {
      if (PlatformDependent.javaVersion() >= 15) {
         try {
            return SelectorProvider.class.getMethod(methodName, ProtocolFamily.class);
         } catch (Throwable e) {
            logger.debug("SelectorProvider.{}(ProtocolFamily) not available, will use default", methodName, e);
         }
      }

      return null;
   }

   @SuppressJava6Requirement(
      reason = "Usage guarded by java version check"
   )
   private static Channel newChannel(Method method, SelectorProvider provider, Object family) throws IOException {
      if (family != null && method != null) {
         try {
            C channel = (C)((Channel)method.invoke(provider, family));
            return channel;
         } catch (InvocationTargetException e) {
            throw new IOException(e);
         } catch (IllegalAccessException e) {
            throw new IOException(e);
         }
      } else {
         return null;
      }
   }

   @SuppressJava6Requirement(
      reason = "Usage guarded by java version check"
   )
   static Channel newChannel(Method method, SelectorProvider provider, InternetProtocolFamily family) throws IOException {
      return family != null ? newChannel(method, provider, (Object)ProtocolFamilyConverter.convert(family)) : null;
   }

   @SuppressJava6Requirement(
      reason = "Usage guarded by java version check"
   )
   static Channel newDomainSocketChannel(Method method, SelectorProvider provider) throws IOException {
      return newChannel(method, provider, (Object)StandardProtocolFamily.valueOf("UNIX"));
   }

   private SelectorProviderUtil() {
   }
}
