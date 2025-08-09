package org.apache.ivy.plugins.repository.file;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.ivy.plugins.repository.AbstractRepository;
import org.apache.ivy.plugins.repository.RepositoryCopyProgressListener;
import org.apache.ivy.plugins.repository.Resource;
import org.apache.ivy.util.Checks;
import org.apache.ivy.util.FileUtil;

public class FileRepository extends AbstractRepository {
   private RepositoryCopyProgressListener progress = new RepositoryCopyProgressListener(this);
   private File baseDir;
   private boolean local = true;

   public FileRepository() {
      this.baseDir = null;
   }

   public FileRepository(File basedir) {
      this.setBaseDir(basedir);
   }

   public Resource getResource(String source) throws IOException {
      return new FileResource(this, this.getFile(source));
   }

   public void get(String source, File destination) throws IOException {
      File s = this.getFile(source);
      this.fireTransferInitiated(this.getResource(source), 5);
      this.copy(s, destination, true);
   }

   public void put(File source, String destination, boolean overwrite) throws IOException {
      File d = this.getFile(destination);
      this.fireTransferInitiated(this.getResource(destination), 6);
      this.copy(source, d, overwrite);
   }

   public void move(File src, File dest) throws IOException {
      if (!src.renameTo(dest)) {
         throw new IOException("impossible to move '" + src + "' to '" + dest + "'");
      }
   }

   public void delete(File f) throws IOException {
      if (!FileUtil.forceDelete(f)) {
         throw new IOException("impossible to delete '" + f + "'");
      }
   }

   private void copy(File src, File destination, boolean overwrite) throws IOException {
      try {
         this.getProgressListener().setTotalLength(src.length());
         if (!FileUtil.copy((File)src, (File)destination, this.getProgressListener(), overwrite)) {
            if (!overwrite && destination.exists()) {
               throw new IOException("file copy not done from " + src + " to " + destination + ": destination already exists and overwrite is false");
            }

            throw new IOException("file copy not done from " + src + " to " + destination);
         }
      } catch (RuntimeException | IOException ex) {
         this.fireTransferError(ex);
         throw ex;
      } finally {
         this.getProgressListener().setTotalLength((Long)null);
      }

   }

   protected RepositoryCopyProgressListener getProgressListener() {
      return this.progress;
   }

   public List list(String parent) throws IOException {
      File dir = this.getFile(parent);
      if (dir.exists() && dir.isDirectory()) {
         String[] names = dir.list();
         if (names != null) {
            List<String> ret = new ArrayList(names.length);

            for(String name : names) {
               ret.add(parent + this.getFileSeparator() + name);
            }

            return ret;
         }
      }

      return null;
   }

   File getFile(String source) {
      if (this.baseDir == null) {
         return Checks.checkAbsolute(source, "source");
      } else {
         File file = FileUtil.resolveFile(this.baseDir, source);
         if (!FileUtil.isLeadingPath(this.baseDir, file)) {
            throw new IllegalArgumentException(source + " outside of repository root");
         } else {
            return file;
         }
      }
   }

   public boolean isLocal() {
      return this.local;
   }

   public void setLocal(boolean local) {
      this.local = local;
   }

   public File getBaseDir() {
      return this.baseDir;
   }

   public final void setBaseDir(File baseDir) {
      Checks.checkAbsolute(baseDir, "basedir");
      this.baseDir = baseDir;
   }

   public String standardize(String source) {
      return this.baseDir == null ? FileUtil.normalize(source).getPath() : FileUtil.resolveFile(this.baseDir, source).getPath();
   }

   public String getFileSeparator() {
      return File.separator;
   }
}
