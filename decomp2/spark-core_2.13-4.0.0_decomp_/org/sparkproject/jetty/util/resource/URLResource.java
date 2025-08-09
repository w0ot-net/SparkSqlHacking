package org.sparkproject.jetty.util.resource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.ReadableByteChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.util.URIUtil;
import org.sparkproject.jetty.util.thread.AutoLock;

public class URLResource extends Resource {
   private static final Logger LOG = LoggerFactory.getLogger(URLResource.class);
   protected final AutoLock _lock;
   protected final URL _url;
   protected final String _urlString;
   protected URLConnection _connection;
   protected InputStream _in;
   transient boolean _useCaches;

   protected URLResource(URL url, URLConnection connection) {
      this._lock = new AutoLock();
      this._in = null;
      this._useCaches = Resource.__defaultUseCaches;
      this._url = url;
      this._urlString = this._url.toExternalForm();
      this._connection = connection;
   }

   protected URLResource(URL url, URLConnection connection, boolean useCaches) {
      this(url, connection);
      this._useCaches = useCaches;
   }

   protected boolean checkConnection() {
      try (AutoLock l = this._lock.lock()) {
         if (this._connection == null) {
            try {
               this._connection = this._url.openConnection();
               this._connection.setUseCaches(this._useCaches);
            } catch (IOException e) {
               LOG.trace("IGNORED", e);
            }
         }

         return this._connection != null;
      }
   }

   public void close() {
      try (AutoLock l = this._lock.lock()) {
         if (this._in != null) {
            try {
               this._in.close();
            } catch (IOException e) {
               LOG.trace("IGNORED", e);
            }

            this._in = null;
         }

         if (this._connection != null) {
            this._connection = null;
         }
      }

   }

   public boolean exists() {
      try (AutoLock l = this._lock.lock()) {
         if (this.checkConnection() && this._in == null) {
            this._in = this._connection.getInputStream();
         }
      } catch (IOException e) {
         LOG.trace("IGNORED", e);
      }

      return this._in != null;
   }

   public boolean isDirectory() {
      return this.exists() && this._urlString.endsWith("/");
   }

   public long lastModified() {
      return this.checkConnection() ? this._connection.getLastModified() : -1L;
   }

   public long length() {
      return this.checkConnection() ? (long)this._connection.getContentLength() : -1L;
   }

   public URI getURI() {
      try {
         return this._url.toURI();
      } catch (URISyntaxException e) {
         throw new RuntimeException(e);
      }
   }

   public File getFile() throws IOException {
      return null;
   }

   public String getName() {
      return this._url.toExternalForm();
   }

   public InputStream getInputStream() throws IOException {
      return this.getInputStream(true);
   }

   protected InputStream getInputStream(boolean resetConnection) throws IOException {
      try (AutoLock l = this._lock.lock()) {
         if (!this.checkConnection()) {
            throw new IOException("Invalid resource");
         } else {
            try {
               if (this._in != null) {
                  InputStream in = this._in;
                  this._in = null;
                  return in;
               } else {
                  return this._connection.getInputStream();
               }
            } finally {
               if (resetConnection) {
                  this._connection = null;
                  if (LOG.isDebugEnabled()) {
                     LOG.debug("Connection nulled");
                  }
               }

            }
         }
      }
   }

   public ReadableByteChannel getReadableByteChannel() throws IOException {
      return null;
   }

   public boolean delete() throws SecurityException {
      throw new SecurityException("Delete not supported");
   }

   public boolean renameTo(Resource dest) throws SecurityException {
      throw new SecurityException("RenameTo not supported");
   }

   public String[] list() {
      return null;
   }

   public Resource addPath(String path) throws IOException {
      if (URIUtil.canonicalPath(path) == null) {
         throw new MalformedURLException(path);
      } else {
         return newResource(URIUtil.addEncodedPaths(this._url.toExternalForm(), URIUtil.encodePath(path)), this._useCaches);
      }
   }

   public String toString() {
      return this._urlString;
   }

   public int hashCode() {
      return this._urlString.hashCode();
   }

   public boolean equals(Object o) {
      return o instanceof URLResource && this._urlString.equals(((URLResource)o)._urlString);
   }

   public boolean getUseCaches() {
      return this._useCaches;
   }

   public boolean isContainedIn(Resource containingResource) throws MalformedURLException {
      return false;
   }
}
