package org.apache.ivy.plugins.repository;

import java.io.File;
import java.net.MalformedURLException;
import org.apache.ivy.plugins.repository.file.FileResource;
import org.apache.ivy.plugins.repository.url.URLResource;
import org.apache.ivy.util.Message;

public final class ResourceHelper {
   private ResourceHelper() {
   }

   public static boolean equals(Resource res, File f) {
      if (res == null && f == null) {
         return true;
      } else if (res != null && f != null) {
         if (res instanceof FileResource) {
            return (new File(res.getName())).equals(f);
         } else if (res instanceof URLResource) {
            try {
               return f.toURI().toURL().toExternalForm().equals(res.getName());
            } catch (MalformedURLException var3) {
               return false;
            }
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   public static long getLastModifiedOrDefault(Resource res) {
      long last = res.getLastModified();
      if (last > 0L) {
         return last;
      } else {
         Message.debug("impossible to get date for " + res + ": using 'now'");
         return System.currentTimeMillis();
      }
   }
}
