package org.apache.zookeeper.common;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;

public class NetUtils {
   public static String formatInetAddr(InetSocketAddress addr) {
      String hostString = addr.getHostString();
      InetAddress ia = addr.getAddress();
      return ia instanceof Inet6Address && hostString.contains(":") ? String.format("[%s]:%s", hostString, addr.getPort()) : String.format("%s:%s", hostString, addr.getPort());
   }

   public static String[] getIPV6HostAndPort(String hostPort) {
      if (hostPort.startsWith("[")) {
         int i = hostPort.lastIndexOf(93);
         if (i < 0) {
            throw new IllegalArgumentException(hostPort + " starts with '[' but has no matching ']'");
         } else {
            String host = hostPort.substring(1, i);
            if (host.isEmpty()) {
               throw new IllegalArgumentException(host + " is empty.");
            } else {
               return hostPort.length() > i + 1 ? getHostPort(hostPort, i, host) : new String[]{host};
            }
         }
      } else {
         return new String[0];
      }
   }

   private static String[] getHostPort(String hostPort, int indexOfClosingBracket, String host) {
      if (hostPort.charAt(indexOfClosingBracket + 1) != ':') {
         throw new IllegalArgumentException(hostPort + " does not have : after ]");
      } else if (indexOfClosingBracket + 2 == hostPort.length()) {
         throw new IllegalArgumentException(hostPort + " doesn't have a port after colon.");
      } else {
         String port = hostPort.substring(indexOfClosingBracket + 2);
         return new String[]{host, port};
      }
   }
}
