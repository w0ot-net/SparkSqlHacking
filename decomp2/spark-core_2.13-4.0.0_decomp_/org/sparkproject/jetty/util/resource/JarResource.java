package org.sparkproject.jetty.util.resource;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import java.util.jar.Manifest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.util.IO;
import org.sparkproject.jetty.util.StringUtil;
import org.sparkproject.jetty.util.URIUtil;
import org.sparkproject.jetty.util.thread.AutoLock;

public class JarResource extends URLResource {
   private static final Logger LOG = LoggerFactory.getLogger(JarResource.class);
   protected JarURLConnection _jarConnection;

   protected JarResource(URL url) {
      super(url, (URLConnection)null);
   }

   protected JarResource(URL url, boolean useCaches) {
      super(url, (URLConnection)null, useCaches);
   }

   public void close() {
      try (AutoLock l = this._lock.lock()) {
         this._jarConnection = null;
         super.close();
      }

   }

   protected boolean checkConnection() {
      try (AutoLock l = this._lock.lock()) {
         super.checkConnection();

         try {
            if (this._jarConnection != this._connection) {
               this.newConnection();
            }
         } catch (IOException e) {
            LOG.trace("IGNORED", e);
            this._jarConnection = null;
         }

         return this._jarConnection != null;
      }
   }

   protected void newConnection() throws IOException {
      this._jarConnection = (JarURLConnection)this._connection;
   }

   public boolean exists() {
      return this._urlString.endsWith("!/") ? this.checkConnection() : super.exists();
   }

   public File getFile() throws IOException {
      return null;
   }

   public InputStream getInputStream() throws IOException {
      this.checkConnection();
      if (!this._urlString.endsWith("!/")) {
         return new FilterInputStream(this.getInputStream(false)) {
            public void close() {
               this.in = IO.getClosedStream();
            }
         };
      } else {
         URL url = new URL(this._urlString.substring(4, this._urlString.length() - 2));
         InputStream is = url.openStream();
         return is;
      }
   }

   public void copyTo(File directory) throws IOException {
      if (this.exists()) {
         if (LOG.isDebugEnabled()) {
            LOG.debug("Extract {} to {}", this, directory);
         }

         String urlString = this.getURI().toASCIIString().trim();
         int endOfJarUrl = urlString.indexOf("!/");
         int startOfJarUrl = endOfJarUrl >= 0 ? 4 : 0;
         if (endOfJarUrl < 0) {
            throw new IOException("Not a valid jar url: " + urlString);
         } else {
            URL jarFileURL = new URL(urlString.substring(startOfJarUrl, endOfJarUrl));
            String subEntryName = endOfJarUrl + 2 < urlString.length() ? urlString.substring(endOfJarUrl + 2) : null;
            boolean subEntryIsDir = subEntryName != null && subEntryName.endsWith("/");
            if (LOG.isDebugEnabled()) {
               LOG.debug("Extracting entry = {} from jar {}", subEntryName, jarFileURL);
            }

            URLConnection c = jarFileURL.openConnection();
            c.setUseCaches(false);
            InputStream is = c.getInputStream();

            try {
               JarInputStream jin = new JarInputStream(is);

               try {
                  JarEntry entry;
                  while((entry = jin.getNextJarEntry()) != null) {
                     String entryName = entry.getName();
                     boolean shouldExtract;
                     if (subEntryName != null && entryName.startsWith(subEntryName)) {
                        if (!subEntryIsDir && subEntryName.length() + 1 == entryName.length() && entryName.endsWith("/")) {
                           subEntryIsDir = true;
                        }

                        if (subEntryIsDir) {
                           entryName = entryName.substring(subEntryName.length());
                           if (!entryName.isEmpty()) {
                              shouldExtract = true;
                           } else {
                              shouldExtract = false;
                           }
                        } else {
                           shouldExtract = true;
                        }
                     } else if (subEntryName != null && !entryName.startsWith(subEntryName)) {
                        shouldExtract = false;
                     } else {
                        shouldExtract = true;
                     }

                     if (!shouldExtract) {
                        if (LOG.isDebugEnabled()) {
                           LOG.debug("Skipping entry: {}", entryName);
                        }
                     } else {
                        String dotCheck = StringUtil.replace(entryName, '\\', '/');
                        dotCheck = URIUtil.canonicalPath(dotCheck);
                        if (dotCheck == null) {
                           if (LOG.isDebugEnabled()) {
                              LOG.debug("Invalid entry: {}", entryName);
                           }
                        } else {
                           File file = new File(directory, entryName);
                           if (entry.isDirectory()) {
                              if (!file.exists()) {
                                 file.mkdirs();
                              }
                           } else {
                              File dir = new File(file.getParent());
                              if (!dir.exists()) {
                                 dir.mkdirs();
                              }

                              OutputStream fout = new FileOutputStream(file);

                              try {
                                 IO.copy((InputStream)jin, (OutputStream)fout);
                              } catch (Throwable var25) {
                                 try {
                                    fout.close();
                                 } catch (Throwable var23) {
                                    var25.addSuppressed(var23);
                                 }

                                 throw var25;
                              }

                              fout.close();
                              if (entry.getTime() >= 0L) {
                                 file.setLastModified(entry.getTime());
                              }
                           }
                        }
                     }
                  }

                  if (subEntryName == null || subEntryName != null && subEntryName.equalsIgnoreCase("META-INF/MANIFEST.MF")) {
                     Manifest manifest = jin.getManifest();
                     if (manifest != null) {
                        File metaInf = new File(directory, "META-INF");
                        metaInf.mkdir();
                        File f = new File(metaInf, "MANIFEST.MF");
                        OutputStream fout = new FileOutputStream(f);

                        try {
                           manifest.write(fout);
                        } catch (Throwable var24) {
                           try {
                              fout.close();
                           } catch (Throwable var22) {
                              var24.addSuppressed(var22);
                           }

                           throw var24;
                        }

                        fout.close();
                     }
                  }
               } catch (Throwable var26) {
                  try {
                     jin.close();
                  } catch (Throwable var21) {
                     var26.addSuppressed(var21);
                  }

                  throw var26;
               }

               jin.close();
            } catch (Throwable var27) {
               if (is != null) {
                  try {
                     is.close();
                  } catch (Throwable var20) {
                     var27.addSuppressed(var20);
                  }
               }

               throw var27;
            }

            if (is != null) {
               is.close();
            }

         }
      }
   }

   public static Resource newJarResource(Resource resource) throws IOException {
      return resource instanceof JarResource ? resource : Resource.newResource("jar:" + String.valueOf(resource) + "!/");
   }
}
