package io.fabric8.kubernetes.api.model;

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
import io.fabric8.kubernetes.model.annotation.Group;
import io.fabric8.kubernetes.model.annotation.Version;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"apiVersion", "kind", "dryRun", "fieldManager", "fieldValidation"})
@Version("v1")
@Group("")
public class UpdateOptions implements Editable, KubernetesResource {
   @JsonProperty("apiVersion")
   private String apiVersion = "v1";
   @JsonProperty("dryRun")
   @JsonInclude(Include.NON_EMPTY)
   private List dryRun = new ArrayList();
   @JsonProperty("fieldManager")
   private String fieldManager;
   @JsonProperty("fieldValidation")
   private String fieldValidation;
   @JsonProperty("kind")
   private String kind = "UpdateOptions";
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public UpdateOptions() {
   }

   public UpdateOptions(String apiVersion, List dryRun, String fieldManager, String fieldValidation, String kind) {
      this.apiVersion = apiVersion;
      this.dryRun = dryRun;
      this.fieldManager = fieldManager;
      this.fieldValidation = fieldValidation;
      this.kind = kind;
   }

   @JsonProperty("apiVersion")
   public String getApiVersion() {
      return this.apiVersion;
   }

   @JsonProperty("apiVersion")
   public void setApiVersion(String apiVersion) {
      this.apiVersion = apiVersion;
   }

   @JsonProperty("dryRun")
   @JsonInclude(Include.NON_EMPTY)
   public List getDryRun() {
      return this.dryRun;
   }

   @JsonProperty("dryRun")
   public void setDryRun(List dryRun) {
      this.dryRun = dryRun;
   }

   @JsonProperty("fieldManager")
   public String getFieldManager() {
      return this.fieldManager;
   }

   @JsonProperty("fieldManager")
   public void setFieldManager(String fieldManager) {
      this.fieldManager = fieldManager;
   }

   @JsonProperty("fieldValidation")
   public String getFieldValidation() {
      return this.fieldValidation;
   }

   @JsonProperty("fieldValidation")
   public void setFieldValidation(String fieldValidation) {
      this.fieldValidation = fieldValidation;
   }

   @JsonProperty("kind")
   public String getKind() {
      return this.kind;
   }

   @JsonProperty("kind")
   public void setKind(String kind) {
      this.kind = kind;
   }

   @JsonIgnore
   public UpdateOptionsBuilder edit() {
      return new UpdateOptionsBuilder(this);
   }

   @JsonIgnore
   public UpdateOptionsBuilder toBuilder() {
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
      String var10000 = this.getApiVersion();
      return "UpdateOptions(apiVersion=" + var10000 + ", dryRun=" + this.getDryRun() + ", fieldManager=" + this.getFieldManager() + ", fieldValidation=" + this.getFieldValidation() + ", kind=" + this.getKind() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof UpdateOptions)) {
         return false;
      } else {
         UpdateOptions other = (UpdateOptions)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$apiVersion = this.getApiVersion();
            Object other$apiVersion = other.getApiVersion();
            if (this$apiVersion == null) {
               if (other$apiVersion != null) {
                  return false;
               }
            } else if (!this$apiVersion.equals(other$apiVersion)) {
               return false;
            }

            Object this$dryRun = this.getDryRun();
            Object other$dryRun = other.getDryRun();
            if (this$dryRun == null) {
               if (other$dryRun != null) {
                  return false;
               }
            } else if (!this$dryRun.equals(other$dryRun)) {
               return false;
            }

            Object this$fieldManager = this.getFieldManager();
            Object other$fieldManager = other.getFieldManager();
            if (this$fieldManager == null) {
               if (other$fieldManager != null) {
                  return false;
               }
            } else if (!this$fieldManager.equals(other$fieldManager)) {
               return false;
            }

            Object this$fieldValidation = this.getFieldValidation();
            Object other$fieldValidation = other.getFieldValidation();
            if (this$fieldValidation == null) {
               if (other$fieldValidation != null) {
                  return false;
               }
            } else if (!this$fieldValidation.equals(other$fieldValidation)) {
               return false;
            }

            Object this$kind = this.getKind();
            Object other$kind = other.getKind();
            if (this$kind == null) {
               if (other$kind != null) {
                  return false;
               }
            } else if (!this$kind.equals(other$kind)) {
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
      return other instanceof UpdateOptions;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $apiVersion = this.getApiVersion();
      result = result * 59 + ($apiVersion == null ? 43 : $apiVersion.hashCode());
      Object $dryRun = this.getDryRun();
      result = result * 59 + ($dryRun == null ? 43 : $dryRun.hashCode());
      Object $fieldManager = this.getFieldManager();
      result = result * 59 + ($fieldManager == null ? 43 : $fieldManager.hashCode());
      Object $fieldValidation = this.getFieldValidation();
      result = result * 59 + ($fieldValidation == null ? 43 : $fieldValidation.hashCode());
      Object $kind = this.getKind();
      result = result * 59 + ($kind == null ? 43 : $kind.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
