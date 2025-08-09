package org.apache.ivy.osgi.p2;

import org.apache.ivy.osgi.util.Version;

public class P2Artifact {
   private String id;
   private Version version;
   private String classifier;

   public P2Artifact(String id, Version version, String classifier) {
      this.id = id;
      this.version = version;
      this.classifier = classifier;
   }

   public String getClassifier() {
      return this.classifier;
   }

   public String getId() {
      return this.id;
   }

   public Version getVersion() {
      return this.version;
   }

   public String toString() {
      return this.id + "@" + this.version + " (" + this.classifier + ")";
   }
}
