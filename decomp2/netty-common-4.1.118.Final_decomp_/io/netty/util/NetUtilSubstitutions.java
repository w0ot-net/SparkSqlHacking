package io.netty.util;

import com.oracle.svm.core.annotate.Alias;
import com.oracle.svm.core.annotate.InjectAccessors;
import com.oracle.svm.core.annotate.TargetClass;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collection;

@TargetClass(NetUtil.class)
final class NetUtilSubstitutions {
   @Alias
   @InjectAccessors(NetUtilLocalhost4Accessor.class)
   public static Inet4Address LOCALHOST4;
   @Alias
   @InjectAccessors(NetUtilLocalhost6Accessor.class)
   public static Inet6Address LOCALHOST6;
   @Alias
   @InjectAccessors(NetUtilLocalhostAccessor.class)
   public static InetAddress LOCALHOST;
   @Alias
   @InjectAccessors(NetUtilNetworkInterfacesAccessor.class)
   public static Collection NETWORK_INTERFACES;

   private NetUtilSubstitutions() {
   }

   private static final class NetUtilLocalhost4Accessor {
      static Inet4Address get() {
         return NetUtilSubstitutions.NetUtilLocalhost4LazyHolder.LOCALHOST4;
      }

      static void set(Inet4Address ignored) {
      }
   }

   private static final class NetUtilLocalhost4LazyHolder {
      private static final Inet4Address LOCALHOST4 = NetUtilInitializations.createLocalhost4();
   }

   private static final class NetUtilLocalhost6Accessor {
      static Inet6Address get() {
         return NetUtilSubstitutions.NetUtilLocalhost6LazyHolder.LOCALHOST6;
      }

      static void set(Inet6Address ignored) {
      }
   }

   private static final class NetUtilLocalhost6LazyHolder {
      private static final Inet6Address LOCALHOST6 = NetUtilInitializations.createLocalhost6();
   }

   private static final class NetUtilLocalhostAccessor {
      static InetAddress get() {
         return NetUtilSubstitutions.NetUtilLocalhostLazyHolder.LOCALHOST;
      }

      static void set(InetAddress ignored) {
      }
   }

   private static final class NetUtilLocalhostLazyHolder {
      private static final InetAddress LOCALHOST;

      static {
         LOCALHOST = NetUtilInitializations.determineLoopback(NetUtilSubstitutions.NetUtilNetworkInterfacesLazyHolder.NETWORK_INTERFACES, NetUtilSubstitutions.NetUtilLocalhost4LazyHolder.LOCALHOST4, NetUtilSubstitutions.NetUtilLocalhost6LazyHolder.LOCALHOST6).address();
      }
   }

   private static final class NetUtilNetworkInterfacesAccessor {
      static Collection get() {
         return NetUtilSubstitutions.NetUtilNetworkInterfacesLazyHolder.NETWORK_INTERFACES;
      }

      static void set(Collection ignored) {
      }
   }

   private static final class NetUtilNetworkInterfacesLazyHolder {
      private static final Collection NETWORK_INTERFACES = NetUtilInitializations.networkInterfaces();
   }
}
