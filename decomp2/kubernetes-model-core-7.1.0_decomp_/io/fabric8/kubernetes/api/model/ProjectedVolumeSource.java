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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"defaultMode", "sources"})
public class ProjectedVolumeSource implements Editable, KubernetesResource {
   @JsonProperty("defaultMode")
   private Integer defaultMode;
   @JsonProperty("sources")
   @JsonInclude(Include.NON_EMPTY)
   private List sources = new ArrayList();
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public ProjectedVolumeSource() {
   }

   public ProjectedVolumeSource(Integer defaultMode, List sources) {
      this.defaultMode = defaultMode;
      this.sources = sources;
   }

   @JsonProperty("defaultMode")
   public Integer getDefaultMode() {
      return this.defaultMode;
   }

   @JsonProperty("defaultMode")
   public void setDefaultMode(Integer defaultMode) {
      this.defaultMode = defaultMode;
   }

   @JsonProperty("sources")
   @JsonInclude(Include.NON_EMPTY)
   public List getSources() {
      return this.sources;
   }

   @JsonProperty("sources")
   public void setSources(List sources) {
      this.sources = sources;
   }

   @JsonIgnore
   public ProjectedVolumeSourceBuilder edit() {
      return new ProjectedVolumeSourceBuilder(this);
   }

   @JsonIgnore
   public ProjectedVolumeSourceBuilder toBuilder() {
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
      Integer var10000 = this.getDefaultMode();
      return "ProjectedVolumeSource(defaultMode=" + var10000 + ", sources=" + this.getSources() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ProjectedVolumeSource)) {
         return false;
      } else {
         ProjectedVolumeSource other = (ProjectedVolumeSource)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$defaultMode = this.getDefaultMode();
            Object other$defaultMode = other.getDefaultMode();
            if (this$defaultMode == null) {
               if (other$defaultMode != null) {
                  return false;
               }
            } else if (!this$defaultMode.equals(other$defaultMode)) {
               return false;
            }

            Object this$sources = this.getSources();
            Object other$sources = other.getSources();
            if (this$sources == null) {
               if (other$sources != null) {
                  return false;
               }
            } else if (!this$sources.equals(other$sources)) {
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
      return other instanceof ProjectedVolumeSource;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $defaultMode = this.getDefaultMode();
      result = result * 59 + ($defaultMode == null ? 43 : $defaultMode.hashCode());
      Object $sources = this.getSources();
      result = result * 59 + ($sources == null ? 43 : $sources.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
