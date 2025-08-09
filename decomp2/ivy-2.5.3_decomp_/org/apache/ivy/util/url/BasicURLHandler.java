package org.apache.ivy.util.url;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import org.apache.ivy.core.settings.TimeoutConstraint;
import org.apache.ivy.util.CopyProgressListener;
import org.apache.ivy.util.FileUtil;
import org.apache.ivy.util.Message;

public class BasicURLHandler extends AbstractURLHandler implements TimeoutConstrainedURLHandler {
   private static final int BUFFER_SIZE = 65536;
   private static final String ACCEPT_HEADER_VALUE = "*/*";

   public URLHandler.URLInfo getURLInfo(URL url) {
      return this.getURLInfo(url, (TimeoutConstraint)null);
   }

   public URLHandler.URLInfo getURLInfo(URL url, int timeout) {
      return this.getURLInfo(url, createTimeoutConstraints(timeout));
   }

   public boolean isReachable(URL url, TimeoutConstraint timeoutConstraint) {
      return this.getURLInfo(url, timeoutConstraint).isReachable();
   }

   public long getContentLength(URL url, TimeoutConstraint timeoutConstraint) {
      return this.getURLInfo(url, timeoutConstraint).getContentLength();
   }

   public long getLastModified(URL url, TimeoutConstraint timeoutConstraint) {
      return this.getURLInfo(url, timeoutConstraint).getLastModified();
   }

   public URLHandler.URLInfo getURLInfo(URL url, TimeoutConstraint timeoutConstraint) {
      if ("http".equals(url.getProtocol()) || "https".equals(url.getProtocol())) {
         IvyAuthenticator.install();
      }

      int connectionTimeout = timeoutConstraint != null && timeoutConstraint.getConnectionTimeout() >= 0 ? timeoutConstraint.getConnectionTimeout() : 0;
      int readTimeout = timeoutConstraint != null && timeoutConstraint.getReadTimeout() >= 0 ? timeoutConstraint.getReadTimeout() : 0;
      URLConnection con = null;

      URLHandler.URLInfo var9;
      try {
         URL normalizedURL = this.normalizeToURL(url);
         con = normalizedURL.openConnection();
         con.setConnectTimeout(connectionTimeout);
         con.setReadTimeout(readTimeout);
         con.setRequestProperty("User-Agent", this.getUserAgent());
         con.setRequestProperty("Accept", "*/*");
         if (!(con instanceof HttpURLConnection)) {
            int contentLength = con.getContentLength();
            if (contentLength <= 0) {
               URLHandler.URLInfo var19 = UNAVAILABLE;
               return var19;
            }

            String bodyCharset = getCharSetFromContentType(con.getContentType());
            var9 = new URLHandler.URLInfo(true, (long)contentLength, con.getLastModified(), bodyCharset);
            return var9;
         }

         HttpURLConnection httpCon = (HttpURLConnection)con;
         if (this.getRequestMethod() == 2) {
            httpCon.setRequestMethod("HEAD");
         }

         if (!this.checkStatusCode(normalizedURL, httpCon)) {
            return UNAVAILABLE;
         }

         String bodyCharset = getCharSetFromContentType(con.getContentType());
         var9 = new URLHandler.URLInfo(true, (long)httpCon.getContentLength(), con.getLastModified(), bodyCharset);
      } catch (UnknownHostException e) {
         Message.warn("Host " + e.getMessage() + " not found. url=" + url);
         Message.info("You probably access the destination server through a proxy server that is not well configured.");
         return UNAVAILABLE;
      } catch (IOException e) {
         Message.error("Server access error at url " + url, e);
         return UNAVAILABLE;
      } finally {
         this.disconnect(con);
      }

      return var9;
   }

   public static String getCharSetFromContentType(String contentType) {
      String charSet = null;
      if (contentType != null) {
         for(String el : contentType.split(";")) {
            String element = el.trim();
            if (element.toLowerCase().startsWith("charset=")) {
               charSet = element.substring("charset=".length());
            }
         }
      }

      if (charSet == null || charSet.length() == 0) {
         charSet = "ISO-8859-1";
      }

      return charSet;
   }

