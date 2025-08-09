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
@JsonPropertyOrder({"paths"})
public class RootPaths implements Editable, KubernetesResource {
   @JsonProperty("paths")
   @JsonInclude(Include.NON_EMPTY)
   private List paths = new ArrayList();
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public RootPaths() {
   }

   public RootPaths(List paths) {
      this.paths = paths;
   }

   @JsonProperty("paths")
   @JsonInclude(Include.NON_EMPTY)
   public List getPaths() {
      return this.paths;
   }

   @JsonProperty("paths")
   public void setPaths(List paths) {
      this.paths = paths;
   }

   @JsonIgnore
   public RootPathsBuilder edit() {
      return new RootPathsBuilder(this);
   }

   @JsonIgnore
   public RootPathsBuilder toBuilder() {
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
      List var10000 = this.getPaths();
      return "RootPaths(paths=" + var10000 + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof RootPaths)) {
         return false;
      } else {
         RootPaths other = (RootPaths)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$paths = this.getPaths();
            Object other$paths = other.getPaths();
            if (this$paths == null) {
               if (other$paths != null) {
                  return false;
               }
            } else if (!this$paths.equals(other$paths)) {
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
      return other instanceof RootPaths;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $paths = this.getPaths();
      result = result * 59 + ($paths == null ? 43 : $paths.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
