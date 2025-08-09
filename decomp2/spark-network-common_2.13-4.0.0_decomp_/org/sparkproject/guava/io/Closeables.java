package org.sparkproject.guava.io;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.CheckForNull;
import org.sparkproject.guava.annotations.GwtIncompatible;
import org.sparkproject.guava.annotations.J2ktIncompatible;
import org.sparkproject.guava.annotations.VisibleForTesting;

@ElementTypesAreNonnullByDefault
@J2ktIncompatible
@GwtIncompatible
public final class Closeables {
   @VisibleForTesting
   static final Logger logger = Logger.getLogger(Closeables.class.getName());

   private Closeables() {
   }

   public static void close(@CheckForNull Closeable closeable, boolean swallowIOException) throws IOException {
      if (closeable != null) {
         try {
            closeable.close();
         } catch (IOException e) {
            if (!swallowIOException) {
               throw e;
            }

            logger.log(Level.WARNING, "IOException thrown while closing Closeable.", e);
         }

      }
   }

   public static void closeQuietly(@CheckForNull InputStream inputStream) {
      try {
         close(inputStream, true);
      } catch (IOException impossible) {
         throw new AssertionError(impossible);
      }
   }

   public static void closeQuietly(@CheckForNull Reader reader) {
      try {
         close(reader, true);
      } catch (IOException impossible) {
         throw new AssertionError(impossible);
      }
   }
}
