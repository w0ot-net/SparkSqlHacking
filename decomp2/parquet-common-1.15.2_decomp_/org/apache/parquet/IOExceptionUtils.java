package org.apache.parquet;

import java.io.Closeable;
import java.io.IOException;

/** @deprecated */
@Deprecated
public class IOExceptionUtils {
   public static void closeQuietly(Closeable closeable) {
      try {
         closeable.close();
      } catch (IOException e) {
         throw new ParquetRuntimeException("Error closing I/O related resources.", e) {
         };
      }
   }
}
