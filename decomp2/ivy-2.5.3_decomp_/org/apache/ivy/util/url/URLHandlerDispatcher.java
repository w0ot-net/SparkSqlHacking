package org.apache.ivy.util.url;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import org.apache.ivy.core.settings.TimeoutConstraint;
import org.apache.ivy.util.CopyProgressListener;

public class URLHandlerDispatcher implements TimeoutConstrainedURLHandler {
   private final Map handlers = new HashMap();
   private URLHandler defaultHandler = new BasicURLHandler();

   public boolean isReachable(URL url) {
      return this.isReachable(url, (TimeoutConstraint)null);
   }

   public boolean isReachable(URL url, int timeout) {
      return this.isReachable(url, createTimeoutConstraints(timeout));
   }

   public boolean isReachable(URL url, TimeoutConstraint timeoutConstraint) {
      URLHandler handler = this.getHandler(url.getProtocol());
      return handler instanceof TimeoutConstrainedURLHandler ? ((TimeoutConstrainedURLHandler)handler).isReachable(url, timeoutConstraint) : handler.isReachable(url, timeoutConstraint != null ? timeoutConstraint.getConnectionTimeout() : 0);
   }

   public long getContentLength(URL url) {
      return this.getContentLength(url, (TimeoutConstraint)null);
   }

   public long getContentLength(URL url, int timeout) {
      return this.getContentLength(url, createTimeoutConstraints(timeout));
   }

   public long getContentLength(URL url, TimeoutConstraint timeoutConstraint) {
      URLHandler handler = this.getHandler(url.getProtocol());
      return handler instanceof TimeoutConstrainedURLHandler ? ((TimeoutConstrainedURLHandler)handler).getContentLength(url, timeoutConstraint) : handler.getContentLength(url, timeoutConstraint != null ? timeoutConstraint.getConnectionTimeout() : 0);
   }

   public long getLastModified(URL url) {
      return this.getLastModified(url, (TimeoutConstraint)null);
   }

   public long getLastModified(URL url, int timeout) {
      return this.getLastModified(url, createTimeoutConstraints(timeout));
   }

   public long getLastModified(URL url, TimeoutConstraint timeoutConstraint) {
      URLHandler handler = this.getHandler(url.getProtocol());
      return handler instanceof TimeoutConstrainedURLHandler ? ((TimeoutConstrainedURLHandler)handler).getLastModified(url, timeoutConstraint) : handler.getLastModified(url, timeoutConstraint != null ? timeoutConstraint.getConnectionTimeout() : 0);
   }

   public URLHandler.URLInfo getURLInfo(URL url) {
      return this.getURLInfo(url, (TimeoutConstraint)null);
   }

   public URLHandler.URLInfo getURLInfo(URL url, int timeout) {
      return this.getURLInfo(url, createTimeoutConstraints(timeout));
   }

   public URLHandler.URLInfo getURLInfo(URL url, TimeoutConstraint timeoutConstraint) {
      URLHandler handler = this.getHandler(url.getProtocol());
      return handler instanceof TimeoutConstrainedURLHandler ? ((TimeoutConstrainedURLHandler)handler).getURLInfo(url, timeoutConstraint) : handler.getURLInfo(url, timeoutConstraint != null ? timeoutConstraint.getConnectionTimeout() : 0);
   }

   public InputStream openStream(URL url) throws IOException {
      return this.openStream(url, (TimeoutConstraint)null);
   }

   public InputStream openStream(URL url, TimeoutConstraint timeoutConstraint) throws IOException {
      URLHandler handler = this.getHandler(url.getProtocol());
      return handler instanceof TimeoutConstrainedURLHandler ? ((TimeoutConstrainedURLHandler)handler).openStream(url, timeoutConstraint) : handler.openStream(url);
   }

   public void download(URL src, File dest, CopyProgressListener l) throws IOException {
      this.download(src, dest, l, (TimeoutConstraint)null);
   }

   public void download(URL src, File dest, CopyProgressListener listener, TimeoutConstraint timeoutConstraint) throws IOException {
      URLHandler handler = this.getHandler(src.getProtocol());
      if (handler instanceof TimeoutConstrainedURLHandler) {
         ((TimeoutConstrainedURLHandler)handler).download(src, dest, listener, timeoutConstraint);
      } else {
         handler.download(src, dest, listener);
      }
   }

   public void upload(File src, URL dest, CopyProgressListener l) throws IOException {
      this.upload(src, dest, l, (TimeoutConstraint)null);
   }

   public void upload(File src, URL dest, CopyProgressListener listener, TimeoutConstraint timeoutConstraint) throws IOException {
      URLHandler handler = this.getHandler(dest.getProtocol());
      if (handler instanceof TimeoutConstrainedURLHandler) {
         ((TimeoutConstrainedURLHandler)handler).upload(src, dest, listener, timeoutConstraint);
      } else {
         handler.upload(src, dest, listener);
      }
   }

   public void setRequestMethod(int requestMethod) {
      this.defaultHandler.setRequestMethod(requestMethod);

      for(URLHandler handler : this.handlers.values()) {
         handler.setRequestMethod(requestMethod);
      }

   }

   public void setDownloader(String protocol, URLHandler downloader) {
      this.handlers.put(protocol, downloader);
   }

   public URLHandler getHandler(String protocol) {
      URLHandler downloader = (URLHandler)this.handlers.get(protocol);
      return downloader == null ? this.defaultHandler : downloader;
   }

   public URLHandler getDefault() {
      return this.defaultHandler;
   }

   public void setDefault(URLHandler default1) {
      this.defaultHandler = default1;
   }

   private static TimeoutConstraint createTimeoutConstraints(final int connectionTimeout) {
      return new TimeoutConstraint() {
         public int getConnectionTimeout() {
            return connectionTimeout;
         }

         public int getReadTimeout() {
            return -1;
         }
      };
   }
}
