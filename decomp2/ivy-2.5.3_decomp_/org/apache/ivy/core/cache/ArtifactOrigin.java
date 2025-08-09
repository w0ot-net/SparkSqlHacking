package org.apache.ivy.core.cache;

import org.apache.ivy.core.module.descriptor.Artifact;
import org.apache.ivy.util.Checks;

public class ArtifactOrigin {
   private static final String UNKNOWN = "UNKNOWN";
   private static final int MAGIC_HASH_VALUE = 31;
   private boolean isLocal;
   private String location;
   private Artifact artifact;
   private Long lastChecked;
   private boolean exists = true;

   /** @deprecated */
   @Deprecated
   public static final ArtifactOrigin unkwnown(Artifact artifact) {
      return unknown(artifact);
   }

   public static final ArtifactOrigin unknown(Artifact artifact) {
      return new ArtifactOrigin(artifact, false, "UNKNOWN");
   }

   public static final boolean isUnknown(ArtifactOrigin artifact) {
      return artifact == null || "UNKNOWN".equals(artifact.getLocation());
   }

   public static final boolean isUnknown(String location) {
      return location == null || "UNKNOWN".equals(location);
   }

   public ArtifactOrigin(Artifact artifact, boolean isLocal, String location) {
      Checks.checkNotNull(artifact, "artifact");
      Checks.checkNotNull(location, "location");
      this.artifact = artifact;
      this.isLocal = isLocal;
      this.location = location;
   }

   public boolean isLocal() {
      return this.isLocal;
   }

   public String getLocation() {
      return this.location;
   }

   public void setLocation(String location) {
      this.location = location;
   }

   public Artifact getArtifact() {
      return this.artifact;
   }

   public Long getLastChecked() {
      return this.lastChecked;
   }

   public void setLastChecked(Long lastChecked) {
      this.lastChecked = lastChecked;
   }

   public boolean isExists() {
      return this.exists;
   }

   public void setExist(boolean exists) {
      this.exists = exists;
   }

   public String toString() {
      return "ArtifactOrigin { isLocal=" + this.isLocal + ", location=" + this.location + ", lastChecked=" + this.lastChecked + ", exists=" + this.exists + "}";
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (!(o instanceof ArtifactOrigin)) {
         return false;
      } else {
         ArtifactOrigin that = (ArtifactOrigin)o;
         if (this.isLocal == that.isLocal && this.location.equals(that.location)) {
            if (this.lastChecked == null) {
               if (that.lastChecked != null) {
                  return false;
               }
            } else if (!this.lastChecked.equals(that.lastChecked)) {
               return false;
            }

            return this.exists == that.exists;
         } else {
            return false;
         }
      }
   }

   public int hashCode() {
      int result = this.isLocal ? 1 : 0;
      result = 31 * result + this.location.hashCode();
      result = 31 * result + (this.lastChecked == null ? 0 : this.lastChecked.hashCode());
      result = 31 * result + (this.exists ? 1 : 0);
      return result;
   }
}
