package jodd.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import jodd.util.StringUtil;

public class NetUtil {
   public static final String LOCAL_HOST = "localhost";
   public static final String LOCAL_IP = "127.0.0.1";
   public static final String DEFAULT_MASK = "255.255.255.0";
   public static final int INT_VALUE_127_0_0_1 = 2130706433;

   public static String resolveIpAddress(String hostname) {
      try {
         InetAddress netAddress;
         if (hostname != null && !hostname.equalsIgnoreCase("localhost")) {
            netAddress = Inet4Address.getByName(hostname);
         } else {
            netAddress = InetAddress.getLocalHost();
         }

         return netAddress.getHostAddress();
      } catch (UnknownHostException var2) {
         return null;
      }
   }

   public static int getIpAsInt(String ipAddress) {
      int ipIntValue = 0;
      String[] tokens = StringUtil.splitc(ipAddress, '.');

      for(String token : tokens) {
         if (ipIntValue > 0) {
            ipIntValue <<= 8;
         }

         ipIntValue += Integer.parseInt(token);
      }

      return ipIntValue;
   }

   public static int getMaskAsInt(String mask) {
      if (!validateHostIp(mask)) {
         mask = "255.255.255.0";
      }

      return getIpAsInt(mask);
   }

   public static boolean isSocketAccessAllowed(int localIp, int socketIp, int mask) {
      boolean _retVal = false;
      if (socketIp == 2130706433 || (localIp & mask) == (socketIp & mask)) {
         _retVal = true;
      }

      return _retVal;
   }

   public static boolean validateHostIp(String host) {
      boolean retVal = false;
      if (host == null) {
         return retVal;
      } else {
         int hitDots = 0;
         char[] data = host.toCharArray();

         for(int i = 0; i < data.length; ++i) {
            char c = data[i];
            int b = 0;

            do {
               if (c < '0' || c > '9') {
                  return false;
               }

               b = b * 10 + c - 48;
               ++i;
               if (i >= data.length) {
                  break;
               }

               c = data[i];
            } while(c != '.');

            if (b > 255) {
               return false;
            }

            ++hitDots;
         }

         return hitDots == 4;
      }
   }

   public static String resolveHostName(byte[] ip) {
      try {
         InetAddress address = InetAddress.getByAddress(ip);
         return address.getHostName();
      } catch (UnknownHostException var2) {
         return null;
      }
   }

   public static byte[] downloadBytes(String url) throws IOException {
      InputStream inputStream = (new URL(url)).openStream();
      return StreamUtil.readBytes(inputStream);
   }

   public static String downloadString(String url, String encoding) throws IOException {
      InputStream inputStream = (new URL(url)).openStream();
      return new String(StreamUtil.readChars(inputStream, encoding));
   }

   public static String downloadString(String url) throws IOException {
      InputStream inputStream = (new URL(url)).openStream();
      return new String(StreamUtil.readChars(inputStream));
   }

   public static void downloadFile(String url, File file) throws IOException {
      InputStream inputStream = (new URL(url)).openStream();
      ReadableByteChannel rbc = Channels.newChannel(inputStream);
      FileOutputStream fos = new FileOutputStream(file);
      fos.getChannel().transferFrom(rbc, 0L, 16777216L);
   }
}
