package org.apache.spark.deploy;

import java.net.URI;
import java.net.URISyntaxException;

public final class ClientArguments$ {
   public static final ClientArguments$ MODULE$ = new ClientArguments$();
   private static final int DEFAULT_CORES = 1;
   private static final int DEFAULT_MEMORY;
   private static final boolean DEFAULT_SUPERVISE;

   static {
      DEFAULT_MEMORY = org.apache.spark.util.Utils$.MODULE$.DEFAULT_DRIVER_MEM_MB();
      DEFAULT_SUPERVISE = false;
   }

   public int DEFAULT_CORES() {
      return DEFAULT_CORES;
   }

   public int DEFAULT_MEMORY() {
      return DEFAULT_MEMORY;
   }

   public boolean DEFAULT_SUPERVISE() {
      return DEFAULT_SUPERVISE;
   }

   public boolean isValidJarUrl(final String s) {
      boolean var10000;
      try {
         URI uri = new URI(s);
         var10000 = uri.getScheme() != null && uri.getPath() != null && uri.getPath().endsWith(".jar");
      } catch (URISyntaxException var3) {
         var10000 = false;
      }

      return var10000;
   }

   private ClientArguments$() {
   }
}
