package org.apache.ivy.core.module.id;

import java.util.Map;
import org.apache.ivy.util.extendable.UnmodifiableExtendableItem;

public class ArtifactRevisionId extends UnmodifiableExtendableItem {
   private ArtifactId artifactId;
   private ModuleRevisionId mrid;

   public static ArtifactRevisionId newInstance(ModuleRevisionId mrid, String name, String type, String ext) {
      return newInstance(mrid, name, type, ext, (Map)null);
   }

   public static ArtifactRevisionId newInstance(ModuleRevisionId mrid, String name, String type, String ext, Map extraAttributes) {
      return new ArtifactRevisionId(new ArtifactId(mrid.getModuleId(), name, type, ext), mrid, extraAttributes);
   }

   public ArtifactRevisionId(ArtifactId artifactId, ModuleRevisionId mrid) {
      this(artifactId, mrid, (Map)null);
   }

   public ArtifactRevisionId(ArtifactId artfId, ModuleRevisionId mdlRevId, Map extraAttributes) {
      super((Map)null, extraAttributes);
      this.artifactId = artfId;
      this.mrid = mdlRevId;
      this.setStandardAttribute("organisation", this.getModuleRevisionId().getOrganisation());
      this.setStandardAttribute("module", this.getModuleRevisionId().getName());
      this.setStandardAttribute("revision", this.getModuleRevisionId().getRevision());
      this.setStandardAttribute("artifact", this.getName());
      this.setStandardAttribute("type", this.getType());
      this.setStandardAttribute("ext", this.getExt());
   }

   public boolean equals(Object obj) {
      if (!(obj instanceof ArtifactRevisionId)) {
         return false;
      } else {
         ArtifactRevisionId arid = (ArtifactRevisionId)obj;
         return this.getArtifactId().equals(arid.getArtifactId()) && this.getModuleRevisionId().equals(arid.getModuleRevisionId()) && this.getQualifiedExtraAttributes().equals(arid.getQualifiedExtraAttributes());
      }
   }

   public int hashCode() {
      int hash = 17;
      hash += this.getArtifactId().hashCode() * 37;
      hash += this.getModuleRevisionId().hashCode() * 37;
      hash += this.getQualifiedExtraAttributes().hashCode() * 37;
      return hash;
   }

   public String toString() {
      return this.getModuleRevisionId() + "!" + this.getName() + "." + this.getExt() + (this.getType().equals(this.getExt()) ? "" : "(" + this.getType() + ")");
   }

   public ArtifactId getArtifactId() {
      return this.artifactId;
   }

   public ModuleRevisionId getModuleRevisionId() {
      return this.mrid;
   }

   public String getName() {
      return this.artifactId.getName();
   }

   public String getType() {
      return this.artifactId.getType();
   }

   public String getExt() {
      return this.artifactId.getExt();
   }

   public String getRevision() {
      return this.mrid.getRevision();
   }
}
