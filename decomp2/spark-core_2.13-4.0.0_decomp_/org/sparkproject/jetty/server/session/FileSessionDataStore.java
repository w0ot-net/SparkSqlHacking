package org.sparkproject.jetty.server.session;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.util.ClassLoadingObjectInputStream;
import org.sparkproject.jetty.util.MultiException;
import org.sparkproject.jetty.util.StringUtil;
import org.sparkproject.jetty.util.annotation.ManagedAttribute;
import org.sparkproject.jetty.util.annotation.ManagedObject;

@ManagedObject
public class FileSessionDataStore extends AbstractSessionDataStore {
   private static final Logger LOG = LoggerFactory.getLogger(FileSessionDataStore.class);
   protected File _storeDir;
   protected boolean _deleteUnrestorableFiles = false;
   protected Map _sessionFileMap = new ConcurrentHashMap();
   protected String _contextString;
   protected long _lastSweepTime = 0L;

   public void initialize(SessionContext context) throws Exception {
      super.initialize(context);
      String var10001 = this._context.getCanonicalContextPath();
      this._contextString = var10001 + "_" + this._context.getVhost();
   }

   protected void doStart() throws Exception {
      this.initializeStore();
      super.doStart();
   }

   protected void doStop() throws Exception {
      this._sessionFileMap.clear();
      this._lastSweepTime = 0L;
      super.doStop();
   }

   @ManagedAttribute(
      value = "dir where sessions are stored",
      readonly = true
   )
   public File getStoreDir() {
      return this._storeDir;
   }

   public void setStoreDir(File storeDir) {
      this.checkStarted();
      this._storeDir = storeDir;
   }

   public boolean isDeleteUnrestorableFiles() {
      return this._deleteUnrestorableFiles;
   }

   public void setDeleteUnrestorableFiles(boolean deleteUnrestorableFiles) {
      this.checkStarted();
      this._deleteUnrestorableFiles = deleteUnrestorableFiles;
   }

   public boolean delete(String id) throws Exception {
      if (this._storeDir != null) {
         String filename = (String)this._sessionFileMap.remove(this.getIdWithContext(id));
         return filename == null ? false : this.deleteFile(filename);
      } else {
         return false;
      }
   }

   public boolean deleteFile(String filename) throws Exception {
      if (filename == null) {
         return false;
      } else {
         File file = new File(this._storeDir, filename);
         return Files.deleteIfExists(file.toPath());
      }
   }

   public Set doCheckExpired(Set candidates, long time) {
      HashSet<String> expired = new HashSet();

      for(String id : candidates) {
         String filename = (String)this._sessionFileMap.get(this.getIdWithContext(id));
         if (filename == null) {
            expired.add(id);
         } else {
            try {
               long expiry = this.getExpiryFromFilename(filename);
               if (expiry > 0L && expiry <= time) {
                  expired.add(id);
               }
            } catch (Exception e) {
               LOG.warn("Error finding expired sessions", e);
            }
         }
      }

      return expired;
   }

   public Set doGetExpired(long timeLimit) {
      HashSet<String> expired = new HashSet();

      for(String filename : this._sessionFileMap.values()) {
         try {
            long expiry = this.getExpiryFromFilename(filename);
            if (expiry > 0L && expiry <= timeLimit) {
               expired.add(this.getIdFromFilename(filename));
            }
         } catch (Exception e) {
            LOG.warn("Error finding sessions expired before {}", timeLimit, e);
         }
      }

      return expired;
   }

   public void doCleanOrphans(long time) {
      this.sweepDisk(time);
   }

   protected void sweepDisk(long time) {
      if (LOG.isDebugEnabled()) {
         LOG.debug("Sweeping {} for old session files at {}", this._storeDir, time);
      }

      try {
         Stream<Path> stream = Files.walk(this._storeDir.toPath(), 1, new FileVisitOption[]{FileVisitOption.FOLLOW_LINKS});

         try {
            stream.filter((p) -> !Files.isDirectory(p, new LinkOption[0])).filter((p) -> this.isSessionFilename(p.getFileName().toString())).forEach((p) -> this.sweepFile(time, p));
         } catch (Throwable var7) {
            if (stream != null) {
               try {
                  stream.close();
               } catch (Throwable var6) {
                  var7.addSuppressed(var6);
               }
            }

            throw var7;
         }

         if (stream != null) {
            stream.close();
         }
      } catch (Exception e) {
         LOG.warn("Unable to walk path {}", this._storeDir, e);
      }

   }