   private boolean checkStatusCode(URL url, HttpURLConnection con) throws IOException {
      int status = con.getResponseCode();
      if (status == 200) {
         return true;
      } else if ("HEAD".equals(con.getRequestMethod()) && status == 204) {
         return true;
      } else {
         Message.debug("HTTP response status: " + status + " url=" + url);
         if (status == 407) {
            Message.warn("Your proxy requires authentication.");
         } else if (String.valueOf(status).startsWith("4")) {
            Message.verbose("CLIENT ERROR: " + con.getResponseMessage() + " url=" + url);
         } else if (String.valueOf(status).startsWith("5")) {
            Message.error("SERVER ERROR: " + con.getResponseMessage() + " url=" + url);
         }

         return false;
      }
   }

   public InputStream openStream(URL url) throws IOException {
      return this.openStream(url, (TimeoutConstraint)null);
   }

   public InputStream openStream(URL url, TimeoutConstraint timeoutConstraint) throws IOException {
      if ("http".equals(url.getProtocol()) || "https".equals(url.getProtocol())) {
         IvyAuthenticator.install();
      }

      int connectionTimeout = timeoutConstraint != null && timeoutConstraint.getConnectionTimeout() >= 0 ? timeoutConstraint.getConnectionTimeout() : 0;
      int readTimeout = timeoutConstraint != null && timeoutConstraint.getReadTimeout() >= 0 ? timeoutConstraint.getReadTimeout() : 0;
      URLConnection conn = null;

      ByteArrayInputStream var11;
      try {
         URL normalizedURL = this.normalizeToURL(url);
         conn = normalizedURL.openConnection();
         conn.setConnectTimeout(connectionTimeout);
         conn.setReadTimeout(readTimeout);
         conn.setRequestProperty("User-Agent", this.getUserAgent());
         conn.setRequestProperty("Accept", "*/*");
         conn.setRequestProperty("Accept-Encoding", "gzip,deflate");
         if (conn instanceof HttpURLConnection) {
            HttpURLConnection httpCon = (HttpURLConnection)conn;
            if (!this.checkStatusCode(normalizedURL, httpCon)) {
               throw new IOException("The HTTP response code for " + normalizedURL + " did not indicate a success. See log for more detail.");
            }
         }

         InputStream inStream = this.getDecodingInputStream(conn.getContentEncoding(), conn.getInputStream());
         ByteArrayOutputStream outStream = new ByteArrayOutputStream();
         byte[] buffer = new byte[65536];

         int len;
         while((len = inStream.read(buffer)) > 0) {
            outStream.write(buffer, 0, len);
         }

         var11 = new ByteArrayInputStream(outStream.toByteArray());
      } finally {
         this.disconnect(conn);
      }

      return var11;
   }

   public void download(URL src, File dest, CopyProgressListener l) throws IOException {
      this.download(src, dest, l, (TimeoutConstraint)null);
   }

   public void download(URL src, File dest, CopyProgressListener listener, TimeoutConstraint timeoutConstraint) throws IOException {
      if ("http".equals(src.getProtocol()) || "https".equals(src.getProtocol())) {
         IvyAuthenticator.install();
      }

      int connectionTimeout = timeoutConstraint != null && timeoutConstraint.getConnectionTimeout() >= 0 ? timeoutConstraint.getConnectionTimeout() : 0;
      int readTimeout = timeoutConstraint != null && timeoutConstraint.getReadTimeout() >= 0 ? timeoutConstraint.getReadTimeout() : 0;
      URLConnection srcConn = null;

      try {
         URL normalizedURL = this.normalizeToURL(src);
         srcConn = normalizedURL.openConnection();
         srcConn.setConnectTimeout(connectionTimeout);
         srcConn.setReadTimeout(readTimeout);
         srcConn.setRequestProperty("User-Agent", this.getUserAgent());
         srcConn.setRequestProperty("Accept", "*/*");
         srcConn.setRequestProperty("Accept-Encoding", "gzip,deflate");
         if (srcConn instanceof HttpURLConnection) {
            HttpURLConnection httpCon = (HttpURLConnection)srcConn;
            if (!this.checkStatusCode(normalizedURL, httpCon)) {
               throw new IOException("The HTTP response code for " + normalizedURL + " did not indicate a success. See log for more detail.");
            }
         }

         InputStream inStream = this.getDecodingInputStream(srcConn.getContentEncoding(), srcConn.getInputStream());
         FileUtil.copy(inStream, dest, listener);
         if (srcConn.getContentEncoding() == null) {
            int contentLength = srcConn.getContentLength();
            long destFileSize = dest.length();
            if (contentLength != -1 && destFileSize != (long)contentLength) {
               dest.delete();
               throw new IOException("Downloaded file size (" + destFileSize + ") doesn't match expected Content Length (" + contentLength + ") for " + normalizedURL + ". Please retry.");
            }
         }

         long lastModified = srcConn.getLastModified();
         if (lastModified > 0L) {
            dest.setLastModified(lastModified);
         }
      } finally {
         this.disconnect(srcConn);
      }

   }

