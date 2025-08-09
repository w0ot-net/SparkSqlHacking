package org.apache.logging.log4j.core.appender.rolling.action;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;
import java.util.zip.GZIPOutputStream;

public final class GzCompressAction extends AbstractAction {
   private static final int BUF_SIZE = 8192;
   private final File source;
   private final File destination;
   private final boolean deleteSource;
   private final int compressionLevel;

   public GzCompressAction(final File source, final File destination, final boolean deleteSource, final int compressionLevel) {
      Objects.requireNonNull(source, "source");
      Objects.requireNonNull(destination, "destination");
      this.source = source;
      this.destination = destination;
      this.deleteSource = deleteSource;
      this.compressionLevel = compressionLevel;
   }

   /** @deprecated */
   @Deprecated
   public GzCompressAction(final File source, final File destination, final boolean deleteSource) {
      this(source, destination, deleteSource, -1);
   }

   public boolean execute() throws IOException {
      return execute(this.source, this.destination, this.deleteSource, this.compressionLevel);
   }

   /** @deprecated */
   @Deprecated
   public static boolean execute(final File source, final File destination, final boolean deleteSource) throws IOException {
      return execute(source, destination, deleteSource, -1);
   }

   public static boolean execute(final File source, final File destination, final boolean deleteSource, final int compressionLevel) throws IOException {
      if (source.exists()) {
         FileInputStream fis = new FileInputStream(source);

         try {
            OutputStream fos = new FileOutputStream(destination);

            try {
               OutputStream gzipOut = new ConfigurableLevelGZIPOutputStream(fos, 8192, compressionLevel);

               try {
                  OutputStream os = new BufferedOutputStream(gzipOut, 8192);

                  try {
                     byte[] inbuf = new byte[8192];

                     int n;
                     while((n = fis.read(inbuf)) != -1) {
                        os.write(inbuf, 0, n);
                     }
                  } catch (Throwable var14) {
                     try {
                        os.close();
                     } catch (Throwable var13) {
                        var14.addSuppressed(var13);
                     }

                     throw var14;
                  }

                  os.close();
               } catch (Throwable var15) {
                  try {
                     gzipOut.close();
                  } catch (Throwable var12) {
                     var15.addSuppressed(var12);
                  }

                  throw var15;
               }

               gzipOut.close();
            } catch (Throwable var16) {
               try {
                  fos.close();
               } catch (Throwable var11) {
                  var16.addSuppressed(var11);
               }

               throw var16;
            }

            fos.close();
         } catch (Throwable var17) {
            try {
               fis.close();
            } catch (Throwable var10) {
               var17.addSuppressed(var10);
            }

            throw var17;
         }

         fis.close();
         if (deleteSource && !source.delete()) {
            LOGGER.warn("Unable to delete {}.", source);
         }

         return true;
      } else {
         return false;
      }
   }

   protected void reportException(final Exception ex) {
      LOGGER.warn("Exception during compression of '" + this.source.toString() + "'.", ex);
   }

   public String toString() {
      return GzCompressAction.class.getSimpleName() + '[' + this.source + " to " + this.destination + ", deleteSource=" + this.deleteSource + ']';
   }

   public File getSource() {
      return this.source;
   }

   public File getDestination() {
      return this.destination;
   }

   public boolean isDeleteSource() {
      return this.deleteSource;
   }

   private static final class ConfigurableLevelGZIPOutputStream extends GZIPOutputStream {
      ConfigurableLevelGZIPOutputStream(final OutputStream out, final int bufSize, final int level) throws IOException {
         super(out, bufSize);
         this.def.setLevel(level);
      }
   }
}
