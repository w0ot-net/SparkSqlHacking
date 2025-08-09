package org.glassfish.jersey.server.internal.scanning;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Set;
import org.glassfish.jersey.server.internal.AbstractResourceFinderAdapter;

final class BundleSchemeResourceFinderFactory implements UriSchemeResourceFinderFactory {
   private static final Set SCHEMES = Collections.singleton("bundle");

   public Set getSchemes() {
      return SCHEMES;
   }

   public BundleSchemeScanner create(URI uri, boolean recursive) {
      return new BundleSchemeScanner(uri);
   }

   private class BundleSchemeScanner extends AbstractResourceFinderAdapter {
      private final URI uri;
      private boolean accessed;
      private boolean iterated;

      private BundleSchemeScanner(URI uri) {
         this.accessed = false;
         this.iterated = false;
         this.uri = uri;
      }

      public boolean hasNext() {
         return !this.accessed && !this.iterated;
      }

      public String next() {
         if (this.hasNext()) {
            this.iterated = true;
            return this.uri.getPath();
         } else {
            throw new NoSuchElementException();
         }
      }

      public InputStream open() {
         if (!this.accessed) {
            try {
               this.accessed = true;
               return this.uri.toURL().openStream();
            } catch (IOException e) {
               throw new ResourceFinderException(e);
            }
         } else {
            return null;
         }
      }

      public void reset() {
         throw new UnsupportedOperationException();
      }
   }
}
