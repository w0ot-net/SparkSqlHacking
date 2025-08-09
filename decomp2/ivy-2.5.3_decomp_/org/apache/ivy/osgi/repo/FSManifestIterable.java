package org.apache.ivy.osgi.repo;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FSManifestIterable extends AbstractFSManifestIterable {
   public static final Set NON_BUNDLE_DIRS = new HashSet(Arrays.asList("source", "sources", "javadoc", "javadocs", "doc", "docs"));
   public static final FilenameFilter DEFAULT_DIR_FILTER = new FilenameFilter() {
      public boolean accept(File dir, String name) {
         return !name.equals(".svn") && !FSManifestIterable.NON_BUNDLE_DIRS.contains(name);
      }
   };
   public static final FilenameFilter DEFAULT_BUNDLE_FILTER = new FilenameFilter() {
      public boolean accept(File dir, String name) {
         return name.endsWith(".jar");
      }
   };
   /** @deprecated */
   @Deprecated
   public static final FilenameFilter DEFAULT_BUNLDE_FILTER;
   private FilenameFilter dirFilter;
   private FilenameFilter bundleFilter;

   public FSManifestIterable(File root) {
      super(root);
      this.dirFilter = DEFAULT_DIR_FILTER;
      this.bundleFilter = DEFAULT_BUNDLE_FILTER;
   }

   public FilenameFilter getDirFilter() {
      return this.dirFilter;
   }

   public void setDirFilter(FilenameFilter dirFilter) {
      this.dirFilter = dirFilter;
   }

   public FilenameFilter getBundleFilter() {
      return this.bundleFilter;
   }

   public void setBundleFilter(FilenameFilter bundleFilter) {
      this.bundleFilter = bundleFilter;
   }

   protected URI buildBundleURI(File location) {
      try {
         return new URI(location.toURI().toURL().toExternalForm());
      } catch (MalformedURLException e) {
         throw new RuntimeException("Unexpected file to url conversion error", e);
      } catch (URISyntaxException e) {
         throw new RuntimeException("Unexpected url to uri conversion error", e);
      }
   }

   protected InputStream getInputStream(File f) throws FileNotFoundException {
      return new FileInputStream(f);
   }

   protected List listBundleFiles(File dir) throws IOException {
      return Arrays.asList(dir.listFiles(new FileFilter() {
         public boolean accept(File f) {
            return f.isFile() && FSManifestIterable.this.bundleFilter.accept(f.getParentFile(), f.getName());
         }
      }));
   }

   protected List listDirs(File dir) throws IOException {
      return Arrays.asList(dir.listFiles(new FileFilter() {
         public boolean accept(File f) {
            return f.isDirectory() && (FSManifestIterable.this.dirFilter == null || FSManifestIterable.this.dirFilter.accept(f.getParentFile(), f.getName()));
         }
      }));
   }

   static {
      DEFAULT_BUNLDE_FILTER = DEFAULT_BUNDLE_FILTER;
   }
}
