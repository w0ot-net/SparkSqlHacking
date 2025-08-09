package org.apache.zookeeper.common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AtomicFileOutputStream extends FilterOutputStream {
   public static final String TMP_EXTENSION = ".tmp";
   private static final Logger LOG = LoggerFactory.getLogger(AtomicFileOutputStream.class);
   private final File origFile;
   private final File tmpFile;

   public AtomicFileOutputStream(File f) throws FileNotFoundException {
      super(new FileOutputStream(new File(f.getParentFile(), f.getName() + ".tmp")));
      this.origFile = f.getAbsoluteFile();
      this.tmpFile = (new File(f.getParentFile(), f.getName() + ".tmp")).getAbsoluteFile();
   }

   public void write(byte[] b, int off, int len) throws IOException {
      this.out.write(b, off, len);
   }

   public void close() throws IOException {
      boolean triedToClose = false;
      boolean success = false;
      boolean var7 = false;

      try {
         var7 = true;
         this.flush();
         ((FileOutputStream)this.out).getFD().sync();
         triedToClose = true;
         super.close();
         success = true;
         var7 = false;
      } finally {
         if (var7) {
            if (success) {
               boolean renamed = this.tmpFile.renameTo(this.origFile);
               if (!renamed && (!this.origFile.delete() || !this.tmpFile.renameTo(this.origFile))) {
                  throw new IOException("Could not rename temporary file " + this.tmpFile + " to " + this.origFile);
               }
            } else {
               if (!triedToClose) {
                  IOUtils.closeStream(this.out);
               }

               if (!this.tmpFile.delete()) {
                  LOG.warn("Unable to delete tmp file {}", this.tmpFile);
               }
            }

         }
      }

      if (success) {
         boolean renamed = this.tmpFile.renameTo(this.origFile);
         if (!renamed && (!this.origFile.delete() || !this.tmpFile.renameTo(this.origFile))) {
            throw new IOException("Could not rename temporary file " + this.tmpFile + " to " + this.origFile);
         }
      } else {
         if (!triedToClose) {
            IOUtils.closeStream(this.out);
         }

         if (!this.tmpFile.delete()) {
            LOG.warn("Unable to delete tmp file {}", this.tmpFile);
         }
      }

   }

   public void abort() {
      try {
         super.close();
      } catch (IOException ioe) {
         LOG.warn("Unable to abort file {}", this.tmpFile, ioe);
      }

      if (!this.tmpFile.delete()) {
         LOG.warn("Unable to delete tmp file during abort {}", this.tmpFile);
      }

   }
}
