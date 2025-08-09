package org.apache.ivy.plugins.repository.jar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;
import org.apache.ivy.core.settings.TimeoutConstraint;
import org.apache.ivy.plugins.repository.AbstractRepository;
import org.apache.ivy.plugins.repository.RepositoryCopyProgressListener;
import org.apache.ivy.plugins.repository.Resource;
import org.apache.ivy.util.FileUtil;

public class JarRepository extends AbstractRepository {
   private RepositoryCopyProgressListener progress = new RepositoryCopyProgressListener(this);
   private JarFile jarFile;

   public JarRepository() {
   }

   public JarRepository(TimeoutConstraint timeoutConstraint) {
      super(timeoutConstraint);
   }

   public void setJarFile(JarFile jarFile) {
      this.jarFile = jarFile;
   }

   public Resource getResource(String source) throws IOException {
      return new JarResource(this.jarFile, source);
   }

   protected RepositoryCopyProgressListener getProgressListener() {
      return this.progress;
   }

   public void get(String source, File destination) throws IOException {
      this.fireTransferInitiated(this.getResource(source), 5);

      try {
         ZipEntry entry = this.jarFile.getEntry(source);
         if (entry == null) {
            throw new FileNotFoundException();
         }

         this.getProgressListener().setTotalLength(entry.getSize());
         FileUtil.copy((InputStream)this.jarFile.getInputStream(entry), (File)destination, this.getProgressListener());
      } catch (RuntimeException | IOException ex) {
         this.fireTransferError(ex);
         throw ex;
      } finally {
         this.getProgressListener().setTotalLength((Long)null);
      }

   }

   public List list(String parent) throws IOException {
      ZipEntry parentEntry = this.jarFile.getEntry(parent);
      if (parentEntry != null && parentEntry.isDirectory()) {
         List<String> children = new ArrayList();
         Enumeration<JarEntry> entries = this.jarFile.entries();

         while(entries.hasMoreElements()) {
            JarEntry entry = (JarEntry)entries.nextElement();
            if (entry.getName().startsWith(parent) && entry.getName().equals(parentEntry.getName())) {
               children.add(entry.getName());
            }
         }

         return children;
      } else {
         return null;
      }
   }
}