   protected void sweepFile(long time, Path p) {
      if (p != null) {
         try {
            long expiry = this.getExpiryFromFilename(p.getFileName().toString());
            if (expiry > 0L && expiry <= time) {
               try {
                  if (!Files.deleteIfExists(p)) {
                     LOG.warn("Could not delete {}", p.getFileName());
                  } else if (LOG.isDebugEnabled()) {
                     LOG.debug("Deleted {}", p.getFileName());
                  }
               } catch (IOException e) {
                  LOG.warn("Could not delete {}", p.getFileName(), e);
               }
            }
         } catch (NumberFormatException e) {
            LOG.warn("Not valid session filename {}", p.getFileName(), e);
         }
      }

   }

   public SessionData doLoad(String id) throws Exception {
      String idWithContext = this.getIdWithContext(id);
      String filename = (String)this._sessionFileMap.get(idWithContext);
      if (filename == null) {
         if (LOG.isDebugEnabled()) {
            LOG.debug("Unknown file {}", idWithContext);
         }

         return null;
      } else {
         File file = new File(this._storeDir, filename);
         if (!file.exists()) {
            if (LOG.isDebugEnabled()) {
               LOG.debug("No such file {}", filename);
            }

            return null;
         } else {
            try {
               FileInputStream in = new FileInputStream(file);

               SessionData var7;
               try {
                  SessionData data = this.load(in, id);
                  data.setLastSaved(file.lastModified());
                  var7 = data;
               } catch (Throwable var10) {
                  try {
                     in.close();
                  } catch (Throwable var9) {
                     var10.addSuppressed(var9);
                  }

                  throw var10;
               }

               in.close();
               return var7;
            } catch (UnreadableSessionDataException e) {
               if (this.isDeleteUnrestorableFiles() && file.exists() && file.getParentFile().equals(this._storeDir)) {
                  try {
                     this.delete(id);
                     LOG.warn("Deleted unrestorable file for session {}", id);
                  } catch (Exception x) {
                     LOG.warn("Unable to delete unrestorable file {} for session {}", new Object[]{filename, id, x});
                  }
               }

               throw e;
            }
         }
      }
   }

   public void doStore(String id, SessionData data, long lastSaveTime) throws Exception {
      if (this._storeDir != null) {
         this.delete(id);
         String filename = this.getIdWithContextAndExpiry(data);
         String idWithContext = this.getIdWithContext(id);
         File file = new File(this._storeDir, filename);

         try {
            FileOutputStream fos = new FileOutputStream(file, false);

            try {
               this.save(fos, id, data);
               this._sessionFileMap.put(idWithContext, filename);
            } catch (Throwable var12) {
               try {
                  fos.close();
               } catch (Throwable var11) {
                  var12.addSuppressed(var11);
               }

               throw var12;
            }

            fos.close();
         } catch (Exception var13) {
            if (!file.delete()) {
               var13.addSuppressed(new IOException("Could not delete " + String.valueOf(file)));
            }

            throw new UnwriteableSessionDataException(id, this._context, var13);
         }
      }

   }

   public void initializeStore() throws Exception {
      if (this._storeDir == null) {
         throw new IllegalStateException("No file store specified");
      } else {
         if (!this._storeDir.exists()) {
            if (!this._storeDir.mkdirs()) {
               throw new IllegalStateException("Could not create " + String.valueOf(this._storeDir));
            }
         } else {
            if (!this._storeDir.isDirectory() || !this._storeDir.canWrite() || !this._storeDir.canRead()) {
               throw new IllegalStateException(this._storeDir.getAbsolutePath() + " must be readable/writeable dir");
            }

            MultiException me = new MultiException();
            long now = System.currentTimeMillis();
            Stream<Path> stream = Files.walk(this._storeDir.toPath(), 1, new FileVisitOption[]{FileVisitOption.FOLLOW_LINKS});

            try {
               stream.filter((p) -> !Files.isDirectory(p, new LinkOption[0])).filter((p) -> this.isSessionFilename(p.getFileName().toString())).forEach((p) -> {
                  this.sweepFile(now - 10L * TimeUnit.SECONDS.toMillis((long)this.getGracePeriodSec()), p);
                  String filename = p.getFileName().toString();
                  String context = this.getContextFromFilename(filename);
                  if (Files.exists(p, new LinkOption[0]) && this._contextString.equals(context)) {
                     String sessionIdWithContext = this.getIdWithContextFromFilename(filename);
                     if (sessionIdWithContext != null) {
                        String existing = (String)this._sessionFileMap.putIfAbsent(sessionIdWithContext, filename);
                        if (existing != null) {
                           try {
                              long existingExpiry = this.getExpiryFromFilename(existing);
                              long thisExpiry = this.getExpiryFromFilename(filename);
                              if (thisExpiry > existingExpiry) {
                                 Path existingPath = this._storeDir.toPath().resolve(existing);
                                 this._sessionFileMap.put(sessionIdWithContext, filename);
                                 Files.delete(existingPath);
                                 if (LOG.isDebugEnabled()) {
                                    LOG.debug("Replaced {} with {}", existing, filename);
                                 }
                              } else {
                                 Files.delete(p);
                                 if (LOG.isDebugEnabled()) {
                                    LOG.debug("Deleted expired session file {}", filename);
                                 }
                              }
                           } catch (IOException e) {
                              me.add(e);
                           }
                        }
                     }
                  }

               });
               me.ifExceptionThrow();
            } catch (Throwable var8) {
               if (stream != null) {
                  try {
                     stream.close();
                  } catch (Throwable var7) {
                     var8.addSuppressed(var7);
                  }
               }

               throw var8;
            }

            if (stream != null) {
               stream.close();
            }
         }

      }
   }

