package org.apache.ivy.core.module.id;

public class ArtifactId {
   private ModuleId mid;
   private String name;
   private String type;
   private String ext;

   public ArtifactId(ModuleId mid, String name, String type, String ext) {
      this.mid = mid;
      this.name = name;
      this.type = type;
      this.ext = ext;
   }

   public boolean equals(Object obj) {
      if (!(obj instanceof ArtifactId)) {
         return false;
      } else {
         ArtifactId aid = (ArtifactId)obj;
         return this.getModuleId().equals(aid.getModuleId()) && this.getName().equals(aid.getName()) && this.getExt().equals(aid.getExt()) && this.getType().equals(aid.getType());
      }
   }

   public int hashCode() {
      int hash = 17;
      hash += this.getModuleId().hashCode() * 37;
      hash += this.getName().hashCode() * 37;
      hash += this.getType().hashCode() * 37;
      return hash;
   }

   public String toString() {
      return this.getModuleId() + "!" + this.getShortDescription();
   }

   public String getShortDescription() {
      return this.getName() + "." + this.getExt() + (this.getType().equals(this.getExt()) ? "" : "(" + this.getType() + ")");
   }

   public ModuleId getModuleId() {
      return this.mid;
   }

   public String getName() {
      return this.name;
   }

   public String getType() {
      return this.type;
   }

   public String getExt() {
      return this.ext;
   }
}
