package io.fabric8.kubernetes.api.model.gatewayapi.v1alpha2;

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
@JsonPropertyOrder({"kind", "group", "name", "sectionName"})
public class LocalPolicyTargetReferenceWithSectionName implements Editable, KubernetesResource {
   @JsonProperty("group")
   private String group;
   @JsonProperty("kind")
   private String kind;
   @JsonProperty("name")
   private String name;
   @JsonProperty("sectionName")
   private String sectionName;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public LocalPolicyTargetReferenceWithSectionName() {
   }

   public LocalPolicyTargetReferenceWithSectionName(String group, String kind, String name, String sectionName) {
      this.group = group;
      this.kind = kind;
      this.name = name;
      this.sectionName = sectionName;
   }

   @JsonProperty("group")
   public String getGroup() {
      return this.group;
   }

   @JsonProperty("group")
   public void setGroup(String group) {
      this.group = group;
   }

   @JsonProperty("kind")
   public String getKind() {
      return this.kind;
   }

   @JsonProperty("kind")
   public void setKind(String kind) {
      this.kind = kind;
   }

   @JsonProperty("name")
   public String getName() {
      return this.name;
   }

   @JsonProperty("name")
   public void setName(String name) {
      this.name = name;
   }

   @JsonProperty("sectionName")
   public String getSectionName() {
      return this.sectionName;
   }

   @JsonProperty("sectionName")
   public void setSectionName(String sectionName) {
      this.sectionName = sectionName;
   }

   @JsonIgnore
   public LocalPolicyTargetReferenceWithSectionNameBuilder edit() {
      return new LocalPolicyTargetReferenceWithSectionNameBuilder(this);
   }

   @JsonIgnore
   public LocalPolicyTargetReferenceWithSectionNameBuilder toBuilder() {
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
      String var10000 = this.getGroup();
      return "LocalPolicyTargetReferenceWithSectionName(group=" + var10000 + ", kind=" + this.getKind() + ", name=" + this.getName() + ", sectionName=" + this.getSectionName() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof LocalPolicyTargetReferenceWithSectionName)) {
         return false;
      } else {
         LocalPolicyTargetReferenceWithSectionName other = (LocalPolicyTargetReferenceWithSectionName)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$group = this.getGroup();
            Object other$group = other.getGroup();
            if (this$group == null) {
               if (other$group != null) {
                  return false;
               }
            } else if (!this$group.equals(other$group)) {
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

            Object this$name = this.getName();
            Object other$name = other.getName();
            if (this$name == null) {
               if (other$name != null) {
                  return false;
               }
            } else if (!this$name.equals(other$name)) {
               return false;
            }

            Object this$sectionName = this.getSectionName();
            Object other$sectionName = other.getSectionName();
            if (this$sectionName == null) {
               if (other$sectionName != null) {
                  return false;
               }
            } else if (!this$sectionName.equals(other$sectionName)) {
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
      return other instanceof LocalPolicyTargetReferenceWithSectionName;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $group = this.getGroup();
      result = result * 59 + ($group == null ? 43 : $group.hashCode());
      Object $kind = this.getKind();
      result = result * 59 + ($kind == null ? 43 : $kind.hashCode());
      Object $name = this.getName();
      result = result * 59 + ($name == null ? 43 : $name.hashCode());
      Object $sectionName = this.getSectionName();
      result = result * 59 + ($sectionName == null ? 43 : $sectionName.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
