package org.sparkproject.jetty.util.resource;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.util.URIUtil;
import org.sparkproject.jetty.util.thread.AutoLock;

public class JarFileResource extends JarResource {
   private static final Logger LOG = LoggerFactory.getLogger(JarFileResource.class);
   private JarFile _jarFile;
   private File _file;
   private String[] _list;
   private JarEntry _entry;
   private boolean _directory;
   private String _jarUrl;
   private String _path;
   private boolean _exists;

   protected JarFileResource(URL url, boolean useCaches) {
      super(url, useCaches);
   }

   public void close() {
      try (AutoLock l = this._lock.lock()) {
         this._exists = false;
         this._list = null;
         this._entry = null;
         this._file = null;
         if (!this.getUseCaches() && this._jarFile != null) {
            try {
               if (LOG.isDebugEnabled()) {
                  LOG.debug("Closing JarFile {}", this._jarFile.getName());
               }

               this._jarFile.close();
            } catch (IOException ioe) {
               LOG.trace("IGNORED", ioe);
            }
         }

         this._jarFile = null;
         super.close();
      }

   }

   protected boolean checkConnection() {
      try (AutoLock l = this._lock.lock()) {
         try {
            super.checkConnection();
         } finally {
            if (this._jarConnection == null) {
               this._entry = null;
               this._file = null;
               this._jarFile = null;
               this._list = null;
            }

         }

         return this._jarFile != null;
      }
   }

   protected void newConnection() throws IOException {
      try (AutoLock l = this._lock.lock()) {
         super.newConnection();
         this._entry = null;
         this._file = null;
         this._jarFile = null;
         this._list = null;
         int sep = this._urlString.lastIndexOf("!/");
         this._jarUrl = this._urlString.substring(0, sep + 2);
         this._path = URIUtil.decodePath(this._urlString.substring(sep + 2));
         if (this._path.length() == 0) {
            this._path = null;
         }

         this._jarFile = this._jarConnection.getJarFile();
         this._file = new File(this._jarFile.getName());
      }

   }

   public boolean exists() {
      if (this._exists) {
         return true;
      } else if (this._urlString.endsWith("!/")) {
         String fileUrl = this._urlString.substring(4, this._urlString.length() - 2);

         try {
            return this._directory = newResource(fileUrl).exists();
         } catch (Exception e) {
            LOG.trace("IGNORED", e);
            return false;
         }
      } else {
         boolean check = this.checkConnection();
         if (this._jarUrl != null && this._path == null) {
            this._directory = check;
            return true;
         } else {
            boolean closeJarFile = false;
            JarFile jarFile = null;
            if (check) {
               jarFile = this._jarFile;
            } else {
               try {
                  JarURLConnection c = (JarURLConnection)(new URL(this._jarUrl)).openConnection();
                  c.setUseCaches(this.getUseCaches());
                  jarFile = c.getJarFile();
                  closeJarFile = !this.getUseCaches();
               } catch (Exception e) {
                  LOG.trace("IGNORED", e);
               }
            }

            if (jarFile != null && this._entry == null && !this._directory) {
               JarEntry entry = jarFile.getJarEntry(this._path);
               if (entry == null) {
                  this._exists = false;
               } else if (entry.isDirectory()) {
                  this._directory = true;
                  this._entry = entry;
               } else {
                  JarEntry directory = jarFile.getJarEntry(this._path + "/");
                  if (directory != null) {
                     this._directory = true;
                     this._entry = directory;
                  } else {
                     this._directory = false;
                     this._entry = entry;
                  }
               }
            }

            if (closeJarFile && jarFile != null) {
               try {
                  jarFile.close();
               } catch (IOException ioe) {
                  LOG.trace("IGNORED", ioe);
               }
            }

            this._exists = this._directory || this._entry != null;
            return this._exists;
         }
      }
   }

   public boolean isDirectory() {
      return this.exists() && this._directory;
   }

   public long lastModified() {
      if (this.checkConnection() && this._file != null) {
         return this.exists() && this._entry != null ? this._entry.getTime() : this._file.lastModified();
      } else {
         return -1L;
      }
   }

   public String[] list() {
      try (AutoLock l = this._lock.lock()) {
         if (this.isDirectory() && this._list == null) {
            List<String> list = null;

            try {
               list = this.listEntries();
            } catch (Exception e) {
               if (LOG.isDebugEnabled()) {
                  LOG.warn("JarFile list failure", e);
               } else {
                  LOG.warn("JarFile list failure {}", e.toString());
               }

               this.close();
               list = this.listEntries();
            }

            if (list != null) {
               this._list = new String[list.size()];
               list.toArray(this._list);
            }
         }

         return this._list;
      }
   }

   private List listEntries() {
      this.checkConnection();
      ArrayList<String> list = new ArrayList(32);
      JarFile jarFile = this._jarFile;
      if (jarFile == null) {
         try {
            JarURLConnection jc = (JarURLConnection)(new URL(this._jarUrl)).openConnection();
            jc.setUseCaches(this.getUseCaches());
            jarFile = jc.getJarFile();
         } catch (Exception e) {
            e.printStackTrace();
            LOG.trace("IGNORED", e);
         }

         if (jarFile == null) {
            throw new IllegalStateException();
         }
      }

      Enumeration<JarEntry> e = jarFile.entries();
      String encodedDir = this._urlString.substring(this._urlString.lastIndexOf("!/") + 2);
      String dir = URIUtil.decodePath(encodedDir);

      while(e.hasMoreElements()) {
         JarEntry entry = (JarEntry)e.nextElement();
         String name = entry.getName();
         if (name.startsWith(dir) && name.length() != dir.length()) {
            String listName = name.substring(dir.length());
            int dash = listName.indexOf(47);
            if (dash >= 0) {
               if (dash == 0 && listName.length() == 1) {
                  continue;
               }

               if (dash == 0) {
                  listName = listName.substring(dash + 1, listName.length());
               } else {
                  listName = listName.substring(0, dash + 1);
               }

               if (list.contains(listName)) {
                  continue;
               }
            }

            list.add(listName);
         }
      }

      return list;
   }

   public long length() {
      if (this.isDirectory()) {
         return -1L;
      } else {
         return this._entry != null ? this._entry.getSize() : -1L;
      }
   }

   public boolean isContainedIn(Resource resource) throws MalformedURLException {
      String string = this._urlString;
      int index = string.lastIndexOf("!/");
      if (index > 0) {
         string = string.substring(0, index);
      }

      if (string.startsWith("jar:")) {
         string = string.substring(4);
      }

      URL url = new URL(string);
      return url.sameFile(resource.getURI().toURL());
   }

   public File getJarFile() {
      return this._file != null ? this._file : null;
   }
}
