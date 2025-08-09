package org.apache.logging.log4j.core.util;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.io.File;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.function.Function;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.status.StatusLogger;

public final class NetUtils {
   private static final Logger LOGGER = StatusLogger.getLogger();
   private static final String UNKNOWN_LOCALHOST = "UNKNOWN_LOCALHOST";

   private NetUtils() {
   }

   public static String getLocalHostname() {
      return getHostname(InetAddress::getHostName);
   }

   public static String getCanonicalLocalHostname() {
      return getHostname(InetAddress::getCanonicalHostName);
   }

   private static String getHostname(final Function callback) {
      try {
         InetAddress address = InetAddress.getLocalHost();
         return address == null ? "UNKNOWN_LOCALHOST" : (String)callback.apply(address);
      } catch (UnknownHostException uhe) {
         try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();

            while(interfaces.hasMoreElements()) {
               NetworkInterface nic = (NetworkInterface)interfaces.nextElement();
               Enumeration<InetAddress> addresses = nic.getInetAddresses();

               while(addresses.hasMoreElements()) {
                  InetAddress address = (InetAddress)addresses.nextElement();
                  if (!address.isLoopbackAddress()) {
                     String hostname = (String)callback.apply(address);
                     if (hostname != null) {
                        return hostname;
                     }
                  }
               }
            }
         } catch (SocketException var7) {
         }

         LOGGER.error("Could not determine local host name", uhe);
         return "UNKNOWN_LOCALHOST";
      }
   }

   public static byte[] getMacAddress() {
      byte[] mac = null;

      try {
         InetAddress localHost = InetAddress.getLocalHost();

         try {
            NetworkInterface localInterface = NetworkInterface.getByInetAddress(localHost);
            if (isUpAndNotLoopback(localInterface)) {
               mac = localInterface.getHardwareAddress();
            }

            if (mac == null) {
               Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
               if (networkInterfaces != null) {
                  while(networkInterfaces.hasMoreElements() && mac == null) {
                     NetworkInterface nic = (NetworkInterface)networkInterfaces.nextElement();
                     if (isUpAndNotLoopback(nic)) {
                        mac = nic.getHardwareAddress();
                     }
                  }
               }
            }
         } catch (SocketException e) {
            LOGGER.catching(e);
         }

         if (ArrayUtils.isEmpty(mac) && localHost != null) {
            byte[] address = localHost.getAddress();
            mac = Arrays.copyOf(address, 6);
         }
      } catch (UnknownHostException var6) {
      }

      return mac;
   }

   public static String getMacAddressString() {
      byte[] macAddr = getMacAddress();
      if (ArrayUtils.isEmpty(macAddr)) {
         return null;
      } else {
         StringBuilder sb = new StringBuilder(String.format("%02x", macAddr[0]));

         for(int i = 1; i < macAddr.length; ++i) {
            sb.append(":").append(String.format("%02x", macAddr[i]));
         }

         return sb.toString();
      }
   }

   private static boolean isUpAndNotLoopback(final NetworkInterface ni) throws SocketException {
      return ni != null && !ni.isLoopback() && ni.isUp();
   }

   @SuppressFBWarnings(
      value = {"PATH_TRAVERSAL_IN"},
      justification = "Currently `path` comes from a configuration file."
   )
   public static URI toURI(final String path) {
      try {
         return new URI(path);
      } catch (URISyntaxException var4) {
         try {
            URL url = new URL(path);
            return new URI(url.getProtocol(), url.getHost(), url.getPath(), (String)null);
         } catch (URISyntaxException | MalformedURLException var3) {
            return (new File(path)).toURI();
         }
      }
   }

   public static List toURIs(final String path) {
      String[] parts = path.split(",");
      String scheme = null;
      List<URI> uris = new ArrayList(parts.length);

      for(String part : parts) {
         URI uri = toURI(scheme != null ? scheme + ":" + part.trim() : part.trim());
         if (scheme == null && uri.getScheme() != null) {
            scheme = uri.getScheme();
         }

         uris.add(uri);
      }

      return uris;
   }
}
