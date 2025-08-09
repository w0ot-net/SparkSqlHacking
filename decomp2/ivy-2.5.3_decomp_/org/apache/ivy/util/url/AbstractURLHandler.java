package org.apache.ivy.util.url;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.regex.Pattern;
import java.util.zip.DataFormatException;
import java.util.zip.GZIPInputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;
import org.apache.ivy.Ivy;
import org.apache.ivy.core.settings.TimeoutConstraint;

public abstract class AbstractURLHandler implements URLHandler {
   private static final Pattern ESCAPE_PATTERN = Pattern.compile("%25([0-9a-fA-F][0-9a-fA-F])");
   private static int requestMethod = 2;

   public boolean isReachable(URL url) {
      return this.getURLInfo(url).isReachable();
   }

   public boolean isReachable(URL url, int timeout) {
      return this.getURLInfo(url, timeout).isReachable();
   }

   public long getContentLength(URL url) {
      return this.getURLInfo(url).getContentLength();
   }

   public long getContentLength(URL url, int timeout) {
      return this.getURLInfo(url, timeout).getContentLength();
   }

   public long getLastModified(URL url) {
      return this.getURLInfo(url).getLastModified();
   }

   public long getLastModified(URL url, int timeout) {
      return this.getURLInfo(url, timeout).getLastModified();
   }

   protected String getUserAgent() {
      return System.getProperty("http.agent", "Apache Ivy/" + Ivy.getIvyVersion());
   }

   protected void validatePutStatusCode(URL dest, int statusCode, String statusMessage) throws IOException {
      switch (statusCode) {
         case 200:
         case 201:
         case 202:
         case 204:
            return;
         case 401:
         case 403:
            throw new IOException("Access to URL " + dest + " was refused by the server" + (statusMessage == null ? "" : ": " + statusMessage));
         default:
            throw new IOException("PUT operation to URL " + dest + " failed with status code " + statusCode + (statusMessage == null ? "" : ": " + statusMessage));
      }
   }

   public void setRequestMethod(int requestMethod) {
      AbstractURLHandler.requestMethod = requestMethod;
   }

   public int getRequestMethod() {
      return requestMethod;
   }

   protected String normalizeToString(URL url) throws IOException {
      if (!"http".equals(url.getProtocol()) && !"https".equals(url.getProtocol())) {
         return url.toExternalForm();
      } else {
         try {
            URI uri = new URI(url.getProtocol(), url.getAuthority(), url.getPath(), url.getQuery(), url.getRef());
            String uriString = uri.normalize().toASCIIString();
            uriString = uriString.replaceAll("\\+", "%2B");
            return ESCAPE_PATTERN.matcher(uriString).replaceAll("%$1");
         } catch (URISyntaxException e) {
            IOException ioe = new MalformedURLException("Couldn't convert '" + url.toString() + "' to a valid URI");
            ioe.initCause(e);
            throw ioe;
         }
      }
   }

   protected URL normalizeToURL(URL url) throws IOException {
      return !"http".equals(url.getProtocol()) && !"https".equals(url.getProtocol()) ? url : new URL(this.normalizeToString(url));
   }

   protected InputStream getDecodingInputStream(String encoding, InputStream in) throws IOException {
      if (encoding == null) {
         return in;
      } else {
         InputStream result = null;
         switch (encoding) {
            case "deflate":
               BufferedInputStream bStream = new BufferedInputStream(in);
               bStream.mark(100);
               byte[] bytes = new byte[100];
               int nbBytes = bStream.read(bytes);
               bStream.reset();
               Inflater inflater = new Inflater();
               inflater.setInput(bytes, 0, nbBytes);

               try {
                  try {
                     inflater.inflate(new byte[1000]);
                     result = new InflaterInputStream(bStream);
                  } catch (DataFormatException var14) {
                     result = new InflaterInputStream(bStream, new Inflater(true));
                  }
                  break;
               } finally {
                  inflater.end();
               }
            case "gzip":
            case "x-gzip":
               result = new GZIPInputStream(in);
               break;
            default:
               result = in;
         }

         return result;
      }
   }

   protected static TimeoutConstraint createTimeoutConstraints(final int connectionTimeout) {
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
