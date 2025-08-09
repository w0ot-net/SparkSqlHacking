package org.apache.logging.log4j.core.appender.rolling.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public final class ZipCompressAction extends AbstractAction {
   private static final int BUF_SIZE = 8192;
   private final File source;
   private final File destination;
   private final boolean deleteSource;
   private final int level;

   public ZipCompressAction(final File source, final File destination, final boolean deleteSource, final int level) {
      Objects.requireNonNull(source, "source");
      Objects.requireNonNull(destination, "destination");
      this.source = source;
      this.destination = destination;
      this.deleteSource = deleteSource;
      this.level = level;
   }

   public boolean execute() throws IOException {
      return execute(this.source, this.destination, this.deleteSource, this.level);
   }

   public static boolean execute(final File source, final File destination, final boolean deleteSource, final int level) throws IOException {
      if (source.exists()) {
         FileInputStream fis = new FileInputStream(source);

         try {
            ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(destination));

            try {
               zos.setLevel(level);
               ZipEntry zipEntry = new ZipEntry(source.getName());
               zos.putNextEntry(zipEntry);
               byte[] inbuf = new byte[8192];

               int n;
               while((n = fis.read(inbuf)) != -1) {
                  zos.write(inbuf, 0, n);
               }
            } catch (Throwable var11) {
               try {
                  zos.close();
               } catch (Throwable var10) {
                  var11.addSuppressed(var10);
               }

               throw var11;
            }

            zos.close();
         } catch (Throwable var12) {
            try {
               fis.close();
            } catch (Throwable var9) {
               var12.addSuppressed(var9);
            }

            throw var12;
         }

         fis.close();
         if (deleteSource && !source.delete()) {
            LOGGER.warn("Unable to delete " + source.toString() + '.');
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
      return ZipCompressAction.class.getSimpleName() + '[' + this.source + " to " + this.destination + ", level=" + this.level + ", deleteSource=" + this.deleteSource + ']';
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

   public int getLevel() {
      return this.level;
   }
}
