package org.apache.ivy.core;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public abstract class RelativeUrlResolver {
   public abstract URL getURL(URL var1, String var2) throws MalformedURLException;

   public URL getURL(URL context, String file, String url) throws MalformedURLException {
      if (file != null) {
         File f = new File(file);
         return f.isAbsolute() ? f.toURI().toURL() : this.getURL(context, file);
      } else {
         return url != null ? this.getURL(context, url) : null;
      }
   }
}
