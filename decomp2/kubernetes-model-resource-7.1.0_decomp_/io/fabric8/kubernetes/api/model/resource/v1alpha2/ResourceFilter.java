package io.fabric8.kubernetes.api.model.resource.v1alpha2;

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
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"driverName", "namedResources"})
public class ResourceFilter implements Editable, KubernetesResource {
   @JsonProperty("driverName")
   private String driverName;
   @JsonProperty("namedResources")
   private NamedResourcesFilter namedResources;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public ResourceFilter() {
   }

   public ResourceFilter(String driverName, NamedResourcesFilter namedResources) {
      this.driverName = driverName;
      this.namedResources = namedResources;
   }

   @JsonProperty("driverName")
   public String getDriverName() {
      return this.driverName;
   }

   @JsonProperty("driverName")
   public void setDriverName(String driverName) {
      this.driverName = driverName;
   }

   @JsonProperty("namedResources")
   public NamedResourcesFilter getNamedResources() {
      return this.namedResources;
   }

   @JsonProperty("namedResources")
   public void setNamedResources(NamedResourcesFilter namedResources) {
      this.namedResources = namedResources;
   }

   @JsonIgnore
   public ResourceFilterBuilder edit() {
      return new ResourceFilterBuilder(this);
   }

   @JsonIgnore
   public ResourceFilterBuilder toBuilder() {
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
      String var10000 = this.getDriverName();
      return "ResourceFilter(driverName=" + var10000 + ", namedResources=" + this.getNamedResources() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ResourceFilter)) {
         return false;
      } else {
         ResourceFilter other = (ResourceFilter)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$driverName = this.getDriverName();
            Object other$driverName = other.getDriverName();
            if (this$driverName == null) {
               if (other$driverName != null) {
                  return false;
               }
            } else if (!this$driverName.equals(other$driverName)) {
               return false;
            }

            Object this$namedResources = this.getNamedResources();
            Object other$namedResources = other.getNamedResources();
            if (this$namedResources == null) {
               if (other$namedResources != null) {
                  return false;
               }
            } else if (!this$namedResources.equals(other$namedResources)) {
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
      return other instanceof ResourceFilter;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $driverName = this.getDriverName();
      result = result * 59 + ($driverName == null ? 43 : $driverName.hashCode());
      Object $namedResources = this.getNamedResources();
      result = result * 59 + ($namedResources == null ? 43 : $namedResources.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
