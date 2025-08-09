package org.apache.ivy.osgi.repo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Stack;
import java.util.jar.JarInputStream;
import java.util.jar.Manifest;
import org.apache.ivy.util.Message;

public abstract class AbstractFSManifestIterable implements Iterable {
   private final Object root;

   public AbstractFSManifestIterable(Object root) {
      this.root = root;
   }

   public Iterator iterator() {
      return new FSManifestIterator();
   }

   protected abstract List listBundleFiles(Object var1) throws IOException;

   protected abstract List listDirs(Object var1) throws IOException;

   protected abstract InputStream getInputStream(Object var1) throws IOException;

   protected abstract URI buildBundleURI(Object var1) throws IOException;

   class FSManifestIterator implements Iterator {
      private ManifestAndLocation next = null;
      private Stack dirs = new Stack();
      private Iterator bundleCandidates = null;
      private Object currentDir = null;

      FSManifestIterator() {
         this.dirs.add(Collections.singleton(AbstractFSManifestIterable.this.root).iterator());
      }

      public boolean hasNext() {
         while(this.next == null) {
            if (this.currentDir == null) {
               if (((Iterator)this.dirs.peek()).hasNext()) {
                  this.currentDir = ((Iterator)this.dirs.peek()).next();

                  try {
                     this.bundleCandidates = AbstractFSManifestIterable.this.listBundleFiles(this.currentDir).iterator();
                  } catch (IOException e) {
                     Message.warn("Unlistable dir: " + this.currentDir, e);
                     this.currentDir = null;
                  }
               } else {
                  if (this.dirs.size() <= 1) {
                     return false;
                  }

                  this.dirs.pop();
               }
            } else if (this.bundleCandidates.hasNext()) {
               T bundleCandidate = (T)this.bundleCandidates.next();

               try {
                  JarInputStream in = new JarInputStream(AbstractFSManifestIterable.this.getInputStream(bundleCandidate));
                  Throwable var3 = null;

                  try {
                     Manifest manifest = in.getManifest();
                     if (manifest != null) {
                        this.next = new ManifestAndLocation(manifest, AbstractFSManifestIterable.this.buildBundleURI(bundleCandidate), (URI)null);
                     } else {
                        Message.debug("No manifest in jar: " + bundleCandidate);
                     }
                  } catch (Throwable var17) {
                     var3 = var17;
                     throw var17;
                  } finally {
                     if (in != null) {
                        if (var3 != null) {
                           try {
                              in.close();
                           } catch (Throwable var15) {
                              var3.addSuppressed(var15);
                           }
                        } else {
                           in.close();
                        }
                     }

                  }
               } catch (FileNotFoundException e) {
                  Message.debug("Jar file just removed: " + bundleCandidate, e);
               } catch (IOException e) {
                  Message.warn("Unreadable jar: " + bundleCandidate, e);
               }
            } else {
               try {
                  this.dirs.add(AbstractFSManifestIterable.this.listDirs(this.currentDir).iterator());
               } catch (IOException e) {
                  Message.warn("Unlistable dir: " + this.currentDir + " (" + e + ")");
                  this.dirs.add(Collections.emptyList().iterator());
               }

               this.currentDir = null;
            }
         }

         return true;
      }

      public ManifestAndLocation next() {
         if (!this.hasNext()) {
            throw new NoSuchElementException();
         } else {
            ManifestAndLocation manifest = this.next;
            this.next = null;
            return manifest;
         }
      }

      public void remove() {
         throw new UnsupportedOperationException();
      }
   }
}
