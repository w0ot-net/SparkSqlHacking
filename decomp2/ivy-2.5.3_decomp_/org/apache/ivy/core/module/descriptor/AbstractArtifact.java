package org.apache.ivy.core.module.descriptor;

import java.util.Map;

public abstract class AbstractArtifact implements Artifact {
   public boolean equals(Object obj) {
      if (!(obj instanceof Artifact)) {
         return false;
      } else {
         Artifact art = (Artifact)obj;
         return this.getModuleRevisionId().equals(art.getModuleRevisionId()) && this.getPublicationDate() == null ? art.getPublicationDate() == null : this.getPublicationDate().equals(art.getPublicationDate()) && this.getName().equals(art.getName()) && this.getExt().equals(art.getExt()) && this.getType().equals(art.getType()) && this.getQualifiedExtraAttributes().equals(art.getQualifiedExtraAttributes());
      }
   }

   public int hashCode() {
      int hash = 33;
      hash = hash * 17 + this.getModuleRevisionId().hashCode();
      if (this.getPublicationDate() != null) {
         hash = hash * 17 + this.getPublicationDate().hashCode();
      }

      hash = hash * 17 + this.getName().hashCode();
      hash = hash * 17 + this.getExt().hashCode();
      hash = hash * 17 + this.getType().hashCode();
      hash = hash * 17 + this.getQualifiedExtraAttributes().hashCode();
      return hash;
   }

   public String toString() {
      return String.valueOf(this.getId());
   }

   public String getAttribute(String attName) {
      return this.getId().getAttribute(attName);
   }

   public Map getAttributes() {
      return this.getId().getAttributes();
   }

   public String getExtraAttribute(String attName) {
      return this.getId().getExtraAttribute(attName);
   }

   public Map getExtraAttributes() {
      return this.getId().getExtraAttributes();
   }

   public Map getQualifiedExtraAttributes() {
      return this.getId().getQualifiedExtraAttributes();
   }
}
