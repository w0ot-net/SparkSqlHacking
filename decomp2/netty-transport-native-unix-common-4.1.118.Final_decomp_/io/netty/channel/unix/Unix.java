package io.netty.channel.unix;

import io.netty.util.internal.ClassInitializerUtil;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.PortUnreachableException;
import java.nio.channels.ClosedChannelException;
import java.util.concurrent.atomic.AtomicBoolean;

public final class Unix {
   private static final AtomicBoolean registered = new AtomicBoolean();

   public static synchronized void registerInternal(Runnable registerTask) {
      registerTask.run();
      Socket.initialize();
   }

   /** @deprecated */
   @Deprecated
   public static boolean isAvailable() {
      return false;
   }

   /** @deprecated */
   @Deprecated
   public static void ensureAvailability() {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   public static Throwable unavailabilityCause() {
      return new UnsupportedOperationException();
   }

   private Unix() {
   }

   static {
      ClassInitializerUtil.tryLoadClasses(Unix.class, new Class[]{OutOfMemoryError.class, RuntimeException.class, ClosedChannelException.class, IOException.class, PortUnreachableException.class, DatagramSocketAddress.class, DomainDatagramSocketAddress.class, InetSocketAddress.class});
   }
}
