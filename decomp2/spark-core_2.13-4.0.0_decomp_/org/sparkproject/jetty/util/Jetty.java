package org.sparkproject.jetty.util;

import java.io.InputStream;
import java.time.Instant;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Jetty {
   private static final Logger LOG = LoggerFactory.getLogger(Jetty.class);
   public static final String VERSION;
   public static final String POWERED_BY;
   public static final boolean STABLE;
   public static final String GIT_HASH;
   public static final String BUILD_TIMESTAMP;
   private static final Properties __buildProperties = new Properties();

   private Jetty() {
   }

   private static String formatTimestamp(String timestamp) {
      try {
         long epochMillis = Long.parseLong(timestamp);
         return Instant.ofEpochMilli(epochMillis).toString();
      } catch (NumberFormatException e) {
         LOG.trace("IGNORED", e);
         return "unknown";
      }
   }

   static {
      try {
         InputStream inputStream = Jetty.class.getResourceAsStream("/org/sparkproject/jetty/version/build.properties");

         try {
            __buildProperties.load(inputStream);
         } catch (Throwable var4) {
            if (inputStream != null) {
               try {
                  inputStream.close();
               } catch (Throwable var3) {
                  var4.addSuppressed(var3);
               }
            }

            throw var4;
         }

         if (inputStream != null) {
            inputStream.close();
         }
      } catch (Exception e) {
         LOG.trace("IGNORED", e);
      }

      String gitHash = __buildProperties.getProperty("buildNumber", "unknown");
      if (gitHash.startsWith("${")) {
         gitHash = "unknown";
      }

      GIT_HASH = gitHash;
      System.setProperty("jetty.git.hash", GIT_HASH);
      BUILD_TIMESTAMP = formatTimestamp(__buildProperties.getProperty("timestamp", "unknown"));
      Package pkg = Jetty.class.getPackage();
      if (pkg != null && "Eclipse Jetty Project".equals(pkg.getImplementationVendor()) && pkg.getImplementationVersion() != null) {
         VERSION = pkg.getImplementationVersion();
      } else {
         VERSION = System.getProperty("jetty.version", __buildProperties.getProperty("version", "10.0.z-SNAPSHOT"));
      }

      String var10000 = VERSION;
      POWERED_BY = "<a href=\"https://jetty.org\">Powered by Jetty:// " + var10000 + "</a>";
      STABLE = !VERSION.matches("^.*\\.(RC|M)[0-9]+$");
   }
}
