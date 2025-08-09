package org.apache.ivy.osgi.core;

import java.net.URI;

public class BundleArtifact {
   private boolean source = false;
   private URI uri;
   private String format;

   public BundleArtifact(boolean source, URI uri, String format) {
      this.source = source;
      this.uri = uri;
      this.format = format;
   }

   public boolean isSource() {
      return this.source;
   }

   public URI getUri() {
      return this.uri;
   }

   public String getFormat() {
      return this.format;
   }
}