   public void upload(File source, URL dest, CopyProgressListener l) throws IOException {
      this.upload(source, dest, l, (TimeoutConstraint)null);
   }

   public void upload(File src, URL dest, CopyProgressListener listener, TimeoutConstraint timeoutConstraint) throws IOException {
      if (!"http".equals(dest.getProtocol()) && !"https".equals(dest.getProtocol())) {
         throw new UnsupportedOperationException("URL repository only support HTTP PUT at the moment");
      } else {
         IvyAuthenticator.install();
         int connectionTimeout = timeoutConstraint != null && timeoutConstraint.getConnectionTimeout() >= 0 ? timeoutConstraint.getConnectionTimeout() : 0;
         HttpURLConnection conn = null;

         try {
            URL normalizedDestURL = this.normalizeToURL(dest);
            conn = (HttpURLConnection)normalizedDestURL.openConnection();
            conn.setDoOutput(true);
            conn.setConnectTimeout(connectionTimeout);
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("User-Agent", this.getUserAgent());
            conn.setRequestProperty("Content-type", "application/octet-stream");
            conn.setRequestProperty("Content-length", Long.toString(src.length()));
            conn.setInstanceFollowRedirects(true);
            InputStream in = new FileInputStream(src);
            Throwable var9 = null;

            try {
               OutputStream os = conn.getOutputStream();
               FileUtil.copy(in, os, listener);
            } catch (Throwable var25) {
               var9 = var25;
               throw var25;
            } finally {
               if (in != null) {
                  if (var9 != null) {
                     try {
                        in.close();
                     } catch (Throwable var24) {
                        var9.addSuppressed(var24);
                     }
                  } else {
                     in.close();
                  }
               }

            }

            this.validatePutStatusCode(normalizedDestURL, conn.getResponseCode(), conn.getResponseMessage());
         } finally {
            this.disconnect(conn);
         }

      }
   }

   private void disconnect(URLConnection con) {
      if (con instanceof HttpURLConnection) {
         if (!"HEAD".equals(((HttpURLConnection)con).getRequestMethod())) {
            this.readResponseBody((HttpURLConnection)con);
         }

         ((HttpURLConnection)con).disconnect();
      } else if (con != null) {
         try {
            con.getInputStream().close();
         } catch (IOException var3) {
         }
      }

   }

   private void readResponseBody(HttpURLConnection conn) {
      byte[] buffer = new byte[65536];

      try {
         InputStream inStream = conn.getInputStream();
         Throwable var4 = null;

         try {
            while(inStream.read(buffer) > 0) {
            }
         } catch (Throwable var32) {
            var4 = var32;
            throw var32;
         } finally {
            if (inStream != null) {
               if (var4 != null) {
                  try {
                     inStream.close();
                  } catch (Throwable var29) {
                     var4.addSuppressed(var29);
                  }
               } else {
                  inStream.close();
               }
            }

         }
      } catch (IOException var34) {
      }

      InputStream errStream = conn.getErrorStream();
      if (errStream != null) {
         try {
            while(errStream.read(buffer) > 0) {
            }
         } catch (IOException var30) {
         } finally {
            try {
               errStream.close();
            } catch (IOException var28) {
            }

         }
      }

   }

   private static final class HttpStatus {
      static final int SC_OK = 200;
      static final int SC_PROXY_AUTHENTICATION_REQUIRED = 407;
   }
}
