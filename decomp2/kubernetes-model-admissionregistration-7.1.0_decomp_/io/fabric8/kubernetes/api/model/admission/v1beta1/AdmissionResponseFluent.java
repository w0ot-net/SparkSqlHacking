package io.fabric8.kubernetes.api.model.admission.v1beta1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.model.Status;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public class AdmissionResponseFluent extends BaseFluent {
   private Boolean allowed;
   private Map auditAnnotations;
   private String patch;
   private String patchType;
   private Status status;
   private String uid;
   private List warnings = new ArrayList();
   private Map additionalProperties;

   public AdmissionResponseFluent() {
   }

   public AdmissionResponseFluent(AdmissionResponse instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(AdmissionResponse instance) {
      instance = instance != null ? instance : new AdmissionResponse();
      if (instance != null) {
         this.withAllowed(instance.getAllowed());
         this.withAuditAnnotations(instance.getAuditAnnotations());
         this.withPatch(instance.getPatch());
         this.withPatchType(instance.getPatchType());
         this.withStatus(instance.getStatus());
         this.withUid(instance.getUid());
         this.withWarnings(instance.getWarnings());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public Boolean getAllowed() {
      return this.allowed;
   }

   public AdmissionResponseFluent withAllowed(Boolean allowed) {
      this.allowed = allowed;
      return this;
   }

   public boolean hasAllowed() {
      return this.allowed != null;
   }

   public AdmissionResponseFluent addToAuditAnnotations(String key, String value) {
      if (this.auditAnnotations == null && key != null && value != null) {
         this.auditAnnotations = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.auditAnnotations.put(key, value);
      }

      return this;
   }

   public AdmissionResponseFluent addToAuditAnnotations(Map map) {
      if (this.auditAnnotations == null && map != null) {
         this.auditAnnotations = new LinkedHashMap();
      }

      if (map != null) {
         this.auditAnnotations.putAll(map);
      }

      return this;
   }

   public AdmissionResponseFluent removeFromAuditAnnotations(String key) {
      if (this.auditAnnotations == null) {
         return this;
      } else {
         if (key != null && this.auditAnnotations != null) {
            this.auditAnnotations.remove(key);
         }

         return this;
      }
   }

   public AdmissionResponseFluent removeFromAuditAnnotations(Map map) {
      if (this.auditAnnotations == null) {
         return this;
      } else {
         if (map != null) {
            for(Object key : map.keySet()) {
               if (this.auditAnnotations != null) {
                  this.auditAnnotations.remove(key);
               }
            }
         }

         return this;
      }
   }

   public Map getAuditAnnotations() {
      return this.auditAnnotations;
   }

   public AdmissionResponseFluent withAuditAnnotations(Map auditAnnotations) {
      if (auditAnnotations == null) {
         this.auditAnnotations = null;
      } else {
         this.auditAnnotations = new LinkedHashMap(auditAnnotations);
      }

      return this;
   }

   public boolean hasAuditAnnotations() {
      return this.auditAnnotations != null;
   }

   public String getPatch() {
      return this.patch;
   }

   public AdmissionResponseFluent withPatch(String patch) {
      this.patch = patch;
      return this;
   }

   public boolean hasPatch() {
      return this.patch != null;
   }

   public String getPatchType() {
      return this.patchType;
   }

   public AdmissionResponseFluent withPatchType(String patchType) {
      this.patchType = patchType;
      return this;
   }

   public boolean hasPatchType() {
      return this.patchType != null;
   }

   public Status getStatus() {
      return this.status;
   }

   public AdmissionResponseFluent withStatus(Status status) {
      this.status = status;
      return this;
   }

   public boolean hasStatus() {
      return this.status != null;
   }

   public String getUid() {
      return this.uid;
   }

   public AdmissionResponseFluent withUid(String uid) {
      this.uid = uid;
      return this;
   }

   public boolean hasUid() {
      return this.uid != null;
   }

   public AdmissionResponseFluent addToWarnings(int index, String item) {
      if (this.warnings == null) {
         this.warnings = new ArrayList();
      }

      this.warnings.add(index, item);
      return this;
   }

   public AdmissionResponseFluent setToWarnings(int index, String item) {
      if (this.warnings == null) {
         this.warnings = new ArrayList();
      }

      this.warnings.set(index, item);
      return this;
   }

   public AdmissionResponseFluent addToWarnings(String... items) {
      if (this.warnings == null) {
         this.warnings = new ArrayList();
      }

      for(String item : items) {
         this.warnings.add(item);
      }

      return this;
   }

   public AdmissionResponseFluent addAllToWarnings(Collection items) {
      if (this.warnings == null) {
         this.warnings = new ArrayList();
      }

      for(String item : items) {
         this.warnings.add(item);
      }

      return this;
   }

   public AdmissionResponseFluent removeFromWarnings(String... items) {
      if (this.warnings == null) {
         return this;
      } else {
         for(String item : items) {
            this.warnings.remove(item);
         }

         return this;
      }
   }

   public AdmissionResponseFluent removeAllFromWarnings(Collection items) {
      if (this.warnings == null) {
         return this;
      } else {
         for(String item : items) {
            this.warnings.remove(item);
         }

         return this;
      }
   }

   public List getWarnings() {
      return this.warnings;
   }

   public String getWarning(int index) {
      return (String)this.warnings.get(index);
   }

   public String getFirstWarning() {
      return (String)this.warnings.get(0);
   }

   public String getLastWarning() {
      return (String)this.warnings.get(this.warnings.size() - 1);
   }

   public String getMatchingWarning(Predicate predicate) {
      for(String item : this.warnings) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingWarning(Predicate predicate) {
      for(String item : this.warnings) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public AdmissionResponseFluent withWarnings(List warnings) {
      if (warnings != null) {
         this.warnings = new ArrayList();

         for(String item : warnings) {
            this.addToWarnings(item);
         }
      } else {
         this.warnings = null;
      }

      return this;
   }

   public AdmissionResponseFluent withWarnings(String... warnings) {
      if (this.warnings != null) {
         this.warnings.clear();
         this._visitables.remove("warnings");
      }

      if (warnings != null) {
         for(String item : warnings) {
            this.addToWarnings(item);
         }
      }

      return this;
   }

   public boolean hasWarnings() {
      return this.warnings != null && !this.warnings.isEmpty();
   }

   public AdmissionResponseFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public AdmissionResponseFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public AdmissionResponseFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public AdmissionResponseFluent removeFromAdditionalProperties(Map map) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (map != null) {
            for(Object key : map.keySet()) {
               if (this.additionalProperties != null) {
                  this.additionalProperties.remove(key);
               }
            }
         }

         return this;
      }
   }

   public Map getAdditionalProperties() {
      return this.additionalProperties;
   }

   public AdmissionResponseFluent withAdditionalProperties(Map additionalProperties) {
      if (additionalProperties == null) {
         this.additionalProperties = null;
      } else {
         this.additionalProperties = new LinkedHashMap(additionalProperties);
      }

      return this;
   }

   public boolean hasAdditionalProperties() {
      return this.additionalProperties != null;
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         if (!super.equals(o)) {
            return false;
         } else {
            AdmissionResponseFluent that = (AdmissionResponseFluent)o;
            if (!Objects.equals(this.allowed, that.allowed)) {
               return false;
            } else if (!Objects.equals(this.auditAnnotations, that.auditAnnotations)) {
               return false;
            } else if (!Objects.equals(this.patch, that.patch)) {
               return false;
            } else if (!Objects.equals(this.patchType, that.patchType)) {
               return false;
            } else if (!Objects.equals(this.status, that.status)) {
               return false;
            } else if (!Objects.equals(this.uid, that.uid)) {
               return false;
            } else if (!Objects.equals(this.warnings, that.warnings)) {
               return false;
            } else {
               return Objects.equals(this.additionalProperties, that.additionalProperties);
            }
         }
      } else {
         return false;
      }
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.allowed, this.auditAnnotations, this.patch, this.patchType, this.status, this.uid, this.warnings, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.allowed != null) {
         sb.append("allowed:");
         sb.append(this.allowed + ",");
      }

      if (this.auditAnnotations != null && !this.auditAnnotations.isEmpty()) {
         sb.append("auditAnnotations:");
         sb.append(this.auditAnnotations + ",");
      }

      if (this.patch != null) {
         sb.append("patch:");
         sb.append(this.patch + ",");
      }

      if (this.patchType != null) {
         sb.append("patchType:");
         sb.append(this.patchType + ",");
      }

      if (this.status != null) {
         sb.append("status:");
         sb.append(this.status + ",");
      }

      if (this.uid != null) {
         sb.append("uid:");
         sb.append(this.uid + ",");
      }

      if (this.warnings != null && !this.warnings.isEmpty()) {
         sb.append("warnings:");
         sb.append(this.warnings + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public AdmissionResponseFluent withAllowed() {
      return this.withAllowed(true);
   }
}
