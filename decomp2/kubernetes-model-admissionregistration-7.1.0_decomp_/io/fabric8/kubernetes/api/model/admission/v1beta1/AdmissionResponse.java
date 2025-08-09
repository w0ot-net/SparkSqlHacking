package io.fabric8.kubernetes.api.model.admission.v1beta1;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.fabric8.kubernetes.api.builder.Editable;
import io.fabric8.kubernetes.api.model.KubernetesResource;
import io.fabric8.kubernetes.api.model.Status;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"allowed", "auditAnnotations", "patch", "patchType", "status", "uid", "warnings"})
public class AdmissionResponse implements Editable, KubernetesResource {
   @JsonProperty("allowed")
   private Boolean allowed;
   @JsonProperty("auditAnnotations")
   @JsonInclude(Include.NON_EMPTY)
   private Map auditAnnotations = new LinkedHashMap();
   @JsonProperty("patch")
   private String patch;
   @JsonProperty("patchType")
   private String patchType;
   @JsonProperty("status")
   private Status status;
   @JsonProperty("uid")
   private String uid;
   @JsonProperty("warnings")
   @JsonInclude(Include.NON_EMPTY)
   private List warnings = new ArrayList();
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public AdmissionResponse() {
   }

   public AdmissionResponse(Boolean allowed, Map auditAnnotations, String patch, String patchType, Status status, String uid, List warnings) {
      this.allowed = allowed;
      this.auditAnnotations = auditAnnotations;
      this.patch = patch;
      this.patchType = patchType;
      this.status = status;
      this.uid = uid;
      this.warnings = warnings;
   }

   @JsonProperty("allowed")
   public Boolean getAllowed() {
      return this.allowed;
   }

   @JsonProperty("allowed")
   public void setAllowed(Boolean allowed) {
      this.allowed = allowed;
   }

   @JsonProperty("auditAnnotations")
   @JsonInclude(Include.NON_EMPTY)
   public Map getAuditAnnotations() {
      return this.auditAnnotations;
   }

   @JsonProperty("auditAnnotations")
   public void setAuditAnnotations(Map auditAnnotations) {
      this.auditAnnotations = auditAnnotations;
   }

   @JsonProperty("patch")
   public String getPatch() {
      return this.patch;
   }

   @JsonProperty("patch")
   public void setPatch(String patch) {
      this.patch = patch;
   }

   @JsonProperty("patchType")
   public String getPatchType() {
      return this.patchType;
   }

   @JsonProperty("patchType")
   public void setPatchType(String patchType) {
      this.patchType = patchType;
   }

   @JsonProperty("status")
   public Status getStatus() {
      return this.status;
   }

   @JsonProperty("status")
   public void setStatus(Status status) {
      this.status = status;
   }

   @JsonProperty("uid")
   public String getUid() {
      return this.uid;
   }

   @JsonProperty("uid")
   public void setUid(String uid) {
      this.uid = uid;
   }

   @JsonProperty("warnings")
   @JsonInclude(Include.NON_EMPTY)
   public List getWarnings() {
      return this.warnings;
   }

   @JsonProperty("warnings")
   public void setWarnings(List warnings) {
      this.warnings = warnings;
   }

   @JsonIgnore
   public AdmissionResponseBuilder edit() {
      return new AdmissionResponseBuilder(this);
   }

   @JsonIgnore
   public AdmissionResponseBuilder toBuilder() {
      return this.edit();
   }

   @JsonAnyGetter
   public Map getAdditionalProperties() {
      return this.additionalProperties;
   }

   @JsonAnySetter
   public void setAdditionalProperty(String name, Object value) {
      this.additionalProperties.put(name, value);
   }

   public void setAdditionalProperties(Map additionalProperties) {
      this.additionalProperties = additionalProperties;
   }

   @Generated
   public String toString() {
      Boolean var10000 = this.getAllowed();
      return "AdmissionResponse(allowed=" + var10000 + ", auditAnnotations=" + this.getAuditAnnotations() + ", patch=" + this.getPatch() + ", patchType=" + this.getPatchType() + ", status=" + this.getStatus() + ", uid=" + this.getUid() + ", warnings=" + this.getWarnings() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof AdmissionResponse)) {
         return false;
      } else {
         AdmissionResponse other = (AdmissionResponse)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$allowed = this.getAllowed();
            Object other$allowed = other.getAllowed();
            if (this$allowed == null) {
               if (other$allowed != null) {
                  return false;
               }
            } else if (!this$allowed.equals(other$allowed)) {
               return false;
            }

            Object this$auditAnnotations = this.getAuditAnnotations();
            Object other$auditAnnotations = other.getAuditAnnotations();
            if (this$auditAnnotations == null) {
               if (other$auditAnnotations != null) {
                  return false;
               }
            } else if (!this$auditAnnotations.equals(other$auditAnnotations)) {
               return false;
            }

            Object this$patch = this.getPatch();
            Object other$patch = other.getPatch();
            if (this$patch == null) {
               if (other$patch != null) {
                  return false;
               }
            } else if (!this$patch.equals(other$patch)) {
               return false;
            }

            Object this$patchType = this.getPatchType();
            Object other$patchType = other.getPatchType();
            if (this$patchType == null) {
               if (other$patchType != null) {
                  return false;
               }
            } else if (!this$patchType.equals(other$patchType)) {
               return false;
            }

            Object this$status = this.getStatus();
            Object other$status = other.getStatus();
            if (this$status == null) {
               if (other$status != null) {
                  return false;
               }
            } else if (!this$status.equals(other$status)) {
               return false;
            }

            Object this$uid = this.getUid();
            Object other$uid = other.getUid();
            if (this$uid == null) {
               if (other$uid != null) {
                  return false;
               }
            } else if (!this$uid.equals(other$uid)) {
               return false;
            }

            Object this$warnings = this.getWarnings();
            Object other$warnings = other.getWarnings();
            if (this$warnings == null) {
               if (other$warnings != null) {
                  return false;
               }
            } else if (!this$warnings.equals(other$warnings)) {
               return false;
            }

            Object this$additionalProperties = this.getAdditionalProperties();
            Object other$additionalProperties = other.getAdditionalProperties();
            if (this$additionalProperties == null) {
               if (other$additionalProperties != null) {
                  return false;
               }
            } else if (!this$additionalProperties.equals(other$additionalProperties)) {
               return false;
            }

            return true;
         }
      }
   }

   @Generated
   protected boolean canEqual(Object other) {
      return other instanceof AdmissionResponse;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $allowed = this.getAllowed();
      result = result * 59 + ($allowed == null ? 43 : $allowed.hashCode());
      Object $auditAnnotations = this.getAuditAnnotations();
      result = result * 59 + ($auditAnnotations == null ? 43 : $auditAnnotations.hashCode());
      Object $patch = this.getPatch();
      result = result * 59 + ($patch == null ? 43 : $patch.hashCode());
      Object $patchType = this.getPatchType();
      result = result * 59 + ($patchType == null ? 43 : $patchType.hashCode());
      Object $status = this.getStatus();
      result = result * 59 + ($status == null ? 43 : $status.hashCode());
      Object $uid = this.getUid();
      result = result * 59 + ($uid == null ? 43 : $uid.hashCode());
      Object $warnings = this.getWarnings();
      result = result * 59 + ($warnings == null ? 43 : $warnings.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
