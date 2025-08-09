package org.glassfish.jersey.server.internal.scanning;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.glassfish.jersey.server.internal.AbstractResourceFinderAdapter;
import org.glassfish.jersey.uri.UriComponent;
import org.glassfish.jersey.uri.UriComponent.Type;

final class JarZipSchemeResourceFinderFactory implements UriSchemeResourceFinderFactory {
   private static final Set SCHEMES = Collections.unmodifiableSet(new HashSet(Arrays.asList("jar", "zip", "wsjar")));

   public Set getSchemes() {
      return SCHEMES;
   }

   public JarZipSchemeScanner create(URI uri, boolean recursive) {
      String ssp = uri.getRawSchemeSpecificPart();
      String jarUrlString = ssp.substring(0, ssp.lastIndexOf(33));
      String parent = ssp.substring(ssp.lastIndexOf(33) + 2);

      try {
         return new JarZipSchemeScanner(this.getInputStream(jarUrlString), parent, recursive);
      } catch (IOException e) {
         throw new ResourceFinderException(e);
      }
   }

   private InputStream getInputStream(String jarUrlString) throws IOException {
      try {
         return (new URL(jarUrlString)).openStream();
      } catch (MalformedURLException var3) {
         return Files.newInputStream(Paths.get(UriComponent.decode(jarUrlString, Type.PATH)));
      }
   }

   private class JarZipSchemeScanner extends AbstractResourceFinderAdapter {
      private final InputStream inputStream;
      private final JarFileScanner jarFileScanner;

      private JarZipSchemeScanner(InputStream inputStream, String parent, boolean recursive) throws IOException {
         this.inputStream = inputStream;
         this.jarFileScanner = new JarFileScanner(inputStream, parent, recursive);
      }

      public boolean hasNext() {
         boolean hasNext = this.jarFileScanner.hasNext();
         if (!hasNext) {
            try {
               this.inputStream.close();
            } catch (IOException e) {
               Logger.getLogger(JarZipSchemeScanner.class.getName()).log(Level.FINE, "Unable to close jar file.", e);
            }

            return false;
         } else {
            return true;
         }
      }

      public String next() {
         return this.jarFileScanner.next();
      }

      public InputStream open() {
         return this.jarFileScanner.open();
      }

      public void close() {
         this.jarFileScanner.close();
      }

      public void reset() {
         this.jarFileScanner.reset();
      }
   }
}
