package org.apache.ivy.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

public final class HostUtil {
   private static String localHostName = null;

   private HostUtil() {
   }

   public static String getLocalHostName() {
      if (localHostName == null) {
         try {
            localHostName = InetAddress.getLocalHost().getHostName();
         } catch (UnknownHostException var1) {
            localHostName = "localhost";
         }
      }

      return localHostName;
   }
}
