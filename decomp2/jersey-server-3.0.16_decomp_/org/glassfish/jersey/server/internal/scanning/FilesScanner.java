package org.glassfish.jersey.server.internal.scanning;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.NoSuchElementException;
import java.util.Stack;
import org.glassfish.jersey.internal.util.Tokenizer;
import org.glassfish.jersey.server.internal.AbstractResourceFinderAdapter;

public final class FilesScanner extends AbstractResourceFinderAdapter {
   private final File[] files;
   private final boolean recursive;
   private CompositeResourceFinder compositeResourceFinder;

   public FilesScanner(String[] fileNames, boolean recursive) {
      this.recursive = recursive;
      this.files = new File[Tokenizer.tokenize(fileNames, " ,;\n").length];

      for(int i = 0; i < this.files.length; ++i) {
         this.files[i] = new File(fileNames[i]);
      }

      this.init();
   }

   private void processFile(final File f) {
      if (!f.getName().endsWith(".jar") && !f.getName().endsWith(".zip")) {
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
                     if (FilesScanner.this.recursive) {
                        FilesScanner.this.processFile(this.next);
                     }

                     this.next = null;
                  } else if (this.next.getName().endsWith(".jar") || this.next.getName().endsWith(".zip")) {
                     FilesScanner.this.processFile(this.next);
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
      } else {
         try {
            this.compositeResourceFinder.push(new JarFileScanner(Files.newInputStream(f.toPath()), "", true));
         } catch (IOException e) {
            throw new ResourceFinderException(e);
         }
      }

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
      this.close();
      this.init();
   }

   private void init() {
      this.compositeResourceFinder = new CompositeResourceFinder();

      for(File file : this.files) {
         this.processFile(file);
      }

   }
}
