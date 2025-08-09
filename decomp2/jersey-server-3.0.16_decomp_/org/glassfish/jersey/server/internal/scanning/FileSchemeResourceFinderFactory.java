package org.glassfish.jersey.server.internal.scanning;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.Stack;
import org.glassfish.jersey.server.internal.AbstractResourceFinderAdapter;

final class FileSchemeResourceFinderFactory implements UriSchemeResourceFinderFactory {
   private static final Set SCHEMES = Collections.singleton("file");

   public Set getSchemes() {
      return SCHEMES;
   }

   public FileSchemeScanner create(URI uri, boolean recursive) {
      return new FileSchemeScanner(uri, recursive);
   }

   private class FileSchemeScanner extends AbstractResourceFinderAdapter {
      private final CompositeResourceFinder compositeResourceFinder;
      private final boolean recursive;

      private FileSchemeScanner(URI uri, boolean recursive) {
         this.compositeResourceFinder = new CompositeResourceFinder();
         this.recursive = recursive;
         this.processFile(new File(uri.getPath()));
      }

      public boolean hasNext() {
         return this.compositeResourceFinder.hasNext();
      }

      public String next() {
         return this.compositeResourceFinder.next();
      }

      public InputStream open() {
         return this.compositeResourceFinder.open();
      }

      public void close() {
         this.compositeResourceFinder.close();
      }

      public void reset() {
         throw new UnsupportedOperationException();
      }

      private void processFile(final File f) {
         this.compositeResourceFinder.push(new AbstractResourceFinderAdapter() {
            Stack files = new Stack() {
               {
                  if (f.isDirectory()) {
                     File[] subDirFiles = f.listFiles();
                     if (subDirFiles != null) {
                        for(File file : subDirFiles) {
                           this.push(file);
                        }
                     }
                  } else {
                     this.push(f);
                  }

               }
            };
            private File current;
            private File next;

            public boolean hasNext() {
               while(this.next == null && !this.files.empty()) {
                  this.next = (File)this.files.pop();
                  if (this.next.isDirectory()) {
                     if (FileSchemeScanner.this.recursive) {
                        FileSchemeScanner.this.processFile(this.next);
                     }

                     this.next = null;
                  }
               }

               return this.next != null;
            }

            public String next() {
               if (this.next == null && !this.hasNext()) {
                  throw new NoSuchElementException();
               } else {
                  this.current = this.next;
                  this.next = null;
                  return this.current.getName();
               }
            }

            public InputStream open() {
               try {
                  return Files.newInputStream(this.current.toPath());
               } catch (IOException e) {
                  throw new ResourceFinderException(e);
               }
            }

            public void reset() {
               throw new UnsupportedOperationException();
            }
         });
      }
   }
}
