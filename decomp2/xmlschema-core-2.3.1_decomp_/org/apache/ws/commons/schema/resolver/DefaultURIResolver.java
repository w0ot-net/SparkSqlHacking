package org.apache.ws.commons.schema.resolver;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import org.xml.sax.InputSource;

public class DefaultURIResolver implements CollectionURIResolver {
   private String collectionBaseURI;

   public InputSource resolveEntity(String namespace, String schemaLocation, String baseUri) {
      if (baseUri != null) {
         try {
            File baseFile = null;

            try {
               URI uri = new URI(baseUri);
               baseFile = new File(uri);
               if (!baseFile.exists()) {
                  baseFile = new File(baseUri);
               }
            } catch (Throwable var6) {
               baseFile = new File(baseUri);
            }

            if (baseFile.exists()) {
               baseUri = baseFile.toURI().toString();
            } else if (this.collectionBaseURI != null) {
               baseFile = new File(this.collectionBaseURI);
               if (baseFile.exists()) {
                  baseUri = baseFile.toURI().toString();
               }
            }

            String ref = (new URL(new URL(baseUri), schemaLocation)).toString();
            return new InputSource(ref);
         } catch (MalformedURLException e1) {
            throw new RuntimeException(e1);
         }
      } else {
         return new InputSource(schemaLocation);
      }
   }

   protected boolean isAbsolute(String uri) {
      return uri.startsWith("http://") || uri.startsWith("https://") || uri.startsWith("urn:");
   }

   protected URL getURL(URL contextURL, String spec) throws IOException {
      String path = spec.replace('\\', '/');

      URL url;
      try {
         url = new URL(contextURL, path);
         if (contextURL != null && url.getProtocol().equals("file") && contextURL.getProtocol().equals("file")) {
            url = this.getFileURL(contextURL, path);
         }
      } catch (MalformedURLException var6) {
         url = this.getFileURL(contextURL, path);
      }

      return url;
   }

   protected URL getFileURL(URL contextURL, String path) throws IOException {
      if (contextURL != null) {
         String contextFileName = contextURL.getFile();
         URL parent = null;
         File contextFile = new File(contextFileName);
         File parentFile;
         if (contextFile.isDirectory()) {
            parentFile = contextFile;
         } else {
            parentFile = contextFile.getParentFile();
         }

         if (parentFile != null) {
            parent = parentFile.toURI().toURL();
         }

         if (parent != null) {
            return new URL(parent, path);
         }
      }

      return new URL("file", "", path);
   }

   public String getCollectionBaseURI() {
      return this.collectionBaseURI;
   }

   public void setCollectionBaseURI(String collectionBaseURI) {
      this.collectionBaseURI = collectionBaseURI;
   }
}
