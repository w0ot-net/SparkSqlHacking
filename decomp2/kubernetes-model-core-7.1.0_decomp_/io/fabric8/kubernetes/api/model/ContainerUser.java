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
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"linux"})
public class ContainerUser implements Editable, KubernetesResource {
   @JsonProperty("linux")
   private LinuxContainerUser linux;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public ContainerUser() {
   }

   public ContainerUser(LinuxContainerUser linux) {
      this.linux = linux;
   }

   @JsonProperty("linux")
   public LinuxContainerUser getLinux() {
      return this.linux;
   }

   @JsonProperty("linux")
   public void setLinux(LinuxContainerUser linux) {
      this.linux = linux;
   }

   @JsonIgnore
   public ContainerUserBuilder edit() {
      return new ContainerUserBuilder(this);
   }

   @JsonIgnore
   public ContainerUserBuilder toBuilder() {
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
      LinuxContainerUser var10000 = this.getLinux();
      return "ContainerUser(linux=" + var10000 + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ContainerUser)) {
         return false;
      } else {
         ContainerUser other = (ContainerUser)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$linux = this.getLinux();
            Object other$linux = other.getLinux();
            if (this$linux == null) {
               if (other$linux != null) {
                  return false;
               }
            } else if (!this$linux.equals(other$linux)) {
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
      return other instanceof ContainerUser;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $linux = this.getLinux();
      result = result * 59 + ($linux == null ? 43 : $linux.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
