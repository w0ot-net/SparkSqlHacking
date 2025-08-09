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
import io.fabric8.kubernetes.api.model.Quantity;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"bool", "int", "intSlice", "name", "quantity", "string", "stringSlice", "version"})
public class NamedResourcesAttribute implements Editable, KubernetesResource {
   @JsonProperty("bool")
   private Boolean bool;
   @JsonProperty("int")
   private Long _int;
   @JsonProperty("intSlice")
   private NamedResourcesIntSlice intSlice;
   @JsonProperty("name")
   private String name;
   @JsonProperty("quantity")
   private Quantity quantity;
   @JsonProperty("string")
   private String string;
   @JsonProperty("stringSlice")
   private NamedResourcesStringSlice stringSlice;
   @JsonProperty("version")
   private String version;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public NamedResourcesAttribute() {
   }

   public NamedResourcesAttribute(Boolean bool, Long _int, NamedResourcesIntSlice intSlice, String name, Quantity quantity, String string, NamedResourcesStringSlice stringSlice, String version) {
      this.bool = bool;
      this._int = _int;
      this.intSlice = intSlice;
      this.name = name;
      this.quantity = quantity;
      this.string = string;
      this.stringSlice = stringSlice;
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

   @JsonProperty("intSlice")
   public NamedResourcesIntSlice getIntSlice() {
      return this.intSlice;
   }

   @JsonProperty("intSlice")
   public void setIntSlice(NamedResourcesIntSlice intSlice) {
      this.intSlice = intSlice;
   }

   @JsonProperty("name")
   public String getName() {
      return this.name;
   }

   @JsonProperty("name")
   public void setName(String name) {
      this.name = name;
   }

   @JsonProperty("quantity")
   public Quantity getQuantity() {
      return this.quantity;
   }

   @JsonProperty("quantity")
   public void setQuantity(Quantity quantity) {
      this.quantity = quantity;
   }

   @JsonProperty("string")
   public String getString() {
      return this.string;
   }

   @JsonProperty("string")
   public void setString(String string) {
      this.string = string;
   }

   @JsonProperty("stringSlice")
   public NamedResourcesStringSlice getStringSlice() {
      return this.stringSlice;
   }

   @JsonProperty("stringSlice")
   public void setStringSlice(NamedResourcesStringSlice stringSlice) {
      this.stringSlice = stringSlice;
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
   public NamedResourcesAttributeBuilder edit() {
      return new NamedResourcesAttributeBuilder(this);
   }

   @JsonIgnore
   public NamedResourcesAttributeBuilder toBuilder() {
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
      return "NamedResourcesAttribute(bool=" + var10000 + ", _int=" + this.getInt() + ", intSlice=" + this.getIntSlice() + ", name=" + this.getName() + ", quantity=" + this.getQuantity() + ", string=" + this.getString() + ", stringSlice=" + this.getStringSlice() + ", version=" + this.getVersion() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof NamedResourcesAttribute)) {
         return false;
      } else {
         NamedResourcesAttribute other = (NamedResourcesAttribute)o;
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

            Object this$intSlice = this.getIntSlice();
            Object other$intSlice = other.getIntSlice();
            if (this$intSlice == null) {
               if (other$intSlice != null) {
                  return false;
               }
            } else if (!this$intSlice.equals(other$intSlice)) {
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

            Object this$quantity = this.getQuantity();
            Object other$quantity = other.getQuantity();
            if (this$quantity == null) {
               if (other$quantity != null) {
                  return false;
               }
            } else if (!this$quantity.equals(other$quantity)) {
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

            Object this$stringSlice = this.getStringSlice();
            Object other$stringSlice = other.getStringSlice();
            if (this$stringSlice == null) {
               if (other$stringSlice != null) {
                  return false;
               }
            } else if (!this$stringSlice.equals(other$stringSlice)) {
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
      return other instanceof NamedResourcesAttribute;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $bool = this.getBool();
      result = result * 59 + ($bool == null ? 43 : $bool.hashCode());
      Object $_int = this.getInt();
      result = result * 59 + ($_int == null ? 43 : $_int.hashCode());
      Object $intSlice = this.getIntSlice();
      result = result * 59 + ($intSlice == null ? 43 : $intSlice.hashCode());
      Object $name = this.getName();
      result = result * 59 + ($name == null ? 43 : $name.hashCode());
      Object $quantity = this.getQuantity();
      result = result * 59 + ($quantity == null ? 43 : $quantity.hashCode());
      Object $string = this.getString();
      result = result * 59 + ($string == null ? 43 : $string.hashCode());
      Object $stringSlice = this.getStringSlice();
      result = result * 59 + ($stringSlice == null ? 43 : $stringSlice.hashCode());
      Object $version = this.getVersion();
      result = result * 59 + ($version == null ? 43 : $version.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
