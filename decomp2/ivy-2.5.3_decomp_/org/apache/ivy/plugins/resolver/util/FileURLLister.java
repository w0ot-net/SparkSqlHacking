package org.apache.ivy.plugins.resolver.util;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileURLLister implements URLLister {
   private File basedir;

   public FileURLLister() {
      this((File)null);
   }

   public FileURLLister(File baseDir) {
      this.basedir = baseDir;
   }

   public boolean accept(String pattern) {
      return pattern.startsWith("file");
   }

   public List listAll(URL url) throws IOException {
      String path;
      try {
         path = (new File(new URI(url.toExternalForm()))).getPath();
      } catch (URISyntaxException var11) {
         path = url.getPath();
      }

      File file = this.basedir == null ? new File(path) : new File(this.basedir, path);
      if (file.exists() && file.isDirectory()) {
         String[] files = file.list();
         List<URL> ret = new ArrayList(files.length);
         URL context = url.getPath().endsWith("/") ? url : new URL(url.toExternalForm() + "/");

         for(String fileName : files) {
            ret.add(new URL(context, fileName));
         }

         return ret;
      } else {
         return Collections.emptyList();
      }
   }

   public String toString() {
      return "file lister";
   }
}