   @ManagedAttribute(
      value = "are sessions serialized by this store",
      readonly = true
   )
   public boolean isPassivating() {
      return true;
   }

   public boolean doExists(String id) throws Exception {
      String idWithContext = this.getIdWithContext(id);
      String filename = (String)this._sessionFileMap.get(idWithContext);
      if (filename == null) {
         return false;
      } else {
         long expiry = this.getExpiryFromFilename(filename);
         if (expiry <= 0L) {
            return true;
         } else {
            return expiry > System.currentTimeMillis();
         }
      }
   }

   protected void save(OutputStream os, String id, SessionData data) throws IOException {
      DataOutputStream out = new DataOutputStream(os);
      out.writeUTF(id);
      out.writeUTF(this._context.getCanonicalContextPath());
      out.writeUTF(this._context.getVhost());
      out.writeUTF(data.getLastNode());
      out.writeLong(data.getCreated());
      out.writeLong(data.getAccessed());
      out.writeLong(data.getLastAccessed());
      out.writeLong(data.getCookieSet());
      out.writeLong(data.getExpiry());
      out.writeLong(data.getMaxInactiveMs());
      ObjectOutputStream oos = new ObjectOutputStream(out);
      SessionData.serializeAttributes(data, oos);
   }

   protected String getIdWithContext(String id) {
      return this._contextString + "_" + id;
   }

   protected String getIdWithContextAndExpiry(SessionData data) {
      long var10000 = data.getExpiry();
      return var10000 + "_" + this.getIdWithContext(data.getId());
   }

   protected String getIdFromFilename(String filename) {
      return filename == null ? null : filename.substring(filename.lastIndexOf(95) + 1);
   }

   protected long getExpiryFromFilename(String filename) {
      if (!StringUtil.isBlank(filename) && filename.contains("_")) {
         String s = filename.substring(0, filename.indexOf(95));
         return Long.parseLong(s);
      } else {
         throw new IllegalStateException("Invalid or missing filename");
      }
   }

   protected String getContextFromFilename(String filename) {
      if (StringUtil.isBlank(filename)) {
         return null;
      } else {
         int start = filename.indexOf(95);
         int end = filename.lastIndexOf(95);
         return filename.substring(start + 1, end);
      }
   }

   protected String getIdWithContextFromFilename(String filename) {
      return !StringUtil.isBlank(filename) && filename.indexOf(95) >= 0 ? filename.substring(filename.indexOf(95) + 1) : null;
   }

   protected boolean isSessionFilename(String filename) {
      if (StringUtil.isBlank(filename)) {
         return false;
      } else {
         String[] parts = filename.split("_");
         return parts.length >= 4;
      }
   }

   protected boolean isOurContextSessionFilename(String filename) {
      if (StringUtil.isBlank(filename)) {
         return false;
      } else {
         String[] parts = filename.split("_");
         if (parts.length < 4) {
            return false;
         } else {
            String context = this.getContextFromFilename(filename);
            return context == null ? false : this._contextString.equals(context);
         }
      }
   }

   protected SessionData load(InputStream is, String expectedId) throws Exception {
      try {
         DataInputStream di = new DataInputStream(is);
         String id = di.readUTF();
         String contextPath = di.readUTF();
         String vhost = di.readUTF();
         String lastNode = di.readUTF();
         long created = di.readLong();
         long accessed = di.readLong();
         long lastAccessed = di.readLong();
         long cookieSet = di.readLong();
         long expiry = di.readLong();
         long maxIdle = di.readLong();
         SessionData data = this.newSessionData(id, created, accessed, lastAccessed, maxIdle);
         data.setContextPath(contextPath);
         data.setVhost(vhost);
         data.setLastNode(lastNode);
         data.setCookieSet(cookieSet);
         data.setExpiry(expiry);
         data.setMaxInactiveMs(maxIdle);
         ClassLoadingObjectInputStream ois = new ClassLoadingObjectInputStream(is);
         SessionData.deserializeAttributes(data, ois);
         return data;
      } catch (Exception e) {
         throw new UnreadableSessionDataException(expectedId, this._context, e);
      }
   }

   public String toString() {
      return String.format("%s[dir=%s,deleteUnrestorableFiles=%b]", super.toString(), this._storeDir, this._deleteUnrestorableFiles);
   }
}
