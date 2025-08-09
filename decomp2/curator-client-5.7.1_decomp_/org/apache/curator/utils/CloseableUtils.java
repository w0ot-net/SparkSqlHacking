package org.apache.curator.utils;

import java.io.Closeable;
import java.io.IOException;
import org.apache.curator.shaded.com.google.common.io.Closeables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CloseableUtils {
   private static final Logger log = LoggerFactory.getLogger(CloseableUtils.class);

   public static void closeQuietly(Closeable closeable) {
      try {
         Closeables.close(closeable, true);
      } catch (IOException e) {
         log.error("IOException should not have been thrown.", e);
      }

   }
}
