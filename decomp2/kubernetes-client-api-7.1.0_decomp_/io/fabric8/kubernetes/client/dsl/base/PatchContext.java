package io.fabric8.kubernetes.client.dsl.base;

import java.util.List;

public class PatchContext {
   private List dryRun;
   private String fieldManager;
   private Boolean force;
   private PatchType patchType;
   private String fieldValidation;

   public static PatchContext of(PatchType type) {
      return (new Builder()).withPatchType(type).build();
   }

   public List getDryRun() {
      return this.dryRun;
   }

   public void setDryRun(List dryRun) {
      this.dryRun = dryRun;
   }

   public String getFieldManager() {
      return this.fieldManager;
   }

   public void setFieldManager(String fieldManager) {
      this.fieldManager = fieldManager;
   }

   public Boolean getForce() {
      return this.force;
   }

   public void setForce(Boolean force) {
      this.force = force;
   }

   public PatchType getPatchType() {
      return this.patchType;
   }

   public void setPatchType(PatchType patchType) {
      this.patchType = patchType;
   }

   public String getFieldValidation() {
      return this.fieldValidation;
   }

   public void setFieldValidation(String fieldValidation) {
      this.fieldValidation = fieldValidation;
   }

   public static class Builder {
      private final PatchContext patchContext = new PatchContext();

      public Builder withDryRun(List dryRun) {
         this.patchContext.setDryRun(dryRun);
         return this;
      }

      public Builder withFieldManager(String fieldManager) {
         this.patchContext.setFieldManager(fieldManager);
         return this;
      }

      public Builder withForce(Boolean force) {
         this.patchContext.setForce(force);
         return this;
      }

      public Builder withPatchType(PatchType patchType) {
         this.patchContext.setPatchType(patchType);
         return this;
      }

      public Builder withFieldValidation(String fieldValidation) {
         this.patchContext.setFieldValidation(fieldValidation);
         return this;
      }

      public PatchContext build() {
         return this.patchContext;
      }
   }
}
