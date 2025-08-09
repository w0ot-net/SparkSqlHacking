package io.fabric8.kubernetes.api.model.resource.v1beta1;

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
@JsonPropertyOrder({"bool", "int", "string", "version"})
public class DeviceAttribute implements Editable, KubernetesResource {
   @JsonProperty("bool")
   private Boolean bool;
   @JsonProperty("int")
   private Long _int;
   @JsonProperty("string")
   private String string;
   @JsonProperty("version")
   private String version;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public DeviceAttribute() {
   }

   public DeviceAttribute(Boolean bool, Long _int, String string, String version) {
      this.bool = bool;
      this._int = _int;
      this.string = string;
      this.version = version;
   }

   @JsonProperty("bool")
   public Boolean getBool() {
      return this.bool;
   }

   @JsonProperty("bool")
   public void setBool(Boolean bool) {
      this.bool = bool;
   }

   @JsonProperty("int")
   public Long getInt() {
      return this._int;
   }

   @JsonProperty("int")
   public void setInt(Long _int) {
      this._int = _int;
   }

   @JsonProperty("string")
   public String getString() {
      return this.string;
   }

   @JsonProperty("string")
   public void setString(String string) {
      this.string = string;
   }

   @JsonProperty("version")
   public String getVersion() {
      return this.version;
   }

   @JsonProperty("version")
   public void setVersion(String version) {
      this.version = version;
   }

   @JsonIgnore
   public DeviceAttributeBuilder edit() {
      return new DeviceAttributeBuilder(this);
   }

   @JsonIgnore
   public DeviceAttributeBuilder toBuilder() {
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
      Boolean var10000 = this.getBool();
      return "DeviceAttribute(bool=" + var10000 + ", _int=" + this.getInt() + ", string=" + this.getString() + ", version=" + this.getVersion() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof DeviceAttribute)) {
         return false;
      } else {
         DeviceAttribute other = (DeviceAttribute)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$bool = this.getBool();
            Object other$bool = other.getBool();
            if (this$bool == null) {
               if (other$bool != null) {
                  return false;
               }
            } else if (!this$bool.equals(other$bool)) {
               return false;
            }

            Object this$_int = this.getInt();
            Object other$_int = other.getInt();
            if (this$_int == null) {
               if (other$_int != null) {
                  return false;
               }
            } else if (!this$_int.equals(other$_int)) {
               return false;
            }

            Object this$string = this.getString();
            Object other$string = other.getString();
            if (this$string == null) {
               if (other$string != null) {
                  return false;
               }
            } else if (!this$string.equals(other$string)) {
               return false;
            }

            Object this$version = this.getVersion();
            Object other$version = other.getVersion();
            if (this$version == null) {
               if (other$version != null) {
                  return false;
               }
            } else if (!this$version.equals(other$version)) {
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
      return other instanceof DeviceAttribute;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $bool = this.getBool();
      result = result * 59 + ($bool == null ? 43 : $bool.hashCode());
      Object $_int = this.getInt();
      result = result * 59 + ($_int == null ? 43 : $_int.hashCode());
      Object $string = this.getString();
      result = result * 59 + ($string == null ? 43 : $string.hashCode());
      Object $version = this.getVersion();
      result = result * 59 + ($version == null ? 43 : $version.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
