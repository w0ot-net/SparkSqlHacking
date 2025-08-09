package org.apache.parquet;

import java.io.Closeable;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** @deprecated */
@Deprecated
public final class Closeables {
   private static final Logger LOG = LoggerFactory.getLogger(Closeables.class);

   private Closeables() {
   }

   public static void close(Closeable c) throws IOException {
      if (c != null) {
         c.close();
      }
   }

   public static void closeAndSwallowIOExceptions(Closeable c) {
      if (c != null) {
         try {
            c.close();
         } catch (IOException e) {
            LOG.warn("Encountered exception closing closeable", e);
         }

      }
   }
}
