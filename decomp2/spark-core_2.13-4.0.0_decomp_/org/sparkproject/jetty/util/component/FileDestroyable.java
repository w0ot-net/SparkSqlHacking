package org.sparkproject.jetty.util.component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.util.IO;
import org.sparkproject.jetty.util.resource.Resource;

public class FileDestroyable implements Destroyable {
   private static final Logger LOG = LoggerFactory.getLogger(FileDestroyable.class);
   final List _files = new ArrayList();

   public FileDestroyable() {
   }

   public FileDestroyable(String file) throws IOException {
      this._files.add(Resource.newResource(file).getFile());
   }

   public FileDestroyable(File file) {
      this._files.add(file);
   }

   public void addFile(String file) throws IOException {
      Resource r = Resource.newResource(file);

      try {
         this._files.add(r.getFile());
      } catch (Throwable var6) {
         if (r != null) {
            try {
               r.close();
            } catch (Throwable var5) {
               var6.addSuppressed(var5);
            }
         }

         throw var6;
      }

      if (r != null) {
         r.close();
      }

   }

   public void addFile(File file) {
      this._files.add(file);
   }

   public void addFiles(Collection files) {
      this._files.addAll(files);
   }

   public void removeFile(String file) throws IOException {
      Resource r = Resource.newResource(file);

      try {
         this._files.remove(r.getFile());
      } catch (Throwable var6) {
         if (r != null) {
            try {
               r.close();
            } catch (Throwable var5) {
               var6.addSuppressed(var5);
            }
         }

         throw var6;
      }

      if (r != null) {
         r.close();
      }

   }

   public void removeFile(File file) {
      this._files.remove(file);
   }

   public void destroy() {
      for(File file : this._files) {
         if (file.exists()) {
            if (LOG.isDebugEnabled()) {
               LOG.debug("Destroy {}", file);
            }

            IO.delete(file);
         }
      }

   }
}
