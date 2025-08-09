package org.apache.ivy.util.url;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import org.apache.ivy.util.CopyProgressListener;

/** @deprecated */
@Deprecated
public interface URLHandler {
   int REQUEST_METHOD_GET = 1;
   int REQUEST_METHOD_HEAD = 2;
   URLInfo UNAVAILABLE = new URLInfo(false, 0L, 0L);

   boolean isReachable(URL var1);

   boolean isReachable(URL var1, int var2);

   long getContentLength(URL var1);

   long getContentLength(URL var1, int var2);

   long getLastModified(URL var1);

   long getLastModified(URL var1, int var2);

   URLInfo getURLInfo(URL var1);

   URLInfo getURLInfo(URL var1, int var2);

   InputStream openStream(URL var1) throws IOException;

   void download(URL var1, File var2, CopyProgressListener var3) throws IOException;

   void upload(File var1, URL var2, CopyProgressListener var3) throws IOException;

   void setRequestMethod(int var1);

   public static class URLInfo {
      private long contentLength;
      private long lastModified;
      private boolean available;
      private String bodyCharset;

      protected URLInfo(boolean available, long contentLength, long lastModified) {
         this(available, contentLength, lastModified, (String)null);
      }

      protected URLInfo(boolean available, long contentLength, long lastModified, String bodyCharset) {
         this.available = available;
         this.contentLength = contentLength;
         this.lastModified = lastModified;
         this.bodyCharset = bodyCharset;
      }

      public boolean isReachable() {
         return this.available;
      }

      public long getContentLength() {
         return this.contentLength;
      }

      public long getLastModified() {
         return this.lastModified;
      }

      public String getBodyCharset() {
         return this.bodyCharset;
      }
   }
}
