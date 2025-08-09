package org.sparkproject.jetty.util.resource;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

class BadResource extends URLResource {
   private String _message = null;

   BadResource(URL url, String message) {
      super(url, (URLConnection)null);
      this._message = message;
   }

   public boolean exists() {
      return false;
   }

   public long lastModified() {
      return -1L;
   }

   public boolean isDirectory() {
      return false;
   }

   public long length() {
      return -1L;
   }

   public File getFile() {
      return null;
   }

   public InputStream getInputStream() throws IOException {
      throw new FileNotFoundException(this._message);
   }

   public boolean delete() throws SecurityException {
      throw new SecurityException(this._message);
   }

   public boolean renameTo(Resource dest) throws SecurityException {
      throw new SecurityException(this._message);
   }

   public String[] list() {
      return null;
   }

   public void copyTo(File destination) throws IOException {
      throw new SecurityException(this._message);
   }

   public String toString() {
      String var10000 = super.toString();
      return var10000 + "; BadResource=" + this._message;
   }
}
