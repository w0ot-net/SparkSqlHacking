package org.apache.ivy.plugins.repository.url;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import org.apache.ivy.core.settings.TimeoutConstraint;
import org.apache.ivy.plugins.repository.LocalizableResource;
import org.apache.ivy.plugins.repository.Resource;
import org.apache.ivy.util.url.TimeoutConstrainedURLHandler;
import org.apache.ivy.util.url.URLHandler;
import org.apache.ivy.util.url.URLHandlerRegistry;

public class URLResource implements LocalizableResource {
   private final URL url;
   private final TimeoutConstraint timeoutConstraint;
   private boolean init;
   private long lastModified;
   private long contentLength;
   private boolean exists;

   public URLResource(URL url) {
      this(url, (TimeoutConstraint)null);
   }

   public URLResource(URL url, TimeoutConstraint timeoutConstraint) {
      this.init = false;
      this.url = url;
      this.timeoutConstraint = timeoutConstraint;
   }

   public String getName() {
      return this.url.toExternalForm();
   }

   public Resource clone(String cloneName) {
      try {
         return new URLResource(new URL(cloneName));
      } catch (MalformedURLException var3) {
         throw new IllegalArgumentException("bad clone name provided: not suitable for an URLResource: " + cloneName);
      }
   }

   public long getLastModified() {
      if (!this.init) {
         this.init();
      }

      return this.lastModified;
   }

   private void init() {
      URLHandler handler = URLHandlerRegistry.getDefault();
      URLHandler.URLInfo info;
      if (handler instanceof TimeoutConstrainedURLHandler) {
         info = ((TimeoutConstrainedURLHandler)handler).getURLInfo(this.url, this.timeoutConstraint);
      } else {
         info = handler.getURLInfo(this.url);
      }

      this.contentLength = info.getContentLength();
      this.lastModified = info.getLastModified();
      this.exists = info.isReachable();
      this.init = true;
   }

   public long getContentLength() {
      if (!this.init) {
         this.init();
      }

      return this.contentLength;
   }

   public boolean exists() {
      if (!this.init) {
         this.init();
      }

      return this.exists;
   }

   public URL getURL() {
      return this.url;
   }

   public String toString() {
      return this.getName();
   }

   public boolean isLocal() {
      return this.url.getProtocol().equals("file");
   }

   public InputStream openStream() throws IOException {
      URLHandler handler = URLHandlerRegistry.getDefault();
      return handler instanceof TimeoutConstrainedURLHandler ? ((TimeoutConstrainedURLHandler)handler).openStream(this.url, this.timeoutConstraint) : handler.openStream(this.url);
   }

   public File getFile() {
      if (!this.isLocal()) {
         throw new IllegalStateException("Cannot get the local file for the not local resource " + this.url);
      } else {
         try {
            return new File(this.url.toURI());
         } catch (URISyntaxException var2) {
            return new File(this.url.getPath());
         }
      }
   }
}
