package io.vertx.core.logging;

import io.vertx.core.spi.logging.LogDelegate;
import io.vertx.core.spi.logging.LogDelegateFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.LogManager;

public class JULLogDelegateFactory implements LogDelegateFactory {
   public static void loadConfig() {
      try {
         InputStream is = JULLogDelegateFactory.class.getClassLoader().getResourceAsStream("vertx-default-jul-logging.properties");
         Throwable var1 = null;

         try {
            if (is != null) {
               LogManager.getLogManager().readConfiguration(is);
            }
         } catch (Throwable var11) {
            var1 = var11;
            throw var11;
         } finally {
            if (is != null) {
               if (var1 != null) {
                  try {
                     is.close();
                  } catch (Throwable var10) {
                     var1.addSuppressed(var10);
                  }
               } else {
                  is.close();
               }
            }

         }
      } catch (IOException var13) {
      }

   }

   public boolean isAvailable() {
      return true;
   }

   public LogDelegate createDelegate(String name) {
      return new JULLogDelegate(name);
   }

   static {
      if (System.getProperty("java.util.logging.config.file") == null) {
         loadConfig();
      }

   }
}
