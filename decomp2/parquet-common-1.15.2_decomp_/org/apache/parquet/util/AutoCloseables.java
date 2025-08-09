package org.apache.parquet.util;

import java.util.Arrays;
import org.apache.parquet.ParquetRuntimeException;

public final class AutoCloseables {
   public static void close(Iterable autoCloseables) throws Throwable {
      Throwable root = null;

      for(AutoCloseable autoCloseable : autoCloseables) {
         try {
            if (autoCloseable != null) {
               autoCloseable.close();
            }
         } catch (Throwable e) {
            if (root == null) {
               root = e;
            } else {
               root.addSuppressed(e);
            }
         }
      }

      if (root != null) {
         throw root;
      }
   }

   public static void close(AutoCloseable... autoCloseables) throws Throwable {
      close((Iterable)Arrays.asList(autoCloseables));
   }

   public static void uncheckedClose(Iterable autoCloseables) throws ParquetCloseResourceException {
      try {
         close(autoCloseables);
      } catch (Throwable e) {
         throw new ParquetCloseResourceException(e);
      }
   }

   public static void uncheckedClose(AutoCloseable... autoCloseables) {
      uncheckedClose((Iterable)Arrays.asList(autoCloseables));
   }

   private AutoCloseables() {
   }

   public static class ParquetCloseResourceException extends ParquetRuntimeException {
      private ParquetCloseResourceException(Throwable e) {
         super("Unable to close resource", e);
      }
   }
}
