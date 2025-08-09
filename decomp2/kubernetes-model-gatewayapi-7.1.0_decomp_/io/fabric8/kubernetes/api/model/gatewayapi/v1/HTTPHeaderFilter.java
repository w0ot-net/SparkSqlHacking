package io.fabric8.kubernetes.api.model.gatewayapi.v1;

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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"add", "remove", "set"})
public class HTTPHeaderFilter implements Editable, KubernetesResource {
   @JsonProperty("add")
   @JsonInclude(Include.NON_EMPTY)
   private List add = new ArrayList();
   @JsonProperty("remove")
   @JsonInclude(Include.NON_EMPTY)
   private List remove = new ArrayList();
   @JsonProperty("set")
   @JsonInclude(Include.NON_EMPTY)
   private List set = new ArrayList();
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public HTTPHeaderFilter() {
   }

   public HTTPHeaderFilter(List add, List remove, List set) {
      this.add = add;
      this.remove = remove;
      this.set = set;
   }

   @JsonProperty("add")
   @JsonInclude(Include.NON_EMPTY)
   public List getAdd() {
      return this.add;
   }

   @JsonProperty("add")
   public void setAdd(List add) {
      this.add = add;
   }

   @JsonProperty("remove")
   @JsonInclude(Include.NON_EMPTY)
   public List getRemove() {
      return this.remove;
   }

   @JsonProperty("remove")
   public void setRemove(List remove) {
      this.remove = remove;
   }

   @JsonProperty("set")
   @JsonInclude(Include.NON_EMPTY)
   public List getSet() {
      return this.set;
   }

   @JsonProperty("set")
   public void setSet(List set) {
      this.set = set;
   }

   @JsonIgnore
   public HTTPHeaderFilterBuilder edit() {
      return new HTTPHeaderFilterBuilder(this);
   }

   @JsonIgnore
   public HTTPHeaderFilterBuilder toBuilder() {
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
      List var10000 = this.getAdd();
      return "HTTPHeaderFilter(add=" + var10000 + ", remove=" + this.getRemove() + ", set=" + this.getSet() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof HTTPHeaderFilter)) {
         return false;
      } else {
         HTTPHeaderFilter other = (HTTPHeaderFilter)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$add = this.getAdd();
            Object other$add = other.getAdd();
            if (this$add == null) {
               if (other$add != null) {
                  return false;
               }
            } else if (!this$add.equals(other$add)) {
               return false;
            }

            Object this$remove = this.getRemove();
            Object other$remove = other.getRemove();
            if (this$remove == null) {
               if (other$remove != null) {
                  return false;
               }
            } else if (!this$remove.equals(other$remove)) {
               return false;
            }

            Object this$set = this.getSet();
            Object other$set = other.getSet();
            if (this$set == null) {
               if (other$set != null) {
                  return false;
               }
            } else if (!this$set.equals(other$set)) {
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
      return other instanceof HTTPHeaderFilter;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $add = this.getAdd();
      result = result * 59 + ($add == null ? 43 : $add.hashCode());
      Object $remove = this.getRemove();
      result = result * 59 + ($remove == null ? 43 : $remove.hashCode());
      Object $set = this.getSet();
      result = result * 59 + ($set == null ? 43 : $set.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
