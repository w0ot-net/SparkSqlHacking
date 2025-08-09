package org.apache.ivy.osgi.repo;

import java.net.URI;
import java.util.jar.Manifest;

public class ManifestAndLocation {
   private final Manifest manifest;
   private final URI uri;
   private final URI sourceURI;

   public ManifestAndLocation(Manifest manifest, URI uri, URI sourceURI) {
      this.manifest = manifest;
      this.uri = uri;
      this.sourceURI = sourceURI;
   }

   public URI getUri() {
      return this.uri;
   }

   public Manifest getManifest() {
      return this.manifest;
   }

   public URI getSourceURI() {
      return this.sourceURI;
   }
}
