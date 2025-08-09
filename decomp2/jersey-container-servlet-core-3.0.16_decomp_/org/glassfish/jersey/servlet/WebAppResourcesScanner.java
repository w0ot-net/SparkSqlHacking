package org.glassfish.jersey.servlet;

import jakarta.servlet.ServletContext;
import java.io.IOException;
import java.io.InputStream;
import java.util.Deque;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Set;
import org.glassfish.jersey.server.internal.AbstractResourceFinderAdapter;
import org.glassfish.jersey.server.internal.scanning.CompositeResourceFinder;
import org.glassfish.jersey.server.internal.scanning.JarFileScanner;
import org.glassfish.jersey.server.internal.scanning.ResourceFinderException;

final class WebAppResourcesScanner extends AbstractResourceFinderAdapter {
   private static final String[] paths = new String[]{"/WEB-INF/lib/", "/WEB-INF/classes/"};
   private final ServletContext sc;
   private CompositeResourceFinder compositeResourceFinder = new CompositeResourceFinder();

   WebAppResourcesScanner(ServletContext sc) {
      this.sc = sc;
      this.processPaths(paths);
   }

   private void processPaths(String... paths) {
      for(String path : paths) {
         final Set<String> resourcePaths = this.sc.getResourcePaths(path);
         if (resourcePaths == null) {
            break;
         }

         this.compositeResourceFinder.push(new AbstractResourceFinderAdapter() {
            private final Deque resourcePathsStack = new LinkedList() {
               private static final long serialVersionUID = 3109256773218160485L;

               {
                  for(String resourcePath : resourcePaths) {
                     this.push(resourcePath);
                  }

               }
            };
            private String current;
            private String next;

            public boolean hasNext() {
               while(this.next == null && !this.resourcePathsStack.isEmpty()) {
                  this.next = (String)this.resourcePathsStack.pop();
                  if (this.next.endsWith("/")) {
                     WebAppResourcesScanner.this.processPaths(this.next);
                     this.next = null;
                  } else if (this.next.endsWith(".jar")) {
                     try {
                        WebAppResourcesScanner.this.compositeResourceFinder.push(new JarFileScanner(WebAppResourcesScanner.this.sc.getResourceAsStream(this.next), "", true));
                     } catch (IOException ioe) {
                        throw new ResourceFinderException(ioe);
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
                  return this.current;
               }
            }

            public InputStream open() {
               return WebAppResourcesScanner.this.sc.getResourceAsStream(this.current);
            }

            public void reset() {
               throw new UnsupportedOperationException();
            }
         });
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
      this.compositeResourceFinder = new CompositeResourceFinder();
      this.processPaths(paths);
   }
}
