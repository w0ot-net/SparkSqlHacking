package io.vertx.core.eventbus;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class AddressHelper {
   private AddressHelper() {
   }

   public static String defaultAddress() {
      Enumeration<NetworkInterface> nets;
      try {
         nets = NetworkInterface.getNetworkInterfaces();
      } catch (SocketException var4) {
         return null;
      }

      while(nets.hasMoreElements()) {
         NetworkInterface netinf = (NetworkInterface)nets.nextElement();
         Enumeration<InetAddress> addresses = netinf.getInetAddresses();

         while(addresses.hasMoreElements()) {
            InetAddress address = (InetAddress)addresses.nextElement();
            if (!address.isAnyLocalAddress() && !address.isMulticastAddress() && !(address instanceof Inet6Address)) {
               return address.getHostAddress();
            }
         }
      }

      return null;
   }
}
